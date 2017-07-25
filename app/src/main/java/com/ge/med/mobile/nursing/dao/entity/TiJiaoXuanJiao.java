package com.ge.med.mobile.nursing.dao.entity;

import com.ge.med.mobile.nursing.forjson.entity.IBaseBean;

import java.io.Serializable;

/**
 * 定义网络访问实体JSON解析Bean
 * Created by xxl on 2017/4/12.
 */

public class TiJiaoXuanJiao implements Serializable, IBaseBean {

    /**
     * edudefineid : 16
     * edumodetext : 示范
     * edurecordpk : 1
     * eduresulttext : 掌握
     * edutime : 1492139182000
     * eduuserid : 3
     * isdeleted :
     * patientid : AA1063
     * status : 0
     * userName : 晨晨
     */

    private int edudefineid;
    private String edumodetext;
    private int edurecordpk;
    private String eduresulttext;
    private long edutime;
    private int eduuserid;
    private String isdeleted;
    private String patientid;
    private String status;
    private String userName;
    private Integer dbId;
    private String edurecordid; // 32位的唯一标识

    public int getEdudefineid() {
        return edudefineid;
    }

    public void setEdudefineid(int edudefineid) {
        this.edudefineid = edudefineid;
    }

    public String getEdumodetext() {
        return edumodetext;
    }

    public void setEdumodetext(String edumodetext) {
        this.edumodetext = edumodetext;
    }

    public int getEdurecordpk() {
        return edurecordpk;
    }

    public void setEdurecordpk(int edurecordpk) {
        this.edurecordpk = edurecordpk;
    }

    public String getEduresulttext() {
        return eduresulttext;
    }

    public void setEduresulttext(String eduresulttext) {
        this.eduresulttext = eduresulttext;
    }

    public long getEdutime() {
        return edutime;
    }

    public void setEdutime(long edutime) {
        this.edutime = edutime;
    }

    public int getEduuserid() {
        return eduuserid;
    }

    public void setEduuserid(int eduuserid) {
        this.eduuserid = eduuserid;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void setDbId(Integer id) {
        this.dbId = id;
    }

    public String getEdurecordid() {
        return edurecordid;
    }

    public void setEdurecordid(String edurecordid) {
        this.edurecordid = edurecordid;
    }
}
