package com.ge.med.mobile.nursing.dao;

import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */
public interface YiZhuInterface {
    //获得医嘱基本data
    List<YiZhuBean.DataBean> getZPaiXuDBYiZhuDatabean(List<YiZhuBean.DataBean> paiDBYiZhuData) ;

    //按时间排列
    List<YiZhuBean.DataBean> getFPaiXuDBYiZhuDatabean(List<YiZhuBean.DataBean> paiDBYiZhuData) ;




}
