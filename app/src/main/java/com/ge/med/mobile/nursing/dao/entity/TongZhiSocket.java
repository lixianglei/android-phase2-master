package com.ge.med.mobile.nursing.dao.entity;

import java.util.List;

/**网络访问实体JSON解析Bean
 * Created by Administrator on 2016/12/22.
 */
public class TongZhiSocket {
    /**
     * serverTime : 1491812463396
     * token : pcx-8944d66c-ec17-4b17-beb8-c33ffb5c1e9d
     * type : notifyOrder
     */

    private long serverTime;
    private String token;
    private String type;
    private String hisOrderId;

    public String getHisOrderId() {
        return hisOrderId;
    }

    public void setHisOrderId(String hisOrderId) {
        this.hisOrderId = hisOrderId;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
