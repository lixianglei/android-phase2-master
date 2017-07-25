package com.ge.med.mobile.nursing.dao.impl;

import android.content.ContentValues;

import com.ge.med.mobile.nursing.dao.HuanZheLieBiaoInterface;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.db.DBlabeList;
import com.ge.med.mobile.nursing.utils.DataConverter;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */
public class HuanZheLieBiaoImpl implements HuanZheLieBiaoInterface {
    @Override
    public List<DBHuanZheLieBiao> findPatientsByUserId(String userId) {
        return DataSupport.where("userid = ?", userId).find(DBHuanZheLieBiao.class);
    }

    @Override
    public DBHuanZheLieBiao getDBHuanZheLieBiao(String patient_id) {
        List<DBHuanZheLieBiao> dbHuanZheLieBiaos = DataSupport.where("patientid=?", patient_id).find(DBHuanZheLieBiao.class);
        return dbHuanZheLieBiaos.get(0);
    }

    @Override
    public List<DBHuanZheLieBiao> getListDBHuanZheLieBiao() {
        List<DBHuanZheLieBiao> dbHuanZheLieBiaos = DataSupport.findAll(DBHuanZheLieBiao.class);
        return dbHuanZheLieBiaos;
    }

    @Override
    public List<DBHuanZheLieBiao> getZhengXuList(List<DBHuanZheLieBiao> datas) {
        Comparator<DBHuanZheLieBiao> comparator = new Comparator<DBHuanZheLieBiao>() {
            public int compare(DBHuanZheLieBiao s1, DBHuanZheLieBiao s2) {
                return Integer.parseInt(s1.getBedno()) - Integer.parseInt(s2.getBedno());
            }
        };
        Collections.sort(datas, comparator);
        return datas;
    }

    @Override
    public List<HuanZheLieBiaoBean.DataBean> getZhengXuListBean(List<HuanZheLieBiaoBean.DataBean> datas) {
        if (datas == null) return new ArrayList<>();
        Comparator<HuanZheLieBiaoBean.DataBean> comparator = new Comparator<HuanZheLieBiaoBean.DataBean>() {
            public int compare(HuanZheLieBiaoBean.DataBean s1, HuanZheLieBiaoBean.DataBean s2) {
                return s1.getBedno()- s2.getBedno();
            }
        };
        Collections.sort(datas, comparator);
        return datas;
    }

    @Override
    public List<DBHuanZheLieBiao> getDaoXuList(List<DBHuanZheLieBiao> datas) {
        if (datas == null) return new ArrayList<>();
        Comparator<DBHuanZheLieBiao> comparator = new Comparator<DBHuanZheLieBiao>() {
            public int compare(DBHuanZheLieBiao s1, DBHuanZheLieBiao s2) {
                return Integer.parseInt(s2.getBedno()) - Integer.parseInt(s1.getBedno());
            }
        };
        Collections.sort(datas, comparator);
        return datas;

    }

    @Override
    public List<HuanZheLieBiaoBean.DataBean> getDaoXuListBean(List<HuanZheLieBiaoBean.DataBean> datas) {
        if (datas == null) return new ArrayList<>();
        Comparator<HuanZheLieBiaoBean.DataBean> comparator = new Comparator<HuanZheLieBiaoBean.DataBean>() {
            public int compare(HuanZheLieBiaoBean.DataBean s1, HuanZheLieBiaoBean.DataBean s2) {
                return s2.getBedno()- s1.getBedno();
            }
        };
        Collections.sort(datas, comparator);
        return datas;
    }

    @Override
    public List<HuanZheLieBiaoBean.DataBean> getDataBeanList(List<DBHuanZheLieBiao> datas) {
        List<HuanZheLieBiaoBean.DataBean> dataBeanList = new ArrayList<>();
        for (DBHuanZheLieBiao huanZheLieBiao : datas) {
            dataBeanList.add(getDataBean(huanZheLieBiao));
        }
        return dataBeanList;
    }
    public DBHuanZheLieBiao getDataBean(HuanZheLieBiaoBean.DataBean data){
        DBHuanZheLieBiao mDBHuanZheLieBiao = new DBHuanZheLieBiao();
        mDBHuanZheLieBiao.setAge(data.getAge() + "");
        mDBHuanZheLieBiao.setAllergyhistory(data.getAllergyhistory());
        mDBHuanZheLieBiao.setBedno(data.getBedno() + "");
        mDBHuanZheLieBiao.setBreathmethod(data.getBreathmethod());
        mDBHuanZheLieBiao.setCarelevel(data.getCarelevel());
        mDBHuanZheLieBiao.setCreatedby(data.getCreatedby());
        mDBHuanZheLieBiao.setCreationtime(data.getCreationtime());
        mDBHuanZheLieBiao.setDiagnosis(data.getDiagnosis());
        mDBHuanZheLieBiao.setDoctorid(data.getDoctorid() + "");
        mDBHuanZheLieBiao.setId(data.getId());
        mDBHuanZheLieBiao.setInhospitaltime(data.getInhospitaltime() + "");
        mDBHuanZheLieBiao.setName(data.getName());
        mDBHuanZheLieBiao.setWardid(data.getWardid() + "");
        HuanZheLieBiaoBean.DataBean.VoOrderStatusBean voOrderStatus = data.getVoOrderStatus();
        mDBHuanZheLieBiao.setNomoneystatus(data.getNomoneystatus());
        if(voOrderStatus!=null){
            mDBHuanZheLieBiao.setVoOrderStatus(voOrderStatus.getNoExeOrderCount() + "");
        }
        mDBHuanZheLieBiao.setSex(data.getSex());
        mDBHuanZheLieBiao.setPatientid(data.getPatientid());
        mDBHuanZheLieBiao.setLastupdatedby(data.getLastupdatedby());
        mDBHuanZheLieBiao.setLastupdatetime(data.getLastupdatetime() + "");
//            mDBHuanZheLieBiao.setPatientRiskList(data.getPatientRiskList());
        mDBHuanZheLieBiao.setUserid(data.getUserid());
        mDBHuanZheLieBiao.setIsdeleted(data.getIsdeleted());
        mDBHuanZheLieBiao.setNote(data.getNote());
        mDBHuanZheLieBiao.setOuthospitaltime(data.getOuthospitaltime() + "");
        mDBHuanZheLieBiao.setIlldetial(data.getIlldetial());
        mDBHuanZheLieBiao.setMrnno(data.getMrnno());
        mDBHuanZheLieBiao.setWandaicode(data.getWandaicode());
        mDBHuanZheLieBiao.setDoctorname(data.getDoctorname());
        return mDBHuanZheLieBiao;
    }
    public DBlabeList getDBlabeList(HuanZheLieBiaoBean.DataBean.LabelList data){
        DBlabeList dBlabeList = new DBlabeList();
        if(data!=null){
            dBlabeList .setShortname(data.getShortname());
            dBlabeList .setScore(data.getScore());
            dBlabeList .setRiskName(data.getRiskName());
            dBlabeList .setRiskId(data.getRiskId());
            dBlabeList .setPatientid(data.getPatientid());
            dBlabeList .setZid(data.getId());
        }
        return dBlabeList;
    }
    @Override
    public HuanZheLieBiaoBean.DataBean getDataBean(DBHuanZheLieBiao dbHuanZheLieBiao) {
        return DataConverter.convert(dbHuanZheLieBiao);
    }

    @Override
    public List<HuanZheLieBiaoBean.DataBean> loadPatientByUserId(String userId) {
        List<HuanZheLieBiaoBean.DataBean> patientList = new ArrayList<>();
        HuanZheLieBiaoImpl patienDao = new HuanZheLieBiaoImpl();
        List<DBHuanZheLieBiao> dbHuanZheLieBiaoList = patienDao.findPatientsByUserId(userId);
        if (dbHuanZheLieBiaoList != null){
            for (DBHuanZheLieBiao dbHuanZheLieBiao : dbHuanZheLieBiaoList){
                patientList.add(patienDao.getDataBean(dbHuanZheLieBiao));
            }
        }
        return getZhengXuListBean(patientList);
    }

    @Override
    public void updatePatientAssessResult(HuanZheLieBiaoBean.DataBean patient) {
        if (patient == null || patient.getPatientid() == null){
            LogUtil.e("Can not update patient assess result in local DB since patient or its patient id is null!");
            return;
        }

        ContentValues values = new ContentValues();

//        values.put("assessresut", patient.getAssessresut());//标签字段改变 待改变
        DataSupport.updateAll(DBHuanZheLieBiao.class, values, "patientid = ?", patient.getPatientid());
        LogUtil.d("Update assess result[] for patient[" + patient.getPatientid() + "] successfully!");
    }

    @Override
    public void savePatients(List<HuanZheLieBiaoBean.DataBean> datas) {
        List<DBHuanZheLieBiao> lst = null;
        DBHuanZheLieBiao mDBHuanZheLieBiao = null;
        DBlabeList dBlabeList = null;
        List<DBlabeList> dBlabeLists = null;
        for (HuanZheLieBiaoBean.DataBean data : datas) {
            mDBHuanZheLieBiao = getDataBean(data);
            if(data!=null && data.getLabelList()!=null && data.getLabelList().size()>0){
              for(HuanZheLieBiaoBean.DataBean.LabelList labelList:data.getLabelList()){
                  dBlabeList = getDBlabeList(labelList);
                  dBlabeLists = DataSupport.where("zid = ?", labelList.getId() + "").find(DBlabeList.class);
                  if (dBlabeLists != null && dBlabeLists.size() > 0) {
                      dBlabeList.updateAll("zid = ?", labelList.getId()+"");
                  }else{
                      boolean save = dBlabeList.save();
                  }
              }
            }
            lst = DataSupport.where("patientid = ?", data.getPatientid()).find(DBHuanZheLieBiao.class);
            if (lst != null && lst.size() > 0) {
                mDBHuanZheLieBiao.updateAll("patientid = ?", data.getPatientid());
                LogUtil.d("Update patient to DB for patient[id:" + data.getPatientid() + ",name:" + data.getName());
            }else{
                boolean save = mDBHuanZheLieBiao.save();
                if (save){
                    LogUtil.d("Insert patient to DB successfully for patient[id:" + data.getPatientid() + ",name:" + data.getName());
                }else{
                    LogUtil.e("Insert patient to DB failed for patient[id:" + data.getPatientid() + ",name:" + data.getName());
                }
            }

        }
    }
}
