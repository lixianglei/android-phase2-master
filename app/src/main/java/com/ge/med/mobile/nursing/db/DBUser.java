package com.ge.med.mobile.nursing.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2016/11/17.
 */
public class DBUser extends DataSupport {
    private String user_id;
    private String ward_id;
    private int id;
    private int zid;
    private String username;
    private String wardname;
    private String work_year;
    private String nursing_level;

    public String getWork_year() {
        return work_year;
    }

    public void setWork_year(String work_year) {
        this.work_year = work_year;
    }

    public String getNursing_level() {
        return nursing_level;
    }

    public void setNursing_level(String nursing_level) {
        this.nursing_level = nursing_level;
    }

    public int getZid() {
        return zid;
    }

    public void setZid(int zid) {
        this.zid = zid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getWard_id() {
        return ward_id;
    }

    public void setWard_id(String ward_id) {
        this.ward_id = ward_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWardname() {
        return wardname;
    }

    public void setWardname(String wardname) {
        this.wardname = wardname;
    }
}
