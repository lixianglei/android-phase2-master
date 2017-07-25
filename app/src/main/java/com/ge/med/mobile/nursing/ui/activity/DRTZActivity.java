package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.HuanZheLieBiaoInterface;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.dao.impl.VitalSignDaoImpl;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignRecord;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignSheet;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.DuorenTizhengLvItemAdapter;
import com.ge.med.mobile.nursing.ui.component.DataFilterMenu;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.component.callback.NotifyFilterSelected;
import com.ge.med.mobile.nursing.ui.component.callback.NotifyPatientChange;
import com.ge.med.mobile.nursing.utils.DataConverter;
import com.mitac.lib.bcr.utils.BARCODE;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class DRTZActivity extends MyBaseActivity {
    private ListView mDrtzLv;
    private DuorenTizhengLvItemAdapter duorenTizhengLvItemAdapter;
    private TextView mNewVitalTextView;
    private List<VitalSignSheet> hisVitalSheetList;
    private TitleBar mTitleBar;
    private PatientInfoPannel mPatientPannel;
    private DataFilterMenu mFilterMenu;
    private Bundle mBundle;


    @Override
    public int setRootView() {
        return R.layout.activity_drtz;
    }

    @Override
    public void initViews() {
        mBundle = mActivitySelf.getIntent().getBundleExtra(Constant.GLOBAL_KEY_DATA);
        if(mBundle == null) mBundle = new Bundle();
        mPatientPannel = new PatientInfoPannel(this, new AssessDaoImpl().findAllRiskDefine());
        mPatientPannel.setOnPatientChangeCallback(new NotifyPatientChange(){
            @Override
            public void onPatientChange() {
                setHisVitalSheetList(getVitalSheetsByPatient(mPatientPannel.getCurrentPatient()));
                mTitleBar.changePatient(mPatientPannel.getCurrentPatient());
            }
        });
        mFilterMenu = new DataFilterMenu(this);
        mFilterMenu.setAfterFilterSelectedCallback(new NotifyFilterSelected() {
            @Override
            public void afterFilterSelected(DataFilterMenu.FilterItem selectedFilter) {
                LogUtil.d("afterFilterSelected was calling!");
                if (selectedFilter == null){
                    LogUtil.e("Can not do anything since selected filter is null!");
                    return;
                }
                if (selectedFilter.getFilterResultType() != DataFilterMenu.FILTER_TYPE_PATIENT
                        || selectedFilter.getResultString() == null || selectedFilter.getResultString().trim().isEmpty()){

                    LogUtil.e("Can not do anything since return select result is empty["
                            + (selectedFilter.getResultString()==null?"null":selectedFilter.getResultString())
                            + "] or result type[" + selectedFilter.getFilterResultType() + "] is not correct!");
                    return;
                }
                mPatientPannel.changePatient(selectedFilter.getResultString().trim());
                mTitleBar.changePatient(mPatientPannel.getCurrentPatient());
                setHisVitalSheetList(getVitalSheetsByPatient(mPatientPannel.getCurrentPatient()));
            }

            @Override
            public boolean beforeFilterSelected(DataFilterMenu.FilterItem selectedFilter) {
                LogUtil.d("beforeFilterSelected is calling and do nothing for this event!");
                return true;
            }
        });

        mNewVitalTextView = (TextView) findViewById(R.id.xin_jian_tz_tv);
        mNewVitalTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPatientPannel.getCurrentPatient() == null){
                    showToastShort("请先选择患者");
                    return;
                }
                showToastShort("收集体征");
                mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, mPatientPannel.getCurrentPatient());
                mBundle.remove(Constant.BUNDLE_KEY_VITAL_GROUP);
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SINGLE_PATIENT, false);
                goToActivity(VitalSignSheetActivity.class, mBundle);
            }
        });
        mTitleBar = new TitleBar(this, "收集体征");
        mTitleBar.setShaiXuanBehavior(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFilterMenu.setShowHide(true);
            }
        });
        mDrtzLv = (ListView) findViewById(R.id.hz_lstz_lv);
        duorenTizhengLvItemAdapter = new DuorenTizhengLvItemAdapter(this);
        duorenTizhengLvItemAdapter.setDan(false);
        if (duorenTizhengLvItemAdapter.getmVitalDefines() == null) {
            duorenTizhengLvItemAdapter.setVitalSignDefines(new VitalSignDaoImpl().findAllWardDefineFromDB());
        }
        mDrtzLv.setAdapter(duorenTizhengLvItemAdapter);
        mDrtzLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //showToastShort("条目"+position);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharePLogin sharePLogin = new SharePLogin(this);
        loadPatientByUserId(sharePLogin.getUserid());
        mFilterMenu.setPatients(mPatientPannel.getPatients());
        loadAllVitalSignSheet();
        if (mPatientPannel.getCurrentPatient() != null) {
            mTitleBar.changePatient(mPatientPannel.getCurrentPatient());
            setHisVitalSheetList(getVitalSheetsByPatient(mPatientPannel.getCurrentPatient()));
        }
        //loadVitalSheetsByPatients();
    }

    @Override
    public void afterPatientScanned(String patientID) {
        LogUtil.d("DRTZActivity.afterPatientScanned calling....");
        HuanZheLieBiaoBean.DataBean patient = DataConverter.convert(new HuanZheLieBiaoImpl().getDBHuanZheLieBiao(patientID));
        mPatientPannel.changePatient(patient);
        setHisVitalSheetList(getVitalSheetsByPatient(patient));
        mTitleBar.changePatient(patient);
    }

    private void loadPatientByUserId(String userId){ // load all patient in ward instead
        HuanZheLieBiaoInterface patientDao = new HuanZheLieBiaoImpl();
        mPatientPannel.setPatients(patientDao.getZhengXuListBean(DataConverter.convertHZs(patientDao.getListDBHuanZheLieBiao())));
//        int i=0;
//        for (HuanZheLieBiaoBean.DataBean patient : mPatientPannel.getPatients()) {
//            LogUtil.d("loadPatientByUserId>" + ++i + ". patientId:" + patient.getPatientId() + ",patientName:" + patient.getName() + ",bedNo:" + patient.getBedno());
//        }
    }
    private HuanZheLieBiaoBean.DataBean getPatientById(String patientId){
        HuanZheLieBiaoBean.DataBean retval = null;
        if (mPatientPannel.getPatients() != null) {
            for (HuanZheLieBiaoBean.DataBean patient : mPatientPannel.getPatients()) {
                if (patient.getPatientid().equals(patientId)) {
                    retval = patient;
                    break;
                }
            }
        }
        return retval;
    }

    private void loadAllVitalSignSheet() { // local all vital sign sheet from local db
        LogUtil.d("loadAllVitalSignSheet calling...");
        if (hisVitalSheetList == null) {
            hisVitalSheetList = new ArrayList<>();
        }else{
            hisVitalSheetList.clear();
        }
        hisVitalSheetList = new VitalSignDaoImpl().findAll("time", "desc");
        if (hisVitalSheetList != null && hisVitalSheetList.size() > 0){
            HuanZheLieBiaoBean.DataBean patient = null;
            for (VitalSignSheet sheet : hisVitalSheetList){
                if (sheet.getSignRecordList() != null){
                    LogUtil.d("time is " + sheet.getTime());
                     patient = getPatientById(sheet.getPatientid());
                    if (patient != null) {
                        sheet.setPatientName(patient.getName());
                        sheet.setBedNo(patient.getBedno() + "");
                    }
//                    for (VitalSignRecord record : sheet.getSignRecordList()){
//                        LogUtil.d("hisVitalSheetList.sheet[id:" + sheet.getId() + ",dbId:"
//                                + sheet.getDbId() + ",patientid:" + sheet.getPatientId() + "].record[id:"
//                                + record.getId() + "].value[" + record.getSignvalue() + "],signValue[" + record.getSignvalue() + "],note[" + record.getNote() + "].");
//                    }
                }
            }
        }
        refreshVitalList();
    }
    private void loadVitalSheetsByPatients(){ // local all vital sign sheet of patient from local db
        if (mPatientPannel.getPatients() == null) return;
        if (hisVitalSheetList == null) {
            hisVitalSheetList = new ArrayList<>();
        }else{
            hisVitalSheetList.clear();
        }
        List<VitalSignSheet> sheets = null;
        for(HuanZheLieBiaoBean.DataBean patient : mPatientPannel.getPatients()){
            sheets = getVitalSheetsByPatient(patient);
            if (sheets != null && sheets.size() > 0)  hisVitalSheetList.addAll(sheets);
        }
        refreshVitalList();
    }
    private List<VitalSignSheet> getVitalSheetsByPatient(HuanZheLieBiaoBean.DataBean patient){
        if (null == patient) return null;
        List<VitalSignSheet> sheets = new VitalSignDaoImpl().findVitalSignsFromDB(patient.getPatientid());
        if (null != sheets){
            for(VitalSignSheet sheet : sheets){
                sheet.setPatientName(patient.getName());
                sheet.setBedNo(patient.getBedno()+"");
            }
        }
        return sheets;
    }
    public void setHisVitalSheetList(List<VitalSignSheet> hisVitalSheetList) {
        this.hisVitalSheetList = hisVitalSheetList;
        refreshVitalList();
    }
    private void refreshVitalList(){
        if (this.hisVitalSheetList != null){
            HuanZheLieBiaoBean.DataBean patient = null;
            for(VitalSignSheet sheet : this.hisVitalSheetList){
                patient = getPatientById(sheet.getPatientid());
                if (patient != null)  sheet.setCareLevel(patient.getCarelevel());
            }
        }
        duorenTizhengLvItemAdapter.setVitalSigns(hisVitalSheetList);
        duorenTizhengLvItemAdapter.notifyDataSetChanged();
    }
    @Override
    public void initDatas() {

    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

}
