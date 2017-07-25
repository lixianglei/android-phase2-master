package com.ge.med.mobile.nursing.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.forjson.NetworkForSynchronize;
import com.ge.med.mobile.nursing.utils.MessageEvent;

import org.xutils.common.util.LogUtil;

import de.greenrobot.event.EventBus;

public class SyncService extends Service {

    private static boolean isConnected = true;
    private boolean killThread = false;
    private Thread mThread;
    private Service service;
    private int noNetCount = 0;
    private int withNetCount = 0;
    private static final int RETRY_MAX_COUNT_DETECT_NONET = 1;
    private static final int RETRY_MAX_COUNT = 3;

    private boolean isSyncing = false;

    public SyncService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d("SyncService starting.....");
        killThread = false;
        getThread();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogUtil.d("SyncService destroying.....");
        super.onDestroy();
        killThread = true;
    }

    @Override
    public void onCreate() {
        LogUtil.d("SyncService creating.....");
        super.onCreate();
        killThread = false;
        service = this;
        getThread();
    }

    public static void syncData(){
        LogUtil.d("SyncService syncData calling.....");
        String retval = preCheckSync();
        if (retval != null){
            if (Constant.MSG_SYNC_READY.equalsIgnoreCase(retval)) {
                retval = Constant.MSG_SYNC_REQ_SUBMIT;
                NetworkForSynchronize.syncAllData();
            }
            EventBus.getDefault().post(new MessageEvent(retval));
            LogUtil.i("SyncService syncData> 同步消息发出：" + retval);
        }
    }

    private void trySycnData(){
        LogUtil.i("SyncService trySycnData calling.....");
        String retval = preCheckSync();
        if (retval != null && Constant.MSG_SYNC_READY.equalsIgnoreCase(retval)){
            EventBus.getDefault().post(new MessageEvent(Constant.MSG_SYNC_REQ));
            LogUtil.i("请求启动同步消息发出：" + retval);
        }
    }

    private static String preCheckSync(){
        LogUtil.i("SyncService preCheckSync calling.....");
        String retval = null;
        if (NetworkForSynchronize.isSyncNeeded()){
            if (NetworkForSynchronize.isSyncFinished()) {
                retval = Constant.MSG_SYNC_READY;
            }else{
                retval = Constant.MSG_SYNC_REQ_REFUSE_NODATA;
            }
        }else{
            retval = Constant.MSG_SYNC_REQ_REFUSE_SYNCING;
        }
        return retval;
    }

    private Thread getThread(){
        if (mThread == null || !mThread.isAlive()){
            mThread = new Thread(){
                @Override
                public void run() {
                    LogUtil.d("SyncService detect thread running...");
                    super.run();
                    int checkUnsyncDataCount = 0;
                    while(true){
                        if (killThread) break;
                     //   if (NetReceiver.isConnected(service) != 0){
                            NetworkForSynchronize.connectServer();
                            if (NetworkForSynchronize.isServerOk){
                                withNetCount++;
                                noNetCount = 0;
                               // LogUtil.d("Server is ready for [" + withNetCount + "] detect period!");
                            }else{
                                noNetCount++;
                                withNetCount = 0;
                                //LogUtil.d("Server is losted for [" + noNetCount + "] detect period!");
                            }
//                        } else{
//                            LogUtil.i("Network not connected!");
//                        }
                        if (noNetCount > RETRY_MAX_COUNT_DETECT_NONET){
                            if (isConnected) {
                                isConnected = false;
                                LogUtil.i("Server is not reachable since cannot get any response for " + RETRY_MAX_COUNT + " times!");
                            }
                        }
                        if (withNetCount > RETRY_MAX_COUNT){
                            if (!isConnected) {
                                isConnected = true;
                                trySycnData();
                                checkUnsyncDataCount = 0;
                                LogUtil.i("Try to sync data since Server is ready from non-reachable for " + RETRY_MAX_COUNT + " times!");
                            }
                        }
                        if (isConnected && checkUnsyncDataCount > Constant.CHECK_UNSYNC_MAX_COUNT){
                            trySycnData();
                            checkUnsyncDataCount = 0;
                            LogUtil.i("withNetCount > RETRY_MAX_COUNT > checkUnsyncDataCount:" + checkUnsyncDataCount + " !");
                        }
                        try {
                            this.sleep(Constant.TIME_INTERVAL_COUNT);
                            checkUnsyncDataCount++;
                            LogUtil.i("after sleep> checkUnsyncDataCount:" + checkUnsyncDataCount + " !");

                        } catch (InterruptedException e) {
                            LogUtil.e("Waiting thread is interrupted by someone!");
                        }
                    }
                }
            };
            mThread.start();
        }
        return mThread;
    }

    public static boolean isConnected() {
        return isConnected;
    }
}
