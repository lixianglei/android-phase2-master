package com.ge.med.mobile.nursing.forjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.AssessDao;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBWardRiskDefine;
import com.ge.med.mobile.nursing.forjson.callback.BaseNetCallback;
import com.ge.med.mobile.nursing.forjson.callback.INetworkHandler;
import com.ge.med.mobile.nursing.forjson.callback.SubmitNetCallback;
import com.ge.med.mobile.nursing.forjson.entity.AssessJSONBean;
import com.ge.med.mobile.nursing.forjson.entity.AssessJSONBeans;
import com.ge.med.mobile.nursing.forjson.entity.AssessRecordBean;
import com.ge.med.mobile.nursing.forjson.entity.BaseJSONBean;
import com.ge.med.mobile.nursing.forjson.entity.WardRiskDefine;
import com.ge.med.mobile.nursing.forjson.entity.WardRiskDefineJSONBeans;
import com.ge.med.mobile.nursing.forjson.syncDifine.CacheDefine;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.List;

/**
 * Created by Alex Qu on 2016/12/4
 */
public class NetworkForAssessment {

    /**
     * Call server API to load all risk definition by ward
     *
     * @param activity
     */
    public static void callSinglePatient(final INetworkHandler activity, final String patientId) {
        LogUtil.d("Calling network api:" + URL.URL_GET_SINGLE_PATIENT + ", patientId is " + patientId);
        OkHttpUtils.post().url(URL.URL_GET_SINGLE_PATIENT).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_PATIENT_ID, patientId).build()
                .execute(new BaseNetCallback() {
                    @Override
                    protected void preHandleError() {
                        LogUtil.e("Can not get patient[" + patientId + "] from server!");
                        activity.handleOnError();
                    }

                    @Override
                    protected void preHandleSuccess(Object obj) {
                        LogUtil.d("Got ward risk define from server successfully!");
                        new HuanZheLieBiaoImpl().savePatients((List<HuanZheLieBiaoBean.DataBean>) obj);
                        activity.handleSuccess(((List<HuanZheLieBiaoBean.DataBean>) obj).get(0));
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, HuanZheLieBiaoBean.class);
                    }
                });
    }

    /**
     * Call server API to load all risk definition by ward
     *
     * @param activity
     */
    public static void callWardRiskDefinitionAll(final MyBaseActivity activity, final String wardId) {
        LogUtil.d("Calling network api:" + URL.URL_WARD_RISK_DEFINE + ", wardId is " + wardId);
        OkHttpUtils.post().url(URL.URL_WARD_RISK_DEFINE).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_WARD_ID, wardId).build()
                .execute(new BaseNetCallback() {
                    @Override
                    protected void preHandleError() {
                        CacheDefine.showDialog();
                        LogUtil.e("Can not got ward risk define from server!");
                        activity.showToastShort("无法同步病区的护理风险标签设置！");
                        activity.handleOnError();
                    }

                    @Override
                    protected void preHandleSuccess(Object obj) {
                        DataSupport.deleteAll(DBWardRiskDefine.class); //清空数据库
                        LogUtil.d("Got ward risk define from server successfully!");
                        new AssessDaoImpl().saveWardRiskDefines((List<WardRiskDefine>) obj);
                        activity.handleSuccess(obj);
                        CacheDefine.changeStatus(Constant.GLOBAL_KEY_IS_CACHE_RISKDEFINE, false);//改变cache状态
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        WardRiskDefineJSONBeans wardRiskDefineJSONBeans = null;
                        try {
                            wardRiskDefineJSONBeans = JSON.parseObject(response, WardRiskDefineJSONBeans.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return wardRiskDefineJSONBeans;
                    }
                });
    }

    /**
     * Call server API to load all assessment definition by ward
     *
     * @param activity
     */
    public static void callAssessDefinitionAll(final MyBaseActivity activity, final String wardId) {
        LogUtil.d("Calling network api:" + URL.URL_ASSESS_DEFINE + ", wardId is " + wardId);
        OkHttpUtils.get().url(URL.URL_ASSESS_DEFINE).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_WARD_ID, wardId).build()
                .execute(new BaseNetCallback() {
                    @Override
                    protected void preHandleError() {
                        CacheDefine.showDialog();
                        activity.showToastShort("无法同步病区的评估设置！您的评估功能将无法使用！");
                        activity.handleOnError();
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
//                        new Thread(){
//                            @Override
//                            public void run() {
//                                new AssessDaoImpl().saveAssessDefines((List<AssessDefineJSONBean.DataBean>) obj);
//                            }
//                        }.start();
                        activity.handleSuccess(obj);
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        AssessDefine bean = JSON.parseObject(response, AssessDefine.class);
                        return bean;
                    }
                });
    }


    /**
     * Call server API to load all assessment record of the ward
     *
     * @param activity
     */

    public static void callAssessHistoryByWard(final MyBaseActivity activity, final String wardId) {
        LogUtil.d("Calling network api:" + URL.URL_ASSESS_LIST + ", wardId is " + wardId);
        OkHttpUtils.get().url(URL.URL_ASSESS_LIST).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_WARD_ID, wardId).build()
                .execute(new BaseNetCallback() {
                    @Override
                    protected void preHandleError() {
                        // do not load from DB since there is no DB data there because this api will call just after login
                        activity.showToastShort("无法同步历史评估数据！");
                        activity.handleOnError();
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                new AssessDaoImpl().saveAssesses((List<AssessRecordBean>) obj);
                            }
                        };
                        thread.start();
                        activity.handleSuccess(obj);
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, AssessJSONBeans.class);
                    }
                });
    }

    /**
     * Call server API to load all assessment record of the patient
     *
     * @param activity
     */
    public static void callAssessHistoryByPatient(final MyBaseActivity activity, final String wardId, final String patientId) {
        LogUtil.d("Calling network api:" + URL.URL_ASSESS_LIST + ", wardId is " + wardId + ", patientId is " + patientId);
        OkHttpUtils.get().url(URL.URL_ASSESS_LIST).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_WARD_ID, wardId)
                .addParams(Constant.GLOBAL_KEY_PATIENT_ID, patientId).build()
                .execute(new BaseNetCallback() {
                    @Override
                    protected void preHandleError() {
                        List<AssessRecordBean> assesses = new AssessDaoImpl().findAssessesFromDB(patientId);
                        if (assesses == null) {
                            activity.handleOnError();
                        } else {
                            activity.handleSuccess(assesses);
                        }
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                new AssessDaoImpl().saveAssesses((List<AssessRecordBean>) obj);
                            }
                        };
                        thread.start();
                        activity.handleSuccess(obj);
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, AssessJSONBeans.class);
                    }
                });
    }

    /**
     * Call server API to load the assessment record by assessment identify
     *
     * @param activity
     */
    public static void callAssessHistoryById(final MyBaseActivity activity, final Integer assessId) {
        LogUtil.d("Calling network api:" + URL.URL_ASSESS_SINGLE + ", id is " + assessId);
        OkHttpUtils.get().url(URL.URL_ASSESS_SINGLE).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_ID, assessId.toString()).build()
                .execute(new BaseNetCallback() {
                    @Override
                    protected void preHandleError() {
                        AssessRecordBean assess = new AssessDaoImpl().getAssessById(assessId);
                        if (assess == null) {
                            activity.handleOnError();
                        } else {
                            activity.handleSuccess(assess);
                        }
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        new AssessDaoImpl().saveAssess((AssessRecordBean) obj);
                        activity.handleSuccess(obj);
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, AssessJSONBean.class);
                    }
                });
    }

    /**
     * Call server API to submit the assessment to server
     *
     * @param activity
     */
    public static void submitSingleAssessment(final MyBaseActivity activity, AssessRecordBean assess) {
        String jsonStr = JSON.toJSONString(assess, SerializerFeature.DisableCircularReferenceDetect);
        LogUtil.d("Calling network api:" + URL.URL_ASSESS_SUBMIT + ", param[" + Constant.GLOBAL_KEY_SUBMIT_JSON + "] is " + jsonStr);
        LogUtil.e("评估2:" + jsonStr);
        OkHttpUtils.post().url(URL.URL_ASSESS_SUBMIT).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_SUBMIT_JSON, jsonStr)
                .addParams(Constant.GLOBAL_KEY_NEED_RETURN, Constant.GLOBAL_VALUE_NEED_RETURN)
                .build()
                .execute(new SubmitNetCallback() {
                    @Override
                    protected void preHandleError() {
                        activity.handleOnError();
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        if (obj != null && obj instanceof List && ((List) obj).size() > 0) {
                            LogUtil.d("try to save [" + ((List) obj).size() + "] assess after submit!");
                            new AssessDaoImpl().saveAssesses((List) obj);
                            activity.handleSuccess(((List) obj).get(0));
                        } else
                            LogUtil.e("submit assess return wrong data! obj is " + (obj == null ? "null" : obj.getClass().getName()));
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        LogUtil.e("评估2:" + response);
                        return JSON.parseObject(response, AssessJSONBeans.class);
                    }
                });
    }

    /**
     * Call server API to submit all local assessment to server
     */
    public static void submitAllAssessment(List<AssessRecordBean> assessList) {
        if (assessList == null || assessList.size() <= 0) {
            LogUtil.i("Can not submit an empty assess list!");
            return;
        }
        String jsonStr = JSON.toJSONString(assessList, SerializerFeature.WriteNullListAsEmpty);
        LogUtil.d("Calling network api:" + URL.URL_ASSESS_SUBMIT_LIST + ", param[" + Constant.GLOBAL_KEY_SUBMIT_JSON + "] is " + jsonStr);
        LogUtil.e("评估1:" + jsonStr);
        NetworkForSynchronize.syncStart(AssessRecordBean.class.getName());
        OkHttpUtils.post().url(URL.URL_ASSESS_SUBMIT_LIST).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_SUBMIT_JSON, jsonStr)
                .addParams(Constant.GLOBAL_KEY_NEED_RETURN, Constant.GLOBAL_VALUE_NEED_RETURN)
                .build()
                .execute(new SubmitNetCallback() {
                    @Override
                    protected void preHandleError() {
                        LogUtil.e("Can not submit all assessments!");
                        NetworkForSynchronize.syncFinished(AssessRecordBean.class.getName());
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        LogUtil.e("Successfully submited all assessments!");
                        //new AssessDaoImpl().updateAllToSynchronized();
                        if (obj != null && obj instanceof List) {
                            new Thread() {
                                @Override
                                public void run() {
                                    AssessDao dao = new AssessDaoImpl();
                                    dao.deleteAllUnSychronized();
                                    dao.saveAssesses((List) obj);
                                    LogUtil.d("Update [" + ((List) obj).size() + "] local assess success!");
                                    NetworkForSynchronize.syncFinished(AssessRecordBean.class.getName());
                                }
                            }.start();
                        } else {
                            LogUtil.e("submit assess return wrong data! obj is " + (obj == null ? "null" : obj.getClass().getName()));
                            NetworkForSynchronize.syncFinished(AssessRecordBean.class.getName());
                        }
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        LogUtil.e("评估1" + response);
                        return JSON.parseObject(response, AssessJSONBeans.class);
                    }
                });
    }
}
