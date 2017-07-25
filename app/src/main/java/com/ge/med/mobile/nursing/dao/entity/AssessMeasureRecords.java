package com.ge.med.mobile.nursing.dao.entity;

import com.ge.med.mobile.nursing.forjson.entity.IBaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * 网络访问实体Bean
 * Created by xxl on 2017/4/16.
 */

public class AssessMeasureRecords implements Serializable ,IBaseBean{


    /**
     * assessMeasureDefineId : 1
     * assessRecordId : 1
     * createdBy :
     * creationTime : 1492072407000
     * id : 1
     * isDeleted : 0
     * lastUpdateBy :
     * lastUpdateTime : 1492072407000
     * measureTopicRecordList : [{"id":1,"measureAnswerRecordList":[{"id":1,"measureAnswerDefineId":1,"measureTopicRecordId":1,"note":""},{"id":41,"measureAnswerDefineId":1,"measureTopicRecordId":1,"note":""}],"measureAssessRecordId":null,"measureTopicDefineId":1,"sortNo":1,"topicType":""},{"id":2,"measureAnswerRecordList":[{"id":2,"measureAnswerDefineId":2,"measureTopicRecordId":2,"note":""},{"id":42,"measureAnswerDefineId":2,"measureTopicRecordId":2,"note":""}],"measureAssessRecordId":null,"measureTopicDefineId":2,"sortNo":2,"topicType":""},{"id":3,"measureAnswerRecordList":[{"id":3,"measureAnswerDefineId":3,"measureTopicRecordId":3,"note":""},{"id":43,"measureAnswerDefineId":3,"measureTopicRecordId":3,"note":""}],"measureAssessRecordId":null,"measureTopicDefineId":3,"sortNo":3,"topicType":""}]
     * patientId : AA1063
     * status : 1
     * time : 1492072407000
     * userId : 3
     */

    private String assessMeasureDefineId;
    private int assessRecordId;
    private String createdBy;
    private long creationTime;
    private int id;
    private int isDeleted;
    private String lastUpdateBy;
    private long lastUpdateTime;
    private String patientId;
    private int status;
    private long time;
    private String userId;
    private Integer dbId;
    private List<MeasureTopicRecordListBean> measureTopicRecordList;

    public String getAssessMeasureDefineId() {
        return assessMeasureDefineId;
    }

    public void setAssessMeasureDefineId(String assessMeasureDefineId) {
        this.assessMeasureDefineId = assessMeasureDefineId;
    }

    public int getAssessRecordId() {
        return assessRecordId;
    }

    public void setAssessRecordId(int assessRecordId) {
        this.assessRecordId = assessRecordId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<MeasureTopicRecordListBean> getMeasureTopicRecordList() {
        return measureTopicRecordList;
    }

    public void setMeasureTopicRecordList(List<MeasureTopicRecordListBean> measureTopicRecordList) {
        this.measureTopicRecordList = measureTopicRecordList;
    }

    @Override
    public void setDbId(Integer id) {
        this.dbId = id;
    }

    public static class MeasureTopicRecordListBean  implements Serializable{
        /**
         * id : 1
         * measureAnswerRecordList : [{"id":1,"measureAnswerDefineId":1,"measureTopicRecordId":1,"note":""},{"id":41,"measureAnswerDefineId":1,"measureTopicRecordId":1,"note":""}]
         * measureAssessRecordId : null
         * measureTopicDefineId : 1
         * sortNo : 1
         * topicType :
         */

        private int id;
        private Object measureAssessRecordId;
        private int measureTopicDefineId;
        private int sortNo;
        private String topicType;
        private List<MeasureAnswerRecordListBean> measureAnswerRecordList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getMeasureAssessRecordId() {
            return measureAssessRecordId;
        }

        public void setMeasureAssessRecordId(Object measureAssessRecordId) {
            this.measureAssessRecordId = measureAssessRecordId;
        }

        public int getMeasureTopicDefineId() {
            return measureTopicDefineId;
        }

        public void setMeasureTopicDefineId(int measureTopicDefineId) {
            this.measureTopicDefineId = measureTopicDefineId;
        }

        public int getSortNo() {
            return sortNo;
        }

        public void setSortNo(int sortNo) {
            this.sortNo = sortNo;
        }

        public String getTopicType() {
            return topicType;
        }

        public void setTopicType(String topicType) {
            this.topicType = topicType;
        }

        public List<MeasureAnswerRecordListBean> getMeasureAnswerRecordList() {
            return measureAnswerRecordList;
        }

        public void setMeasureAnswerRecordList(List<MeasureAnswerRecordListBean> measureAnswerRecordList) {
            this.measureAnswerRecordList = measureAnswerRecordList;
        }

        public static class MeasureAnswerRecordListBean  implements Serializable{
            /**
             * id : 1
             * measureAnswerDefineId : 1
             * measureTopicRecordId : 1
             * note :
             */

            private int id;
            private int measureAnswerDefineId;
            private int measureTopicRecordId;
            private String note;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMeasureAnswerDefineId() {
                return measureAnswerDefineId;
            }

            public void setMeasureAnswerDefineId(int measureAnswerDefineId) {
                this.measureAnswerDefineId = measureAnswerDefineId;
            }

            public int getMeasureTopicRecordId() {
                return measureTopicRecordId;
            }

            public void setMeasureTopicRecordId(int measureTopicRecordId) {
                this.measureTopicRecordId = measureTopicRecordId;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }
        }
    }
}
