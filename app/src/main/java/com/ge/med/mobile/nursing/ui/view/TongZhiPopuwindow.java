package com.ge.med.mobile.nursing.ui.view;

import android.animation.Animator;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.TongZhiBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.ui.adapter.YizhuPopuwindowLvItemAdapter;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/4/6.
 */
public class TongZhiPopuwindow extends BasePopupWindow {
    private LinearLayout mYizhuPopuwindowBackgroundLl;
    private LinearLayout mYizhuPopuwindowLl1;
    private LinearLayout mYizhuPopuwindow1;
    private TextView mTongZhiXiangQingSjTv;
    private TextView mTongZhiXiangQingTv;
    private TextView mTongZhiXiangQingSjTv2;
    private LinearLayout mYizhuPopuwindowLl2;
    private WindowManager wm;
    private int[] location = new int[2];
    private boolean isOptFor;
    private View[] mViews;

    public TongZhiPopuwindow(Activity context, final View view, final TextView mYzShuxueXuanzhongCheckBox, TongZhiBean.DataBean object, String sj) {
        super(context);
        wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        final int height = wm.getDefaultDisplay().getHeight();
        mYizhuPopuwindowBackgroundLl = (LinearLayout) findViewById(R.id.yizhu_popuwindow_background_ll);
        mYizhuPopuwindowLl1 = (LinearLayout) findViewById(R.id.yizhu_popuwindow_ll1);
        mYizhuPopuwindow1 = (LinearLayout) findViewById(R.id.yizhu_popuwindow_1);
        mTongZhiXiangQingSjTv = (TextView) findViewById(R.id.tong_zhi_xiang_qing_sj_tv);
        mTongZhiXiangQingTv = (TextView) findViewById(R.id.tong_zhi_xiang_qing_tv);
        mTongZhiXiangQingSjTv2 = (TextView) findViewById(R.id.tong_zhi_xiang_qing_sj_tv2);
        mYizhuPopuwindowLl2 = (LinearLayout) findViewById(R.id.yizhu_popuwindow_ll2);
        if (object != null) {
            if (Constant.NOTIFY_TYPE_ORDER == object.getNotifyType()) {
                mTongZhiXiangQingSjTv2.setVisibility(View.VISIBLE);
            }else{
                mTongZhiXiangQingSjTv2.setVisibility(View.GONE);
            }
            try {
                mTongZhiXiangQingTv.setText(object.getContent());
                mTongZhiXiangQingSjTv2.setText(sj);
                mTongZhiXiangQingSjTv.setText(DateUtils.getDateString("yyyy-MM-dd HH:mm", object.getShowTime()));
            } catch (Exception e) {
                LogUtil.e(e.getMessage() + "");
            }
        }
        view.getLocationOnScreen(location);
        mYzShuxueXuanzhongCheckBox.setVisibility(View.VISIBLE);
        mYizhuPopuwindow1.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                , View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                mYizhuPopuwindow1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        View iv1 = findViewById(R.id.yizhu_popuwindow_1);
                        if (location[1] > height / 2) {
                            ViewGroup.LayoutParams layoutParams = mYizhuPopuwindowLl1.getLayoutParams();
                            layoutParams.height = location[1]  - iv1.getMeasuredHeight();
                            mYizhuPopuwindowLl1.setLayoutParams(layoutParams);
                        } else {
                            ViewGroup.LayoutParams layoutParams = mYizhuPopuwindowLl1.getLayoutParams();
                            layoutParams.height = location[1] + view.getHeight();
                            mYizhuPopuwindowLl1.setLayoutParams(layoutParams);
                        }
                    }
                }, 300);
            }
        });

        if (location[1] > height / 2) {
            ViewGroup.LayoutParams layoutParams = mYizhuPopuwindowLl1.getLayoutParams();
            layoutParams.height = location[1]  - mYizhuPopuwindow1.getMeasuredHeight();
            mYizhuPopuwindowLl1.setLayoutParams(layoutParams);
        } else {
            ViewGroup.LayoutParams layoutParams = mYizhuPopuwindowLl1.getLayoutParams();
            layoutParams.height = location[1] + view.getHeight();
            mYizhuPopuwindowLl1.setLayoutParams(layoutParams);
        }
        mYizhuPopuwindowBackgroundLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongZhiPopuwindow.this.dismiss();
                mYzShuxueXuanzhongCheckBox.setVisibility(View.GONE);
                if (isOptFor) {
                    gongView();
                }
            }
        });
    }

    private void gongView() {
        if (mViews != null) {
            for (int i = 0; i < mViews.length; i++) {
                mViews[i].setVisibility(View.GONE);
            }
        }
    }

    public void setOptFor(Boolean b, View... views) {
        isOptFor = b;
        if (views != null) {
            mViews = views;
        }
    }

    //获取listView 的高度
    public int getTotalHeightofListView(ListView listView) {
        ListAdapter mAdapter = (ListAdapter) listView.getAdapter();
        if (mAdapter == null) {
            return 0;
        }
        int totalHeight = 0;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, listView);
            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            //mView.measure(0, 0);
            totalHeight += mView.getMeasuredHeight();
            //  Log.w("HEIGHT" + i, String.valueOf(totalHeight));
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
        return params.height;
    }

    //获取状态栏的高度
    private Integer getStatusBarHeight(Context context) {
        /**
         * 获取状态栏高度——方法1
         * */
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight1;
    }

    @Override
    protected Animator initShowAnimator() {
        return super.initShowAnimator();
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.tong_zhi_popuwindow);
    }

    @Override
    public View initAnimaView() {
        return null;
    }
}
