package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.TouchTime;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.YiZhuImpl;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.HzYzFragLvItemAdapter2;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.view.JiaZaiDialog;
import com.ge.med.mobile.nursing.utils.DateUtil;
import com.mitac.lib.bcr.utils.BARCODE;

import java.util.ArrayList;
import java.util.List;

public class YZ_ZX_ZhuShe2Activity extends MyBaseActivity {
    private ListView mHzYzLv;
    private Button mYzShuxueYichangBt;
    private Button mYzShuxueWanchengBt;

    private YiZhuBean.DataBean dataBean;
    private Bundle mBundle;
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;
    private TitleBar mTitleBar;
    private PatientInfoPannel mPatientPanel;
    private HzYzFragLvItemAdapter2 hzYzFragLvItemAdapter;
    private String displayUserName;
    private List<YiZhuBean.DataBean.DoctorOrdersBean> DoctorOrdersBeandataBeen;
    private JiaZaiDialog jiaZaiDialog;
    private String userid;
    private int ciShuInt = 0;
    private boolean isWanCheng;

    @Override
    public int setRootView() {
        return R.layout.activity_yz__zx__zhu_she2;
    }

    @Override
    public void initViews() {
        mHzYzLv = (ListView) findViewById(R.id.hz_yz_lv);
        mYzShuxueYichangBt = (Button) findViewById(R.id.yz_shuxue_yichang_bt);
        mYzShuxueWanchengBt = (Button) findViewById(R.id.yz_shuxue_wancheng_bt);
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
         userid = sharePLogin.getUserid();
        displayUserName = sharePLogin.getDisplayUserName();
        mBundle = getIntent().getBundleExtra("data");
        mSelectedHZ = (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
        mTitleBar = new TitleBar(this, mSelectedHZ);
        mPatientPanel = new PatientInfoPannel(this, new AssessDaoImpl().findAllRiskDefine(), mSelectedHZ);
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
        mPatientPanel.setTipName("未执行");
        mPatientPanel.showHideTipLayout(false);
        boolean aBoolean = mBundle.getBoolean(Constant.BUNDLE_KEY_ISFENCI, false);
        //判断是从哪里 跳转到这里的
        jiaZaiDialog = new JiaZaiDialog(mActivitySelf);
        jiaZaiDialog.setCanceledOnTouchOutside(false);
        jiaZaiDialog.show();
        if (!aBoolean) {
            ciShuInt = mBundle.getInt(Constant.BUNDLE_KEY_FENCI);
            DoctorOrdersBeandataBeen = dataBean.getDoctorOrders();
            int oder = 1;
            if(DoctorOrdersBeandataBeen == null){
                DoctorOrdersBeandataBeen = new ArrayList<>();
                oder = 0;
            }
            YiZhuBean.DataBean.DoctorOrdersBean doctorOrdersBean;

            for (int i = oder; i < ciShuInt; i++) {
                doctorOrdersBean = new YiZhuBean.DataBean.DoctorOrdersBean();
                Long aLong = DateUtil.addMinute(dataBean.getStarttime(), TouchTime.fenCi_F * i);
                doctorOrdersBean.setStarttime(aLong);
                doctorOrdersBean.setStatus(Constant.YZ_TYPE_WEIZHIXING);
                doctorOrdersBean.setSubordername(dataBean.getOrdername());
                doctorOrdersBean.setCreatedby(userid);
                doctorOrdersBean.setCreationtime(System.currentTimeMillis());
                doctorOrdersBean.setHisid(dataBean.getId());
                DoctorOrdersBeandataBeen.add(doctorOrdersBean);
            }
            dataBean.setDoctorOrders(DoctorOrdersBeandataBeen);
            //分次数据提交 同步id
            new DoctorOrderDaoImpl().saveDoctorOrder(dataBean,true);
            NetworkForDoctorOrder.submitSingleDoctorOrder(this, dataBean);

        }else {
            DoctorOrdersBeandataBeen = dataBean.getDoctorOrders();
            ciShuInt = DoctorOrdersBeandataBeen.size();
            jiaZaiDialog.cancel();
        }
        DoctorOrdersBeandataBeen = YiZhuImpl.paixuDoctorOrdersBean(DoctorOrdersBeandataBeen);
        hzYzFragLvItemAdapter = new HzYzFragLvItemAdapter2(mActivitySelf,DoctorOrdersBeandataBeen, dataBean, ciShuInt);
        mHzYzLv.setAdapter(hzYzFragLvItemAdapter);
        mPatientPanel.setTipNumber(ciShuInt);
        System.out.println("dataBean.getDoctorOrders()" + dataBean.getDoctorOrders());
    }



    @Override
    public void initDatas() {
        //异常按钮
        mYzShuxueYichangBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(Constant.ZHU_SHE_2_KEY, Constant.ZHU_SHE_2);
                goToActivity(YZ_ZX_ZhuSheActivity.class, mBundle);
            }
        });
        int i = 1;
        //完成按钮
        for (YiZhuBean.DataBean.DoctorOrdersBean doctorOrdersBean : DoctorOrdersBeandataBeen) {
            if (Constant.YZ_TYPE_YIZHIXING.equals(doctorOrdersBean.getStatus())) {
                i += 1;
            }
        }
        if (i > DoctorOrdersBeandataBeen.size()) {
            mYzShuxueWanchengBt.setText("完成注射");
        } else {
            mYzShuxueWanchengBt.setText("注射(" + i + "/" + DoctorOrdersBeandataBeen.size() + ")次");
        }
        mYzShuxueWanchengBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long l = System.currentTimeMillis();
              isWanCheng = false ;
                for (YiZhuBean.DataBean.DoctorOrdersBean doctorOrdersBean : DoctorOrdersBeandataBeen) {
                    if (Constant.YZ_TYPE_WEIZHIXING.equals(doctorOrdersBean.getStatus())) {
                        dataBean.setOrderststus(Constant.YZ_TYPE_ZHIXINGZHONG);
                        doctorOrdersBean.setStatus(Constant.YZ_TYPE_YIZHIXING);
                        doctorOrdersBean.setLastupdatetime(l);
                        doctorOrdersBean.setLastupdatedby(userid);
                        Integer id = doctorOrdersBean.getId();
                        mYzShuxueWanchengBt.setText("注射(" + (DoctorOrdersBeandataBeen.indexOf(doctorOrdersBean) + 2) + "/" + DoctorOrdersBeandataBeen.size() + ")次");
                        break;
                    }
                }
                if ("完成注射".equals(mYzShuxueWanchengBt.getText().toString())) {
                    dataBean.setOrderststus(Constant.YZ_TYPE_YIZHIXING);
                    dataBean.setLastupdatetime(l);
                    isWanCheng = true;
                    dataBean.setExecuteby(userid);
                    dataBean.setExetime(l);
                    dataBean.setLastupdatedby(userid);
                } else {
                    if (Constant.YZ_TYPE_YIZHIXING.equals(DoctorOrdersBeandataBeen.get(DoctorOrdersBeandataBeen.size() - 1).getStatus())) {
                        mYzShuxueWanchengBt.setText("完成注射");
                    }
                }
                dataBean.setDoctorOrders(DoctorOrdersBeandataBeen);
                DoctorOrdersBeandataBeen = YiZhuImpl.paixuDoctorOrdersBean(DoctorOrdersBeandataBeen);
                hzYzFragLvItemAdapter.setDbYiZhuDaTaList(DoctorOrdersBeandataBeen);
                hzYzFragLvItemAdapter.notifyDataSetChanged();
                jiaZaiDialog.show();
                new DoctorOrderDaoImpl().saveDoctorOrder(dataBean,true);
                NetworkForDoctorOrder.submitSingleDoctorOrder(YZ_ZX_ZhuShe2Activity.this, dataBean);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dataBean =  new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId());
        DoctorOrdersBeandataBeen = dataBean.getDoctorOrders();
        DoctorOrdersBeandataBeen = YiZhuImpl.paixuDoctorOrdersBean(DoctorOrdersBeandataBeen);
        hzYzFragLvItemAdapter.setDbYiZhuDaTaList(DoctorOrdersBeandataBeen);
        hzYzFragLvItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void handleOnError() {
        super.handleOnError();
        jiaZaiDialog.cancel();
        if (isWanCheng) {
            killSelf();
        }
    }

    @Override
    public void handleOnError(String urlStr) {
        super.handleOnError(urlStr);
        jiaZaiDialog.cancel();
        if (isWanCheng) {
            killSelf();
        }
    }

    @Override
    public void handleSuccess(Object obj) {
        super.handleSuccess(obj);
        jiaZaiDialog.cancel();
        dataBean = new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId());
        mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU,dataBean);
        DoctorOrdersBeandataBeen = dataBean.getDoctorOrders();
        ciShuInt = DoctorOrdersBeandataBeen.size();
        DoctorOrdersBeandataBeen = YiZhuImpl.paixuDoctorOrdersBean(DoctorOrdersBeandataBeen);
        if(hzYzFragLvItemAdapter == null){
            hzYzFragLvItemAdapter = new HzYzFragLvItemAdapter2(mActivitySelf,DoctorOrdersBeandataBeen, dataBean, ciShuInt);
            mHzYzLv.setAdapter(hzYzFragLvItemAdapter);
        }else{
            hzYzFragLvItemAdapter.setDbYiZhuDaTaList(DoctorOrdersBeandataBeen);
            hzYzFragLvItemAdapter.notifyDataSetChanged();
        }
        if (isWanCheng) {
            killSelf();
        }
    }

}
