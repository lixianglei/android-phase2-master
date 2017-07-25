package com.ge.med.mobile.nursing.db;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ge.med.mobile.nursing.forjson.entity.AssessRecordBean;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

/**
 * Created by Alex Qu on 2016/12/2.
 * 患者评估
 */
public class DBAssess extends DataSupport implements IBaseDB{
    private int id;
    private String assessName;
    private int assessdefineid;
    private String createdby;
    private Long creationtime;
    private String executeUserName;
    private Integer assessId;
    private String isdeleted;
    private String lastupdatedby;
    private Long lastupdatetime;
    private String patientid;
    private String result;
    private int score;
    private String status;
    private int userid;
    //private List<DBTopicRecord> topicRecordList;
    private String jsonString;
    private String isModified;

    public String getAssessName() {
        return assessName;
    }

    public void setAssessName(String assessName) {
        this.assessName = assessName;
    }

    public int getAssessdefineid() {
        return assessdefineid;
    }

    public void setAssessdefineid(int assessdefineid) {
        this.assessdefineid = assessdefineid;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Long getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Long creationtime) {
        this.creationtime = creationtime;
    }

    public String getExecuteUserName() {
        return executeUserName;
    }

    public void setExecuteUserName(String executeUserName) {
        this.executeUserName = executeUserName;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Long getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Long lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String isModified() {
        return isModified;
    }
    public String getIsModified() {
        return isModified;
    }

    public void setModified(String modified) {
        isModified = modified;
    }
    public void setIsModified(String modified) {
        isModified = modified;
    }
    public Integer getAssessId() {
        return assessId;
    }
//    public List<DBTopicRecord> getAssessTopicRecordList() {
//        return topicRecordList;
//    }
//
//    public void setAssessTopicRecordList(List<DBTopicRecord> topicRecordList) {
//        this.topicRecordList = topicRecordList;
//    }

    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setAssessId(Integer assessId) {
        this.assessId = assessId;
    }
    /**
     * Convert AssessJSONBean.DataBean into DBAssess
     * @param assess AssessJSONBean.DataBean
     * @return DBAssess
     */
    public static DBAssess convertFrom(AssessRecordBean assess){
        return convertFrom(assess, false);
    }
    public static DBAssess convertFrom(AssessRecordBean assess, boolean isModified){
        if (null == assess){
            LogUtil.i("Can not convert Assess into DBAssess since assess is null.");
            return null;
        }
        DBAssess dbAssess = new DBAssess();
        if (assess.getDbId() != null) dbAssess.setId(assess.getDbId());
        dbAssess.setCreationtime(assess.getCreationTime());
        dbAssess.setModified(Boolean.valueOf(isModified).toString());
        if (null != assess.getId()) dbAssess.setAssessId(assess.getId());
        dbAssess.setAssessdefineid(assess.getAssessDefineId());
        dbAssess.setAssessName(assess.getDescription());
        dbAssess.setExecuteUserName(assess.getUserName());
        dbAssess.setCreatedby(assess.getCreatedBy());
        dbAssess.setIsdeleted(assess.getIsdeleted());
        dbAssess.setLastupdatedby(assess.getLastupdatedby());
        dbAssess.setPatientid(assess.getPatientId());
        dbAssess.setResult(assess.getResult());
        dbAssess.setScore(assess.getScore());
        dbAssess.setStatus(assess.getStatus());
        dbAssess.setUserid(assess.getUserId());
        dbAssess.setJsonString(JSON.toJSONString(assess, SerializerFeature
                .WriteNullListAsEmpty, SerializerFeature.DisableCircularReferenceDetect));
//        if (assess.getAssessTopicRecordList() != null && assess.getAssessTopicRecordList().size() > 0){
//            List<DBTopicRecord> topicRecords = new ArrayList<>();
//            dbAssess.setAssessTopicRecordList(topicRecords);
//            for (AssessRecordBean.AssessTopicRecordListBean topic : assess.getAssessTopicRecordList()){
//                topicRecords.add(DBTopicRecord.convertFrom(topic));
//            }
//        }
        return dbAssess;
    }

    public static AssessRecordBean convertFrom(DBAssess dbAssess){
        if (null == dbAssess){
            LogUtil.i("Can not convert Assess into DBAssess since assess is null.");
            return null;
        }
        if (dbAssess.getJsonString() != null){
            return JSON.parseObject(dbAssess.getJsonString(), AssessRecordBean.class);
        }
        AssessRecordBean assessBean = new AssessRecordBean();
        assessBean.setCreationTime(dbAssess.getCreationtime());
        assessBean.setId(dbAssess.getAssessdefineid());
        assessBean.setDescription(dbAssess.getAssessName());
        assessBean.setCreatedBy(dbAssess.getCreatedby());
        assessBean.setUserName(dbAssess.getExecuteUserName());
        assessBean.setIsdeleted(dbAssess.getIsdeleted());
        assessBean.setLastupdatedby(dbAssess.getLastupdatedby());
        assessBean.setPatientId(dbAssess.getPatientid());
        assessBean.setResult(dbAssess.getResult());
        assessBean.setScore(dbAssess.getScore());
        assessBean.setStatus(dbAssess.getStatus());
        assessBean.setUserId(dbAssess.getUserid());
//        if (dbAssess.getAssessTopicRecordList() != null && dbAssess.getAssessTopicRecordList().size() > 0){
//            List<AssessRecordBean.AssessTopicRecordListBean> topicListBeen = new ArrayList<>();
//            assessBean.setAssessTopicRecordList(topicListBeen);
//            for(DBTopicRecord topicRecord : dbAssess.getAssessTopicRecordList()){
//                topicListBeen.add(DBTopicRecord.convertFrom(topicRecord));
//            }
//        }
        return assessBean;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
