package com.ge.med.mobile.nursing.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.Myapp;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.TongZhiBean;
import com.ge.med.mobile.nursing.dao.entity.TongZhiShanChuBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.forjson.entity.AssessJSONBean;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.activity.JiaoBanSJActivity;
import com.ge.med.mobile.nursing.ui.activity.MainActivity;
import com.ge.med.mobile.nursing.ui.activity.PG_DTMainActivity;
import com.ge.med.mobile.nursing.ui.activity.PG_ZJMActivity;
import com.ge.med.mobile.nursing.ui.activity.VitalSignSheetActivity;
import com.ge.med.mobile.nursing.ui.adapter.TongZhiLvItemAdapter;
import com.ge.med.mobile.nursing.ui.view.JiaZaiDialog;
import com.ge.med.mobile.nursing.utils.TipHelper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class TZFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView mTzLv;
    private TongZhiLvItemAdapter tongZhiLvItemAdapter;
    private SwipeRefreshLayout mSyTongzhiSwipeRefresh;
    public RequestCall mTongZhiRec;
    private MainActivity mainActivity;
    public StringCallback stringCallback;
    private String userid;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    TongZhiCaoZuo();
                    break;
            }

        }
    };


    private List<TongZhiBean.DataBean> mItems;
    private SharePLogin sharePLogin;
    private Bundle mBundle;
    private JiaZaiDialog jiaZaiDialog;


    public TongZhiLvItemAdapter getTongZhiLvItemAdapter() {
        return tongZhiLvItemAdapter;
    }

    public void setTongZhiLvItemAdapter(TongZhiLvItemAdapter tongZhiLvItemAdapter) {
        this.tongZhiLvItemAdapter = tongZhiLvItemAdapter;
    }

    public TZFragment() {
        // Required empty public constructor
    }

    @Override
    public int setRootView() {
        return R.layout.fragment_tz;
    }

    @Override
    public void initViews() {
        mBundle = new Bundle();
        sharePLogin = new SharePLogin(mActivitySelf);
        mainActivity = (MainActivity) mActivitySelf;
        mTzLv = (ListView) mRootView.findViewById(R.id.tz_lv);
        mSyTongzhiSwipeRefresh = (SwipeRefreshLayout) mRootView.findViewById(R.id.sy_tongzhi_swipe_refresh);
        tongZhiLvItemAdapter = new TongZhiLvItemAdapter(mActivitySelf, this);
        mTzLv.setAdapter(tongZhiLvItemAdapter);

    }

    public void lvClick(int position) {
        if (tongZhiLvItemAdapter != null && tongZhiLvItemAdapter.getItem(position) != null) {
            final TongZhiBean.DataBean dataBean = tongZhiLvItemAdapter.getItem(position);
            if (Constant.NOTIFY_TYPE_ORDER == dataBean.getNotifyType()) {
                yizhuTiaoZhuan(dataBean, sharePLogin);
            } else if (Constant.NOTIFY_TYPE_VITAL == dataBean.getNotifyType()) {
                tiZhengTiaoZhuan(dataBean);
            } else if (Constant.NOTIFY_TYPE_ASSESS == dataBean.getNotifyType()) {
                // TODO: 2017/4/16 评估措施跳转
                pingGuTiaoZhuan(dataBean);
            } else if (Constant.NOTIFY_TYPE_JIAOBAN == dataBean.getNotifyType()) {
               jiaobanChuLi(dataBean);
            }

        }
    }

    private void jiaobanChuLi(TongZhiBean.DataBean dataBean) {
        List<TongZhiBean.DataBean> objects = tongZhiLvItemAdapter.getObjects();
        objects.remove(dataBean);
        tongZhiLvItemAdapter.setObjects(objects);
        tongZhiLvItemAdapter.notifyDataSetChanged();
        tongZhiShanChu(dataBean);
        ArrayList<CharSequence> patientidlist = new ArrayList<>();
        if(dataBean!=null && dataBean.getPatient()!= null && dataBean.getPatient().getPatientid()!=null){
            patientidlist.add(dataBean.getPatient().getPatientid());
        }
        mBundle.putCharSequenceArrayList(Constant.BUNDLE_KEY_LIST_HUANZHE, patientidlist);
        goToActivity(JiaoBanSJActivity.class, mBundle);
    }

    private void tongZhiShanChu(TongZhiBean.DataBean dataBean) {
        if(dataBean!=null){
            userid = sharePLogin.getUserid();
            List<TongZhiShanChuBean> tongZhiShanChuBeen = new ArrayList<>();
            tongZhiShanChuBeen.add(new TongZhiShanChuBean(userid, dataBean.getNotifyId() + "", "2"));
            String s = JSON.toJSONString(tongZhiShanChuBeen);
            OkHttpUtils.post()
                    .url(URL.TZ_SHANCHU)
                    .addHeader("User-Agent", "www.gs.com")
                    .addParams(Constant.GLOBAL_KEY_SUBMIT_JSON, s)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtil.e(e.getMessage()+"");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            LogUtil.d(response+"");
                        }
                    });
        }
    }

    private void pingGuTiaoZhuan(final TongZhiBean.DataBean dataBean) {
        if (dataBean != null && dataBean.getPatient() != null && dataBean.getPatient().getPatientid() != null) {
            DBHuanZheLieBiao dbHuanZheLieBiao = new HuanZheLieBiaoImpl().getDBHuanZheLieBiao(dataBean.getPatient().getPatientid());
            HuanZheLieBiaoBean.DataBean dataBean1;
            if (dbHuanZheLieBiao != null) {
                dataBean1 = new HuanZheLieBiaoImpl().getDataBean(dbHuanZheLieBiao);
                mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, dataBean1);
                if (Constant.NOTIFY_SUB_TYPE_ASSESS_COMMON == dataBean.getNotifySubType()) {
                    AssessDefine.DataBean assessDefine = new AssessDaoImpl().getAssessDefineById(dataBean.getRedirectList().get(0));
                    mBundle.putSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE, assessDefine);
                    goToActivity(PG_DTMainActivity.class, mBundle);
                } else {
                    mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_HOUJI_CUOSHI);
                    mBundle.putString(Constant.BUNDLE_KEY_FRAGMENT_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_HOUJI_CUOSHI);
                    jiaZaiDialog = new JiaZaiDialog(mActivitySelf);
                    jiaZaiDialog.show();
                    if (dataBean.getBusinessId() != null) {
                        OkHttpUtils.get().url(URL.URL_ASSESS_SINGLE).addHeader("User-Agent", "www.gs.com")
                                .addParams(Constant.GLOBAL_KEY_ID, dataBean.getBusinessId().toString()).build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        jiaZaiDialog.cancel();
                                        showToastShort("服务器数据异常!");
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        AssessJSONBean assessJSONBean = null;
                                        if (response != null) {
                                            assessJSONBean = JSON.parseObject(response, AssessJSONBean.class);
                                        }
                                        jiaZaiDialog.cancel();
                                        List<AssessDefine.DataBean> assessDefineList = new AssessDaoImpl().findAllAssessDefine();
                                        if (null != assessDefineList && assessJSONBean != null && assessJSONBean.getData() != null) {
                                            for (AssessDefine.DataBean assessDefine : assessDefineList) {
                                                if (assessDefine.getId() == assessJSONBean.getData().getAssessDefineId()) {
                                                    List<TongZhiBean.DataBean> objects = tongZhiLvItemAdapter.getObjects();
                                                    objects.remove(dataBean);
                                                    tongZhiLvItemAdapter.setObjects(objects);
                                                    tongZhiLvItemAdapter.notifyDataSetChanged();
                                                    mBundle.putSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE, assessDefine);
                                                    mBundle.putSerializable(Constant.BUNDLE_KEY_ASSESS, assessJSONBean.getData());
                                                    tongZhiShanChu(dataBean);
                                                    goToActivity(PG_ZJMActivity.class, mBundle);
                                                    break;
                                                }
                                            }
                                        }else{
                                            showToastShort("服务器数据异常!");
                                        }
                                    }
                                });
                    }
                }
            }
        }
    }

    //体征跳珠
    private void tiZhengTiaoZhuan(TongZhiBean.DataBean dataBean) {
        List<TongZhiBean.DataBean> objects = tongZhiLvItemAdapter.getObjects();
        objects.remove(dataBean);
        tongZhiLvItemAdapter.setObjects(objects);
        tongZhiLvItemAdapter.notifyDataSetChanged();
        Bundle mBundle = mainActivity.mBundle;
        TongZhiBean.DataBean.PatientBean patient = dataBean.getPatient();
        if (patient != null && patient.getPatientid() != null) {
            HuanZheLieBiaoImpl huanZheLieBiao = new HuanZheLieBiaoImpl();
            DBHuanZheLieBiao dbHuanZheLieBiao = huanZheLieBiao.getDBHuanZheLieBiao(patient.getPatientid());
            HuanZheLieBiaoBean.DataBean dataBean1 = huanZheLieBiao.getDataBean(dbHuanZheLieBiao);
            mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, dataBean1);
        }
        mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SINGLE_PATIENT, false);
        mBundle.putIntegerArrayList(Constant.BUNDLE_KEY_VITAL_GROUP, dataBean.getRedirectList());
        tongZhiShanChu(dataBean);
        goToActivity(VitalSignSheetActivity.class, mBundle);
    }

    private void yizhuTiaoZhuan(TongZhiBean.DataBean dataBean, SharePLogin sharePLogin) {
        List<TongZhiBean.DataBean> objects = tongZhiLvItemAdapter.getObjects();
        objects.remove(dataBean);
        tongZhiLvItemAdapter.setObjects(objects);
        tongZhiLvItemAdapter.notifyDataSetChanged();
        if (dataBean != null) {
            int i = 0;
            try {
                i = Integer.parseInt(dataBean.getOrderNotify().getHisOrderId());
            } catch (NumberFormatException e) {
                LogUtil.e(e.getMessage());
            }
            tongZhiShanChu(dataBean);
            mainActivity.afterDoctorOrderScanned(i);
        }
    }

    @Override
    public void initDatas() {
        mSyTongzhiSwipeRefresh.setOnRefreshListener(this);
    }

    public void setSYNetForJSON(final String user_id, final String token) {
        //通知 网络访问
        mTongZhiRec = OkHttpUtils
                .post()
                .url(URL.URL_TONGZHI)
                .addHeader("User-Agent", "www.gs.com")
                .addParams("userId", user_id + "")
                .addParams("token", token + "")
                .build();
        stringCallback = new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
                mSyTongzhiSwipeRefresh.setRefreshing(false);
                showToastShort("user_id" + user_id);
                showToastShort("url= " + URL.URL_TONGZHI);
            }

            @Override
            public void onResponse(String response, int id) {
                //
                LogUtil.d("tongzhi response :" + response);
                LogUtil.d("tongzhi response token:" + token);
                TongZhiBean tongZhiBean = null;
                try {
                    tongZhiBean = JSON.parseObject(response, TongZhiBean.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                List<TongZhiBean.DataBean> data = null;

                if (tongZhiBean != null) {
                    data = tongZhiBean.getData();
                }
                mSyTongzhiSwipeRefresh.setRefreshing(false);
                if (tongZhiLvItemAdapter == null) {
                    tongZhiLvItemAdapter = new TongZhiLvItemAdapter(mActivitySelf, TZFragment.this);
                    mTzLv.setAdapter(tongZhiLvItemAdapter);
                }
                if (data != null) {
                    tongZhiLvItemAdapter.setObjects(data);
                    tongZhiLvItemAdapter.notifyDataSetChanged();
                }
                handler.sendEmptyMessage(1);
            }
        };
        mTongZhiRec.execute(stringCallback);
    }

    private void TongZhiCaoZuo() {
        try {
            mItems = tongZhiLvItemAdapter.getObjects();
            if (mItems != null && mItems.size() > 0) {
                if (Myapp.playSound) {
                    TipHelper.PlaySound(mActivitySelf);
                }
                if (Myapp.vibrate) {
                    LogUtil.e("handler---->4");
                    TipHelper.Vibrate(mActivitySelf, new long[]{1000, 1000}, false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtil.d("init Thread ....");
                if (mTongZhiRec != null) {
                    mTongZhiRec.execute(stringCallback);
                }
            }
        }).start();
    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    @Override
    public void onRefresh() {
        if (mTongZhiRec != null) {
            mTongZhiRec.execute(stringCallback);
        }
    }
}
