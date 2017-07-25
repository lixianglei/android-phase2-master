package com.ge.med.mobile.nursing.forjson.entity;

/**
 * 获取评估记录
 * Created by Administrator on 2016/10/27.
 */
public class AssessJSONBean implements BaseJSONBean {
    /**
     * status : 1
     * data : {"assessName":"","assessdefineid":1,"createdby":"系统","creationtime":1480821458935,"executeUserName":"","id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"AA01","result":"","score":45,"status":"","topicRecordList":[{"assessid":1,"createdby":"系统","creationtime":1480821582103,"id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"record":[],"topicdefineid":1,"value":""},{"assessid":1,"createdby":"系统","creationtime":1480821583952,"id":2,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"record":[{"answerdefineid":2,"answerflag":"","id":2,"toprecordid":2}],"topicdefineid":2,"value":""},{"assessid":1,"createdby":"系统","creationtime":1480821585887,"id":3,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"record":[{"answerdefineid":5,"answerflag":"","id":3,"toprecordid":3}],"topicdefineid":3,"value":""},{"assessid":1,"createdby":"系统","creationtime":1480821587302,"id":4,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"record":[{"answerdefineid":8,"answerflag":"","id":4,"toprecordid":4}],"topicdefineid":4,"value":""},{"assessid":1,"createdby":"系统","creationtime":1480821593077,"id":5,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"record":[],"topicdefineid":5,"value":""}],"userid":1}
     * msg : 成功
     */

    private Integer status;
    /**
     * assessName :
     * assessdefineid : 1
     * createdby : 系统
     * creationtime : 1480821458935
     * executeUserName :
     * id : 1
     * isdeleted : 0
     * lastupdatedby :
     * lastupdatetime : null
     * patientid : AA01
     * result :
     * score : 45
     * status :
     * topicRecordList : [{"assessid":1,"createdby":"系统","creationtime":1480821582103,"id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"record":[],"topicdefineid":1,"value":""},{"assessid":1,"createdby":"系统","creationtime":1480821583952,"id":2,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"record":[{"answerdefineid":2,"answerflag":"","id":2,"toprecordid":2}],"topicdefineid":2,"value":""},{"assessid":1,"createdby":"系统","creationtime":1480821585887,"id":3,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"record":[{"answerdefineid":5,"answerflag":"","id":3,"toprecordid":3}],"topicdefineid":3,"value":""},{"assessid":1,"createdby":"系统","creationtime":1480821587302,"id":4,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"record":[{"answerdefineid":8,"answerflag":"","id":4,"toprecordid":4}],"topicdefineid":4,"value":""},{"assessid":1,"createdby":"系统","creationtime":1480821593077,"id":5,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"record":[],"topicdefineid":5,"value":""}]
     * userid : 1
     */

    private AssessRecordBean data;
    private String msg;

    @Override
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public AssessRecordBean getData() {
        return data;
    }

    public void setData(AssessRecordBean data) {
        this.data = data;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getCallName() {
        return "load Assess Record";
    }
}
