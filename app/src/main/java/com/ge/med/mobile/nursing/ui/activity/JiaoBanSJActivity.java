package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.ui.component.DateTimeControl;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.component.callback.NotifyPatientChange;
import com.mitac.lib.bcr.utils.BARCODE;

import java.util.ArrayList;
import java.util.List;

public class JiaoBanSJActivity extends MyBaseActivity {
    private PatientInfoPannel mPatientPanel;
    private DateTimeControl mDateTimeControl;
    private Bundle mBundle;
    private List<CharSequence> list = new ArrayList<>();
    private List<DBHuanZheLieBiao> huanZheLieBiaoList = new ArrayList<>();
    private HuanZheLieBiaoImpl huanZheLieBiao;
    private List<HuanZheLieBiaoBean.DataBean> dataBeanList;
    private HuanZheLieBiaoBean.DataBean dataBean;
    private TitleBar mTitleBar;
    private Button mTiZhengDuoRenBackBt;
    private Button mTiZhengDuoRenBaocunBt;
    private TextView drtzSjImgv;




    @Override
    public int setRootView() {
        return R.layout.activity_jiao_ban_sj;
    }

    @Override
    public void initViews() {
        mTiZhengDuoRenBackBt = (Button) findViewById(R.id.ti_zheng_duo_ren_back_bt);
        mTiZhengDuoRenBaocunBt = (Button) findViewById(R.id.ti_zheng_duo_ren_baocun_bt);
        drtzSjImgv = (TextView) findViewById(R.id.drtz_sj_imgv);

        mBundle = getIntent().getBundleExtra(Constant.GLOBAL_KEY_DATA);
        String activityType = mBundle.getString(Constant.BUNDLE_KEY_ACTIVITY_TYPE);
        if (Constant.BUNDLE_ACTIVITY_JILU_CHANGGUI_HULI.equals(activityType)) {
            dataBean = (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
            mPatientPanel = new PatientInfoPannel(this, new AssessDaoImpl().findAllRiskDefine(), dataBean);
            mPatientPanel.showHideTipLayout(false);
            mDateTimeControl = new DateTimeControl(this);
            mTitleBar = new TitleBar(this, dataBean);
            drtzSjImgv.setText("记录时间");
            mTiZhengDuoRenBaocunBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String aLong = mDateTimeControl.getLong();
                    mBundle.putString(Constant.BUNDLE_KEY_SJ, aLong);
                    mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.BUNDLE_KEY_SJ);
                    goToActivity(ChangGuiHuLiActivity.class,mBundle);
                }
            });
        } else {
            initJiaoBanData();
        }
    }

    @Override
    public void initDatas() {
        mTiZhengDuoRenBackBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killSelf();
            }
        });

    }

    //交班跳转过来 数据初始化
    private void initJiaoBanData() {
        list = mBundle.getCharSequenceArrayList(Constant.BUNDLE_KEY_LIST_HUANZHE);
        huanZheLieBiao = new HuanZheLieBiaoImpl();
        for (CharSequence cs : list) {
            huanZheLieBiaoList.add(huanZheLieBiao.getDBHuanZheLieBiao(cs.toString()));
        }
        dataBeanList = huanZheLieBiao.getDataBeanList(huanZheLieBiaoList);
        dataBeanList = huanZheLieBiao.getZhengXuListBean(dataBeanList);
        mPatientPanel = new PatientInfoPannel(this, new AssessDaoImpl().findAllRiskDefine(), dataBeanList);
        mDateTimeControl = new DateTimeControl(this);
        mTitleBar = new TitleBar(this, dataBeanList.get(0));
        mPatientPanel.changePatient(dataBeanList.get(0));
        mTitleBar.changePatient(dataBeanList.get(0));
        initData();
        mPatientPanel.setOnPatientChangeCallback(new NotifyPatientChange() {
            @Override
            public void onPatientChange() {
                initData();
            }
        });
        mPatientPanel.setPatients(dataBeanList);

        mTiZhengDuoRenBaocunBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                String aLong = mDateTimeControl.getLong();
                bundle.putString(Constant.BUNDLE_KEY_SJ, aLong);
                bundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, dataBean);
                goToActivity(JiaoBan_HZActivity.class, bundle);
            }
        });
    }

    private void initData() {
        dataBean = mPatientPanel.getCurrentPatient();
        mTitleBar.changePatient(dataBean);
    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }


}
