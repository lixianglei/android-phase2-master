package com.ge.med.mobile.nursing.config;

/**
 * Created by Administrator on 2016/10/28.
 */
public class TouchTime {
    public static long NOW_TIME;
    // 分次间隔时间 (分钟)
    public static  Integer fenCi_F = 30 ;
    // 分次间隔时间 (毫秒)
    public static  long getFenCi_H (long fenCi_F){
        long fenCi_H = fenCi_F*60000;
        return fenCi_H;
    }

}
