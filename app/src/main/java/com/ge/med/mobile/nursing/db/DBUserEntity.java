package com.ge.med.mobile.nursing.db;

import com.ge.med.mobile.nursing.forjson.entity.UserEntity;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

/**
 * Created by Qu on 2016/12/10.
 */
public class DBUserEntity extends DataSupport {
    private int id;
    private String userId;
    private String empno;
    private String rOLE_TYPE_NURSE;
    private String createdby;
    private String userPatientList;
    private String lastupdatedby;
    private String password;
    private String rOLE_TYPE_DOCTOR;
    private String rOLE_TYPE_NURSE_LEADER;
    private String creationtime;
    private String lastupdatetime;
    private String isdeleted;
    private String name;
    private String rOLE_TYPE_ADMIN;
    private String wardid;
    private String roletype;
    private String patientList;

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getrOLE_TYPE_NURSE() {
        return rOLE_TYPE_NURSE;
    }

    public void setrOLE_TYPE_NURSE(String rOLE_TYPE_NURSE) {
        this.rOLE_TYPE_NURSE = rOLE_TYPE_NURSE;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getUserPatientList() {
        return userPatientList;
    }

    public void setUserPatientList(String userPatientList) {
        this.userPatientList = userPatientList;
    }

    public String getLastupdatedby() {
        return lastupdatedby;
    }

    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getrOLE_TYPE_DOCTOR() {
        return rOLE_TYPE_DOCTOR;
    }

    public void setrOLE_TYPE_DOCTOR(String rOLE_TYPE_DOCTOR) {
        this.rOLE_TYPE_DOCTOR = rOLE_TYPE_DOCTOR;
    }

    public String getrOLE_TYPE_NURSE_LEADER() {
        return rOLE_TYPE_NURSE_LEADER;
    }

    public void setrOLE_TYPE_NURSE_LEADER(String rOLE_TYPE_NURSE_LEADER) {
        this.rOLE_TYPE_NURSE_LEADER = rOLE_TYPE_NURSE_LEADER;
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(String lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getrOLE_TYPE_ADMIN() {
        return rOLE_TYPE_ADMIN;
    }

    public void setrOLE_TYPE_ADMIN(String rOLE_TYPE_ADMIN) {
        this.rOLE_TYPE_ADMIN = rOLE_TYPE_ADMIN;
    }

    public String getWardid() {
        return wardid;
    }

    public void setWardid(String wardid) {
        this.wardid = wardid;
    }

    public String getRoletype() {
        return roletype;
    }

    public void setRoletype(String roletype) {
        this.roletype = roletype;
    }

    public String getPatientList() {
        return patientList;
    }

    public void setPatientList(String patientList) {
        this.patientList = patientList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static DBUserEntity convertToDB(UserEntity entity){
        if (entity == null){
            LogUtil.i("Cannot convert to DB since user entity is null!");
            return null;
        }
        DBUserEntity user = new DBUserEntity();
        user.setCreatedby(entity.getCreatedby());
        user.setCreationtime(entity.getCreationtime());
        user.setEmpno(entity.getEmpno());
        user.setIsdeleted(entity.getIsdeleted());
        user.setLastupdatedby(entity.getLastupdatedby());
        user.setLastupdatetime(entity.getLastupdatetime());
        user.setName(entity.getName());
        user.setPassword(entity.getPassword());
        user.setPatientList(entity.getPatientList());
        user.setRoletype(entity.getRoletype());
        user.setUserId(entity.getId());
        user.setWardid(entity.getWardid());
        return user;
    }

    public static UserEntity convertFromDB(DBUserEntity entity){
        if (entity == null){
            LogUtil.i("Cannot convert from DB since user entity is null!");
            return null;
        }
        UserEntity user = new UserEntity();
        user.setCreatedby(entity.getCreatedby());
        user.setCreationtime(entity.getCreationtime());
        user.setEmpno(entity.getEmpno());
        user.setIsdeleted(entity.getIsdeleted());
        user.setLastupdatedby(entity.getLastupdatedby());
        user.setLastupdatetime(entity.getLastupdatetime());
        user.setName(entity.getName());
        user.setPassword(entity.getPassword());
        user.setPatientList(entity.getPatientList());
        user.setRoletype(entity.getRoletype());
        user.setId(entity.getUserId());
        user.setWardid(entity.getWardid());
        return user;
    }
}
