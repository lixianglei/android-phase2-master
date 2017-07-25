package com.ge.med.mobile.nursing.dao.entity;

/**
 * 修改密码网络访问实体JSON解析Bean
 * Created by Administrator on 2016/11/17.
 */
public class LoginBean2 {

    /**
     * status : 1
     * data : {"token":"pda-26245909-0569-4703-8a4b-b3c37ad40853","auth":"0","remindInfo":"1"}
     * msg : 成功
     */

    private int status;
    private String data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
