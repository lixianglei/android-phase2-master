package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.HuanZheLieBiaoInterface;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.ui.adapter.AdapterUtil;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.fragment.YZ_PiShiFragment;
import com.ge.med.mobile.nursing.ui.fragment.YZ_ZX_ZhuSheFragment;
import com.mitac.lib.bcr.utils.BARCODE;

import org.xutils.common.util.LogUtil;

public class YZ_ZX_ZhuSheActivity extends MyBaseActivity {
    private FrameLayout mYiZhuZhiXingFrameLayout;
    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private DBHuanZheLieBiao huanZheLieBiao;
    private HuanZheLieBiaoInterface huanZheLieBiaoInterface;

    private TitleBar mTitleBar;
    private DocOrderPannel mDocOrderPannel;

    private String string;
    private HuanZheLieBiaoBean.DataBean huanZheLieBiaoInterfaceDataBean;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public DBHuanZheLieBiao getHuanZheLieBiao() {
        return huanZheLieBiao;
    }

    public YiZhuBean.DataBean getDataBean() {
        return dataBean;
    }

    private YZ_ZX_ZhuSheFragment yz_zx_zhuSheFragment = new YZ_ZX_ZhuSheFragment();
    private YZ_PiShiFragment mYzPiShiFragment = new YZ_PiShiFragment();

    public Bundle getmBundle() {
        return mBundle;
    }

    public void setmBundle(Bundle mBundle) {
        this.mBundle = mBundle;
    }

    @Override
    public int setRootView() {
        return R.layout.activity_yz__zx__zhu_she;
    }
    @Override
    protected void onResume() {
        super.onResume();
        mDocOrderPannel.bindData(new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId()));
    }
    @Override
    public void initViews() {
        mBundle = getIntent().getBundleExtra("data");
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
        huanZheLieBiao = AdapterUtil.getPatientFromDB(dataBean.getPatientid());
        huanZheLieBiaoInterface = new HuanZheLieBiaoImpl();
        huanZheLieBiaoInterfaceDataBean = huanZheLieBiaoInterface.getDataBean(huanZheLieBiao);
        mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, huanZheLieBiaoInterfaceDataBean);
        mYiZhuZhiXingFrameLayout = (FrameLayout) findViewById(R.id.yi_zhu_zhi_xing_frame_layout);

        mTitleBar = new TitleBar(this, huanZheLieBiaoInterfaceDataBean);
        mDocOrderPannel = new DocOrderPannel(this, dataBean);
        if(dataBean!=null){
            if(Constant.TYPE_YZ_PISHI.equals(dataBean.getOrdertype())
                    && Constant.YZ_TYPE_YIZHIXING.equals(dataBean.getOrderststus())){
                replaceFrag(R.id.yi_zhu_zhi_xing_frame_layout,mYzPiShiFragment);
            }else {
                replaceFrag(R.id.yi_zhu_zhi_xing_frame_layout, yz_zx_zhuSheFragment);
            }
        }
        string = mBundle.getString(Constant.ZHU_SHE_2_KEY);

    }


    @Override
    public void initDatas() {

    }


    @Override
    public void init() {
        Intent intent = new Intent();
        intent.setClass(mActivitySelf, HZActivity.class);
        setResult(Constant.RESULT_CODE_ZHUSHE, intent);
    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 0){
            if(data!=null){
                mBundle = data.getBundleExtra("data");
                LogUtil.e("mBundle  0="+mBundle);
                this.setResult(0, data);
                killSelf();
            }
        }
        if (Constant.RESULT_CODE_YICHANG_CHULI == resultCode) {
            System.out.println("YZ_ZX_ZhuSheActivity.onActivityResult");
            killSelf();
        }


    }

}
