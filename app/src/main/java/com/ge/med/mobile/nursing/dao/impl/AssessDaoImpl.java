package com.ge.med.mobile.nursing.dao.impl;

import android.content.ContentValues;

import com.ge.med.mobile.nursing.dao.AssessDao;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.db.DBAssess;
import com.ge.med.mobile.nursing.db.DBAssessDefine;
import com.ge.med.mobile.nursing.db.DBWardRiskDefine;
import com.ge.med.mobile.nursing.forjson.entity.AssessRecordBean;
import com.ge.med.mobile.nursing.forjson.entity.WardRiskDefine;
import com.ge.med.mobile.nursing.ui.data.RiskDefine;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Qu on 2016/12/7.
 */
public class AssessDaoImpl extends BaseDaoImpl implements AssessDao{
    @Override
    public void clearAll() {
        LogUtil.i("AssessDaoImpl.clearAll calling...");
        clearAllRecord();
        clearAllDefine();
    }

    @Override
    public void clearAllDefine() {
        LogUtil.i("AssessDaoImpl.clearAllDefine calling...");
        DataSupport.deleteAll(DBAssessDefine.class);
        DataSupport.deleteAll(DBWardRiskDefine.class);
    }

    @Override
    public void clearAllRecord() {
        LogUtil.i("AssessDaoImpl.clearAllRecord calling...");
        DataSupport.deleteAll(DBAssess.class);
    }

    @Override
    public void delete(AssessRecordBean assess) {
        if (assess == null || assess.getDbId() == null){
            LogUtil.i("Can not delete assess since assess or assess.dbId is null!");
            return;
        }
        DataSupport.delete(DBAssess.class, assess.getDbId());
    }

    @Override
    public List<AssessDefine.DataBean> findAllAssessDefine() {
        List<DBAssessDefine> dbAnswerDefineList = DataSupport.findAll(DBAssessDefine.class);
        List<AssessDefine.DataBean> assessDefineList = new ArrayList<>();
        if (null == dbAnswerDefineList){
            LogUtil.i("No local data for assess define!");
            return assessDefineList;
        }

        AssessDefine.DataBean assessDefine = null;
        for (DBAssessDefine dbAssessDefine : dbAnswerDefineList){
          //  loadDefineChild(dbAssessDefine);
            assessDefine = DBAssessDefine.convertFrom(dbAssessDefine);
            if (null == assessDefine){
                continue;
            }
            assessDefineList.add(assessDefine);
        }
        LogUtil.d("Called find Assesses From DB  ! load total [" + assessDefineList.size() + "] record.");
        return assessDefineList;
    }

    @Override
    public List<RiskDefine> findAllRiskDefine() {

        List<DBWardRiskDefine> dbWardRiskDefines = DataSupport.findAll(DBWardRiskDefine.class);
        List<RiskDefine> riskDefines = new ArrayList<>();
        if (null == dbWardRiskDefines){
            LogUtil.i("No local data for ward risk define!");
            return riskDefines;
        }

        RiskDefine riskDefine = null;
        for (DBWardRiskDefine dbWardRiskDefine : dbWardRiskDefines){
            riskDefine = DBWardRiskDefine.convertFromDB(dbWardRiskDefine);
            if (null == riskDefine){
                continue;
            }
            riskDefines.add(riskDefine);
        }
        LogUtil.d("Called find Risk define From DB  ! load total [" + riskDefines.size() + "] record.");
        return riskDefines;
    }
    public void saveWardRiskDefines(List<WardRiskDefine> aDefines) {
        if (null == aDefines || aDefines.size() <= 0) {
            LogUtil.e("Can not save anything because list of WardRiskDefine is empty to be saved!");
            return;
        }
        DBWardRiskDefine dbDefine = null;
        List<DBWardRiskDefine> defineList = null;
        for (WardRiskDefine adefine : aDefines) {
            dbDefine = DBWardRiskDefine.convertToDB(adefine);
            if (null == dbDefine) {
                LogUtil.i("Can not handle anything because wardriskdefine cannot convert data into DB format!");
                continue;
            }
            defineList = DataSupport.where("riskId = ?", (adefine.getRiskid() + "")).find(DBWardRiskDefine.class);
            if (defineList != null && defineList.size() > 0) {
                dbDefine.updateAll("riskId = ?", (adefine.getRiskid() + ""));
                LogUtil.d("Successfully updated [" + defineList.size() + "] record(s) for ward risk define table!");
                continue;
            }
            LogUtil.d("Called creation record for ward risk define table ! The status of save is [" + dbDefine.save() + "]");
        }
    }
    @Override
    public AssessDefine.DataBean getAssessDefineById(int defineId) {
        List<DBAssessDefine> dbAnswerDefineList = DataSupport.where("assessDefineId = ?", defineId+"").find(DBAssessDefine.class);
        if (dbAnswerDefineList == null || dbAnswerDefineList.size() <= 0){
            LogUtil.i("No local assess define for defineid[" + defineId + "]!");
            return null;
        }
        return DBAssessDefine.convertFrom(dbAnswerDefineList.get(0));
    }

    @Override
    public List<AssessRecordBean> findAssessesFromDB(String patientId) {
        if (null == patientId) {
            LogUtil.i("Can not find anything because patient id not present!");
            return null;
        }
        List<DBAssess> assessList = DataSupport.where("patientid = ?", patientId).find(DBAssess.class);
        List<AssessRecordBean> assesses = new ArrayList<>();
        if (null == assessList){
            LogUtil.i("No local data for patient id!");
            return assesses;
        }
        AssessRecordBean assess = null;
        for (DBAssess dbAssess : assessList){
            assess = DBAssess.convertFrom(dbAssess);
            if (null == assess){
                continue;
            }
            if(assess!=null){
                AssessDefine.DataBean assessDefineById = getAssessDefineById(assess.getAssessDefineId());
                if(assessDefineById == null){
                    continue;
                }
                if(assessDefineById != null&&assessDefineById.getAssessCode().endsWith("RECORD")){
                    continue;
                }
            }
            assesses.add(assess);
        }
        LogUtil.d("Called find Assesses From DB  ! load total [" + assesses.size() + "] record.");
        return assesses;
    }
    @Override
    public AssessRecordBean getAssessById(Integer assessId) {
        if (null == assessId) {
            LogUtil.i("Can not get anything because assess id not present!");
            return null;
        }
        DBAssess db = DataSupport.where("assessId = ?", (assessId + "")).findFirst(DBAssess.class);
        if (db == null) {
            LogUtil.d("Cannot found assess from local DB!");
            return null;
        }
      //  loadAssessChild(assessList.get(0));
        return DBAssess.convertFrom(db);
    }

    @Override
    public void saveAssessDefines(List<AssessDefine.DataBean> aDefines) {
        if (null == aDefines || aDefines.size() <= 0) {
            LogUtil.i("Can not save anything because nothing to be saved!");
            return;
        }
        DBAssessDefine dbDefine = null;
        List<DBAssessDefine> assessDefineList = null;
        for (AssessDefine.DataBean adefine : aDefines) {
            dbDefine = DBAssessDefine.convertFrom(adefine);
            if (null == dbDefine) {
                LogUtil.i("Can not handle anything because cannot convert data into DB format!");
                continue;
            }
//            if (dbDefine.getmTopicDefineList() != null && dbDefine.getmTopicDefineList().size() > 0){
//                for (DBTopicDefine topicDefine : dbDefine.getmTopicDefineList()){
//                    saveTopicDefine(topicDefine);
//                }
//            }
            assessDefineList = DataSupport.where("assessDefineId = ?", (adefine.getId() + "")).find(DBAssessDefine.class);
            if (assessDefineList != null && assessDefineList.size() > 0) {
                dbDefine.updateAll("assessDefineId = ?", (adefine.getId() + ""));
                LogUtil.d("Successfully updated [" + assessDefineList.size() + "] record(s) for assess define table!");
                continue;
            }
            LogUtil.d("Called creation record for assess define table ! The status of save is [" + dbDefine.save() + "]");
        }
    }
//    public void saveTopicDefine(DBTopicDefine topicDefine){
//        if (null == topicDefine) {
//            LogUtil.i("Can not save anything because topicDefine is null!");
//            return;
//        }
//
//        if (topicDefine.getAssessAnswerDefineList() != null && topicDefine.getAssessAnswerDefineList().size() > 0){
//            for (DBAnswerDefine answerRecord : topicDefine.getAssessAnswerDefineList()){
//                saveAnswerDefine(answerRecord);
//            }
//        }
//
//        List<DBTopicDefine> topicDefineList =  DataSupport.where("topicDefineId = ?", (topicDefine.getTopicDefineId() + "")).find(DBTopicDefine.class);
//        if (topicDefineList != null && topicDefineList.size() > 0) {
//            topicDefine.updateAll("topicDefineId = ?", (topicDefine.getTopicDefineId() + ""));
//            LogUtil.d("Successfully updated [" + topicDefineList.size() + "] record(s) for topic define table!");
//            return;
//        }
//        boolean isSaved = topicDefine.save();
//        if (!isSaved){
//            LogUtil.e("Cannot insert topic define into DB table for getTopicDefineId[" + topicDefine.getTopicDefineId() + "]");
//        }else{
//            LogUtil.d("Successfully insert topic define into DB table for getTopicDefineId[" + topicDefine.getTopicDefineId() + "]");
//
//        }
//    }

//    public void saveAnswerDefine(DBAnswerDefine answerDefine){
//        if (null == answerDefine) {
//            LogUtil.i("Can not save anything because answerDefine is null!");
//            return;
//        }
//
//        List<DBAnswerDefine> answerDefineList =  DataSupport.where("anwserDefineId = ?", (answerDefine.getAnwserDefineId() + "")).find(DBAnswerDefine.class);
//        if (answerDefineList != null && answerDefineList.size() > 0) {
//            answerDefine.updateAll("anwserDefineId = ?", (answerDefine.getAnwserDefineId() + ""));
//            LogUtil.d("Successfully updated [" + answerDefineList.size() + "] record(s) for answer define table!");
//            return;
//        }
//
//        boolean isSaved = answerDefine.save();
//        if (!isSaved){
//            LogUtil.e("Cannot insert answer define into DB table for getAnwserDefineId[" + answerDefine.getAnwserDefineId() + "]");
//        }else{
//            LogUtil.d("Successfully insert answer define into DB table for getAnwserDefineId[" + answerDefine.getAnwserDefineId() + "]");
//
//        }
//    }

    @Override
    public void saveAssesses(List<AssessRecordBean> lstAssess) {
        saveAssesses(lstAssess, false);
    }
    public void saveAssesses(List<AssessRecordBean> lstAssess, boolean isModifed) {

        if (!(null == lstAssess || lstAssess.size() <= 0)) {
            for (int iloop=0; iloop<lstAssess.size(); iloop++){
                LogUtil.d("Try to save No.[" + iloop + "] asess[ID:" + lstAssess.get(iloop).getId() + "] to DB.");
                saveAssess(lstAssess.get(iloop), isModifed);
            }
        }else{
            LogUtil.i("Can not save anything because nothing to be saved!");
        }
    }

    @Override
    public void saveAssess(AssessRecordBean assess) {
        saveAssess(assess, false);
    }

    @Override
    public void updateAssessStatus(Integer assessId, String status) {
        List<DBAssess> dbAssessList = DataSupport.where("assessId = ?", assessId.toString()).find(DBAssess.class);
        if (null == dbAssessList || dbAssessList.size() <= 0){
            LogUtil.e("Cannot update anything since no record found in local DB for assessID:" + assessId);
        }else{
            ContentValues values = new ContentValues();
            values.put("status", status);
            for(DBAssess dbAssess : dbAssessList) {
                DataSupport.update(DBAssess.class, values, dbAssess.getId());
            }
            LogUtil.d("Update assessment status to [" + status + "] successfully for assessId:" + assessId);
        }

    }

    public void saveAssess(AssessRecordBean assess, boolean isModified) {
        if (null == assess) {
            LogUtil.i("Can not save anything because assess bean is null!");
            return;
        }
        Integer retval = saveAssess(DBAssess.convertFrom(assess, isModified));
        if (retval != null) assess.setDbId(retval);
    }

    public Integer saveAssess(DBAssess dbAssess){
        if (null == dbAssess) {
            LogUtil.i("Can not handle anything because cannot convert data into DB format!");
            return null;
        }
        DBAssess oldone =  DataSupport.find(DBAssess.class, dbAssess.getId());
        if (oldone != null) {
            dbAssess.update(dbAssess.getId());
            LogUtil.d("Successfully updated record[dbId:" + dbAssess.getId() + "] for assess id[" + dbAssess.getAssessId() + "]!");
        }else {
            if (dbAssess.getAssessId() != null) {
                List<DBAssess> oldLst = DataSupport.where("assessId=?", dbAssess.getAssessId() + "").find(DBAssess.class);
                if (oldLst != null &&  oldLst.size() > 0){
                    dbAssess.updateAll("assessId=?", dbAssess.getAssessId() + "");
                    LogUtil.d("Successfully updated [" + oldLst.size() + "] record(s) using assess id[" + dbAssess.getAssessId() + "]!");
                }else{
                    insertAssess(dbAssess);
                }
            }else {
                insertAssess(dbAssess);
            }
        }
        return dbAssess.getId();
    }
    private void insertAssess(DBAssess dbAssess){
        boolean isSaved = dbAssess.save();
        if (!isSaved) {
            LogUtil.e("Cannot insert assess into DB table for assessId[" + dbAssess.getAssessId() + "]");
        } else {
            LogUtil.d("Successfully insert assess into DB table[" + dbAssess.getId() + "] for assessId[" + dbAssess.getAssessId() + "]");
        }
    }
    @Override
    public Class getDBTable() {
        return DBAssess.class;
    }

    @Override
    public Class getJsonBean() {
        return AssessRecordBean.class;
    }
}
