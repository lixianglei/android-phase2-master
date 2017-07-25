package com.ge.med.mobile.nursing.ui.component;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.NursingEventTempLateLisBean;
import com.ge.med.mobile.nursing.dao.entity.XinjianJiaoBanBean;
import com.ge.med.mobile.nursing.dao.impl.NursingEventTempLateDispose;
import com.ge.med.mobile.nursing.ui.adapter.HuanzheZhusuShijianFenleiLvItemAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/29.
 * 常规护理记录事件分类
 */
public class ShiJianFenLei {
    private List<NursingEventTempLateLisBean.DataBean> nursingEventTempLateList;
    private HuanzheZhusuShijianFenleiLvItemAdapter huanzheZhusuShijianFenleiLvItemAdapter;
    private MyBaseActivity mActivity;
    private LayoutInflater layoutInflater;
    private ListView mHuanzheZhusuShijianFenlei;
private List<XinjianJiaoBanBean.DataBean.EventListBean> list;

    public ShiJianFenLei(MyBaseActivity mActivity, LayoutInflater layoutInflater, LinearLayout linearLayout) {
        this.mActivity = mActivity;
        this.layoutInflater = layoutInflater;
        View view = layoutInflater.inflate(R.layout.shijian_fenlei_include, null);
        linearLayout.removeAllViewsInLayout();
        linearLayout.addView(view);
        mHuanzheZhusuShijianFenlei = (ListView) linearLayout.findViewById(R.id.huanzhe_zhusu_shijian_fenlei);
        nursingEventTempLateList = new NursingEventTempLateDispose().findNursingEventTempLateList();
        List<String> s = new ArrayList<>();
        if (nursingEventTempLateList != null && nursingEventTempLateList.size() > 0) {
            for (NursingEventTempLateLisBean.DataBean d : nursingEventTempLateList) {
                if(d == null){
                    d = new NursingEventTempLateLisBean.DataBean();
                }
                s.add(d.getEventname());
            }
        }

        huanzheZhusuShijianFenleiLvItemAdapter = new HuanzheZhusuShijianFenleiLvItemAdapter(mActivity, s);
        mHuanzheZhusuShijianFenlei.setAdapter(huanzheZhusuShijianFenleiLvItemAdapter);

    }

    public ShiJianFenLei(MyBaseActivity mActivity, LayoutInflater layoutInflater, LinearLayout linearLayout, List<XinjianJiaoBanBean.DataBean.EventListBean> list) {
        this.mActivity = mActivity;
        this.list = list;
        this.layoutInflater = layoutInflater;
        View view = layoutInflater.inflate(R.layout.shijian_fenlei_include, null);
        linearLayout.removeAllViewsInLayout();
        linearLayout.addView(view);
        mHuanzheZhusuShijianFenlei = (ListView) linearLayout.findViewById(R.id.huanzhe_zhusu_shijian_fenlei);
        List<String> s = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (XinjianJiaoBanBean.DataBean.EventListBean d : list) {
                if(d == null){
                    d = new XinjianJiaoBanBean.DataBean.EventListBean();
                }
                s.add(d.getTemplatename());
            }
        }
        huanzheZhusuShijianFenleiLvItemAdapter = new HuanzheZhusuShijianFenleiLvItemAdapter(mActivity,s);
        mHuanzheZhusuShijianFenlei.setAdapter(huanzheZhusuShijianFenleiLvItemAdapter);

    }
  public String  getEdt(){
      int postion = -1;
      XinjianJiaoBanBean.DataBean.EventListBean dataBean = null;
      Map<Integer, Boolean> booleanMap = huanzheZhusuShijianFenleiLvItemAdapter.getBooleanMap();
      if (booleanMap != null) {
          Iterator<Integer> iterator = booleanMap.keySet().iterator();
          while (iterator.hasNext()) {
              Integer next = iterator.next();
              if (booleanMap.get(next)) {
                  postion = next;
              }
          }
      }
      if (postion >= 0 && postion < list.size()) {
          dataBean = list.get(postion);
      }

      return dataBean .getTemplatecontent();
    }
    public int getTemplateId(){
        int postion = -1;
        XinjianJiaoBanBean.DataBean.EventListBean dataBean = null;
        Map<Integer, Boolean> booleanMap = huanzheZhusuShijianFenleiLvItemAdapter.getBooleanMap();
        if (booleanMap != null) {
            Iterator<Integer> iterator = booleanMap.keySet().iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (booleanMap.get(next)) {
                    postion = next;
                }
            }
        }
        if (postion >= 0 && postion < list.size()) {
            dataBean = list.get(postion);
        }

        return dataBean .getId();
    }
    public NursingEventTempLateLisBean.DataBean getDataBean() {
        int postion = -1;
        NursingEventTempLateLisBean.DataBean dataBean = null;
        Map<Integer, Boolean> booleanMap = huanzheZhusuShijianFenleiLvItemAdapter.getBooleanMap();
        if (booleanMap != null) {
            Iterator<Integer> iterator = booleanMap.keySet().iterator();
            while (iterator.hasNext()) {
                Integer next = iterator.next();
                if (booleanMap.get(next)) {
                    postion = next;
                }
            }
        }
        if (postion >= 0 && postion < nursingEventTempLateList.size()) {
            dataBean = nursingEventTempLateList.get(postion);
        }
        return dataBean;
    }
}
