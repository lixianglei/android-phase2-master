package com.ge.med.mobile.nursing.forjson.entity;

import java.util.List;

/**
 * Created by Qu on 2016/12/10.
 */
public class VitalSignDefineJSONBean implements BaseJSONBean{
    /**
     * status : 1
     * data : [{"id":1,"creationtime":null,"vitalDefine":{"id":1,"creationtime":null,"unitdesc":"°C","rangestart":0,"signname":"体温","rangeend":50,"lastupdatetime":null,"isdeleted":"","vitalNoteList":[{"id":4,"creationtime":null,"lastupdatetime":null,"vitaldefineid":1,"isdeleted":"","createdby":"","lastupdatedby":"","note":"物理降温"},{"id":2,"creationtime":null,"lastupdatetime":null,"vitaldefineid":1,"isdeleted":"","createdby":"","lastupdatedby":"","note":"腋温"},{"id":3,"creationtime":null,"lastupdatetime":null,"vitaldefineid":1,"isdeleted":"","createdby":"","lastupdatedby":"","note":"肛温"},{"id":1,"creationtime":null,"lastupdatetime":null,"vitaldefineid":1,"isdeleted":"","createdby":"","lastupdatedby":"","note":"口温"}],"createdby":"","lastupdatedby":""},"lastupdatetime":null,"vitaldefineid":1,"isdeleted":"","createdby":"","wardid":301,"lastupdatedby":""},{"id":2,"creationtime":null,"vitalDefine":{"id":2,"creationtime":null,"unitdesc":"bpm","rangestart":0,"signname":"脉搏","rangeend":200,"lastupdatetime":null,"isdeleted":"","vitalNoteList":[{"id":5,"creationtime":null,"lastupdatetime":null,"vitaldefineid":2,"isdeleted":"","createdby":"","lastupdatedby":"","note":"无"}],"createdby":"","lastupdatedby":""},"lastupdatetime":null,"vitaldefineid":2,"isdeleted":"","createdby":"","wardid":301,"lastupdatedby":""},{"id":3,"creationtime":null,"vitalDefine":{"id":3,"creationtime":null,"unitdesc":"分/次","rangestart":0,"signname":"呼吸","rangeend":100,"lastupdatetime":null,"isdeleted":"","vitalNoteList":[],"createdby":"","lastupdatedby":""},"lastupdatetime":null,"vitaldefineid":3,"isdeleted":"","createdby":"","wardid":301,"lastupdatedby":""},{"id":4,"creationtime":null,"vitalDefine":{"id":4,"creationtime":null,"unitdesc":"","rangestart":0,"signname":"心率","rangeend":200,"lastupdatetime":null,"isdeleted":"","vitalNoteList":[],"createdby":"","lastupdatedby":""},"lastupdatetime":null,"vitaldefineid":4,"isdeleted":"","createdby":"","wardid":301,"lastupdatedby":""},{"id":5,"creationtime":null,"vitalDefine":{"id":5,"creationtime":null,"unitdesc":"","rangestart":0,"signname":"收缩压","rangeend":300,"lastupdatetime":null,"isdeleted":"","vitalNoteList":[],"createdby":"","lastupdatedby":""},"lastupdatetime":null,"vitaldefineid":5,"isdeleted":"","createdby":"","wardid":301,"lastupdatedby":""},{"id":6,"creationtime":null,"vitalDefine":{"id":6,"creationtime":null,"unitdesc":"","rangestart":0,"signname":"舒张压","rangeend":300,"lastupdatetime":null,"isdeleted":"","vitalNoteList":[],"createdby":"","lastupdatedby":""},"lastupdatetime":null,"vitaldefineid":6,"isdeleted":"","createdby":"","wardid":301,"lastupdatedby":""},{"id":7,"creationtime":null,"vitalDefine":{"id":7,"creationtime":null,"unitdesc":"","rangestart":0,"signname":"体重","rangeend":1000,"lastupdatetime":null,"isdeleted":"","vitalNoteList":[],"createdby":"","lastupdatedby":""},"lastupdatetime":null,"vitaldefineid":7,"isdeleted":"","createdby":"","wardid":301,"lastupdatedby":""},{"id":8,"creationtime":null,"vitalDefine":{"id":8,"creationtime":null,"unitdesc":"","rangestart":null,"signname":"血氧饱和度","rangeend":null,"lastupdatetime":null,"isdeleted":"","vitalNoteList":[],"createdby":"","lastupdatedby":""},"lastupdatetime":null,"vitaldefineid":8,"isdeleted":"","createdby":"","wardid":301,"lastupdatedby":""}]
     * msg : 成功
     */
    private Integer status;
    private List<VitalSignWardDefine> data;
    private String msg;

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setData(List<VitalSignWardDefine> data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
@Override
    public Integer getStatus() {
        return status;
    }
@Override
    public List<VitalSignWardDefine> getData() {
        return data;
    }
@Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getCallName() {
        return "load Vital Sign Define";
    }
}
