package com.ge.med.mobile.nursing.forjson.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Qu on 2016/12/10.
 */
public class VitalDefineEntity implements Serializable {

    private Integer id;
    private Integer defaultvitalnoteid;
    private Integer inouttype;
    private String unitdesc;
    private Integer rangestart;
    private String signname;
    private Integer rangeend;
    private List<VitalNoteListEntity> vitalNoteList;
    private String inputtype;
    private String isnumber;
    private String isnotemandatory;
    private Integer position;
    private String vcode;//判断疼痛级别 V021

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUnitdesc(String unitdesc) {
        this.unitdesc = unitdesc;
    }

    public void setRangestart(Integer rangestart) {
        this.rangestart = rangestart;
    }

    public void setSignname(String signname) {
        this.signname = signname;
    }

    public void setRangeend(Integer rangeend) {
        this.rangeend = rangeend;
    }

    public void setVitalNoteList(List<VitalNoteListEntity> vitalNoteList) {
        this.vitalNoteList = vitalNoteList;
    }

    public Integer getId() {
        return id;
    }

    public String getUnitdesc() {
        return unitdesc;
    }

    public Integer getRangestart() {
        return rangestart;
    }

    public String getSignname() {
        return signname;
    }

    public Integer getRangeend() {
        return rangeend;
    }

    public List<VitalNoteListEntity> getVitalNoteList() {
        return vitalNoteList;
    }

    public String getInputtype() {
        return inputtype;
    }

    public void setInputtype(String inputtype) {
        this.inputtype = inputtype;
    }

    public String getIsnumber() {
        return isnumber;
    }

    public void setIsnumber(String isnumber) {
        this.isnumber = isnumber;
    }

    public String getIsnotemandatory() {
        return isnotemandatory;
    }

    public void setIsnotemandatory(String isnotemandatory) {
        this.isnotemandatory = isnotemandatory;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getDefaultvitalnoteid() {
        return defaultvitalnoteid;
    }

    public void setDefaultvitalnoteid(Integer defaultvitalnoteid) {
        this.defaultvitalnoteid = defaultvitalnoteid;
    }

    public Integer getInouttype() {
        return inouttype;
    }

    public void setInouttype(Integer inouttype) {
        this.inouttype = inouttype;
    }
}
