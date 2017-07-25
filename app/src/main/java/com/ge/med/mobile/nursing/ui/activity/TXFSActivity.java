package com.ge.med.mobile.nursing.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.base.Myapp;
import com.ge.med.mobile.nursing.shareP.SharePLogin;

public class TXFSActivity extends MyBaseActivity {
    private RelativeLayout mRlTitleBj;
    private ImageView mMyTvTitleLeft;
    private TextView mMyTvTitleCenter;
    private ImageView mImgvTitleRight;
    private TextView mMyTvTitleRight;
    private CheckBox mTxfsSySwitch;
    private CheckBox mTxfsZdSwitch;


    @Override
    public int setRootView() {
        return R.layout.activity_txfs;
    }

    @Override
    public void initViews() {
        final SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        Myapp.vibrate = sharePLogin.getVibrate();
        Myapp.playSound = sharePLogin.getPlaySound();
        tintManager.setTintColor(Color.parseColor("#dddddd"));
        mRlTitleBj = (RelativeLayout) findViewById(R.id.rl_title_bj);
        mMyTvTitleLeft = (ImageView) findViewById(R.id.my_tv_title_left);
        mMyTvTitleCenter = (TextView) findViewById(R.id.my_tv_title_center);
        mImgvTitleRight = (ImageView) findViewById(R.id.imgv_title_right);
        mMyTvTitleRight = (TextView) findViewById(R.id.my_tv_title_right);
        mTxfsSySwitch = (CheckBox) findViewById(R.id.txfs_sy_switch);
        mTxfsZdSwitch = (CheckBox) findViewById(R.id.txfs_zd_switch);
        mMyTvTitleCenter.setText("设置提醒方式");
        mImgvTitleRight.setVisibility(View.GONE);
        mMyTvTitleRight.setVisibility(View.GONE);
        mMyTvTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killSelf();
            }
        });
        mTxfsSySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePLogin.savePlaySound(mTxfsSySwitch.isChecked());
                Myapp.playSound = mTxfsSySwitch.isChecked();
            }
        });
        mTxfsZdSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePLogin.saveVibrate(mTxfsZdSwitch.isChecked());
                Myapp.vibrate = mTxfsZdSwitch.isChecked();
            }
        });
        mTxfsSySwitch.setChecked(Myapp.playSound);
        mTxfsZdSwitch.setChecked(Myapp.vibrate);
    }

    @Override
    protected void onResume() {
        super.onResume();

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
