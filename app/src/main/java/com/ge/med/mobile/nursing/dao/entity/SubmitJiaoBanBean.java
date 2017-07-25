package com.ge.med.mobile.nursing.dao.entity;

import java.util.List;

/**定义网络访问实体JSON解析Bean
 * Created by xxl on 2017/4/5.
 */

public class SubmitJiaoBanBean {

    private Object id;
    private int createdby;
    private Object creationtime;
    private int lastupdatedby;
    private Object lastupdatetime;
    private String userid; //
    private int templateId;//事件
    private String patientid;//
    private DischargePersonalBean dischargePersonal;//
    private Integer signsheetid;//
    private int status;// 1
    private String name;
    private String time;//

    private String description;//
    private VitalSignSheetBean vitalSignSheet;//
    private List<DisOrderListBean> disOrderList;//
    private List<DischargeVitalTotalListBean> dischargeVitalTotalList;//


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public int getCreatedby() {
        return createdby;
    }

    public void setCreatedby(int createdby) {
        this.createdby = createdby;
    }

    public Object getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Object creationtime) {
        this.creationtime = creationtime;
    }

    public int getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(int lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public Object getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Object lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public DischargePersonalBean getDischargePersonal() {
        return dischargePersonal;
    }

    public void setDischargePersonal(DischargePersonalBean dischargePersonal) {
        this.dischargePersonal = dischargePersonal;
    }

    public Integer getSignsheetid() {
        return signsheetid;
    }

    public void setSignsheetid(Integer signsheetid) {
        this.signsheetid = signsheetid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VitalSignSheetBean getVitalSignSheet() {
        return vitalSignSheet;
    }

    public void setVitalSignSheet(VitalSignSheetBean vitalSignSheet) {
        this.vitalSignSheet = vitalSignSheet;
    }

    public List<DisOrderListBean> getDisOrderList() {
        return disOrderList;
    }

    public void setDisOrderList(List<DisOrderListBean> disOrderList) {
        this.disOrderList = disOrderList;
    }

    public List<DischargeVitalTotalListBean> getDischargeVitalTotalList() {
        return dischargeVitalTotalList;
    }

    public void setDischargeVitalTotalList(List<DischargeVitalTotalListBean> dischargeVitalTotalList) {
        this.dischargeVitalTotalList = dischargeVitalTotalList;
    }

    public static class DischargePersonalBean {


        private String userid;//
        private String time;//
        private int createdby;
        private int lastupdateby;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getCreatedby() {
            return createdby;
        }

        public void setCreatedby(int createdby) {
            this.createdby = createdby;
        }

        public int getLastupdateby() {
            return lastupdateby;
        }

        public void setLastupdateby(int lastupdateby) {
            this.lastupdateby = lastupdateby;
        }
    }

    public static class VitalSignSheetBean {

        private String comment;//  备注
        private String createdby;
        private Object creationtime;
        private int id;
        private String patientid;//
        private String lastupdatedby;
        private Object lastupdatetime;
        private String status;//1
        private String time;//
        private int userid;//
        private List<SignRecordListBean> signRecordList;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public List<SignRecordListBean> getSignRecordList() {
            return signRecordList;
        }

        public void setSignRecordList(List<SignRecordListBean> signRecordList) {
            this.signRecordList = signRecordList;
        }

        public static class SignRecordListBean {
            private String name;
            private String unit;
            private int value;//
            private int savestatus;//1
            private int userid;//
            private Object sheetid;
            private Object id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public int getSavestatus() {
                return savestatus;
            }

            public void setSavestatus(int savestatus) {
                this.savestatus = savestatus;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public Object getSheetid() {
                return sheetid;
            }

            public void setSheetid(Object sheetid) {
                this.sheetid = sheetid;
            }

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }
        }
    }

    public static class DisOrderListBean {

        private String createdby;
        private Object creationtime;
        private Object endtime;
        private int hisid;
        private Object id;
        private String isdeleted;
        private String lastupdatedby;
        private Object lastupdatetime;
        private String orderDose;
        private Object orderExceptions;
        private Object orderExecuteRecords;
        private Object orderNoteList;
        private String orderUnit;
        private Object signRecord;
        private Object starttime;
        private String suborderduration;
        private String subordername;
        private Object hisDocOrder;
        private int creationby;
        private int lastupdateby;
        private int hisorderid;//
        private Object doctorOrders;
        private Object exetime;

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

        public Object getEndtime() {
            return endtime;
        }

        public void setEndtime(Object endtime) {
            this.endtime = endtime;
        }

        public int getHisid() {
            return hisid;
        }

        public void setHisid(int hisid) {
            this.hisid = hisid;
        }

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
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

        public Object getLastupdatetime() {
            return lastupdatetime;
        }

        public void setLastupdatetime(Object lastupdatetime) {
            this.lastupdatetime = lastupdatetime;
        }

        public String getOrderDose() {
            return orderDose;
        }

        public void setOrderDose(String orderDose) {
            this.orderDose = orderDose;
        }

        public Object getOrderExceptions() {
            return orderExceptions;
        }

        public void setOrderExceptions(Object orderExceptions) {
            this.orderExceptions = orderExceptions;
        }

        public Object getOrderExecuteRecords() {
            return orderExecuteRecords;
        }

        public void setOrderExecuteRecords(Object orderExecuteRecords) {
            this.orderExecuteRecords = orderExecuteRecords;
        }

        public Object getOrderNoteList() {
            return orderNoteList;
        }

        public void setOrderNoteList(Object orderNoteList) {
            this.orderNoteList = orderNoteList;
        }

        public String getOrderUnit() {
            return orderUnit;
        }

        public void setOrderUnit(String orderUnit) {
            this.orderUnit = orderUnit;
        }

        public Object getSignRecord() {
            return signRecord;
        }

        public void setSignRecord(Object signRecord) {
            this.signRecord = signRecord;
        }

        public Object getStarttime() {
            return starttime;
        }

        public void setStarttime(Object starttime) {
            this.starttime = starttime;
        }

        public String getSuborderduration() {
            return suborderduration;
        }

        public void setSuborderduration(String suborderduration) {
            this.suborderduration = suborderduration;
        }

        public String getSubordername() {
            return subordername;
        }

        public void setSubordername(String subordername) {
            this.subordername = subordername;
        }

        public Object getHisDocOrder() {
            return hisDocOrder;
        }

        public void setHisDocOrder(Object hisDocOrder) {
            this.hisDocOrder = hisDocOrder;
        }

        public int getCreationby() {
            return creationby;
        }

        public void setCreationby(int creationby) {
            this.creationby = creationby;
        }

        public int getLastupdateby() {
            return lastupdateby;
        }

        public void setLastupdateby(int lastupdateby) {
            this.lastupdateby = lastupdateby;
        }

        public int getHisorderid() {
            return hisorderid;
        }

        public void setHisorderid(int hisorderid) {
            this.hisorderid = hisorderid;
        }

        public Object getDoctorOrders() {
            return doctorOrders;
        }

        public void setDoctorOrders(Object doctorOrders) {
            this.doctorOrders = doctorOrders;
        }

        public Object getExetime() {
            return exetime;
        }

        public void setExetime(Object exetime) {
            this.exetime = exetime;
        }
    }

    public static class DischargeVitalTotalListBean {
        private String name;
        private String unit;//
        private String value;//
        private String totalValue;//
        private String vitalName;//

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getTotalValue() {
            return totalValue;
        }

        public void setTotalValue(String totalValue) {
            this.totalValue = totalValue;
        }

        public String getVitalName() {
            return vitalName;
        }

        public void setVitalName(String vitalName) {
            this.vitalName = vitalName;
        }
    }
}
