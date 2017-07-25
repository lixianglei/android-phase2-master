package com.ge.med.mobile.nursing.ui.component;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.NursingEventTempLateLisBean;
import com.ge.med.mobile.nursing.dao.impl.NursingEventTempLateDispose;
import com.ge.med.mobile.nursing.ui.adapter.YiZhuBeiZhuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */
public class ChangGuiHuLiBingQingZhiLiang {

    private List<NursingEventTempLateLisBean.DataBean> nursingEventTempLateList;
    private YiZhuBeiZhuAdapter yiZhuBeiZhuAdapter;
    private MyBaseActivity mActivity;
    private LayoutInflater layoutInflater;
    private ListView mHuanzheZhusuShijianFenlei;
    private RelativeLayout mLvRl;
    private ArrayList<Object> arrayList = new ArrayList<>();
    private NursingEventTempLateLisBean.DataBean dataBean;


    public ChangGuiHuLiBingQingZhiLiang(MyBaseActivity mActivity, LayoutInflater layoutInflater,
                                        LinearLayout linearLayout, NursingEventTempLateLisBean.DataBean dataBean) {
        this.mActivity = mActivity;
        this.dataBean = dataBean;
        this.layoutInflater = layoutInflater;
        View view = layoutInflater.inflate(R.layout.shijian_fenlei_include, null);
        linearLayout.removeAllViewsInLayout();
        linearLayout.addView(view);
        mHuanzheZhusuShijianFenlei = (ListView) linearLayout.findViewById(R.id.huanzhe_zhusu_shijian_fenlei);
        nursingEventTempLateList = new NursingEventTempLateDispose().findNursingEventTempLateList();
        mLvRl = (RelativeLayout) linearLayout.findViewById(R.id.lv_rl);
        if (dataBean != null && dataBean.getNursingEventTemplateDefineList() != null && dataBean.getNursingEventTemplateDefineList().size() > 0) {
            for (NursingEventTempLateLisBean.DataBean.NursingEventTemplateDefineListBean n:dataBean.getNursingEventTemplateDefineList()) {
                    if(n!=null){
                        arrayList.add(n);
                    }
            }
        }
        arrayList.add("edt");
        yiZhuBeiZhuAdapter = new YiZhuBeiZhuAdapter(mActivity, arrayList);
        mHuanzheZhusuShijianFenlei.setAdapter(yiZhuBeiZhuAdapter);
    }
    public String getNot(){
        return yiZhuBeiZhuAdapter.getEdt();
    }
}
