package com.ge.med.mobile.nursing.ui.logic;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.ui.activity.YZ_HD_RenActivity;
import com.ge.med.mobile.nursing.ui.adapter.RWyizhuShuaiXuanItemAdapter;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/20.
 */
public class RW_BaiYao {
    private Map<String, Integer> shaiXuanMap;
    private RWyizhuShuaiXuanItemAdapter huanZheXuanZeLvItemAdapter;
    private List<YiZhuBean.DataBean> mDataBean;
    private List<YiZhuBean.DataBean> DataBeans;
    private YZ_HD_RenActivity myBaseActivity;
    private Button mYiChuangBt, mPiLiangBt,mYzHdRenFanhuiBt;
    private String mRWLeiXing;

    public RW_BaiYao(Button mYzHdRenFanhuiBt, Button mYiChuangBt, Button mPiLiangBt
            , ListView mHuanZheXuanZeLv, MyBaseActivity mActivitySelf,String mRWLeiXing) {
        myBaseActivity = (YZ_HD_RenActivity) mActivitySelf;
        this .mYiChuangBt =mYiChuangBt;
        this .mRWLeiXing =mRWLeiXing;
        this.mPiLiangBt=mPiLiangBt;
        this.mYzHdRenFanhuiBt =mYzHdRenFanhuiBt;
        mYiChuangBt.setVisibility(View.GONE);
        mYzHdRenFanhuiBt.setVisibility(View.GONE);
        mPiLiangBt.setText("批量操作");
        if(Constant.BUNDLE_KEY_VALUE_BAIYAO.equals(mRWLeiXing)){
            initListView(mHuanZheXuanZeLv, mActivitySelf);
        }
        mYzHdRenFanhuiBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBaseActivity.getYzHdRenLvItemAdapter().setPiLiang(false);
                myBaseActivity.setPiLiang(false);
                RW_BaiYao.this.mYiChuangBt.setVisibility(View.GONE);
                RW_BaiYao.this.mYzHdRenFanhuiBt.setVisibility(View.GONE);
                RW_BaiYao.this. mPiLiangBt.setText("批量操作");
                myBaseActivity.getYzHdRenLvItemAdapter().notifyDataSetChanged();
            }
        });
    }
    public void initPiLiang(){
        myBaseActivity.getYzHdRenLvItemAdapter().setPiLiang(true);
        myBaseActivity.setPiLiang(true);
        mYiChuangBt.setVisibility(View.VISIBLE);
        mYzHdRenFanhuiBt.setVisibility(View.VISIBLE);
        if(Constant.BUNDLE_KEY_VALUE_BAIYAO.equals(mRWLeiXing)){
            mPiLiangBt.setText("确认摆药");
        }else{
            mPiLiangBt.setText("确认配液");
        }

    }
    public void setDateBean(List<YiZhuBean.DataBean> dateBean) {
        mDataBean = dateBean;
        shaiXuanMap = getMap(dateBean);
        huanZheXuanZeLvItemAdapter.setStringIntegerMap(shaiXuanMap);
        huanZheXuanZeLvItemAdapter.notifyDataSetChanged();
    }

    private void initListView(ListView mHuanZheXuanZeLv, MyBaseActivity mActivitySelf) {
        shaiXuanMap = getMap(null);
        huanZheXuanZeLvItemAdapter = new RWyizhuShuaiXuanItemAdapter(mActivitySelf, shaiXuanMap);
        mHuanZheXuanZeLv.setAdapter(huanZheXuanZeLvItemAdapter);
        //list 列表条目点击监听
        mHuanZheXuanZeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (huanZheXuanZeLvItemAdapter.getmEntities().get(position)) {
                    case "全部摆药":
                        shaiXuan("全部摆药");
                        break;
                    case "输液":
                        shaiXuan(Constant.TYPE_YZ_SHUYE);
                        break;
                    case "口服":
                        shaiXuan(Constant.TYPE_YZ_KOUFU);
                        break;
                    case "肌肉注射":
                        shaiXuan(Constant.TYPE_YZ_JIROUZHUSHE);
                        break;
                    case "静脉注射":
                        shaiXuan(Constant.TYPE_YZ_JINGMAIZHUSHE);
                        break;
                    case "皮下注射":
                        shaiXuan(Constant.TYPE_YZ_PIXIAZHUSHE);
                        break;
                    case "皮试":
                        shaiXuan(Constant.TYPE_YZ_PISHI);
                        break;
                }

            }
        });
    }

    private void shaiXuan(String type) {
            DataBeans = new ArrayList<>();
        if (mDataBean != null && type != null) {
            if ("全部摆药".equals(type)) {
                DataBeans = mDataBean;
            } else {
                for (YiZhuBean.DataBean yiZhuBean : mDataBean) {
                    if (type.equals(yiZhuBean.getOrdertype())) {
                        DataBeans.add(yiZhuBean);
                    }
                }
            }
        }
        try {
            myBaseActivity.getmShaiXuanIncludeLl().setVisibility(View.GONE);
            myBaseActivity.getYzHdRenLvItemAdapter().setmEntities(DataBeans);
            myBaseActivity.getYzHdRenLvItemAdapter().notifyDataSetChanged();
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
    }

    //获取筛选统计Map
    private Map<String, Integer> getMap(List<YiZhuBean.DataBean> yiZhuBeans) {
        if (shaiXuanMap == null) {
            shaiXuanMap = new HashMap<>();
        }
        int quanbu = 0;
        int shuye = 0;
        int koufu = 0;
        int jiRouZhushe = 0;
        int jingMaiZhushe = 0;
        int piXiaZhushe = 0;
        int piShi = 0;
        if (yiZhuBeans != null) {
            quanbu = yiZhuBeans.size();
            for (YiZhuBean.DataBean yiZhuBean : yiZhuBeans) {
                switch (yiZhuBean.getOrdertype()) {
                    case Constant.TYPE_YZ_SHUYE:
                        shuye++;
                        break;
                    case Constant.TYPE_YZ_KOUFU:
                        koufu++;
                        break;
                    case Constant.TYPE_YZ_JIROUZHUSHE:
                        jiRouZhushe++;
                        break;
                    case Constant.TYPE_YZ_JINGMAIZHUSHE:
                        jingMaiZhushe++;
                        break;
                    case Constant.TYPE_YZ_PIXIAZHUSHE:
                        piXiaZhushe++;
                        break;
                    case Constant.TYPE_YZ_PISHI:
                        piShi++;
                        break;

                }
            }
        }
        shaiXuanMap.put("全部摆药", quanbu);
        shaiXuanMap.put("肌肉注射", jiRouZhushe);
        shaiXuanMap.put("静脉注射", jingMaiZhushe);
        shaiXuanMap.put("皮下注射", piXiaZhushe);
        shaiXuanMap.put("皮试", piShi);
        shaiXuanMap.put("输液", shuye);
        shaiXuanMap.put("口服", koufu);
        return shaiXuanMap;
    }


}
