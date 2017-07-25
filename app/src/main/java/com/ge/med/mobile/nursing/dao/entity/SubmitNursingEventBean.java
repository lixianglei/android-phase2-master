package com.ge.med.mobile.nursing.dao.entity;

import java.io.Serializable;
import java.util.List;

/**定义网络访问实体JSON解析Bean
 * Created by Administrator on 2017/3/31.
 */
public class SubmitNursingEventBean implements Serializable{
    /**
     * id : null
     * createdby : 晨晨
     * creationtime : 2017-3-31 9:22:35
     * lastupdatedby : 晨晨
     * lastupdatetime : 2017-3-31 9:22:35
     * userid : 3  ==
     * patientid : A100233_1  ==
     * recordtime : 2017-3-31 09:22:00   记录时间 ==
     * orderid : null                                +++++++++++++++++++++=
     * comment : 大幅度第三个  备注                ++++++++++++++++
     * savestatus : 0    保存 0 or 提交 1  ==
     * nursingeventdefineid : 2  事件id ==
     * vitalSignRecordList : [{"signvalue":1,"signtype":21,"note":"BZ3","lastupdatedby":"晨晨","createdby":"晨晨","patientid":"A100233_1","userid":3,"signrecordtime":"2017-3-31 09:22:00"},{"signvalue":3,"signtype":21,"note":"BF34","lastupdatedby":"晨晨","createdby":"晨晨","patientid":"A100233_1","userid":3,"signrecordtime":"2017-3-31 09:22:00"}]
     * vitalSignSheet : null
     */

    private Object id;
    private String createdby;
    private String creationtime;
    private String lastupdatedby;
    private String lastupdatetime;
    private String userid;
    private String patientid;
    private String recordtime;
    private Object orderid;
    private String comment;
    private int savestatus;
    private int nursingeventdefineid;
    private Object vitalSignSheet;
    /**
     * signvalue : 1
     * signtype : 21      id
     * note : BZ3
     * lastupdatedby : 晨晨
     * createdby : 晨晨
     * patientid : A100233_1
     * userid : 3
     * signrecordtime : 2017-3-31 09:22:00
     */

    private List<VitalSignRecordListBean> vitalSignRecordList;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public String getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(String lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(String recordtime) {
        this.recordtime = recordtime;
    }

    public Object getOrderid() {
        return orderid;
    }

    public void setOrderid(Object orderid) {
        this.orderid = orderid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSavestatus() {
        return savestatus;
    }

    public void setSavestatus(int savestatus) {
        this.savestatus = savestatus;
    }

    public int getNursingeventdefineid() {
        return nursingeventdefineid;
    }

    public void setNursingeventdefineid(int nursingeventdefineid) {
        this.nursingeventdefineid = nursingeventdefineid;
    }

    public Object getVitalSignSheet() {
        return vitalSignSheet;
    }

    public void setVitalSignSheet(Object vitalSignSheet) {
        this.vitalSignSheet = vitalSignSheet;
    }

    public List<VitalSignRecordListBean> getVitalSignRecordList() {
        return vitalSignRecordList;
    }

    public void setVitalSignRecordList(List<VitalSignRecordListBean> vitalSignRecordList) {
        this.vitalSignRecordList = vitalSignRecordList;
    }

    public static class VitalSignRecordListBean  implements Serializable{
        private String signvalue;
        private int signtype;
        private String note;
        private String lastupdatedby;
        private String createdby;
        private String patientid;
        private int userid;
        private String signrecordtime;

        public String getSignvalue() {
            return signvalue;
        }

        public void setSignvalue(String signvalue) {
            this.signvalue = signvalue;
        }

        public int getSigntype() {
            return signtype;
        }

        public void setSigntype(int signtype) {
            this.signtype = signtype;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getLastupdatedby() {
            return lastupdatedby;
        }

        public void setLastupdatedby(String lastupdatedby) {
            this.lastupdatedby = lastupdatedby;
        }

        public String getCreatedby() {
            return createdby;
        }

        public void setCreatedby(String createdby) {
            this.createdby = createdby;
        }

        public String getPatientid() {
            return patientid;
        }

        public void setPatientid(String patientid) {
            this.patientid = patientid;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getSignrecordtime() {
            return signrecordtime;
        }

        public void setSignrecordtime(String signrecordtime) {
            this.signrecordtime = signrecordtime;
        }
    }
}
