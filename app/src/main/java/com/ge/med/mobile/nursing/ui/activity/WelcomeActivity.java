package com.ge.med.mobile.nursing.ui.activity;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;

public class WelcomeActivity extends MyBaseActivity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                goToActivity(LoginActivity.class);
                killSelf();
            }

        }
    };

    @Override
    public int setRootView() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initViews() {
        tintManager.setTintColor(Color.parseColor("#FFFFFF"));
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(500);
                    mHandler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void go(View view) {

        killSelf();
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void init() {

    }


    @Override
    public boolean useTitleBar() {
        return false;
    }

}
