package com.ge.med.mobile.nursing.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;

/**
 *
 */

public class SearchGroupView extends LinearLayout {

    private Context context;

    public SearchGroupView(Context context) {
        super(context);
        this.context = context;
        setOrientation(VERTICAL);//设置方向
    }

    public SearchGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setOrientation(VERTICAL);//设置方向
    }

    public SearchGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setOrientation(VERTICAL);//设置方向
    }


    /**
     * 外部接口调用
     *
     * @param items
     * @param onItemClick
     */
    public void initViews(String[] items, final OnItemClick onItemClick,int deleteWidth) {
        removeAllViews();
        int length = 0;//一行加载item 的宽度

        LinearLayout layout = null;

        LayoutParams layoutLp = null;

        boolean isNewLine = true;//是否换行

        int screenWidth = getScreenWidth()-deleteWidth;//屏幕的宽度

        int size = items.length;
        for (int i = 0; i < size; i++) {//便利items
            if (isNewLine) {//是否开启新的一行
                layout = new LinearLayout(context);
                layout.setOrientation(HORIZONTAL);
                layoutLp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutLp.topMargin = 16;
            }

            View view = LayoutInflater.from(context).inflate(R.layout.layout_search_group_item, null);
            TextView itemView = (TextView) view.findViewById(R.id.hz_pg_tv_quan);
            itemView.setText(items[i]);
            if(i == 0){
                setIllTagColor(itemView,items[i]);
            }
            final int j = i;
            itemView.setOnClickListener(new OnClickListener() {//给每个item设置点击事件
                @Override
                public void onClick(View v) {
                    if (null != onItemClick) {
                        onItemClick.onClick(j);
                    }
                }
            });

            //设置item的参数
            LayoutParams itemLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            itemLp.leftMargin = 15;

            //得到当前行的长度
            length += 15 + getViewWidth(itemView);
            if (length > screenWidth) {//当前行的长度大于屏幕宽度则换行
                length = 0;
                addView(layout, layoutLp);
                isNewLine = true;
                i--;
            } else {//否则添加到当前行
                isNewLine = false;
                layout.addView(view, itemLp);
            }
        }
        addView(layout, layoutLp);
    }
    //设置风险标签的颜色
    private  void setIllTagColor(TextView view, String illState) {
        if (illState == null) return;
        switch (illState) {
            case Constant.ILLNESS_STATE_NAME_HEAVY:
                view.setBackgroundResource(R.drawable.sy_hljb_pg_shape_6);
                view.setTextColor(Color.parseColor("#ffffff"));
                break;
            case Constant.ILLNESS_STATE_NAME_FATAL:
                view.setBackgroundResource(R.drawable.sy_hljb_pg_shape_7);
                view.setTextColor(Color.parseColor("#ffffff"));
                break;
            case Constant.ILLNESS_STATE_NAME_GENERAL:
            default:
                break;
        }
    }

//
//    /**
//     * @param items
//     * @param onItemClick
//     */
//    public void initViews(List<String> items, OnItemClick onItemClick) {
//        initViews( (String[]) items.toArray(), onItemClick);
//    }

    /**
     * 得到手机屏幕的宽度
     *
     * @return
     */
    private int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 得到view控件的宽度
     *
     * @param view
     * @return
     */
    private int getViewWidth(View view) {
        int w = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        int h = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredWidth();
    }
}
