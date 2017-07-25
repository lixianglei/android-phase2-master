package com.ge.med.mobile.nursing.forjson.entity;

import java.util.List;

/**
 * Created by lisa on 2017/7/6.
 * 事件实体
 */

public class EventsBean {
    private int status;
    private String msg;
    private List<DataBean> data;

    public class DataBean {
        private int id;
        private String createdby;
        private long creationtime;
        private String defineEventCode;
        private String defineEventName;
        // defineReadOnly="1"是来自his的数据，不允许修改和删除！
        private String defineReadOnly;
        private int eventdefineid;
        private long eventtime;
        private String isdeleted;
        private String lastupdatedby;
        private long lastupdatetime;
        private String patientid;
        private String userName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreatedby() {
            return createdby;
        }

        public void setCreatedby(String createdby) {
            this.createdby = createdby;
        }

        public long getCreationtime() {
            return creationtime;
        }

        public void setCreationtime(long creationtime) {
            this.creationtime = creationtime;
        }

        public String getDefineEventCode() {
            return defineEventCode;
        }

        public void setDefineEventCode(String defineEventCode) {
            this.defineEventCode = defineEventCode;
        }

        public String getDefineEventName() {
            return defineEventName;
        }

        public void setDefineEventName(String defineEventName) {
            this.defineEventName = defineEventName;
        }

        public String getDefineReadOnly() {
            return defineReadOnly;
        }

        public void setDefineReadOnly(String defineReadOnly) {
            this.defineReadOnly = defineReadOnly;
        }

        public int getEventdefineid() {
            return eventdefineid;
        }

        public void setEventdefineid(int eventdefineid) {
            this.eventdefineid = eventdefineid;
        }

        public long getEventtime() {
            return eventtime;
        }

        public void setEventtime(long eventtime) {
            this.eventtime = eventtime;
        }

        public String getIsdeleted() {
            return isdeleted;
        }

        public void setIsdeleted(String isdeleted) {
            this.isdeleted = isdeleted;
        }

        public String getLastupdatedby() {
            return lastupdatedby;
        }

        public void setLastupdatedby(String lastupdatedby) {
            this.lastupdatedby = lastupdatedby;
        }

        public long getLastupdatetime() {
            return lastupdatetime;
        }

        public void setLastupdatetime(long lastupdatetime) {
            this.lastupdatetime = lastupdatetime;
        }

        public String getPatientid() {
            return patientid;
        }

        public void setPatientid(String patientid) {
            this.patientid = patientid;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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
