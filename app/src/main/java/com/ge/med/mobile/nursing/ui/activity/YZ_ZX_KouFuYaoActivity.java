package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.DoctorOrderDao;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.YzZxKouFuYaoLvItemAdapter;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YZ_ZX_KouFuYaoActivity extends MyBaseActivity implements View.OnClickListener {
    private YzZxKouFuYaoLvItemAdapter yzZxKouFuYaoLvItemAdapter;
    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private List<YiZhuBean.DataBean> dataBeanList;
    private String shaiXuanType = "全部医嘱";
    private ArrayList<Integer> yzShuList;
    private TextView mShaiXuanIncludeKongbaiTv;
    private LinearLayout mYiZhuXuanXiangQuanbuYizhuLl;
    public TextView mYiZhuXuanXiangQuanbuYizhuShu;
    private LinearLayout mYiZhuXuanXiangZhuSheLl;
    public TextView mYiZhuXuanXiangZhuSheShu;
    private LinearLayout mYiZhuXuanXiangZhuShuYeLl;
    public TextView mYiZhuXuanXiangZhuShuYeShu;
    private LinearLayout mYiZhuXuanXiangZhuKouFuLl;
    public TextView mYiZhuXuanXiangZhuSkouFuShu;
    private LinearLayout mYiZhuXuanXiangZhuJianYanLl;
    public TextView mYiZhuXuanXiangZhuJianYanShu;
    private LinearLayout mYiZhuXuanXiangZhuShuXueLl;
    public TextView mYiZhuXuanXiangZhuShuXueShu;
    private LinearLayout mYiZhuXuanXiangZhuFuzhuZhiliaoLl;
    public TextView mYiZhuXuanXiangZhuFuzhuZhiliaoShu;
    private ScrollView mYiZhuXuanXiangIncludeLl;
    private LinearLayout mYiZhuXuanXiangZhuPishiLl;
    private TextView mYiZhuXuanXiangZhuPishiShu;

    private ListView mHzYzLv;
    private TextView mZhanweizhi;
    private TextView mHzYzShuTv;
    private Button mYzShuxueYichangBt;
    private Button mYzShuxueWanchengBt;
    private DoctorOrderDao doctorOrderDao;

    private TitleBar mTitleBar;
    private PatientInfoPannel mPatientPannel;
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;
    private String patientid;
    private String displayUserName;
    private String yzType;
    private String userid;
    private LinearLayout mYiZhuXuanXiangPixiaZhuSheLl;
    private TextView mYiZhuXuanXiangZhuHuliZhiliaoShu;
    private TextView mYiZhuXuanXiangPixiaZhuSheShu;
    private LinearLayout mYiZhuXuanXiangJirouZhuSheLl;
    private LinearLayout mYiZhuXuanXiangZhuHuliZhiliaoLl;
    private TextView mYiZhuXuanXiangZhuShanshiZhiliaoShu;
    private LinearLayout mYiZhuXuanXiangZhuShanshiZhiliaoLl;
    private TextView mYiZhuXuanXiangJirouZhuSheShu;
    private LinearLayout mYiZhuXuanXiangZhuJianYanXueLl;
    private TextView mYiZhuXuanXiangZhuJianYanXueShu;
    private LinearLayout mYiZhuXuanXiangZhuRuhuLl;
    private TextView mYiZhuXuanXiangZhuRuhuShu;

    private LinearLayout mYiZhuXuanXiangZhuChangqiLl;
    public TextView mYiZhuXuanXiangZhuChangqiShu;
    private LinearLayout mYiZhuXuanXiangZhuLinshiLl;
    public TextView mYiZhuXuanXiangZhuLinshiShu;
    private LinearLayout mYiZhuXuanXiangZhuLijiLl;
    public TextView mYiZhuXuanXiangZhuLijiShu;
    private LinearLayout mYiZhuXuanXiangZhuCaixueLl;
    public TextView mYiZhuXuanXiangZhuCaixueShu;
    @Override
    public int setRootView() {
        return R.layout.activity_yz__zx__kou_fu_yao;
    }

    @Override
    public void initViews() {
        doctorOrderDao = new DoctorOrderDaoImpl();
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
        mYiZhuXuanXiangZhuChangqiLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_changqi_ll);
        mYiZhuXuanXiangZhuChangqiShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_changqi_shu);
        mYiZhuXuanXiangZhuLinshiLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_linshi_ll);
        mYiZhuXuanXiangZhuLinshiShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_linshi_shu);
        mYiZhuXuanXiangZhuLijiLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_liji_ll);
        mYiZhuXuanXiangZhuLijiShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_liji_shu);

        mYiZhuXuanXiangZhuCaixueLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_caixue_ll);
        mYiZhuXuanXiangZhuCaixueShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_caixue_shu);
        mHzYzLv = (ListView) findViewById(R.id.hz_yz_lv);
        mZhanweizhi = (TextView) findViewById(R.id.zhanweizhi);
        mHzYzShuTv = (TextView) findViewById(R.id.hz_yz_shu_tv);
        mYzShuxueYichangBt = (Button) findViewById(R.id.yz_shuxue_yichang_bt);
        mYzShuxueWanchengBt = (Button) findViewById(R.id.yz_shuxue_wancheng_bt);
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        userid = sharePLogin.getUserid();
        mBundle = getIntent().getBundleExtra("data");
        yzShuList = mBundle.getIntegerArrayList("yzshu");
        mYzShuxueYichangBt.setEnabled(false);
        mSelectedHZ = (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
        patientid = mSelectedHZ.getPatientid();
        mTitleBar = new TitleBar(this, mSelectedHZ);

        mPatientPannel = new PatientInfoPannel(this, new AssessDaoImpl().findAllRiskDefine(), mSelectedHZ);
        mPatientPannel.setTipBehavior(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastShort("筛选");
                mYiZhuXuanXiangIncludeLl.setVisibility(View.VISIBLE);
            }
        });

        yzType = mBundle.getString(Constant.BUNDLE_KEY_YITYPE);
        if (Constant.TYPE_YZ_KOUFU.equals(yzType)) {
            mYzShuxueWanchengBt.setText("确认服药");
        } else {
            mYzShuxueWanchengBt.setText("确认执行");
        }
        shuaXinJieMian();

    }

    private void shuaXinJieMian() {
        dataBeanList = doctorOrderDao.findDoctorOrdersExcludeStatus(patientid, yzType, Constant.YZ_TYPE_WEIHEDUI);
        dataBeanList = doctorOrderDao.getFPaiXuDBYiZhuDatabean(dataBeanList);
        if (yzZxKouFuYaoLvItemAdapter == null) {
            yzZxKouFuYaoLvItemAdapter = new YzZxKouFuYaoLvItemAdapter(mActivitySelf, dataBeanList, mYzShuxueYichangBt,null);
            yzZxKouFuYaoLvItemAdapter.setPosList(ActivityUtils.suanPos(dataBeanList));

            mHzYzLv.setAdapter(yzZxKouFuYaoLvItemAdapter);
        } else {
            yzZxKouFuYaoLvItemAdapter.setmEntities(dataBeanList);
            yzZxKouFuYaoLvItemAdapter.setPosList(ActivityUtils.suanPos(dataBeanList));

            yzZxKouFuYaoLvItemAdapter.notifyDataSetChanged();
        }
        List<YiZhuBean.DataBean> doctorOrdersInJsonBean = doctorOrderDao.findDoctorOrdersInJsonBean(patientid, yzType, Constant.YZ_TYPE_WEIZHIXING);
        if (doctorOrdersInJsonBean != null) {
            mPatientPannel.setTipNumber(doctorOrdersInJsonBean.size());
        } else {
            mPatientPannel.setTipNumber(0);

        }
    }

    @Override
    public void initDatas() {
        //异常按钮监听
        mYzShuxueYichangBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean = yzZxKouFuYaoLvItemAdapter.getDataBean();
                if (dataBean == null) {
                    showToastShort("请选择医嘱！");
                    return;
                }
                mBundle.putInt("hisid", dataBean.getId());
                mBundle.putString("name", mSelectedHZ.getName());
                mBundle.putString("carelevel", mSelectedHZ.getCarelevel());
                mBundle.putInt("bedno", mSelectedHZ.getBedno());
                mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU, dataBean);
                mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, mSelectedHZ);
                goToActivity(YZ_ZX_ZhuSheActivity.class, mBundle, 0);
            }
        });
        setOnclik();
    }

    private void setOnclik() {
        if (yzShuList != null && yzShuList.size() > 0) {
            mYiZhuXuanXiangQuanbuYizhuShu.setText(yzShuList.get(0) + "");
            if(Constant.TYPE_YZ_KOUFU.equals(yzType)){
                mHzYzShuTv.setText(yzShuList.get(4) + "");
            }else{
                mHzYzShuTv.setText(yzShuList.get(1) + "");
            }
            mYiZhuXuanXiangZhuFuzhuZhiliaoShu.setText(yzShuList.get(1) + "");
            mYiZhuXuanXiangZhuSheShu.setText(yzShuList.get(2) + "");
            mYiZhuXuanXiangZhuShuYeShu.setText(yzShuList.get(3) + "");
            mYiZhuXuanXiangZhuSkouFuShu.setText(yzShuList.get(4) + "");
            mYiZhuXuanXiangZhuJianYanShu.setText(yzShuList.get(5) + "");
            mYiZhuXuanXiangZhuShuXueShu.setText(yzShuList.get(6) + "");
            mYiZhuXuanXiangZhuPishiShu.setText(yzShuList.get(7) + "");
            mYiZhuXuanXiangPixiaZhuSheShu.setText(yzShuList.get(8) + "");
            mYiZhuXuanXiangJirouZhuSheShu.setText(yzShuList.get(9) + "");
            mYiZhuXuanXiangZhuShanshiZhiliaoShu.setText(yzShuList.get(10) + "");
            mYiZhuXuanXiangZhuHuliZhiliaoShu.setText(yzShuList.get(11) + "");
            mYiZhuXuanXiangZhuJianYanXueShu.setText(yzShuList.get(12) + "");
            mYiZhuXuanXiangZhuRuhuShu.setText(yzShuList.get(13) + "");
            mYiZhuXuanXiangZhuChangqiShu.setText(yzShuList.get(14) + "");
            mYiZhuXuanXiangZhuLinshiShu.setText(yzShuList.get(15) + "");
            mYiZhuXuanXiangZhuLijiShu.setText(yzShuList.get(16) + "");
            mYiZhuXuanXiangZhuCaixueShu.setText(yzShuList.get(17) + "");
        }
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
        mYiZhuXuanXiangZhuCaixueLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuLijiLl.setOnClickListener(this);
        mShaiXuanIncludeKongbaiTv.setOnClickListener(this);
        mZhanweizhi.setOnClickListener(this);
    }

    @Override
    public void init() {
//        完成提交按钮
        mYzShuxueWanchengBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isNull = true;
                List<YiZhuBean.DataBean> dataBeens = yzZxKouFuYaoLvItemAdapter.getmEntities();
                Map<Integer, Boolean> integerBooleanMap = yzZxKouFuYaoLvItemAdapter.getmCBFlag();
                for (Map.Entry<Integer, Boolean> entry : integerBooleanMap.entrySet()) {
                    int pos = entry.getKey();
                    Boolean value = entry.getValue();
                    if (value) {
                        isNull = false;
                        Integer id = dataBeens.get(pos).getId();
                        dataBeens.get(pos).setOrderststus("已执行");
                        dataBeens.get(pos).setLastupdatedby(userid);
                        dataBeens.get(pos).setExecuteby(userid);
                        dataBeens.get(pos).setExetime(System.currentTimeMillis());
                        dataBeens.get(pos).setLastupdatetime(System.currentTimeMillis());
                    }
                }
                if(isNull){
                    showToastShort("没有选择医嘱");
                    return;
                }
                jiaZaiDialog.show();
                doctorOrderDao.saveDoctorOrders(dataBeens,true);
                NetworkForDoctorOrder.sumbmitAllDoctorOrder(YZ_ZX_KouFuYaoActivity.this, dataBeens);
            }
        });

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    private void shuaiXuan(String str) {
        Intent intent = new Intent();
        intent.setClass(mActivitySelf, HZActivity.class);
        mBundle.putString("xuanxiang", str);
        intent.putExtra("data", mBundle);
        setResult(1, intent);
        killSelf();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //全部医嘱
            case R.id.yi_zhu_xuan_xiang_quanbu_yizhu_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                shaiXuanType = "全部医嘱";
                shuaiXuan(shaiXuanType);
                break;
            //静脉注射
            case R.id.yi_zhu_xuan_xiang_zhu_she_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                shaiXuanType = Constant.TYPE_YZ_JINGMAIZHUSHE;
                shuaiXuan(shaiXuanType);
                break;
            //皮下注射
            case R.id.yi_zhu_xuan_xiang_pixia_zhu_she_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                shaiXuanType = Constant.TYPE_YZ_PIXIAZHUSHE;
                shuaiXuan(shaiXuanType);
                break;
            //肌肉注射
            case R.id.yi_zhu_xuan_xiang_jirou_zhu_she_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                shaiXuanType = Constant.TYPE_YZ_JIROUZHUSHE;
                shuaiXuan(shaiXuanType);
                break;
            //输液
            case R.id.yi_zhu_xuan_xiang_zhu_shu_ye_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                shaiXuanType = Constant.TYPE_YZ_SHUYE;
                shuaiXuan(shaiXuanType);
                break;
            //口服
            case R.id.yi_zhu_xuan_xiang_zhu_kou_fu_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                shaiXuanType = Constant.TYPE_YZ_KOUFU;
                shuaiXuan(shaiXuanType);
                break;
            //检验(非血)
            case R.id.yi_zhu_xuan_xiang_zhu_jian_yan_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                shaiXuanType = Constant.TYPE_YZ_JIANYAN_UN_BLOOD;
                shuaiXuan(shaiXuanType);
                break;
            //检验(xue)
            case R.id.yi_zhu_xuan_xiang_zhu_jian_yan_xue_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                shaiXuanType = Constant.TYPE_YZ_JIANYAN_BLOOD;
                shuaiXuan(shaiXuanType);
                break;
            //输血
            case R.id.yi_zhu_xuan_xiang_zhu_shu_xue_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                shaiXuanType = Constant.TYPE_YZ_SHUXUE;
                shuaiXuan(shaiXuanType);
                break;
            //治疗
            case R.id.yi_zhu_xuan_xiang_zhu_fuzhu_zhiliao_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                shaiXuanType = Constant.TYPE_YZ_ZHILIAO;
                shuaiXuan(shaiXuanType);
                break;
            //膳食
            case R.id.yi_zhu_xuan_xiang_zhu_shanshi_zhiliao_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                shaiXuanType = Constant.TYPE_YZ_SHANSHI;
                shuaiXuan(shaiXuanType);
                break;
            //护理
            case R.id.yi_zhu_xuan_xiang_zhu_huli_zhiliao_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                shaiXuanType = Constant.TYPE_YZ_HULI;
                shuaiXuan(shaiXuanType);
                break;
            //皮试
            case R.id.yi_zhu_xuan_xiang_zhu_pishi_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                shaiXuanType = Constant.TYPE_YZ_PISHI;
                shuaiXuan(shaiXuanType);
                break;
            //入壶
            case R.id.yi_zhu_xuan_xiang_zhu_ruhu_ll:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                shaiXuanType = Constant.TYPE_YZ_RUHU;
                shuaiXuan(shaiXuanType);
                break;
            //空
            case R.id.shai_xuan_include_kongbai_tv:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        shuaXinJieMian();
    }

    @Override
    public void handleOnError() {
        super.handleOnError();
        System.out.println("失败");
        shuaXinJieMian();
        jiaZaiDialog.cancel();
    }

    @Override
    public void handleOnError(String urlStr) {
        super.handleOnError(urlStr);
        System.out.println("失败");
        shuaXinJieMian();
        jiaZaiDialog.cancel();
    }

    @Override
    public void handleSuccess(Object obj) {
        super.handleSuccess(obj);
        shuaXinJieMian();
        jiaZaiDialog.cancel();
    }

}
