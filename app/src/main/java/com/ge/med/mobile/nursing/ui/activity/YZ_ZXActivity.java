package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.db.DBUserList;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.fragment.YZ_SX_SMFragment;
import com.ge.med.mobile.nursing.ui.fragment.YZ_ShuXueFragment;
import com.mitac.lib.bcr.utils.BARCODE;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class YZ_ZXActivity extends MyBaseActivity {
    private FrameLayout mYiZhuZhiXingFrameLayout;
    private YZ_ShuXueFragment yz_shuXueFragment = new YZ_ShuXueFragment();
    private YZ_SX_SMFragment yz_sx_smFragment = new YZ_SX_SMFragment();
    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private TitleBar mTitleBar;
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;
    private DocOrderPannel mDocOrderPannel;

    public Button mYzShuxueYichangBt;
    public Button mYzShuxueWanchengBt;
    private List<YiZhuBean.DataBean.OrderBloodPropertyBean> orderBloodProperty;
    private YiZhuBean.DataBean.OrderBloodPropertyBean orderBloodPropertyBean;
    private String username;
    private int xuedaiNO;
    private int userId2;
    private String userId;

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
        return R.layout.activity_yz__zx;
    }

    @Override
    public void initViews() {
        mBundle = getIntent().getBundleExtra("data");
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
        xuedaiNO = mBundle.getInt(Constant.BUNDLE_KEY_STATUS_EXCE);
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        username = sharePLogin.getUsername();
        userId = sharePLogin.getUserid();
        mYiZhuZhiXingFrameLayout = (FrameLayout) findViewById(R.id.yi_zhu_zhi_xing_frame_layout);
        mYzShuxueYichangBt = (Button) findViewById(R.id.yz_shuxue_yichang_bt);
        mYzShuxueWanchengBt = (Button) findViewById(R.id.yz_shuxue_wancheng_bt);
        if (null == mBundle) mSelectedHZ = null;
        else
            mSelectedHZ = (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
        mTitleBar = new TitleBar(this, mSelectedHZ);
        mDocOrderPannel = new DocOrderPannel(this, dataBean);
        if (dataBean != null) {
            if (Constant.TYPE_YZ_CAIXUE.equals(dataBean.getOrdertype())) {
                replaceFrag(R.id.yi_zhu_zhi_xing_frame_layout, yz_shuXueFragment);
            } else {
                replaceFrag(R.id.yi_zhu_zhi_xing_frame_layout, yz_sx_smFragment);
            }
            orderBloodProperty = dataBean.getOrderBloodProperty();
            if (orderBloodProperty != null && !orderBloodProperty.isEmpty()) {
                orderBloodPropertyBean = orderBloodProperty.get(xuedaiNO);
            } else {
                orderBloodPropertyBean = new YiZhuBean.DataBean.OrderBloodPropertyBean();
            }
            if (Constant.TYPE_YZ_CAIXUE.equals(dataBean.getOrdertype())) {
                mYzShuxueWanchengBt.setText("确认采血");
            }
        }

    }

    @Override
    public void afterBloodPackageScanned(String BloodPackageScanned) {
        if (orderBloodPropertyBean != null) {
            if (orderBloodPropertyBean.getBloodbagno() != null || BloodPackageScanned != null) {
                if (orderBloodPropertyBean.getBloodbagno().equals(BloodPackageScanned)) {
                    yz_sx_smFragment.mShuxueXuedaiHeduiCb3.performClick();
                }
            }
        }
    }

    @Override
    public void afterProductScanned(String ProductScanned) {
        if (orderBloodPropertyBean != null) {
            if (orderBloodPropertyBean.getProductno() != null || ProductScanned != null) {
                if (orderBloodPropertyBean.getProductno().equals(ProductScanned)) {
                    yz_sx_smFragment.mShuxueXuedaiHeduiCb4.performClick();
                }
            }
        }
    }

    @Override
    public void afterUserScanned(String UserScanned) {
        DBUserList first = DataSupport.where("empno like ?", UserScanned).findFirst(DBUserList.class);
        if (first != null) {
            if (username != null || UserScanned != null) {
                if (username.equalsIgnoreCase(UserScanned)) {
                    showMessage("双人确认不能是自己 ！");
                } else {
                    if (yz_sx_smFragment != null && yz_sx_smFragment.mShuxueXuedaiHeduiCb5 != null) {
                        yz_sx_smFragment.mShuxueXuedaiHeduiCb5.performClick();
                        yz_sx_smFragment.mShuxueXuedaiHeduiCb5.setText("双人确认：" + first.getName() +"  "+ DateUtils.getDateString("yyyy-MM-dd HH:mm",System.currentTimeMillis()));
                    }
                    if (yz_shuXueFragment != null && yz_shuXueFragment.mCaixueShuangrenCb != null) {
                        yz_shuXueFragment.mCaixueShuangrenCb.performClick();
                        yz_shuXueFragment.mCaixueShuangrenCb.setText("双人确认："  + first.getName() +"  "+ DateUtils.getDateString("yyyy-MM-dd HH:mm",System.currentTimeMillis()));
                    }

                    userId2 = first.getZid();
                }
            }
        } else {
            showMessage("此用户不存在！" + UserScanned);
        }
    }

    public void setBtEnadle(boolean flag) {
        if (flag) {
            mYzShuxueWanchengBt.setEnabled(flag);
            mYzShuxueWanchengBt.setBackgroundResource(R.drawable.ti_zheng_duo_ren_bt_selater1);
        } else {
            mYzShuxueWanchengBt.setEnabled(flag);
            mYzShuxueWanchengBt.setBackgroundResource(R.drawable.ti_zheng_duo_ren_bt_selater_hui);
        }

    }

    @Override
    public void initDatas() {
        //异常按钮
        mYzShuxueYichangBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(YZ_ZX_ZhuSheActivity.class, mBundle);
            }
        });
//        执行按钮监听
        mYzShuxueWanchengBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("确认采血".equals(mYzShuxueWanchengBt.getText().toString())) {
                    submitCaiXue();
                } else {
                    if (!mBundle.getBoolean(Constant.BUNDLE_KEY_IS_HUANYE)) {
                        mBundle.putBoolean(Constant.BUNDLE_KEY_IS_HUANYE, false);
                    }
                    mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SHIRULIANG, false);
                    mBundle.putString(Constant.BUNDLE_KEY_TYPE_EXCE, Constant.YZ_EXECUTE_JOBTYPE_HUANYE);
                    mBundle.putInt(Constant.BUNDLE_KEY_USER_ID2, userId2);
                    goToActivity(YZ_ZX_ShuYe_DiSuActivity.class, mBundle);
                }
                killSelf();
            }
        });
    }

    private void submitCaiXue() {
        if (dataBean != null) {
            dataBean.setOrderststus(Constant.YZ_TYPE_YIZHIXING);
            dataBean.setExetime(System.currentTimeMillis());
            dataBean.setExecuteby(userId);
            if (dataBean.getDoctorOrders() != null && dataBean.getDoctorOrders().size() > 0
                    && dataBean.getDoctorOrders().get(0) != null) {
                if (dataBean.getDoctorOrders().get(0).getOrderExecuteRecords() == null) {
                    dataBean.getDoctorOrders().get(0).setOrderExecuteRecords(new ArrayList<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean>());
                }
                YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean
                        = new YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean();
                orderExecuteRecordsBean.setExetime(System.currentTimeMillis());
                orderExecuteRecordsBean.setUserid(userId);
                orderExecuteRecordsBean.setJobtype(Constant.YZ_EXECUTE_JOBTYPE_CAIXUE);
                dataBean.getDoctorOrders().get(0).getOrderExecuteRecords().add(orderExecuteRecordsBean);
            }
            new DoctorOrderDaoImpl().saveDoctorOrder(dataBean,true);
            NetworkForDoctorOrder.submitSingleDoctorOrder(YZ_ZXActivity.this, dataBean);
        }

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
