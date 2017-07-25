package com.ge.med.mobile.nursing.db;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.dao.entity.TiJiaoXuanJiao;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by xxl on 2017/4/14.
 * 患者宣教
 */

public class DBXuanJiaoRecord extends DataSupport implements Serializable, IBaseDB {
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
    private String isModified;
    private int id;
    // 每个病人相同的标识
    private String edurecordid;


    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getIsModified() {
        return isModified;
    }

    public void setIsModified(String isModified) {
        this.isModified = isModified;
    }

    public static DBXuanJiaoRecord convertXuanJiaoRecord(TiJiaoXuanJiao tiJiaoXuanJiao) {
        DBXuanJiaoRecord dbXuanJiaoRecord = new DBXuanJiaoRecord();
        if (tiJiaoXuanJiao != null) {
            dbXuanJiaoRecord.setEduuserid(tiJiaoXuanJiao.getEduuserid());
            dbXuanJiaoRecord.setEdudefineid(tiJiaoXuanJiao.getEdudefineid());
            dbXuanJiaoRecord.setEdumodetext(tiJiaoXuanJiao.getEdumodetext());
            dbXuanJiaoRecord.setEdurecordpk(tiJiaoXuanJiao.getEdurecordpk());
            dbXuanJiaoRecord.setEduresulttext(tiJiaoXuanJiao.getEduresulttext());
            dbXuanJiaoRecord.setEdutime(tiJiaoXuanJiao.getEdutime());
            dbXuanJiaoRecord.setIsdeleted(tiJiaoXuanJiao.getIsdeleted());
            dbXuanJiaoRecord.setPatientid(tiJiaoXuanJiao.getPatientid());
            dbXuanJiaoRecord.setStatus(tiJiaoXuanJiao.getStatus());
            dbXuanJiaoRecord.setUserName(tiJiaoXuanJiao.getUserName());
            dbXuanJiaoRecord.setEdurecordid(tiJiaoXuanJiao.getEdurecordid());
        }
        return dbXuanJiaoRecord;
    }

    @Override
    public String getJsonString() {
        return JSON.toJSONString(convertXuanJiaoRecord(this));
    }

    public static TiJiaoXuanJiao convertXuanJiaoRecord(DBXuanJiaoRecord dbXuanJiaoRecord) {
        TiJiaoXuanJiao tiJiaoXuanJiao = new TiJiaoXuanJiao();
        if (dbXuanJiaoRecord != null) {
            tiJiaoXuanJiao.setEduuserid(dbXuanJiaoRecord.getEduuserid());
            tiJiaoXuanJiao.setEdudefineid(dbXuanJiaoRecord.getEdudefineid());
            tiJiaoXuanJiao.setEdumodetext(dbXuanJiaoRecord.getEdumodetext());
            tiJiaoXuanJiao.setEdurecordpk(dbXuanJiaoRecord.getEdurecordpk());
            tiJiaoXuanJiao.setEduresulttext(dbXuanJiaoRecord.getEduresulttext());
            tiJiaoXuanJiao.setEdutime(dbXuanJiaoRecord.getEdutime());
            tiJiaoXuanJiao.setIsdeleted(dbXuanJiaoRecord.getIsdeleted());
            tiJiaoXuanJiao.setPatientid(dbXuanJiaoRecord.getPatientid());
            tiJiaoXuanJiao.setStatus(dbXuanJiaoRecord.getStatus());
            tiJiaoXuanJiao.setUserName(dbXuanJiaoRecord.getUserName());
            tiJiaoXuanJiao.setEdurecordid(dbXuanJiaoRecord.getEdurecordid());
        }
        return tiJiaoXuanJiao;
    }

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

    public String getEdurecordid() {
        return edurecordid;
    }

    public void setEdurecordid(String edurecordid) {
        this.edurecordid = edurecordid;
    }
}
