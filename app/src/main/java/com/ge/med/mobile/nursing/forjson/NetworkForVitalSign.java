package com.ge.med.mobile.nursing.forjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.impl.VitalSignDaoImpl;
import com.ge.med.mobile.nursing.db.DBVitalSignWardDefine;
import com.ge.med.mobile.nursing.forjson.callback.BaseNetCallback;
import com.ge.med.mobile.nursing.forjson.callback.SubmitNetCallback;
import com.ge.med.mobile.nursing.forjson.entity.BaseJSONBean;
import com.ge.med.mobile.nursing.forjson.entity.ResponseSubmitVitalSignSignle;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignDefineJSONBean;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignJSONBean;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignJSONBeans;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignSheet;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignSheetMini;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignWardDefine;
import com.ge.med.mobile.nursing.forjson.syncDifine.CacheDefine;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Qu on 2016/12/4
 */
public class NetworkForVitalSign {
    /**
     * Call server API to load all assessment definition by ward
     *
     * @param activity
     */
    public static void callVitalSignDefinitionAll(final MyBaseActivity activity, final String wardId) {
        LogUtil.d("Calling network api:" + URL.URL_VITAL_WARD_DEFINE + ", wardId is " + wardId);
        OkHttpUtils.post().url(URL.URL_VITAL_WARD_DEFINE).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_WARD_ID, wardId)
                .addParams("bodyFlag", "true")
                .build()
                .execute(new BaseNetCallback() {
                    @Override
                    protected void preHandleError() {
                        CacheDefine.showDialog();
                        activity.showToastShort("无法同步病区的体征设置！您的体征功能将无法使用！");
                        activity.handleOnError(URL.URL_VITAL_WARD_DEFINE);
                    }

                    @Override
                    protected void preHandleSuccess(Object obj) {
                        if (obj != null && obj instanceof List) {
                            DataSupport.deleteAll(DBVitalSignWardDefine.class); //清空数据库
                            new VitalSignDaoImpl().saveVitalSignWardDefines((List<VitalSignWardDefine>) obj);
                            activity.handleSuccess(obj);
                            CacheDefine.changeStatus(Constant.GLOBAL_KEY_IS_CACHE_VITALDEFINE, false);
                        }
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, VitalSignDefineJSONBean.class);
                    }
                });
    }


    /**
     * Call server API to load all assessment record of the ward
     *
     * @param activity
     */

    public static void callVitalSignHistoryByWard(final MyBaseActivity activity, final String wardId) {
        LogUtil.d("Calling network api:" + URL.URL_VITAL_LIST + ", wardId is " + wardId);
        OkHttpUtils.post().url(URL.URL_VITAL_LIST).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_WARD_ID, wardId)
                .addParams(Constant.GLOBAL_KEY_DAYS, "0")
                .build()
                .execute(new BaseNetCallback() {
                    @Override
                    protected void preHandleError() {
                        activity.showToastShort("今天没有体征数据！");
                        activity.handleOnError(URL.URL_VITAL_LIST);
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                new VitalSignDaoImpl().saveVitalSigns((List<VitalSignSheet>) obj);
                            }
                        };
                        thread.start();
                        activity.handleSuccess(obj);
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, VitalSignJSONBeans.class);
                    }
                });
    }

    /**
     * Call server API to load all assessment record of the patient
     *
     * @param activity
     */
    public static void callVitalSheetHistoryByPatient(final MyBaseActivity activity, final String wardId, final String patientId) {
        LogUtil.d("Calling network api:" + URL.URL_VITAL_LIST + ", wardId is " + wardId + ", patientId is " + patientId);
        OkHttpUtils.post().url(URL.URL_VITAL_LIST).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_WARD_ID, wardId)
                .addParams(Constant.GLOBAL_KEY_DAYS, "0")
                .addParams(Constant.GLOBAL_KEY_PATIENT_ID, patientId).build()
                .execute(new BaseNetCallback() {
                    @Override
                    protected void preHandleError() {
                        List<VitalSignSheet> sheetList = new VitalSignDaoImpl().findVitalSignsFromDB(patientId);
                        if (null != sheetList) activity.handleSuccess(sheetList);
                        else {
                            LogUtil.d("There is no Vital Sheet record for patient id:" + patientId);
                            activity.handleOnError(URL.URL_VITAL_LIST);
                        }
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                new VitalSignDaoImpl().saveVitalSigns((List<VitalSignSheet>) obj);
                            }
                        };
                        thread.start();
                        activity.handleSuccess(obj);
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, VitalSignJSONBeans.class);
                    }
                });
    }

    /**
     * Call server API to load all assessment record of the patient
     *
     * @param activity
     */
    public static void callLastVitalSheetByPatient(final MyBaseActivity activity, final String patientId) {
        LogUtil.d("Calling network api:" + URL.URL_VITAL_PATIENT_LAST + ", patientId is " + patientId);
        OkHttpUtils.post().url(URL.URL_VITAL_PATIENT_LAST).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_PATIENT_ID, patientId).build()
                .execute(new BaseNetCallback() {
                    @Override
                    protected void preHandleError() {
                        VitalSignSheet sheet = new VitalSignDaoImpl().findLastVitalSignsFromDB(patientId);
                        if (null != sheet) activity.handleSuccess(sheet);
                        else {
                            LogUtil.d("There is no last Vital Sheet record for patient id:" + patientId);
                            activity.handleOnError(URL.URL_VITAL_PATIENT_LAST);
                        }
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        new VitalSignDaoImpl().saveVitalSign((VitalSignSheet) obj);
                        activity.handleSuccess(obj);
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, VitalSignJSONBean.class);
                    }
                });
    }

    /**
     * Call server API to load the assessment record by assessment identify
     *
     * @param activity
     */
    public static void callGetVitalSheetBySheetId(final MyBaseActivity activity, final Integer sheetId) {
        LogUtil.d("Calling network api:" + URL.URL_VITAL_SINGLE + ", sheetId is " + sheetId);
        OkHttpUtils.post().url(URL.URL_VITAL_SINGLE).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_SHEET_ID, sheetId.toString()).build()
                .execute(new BaseNetCallback() {
                    @Override
                    protected void preHandleError() {
                        VitalSignSheet sheet = new VitalSignDaoImpl().getVitalSignByIdFromDB(sheetId);
                        if (null != sheet) activity.handleSuccess(sheet);
                        else {
                            LogUtil.d("There is no Vital Sheet record for sheetId:" + sheetId);
                            activity.handleOnError(URL.URL_VITAL_SINGLE);
                        }
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        new VitalSignDaoImpl().saveVitalSign((VitalSignSheet) obj);
                        activity.handleSuccess(obj);
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, VitalSignJSONBean.class);
                    }
                });
    }

    /**
     * Call server API to submit the assessment to server
     *
     * @param activity
     */
    public static void submitSingleVitalSignSheet(final MyBaseActivity activity, final VitalSignSheet vitalSignSheet) {
        String jsonStr = JSON.toJSONString(new VitalSignSheetMini(vitalSignSheet), SerializerFeature.WriteNullListAsEmpty);
        final String urlSr = URL.URL_VITAL_SAVE;
        LogUtil.d("submit single vital sign to [" + urlSr + "],json is >" + jsonStr);
        OkHttpUtils.post().url(urlSr).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_SUBMIT_JSON_VITAL, jsonStr)
                .addParams(Constant.GLOBAL_KEY_EMPNO, vitalSignSheet.getUserid() + "")
                .build()
                .execute(new SubmitNetCallback() {
                    @Override
                    protected void preHandleError() {
                        activity.handleOnError(urlSr);
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        activity.handleSuccess(obj);
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, ResponseSubmitVitalSignSignle.class);
                    }
                });
    }

    /**
     * Call server API to submit all local assessment to server
     */
    public static void submitAllVitalSignSheet(List<VitalSignSheet> lst) {
        if (lst == null || lst.size() <= 0) {
            LogUtil.i("Can not submit an empty vital sign sheet list!");
            return;
        }
        List<VitalSignSheetMini> sheets = new ArrayList<>();
        for (VitalSignSheet st : lst) {
            sheets.add(new VitalSignSheetMini(st));
        }
        String jsonStr = JSON.toJSONString(sheets, SerializerFeature.WriteNullListAsEmpty);
        LogUtil.d("submit multiple vital sign to[" + URL.URL_VITAL_SAVE_LIST + "], json is >" + jsonStr);

        NetworkForSynchronize.syncStart(VitalSignSheet.class.getName());
        OkHttpUtils.post().url(URL.URL_VITAL_SAVE_LIST).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_SUBMIT_JSON_VITAL, jsonStr)
                .addParams(Constant.GLOBAL_KEY_EMPNO, lst.get(0).getUserid() + "")
                .build()
                .execute(new SubmitNetCallback() {
                    @Override
                    protected void preHandleError() {
                        LogUtil.e("Can not submit all vital sign sheet!");
                        NetworkForSynchronize.syncFinished(VitalSignSheet.class.getName());
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        LogUtil.d("Successfully submited all vital sign sheet!");
                        if (obj != null && obj instanceof List) { // 更新未同步数据
                            new VitalSignDaoImpl().updateAllToSynchronized();
                        } else {
                            LogUtil.e("submit vital sign return wrong data! obj is " + (obj == null ? "null" : obj.getClass().getName()));
                        }
                        // 体征数据同步完毕设置
                        NetworkForSynchronize.syncFinished(VitalSignSheet.class.getName());
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, ResponseSubmitVitalSignSignle.class);
                    }
                });
    }
}