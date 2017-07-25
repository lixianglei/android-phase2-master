package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.ui.adapter.AdapterUtil;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;

public class SOPActivity extends MyBaseActivity {
    private TitleBar mTitleBar;
    private DocOrderPannel mDocOrderPannel;
    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private DBHuanZheLieBiao huanZheLieBiao;
    private HuanZheLieBiaoImpl huanZheLieBiaoInterface;
    private HuanZheLieBiaoBean.DataBean huanZheLieBiaoInterfaceDataBean;

    @Override
    public int setRootView() {
        return R.layout.activity_sop;
    }

    @Override
    public void initViews() {
        mBundle = getIntent().getBundleExtra("data");
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
        huanZheLieBiao = AdapterUtil.getPatientFromDB(dataBean.getPatientid());
        huanZheLieBiaoInterface = new HuanZheLieBiaoImpl();
        huanZheLieBiaoInterfaceDataBean = huanZheLieBiaoInterface.getDataBean(huanZheLieBiao);
        mTitleBar = new TitleBar(this, huanZheLieBiaoInterfaceDataBean);
        mDocOrderPannel = new DocOrderPannel(this, dataBean);
    }

    @Override
    public void initDatas() {

    }
    @Override
    protected void onResume() {
        super.onResume();
        mDocOrderPannel.bindData(new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId()));
    }
    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

}
