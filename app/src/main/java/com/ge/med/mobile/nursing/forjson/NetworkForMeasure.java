package com.ge.med.mobile.nursing.forjson;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.MeasureDefineBean;
import com.ge.med.mobile.nursing.dao.entity.TiJiaoXuanJiao;
import com.ge.med.mobile.nursing.dao.entity.XuanJiaoJiLu;
import com.ge.med.mobile.nursing.db.DBMeasureDefine;
import com.ge.med.mobile.nursing.db.DBXuanJiaoRecord;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import okhttp3.Call;

/**
 * Created by xxl on 2017/4/14.
 */

public class NetworkForMeasure {

    public static void callMeasureDefine() {
        OkHttpUtils.get().url(URL.URL_HOUJI_CUOSHI_DEFINE).addHeader("User-Agent", "www.gs.com")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("后继措施:"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null) {
                            MeasureDefineBean measureDefineBean = JSON.parseObject(response, MeasureDefineBean.class);
                            if (measureDefineBean != null && measureDefineBean.getData() != null && measureDefineBean.getData().size() > 0) {
                                DataSupport.deleteAll(DBMeasureDefine.class);
                                DBMeasureDefine dbMeasureDefine;
                                for(MeasureDefineBean.DataBean defineBean:measureDefineBean.getData()){
                                    dbMeasureDefine = DBMeasureDefine.convertBean(defineBean);
                                    boolean save = dbMeasureDefine.save();
                                    LogUtil.d("后继措施:defineCache"+save+dbMeasureDefine.getAssessDefineCode());
                                }
                            }

                        }
                    }
                });
    }
}
