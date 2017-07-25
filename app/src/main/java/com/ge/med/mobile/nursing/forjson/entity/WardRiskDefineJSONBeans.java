package com.ge.med.mobile.nursing.forjson.entity;

import java.util.List;

/**
 * Created by Qu on 2016/12/17.
 */
public class WardRiskDefineJSONBeans implements BaseJSONBean {
    /**
     * status : 1
     * data : [{"id":null,"creationtime":null,"riskname":"积水","lastupdatetime":null,"riskid":5,"isdeleted":"","createdby":"","wardid":301,"lastupdatedby":"","risksortno":5},{"id":null,"creationtime":null,"riskname":"三高","lastupdatetime":null,"riskid":4,"isdeleted":"","createdby":"","wardid":301,"lastupdatedby":"","risksortno":4},{"id":null,"creationtime":null,"riskname":"跌倒","lastupdatetime":null,"riskid":3,"isdeleted":"","createdby":"","wardid":301,"lastupdatedby":"","risksortno":3},{"id":null,"creationtime":null,"riskname":"压疮","lastupdatetime":null,"riskid":2,"isdeleted":"","createdby":"","wardid":301,"lastupdatedby":"","risksortno":2},{"id":null,"creationtime":null,"riskname":"跌倒","lastupdatetime":null,"riskid":1,"isdeleted":"","createdby":"","wardid":301,"lastupdatedby":"","risksortno":1}]
     * msg : 成功
     */
    private Integer status;
    private List<WardRiskDefine> data;
    private String msg;

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setData(List<WardRiskDefine> data) {
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
    public List<WardRiskDefine> getData() {
        return data;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getCallName() {
        return "load Ward Risk Define";
    }
}
