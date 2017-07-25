package com.ge.med.mobile.nursing.dao.entity;

import com.ge.med.mobile.nursing.forjson.entity.BaseJSONBean;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignSheet;

import java.io.Serializable;
import java.util.List;

/**
 * 网络访问实体JSON解析Bean
 * Created by Administrator on 2016/11/17.
 */
public class HuanZheLieBiaoBean implements BaseJSONBean {

    /**
     * status : 1
     * data : [{"age":32,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":8,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_23","result":"","score":null,"time":null,"userid":3}],"assessresut":"高血压","bedno":19,"breathmethod":"良好","carelevel":"二级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":8,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"李四5","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_23","sex":"未知","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":34,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":17,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_32","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒","bedno":15,"breathmethod":"良好","carelevel":"一级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":17,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"李四14","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_32","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":34,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":16,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_31","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒","bedno":14,"breathmethod":"良好","carelevel":"一级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":16,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"李四13","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_31","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":44,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":12,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_27","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒","bedno":8,"breathmethod":"良好","carelevel":"一级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":12,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"李四9","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_27","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":34,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":7,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_22","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒","bedno":4,"breathmethod":"良好","carelevel":"一级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":7,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"李四4","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_22","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":20,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":4,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_4","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒","bedno":3,"breathmethod":"良好","carelevel":"一级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":4,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"王二2","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_4","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":34,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":6,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_21","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒,晕血","bedno":20,"breathmethod":"良好","carelevel":"一级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":6,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"李四3","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_21","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":64,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":14,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_29","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒","bedno":12,"breathmethod":"良好","carelevel":"一级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":14,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"李四11","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_29","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":40,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":9,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_24","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒","bedno":5,"breathmethod":"良好","carelevel":"一级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":9,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"李四6","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_24","sex":"女","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":34,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":10,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_25","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒","bedno":6,"breathmethod":"良好","carelevel":"一级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":10,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"李四7","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_25","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":20,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":3,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_3","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒","bedno":17,"breathmethod":"良好","carelevel":"特级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":3,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"王二","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_3","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":34,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":1,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_1","result":"","score":null,"time":null,"userid":3}],"assessresut":"压床,跌倒","bedno":1,"breathmethod":"良好","carelevel":"三级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":1,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"张三","nomoneystatus":"1","note":"","outhospitaltime":null,"patientid":"A100233_1","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":34,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":15,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_30","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒","bedno":13,"breathmethod":"良好","carelevel":"一级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":15,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"李四12","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_30","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":34,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":18,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_33","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒","bedno":16,"breathmethod":"良好","carelevel":"一级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":18,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"李四15","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_33","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":54,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":13,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_28","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒","bedno":9,"breathmethod":"良好","carelevel":"一级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":13,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"李四10","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_28","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":34,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":5,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_20","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒","bedno":18,"breathmethod":"良好","carelevel":"二级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":5,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"李四2","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_20","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null},{"age":36,"allergyhistory":"无","assessResultList":[{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":11,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_26","result":"","score":null,"time":null,"userid":3}],"assessresut":"跌倒","bedno":7,"breathmethod":"良好","carelevel":"一级","createdby":"系统","creationtime":1479291722545,"deathtime":null,"diagnosis":"","doctorid":"","id":11,"inhospitaltime":null,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"name":"李四8","nomoneystatus":"","note":"","outhospitaltime":null,"patientid":"A100233_26","sex":"男","transferdepartmenttime":null,"transferhospitaltime":null,"userid":3,"voOrderStatus":{"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null},"wardid":null}]
     * msg : 成功
     */

    private int status;
    private String msg;
    /**
     * age : 32
     * allergyhistory : 无
     * assessResultList : [{"assessdefineid":null,"createdby":"系统","creationtime":1479291722545,"id":8,"isdeleted":"0","lastupdatedby":"","lastupdatetime":null,"patientid":"A100233_23","result":"","score":null,"time":null,"userid":3}]
     * assessresut : 高血压
     * bedno : 19
     * breathmethod : 良好
     * carelevel : 二级
     * createdby : 系统
     * creationtime : 1479291722545
     * deathtime : null
     * diagnosis :
     * doctorid :
     * id : 8
     * inhospitaltime : null
     * isdeleted : 0
     * lastupdatedby :
     * lastupdatetime : null
     * name : 李四5
     * nomoneystatus :
     * note :
     * outhospitaltime : null
     * patientid : A100233_23
     * sex : 未知
     * transferdepartmenttime : null
     * transferhospitaltime : null
     * userid : 3
     * voOrderStatus : {"noExeOrderCount":0,"noExeOrderStatus":"","orderStatus":null}
     * wardid : null
     */

    private List<DataBean> data;

    @Override
    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String getCallName() {
        return "get single patient";
    }

    public static class DataBean implements Serializable {
        private String illdetial;
        private int age;
        private String allergyhistory;
        private int bedno;
        private String breathmethod;
        private String carelevel;
        private String createdby;
        private long creationtime;
        private Object deathtime;
        private String diagnosis;
        private String doctorid;
        private int id;
        private Object inhospitaltime;
        private String isdeleted;
        private String lastupdatedby;
        private Object lastupdatetime;
        private String name;
        private String nomoneystatus;
        private String note;
        private Object outhospitaltime;
        private String patientid;
        private String sex;
        private Object transferdepartmenttime;
        private Object transferhospitaltime;
        private int userid;
        private int unAssessNumber = 0;
        private String mrnno;//住院号
        private String doctorname;
        private String wandaicode;

        public String getWandaicode() {
            return wandaicode;
        }

        public void setWandaicode(String wandaicode) {
            this.wandaicode = wandaicode;
        }

        public String getMrnno() {
            return mrnno;
        }

        public void setMrnno(String mrnno) {
            this.mrnno = mrnno;
        }

        /**
         * noExeOrderCount : 0
         * <p>
         * noExeOrderStatus :
         * orderStatus : null
         */

        private VoOrderStatusBean voOrderStatus;
        private Object wardid;
        /**
         * assessdefineid : null
         * createdby : 系统
         * creationtime : 1479291722545
         * id : 8
         * isdeleted : 0
         * lastupdatedby :
         * lastupdatetime : null
         * patientid : A100233_23
         * result :
         * score : null
         * time : null
         * userid : 3
         */

        private List<LabelList> labelList;

        private List<YiZhuBean> executingOrderList;//执行中的医嘱信息

        public List<YiZhuBean> getExecutingOrderList() {
            return executingOrderList;
        }

        public void setExecutingOrderList(List<YiZhuBean> executingOrderList) {
            this.executingOrderList = executingOrderList;
        }

        private VitalSignSheet lastSignRecord;//最近一次生命体征

        public VitalSignSheet getLastSignRecord() {
            return lastSignRecord;
        }

        public void setLastSignRecord(VitalSignSheet lastSignRecord) {
            this.lastSignRecord = lastSignRecord;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getAllergyhistory() {
            return allergyhistory;
        }

        public void setAllergyhistory(String allergyhistory) {
            this.allergyhistory = allergyhistory;
        }


        public int getBedno() {
            return bedno;
        }

        public void setBedno(int bedno) {
            this.bedno = bedno;
        }

        public String getBreathmethod() {
            return breathmethod;
        }

        public void setBreathmethod(String breathmethod) {
            this.breathmethod = breathmethod;
        }

        public String getCarelevel() {
            return carelevel;
        }

        public void setCarelevel(String carelevel) {
            this.carelevel = carelevel;
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

        public Object getDeathtime() {
            return deathtime;
        }

        public void setDeathtime(Object deathtime) {
            this.deathtime = deathtime;
        }

        public String getDiagnosis() {
            return diagnosis;
        }

        public void setDiagnosis(String diagnosis) {
            this.diagnosis = diagnosis;
        }

        public String getDoctorid() {
            return doctorid;
        }

        public void setDoctorid(String doctorid) {
            this.doctorid = doctorid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getInhospitaltime() {
            return inhospitaltime;
        }

        public void setInhospitaltime(Object inhospitaltime) {
            this.inhospitaltime = inhospitaltime;
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

        public Object getLastupdatetime() {
            return lastupdatetime;
        }

        public void setLastupdatetime(Object lastupdatetime) {
            this.lastupdatetime = lastupdatetime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNomoneystatus() {
            return nomoneystatus;
        }

        public void setNomoneystatus(String nomoneystatus) {
            this.nomoneystatus = nomoneystatus;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public Object getOuthospitaltime() {
            return outhospitaltime;
        }

        public void setOuthospitaltime(Object outhospitaltime) {
            this.outhospitaltime = outhospitaltime;
        }

        public String getPatientid() {
            return patientid;
        }

        public void setPatientid(String patientid) {
            this.patientid = patientid;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Object getTransferdepartmenttime() {
            return transferdepartmenttime;
        }

        public void setTransferdepartmenttime(Object transferdepartmenttime) {
            this.transferdepartmenttime = transferdepartmenttime;
        }

        public Object getTransferhospitaltime() {
            return transferhospitaltime;
        }

        public void setTransferhospitaltime(Object transferhospitaltime) {
            this.transferhospitaltime = transferhospitaltime;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public VoOrderStatusBean getVoOrderStatus() {
            return voOrderStatus;
        }

        public void setVoOrderStatus(VoOrderStatusBean voOrderStatus) {
            this.voOrderStatus = voOrderStatus;
        }

        public Object getWardid() {
            return wardid;
        }

        public void setWardid(Object wardid) {
            this.wardid = wardid;
        }

        public List<LabelList> getLabelList() {
            return labelList;
        }

        public void setLabelList(List<LabelList> LabelList) {
            this.labelList = LabelList;
        }

        public int getUnAssessNumber() {
            return unAssessNumber;
        }

        public void setUnAssessNumber(int unAssessNumber) {
            this.unAssessNumber = unAssessNumber;
        }

        public String getIlldetial() {
            return illdetial;
        }

        public void setIlldetial(String illdetial) {
            this.illdetial = illdetial;
        }

        public String getDoctorname() {
            return doctorname;
        }

        public void setDoctorname(String doctorname) {
            this.doctorname = doctorname;
        }

        public static class VoOrderStatusBean implements Serializable {
            private int noExeOrderCount;
            private String noExeOrderStatus;
            private Object orderStatus;

            public int getNoExeOrderCount() {
                return noExeOrderCount;
            }

            public void setNoExeOrderCount(int noExeOrderCount) {
                this.noExeOrderCount = noExeOrderCount;
            }

            public String getNoExeOrderStatus() {
                return noExeOrderStatus;
            }

            public void setNoExeOrderStatus(String noExeOrderStatus) {
                this.noExeOrderStatus = noExeOrderStatus;
            }

            public Object getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(Object orderStatus) {
                this.orderStatus = orderStatus;
            }
        }

        public static class LabelList implements Serializable {
            private int id;
            private String patientid;
            private String riskId;
            private String riskName;
            private String score;
            private String shortname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPatientid() {
                return patientid;
            }

            public void setPatientid(String patientid) {
                this.patientid = patientid;
            }

            public String getRiskId() {
                return riskId;
            }

            public void setRiskId(String riskId) {
                this.riskId = riskId;
            }

            public String getRiskName() {
                return riskName;
            }

            public void setRiskName(String riskName) {
                this.riskName = riskName;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getShortname() {
                return shortname;
            }

            public void setShortname(String shortname) {
                this.shortname = shortname;
            }
        }
    }
}
