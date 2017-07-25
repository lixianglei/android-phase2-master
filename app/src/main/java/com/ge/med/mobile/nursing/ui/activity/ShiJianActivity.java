package com.ge.med.mobile.nursing.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.ui.adapter.VPFragMainAdapter;
import com.ge.med.mobile.nursing.ui.fragment.ShiJianFragment;
import com.ge.med.mobile.nursing.ui.view.MyViewPager;
import com.mitac.lib.bcr.utils.BARCODE;

public class ShiJianActivity extends MyBaseActivity implements View.OnClickListener {
    private RelativeLayout mRlTitleBj;
    private ImageView mMyTvTitleLeft;
    private TextView mMyTvTitleCenter;
    private ImageView mImgvTitleRight;
    private TextView mMyTvTitleRight;
    private TextView mHzJibenXinxiTv;
    private LinearLayout mHzPgLl;
    private TextView mHzPgTv1;
    private TextView mHzPgTv2;
    private TextView mHzPgTv3;
    private TextView mHzPgTv4;
    private TextView mHzYsZdTv;
    private TextView mHzGmsTv;
    private TextView mDrtzSjImgv;
    private MyViewPager mTiZhengDuoRenVp;
    private ImageView mDrtzBackImgv;
    private ImageView mDrtzNextImgv;
    private LinearLayout mTiZhengDuoRenBtLl;
    private ImageButton mTiZhengDuoRenBackBt;
    private ImageButton mTiZhengDuoRenNextBt;
    private Button mTiZhengDuoRenBaocunBt;

    private ShiJianFragment shiJianFragment = new ShiJianFragment();
    private BaseFragment[] baseFragments = { shiJianFragment};
    private VPFragMainAdapter vpFragMainAdapter;
    @Override
    public int setRootView() {
        return R.layout.activity_shi_jian;
    }

    @Override
    public void initViews() {

        mRlTitleBj = (RelativeLayout) findViewById(R.id.rl_title_bj);
        mMyTvTitleLeft = (ImageView) findViewById(R.id.my_tv_title_left);
        mMyTvTitleCenter = (TextView) findViewById(R.id.my_tv_title_center);
        mImgvTitleRight = (ImageView) findViewById(R.id.imgv_title_right);
        mMyTvTitleRight = (TextView) findViewById(R.id.my_tv_title_right);
        mHzJibenXinxiTv = (TextView) findViewById(R.id.hz_jiben_xinxi_tv);
        mHzPgLl = (LinearLayout) findViewById(R.id.hz_pg_ll);
        mHzPgTv1 = (TextView) findViewById(R.id.hz_pg_tv1);
        mHzPgTv2 = (TextView) findViewById(R.id.hz_pg_tv2);
        mHzPgTv3 = (TextView) findViewById(R.id.hz_pg_tv3);
        mHzPgTv4 = (TextView) findViewById(R.id.hz_pg_tv4);
        mHzYsZdTv = (TextView) findViewById(R.id.hz_ys_zd_tv);
        mHzGmsTv = (TextView) findViewById(R.id.hz_gms_tv);
        mDrtzSjImgv = (TextView) findViewById(R.id.drtz_sj_imgv);
        mTiZhengDuoRenVp = (MyViewPager) findViewById(R.id.ti_zheng_duo_ren_vp);
        mDrtzBackImgv = (ImageView) findViewById(R.id.drtz_back_imgv);
        mDrtzNextImgv = (ImageView) findViewById(R.id.drtz_next_imgv);
        mTiZhengDuoRenBtLl = (LinearLayout) findViewById(R.id.ti_zheng_duo_ren_bt_ll);
        mTiZhengDuoRenBackBt = (ImageButton) findViewById(R.id.ti_zheng_duo_ren_back_bt);
        mTiZhengDuoRenNextBt = (ImageButton) findViewById(R.id.ti_zheng_duo_ren_next_bt);
        mTiZhengDuoRenBaocunBt = (Button) findViewById(R.id.ti_zheng_duo_ren_baocun_bt);

        //标题栏设置
        tintManager.setTintColor(Color.parseColor("#f5a623"));
        mRlTitleBj.setBackgroundColor(Color.parseColor("#f5a623"));
        mMyTvTitleCenter.setText("孙尚香 33床");
        mMyTvTitleCenter.setTextColor(Color.WHITE);
        mMyTvTitleLeft.setImageResource(R.mipmap.icon_return);
//        mImgvTitleRight.setVisibility(View.GONE);
        mImgvTitleRight.setOnClickListener(this);
        mMyTvTitleRight.setVisibility(View.GONE);
        mMyTvTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killSelf();
            }
        });

        mTiZhengDuoRenBaocunBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(TiZheng_DuoRenActivity.class);
            }
        });

        vpFragMainAdapter = new VPFragMainAdapter(mFragmentManager, baseFragments);
        mTiZhengDuoRenVp.setAdapter(vpFragMainAdapter);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //体温
            case R.id.imgv_title_right:
                killSelf();
                break;
        }
    }

}
