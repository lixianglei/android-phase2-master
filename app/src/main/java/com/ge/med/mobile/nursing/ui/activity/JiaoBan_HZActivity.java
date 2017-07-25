package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.DoctorOrderDao;
import com.ge.med.mobile.nursing.dao.YiZhuInterface;
import com.ge.med.mobile.nursing.dao.entity.DischargepatientBean;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.SubmitJiaoBanBean;
import com.ge.med.mobile.nursing.dao.entity.TiJiaoBean;
import com.ge.med.mobile.nursing.dao.entity.XinjianJiaoBanBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.dao.impl.VitalSignDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.YiZhuImpl;
import com.ge.med.mobile.nursing.forjson.NetworkForVitalSign;
import com.ge.med.mobile.nursing.forjson.callback.BaseNetCallback;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignSheet;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.ZhiXingZhongYzIncludeAdapter;
import com.ge.med.mobile.nursing.ui.component.JiaoBanEdt;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.view.JiaZaiDialog;
import com.ge.med.mobile.nursing.ui.view.SelfDialog;
import com.ge.med.mobile.nursing.utils.DataConverter;
import com.mitac.lib.bcr.utils.BARCODE;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class JiaoBan_HZActivity extends MyBaseActivity {
    private TextView mShaiXuanIncludeKongbaiTv;
    private TextView mZhiXingZhongYzIncluedYishengTujingTv;
    private TextView mZhiXingZhongYzIncluedBeizhuTv;
    private TextView mZhiXingZhongYzIncluedZhuangtaiTv;
    private LinearLayout mShaiXuanIncludeLl;
    private LinearLayout mTiZhengDuoRenBtLl;
    private LinearLayout mHzPgLl;
    private LinearLayout mYzHzPgLl;
    private CheckBox mZhiXingZhongCheckBox;
    private CheckBox mJiaoBanCheckBox;
    private Button mTiZhengDuoRenBackBt;
    private Button mTiZhengDuoRenBaocunBt;
    private ImageView mJiaoBanCheckImgv;
    private ImageView mZhiXingZhongYzIncluedHuliJibieImgv;
    private ImageView mDuorenTizhengJiaobiaoImgv;
    private ImageView mZhiXingZhongYzIncluedZhuangtaiImgv;
    private ImageView mZhiXingZhongCheckImgv;
    private ImageView mDrtzBackImgv;
    private boolean isShiJian;

    private ImageView mDrtzNextImgv;
    //    private ListView mHuanZheXuanZeLv;
//    private HuanZheXuanZeLvItemAdapter huanZheXuanZeLvItemAdapter;
    private ListView mJiaobanYizhuLv;
    private ZhiXingZhongYzIncludeAdapter zhiXingZhongYzIncludeAdapter;

    private Bundle mBundle;
    private HuanZheLieBiaoImpl huanZheLieBiao;
    private HuanZheLieBiaoBean.DataBean dataBean;
    private int i;
    private DoctorOrderDao mDoctorOrderDaoImpl;
    private String userid;
    private String patientid;
    private TitleBar mTitleBar;
    private YiZhuInterface doctorOrderDao;
    private VitalSignSheet mVitalSignSheet;
    private List<YiZhuBean.DataBean> dataBeens;
    private PatientInfoPannel mPatientPannel;
    private XinjianJiaoBanBean xinjianJiaoBanBean;
    private JiaZaiDialog jiaZaiDialog;
    private JiaoBanEdt jiaoBanEdt;
    private int templateId;
    private String mTime;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Map<String, String> mEntitiesMap = new HashMap<>();
            switch (msg.what) {
                case 1:
                    List<XinjianJiaoBanBean.DataBean.PatientBean.ExecutingOrderListBean> executingOrderList = new ArrayList<>();
                    if (xinjianJiaoBanBean != null && xinjianJiaoBanBean.getData() != null) {
                        if (xinjianJiaoBanBean.getData().getWardVitalDefine() != null) {
                            for (XinjianJiaoBanBean.DataBean.WardVitalDefineBean wardVitalDefineBean : xinjianJiaoBanBean.getData().getWardVitalDefine()) {
                                mEntitiesMap.put(wardVitalDefineBean.getSignname(), "0 " + wardVitalDefineBean.getUnitdesc());
                            }
                        }
                        if (xinjianJiaoBanBean.getData().getPatient() != null) {
                            if (xinjianJiaoBanBean.getData().getPatient().getExecutingOrderList() != null) {
                                executingOrderList = xinjianJiaoBanBean.getData().getPatient().getExecutingOrderList();
                            }
                            if (xinjianJiaoBanBean.getData().getPatient().getTotalVitalInOutList() != null
                                    && xinjianJiaoBanBean.getData().getPatient().getTotalVitalInOutList().size() > 0) {
                                for (XinjianJiaoBanBean.DataBean.PatientBean.TotalVitalInOutListBean totalVitalInOutListBean
                                        : xinjianJiaoBanBean.getData().getPatient().getTotalVitalInOutList()) {
                                    if (mEntitiesMap != null) {
                                        mEntitiesMap.put(totalVitalInOutListBean.getName(), totalVitalInOutListBean.getValue()
                                                + " " + totalVitalInOutListBean.getUnit());
                                    }
                                }
                            }
                        }
                        mTitleBar = new TitleBar(JiaoBan_HZActivity.this, dataBean);
                        mTitleBar.setShaiXuanVisible(false);
                        mPatientPannel = new PatientInfoPannel(JiaoBan_HZActivity.this, new AssessDaoImpl().findAllRiskDefine(), dataBean);
                        mPatientPannel.showHideTipLayout(false);
                        initTiZheng(dataBean);
                        zhiXingZhongYzIncludeAdapter = new ZhiXingZhongYzIncludeAdapter(mActivitySelf, executingOrderList,
                                dataBean, mEntitiesMap, xinjianJiaoBanBean.getData(), jiaoBanEdt);
                        mJiaobanYizhuLv.setAdapter(zhiXingZhongYzIncludeAdapter);
                        if (zhiXingZhongYzIncludeAdapter.getmVitalDefines() == null) {
                            zhiXingZhongYzIncludeAdapter.setVitalSignDefines(new VitalSignDaoImpl().findAllWardDefineFromDB());
                        }
                        if (jiaoBanEdt != null && jiaoBanEdt.getEdt() != null) {
                            zhiXingZhongYzIncludeAdapter.setEdt(jiaoBanEdt.getEdt());
                        }
                        zhiXingZhongYzIncludeAdapter.notifyDataSetChanged();
                    }
                    break;
                default:
                    break;
            }
        }
    };
//    private Map<Integer, String> mJiaoBanJiLu = new HashMap<>();

    @Override
    public int setRootView() {
        return R.layout.activity_jiao_ban__hz;
    }

    @Override
    public void initViews() {
        jiaoBanEdt = new JiaoBanEdt();
        doctorOrderDao = new YiZhuImpl();
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        mDoctorOrderDaoImpl = new DoctorOrderDaoImpl();
        userid = sharePLogin.getUserid();
        huanZheLieBiao = new HuanZheLieBiaoImpl();
        mBundle = getIntent().getBundleExtra("data");
        dataBean = (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
        mTime = mBundle.getString(Constant.BUNDLE_KEY_SJ);
        mDuorenTizhengJiaobiaoImgv = (ImageView) findViewById(R.id.duoren_tizheng_jiaobiao_imgv);
        mJiaoBanCheckBox = (CheckBox) findViewById(R.id.jiao_ban_check_box);
        mJiaoBanCheckImgv = (ImageView) findViewById(R.id.jiao_ban_check_imgv);
        mZhiXingZhongYzIncluedHuliJibieImgv = (ImageView) findViewById(R.id.zhi_xing_zhong_yz_inclued_huli_jibie_imgv);
        mTiZhengDuoRenBtLl = (LinearLayout) findViewById(R.id.ti_zheng_duo_ren_bt_ll);
        mTiZhengDuoRenBackBt = (Button) findViewById(R.id.ti_zheng_duo_ren_back_bt);
        mTiZhengDuoRenBaocunBt = (Button) findViewById(R.id.ti_zheng_duo_ren_baocun_bt);
        mShaiXuanIncludeLl = (LinearLayout) findViewById(R.id.shai_xuan_include_ll);
        mShaiXuanIncludeKongbaiTv = (TextView) findViewById(R.id.shai_xuan_include_kongbai_tv);
        mJiaobanYizhuLv = (ListView) findViewById(R.id.jiaoban_yizhu_lv);
        mShaiXuanIncludeKongbaiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShaiXuanIncludeLl.setVisibility(View.GONE);
            }
        });
        if (dataBean == null) {
            dataBean = new HuanZheLieBiaoBean.DataBean();
            showToastShort("数据异常,无法初始化界面,请退出重新进入!");
            killSelf();
        }
        jiaZaiDialog = new JiaZaiDialog(this);
        jiaZaiDialog.show();
        initJiaoBianDatas(dataBean.getPatientid());

    }

    private void initJiaoBianDatas(String patientid) {
        OkHttpUtils.post().url(URL.URL_JB_CALL).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_PATIENT_ID, patientid)
                .addParams("time", mTime)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        jiaZaiDialog.cancel();
                        showToastShort("网络或服务器数据异常,请重新请求!");
                        LogUtil.e(e.getMessage()+"");
                        killSelf();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        jiaZaiDialog.cancel();
                        if (response != null) {
                            try {
                                xinjianJiaoBanBean = JSON.parseObject(response, XinjianJiaoBanBean.class);
                                mHandler.sendEmptyMessage(1);
                            } catch (Exception e) {
                                LogUtil.e(e.getMessage());
                                showToastShort("网络或服务器数据异常,请重新请求!");
                                killSelf();
                            }
                        } else {
                            showToastShort("网络或服务器数据异常,请重新请求!");
                            LogUtil.e("response is null!");
                            killSelf();
                        }
                    }
                });
    }

    @Override
    public void afterPatientScanned(String patientID) {
        dataBean = DataConverter.convert(new HuanZheLieBiaoImpl().getDBHuanZheLieBiao(patientID));
        mPatientPannel.changePatient(dataBean);
        mTitleBar.changePatient(dataBean);
        initTiZheng(dataBean);

    }


    //体征数据绑定
    private void initTiZheng(HuanZheLieBiaoBean.DataBean dataBean) {
        Log.d("dataBean", dataBean + "");
        if (dataBean != null) {
            patientid = dataBean.getPatientid();
            NetworkForVitalSign.callLastVitalSheetByPatient(this, patientid);
        }
    }

    @Override
    public void handleSuccess(Object obj) {
        if (obj instanceof VitalSignSheet) {
            mVitalSignSheet = (VitalSignSheet) obj;
            zhiXingZhongYzIncludeAdapter.setVitalSignSheet(mVitalSignSheet);
//            initEdtTextString();
            zhiXingZhongYzIncludeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void initDatas() {
        //保存按钮监听
        mTiZhengDuoRenBaocunBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonString = initJiaoBanTiJiaoBean();
                LogUtil.d("jsonString :" + jsonString);
                OkHttpUtils.post()
                        .url(URL.URL_JB_DAN)
                        .addHeader("User-Agent", "www.gs.com")
                        .addParams("jsonString", jsonString)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                e.printStackTrace();
                                Log.e("交班提交", "onError");
                                showToastShort("网络访问数据异常！");
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("交班提交", "onResponse=" + response);
                                TiJiaoBean tiJiaoBean = JSON.parseObject(response, TiJiaoBean.class);
                                if ("成功".equals(tiJiaoBean.getMsg())) {
//                                    if(dataBeanList.indexOf(mPatientPannel.getCurrentPatient())==(dataBeanList.size()-1)){
//                                        showToastShort("全部患者保存完毕，交班完成！");
//                                        killSelf();
//                                    }
//                                    mPatientPannel.nextPatient();
//                                    initData();
                                    killSelf();
                                    showToastShort("保存成功");

                                } else {
                                    showToastShort("网络访问数据异常！");
                                }
                            }
                        });
            }
        });
    }

    private String initJiaoBanTiJiaoBean() {
        SubmitJiaoBanBean submitJiaoBanBean = new SubmitJiaoBanBean();
        submitJiaoBanBean.setUserid(userid);
        submitJiaoBanBean.setTemplateId(templateId);
        if (dataBean != null) {
            submitJiaoBanBean.setPatientid(dataBean.getPatientid());
        }
        SubmitJiaoBanBean.DischargePersonalBean dischargePersonalBean = new SubmitJiaoBanBean.DischargePersonalBean();
        String dateString = null;
        try {
            dateString = DateUtils.getDateString("yyyy-MM-dd HH:mm:ss", Long.parseLong(mTime));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        dischargePersonalBean.setTime(dateString);
        submitJiaoBanBean.setName(dataBean.getName());
        dischargePersonalBean.setUserid(userid);
        submitJiaoBanBean.setDischargePersonal(dischargePersonalBean);
        if (mVitalSignSheet != null && zhiXingZhongYzIncludeAdapter != null && zhiXingZhongYzIncludeAdapter.isTiZhengXuanZhong()) {
            submitJiaoBanBean.setSignsheetid(mVitalSignSheet.getId());
        }
        submitJiaoBanBean.setStatus(1);
        submitJiaoBanBean.setTime(dateString);
        if (zhiXingZhongYzIncludeAdapter != null) {
            submitJiaoBanBean.setDescription(zhiXingZhongYzIncludeAdapter.getEdt());
        }
        List<XinjianJiaoBanBean.DataBean.PatientBean.ExecutingOrderListBean> executingOrderListBeen = zhiXingZhongYzIncludeAdapter.getmEntities();
        Map<Integer, Boolean> integerBooleanMap = zhiXingZhongYzIncludeAdapter.getmCBFlag();
        List<SubmitJiaoBanBean.DisOrderListBean> disOrderList = new ArrayList<>();
        SubmitJiaoBanBean.DisOrderListBean disOrderListBean1 = null;
        for (Map.Entry<Integer, Boolean> entry : integerBooleanMap.entrySet()) {
            int pos = entry.getKey();
            if (integerBooleanMap.get(pos)) {
                disOrderListBean1 = new SubmitJiaoBanBean.DisOrderListBean();
                disOrderListBean1.setHisorderid(executingOrderListBeen.get(pos).getId());
                disOrderList.add(disOrderListBean1);
            }
        }
        submitJiaoBanBean.setDisOrderList(disOrderList);
        List<SubmitJiaoBanBean.DischargeVitalTotalListBean> dischargeVitalTotalList = new ArrayList<>();
        SubmitJiaoBanBean.DischargeVitalTotalListBean dischargeVitalTotalListBean;
        if (xinjianJiaoBanBean != null && xinjianJiaoBanBean.getData() != null
                && xinjianJiaoBanBean.getData().getPatient() != null
                && xinjianJiaoBanBean.getData().getPatient().getTotalVitalInOutList() != null
                && xinjianJiaoBanBean.getData().getPatient().getTotalVitalInOutList().size() > 0) {
            for (XinjianJiaoBanBean.DataBean.PatientBean.TotalVitalInOutListBean totalVitalInOutListBean
                    : xinjianJiaoBanBean.getData().getPatient().getTotalVitalInOutList()) {
                dischargeVitalTotalListBean = new SubmitJiaoBanBean.DischargeVitalTotalListBean();
                dischargeVitalTotalListBean.setTotalValue(totalVitalInOutListBean.getValue());
                dischargeVitalTotalListBean.setValue(totalVitalInOutListBean.getValue());
                dischargeVitalTotalListBean.setVitalName(totalVitalInOutListBean.getName());
                dischargeVitalTotalListBean.setUnit(totalVitalInOutListBean.getUnit());
                dischargeVitalTotalList.add(dischargeVitalTotalListBean);
            }
        }
        submitJiaoBanBean.setDischargeVitalTotalList(dischargeVitalTotalList);
        return JSONObject.toJSONString(submitJiaoBanBean, SerializerFeature.WriteNullListAsEmpty);
    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {

        return false;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isShiJian) {
            isShiJian = false;
            return;
        }
        initJiaoBianDatas(dataBean.getPatientid());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && data.getBundleExtra("data") != null) {
            isShiJian = true;
            mBundle = data.getBundleExtra("data");
            jiaoBanEdt = (JiaoBanEdt) mBundle.getSerializable(Constant.BUNDLE_KEY_JIAOBAN_SHIJIAN_EDT);
            templateId = mBundle.getInt(Constant.BUNDLE_KEY_JIAOBAN_SHIJIAN_TEMPLATEID);
            if (jiaoBanEdt != null && jiaoBanEdt.getEdt() != null) {
                zhiXingZhongYzIncludeAdapter.setEdt(jiaoBanEdt.getEdt());
                zhiXingZhongYzIncludeAdapter.notifyDataSetChanged();
            }
        }

    }


}
