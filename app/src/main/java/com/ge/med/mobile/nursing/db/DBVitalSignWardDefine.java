package com.ge.med.mobile.nursing.db;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignWardDefine;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qu on 2016/12/10.
 */
public class DBVitalSignWardDefine extends DataSupport {
    private int id;
    private int warDefineId;
    private String vcode;
    private String wardid;
    private int vitaldefineid;
    private String jsonString;

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVitaldefineid() {
        return vitaldefineid;
    }

    public void setVitaldefineid(int vitaldefineid) {
        this.vitaldefineid = vitaldefineid;
    }

    public String getWardid() {
        return wardid;
    }

    public void setWardid(String wardid) {
        this.wardid = wardid;
    }

    public int getWarDefineId() {
        return warDefineId;
    }

    public void setWarDefineId(int warDefineId) {
        this.warDefineId = warDefineId;
    }

    public static List<DBVitalSignWardDefine> convertToDB(List<VitalSignWardDefine> list) {
        if (null == list) {
            LogUtil.i("Cannot convert to DB since list of VitalSignSheet is null!");
            return null;
        }
        List<DBVitalSignWardDefine> retval = new ArrayList<>();
        DBVitalSignWardDefine dbDefine = null;
        for (VitalSignWardDefine define : list) {
            dbDefine = convertToDB(define);
            if (null != dbDefine) retval.add(dbDefine);
        }
        return retval;
    }

    public static DBVitalSignWardDefine convertToDB(VitalSignWardDefine entity) {
        if (null == entity) {
            LogUtil.i("Cannot convert to DB since entity is null!");
            return null;
        }
        DBVitalSignWardDefine retval = new DBVitalSignWardDefine();
        retval.setVitaldefineid(entity.getVitaldefineid());
        retval.setWarDefineId(entity.getId());
        if (entity.getVitalDefine() != null){
            if (entity.getVitalDefine().getVcode() != null) retval.setVcode(entity.getVitalDefine().getVcode());
            if (entity.getDefaultvitalnoteid() != null) {
                entity.getVitalDefine().setDefaultvitalnoteid(entity.getDefaultvitalnoteid());
            }
            if (entity.getSortvitalno() != null) entity.getVitalDefine().setPosition(entity.getSortvitalno());
        }
        retval.setWardid(entity.getWardid());
        retval.setJsonString(JSON.toJSONString(entity, SerializerFeature
                .WriteNullListAsEmpty, SerializerFeature.DisableCircularReferenceDetect));
        return retval;
    }

    public static List<VitalSignWardDefine> convertFromDB(List<DBVitalSignWardDefine> list) {
        if (null == list) {
            LogUtil.i("Cannot convert to DB since list of VitalSignSheet is null!");
            return null;
        }
        List<VitalSignWardDefine> retval = new ArrayList<>();
        VitalSignWardDefine dbDefine = null;
        for (DBVitalSignWardDefine define : list) {
            dbDefine = convertFromDB(define);
            if (null != dbDefine) retval.add(dbDefine);
        }
        return retval;
    }

    public static VitalSignWardDefine convertFromDB(DBVitalSignWardDefine entity) {
        if (null == entity) {
            LogUtil.i("Cannot convert to DB since entity is null!");
            return null;
        }
        if (entity.getJsonString() != null) {
            LogUtil.d("Vital Define for ward is :" + entity.getJsonString());
            return JSON.parseObject(entity.getJsonString(), VitalSignWardDefine.class);
        }
        VitalSignWardDefine retval = new VitalSignWardDefine();
        retval.setVitaldefineid(entity.getVitaldefineid());
        retval.setId(entity.getWarDefineId());
        retval.setWardid(entity.getWardid());
        return retval;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
