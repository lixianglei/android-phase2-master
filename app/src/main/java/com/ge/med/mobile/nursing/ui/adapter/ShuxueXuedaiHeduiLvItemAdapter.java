package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/23.
 */
public class ShuxueXuedaiHeduiLvItemAdapter extends BaseAdapter {

    private List<YiZhuBean.DataBean.OrderBloodPropertyBean> mEntities;
    private Context context;
    private LayoutInflater layoutInflater;
    private Map<Integer, Boolean> jiBenMap;
    private Map<Integer, Boolean> xueDaiMap;
    private Map<Integer, Boolean> chanPinMap;
    private Button mButton;


    public ShuxueXuedaiHeduiLvItemAdapter(Context context, List<YiZhuBean.DataBean.OrderBloodPropertyBean> entities, Button button) {
        this.context = context;
        mButton = button;
        this.layoutInflater = LayoutInflater.from(context);
        if(entities == null){
            entities = new ArrayList<>();
        }
        this.mEntities = entities;
        initMap();
    }
    public Map<Integer, Boolean> getChanPinMap() {
        return chanPinMap;
    }

    public Map<Integer, Boolean> getXueDaiMap() {
        return xueDaiMap;
    }

    private void initMap() {
        if (jiBenMap == null) {
            jiBenMap = new HashMap<>();
        }
        if (xueDaiMap == null) {
            xueDaiMap = new HashMap<>();
        }
        if (chanPinMap == null) {
            chanPinMap = new HashMap<>();
        }
        if (mEntities != null && mEntities.size() > 0) {
            for (int i = 0; i < mEntities.size(); i++) {
                jiBenMap.put(i, false);
                xueDaiMap.put(i, false);
                chanPinMap.put(i, false);
            }
        }

    }

    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public YiZhuBean.DataBean.OrderBloodPropertyBean getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.shuxue_xuedai_hedui_lv_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((YiZhuBean.DataBean.OrderBloodPropertyBean) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(YiZhuBean.DataBean.OrderBloodPropertyBean entity, final ViewHolder holder, final int position) {
        holder.shuxueXuedaiHeduiDaishuTv.setText("第"+(position+1)+"袋/共"+mEntities.size()+"袋");
        String dateString = null;
        try {
            dateString = DateUtils.getDateString("yyyy-MM-dd HH:mm", entity.getLeavestoragetime());
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
        holder.shuxueXuedaiHeduiChukushijianTv.setText("出库时间："+dateString);
        holder.shuxueXuedaiHeduiXuedaixuexingTv.setText("血袋血型："+entity.getBloodbagbloodtype());
        holder.shuxueXuedaiHeduiXuedairhTv.setText("血袋RH："+entity.getBloodbagbloodrh());
        holder.shuxueXuedaiHeduiXuedaiNameTv.setText("血袋类型名称："+entity.getBloodbagtypename());
        holder.shuxueXuedaiHeduiXueliangTv.setText("血量："+entity.getBloodvolume());
        holder.shuxueXuedaiHeduiCb2.setText("血袋号："+entity.getBloodbagno());
        holder.shuxueXuedaiHeduiCb3.setText("产品号："+entity.getProductno());
        holder.shuxueXuedaiHeduiCb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.shuxueXuedaiHeduiCb1.isChecked()) {
                    jiBenMap.put(position, true);
                    holder.yizhuXiangqingLvImgv1.setImageResource(R.mipmap.icon_select);
                    btEnable();
                }else{
                    jiBenMap.put(position, false);
                    holder.yizhuXiangqingLvImgv1.setImageResource(R.mipmap.icon_complete);
                    btEnable();
                }
            }
        });

        holder.shuxueXuedaiHeduiCb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.shuxueXuedaiHeduiCb2.isChecked()) {
                    xueDaiMap.put(position, true);
                    holder.yizhuXiangqingLvImgv2.setImageResource(R.mipmap.icon_select);
                    btEnable();
                }else{
                    xueDaiMap.put(position, false);
                    holder.yizhuXiangqingLvImgv2.setImageResource(R.mipmap.icon_complete);
                    btEnable();
                }
            }
        });
        holder.shuxueXuedaiHeduiCb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.shuxueXuedaiHeduiCb3.isChecked()) {
                    chanPinMap.put(position, true);
                    holder.yizhuXiangqingLvImgv3.setImageResource(R.mipmap.icon_select);
                    btEnable();
                }else{
                    chanPinMap.put(position, false);
                    holder.yizhuXiangqingLvImgv3.setImageResource(R.mipmap.icon_complete);
                    btEnable();
                }
            }
        });

        if(jiBenMap.get(position)){
            holder.yizhuXiangqingLvImgv1.setImageResource(R.mipmap.icon_select);
        }else{
            holder.yizhuXiangqingLvImgv1.setImageResource(R.mipmap.icon_complete);
        }
        if(xueDaiMap.get(position)){
            holder.yizhuXiangqingLvImgv2.setImageResource(R.mipmap.icon_select);
        }else{
            holder.yizhuXiangqingLvImgv2.setImageResource(R.mipmap.icon_complete);
        }
        if(chanPinMap.get(position)){
            holder.yizhuXiangqingLvImgv3.setImageResource(R.mipmap.icon_select);
        }else{
            holder.yizhuXiangqingLvImgv3.setImageResource(R.mipmap.icon_complete);
        }

        holder.shuxueXuedaiHeduiCb1.setChecked(jiBenMap.get(position));
        holder.shuxueXuedaiHeduiCb2.setChecked(xueDaiMap.get(position));
        holder.shuxueXuedaiHeduiCb3.setChecked(chanPinMap.get(position));
    }

    public void btEnable(){
        boolean flag = true;
        Iterator<Integer> iterator1 = jiBenMap.keySet().iterator();
        while (iterator1.hasNext()){
            if(!jiBenMap.get(iterator1.next())){
                flag = false;
                break;
            }
        }
        Iterator<Integer> iterator2 = xueDaiMap.keySet().iterator();
        while (iterator2.hasNext()){
            if(!xueDaiMap.get(iterator2.next())){
                flag = false;
                break;
            }
        }
        Iterator<Integer> iterator3 = chanPinMap.keySet().iterator();
        while (iterator3.hasNext()){
            if(!chanPinMap.get(iterator3.next())){
                flag = false;
                break;
            }
        }
        if(flag){
            mButton.setEnabled(true);
            mButton.setBackgroundResource(R.drawable.ti_zheng_duo_ren_bt_selater1);
        }else{
            mButton.setEnabled(false);
            mButton.setBackgroundColor(Color.parseColor("#ffdddddd"));
        }
    }
    protected class ViewHolder {
        private TextView shuxueXuedaiHeduiDaishuTv;
        private CheckBox shuxueXuedaiHeduiCb1;
        private TextView shuxueXuedaiHeduiChukushijianTv;
        private TextView shuxueXuedaiHeduiXuedaixuexingTv;
        private TextView shuxueXuedaiHeduiXuedairhTv;
        private TextView shuxueXuedaiHeduiXuedaiNameTv;
        private TextView shuxueXuedaiHeduiXueliangTv;
        private ImageView yizhuXiangqingLvImgv1;
        private CheckBox shuxueXuedaiHeduiCb2;
        private ImageView yizhuXiangqingLvImgv2;
        private CheckBox shuxueXuedaiHeduiCb3;
        private ImageView yizhuXiangqingLvImgv3;

        public ViewHolder(View view) {
            shuxueXuedaiHeduiDaishuTv = (TextView) view.findViewById(R.id.shuxue_xuedai_hedui_daishu_tv);
            shuxueXuedaiHeduiCb1 = (CheckBox) view.findViewById(R.id.shuxue_xuedai_hedui_cb1);
            shuxueXuedaiHeduiChukushijianTv = (TextView) view.findViewById(R.id.shuxue_xuedai_hedui_chukushijian_tv);
            shuxueXuedaiHeduiXuedaixuexingTv = (TextView) view.findViewById(R.id.shuxue_xuedai_hedui_xuedaixuexing_tv);
            shuxueXuedaiHeduiXuedairhTv = (TextView) view.findViewById(R.id.shuxue_xuedai_hedui_xuedairh_tv);
            shuxueXuedaiHeduiXuedaiNameTv = (TextView) view.findViewById(R.id.shuxue_xuedai_hedui_xuedai_name_tv);
            shuxueXuedaiHeduiXueliangTv = (TextView) view.findViewById(R.id.shuxue_xuedai_hedui_xueliang_tv);
            yizhuXiangqingLvImgv1 = (ImageView) view.findViewById(R.id.yizhu_xiangqing_lv_imgv1);
            shuxueXuedaiHeduiCb2 = (CheckBox) view.findViewById(R.id.shuxue_xuedai_hedui_cb2);
            yizhuXiangqingLvImgv2 = (ImageView) view.findViewById(R.id.yizhu_xiangqing_lv_imgv2);
            shuxueXuedaiHeduiCb3 = (CheckBox) view.findViewById(R.id.shuxue_xuedai_hedui_cb3);
            yizhuXiangqingLvImgv3 = (ImageView) view.findViewById(R.id.yizhu_xiangqing_lv_imgv3);
        }
    }

}
