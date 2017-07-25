package com.ge.med.mobile.nursing.forjson.entity;

import java.io.Serializable;

/**
 * Created by Qu on 2016/12/10.
 */
public class UserEntity implements Serializable{
    /**
     * empno :
     * rOLE_TYPE_NURSE : 0
     * createdby :
     * userPatientList : null
     * lastupdatedby :
     * password :
     * rOLE_TYPE_DOCTOR : 2
     * rOLE_TYPE_NURSE_LEADER : 1
     * creationtime : null
     * id : null
     * lastupdatetime : null
     * isdeleted :
     * name : 晨晨
     * rOLE_TYPE_ADMIN : 9
     * wardid : null
     * roletype :
     * patientList : null
     */
    private String empno;
    private String rOLE_TYPE_NURSE;
    private String createdby;
    private String userPatientList;
    private String lastupdatedby;
    private String password;
    private String rOLE_TYPE_DOCTOR;
    private String rOLE_TYPE_NURSE_LEADER;
    private String creationtime;
    private String id;
    private String lastupdatetime;
    private String isdeleted;
    private String name;
    private String rOLE_TYPE_ADMIN;
    private String wardid;
    private String roletype;
    private String patientList;

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public void setROLE_TYPE_NURSE(String rOLE_TYPE_NURSE) {
        this.rOLE_TYPE_NURSE = rOLE_TYPE_NURSE;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public void setUserPatientList(String userPatientList) {
        this.userPatientList = userPatientList;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setROLE_TYPE_DOCTOR(String rOLE_TYPE_DOCTOR) {
        this.rOLE_TYPE_DOCTOR = rOLE_TYPE_DOCTOR;
    }

    public void setROLE_TYPE_NURSE_LEADER(String rOLE_TYPE_NURSE_LEADER) {
        this.rOLE_TYPE_NURSE_LEADER = rOLE_TYPE_NURSE_LEADER;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastupdatetime(String lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setROLE_TYPE_ADMIN(String rOLE_TYPE_ADMIN) {
        this.rOLE_TYPE_ADMIN = rOLE_TYPE_ADMIN;
    }

    public void setWardid(String wardid) {
        this.wardid = wardid;
    }

    public void setRoletype(String roletype) {
        this.roletype = roletype;
    }

    public void setPatientList(String patientList) {
        this.patientList = patientList;
    }

    public String getEmpno() {
        return empno;
    }

    public String getROLE_TYPE_NURSE() {
        return rOLE_TYPE_NURSE;
    }

    public String getCreatedby() {
        return createdby;
    }

    public String getUserPatientList() {
        return userPatientList;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public String getPassword() {
        return password;
    }

    public String getROLE_TYPE_DOCTOR() {
        return rOLE_TYPE_DOCTOR;
    }

    public String getROLE_TYPE_NURSE_LEADER() {
        return rOLE_TYPE_NURSE_LEADER;
    }

    public String getCreationtime() {
        return creationtime;
    }

    public String getId() {
        return id;
    }

    public String getLastupdatetime() {
        return lastupdatetime;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public String getName() {
        return name;
    }

    public String getROLE_TYPE_ADMIN() {
        return rOLE_TYPE_ADMIN;
    }

    public String getWardid() {
        return wardid;
    }

    public String getRoletype() {
        return roletype;
    }

    public String getPatientList() {
        return patientList;
    }

}
