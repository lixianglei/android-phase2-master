package com.ge.med.mobile.nursing.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.mySyncTask.ErWeiMaSync;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.mitac.lib.bcr.utils.BARCODE;

public class ErWeiMaActivity extends MyBaseActivity {

    private ErWeiMaSync erWeiMaSync;

    private RelativeLayout mRlTitleBj;
    private ImageView mMyTvTitleLeft;
    private TextView mMyTvTitleCenter;
    private ImageView mImgvTitleRight;
    private TextView mMyTvTitleRight;
    private ImageView mErweimaImgv;
    private TextView mErweimaBianhaoTv;
    private TextView mErweimaKeshiTv;
    private TextView mErweimaYiyuanTv;
    private TextView mUserNameTv;
    private TextView mUserLevelTv;






    @Override
    public int setRootView() {
        return R.layout.activity_er_wei_ma;
    }

    @Override
    public void initViews() {
        tintManager.setTintColor(Color.parseColor("#dddddd"));
        mRlTitleBj = (RelativeLayout) findViewById(R.id.rl_title_bj);
        mMyTvTitleLeft = (ImageView) findViewById(R.id.my_tv_title_left);
        mMyTvTitleCenter = (TextView) findViewById(R.id.my_tv_title_center);
        mImgvTitleRight = (ImageView) findViewById(R.id.imgv_title_right);
        mMyTvTitleRight = (TextView) findViewById(R.id.my_tv_title_right);
        mErweimaImgv = (ImageView) findViewById(R.id.erweima_imgv);
        mErweimaBianhaoTv = (TextView) findViewById(R.id.erweima_bianhao_tv);
        mErweimaKeshiTv = (TextView) findViewById(R.id.erweima_keshi_tv);
        mErweimaYiyuanTv = (TextView) findViewById(R.id.erweima_yiyuan_tv);
        mUserNameTv = (TextView) findViewById(R.id.user_name_tv);
        mUserLevelTv = (TextView) findViewById(R.id.user_level_tv);

        mImgvTitleRight.setVisibility(View.GONE);
        mMyTvTitleRight.setVisibility(View.GONE);
        mMyTvTitleCenter.setText("我的二维码");
        SharePLogin sharePLogin=new SharePLogin(mActivitySelf);
        String displayUserName = sharePLogin.getDisplayUserName();
        String username = sharePLogin.getUsername();
        String wardName = sharePLogin.getWardName();
        mErweimaKeshiTv.setText(wardName);
        mErweimaBianhaoTv.setText(username);
        mUserNameTv.setText(displayUserName);

//        mErweimaYiyuanTv.setText(sharePLogin.getHospitalname());//设置医院名字
        mUserLevelTv.setText(sharePLogin.getUserLevel());
        erWeiMaSync=new ErWeiMaSync(this,mErweimaImgv);
        erWeiMaSync.execute(username);
        mMyTvTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastShort("返回");
                killSelf();
            }
        });
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
