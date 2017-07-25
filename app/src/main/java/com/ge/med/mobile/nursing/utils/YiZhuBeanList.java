package com.ge.med.mobile.nursing.utils;

import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/30.
 */
public class YiZhuBeanList implements Serializable {
    private List<YiZhuBean.DataBean> mYizhuBeanList;

    public List<YiZhuBean.DataBean> getmYizhuBeanList() {
        return mYizhuBeanList;
    }

    public void setmYizhuBeanList(List<YiZhuBean.DataBean> mYizhuBeanList) {
        this.mYizhuBeanList = mYizhuBeanList;
    }
}
