package com.ge.med.mobile.nursing.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/12/2.
 */
public class DBExceptionDefine extends DataSupport {

    private String exceptionname;
    private String handle;
    private Integer zid;
    private String type;
    private Integer dbexceptionid;

    public Integer getDbexceptionid() {
        return dbexceptionid;
    }

    public void setDbexceptionid(Integer dbexceptionid) {
        this.dbexceptionid = dbexceptionid;
    }

    public String getExceptionname() {
        return exceptionname;
    }

    public void setExceptionname(String exceptionname) {
        this.exceptionname = exceptionname;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public Integer getZid() {
        return zid;
    }

    public void setZid(Integer zid) {
        this.zid = zid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
