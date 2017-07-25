package com.ge.med.mobile.nursing.ui.activity;

import android.graphics.Color;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;

import org.xutils.common.util.LogUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/1.
 */
public class ActivityUtils {


    public static boolean isExeTime(long startTime) {
        long l = 1000 * 60 * 60 * 2;
        return  Math.abs(startTime - System.currentTimeMillis()) > l;
    }

    public static int getColorByCareLevel(String carelevel) {
        int color = Color.parseColor("#ff00ff");
        switch (carelevel) {
            case "特级":
                color = Color.parseColor("#ff00ff");
                break;
            case "一级":
                color = Color.parseColor("#ff0000");
                break;
            case "二级":
                color = Color.parseColor("#00ff00");
                break;
            case "三级":
                color = Color.parseColor("#c8c8c8");
                break;
        }
        return color;
    }

    //算 哪个条目开始显示 时间；
    public static Map<Integer, Boolean> suanPos(List<YiZhuBean.DataBean> dataBeens) {
        String date = "";
        Map<Integer, Boolean> pos = new HashMap<>();
        if (dataBeens == null || dataBeens.isEmpty()) {
            return pos;
        }
        String dateString1 = null;
        for (int i = 0; i < dataBeens.size(); i++) {
            try {
                dateString1 =  DateUtils.getDateString("yyyy/MM/dd", dataBeens.get(i).getStarttime());
                if (!date.equals(dateString1)) {
                    date = dateString1;
                    pos.put(i, true);
                } else {
                    pos.put(i, false);
                }
            } catch (Exception e) {
                LogUtil.e(e.getMessage()+"");
            }

        }
        return pos;
    }
    //排序
    public static List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> getDBYiZhudata_OrderExecuteRecords(List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> paiDBYiZhuData) {
        Comparator<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> comparator = new Comparator<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean>() {
            @Override
            public int compare(YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean s1, YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean s2) {
                if (s1.getCreationtime() != null && s2.getCreationtime() != null) {
                    if (s1.getCreationtime() < s2.getCreationtime()) {
                        return 1;
                    } else if (s2.getCreationtime() == s1.getCreationtime()) {
                        return 0;
                    }
                }
                return -1;
            }
        };
        Collections.sort(paiDBYiZhuData, comparator);
        return paiDBYiZhuData;
    }
}
