package com.ge.med.mobile.nursing.db;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

/**
 * Created by Administrator on 2016/11/24.
 */
public class DBHisOrder extends DataSupport implements IBaseDB {
    private int id;
    private Integer zid;
    private String orderbarcode;//医嘱条码code
    private String orderststus;
    private String ordertype;
    private String patientid;
    private Long starttime;
    private String isModified;
    private String jsonString;
    private String yiChangXinXi;//核对医嘱
    private String yiChang; //核对医嘱 用

    public String getYiChangXinXi() {
        return yiChangXinXi;
    }

    public void setYiChangXinXi(String yiChangXinXi) {
        this.yiChangXinXi = yiChangXinXi;
    }

    public String getYiChang() {
        return yiChang;
    }

    public void setYiChang(String yiChang) {
        this.yiChang = yiChang;
    }

    public Integer getId() {
        return Integer.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getZid() {
        return zid;
    }

    public void setZid(Integer zid) {
        this.zid = zid;
    }

    public String getOrderststus() {
        return orderststus;
    }

    public void setOrderststus(String orderststus) {
        this.orderststus = orderststus;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public Long getStarttime() {
        return starttime;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public String getIsModified() {
        return isModified;
    }

    public void setIsModified(String modified) {
        isModified = modified;
    }


    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public static DBHisOrder convertToDB(YiZhuBean.DataBean entity, boolean isModified){
        DBHisOrder retval = new DBHisOrder();
        if (entity.getDbId() != null) retval.setId(entity.getDbId());
        retval.setJsonString(JSON.toJSONString(entity));
        retval.setIsModified(Boolean.valueOf(isModified).toString());
        retval.setOrderststus(entity.getOrderststus());
        retval.setOrdertype(entity.getOrdertype());
        retval.setPatientid(entity.getPatientid());
        retval.setStarttime(entity.getStarttime());
        retval.setZid(entity.getId());
        retval.setOrderbarcode(entity.getOrderbarcode());
        return retval;
    }
    public static YiZhuBean.DataBean convertToDB(DBHisOrder entity){
        if (entity == null || entity.getJsonString() == null){
            LogUtil.e("Can not convert anything since entity is null!");
            return null;
        }
        YiZhuBean.DataBean retval = JSON.parseObject(entity.getJsonString(), YiZhuBean.DataBean.class);
        if (entity.getId() > 0) retval.setDbId(Integer.valueOf(entity.getId()));
        return retval;
    }

    public String getOrderbarcode() {
        return orderbarcode;
    }

    public void setOrderbarcode(String orderbarcode) {
        this.orderbarcode = orderbarcode;
    }
}
