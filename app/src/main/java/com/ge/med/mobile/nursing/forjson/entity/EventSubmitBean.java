package com.ge.med.mobile.nursing.forjson.entity;

/**
 * Created by lisa on 2017/7/7.
 * 提交事件
 */

public class EventSubmitBean {
    private int id;
    private String patientid;
    private int eventdefineid;
    private long eventtime;
    private String createdby;

    public EventSubmitBean(int id, String patientid, int eventdefineid, long eventtime, String createdby) {
        this.id = id;
        this.patientid = patientid;
        this.eventdefineid = eventdefineid;
        this.eventtime = eventtime;
        this.createdby = createdby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
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

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }
}
