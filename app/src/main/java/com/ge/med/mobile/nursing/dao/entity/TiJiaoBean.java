package com.ge.med.mobile.nursing.dao.entity;

import com.ge.med.mobile.nursing.forjson.entity.BaseJSONBean;

/** 定义网络访问实体JSON解析Bean
 * Created by Administrator on 2016/11/28.
 */
public class TiJiaoBean implements BaseJSONBean {

    /**
     * status : 1
     * data : null
     * msg : 成功
     */

    private Integer status;
    private Object data;
    private String msg;
@Override
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
@Override
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getCallName() {
        return "submit single doctor order";
    }
}
