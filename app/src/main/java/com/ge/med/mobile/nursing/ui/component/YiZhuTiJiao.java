package com.ge.med.mobile.nursing.ui.component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * Created by Administrator on 2016/12/5.
 */
public class YiZhuTiJiao {
    public static void tiJiao(YiZhuBean.DataBean dataBean, StringCallback stringCallback){
        String jsonString = JSONObject.toJSONString(dataBean, SerializerFeature.WriteNullListAsEmpty);
        System.out.println("jsonString--------->"+jsonString);
        OkHttpUtils.post()
                .url(URL.URL_SC_YZ)
                .addHeader("User-Agent", "www.gs.com")
                .addParams("jsonString", jsonString)
                .build()
                .execute(stringCallback);
    }
}
