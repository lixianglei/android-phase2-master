package com.ge.med.mobile.nursing.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/12/14.
 */
public class DBUserList extends DataSupport {
    private String empno;
    private Integer zid;
    private String name;

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public Integer getZid() {
        return zid;
    }

    public void setZid(Integer zid) {
        this.zid = zid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
