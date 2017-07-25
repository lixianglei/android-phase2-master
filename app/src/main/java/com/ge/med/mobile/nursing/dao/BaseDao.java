package com.ge.med.mobile.nursing.dao;

import com.ge.med.mobile.nursing.db.IBaseDB;
import com.ge.med.mobile.nursing.forjson.entity.IBaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Qu on 2016/12/23.
 */
public interface BaseDao {
    IBaseDB getEntity(Long id);
    List findAll();
    List findAll(String sortCol, String sortOrder);
    List findUnSyncData();
    boolean isUnSyncDataExists();
    public boolean isUnSyncDataExists(Class clazz);
    int deleteAllUnSychronized();
    Class getDBTable();
    Class getJsonBean();
    int updateAllToSynchronized();
}
