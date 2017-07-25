package com.ge.med.mobile.nursing.db;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.dao.entity.MeasureDefineBean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by xxl on 2017/4/14.
 */

public class DBMeasureDefine extends DataSupport implements Serializable {
    private String measureDefineId;
    private String assessDefineCode;
    private String measureCode;
    private String jsonString;

    public static DBMeasureDefine convertBean(MeasureDefineBean.DataBean dataBean){
        DBMeasureDefine dbMeasureDefine = new DBMeasureDefine();
        if(dataBean!=null){
            dbMeasureDefine.setMeasureDefineId(dataBean.getId()+"");
            dbMeasureDefine.setAssessDefineCode(dataBean.getAssessDefineCode()+"");
            dbMeasureDefine.setMeasureCode(dataBean.getMeasureCode()+"");
            dbMeasureDefine.setJsonString(JSON.toJSONString(dataBean));
        }
        return dbMeasureDefine;
    }
    public static MeasureDefineBean.DataBean convertBean(DBMeasureDefine dbMeasureDefine){
        try {
            return  JSON.parseObject(dbMeasureDefine.getJsonString(),MeasureDefineBean.DataBean.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new MeasureDefineBean.DataBean();
        }
    }

    public String getMeasureDefineId() {
        return measureDefineId;
    }

    public void setMeasureDefineId(String measureDefineId) {
        this.measureDefineId = measureDefineId;
    }

    public String getAssessDefineCode() {
        return assessDefineCode;
    }

    public void setAssessDefineCode(String assessDefineCode) {
        this.assessDefineCode = assessDefineCode;
    }

    public String getMeasureCode() {
        return measureCode;
    }

    public void setMeasureCode(String measureCode) {
        this.measureCode = measureCode;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
