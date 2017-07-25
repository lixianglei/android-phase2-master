package com.ge.med.mobile.nursing.dao;

import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.forjson.entity.AssessRecordBean;
import com.ge.med.mobile.nursing.forjson.entity.WardRiskDefine;
import com.ge.med.mobile.nursing.ui.data.RiskDefine;

import java.util.List;

/**
 * Created by Qu on 2016/12/7.
 */
public interface AssessDao extends BaseDao{
    public void clearAll();
    public void clearAllDefine();
    public void clearAllRecord();
    public void delete(AssessRecordBean assess);
    public List<AssessDefine.DataBean> findAllAssessDefine();
    public List<RiskDefine> findAllRiskDefine();
    public AssessDefine.DataBean getAssessDefineById(int defineId);
    public List<AssessRecordBean> findAssessesFromDB(String patientId);
    public AssessRecordBean getAssessById(Integer assessId);
    public void saveAssessDefines(List<AssessDefine.DataBean> aDefines);
    public void saveWardRiskDefines(List<WardRiskDefine> aDefines);
    public void saveAssesses(List<AssessRecordBean> lstAssess);
    public void saveAssess(AssessRecordBean assess);
    public void updateAssessStatus(Integer assessId, String status);
}
