package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.HuanZheLieBiaoInterface;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.AdapterUtil;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.fragment.Shu_Xue_SQ_YC_Fragment;
import com.mitac.lib.bcr.utils.BARCODE;

public class Shu_Xue_SQ_YCActivity extends MyBaseActivity {

    private ImageView mHzYzTuBiaoTv;
    private FrameLayout mYiZhuZhiXingFrameLayout;

    private TitleBar mTitleBar;
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;
    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private String yiChang;
    private DBHuanZheLieBiao huanZheLieBiao;
    private HuanZheLieBiaoInterface huanZheLieBiaoInterface;

    private Shu_Xue_SQ_YC_Fragment shu_xue_sq_yc_fragment = new Shu_Xue_SQ_YC_Fragment();
    private String displayUserName;
    private String userId;
    private DocOrderPannel mDocOrderPannel;
    private HuanZheLieBiaoBean.DataBean huanZheLieBiaoInterfaceDataBean;

    public String getUserId() {
        return userId;
    }

    public String getYiChang() {
        return yiChang;
    }

    public void setYiChang(String yiChang) {
        this.yiChang = yiChang;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayUserName() {
        return displayUserName;
    }

    public void setDisplayUserName(String displayUserName) {
        this.displayUserName = displayUserName;
    }

    public Bundle getmBundle() {
        return mBundle;
    }

    public void setmBundle(Bundle mBundle) {
        this.mBundle = mBundle;
    }

    public YiZhuBean.DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(YiZhuBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }


    @Override
    public int setRootView() {
        return R.layout.activity_shu__xue__sq__yc;
    }

    @Override
    public void initViews() {
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        displayUserName = sharePLogin.getDisplayUserName();
        userId=sharePLogin.getUserid();
        mBundle = getIntent().getBundleExtra("data");
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
        huanZheLieBiao = AdapterUtil.getPatientFromDB(dataBean.getPatientid());
        huanZheLieBiaoInterface = new HuanZheLieBiaoImpl();
        huanZheLieBiaoInterfaceDataBean = huanZheLieBiaoInterface.getDataBean(huanZheLieBiao);
        mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, huanZheLieBiaoInterfaceDataBean);
        mHzYzTuBiaoTv = (ImageView) findViewById(R.id.hz_yz_tu_biao_tv);
        mYiZhuZhiXingFrameLayout = (FrameLayout) findViewById(R.id.yi_zhu_zhi_xing_frame_layout);

        mTitleBar = new TitleBar(this, huanZheLieBiaoInterfaceDataBean);
        mDocOrderPannel = new DocOrderPannel(this, dataBean);
        yiChang = mBundle.getString(Constant.BUNDLE_KEY_YICHANG);
        mHzYzTuBiaoTv.setImageResource(AdapterUtil.findYZImage(dataBean));

        replaceFrag(R.id.yi_zhu_zhi_xing_frame_layout, shu_xue_sq_yc_fragment);


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
