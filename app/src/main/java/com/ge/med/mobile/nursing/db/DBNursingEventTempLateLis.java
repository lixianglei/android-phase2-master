package com.ge.med.mobile.nursing.db;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/29.
 */
public class DBNursingEventTempLateLis extends DataSupport implements Serializable {
    private String jsonString;

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
