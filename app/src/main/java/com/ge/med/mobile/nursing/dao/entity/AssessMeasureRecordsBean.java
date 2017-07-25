package com.ge.med.mobile.nursing.dao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 网络访问实体Bean
 * Created by xxl on 2017/4/16.
 */

public class AssessMeasureRecordsBean implements Serializable{

    /**
     * msg : 成功
     * data : [{"assessMeasureDefineId":1,"assessRecordId":1,"createdBy":"","creationTime":1492072407000,"id":1,"isDeleted":0,"lastUpdateBy":"","lastUpdateTime":1492072407000,"measureTopicRecordList":[{"id":1,"measureAnswerRecordList":[{"id":1,"measureAnswerDefineId":1,"measureTopicRecordId":1,"note":""},{"id":41,"measureAnswerDefineId":1,"measureTopicRecordId":1,"note":""}],"measureAssessRecordId":null,"measureTopicDefineId":1,"sortNo":1,"topicType":""},{"id":2,"measureAnswerRecordList":[{"id":2,"measureAnswerDefineId":2,"measureTopicRecordId":2,"note":""},{"id":42,"measureAnswerDefineId":2,"measureTopicRecordId":2,"note":""}],"measureAssessRecordId":null,"measureTopicDefineId":2,"sortNo":2,"topicType":""},{"id":3,"measureAnswerRecordList":[{"id":3,"measureAnswerDefineId":3,"measureTopicRecordId":3,"note":""},{"id":43,"measureAnswerDefineId":3,"measureTopicRecordId":3,"note":""}],"measureAssessRecordId":null,"measureTopicDefineId":3,"sortNo":3,"topicType":""}],"patientId":"AA1063","status":1,"time":1492072407000,"userId":3}]
     * status : 1
     */

    private String msg;
    private int status;
    private List<AssessMeasureRecords> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<AssessMeasureRecords> getData() {
        return data;
    }

    public void setData(List<AssessMeasureRecords> data) {
        this.data = data;
    }

}
