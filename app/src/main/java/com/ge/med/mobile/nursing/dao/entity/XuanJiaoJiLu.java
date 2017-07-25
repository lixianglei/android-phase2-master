package com.ge.med.mobile.nursing.dao.entity;

import java.util.List;

/**
 * 网络访问实体JSON解析Bean
 * Created by xxl on 2017/4/14.
 */

public class XuanJiaoJiLu {
    /**
     * msg : 成功
     * data : [{"edudefineid":16,"edumodetext":"示范","edurecordpk":1,"eduresulttext":"掌握","edutime":1492139182000,"eduuserid":3,"isdeleted":"","patientid":"AA1063","status":"0","userName":"晨晨"},{"edudefineid":16,"edumodetext":"讲解","edurecordpk":2,"eduresulttext":"未掌握","edutime":1492139183000,"eduuserid":4,"isdeleted":"","patientid":"AA1063","status":"0","userName":"张扬张扬"}]
     * status : 1
     */

    private String msg;
    private int status;
    private List<TiJiaoXuanJiao> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<TiJiaoXuanJiao> getData() {
        return data;
    }

    public void setData(List<TiJiaoXuanJiao> data) {
        this.data = data;

    }
}
