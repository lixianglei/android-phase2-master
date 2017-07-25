package com.ge.med.mobile.nursing.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.forjson.callback.INetworkHandler;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_ZhuSheActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_ZhuShe_BeiZhuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class YZ_PiShiFragment extends BaseFragment implements View.OnClickListener, INetworkHandler {
    private LinearLayout mYzZxZhuSheBtLl;
    private Button mPiShiBeizhuBt;
    private Button mPiShiYangXingBt;
    private Button mPiShiYinXingBt;
    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private DBHuanZheLieBiao huanZheLieBiao;
    private YZ_ZX_ZhuSheActivity yz_zx_zhuSheActivity;
    private String userid;

    @Override
    public int setRootView() {
        return R.layout.fragment_yz__pi_shi;
    }

    @Override
    public void initViews() {
        yz_zx_zhuSheActivity = (YZ_ZX_ZhuSheActivity) mActivitySelf;
        mBundle = yz_zx_zhuSheActivity.getmBundle();
        huanZheLieBiao = yz_zx_zhuSheActivity.getHuanZheLieBiao();
        dataBean = yz_zx_zhuSheActivity.getDataBean();
        mYzZxZhuSheBtLl = (LinearLayout) mRootView.findViewById(R.id.yz_zx_zhu_she_bt_ll);
        mPiShiBeizhuBt = (Button) mRootView.findViewById(R.id.pi_shi_beizhu_bt);
        mPiShiYangXingBt = (Button) mRootView.findViewById(R.id.pi_shi_yang_xing_bt);
        mPiShiYinXingBt = (Button) mRootView.findViewById(R.id.pi_shi_yin_xing_bt);

    }

    @Override
    public void initDatas() {
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        userid = sharePLogin.getUserid();
        mPiShiYangXingBt.setOnClickListener(this);
        mPiShiYinXingBt.setOnClickListener(this);
        mPiShiBeizhuBt.setOnClickListener(this);
    }

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
//            备注按钮监听
            case R.id.pi_shi_beizhu_bt:
                goToActivity(YZ_ZX_ZhuShe_BeiZhuActivity.class, mBundle);
                break;
//            阳性按钮监听
            case R.id.pi_shi_yang_xing_bt:
                tiJiao(Constant.SKIN_RESULT_YANG);
                break;
//            阴性按钮监听
            case R.id.pi_shi_yin_xing_bt:
                tiJiao(Constant.SKIN_RESULT_YIN);
                break;
        }

    }

    private void tiJiao(String skinResult) {
        dataBean.setOrderresult(skinResult);
        dataBean.setOrderststus(Constant.YZ_TYPE_YIZHIXING);
        dataBean.setLastupdatedby(userid);
        dataBean.setExecuteby(userid);
        long l = System.currentTimeMillis();
        dataBean.setLastupdatetime(l);
        dataBean.setExetime(l);
        new DoctorOrderDaoImpl().saveDoctorOrder(dataBean,true);
        NetworkForDoctorOrder.submitSingleDoctorOrder(this, dataBean);
        mActivitySelf.killSelf();
    }

    @Override
    public void handleOnError() {

    }

    @Override
    public void handleOnError(String urlStr) {

    }

    @Override
    public void handleSuccess(Object obj) {

    }
}
