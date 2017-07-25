package com.ge.med.mobile.nursing.forjson;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.db.DBNursingEventTempLateLis;
import com.ge.med.mobile.nursing.db.DBNursingLieBiao;
import com.ge.med.mobile.nursing.forjson.syncDifine.CacheDefine;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/29.
 */
public class NetworkForNursingEventTempLateLis {

    public static void callNursingEventTempLateLis(String wardId) {
        if (wardId == null) {
            LogUtil.d("wardId is null!");
            return;
        }
        OkHttpUtils.get()
                .url(URL.URL_HULI_JILU_LIEBIAO)
                .addHeader("User-Agent", "www.gs.com")
                .addParams("wardId", wardId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        CacheDefine.showDialog();
                        LogUtil.e(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if(response!=null){
                            DataSupport.deleteAll(DBNursingLieBiao.class);
                            DBNursingLieBiao dbNursingLieBiao = new DBNursingLieBiao();
                            dbNursingLieBiao.setJsonString(response);
                            if( dbNursingLieBiao.save()){
                                CacheDefine.changeStatus(Constant.GLOBAL_KEY_IS_CACHE_NUESINGEV_LIEBIAO,false);//改变cache状态
                            }
                        }else{
                            LogUtil.d("response is null!");
                        }
                    }
                });
        OkHttpUtils.get()
                .url(URL.URL_HULI_JILU_SHIJIAN)
                .addHeader("User-Agent", "www.gs.com")
                .addParams("wardId", wardId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        CacheDefine.showDialog();
                        LogUtil.e(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if(response!=null){
                            DataSupport.deleteAll(DBNursingEventTempLateLis.class);
                            DBNursingEventTempLateLis dbNursingEventTempLateLis = new DBNursingEventTempLateLis();
                            dbNursingEventTempLateLis.setJsonString(response);
                            if( dbNursingEventTempLateLis.save()){
                                CacheDefine.changeStatus(Constant.GLOBAL_KEY_IS_CACHE_NUESINGEVENTTEMP,false);//改变cache状态
                            }
                        }else{
                            LogUtil.d("response is null!");
                        }
                    }
                });
    }

    public static void callNursingLieBiao(String wardId) {
        if (wardId == null) {
            LogUtil.d("wardId is null!");
            return;
        }
        OkHttpUtils.get()
                .url(URL.URL_HULI_JILU_LIEBIAO)
                .addHeader("User-Agent", "www.gs.com")
                .addParams("wardId", wardId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        CacheDefine.showDialog();
                        LogUtil.e(e.getMessage()+"");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if(response!=null){
                            DataSupport.deleteAll(DBNursingLieBiao.class);
                            DBNursingLieBiao dbNursingLieBiao = new DBNursingLieBiao();
                            dbNursingLieBiao.setJsonString(response);
                            if( dbNursingLieBiao.save()){
                                CacheDefine.changeStatus(Constant.GLOBAL_KEY_IS_CACHE_NUESINGEV_LIEBIAO,false);//改变cache状态
                            }
                        }else{
                            LogUtil.d("response is null!");
                        }
                    }
                });
    }
}
