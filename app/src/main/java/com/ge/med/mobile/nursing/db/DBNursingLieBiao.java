package com.ge.med.mobile.nursing.db;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by xxl on 2017/4/5.
 */

public class DBNursingLieBiao extends DataSupport implements Serializable {
    private String jsonString;

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
