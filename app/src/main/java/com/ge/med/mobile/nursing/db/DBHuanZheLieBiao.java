package com.ge.med.mobile.nursing.db;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/21.
 * 患者列表
 */
public class DBHuanZheLieBiao extends DataSupport implements Serializable {
    private String age;
    private String allergyhistory;
    private String assessresut;
    private String bedno;
    private String breathmethod;
    private String carelevel;
    private String createdby;
    private Long creationtime;
    private String diagnosis;
    private String doctorid;
    private int id;
    private String inhospitaltime;
    private String isdeleted;
    private String lastupdatedby;
    private String lastupdatetime;
    private String name;
    private String note;
    private String outhospitaltime;
    private String patientid;
    private String sex;
    private Integer userid;
    private String nomoneystatus;
    private String illdetial;
    private String doctorname;
    private String wandaicode;

    public String getWandaicode() {
        return wandaicode;
    }

    public void setWandaicode(String wandaicode) {
        this.wandaicode = wandaicode;
    }

    public String getNomoneystatus() {
        return nomoneystatus;
    }

    public void setNomoneystatus(String nomoneystatus) {
        this.nomoneystatus = nomoneystatus;
    }

    private String voOrderStatus;
    private String wardid;
    private String mrnno;//住院号

    @Override
    public String toString() {
        return "DBHuanZheLieBiao{" +
                "age='" + age + '\'' +
                ", allergyhistory='" + allergyhistory + '\'' +
                ", assessresut='" + assessresut + '\'' +
                ", bedno='" + bedno + '\'' +
                ", breathmethod='" + breathmethod + '\'' +
                ", carelevel='" + carelevel + '\'' +
                ", createdby='" + createdby + '\'' +
                ", creationtime=" + creationtime +
                ", diagnosis='" + diagnosis + '\'' +
                ", doctorid='" + doctorid + '\'' +
                ", dcotorname='" + doctorname + '\'' +
                ", id=" + id +
                ", inhospitaltime='" + inhospitaltime + '\'' +
                ", isdeleted='" + isdeleted + '\'' +
                ", lastupdatedby='" + lastupdatedby + '\'' +
                ", lastupdatetime='" + lastupdatetime + '\'' +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", outhospitaltime='" + outhospitaltime + '\'' +
                ", patientid='" + patientid + '\'' +
                ", sex='" + sex + '\'' +
                ", userid=" + userid +
                ", nomoneystatus='" + nomoneystatus + '\'' +
                ", illdetial='" + illdetial + '\'' +
                ", voOrderStatus='" + voOrderStatus + '\'' +
                ", wardid='" + wardid + '\'' +
                ", mrnno='" + mrnno + '\'' +
                '}';
    }

    public String getMrnno() {
        return mrnno;
    }

    public void setMrnno(String mrnno) {
        this.mrnno = mrnno;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public String getBedno() {
        return bedno;
    }

    public void setBedno(String bedno) {
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

    public Long getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Long creationtime) {
        this.creationtime = creationtime;
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

    public String getInhospitaltime() {
        return inhospitaltime;
    }

    public void setInhospitaltime(String inhospitaltime) {
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

    public String getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(String lastupdatetime) {
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

    public String getOuthospitaltime() {
        return outhospitaltime;
    }

    public void setOuthospitaltime(String outhospitaltime) {
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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getVoOrderStatus() {
        return voOrderStatus;
    }

    public void setVoOrderStatus(String voOrderStatus) {
        this.voOrderStatus = voOrderStatus;
    }

    public String getWardid() {
        return wardid;
    }

    public void setWardid(String wardid) {
        this.wardid = wardid;
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
}
