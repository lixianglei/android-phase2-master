package com.ge.med.mobile.nursing.db;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

/**
 * Created by Administrator on 2016/12/2.
 */
public class DBAssessDefine extends DataSupport {
    private String createdby;
    private long creationtime;
    private String icom;
    private int assessDefineId;
    private String isdeleted;
    private String lastupdatedby;
    private long lastupdatetime;
    private String name;
    private String note;
    private int rangeend;
    private int rangestart;
    private int riskid;
    private int topictotalno;
    private String wardId;
    private String jsonString;
    //private List<DBTopicDefine> topicDefineList;


    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public long getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(long creationtime) {
        this.creationtime = creationtime;
    }

    public String getIcom() {
        return icom;
    }

    public void setIcom(String icom) {
        this.icom = icom;
    }

    public int getAssessDefineId() {
        return assessDefineId;
    }

    public void setAssessDefineId(int aid) {
        this.assessDefineId = aid;
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

    public long getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(long lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getRangeend() {
        return rangeend;
    }

    public void setRangeend(int rangeend) {
        this.rangeend = rangeend;
    }

    public int getRangestart() {
        return rangestart;
    }

    public void setRangestart(int rangestart) {
        this.rangestart = rangestart;
    }

    public int getRiskid() {
        return riskid;
    }

    public void setRiskid(int riskid) {
        this.riskid = riskid;
    }

    public int getTopictotalno() {
        return topictotalno;
    }

    public void setTopictotalno(int topictotalno) {
        this.topictotalno = topictotalno;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    //    public List<DBTopicDefine> getmTopicDefineList() {
//        return topicDefineList;
//    }
//
//    public void setAssessTopicDefineList(List<DBTopicDefine> topicDefineList) {
//        this.topicDefineList = topicDefineList;
//    }

    public static DBAssessDefine convertFrom(AssessDefine.DataBean aDefine){
        DBAssessDefine assessDefine = new DBAssessDefine();
        assessDefine.setCreatedby(aDefine.getCreatedBy());
        assessDefine.setCreationtime(aDefine.getCreationTime());
        assessDefine.setIcom(aDefine.getIcom());
        assessDefine.setAssessDefineId(aDefine.getId());
        assessDefine.setIsdeleted(aDefine.getIsDeleted());
        assessDefine.setLastupdatedby(aDefine.getLastUpdatedBy());
        try {
            assessDefine.setLastupdatetime((long) aDefine.getLastUpdateTime());
        }catch(Exception e){
            LogUtil.e("Last update time cannot convert to long, error message is [" + e.getMessage() + "].");
        }
        assessDefine.setName(aDefine.getName());
        assessDefine.setNote(aDefine.getNote());
        try {
            assessDefine.setRangeend((int)aDefine.getRangeEnd());
        }catch(Exception e){
            LogUtil.e("Range end cannot convert to int, error message is [" + e.getMessage() + "].");
        }
        try {
            assessDefine.setRangestart((int)aDefine.getRangeStart());
        }catch(Exception e){
            LogUtil.e("Range start cannot convert to int, error message is [" + e.getMessage() + "].");
        }
        try {
            assessDefine.setRiskid(aDefine.getRiskId());
        }catch(Exception e){
            LogUtil.e("Risk Id cannot convert to String, error message is [" + e.getMessage() + "].");
        }
        try {
            assessDefine.setTopictotalno((int)aDefine.getTopicTotalNo());
        }catch(Exception e){
            LogUtil.e("Topic no cannot convert to int, error message is [" + e.getMessage() + "].");
        }
        assessDefine.setWardId(aDefine.getWardId());
        assessDefine.setJsonString(JSON.toJSONString(aDefine, SerializerFeature
                .WriteNullListAsEmpty, SerializerFeature.DisableCircularReferenceDetect));
//        if (aDefine.getmTopicDefineList() != null && aDefine.getmTopicDefineList().size() > 0){
//            List<DBTopicDefine>  topicDefineList = new ArrayList<>();
//            assessDefine.setAssessTopicDefineList(topicDefineList);
//            for (AssessDefineJSONBean.DataBean.AssessTopicDefineListBean topicDefine : aDefine.getmTopicDefineList()){
//                topicDefineList.add(DBTopicDefine.convertFrom(topicDefine));
//            }
//        }
        return assessDefine;
    }
    public static AssessDefine.DataBean convertFrom(DBAssessDefine aDefine){
        if (aDefine == null){
            LogUtil.i("Can not convert assessdefine from db since it is null.");
        }
        if (aDefine.getJsonString() != null){
            return JSON.parseObject(aDefine.getJsonString(), AssessDefine.DataBean.class);
        }
        return null;
//        AssessDefineJSONBean.DataBean assessDefine = new AssessDefineJSONBean.DataBean();
//        assessDefine.setCreatedBy(aDefine.getCreatedBy());
//        assessDefine.setCreationTime(aDefine.getCreationTime());
//        assessDefine.setIcom(aDefine.getIcom());
//        assessDefine.setId(aDefine.getAssessDefineId());
//        assessDefine.setIsdeleted(aDefine.getIsdeleted());
//        assessDefine.setLastUpdatedBy(aDefine.getLastUpdatedBy());
//        try {
//            assessDefine.setLastUpdateTime((long) aDefine.getLastUpdateTime());
//        }catch(Exception e){
//            LogUtil.e("Last update time cannot convert to long, error message is [" + e.getMessage() + "].");
//        }
//        assessDefine.setName(aDefine.getName());
//        assessDefine.setNote(aDefine.getNote());
//        try {
//            assessDefine.setRangeend((int)aDefine.getRangeend());
//        }catch(Exception e){
//            LogUtil.e("Range end cannot convert to int, error message is [" + e.getMessage() + "].");
//        }
//        try {
//            assessDefine.setRangestart((int)aDefine.getRangestart());
//        }catch(Exception e){
//            LogUtil.e("Range start cannot convert to int, error message is [" + e.getMessage() + "].");
//        }
//        try {
//            assessDefine.setRiskid(aDefine.getRiskid());
//        }catch(Exception e){
//            LogUtil.e("Risk Id cannot convert to String, error message is [" + e.getMessage() + "].");
//        }
//        try {
//            assessDefine.setTopictotalno((int)aDefine.getTopictotalno());
//        }catch(Exception e){
//            LogUtil.e("Topic no cannot convert to int, error message is [" + e.getMessage() + "].");
//        }
//        assessDefine.setWardid(aDefine.getWardId());
//        if (aDefine.getmTopicDefineList() != null && aDefine.getmTopicDefineList().size() > 0){
//            List<AssessDefineJSONBean.DataBean.AssessTopicDefineListBean> topicDefineListBeen = new ArrayList<>();
//            assessDefine.setAssessTopicDefineList(topicDefineListBeen);
//            for(DBTopicDefine topicDefine : aDefine.getmTopicDefineList()){
//                topicDefineListBeen.add(DBTopicDefine.convertFrom(topicDefine));
//            }
//        }
 //       return assessDefine;
    }


    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
