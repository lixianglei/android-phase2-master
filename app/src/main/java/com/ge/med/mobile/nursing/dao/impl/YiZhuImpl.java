package com.ge.med.mobile.nursing.dao.impl;

import com.ge.med.mobile.nursing.dao.YiZhuInterface;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;

import org.xutils.common.util.LogUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */
public class YiZhuImpl implements YiZhuInterface {

    @Override
    public List<YiZhuBean.DataBean> getZPaiXuDBYiZhuDatabean(List<YiZhuBean.DataBean> paiDBYiZhuData) {
        Comparator<YiZhuBean.DataBean> comparator = new Comparator<YiZhuBean.DataBean>() {
            @Override
            public int compare(YiZhuBean.DataBean s1, YiZhuBean.DataBean s2) {
                if(s1.getStarttime().longValue() > s2.getStarttime().longValue()){
                    return 1;
                }
                else if (s2.getStarttime().longValue() == s1.getStarttime().longValue()){
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
                if(s2.getStarttime().longValue() > s1.getStarttime().longValue()){
                    return 1;
                }
                else if (s2.getStarttime().longValue() == s1.getStarttime().longValue()){
                    return 0;
                }
                return -1;
                //return (int) (s2.getStarttime() - s1.getStarttime());
            }
        };
        Collections.sort(paiDBYiZhuData,comparator);
        return paiDBYiZhuData;
    }
    public static List<YiZhuBean.DataBean.DoctorOrdersBean> paixuDoctorOrdersBean(List<YiZhuBean.DataBean.DoctorOrdersBean> doctorOrdersBeandataBeen) {
        Comparator<YiZhuBean.DataBean.DoctorOrdersBean> comparator = new Comparator<YiZhuBean.DataBean.DoctorOrdersBean>() {
            public int compare(YiZhuBean.DataBean.DoctorOrdersBean s1, YiZhuBean.DataBean.DoctorOrdersBean s2) {
                try {
                    if (s2.getStarttime().longValue() < s1.getStarttime().longValue()) {
                        return 1;
                    } else if (s2.getStarttime().longValue() == s1.getStarttime().longValue()) {
                        return 0;
                    }
                } catch (Exception e) {
                    LogUtil.e("IS NULL");
                }
                return -1;
            }
        };
        Collections.sort(doctorOrdersBeandataBeen, comparator);
        return doctorOrdersBeandataBeen;
    }

}
