package com.ge.med.mobile.nursing.dao.entity;

import com.ge.med.mobile.nursing.forjson.entity.BaseJSONBean;
import com.ge.med.mobile.nursing.forjson.entity.IBaseBean;

/**网络访问实体JSON解析Bean
 * Created by Administrator on 2017/1/5.
 */
public class TuPianBean implements BaseJSONBean {

    /**
     * status : 1
     * data : http://192.168.140.253:8080//intelli/img_tmp/1.PNG
     * msg : 成功
     */

    private int status;
    private String data;
    private String msg;
    @Override
    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getCallName() {
        return "get image";
    }
}
