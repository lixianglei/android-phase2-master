package com.ge.med.mobile.nursing.dao.entity;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.forjson.entity.BaseJSONBean;
import com.ge.med.mobile.nursing.forjson.entity.NetworkForImage;

import org.xutils.common.util.LogUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 网络访问实体Bean
 * Created by Administrator on 2017/3/1.
 */
public class AssessDefine implements BaseJSONBean, Serializable {
    private String msg;
    private Integer status;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    @Override
    public String getCallName() {
        return "load Assess Define";
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private Object assess;
        private String createdBy;
        private long creationTime;
        private String icom;
        private int id;
        private String isDeleted;
        private Object lastUpdateTime;
        private String lastUpdatedBy;
        private String name;//评估问卷名字
        private String note;//评估问卷注释
        private Object rangeEnd;
        private Object rangeStart;
        private int resultCheckType;
        private int riskId;
        private Object topicClassifylist;
        private int topicTotalNo;
        private String wardId;
        private List<AssessTopicDefineListBean> assessTopicDefineList;
        private String assessCode;
        private int sortNumber;

        public int getSortNumber() {
            return sortNumber;
        }

        public void setSortNumber(int sortNumber) {
            this.sortNumber = sortNumber;
        }

        public String getAssessCode() {
            return assessCode;
        }

        public void setAssessCode(String assessCode) {
            this.assessCode = assessCode;
        }

        public Object getAssess() {
            return assess;
        }

        public void setAssess(Object assess) {
            this.assess = assess;
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

        public String getIcom() {
            return icom;
        }

        public void setIcom(String icom) {
            this.icom = icom;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }

        public Object getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(Object lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public String getLastUpdatedBy() {
            return lastUpdatedBy;
        }

        public void setLastUpdatedBy(String lastUpdatedBy) {
            this.lastUpdatedBy = lastUpdatedBy;
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

        public Object getRangeEnd() {
            return rangeEnd;
        }

        public void setRangeEnd(Object rangeEnd) {
            this.rangeEnd = rangeEnd;
        }

        public Object getRangeStart() {
            return rangeStart;
        }

        public void setRangeStart(Object rangeStart) {
            this.rangeStart = rangeStart;
        }

        public int getResultCheckType() {
            return resultCheckType;
        }

        public void setResultCheckType(int resultCheckType) {
            this.resultCheckType = resultCheckType;
        }

        public int getRiskId() {
            return riskId;
        }

        public void setRiskId(int riskId) {
            this.riskId = riskId;
        }

        public Object getTopicClassifylist() {
            return topicClassifylist;
        }

        public void setTopicClassifylist(Object topicClassifylist) {
            this.topicClassifylist = topicClassifylist;
        }

        public int getTopicTotalNo() {
            return topicTotalNo;
        }

        public void setTopicTotalNo(int topicTotalNo) {
            this.topicTotalNo = topicTotalNo;
        }

        public List<AssessTopicDefineListBean> getAssessTopicDefineList() {
            return assessTopicDefineList;
        }

        public String getWardId() {
            return wardId;
        }

        public void setWardId(String wardId) {
            this.wardId = wardId;
        }

        public void setAssessTopicDefineList(List<AssessTopicDefineListBean> assessTopicDefineList) {
            this.assessTopicDefineList = assessTopicDefineList;
        }

        public static class AssessTopicDefineListBean implements Serializable, NetworkForImage.IImageBean {
            private Integer appPicture;//图片ID
            private int assessDefineId;
            private int classifyType;//是否是分组 0 是 1 不是  2 不用管
            private int countScoreType;
            private String createdBy;
            private Object creationTime;
            private int id;
            private int initType;//题目初始化显不显示 0 显示
            private String isDeleted;
            private Object lastUpdateTime;
            private String lastUpdatedBy;
            private Object lineLimit;
            private String name;//题目的名字
            private String picture;
            private List<AssessTopicDefineListBean> relateTopicList;//分组的题
            private int relateType;
            private int requested;//0 必答 1 不是必答
            private Object sortNo;
            private String topicType;//题目答案类型  0 单选  1 多选   2 文本 3 时间
            private List<AssessAnswerDefineListBean> assessAnswerDefineList;//答案选项
            private List<?> assessTopicDefineClassifyList;
            private List<OptionList> optionList;
            private String imageString;
            private String topicCode;
            private String fatherTitle;//父标题

            public List<AssessTopicDefineListBean> getRelateTopicList() {
                return relateTopicList;
            }

            public void setRelateTopicList(List<AssessTopicDefineListBean> relateTopicList) {
                this.relateTopicList = relateTopicList;
            }

            public List<OptionList> getOptionList() {
                return optionList;
            }

            public String getTopicCode() {
                return topicCode;
            }

            public void setTopicCode(String topicCode) {
                this.topicCode = topicCode;
            }

            public void setOptionList(List<OptionList> optionList) {
                this.optionList = optionList;
            }
            //// TODO: 2017/3/8 下挂一个哪些题目和这道题的显示隐藏有关的一个答案id list 内含答案所在题的id

            public String getFatherTitle() {
                return fatherTitle;
            }

            public void setFatherTitle(String fatherTitle) {
                this.fatherTitle = fatherTitle;
            }

            public String getImageString() {
                return imageString;
            }

            public Integer getAppPicture() {
                return appPicture;
            }

            public void setAppPicture(Integer appPicture) {
                this.appPicture = appPicture;
            }

            public int getAssessDefineId() {
                return assessDefineId;
            }

            public void setAssessDefineId(int assessDefineId) {
                this.assessDefineId = assessDefineId;
            }

            public int getClassifyType() {
                return classifyType;
            }

            public void setClassifyType(int classifyType) {
                this.classifyType = classifyType;
            }

            public int getCountScoreType() {
                return countScoreType;
            }

            public void setCountScoreType(int countScoreType) {
                this.countScoreType = countScoreType;
            }

            public String getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
            }

            public Object getCreationTime() {
                return creationTime;
            }

            public void setCreationTime(Object creationTime) {
                this.creationTime = creationTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getInitType() {
                return initType;
            }

            public void setInitType(int initType) {
                this.initType = initType;
            }

            public String getIsDeleted() {
                return isDeleted;
            }

            public void setIsDeleted(String isDeleted) {
                this.isDeleted = isDeleted;
            }

            public Object getLastUpdateTime() {
                return lastUpdateTime;
            }

            public void setLastUpdateTime(Object lastUpdateTime) {
                this.lastUpdateTime = lastUpdateTime;
            }

            public String getLastUpdatedBy() {
                return lastUpdatedBy;
            }

            public void setLastUpdatedBy(String lastUpdatedBy) {
                this.lastUpdatedBy = lastUpdatedBy;
            }

            public Object getLineLimit() {
                return lineLimit;
            }

            public void setLineLimit(Object lineLimit) {
                this.lineLimit = lineLimit;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }


            public int getRelateType() {
                return relateType;
            }

            public void setRelateType(int relateType) {
                this.relateType = relateType;
            }

            public int getRequested() {
                return requested;
            }

            public void setRequested(int requested) {
                this.requested = requested;
            }

            public Object getSortNo() {
                return sortNo;
            }

            public void setSortNo(Object sortNo) {
                this.sortNo = sortNo;
            }

            public String getTopicType() {
                return topicType;
            }

            public void setTopicType(String topicType) {
                this.topicType = topicType;
            }

            public List<AssessAnswerDefineListBean> getAssessAnswerDefineList() {
                return assessAnswerDefineList;
            }

            public void setAssessAnswerDefineList(List<AssessAnswerDefineListBean> assessAnswerDefineList) {
                this.assessAnswerDefineList = assessAnswerDefineList;
            }

            public List<?> getAssessTopicDefineClassifyList() {
                return assessTopicDefineClassifyList;
            }

            public void setAssessTopicDefineClassifyList(List<?> assessTopicDefineClassifyList) {
                this.assessTopicDefineClassifyList = assessTopicDefineClassifyList;
            }

            @Override
            public Integer getImageId() {
                Integer retval = null;
                if (appPicture != null) {
                    retval = appPicture;
                } else if (picture != null && !picture.trim().isEmpty()) {
                    try {
                        retval = Integer.parseInt(picture);
                    } catch (NumberFormatException e) {
                        retval = null;
                        LogUtil.e("Topic[" + id + "]'s picture is not an integer[" + picture + "]!");
                    }
                }
                return retval;
            }

            @Override
            public void setImageString(String str) {
                this.imageString = str;
            }

            public static class AssessAnswerDefineListBean implements Serializable, NetworkForImage.IImageBean {
                private Integer androidImage;
                private Integer answerType;
                private Integer attribute;
                private Object classifyTopicDefineId;
                private String comment;
                private String createdBy;
                private Object creationTime;
                private int id;
                private int initType;
                private String isDeleted;
                private Object lastUpdateTime;
                private String lastUpdatedBy;
                private Integer needNote;
                private Object relateAnswerDefineId;
                private int relateTo;//判断和其他题或者选项 有无关联关系 0 没有 1 和题 2 选项
                private int score;
                private int topicDefineId;
                private String value;
                private String imageString;
                private String answerDefineCode;
                // 压疮评估说明
                private String instruction;

                public String getInstruction() {
                    return instruction;
                }

                public void setInstruction(String instruction) {
                    this.instruction = instruction;
                }

                public String getAnswerDefineCode() {
                    return answerDefineCode;
                }

                public void setAnswerDefineCode(String answerDefineCode) {
                    this.answerDefineCode = answerDefineCode;
                }

                public String getImageString() {
                    return imageString;
                }

                private List<AssessAnswerRelateAnswerListBean> assessAnswerRelateAnswerList;//如果relateTo = 2  找这个

                private List<AssessAnswerTopicRelationListBean> assessAnswerTopicRelationList; //如果relateTo = 1  找这个

                public Integer getAndroidImage() {
                    return androidImage;
                }

                public void setAndroidImage(Integer androidImage) {
                    this.androidImage = androidImage;
                }

                public Integer getAnswerType() {
                    return answerType;
                }

                public void setAnswerType(Integer answerType) {
                    this.answerType = answerType;
                }

                public Integer getAttribute() {
                    return attribute;
                }

                public void setAttribute(Integer attribute) {
                    this.attribute = attribute;
                }

                public Object getClassifyTopicDefineId() {
                    return classifyTopicDefineId;
                }

                public void setClassifyTopicDefineId(Object classifyTopicDefineId) {
                    this.classifyTopicDefineId = classifyTopicDefineId;
                }

                public String getComment() {
                    return comment;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }

                public String getCreatedBy() {
                    return createdBy;
                }

                public void setCreatedBy(String createdBy) {
                    this.createdBy = createdBy;
                }

                public Object getCreationTime() {
                    return creationTime;
                }

                public void setCreationTime(Object creationTime) {
                    this.creationTime = creationTime;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getInitType() {
                    return initType;
                }

                public void setInitType(int initType) {
                    this.initType = initType;
                }

                public String getIsDeleted() {
                    return isDeleted;
                }

                public void setIsDeleted(String isDeleted) {
                    this.isDeleted = isDeleted;
                }

                public Object getLastUpdateTime() {
                    return lastUpdateTime;
                }

                public void setLastUpdateTime(Object lastUpdateTime) {
                    this.lastUpdateTime = lastUpdateTime;
                }

                public String getLastUpdatedBy() {
                    return lastUpdatedBy;
                }

                public void setLastUpdatedBy(String lastUpdatedBy) {
                    this.lastUpdatedBy = lastUpdatedBy;
                }

                public Integer getNeedNote() {
                    return needNote;
                }

                public void setNeedNote(Integer needNote) {
                    this.needNote = needNote;
                }

                public Object getRelateAnswerDefineId() {
                    return relateAnswerDefineId;
                }

                public void setRelateAnswerDefineId(Object relateAnswerDefineId) {
                    this.relateAnswerDefineId = relateAnswerDefineId;
                }

                public int getRelateTo() {
                    return relateTo;
                }

                public void setRelateTo(int relateTo) {
                    this.relateTo = relateTo;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }

                public int getTopicDefineId() {
                    return topicDefineId;
                }

                public void setTopicDefineId(int topicDefineId) {
                    this.topicDefineId = topicDefineId;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public List<AssessAnswerRelateAnswerListBean> getAssessAnswerRelateAnswerList() {
                    return assessAnswerRelateAnswerList;
                }

                public void setAssessAnswerRelateAnswerList(List<AssessAnswerRelateAnswerListBean> assessAnswerRelateAnswerList) {
                    this.assessAnswerRelateAnswerList = assessAnswerRelateAnswerList;
                }

                public List<AssessAnswerTopicRelationListBean> getAssessAnswerTopicRelationList() {
                    return assessAnswerTopicRelationList;
                }

                public void setAssessAnswerTopicRelationList(List<AssessAnswerTopicRelationListBean> assessAnswerTopicRelationList) {
                    this.assessAnswerTopicRelationList = assessAnswerTopicRelationList;
                }

                @Override
                public Integer getImageId() {
                    Integer retval = null;
                    if (answerType != null && Constant.ANSWER_TYPE_PICTURE == answerType) {
                        if (androidImage != null) {
                            retval = androidImage;
                        } else if (value != null && !value.trim().isEmpty()) {
                            try {
                                retval = Integer.parseInt(value);
                            } catch (NumberFormatException e) {
                                retval = null;
                                LogUtil.e("Answer[" + id + "]'s value is not an integer[" + value + "]!");
                            }
                        }
                    }
                    return retval;
                }

                @Override
                public void setImageString(String str) {
                    this.imageString = str;
                }

                public static class AssessAnswerRelateAnswerListBean implements Serializable {
                    private int answerDefineId;
                    private int displayType;// 先不显示 0显示 1 不显示
                    private int id;
                    private int relateAnswerDefineId;
                    private int relateTopicDefineId;

                    public int getAnswerDefineId() {
                        return answerDefineId;
                    }

                    public void setAnswerDefineId(int answerDefineId) {
                        this.answerDefineId = answerDefineId;
                    }

                    public int getDisplayType() {
                        return displayType;
                    }

                    public void setDisplayType(int displayType) {
                        this.displayType = displayType;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getRelateAnswerDefineId() {
                        return relateAnswerDefineId;
                    }

                    public void setRelateAnswerDefineId(int relateAnswerDefineId) {
                        this.relateAnswerDefineId = relateAnswerDefineId;
                    }

                    public int getRelateTopicDefineId() {
                        return relateTopicDefineId;
                    }

                    public void setRelateTopicDefineId(int relateTopicDefineId) {
                        this.relateTopicDefineId = relateTopicDefineId;
                    }
                }

                public static class AssessAnswerTopicRelationListBean implements Serializable {
                    private Object answerDefineId;
                    private int attribute;// 先不显示 0 不显示 1 不重复显示  2 重复显示
                    private int id;
                    private int relateTopicDefineId;

                    public Object getAnswerDefineId() {
                        return answerDefineId;
                    }

                    public void setAnswerDefineId(Object answerDefineId) {
                        this.answerDefineId = answerDefineId;
                    }

                    public int getAttribute() {
                        return attribute;
                    }

                    public void setAttribute(int attribute) {
                        this.attribute = attribute;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getRelateTopicDefineId() {
                        return relateTopicDefineId;
                    }

                    public void setRelateTopicDefineId(int relateTopicDefineId) {
                        this.relateTopicDefineId = relateTopicDefineId;
                    }
                }
            }
        }
    }

    /**
     * Created by Administrator on 2017/3/9.
     */
    public static class OptionList implements Serializable {
        private Integer answerDefineId;
        private Integer topicDefineId;

        public Integer getAnswerDefineId() {
            return answerDefineId;
        }

        public void setAnswerDefineId(Integer answerDefineId) {
            this.answerDefineId = answerDefineId;
        }

        public Integer getTopicDefineId() {
            return topicDefineId;
        }

        public void setTopicDefineId(Integer topicDefineId) {
            this.topicDefineId = topicDefineId;
        }
    }
}
