package com.ge.med.mobile.nursing.forjson.entity;

import com.ge.med.mobile.nursing.dao.entity.AssessMeasureRecords;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Qu on 2016/12/9.
 */
public class AssessRecordBean implements Serializable, IBaseBean{
    private String  description;
    private int assessDefineId;
    private String createdBy;
    private String userName;
    private Integer id;
    private Long creationTime;
    private String isdeleted;
    private String lastupdatedby;
    private Long lastUpdateTime;
    private String patientId;
    private String result;
    private String resultDescription;
    private int score;
    private String status;
    private int userId;
    private List<AssessTopicRecordListBean> assessTopicRecordList;
    private Integer dbId;
    private AssessMeasureRecords assessMeasureRecord;
    private transient Map<Integer,List<AssessTopicRecordListBean>> assessRecorMap;
    private transient Map<Integer,Map<Integer,AssessTopicRecordListBean>> assessRecorMapTengTong;

    public AssessMeasureRecords getAssessMeasureRecord() {
        return assessMeasureRecord;
    }

    public void setAssessMeasureRecord(AssessMeasureRecords assessMeasureRecord) {
        this.assessMeasureRecord = assessMeasureRecord;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    public Map<Integer, Map<Integer, AssessTopicRecordListBean>> getAssessRecorMapTengTong() {
        return assessRecorMapTengTong;
    }

    public void setAssessRecorMapTengTong(Map<Integer, Map<Integer, AssessTopicRecordListBean>> assessRecorMapTengTong) {
        this.assessRecorMapTengTong = assessRecorMapTengTong;
    }

    public Map<Integer, List<AssessTopicRecordListBean>> getAssessRecorMap() {
        return assessRecorMap;
    }

    public void setAssessRecorMap(Map<Integer, List<AssessTopicRecordListBean>> assessRecorMap) {
        this.assessRecorMap = assessRecorMap;
    }

    /**
     * assessid : 1
     * createdBy : 系统
     * creationTime : 1480821582103
     * id : 1
     * isdeleted : 0
     * lastUpdatedBy :
     * lastUpdateTime : null
     * record : []
     * topicDefineId : 1
     * value :
     */



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAssessDefineId() {
        return assessDefineId;
    }

    public void setAssessDefineId(int assessDefineId) {
        this.assessDefineId = assessDefineId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<AssessTopicRecordListBean> getAssessTopicRecordList() {
        return assessTopicRecordList;
    }

    public void setAssessTopicRecordList(List<AssessTopicRecordListBean> assessTopicRecordList) {
        this.assessTopicRecordList = assessTopicRecordList;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public Integer getDbId() {
        return dbId;
    }

    @Override
    public void setDbId(Integer dbId) {
        this.dbId = dbId;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public static class AssessTopicRecordListBean implements Serializable {
        private Integer assessid;
        private String createdBy;
        private Integer id;
        private String isdeleted;
        private String lastUpdatedBy;
        private Integer topicDefineId;
        private String value;
        private List<AssessAnswerRecordListBean> assessAnswerRecordList;
        private transient Map<Integer ,AssessAnswerRecordListBean> answerRecordListMap;
        private Integer parentAnswerDefineId;

        public Integer getParentAnswerDefineId() {
            return parentAnswerDefineId;
        }

        public void setParentAnswerDefineId(Integer parentAnswerDefineId) {
            this.parentAnswerDefineId = parentAnswerDefineId;
        }

        public Map<Integer, AssessAnswerRecordListBean> getAnswerRecordListMap() {
            return answerRecordListMap;
        }

        public void setAnswerRecordListMap(Map<Integer, AssessAnswerRecordListBean> answerRecordListMap) {
            this.answerRecordListMap = answerRecordListMap;
        }

        public Integer getAssessid() {
            return assessid;
        }

        public void setAssessid(Integer assessid) {
            this.assessid = assessid;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getIsdeleted() {
            return isdeleted;
        }

        public void setIsdeleted(String isdeleted) {
            this.isdeleted = isdeleted;
        }

        public String getLastUpdatedBy() {
            return lastUpdatedBy;
        }

        public void setLastUpdatedBy(String lastUpdatedBy) {
            this.lastUpdatedBy = lastUpdatedBy;
        }

        public Integer getTopicDefineId() {
            return topicDefineId;
        }

        public void setTopicDefineId(Integer topicDefineId) {
            this.topicDefineId = topicDefineId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<AssessAnswerRecordListBean> getAssessAnswerRecordList() {
            return assessAnswerRecordList;
        }

        public void setAssessAnswerRecordList(List<AssessAnswerRecordListBean> assessAnswerRecordList) {
            this.assessAnswerRecordList = assessAnswerRecordList;
        }

        public static class AssessAnswerRecordListBean implements Serializable{
            private Integer id;
            private Integer answerDefineId;
            private String answerflag;
            private String timeValue;
            private Integer toprecordid;

            public String getTimeValue() {

                return timeValue;
            }

            public void setTimeValue(String timeValue) {
                this.timeValue = timeValue;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer Integer) {
                this.id = id;
            }

            public Integer getAnswerDefineId() {
                return answerDefineId;
            }

            public void setAnswerDefineId(Integer answerDefineId) {
                this.answerDefineId = answerDefineId;
            }

            public String getAnswerflag() {
                return answerflag;
            }

            public void setAnswerflag(String answerflag) {
                this.answerflag = answerflag;
            }

            public Integer getToprecordid() {
                return toprecordid;
            }

            public void setToprecordid(Integer toprecordid) {
                this.toprecordid = toprecordid;
            }
        }

    }
}
