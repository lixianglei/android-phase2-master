package com.ge.med.mobile.nursing.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.ge.med.mobile.nursing.ui.activity.Shu_Xue_SQ_YCActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_YaoPin_XiangQingActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_ShuYe_DiSuActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_ZhuSheActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_ZhuShe_BeiZhuActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_ZhuShe_FenCiActivity;
import com.ge.med.mobile.nursing.utils.MyExceptionDingYi;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class YZ_ZX_ZhuSheFragment extends BaseFragment implements View.OnClickListener, INetworkHandler {
    private Button mYzZxZhuSheXiangQingBt;
    private LinearLayout mYzZxZhuSheLl1;
    private LinearLayout mYzZxZhuSheLl2;
    private LinearLayout mYzZxZhuSheLl3;
    private LinearLayout mYzZxZhuSheLl4;
    private LinearLayout mYzZxZhuSheLl5;
    private LinearLayout mYzZxZhuSheLl6;
    private CheckBox mYzZxZhuSheCb11;
    private CheckBox mYzZxZhuSheCb12;
    private CheckBox mYzZxZhuSheCb13;
    private CheckBox mYzZxZhuSheCb21;
    private CheckBox mYzZxZhuSheCb22;
    private CheckBox mYzZxZhuSheCb23;
    private CheckBox mYzZxZhuSheCb31;
    private CheckBox mYzZxZhuSheCb32;
    private CheckBox mYzZxZhuSheCb33;
    private CheckBox mYzZxZhuSheCb41;
    private CheckBox mYzZxZhuSheCb42;
    private CheckBox mYzZxZhuSheCb43;
    private CheckBox mYzZxZhuSheCb51;
    private CheckBox mYzZxZhuSheCb52;
    private CheckBox mYzZxZhuSheCb53;
    private CheckBox mYzZxZhuSheCb61;
    private CheckBox mYzZxZhuSheCb62;
    private CheckBox mYzZxZhuSheCb63;
    private LinearLayout mYzZxZhuSheBtLl;
    public Button mYzZxZhuSheNextBt;
    public Button mYzZxZhuSheFenciBt;
    public Button mYzZxZhuSheBeizhuBt;
    private Button mYzZxZhuSheZhixingBt;
    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private DBHuanZheLieBiao huanZheLieBiao;
    private YZ_ZX_ZhuSheActivity yz_zx_zhuSheActivity;
    private List<String> list = new ArrayList<>();
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private String displayUserName;
    private boolean isShuYe = false;
    private boolean isCaiXue = false;
    private Button mYzZxShuyeBt;
    private Button mYzZxShuxueBt;
    private boolean isXunShiGoto;
    private String userid;


    @Override
    public int setRootView() {
        return R.layout.fragment_yz__zx__zhu_she;
    }

    @Override
    public void initViews() {
        yz_zx_zhuSheActivity = (YZ_ZX_ZhuSheActivity) mActivitySelf;
        mBundle = yz_zx_zhuSheActivity.getmBundle();
        huanZheLieBiao = yz_zx_zhuSheActivity.getHuanZheLieBiao();
        dataBean = yz_zx_zhuSheActivity.getDataBean();
        mYzZxZhuSheXiangQingBt = (Button) mRootView.findViewById(R.id.yz_zx_zhu_she_xiang_qing_bt);
        mYzZxZhuSheLl1 = (LinearLayout) mRootView.findViewById(R.id.yz_zx_zhu_she_ll1);
        mYzZxZhuSheCb11 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb1_1);
        mYzZxZhuSheCb12 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb1_2);
        mYzZxZhuSheCb13 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb1_3);
        mYzZxZhuSheLl2 = (LinearLayout) mRootView.findViewById(R.id.yz_zx_zhu_she_ll2);
        mYzZxZhuSheCb21 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb2_1);
        mYzZxZhuSheCb22 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb2_2);
        mYzZxZhuSheCb23 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb2_3);
        mYzZxZhuSheLl3 = (LinearLayout) mRootView.findViewById(R.id.yz_zx_zhu_she_ll3);
        mYzZxZhuSheCb31 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb3_1);
        mYzZxZhuSheCb32 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb3_2);
        mYzZxZhuSheCb33 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb3_3);
        mYzZxZhuSheLl4 = (LinearLayout) mRootView.findViewById(R.id.yz_zx_zhu_she_ll4);
        mYzZxZhuSheCb41 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb4_1);
        mYzZxZhuSheCb42 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb4_2);
        mYzZxZhuSheCb43 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb4_3);
        mYzZxZhuSheLl5 = (LinearLayout) mRootView.findViewById(R.id.yz_zx_zhu_she_ll5);
        mYzZxZhuSheCb51 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb5_1);
        mYzZxZhuSheCb52 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb5_2);
        mYzZxZhuSheCb53 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb5_3);
        mYzZxZhuSheLl6 = (LinearLayout) mRootView.findViewById(R.id.yz_zx_zhu_she_ll6);
        mYzZxZhuSheCb61 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb6_1);
        mYzZxZhuSheCb62 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb6_2);
        mYzZxZhuSheCb63 = (CheckBox) mRootView.findViewById(R.id.yz_zx_zhu_she_cb6_3);
        mYzZxZhuSheBtLl = (LinearLayout) mRootView.findViewById(R.id.yz_zx_zhu_she_bt_ll);
        mYzZxZhuSheNextBt = (Button) mRootView.findViewById(R.id.yz_zx_zhu_she_next_bt);
        mYzZxZhuSheFenciBt = (Button) mRootView.findViewById(R.id.yz_zx_zhu_she_fenci_bt);
        mYzZxZhuSheBeizhuBt = (Button) mRootView.findViewById(R.id.yz_zx_zhu_she_beizhu_bt);
        mYzZxZhuSheZhixingBt = (Button) mRootView.findViewById(R.id.yz_zx_zhu_she_zhixing_bt);
        mYzZxShuxueBt = (Button) mRootView.findViewById(R.id.yz_zx_shuxue_bt);
        mYzZxShuyeBt = (Button) mRootView.findViewById(R.id.yz_zx_shuye_bt);

        mYzZxZhuSheCb11.setOnClickListener(this);
        mYzZxZhuSheCb12.setOnClickListener(this);
        mYzZxZhuSheCb13.setOnClickListener(this);
        mYzZxZhuSheCb21.setOnClickListener(this);
        mYzZxZhuSheCb22.setOnClickListener(this);
        mYzZxZhuSheCb23.setOnClickListener(this);
        mYzZxZhuSheCb31.setOnClickListener(this);
        mYzZxZhuSheCb32.setOnClickListener(this);
        mYzZxZhuSheCb33.setOnClickListener(this);
        mYzZxZhuSheCb41.setOnClickListener(this);
        mYzZxZhuSheCb42.setOnClickListener(this);
        mYzZxZhuSheCb43.setOnClickListener(this);
        mYzZxZhuSheCb51.setOnClickListener(this);
        mYzZxZhuSheCb52.setOnClickListener(this);
        mYzZxZhuSheCb53.setOnClickListener(this);
        mYzZxZhuSheCb61.setOnClickListener(this);
        mYzZxZhuSheCb62.setOnClickListener(this);
        mYzZxZhuSheCb63.setOnClickListener(this);
        mYzZxZhuSheNextBt.setOnClickListener(this);
        mYzZxZhuSheFenciBt.setOnClickListener(this);
        mYzZxZhuSheBeizhuBt.setOnClickListener(this);
        mYzZxZhuSheZhixingBt.setOnClickListener(this);
        mYzZxZhuSheXiangQingBt.setOnClickListener(this);
        checkBoxes.add(mYzZxZhuSheCb11);
        checkBoxes.add(mYzZxZhuSheCb12);
        checkBoxes.add(mYzZxZhuSheCb13);
        checkBoxes.add(mYzZxZhuSheCb21);
        checkBoxes.add(mYzZxZhuSheCb22);
        checkBoxes.add(mYzZxZhuSheCb23);
        checkBoxes.add(mYzZxZhuSheCb31);
        checkBoxes.add(mYzZxZhuSheCb32);
        checkBoxes.add(mYzZxZhuSheCb33);
        checkBoxes.add(mYzZxZhuSheCb41);
        checkBoxes.add(mYzZxZhuSheCb42);
        checkBoxes.add(mYzZxZhuSheCb43);
        checkBoxes.add(mYzZxZhuSheCb51);
        checkBoxes.add(mYzZxZhuSheCb52);
        checkBoxes.add(mYzZxZhuSheCb53);
        checkBoxes.add(mYzZxZhuSheCb61);
        checkBoxes.add(mYzZxZhuSheCb62);
        checkBoxes.add(mYzZxZhuSheCb63);
//根据医嘱类型配置异常
        MyExceptionDingYi.setZhuShe_NOExce(checkBoxes, dataBean.getOrdertype());
//根据医嘱类型配置异常
//        if (Constant.TYPE_YZ_SHUXUE.equals(dataBean.getOrdertype())) {
//            MyExceptionDingYi.setZhuShe_NOExce(checkBoxes, dataBean.getOrdertype());
//        } else {
//            MyExceptionDingYi.setZhuShe_NOExce(checkBoxes, Constant.TYPE_YZ_ZHUSHE);
//        }
        if (mYzZxZhuSheCb21.getVisibility() == View.GONE) {
            mYzZxZhuSheLl2.setVisibility(View.GONE);
        }
        if (mYzZxZhuSheCb31.getVisibility() == View.GONE) {
            mYzZxZhuSheLl3.setVisibility(View.GONE);
        }

    }

    @Override
    public void initDatas() {
        mBundle.putInt("hisid", dataBean.getId());
        mBundle.putString("name", huanZheLieBiao.getName());
        mBundle.putString("carelevel", huanZheLieBiao.getCarelevel());
        mBundle.putInt("bedno", Integer.parseInt(huanZheLieBiao.getBedno()));
        mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU, dataBean);
        isXunShiGoto = mBundle.getBoolean(Constant.BUNDLE_KEY_IS_XUNSHI_GOTO);
        if(isXunShiGoto || Constant.YZ_TYPE_DAIBAIYAO.equals(dataBean.getOrderststus()) || Constant.YZ_TYPE_DAIPEIYE.equals(dataBean.getOrderststus()) ){
            isShuYe = true;
            mYzZxShuyeBt.setText("继续执行");
            mYzZxShuyeBt.setVisibility(View.VISIBLE);
            mYzZxShuxueBt.setVisibility(View.GONE);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2.0f);
            mYzZxZhuSheBeizhuBt.setLayoutParams(lp);
            mYzZxZhuSheZhixingBt.setVisibility(View.GONE);
            mYzZxZhuSheFenciBt.setVisibility(View.GONE);
        }
    }

    @Override
    public void init() {
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        displayUserName = sharePLogin.getDisplayUserName();
        userid = sharePLogin.getUserid();
        if (null != yz_zx_zhuSheActivity.getString() && Constant.ZHU_SHE_2.equals(yz_zx_zhuSheActivity.getString())) {
            mYzZxZhuSheNextBt.setVisibility(View.GONE);
            mYzZxZhuSheFenciBt.setVisibility(View.GONE);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2.0f);
            mYzZxZhuSheBeizhuBt.setLayoutParams(lp);
            mYzZxZhuSheNextBt.setLayoutParams(lp);
        }

        //如果是皮试 入壶 跳转过来的 在这里处理
        if (Constant.TYPE_YZ_PISHI.equals(dataBean.getOrdertype()) || Constant.TYPE_YZ_RUHU.equals(dataBean.getOrdertype())) {
            isShuYe = true;
            mYzZxShuyeBt.setVisibility(View.GONE);
            mYzZxShuxueBt.setVisibility(View.GONE);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2.0f);
            mYzZxZhuSheBeizhuBt.setLayoutParams(lp);
            mYzZxZhuSheZhixingBt.setVisibility(View.VISIBLE);
            mYzZxZhuSheFenciBt.setVisibility(View.GONE);
        }

        //如果是输液 跳转过来的 在这里处理
        if (Constant.TYPE_YZ_SHUYE.equals(dataBean.getOrdertype())) {
            isShuYe = true;
            mYzZxShuyeBt.setVisibility(View.VISIBLE);
            mYzZxShuxueBt.setVisibility(View.GONE);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2.0f);
            mYzZxZhuSheBeizhuBt.setLayoutParams(lp);
            mYzZxZhuSheZhixingBt.setVisibility(View.GONE);
            mYzZxZhuSheFenciBt.setVisibility(View.GONE);
        }
        //如果是采血 跳转过来的 在这里处理
        if (Constant.TYPE_YZ_CAIXUE.equals(dataBean.getOrdertype())) {
            isCaiXue = true;
            mYzZxShuyeBt.setText("继续执行");
            mYzZxShuyeBt.setVisibility(View.VISIBLE);
            mYzZxShuxueBt.setVisibility(View.GONE);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2.0f);
            mYzZxZhuSheBeizhuBt.setLayoutParams(lp);
            mYzZxZhuSheZhixingBt.setVisibility(View.GONE);
            mYzZxZhuSheFenciBt.setVisibility(View.GONE);
        }
        //开始输液按钮监听
        mYzZxShuyeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("开始输液".equals(mYzZxShuyeBt.getText())){
                    mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SHIRULIANG, false);
                    mBundle.putBoolean(Constant.BUNDLE_KEY_IS_HUANYE, false);
                    mBundle.putString(Constant.BUNDLE_KEY_TYPE_EXCE, Constant.YZ_EXECUTE_JOBTYPE_XUNSHI);
                    goToActivity(YZ_ZX_ShuYe_DiSuActivity.class, mBundle);
                }
                yz_zx_zhuSheActivity.killSelf();
            }
        });
        //如果是输血跳转过来的在这里处理界面按钮
        //口服 辅助治疗
        if (Constant.TYPE_YZ_SHUXUE.equals(dataBean.getOrdertype())
                || Constant.TYPE_YZ_KOUFU.equals(dataBean.getOrdertype())
                || Constant.TYPE_YZ_SHANSHI.equals(dataBean.getOrdertype())
                || Constant.TYPE_YZ_ZHILIAO.equals(dataBean.getOrdertype())
                || Constant.TYPE_YZ_HULI.equals(dataBean.getOrdertype())) {
            isShuYe = true;
            mYzZxShuxueBt.setVisibility(View.VISIBLE);
            if (Constant.TYPE_YZ_SHANSHI.equals(dataBean.getOrdertype())
                    ||Constant.TYPE_YZ_HULI.equals(dataBean.getOrdertype())
                    ||Constant.TYPE_YZ_ZHILIAO.equals(dataBean.getOrdertype())) {
                mYzZxShuxueBt.setText("确认执行");
            }
            if (Constant.TYPE_YZ_KOUFU.equals(dataBean.getOrdertype())) {
                mYzZxShuxueBt.setText("确认服药");
            }

            mYzZxShuyeBt.setVisibility(View.GONE);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2.0f);
            mYzZxZhuSheBeizhuBt.setLayoutParams(lp);
            mYzZxZhuSheZhixingBt.setVisibility(View.GONE);
            mYzZxZhuSheFenciBt.setVisibility(View.GONE);
        }
        //输血按继续执行按钮钮监听
        mYzZxShuxueBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constant.TYPE_YZ_KOUFU.equals(dataBean.getOrdertype())
                        || Constant.TYPE_YZ_ZHILIAO.equals(dataBean.getOrdertype())
                        || Constant.TYPE_YZ_HULI.equals(dataBean.getOrdertype())
                        || Constant.TYPE_YZ_SHANSHI.equals(dataBean.getOrdertype())) {
                    mYzZxZhuSheZhixingBt.performClick();
                } else {
                    yz_zx_zhuSheActivity.killSelf();
                }
            }
        });
        if (null != yz_zx_zhuSheActivity.getString() && Constant.ZHU_SHE_2.equals(yz_zx_zhuSheActivity.getString())) {
            mYzZxZhuSheZhixingBt.setText("继续执行");
        }
        if (dataBean.getPharmList() == null || dataBean.getPharmList().size() <= 0) {
            mYzZxZhuSheXiangQingBt.setVisibility(View.GONE);
        }
        if (Constant.YZ_TYPE_DAIBAIYAO.equals(dataBean.getOrderststus())) {
            mYzZxShuxueBt.setText("确认摆药");
        }
    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dataBean = new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId());
        mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU, dataBean);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //  cb1_1
            case R.id.yz_zx_zhu_she_cb1_1:
                btKongZhi(mYzZxZhuSheCb11);
                break;
            //  cb1_2
            case R.id.yz_zx_zhu_she_cb1_2:
                btKongZhi(mYzZxZhuSheCb12);
                break;
            //  cb1_3
            case R.id.yz_zx_zhu_she_cb1_3:
                btKongZhi(mYzZxZhuSheCb13);
                break;
            //  cb2_1
            case R.id.yz_zx_zhu_she_cb2_1:
                btKongZhi(mYzZxZhuSheCb21);
                break;
            //  cb2_2
            case R.id.yz_zx_zhu_she_cb2_2:
                btKongZhi(mYzZxZhuSheCb22);
                break;
            //  cb2_3
            case R.id.yz_zx_zhu_she_cb2_3:
                btKongZhi(mYzZxZhuSheCb23);
                break;
            //  cb3_1
            case R.id.yz_zx_zhu_she_cb3_1:
                btKongZhi(mYzZxZhuSheCb31);
                break;
            //  cb3_2
            case R.id.yz_zx_zhu_she_cb3_2:
                btKongZhi(mYzZxZhuSheCb32);
                break;
            //  cb3_3
            case R.id.yz_zx_zhu_she_cb3_3:
                btKongZhi(mYzZxZhuSheCb33);
                break;
            //  cb4_1
            case R.id.yz_zx_zhu_she_cb4_1:
                btKongZhi(mYzZxZhuSheCb41);
                break;
            //  cb4_2
            case R.id.yz_zx_zhu_she_cb4_2:
                btKongZhi(mYzZxZhuSheCb42);
                break;
            //  cb4_3
            case R.id.yz_zx_zhu_she_cb4_3:
                btKongZhi(mYzZxZhuSheCb43);
                break;
            //  cb5_1
            case R.id.yz_zx_zhu_she_cb5_1:
                btKongZhi(mYzZxZhuSheCb51);
                break;
            //  cb5_2
            case R.id.yz_zx_zhu_she_cb5_2:
                btKongZhi(mYzZxZhuSheCb52);
                break;
            //  cb5_3
            case R.id.yz_zx_zhu_she_cb5_3:
                btKongZhi(mYzZxZhuSheCb53);
                break;
            //  cb6_1
            case R.id.yz_zx_zhu_she_cb6_1:
                btKongZhi(mYzZxZhuSheCb61);
                break;
            //  cb6_2
            case R.id.yz_zx_zhu_she_cb6_2:

                btKongZhi(mYzZxZhuSheCb62);
                break;
            //  cb6_3
            case R.id.yz_zx_zhu_she_cb6_3:
                btKongZhi(mYzZxZhuSheCb63);
                break;
            //  下一步
            case R.id.yz_zx_zhu_she_next_bt:
                String s = list.get(0);
                if (s != null && !s.isEmpty()) {
                    mBundle.putString(Constant.BUNDLE_KEY_YICHANG, s);
                    goToActivity(Shu_Xue_SQ_YCActivity.class, mBundle);
                } else {
                    showToastShort("请选择异常信息！");
                }
                break;
            //  分次
            case R.id.yz_zx_zhu_she_fenci_bt:
                goToActivity(YZ_ZX_ZhuShe_FenCiActivity.class, mBundle);
                break;
            //  备注
            case R.id.yz_zx_zhu_she_beizhu_bt:
                goToActivity(YZ_ZX_ZhuShe_BeiZhuActivity.class, mBundle);
                break;
            //  注射 or 皮试执行
            case R.id.yz_zx_zhu_she_zhixing_bt:
                dataBean.setOrderststus(Constant.YZ_TYPE_YIZHIXING);
                if (null != yz_zx_zhuSheActivity.getString() && Constant.ZHU_SHE_2.equals(yz_zx_zhuSheActivity.getString())) {
                    yz_zx_zhuSheActivity.killSelf();
                }
                dataBean.setLastupdatedby(userid);
                dataBean.setExecuteby(userid);
                long l = System.currentTimeMillis();
                dataBean.setLastupdatetime(l);
                dataBean.setExetime(l);
                if (dataBean.getDoctorOrders() != null && dataBean.getDoctorOrders().get(0) != null) {
                    if(dataBean.getDoctorOrders().get(0).getOrderExecuteRecords() == null ){
                        dataBean.getDoctorOrders().get(0).setOrderExecuteRecords(new ArrayList<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean>());
                    }
                    YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean = new YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean();
                    orderExecuteRecordsBean.setUserid(userid);
                    orderExecuteRecordsBean.setJobtype(Constant.YZ_EXECUTE_JOBTYPE_ZHIXING);
                    orderExecuteRecordsBean.setExetime(System.currentTimeMillis());
                    dataBean.getDoctorOrders().get(0).getOrderExecuteRecords().add(orderExecuteRecordsBean);
                }
                new DoctorOrderDaoImpl().saveDoctorOrder(dataBean,true);
                NetworkForDoctorOrder.submitSingleDoctorOrder(this, dataBean);
                mActivitySelf.killSelf();
                break;
            //详情
            case R.id.yz_zx_zhu_she_xiang_qing_bt:
                goToActivity(YZ_YaoPin_XiangQingActivity.class, mBundle);
                break;
        }
    }

    private void btKongZhi(CheckBox checkBox) {
        for (CheckBox checkBox1 : checkBoxes) {
            if (checkBox1.getId() == checkBox.getId()) {

            } else {
                checkBox1.setChecked(false);
            }
            if (checkBox1.isChecked()) {
                if ("".equals(checkBox1.getText().toString()) || checkBox1.getText().toString().isEmpty()) {
                    checkBox1.setChecked(false);
                    list.remove(checkBox1.getText().toString());
                } else {
                    list.add(checkBox1.getText().toString());
                }
            } else {
                list.remove(checkBox1.getText().toString());
            }
        }
        if (list.size() > 0) {
            mYzZxZhuSheNextBt.setVisibility(View.VISIBLE);
            if (isShuYe || isCaiXue) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 2.0f);
                mYzZxZhuSheNextBt.setLayoutParams(lp);
            }
            mYzZxZhuSheFenciBt.setVisibility(View.GONE);
            mYzZxZhuSheZhixingBt.setText("继续执行");
            mYzZxShuyeBt.setText("继续执行");
            mYzZxShuxueBt.setText("继续执行");
        } else {
            mYzZxZhuSheZhixingBt.setText("确认执行");
            mYzZxZhuSheNextBt.setVisibility(View.GONE);
            mYzZxShuyeBt.setText("开始输液");
            if(isXunShiGoto || Constant.YZ_TYPE_DAIBAIYAO.equals(dataBean.getOrderststus()) || Constant.YZ_TYPE_DAIPEIYE.equals(dataBean.getOrderststus()) ){
                mYzZxShuyeBt.setText("继续执行");
            }
            if (!isShuYe) {
                mYzZxZhuSheFenciBt.setVisibility(View.VISIBLE);
                mYzZxShuyeBt.setText("继续执行");
            }
            if (isXunShiGoto||isCaiXue) {
                mYzZxZhuSheFenciBt.setVisibility(View.GONE);
                mYzZxShuyeBt.setText("继续执行");
            }
            if (Constant.TYPE_YZ_ZHILIAO.equals(dataBean.getOrdertype())
                    ||Constant.TYPE_YZ_SHANSHI.equals(dataBean.getOrdertype())
                    ||Constant.TYPE_YZ_HULI.equals(dataBean.getOrdertype())) {
                mYzZxShuxueBt.setText("确认执行");
            }
            if (Constant.TYPE_YZ_KOUFU.equals(dataBean.getOrdertype())) {
                mYzZxShuxueBt.setText("确认服药");
            }
            //注射分次 异常跳转过来的
            if (null != yz_zx_zhuSheActivity.getString() && Constant.ZHU_SHE_2.equals(yz_zx_zhuSheActivity.getString())) {
                mYzZxZhuSheZhixingBt.setText("确认执行");
                mYzZxZhuSheFenciBt.setVisibility(View.GONE);
            }
        }

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
