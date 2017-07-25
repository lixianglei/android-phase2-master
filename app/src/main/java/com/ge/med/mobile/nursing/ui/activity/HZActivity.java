package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.VitalSignDaoImpl;
import com.ge.med.mobile.nursing.forjson.NetworkForAssessMeasureRecords;
import com.ge.med.mobile.nursing.forjson.NetworkForAssessment;
import com.ge.med.mobile.nursing.forjson.NetworkForVitalSign;
import com.ge.med.mobile.nursing.forjson.entity.AssessRecordBean;
import com.ge.med.mobile.nursing.forjson.entity.TaskLabelBean;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignSheet;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.VPFragMainAdapter;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.fragment.JLFragment;
import com.ge.med.mobile.nursing.ui.fragment.PGFragment;
import com.ge.med.mobile.nursing.ui.fragment.TiZhengFragment;
import com.ge.med.mobile.nursing.ui.fragment.YZFragment;
import com.ge.med.mobile.nursing.ui.view.MyViewPager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * 患者界面
 */
public class HZActivity extends MyBaseActivity implements View.OnClickListener {
    public final static long CALL_NET_INTERVAL = 100 * 1000l;
    private VPFragMainAdapter vpFragMainAdapter;
    private YZFragment yzFragment = new YZFragment();
    private PGFragment pgFragment = new PGFragment();
    private TiZhengFragment tiZhengFragment = new TiZhengFragment();
    private JLFragment jlFragment = new JLFragment();
    private BaseFragment[] baseFragments = {yzFragment, pgFragment, tiZhengFragment, jlFragment};

    public TextView mHzYzShuTv;
    private TextView mHzLlYzTv;
    private TextView mHzLlPgTv;
    private TextView mHzLlTzTv;
    private TextView mHzLlJlTv;
    private MyViewPager mHzVp;
    private ImageView mHzYzXiaoJianTou;

    private TextView mShaiXuanIncludeKongbaiTv;
    private LinearLayout mYiZhuXuanXiangQuanbuYizhuLl;
    public TextView mYiZhuXuanXiangQuanbuYizhuShu;
    private LinearLayout mYiZhuXuanXiangZhuSheLl;
    public TextView mYiZhuXuanXiangZhuSheShu;
    private LinearLayout mYiZhuXuanXiangZhuShuYeLl;
    public TextView mYiZhuXuanXiangZhuShuYeShu;
    public LinearLayout mYiZhuXuanXiangZhuKouFuLl;
    public TextView mYiZhuXuanXiangZhuSkouFuShu;
    private LinearLayout mYiZhuXuanXiangZhuJianYanLl;
    public TextView mYiZhuXuanXiangZhuJianYanShu;
    private LinearLayout mYiZhuXuanXiangZhuShuXueLl;
    public TextView mYiZhuXuanXiangZhuShuXueShu;
    public LinearLayout mYiZhuXuanXiangZhuFuzhuZhiliaoLl;
    public TextView mYiZhuXuanXiangZhuFuzhuZhiliaoShu;
    private ScrollView mYiZhuXuanXiangIncludeLl;
    private TextView mZhanweizhi;
    private Bundle mBundle;
    private LinearLayout mYiZhuXuanXiangZhuPishiLl;
    public TextView mYiZhuXuanXiangZhuPishiShu;
    private LinearLayout mYiZhuXuanXiangPixiaZhuSheLl;
    public TextView mYiZhuXuanXiangPixiaZhuSheShu;
    private LinearLayout mYiZhuXuanXiangJirouZhuSheLl;
    public TextView mYiZhuXuanXiangJirouZhuSheShu;
    private LinearLayout mYiZhuXuanXiangZhuShanshiZhiliaoLl;
    public TextView mYiZhuXuanXiangZhuShanshiZhiliaoShu;
    private LinearLayout mYiZhuXuanXiangZhuHuliZhiliaoLl;
    public TextView mYiZhuXuanXiangZhuHuliZhiliaoShu;
    private LinearLayout mYiZhuXuanXiangZhuJianYanXueLl;
    public TextView mYiZhuXuanXiangZhuJianYanXueShu;
    private LinearLayout mYiZhuXuanXiangZhuRuhuLl;
    public TextView mYiZhuXuanXiangZhuRuhuShu;
    private LinearLayout mYiZhuXuanXiangZhuChangqiLl;
    public TextView mYiZhuXuanXiangZhuChangqiShu;
    private LinearLayout mYiZhuXuanXiangZhuLinshiLl;
    public TextView mYiZhuXuanXiangZhuLinshiShu;
    private LinearLayout mYiZhuXuanXiangZhuLijiLl;
    public TextView mYiZhuXuanXiangZhuLijiShu;
    private LinearLayout mYiZhuXuanXiangZhuCaixueLl;
    public TextView mYiZhuXuanXiangZhuCaixueShu;
    // 风险标签
    public LinearLayout labels_layout;
    private TextView label0_tv;
    private TextView label1_tv;
    private TextView label2_tv;
    private TextView label3_tv;
    private TextView label4_tv;
    private TextView[] labels;

    private String patientid;
    private String yiZhuType = "全部医嘱";
    private ArrayList<Integer> yzShuList;
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;
    private List<AssessRecordBean> mAssessList = new ArrayList<>();
    private List<VitalSignSheet> mVitalSheetList = new ArrayList<>();
    private TitleBar mTitleBar;
    private PatientInfoPannel mPatientPannel;
    public static Map<String, Long> lastCallNetTime = new HashMap<String, Long>();
    private String userid;
    private String wardId;
    @Override
    public int setRootView() {
        return R.layout.activity_hz;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LogUtil.d("HZActivity mHandler handleMessage calling...");
            if (msg != null && msg.what == 0) {
                pgFragment.setHisAssessList(mAssessList);
                pgFragment.calFinishInfo();
                tiZhengFragment.setHisVitalSheetList(mVitalSheetList);
            }
        }
    };

    @Override
    protected void onResume() {
        LogUtil.d("HZActivity onResume 时间1 :" + System.currentTimeMillis());
        LogUtil.d("HZActivity onResume calling...");
        super.onResume();
        mBundle = mActivitySelf.getIntent().getBundleExtra(Constant.GLOBAL_KEY_DATA);
        mSelectedHZ = (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
        patientid = mSelectedHZ.getPatientid();
        setFregmentBundle();
        if (mTitleBar == null) {
            mTitleBar = new TitleBar(this, mSelectedHZ);
        }
        if (mPatientPannel == null) {
            mPatientPannel = new PatientInfoPannel(this, new AssessDaoImpl().findAllRiskDefine(), mSelectedHZ);
            mPatientPannel.setTipBehavior(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mYiZhuXuanXiangIncludeLl.setVisibility(View.VISIBLE);
                }
            });
        }
//        mHzVp.setOffscreenPageLimit(3);
//        mHzVp.setAdapter(vpFragMainAdapter);
        int tabNo = mBundle.getInt(Constant.BUNDLE_KEY_PATIENT_TAB_NUM, -1);
        if (tabNo != -1) {
            if (tabNo < 0 || tabNo > 3) tabNo = 0;
            pageChanged(tabNo);
        }
        // 刷新数据
        loadPatientAssessHistory();
        loadPatientVitalHistory();
        final long interval = System.currentTimeMillis() - (lastCallNetTime.get(patientid) == null ? 0 : lastCallNetTime.get(patientid).longValue());
        if (lastCallNetTime == null || interval > CALL_NET_INTERVAL) {
            LogUtil.d("HZActivity onResume load data from server...");
            //loadPatientAssessHistory();
            //loadPatientVitalHistory();
            lastCallNetTime.put(patientid, System.currentTimeMillis());
        } else {
            new Thread() {
                @Override
                public void run() {
                    LogUtil.d("HZActivity onResume load data from local...");
                    mAssessList = new AssessDaoImpl().findAssessesFromDB(patientid);
                    NetworkForAssessMeasureRecords.callAssessMeasureRecord(patientid);
                    mVitalSheetList = new VitalSignDaoImpl().findVitalSignsFromDB(patientid);
                    mHandler.sendEmptyMessage(0);
                }
            }.start();
        }

        LogUtil.d("HZActivity onResume 时间2 :" + System.currentTimeMillis());
        // 获取评估标签
        getAssessLabel();
    } // the end of onResume

    @Override
    public void initViews() {
        LogUtil.d("HZActivity initViews 时间1 :" + System.currentTimeMillis());
        SharePLogin sharePLogin = new SharePLogin(this);
        userid = sharePLogin.getUserid();
        wardId = sharePLogin.getWardid();
        mHzYzShuTv = (TextView) findViewById(R.id.hz_yz_shu_tv);
        mHzVp = (MyViewPager) findViewById(R.id.hz_vp);
        mHzLlYzTv = (TextView) findViewById(R.id.hz_ll_yz_tv);
        mHzLlPgTv = (TextView) findViewById(R.id.hz_ll_pg_tv);
        mHzLlTzTv = (TextView) findViewById(R.id.hz_ll_tz_tv);
        mHzLlJlTv = (TextView) findViewById(R.id.hz_ll_jl_tv);
        mHzYzXiaoJianTou = (ImageView) findViewById(R.id.hz_yz_xiao_jian_tou);
        mYiZhuXuanXiangIncludeLl = (ScrollView) findViewById(R.id.yi_zhu_xuan_xiang_include_ll);
        mShaiXuanIncludeKongbaiTv = (TextView) findViewById(R.id.shai_xuan_include_kongbai_tv);
        mYiZhuXuanXiangQuanbuYizhuLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_quanbu_yizhu_ll);
        mYiZhuXuanXiangQuanbuYizhuShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_quanbu_yizhu_shu);
        mYiZhuXuanXiangZhuSheLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_she_ll);
        mYiZhuXuanXiangZhuSheShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_she_shu);
        mYiZhuXuanXiangZhuShuYeLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_shu_ye_ll);
        mYiZhuXuanXiangZhuShuYeShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_shu_ye_shu);
        mYiZhuXuanXiangZhuKouFuLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_kou_fu_ll);
        mYiZhuXuanXiangZhuSkouFuShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_skou_fu_shu);
        mYiZhuXuanXiangZhuJianYanLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_jian_yan_ll);
        mYiZhuXuanXiangZhuJianYanShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_jian_yan_shu);
        mYiZhuXuanXiangZhuShuXueLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_shu_xue_ll);
        mYiZhuXuanXiangZhuShuXueShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_shu_xue_shu);
        mYiZhuXuanXiangZhuFuzhuZhiliaoLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_fuzhu_zhiliao_ll);
        mYiZhuXuanXiangZhuFuzhuZhiliaoShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_fuzhu_zhiliao_shu);
        mYiZhuXuanXiangZhuPishiLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_pishi_ll);
        mYiZhuXuanXiangZhuPishiShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_pishi_shu);
        mYiZhuXuanXiangZhuJianYanXueLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_jian_yan_xue_ll);
        mYiZhuXuanXiangZhuJianYanXueShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_jian_yan_xue_shu);
        mYiZhuXuanXiangPixiaZhuSheLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_pixia_zhu_she_ll);
        mYiZhuXuanXiangPixiaZhuSheShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_pixia_zhu_she_shu);
        mYiZhuXuanXiangJirouZhuSheLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_jirou_zhu_she_ll);
        mYiZhuXuanXiangJirouZhuSheShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_jirou_zhu_she_shu);
        mYiZhuXuanXiangZhuShanshiZhiliaoLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_shanshi_zhiliao_ll);
        mYiZhuXuanXiangZhuShanshiZhiliaoShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_shanshi_zhiliao_shu);
        mYiZhuXuanXiangZhuHuliZhiliaoLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_huli_zhiliao_ll);
        mYiZhuXuanXiangZhuHuliZhiliaoShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_huli_zhiliao_shu);
        mYiZhuXuanXiangZhuRuhuLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_ruhu_ll);
        mYiZhuXuanXiangZhuRuhuShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_ruhu_shu);
        mYiZhuXuanXiangZhuCaixueLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_caixue_ll);
        mYiZhuXuanXiangZhuCaixueShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_caixue_shu);
        mYiZhuXuanXiangZhuChangqiLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_changqi_ll);
        mYiZhuXuanXiangZhuChangqiShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_changqi_shu);
        mYiZhuXuanXiangZhuLinshiLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_linshi_ll);
        mYiZhuXuanXiangZhuLinshiShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_linshi_shu);
        mYiZhuXuanXiangZhuLijiLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_liji_ll);
        mYiZhuXuanXiangZhuLijiShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_liji_shu);

        mZhanweizhi = (TextView) findViewById(R.id.zhanweizhi);
        setOnclik();
        mHzLlYzTv.setOnClickListener(this);
        mHzLlPgTv.setOnClickListener(this);
        mHzLlTzTv.setOnClickListener(this);
        mHzLlJlTv.setOnClickListener(this);
        drawable_yz1 = getResources().getDrawable(R.mipmap.icon_orders_normal);
        drawable_yz2 = getResources().getDrawable(R.mipmap.icon_orders_pressed);
        drawable_pg1 = getResources().getDrawable(R.mipmap.icon_assess_normal);
        drawable_pg2 = getResources().getDrawable(R.mipmap.icon_assess_pressed);
        drawable_tz1 = getResources().getDrawable(R.mipmap.icon_signs_normal);
        drawable_tz2 = getResources().getDrawable(R.mipmap.icon_signs_pressed);
        drawable_jl1 = getResources().getDrawable(R.mipmap.icon_record_normal);
        drawable_jl2 = getResources().getDrawable(R.mipmap.icon_record_pressed);
        drawable_yz1.setBounds(0, 0, drawable_yz1.getMinimumWidth(), drawable_yz1.getMinimumHeight());
        drawable_yz2.setBounds(0, 0, drawable_yz2.getMinimumWidth(), drawable_yz2.getMinimumHeight());
        drawable_pg1.setBounds(0, 0, drawable_pg1.getMinimumWidth(), drawable_pg1.getMinimumHeight());
        drawable_pg2.setBounds(0, 0, drawable_pg2.getMinimumWidth(), drawable_pg2.getMinimumHeight());
        drawable_tz1.setBounds(0, 0, drawable_tz1.getMinimumWidth(), drawable_tz1.getMinimumHeight());
        drawable_tz2.setBounds(0, 0, drawable_tz2.getMinimumWidth(), drawable_tz2.getMinimumHeight());
        drawable_jl1.setBounds(0, 0, drawable_jl1.getMinimumWidth(), drawable_jl1.getMinimumHeight());
        drawable_jl2.setBounds(0, 0, drawable_jl2.getMinimumWidth(), drawable_jl2.getMinimumHeight());
        vpFragMainAdapter = new VPFragMainAdapter(mFragmentManager, baseFragments);
        mHzVp.setOffscreenPageLimit(3);
        mHzVp.setAdapter(vpFragMainAdapter);
        //fragment vp监听
        mHzVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.d("onPageSelected calling, position is " + position);
                pageChanged(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        LogUtil.d("HZActivity initViews 时间2 :" + System.currentTimeMillis());
        // 风险标签
        labels_layout = (LinearLayout) findViewById(R.id.labels_layout);
        label0_tv = (TextView) findViewById(R.id.label0_tv);
        label1_tv = (TextView) findViewById(R.id.label1_tv);
        label2_tv = (TextView) findViewById(R.id.label2_tv);
        label3_tv = (TextView) findViewById(R.id.label3_tv);
        label4_tv = (TextView) findViewById(R.id.label4_tv);
        labels = new TextView[]{label0_tv, label1_tv, label2_tv, label3_tv, label4_tv};
    } // initViews

    private void pageChanged(int position) {
        LogUtil.d("pageChanged calling, position is " + position);
        if (mPatientPannel != null) {
            if (position == 0) {
                mPatientPannel.showHideTipLayout(true);
            } else {
                mPatientPannel.showHideTipLayout(false);
            }
            if (position == 1) {
                labels_layout.setVisibility(View.VISIBLE);
            } else {
                labels_layout.setVisibility(View.GONE);
            }
        }
        mHzVp.setCurrentItem(position, false);
        switch (position) {
            //医嘱界面
            case 0:
                mHzYzXiaoJianTou.setVisibility(View.VISIBLE);
                mHzLlYzTv.setCompoundDrawables(null, drawable_yz2, null, null);
                mHzLlPgTv.setCompoundDrawables(null, drawable_pg1, null, null);
                mHzLlTzTv.setCompoundDrawables(null, drawable_tz1, null, null);
                mHzLlJlTv.setCompoundDrawables(null, drawable_jl1, null, null);
                break;
            //评估界面
            case 1:
                mHzYzXiaoJianTou.setVisibility(View.GONE);
                mHzLlYzTv.setCompoundDrawables(null, drawable_yz1, null, null);
                mHzLlPgTv.setCompoundDrawables(null, drawable_pg2, null, null);
                mHzLlTzTv.setCompoundDrawables(null, drawable_tz1, null, null);
                mHzLlJlTv.setCompoundDrawables(null, drawable_jl1, null, null);
                break;
            //体征界面
            case 2:
                mHzYzXiaoJianTou.setVisibility(View.GONE);
                mHzLlYzTv.setCompoundDrawables(null, drawable_yz1, null, null);
                mHzLlPgTv.setCompoundDrawables(null, drawable_pg1, null, null);
                mHzLlTzTv.setCompoundDrawables(null, drawable_tz2, null, null);
                mHzLlJlTv.setCompoundDrawables(null, drawable_jl1, null, null);
                break;
            //记录界面
            case 3:
                mHzYzXiaoJianTou.setVisibility(View.GONE);
                mHzLlYzTv.setCompoundDrawables(null, drawable_yz1, null, null);
                mHzLlPgTv.setCompoundDrawables(null, drawable_pg1, null, null);
                mHzLlTzTv.setCompoundDrawables(null, drawable_tz1, null, null);
                mHzLlJlTv.setCompoundDrawables(null, drawable_jl2, null, null);
                break;
        }
    }

    private void setOnclik() {
        mYiZhuXuanXiangQuanbuYizhuLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuSheLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuShuYeLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuKouFuLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuJianYanLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuShuXueLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuFuzhuZhiliaoLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuPishiLl.setOnClickListener(this);
        mYiZhuXuanXiangPixiaZhuSheLl.setOnClickListener(this);
        mYiZhuXuanXiangJirouZhuSheLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuShanshiZhiliaoLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuHuliZhiliaoLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuJianYanXueLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuRuhuLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuChangqiLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuLinshiLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuLijiLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuCaixueLl.setOnClickListener(this);
        mShaiXuanIncludeKongbaiTv.setOnClickListener(this);
        mZhanweizhi.setOnClickListener(this);
    }

    @Override
    public void initDatas() {
    }

    private Drawable drawable_yz1;
    private Drawable drawable_yz2;
    private Drawable drawable_pg1;
    private Drawable drawable_pg2;
    private Drawable drawable_tz1;
    private Drawable drawable_tz2;
    private Drawable drawable_jl1;
    private Drawable drawable_jl2;

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //医嘱界面
            case R.id.hz_ll_yz_tv:
                mHzVp.setCurrentItem(0, false);
                mHzYzXiaoJianTou.setVisibility(View.VISIBLE);
                mHzLlYzTv.setCompoundDrawables(null, drawable_yz2, null, null);
                mHzLlPgTv.setCompoundDrawables(null, drawable_pg1, null, null);
                mHzLlTzTv.setCompoundDrawables(null, drawable_tz1, null, null);
                mHzLlJlTv.setCompoundDrawables(null, drawable_jl1, null, null);
                break;
            //评估界面
            case R.id.hz_ll_pg_tv:
                mHzVp.setCurrentItem(1, false);
                mHzYzXiaoJianTou.setVisibility(View.GONE);
                mHzLlYzTv.setCompoundDrawables(null, drawable_yz1, null, null);
                mHzLlPgTv.setCompoundDrawables(null, drawable_pg2, null, null);
                mHzLlTzTv.setCompoundDrawables(null, drawable_tz1, null, null);
                mHzLlJlTv.setCompoundDrawables(null, drawable_jl1, null, null);
                break;
            //体征界面
            case R.id.hz_ll_tz_tv:
                mHzVp.setCurrentItem(2, false);
                mHzYzXiaoJianTou.setVisibility(View.GONE);
                mHzLlYzTv.setCompoundDrawables(null, drawable_yz1, null, null);
                mHzLlPgTv.setCompoundDrawables(null, drawable_pg1, null, null);
                mHzLlTzTv.setCompoundDrawables(null, drawable_tz2, null, null);
                mHzLlJlTv.setCompoundDrawables(null, drawable_jl1, null, null);
                break;
            //记录界面
            case R.id.hz_ll_jl_tv:
                mHzVp.setCurrentItem(3, false);
                mHzYzXiaoJianTou.setVisibility(View.GONE);
                mHzLlYzTv.setCompoundDrawables(null, drawable_yz1, null, null);
                mHzLlPgTv.setCompoundDrawables(null, drawable_pg1, null, null);
                mHzLlTzTv.setCompoundDrawables(null, drawable_tz1, null, null);
                mHzLlJlTv.setCompoundDrawables(null, drawable_jl2, null, null);
                break;

            //全部医嘱
            case R.id.yi_zhu_xuan_xiang_quanbu_yizhu_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                yiZhuType = "全部医嘱";
                shuaXin();
                break;
            //静脉注射
            case R.id.yi_zhu_xuan_xiang_zhu_she_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                yiZhuType = Constant.TYPE_YZ_JINGMAIZHUSHE;
                shuaXin();
                break;
            //皮下注射
            case R.id.yi_zhu_xuan_xiang_pixia_zhu_she_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                yiZhuType = Constant.TYPE_YZ_PIXIAZHUSHE;
                shuaXin();
                break;
            //肌肉注射
            case R.id.yi_zhu_xuan_xiang_jirou_zhu_she_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                yiZhuType = Constant.TYPE_YZ_JIROUZHUSHE;
                shuaXin();
                break;
            //输液
            case R.id.yi_zhu_xuan_xiang_zhu_shu_ye_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                yiZhuType = Constant.TYPE_YZ_SHUYE;
                shuaXin();
                break;
            //口服
            case R.id.yi_zhu_xuan_xiang_zhu_kou_fu_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
//                yiZhuType = Constant.TYPE_YZ_KOUFU;
//                shuaXin();
                yzShuList = yzFragment.getYzShuList();
                mBundle.putString(Constant.BUNDLE_KEY_YITYPE, Constant.TYPE_YZ_KOUFU);
                mBundle.putIntegerArrayList("yzshu", yzShuList);
                Intent intent = new Intent(mActivitySelf, YZ_ZX_KouFuYaoActivity.class);
                if (mBundle != null) {
                    intent.putExtra("data", mBundle);
                }
                startActivityForResult(intent, 1);
                break;
            //检验(非血)
            case R.id.yi_zhu_xuan_xiang_zhu_jian_yan_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                yiZhuType = Constant.TYPE_YZ_JIANYAN_UN_BLOOD;
                shuaXin();
                break;
            //检验(血)
            case R.id.yi_zhu_xuan_xiang_zhu_jian_yan_xue_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                yiZhuType = Constant.TYPE_YZ_JIANYAN_BLOOD;
                shuaXin();
                break;
            //输血
            case R.id.yi_zhu_xuan_xiang_zhu_shu_xue_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                yiZhuType = Constant.TYPE_YZ_SHUXUE;
                shuaXin();
                break;
            //治疗
            case R.id.yi_zhu_xuan_xiang_zhu_fuzhu_zhiliao_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
//                yiZhuType = Constant.TYPE_YZ_ZHILIAO;
//                shuaXin();
                yzShuList = yzFragment.getYzShuList();
                mBundle.putIntegerArrayList("yzshu", yzShuList);
                mBundle.putString(Constant.BUNDLE_KEY_YITYPE, Constant.TYPE_YZ_ZHILIAO);
                Intent intent1 = new Intent(mActivitySelf, YZ_ZX_KouFuYaoActivity.class);
                if (mBundle != null) {
                    intent1.putExtra("data", mBundle);
                }
                startActivityForResult(intent1, 1);
                break;
            //膳食
            case R.id.yi_zhu_xuan_xiang_zhu_shanshi_zhiliao_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
//                yiZhuType = Constant.TYPE_YZ_SHANSHI;
//                shuaXin();
                yzShuList = yzFragment.getYzShuList();
                mBundle.putIntegerArrayList("yzshu", yzShuList);
                mBundle.putString(Constant.BUNDLE_KEY_YITYPE, Constant.TYPE_YZ_SHANSHI);
                Intent intent2 = new Intent(mActivitySelf, YZ_ZX_KouFuYaoActivity.class);
                if (mBundle != null) {
                    intent2.putExtra("data", mBundle);
                }
                startActivityForResult(intent2, 1);
                break;
            //护理
            case R.id.yi_zhu_xuan_xiang_zhu_huli_zhiliao_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
//                yiZhuType = Constant.TYPE_YZ_HULI;
//                shuaXin();
                yzShuList = yzFragment.getYzShuList();
                mBundle.putIntegerArrayList("yzshu", yzShuList);
                mBundle.putString(Constant.BUNDLE_KEY_YITYPE, Constant.TYPE_YZ_HULI);
                Intent intent3 = new Intent(mActivitySelf, YZ_ZX_KouFuYaoActivity.class);
                if (mBundle != null) {
                    intent3.putExtra("data", mBundle);
                }
                startActivityForResult(intent3, 1);
                break;
            //皮试
            case R.id.yi_zhu_xuan_xiang_zhu_pishi_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                yiZhuType = Constant.TYPE_YZ_PISHI;
                shuaXin();
                break;
            //入壶
            case R.id.yi_zhu_xuan_xiang_zhu_ruhu_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                yiZhuType = Constant.TYPE_YZ_RUHU;
                shuaXin();
                break;
            //长期
            case R.id.yi_zhu_xuan_xiang_zhu_changqi_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                yiZhuType = Constant.ORDER_DURATION_CHANGQI;
                shuaXin();
                break;
            //临时
            case R.id.yi_zhu_xuan_xiang_zhu_linshi_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                yiZhuType = Constant.ORDER_DURATION_LINGSHI;
                shuaXin();
                break;
            //立即
            case R.id.yi_zhu_xuan_xiang_zhu_liji_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                yiZhuType = Constant.ORDER_DURATION_LIJI;
                shuaXin();
                break;
            //采血
            case R.id.yi_zhu_xuan_xiang_zhu_caixue_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                yiZhuType = Constant.TYPE_YZ_CAIXUE;
                shuaXin();
                break;
            //空
            case R.id.shai_xuan_include_kongbai_tv:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;

        }
    }

    public void shuaXin() {
        yzFragment.refreshData(yiZhuType, false, true, 0);
        yzShuList = yzFragment.getYzShuList();
        LogUtil.d("shuaXin yzShuList = " + yzShuList);
        setmHzYzShuTv();
//                ((HZActivity) mActivitySelf).mYiZhuXuanXiangPixiaZhuSheShu.setText(yzShuList.get(8) + "");
//                ((HZActivity) mActivitySelf).mYiZhuXuanXiangJirouZhuSheShu.setText(yzShuList.get(9) + "");
//                ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuShanshiZhiliaoShu.setText(yzShuList.get(10) + "");
//                ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuHuliZhiliaoShu.setText(yzShuList.get(11) + "");
    }

    public void setmHzYzShuTv() {
        if (yzShuList != null) {
            switch (yiZhuType) {
                case Constant.TYPE_YZ_ZHILIAO:
//                    mHzYzShuTv.setText(yzShuList.get(1) + "");
                    break;
                case Constant.TYPE_YZ_JINGMAIZHUSHE:
                    mHzYzShuTv.setText(yzShuList.get(2) + "");
                    break;
                case Constant.TYPE_YZ_SHUYE:
                    mHzYzShuTv.setText(yzShuList.get(3) + "");
                    break;
                case Constant.TYPE_YZ_KOUFU:
//                    mHzYzShuTv.setText(yzShuList.get(4) + "");
                    break;
                case Constant.TYPE_YZ_JIANYAN_UN_BLOOD:
                    mHzYzShuTv.setText(yzShuList.get(5) + "");
                    break;
                case Constant.TYPE_YZ_SHUXUE:
                    mHzYzShuTv.setText(yzShuList.get(6) + "");
                    break;
                case Constant.TYPE_YZ_PISHI:
                    mHzYzShuTv.setText(yzShuList.get(7) + "");
                    break;
                case "全部医嘱":
                    mHzYzShuTv.setText(yzShuList.get(0) + "");
                    break;
                case Constant.TYPE_YZ_PIXIAZHUSHE:
                    mHzYzShuTv.setText(yzShuList.get(8) + "");
                    break;
                case Constant.TYPE_YZ_JIROUZHUSHE:
                    mHzYzShuTv.setText(yzShuList.get(9) + "");
                    break;
                case Constant.TYPE_YZ_SHANSHI:
//                    mHzYzShuTv.setText(yzShuList.get(10) + "");
                    break;
                case Constant.TYPE_YZ_HULI:
//                    mHzYzShuTv.setText(yzShuList.get(11) + "");
                    break;
                case Constant.TYPE_YZ_JIANYAN_BLOOD:
                    mHzYzShuTv.setText(yzShuList.get(12) + "");
                    break;
                case Constant.TYPE_YZ_RUHU:
                    mHzYzShuTv.setText(yzShuList.get(13) + "");
                    break;
                case Constant.ORDER_DURATION_CHANGQI:
                    mHzYzShuTv.setText(yzShuList.get(14) + "");
                    break;
                case Constant.ORDER_DURATION_LINGSHI:
                    mHzYzShuTv.setText(yzShuList.get(15) + "");
                    break;
                case Constant.ORDER_DURATION_LIJI:
                    mHzYzShuTv.setText(yzShuList.get(16) + "");
                    break;
                case Constant.TYPE_YZ_CAIXUE:
                    mHzYzShuTv.setText(yzShuList.get(17) + "");
                    break;
                default:
                    mHzYzShuTv.setText(yzShuList.get(0) + "");
                    break;
            }
        }

    }

    private void setFregmentBundle() {
        for (BaseFragment freg : baseFragments) {
            freg.setBundle(mBundle);
        }
    }

    /**
     * load patient assess history data from API or local DB
     */
    private void loadPatientVitalHistory() {
        if (null == mSelectedHZ) {
            LogUtil.e("Cannot found selected Patient so that cannot load vital history record for patient.");
            return;
        }
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        try {
            NetworkForVitalSign.callVitalSheetHistoryByPatient(this, sharePLogin.getWardid(), mSelectedHZ.getPatientid());
        } catch (Exception e) {
            LogUtil.e("Cannot load vital sign history data since the Ward ID not present!");
        }
    }

    /**
     * load patient assess history data from API or local DB
     */
    private void loadPatientAssessHistory() {
        if (null == mSelectedHZ) {
            LogUtil.e("Cannot found selected Patient so that cannot load assess history record for patient.");
            return;
        }
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        try {
            NetworkForAssessment.callAssessHistoryByPatient(this
                    , sharePLogin.getWardid(), mSelectedHZ.getPatientid());
            NetworkForAssessMeasureRecords.callAssessMeasureRecord(mSelectedHZ.getPatientid());
        } catch (Exception e) {
            LogUtil.e("Cannot load Assess history data since the Ward ID not present!");
        }
    }

    @Override
    public void handleOnError() {
    }

    @Override
    public void handleSuccess(Object obj) {
        LogUtil.d("HZActivity.handleSuccess() calling!");
        if (obj == null) {
            LogUtil.e("Got null object, cannot handle anything!");
            return;
        }
        if (obj instanceof List) {
            List lst = (List) obj;
            if (lst.size() <= 0) {
                LogUtil.i("Got empty list, cannot handle anything!");
                return;
            }
            LogUtil.d("HZActivity.handleSuccess() found list with [" + lst.size() + "] data!");
            if (lst.get(0) instanceof AssessRecordBean) {
                mAssessList = lst;
                pgFragment.setHisAssessList(mAssessList);
                pgFragment.calFinishInfo();
            } else if (lst.get(0) instanceof VitalSignSheet) {
                mVitalSheetList = lst;
                tiZhengFragment.setHisVitalSheetList(mVitalSheetList);
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (mYiZhuXuanXiangIncludeLl.getVisibility() == View.VISIBLE) {
            mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
            return;
        }
        mBundle = mActivitySelf.getIntent().getBundleExtra(Constant.GLOBAL_KEY_DATA);
        mSelectedHZ = updatePatientPannel(mBundle, mPatientPannel);
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (1 == resultCode) {
            yiZhuType = data.getBundleExtra("data").getString("xuanxiang");
            LogUtil.d("HZActivity.onActivityResult  resultCode -->1yiZhuType+" + yiZhuType);
            switch (yiZhuType) {
                case Constant.TYPE_YZ_KOUFU:
                    mYiZhuXuanXiangZhuKouFuLl.performClick();
                    break;
                case Constant.TYPE_YZ_ZHILIAO:
                    mYiZhuXuanXiangZhuFuzhuZhiliaoLl.performClick();
                    break;
                case Constant.TYPE_YZ_HULI:
                    mYiZhuXuanXiangZhuHuliZhiliaoLl.performClick();
                    break;
                case Constant.TYPE_YZ_SHANSHI:
                    mYiZhuXuanXiangZhuShanshiZhiliaoLl.performClick();
                    break;
                case Constant.TYPE_YZ_JINGMAIZHUSHE:
                    mYiZhuXuanXiangZhuSheLl.performClick();
                    break;
                case Constant.TYPE_YZ_PIXIAZHUSHE:
                    mYiZhuXuanXiangPixiaZhuSheLl.performClick();
                    break;
                case Constant.TYPE_YZ_JIROUZHUSHE:
                    mYiZhuXuanXiangJirouZhuSheLl.performClick();
                    break;
                case Constant.TYPE_YZ_SHUYE:
                    mYiZhuXuanXiangZhuShuYeLl.performClick();
                    break;
                case Constant.TYPE_YZ_JIANYAN_UN_BLOOD:
                    mYiZhuXuanXiangZhuJianYanLl.performClick();
                    break;
                case Constant.TYPE_YZ_JIANYAN_BLOOD:
                    mYiZhuXuanXiangZhuJianYanXueLl.performClick();
                    break;
                case Constant.TYPE_YZ_SHUXUE:
                    mYiZhuXuanXiangZhuShuXueLl.performClick();
                    break;
                case Constant.TYPE_YZ_PISHI:
                    mYiZhuXuanXiangZhuPishiLl.performClick();
                    break;
                case Constant.TYPE_YZ_RUHU:
                    mYiZhuXuanXiangZhuRuhuLl.performClick();
                    break;
                case Constant.TYPE_YZ_CAIXUE:
                    mYiZhuXuanXiangZhuCaixueLl.performClick();
                    break;
                case Constant.ORDER_DURATION_CHANGQI:
                    mYiZhuXuanXiangZhuChangqiLl.performClick();
                    break;
                case Constant.ORDER_DURATION_LINGSHI:
                    mYiZhuXuanXiangZhuLinshiLl.performClick();
                    break;
                case Constant.ORDER_DURATION_LIJI:
                    mYiZhuXuanXiangZhuLijiLl.performClick();
                    break;
                case "全部医嘱":
                    mYiZhuXuanXiangQuanbuYizhuLl.performClick();
                    break;
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    public HuanZheLieBiaoBean.DataBean getSelectedPatient() {
        return mSelectedHZ;
    }

    public Bundle getmBundle() {
        return mBundle;
    }

    public void setmBundle(Bundle mBundle) {
        this.mBundle = mBundle;
    }

    public HuanZheLieBiaoBean.DataBean getmSelectedHZ() {
        return mSelectedHZ;
    }

    public void setmSelectedHZ(HuanZheLieBiaoBean.DataBean mSelectedHZ) {
        this.mSelectedHZ = mSelectedHZ;
    }

    public String getPatientid() {
        return patientid;
    }

    public String getYiZhuType() {
        return yiZhuType;
    }

    public String getUserid() {
        return userid;
    }

    public String getWardId() {
        return wardId;
    }

    /**
     * 获取评估标签
     */
    private void getAssessLabel() {
        OkHttpUtils.get().url(URL.URL_ASSESS_GET_TASK_LABEL).addHeader("User-Agent", "www.gs.com")
                .addParams("wardId", String.valueOf(mSelectedHZ.getWardid()))
                .addParams("patientId", mSelectedHZ.getPatientid())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        TaskLabelBean taskLabelBean = JSON.parseObject(response, TaskLabelBean.class);
                        if (taskLabelBean.getStatus() == 1) {
                            List<TaskLabelBean.DataBean> assessLabels = taskLabelBean.getData();
                            if (assessLabels.size() > 0) {
                                for (int i = 0; i < assessLabels.size(); i++) {
                                    if (i <= 5) {
                                        labels[i].setText(assessLabels.get(i).getName());
                                        labels[i].setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        }
                    }
                });
    }
}
