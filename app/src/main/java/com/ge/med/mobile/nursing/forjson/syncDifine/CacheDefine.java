package com.ge.med.mobile.nursing.forjson.syncDifine;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.forjson.NetworkForAssessment;
import com.ge.med.mobile.nursing.forjson.NetworkForMeasure;
import com.ge.med.mobile.nursing.forjson.NetworkForNursingEventTempLateLis;
import com.ge.med.mobile.nursing.forjson.NetworkForVitalSign;
import com.ge.med.mobile.nursing.forjson.NetworkForXuanJiao;
import com.ge.med.mobile.nursing.forjson.callback.ExceptionConfigCallBack;
import com.ge.med.mobile.nursing.forjson.callback.UserListCallBack;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.view.SelfDialog;
import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.common.util.LogUtil;

/**
 * Created by XXL on 2017/2/7.
 */
public class CacheDefine {
    private static SelfDialog selfDialog;
    private static SharePLogin mSharePLogin;
    private MyBaseActivity mActivitySelf;
    private String mWardId;

    public CacheDefine(SharePLogin sharePLogin, MyBaseActivity mActivitySelf, String wardId) {
        this.mSharePLogin = sharePLogin;
        this.mActivitySelf = mActivitySelf;
        this.mWardId = wardId;
        initSelectDiaLog();
        cacheWard(sharePLogin, mActivitySelf, wardId);
    }


    /**
     * Cache definition
     *
     * @param sharePLogin SharedPreferences
     * @param
     */
    private void cacheWard(SharePLogin sharePLogin, MyBaseActivity mActivitySelf, String wardId) {
        if (sharePLogin.getCache()) {
            cache(sharePLogin, mActivitySelf);
            if (sharePLogin.getCacheAssessDefine()) {
                NetworkForAssessment.callAssessDefinitionAll(mActivitySelf, wardId);
            }
            if (sharePLogin.getVitalDefine()) {
                NetworkForVitalSign.callVitalSignDefinitionAll(mActivitySelf, wardId);
            }
            if (sharePLogin.getCacheUser()) {
                getUserAll(wardId);
            }
            if (sharePLogin.getRiskDefine()) {
                NetworkForAssessment.callWardRiskDefinitionAll(mActivitySelf, wardId);
            }
            if (sharePLogin.getNursingEventTemp()) {
                NetworkForNursingEventTempLateLis.callNursingEventTempLateLis(wardId + "");
            }
            if (sharePLogin.getNursingLieBiao()) {
                NetworkForNursingEventTempLateLis.callNursingLieBiao(wardId + "");
            }
            if (sharePLogin.getXuanJiao()) {
                NetworkForXuanJiao.callXuanJiaoDefine(wardId + "");
            }
            if (sharePLogin.getHouQiCuoShi()) {
                NetworkForMeasure.callMeasureDefine();
            }
        }
    }

    /**
     * Cache globally defined (缓存与病区无关的一些定义)
     *
     * @param sharePLogin   SharedPreferences
     * @param mActivitySelf
     */
    private void cache(SharePLogin sharePLogin, MyBaseActivity mActivitySelf) {
        if (sharePLogin.getExceptionDefine()) {
            getExceptionConfig();
        }
    }

    //Get all the abnormal definition . (获取所有异常定义)
    private void getExceptionConfig() {
        OkHttpUtils.get()
                .url(URL.URL_GET_ALL_EXCEPTION)
                .addHeader("User-Agent", "www.gs.com")
                .build()
                .execute(new ExceptionConfigCallBack());
    }

    //.For information on all ward nurses (获取病区所有护士信息)
    private void getUserAll(String wardId) {
        //获取全部users 信息网络访问
        OkHttpUtils.post()
                .url(URL.USER_ALL)
                .addHeader("User-Agent", "www.gs.com")
                .addParams("wardId", wardId)
                .build()
                .execute(new UserListCallBack());
    }

    public static void changeStatus(String type) {
        changeStatus(type, false);
    }

    /**
     * Define the cache after successful change state  定义缓存成功之后改变状态
     *
     * @param type      Cache type definition of success 缓存成功的定义类型
     * @param isTongZhi 是否是通知改变的
     */
    public static void changeStatus(String type, boolean isTongZhi) {
        if (mSharePLogin == null || type == null) {
            return;
        }
        switch (type) {
            case Constant.GLOBAL_KEY_IS_CACHE_ASSESSDEFINE:
                mSharePLogin.saveCacheAssessDefine(isTongZhi);
                LogUtil.i("mSharePLogin getCacheAssessDefine: " + mSharePLogin.getCacheAssessDefine());
                break;
            case Constant.GLOBAL_KEY_IS_CACHE_VITALDEFINE:
                mSharePLogin.saveVitalDefine(isTongZhi);
                LogUtil.i("mSharePLogin getVitalDefine: " + mSharePLogin.getVitalDefine());
                break;
            case Constant.GLOBAL_KEY_IS_CACHE_EXCEPTIONDEFINE:
                mSharePLogin.saveExceptionDefine(isTongZhi);
                LogUtil.i("mSharePLogin getExceptionDefine: " + mSharePLogin.getExceptionDefine());
                break;
            case Constant.GLOBAL_KEY_IS_CACHE_RISKDEFINE:
                mSharePLogin.saveRiskDefine(isTongZhi);
                LogUtil.i("mSharePLogin getRiskDefine: " + mSharePLogin.getRiskDefine());
                break;
            case Constant.GLOBAL_KEY_IS_CACHE_NUESINGEVENTTEMP:
                mSharePLogin.saveNursingEventTemp(isTongZhi);
                LogUtil.i("mSharePLogin getNursingEventTemp: " + mSharePLogin.getNursingEventTemp());
                break;
            case Constant.GLOBAL_KEY_IS_CACHE_USER:
                mSharePLogin.saveCacheUser(isTongZhi);
                LogUtil.i("mSharePLogin getCacheUser: " + mSharePLogin.getCacheUser());
                break;
            case Constant.GLOBAL_KEY_IS_CACHE_NUESINGEV_LIEBIAO:
                mSharePLogin.saveNursingLieBiao(isTongZhi);
                LogUtil.i("mSharePLogin getCacheUser: " + mSharePLogin.getCacheUser());
                break;
            case Constant.GLOBAL_KEY_IS_CACHE_XUANJIAO:
                mSharePLogin.saveXuanJiao(isTongZhi);
                LogUtil.i("mSharePLogin getCacheUser: " + mSharePLogin.getCacheUser());
                break;
            case Constant.GLOBAL_KEY_IS_CACHE_HOUQI_CUOSHI:
                mSharePLogin.saveHouQiCuoShi(isTongZhi);
                LogUtil.i("mSharePLogin getCacheUser: " + mSharePLogin.getCacheUser());
                break;
        }
        if (mSharePLogin.getCacheAssessDefine() || mSharePLogin.getVitalDefine()
                || mSharePLogin.getExceptionDefine() || mSharePLogin.getRiskDefine()
                || mSharePLogin.getCacheUser() || mSharePLogin.getNursingEventTemp()) {
            mSharePLogin.saveCache(true);
        } else {
            mSharePLogin.saveCache(false);
        }
    }

    public static void showDialog() {
        if (selfDialog != null) {
            try {
                selfDialog.show();
            } catch (Exception e) {
                LogUtil.e(e.getMessage() + "");
            }
        }
    }

    /**
     * Init selection dialog 。 （初始化选择对话框）
     */
    private void initSelectDiaLog() {
        selfDialog = new SelfDialog(mActivitySelf);
        selfDialog.setTitle("网络异常");
        selfDialog.setMessage("定义缓存异常，是否重新缓存！");
        selfDialog.setNoOnclickListener("否", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });
        selfDialog.setYesOnclickListener("是", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                cacheWard(mSharePLogin, mActivitySelf, mWardId);
                selfDialog.dismiss();
            }
        });
    }
}
