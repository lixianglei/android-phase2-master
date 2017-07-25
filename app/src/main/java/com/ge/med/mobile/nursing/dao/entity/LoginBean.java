package com.ge.med.mobile.nursing.dao.entity;

/**
 * 登录网络访问实体JSON解析Bean
 * Created by Administrator on 2016/11/17.
 */
public class LoginBean {

    /**
     * status : 1
     * data : {"token":"pda-26245909-0569-4703-8a4b-b3c37ad40853","auth":"0","remindInfo":"1"}
     * msg : 成功
     */

    private int status;
    private DataBean data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * token : pda-26245909-0569-4703-8a4b-b3c37ad40853
         * auth : 0
         * remindInfo : 1
         */

        private String token;
        private String auth;
        private String remindInfo;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getAuth() {
            return auth;
        }

        public void setAuth(String auth) {
            this.auth = auth;
        }

        public String getRemindInfo() {
            return remindInfo;
        }

        public void setRemindInfo(String remindInfo) {
            this.remindInfo = remindInfo;
        }
    }
}
