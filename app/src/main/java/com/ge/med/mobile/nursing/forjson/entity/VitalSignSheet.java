package com.ge.med.mobile.nursing.forjson.entity;

import org.xutils.common.util.LogUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Qu on 2016/12/9.
 */
public class VitalSignSheet implements Serializable, IBaseBean {
    /**
     * batchStatus : 0
     * status : 1
     * userid : 3
     * signRecordList : [{"execrecordid":null,"signvalue":"37.5","vitalDefine":{"id":1,"creationtime":null,"unitdesc":"","rangestart":0,"signname":"体温","rangeend":50,"lastupdatetime":null,"isdeleted":"","vitalNoteList":null,"createdby":"","lastupdatedby":""},"patientInHospitalStatus":"","userid":3,"createdby":"系统1","type":"","recordTime":null,"standardVitalSignType":"","lastupdatedby":"","creationtime":1479805512570,"id":1251,"chartText":"","lastupdatetime":null,"executeName":"","isdeleted":"","value":null,"sheetid":1104,"savestatus":"1","note":"体征备注1","signtype":1},{"execrecordid":null,"signvalue":"99","vitalDefine":{"id":2,"creationtime":null,"unitdesc":"","rangestart":0,"signname":"脉搏","rangeend":200,"lastupdatetime":null,"isdeleted":"","vitalNoteList":null,"createdby":"","lastupdatedby":""},"patientInHospitalStatus":"","userid":3,"createdby":"系统2","type":"","recordTime":null,"standardVitalSignType":"","lastupdatedby":"","creationtime":1479805512570,"id":1252,"chartText":"","lastupdatetime":null,"executeName":"","isdeleted":"","value":null,"sheetid":1104,"savestatus":"1","note":"体征备注2","signtype":2},{"execrecordid":null,"signvalue":"20","vitalDefine":{"id":3,"creationtime":null,"unitdesc":"","rangestart":0,"signname":"呼吸","rangeend":100,"lastupdatetime":null,"isdeleted":"","vitalNoteList":null,"createdby":"","lastupdatedby":""},"patientInHospitalStatus":"","userid":3,"createdby":"系统3","type":"","recordTime":null,"standardVitalSignType":"","lastupdatedby":"","creationtime":1479805512570,"id":1253,"chartText":"","lastupdatetime":null,"executeName":"","isdeleted":"","value":null,"sheetid":1104,"savestatus":"1","note":"体征备注3","signtype":3}]
     * createdby : 系统1
     * lastupdatedby :
     * creationtime : 1478937400000
     * id : 1104
     * time : 1476518200000
     * lastupdatetime : null
     * patientid : A100233_8
     * isdeleted :
     * comment : 第一条备注
     * user : {"empno":"","rOLE_TYPE_NURSE":"0","createdby":"","userPatientList":null,"lastupdatedby":"","password":"","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE_LEADER":"1","creationtime":null,"id":null,"lastupdatetime":null,"isdeleted":"","name":"晨晨","rOLE_TYPE_ADMIN":"9","wardid":null,"roletype":"","patientList":null}
     */
    private Integer batchStatus;
    private String status;
    private Integer userid;
    private List<VitalSignRecord> signRecordList;
    private String createdby;
    private String lastupdatedby;
    private String creationtime;
    private Integer id;
    private Integer dbId;
    private String time;
    private String lastupdatetime;
    private String patientid;
    private String bedNo;
    private String patientName;
    private String isdeleted;
    private String comment;
    private UserEntity user;
    // display only
    private String careLevel;
    private String mrnNo;

    public String getMrnNo() {
        return mrnNo;
    }

    public void setMrnNo(String mrnNo) {
        this.mrnNo = mrnNo;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
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

    public void setSignRecordList(List<VitalSignRecord> signRecordList) {
        this.signRecordList = signRecordList;
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

    public List<VitalSignRecord> getSignRecordList() {
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

    public Integer getDbId() {
        return dbId;
    }
    @Override
    public void setDbId(Integer dbId) {
        this.dbId = dbId;
    }

    public static List<VitalSignSheet> sortByTimeDesc(List<VitalSignSheet> lst){
        List<VitalSignSheet> retval = new ArrayList<>();
        if (lst != null && lst.size() > 0){
            Comparator<VitalSignSheet> comparator = new Comparator<VitalSignSheet>() {
                public int compare(VitalSignSheet s1, VitalSignSheet s2) {
                    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    long t1 = 0, t2 = 0;
                    try {
                        t1 = Long.parseLong(s1.getTime());
                    } catch (NumberFormatException e) {
                        LogUtil.e("Time[" + s1.getTime() + "] format is wrong, cannot sort!");
                    }
                    try {
                        t2 = Long.parseLong(s2.getTime());
                    } catch (NumberFormatException e) {
                        LogUtil.e("Time[" + s2.getTime() + "] format is wrong, cannot sort!");
                    }
                    LogUtil.d("sort: t1[" + s1.getTime() + "] to [" + t1 + "], t2 [" + s2.getTime() + "] to [" + t2 + "].");
                    if (t1 < t2) {
                        return 1;
                    } else if (t2 == t1) {
                        return 0;
                    }
                    return -1;
                }
            };
            Collections.sort(lst, comparator);
            retval = lst;
        }
        return retval;
    }
}