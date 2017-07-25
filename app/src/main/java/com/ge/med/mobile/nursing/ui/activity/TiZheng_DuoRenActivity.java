package com.ge.med.mobile.nursing.ui.activity;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
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
import com.ge.med.mobile.nursing.ui.fragment.TZ_HXFragment;
import com.ge.med.mobile.nursing.ui.fragment.TZ_MBFragment;
import com.ge.med.mobile.nursing.ui.fragment.TZ_SSYFragment;
import com.ge.med.mobile.nursing.ui.fragment.TZ_SZYFragment;
import com.ge.med.mobile.nursing.ui.fragment.TZ_TWFragment;
import com.ge.med.mobile.nursing.ui.fragment.TZ_TZFragment;
import com.ge.med.mobile.nursing.ui.fragment.TZ_XLFragment;
import com.ge.med.mobile.nursing.ui.view.MyViewPager;

public class TiZheng_DuoRenActivity extends MyBaseActivity implements View.OnClickListener {
    private VPFragMainAdapter vpFragMainAdapter;

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
    private LinearLayout mTiZhengXuanXiangIncludeLl;
    private TextView mShaiXuanIncludeKongbaiTv;
    private TextView mTiZhengXuanXiangTiwenTv;
    private TextView mTiZhengXuanXiangTizhongTv;
    private TextView mTiZhengXuanXiangMaiboTv;
    private TextView mTiZhengXuanXiangXinlvTv;
    private TextView mTiZhengXuanXiangShousuoyaTv;
    private TextView mTiZhengXuanXiangShuzhangyaTv;
    private TextView mTiZhengXuanXiangHuxiTv;
    private LinearLayout mTiZhengDuoRenBtLl;
    private ImageButton mTiZhengDuoRenBackBt;
    private ImageButton mTiZhengDuoRenNextBt;
    private Button mTiZhengDuoRenBaocunBt;

    private TextView mZhanweizhi;



    private TZ_TWFragment tz_twFragment = new TZ_TWFragment();
    private TZ_TZFragment tz_tzFragment = new TZ_TZFragment();
    private TZ_MBFragment tz_mbFragment = new TZ_MBFragment();
    private TZ_XLFragment tz_xlFragment = new TZ_XLFragment();
    private TZ_SSYFragment tz_ssyFragment = new TZ_SSYFragment();
    private TZ_SZYFragment tz_szyFragment = new TZ_SZYFragment();
    private TZ_HXFragment tz_hxFragment = new TZ_HXFragment();
    private BaseFragment[] baseFragments = {tz_twFragment, tz_tzFragment, tz_mbFragment, tz_xlFragment, tz_ssyFragment, tz_szyFragment, tz_hxFragment};


    @Override
    public int setRootView() {
        return R.layout.activity_ti_zheng__duo_ren;
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
        mTiZhengXuanXiangIncludeLl = (LinearLayout) findViewById(R.id.ti_zheng_xuan_xiang_include_ll);
        mShaiXuanIncludeKongbaiTv = (TextView) findViewById(R.id.shai_xuan_include_kongbai_tv);
        mTiZhengXuanXiangTiwenTv = (TextView) findViewById(R.id.ti_zheng_xuan_xiang_tiwen_tv);
        mTiZhengXuanXiangTizhongTv = (TextView) findViewById(R.id.ti_zheng_xuan_xiang_tizhong_tv);
        mTiZhengXuanXiangMaiboTv = (TextView) findViewById(R.id.ti_zheng_xuan_xiang_maibo_tv);
        mTiZhengXuanXiangXinlvTv = (TextView) findViewById(R.id.ti_zheng_xuan_xiang_xinlv_tv);
        mTiZhengXuanXiangShousuoyaTv = (TextView) findViewById(R.id.ti_zheng_xuan_xiang_shousuoya_tv);
        mTiZhengXuanXiangShuzhangyaTv = (TextView) findViewById(R.id.ti_zheng_xuan_xiang_shuzhangya_tv);
        mTiZhengXuanXiangHuxiTv = (TextView) findViewById(R.id.ti_zheng_xuan_xiang_huxi_tv);
        mTiZhengDuoRenBtLl = (LinearLayout) findViewById(R.id.ti_zheng_duo_ren_bt_ll);
        mTiZhengDuoRenBackBt = (ImageButton) findViewById(R.id.ti_zheng_duo_ren_back_bt);
        mTiZhengDuoRenNextBt = (ImageButton) findViewById(R.id.ti_zheng_duo_ren_next_bt);
        mTiZhengDuoRenBaocunBt = (Button) findViewById(R.id.ti_zheng_duo_ren_baocun_bt);
        mZhanweizhi = (TextView) findViewById(R.id.zhanweizhi);

        mTiZhengXuanXiangTiwenTv.setOnClickListener(this);
        mTiZhengXuanXiangTizhongTv.setOnClickListener(this);
        mTiZhengXuanXiangMaiboTv.setOnClickListener(this);
        mTiZhengXuanXiangXinlvTv.setOnClickListener(this);
        mTiZhengXuanXiangShousuoyaTv.setOnClickListener(this);
        mTiZhengXuanXiangShuzhangyaTv.setOnClickListener(this);
        mTiZhengXuanXiangHuxiTv.setOnClickListener(this);
        mTiZhengDuoRenBackBt.setOnClickListener(this);
        mTiZhengDuoRenNextBt.setOnClickListener(this);
        mTiZhengDuoRenBaocunBt.setOnClickListener(this);
        mShaiXuanIncludeKongbaiTv.setOnClickListener(this);
        mZhanweizhi.setOnClickListener(this);

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

        vpFragMainAdapter = new VPFragMainAdapter(mFragmentManager, baseFragments);
        mTiZhengDuoRenVp.setAdapter(vpFragMainAdapter);
        mTiZhengDuoRenVp.setCurrentItem(0, false);
        //fragment vp监听
        mTiZhengDuoRenVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mDrtzSjImgv.setText("体温");
                        break;
                    case 1:
                        mDrtzSjImgv.setText("体重");
                        break;
                    case 2:
                        mDrtzSjImgv.setText("脉搏");
                        break;
                    case 3:
                        mDrtzSjImgv.setText("心率");
                        break;
                    case 4:
                        mDrtzSjImgv.setText("收缩压");
                        break;
                    case 5:
                        mDrtzSjImgv.setText("舒张压");
                        break;
                    case 6:
                        mDrtzSjImgv.setText("呼吸");
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //体温
            case R.id.ti_zheng_xuan_xiang_tiwen_tv:
                showToastShort("体温");
                mTiZhengDuoRenVp.setCurrentItem(0, false);
                mTiZhengXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //体重
            case R.id.ti_zheng_xuan_xiang_tizhong_tv:
                showToastShort("体重");
                mTiZhengDuoRenVp.setCurrentItem(1, false);
                mTiZhengXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //脉搏
            case R.id.ti_zheng_xuan_xiang_maibo_tv:
                showToastShort("脉搏");
                mTiZhengDuoRenVp.setCurrentItem(2, false);
                mTiZhengXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //心率
            case R.id.ti_zheng_xuan_xiang_xinlv_tv:
                showToastShort("心率");
                mTiZhengDuoRenVp.setCurrentItem(3, false);
                mTiZhengXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //收缩压
            case R.id.ti_zheng_xuan_xiang_shousuoya_tv:
                showToastShort("收缩压");
                mTiZhengDuoRenVp.setCurrentItem(4, false);
                mTiZhengXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //舒张压
            case R.id.ti_zheng_xuan_xiang_shuzhangya_tv:
                showToastShort("舒张压");
                mTiZhengDuoRenVp.setCurrentItem(5, false);
                mTiZhengXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //呼吸
            case R.id.ti_zheng_xuan_xiang_huxi_tv:
                showToastShort("呼吸");
                mTiZhengDuoRenVp.setCurrentItem(6, false);
                mTiZhengXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //空白
            case R.id.shai_xuan_include_kongbai_tv:
                showToastShort("空白");
                mTiZhengXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //回退
            case R.id.ti_zheng_duo_ren_back_bt:
                showToastShort("回退");
                int i = mTiZhengDuoRenVp.getCurrentItem();
                if (i != 0) {
                    mTiZhengDuoRenVp.setCurrentItem(i - 1, false);
                }
                break;
            //前进
            case R.id.ti_zheng_duo_ren_next_bt:
                showToastShort("前进");
                int i1 = mTiZhengDuoRenVp.getCurrentItem();
                if (i1 != 6) {
                    mTiZhengDuoRenVp.setCurrentItem(i1 + 1, false);
                }
                break;
            //保存
            case R.id.ti_zheng_duo_ren_baocun_bt:
                showToastShort("保存");
                int i2 = mTiZhengDuoRenVp.getCurrentItem();
                if (i2 != 6) {
                    mTiZhengDuoRenVp.setCurrentItem(i2 + 1, false);
                }
                break;
            //筛选
            case R.id.imgv_title_right:
                mTiZhengXuanXiangIncludeLl.setVisibility(View.VISIBLE);
                break;


        }
    }

}
