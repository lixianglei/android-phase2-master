package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.YizhuXiangqingLvItemAdapter;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
//医嘱详情
public class YZ_XiangQingActivity extends MyBaseActivity {

    private LinearLayout mYizhuXiangqingLl;
    private Button mYzZxZhuSheXiangQingBt;
    private ListView mYizhuXiangQingLv;
    private LinearLayout mYzHdRenBtLl;
    private Button mYzHdRenYichangBt;
    private Button mYzHdRenTijiaoBt;
    private LinearLayout mShaiXuanIncludeLl;
    private TextView mShaiXuanIncludeKongbaiTv;
    private ListView mHuanZheXuanZeLv;
    private Button mYzXhixingFenciBt;


    private TitleBar mTitleBar;
    private Bundle mBundle;
    private DocOrderPannel mDocOrderPannel;
    private YizhuXiangqingLvItemAdapter yizhuXiangqingLvItemAdapter;
    private YiZhuBean.DataBean dataBean;
    private String userid;
    private String userid1;

    @Override
    public int setRootView() {
        return R.layout.activity_yz__xiangqing;
    }

    @Override
    public void initViews() {
        SharePLogin sharePLogin = new SharePLogin(this);
        userid = sharePLogin.getUserid();
        mYizhuXiangqingLl = (LinearLayout) findViewById(R.id.yizhu_xiangqing_ll);
        mYzZxZhuSheXiangQingBt = (Button) findViewById(R.id.yz_zx_zhu_she_xiang_qing_bt);
        mYizhuXiangQingLv = (ListView) findViewById(R.id.yizhu_xiang_qing_lv);
        mYzHdRenBtLl = (LinearLayout) findViewById(R.id.yz_hd_ren_bt_ll);
        mYzHdRenYichangBt = (Button) findViewById(R.id.yz_hd_ren_yichang_bt);
        mYzHdRenTijiaoBt = (Button) findViewById(R.id.yz_hd_ren_tijiao_bt);
        mShaiXuanIncludeLl = (LinearLayout) findViewById(R.id.shai_xuan_include_ll);
        mShaiXuanIncludeKongbaiTv = (TextView) findViewById(R.id.shai_xuan_include_kongbai_tv);
        mHuanZheXuanZeLv = (ListView) findViewById(R.id.huan_zhe_xuan_ze_lv);
        mYzXhixingFenciBt = (Button) findViewById(R.id.yz_xhixing_fenci_bt);

        mBundle = getIntent().getBundleExtra("data");
        mYizhuXiangqingLl.setVisibility(View.VISIBLE);
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
    }

    @Override
    public void initDatas() {
        try {
            DBHuanZheLieBiao dbHuanZheLieBiao = new HuanZheLieBiaoImpl().getDBHuanZheLieBiao(dataBean.getPatientid());
            HuanZheLieBiaoBean.DataBean dataBean = new HuanZheLieBiaoImpl().getDataBean(dbHuanZheLieBiao);
            mTitleBar = new TitleBar(this, dataBean);
            mDocOrderPannel = new DocOrderPannel(this, this.dataBean);
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
        yizhuXiangqingLvItemAdapter = new YizhuXiangqingLvItemAdapter(mActivitySelf, dataBean);
        mYizhuXiangQingLv.setAdapter(yizhuXiangqingLvItemAdapter);
        if (Constant.YZ_TYPE_WEIZHIXING.equals(dataBean.getOrderststus())
                && (Constant.TYPE_YZ_JINGMAIZHUSHE.equals(dataBean.getOrdertype())
                || Constant.TYPE_YZ_PIXIAZHUSHE.equals(dataBean.getOrdertype())
                || Constant.TYPE_YZ_JIROUZHUSHE.equals(dataBean.getOrdertype()))) {
            mYzXhixingFenciBt.setVisibility(View.VISIBLE);
            mYzXhixingFenciBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToActivity(YZ_ZX_ZhuShe_FenCiActivity.class, mBundle);
                }
            });
        }
    }

    @Override
    public void init() {
        mYzHdRenYichangBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Constant.YZ_TYPE_WEIHEDUI.equals(dataBean.getOrderststus())) {
                    Intent intent = new Intent();
                    intent.setClass(mActivitySelf, YZ_HD_RenActivity.class);
                    intent.putExtra("data", mBundle);
                    setResult(2, intent);
                    killSelf();
                } else {
                    mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU, dataBean);
                    goToActivity(YZ_ZX_ZhuSheActivity.class, mBundle);
                }
            }
        });
        if (dataBean != null && dataBean.getOrderststus() != null) {
            if (Constant.YZ_TYPE_DAIBAIYAO.equals(dataBean.getOrderststus())) {
                mYzHdRenTijiaoBt.setText("确认摆药");
            }
            if (Constant.YZ_TYPE_WEIHEDUI.equals(dataBean.getOrderststus())) {
                mYzHdRenTijiaoBt.setText("确认核对");
            }

            if (Constant.YZ_TYPE_WEIZHIXING.equals(dataBean.getOrderststus())) {
                mYzHdRenTijiaoBt.setText("确认执行");
                if (Constant.TYPE_YZ_SHUYE.equals(dataBean.getOrdertype())) {
                    mYzHdRenTijiaoBt.setText("开始输液");
                }
            }

            if (Constant.YZ_TYPE_DAIPEIYE.equals(dataBean.getOrderststus())) {
                mYzHdRenTijiaoBt.setText("确认配液（1/2）");
                if (dataBean.getDoctorOrders() != null && dataBean.getDoctorOrders().get(0) != null
                        && dataBean.getDoctorOrders().get(0).getOrderExecuteRecords() != null
                        && dataBean.getDoctorOrders().get(0).getOrderExecuteRecords().size() > 0) {
                    for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean
                            : dataBean.getDoctorOrders().get(0).getOrderExecuteRecords()) {
                        if (Constant.YZ_EXECUTE_JOBTYPE_PEIYE.equals(orderExecuteRecordsBean.getJobtype())) {
                            userid1 = orderExecuteRecordsBean.getUserid();
                            mYzHdRenTijiaoBt.setText("双人确认（2/2）");
                        }
                    }
                }
            }
            if (Constant.YZ_TYPE_WEIZHIXING.equals(dataBean.getOrderststus()) && Constant.TYPE_YZ_KOUFU.equals(dataBean.getOrdertype())) {
                mYzHdRenTijiaoBt.setText("确认服药");
            }

        }
        mYzHdRenTijiaoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //核对
                if ("确认核对".equals(mYzHdRenTijiaoBt.getText())) {
                    new DoctorOrderDaoImpl().saveExceptionForCheck(dataBean.getId(), null);
                    Intent intent = new Intent();
                    intent.setClass(mActivitySelf, YZ_HD_RenActivity.class);
                    intent.putExtra("data", mBundle);
                    setResult(1, intent);
                    killSelf();
                    return;
                }
                //****** 注射提交
                if (Constant.YZ_TYPE_WEIZHIXING.equals(dataBean.getOrderststus())
                        && (Constant.TYPE_YZ_JINGMAIZHUSHE.equals(dataBean.getOrdertype())
                        || Constant.TYPE_YZ_SHANSHI.equals(dataBean.getOrdertype())
                        || Constant.TYPE_YZ_ZHILIAO.equals(dataBean.getOrdertype())
                        || Constant.TYPE_YZ_HULI.equals(dataBean.getOrdertype())
                        || Constant.TYPE_YZ_RUHU.equals(dataBean.getOrdertype())
                        || Constant.TYPE_YZ_PISHI.equals(dataBean.getOrdertype())
                        || Constant.TYPE_YZ_KOUFU.equals(dataBean.getOrdertype())
                        || Constant.TYPE_YZ_PIXIAZHUSHE.equals(dataBean.getOrdertype())
                        || Constant.TYPE_YZ_JIROUZHUSHE.equals(dataBean.getOrdertype()))) {
                    dataBean.setOrderststus(Constant.YZ_TYPE_YIZHIXING);
                    dataBean.setLastupdatedby(userid);
                    dataBean.setExecuteby(userid);
                    long l = System.currentTimeMillis();
                    dataBean.setLastupdatetime(l);
                    dataBean.setExetime(l);
                    new DoctorOrderDaoImpl().saveDoctorOrder(dataBean,true);
                    NetworkForDoctorOrder.submitSingleDoctorOrder(YZ_XiangQingActivity.this, dataBean);
                    mActivitySelf.killSelf();
                    return;
                }
                //******  **************//
                //*********开始输液
                if (Constant.YZ_TYPE_WEIZHIXING.equals(dataBean.getOrderststus()) && Constant.TYPE_YZ_SHUYE.equals(dataBean.getOrdertype())) {
                    mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SHIRULIANG, false);
                    mBundle.putBoolean(Constant.BUNDLE_KEY_IS_HUANYE, false);
                    mBundle.putString(Constant.BUNDLE_KEY_TYPE_EXCE, Constant.YZ_EXECUTE_JOBTYPE_XUNSHI);
                    goToActivity(YZ_ZX_ShuYe_DiSuActivity.class, mBundle);
                    mActivitySelf.killSelf();
                    return;
                }
                //******  **************//
                List<Integer> pharmIds = yizhuXiangqingLvItemAdapter.getPharmIds();
                if (dataBean != null && dataBean.getDoctorOrders() != null && dataBean.getDoctorOrders().get(0) != null) {
                    if (dataBean.getDoctorOrders().get(0).getOrderExecuteRecords() == null) {
                        dataBean.getDoctorOrders().get(0).setOrderExecuteRecords(new ArrayList<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean>());
                    }
                    List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> orderExecuteRecords
                            = dataBean.getDoctorOrders().get(0).getOrderExecuteRecords();
                    deleException();
                    YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean = null;
                    if ("确认配液（1/2）".equals(mYzHdRenTijiaoBt.getText().toString())) {
                        orderExecuteRecordsBean = new YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean();
                        orderExecuteRecordsBean.setUserid(userid);
                        orderExecuteRecordsBean.setExetime(System.currentTimeMillis());
                        orderExecuteRecordsBean.setJobtype("1");
                        orderExecuteRecords.add(orderExecuteRecordsBean);
                        dataBean.setOrderststus(Constant.YZ_TYPE_DAIPEIYE);
                    } else if ("双人确认（2/2）".equals(mYzHdRenTijiaoBt.getText().toString())) {
                        if (userid.equals(userid1)) {
                            showToastShort("双人确认不能是同一个人！");
                            return;
                        }
                        orderExecuteRecordsBean = new YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean();
                        orderExecuteRecordsBean.setUserid2(userid);
                        orderExecuteRecordsBean.setExetime(System.currentTimeMillis());
                        orderExecuteRecordsBean.setJobtype("1");
                        orderExecuteRecords.add(orderExecuteRecordsBean);
                        dataBean.setOrderststus(Constant.YZ_TYPE_WEIZHIXING);
                    } else {
                        if (pharmIds == null || pharmIds.size() <= 0) {
                            showToastShort("请选择药品!");
                            return;
                        }
                        if (dataBean.getPharmList() != null && dataBean.getPharmList().size() > 0
                                && pharmIds != null && pharmIds.size() > 0) {
                            for (Integer pharmId : pharmIds) {
                                orderExecuteRecordsBean = new YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean();
                                orderExecuteRecordsBean.setUserid(userid);
                                orderExecuteRecordsBean.setExetime(System.currentTimeMillis());
                                orderExecuteRecordsBean.setPharmId(pharmId);
                                orderExecuteRecordsBean.setPharmStatus(0);
                                orderExecuteRecordsBean.setJobtype("0");
                                orderExecuteRecords.add(orderExecuteRecordsBean);
                            }
                            int i = 0;
                            deleException();
                            if (orderExecuteRecords.size() > 0) {
                                for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean recordsBean : orderExecuteRecords) {
                                    if (Constant.YZ_EXECUTE_JOBTYPE_BAIYAO.equals(recordsBean.getJobtype())) {
                                        if (recordsBean.getPharmId() != null) {
                                            for (YiZhuBean.OrderPharm orderPharm : dataBean.getPharmList()) {
                                                if (orderPharm.getPharmId() == recordsBean.getPharmId()) {
                                                    i++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!(i < dataBean.getPharmList().size())) {
                                if (Constant.TYPE_YZ_SHUYE.equals(dataBean.getOrdertype())) {
                                    dataBean.setOrderststus(Constant.YZ_TYPE_DAIPEIYE);
                                } else {
                                    dataBean.setOrderststus(Constant.YZ_TYPE_WEIZHIXING);
                                }
                            }
                        }
                    }

                    jiaZaiDialog.show();
                    new DoctorOrderDaoImpl().saveDoctorOrder(dataBean, true);
                    NetworkForDoctorOrder.submitSingleDoctorOrder(YZ_XiangQingActivity.this, dataBean);
                }
            }
        });
    }

    private void deleException() {
        try {
            Long orderExceptionsLastTime = 0l;
            if (dataBean.getDoctorOrders().get(0).getOrderExceptions() != null && dataBean.getDoctorOrders().get(0).getOrderExceptions().size() > 0) {
                for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean orderExceptionsBean : dataBean.getDoctorOrders().get(0).getOrderExceptions()) {
                    if (orderExceptionsBean != null && orderExceptionsBean.getCreationtime() != null) {

                        if ((orderExceptionsLastTime - orderExceptionsBean.getCreationtime().longValue()) < 0) {
                            orderExceptionsLastTime = orderExceptionsBean.getCreationtime();
                        }
                        if (orderExceptionsLastTime == orderExceptionsBean.getCreationtime().longValue()) {
                            orderExceptionsBean.setIsdeleted("1");
                            new DoctorOrderDaoImpl().saveExceptionForCheck(dataBean.getId(), null);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    @Override
    public void handleOnError() {
        super.handleOnError();
        jiaZaiDialog.cancel();
        showToastShort("提交失败，等待后台自动提交！");
        killSelf();
    }

    @Override
    public void handleOnError(String urlStr) {
        super.handleOnError(urlStr);
        jiaZaiDialog.cancel();
        showToastShort("提交失败，等待后台自动提交！");
        killSelf();
    }

    @Override
    public void handleSuccess(Object obj) {
        super.handleSuccess(obj);
        jiaZaiDialog.cancel();
        killSelf();
        showToastShort("提交成功");
    }

}
