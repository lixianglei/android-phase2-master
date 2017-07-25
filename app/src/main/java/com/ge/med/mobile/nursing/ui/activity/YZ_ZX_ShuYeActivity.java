package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

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
import com.ge.med.mobile.nursing.ui.adapter.YzZxShuYeLvItemAdapter;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class YZ_ZX_ShuYeActivity extends MyBaseActivity {

    private ListView mHzYzLv;
    private LinearLayout mYzZxKouFuYaoLl;
    private Button mYzShuxueXunshiBt;
    private Button mYzShuxueHuanyeBt;

    private Button mYzShuxueWanchengBt;

    private YzZxShuYeLvItemAdapter yzZxShuYeLvItemAdapter;
    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private DBHuanZheLieBiao huanZheLieBiao;
    private TitleBar mTitleBar;
    private DocOrderPannel mDocOrderPannel;
    private HuanZheLieBiaoBean.DataBean huanZheLieBiaoInterfaceDataBean;
    private HuanZheLieBiaoInterface huanZheLieBiaoInterface;
    private String displayUserName;
    private String userid;
    private List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> orderExecuteRecords;

    @Override
    public int setRootView() {
        return R.layout.activity_yz__zx__shu_ye;
    }

    @Override
    public void initViews() {
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        displayUserName = sharePLogin.getDisplayUserName();
        userid = sharePLogin.getUserid();
        mYzShuxueXunshiBt = (Button) findViewById(R.id.yz_shuxue_xunshi_bt);
        mYzShuxueHuanyeBt = (Button) findViewById(R.id.yz_shuxue_huanye_bt);
        mHzYzLv = (ListView) findViewById(R.id.hz_yz_lv);
        mYzZxKouFuYaoLl = (LinearLayout) findViewById(R.id.yz_zx_kou_fu_yao_ll);
        mYzShuxueWanchengBt = (Button) findViewById(R.id.yz_shuxue_wancheng_bt);

        mBundle = getIntent().getBundleExtra("data");
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
        huanZheLieBiao = AdapterUtil.getPatientFromDB(dataBean.getPatientid());
        huanZheLieBiaoInterface = new HuanZheLieBiaoImpl();
        huanZheLieBiaoInterfaceDataBean = huanZheLieBiaoInterface.getDataBean(huanZheLieBiao);
        mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, huanZheLieBiaoInterfaceDataBean);

        mTitleBar = new TitleBar(this, huanZheLieBiaoInterfaceDataBean);
        mDocOrderPannel = new DocOrderPannel(this, dataBean);
        initExecuteRecords();
        mHzYzLv.setAdapter(yzZxShuYeLvItemAdapter);
        if (Constant.TYPE_YZ_SHUXUE.equals(dataBean.getOrdertype())) {
            mYzShuxueWanchengBt.setText("完成输血");
            mYzShuxueHuanyeBt.setText("换血袋");
        }


//        输液完成按钮
        mYzShuxueWanchengBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_HUANYE, true);
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SHIRULIANG, false);
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_DISU, false);
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_WANCHENG_SHUYE, true);
                mBundle.putString(Constant.BUNDLE_KEY_TYPE_EXCE, Constant.YZ_EXECUTE_JOBTYPE_HUANYE);
                goToActivity(YZ_ZX_ShuYe_DiSuActivity.class, mBundle);

            }
        });
    }

    private void initExecuteRecords() {
        List<YiZhuBean.DataBean.DoctorOrdersBean> doctorOrders = dataBean.getDoctorOrders();
        if (doctorOrders != null && !doctorOrders.isEmpty()) {
            orderExecuteRecords = new ArrayList<>();
            //从数据库拉取执行记录
            if (doctorOrders.get(0).getOrderExecuteRecords() != null && !doctorOrders.get(0).getOrderExecuteRecords().isEmpty()) {
                for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean recordsBean : doctorOrders.get(0).getOrderExecuteRecords()) {
                    if (recordsBean != null) {
                        if (Constant.YZ_EXECUTE_JOBTYPE_XUNSHI.equals(recordsBean.getJobtype()) || Constant.YZ_EXECUTE_JOBTYPE_HUANYE.equals(recordsBean.getJobtype())) {
                            orderExecuteRecords.add(recordsBean);
                        }
                    }
                }
                orderExecuteRecords = ActivityUtils.getDBYiZhudata_OrderExecuteRecords(orderExecuteRecords);
                if(yzZxShuYeLvItemAdapter == null){
                    yzZxShuYeLvItemAdapter = new YzZxShuYeLvItemAdapter(mActivitySelf,
                            orderExecuteRecords, dataBean);
                }
            } else {
                if(yzZxShuYeLvItemAdapter == null){
                    yzZxShuYeLvItemAdapter = new YzZxShuYeLvItemAdapter(mActivitySelf,
                            new ArrayList<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean>(),
                            dataBean);
                }
            }
        } else {
            if(yzZxShuYeLvItemAdapter == null){
                yzZxShuYeLvItemAdapter = new YzZxShuYeLvItemAdapter(mActivitySelf,
                        new ArrayList<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean>(),
                        dataBean);
            }
        }
    }

    @Override
    public void initDatas() {
        mBundle.putBoolean(Constant.BUNDLE_KEY_IS_XUNSHI_GOTO, false);
        //巡视记录
        mYzShuxueXunshiBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SHIRULIANG, true);
                mBundle.putString(Constant.BUNDLE_KEY_TYPE_EXCE, Constant.YZ_EXECUTE_JOBTYPE_XUNSHI);
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_HUANYE, false);
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_XUNSHI_GOTO, true);
                mBundle.putSerializable(Constant.BUNDLE_KEY_XUNSHI_JILU, new YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean());
                goToActivity(YZ_ZX_ShuYe_DiSuActivity.class, mBundle);
            }
        });
        //换液按钮 监听
        mYzShuxueHuanyeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int xuedaiNO = 0;
                if (orderExecuteRecords != null && orderExecuteRecords.size() > 0) {
                    for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean recordsBean : orderExecuteRecords) {
                        if (Constant.YZ_EXECUTE_JOBTYPE_HUANYE.equals(recordsBean.getJobtype())) {
                            if (recordsBean.getJobStatus() != null) {
                                try {
                                    if (recordsBean.getJobStatus() == null) {
                                        recordsBean.setJobStatus("0");
                                    }
                                    xuedaiNO = Integer.parseInt(recordsBean.getJobStatus());
                                    break;
                                } catch (NumberFormatException e) {
                                    LogUtil.e(e.getMessage());
                                }
                            }
                        }
                    }
                }
                if (dataBean != null &&Constant.TYPE_YZ_SHUXUE.equals(dataBean.getOrdertype())
                        && dataBean.getOrderBloodProperty() != null && dataBean.getOrderBloodProperty().size() <= xuedaiNO) {
                    showToastShort("该医嘱已经没有可换血袋信息!");
                    return;
                }
                mBundle.putString(Constant.BUNDLE_KEY_TYPE_EXCE, Constant.YZ_EXECUTE_JOBTYPE_HUANYE);
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SHIRULIANG, false);
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_DISU, false);
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_HUANYE, true);
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_XUNSHI_GOTO, true);
                mBundle.putInt(Constant.BUNDLE_KEY_STATUS_EXCE, xuedaiNO);
                mBundle.putSerializable(Constant.BUNDLE_KEY_XUNSHI_JILU, new YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean());
                if (Constant.TYPE_YZ_SHUXUE.equals(dataBean.getOrdertype())) {
                    goToActivity(YZ_ZXActivity.class, mBundle);
                } else {
                    goToActivity(YZ_ZX_ShuYe_DiSuActivity.class, mBundle);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataBean = new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId());
        initExecuteRecords();
        yzZxShuYeLvItemAdapter.setDataBean(dataBean);
        yzZxShuYeLvItemAdapter.setmEntities(orderExecuteRecords);
        yzZxShuYeLvItemAdapter.notifyDataSetChanged();
        mDocOrderPannel.bindData(dataBean);
    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

}
