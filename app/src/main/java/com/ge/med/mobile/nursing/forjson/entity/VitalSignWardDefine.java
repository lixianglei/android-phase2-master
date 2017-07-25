package com.ge.med.mobile.nursing.forjson.entity;

import java.io.Serializable;

/**
 * Created by Qu on 2016/12/10.
 */
public class VitalSignWardDefine implements Serializable {
    // 病区体征编号
    private int id;
    // 体征定义
    private VitalDefineEntity vitalDefine;
    // 体征定义编号
    private int vitaldefineid;
    // 病区编号
    private String wardid;
    // 病区默认备注编号
    private Integer defaultvitalnoteid;
    // 病区体征排序号码
    private Integer sortvitalno;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VitalDefineEntity getVitalDefine() {
        return vitalDefine;
    }

    public void setVitalDefine(VitalDefineEntity vitalDefine) {
        this.vitalDefine = vitalDefine;
    }

    public int getVitaldefineid() {
        return vitaldefineid;
    }

    public void setVitaldefineid(int vitaldefineid) {
        this.vitaldefineid = vitaldefineid;
    }

    public String getWardid() {
        return wardid;
    }

    public void setWardid(String wardid) {
        this.wardid = wardid;
    }

    public Integer getDefaultvitalnoteid() {
        return defaultvitalnoteid;
    }

    public void setDefaultvitalnoteid(Integer defaultvitalnoteid) {
        this.defaultvitalnoteid = defaultvitalnoteid;
    }

    public Integer getSortvitalno() {
        return sortvitalno;
    }

    public void setSortvitalno(Integer sortvitalno) {
        this.sortvitalno = sortvitalno;
    }
}
