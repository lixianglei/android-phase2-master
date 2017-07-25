package com.ge.med.mobile.nursing.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.activity.PG_DTMainActivity;
import com.ge.med.mobile.nursing.ui.activity.PG_ZJMActivity;
import com.ge.med.mobile.nursing.ui.adapter.PingGuZjmLvItemAdapter;
import com.ge.med.mobile.nursing.ui.view.dialog.PatientTypeDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;

/**
 * 新建评估
 */
public class PG_ZYMFragment extends BaseFragment {
    private ListView mPingGuZjmLv;
    private PingGuZjmLvItemAdapter pingGuZjmLvItemAdapter;
    private Bundle mBundle;
    private final List<AssessDefine.DataBean> mList = new ArrayList<>();
    // 退出本界面
    private static final int CODE_FINISH = 1;

    @Override
    public int setRootView() {
        return R.layout.fragment_pg__zym;
    }

    @Override
    public void initViews() {
        PG_ZJMActivity activity = (PG_ZJMActivity) this.getActivity();
        mBundle = activity.getmBundle();
        pingGuChuLi();
    }

    private void pingGuChuLi() {
        mList.clear();
        List<AssessDefine.DataBean> assessDefineList = new AssessDaoImpl().findAllAssessDefine();
        if (null != assessDefineList) {
            for (AssessDefine.DataBean assessDefine : assessDefineList) {
                mList.add(assessDefine);
            }
            Collections.sort(mList, new Comparator<AssessDefine.DataBean>() {
                @Override
                public int compare(AssessDefine.DataBean lhs, AssessDefine.DataBean rhs) {
                    return lhs.getSortNumber() - rhs.getSortNumber();
                }
            });
        }
        mPingGuZjmLv = (ListView) mRootView.findViewById(R.id.ping_gu_zjm_lv);
        pingGuZjmLvItemAdapter = new PingGuZjmLvItemAdapter(mActivitySelf, mList);
        mPingGuZjmLv.setAdapter(pingGuZjmLvItemAdapter);
        mPingGuZjmLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // 压疮，跌倒有几种情况是需要直接跳转到措施界面
                if (mList.size() > 0) {
                    // 评估名称
                    String assessName = mList.get(position).getName();
                    if (assessName != null) {
                        if (assessName.contains("跌倒")) {
                            // 弹出提示框，选择 孕妇，儿童，残疾人和其他
                            // 如果是孕妇，儿童，残疾人则直接跳转到措施界面
                            PatientTypeDialog patientTypeDialog = new PatientTypeDialog(getActivity());
                            patientTypeDialog.showPopupWindow();
                            patientTypeDialog.setListener(new PatientTypeDialog.ClickListener() {
                                @Override
                                public void clickOk(int mark) {
                                    if (mark == 0) {
                                        // 非孕妇、儿童、残疾人
                                        if (mBundle == null) {
                                            mBundle = new Bundle();
                                        }
                                        mBundle.putSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE, mList.get(position));
                                        goToActivity(PG_DTMainActivity.class, mBundle);
                                    } else {
                                        HuanZheLieBiaoBean.DataBean patientInfo = (HuanZheLieBiaoBean.DataBean) mBundle.get(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
                                        String patientId = patientInfo.getPatientid();
                                        String wardId = (String) patientInfo.getWardid();
                                        SharePLogin sharePLogin = new SharePLogin(getActivity());
                                        String userId = sharePLogin.getUserid();
                                        Log.d("measure_insert", URL.URL_MEASURE_FALLDOWN_INSERT);
                                        // 孕妇、儿童、残疾人,跳转到措施界面
                                        OkHttpUtils
                                                .post()
                                                .addHeader("User-Agent", "www.gs.com")
                                                .url(URL.URL_MEASURE_FALLDOWN_INSERT)
                                                .addParams("wardId", wardId)
                                                .addParams("patientId", patientId)
                                                .addParams("userId", userId)
                                                .build()
                                                .execute(new StringCallback() {
                                                    @Override
                                                    public void onError(Call call, Exception e, int id) {
                                                        e.printStackTrace();
                                                        Log.d("measure_insert", "error");
                                                    }

                                                    @Override
                                                    public void onResponse(String response, int id) {
                                                        Log.d("measure_insert", response);
                                                        // 退出界面
                                                        handler.sendEmptyMessage(CODE_FINISH);
                                                    }
                                                });
                                    }
                                }
                            });
                        } else {
                            if (mBundle == null) {
                                mBundle = new Bundle();
                            }
                            mBundle.putSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE, mList.get(position));
                            goToActivity(PG_DTMainActivity.class, mBundle);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void init() {

    }

    public void setBundle(Bundle bundle) {
        mBundle = bundle;
    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 退出本界面
                case CODE_FINISH: {
                    mActivitySelf.finish();
                }
                break;
            }
        }
    };

}
