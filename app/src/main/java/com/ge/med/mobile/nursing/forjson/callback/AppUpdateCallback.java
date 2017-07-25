package com.ge.med.mobile.nursing.forjson.callback;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.forjson.entity.AppUpdateBean;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * Created by Lisa on 2017/6/5.
 * 获取最新的版本
 */

public abstract class AppUpdateCallback extends StringCallback {
    public AppUpdateBean appUpdateBean;

    @Override
    public void onResponse(String response, int id) {
        appUpdateBean = JSON.parseObject(response, AppUpdateBean.class);
    }
}
