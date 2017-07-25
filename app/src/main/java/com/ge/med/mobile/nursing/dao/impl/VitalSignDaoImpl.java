package com.ge.med.mobile.nursing.dao.impl;

import android.content.ContentValues;

import com.ge.med.mobile.nursing.dao.VitalSignDao;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignSheet;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignWardDefine;
import com.ge.med.mobile.nursing.db.DBAssess;
import com.ge.med.mobile.nursing.db.DBVitalSignSheet;
import com.ge.med.mobile.nursing.db.DBVitalSignWardDefine;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Alex Qu on 2016/12/10.
 */
public class VitalSignDaoImpl extends BaseDaoImpl implements VitalSignDao {
    @Override
    public void clearAll() {
        clearAllRecord();
        clearAllDefine();
    }

    @Override
    public void clearAllDefine() {
        try {
            DataSupport.deleteAll(DBVitalSignWardDefine.class);
        } catch (Exception e) {
            LogUtil.e("Cannot delete record from table DBVitalSignSheet" + e.getMessage());
        }
    }

    @Override
    public void clearAllRecord() {
        try {
            DataSupport.deleteAll(DBVitalSignSheet.class);
        } catch (Exception e) {
            LogUtil.e("Cannot delete record from table DBVitalSignSheet" + e.getMessage());
        }
    }

    @Override
    public void delete(VitalSignSheet sheet) {
        if (sheet == null){
            LogUtil.i("Can not delete vital sign sheet since it is null!");
            return;
        }
        DataSupport.deleteAll(DBVitalSignSheet.class, "sheetId = ?", sheet.getId()+"");
    }

    @Override
    public List<VitalSignWardDefine> findAllWardDefineFromDB() {
        List<VitalSignWardDefine> retval = DBVitalSignWardDefine.convertFromDB(DataSupport.findAll(DBVitalSignWardDefine.class));
        Collections.sort(retval, new Comparator<VitalSignWardDefine>() {
            @Override
            public int compare(VitalSignWardDefine lhs, VitalSignWardDefine rhs) {
                if (lhs == null || rhs == null || lhs.getVitalDefine() == null
                        || rhs.getVitalDefine() == null || lhs.getVitalDefine().getPosition() == null || rhs.getVitalDefine().getPosition() == null) {
                    return 0;
                }
                if (lhs.getVitalDefine().getPosition().intValue() > rhs.getVitalDefine().getPosition().intValue()){
                    return 1;
                } else if(lhs.getVitalDefine().getPosition().intValue() < rhs.getVitalDefine().getPosition().intValue()){
                    return -1;
                }
                return 0;
            }
        });
        return retval;
    }

    @Override
    public VitalSignWardDefine getVitalSignWardDefineByIdFromDB(int defineId) {
        List<DBVitalSignWardDefine> wardDefineList =
                DataSupport.where("warDefineId = ?", defineId + "").find(DBVitalSignWardDefine.class);
        if (null == wardDefineList || wardDefineList.size() <= 0) return null;
        return DBVitalSignWardDefine.convertFromDB(wardDefineList.get(0));
    }

    public VitalSignSheet findLastVitalSignsFromDB(String patientId) {
        DBVitalSignSheet dbSheet =
                DataSupport.where("patientid = ?", patientId).order("time desc").findFirst(DBVitalSignSheet.class);
        if (null == dbSheet) return null;
        return DBVitalSignSheet.convertFromDB(dbSheet);

    }

    @Override
    public List<VitalSignSheet> findVitalSignsFromDB(String patientId) {
        LogUtil.d("VitalSignDaoImpl.findVitalSignsFromDB(patentId[" + patientId + "]) is calling...");
        List<VitalSignSheet> retval = new ArrayList<>();
        if (patientId != null) {
            List<DBVitalSignSheet> dbSheetList =
                    DataSupport.where("patientid = ?", patientId).order("time desc").find(DBVitalSignSheet.class);
            if (!(null == dbSheetList || dbSheetList.size() <= 0)) {
                LogUtil.d("Found [" + dbSheetList.size() + "] vital sign sheet from local DB by patientid[" + patientId + "].");
                retval = DBVitalSignSheet.convertFromDB(dbSheetList);
            }
        }
        return retval;
    }

    @Override
    public VitalSignSheet getVitalSignByIdFromDB(Integer vitalSignId) {
        List<DBVitalSignSheet> dbSheetList =
                DataSupport.where("sheetId = ?", vitalSignId.toString()).find(DBVitalSignSheet.class);
        if (null == dbSheetList || dbSheetList.size() <= 0) return null;
        return DBVitalSignSheet.convertFromDB(dbSheetList.get(0));
    }

    @Override
    public void saveVitalSignWardDefines(List<VitalSignWardDefine> list) {
        long currentTime = System.currentTimeMillis();
        if (null == list || list.size() <= 0) {
            LogUtil.i("Can not save anything because nothing to be saved!");
            return;
        }
        List<DBVitalSignWardDefine> dbList = DBVitalSignWardDefine.convertToDB(list);
        boolean bSave = false;
        for (DBVitalSignWardDefine dbBean : dbList) {
            if (null == dbBean) {
                LogUtil.i("Can not handle anything because dbBean is null!");
                continue;
            }
//            if (dbBean.getVitalDefine() != null) {
//                saveVitalDefine(dbBean.getVitalDefine());
//            }
            bSave = dbBean.save();
            LogUtil.d("Called creation record for ward vital sign define table ! The status of save is [" + bSave + "]");
        }
    }

    @Override
    public void saveVitalSigns(List<VitalSignSheet> lstVitalSign) {
        if (null == lstVitalSign) {
            LogUtil.i("Can not save Vital Sign Sheet since list is null!");
            return;
        }
        for (VitalSignSheet sheet : lstVitalSign) {
            if (null == sheet) {
                LogUtil.i("The VitalSignSheet got from list is null!");
            }
            saveVitalSign(sheet);
        }
    }

    @Override
    public void saveVitalSign(VitalSignSheet vitalSign) {
        saveVitalSign(vitalSign, false);
    }

    public void saveVitalSign(VitalSignSheet vitalSign, boolean isModified) {
        if (null == vitalSign) {
            LogUtil.i("Can not save Vital Sign Sheet since it is null!");
            return;
        }
        DBVitalSignSheet dbSheet = DBVitalSignSheet.convertToDB(vitalSign, isModified);
        if (dbSheet == null) {
            LogUtil.i("Can not save Vital Sign Sheet since the sheet cannot convert into DB format!");
            return;
        }
        Integer dbId = saveVitalSign(dbSheet);
        if (dbId != null) vitalSign.setDbId(dbId);
    }
    private Integer updateVitalSign(DBVitalSignSheet dbSheet){
        LogUtil.d("updateVitalSign(dbSheet[id:" + (dbSheet==null?"null":dbSheet.getId())
                + ",time:" + (dbSheet==null?"null":dbSheet.getTime()) + ",patientid:" + (dbSheet==null?"null":dbSheet.getPatientid()) + "]) is calling...");
        Integer retval = null;
        if (dbSheet != null) {
            if (dbSheet.getId() > 0) {
                LogUtil.d("Trying to update vital sign sheet by its id[" + dbSheet.getId() + "]");
                retval = dbSheet.update(dbSheet.getId());
            }
            if (retval == null || retval.intValue() <= 0){
                LogUtil.d("Trying to update vital sign sheet by its sheetid[" + dbSheet.getSheetId() + "].");
                DBVitalSignSheet sheet = DataSupport.where("sheetId = ?", dbSheet.getSheetId()+"").findFirst(DBVitalSignSheet.class);
                if (sheet != null){
                    LogUtil.d("Found vital sign sheet[dbId:" + sheet.getId() + "] by its sheetId[" + dbSheet.getSheetId() + "] in local DB.");
                    retval = dbSheet.update(sheet.getId());
                    if (retval != null && retval.intValue() > 0){
                        retval = sheet.getId();
                        LogUtil.d("Update vital sign sheet[sheetId:" + dbSheet.getSheetId() + ",dbId:" + dbSheet.getId() + "] by its sheetId successfully!");
                    }
                }
            }else{

                LogUtil.d("Update vital sign sheet[sheetId:" + dbSheet.getSheetId() + ",dbId:" + dbSheet.getId() + "] by its dbId successfully!jsonString is " + dbSheet.getJsonString());
                retval = dbSheet.getId();
            }
        }
        return retval;
    }
    private Integer insertVitalSign(DBVitalSignSheet dbSheet){
        Integer retval = null;
        if (dbSheet != null) {
            boolean isSaved = dbSheet.save();
            if (!isSaved) {
                LogUtil.e("Cannot insert vital sheet into DB table for sheetId[" + dbSheet.getSheetId() + "]");
            } else {
                LogUtil.d("Successfully insert vital sheet[id:" + dbSheet.getId() + "] into DB table for sheetId[" + dbSheet.getSheetId() + "]");
                retval = dbSheet.getId();
            }
        }
        return retval;
    }
    public Integer saveVitalSign(DBVitalSignSheet dbSheet) {
        LogUtil.d("saveVitalSign(dbSheet[dbId" + (dbSheet==null?"null":dbSheet.getId())
                + ":,sheetId:" + (dbSheet==null?"null":dbSheet.getSheetId()) + "]) calling...");
        Integer retval = null;
        if (null == dbSheet) {
            LogUtil.i("Can not save DB Vital Sign Sheet since it is null!");
        }else{
            retval = updateVitalSign(dbSheet);
            if (retval == null || retval.intValue() <= 0){
                retval = insertVitalSign(dbSheet);
            }
        }
        if (retval == null || retval.intValue() <= 0){
            LogUtil.e("Can not update or insert record for DBVitalSignSheet[dbId:"
                    + (dbSheet==null?"null":dbSheet.getId()) + ",sheetId:" + (dbSheet==null?"null":dbSheet.getSheetId()) + "].");
        }else {
            LogUtil.d("Called saveVitalSign, updated or insert DBVitalSignSheet using dbId[" + retval + "].");
        }
        return retval;
    }

    @Override
    public void updateVitalSignSheetStatus(Integer sheetId, String status) {
        List<DBVitalSignSheet> dbVitalSheetList = DataSupport.where("sheetId = ?", sheetId.toString()).find(DBVitalSignSheet.class);
        if (null == dbVitalSheetList || dbVitalSheetList.size() <= 0) {
            LogUtil.i("Cannot found in local DB for sheetId:" + sheetId);
        } else {
            ContentValues values = new ContentValues();
            values.put("status", status);
            for (DBVitalSignSheet dbSheet : dbVitalSheetList) {
                DataSupport.update(DBAssess.class, values, dbSheet.getId());
            }
        }

    }

    @Override
    public Class getDBTable() {
        return DBVitalSignSheet.class;
    }

    @Override
    public Class getJsonBean() {
        return VitalSignSheet.class;
    }
}
