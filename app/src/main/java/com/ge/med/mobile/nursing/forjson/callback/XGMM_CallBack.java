package com.ge.med.mobile.nursing.forjson.callback;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.dao.entity.LoginBean;
import com.ge.med.mobile.nursing.dao.entity.LoginBean2;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * Created by Administrator on 2016/11/29.
 */
public abstract class XGMM_CallBack extends StringCallback {
  public    LoginBean2 loginBean;
    @Override
    public void onResponse(String response, int id) {
         loginBean = JSON.parseObject(response, LoginBean2.class);
    }
}
