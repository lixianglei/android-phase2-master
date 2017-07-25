package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.HuanZheLieBiaoInterface;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.service.SyncService;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.AdapterUtil;
import com.ge.med.mobile.nursing.ui.adapter.ShuxueXuedaiHeduiLvItemAdapter;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.view.FuWenPopuwindow;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
//医嘱 输血
public class YZ_ShuXue_QuXueActivity extends MyBaseActivity {
    private ListView mShuxueXuedaiHeduiLv;
    private Button mYzHdRenYichangBt;
    private Button mYzHdRenTijiaoBt;
    private TitleBar mTitleBar;
    private Bundle mBundle;
    private DocOrderPannel mDocOrderPannel;
    private YiZhuBean.DataBean dataBean;
    private DBHuanZheLieBiao huanZheLieBiao;
    private HuanZheLieBiaoInterface huanZheLieBiaoInterface;
    private HuanZheLieBiaoBean.DataBean huanZheLieBiaoInterfaceDataBean;
    private ShuxueXuedaiHeduiLvItemAdapter mShuxueXuedaiHeduiLvItemAdapter;
    private String userId;
    private Button mYzHdRenFuwenBt;
    private FuWenPopuwindow fuWenPopuwindow;
    private String FuWenInterval;//复温间隔 分钟

    @Override
    public int setRootView() {
        return R.layout.activity_yz__shu_xue__qu_xue;
    }

    @Override
    public void initViews() {
        SharePLogin sharePLogin = new SharePLogin(this);
        userId = sharePLogin.getUserid();
        mShuxueXuedaiHeduiLv = (ListView) findViewById(R.id.shuxue_xuedai_hedui_lv);
        mYzHdRenYichangBt = (Button) findViewById(R.id.yz_hd_ren_yichang_bt);
        mYzHdRenFuwenBt = (Button) findViewById(R.id.yz_hd_ren_fuwen_bt);
        mYzHdRenTijiaoBt = (Button) findViewById(R.id.yz_hd_ren_tijiao_bt);
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
        if (dataBean != null && Constant.YZ_TYPE_DAIHEDUI.equals(dataBean.getOrderststus())) {
            mYzHdRenFuwenBt.setVisibility(View.VISIBLE);
            fuWenPopuwindow = new FuWenPopuwindow(this);
            mYzHdRenTijiaoBt.setText("确认核对(1/2)");
            if (dataBean.getDoctorOrders() != null && dataBean.getDoctorOrders().size() > 0
                    && dataBean.getDoctorOrders().get(0) != null && dataBean.getDoctorOrders().get(0).getOrderExecuteRecords() != null
                    && dataBean.getDoctorOrders().get(0).getOrderExecuteRecords().size() > 0) {
                for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean
                        : dataBean.getDoctorOrders().get(0).getOrderExecuteRecords()) {
                    if (Constant.YZ_EXECUTE_JOBTYPE_HEDUI.equals(orderExecuteRecordsBean.getJobtype())) {
                        mYzHdRenFuwenBt.setEnabled(true);
                        mYzHdRenTijiaoBt.setText("确认核对(2/2)");
                        break;
                    }
                }
            }
        }
        mShuxueXuedaiHeduiLvItemAdapter = new ShuxueXuedaiHeduiLvItemAdapter(this, dataBean.getOrderBloodProperty(), mYzHdRenTijiaoBt);
        mShuxueXuedaiHeduiLv.setAdapter(mShuxueXuedaiHeduiLvItemAdapter);
    }

    @Override
    public void afterProductScanned(String ProductScanned) {
        int postion = -1;
        if (dataBean != null && dataBean.getOrderBloodProperty() != null && dataBean.getOrderBloodProperty().size() > 0) {
            for (YiZhuBean.DataBean.OrderBloodPropertyBean orderBloodPropertyBean : dataBean.getOrderBloodProperty()) {
                if (ProductScanned.equals(orderBloodPropertyBean.getProductno())) {
                    postion = dataBean.getOrderBloodProperty().indexOf(orderBloodPropertyBean);
                    break;
                }
            }
        }
        if (postion == -1) {
            showToastShort("未找到该产品号！");
        } else {
            if (mShuxueXuedaiHeduiLvItemAdapter != null && mShuxueXuedaiHeduiLvItemAdapter.getChanPinMap() != null) {
                mShuxueXuedaiHeduiLvItemAdapter.getChanPinMap().put(postion, true);
                mShuxueXuedaiHeduiLvItemAdapter.notifyDataSetChanged();
                mShuxueXuedaiHeduiLvItemAdapter.btEnable();
                mShuxueXuedaiHeduiLv.setSelection(postion);
            }
        }
    }

    @Override
    public void afterBloodPackageScanned(String BloodPackageScanned) {
        int postion = -1;
        if (dataBean != null && dataBean.getOrderBloodProperty() != null && dataBean.getOrderBloodProperty().size() > 0) {
            for (YiZhuBean.DataBean.OrderBloodPropertyBean orderBloodPropertyBean : dataBean.getOrderBloodProperty()) {
                if (BloodPackageScanned.equals(orderBloodPropertyBean.getBloodbagno())) {
                    postion = dataBean.getOrderBloodProperty().indexOf(orderBloodPropertyBean);
                    break;
                }
            }
        }
        if (postion == -1) {
            showToastShort("未找到该血袋号！");
        } else {
            if (mShuxueXuedaiHeduiLvItemAdapter != null && mShuxueXuedaiHeduiLvItemAdapter.getXueDaiMap() != null) {
                mShuxueXuedaiHeduiLvItemAdapter.getXueDaiMap().put(postion, true);
                mShuxueXuedaiHeduiLvItemAdapter.notifyDataSetChanged();
                mShuxueXuedaiHeduiLvItemAdapter.btEnable();
                mShuxueXuedaiHeduiLv.setSelection(postion);
            }
        }
    }

    @Override
    public void init() {
        //异常
        mYzHdRenYichangBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(YZ_ZX_ZhuSheActivity.class, mBundle);
            }
        });
        //提交
        mYzHdRenTijiaoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBean != null && dataBean.getDoctorOrders() != null
                        && dataBean.getDoctorOrders().size() > 0 && dataBean.getDoctorOrders().get(0) != null) {
                    List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> orderExecuteRecords
                            = dataBean.getDoctorOrders().get(0).getOrderExecuteRecords();
                    if (orderExecuteRecords == null) {
                        orderExecuteRecords = new ArrayList<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean>();
                    }
                    YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean
                            = new YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean();
                    orderExecuteRecordsBean.setUserid(userId);
                    orderExecuteRecordsBean.setCreationtime(System.currentTimeMillis());
                    orderExecuteRecordsBean.setJobtype(Constant.YZ_EXECUTE_JOBTYPE_QUXUE);
                    dataBean.setOrderststus(Constant.YZ_TYPE_DAIHEDUI);
                    if ("确认核对(1/2)".equals(mYzHdRenTijiaoBt.getText().toString())) {
                        orderExecuteRecordsBean.setJobtype(Constant.YZ_EXECUTE_JOBTYPE_HEDUI);
                    }
                    if ("确认核对(2/2)".equals(mYzHdRenTijiaoBt.getText().toString())) {
                        orderExecuteRecordsBean.setJobtype(Constant.YZ_EXECUTE_JOBTYPE_HEDUI);
                        dataBean.setOrderststus(Constant.YZ_TYPE_WEIZHIXING);
                        orderExecuteRecordsBean.setUserid2(userId);
                        orderExecuteRecordsBean.setUserid(null);
                        if (fuWenPopuwindow != null) {
                            FuWenInterval = fuWenPopuwindow.getFuWenInterval();
                            if (FuWenInterval != null) {
                                if (SyncService.isConnected()) {
                                    submitFuWen(userId, dataBean.getId(), FuWenInterval, System.currentTimeMillis());
                                    dataBean.setOrderststus(Constant.YZ_TYPE_FUWENZHONG);
                                } else {
                                    showToastShort("当前网络无法连接服务器,无法进行复温设置!");
                                }
                            }
                        }
                    }
                    orderExecuteRecords.add(orderExecuteRecordsBean);
                    new DoctorOrderDaoImpl().saveDoctorOrder(dataBean, true);
                    NetworkForDoctorOrder.submitSingleDoctorOrder(YZ_ShuXue_QuXueActivity.this, dataBean);
                    killSelf();
                }
            }
        });
        mYzHdRenFuwenBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SyncService.isConnected()) {
                    fuWenPopuwindow.showPopupWindow();
                } else {
                    showToastShort("当前网络无法连接服务器,无法进行复温设置!");
                }

            }
        });
    }

    //复温时间间隔提交
    private void submitFuWen(String userId, Integer id, String fuWenInterval, long l) {
        OkHttpUtils.get().url(URL.URL_FUWEN).addHeader("User-Agent", "www.gs.com")
                .addParams("userId", userId)
                .addParams("orderId", id + "")
                .addParams("rewarmingInterval", fuWenInterval)
                .addParams("rewarmingStartTime", l + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showToastShort("复温时间提交失败,服务器异常!");
                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

}
