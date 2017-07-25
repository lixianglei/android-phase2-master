package com.ge.med.mobile.nursing.db;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignRecord;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignSheet;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qu on 2016/12/10.
 */
public class DBVitalSignSheet extends DataSupport implements IBaseDB{
    private int id;
    private Integer sheetId;
    private String status;
    private Integer userid;
    private String patientid;
    private String isdeleted;
    private String isModified;
    private Integer batchStatus;
    private String createdby;
    private String lastupdatedby;
    private String creationtime;
    private String time;
    private String lastupdatetime;
    private String comment;
    private String jsonString;
    private String mrnNo;

    public String getMrnNo() {
        return mrnNo;
    }

    public void setMrnNo(String mrnNo) {
        this.mrnNo = mrnNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSheetId() {
        return sheetId;
    }

    public void setSheetId(Integer sheetId) {
        this.sheetId = sheetId;
    }

    public Integer getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(Integer batchStatus) {
        this.batchStatus = batchStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(String lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public static List<DBVitalSignSheet> convertToDB(List<VitalSignSheet> sheetList){
        if (null == sheetList) {
            LogUtil.i("Cannot convert to DB since list of VitalSignSheet is null!");
            return null;
        }
        List<DBVitalSignSheet> retval = new ArrayList<>();
        DBVitalSignSheet dbSheet = null;
        for(VitalSignSheet sheet : sheetList){
            dbSheet = convertToDB(sheet);
            if (null != dbSheet) retval.add(dbSheet);
        }
        return retval;
    }
    public static DBVitalSignSheet convertToDB(VitalSignSheet sheet) {
        return convertToDB(sheet, false);
    }
    public static DBVitalSignSheet convertToDB(VitalSignSheet sheet, boolean isModified){
        if (null == sheet) {
            LogUtil.i("Cannot convert to DB since VitalSignSheet is null!");
            return null;
        }
        DBVitalSignSheet dbSheet = new DBVitalSignSheet();
        dbSheet.setModified(Boolean.valueOf(isModified).toString());
        if (sheet.getDbId() != null) dbSheet.setId(sheet.getDbId());
        dbSheet.setSheetId(sheet.getId());
        dbSheet.setStatus(sheet.getStatus());
        dbSheet.setCreatedby(sheet.getCreatedby());
        dbSheet.setCreationtime(sheet.getCreationtime());
        dbSheet.setIsdeleted(sheet.getIsdeleted());
        dbSheet.setLastupdatedby(sheet.getLastupdatedby());
        dbSheet.setLastupdatetime(sheet.getLastupdatetime());
        dbSheet.setBatchStatus(sheet.getBatchStatus());
        dbSheet.setComment(sheet.getComment());
        dbSheet.setPatientid(sheet.getPatientid());
        dbSheet.setTime(sheet.getTime());
        dbSheet.setUserid(sheet.getUserid());
        dbSheet.setMrnNo(sheet.getMrnNo());
        dbSheet.setJsonString(JSON.toJSONString(sheet, SerializerFeature
                .WriteNullListAsEmpty, SerializerFeature.DisableCircularReferenceDetect));
        LogUtil.d("convert to db from jsonString:" + dbSheet.getJsonString());
        return dbSheet;
    }

    public static List<VitalSignSheet> convertFromDB(List<DBVitalSignSheet> dbSheetList){
        if (null == dbSheetList) {
            LogUtil.i("Cannot convert from DB since list of DBVitalSignSheet is null!");
            return null;
        }
        List<VitalSignSheet> retval = new ArrayList<>();
        VitalSignSheet vitalSheet = null;
        for(DBVitalSignSheet dbSheet : dbSheetList){
            vitalSheet = convertFromDB(dbSheet);
            if (null != vitalSheet) retval.add(vitalSheet);
        }
        return retval;
    }
    public static VitalSignSheet convertFromDB(DBVitalSignSheet dbSheet){
        if (null == dbSheet) {
            LogUtil.i("Cannot convert from DB since DBVitalSignSheet is null!");
            return null;
        }
        VitalSignSheet vitalSheet = null;
        if (dbSheet.getJsonString() != null){
            LogUtil.d("convertFromDB>convert to vital sign from jsonstring:" + dbSheet.getJsonString());
            vitalSheet = JSON.parseObject(dbSheet.getJsonString(), VitalSignSheet.class);
            if (vitalSheet != null){
                vitalSheet.setDbId(dbSheet.getId());
                if (vitalSheet.getSignRecordList() != null || vitalSheet.getSignRecordList().size() <= 0){
                    for(VitalSignRecord record : vitalSheet.getSignRecordList()){
                        LogUtil.d("convertFromDB>VitalSignRecord.id[" + record.getId() + "],value["
                                + record.getSignvalue() + "],signType[" + record.getSigntype() + "],note[" + record.getNote() + "].");
                    }
                }else LogUtil.d("convertFromDB>No vital record found!");
            }else{
                LogUtil.e("convertFromDB>Can not convert json to vital sign!");
            }
        }else {
            vitalSheet = new VitalSignSheet();
            vitalSheet.setId(dbSheet.getSheetId());
            vitalSheet.setDbId(dbSheet.getId());
            vitalSheet.setStatus(dbSheet.getStatus());
            vitalSheet.setMrnNo(dbSheet.getMrnNo());
            vitalSheet.setCreatedby(dbSheet.getCreatedby());
            vitalSheet.setBatchStatus(dbSheet.getBatchStatus());
            vitalSheet.setComment(dbSheet.getComment());
            vitalSheet.setCreationtime(dbSheet.getCreationtime());
            vitalSheet.setIsdeleted(dbSheet.getIsdeleted());
            vitalSheet.setLastupdatedby(dbSheet.getLastupdatedby());
            vitalSheet.setLastupdatetime(dbSheet.getLastupdatetime());
            vitalSheet.setPatientid(dbSheet.getPatientid());
            vitalSheet.setTime(dbSheet.getTime());
            vitalSheet.setUserid(dbSheet.getUserid());
        }
        return vitalSheet;
    }

    public String isModified() {
        return isModified;
    }
    public String getIsModified() {
        return isModified;
    }

    public void setModified(String modified) {
        isModified = modified;
    }
    public void setIsModified(String modified) {
        isModified = modified;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
