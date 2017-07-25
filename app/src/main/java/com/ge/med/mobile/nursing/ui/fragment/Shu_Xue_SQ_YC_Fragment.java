package com.ge.med.mobile.nursing.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.YiZhuImpl;
import com.ge.med.mobile.nursing.db.DBExceptionDefine;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.forjson.callback.INetworkHandler;
import com.ge.med.mobile.nursing.ui.activity.HZActivity;
import com.ge.med.mobile.nursing.ui.activity.SOPActivity;
import com.ge.med.mobile.nursing.ui.activity.Shu_Xue_SQ_YCActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_ShuYe_DiSuActivity;
import com.ge.med.mobile.nursing.ui.adapter.ShuXueShenqingYichangLvItemAdapter;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class Shu_Xue_SQ_YC_Fragment extends BaseFragment implements INetworkHandler {
    private ShuXueShenqingYichangLvItemAdapter shuXueShenqingYichangLvItemAdapter;

    private ListView mShuXueSqYcLv;
    private Button mYzZxZhuSheBeizhuBt;
    private Button mYzZxZhuSheZhixingBt;

    private YiZhuBean.DataBean dataBean;
    private Shu_Xue_SQ_YCActivity activity;
    private List<String> stringList = new ArrayList<>();
    private Bundle mBundle;
    private Shu_Xue_SQ_YCActivity shu_xue_sq_ycActivity;
    private Integer mExceptiondefineid;
    private boolean mBundleBoolean;

    @Override
    public int setRootView() {
        return R.layout.fragment_shu__xue__sq__yc_;
    }

    @Override
    public void initViews() {
        activity = (Shu_Xue_SQ_YCActivity) getActivity();
        dataBean = activity.getDataBean();
        shu_xue_sq_ycActivity = (Shu_Xue_SQ_YCActivity) mActivitySelf;
        mBundle = shu_xue_sq_ycActivity.getmBundle();
        mBundleBoolean = mBundle.getBoolean(Constant.BUNDLE_KEY_IS_XUNSHI_GOTO, false);
        mShuXueSqYcLv = (ListView) mRootView.findViewById(R.id.shu_xue_sq_yc_lv);
        mYzZxZhuSheBeizhuBt = (Button) mRootView.findViewById(R.id.yz_zx_zhu_she_beizhu_bt);
        mYzZxZhuSheZhixingBt = (Button) mRootView.findViewById(R.id.yz_zx_zhu_she_zhixing_bt);
        String yiChang = activity.getYiChang();
        LogUtil.d("确认措施 失败返回——yiChang=" + yiChang);
        if (yiChang != null) {
            DBExceptionDefine first = DataSupport.where("exceptionname = ?", yiChang).findFirst(DBExceptionDefine.class);
            LogUtil.d("确认措施 失败返回——first=" + first);
            if (first != null) {
                String handle = first.getHandle();
                mExceptiondefineid = first.getZid();
                LogUtil.d("确认措施 失败返回1111——mExceptiondefineid=" + mExceptiondefineid);
                if (handle != null) {
                    String[] split = handle.split(",");
                    for (int i = 0; i < split.length; i++) {
                        stringList.add(split[i]);
                    }
                }
            }
        }
        shuXueShenqingYichangLvItemAdapter = new ShuXueShenqingYichangLvItemAdapter(mActivitySelf, stringList);
        mShuXueSqYcLv.setAdapter(shuXueShenqingYichangLvItemAdapter);
        // 确认措施按钮监听
        mYzZxZhuSheZhixingBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yiChangCuoShi= null;
                if(shuXueShenqingYichangLvItemAdapter!=null){
                     yiChangCuoShi = shuXueShenqingYichangLvItemAdapter.getYiChangCuoShi();
                }
                if (yiChangCuoShi== null) {
                    showToastShort("请选择措施！");
                    return;
                }
                long currentTimeMillis = System.currentTimeMillis();
                dataBean.setLastupdatedby(activity.getDisplayUserName());
                dataBean.setLastupdatetime(currentTimeMillis);
                List<YiZhuBean.DataBean.DoctorOrdersBean> doctorOrders = dataBean.getDoctorOrders();
                YiZhuBean.DataBean.DoctorOrdersBean ordersBean = null;
                if (doctorOrders != null && doctorOrders.size() > 0) {
                    if (Constant.TYPE_YZ_PIXIAZHUSHE.equals(dataBean.getOrdertype())
                            || Constant.TYPE_YZ_JINGMAIZHUSHE.equals(dataBean.getOrdertype())
                            || Constant.TYPE_YZ_JIROUZHUSHE.equals(dataBean.getOrdertype())) {
                        doctorOrders = YiZhuImpl.paixuDoctorOrdersBean(doctorOrders);
                        for (YiZhuBean.DataBean.DoctorOrdersBean doctorOrdersBean : doctorOrders) {
                            if (Constant.YZ_TYPE_YIZHIXING.equals(doctorOrdersBean.getStatus())) {
                                continue;
                            }
                            ordersBean = doctorOrdersBean;
                            break;
                        }
                    } else {
                        LogUtil.e("异常--正常过来的2");
                        if (doctorOrders != null && doctorOrders.size() > 0) {
                            for (YiZhuBean.DataBean.DoctorOrdersBean doctorOrdersBean : doctorOrders) {
                                ordersBean = doctorOrdersBean;
                            }
                        }
                    }
                    if (mBundleBoolean) {
                        LogUtil.e("异常--巡视过来的");
                        YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecords
                                = (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean) mBundle.getSerializable(Constant.BUNDLE_KEY_XUNSHI_JILU);
                        if (orderExecuteRecords != null) {
                            YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean exceptionsBean = new YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean();
                            setExceptionBean(yiChangCuoShi, currentTimeMillis, ordersBean, exceptionsBean);
                            if (orderExecuteRecords.getOrderExceptionList() == null){
                                orderExecuteRecords.setOrderExceptionList(new ArrayList<YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean>());
                            }
                            orderExecuteRecords.getOrderExceptionList().add(exceptionsBean);
                            Intent intent = new Intent();
                            intent.setClass(mActivitySelf, YZ_ZX_ShuYe_DiSuActivity.class);
                            mBundle.putSerializable(Constant.BUNDLE_KEY_XUNSHI_JILU
                                    , orderExecuteRecords);
                            intent.putExtra("data", mBundle);
                            activity.setResult(0, intent);
                            activity.killSelf();
                            return;
                        }
                    } else {
                        List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean> orderExceptions = ordersBean.getOrderExceptions();
                        if (orderExceptions == null) {
                            orderExceptions = new ArrayList<YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean>();
                        }
                        YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean exceptionsBean = new YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean();
                        setExceptionBean(yiChangCuoShi, currentTimeMillis, ordersBean, exceptionsBean);
                        orderExceptions.add(exceptionsBean);

                        ordersBean.setHisid(dataBean.getId());
                        ordersBean.setOrderExceptions(orderExceptions);
                        LogUtil.e("ordersBean id is " + ordersBean.getId());
                        dataBean.setDoctorOrders(doctorOrders);
                    }
                } else {
                    List<YiZhuBean.DataBean.DoctorOrdersBean> doctorOrders1 = new ArrayList<YiZhuBean.DataBean.DoctorOrdersBean>();
                    YiZhuBean.DataBean.DoctorOrdersBean doctorOrdersBean = new YiZhuBean.DataBean.DoctorOrdersBean();
                    List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean> orderExceptions = new ArrayList<YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean>();
                    YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean exceptionsBean = new YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean();
                    setExceptionsBean(yiChangCuoShi, currentTimeMillis, exceptionsBean);
                    orderExceptions.add(exceptionsBean);
                    doctorOrdersBean.setOrderExceptions(orderExceptions);
                    doctorOrdersBean.setHisid(dataBean.getId());
                    doctorOrders1.add(doctorOrdersBean);
                    dataBean.setDoctorOrders(doctorOrders1);
                }
                new DoctorOrderDaoImpl().saveDoctorOrder(dataBean, true);
                NetworkForDoctorOrder.submitSingleDoctorOrder(Shu_Xue_SQ_YC_Fragment.this, dataBean);
                    Intent intent = new Intent();
                    intent.setClass(mActivitySelf, HZActivity.class);
                    activity.setResult(Constant.RESULT_CODE_YICHANG_CHULI, intent);
            }
        });
    }

    private void setExceptionBean(String yiChangCuoShi, long currentTimeMillis, YiZhuBean.DataBean.DoctorOrdersBean ordersBean, YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean exceptionsBean) {
        exceptionsBean.setLastupdatedby(activity.getDisplayUserName());
        exceptionsBean.setLastupdatetime(currentTimeMillis);
        exceptionsBean.setCreationtime(currentTimeMillis);
        exceptionsBean.setCreatedby(activity.getDisplayUserName());
        exceptionsBean.setExceptionDefine(null);
        exceptionsBean.setExerecordid(null);
        exceptionsBean.setExceptiondefineid(mExceptiondefineid);
        exceptionsBean.setId(null);
        exceptionsBean.setUserid(Integer.parseInt(activity.getUserId()));
        exceptionsBean.setOrderid(ordersBean.getId());
        exceptionsBean.setTreatmentmeasures(yiChangCuoShi);
    }

    private void setExceptionsBean(String yiChangCuoShi, long currentTimeMillis, YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean exceptionsBean) {
        exceptionsBean.setLastupdatedby(activity.getDisplayUserName());
        exceptionsBean.setLastupdatetime(currentTimeMillis);
        exceptionsBean.setExerecordid(null);
        exceptionsBean.setId(null);
        exceptionsBean.setExceptiondefineid(mExceptiondefineid);
        exceptionsBean.setExceptionDefine(null);
        exceptionsBean.setUserid(Integer.parseInt(activity.getUserId()));
        exceptionsBean.setTreatmentmeasures(yiChangCuoShi);
    }


    @Override
    public void initDatas() {
        //SOP流程图de  监听
        mYzZxZhuSheBeizhuBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(SOPActivity.class, activity.getmBundle());
            }
        });
    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    @Override
    public void handleOnError() {
        activity.killSelf();
    }

    @Override
    public void handleOnError(String urlStr) {
        activity.killSelf();
    }

    @Override
    public void handleSuccess(Object obj) {
        activity.killSelf();
    }
}
