package com.ge.med.mobile.nursing.forjson.entity;

/**
 * Created by Lisa on 2017/6/5.
 * app更新bean
 */

public class AppUpdateBean {

    private String status;
    private String msg;
    private AppUpdateDataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AppUpdateDataBean getData() {
        return data;
    }

    public void setData(AppUpdateDataBean data) {
        this.data = data;
    }
}
