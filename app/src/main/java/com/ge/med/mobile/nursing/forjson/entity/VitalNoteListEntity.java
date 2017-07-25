package com.ge.med.mobile.nursing.forjson.entity;

import java.io.Serializable;

/**
 * Created by Qu on 2016/12/10.
 */
public class VitalNoteListEntity implements Serializable{
    private Integer id;
    private Integer vitaldefineid;
    private String note; //名字
    private String ncode;//编号
    private String androidshowname;
    private Integer ishandlemeasure;

    public String getNcode() {
        return ncode;
    }

    public void setNcode(String ncode) {
        this.ncode = ncode;
    }

    public void setId(Integer id) {
            this.id = id;
        }

    public void setVitaldefineid(Integer vitaldefineid) {
        this.vitaldefineid = vitaldefineid;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVitaldefineid() {
        return vitaldefineid;
    }

    public String getNote() {
        return note;
    }

    public String getAndroidshowname() {
        return androidshowname;
    }

    public void setAndroidshowname(String androidshowname) {
        this.androidshowname = androidshowname;
    }

    public Integer getIshandlemeasure() {
        return ishandlemeasure;
    }

    public void setIshandlemeasure(Integer ishandlemeasure) {
        this.ishandlemeasure = ishandlemeasure;
    }
}