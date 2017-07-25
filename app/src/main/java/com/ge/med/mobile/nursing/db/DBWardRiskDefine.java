package com.ge.med.mobile.nursing.db;

import com.ge.med.mobile.nursing.forjson.entity.WardRiskDefine;
import com.ge.med.mobile.nursing.ui.data.RiskDefine;

import org.litepal.crud.DataSupport;

/**
 * Created by Qu on 2016/12/17.
 */
public class DBWardRiskDefine extends DataSupport {
    private int id;
    private String riskId;
    private String riskName;
    private Integer rankNo;
    private String ShortName;

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public Integer getRankNo() {
        return rankNo;
    }

    public void setRankNo(Integer rankNo) {
        this.rankNo = rankNo;
    }

    public static RiskDefine convertFromDB(DBWardRiskDefine entity){
        return new RiskDefine(entity);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static DBWardRiskDefine convertToDB(WardRiskDefine define){
        DBWardRiskDefine retval = new DBWardRiskDefine();
        retval.setRankNo(define.getRisksortno());
        retval.setRiskId(define.getId());
        retval.setRiskName(define.getRiskname());
        retval.setShortName(define.getShortname());
        return retval;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }
}
