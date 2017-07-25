package com.ge.med.mobile.nursing.forjson;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.AssessMeasureRecords;
import com.ge.med.mobile.nursing.dao.entity.AssessMeasureRecordsBean;
import com.ge.med.mobile.nursing.dao.entity.TiJiaoXuanJiao;
import com.ge.med.mobile.nursing.dao.entity.XuanJiaoJiLu;
import com.ge.med.mobile.nursing.db.DBAssessMeasureRecords;
import com.ge.med.mobile.nursing.db.DBXuanJiaoRecord;
import com.ge.med.mobile.nursing.forjson.entity.AssessRecordBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.List;

import okhttp3.Call;

/**
 * Created by xxl on 2017/4/16.
 */

public class NetworkForAssessMeasureRecords {
    public static void callAssessMeasureRecord(final String patientId, String assessRecordId) {
        OkHttpUtils.get().url(URL.URL_HOUJI_CUOSHI_RECORD).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_PATIENT_ID, patientId)
                .addParams("assessRecordId", assessRecordId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null) {
                            saveRecords(response);
                        }
                    }
                });
    }

    public static void callAssessMeasureRecord(final String patientId) {
        OkHttpUtils.get().url(URL.URL_HOUJI_CUOSHI_RECORD).addHeader("User-Agent", "www.gs.com")
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
                            saveRecords(response);
                        }
                    }
                });
    }

    private static void saveRecords(String response) {
        AssessMeasureRecordsBean assessMeasureRecordsBean = JSON.parseObject(response, AssessMeasureRecordsBean.class);
        if (assessMeasureRecordsBean != null && assessMeasureRecordsBean.getData() != null && assessMeasureRecordsBean.getData().size() > 0) {
            DBAssessMeasureRecords dbAssessMeasureRecords;
            DBAssessMeasureRecords first = null;
            for (AssessMeasureRecords assessMeasureRecords : assessMeasureRecordsBean.getData()) {
                dbAssessMeasureRecords = DBAssessMeasureRecords.convertBean(assessMeasureRecords);
                /*if (dbAssessMeasureRecords != null) {
                    first = DataSupport.where("assessRecordId = ?",
                            dbAssessMeasureRecords.getAssessRecordId() + "").findFirst(DBAssessMeasureRecords.class);
                }
                dbAssessMeasureRecords.setIsModified(false + "");
                if (first != null) {
                    dbAssessMeasureRecords.save();
                } else {
                    dbAssessMeasureRecords.updateAll("assessRecordId = ?",
                            dbAssessMeasureRecords.getAssessRecordId() + "");
                }*/
                // 修改数据的储存方式
                DataSupport.deleteAll(DBAssessMeasureRecords.class, "assessRecordId = ?", dbAssessMeasureRecords.getAssessRecordId() + "");
                dbAssessMeasureRecords.save();
            } // the end of for
        }
    }

    public static void submitAssessMeasureRecord(List<AssessMeasureRecords> assessMeasureRecords, String needReturn) {
        if (assessMeasureRecords == null || assessMeasureRecords.size() <= 0) {
            LogUtil.i("Can not submit an empty assessMeasureRecords list!");
            return;
        }
        if (needReturn == null) needReturn = false + "";
        NetworkForSynchronize.syncStart(AssessMeasureRecords.class.getName());
        String s = JSON.toJSONString(assessMeasureRecords);
        OkHttpUtils.post().url(URL.URL_SUBMIT_HOUJI_CUOSHI_RECORD).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_SUBMIT_JSON, s)
                .addParams("needReturn", needReturn)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e(e.getMessage());
                        NetworkForSynchronize.syncFinished(AssessMeasureRecords.class.getName());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.d(response);
                        saveRecords(response);
                        NetworkForSynchronize.syncFinished(AssessMeasureRecords.class.getName());
                    }
                });
    }

}
