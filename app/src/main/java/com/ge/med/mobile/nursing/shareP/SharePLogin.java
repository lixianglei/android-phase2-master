package com.ge.med.mobile.nursing.shareP;

import android.content.Context;

import com.example.sj.library.util.SharedPrefrencesUtil;
import com.ge.med.mobile.nursing.Constant;

/**
 * Created by Administrator on 2016/8/1 0001.
 */
public class SharePLogin {
    private Context mContext;

    public SharePLogin(Context context) {
        mContext = context;
    }

    //***************************************
    //声音震动控制
    public void savePlaySound(boolean isPlaySound) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_IS_PLAYSOUND, isPlaySound);
    }

    public void saveVibrate(boolean isVibrate) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_IS_VIBRATE, isVibrate);
    }

    public boolean getPlaySound() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_IS_PLAYSOUND, true);

    }

    public boolean getVibrate() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_IS_VIBRATE, true);

    }

    //***********************************
    //Cache 控制
    public void saveCache(boolean isCache) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE, isCache);
    }

    public boolean getCache() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE, true);
    }
    public void saveCacheAssessDefine(boolean isCacheAssessDefine) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_ASSESSDEFINE, isCacheAssessDefine);
    }
    public boolean getCacheAssessDefine() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_ASSESSDEFINE, true);
    }
//    public void saveTopicDefine(boolean isTopicDefine) {
//        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_TOPICDEFINE, isTopicDefine);
//    }
//    public boolean getTopicDefine() {
//        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_TOPICDEFINE, true);
//    }
//    public void saveAnswerdefine(boolean isCacheAnswerdefine) {
//        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_ANSWERDEFINE, isCacheAnswerdefine);
//    }
//    public boolean getAnswerdefine() {
//        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_ANSWERDEFINE, true);
//    }

    public void saveVitalDefine(boolean isVitalDefine) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_VITALDEFINE, isVitalDefine);
    }
    public boolean getVitalDefine() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_VITALDEFINE, true);
    }

    public void saveExceptionDefine(boolean isExceptionDefine) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_EXCEPTIONDEFINE, isExceptionDefine);
    }
    public boolean getExceptionDefine() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_EXCEPTIONDEFINE, true);
    }
    public void saveRiskDefine(boolean isRiskDefine) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_RISKDEFINE, isRiskDefine);
    }
    public boolean getRiskDefine() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_RISKDEFINE, true);
    }
    public void saveNursingEventTemp(boolean isNursingEventTemp) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_NUESINGEVENTTEMP, isNursingEventTemp);
    }
    public boolean getNursingEventTemp() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_NUESINGEVENTTEMP, true);
    }
    public void saveNursingLieBiao(boolean isNursingEventTemp) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_NUESINGEV_LIEBIAO, isNursingEventTemp);
    }
    public boolean getNursingLieBiao() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_NUESINGEV_LIEBIAO, true);
    }
    public void saveXuanJiao(boolean isNursingEventTemp) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_XUANJIAO, isNursingEventTemp);
    }
    public boolean getXuanJiao() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_XUANJIAO, true);
    }
    public void saveHouQiCuoShi(boolean isNursingEventTemp) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_HOUQI_CUOSHI, isNursingEventTemp);
    }
    public boolean getHouQiCuoShi() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_HOUQI_CUOSHI, true);
    }
    public void saveCacheUser(boolean isCacheUser) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_USER, isCacheUser);
    }
    public boolean getCacheUser() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_CACHE_USER, true);
    }

    public void saveFirst(boolean isFirst) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_FIRST, isFirst);
    }

    public boolean getFirst() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_CACHE, Constant.GLOBAL_KEY_IS_FIRST, true);
    }

    //********************************************
    //用户信息控制
    public void saveUsername(String username) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_USER_ACCOUND, username);
    }

    public String getUsername() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_USER_ACCOUND, "");

    }
    public void saveHospitalname(String username) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_HOSPITAL_NAME, username);
    }

    public String getHospitalname() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_HOSPITAL_NAME, "");

    }
    public void saveUserLevel(String userlevel) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_USER_LEVEL, userlevel);
    }

    public String getUserLevel() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_USER_LEVEL, "");

    }

    public void saveUserDisplayName(String name) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_USER_NAME, name);
    }

    public String getDisplayUserName() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_USER_NAME, "");
    }

    public void saveUserid(String userid) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_USER_ID, userid);
    }

    public String getUserid() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_USER_ID, "");
    }

    public void saveWardid(String wardid) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_WARD_ID, wardid);
    }

    public String getWardid() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_WARD_ID, "");
    }

    public void saveWardName(String wardName) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_WARD_NAME, wardName);
    }

    public String getWardName() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_WARD_NAME, "");
    }
    public void saveToken(String token) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_TOKEN, token);
    }

    public String getToken() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_TOKEN, "");
    }
    //*******************************************
    //保存ip 控制
    public void saveIP(String ip) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_IP, ip);
    }

    //
    public String getIP() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_IP, "");
    }

    public void savePort(String port) {
        SharedPrefrencesUtil.saveData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_PORT, port);
    }

    public String getPort() {
        return SharedPrefrencesUtil.getData(mContext, Constant.GLOBAL_FILENAME_USER, Constant.GLOBAL_KEY_PORT, "");
    }

}
