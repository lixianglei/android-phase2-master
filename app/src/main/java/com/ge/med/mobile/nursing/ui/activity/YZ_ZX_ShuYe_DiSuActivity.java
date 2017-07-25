package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.sj.library.control.ActivityControl;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.HuanZheLieBiaoInterface;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.forjson.callback.INetworkHandler;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.AdapterUtil;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.utils.DataConverter;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class YZ_ZX_ShuYe_DiSuActivity extends MyBaseActivity implements View.OnClickListener {

    private TextView mDrtzSjImgv;
    private TextView mTiZhengZhi;
    private TextView mTiZhengDanWei;
    private LinearLayout mDiyiPaiBtLl1;
    private Button mBt11;
    private Button mBt12;
    private Button mBt13;
    private Button mBt14;
    private Button mBt21;
    private Button mBt22;
    private Button mBt23;
    private Button mBt24;
    private Button mBt31;
    private Button mBt32;
    private Button mBt33;
    private Button mBt34;
    private Button mBt41;
    private Button mBt42;
    private Button mBt43;
    private ImageButton mBt44;
    private RadioGroup mDiyiPaiBtLl5;
    private Button mYzShuxueBeizhuBt;
    private Button mYzShuxueYichangBt;
    private Button mYzShuxueWanchengBt;
    private Button mYzShuxueShiruliangBt;
    private Button mYzShuxueHuanyeNextBt;
    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private DBHuanZheLieBiao huanZheLieBiao;
    private TitleBar mTitleBar;
    private DocOrderPannel mDocOrderPannel;
    private HuanZheLieBiaoBean.DataBean huanZheLieBiaoInterfaceDataBean;
    private HuanZheLieBiaoInterface huanZheLieBiaoInterface;
    private Button mYzShuxueWanchengXunshiBt;
    private String userid;
    private StringBuilder stringBuilder;
    private boolean isXunShiGoto;
    private YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean;
    private int execStatus;
    private int userId2;

    @Override
    public int setRootView() {
        return R.layout.activity_yz__zx__shu_ye__di_su;
    }

    @Override
    public void initViews() {
        mDrtzSjImgv = (TextView) findViewById(R.id.drtz_sj_imgv);
        mTiZhengZhi = (TextView) findViewById(R.id.ti_zheng_zhi);
        mTiZhengDanWei = (TextView) findViewById(R.id.ti_zheng_dan_wei);
        mDiyiPaiBtLl1 = (LinearLayout) findViewById(R.id.diyi_pai_bt_ll_1);
        mBt11 = (Button) findViewById(R.id.bt_1_1);
        mBt12 = (Button) findViewById(R.id.bt_1_2);
        mBt13 = (Button) findViewById(R.id.bt_1_3);
        mBt14 = (Button) findViewById(R.id.bt_1_4);
        mBt21 = (Button) findViewById(R.id.bt_2_1);
        mBt22 = (Button) findViewById(R.id.bt_2_2);
        mBt23 = (Button) findViewById(R.id.bt_2_3);
        mBt24 = (Button) findViewById(R.id.bt_2_4);
        mBt31 = (Button) findViewById(R.id.bt_3_1);
        mBt32 = (Button) findViewById(R.id.bt_3_2);
        mBt33 = (Button) findViewById(R.id.bt_3_3);
        mBt34 = (Button) findViewById(R.id.bt_3_4);
        mBt41 = (Button) findViewById(R.id.bt_4_1);
        mBt42 = (Button) findViewById(R.id.bt_4_2);
        mBt43 = (Button) findViewById(R.id.bt_4_3);
        mBt44 = (ImageButton) findViewById(R.id.bt_4_4);
        mDiyiPaiBtLl5 = (RadioGroup) findViewById(R.id.diyi_pai_bt_ll_5);
        mYzShuxueBeizhuBt = (Button) findViewById(R.id.yz_shuxue_beizhu_bt);
        mYzShuxueYichangBt = (Button) findViewById(R.id.yz_shuxue_yichang_bt);
        mYzShuxueWanchengBt = (Button) findViewById(R.id.yz_shuxue_wancheng_bt);
        mYzShuxueShiruliangBt = (Button) findViewById(R.id.yz_shuxue_shiruliang_bt);
        mYzShuxueHuanyeNextBt = (Button) findViewById(R.id.yz_shuxue_huanye_next_bt);
    }

    @Override
    public void initDatas() {
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        userid = sharePLogin.getUserid();
        mBundle = getIntent().getBundleExtra("data");
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
        huanZheLieBiao = AdapterUtil.getPatientFromDB(dataBean.getPatientid());
        huanZheLieBiaoInterface = new HuanZheLieBiaoImpl();
        huanZheLieBiaoInterfaceDataBean = huanZheLieBiaoInterface.getDataBean(huanZheLieBiao);
        mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, huanZheLieBiaoInterfaceDataBean);
        mYzShuxueWanchengXunshiBt = (Button) findViewById(R.id.yz_shuxue_wancheng_xunshi_bt);
        mTitleBar = new TitleBar(this, huanZheLieBiaoInterfaceDataBean);
        mDocOrderPannel = new DocOrderPannel(this, dataBean);
        mDrtzSjImgv.setText("录入滴速");
        mTiZhengZhi.setText("");
        mTiZhengDanWei.setText("滴/分");
        mDiyiPaiBtLl1.setVisibility(View.INVISIBLE);
        mBt11.setOnClickListener(this);
        mBt12.setOnClickListener(this);
        mBt13.setOnClickListener(this);
        mBt14.setOnClickListener(this);
        mBt21.setOnClickListener(this);
        mBt22.setOnClickListener(this);
        mBt23.setOnClickListener(this);
        mBt24.setOnClickListener(this);
        mBt31.setOnClickListener(this);
        mBt32.setOnClickListener(this);
        mBt33.setOnClickListener(this);
        mBt34.setOnClickListener(this);
        mBt41.setOnClickListener(this);
        mBt42.setOnClickListener(this);
        mBt43.setOnClickListener(this);
        mBt44.setOnClickListener(this);
        mYzShuxueWanchengBt.setOnClickListener(this);
        mDiyiPaiBtLl5.setVisibility(View.GONE);
        orderExecuteRecordsBean = (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean) mBundle.getSerializable(Constant.BUNDLE_KEY_XUNSHI_JILU);
        if (orderExecuteRecordsBean == null) {
            orderExecuteRecordsBean = new YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean();
        }
        //备注跳转
        mYzShuxueBeizhuBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(YZ_ZX_ZhuShe_BeiZhuActivity.class, mBundle);
            }
        });
    }

    @Override
    public void init() {
        //异常按钮
        mYzShuxueYichangBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_XUNSHI_GOTO, true);
                mBundle.putString(Constant.ZHU_SHE_2_KEY, Constant.ZHU_SHE_2);
                goToActivity(YZ_ZX_ZhuSheActivity.class, mBundle);
            }
        });
        final int shiRuLiang = mBundle.getInt(Constant.BUNDLE_KEY_SHIRULIANG);
        //是否为滴速界面跳转过来的
        final int diSu = mBundle.getInt(Constant.BUNDLE_KEY_DISU, 0);
        boolean isShiRuLiang = mBundle.getBoolean(Constant.BUNDLE_KEY_IS_SHIRULIANG, false);
        boolean isDiSu = mBundle.getBoolean(Constant.BUNDLE_KEY_IS_DISU, false);
        boolean isHuanYe = mBundle.getBoolean(Constant.BUNDLE_KEY_IS_HUANYE, false);
        execStatus = mBundle.getInt(Constant.BUNDLE_KEY_STATUS_EXCE);
        userId2 = mBundle.getInt(Constant.BUNDLE_KEY_USER_ID2);
        if (diSu == 0) {
            mTiZhengZhi.setText("");
        } else {
            mTiZhengZhi.setText(diSu + "");
        }
        //换液 上一袋液体实入量
        if (!isShiRuLiang && !isDiSu && isHuanYe) {
            mTiZhengDanWei.setText("ml");
            mTiZhengZhi.setText("");
            mDrtzSjImgv.setText("上一袋液体实入量");
            mYzShuxueShiruliangBt.setVisibility(View.GONE);
            mYzShuxueHuanyeNextBt.setVisibility(View.VISIBLE);
            if (Constant.TYPE_YZ_SHUXUE.equals(dataBean.getOrdertype()) && mBundle.getBoolean(Constant.BUNDLE_KEY_IS_WANCHENG_SHUYE, false)) {
                mYzShuxueHuanyeNextBt.setText("完成输血");
            }
            if (Constant.TYPE_YZ_SHUYE.equals(dataBean.getOrdertype()) && mBundle.getBoolean(Constant.BUNDLE_KEY_IS_WANCHENG_SHUYE, false)) {
                mYzShuxueHuanyeNextBt.setText("完成输液");
            }
            mYzShuxueWanchengBt.setVisibility(View.GONE);
        }
        //巡视时间界面
        if (diSu != 0 && !isShiRuLiang && isDiSu && !isHuanYe) {
            String string = mBundle.getString(Constant.BUNDLE_KEY_XUNSHI_SJ, "");
            mTiZhengZhi.setText(string);
            mYzShuxueWanchengXunshiBt.setVisibility(View.VISIBLE);
            mYzShuxueWanchengBt.setVisibility(View.GONE);
            mYzShuxueHuanyeNextBt.setVisibility(View.GONE);
            mDrtzSjImgv.setText("设置巡视间隔时间");
            mTiZhengDanWei.setText("分钟");
        }
        //实入量
        if (diSu != 0 && isShiRuLiang && isDiSu && !isHuanYe) {
            mTiZhengDanWei.setText("ml");
            mTiZhengZhi.setText("");
            mYzShuxueShiruliangBt.setVisibility(View.VISIBLE);
            mYzShuxueHuanyeNextBt.setVisibility(View.GONE);
            mYzShuxueWanchengBt.setVisibility(View.GONE);
            mDrtzSjImgv.setText("录入实入量");
        }

//实入量 按钮监听
        mYzShuxueShiruliangBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mTiZhengZhi.getText().toString();
                if (null == s || "".equals(s) || "0".equals(s)) {
                    showToastShort("请输入实入量！");
                    return;
                }
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_DISU, true);
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SHIRULIANG, false);
                mBundle.putInt(Constant.BUNDLE_KEY_SHIRULIANG, Integer.parseInt(s));
                goToActivity(YZ_ZX_ShuYe_DiSuActivity.class, mBundle);
            }
        });
//换液下一步
        mYzShuxueHuanyeNextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constant.TYPE_YZ_SHUXUE.equals(dataBean.getOrdertype())
                        ||Constant.TYPE_YZ_SHUYE.equals(dataBean.getOrdertype())) {
                    String s = mTiZhengZhi.getText().toString();
                    if (null == s || "".equals(s) || "0".equals(s)) {
                        showToastShort("请输入实入量！");
                        return;
                    }
                    mBundle.putInt(Constant.BUNDLE_KEY_SHIRULIANG, Integer.parseInt(s));
                    if (mBundle.getBoolean(Constant.BUNDLE_KEY_IS_WANCHENG_SHUYE, false)) {
                        long l = System.currentTimeMillis();
                        String exceType = mBundle.getString(Constant.BUNDLE_KEY_TYPE_EXCE);
                        tijiao(l, exceType, null, null, s);
                        dataBean.setOrderststus(Constant.YZ_TYPE_YIZHIXING);
                        dataBean.setExetime(System.currentTimeMillis());
                        dataBean.setLastupdatetime(System.currentTimeMillis());
                        dataBean.setLastupdatedby(userid);
                        dataBean.setExecuteby(userid);
                        new DoctorOrderDaoImpl().saveDoctorOrder(dataBean, true);
                        NetworkForDoctorOrder.submitSingleDoctorOrder(new INetworkHandler() {
                            @Override
                            public void handleOnError() {
                                ActivityControl.killActivity(YZ_ZX_ShuYeActivity.class);
                                ActivityControl.killActivity(YZ_ZX_ShuYe_DiSuActivity.class);
                            }

                            @Override
                            public void handleOnError(String urlStr) {
                                ActivityControl.killActivity(YZ_ZX_ShuYeActivity.class);
                                ActivityControl.killActivity(YZ_ZX_ShuYe_DiSuActivity.class);
                            }

                            @Override
                            public void handleSuccess(Object obj) {
                                ActivityControl.killActivity(YZ_ZX_ShuYeActivity.class);
                                ActivityControl.killActivity(YZ_ZX_ShuYe_DiSuActivity.class);
                            }
                        }, dataBean);
                        return;
                    }
                    mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SHIRULIANG, false);
                    mBundle.putBoolean(Constant.BUNDLE_KEY_IS_HUANYE, false);
                    goToActivity(YZ_ZX_ShuYe_DiSuActivity.class, mBundle);
                    return;
                }
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_HUANYE, false);
                String s = mTiZhengZhi.getText().toString();
                if (null == s || "".equals(s) || "0".equals(s)) {
                    showToastShort("请输入滴速！");
                    return;
                }

                mBundle.putInt(Constant.BUNDLE_KEY_SHIRULIANG, Integer.parseInt(s));
                goToActivity(YZ_ZX_ShuYe_HuanYeActivity.class, mBundle);
            }
        });
        //巡视时间间隔按钮监听
        mYzShuxueWanchengXunshiBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("orderExecuteRecordsBean  0=" + orderExecuteRecordsBean);
                long l = System.currentTimeMillis();
                String exceType = mBundle.getString(Constant.BUNDLE_KEY_TYPE_EXCE);
                String s = mTiZhengZhi.getText().toString();
                if (null == s || "".equals(s) || "0".equals(s)) {
                    showToastShort("请输入巡视时间！");
                    return;
                }
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_DISU, false);
                mBundle.putString(Constant.BUNDLE_KEY_XUNSHI_SJ, s);
                tijiao(l, exceType, s, diSu, shiRuLiang+"");
                jiaZaiDialog.show();
                new DoctorOrderDaoImpl().saveDoctorOrder(dataBean, true);
                NetworkForDoctorOrder.submitSingleDoctorOrder(YZ_ZX_ShuYe_DiSuActivity.this, dataBean);
            }

        });
    }

    private void tijiao(long l, String exceType, String s, Integer diSu, String shiRuLiang) {
        List<YiZhuBean.DataBean.DoctorOrdersBean> doctorOrders = dataBean.getDoctorOrders();
        List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> orderExecuteRecordsBeens;
        if (doctorOrders != null && !doctorOrders.isEmpty()) {
            for (YiZhuBean.DataBean.DoctorOrdersBean doctorOrdersBean : doctorOrders) {
                orderExecuteRecordsBeens = doctorOrdersBean.getOrderExecuteRecords();
                if (orderExecuteRecordsBeens == null) {
                    orderExecuteRecordsBeens = new ArrayList<>();
                }
                orderExecuteRecordsBean.setDropspeed(diSu + "");
                orderExecuteRecordsBean.setJobtype(exceType);
                orderExecuteRecordsBean.setActualvolume(shiRuLiang + "");
                if (Constant.YZ_EXECUTE_JOBTYPE_HUANYE.equals(exceType)) {
                    orderExecuteRecordsBean.setJobStatus(execStatus + 1 + "");
                    orderExecuteRecordsBean.setUserid2(userId2 + "");
                }
                orderExecuteRecordsBean.setIntervaltime(s);
                try {
                    if (userid != null) {
                        orderExecuteRecordsBean.setCreatedby(userid);
                        orderExecuteRecordsBean.setUserid(userid);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                orderExecuteRecordsBean.setCreationtime(l);
                orderExecuteRecordsBean.setOrderid(doctorOrdersBean.getId());
                orderExecuteRecordsBeens.add(orderExecuteRecordsBean);
                doctorOrdersBean.setOrderExecuteRecords(orderExecuteRecordsBeens);
                doctorOrdersBean.setHisid(dataBean.getId());
                doctorOrdersBean.setStatus(Constant.YZ_TYPE_ZHIXINGZHONG);
            }
            dataBean.setDoctorOrders(doctorOrders);
            dataBean.setOrderststus(Constant.YZ_TYPE_ZHIXINGZHONG);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataBean = new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId());
        mDocOrderPannel.bindData(new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId()));
    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //体征结果值
            case R.id.bt_1_1:
                jianPanChuLi(mBt11);
                break;
            //体征结果值
            case R.id.bt_1_2:
                jianPanChuLi(mBt12);
                break;
            //体征结果值
            case R.id.bt_1_3:
                jianPanChuLi(mBt13);
                break;
            //体征结果值
            case R.id.bt_1_4:
                jianPanChuLi(mBt14);
                break;
            //体征结果值
            case R.id.bt_2_1:
                jianPanChuLi(mBt21);
                break;
            //体征结果值
            case R.id.bt_2_2:
                jianPanChuLi(mBt22);
                break;
            //体征结果值
            case R.id.bt_2_3:
                jianPanChuLi(mBt23);
                break;
            //体征结果值 .
            case R.id.bt_2_4:
//                jianPanChuLi(mBt24);
                break;
            //体征结果值
            case R.id.bt_3_1:
                jianPanChuLi(mBt31);
                break;
            //体征结果值
            case R.id.bt_3_2:
                jianPanChuLi(mBt32);
                break;
            //体征结果值
            case R.id.bt_3_3:
                jianPanChuLi(mBt33);
                break;
            //体征结果值
            case R.id.bt_3_4:
                jianPanChuLi(mBt34);
                break;
            //体征结果值
            case R.id.bt_4_1:
                jianPanChuLi(mBt41);
                break;
            //体征结果值
            case R.id.bt_4_2:
                jianPanChuLi(mBt42);
                break;
            //体征结果值
            case R.id.bt_4_3:
                jianPanChuLi(mBt43);
                break;
            //体征结果值
            case R.id.bt_4_4:
                stringBuilder = new StringBuilder(mTiZhengZhi.getText());
                if (stringBuilder != null && stringBuilder.length() != 0) {
                    String s = stringBuilder.substring(0, stringBuilder.length() - 1);
                    mTiZhengZhi.setText(s);
                    break;
                }
                if (stringBuilder.length() == 1) {
                    mTiZhengZhi.setText("");
                }
                break;
            //保存滴速
            case R.id.yz_shuxue_wancheng_bt:
                String s = mTiZhengZhi.getText().toString();
                if (null == s || "".equals(s) || "0".equals(s)) {
                    showToastShort("请输入滴速！");
                    break;
                }
                mBundle.putInt(Constant.BUNDLE_KEY_DISU, Integer.parseInt(s));
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_DISU, true);
                goToActivity(YZ_ZX_ShuYe_DiSuActivity.class, mBundle);
                break;
        }
    }

    private void jianPanChuLi(Button mBt11) {
        if (mTiZhengZhi.getText().toString().length() > 3) {
            return;
        }
        stringBuilder = new StringBuilder(mTiZhengZhi.getText());
        stringBuilder.append(mBt11.getText());
        mTiZhengZhi.setText(DataConverter.getFormalFloat(stringBuilder.toString()));
    }

    private void tiJiaoChuLi() {
        ActivityControl.killActivity(YZ_ZX_ShuYe_DiSuActivity.class);
        ActivityControl.killActivity(YZ_ZX_ShuYe_HuanYeActivity.class);
        ActivityControl.killActivity(YZ_ZX_ShuYeActivity.class);
        goToActivity(YZ_ZX_ShuYeActivity.class, mBundle);
        killSelf();
    }

    @Override
    public void handleSuccess(Object obj) {
        jiaZaiDialog.cancel();
        if (obj != null && obj instanceof List && ((List) obj).size() > 0) {
            if (mBundle == null) mBundle = new Bundle();
            mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU, ((List<YiZhuBean.DataBean>) obj).get(0));
        }
        tiJiaoChuLi();
    }

    @Override
    public void handleOnError() {
        super.handleOnError();
        jiaZaiDialog.cancel();
        tiJiaoChuLi();
    }

    @Override
    public void handleOnError(String urlStr) {
        super.handleOnError(urlStr);
        jiaZaiDialog.cancel();
        tiJiaoChuLi();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            try {
                mBundle = data.getBundleExtra("data");
                LogUtil.d("mBundle  0=" + mBundle);
                LogUtil.d("YZ_ZX_ShuYe_DiSuActivity onActivityResult orderExecuteRecordsBean  0=" + orderExecuteRecordsBean);
                orderExecuteRecordsBean = (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean) mBundle.getSerializable(Constant.BUNDLE_KEY_XUNSHI_JILU);
                LogUtil.d("YZ_ZX_ShuYe_DiSuActivity onActivityResult orderExecuteRecordsBean  1=" + orderExecuteRecordsBean);
            } catch (Exception e) {
                LogUtil.e("Intent data is null!");
            }
        }

    }

}
