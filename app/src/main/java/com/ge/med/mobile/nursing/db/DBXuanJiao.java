package com.ge.med.mobile.nursing.db;

import org.litepal.crud.DataSupport;

/**
 * Created by xxl on 2017/4/13.
 */

public class DBXuanJiao extends DataSupport {
    private String jsonString;

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
