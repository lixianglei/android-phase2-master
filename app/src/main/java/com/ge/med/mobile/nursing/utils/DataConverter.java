package com.ge.med.mobile.nursing.utils;

import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.db.DBUserList;
import com.ge.med.mobile.nursing.db.DBlabeList;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qu on 2016/12/3.
 */
public class DataConverter {
    public static String getUserName(String userId){
        String userName = "";
        if(userId!=null){
            DBUserList first = DataSupport.where("zid = ?", userId).findFirst(DBUserList.class);
            if(first!=null){
                userName = first.getName();
            }
        }
        return  userName;
    }

    public static String getFormalFloat(String value){
        String retval = null;
        if (value != null) {
            try {
                Float val = Float.parseFloat(value);
                if (val % 1.0 != 0) retval = String.format("%s", val);
                else retval = String.format("%.0f",val);
            } catch (NumberFormatException e) {
                retval = "0";
                LogUtil.e("vital value[" + value + "] is not in number format! set it to zero");
            }
        }
        return retval;
    }

    public static List<HuanZheLieBiaoBean.DataBean> convertHZs(List<DBHuanZheLieBiao> from) {
        if (null == from || from.size() <= 0) return null;
        List<HuanZheLieBiaoBean.DataBean> retval = new ArrayList();
        for (DBHuanZheLieBiao data : from) {
            retval.add(convert(data));
        }
        return retval;
    }

    public static HuanZheLieBiaoBean.DataBean convert(DBHuanZheLieBiao from) {
        HuanZheLieBiaoBean.DataBean bean = new HuanZheLieBiaoBean.DataBean();
        HuanZheLieBiaoBean.DataBean.LabelList labelList;
        List<HuanZheLieBiaoBean.DataBean.LabelList> labelLists = new ArrayList<>();
        List<DBlabeList> dBlabeLists = DataSupport.where("patientid = ?", from.getPatientid()).find(DBlabeList.class);
        if(dBlabeLists != null && dBlabeLists.size()>0){
            for(DBlabeList dBlabeList:dBlabeLists){
                labelList= new HuanZheLieBiaoBean.DataBean.LabelList();
                labelList.setId(dBlabeList.getZid());
                labelList.setPatientid(dBlabeList.getPatientid());
                labelList.setRiskId(dBlabeList.getRiskId());
                labelList.setRiskName(dBlabeList.getRiskName());
                labelList.setScore(dBlabeList.getScore());
                labelList.setShortname(dBlabeList.getShortname());
                labelLists.add(labelList);
            }
        }
        bean.setLabelList(labelLists);
        bean.setDiagnosis(from.getDiagnosis());
        bean.setAllergyhistory(from.getAllergyhistory());
        bean.setDoctorid(from.getDoctorid());
        bean.setDoctorname(from.getDoctorname());
        bean.setPatientid(from.getPatientid());
        int age = 0, bedno = 0;
        try {
            age = Integer.parseInt(from.getAge());
        } catch (Exception e) {
            age = 0;
        }
        bean.setAge(age);
        try {
            bedno = Integer.parseInt(from.getBedno());
        } catch (Exception e) {
            bedno = 0;
        }
        bean.setBedno(bedno);
        bean.setBreathmethod(from.getBreathmethod());
        bean.setCarelevel(from.getCarelevel());
        bean.setId(from.getId());
        bean.setInhospitaltime(from.getInhospitaltime());
        bean.setIsdeleted(from.getIsdeleted());
        bean.setName(from.getName());
        bean.setNomoneystatus(from.getNomoneystatus());
        bean.setNote(from.getNote());
        bean.setOuthospitaltime(from.getOuthospitaltime());
        bean.setSex(from.getSex());
        bean.setUserid(from.getUserid());
        bean.setWardid(from.getWardid());
        bean.setIlldetial(from.getIlldetial());
        bean.setMrnno(from.getMrnno());
        bean.setWandaicode(from.getWandaicode());
        bean.setCreatedby(from.getCreatedby());
        bean.setCreationtime(from.getCreationtime());
        bean.setDiagnosis(from.getDiagnosis());
        bean.setDoctorid(from.getDoctorid());
        bean.setLastupdatedby(from.getLastupdatedby());
        bean.setLastupdatetime(from.getLastupdatetime());
        bean.setOuthospitaltime(from.getOuthospitaltime());
        bean.setPatientid(from.getPatientid());
        bean.setUserid(from.getUserid());
        try {
            HuanZheLieBiaoBean.DataBean.VoOrderStatusBean voOrderStatusBean = new HuanZheLieBiaoBean.DataBean.VoOrderStatusBean();
            voOrderStatusBean.setNoExeOrderCount(Integer.parseInt(from.getVoOrderStatus()));
            bean.setVoOrderStatus(voOrderStatusBean);
        } catch (NumberFormatException e) {
            LogUtil.i("Patient[id:" + from.getPatientid() + ",name:" + from.getName() + "] VoOrderStatus is null!");
        }
        bean.setDoctorname(from.getDoctorname());
        bean.setMrnno(from.getMrnno());
        bean.setWandaicode(from.getWandaicode());
        return bean;
    }
}
