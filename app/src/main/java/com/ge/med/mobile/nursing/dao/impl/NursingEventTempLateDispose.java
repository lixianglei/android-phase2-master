package com.ge.med.mobile.nursing.dao.impl;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.dao.entity.NursingEventTempLateLisBean;
import com.ge.med.mobile.nursing.db.DBNursingEventTempLateLis;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */
public class NursingEventTempLateDispose {
    public List<NursingEventTempLateLisBean.DataBean> findNursingEventTempLateList() {
        List<NursingEventTempLateLisBean.DataBean> list = null;
        DBNursingEventTempLateLis dbNursingEventTempLateLis = DataSupport.findFirst(DBNursingEventTempLateLis.class);
        if (dbNursingEventTempLateLis != null) {
            String jsonString = dbNursingEventTempLateLis.getJsonString();
            if (jsonString != null) {
                NursingEventTempLateLisBean nursingEventTempLateLisBean = JSON.parseObject(jsonString, NursingEventTempLateLisBean.class);
                list = nursingEventTempLateLisBean.getData();
            }
        }
        return list;
    }
}
