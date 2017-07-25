package com.ge.med.mobile.nursing.dao.entity;

/**网络访问实体JSON解析Bean
 * Created by Administrator on 2017/1/6.
 */
public class TongZhiShanChuBean {

    /**
     * userId : 3
     * orderId : 242
     * notifyStatus : 2
     */

    private String userId;
    private String notifyId;
    private String notifyStatus;

    public TongZhiShanChuBean() {
    }

    public TongZhiShanChuBean(String userId, String orderId, String notifyStatus) {
        this.userId = userId;
        this.notifyId = orderId;
        this.notifyStatus = notifyStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(String notifyStatus) {
        this.notifyStatus = notifyStatus;
    }
}
