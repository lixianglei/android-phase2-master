package com.ge.med.mobile.nursing.ui.component;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.ui.activity.ActivityUtils;
import com.ge.med.mobile.nursing.ui.adapter.YzZxKouFuYaoLvItemAdapter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/31.
 */
public class ChangGuiHuLiYiZhu {
    private List<YiZhuBean.DataBean> entities;
    private final YzZxKouFuYaoLvItemAdapter yzZxKouFuYaoLvItemAdapter;
    private MyBaseActivity mActivity;
    private LayoutInflater layoutInflater;
    private ListView mHuanzheZhusuShijianFenlei;
    private RelativeLayout mLvRl;


    public ChangGuiHuLiYiZhu(MyBaseActivity mActivity, LayoutInflater layoutInflater, LinearLayout linearLayout,
                             List<YiZhuBean.DataBean> entities, Button mYzHdLxYichangBt) {
        this.mActivity = mActivity;
        this.entities = entities;
        this.layoutInflater = layoutInflater;
        View view = layoutInflater.inflate(R.layout.shijian_fenlei_include, null);
        linearLayout.removeAllViewsInLayout();
        linearLayout.addView(view);
        linearLayout.setBackgroundColor(Color.parseColor("#ff888888"));
        mHuanzheZhusuShijianFenlei = (ListView) linearLayout.findViewById(R.id.huanzhe_zhusu_shijian_fenlei);
        mLvRl = (RelativeLayout) linearLayout.findViewById(R.id.lv_rl);
        mLvRl.setBackgroundColor(Color.parseColor("#ff888888"));
        yzZxKouFuYaoLvItemAdapter = new YzZxKouFuYaoLvItemAdapter(mActivity, entities, mYzHdLxYichangBt, Constant.HULI_TYPR_YIZHU_BEIZHU);
        yzZxKouFuYaoLvItemAdapter.setPosList(ActivityUtils.suanPos(entities));
        mHuanzheZhusuShijianFenlei.setAdapter(yzZxKouFuYaoLvItemAdapter);
    }

    public YiZhuBean.DataBean getDataBean() {
        int postion = -1;
        YiZhuBean.DataBean dataBean = null;
        Map<Integer, Boolean> booleanMap = yzZxKouFuYaoLvItemAdapter.getmCBFlag();
        if (booleanMap != null) {
            Iterator<Integer> iterator = booleanMap.keySet().iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (booleanMap.get(next)) {
                    postion = next;
                }
            }
        }
        if (entities != null && postion >= 0 && postion < entities.size()) {
            dataBean = entities.get(postion);
        }
        return dataBean;
    }
}
