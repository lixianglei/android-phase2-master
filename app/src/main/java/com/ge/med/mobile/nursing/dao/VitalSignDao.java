package com.ge.med.mobile.nursing.dao;

import com.ge.med.mobile.nursing.forjson.entity.VitalSignSheet;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignWardDefine;

import java.util.List;

/**
 * Created by Qu on 2016/12/7.
 */
public interface VitalSignDao extends BaseDao {
    public void clearAll();

    public void clearAllDefine();

    public void clearAllRecord();

    public void delete(VitalSignSheet vitalSheet);

    public List<VitalSignWardDefine> findAllWardDefineFromDB();

    public VitalSignWardDefine getVitalSignWardDefineByIdFromDB(int defineId);

    public List<VitalSignSheet> findVitalSignsFromDB(String patientId);

    public VitalSignSheet findLastVitalSignsFromDB(String patientId);

    public VitalSignSheet getVitalSignByIdFromDB(Integer vitalSignId);

    public void saveVitalSignWardDefines(List<VitalSignWardDefine> aDefines);

    public void saveVitalSigns(List<VitalSignSheet> lstVitalSign);

    public void saveVitalSign(VitalSignSheet vitalSign);

    public void updateVitalSignSheetStatus(Integer sheetId, String status);
}
