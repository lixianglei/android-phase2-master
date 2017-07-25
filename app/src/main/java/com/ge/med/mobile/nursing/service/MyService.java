package com.ge.med.mobile.nursing.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.example.sj.library.control.TouchTime;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.ui.activity.LoginActivity;

/**
 * 控制用户一段时间无操作自动返回登陆界面
 */
public class MyService extends Service {
    Thread myThread;

    public MyService() {

    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        if (myThread == null||!myThread.isAlive()) {
            myThread = new MyThread();
            myThread.start();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            super.run();
            while (true) {

                if (!MyBaseActivity.IsLogin) {
                    break;
                }
                if (System.currentTimeMillis() - TouchTime.NOW_TIME > Constant.TIMING) {
                    Intent dl = new Intent(MyService.this, LoginActivity.class);
                    dl.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dl);
                    break;
                }
                SystemClock.sleep(1000);
            }

        }
    }
}
