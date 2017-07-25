package com.ge.med.mobile.nursing.dao.impl;

import com.ge.med.mobile.nursing.dao.DoctorOrderDao;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.db.DBHisOrder;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/24.
 */
public class DoctorOrderDaoImpl extends BaseDaoImpl implements DoctorOrderDao {
    @Override
    public void clearAll() {
        DataSupport.deleteAll(DBHisOrder.class);
//        DataSupport.deleteAll(DBExceptionDefine.class);
//        DataSupport.deleteAll(DBExceptionConfig.class);
    }

    @Override
    public Integer saveDoctorOrder(DBHisOrder dbEntity) {
        Integer retval = null;
        if (null == dbEntity) {
            LogUtil.i("Can not save anything because nothing to be saved!");
        } else {
            if (dbEntity.getId() > 0){
                retval = dbEntity.getId();
                dbEntity.update(dbEntity.getId());
                LogUtil.d("Successfully updated record[" + dbEntity.getId() + "] for doctor order id[" + dbEntity.getZid() + "]!");
            }else {
                DBHisOrder order = DataSupport.where("zid = ?", dbEntity.getZid() + "").findFirst(DBHisOrder.class);
                if (order != null) {
                    dbEntity.update(order.getId());
                    retval = order.getId();
                }else {
                    boolean save = dbEntity.save();
                    if (!save) {
                        LogUtil.e("Cannot insert doctor order into DB table for orderid[" + dbEntity.getZid() + "]");
                    } else {
                        retval = dbEntity.getId();
                        LogUtil.d("Successfully insert doctor order into DB table[" + retval + "] for orderid[" + dbEntity.getZid() + "]");
                    }
                }
            }
        }
        return retval;
    }

    @Override
    public boolean saveDoctorOrder(YiZhuBean.DataBean dataBean) {
        return saveDoctorOrder(dataBean, false);
    }

    @Override
    public boolean saveDoctorOrder(YiZhuBean.DataBean dataBean, boolean isModified) {
        LogUtil.d("calling saveDoctorOrder ...");
        if (dataBean == null) {
            LogUtil.i("Can not save doctor order since databean is null!");
            return false;
        }
        Integer dbId = saveDoctorOrder(DBHisOrder.convertToDB(dataBean, isModified));
        if (dbId != null) dataBean.setDbId(dbId);
        return true;
    }

    @Override
    public void saveDoctorOrders(List<YiZhuBean.DataBean> datas) {
        if (datas == null) {
            LogUtil.i("Can not save doctor orders since given collection is null!");
            return;
        }
        datas = new ArrayList<>(datas);
        for (YiZhuBean.DataBean data : datas) {
            saveDoctorOrder(data);
        }
    }

    @Override
    public void saveDoctorOrders(List<YiZhuBean.DataBean> datas, boolean isModefine) {
        if (datas == null) {
            LogUtil.i("Can not save doctor orders since given collection is null!");
            return;
        }
        for (YiZhuBean.DataBean data : datas) {
            saveDoctorOrder(data,isModefine);
        }
    }

    @Override
    public String getExceptionForCheck(Integer hisId) {
        if (hisId == null){
            LogUtil.i("Can not get doctor order's exception by his id since hisId is null");
            return null;
        }
        String retval = null;
        DBHisOrder order = DataSupport.where("zid = ?", hisId.toString()).findFirst(DBHisOrder.class);
        if (order != null && order.getYiChang() != null && order.getYiChang().equals(Boolean.valueOf(true).toString())){
            retval = order.getYiChangXinXi();
        }
        return retval;
    }

    @Override
    public void saveExceptionForCheck(Integer hisId, String exceptionMsg) {
        if (hisId == null){
            LogUtil.i("Can not save doctor order's exception since hisid is null");
        }else{
            DBHisOrder order = DataSupport.where("zid = ?", hisId.toString()).findFirst(DBHisOrder.class);
            if (order == null){
                LogUtil.i("Can not update order's exception since no local record for hisId" + hisId);
            }else{
                if (exceptionMsg == null || exceptionMsg.trim().isEmpty()){
                    order.setYiChang(Boolean.valueOf(false).toString());
                    order.setYiChangXinXi("");
                }else {
                    order.setYiChang(Boolean.valueOf(true).toString());
                    order.setYiChangXinXi(exceptionMsg);
                }
                order.update(order.getId());
            }
        }
    }

    @Override
    public YiZhuBean.DataBean getDoctorOrderByHisId(String qrCode) {
        YiZhuBean.DataBean retval = null;
        if (qrCode != null) {
            Map<String, String[]> conditions = new HashMap<>();
            if (!generateWhereConditions(conditions, "orderbarcode", qrCode + "", "=")) {
                LogUtil.e("Can not query from local DB since where qrCode cannot generate!");
            }
            retval = DBHisOrder.convertToDB(DataSupport.where(conditions.get(DoctorOrderDaoImpl.WHERE_KEY_NAME)).findFirst(DBHisOrder.class));
        }else LogUtil.e("Can not get doctor order from local DB since given qrCode is null!");
        return retval;
    }

    @Override
    public YiZhuBean.DataBean getDoctorOrderByHisId(Integer hisId) {
        YiZhuBean.DataBean retval = null;
        if (hisId != null) {
            Map<String, String[]> conditions = new HashMap<>();
            if (!generateWhereConditions(conditions, "zid", hisId + "", "=")) {
                LogUtil.e("Can not query from local DB since where zid cannot generate!");
            }
            retval = DBHisOrder.convertToDB(DataSupport.where(conditions.get(DoctorOrderDaoImpl.WHERE_KEY_NAME)).findFirst(DBHisOrder.class));
        }else LogUtil.e("Can not get doctor order from local DB since given hisId is null!");
        return retval;
    }

    @Override
    public List<YiZhuBean.DataBean> findDoctorOrdersInJsonBean(String patientid, String orderType, String orderStatus) {
        List<YiZhuBean.DataBean> retval = new ArrayList<>();
        Map<String, String[]> conditions = getCondition(patientid, orderType, orderStatus);
        List<DBHisOrder> dbLst = DataSupport.where(conditions.get(DoctorOrderDaoImpl.WHERE_KEY_NAME)).find(DBHisOrder.class);
        if (dbLst != null) {
            for (DBHisOrder dbHisOrder : dbLst) {
                retval.add(DBHisOrder.convertToDB(dbHisOrder));
            }
        }
        return retval;
    }

    @Override
    public List<YiZhuBean.DataBean> findDoctorOrdersExcludeStatus(String patientid, String orderType, String orderStatus) {
        List<YiZhuBean.DataBean> retval = new ArrayList<>();
        Map<String, String[]> conditions = new HashMap<>();
        if (!generateWhereConditions(conditions, "patientid", patientid, "=")) {
            LogUtil.i("Can not generate where condition for patientid[" + patientid + "]!");
            return retval;
        }
        if (!generateWhereConditions(conditions, "ordertype", orderType, "=")) {
            LogUtil.i("Can not generate where condition for ordertype[" + orderType + "]!");
        }
        if (!generateWhereConditions(conditions, "orderststus", orderStatus, "<>")) {
            LogUtil.i("Can not generate where condition for orderStatus[" + orderStatus + "]!");
        }
        List<DBHisOrder> dbLst = DataSupport.where(conditions.get(DoctorOrderDaoImpl.WHERE_KEY_NAME)).find(DBHisOrder.class);
        LogUtil.d("findDoctorOrdersExcludeStatus>Found [" + (dbLst==null?"null":dbLst.size()) + ")] records from DBHisOrder table by patientid["
                + patientid + "], orderTyp[" + orderType + "], orderStatus[" + orderStatus + "].");
        if (dbLst != null) {
            for (DBHisOrder dbHisOrder : dbLst) {
                retval.add(DBHisOrder.convertToDB(dbHisOrder));
            }
        }
        return retval;
    }

    private Map<String, String[]> getCondition(String patientid, String orderType, String orderStatus) {
        Map<String, String[]> conditions = new HashMap<>();
        if (!generateWhereConditions(conditions, "patientid", patientid, "=")) {
            LogUtil.e("Can not query from local DB since where clause cannot generate!");
        }
        if (!generateWhereConditions(conditions, "ordertype", orderType, "=")) {
            LogUtil.e("Can not query from local DB since where clause cannot generate!");
        }
        if (!generateWhereConditions(conditions, "orderststus", orderStatus, "=")) {
            LogUtil.e("Can not query from local DB since where clause cannot generate!");
        }
        return conditions;
    }

    @Override
    public List<YiZhuBean.DataBean> getZPaiXuDBYiZhuDatabean(List<YiZhuBean.DataBean> paiDBYiZhuData) {
        Comparator<YiZhuBean.DataBean> comparator = new Comparator<YiZhuBean.DataBean>() {
            @Override
            public int compare(YiZhuBean.DataBean s1, YiZhuBean.DataBean s2) {
                if (s1.getStarttime().longValue() > s2.getStarttime().longValue()) {
                    return 1;
                } else if (s2.getStarttime().longValue() == s1.getStarttime().longValue()) {
                    return 0;
                }
                return -1;
            }
        };
        Collections.sort(paiDBYiZhuData, comparator);
        return paiDBYiZhuData;
    }

    @Override
    public List<YiZhuBean.DataBean> getFPaiXuDBYiZhuDatabean(List<YiZhuBean.DataBean> paiDBYiZhuData) {
        if (paiDBYiZhuData == null) return null;
        Comparator<YiZhuBean.DataBean> comparator = new Comparator<YiZhuBean.DataBean>() {
            public int compare(YiZhuBean.DataBean s1, YiZhuBean.DataBean s2) {
                try {
                    if (s2.getStarttime().longValue() > s1.getStarttime().longValue()) {
                        return 1;
                    } else if (s2.getStarttime().longValue() == s1.getStarttime().longValue()) {
                        return 0;
                    }
                } catch (Exception e) {
                    LogUtil.e("Starttime is null!");
                }
                return -1;
                //return (int) (s2.getStarttime() - s1.getStarttime());
            }
        };
        Collections.sort(paiDBYiZhuData, comparator);
        return paiDBYiZhuData;
    }

    @Override
    public Class getDBTable() {
        return DBHisOrder.class;
    }

    @Override
    public Class getJsonBean() {
        return YiZhuBean.DataBean.class;
    }

}
