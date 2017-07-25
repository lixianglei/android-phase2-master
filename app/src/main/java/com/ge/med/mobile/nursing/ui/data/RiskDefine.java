package com.ge.med.mobile.nursing.ui.data;

import com.ge.med.mobile.nursing.db.DBWardRiskDefine;
import com.ge.med.mobile.nursing.forjson.entity.WardRiskDefine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qu on 2016/12/17.
 */
public class RiskDefine implements Serializable {
    private String riskId;
    private String riskName;
    private String shortName;
    private Integer rankNo;

    public RiskDefine(WardRiskDefine define) {
        this.setRiskName(define.getRiskname());
        this.setRankNo(define.getRisksortno());
        this.setRiskId(define.getRiskid()+"");
        this.setShortName(define.getShortname());
    }
    public RiskDefine(DBWardRiskDefine define) {
        this.setRiskName(define.getRiskName());
        this.setRankNo(define.getRankNo());
        this.setRiskId(define.getRiskId());
        this.setShortName(define.getShortName());
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String String) {
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

    public static List<RiskDefine> convertFromJsonBeans(List<WardRiskDefine> lst){
        List<RiskDefine> retval = new ArrayList<>();
        if (lst != null){
            for(WardRiskDefine define : lst){
                retval.add(new RiskDefine(define));
            }
        }
        return retval;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
