package com.ge.med.mobile.nursing.forjson.callback;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.dao.entity.LoginBean;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * Created by Administrator on 2016/11/29.
 */
public abstract class LoginCallBack extends StringCallback {
 public   LoginBean  loginBean;
    @Override
    public void onResponse(String response, int id) {
          loginBean = JSON.parseObject(response, LoginBean.class);
    }
}
