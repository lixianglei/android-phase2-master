package com.ge.med.mobile.nursing.utils;

import java.util.UUID;

/**
 * Created by lisa on 2017/6/27.
 * 32位的唯一标识符
 */

public class UUIDTools {
    /**
     * 自动生成32位的UUid
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
