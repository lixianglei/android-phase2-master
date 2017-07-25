package com.ge.med.mobile.nursing.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**定义网络访问实体JSON解析Bean
 * Created by Administrator on 2016/12/21.
 */
public class TongZhiBean implements Serializable{

    private String msg;
    private List<DataBean> data;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public  List<DataBean> getData() {
        return data;
    }

    public void setData( List<DataBean> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public static class DataBean  implements Serializable{
        private String businessId;
        private String content;
        private Object deleteTime;
        private String eventType;
        private String notifyId;
        private int notifyStatus;
        private int notifyType;
        private OrderNotifyBean orderNotify;
        private PatientBean patient;
        private Object readTime;
        private ArrayList<Integer> redirectList;
        private Object showDeleteTime;
        private long showTime;
        private String timeOutTime;
        private int notifySubType;//通知类型:对应是哪个子模块

        public int getNotifySubType() {
            return notifySubType;
        }

        public void setNotifySubType(int notifySubType) {
            this.notifySubType = notifySubType;
        }

        public String getBusinessId() {
            return businessId;
        }

        public void setBusinessId(String businessId) {
            this.businessId = businessId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(Object deleteTime) {
            this.deleteTime = deleteTime;
        }

        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }

        public String getNotifyId() {
            return notifyId;
        }

        public void setNotifyId(String notifyId) {
            this.notifyId = notifyId;
        }

        public int getNotifyStatus() {
            return notifyStatus;
        }

        public void setNotifyStatus(int notifyStatus) {
            this.notifyStatus = notifyStatus;
        }

        public int getNotifyType() {
            return notifyType;
        }

        public void setNotifyType(int notifyType) {
            this.notifyType = notifyType;
        }

        public OrderNotifyBean getOrderNotify() {
            return orderNotify;
        }

        public void setOrderNotify(OrderNotifyBean orderNotify) {
            this.orderNotify = orderNotify;
        }

        public PatientBean getPatient() {
            return patient;
        }

        public void setPatient(PatientBean patient) {
            this.patient = patient;
        }

        public Object getReadTime() {
            return readTime;
        }

        public void setReadTime(Object readTime) {
            this.readTime = readTime;
        }

        public ArrayList<Integer> getRedirectList() {
            return redirectList;
        }

        public void setRedirectList(ArrayList<Integer> redirectList) {
            this.redirectList = redirectList;
        }

        public Object getShowDeleteTime() {
            return showDeleteTime;
        }

        public void setShowDeleteTime(Object showDeleteTime) {
            this.showDeleteTime = showDeleteTime;
        }

        public long getShowTime() {
            return showTime;
        }

        public void setShowTime(long showTime) {
            this.showTime = showTime;
        }

        public String getTimeOutTime() {
            return timeOutTime;
        }

        public void setTimeOutTime(String timeOutTime) {
            this.timeOutTime = timeOutTime;
        }

        public static class PatientBean  implements Serializable{

            private Object age;
            private String ageValue;
            private String allergyhistory;
            private String assessresut;
            private Object bedno;
            private Object birthdate;
            private String breathmethod;
            private Object cardStatus;
            private String carelevel;
            private String createdby;
            private Object creationtime;
            private Object deathtime;
            private String diagnosis;
            private String docName;
            private String doctorid;
            private String doctorname;
            private String doctorno;
            private Object hisDocOrderList;
            private Object id;
            private String illdetial;
            private Object inHostipaIDays;
            private String inRoomDate;
            private Object inhospitaltime;
            private String isdeleted;
            private Object labelList;
            private Object lastSignRecord;
            private String lastupdatedby;
            private Object lastupdatetime;
            private String mrnno;
            private String name;
            private String nomoneystatus;
            private String note;
            private Object operationTimes;
            private String opmrn;
            private Object outHospitalDays;
            private Object outhospitaltime;
            private String patientid;
            private Object riskList;
            private String roomno;
            private String sex;
            private Object transferdepartmenttime;
            private Object transferhospitaltime;
            private Object transferintime;
            private Object userList;
            private String userName;
            private String userName2;
            private Object userid;
            private Object userid2;
            private Object userid3;
            private Object userid4;
            private Object voOrderStatus;
            private String wandaicode;
            private Object wardid;

            public Object getAge() {
                return age;
            }

            public void setAge(Object age) {
                this.age = age;
            }

            public String getAgeValue() {
                return ageValue;
            }

            public void setAgeValue(String ageValue) {
                this.ageValue = ageValue;
            }

            public String getAllergyhistory() {
                return allergyhistory;
            }

            public void setAllergyhistory(String allergyhistory) {
                this.allergyhistory = allergyhistory;
            }

            public String getAssessresut() {
                return assessresut;
            }

            public void setAssessresut(String assessresut) {
                this.assessresut = assessresut;
            }

            public Object getBedno() {
                return bedno;
            }

            public void setBedno(Object bedno) {
                this.bedno = bedno;
            }

            public Object getBirthdate() {
                return birthdate;
            }

            public void setBirthdate(Object birthdate) {
                this.birthdate = birthdate;
            }

            public String getBreathmethod() {
                return breathmethod;
            }

            public void setBreathmethod(String breathmethod) {
                this.breathmethod = breathmethod;
            }

            public Object getCardStatus() {
                return cardStatus;
            }

            public void setCardStatus(Object cardStatus) {
                this.cardStatus = cardStatus;
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

            public Object getCreationtime() {
                return creationtime;
            }

            public void setCreationtime(Object creationtime) {
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

            public String getDocName() {
                return docName;
            }

            public void setDocName(String docName) {
                this.docName = docName;
            }

            public String getDoctorid() {
                return doctorid;
            }

            public void setDoctorid(String doctorid) {
                this.doctorid = doctorid;
            }

            public String getDoctorname() {
                return doctorname;
            }

            public void setDoctorname(String doctorname) {
                this.doctorname = doctorname;
            }

            public String getDoctorno() {
                return doctorno;
            }

            public void setDoctorno(String doctorno) {
                this.doctorno = doctorno;
            }

            public Object getHisDocOrderList() {
                return hisDocOrderList;
            }

            public void setHisDocOrderList(Object hisDocOrderList) {
                this.hisDocOrderList = hisDocOrderList;
            }

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public String getIlldetial() {
                return illdetial;
            }

            public void setIlldetial(String illdetial) {
                this.illdetial = illdetial;
            }

            public Object getInHostipaIDays() {
                return inHostipaIDays;
            }

            public void setInHostipaIDays(Object inHostipaIDays) {
                this.inHostipaIDays = inHostipaIDays;
            }

            public String getInRoomDate() {
                return inRoomDate;
            }

            public void setInRoomDate(String inRoomDate) {
                this.inRoomDate = inRoomDate;
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

            public Object getLabelList() {
                return labelList;
            }

            public void setLabelList(Object labelList) {
                this.labelList = labelList;
            }

            public Object getLastSignRecord() {
                return lastSignRecord;
            }

            public void setLastSignRecord(Object lastSignRecord) {
                this.lastSignRecord = lastSignRecord;
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

            public String getMrnno() {
                return mrnno;
            }

            public void setMrnno(String mrnno) {
                this.mrnno = mrnno;
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

            public Object getOperationTimes() {
                return operationTimes;
            }

            public void setOperationTimes(Object operationTimes) {
                this.operationTimes = operationTimes;
            }

            public String getOpmrn() {
                return opmrn;
            }

            public void setOpmrn(String opmrn) {
                this.opmrn = opmrn;
            }

            public Object getOutHospitalDays() {
                return outHospitalDays;
            }

            public void setOutHospitalDays(Object outHospitalDays) {
                this.outHospitalDays = outHospitalDays;
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

            public Object getRiskList() {
                return riskList;
            }

            public void setRiskList(Object riskList) {
                this.riskList = riskList;
            }

            public String getRoomno() {
                return roomno;
            }

            public void setRoomno(String roomno) {
                this.roomno = roomno;
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

            public Object getTransferintime() {
                return transferintime;
            }

            public void setTransferintime(Object transferintime) {
                this.transferintime = transferintime;
            }

            public Object getUserList() {
                return userList;
            }

            public void setUserList(Object userList) {
                this.userList = userList;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserName2() {
                return userName2;
            }

            public void setUserName2(String userName2) {
                this.userName2 = userName2;
            }

            public Object getUserid() {
                return userid;
            }

            public void setUserid(Object userid) {
                this.userid = userid;
            }

            public Object getUserid2() {
                return userid2;
            }

            public void setUserid2(Object userid2) {
                this.userid2 = userid2;
            }

            public Object getUserid3() {
                return userid3;
            }

            public void setUserid3(Object userid3) {
                this.userid3 = userid3;
            }

            public Object getUserid4() {
                return userid4;
            }

            public void setUserid4(Object userid4) {
                this.userid4 = userid4;
            }

            public Object getVoOrderStatus() {
                return voOrderStatus;
            }

            public void setVoOrderStatus(Object voOrderStatus) {
                this.voOrderStatus = voOrderStatus;
            }

            public String getWandaicode() {
                return wandaicode;
            }

            public void setWandaicode(String wandaicode) {
                this.wandaicode = wandaicode;
            }

            public Object getWardid() {
                return wardid;
            }

            public void setWardid(Object wardid) {
                this.wardid = wardid;
            }
        }

    }

    /**
     * Created by xxl on 2017/4/11.
     */
    public static class OrderNotifyBean  implements Serializable{
        private String exeOrderTime;
        private String hisOrderId;
        private String orderId;
        private String orderStatus;
        private String orderType;
        private String watchTime;

        public String getExeOrderTime() {
            return exeOrderTime;
        }

        public void setExeOrderTime(String exeOrderTime) {
            this.exeOrderTime = exeOrderTime;
        }

        public String getHisOrderId() {
            return hisOrderId;
        }

        public void setHisOrderId(String hisOrderId) {
            this.hisOrderId = hisOrderId;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getWatchTime() {
            return watchTime;
        }

        public void setWatchTime(String watchTime) {
            this.watchTime = watchTime;
        }
    }
}
