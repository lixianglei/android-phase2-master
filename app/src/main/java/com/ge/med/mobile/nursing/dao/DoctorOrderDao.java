package com.ge.med.mobile.nursing.dao;

import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.db.DBHisOrder;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */
public interface DoctorOrderDao extends BaseDao {
    void clearAll();
    Integer saveDoctorOrder(DBHisOrder data);

    boolean saveDoctorOrder(YiZhuBean.DataBean data);

    boolean saveDoctorOrder(YiZhuBean.DataBean dataBean, boolean isModified);

    void saveDoctorOrders(List<YiZhuBean.DataBean> datas);
    void saveDoctorOrders(List<YiZhuBean.DataBean> datas,boolean isModefine);
    String getExceptionForCheck(Integer hisId);
    void saveExceptionForCheck(Integer hisId, String exceptionMsg);
    YiZhuBean.DataBean getDoctorOrderByHisId(Integer hisId);
    YiZhuBean.DataBean getDoctorOrderByHisId(String qrCode);
    List<YiZhuBean.DataBean> findDoctorOrdersInJsonBean(String patientid, String orderType, String orderStatus);

    List<YiZhuBean.DataBean> findDoctorOrdersExcludeStatus(String patientid, String orderType, String orderStatus);

    List<YiZhuBean.DataBean> getZPaiXuDBYiZhuDatabean(List<YiZhuBean.DataBean> paiDBYiZhuData);

    List<YiZhuBean.DataBean> getFPaiXuDBYiZhuDatabean(List<YiZhuBean.DataBean> paiDBYiZhuData);
}
