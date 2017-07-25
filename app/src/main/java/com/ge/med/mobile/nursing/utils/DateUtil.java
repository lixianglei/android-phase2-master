package com.ge.med.mobile.nursing.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Long addMinute(Long source, Integer minute) {
        Calendar time = Calendar.getInstance();
        time.setTime(new Date(source));
        time.add(Calendar.MINUTE, minute);
        return time.getTimeInMillis();
    }

    /**
     * long转成字符串格式
     */
    public static String longToString(long srcTime) {
        Date date = new Date(srcTime);
        return sdf.format(date);
    }
}
