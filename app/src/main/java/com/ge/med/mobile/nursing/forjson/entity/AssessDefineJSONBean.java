package com.ge.med.mobile.nursing.forjson.entity;

import com.ge.med.mobile.nursing.Constant;

import org.xutils.common.util.LogUtil;

import java.io.Serializable;
import java.util.List;

/**
 根据病区获取评估定义列表
 /assess/getWardDefine?wardId=1  GET

 * Created by Administrator on 2016/12/4.
 */
public class AssessDefineJSONBean implements BaseJSONBean{
    /**
     * status : 1
     * data : [{"assess":null,"createdby":"系统","creationtime":1480820830790,"icom":"","id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"入院","note":"","rangeend":null,"rangestart":null,"riskid":null,"topicDefineList":[{"answerDefineList":[{"answertype":null,"createdby":"系统","creationtime":1480821613310,"id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":20,"topicdefineid":1,"value":""},{"answertype":null,"createdby":"系统","creationtime":1480821621964,"id":2,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":15,"topicdefineid":1,"value":""},{"answertype":null,"createdby":"系统","creationtime":1480821622586,"id":3,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":10,"topicdefineid":1,"value":""}],"answerRecordList":null,"assessdefineid":1,"createdby":"系统","creationtime":1480821564474,"id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"","picture":"","topictype":""},{"answerDefineList":[{"answertype":null,"createdby":"系统","creationtime":1480821654984,"id":4,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":20,"topicdefineid":2,"value":""},{"answertype":null,"createdby":"系统","creationtime":1480821655424,"id":5,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":15,"topicdefineid":2,"value":""},{"answertype":null,"createdby":"系统","creationtime":1480821657487,"id":6,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":10,"topicdefineid":2,"value":""}],"answerRecordList":null,"assessdefineid":1,"createdby":"系统","creationtime":1480821566113,"id":2,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"","picture":"","topictype":""},{"answerDefineList":[{"answertype":null,"createdby":"系统","creationtime":1480821673639,"id":7,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":20,"topicdefineid":3,"value":""},{"answertype":null,"createdby":"系统","creationtime":1480821673959,"id":8,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":15,"topicdefineid":3,"value":""},{"answertype":null,"createdby":"系统","creationtime":1480821675083,"id":9,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":10,"topicdefineid":3,"value":""}],"answerRecordList":null,"assessdefineid":1,"createdby":"系统","creationtime":1480821566648,"id":3,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"","picture":"","topictype":""},{"answerDefineList":[],"answerRecordList":null,"assessdefineid":1,"createdby":"系统","creationtime":1480821568538,"id":5,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"","picture":"","topictype":""},{"answerDefineList":[],"answerRecordList":null,"assessdefineid":1,"createdby":"系统","creationtime":1480821567319,"id":4,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"","picture":"","topictype":""}],"topictotalno":null,"wardid":1},{"assess":null,"createdby":"系统","creationtime":1480820833025,"icom":"","id":2,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"生活","note":"","rangeend":null,"rangestart":null,"riskid":null,"topicDefineList":[],"topictotalno":null,"wardid":1},{"assess":null,"createdby":"系统","creationtime":1480820835986,"icom":"","id":5,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"aa","note":"","rangeend":null,"rangestart":null,"riskid":null,"topicDefineList":[],"topictotalno":null,"wardid":1},{"assess":null,"createdby":"系统","creationtime":1480820834847,"icom":"","id":4,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"手术","note":"","rangeend":null,"rangestart":null,"riskid":null,"topicDefineList":[],"topictotalno":null,"wardid":1},{"assess":null,"createdby":"系统","creationtime":1480820834280,"icom":"","id":3,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"检验","note":"","rangeend":null,"rangestart":null,"riskid":null,"topicDefineList":[],"topictotalno":null,"wardid":1}]
     * msg : 成功
     */

    private Integer status;
    private String msg;
    /**
     * assess : null
     * createdby : 系统
     * creationtime : 1480820830790
     * icom :
     * id : 1
     * isdeleted : 0
     * lastupdatedby :
     * lastupdatetime : null
     * name : 入院
     * note :
     * rangeend : null
     * rangestart : null
     * riskid : null
     * topicDefineList : [{"answerDefineList":[{"answertype":null,"createdby":"系统","creationtime":1480821613310,"id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":20,"topicdefineid":1,"value":""},{"answertype":null,"createdby":"系统","creationtime":1480821621964,"id":2,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":15,"topicdefineid":1,"value":""},{"answertype":null,"createdby":"系统","creationtime":1480821622586,"id":3,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":10,"topicdefineid":1,"value":""}],"answerRecordList":null,"assessdefineid":1,"createdby":"系统","creationtime":1480821564474,"id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"","picture":"","topictype":""},{"answerDefineList":[{"answertype":null,"createdby":"系统","creationtime":1480821654984,"id":4,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":20,"topicdefineid":2,"value":""},{"answertype":null,"createdby":"系统","creationtime":1480821655424,"id":5,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":15,"topicdefineid":2,"value":""},{"answertype":null,"createdby":"系统","creationtime":1480821657487,"id":6,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":10,"topicdefineid":2,"value":""}],"answerRecordList":null,"assessdefineid":1,"createdby":"系统","creationtime":1480821566113,"id":2,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"","picture":"","topictype":""},{"answerDefineList":[{"answertype":null,"createdby":"系统","creationtime":1480821673639,"id":7,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":20,"topicdefineid":3,"value":""},{"answertype":null,"createdby":"系统","creationtime":1480821673959,"id":8,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":15,"topicdefineid":3,"value":""},{"answertype":null,"createdby":"系统","creationtime":1480821675083,"id":9,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":10,"topicdefineid":3,"value":""}],"answerRecordList":null,"assessdefineid":1,"createdby":"系统","creationtime":1480821566648,"id":3,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"","picture":"","topictype":""},{"answerDefineList":[],"answerRecordList":null,"assessdefineid":1,"createdby":"系统","creationtime":1480821568538,"id":5,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"","picture":"","topictype":""},{"answerDefineList":[],"answerRecordList":null,"assessdefineid":1,"createdby":"系统","creationtime":1480821567319,"id":4,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"","picture":"","topictype":""}]
     * topictotalno : null
     * wardid : 1
     */

    private List<DataBean> data;
@Override
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    @Override
    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String getCallName() {
        return "load Assess Define";
    }

    public static class DataBean implements Serializable{
        private Object assess;
        private String createdby;
        private long creationtime;
        private String icom;
        private int id;
        private String isdeleted;
        private String lastupdatedby;
        private long lastupdatetime;
        private String name;
        private String note;
        private int rangeend;
        private int rangestart;
        private int riskid;
        private int topictotalno;
        private String wardid;
        /**
         * answerDefineList : [{"answertype":null,"createdby":"系统","creationtime":1480821613310,"id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":20,"topicdefineid":1,"value":""},{"answertype":null,"createdby":"系统","creationtime":1480821621964,"id":2,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":15,"topicdefineid":1,"value":""},{"answertype":null,"createdby":"系统","creationtime":1480821622586,"id":3,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"score":10,"topicdefineid":1,"value":""}]
         * answerRecordList : null
         * assessdefineid : 1
         * createdby : 系统
         * creationtime : 1480821564474
         * id : 1
         * isdeleted : 0
         * lastupdatedby :
         * lastupdatetime : null
         * name :
         * picture :
         * topictype :
         */

        private List<TopicDefineListBean> topicDefineList;

        public Object getAssess() {
            return assess;
        }

        public void setAssess(Object assess) {
            this.assess = assess;
        }

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
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

        public String getWardid() {
            return wardid;
        }

        public void setWardid(String wardid) {
            this.wardid = wardid;
        }

        public List<TopicDefineListBean> getTopicDefineList() {
            return topicDefineList;
        }

        public void setTopicDefineList(List<TopicDefineListBean> topicDefineList) {
            this.topicDefineList = topicDefineList;
        }

        public static class TopicDefineListBean implements Serializable, NetworkForImage.IImageBean{
            private Object answerRecordList;
            private int assessdefineid;
            private String createdby;
            private long creationtime;
            private int id;
            private String isdeleted;
            private String lastupdatedby;
            private long lastupdatetime;
            private String name;
            private String picture;
            private String topictype;
            private Integer apppicture;
            private Integer linelimit;
            private String imageString;
            private List<AnswerDefineListBean> answerDefineList;


            @Override
            public Integer getImageId() {
                Integer retval = null;
                if (apppicture != null){
                    retval = apppicture;
                } else if (picture != null && !picture.trim().isEmpty()){
                    try {
                        retval = Integer.parseInt(picture);
                    } catch (NumberFormatException e) {
                        retval = null;
                        LogUtil.e("Topic[" + id + "]'s picture is not an integer[" + picture + "]!");
                    }
                }
                return retval;
            }
            public Object getAnswerRecordList() {
                return answerRecordList;
            }

            public void setAnswerRecordList(Object answerRecordList) {
                this.answerRecordList = answerRecordList;
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

            public long getCreationtime() {
                return creationtime;
            }

            public void setCreationtime(long creationtime) {
                this.creationtime = creationtime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getTopictype() {
                return topictype;
            }

            public void setTopictype(String topictype) {
                this.topictype = topictype;
            }

            public List<AnswerDefineListBean> getAnswerDefineList() {
                return answerDefineList;
            }

            public void setAnswerDefineList(List<AnswerDefineListBean> answerDefineList) {
                this.answerDefineList = answerDefineList;
            }

            public Integer getApppicture() {
                return apppicture;
            }

            public void setApppicture(Integer apppicture) {
                this.apppicture = apppicture;
            }

            public Integer getLinelimit() {
                return linelimit;
            }

            public void setLinelimit(Integer linelimit) {
                this.linelimit = linelimit;
            }

            public String getImageString() {
                return imageString;
            }
            @Override
            public void setImageString(String imageString) {
                this.imageString = imageString;
            }

            public static class AnswerDefineListBean implements Serializable, NetworkForImage.IImageBean{
                private int answertype;
                private String createdby;
                private long creationtime;
                private int id;
                private String isdeleted;
                private String lastupdatedby;
                private long lastupdatetime;
                private int score;
                private int topicdefineid;
                private String value;
                private Integer androidimage;
                private Integer neednote;
                private String imageString;
                @Override
                public Integer getImageId() {
                    Integer retval = null;
                    if (Constant.ANSWER_TYPE_PICTURE == answertype) {
                        if (androidimage != null) {
                            retval = androidimage;
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
                public int getAnswertype() {
                    return answertype;
                }

                public void setAnswertype(int answertype) {
                    this.answertype = answertype;
                }

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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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

                public long getLastupdatetime() {
                    return lastupdatetime;
                }

                public void setLastupdatetime(long lastupdatetime) {
                    this.lastupdatetime = lastupdatetime;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }

                public int getTopicdefineid() {
                    return topicdefineid;
                }

                public void setTopicdefineid(int topicdefineid) {
                    this.topicdefineid = topicdefineid;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public Integer getAndroidimage() {
                    return androidimage;
                }

                public void setAndroidimage(Integer androidimage) {
                    this.androidimage = androidimage;
                }

                public Integer getNeednote() {
                    return neednote;
                }

                public void setNeednote(Integer neednote) {
                    this.neednote = neednote;
                }

                public String getImageString() {
                    return imageString;
                }
                @Override
                public void setImageString(String imageString) {
                    this.imageString = imageString;
                }
            }
        }
    }
}
