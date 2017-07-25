package com.ge.med.mobile.nursing.forjson;

import android.util.Log;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.AssessMeasureRecords;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.VitalSignDaoImpl;
import com.ge.med.mobile.nursing.db.DBAssessMeasureRecords;
import com.ge.med.mobile.nursing.db.DBXuanJiaoRecord;
import com.ge.med.mobile.nursing.forjson.entity.IBaseBean;
import com.ge.med.mobile.nursing.service.SyncService;
import com.ge.med.mobile.nursing.utils.MessageEvent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.common.util.LogUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.greenrobot.event.EventBus;
import okhttp3.Call;

/**
 * Created by Qu on 2016/12/23.
 */
public class NetworkForSynchronize {
    public static boolean isServerOk = false;
    private static long TIMOUT_INTERVAL = 2*60*1000l;
    // 同步发起时创建条目，key为同步的数据类，value为发起同步时的时间戳，当时间超过timeout后，需要删除改条目
    private static Map<String, Long> syncStatusAll = new HashMap<String, Long>();

    private static String getTestURL() {
        LogUtil.d("URL.URL_IP is " + URL.URL_IP + ",URL.PORT is " + URL.URL_PORT + ", URL.URL is " + URL.URL);
        return URL.CESHI;
    }

    public static void connectServer() {
        LogUtil.d("Detecting server reachable or not at URL:" + getTestURL());
        OkHttpUtils.get().url(getTestURL()).addHeader("User-Agent", "www.gs.com")
                //.addParams(Constant.GLOBAL_KEY_WARD_ID, "301")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                NetworkForSynchronize.isServerOk = false;
                LogUtil.e("Tried to connect Server[" + call.toString() + "] failed! exception is " + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                NetworkForSynchronize.isServerOk = true;
                // LogUtil.d("Tried to connect Server[" + TEST_URL + "] successfully! response is " + response);
            }
        });
    }
    public static boolean isSyncFinished(){
        boolean retval = true;
        if (syncStatusAll == null){
            syncStatusAll = new HashMap<String, Long>();
        }else {
            Iterator<String> keySet = syncStatusAll.keySet().iterator();
            Long syncTime = null;
            while(keySet.hasNext()){
                syncTime = syncStatusAll.get(keySet.next());
                if (syncTime + TIMOUT_INTERVAL <= System.currentTimeMillis()){
                    keySet.remove();
                }else retval = false;
            }
        }
        return retval;
    }
    public static void syncFinished(String clazz){
        if (syncStatusAll == null){
            syncStatusAll = new HashMap<String, Long>();
        }else{
            syncStatusAll.remove(clazz);
        }
    }

    public static void syncStart(String clazz){
        if (syncStatusAll == null){
            syncStatusAll = new HashMap<String, Long>();
        }
        syncStatusAll.put(clazz, System.currentTimeMillis());
    }

    public static boolean isSyncNeeded(){
        boolean retval = false;
        //findalldb();
        if (!retval){
            retval = new AssessDaoImpl().isUnSyncDataExists();
        }

        if (!retval){
            retval = new VitalSignDaoImpl().isUnSyncDataExists();
        }

        if (!retval){
            retval = new DoctorOrderDaoImpl().isUnSyncDataExists();
        }

        if (!retval){
            retval = new AssessDaoImpl().isUnSyncDataExists(DBXuanJiaoRecord.class);
        }


        if (!retval){
            retval = new AssessDaoImpl().isUnSyncDataExists(DBAssessMeasureRecords.class);
        }

        return retval;
    }
    public static void syncAllData() {
        LogUtil.d("syncAllData calling...");
        if (SyncService.isConnected()) {
            try {
                NetworkForAssessment.submitAllAssessment(new AssessDaoImpl().findUnSyncData());
            } catch (Exception e) {
                LogUtil.e("Exception occured while try to synchronize assessment data! message is " + e.getMessage());
            }
            try {
                NetworkForXuanJiao.submitXuanJiaoJilu(new AssessDaoImpl().findUnSyncData(DBXuanJiaoRecord.class));
            } catch (Exception e) {
                LogUtil.e("Exception occured while try to synchronize XuanJiaoJilu data! message is " + e.getMessage());
            }
            try {
                NetworkForAssessMeasureRecords.submitAssessMeasureRecord(new AssessDaoImpl().findUnSyncData(DBAssessMeasureRecords.class), false + "");
            } catch (Exception e) {
                LogUtil.e("Exception occured while try to synchronize XuanJiaoJilu data! message is " + e.getMessage());
            }

            try {
                NetworkForVitalSign.submitAllVitalSignSheet(new VitalSignDaoImpl().findUnSyncData());
            } catch (Exception e) {
                LogUtil.e("Exception occured while try to synchronize vital sign data! message is " + e.getMessage());
            }
            try {
                NetworkForDoctorOrder.sumbmitAllDoctorOrder(new DoctorOrderDaoImpl().findUnSyncData());
            } catch (Exception e) {
                LogUtil.e("Exception occured while try to synchronize doctor order data! message is " + e.getMessage());
            }
        } else {
            LogUtil.e("Can not synchronize any data since SyncService.isConnected is false!");
        }
    }
}
