package com.ge.med.mobile.nursing.forjson.entity;

import org.xutils.common.util.LogUtil;

import java.io.Serializable;

/**
 * Created by Qu on 2016/12/10.
 */
public class VitalSignRecordMini implements Serializable {
    public VitalSignRecordMini(VitalSignRecord record){
        this.setLastupdatedby(record.getLastupdatedby());
        this.setId(record.getId());
        this.setUserid(record.getUserid());
        this.setIsdeleted(record.getIsdeleted());
        this.setCreatedby(record.getCreatedby());
        try {
            this.setCreationtime(Long.parseLong(record.getCreationtime()));
        } catch (Exception e) {
            LogUtil.i("Create time cannot parse to long:error is :" + e.getMessage());
        }
        try {
            this.setExecrecordid(Integer.parseInt(record.getExecrecordid()));
        } catch (NumberFormatException e) {
            LogUtil.i("Exec record id cannot parse to int:error is :" + e.getMessage());
        }
        try {
            this.setLastupdatetime(Long.parseLong(record.getLastupdatetime()));
        } catch (NumberFormatException e) {
            LogUtil.i("Last update time cannot parse to long:error is :" + e.getMessage());
        }
        this.setNote(record.getNote());
        this.setSavestatus("1");
        this.setSheetid(record.getSheetid());
        this.setSigntype(record.getSigntype());
        this.setSignvalue(record.getSignvalue());
        this.setHandlemeasure(record.getHandlemeasure());
        //this.setSignvalue(record.getSignvalue());
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSignvalue() {
        return signvalue;
    }

    public void setSignvalue(String signvalue) {
        this.signvalue = signvalue;
    }

    public Integer getExecrecordid() {
        return execrecordid;
    }

    public void setExecrecordid(Integer execrecordid) {
        this.execrecordid = execrecordid;
    }

    public Integer getSheetid() {
        return sheetid;
    }

    public void setSheetid(Integer sheetid) {
        this.sheetid = sheetid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getSigntype() {
        return signtype;
    }

    public void setSigntype(Integer signtype) {
        this.signtype = signtype;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Long getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Long creationtime) {
        this.creationtime = creationtime;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Long getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Long lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getSavestatus() {
        return savestatus;
    }

    public void setSavestatus(String savestatus) {
        this.savestatus = savestatus;
    }

    public String getHandlemeasure() {
        return handlemeasure;
    }

    public void setHandlemeasure(String handlemeasure) {
        this.handlemeasure = handlemeasure;
    }

    private Integer id;

    private Integer userid;

    private String signvalue;

    private Integer execrecordid;

    private Integer sheetid;

    private String note;

    private Integer signtype;

    private String createdby;

    private Long creationtime;

    private String lastupdatedby;

    private Long lastupdatetime;

    private String isdeleted;

    private String savestatus;
    private String handlemeasure;
}
