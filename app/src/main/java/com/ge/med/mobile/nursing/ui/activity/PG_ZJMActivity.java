package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.fragment.PG_ZYMFragment;
import com.ge.med.mobile.nursing.ui.fragment.XuanJiaoFragment;
import com.ge.med.mobile.nursing.ui.fragment.XuanJiaoXiangQingFragment;
import com.ge.med.mobile.nursing.utils.DataConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 宣教列表界面：入院宣教，检查检验宣教，预防措施
 * 下一步界面
 */
public class PG_ZJMActivity extends MyBaseActivity {

    private PG_ZYMFragment pg_zymFragment = new PG_ZYMFragment();
    private XuanJiaoFragment xuanJiaoFragment = new XuanJiaoFragment();
    private XuanJiaoXiangQingFragment xuanJiaoXiangQingFragment = new XuanJiaoXiangQingFragment();
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;
    private TitleBar mTitleBar;
    private PatientInfoPannel mPatientPanel;
    private Bundle mBundle;
    private FrameLayout mZhujiemianFl;
    private String wardName;


    private String userid;

    public HuanZheLieBiaoBean.DataBean getmSelectedHZ() {
        return mSelectedHZ;
    }

    @Override
    public int setRootView() {
        return R.layout.activity_pg__zjm;
    }

    @Override
    public void initViews() {
        SharePLogin sharePLogin = new SharePLogin(this);
        wardName = sharePLogin.getWardName();
        userid = sharePLogin.getUserid();
        mBundle = getIntent().getBundleExtra(Constant.GLOBAL_KEY_DATA);
        mSelectedHZ = (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
        String string = mBundle.getString(Constant.BUNDLE_KEY_ACTIVITY_TYPE);
        pg_zymFragment.setBundle(mBundle);
        mTitleBar = new TitleBar(this, mSelectedHZ);
        mPatientPanel = new PatientInfoPannel(this, new AssessDaoImpl().findAllRiskDefine(), mSelectedHZ);
        mPatientPanel.showHideTipLayout(false);
        mZhujiemianFl = (FrameLayout) findViewById(R.id.zhujiemian_fl);

        if (Constant.BUNDLE_KEY_ACTIVITY_TYPE_XUANJIAO.equals(string) || Constant.BUNDLE_KEY_ACTIVITY_TYPE_HOUJI_CUOSHI.equals(string)) {
            // 宣教一级二级，预防措施
            replaceFrag(R.id.zhujiemian_fl, xuanJiaoFragment);
        } else if (Constant.BUNDLE_KEY_ACTIVITY_TYPE_XIANGQING.equals(string)) {
            // 下一步
            replaceFrag(R.id.zhujiemian_fl, xuanJiaoXiangQingFragment);
        } else {
            // 新建评估
            replaceFrag(R.id.zhujiemian_fl, pg_zymFragment);
        }
    }

    public String getWardName() {
        return wardName;
    }

    @Override
    public void initDatas() {

    }

    public String getUserid() {
        return userid;
    }

    public Bundle getmBundle() {
        return mBundle;
    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    @Override
    public void onBackPressed() {
        mBundle = mActivitySelf.getIntent().getBundleExtra(Constant.GLOBAL_KEY_DATA);
        mSelectedHZ = updatePatientPannel(mBundle, mPatientPanel);
        super.onBackPressed();
    }

    @Override
    public void afterPatientScanned(String patientID) {
        String fragmentType = mBundle.getString(Constant.XUANJIAO_CHILD_KEY);
        if (Constant.XUANJIAO_CHILD_VALUE.equals(fragmentType)) {
            return;
        }
        mSelectedHZ = DataConverter.convert(new HuanZheLieBiaoImpl().getDBHuanZheLieBiao(patientID));
        mPatientPanel.changePatient(mSelectedHZ);
        bindPatientInfo();
    }

    private void bindPatientInfo() {
        mTitleBar = new TitleBar(this, mSelectedHZ);
        mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, mSelectedHZ);
    }

}
