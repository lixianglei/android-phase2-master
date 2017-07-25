package com.ge.med.mobile.nursing.dao;

import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */
public interface HuanZheLieBiaoInterface {
    //获得护士的责任患者列表
    List<DBHuanZheLieBiao> findPatientsByUserId(String userId);
    //封装单个数据
    DBHuanZheLieBiao getDBHuanZheLieBiao(String patient_id);

    //封装集合数据
    List<DBHuanZheLieBiao> getListDBHuanZheLieBiao();

    //集合床号正序
    List<DBHuanZheLieBiao> getZhengXuList(List<DBHuanZheLieBiao> datas);
    //集合床号正序
    List<HuanZheLieBiaoBean.DataBean> getZhengXuListBean(List<HuanZheLieBiaoBean.DataBean> datas);

    //集合床号倒序
    List<DBHuanZheLieBiao> getDaoXuList(List<DBHuanZheLieBiao> datas);
    //集合床号倒序
    List<HuanZheLieBiaoBean.DataBean> getDaoXuListBean(List<HuanZheLieBiaoBean.DataBean> datas);

    //DBHuanZheLieBiao 转换称databean 集合
    List<HuanZheLieBiaoBean.DataBean> getDataBeanList(List<DBHuanZheLieBiao> datas);
    HuanZheLieBiaoBean.DataBean getDataBean(DBHuanZheLieBiao dbHuanZheLieBiao);
    //集合床号正序
    List<HuanZheLieBiaoBean.DataBean> loadPatientByUserId(String userId);
    void savePatients(List<HuanZheLieBiaoBean.DataBean> datas);
    void updatePatientAssessResult(HuanZheLieBiaoBean.DataBean patient);
}
