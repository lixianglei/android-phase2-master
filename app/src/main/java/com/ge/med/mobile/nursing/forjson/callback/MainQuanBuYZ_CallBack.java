package com.ge.med.mobile.nursing.forjson.callback;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public abstract class MainQuanBuYZ_CallBack extends StringCallback {
    public YiZhuBean yiZhuBean;

    @Override
    public void onResponse(String response, int id) {
        Log.d("医嘱信息-->", response);
        yiZhuBean = JSON.parseObject(response, YiZhuBean.class);
        final List<YiZhuBean.DataBean> datas = yiZhuBean.getData();
        System.out.println("MainQuanBuYZ_CallBack.onResponse===>" + datas);
    }
}
