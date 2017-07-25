package com.ge.med.mobile.nursing.db;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.dao.entity.AssessMeasureRecords;

import org.litepal.crud.DataSupport;

/**
 * Created by xxl on 2017/4/16.
 * 患者评估测量记录
 */

public class DBAssessMeasureRecords extends DataSupport implements IBaseDB{
    private String assessMeasureDefineId;
    private int assessRecordId;
    private String patientId;
    private String jsonString;
    private String isModified;
    private int id;

    public String getIsModified() {
        return isModified;
    }

    public void setIsModified(String isModified) {
        this.isModified = isModified;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public static DBAssessMeasureRecords convertBean(AssessMeasureRecords assessMeasureRecords) {
        DBAssessMeasureRecords dbAssessMeasureRecords = new DBAssessMeasureRecords();
        dbAssessMeasureRecords.setAssessRecordId(assessMeasureRecords.getAssessRecordId());
        dbAssessMeasureRecords.setAssessMeasureDefineId(assessMeasureRecords.getAssessMeasureDefineId()+"");
        dbAssessMeasureRecords.setPatientId(assessMeasureRecords.getPatientId());
        String s = JSON.toJSONString(assessMeasureRecords);
        dbAssessMeasureRecords.setJsonString(s);
        return dbAssessMeasureRecords;
    }

    public static AssessMeasureRecords convertBean(DBAssessMeasureRecords dbAssessMeasureRecords) {
        if (dbAssessMeasureRecords == null || dbAssessMeasureRecords.getJsonString() == null) {
            return null;
        }
        return JSON.parseObject(dbAssessMeasureRecords.getJsonString(), AssessMeasureRecords.class);
    }

    public String getAssessMeasureDefineId() {
        return assessMeasureDefineId;
    }

    public void setAssessMeasureDefineId(String assessMeasureDefineId) {
        this.assessMeasureDefineId = assessMeasureDefineId;
    }

    public int getAssessRecordId() {
        return assessRecordId;
    }

    public void setAssessRecordId(int assessRecordId) {
        this.assessRecordId = assessRecordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getJsonString() {
        return jsonString;
    }


    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
