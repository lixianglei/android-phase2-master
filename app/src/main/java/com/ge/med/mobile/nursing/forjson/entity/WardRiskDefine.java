package com.ge.med.mobile.nursing.forjson.entity;

/**
 * Created by Qu on 2016/12/17.
 */
public class WardRiskDefine {
    private String id;
    private String creationtime;
    private String riskname;
    private String lastupdatetime;
    private int riskid;
    private String isdeleted;
    private String createdby;
    private String wardid;
    private String lastupdatedby;
    private int risksortno;
    private String shortname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }

    public String getRiskname() {
        return riskname;
    }

    public void setRiskname(String riskname) {
        this.riskname = riskname;
    }

    public String getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(String lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public int getRiskid() {
        return riskid;
    }

    public void setRiskid(int riskid) {
        this.riskid = riskid;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getWardid() {
        return wardid;
    }

    public void setWardid(String wardid) {
        this.wardid = wardid;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public int getRisksortno() {
        return risksortno;
    }

    public void setRisksortno(int risksortno) {
        this.risksortno = risksortno;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
}
