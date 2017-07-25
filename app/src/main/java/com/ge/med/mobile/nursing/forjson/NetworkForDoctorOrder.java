package com.ge.med.mobile.nursing.forjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.forjson.callback.BaseNetCallback;
import com.ge.med.mobile.nursing.forjson.callback.INetworkHandler;
import com.ge.med.mobile.nursing.forjson.callback.SubmitNetCallback;
import com.ge.med.mobile.nursing.forjson.entity.BaseJSONBean;
import com.ge.med.mobile.nursing.ui.activity.MainActivity;
import com.ge.med.mobile.nursing.utils.MessageEvent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import org.xutils.common.util.LogUtil;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Alex Qu on 2016/12/20
 */
public class NetworkForDoctorOrder {
    /**
     * Call server API to load all patient's doctor order for a ward
     *
     * @param activity
     */
    public static void callFindAllDoctorOrder(final INetworkHandler activity, final String wardId, final String patientId
            , final Integer userId, final String orderType, final String status) {
        String urlStr = URL.URL_QUAN_BU_YZ;
        if (activity == null) {
            LogUtil.e("Can not call network api[" + urlStr + "] since activity is null!");
            return;
        }
        if (wardId == null) {
            LogUtil.e("Can not call network api[" + urlStr + "] since wardId is null!");
            activity.handleOnError();
            return;
        }

        LogUtil.d("callFindAllDoctorOrderByPatient");
        callOrderList(urlStr, activity, patientId, null, null, wardId, null, orderType, status, null);
    }

    public static void callFindAllDoctorOrderByPatient(final INetworkHandler activity, final String wardId, String patientId, String progress) {
        if (activity == null) {
            LogUtil.e("Can not call network api[" + URL.URL_QUAN_BU_YZ + "] since activity is null!");
            return;
        }
        if (patientId == null) {
            LogUtil.e("Can not call network api[" + URL.URL_QUAN_BU_YZ + "] since patientId is null!");
            activity.handleOnError();
            return;
        }

        callOrderList(URL.URL_QUAN_BU_YZ, activity, patientId, null, null, wardId, null, null, null, progress);
    }

    /**
     * Call server API to load one day's all patient's doctor order  for a ward
     *
     * @param activity
     */
    public static void callFindDoctorOrderByADay(final INetworkHandler activity, final String wardId) {
        callFindDoctorOrderByADay(activity, wardId, null, null, null, null, null);
        //callFindAllDoctorOrderByPatient(activity, wardId);

    }

    public static void callFindDoctorOrderByADay(final INetworkHandler activity, final String wardId, String patientId, String progress) {
        callFindDoctorOrderByADay(activity, wardId, patientId, null, null, null, progress);
//        callFindAllDoctorOrderByPatient(activity, wardId, patientId, progress);
    }

    public static void callFindDoctorOrderByADay(final INetworkHandler activity
            , final String wardId, final String patientId, Integer userId, String orderType, String status, String progress) {
        if (activity == null) {
            LogUtil.e("Can not call network api[" + URL.URL_DANG_TIAN_YZ + "] since activity is null!");
            return;
        }
        if (patientId == null && wardId == null) {
            LogUtil.e("Can not call network api[" + URL.URL_DANG_TIAN_YZ + "] since wardId and patientId is/are null!");
            activity.handleOnError();
            return;
        }
        callOrderList(URL.URL_DANG_TIAN_YZ, activity, patientId, null, null, wardId, userId, orderType, status, progress);
    }

    /**
     * Call server API to load 12 hourse patient's doctor order from given time
     *
     * @param activity
     */
    public static void callFindDoctorOrderMore(final INetworkHandler activity, Long date, Integer flag) {
        callFindDoctorOrderMore(activity, null, date, flag, null, null, null, null, null);
    }

    public static void callFindDoctorOrderMore(INetworkHandler activity, String patientId, Long date, Integer flag, String orderType, String progress) {
        callFindDoctorOrderMore(activity, patientId, date, flag, null, null, orderType, null, progress);
    }

    public static void callFindDoctorOrderMore(final INetworkHandler activity, String patientId, Long date, final Integer flag, final String wardId
            , final Integer userId, String orderType, String status, final String progress) {
        if (activity == null) {
            LogUtil.e("Can not call network api[" + URL.URL_YZ_MORE + "] since activity is null!");
            return;
        }
        if (date == null || flag == null) {
            LogUtil.i("Can not call network api[" + URL.URL_YZ_MORE + "] when date or flag was not given!");
            return;
        }
        callOrderList(URL.URL_YZ_MORE, activity, patientId, date, flag, wardId, userId, orderType, status, progress);
    }

    private static void callOrderList(String urlStr, final INetworkHandler container, final String patientId, Long date, final Integer flag, final String wardId
            , final Integer userId, final String orderType, final String status, final String progress) {
        if (container == null) {
            return;
        }
        LogUtil.d("Calling network api:" + urlStr);
        PostFormBuilder pfb = OkHttpUtils.post().url(urlStr).addHeader("User-Agent", "www.gs.com");
        if (date != null) {
            LogUtil.d("parameter date is " + date);
            pfb = pfb.addParams(Constant.GLOBAL_KEY_DATE, date + "");
        }
        if (flag != null) {
            LogUtil.d("parameter flag is " + flag);
            pfb = pfb.addParams(Constant.GLOBAL_KEY_FLAG, flag + "");
        }
        if (patientId != null) {
            LogUtil.d("parameter patientId is " + patientId);
            pfb = pfb.addParams(Constant.GLOBAL_KEY_PATIENT_ID, patientId);
        }
        if (wardId != null) {
            LogUtil.d("parameter wardId is " + wardId);
            pfb = pfb.addParams(Constant.GLOBAL_KEY_WARD_ID, wardId.toString());
        }
        if (userId != null) {
            LogUtil.d("parameter userId is " + userId);
            pfb = pfb.addParams(Constant.GLOBAL_KEY_USER_ID1, userId.toString());
        }

        if (orderType != null) {
            LogUtil.d("parameter orderType is " + orderType);
            pfb = pfb.addParams(Constant.GLOBAL_KEY_ORDER_TYPE, orderType);
        }
        if (status != null) {
            LogUtil.d("parameter status is " + status);
            pfb = pfb.addParams(Constant.GLOBAL_KEY_ORDER_STATUS, status);
        }
        if (progress != null) {
            LogUtil.d("parameter progress is " + progress);
            pfb = pfb.addParams(Constant.GLOBAL_KEY_PROGRESS, progress);
        }
        pfb.build().execute(new BaseNetCallback() {
            @Override
            protected void preHandleError() {
                List<YiZhuBean.DataBean> orders = new DoctorOrderDaoImpl().findDoctorOrdersInJsonBean(patientId, orderType, status);
                if (Constant.YZ_TYPE_CHECKED.equals(progress)) {
                    orders = new DoctorOrderDaoImpl().findDoctorOrdersExcludeStatus(patientId, orderType, Constant.YZ_TYPE_WEIHEDUI);
                } else {
                    orders = new DoctorOrderDaoImpl().findDoctorOrdersInJsonBean(patientId, orderType, status);
                }
                if (orders == null) {
                    LogUtil.i("There is no doctor order in local DB!");
                    container.handleOnError();
                } else {
                    LogUtil.d("Successfully load [" + orders.size() + "] doctor orders in local DB!");
                    container.handleSuccess(orders);
                    EventBus.getDefault().post(new MessageEvent("数据存储成功"));
                }
            }

            @Override
            protected void preHandleSuccess(final Object obj) {
                LogUtil.d("preHandleSuccess>obj is " + obj);
                if (obj != null && obj instanceof List && ((List) obj).size() > 0) {
                    List lst = ((List) obj);
                    if (lst.get(0) instanceof YiZhuBean.DataBean) {
                        final List<YiZhuBean.DataBean> yzLst = (List<YiZhuBean.DataBean>) lst;
                        LogUtil.d("preHandleSuccess>obj is yzLst " + yzLst.size());
                        container.handleSuccess(obj);
                        new Thread() {
                            @Override
                            public void run() {
                                new DoctorOrderDaoImpl().saveDoctorOrders(yzLst);
                                EventBus.getDefault().post(new MessageEvent("数据存储成功"));
                            }
                        }.start();
                    }
                }
            }

            @Override
            protected BaseJSONBean parseResonseString(String response) {
                YiZhuBean yiZhuBean = null;
                try {
                    yiZhuBean = JSON.parseObject(response, YiZhuBean.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return yiZhuBean;
            }
        });
    }

    /**
     * Call server API to load the assessment record by assessment identify
     *
     * @param activity
     */
    public static void callGetDoctorOrderByHisId(final INetworkHandler activity, final String hisId) {
        final String urlStr = URL.URL_DAN_GE_YZ;
        if (activity == null || hisId == null) {
            LogUtil.e("Can not call network api:" + urlStr + " since caller or hisId is null!");
            return;
        }
        LogUtil.d("Calling network api:" + urlStr + ", HisId is " + hisId + ", caller is " + activity.getClass().getName());
        OkHttpUtils.post().url(urlStr).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_ID, hisId.toString()).build()
                .execute(new BaseNetCallback() {
                    @Override
                    protected void preHandleError() {
                        YiZhuBean.DataBean order = new DoctorOrderDaoImpl().getDoctorOrderByHisId(hisId);
                        if (null != order) activity.handleSuccess(order);
                        else {
                            LogUtil.d("There is no doctor order record found by hisId:" + hisId);
                            activity.handleOnError(urlStr);
                        }
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        if (null == obj) {
                            LogUtil.i("Can not handle success since returned obj is null!");
                            return;
                        }
                        if (obj instanceof YiZhuBean.DataBean) {
                            YiZhuBean.DataBean order = (YiZhuBean.DataBean) obj;
                            LogUtil.d("Succesfully get doctor order from network api, order is [id:"
                                    + order.getId() + ", name:" + order.getOrdername() + ",patientid:" + order.getPatientid() + "]");
                            new DoctorOrderDaoImpl().saveDoctorOrder(order);
                            activity.handleSuccess(obj);
                        } else LogUtil.e("Can not handle success since returned obj["
                                + obj.getClass().getName() + "] is not YiZhuBean.DataBean!");
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, YiZhuBean.class);
                    }
                });
    }

    /**
     * Call server API to submit the assessment to server
     *
     * @param activity
     */
    public static void submitSingleDoctorOrder(final INetworkHandler activity, YiZhuBean.DataBean docOrder) {
        String jsonStr = JSON.toJSONString(docOrder, SerializerFeature.WriteNullListAsEmpty);
        LogUtil.d("Calling network api:" + URL.URL_SC_YZ + ", param[" + Constant.GLOBAL_KEY_SUBMIT_JSON + "] is " + jsonStr);
        OkHttpUtils.post().url(URL.URL_SC_YZ).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_SUBMIT_JSON, jsonStr)
                .addParams(Constant.GLOBAL_KEY_NEED_RETURN, Constant.GLOBAL_VALUE_NEED_RETURN)
                .addParams("userId", MainActivity.userCache.get("userid"))
                .build()
                .execute(new SubmitNetCallback() {
                    @Override
                    protected void preHandleError() {
                        activity.handleOnError();
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        if (obj != null && obj instanceof List) {
                            new DoctorOrderDaoImpl().saveDoctorOrders((List) obj);
                            LogUtil.d("Update [" + ((List) obj).size() + "] local doctor order success!");
                            activity.handleSuccess(obj);
                        } else
                            LogUtil.e("submit doctor return wrong data! obj is " + (obj == null ? "null" : obj.getClass().getName()));
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, YiZhuBean.class);
                    }
                });
    }

    /**
     * Call server API to submit all local assessment to server
     */
    public static void sumbmitAllDoctorOrder(final INetworkHandler activity, List<YiZhuBean.DataBean> lst) {
        if (lst == null || lst.size() <= 0) {
            LogUtil.i("Can not submit an empty doctor order list!");
            activity.handleOnError();
            return;
        }
        String jsonStr = JSON.toJSONString(lst, SerializerFeature.WriteNullListAsEmpty);
        LogUtil.d("Calling network api:" + URL.URL_SC_HD_YZ + ", param[" + Constant.GLOBAL_KEY_SUBMIT_JSON + "] is " + jsonStr);
        OkHttpUtils.post().url(URL.URL_SC_HD_YZ).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_SUBMIT_JSON, jsonStr)
                .addParams(Constant.GLOBAL_KEY_NEED_RETURN, Constant.GLOBAL_VALUE_NEED_RETURN)
                .addParams("userId", MainActivity.userCache.get("userid"))
                .build()
                .execute(new SubmitNetCallback() {
                    @Override
                    protected void preHandleError() {
                        LogUtil.e("Can not submit many doctor order!");
                        activity.handleOnError();
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        LogUtil.d("Successfully submited many doctor order!");
                        if (obj != null && obj instanceof List) {
                            new DoctorOrderDaoImpl().saveDoctorOrders((List) obj);
                            LogUtil.d("Update [" + ((List) obj).size() + "] local doctor order success!");
                            activity.handleSuccess(obj);
                        } else {
                            LogUtil.e("submit doctor return wrong data! obj is " + (obj == null ? "null" : obj.getClass().getName()));
                            activity.handleOnError();
                        }
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, YiZhuBean.class);
                    }
                });
    }

    public static void sumbmitAllDoctorOrder(List<YiZhuBean.DataBean> lst) {
        if (lst == null || lst.size() <= 0) {
            LogUtil.i("Can not submit an empty doctor order list!");
            return;
        }
        String jsonStr = JSON.toJSONString(lst, SerializerFeature.WriteNullListAsEmpty);
        LogUtil.d("Calling network api:" + URL.URL_SC_HD_YZ + ", param[" + Constant.GLOBAL_KEY_SUBMIT_JSON + "] is " + jsonStr);
        NetworkForSynchronize.syncStart(YiZhuBean.DataBean.class.getName());
        OkHttpUtils.post().url(URL.URL_SC_HD_YZ).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_SUBMIT_JSON, jsonStr)
                .addParams(Constant.GLOBAL_KEY_NEED_RETURN, Constant.GLOBAL_VALUE_NEED_RETURN)
                .build()
                .execute(new SubmitNetCallback() {
                    @Override
                    protected void preHandleError() {
                        LogUtil.e("Can not submit all doctor order!");
                        NetworkForSynchronize.syncFinished(YiZhuBean.DataBean.class.getName());
                    }

                    @Override
                    protected void preHandleSuccess(final Object obj) {
                        LogUtil.d("Successfully submited all doctor order!");
                        if (obj != null && obj instanceof List) { // 更新未同步数据
                            new DoctorOrderDaoImpl().saveDoctorOrders((List) obj);
                            LogUtil.d("Update [" + ((List) obj).size() + "] local doctor order success!");
                            NetworkForSynchronize.syncFinished(YiZhuBean.DataBean.class.getName());
                        } else {
                            LogUtil.e("submit doctor return wrong data! obj is " + (obj == null ? "null" : obj.getClass().getName()));
                        }
                        // 医嘱数据同步完毕设置
                        NetworkForSynchronize.syncFinished(YiZhuBean.DataBean.class.getName());
                    }

                    @Override
                    protected BaseJSONBean parseResonseString(String response) {
                        return JSON.parseObject(response, YiZhuBean.class);
                    }
                });
    }
}
