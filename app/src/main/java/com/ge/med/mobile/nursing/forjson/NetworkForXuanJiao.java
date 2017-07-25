package com.ge.med.mobile.nursing.forjson;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.TiJiaoXuanJiao;
import com.ge.med.mobile.nursing.dao.entity.XuanJiaoBean;
import com.ge.med.mobile.nursing.dao.entity.XuanJiaoJiLu;
import com.ge.med.mobile.nursing.db.DBXuanJiao;
import com.ge.med.mobile.nursing.db.DBXuanJiaoRecord;
import com.ge.med.mobile.nursing.forjson.entity.NetworkForImage;
import com.ge.med.mobile.nursing.forjson.syncDifine.CacheDefine;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Alex Qu on 2016/12/4
 */
public class NetworkForXuanJiao {

    public static void callXuanJiaoDefine(final String wardId) {
        OkHttpUtils.get().url(URL.URL_XUANJIAO).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_WARD_ID, wardId).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null) {
                            XuanJiaoBean xuanJiaoBean = JSON.parseObject(response, XuanJiaoBean.class);
                            if (xuanJiaoBean != null && xuanJiaoBean.getData() != null) {
                                List<XuanJiaoBean.DataBean> data = xuanJiaoBean.getData();
                                data = findImgPathList(data);
                                xuanJiaoBean.setData(data);
                            }
                            response = JSON.toJSONString(xuanJiaoBean);
                            DataSupport.deleteAll(DBXuanJiao.class); //清空数据库
                            DBXuanJiao dbXuanJiao = new DBXuanJiao();
                            dbXuanJiao.setJsonString(response);
                            boolean save = dbXuanJiao.save();
                            if (save) {
                                LogUtil.d("宣教定义储存成功 !");
                                CacheDefine.changeStatus(Constant.GLOBAL_KEY_IS_CACHE_VITALDEFINE, false);
                            } else {
                                LogUtil.e("宣教定义储存失败 !");
                            }


                        }
                    }
                });
    }

    private static List<XuanJiaoBean.DataBean> findImgPathList(List<XuanJiaoBean.DataBean> data) {
        for (XuanJiaoBean.DataBean dataBean : data) {
            dataBean = findImagPath(dataBean);
        }
        return data;
    }

    private static XuanJiaoBean.DataBean findImagPath(XuanJiaoBean.DataBean dataBean) {
        if (dataBean.getChild() != null && dataBean.getChild().size() > 0) {
            for (XuanJiaoBean.DataBean dataBean1 : dataBean.getChild()) {
                findImagPath(dataBean1);
            }
        } else {
            if (dataBean != null && dataBean.getDetailpageimgpath() != null) {
                dataBean.setDetailpageimgpath(NetworkForImage.getImageLocalPath(dataBean.getDetailpageimgpath(), NetworkForImage.fileCache));
            }
        }
        return dataBean;
    }

    public static void callXuanJiaoRecord(final String patientId) {
        OkHttpUtils.get().url(URL.URL_JILU_XUANJIAO).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_PATIENT_ID, patientId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null) {
                            XuanJiaoJiLu xuanJiaoBean = JSON.parseObject(response, XuanJiaoJiLu.class);
                            if (xuanJiaoBean != null && xuanJiaoBean.getData() != null) {
                                DBXuanJiaoRecord dbXuanJiaoRecord;
                                DBXuanJiaoRecord first;
                                for (TiJiaoXuanJiao tiJiaoXuanJiao : xuanJiaoBean.getData()) {
                                    dbXuanJiaoRecord = DBXuanJiaoRecord.convertXuanJiaoRecord(tiJiaoXuanJiao);
                                    first = DataSupport.where("patientid = ? and edudefineid = ?", patientId, tiJiaoXuanJiao.getEdudefineid() + "")
                                            .findFirst(DBXuanJiaoRecord.class);
                                    if (first == null) {
                                        dbXuanJiaoRecord.save();
                                    } else {
                                        dbXuanJiaoRecord.updateAll("patientid = ? and edudefineid = ?", patientId, tiJiaoXuanJiao.getEdudefineid() + "");
                                    }
                                }
                            }
                        }
                    }
                });
    }

    public static void submitXuanJiaoJilu(String tiJiaoJson) {
        OkHttpUtils.get().url(URL.URL_XUANJIAO).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_SUBMIT_JSON, tiJiaoJson).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }

    public static void submitXuanJiaoJilu(final List<TiJiaoXuanJiao> tiJiaoXuanJiaos) {
        if (tiJiaoXuanJiaos == null || tiJiaoXuanJiaos.size() <= 0) {
            LogUtil.i("Can not submit an empty tiJiaoXuanJiaos list!");
            return;
        }
        NetworkForSynchronize.syncStart(TiJiaoXuanJiao.class.getName());
        String tiJiaoJson = JSON.toJSONString(tiJiaoXuanJiaos);
        OkHttpUtils.post().url(URL.URL_SUBMIT_XUANJIAO).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_SUBMIT_JSON, tiJiaoJson).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e(e.getMessage() + "");
                        NetworkForSynchronize.syncFinished(TiJiaoXuanJiao.class.getName());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DBXuanJiaoRecord dbXuanJiaoRecord;
                        DBXuanJiaoRecord first;
                        if (tiJiaoXuanJiaos != null && tiJiaoXuanJiaos.size() > 0) {
                            for (TiJiaoXuanJiao tiJiaoXuanJiao : tiJiaoXuanJiaos) {
                                dbXuanJiaoRecord = DBXuanJiaoRecord.convertXuanJiaoRecord(tiJiaoXuanJiao);
                                /*dbXuanJiaoRecord.setIsModified("false");
                                first = DataSupport.where("patientid = ? and edudefineid = ?", tiJiaoXuanJiao.getPatientid(), tiJiaoXuanJiao.getEdudefineid() + "")
                                        .findFirst(DBXuanJiaoRecord.class);
                                LogUtil.d("宣教測試2: patientid = " + dbXuanJiaoRecord.getPatientid() + "edudefineid = " + dbXuanJiaoRecord.getEduuserid());
                                if (first == null) {
                                    dbXuanJiaoRecord.save();
                                } else {
                                    dbXuanJiaoRecord.updateAll("patientid = ? and edudefineid = ?", tiJiaoXuanJiao.getPatientid(), tiJiaoXuanJiao.getEdudefineid() + "");
                                }
                                dbXuanJiaoRecord.save();*/
                                // 先删除后保存
                                DataSupport.deleteAll(DBXuanJiaoRecord.class, "patientid = ? and edudefineid = ?", tiJiaoXuanJiao.getPatientid(), tiJiaoXuanJiao.getEdudefineid() + "");
                                dbXuanJiaoRecord.save();
                            }
                        }
                        NetworkForSynchronize.syncFinished(TiJiaoXuanJiao.class.getName());
                    }
                });
    }
}
