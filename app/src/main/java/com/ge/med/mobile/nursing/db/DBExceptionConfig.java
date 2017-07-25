package com.ge.med.mobile.nursing.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/12/7.
 */
public class DBExceptionConfig extends DataSupport {

    private String createdby;
    private long creationtime;
    private Integer exceptiondefineid;
    private Integer zid;
    private String isdeleted;
    private String lastupdatedby;
    private long lastupdatetime;
    private String orderstatus;
    private String ordertype;

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

    public Integer getExceptiondefineid() {
        return exceptiondefineid;
    }

    public void setExceptiondefineid(Integer exceptiondefineid) {
        this.exceptiondefineid = exceptiondefineid;
    }

    public Integer getZid() {
        return zid;
    }

    public void setZid(Integer zid) {
        this.zid = zid;
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

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }
}
