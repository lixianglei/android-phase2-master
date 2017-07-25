package com.ge.med.mobile.nursing.forjson.entity;

import java.util.List;

/**
 * Created by Qu on 2016/12/9.
 */
public class ResponseSubmitVitalSignSignle implements BaseJSONBean{
    /**
     * status : 1
     * data : [-496364308]
     * msg : 成功
     */
    private Integer status;
    private List<Integer> data;
    private String msg;

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setData(List<Integer> data) {
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
    public List<Integer> getData() {
        return data;
    }
@Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getCallName() {
        return "submit Vital Sign Sheet";
    }
}
