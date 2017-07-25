package com.ge.med.mobile.nursing.ui.view;

import android.animation.Animator;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.ui.adapter.YizhuPopuwindowLvItemAdapter;
import com.ge.med.mobile.nursing.ui.adapter.YzHdRenLvItemAdapter;
import com.ge.med.mobile.nursing.utils.Utility;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/3/16.
 */
public class YiZhuPopuwindow extends BasePopupWindow {
    private LinearLayout mYizhuPopuwindowLl1;
    private LinearLayout mYizhuPopuwindowBackgroundLl;
    private LinearLayout mYizhuPopuwindowLl2;
    private ListView mYizhuPopuwindowLv;
    private WindowManager wm;
    private int[] location = new int[2];
    private boolean isOptFor;
    private View[] mViews;

    public YiZhuPopuwindow(Activity context, View view, YiZhuBean.DataBean mYiZhuBean, final TextView mYzShuxueXuanzhongCheckBox, final YzHdRenLvItemAdapter yzHdRenLvItemAdapter) {
        super(context);
        List<YiZhuBean.OrderPharm> list = mYiZhuBean.getPharmList();
      if(list == null){
          list = new ArrayList<>();
      }
        wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        mYizhuPopuwindowLl1 = (LinearLayout) findViewById(R.id.yizhu_popuwindow_ll1);
        mYizhuPopuwindowBackgroundLl = (LinearLayout) findViewById(R.id.yizhu_popuwindow_background_ll);
        mYizhuPopuwindowLl2 = (LinearLayout) findViewById(R.id.yizhu_popuwindow_ll2);
        mYizhuPopuwindowLv = (ListView) findViewById(R.id.yizhu_popuwindow_lv);
        view.getLocationOnScreen(location);
        ViewGroup.LayoutParams layoutParams1 = mYizhuPopuwindowLv.getLayoutParams();
        if (list.size() < 3) {
            layoutParams1.height= ActionBar.LayoutParams.WRAP_CONTENT;
        }

        mYizhuPopuwindowLv.setAdapter(new YizhuPopuwindowLvItemAdapter(context, mYiZhuBean));
        mYizhuPopuwindowLv.setLayoutParams(layoutParams1);
        if (location[1] > height / 2) {
            ViewGroup.LayoutParams layoutParams = mYizhuPopuwindowLl1.getLayoutParams();
            if (list.size() < 3) {
                layoutParams.height = location[1] - getStatusBarHeight(context) - Utility.getTotalHeightofListView(mYizhuPopuwindowLv);
            }else{
                layoutParams.height = location[1] - getStatusBarHeight(context) - layoutParams1.height;
            }
            mYizhuPopuwindowLl1.setLayoutParams(layoutParams);
        } else {
            ViewGroup.LayoutParams layoutParams = mYizhuPopuwindowLl1.getLayoutParams();
            layoutParams.height = location[1] + view.getHeight() - getStatusBarHeight(context);
            mYizhuPopuwindowLl1.setLayoutParams(layoutParams);
        }
        if(mYzShuxueXuanzhongCheckBox!=null){
            mYzShuxueXuanzhongCheckBox.setVisibility(View.VISIBLE);
        }
        mYizhuPopuwindowBackgroundLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YiZhuPopuwindow.this.dismiss();
                if(yzHdRenLvItemAdapter!=null){
                    yzHdRenLvItemAdapter.notifyDataSetChanged();
                }
                if(mYzShuxueXuanzhongCheckBox!=null){
                    mYzShuxueXuanzhongCheckBox.setVisibility(View.GONE);
                }
                if(isOptFor){
                    gongView();
                }
            }
        });
    }
    private void gongView(){
        if(mViews!=null){
            for(int i = 0 ; i < mViews.length ;i++){
                mViews[i] .setVisibility(View.GONE);
            }
        }
    }
    public void setOptFor(Boolean b,View... views){
        isOptFor =b;
        if(views!=null){
            mViews = views;
        }
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
        return createPopupById(R.layout.yi_zhu_popuwindow);
    }

    @Override
    public View initAnimaView() {
        return null;
    }
}
