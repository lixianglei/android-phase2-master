package com.ge.med.mobile.nursing.db;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by xxl on 2017/4/12.
 */

public class DBlabeList extends DataSupport implements Serializable {
    private int zid;
    private String patientid;
    private String riskId;
    private String riskName;
    private String score;
    private String shortname;

    public int getZid() {
        return zid;
    }

    public void setZid(int zid) {
        this.zid = zid;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
}
