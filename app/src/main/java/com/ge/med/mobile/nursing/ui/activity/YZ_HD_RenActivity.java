package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.DoctorOrderDao;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.service.SyncService;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.AdapterUtil;
import com.ge.med.mobile.nursing.ui.adapter.HuanZheXuanZeLvItemAdapter;
import com.ge.med.mobile.nursing.ui.adapter.YzHdRenLvItemAdapter;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.component.callback.NotifyPatientChange;
import com.ge.med.mobile.nursing.ui.logic.RW_BaiYao;
import com.ge.med.mobile.nursing.ui.view.SelfDialog;
import com.ge.med.mobile.nursing.utils.DataConverter;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 医嘱核对
 */
public class YZ_HD_RenActivity extends MyBaseActivity {
    private ListView mYzHdRenLv;
    private Button mYzHdRenYichangBt;
    private Button mYzHdRenTijiaoBt;
    private LinearLayout mShaiXuanIncludeLl;
    private TextView mShaiXuanIncludeKongbaiTv;
    private ListView mHuanZheXuanZeLv;
    private HuanZheXuanZeLvItemAdapter huanZheXuanZeLvItemAdapter;
    private YzHdRenLvItemAdapter yzHdRenLvItemAdapter;
    private List<CharSequence> list = new ArrayList<>();
    private Bundle mBundle;
    private HuanZheLieBiaoImpl huanZheLieBiao;
    private List<DBHuanZheLieBiao> huanZheLieBiaoList = new ArrayList<>();
    private List<HuanZheLieBiaoBean.DataBean> dataBeanList;
    private HuanZheLieBiaoBean.DataBean dataBean;
    private DoctorOrderDao mDoctorOrderDaoImpl;
    private List<YiZhuBean.DataBean> dbYiZhuData;
    private String username;
    private String patientid;
    private TitleBar mTitleBar;
    private PatientInfoPannel mPatientPanel;
    private String userid;
    private YiZhuBean.DataBean yiZhuDataBean;
    private boolean isToast;
    private String mRWLeiXing;
    private RW_BaiYao rw_baiYao;
    private Button mYzHdRenFanhuiBt;
    private boolean isPiLiang;
    private boolean fanhui;

    public boolean isPiLiang() {
        return isPiLiang;
    }

    public void setPiLiang(boolean piLiang) {
        isPiLiang = piLiang;
    }

    @Override
    public int setRootView() {
        return R.layout.activity_yz__hd__ren;
    }

    @Override
    public void initViews() {
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        username = sharePLogin.getDisplayUserName();
        userid = sharePLogin.getUserid();
        mDoctorOrderDaoImpl = new DoctorOrderDaoImpl();
        huanZheLieBiao = new HuanZheLieBiaoImpl();
        mBundle = getIntent().getBundleExtra("data");
        mRWLeiXing = mBundle.getString(Constant.BUNDLE_KEY_RW);
        list = mBundle.getCharSequenceArrayList(Constant.BUNDLE_KEY_LIST_HUANZHE);
        for (CharSequence cs : list) {
            huanZheLieBiaoList.add(huanZheLieBiao.getDBHuanZheLieBiao(cs.toString()));
        }
        dataBeanList = huanZheLieBiao.getDataBeanList(huanZheLieBiaoList);
        mShaiXuanIncludeLl = (LinearLayout) findViewById(R.id.shai_xuan_include_ll);
        mShaiXuanIncludeKongbaiTv = (TextView) findViewById(R.id.shai_xuan_include_kongbai_tv);
        mHuanZheXuanZeLv = (ListView) findViewById(R.id.huan_zhe_xuan_ze_lv);
        mYzHdRenLv = (ListView) findViewById(R.id.yz_hd_ren_lv);
        mYzHdRenFanhuiBt = (Button) findViewById(R.id.yz_hd_ren_fanhui_bt);
        mYzHdRenYichangBt = (Button) findViewById(R.id.yz_hd_ren_yichang_bt);
        mYzHdRenTijiaoBt = (Button) findViewById(R.id.yz_hd_ren_tijiao_bt);
        mShaiXuanIncludeKongbaiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShaiXuanIncludeLl.setVisibility(View.GONE);
            }
        });
        dataBeanList = huanZheLieBiao.getZhengXuListBean(dataBeanList);
        if (dataBeanList != null && dataBeanList.size() > 0) {
            dataBean = dataBeanList.get(0);
        }
        bindPatientInfo();
        if (mRWLeiXing != null
                && (mRWLeiXing.equals(Constant.BUNDLE_KEY_VALUE_BAIYAO) || mRWLeiXing.equals(Constant.BUNDLE_KEY_VALUE_PEIYE))) {
            rw_baiYao = new RW_BaiYao(mYzHdRenFanhuiBt, mYzHdRenYichangBt, mYzHdRenTijiaoBt, mHuanZheXuanZeLv, this, mRWLeiXing);
        }else{
            mYzHdRenTijiaoBt.setText("确认核对");
        }
        if (!mRWLeiXing.equals(Constant.BUNDLE_KEY_VALUE_BAIYAO)) {
            huanZheXuanZeLvItemAdapter = new HuanZheXuanZeLvItemAdapter(mActivitySelf, dataBeanList);
            mHuanZheXuanZeLv.setAdapter(huanZheXuanZeLvItemAdapter);
            //list 列表条目点击监听
            mHuanZheXuanZeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    dataBean = dataBeanList.get(position);
                    mPatientPanel.changePatient(dataBean);
                    bindPatientInfo();
                    yiZhuLv();
                    mShaiXuanIncludeLl.setVisibility(View.GONE);
                }
            });
        }
        yiZhuLv();
    }


    @Override
    public void afterPatientScanned(String patientID) {
        dataBean = DataConverter.convert(new HuanZheLieBiaoImpl().getDBHuanZheLieBiao(patientID));
        mPatientPanel.changePatient(dataBean);
        bindPatientInfo();
        yiZhuLv();
    }

    @Override
    public void afterDoctorOrderScanned(int hisID, Boolean isSaoMa) {
        final List<YiZhuBean.DataBean> dataBeen = yzHdRenLvItemAdapter.getmEntities();

        for (int i = 0; i < dataBeen.size(); i++) {
            if (dataBeen.get(i) != null) {
                if (hisID == dataBeen.get(i).getId()) {
                    if (mDoctorOrderDaoImpl.getExceptionForCheck(dataBeen.get(i).getId()) != null) {
                        final int finalI = i;
                        yzHdRenLvItemAdapter.selfDialog.setYesOnclickListener("确认", new SelfDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                saoMa(dataBeen.get(finalI), finalI);
                                mDoctorOrderDaoImpl.saveExceptionForCheck(dataBeen.get(finalI).getId(), null);
                                yzHdRenLvItemAdapter.selfDialog.dismiss();
                            }
                        });
                        yzHdRenLvItemAdapter.selfDialog.show();
                    }
                    saoMa(dataBeen.get(i), i);
                }
            }
        }
        if (!isToast) {
            showMessage("条码不匹配！");
            isToast = false;
        }
    }

    private void saoMa(YiZhuBean.DataBean dataBean, int i) {
        if (Constant.YZ_TYPE_DAIBAIYAO.equals(dataBean.getOrderststus()) && isPiLiang) {
            return;
        }
        yzHdRenLvItemAdapter.mCBFlag.put(i, true);
        yzHdRenLvItemAdapter.setPos(i);
        yzHdRenLvItemAdapter.notifyDataSetChanged();
        mYzHdRenLv.setSelection(i);
        isToast = true;
        mYzHdRenYichangBt.setEnabled(true);
        yiZhuDataBean = dataBean;
        if (!Constant.YZ_TYPE_WEIHEDUI.equals(dataBean.getOrderststus()) && "批量操作".equals(mYzHdRenTijiaoBt.getText().toString())) {
            mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU, dataBean);
            goToActivity(YZ_XiangQingActivity.class, mBundle);
        }
    }

    private void yiZhuLv(boolean fanhui) {
        if (fanhui) {
            return;
        }
        yiZhuLv();
    }

    //患者医嘱lv设置
    private void yiZhuLv() {
        mYzHdRenYichangBt.setEnabled(false);
        patientid = dataBean.getPatientid();
        if (mRWLeiXing != null) {
            if (mRWLeiXing.equals(Constant.BUNDLE_KEY_VALUE_BAIYAO)) {
                if (rw_baiYao == null) {
                    rw_baiYao = new RW_BaiYao(mYzHdRenFanhuiBt, mYzHdRenYichangBt, mYzHdRenTijiaoBt, mHuanZheXuanZeLv, this, mRWLeiXing);
                }
                dbYiZhuData = mDoctorOrderDaoImpl.findDoctorOrdersInJsonBean(patientid, null, Constant.YZ_TYPE_DAIBAIYAO);
                rw_baiYao.setDateBean(dbYiZhuData);
            }
            if (mRWLeiXing.equals(Constant.BUNDLE_KEY_VALUE_PEIYE)) {
                if (rw_baiYao == null) {
                    rw_baiYao = new RW_BaiYao(mYzHdRenFanhuiBt, mYzHdRenYichangBt, mYzHdRenTijiaoBt, mHuanZheXuanZeLv, this, mRWLeiXing);
                }
                dbYiZhuData = mDoctorOrderDaoImpl.findDoctorOrdersInJsonBean(patientid, null, Constant.YZ_TYPE_DAIPEIYE);
            }
            if (mRWLeiXing.equals(Constant.BUNDLE_KEY_VALUE_HEDUI)) {
                dbYiZhuData = mDoctorOrderDaoImpl.findDoctorOrdersInJsonBean(patientid, null, Constant.YZ_TYPE_WEIHEDUI);
            }
        }
        dbYiZhuData = mDoctorOrderDaoImpl.getFPaiXuDBYiZhuDatabean(dbYiZhuData);
        if (yzHdRenLvItemAdapter == null) {
            yzHdRenLvItemAdapter = new YzHdRenLvItemAdapter(mActivitySelf, dbYiZhuData, mYzHdRenYichangBt, mBundle, mRWLeiXing, userid);
            yzHdRenLvItemAdapter.setSjPosList(ActivityUtils.suanPos(dbYiZhuData));
            mYzHdRenLv.setAdapter(yzHdRenLvItemAdapter);
        } else {
            yzHdRenLvItemAdapter.setmEntities(dbYiZhuData);
            yzHdRenLvItemAdapter.setSjPosList(ActivityUtils.suanPos(dbYiZhuData));
            yzHdRenLvItemAdapter.notifyDataSetChanged();
        }

    }

    private void bindPatientInfo() {
        mTitleBar = new TitleBar(this, dataBean);
        mTitleBar.setShaiXuanBehavior(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShaiXuanIncludeLl.setVisibility(View.VISIBLE);
            }
        });
        mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, dataBean);
    }

    @Override
    public void initDatas() {
        mPatientPanel = new PatientInfoPannel(this, new AssessDaoImpl().findAllRiskDefine(), dataBeanList);
        mPatientPanel.changePatient(dataBeanList.get(0));
        mPatientPanel.setOnPatientChangeCallback(new NotifyPatientChange() {
            @Override
            public void onPatientChange() {
                dataBean = mPatientPanel.getCurrentPatient();
                bindPatientInfo();
                yiZhuLv();
            }
        });
    }

    @Override
    public void init() {
        int hisid = mBundle.getInt(Constant.BUNDLE_KEY_HISID);
        if (hisid != 0) {
            afterDoctorOrderScanned(hisid, true);
        }
        //异常按钮
        mYzHdRenYichangBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YiZhuBean.DataBean dbYiZhuDaTa = null;
                if (yzHdRenLvItemAdapter != null) {
                    dbYiZhuDaTa = yzHdRenLvItemAdapter.getDbYiZhuDaTa();
                }
                if (dbYiZhuDaTa == null) {
                    dbYiZhuDaTa = yiZhuDataBean;
                }
                if (dbYiZhuDaTa == null) {
                    showToastShort("医嘱数据异常！");
                    return;
                }
                mBundle.putInt("hisid", dbYiZhuDaTa.getId());
                mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU, dbYiZhuDaTa);
                mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE
                        , DataConverter.convert(AdapterUtil.getPatientFromDB(dataBean.getPatientid())));
                Intent intent = new Intent(mActivitySelf, YZ_HD_YiChangActivity.class);
                if (mBundle != null) {
                    intent.putExtra("data", mBundle);
                }
                startActivityForResult(intent, 0);
            }
        });
        //提交按钮
        mYzHdRenTijiaoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SyncService.isConnected()){
                    if ("批量操作".equals(mYzHdRenTijiaoBt.getText().toString())) {
                        rw_baiYao.initPiLiang();
                        yzHdRenLvItemAdapter.notifyDataSetChanged();
                    } else if ("确认摆药".equals(mYzHdRenTijiaoBt.getText().toString())) {
                        baiYaoTiJiao();
                    } else if ("确认配液".equals(mYzHdRenTijiaoBt.getText().toString())) {
                        peiYeTiJiao();
                    } else {
                        heDuiTiJiao();
                    }
                }else{
                    showToastShort("当前状态连接不上服务器,不能进行此操作!");
                }

            }
        });
    }

    private void peiYeTiJiao() {
        List<YiZhuBean.DataBean> dbYiZhuDaTas = yzHdRenLvItemAdapter.getmEntities();
        Map<Integer, Boolean> cbMap = yzHdRenLvItemAdapter.getmCBFlag();
        int pos = 0;
        Integer pharmId = null;
        List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> orderExecuteRecords;
        YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean;
        boolean flag = false;
        dbYiZhuData = new ArrayList<YiZhuBean.DataBean>();
        long orderExceptionsLastTime = 0;
        for (Map.Entry<Integer, Boolean> entry : cbMap.entrySet()) {
            pos = entry.getKey();
            if (entry.getValue()) {
                if (dbYiZhuDaTas.get(pos).getDoctorOrders() != null && dbYiZhuDaTas.get(pos).getDoctorOrders().get(0) != null) {
                   deleteException(dbYiZhuDaTas, pos, orderExceptionsLastTime);
                    try {
                        pharmId = dbYiZhuDaTas.get(pos).getPharmList().get(0).getPharmId();
                    } catch (Exception e) {
                        LogUtil.e(e.getMessage());
                    }
                    if (dbYiZhuDaTas.get(pos).getDoctorOrders().get(0).getOrderExecuteRecords() == null) {
                        dbYiZhuDaTas.get(pos).getDoctorOrders().get(0).setOrderExecuteRecords(new ArrayList<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean>());
                    }
                    orderExecuteRecords = dbYiZhuDaTas.get(pos).getDoctorOrders().get(0).getOrderExecuteRecords();
                    orderExecuteRecordsBean = new YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean();
                    if (orderExecuteRecords.size() > 0) {
                        for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean recordsBean
                                : orderExecuteRecords) {
                            if (recordsBean != null) {
                                if (Constant.YZ_EXECUTE_JOBTYPE_PEIYE.equals(recordsBean.getJobtype())) {
                                    flag = true;
                                    orderExecuteRecordsBean.setUserid2(userid);
                                    dbYiZhuDaTas.get(pos).setOrderststus(Constant.YZ_TYPE_WEIZHIXING);
                                }
                            }
                        }
                    }
                    if (!flag) {
                        orderExecuteRecordsBean.setUserid(userid);
                    }
                    orderExecuteRecordsBean.setExetime(System.currentTimeMillis());
                    orderExecuteRecordsBean.setJobtype("1");
                    orderExecuteRecords.add(orderExecuteRecordsBean);
                }

                dbYiZhuData.add(  dbYiZhuDaTas.get(pos));
            }
        }

        for (int i = dbYiZhuDaTas.size() - 1; i >= 0; i--) {
            if (!(Constant.YZ_TYPE_DAIPEIYE.equals(dbYiZhuDaTas.get(i).getOrderststus()))) {
                dbYiZhuDaTas.remove(i);
            }
        }
        if (dbYiZhuData == null || dbYiZhuData.size() <= 0) {
            showToastShort("请选择至少一个医嘱!");
            return;
        }
        new DoctorOrderDaoImpl().saveDoctorOrders(dbYiZhuData,true);
        NetworkForDoctorOrder.sumbmitAllDoctorOrder(YZ_HD_RenActivity.this, dbYiZhuData);
        dbYiZhuDaTas = mDoctorOrderDaoImpl.getFPaiXuDBYiZhuDatabean(dbYiZhuDaTas);
        yzHdRenLvItemAdapter.setmEntities(dbYiZhuDaTas);
        yzHdRenLvItemAdapter.setSjPosList(ActivityUtils.suanPos(dbYiZhuDaTas));
        yzHdRenLvItemAdapter.notifyDataSetChanged();
        jiaZaiDialog.show();
    }

    private void deleteException(List<YiZhuBean.DataBean> dbYiZhuDaTas, int pos, long orderExceptionsLastTime) {
        if (dbYiZhuDaTas.get(pos).getDoctorOrders().get(0).getOrderExceptions() != null && dbYiZhuDaTas.get(pos).getDoctorOrders().get(0).getOrderExceptions().size() > 0) {
            for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean orderExceptionsBean : dbYiZhuDaTas.get(pos).getDoctorOrders().get(0).getOrderExceptions()) {
                if (orderExceptionsBean != null && orderExceptionsBean.getCreationtime() != null) {
                    if ((orderExceptionsLastTime - orderExceptionsBean.getCreationtime().longValue()) < 0) {
                        orderExceptionsLastTime = orderExceptionsBean.getCreationtime();
                    }
                    if (orderExceptionsLastTime == orderExceptionsBean.getCreationtime().longValue()) {
                        orderExceptionsBean.setIsdeleted("1");
                        mDoctorOrderDaoImpl.saveExceptionForCheck(dbYiZhuDaTas.get(pos).getId(),null);
                    }
                }
            }
        }
    }

    private void baiYaoTiJiao() {
        List<YiZhuBean.DataBean> dbYiZhuDaTas = yzHdRenLvItemAdapter.getmEntities();
        Map<Integer, Boolean> cbMap = yzHdRenLvItemAdapter.getmCBFlag();
        int pos = 0;
        Integer pharmId = null;
        List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> orderExecuteRecords;
        YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean;
        long orderExceptionsLastTime = 0;
        for (Map.Entry<Integer, Boolean> entry : cbMap.entrySet()) {
            pos = entry.getKey();
            if (entry.getValue()) {
                if (dbYiZhuDaTas.get(pos).getOrdertype().equals(Constant.TYPE_YZ_SHUYE)) {
                    dbYiZhuDaTas.get(pos).setOrderststus(Constant.YZ_TYPE_DAIPEIYE);
                } else {
                    dbYiZhuDaTas.get(pos).setOrderststus(Constant.YZ_TYPE_WEIZHIXING);
                }
                if (dbYiZhuDaTas.get(pos).getDoctorOrders() != null && dbYiZhuDaTas.get(pos).getDoctorOrders().get(0) != null) {
                 deleteException(dbYiZhuDaTas, pos, orderExceptionsLastTime);
                    try {
                        pharmId = dbYiZhuDaTas.get(pos).getPharmList().get(0).getPharmId();
                    } catch (Exception e) {
                        LogUtil.e(e.getMessage());
                    }
                    if (dbYiZhuDaTas.get(pos).getDoctorOrders().get(0).getOrderExecuteRecords() == null) {
                        dbYiZhuDaTas.get(pos).getDoctorOrders().get(0).setOrderExecuteRecords(new ArrayList<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean>());
                    }
                    orderExecuteRecords = dbYiZhuDaTas.get(pos).getDoctorOrders().get(0).getOrderExecuteRecords();
                    orderExecuteRecordsBean = new YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean();
                    orderExecuteRecordsBean.setUserid(userid);
                    orderExecuteRecordsBean.setExetime(System.currentTimeMillis());
                    orderExecuteRecordsBean.setPharmId(pharmId);
                    orderExecuteRecordsBean.setPharmStatus(0);
                    orderExecuteRecordsBean.setJobtype("0");
                    orderExecuteRecords.add(orderExecuteRecordsBean);
                }
            }
        }
        dbYiZhuData = new ArrayList<YiZhuBean.DataBean>();
        for (int i = dbYiZhuDaTas.size() - 1; i >= 0; i--) {
            if (!(Constant.YZ_TYPE_DAIBAIYAO.equals(dbYiZhuDaTas.get(i).getOrderststus()))) {
                dbYiZhuData.add(dbYiZhuDaTas.get(i));
                dbYiZhuDaTas.remove(i);
            }
        }
        if (dbYiZhuData == null || dbYiZhuData.size() <= 0) {
            showToastShort("请选择至少一个医嘱!");
            return;
        }
        new DoctorOrderDaoImpl().saveDoctorOrders(dbYiZhuData,true);
        NetworkForDoctorOrder.sumbmitAllDoctorOrder(YZ_HD_RenActivity.this, dbYiZhuData);
        dbYiZhuDaTas = mDoctorOrderDaoImpl.getFPaiXuDBYiZhuDatabean(dbYiZhuDaTas);
        yzHdRenLvItemAdapter.setmEntities(dbYiZhuDaTas);
        yzHdRenLvItemAdapter.setSjPosList(ActivityUtils.suanPos(dbYiZhuDaTas));
        yzHdRenLvItemAdapter.notifyDataSetChanged();
        jiaZaiDialog.show();
    }

    private void heDuiTiJiao() {
        List<YiZhuBean.DataBean> dbYiZhuDaTas = yzHdRenLvItemAdapter.getmEntities();
        Map<Integer, Boolean> cbMap = yzHdRenLvItemAdapter.getmCBFlag();
        int pos = 0;
        for (Map.Entry<Integer, Boolean> entry : cbMap.entrySet()) {
            pos = entry.getKey();
            if (entry.getValue()) {
                if (dbYiZhuDaTas.get(pos).getOrdertype().equals(Constant.TYPE_YZ_KOUFU)
                        || dbYiZhuDaTas.get(pos).getOrdertype().equals(Constant.TYPE_YZ_JIROUZHUSHE)
                        || dbYiZhuDaTas.get(pos).getOrdertype().equals(Constant.TYPE_YZ_JINGMAIZHUSHE)
                        || dbYiZhuDaTas.get(pos).getOrdertype().equals(Constant.TYPE_YZ_PIXIAZHUSHE)
                        || dbYiZhuDaTas.get(pos).getOrdertype().equals(Constant.TYPE_YZ_PISHI)
                        || dbYiZhuDaTas.get(pos).getOrdertype().equals(Constant.TYPE_YZ_SHUYE)) {
                    dbYiZhuDaTas.get(pos).setOrderststus(Constant.YZ_TYPE_DAIBAIYAO);
                } else if (dbYiZhuDaTas.get(pos).getOrdertype().equals(Constant.TYPE_YZ_SHUXUE)) {
                    dbYiZhuDaTas.get(pos).setOrderststus(Constant.YZ_TYPE_DAIQUXUE);
                } else {
                    dbYiZhuDaTas.get(pos).setOrderststus(Constant.YZ_TYPE_WEIZHIXING);
                }
                dbYiZhuDaTas.get(pos).setLastupdatedby(userid);
                dbYiZhuDaTas.get(pos).setCheckby(userid);
                dbYiZhuDaTas.get(pos).setChecktime(System.currentTimeMillis());
                dbYiZhuDaTas.get(pos).setLastupdatetime(System.currentTimeMillis());
            }
        }
        dbYiZhuData = new ArrayList<YiZhuBean.DataBean>();
        for (int i = dbYiZhuDaTas.size() - 1; i >= 0; i--) {
            if (!(Constant.YZ_TYPE_WEIHEDUI.equals(dbYiZhuDaTas.get(i).getOrderststus()))) {
                dbYiZhuData.add(dbYiZhuDaTas.get(i));
                dbYiZhuDaTas.remove(i);
            }
        }
        if (dbYiZhuData == null || dbYiZhuData.size() <= 0) {
            showToastShort("请选择至少一个医嘱!");
            return;
        }
        new DoctorOrderDaoImpl().saveDoctorOrders(dbYiZhuData,true);
        NetworkForDoctorOrder.sumbmitAllDoctorOrder(YZ_HD_RenActivity.this, dbYiZhuData);
        dbYiZhuDaTas = mDoctorOrderDaoImpl.getFPaiXuDBYiZhuDatabean(dbYiZhuDaTas);
        yzHdRenLvItemAdapter.setmEntities(dbYiZhuDaTas);
        yzHdRenLvItemAdapter.setSjPosList(ActivityUtils.suanPos(dbYiZhuDaTas));
        yzHdRenLvItemAdapter.notifyDataSetChanged();
        jiaZaiDialog.show();
    }

    @Override
    public boolean useTitleBar() {
        return false;
    }


    @Override
    public void onBackPressed() {
        if (mShaiXuanIncludeLl.getVisibility() == View.VISIBLE) {
            mShaiXuanIncludeLl.setVisibility(View.GONE);
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        yiZhuLv(fanhui);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (2 == resultCode) {
            if (data != null) {
                data.getBundleExtra(Constant.GLOBAL_KEY_DATA);
                yiZhuDataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
            }
            int i = dbYiZhuData.indexOf(yiZhuDataBean);
            yzHdRenLvItemAdapter.mCBFlag.put(i, true);
            yzHdRenLvItemAdapter.setPos(i);
            yzHdRenLvItemAdapter.notifyDataSetChanged();
            mYzHdRenLv.setSelection(i);
            isToast = true;
            fanhui = true;
            mYzHdRenYichangBt.setEnabled(true);
            mYzHdRenYichangBt.performClick();
        }
        if (1 == resultCode) {//详情确认核对返回
            if (data != null) {
                data.getBundleExtra(Constant.GLOBAL_KEY_DATA);
                yiZhuDataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
            }
            int i = dbYiZhuData.indexOf(yiZhuDataBean);
            yzHdRenLvItemAdapter.mCBFlag.put(i, true);
            yzHdRenLvItemAdapter.setPos(i);
            yzHdRenLvItemAdapter.notifyDataSetChanged();
            mYzHdRenLv.setSelection(i);
            isToast = true;
            fanhui = true;
            mYzHdRenYichangBt.setEnabled(true);
        }
        if (0 == requestCode) {
            if (0 == resultCode) {
                if (data != null) {
                    Bundle bundle2 = data.getBundleExtra("data");
                    final ArrayList<String> yichangs = bundle2.getStringArrayList("yichang");
                    if (yichangs != null && !yichangs.isEmpty()) {
                        final CheckBox yichangCB = yzHdRenLvItemAdapter.getYichangCB();
                        int pos = yzHdRenLvItemAdapter.getPos();
                        Map<Integer, TextView> cbMap = yzHdRenLvItemAdapter.getCbMap();
                        cbMap.remove(pos);
                        YiZhuBean.DataBean dbYiZhuDaTa = dbYiZhuData.get(pos);
                        dbYiZhuDaTa.setYiChang(true);
                        yichangCB.setChecked(false);
                        final TextView yichangTV = yzHdRenLvItemAdapter.getYichangTV();
                        yichangTV.setText(yichangs.get(yichangs.size() - 1));
                        dbYiZhuDaTa.setYiChangXinXi(yichangs.get(yichangs.size() - 1));
                        yichangTV.setTextColor(Color.RED);
                        final ImageView yichangIMGV = yzHdRenLvItemAdapter.getYichangIMGV();
                        yichangIMGV.setVisibility(View.VISIBLE);
                        yichangIMGV.setImageResource(R.mipmap.icon_stop);
                        final TextView xuanzhongTV = yzHdRenLvItemAdapter.getXuanzhongTV();
                        final TextView xuanzhongTV1 = yzHdRenLvItemAdapter.getXuanzhongTV1();
                        xuanzhongTV.setVisibility(View.GONE);
                        xuanzhongTV1.setVisibility(View.GONE);
                        mYzHdRenYichangBt.setEnabled(false);
                        yzHdRenLvItemAdapter.notifyDataSetChanged();
                        mDoctorOrderDaoImpl.saveExceptionForCheck(dbYiZhuDaTa.getId(), yichangs.get(yichangs.size() - 1));
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public YzHdRenLvItemAdapter getYzHdRenLvItemAdapter() {
        return yzHdRenLvItemAdapter;
    }

    public LinearLayout getmShaiXuanIncludeLl() {
        return mShaiXuanIncludeLl;
    }


    @Override
    public void handleOnError() {
        super.handleOnError();
        jiaZaiDialog.cancel();
        LogUtil.e("YZ_HD_LXActivity handleOnError");
    }

    @Override
    public void handleOnError(String urlStr) {
        super.handleOnError(urlStr);
        jiaZaiDialog.cancel();
        LogUtil.e("YZ_HD_LXActivity handleOnError()");
    }

    @Override
    public void handleSuccess(Object entity) {
        super.handleSuccess(entity);
        LogUtil.e("YZ_HD_LXActivity handleSuccess");
        showToastShort("提交成功");
//        if (!mPatientPanel.nextPatient()) {
        jiaZaiDialog.cancel();
        killSelf();
//        }
//        dataBean = mPatientPanel.getCurrentPatient();
//        bindPatientInfo();
//        yiZhuLv();
    }


}
