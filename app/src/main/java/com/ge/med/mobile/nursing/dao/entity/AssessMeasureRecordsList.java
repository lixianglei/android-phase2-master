package com.ge.med.mobile.nursing.dao.entity;

import java.io.Serializable;
import java.util.List;

/**
 *网络访问实体Bean
 * Created by xxl on 2017/4/17.
 */

public class AssessMeasureRecordsList implements Serializable{
    private List<AssessMeasureRecords> assessMeasureRecordsList;

    public List<AssessMeasureRecords> getAssessMeasureRecordsList() {
        return assessMeasureRecordsList;
    }

    public void setAssessMeasureRecordsList(List<AssessMeasureRecords> assessMeasureRecordsList) {
        this.assessMeasureRecordsList = assessMeasureRecordsList;
    }
}
