package com.ge.med.mobile.nursing.forjson.entity;

import org.xutils.common.util.LogUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Qu on 2016/12/9.
 */
public class VitalSignSheetMini implements Serializable {
    private Integer batchStatus;
    private String status;
    private Integer userid;
    private List<VitalSignRecordMini> signRecordList;
    private String createdby;
    private String lastupdatedby;
    private String creationtime;
    private Integer id;
    private String time;
    private String lastupdatetime;
    private String patientid;
    private String isdeleted;
    private String comment;
    private UserEntity user;
    private String mrnNo;



    // display only
    private String careLevel;

    public VitalSignSheetMini(VitalSignSheet sheet){
        this.setPatientid(sheet.getPatientid());
        this.setIsdeleted(sheet.getIsdeleted());
        this.setCreatedby(sheet.getCreatedby());
        this.setCareLevel(sheet.getCareLevel());
        this.setComment(sheet.getComment());
        this.setCreationtime(sheet.getCreationtime());
        this.setBatchStatus(sheet.getBatchStatus());
        this.setId(sheet.getId());
        this.setMrnNo(sheet.getMrnNo());
        this.setLastupdatedby(sheet.getLastupdatedby());
        this.setLastupdatetime(sheet.getLastupdatetime());
        if (sheet.getSignRecordList() != null) {
            List<VitalSignRecordMini> lst = new ArrayList<>();
            for(VitalSignRecord rec : sheet.getSignRecordList()){
                lst.add(new VitalSignRecordMini(rec));
            }
            this.setSignRecordList(lst);
        }
        this.setStatus(sheet.getStatus());
        if (sheet.getTime() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                this.setTime(format.format(new Date(Long.parseLong(sheet.getTime()))));
            } catch (Exception e) {
                LogUtil.e("Can not format time to yyyy-MM-dd HH:mm:ss! err is " + e.getMessage());
            }
        }
        this.setUser(sheet.getUser());
        this.setUserid(sheet.getUserid());
    }

    public void setBatchStatus(Integer batchStatus) {
        this.batchStatus = batchStatus;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setSignRecordList(List<VitalSignRecordMini> signRecordList) {
        this.signRecordList = signRecordList;
    }
    public String getMrnNo() {
        return mrnNo;
    }

    public void setMrnNo(String mrnNo) {
        this.mrnNo = mrnNo;
    }
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLastupdatetime(String lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getBatchStatus() {
        return batchStatus;
    }

    public String getStatus() {
        return status;
    }

    public Integer getUserid() {
        return userid;
    }

    public List<VitalSignRecordMini> getSignRecordList() {
        return signRecordList;
    }

    public String getCreatedby() {
        return createdby;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public String getCreationtime() {
        return creationtime;
    }

    public Integer getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getLastupdatetime() {
        return lastupdatetime;
    }

    public String getPatientid() {
        return patientid;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public String getComment() {
        return comment;
    }

    public UserEntity getUser() {
        return user;
    }

    public String getCareLevel() {
        return careLevel;
    }

    public void setCareLevel(String careLevel) {
        this.careLevel = careLevel;
    }
}