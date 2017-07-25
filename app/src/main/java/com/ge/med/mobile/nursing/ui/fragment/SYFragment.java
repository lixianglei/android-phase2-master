package com.ge.med.mobile.nursing.ui.fragment;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.forjson.callback.SY_HuanZheLieBiao_CallBack;
import com.ge.med.mobile.nursing.ui.activity.HZActivity;
import com.ge.med.mobile.nursing.ui.activity.MainActivity;
import com.ge.med.mobile.nursing.ui.adapter.SyHzlbLvItemAdapter;
import com.ge.med.mobile.nursing.ui.component.RiskTag;
import com.ge.med.mobile.nursing.ui.view.JiaZaiDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 首页患者列表
 */
public class SYFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    public ListView mSyHzlbLv;
    public SyHzlbLvItemAdapter mSyHzlbLvItemAdapter;
    private SwipeRefreshLayout mSyHljbSwipeRefresh;
    private JiaZaiDialog jiaZaiDialog;
    private MainActivity activity;
    private String ward_id;
    private int user_id;
    private RequestCall mHuanZheLieBiaoNet;
    private TextView mClickView;

    //    private List<AssessDefineJSONBean.DataBean> mAssessDefineList;
//    private List<VitalSignWardDefine> mVitalDefineList;
    private SY_HuanZheLieBiao_CallBack sy_huanZheLieBiao_callBack;
    private RiskTag mRiskTag;

    @Override
    public int setRootView() {
        return R.layout.fragment_sy;
    }

        public void setSYNetForJSON(String ward_id, final int user_id) {
        LogUtil.d("SYFragment.setSYNetForJSON calling. wardId is " + ward_id + ", userId is " + user_id);
        this.ward_id = ward_id;
        this.user_id = user_id;
        sy_huanZheLieBiao_callBack = new SY_HuanZheLieBiao_CallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.e("Can not got patient list from server!exception is:" + e.getMessage());
                showToastShort("无法连接到服务器！");
                mSyHljbSwipeRefresh.setRefreshing(false);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.d("Got patient response from server! response str is:" + response);
                super.onResponse(response, id);
                jiaZaiDialog.cancel();
                if (data == null || data.size() <= 0) {
                    showToastShort("病区当前没有患者！");
                    LogUtil.e("Can not got patient from server!");
                    return;
                }
                LogUtil.d("Got [" + data.size() + "] patient(s) from server!");
                activity.mShaiXuanIncludeAllTv.setText("" + data.size());
                List<HuanZheLieBiaoBean.DataBean> data0 = new ArrayList<>();
                List<HuanZheLieBiaoBean.DataBean> data1 = new ArrayList<>();
                List<HuanZheLieBiaoBean.DataBean> data2 = new ArrayList<>();
                List<HuanZheLieBiaoBean.DataBean> data3 = new ArrayList<>();
                List<HuanZheLieBiaoBean.DataBean> data4 = new ArrayList<>();
                for (HuanZheLieBiaoBean.DataBean dataBean : data) {
                    if (user_id == dataBean.getUserid()) {
                        data0.add(dataBean);
                    }
                    if (Constant.CARE_LEVEL_YIZHI.equals(dataBean.getCarelevel())) {
                        data1.add(dataBean);
                    }
                    if (Constant.CARE_LEVEL_ERZHI.equals(dataBean.getCarelevel())) {
                        data2.add(dataBean);
                    }
                    if (Constant.CARE_LEVEL_SANZHI.equals(dataBean.getCarelevel())) {
                        data3.add(dataBean);
                    }
                    if (Constant.CARE_LEVEL_TEZHI.equals(dataBean.getCarelevel())) {
                        data4.add(dataBean);
                    }
                }
                activity.mShaiXuanIncludeMyTv.setText(data0.size() + "");
                activity.mShaiXuanIncludeOneTv.setText("" + data1.size());
                activity.mShaiXuanIncludeTwoTv.setText("" + data2.size());
                activity.mShaiXuanIncludeThreeTv.setText("" + data3.size());
                activity.mShaiXuanIncludeTeTv.setText("" + data4.size());
                if (mSyHzlbLvItemAdapter == null) {
                    mSyHzlbLvItemAdapter = new SyHzlbLvItemAdapter(mActivitySelf, data, mClickView);
                    mSyHzlbLv.setAdapter(mSyHzlbLvItemAdapter);
                } else {
                    mSyHzlbLvItemAdapter.setObjects(data);
                    mSyHzlbLvItemAdapter.notifyDataSetChanged();
                }
                activity.jibieshuaixuan(activity.mLeiXing);
                mSyHljbSwipeRefresh.setRefreshing(false);
            }
        };
        //获取患者列表的网络请求；
        LogUtil.d("ward_id = " + ward_id);
        mHuanZheLieBiaoNet = OkHttpUtils
                .post()
                .url(URL.URL_QUAN_BU_SY)
                .addHeader("User-Agent", "www.gs.com")
                .addParams("wardId", ward_id + "")
                .addParams("userId", user_id + "")
                .build();
        mHuanZheLieBiaoNet.execute(sy_huanZheLieBiao_callBack);
        LogUtil.d("Try to load patient data from server.");
        jiaZaiDialog = new JiaZaiDialog(mActivitySelf);
        jiaZaiDialog.setCanceledOnTouchOutside(false);
        jiaZaiDialog.show();
    }

    @Override
    public void initViews() {
        activity = (MainActivity) mActivitySelf;
//下拉刷新
        mSyHljbSwipeRefresh = (SwipeRefreshLayout) mRootView.findViewById(R.id.sy_hljb_swipe_refresh);
        mSyHljbSwipeRefresh.setOnRefreshListener(this);
        mClickView = (TextView) mRootView.findViewById(R.id.click_view);

        mSyHzlbLv = (ListView) mRootView.findViewById(R.id.sy_hzlb_lv);
        mSyHzlbLvItemAdapter = new SyHzlbLvItemAdapter(mActivitySelf, new ArrayList<HuanZheLieBiaoBean.DataBean>(), mClickView);
        mSyHzlbLv.setAdapter(mSyHzlbLvItemAdapter);

        mSyHzlbLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.d("时间1 :" + System.currentTimeMillis());
                List<HuanZheLieBiaoBean.DataBean> objects = mSyHzlbLvItemAdapter.getObjects();
                HuanZheLieBiaoBean.DataBean dataBean = objects.get(position);
                MainActivity activity = (MainActivity) mFragSelf.getActivity();
                activity.mBundle.putString("patientid", dataBean.getPatientid());
                activity.mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, dataBean);
                LogUtil.d("时间2 :" + System.currentTimeMillis());
                goToActivity(HZActivity.class, activity.mBundle);
            }
        });

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    //下拉刷新  监听
    @Override
    public void onRefresh() {
        if (mHuanZheLieBiaoNet != null) mHuanZheLieBiaoNet.execute(sy_huanZheLieBiao_callBack);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        mSyHzlbLv.clearChoices();
//        mSyHzlbLv.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
//        mSyHljbSwipeRefresh.post(new Runnable() {
//            @Override
//            public void run() {
//                mSyHljbSwipeRefresh.setRefreshing(true);
//            }
//        });
//        onRefresh();
    }
}
