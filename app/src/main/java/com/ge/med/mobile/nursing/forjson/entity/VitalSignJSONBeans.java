package com.ge.med.mobile.nursing.forjson.entity;

import java.util.List;

/**
 * Created by Qu on 2016/12/9.
 */
public class VitalSignJSONBeans implements BaseJSONBean{


    /**
     * status : 1
     * data : [{"batchStatus":0,"status":"1","userid":3,"signRecordList":[{"execrecordid":null,"signvalue":"37.5","vitalDefine":{"id":1,"creationtime":null,"unitdesc":"","rangestart":0,"signname":"体温","rangeend":50,"lastupdatetime":null,"isdeleted":"","vitalNoteList":null,"createdby":"","lastupdatedby":""},"patientInHospitalStatus":"","userid":3,"createdby":"系统1","type":"","recordTime":null,"standardVitalSignType":"","lastupdatedby":"","creationtime":1479805512570,"id":1251,"chartText":"","lastupdatetime":null,"executeName":"","isdeleted":"","value":null,"sheetid":1104,"savestatus":"1","note":"体征备注1","signtype":1},{"execrecordid":null,"signvalue":"99","vitalDefine":{"id":2,"creationtime":null,"unitdesc":"","rangestart":0,"signname":"脉搏","rangeend":200,"lastupdatetime":null,"isdeleted":"","vitalNoteList":null,"createdby":"","lastupdatedby":""},"patientInHospitalStatus":"","userid":3,"createdby":"系统2","type":"","recordTime":null,"standardVitalSignType":"","lastupdatedby":"","creationtime":1479805512570,"id":1252,"chartText":"","lastupdatetime":null,"executeName":"","isdeleted":"","value":null,"sheetid":1104,"savestatus":"1","note":"体征备注2","signtype":2},{"execrecordid":null,"signvalue":"20","vitalDefine":{"id":3,"creationtime":null,"unitdesc":"","rangestart":0,"signname":"呼吸","rangeend":100,"lastupdatetime":null,"isdeleted":"","vitalNoteList":null,"createdby":"","lastupdatedby":""},"patientInHospitalStatus":"","userid":3,"createdby":"系统3","type":"","recordTime":null,"standardVitalSignType":"","lastupdatedby":"","creationtime":1479805512570,"id":1253,"chartText":"","lastupdatetime":null,"executeName":"","isdeleted":"","value":null,"sheetid":1104,"savestatus":"1","note":"体征备注3","signtype":3}],"createdby":"系统1","lastupdatedby":"","creationtime":1478937400000,"id":1104,"time":1476518200000,"lastupdatetime":null,"patientid":"A100233_8","isdeleted":"","comment":"第一条备注","user":{"empno":"","rOLE_TYPE_NURSE":"0","createdby":"","userPatientList":null,"lastupdatedby":"","password":"","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE_LEADER":"1","creationtime":null,"id":null,"lastupdatetime":null,"isdeleted":"","name":"晨晨","rOLE_TYPE_ADMIN":"9","wardid":null,"roletype":"","patientList":null}}]
     * msg : 成功
     */
    private Integer status;
    private List<VitalSignSheet> data;
    private String msg;

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setData(List<VitalSignSheet> data) {
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
    public List<VitalSignSheet> getData() {
        return data;
    }
@Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getCallName() {
        return "load Vital Sign Sheets";
    }
}