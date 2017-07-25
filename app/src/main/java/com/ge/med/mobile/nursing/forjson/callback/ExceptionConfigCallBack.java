package com.ge.med.mobile.nursing.forjson.callback;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.dao.entity.ExceptionConfigBean;
import com.ge.med.mobile.nursing.db.DBExceptionConfig;
import com.ge.med.mobile.nursing.db.DBExceptionDefine;
import com.ge.med.mobile.nursing.forjson.syncDifine.CacheDefine;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;

import java.util.List;

import okhttp3.Call;

/**
 * Created by xxl on 2016/12/15.
 */
public class ExceptionConfigCallBack extends StringCallback {
    @Override
    public void onError(Call call, Exception e, int id) {
        CacheDefine.showDialog();
        e.printStackTrace();
        Log.d("异常信息存储", "网络失败");
    }

    @Override
    public void onResponse(String response, int id) {
        Log.d("异常信息存储", "返回数据————>" + response);
        ExceptionConfigBean exceptionConfigBean = JSON.parseObject(response, ExceptionConfigBean.class);
        if (exceptionConfigBean != null) {
            final List<ExceptionConfigBean.DataBean> data = exceptionConfigBean.getData();
            if (data != null && data.size() > 0) {
                new Thread() {
                    @Override
                    public void run() {
                        DataSupport.deleteAll(DBExceptionConfig.class);
                        DataSupport.deleteAll(DBExceptionDefine.class);
                        cunshuju(data);
                        CacheDefine.changeStatus(Constant.GLOBAL_KEY_IS_CACHE_EXCEPTIONDEFINE, false);
                    }
                }.start();
            }
        }
    }

    private void cunshuju(List<ExceptionConfigBean.DataBean> data) {
        for (ExceptionConfigBean.DataBean dataBean : data) {
//            List<DBExceptionConfig> dbExceptionConfigs = DataSupport.where("zid = ?", dataBean.getId() + "").find(DBExceptionConfig.class);
            DBExceptionConfig dbExceptionConfig = new DBExceptionConfig();
            dbExceptionConfig.setCreatedby(dataBean.getCreatedby());
            if (dataBean.getCreationtime() != null) {
                dbExceptionConfig.setCreationtime((Long) dataBean.getCreationtime());
            }
            dbExceptionConfig.setExceptiondefineid(dataBean.getExceptiondefineid());
            dbExceptionConfig.setZid(dataBean.getId());
            dbExceptionConfig.setIsdeleted(dataBean.getIsdeleted());
            dbExceptionConfig.setLastupdatetime(dataBean.getLastupdatetime());
            dbExceptionConfig.setLastupdatedby(dataBean.getLastupdatedby());
            dbExceptionConfig.setOrdertype(dataBean.getOrdertype());
            dbExceptionConfig.setOrderstatus(dataBean.getOrderstatus());
            if (dataBean.getExceptionDefine() != null) {
                cunshuju(dataBean.getExceptionDefine(), dataBean.getExceptiondefineid());
            }
//            if(dbExceptionConfigs!=null&&dbExceptionConfigs.size()>0){
//                dbExceptionConfig.updateAll("zid=?", dataBean.getId() + "");
//            }else{
            dbExceptionConfig.save();
//            }
        }
    }

    private void cunshuju(ExceptionConfigBean.DataBean.ExceptionDefineBean exceptionDefine, int Exceptiondefineid) {
//        List<DBExceptionDefine> dbExceptionDefines = DataSupport.where("zid = ?", exceptionDefine.getId() + "").find(DBExceptionDefine.class);
        DBExceptionDefine dbExceptionDefine = new DBExceptionDefine();
        dbExceptionDefine.setExceptionname(exceptionDefine.getExceptionname());
        dbExceptionDefine.setHandle(exceptionDefine.getHandle());
        dbExceptionDefine.setZid(exceptionDefine.getId());
        dbExceptionDefine.setType(exceptionDefine.getType());
        dbExceptionDefine.setDbexceptionid(Exceptiondefineid);
//        if(dbExceptionDefines!=null&&dbExceptionDefines.size()>0){
//            dbExceptionDefine.updateAll("zid=?", exceptionDefine.getId() + "");
//        }else{
        dbExceptionDefine.save();
//        }
    }
}
