package com.ge.med.mobile.nursing.forjson.entity;

import java.io.Serializable;

/**
 * Created by Qu on 2016/12/10.
 */
public class VitalSignRecord implements Serializable {
    /**
     * execrecordid : null
     * signvalue : 37.5
     * vitalDefine : {"id":1,"creationtime":null,"unitdesc":"","rangestart":0,"signname":"体温","rangeend":50,"lastupdatetime":null,"isdeleted":"","vitalNoteList":null,"createdby":"","lastupdatedby":""}
     * patientInHospitalStatus :
     * userid : 3
     * createdby : 系统1
     * type :
     * recordTime : null
     * standardVitalSignType :
     * lastupdatedby :
     * creationtime : 1479805512570
     * id : 1251
     * chartText :
     * lastupdatetime : null
     * executeName :
     * isdeleted :
     * value : null
     * sheetid : 1104
     * savestatus : 1
     * note : 体征备注1
     * signtype : 1
     */
    private String execrecordid;
    private String signvalue;
    private String note;
    private VitalDefineEntity vitalDefine;
    private String patientInHospitalStatus;
    private Integer userid;
    private String createdby;
    private String type;
    private String recordTime;
    private String standardVitalSignType;
    private String lastupdatedby;
    private String creationtime;
    private Integer id;
    private String chartText;
    private String lastupdatetime;
    private String executeName;
    private String isdeleted;
    private String handlemeasure;

    public String getHandlemeasure() {
        return handlemeasure;
    }

    public void setHandlemeasure(String handleMeasure) {
        this.handlemeasure = handleMeasure;
    }

    private Integer sheetid;
    private String savestatus;

    private Integer signtype;

    public void setExecrecordid(String execrecordid) {
        this.execrecordid = execrecordid;
    }

    public void setSignvalue(String signvalue) {
        this.signvalue = signvalue;
    }

    public void setVitalDefine(VitalDefineEntity vitalDefine) {
        this.vitalDefine = vitalDefine;
    }

    public void setPatientInHospitalStatus(String patientInHospitalStatus) {
        this.patientInHospitalStatus = patientInHospitalStatus;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public void setStandardVitalSignType(String standardVitalSignType) {
        this.standardVitalSignType = standardVitalSignType;
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

    public void setChartText(String chartText) {
        this.chartText = chartText;
    }

    public void setLastupdatetime(String lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public void setExecuteName(String executeName) {
        this.executeName = executeName;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }


    public void setSheetid(Integer sheetid) {
        this.sheetid = sheetid;
    }

    public void setSavestatus(String savestatus) {
        this.savestatus = savestatus;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setSigntype(Integer signtype) {
        this.signtype = signtype;
    }

    public String getExecrecordid() {
        return execrecordid;
    }

    public String getSignvalue() {
        return signvalue;
    }

    public VitalDefineEntity getVitalDefine() {
        return vitalDefine;
    }

    public String getPatientInHospitalStatus() {
        return patientInHospitalStatus;
    }

    public Integer getUserid() {
        return userid;
    }

    public String getCreatedby() {
        return createdby;
    }

    public String getType() {
        return type;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public String getStandardVitalSignType() {
        return standardVitalSignType;
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

    public String getChartText() {
        return chartText;
    }

    public String getLastupdatetime() {
        return lastupdatetime;
    }

    public String getExecuteName() {
        return executeName;
    }

    public String getIsdeleted() {
        return isdeleted;
    }


    public Integer getSheetid() {
        return sheetid;
    }

    public String getSavestatus() {
        return savestatus;
    }

    public String getNote() {
        return note;
    }

    public Integer getSigntype() {
        return signtype;
    }
}
