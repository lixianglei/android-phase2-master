package com.ge.med.mobile.nursing.forjson.entity;

import java.util.List;

/**
 * Created by lisa on 2017/7/6.
 * 事件定义
 */

public class EventDefineBean {
    private int status;
    private String msg;
    private List<DataBean> data;

    public class DataBean {
        private int id;
        private String datasourcedesc;
        private String eventcode;
        private String eventname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDatasourcedesc() {
            return datasourcedesc;
        }

        public void setDatasourcedesc(String datasourcedesc) {
            this.datasourcedesc = datasourcedesc;
        }

        public String getEventcode() {
            return eventcode;
        }

        public void setEventcode(String eventcode) {
            this.eventcode = eventcode;
        }

        public String getEventname() {
            return eventname;
        }

        public void setEventname(String eventname) {
            this.eventname = eventname;
        }
    } // the end of DataBean

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
}
