package com.ge.med.mobile.nursing.dao.entity;

import java.util.List;

/**
 * 获取交班记录的患者Bean
 * Created by yang on 2016/12/10.
 */
public class DischargepatientBean {

    /**
     * id : null
     * createdby : 晨晨
     * creationtime : 2016-12-16 11:50:00
     * lastupdatedby : 晨晨
     * lastupdatetime : 2016-12-16 11:50:00
     * disOrderList : [{"auditby":"","audittime":null,"bedNo":null,"checkby":"","checktime":null,"createdby":"test","creationtime":"2016-12-16 11:50:00","doctorOrders":null,"doctorname":"","doctorno":"","executeby":"","executetime":null,"exetime":null,"id":null,"isdeleted":"0","lastupdatedby":"晨晨","lastupdatetime":"2016-12-16 11:50:00","note":"未通过","orderBloodProperty":null,"orderCheckProperty":null,"orderdose":"200","orderduration":"1","orderfrequency":"TLD","orderfrom":"","ordername":"名称3&Adf","orderststus":"执行中","ordertype":"1","orderunit":"mm","patientName":"","patientid":"A100233_20","routedesc":"","starttime":null,"userid":3,"hisDocOrder":null,"creationby":"晨晨","lastupdateby":"晨晨","hisorderid":3,"status":"执行中"},{"auditby":"","audittime":null,"bedNo":null,"checkby":"","checktime":null,"createdby":"系统","creationtime":"2016-12-16 11:50:00","doctorOrders":null,"doctorname":"","doctorno":"","executeby":"","executetime":null,"exetime":null,"id":null,"isdeleted":"0","lastupdatedby":"晨晨","lastupdatetime":"2016-12-16 11:50:00","note":"XXXX","orderBloodProperty":null,"orderCheckProperty":null,"orderdose":"150","orderduration":"0","orderfrequency":"T/D","orderfrom":"","ordername":"输液300ml","orderststus":"执行中","ordertype":"3","orderunit":"ml","patientName":"","patientid":"A100233_20","routedesc":"","starttime":null,"userid":3,"hisDocOrder":null,"creationby":"晨晨","lastupdateby":"晨晨","hisorderid":72,"status":"执行中"}]
     * userid : 3
     * patientid : A100233_20
     * dischargePersonal : {"note":"交班记录","userid":3,"time":"2016-12-16 11:50:00","createdby":"晨晨","lastupdateby":"晨晨"}
     * dischargeVitalSign : {"id":null,"vitalsheetid":1583,"time":"2016-12-16 11:50:00","createdby":"晨晨","lastupdateby":"晨晨","lastupdatetime":"2016-12-16 11:50:00","createtime":"2016-12-16 11:50:00"}
     * signsheetid : 1583
     * status : 0
     * name : 李四2
     * description : 交班记录
     */

    private Object id;
    private String createdby;
    private String lastupdatedby;
    private int userid;
    private String patientid;
    private Integer signsheetid;

    public Integer getSignsheetid() {
        return signsheetid;
    }

    public void setSignsheetid(Integer signsheetid) {
        this.signsheetid = signsheetid;
    }

    /**
     * note : 交班记录
     * userid : 3
     * time : 2016-12-16 11:50:00
     * createdby : 晨晨
     * lastupdateby : 晨晨
     */
    private DischargePersonalBean dischargePersonal;
    /**
     * id : null
     * vitalsheetid : 1583
     * time : 2016-12-16 11:50:00
     * createdby : 晨晨
     * lastupdateby : 晨晨
     * lastupdatetime : 2016-12-16 11:50:00
     * createtime : 2016-12-16 11:50:00
     */

    private DischargeVitalSignBean dischargeVitalSign;
    private int status;
    private String name;
    private String description;
    /**
     * auditby :
     * audittime : null
     * bedNo : null
     * checkby :
     * checktime : null
     * createdby : test
     * creationtime : 2016-12-16 11:50:00
     * doctorOrders : null
     * doctorname :
     * doctorno :
     * executeby :
     * executetime : null
     * exetime : null
     * id : null
     * isdeleted : 0
     * lastupdatedby : 晨晨
     * lastupdatetime : 2016-12-16 11:50:00
     * note : 未通过
     * orderBloodProperty : null
     * orderCheckProperty : null
     * orderdose : 200
     * orderduration : 1
     * orderfrequency : TLD
     * orderfrom :
     * ordername : 名称3&Adf
     * orderststus : 执行中
     * ordertype : 1
     * orderunit : mm
     * patientName :
     * patientid : A100233_20
     * routedesc :
     * starttime : null
     * userid : 3
     * hisDocOrder : null
     * creationby : 晨晨
     * lastupdateby : 晨晨
     * hisorderid : 3
     * status : 执行中
     */

    private List<DisOrderListBean> disOrderList;

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


    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public DischargeVitalSignBean getDischargeVitalSign() {
        return dischargeVitalSign;
    }

    public void setDischargeVitalSign(DischargeVitalSignBean dischargeVitalSign) {
        this.dischargeVitalSign = dischargeVitalSign;
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

    public List<DisOrderListBean> getDisOrderList() {
        return disOrderList;
    }

    public void setDisOrderList(List<DisOrderListBean> disOrderList) {
        this.disOrderList = disOrderList;
    }

    public static class DischargePersonalBean {
        private String note;
        private Object userid;
        private String createdby;
        private String lastupdateby;
        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public Object getUserid() {
            return userid;
        }

        public void setUserid(Object userid) {
            this.userid = userid;
        }


        public String getCreatedby() {
            return createdby;
        }

        public void setCreatedby(String createdby) {
            this.createdby = createdby;
        }

        public String getLastupdateby() {
            return lastupdateby;
        }

        public void setLastupdateby(String lastupdateby) {
            this.lastupdateby = lastupdateby;
        }
    }

    public static class DischargeVitalSignBean {
        private Object id;
        private int vitalsheetid;
        private String createdby;
        private String lastupdateby;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public int getVitalsheetid() {
            return vitalsheetid;
        }

        public void setVitalsheetid(int vitalsheetid) {
            this.vitalsheetid = vitalsheetid;
        }

        public String getCreatedby() {
            return createdby;
        }

        public void setCreatedby(String createdby) {
            this.createdby = createdby;
        }

        public String getLastupdateby() {
            return lastupdateby;
        }

        public void setLastupdateby(String lastupdateby) {
            this.lastupdateby = lastupdateby;
        }


    }

    public static class DisOrderListBean {
        private Object id;
        private String isdeleted;
        private String note;
        private String patientName;
        private String patientid;
        private int userid;
        private String creationby;
        private String lastupdateby;
        private int hisorderid;
        private String status;

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

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
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

        public String getCreationby() {
            return creationby;
        }

        public void setCreationby(String creationby) {
            this.creationby = creationby;
        }

        public String getLastupdateby() {
            return lastupdateby;
        }

        public void setLastupdateby(String lastupdateby) {
            this.lastupdateby = lastupdateby;
        }

        public int getHisorderid() {
            return hisorderid;
        }

        public void setHisorderid(int hisorderid) {
            this.hisorderid = hisorderid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
