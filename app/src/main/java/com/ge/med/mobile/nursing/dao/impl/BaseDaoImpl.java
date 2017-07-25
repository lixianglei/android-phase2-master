package com.ge.med.mobile.nursing.dao.impl;

import android.content.ContentValues;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.dao.BaseDao;
import com.ge.med.mobile.nursing.db.IBaseDB;
import com.ge.med.mobile.nursing.forjson.entity.IBaseBean;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Qu on 2016/12/23.
 */
public abstract class BaseDaoImpl implements BaseDao {
    public static final String WHERE_KEY_NAME = "clause";
    protected Class clazz;

    @Override
    public IBaseDB getEntity(Long id) {
        if (id == null) return null;
        return (IBaseDB)DataSupport.find(getDBTable(), id);
    }

    @Override
    public int updateAllToSynchronized() {
        LogUtil.d("updateAllToSynchronized for [" + getDBTable() + "] calling...");
        int retval = 0;
        List<IBaseDB> dblist = DataSupport.where("isModified = ?", Boolean.valueOf(true).toString()).find(getDBTable());
        if (dblist != null && dblist.size() > 0){
            LogUtil.d("Try to update [" + retval + "] un submit record to synchronized status!");
            retval = dblist.size();
            ContentValues values = new ContentValues();
            values.put("isModified", Boolean.FALSE.toString());
            DataSupport.updateAll(getDBTable(), values);
        }
//        dblist = DataSupport.where("isModified = ?", Boolean.valueOf(true).toString()).find(getDBTable());
//        if (dblist != null && dblist.size() > 0){
//            LogUtil.e("Total [" + dblist.size() + "] un submit record was not set to synchronized status!");
//        }else{
//            LogUtil.d("All un synchronized record was successfully udpated!");
//        }
        return retval;
    }
    @Override
    public int deleteAllUnSychronized(){
        LogUtil.d("Delete all un sychronized data in DB table:" + getDBTable());
        return DataSupport.deleteAll(getDBTable(), "isModified=?", Boolean.valueOf(true).toString());
    }
    @Override
    public List findAll() {
        return findAll(null, null);
    }

    @Override
    public List findAll(String sortCol, String sortOrder) {
        LogUtil.d("findAll calling for local DB:" + getDBTable().getName());
        List<IBaseDB> dblist = null;
        if (sortCol == null || sortCol.trim().isEmpty()){
            dblist = DataSupport.findAll(getDBTable());
        }else{
            dblist = DataSupport.order(sortCol + " " + sortOrder).find(getDBTable());
        }
        List retval = null;
        if (dblist == null || dblist.size() <= 0){
            LogUtil.d("No Data found for [" + getDBTable() + "]!");
        }else{
            LogUtil.i("Found [" + dblist.size() + "] DB Data for [" + getDBTable() + "]!");
            retval = convertFromDB(dblist);
            LogUtil.i("Got [" + (retval==null?"null":retval.size()) + "] Data for [" + getJsonBean().getClass().getName() + "]!");
        }
        return retval;
    }

    public List<IBaseBean> convertFromDB(List<IBaseDB> dbList){
        List<IBaseBean> retval = new ArrayList();
        IBaseBean bean = null;
        for (IBaseDB base : dbList) {
            bean = convertFromDB(base);
            if (bean != null) retval.add(bean);
        }
        return retval;
    }
    public IBaseBean convertFromDB(IBaseDB dbEntity){
        LogUtil.d("BaseDaoImpl>convertFromDB.jsonString is " + dbEntity.getJsonString());
        IBaseBean bean = (IBaseBean) JSON.parseObject(dbEntity.getJsonString(), getJsonBean());
        if (bean != null) {
            bean.setDbId(dbEntity.getId());
        }
        return bean;
    }

    @Override
    public boolean isUnSyncDataExists() {
        return isUnSyncDataExists(getDBTable());
    }

    @Override
    public boolean isUnSyncDataExists(Class clazz) {
        LogUtil.d("isUnSyncDataExists for [" + clazz + "] calling...");
        boolean retval = false;
        int count = DataSupport.where("isModified = ?", Boolean.valueOf(true).toString()).count(clazz);
        if (count > 0) retval = true;
        LogUtil.i("isUnSyncDataExists return [" + retval + "]!");
        return retval;
    }
    @Override
    public List findUnSyncData() {
        return findUnSyncData(getDBTable());
    }
    public List findUnSyncData(Class clazz) {
        LogUtil.d("findUnSyncData for [" + clazz + "] calling...");
        List retval = null;
        List<IBaseDB> dblist = DataSupport.where("isModified = ?", Boolean.valueOf(true).toString()).find(clazz);
        //DataSupport.findAll(clazz);
        if (dblist == null || dblist.size() <= 0){
            LogUtil.d("No Un Synced Data found for [" + clazz + "]!");
        }else{
            LogUtil.i("Found [" + dblist.size() + "] Un Synced DB Data for [" + clazz + "]!");
            retval = convertFromDB(dblist);
            LogUtil.i("Got [" + (retval==null?"null":retval.size()) + "] Un Synced Data for [" + getJsonBean().getName() + "]!");
        }
        return retval;
    }

    protected boolean generateWhereConditions(Map<String, String[]> conditions, String colName, String value, String signal) {
        if (value == null || conditions == null || colName == null) {
            LogUtil.e("Colunm [" + colName + " or value[" + value + "] or conditions[" + (conditions == null ? "null" : conditions) + "] not present!");
            return false;
        }
        String[] clause = (String[]) conditions.get(WHERE_KEY_NAME);
        if (signal == null) signal = "=";
        if (clause == null) {
            clause = new String[2];
            clause[0] = colName + " " + signal + " ?";
            clause[1] = value;
        } else {
            clause[0] = clause[0] + " and " + colName + " " + signal + " ?";
            String[] vs = new String[clause.length + 1];
            for (int i = 0; i < clause.length; i++) {
                vs[i] = clause[i];
                LogUtil.d("vs[" + i + "]=" + vs[i]);
            }
            vs[vs.length - 1] = value;
            clause = vs;
        }
        LogUtil.d("whereStr is " + clause[0] + ", values.length is " + clause.length + ", last value is " + clause[clause.length - 1]);
        conditions.put(WHERE_KEY_NAME, clause);
        return true;
    }
}
