package com.ge.med.mobile.nursing.dao.entity;

import java.util.List;

/**网络访问实体JSON解析Bean
 * Created by Administrator on 2016/12/14.
 */
public class UserListBean {


    /**
     * status : 1
     * data : [{"createdby":"","creationtime":null,"empno":"an090","id":4,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"张扬","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an091","id":5,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明1","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an092","id":6,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明2","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an093","id":7,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明3","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an094","id":8,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明4","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an095","id":9,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明5","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an096","id":10,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明6","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an097","id":11,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明7","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an098","id":12,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明8","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an099","id":13,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明9","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an100","id":14,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明11","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an102","id":16,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明13","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an103","id":17,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明14","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an104","id":18,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明15","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an105","id":19,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明16","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an101","id":15,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"明明122","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null},{"createdby":"","creationtime":null,"empno":"an089","id":3,"isdeleted":"","lastupdatedby":"","lastupdatetime":null,"name":"晨晨","password":"","patientList":null,"rOLE_TYPE_ADMIN":"9","rOLE_TYPE_DOCTOR":"2","rOLE_TYPE_NURSE":"0","rOLE_TYPE_NURSE_LEADER":"1","roletype":"","userPatientList":null,"wardid":null}]
     * msg : 成功
     */

    private int status;
    private String msg;
    /**
     * createdby :
     * creationtime : null
     * empno : an090
     * id : 4
     * isdeleted :
     * lastupdatedby :
     * lastupdatetime : null
     * name : 张扬
     * password :
     * patientList : null
     * rOLE_TYPE_ADMIN : 9
     * rOLE_TYPE_DOCTOR : 2
     * rOLE_TYPE_NURSE : 0
     * rOLE_TYPE_NURSE_LEADER : 1
     * roletype :
     * userPatientList : null
     * wardid : null
     */

    private List<DataBean> data;

    public int getStatus() {
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

    public static class DataBean {
        private String createdby;
        private Object creationtime;
        private String empno;
        private int id;
        private String isdeleted;
        private String lastupdatedby;
        private Object lastupdatetime;
        private String name;
        private String password;
        private Object patientList;
        private String rOLE_TYPE_ADMIN;
        private String rOLE_TYPE_DOCTOR;
        private String rOLE_TYPE_NURSE;
        private String rOLE_TYPE_NURSE_LEADER;
        private String roletype;
        private Object userPatientList;
        private Object wardid;

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

        public String getEmpno() {
            return empno;
        }

        public void setEmpno(String empno) {
            this.empno = empno;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getPatientList() {
            return patientList;
        }

        public void setPatientList(Object patientList) {
            this.patientList = patientList;
        }

        public String getROLE_TYPE_ADMIN() {
            return rOLE_TYPE_ADMIN;
        }

        public void setROLE_TYPE_ADMIN(String rOLE_TYPE_ADMIN) {
            this.rOLE_TYPE_ADMIN = rOLE_TYPE_ADMIN;
        }

        public String getROLE_TYPE_DOCTOR() {
            return rOLE_TYPE_DOCTOR;
        }

        public void setROLE_TYPE_DOCTOR(String rOLE_TYPE_DOCTOR) {
            this.rOLE_TYPE_DOCTOR = rOLE_TYPE_DOCTOR;
        }

        public String getROLE_TYPE_NURSE() {
            return rOLE_TYPE_NURSE;
        }

        public void setROLE_TYPE_NURSE(String rOLE_TYPE_NURSE) {
            this.rOLE_TYPE_NURSE = rOLE_TYPE_NURSE;
        }

        public String getROLE_TYPE_NURSE_LEADER() {
            return rOLE_TYPE_NURSE_LEADER;
        }

        public void setROLE_TYPE_NURSE_LEADER(String rOLE_TYPE_NURSE_LEADER) {
            this.rOLE_TYPE_NURSE_LEADER = rOLE_TYPE_NURSE_LEADER;
        }

        public String getRoletype() {
            return roletype;
        }

        public void setRoletype(String roletype) {
            this.roletype = roletype;
        }

        public Object getUserPatientList() {
            return userPatientList;
        }

        public void setUserPatientList(Object userPatientList) {
            this.userPatientList = userPatientList;
        }

        public Object getWardid() {
            return wardid;
        }

        public void setWardid(Object wardid) {
            this.wardid = wardid;
        }
    }
}
