package com.ge.med.mobile.nursing.ui.view;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
public class YiZhuXiangQingInclude {
    private LinearLayout mYizhuXiangqingLl;
    private Button mYzZxZhuSheXiangQingBt;
    private ListView mYizhuXiangQingLv;
    private MyBaseActivity myBaseActivity;
    public YiZhuXiangQingInclude(MyBaseActivity myBaseActivity) {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        this.myBaseActivity = myBaseActivity;
        mYizhuXiangqingLl = (LinearLayout) myBaseActivity.findViewById(R.id.yizhu_xiangqing_ll);
        mYzZxZhuSheXiangQingBt = (Button) myBaseActivity.findViewById(R.id.yz_zx_zhu_she_xiang_qing_bt);
        mYizhuXiangQingLv = (ListView) myBaseActivity.findViewById(R.id.yizhu_xiang_qing_lv);
//        YizhuXiangqingLvItemAdapter yizhuXiangqingLvItemAdapter= new YizhuXiangqingLvItemAdapter(myBaseActivity,list);
//        mYizhuXiangQingLv.setAdapter(yizhuXiangqingLvItemAdapter);
    }
    public void showYiZhuXiangQingInclude(){
        mYizhuXiangqingLl.setVisibility(View.VISIBLE);
    }
    public void goneYiZhuXiangQingInclude(){
        mYizhuXiangqingLl.setVisibility(View.GONE);
    }
    public boolean getViewVisiblity(){
        return mYizhuXiangqingLl.getVisibility() == View.VISIBLE ? true:false;
    }
    public View getVisibilityView(){
        return mYizhuXiangqingLl;
    }
}
