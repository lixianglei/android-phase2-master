package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.NursingEventTempLateLisBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/29.
 */

public class HuanzheZhusuShijianFenleiLvItemAdapter extends BaseAdapter {
    private List<String> mEntities;
    private Context context;
    private LayoutInflater layoutInflater;
    private Map<Integer, Boolean> booleanMap;

    public HuanzheZhusuShijianFenleiLvItemAdapter(Context context, List<String> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        if (entities == null) {
            entities = new ArrayList<>();
        }
        this.mEntities = entities;
        initMap();
    }

    private void initMap() {
        booleanMap = new HashMap<>();
        for (int i = 0; i < mEntities.size(); i++) {
            booleanMap.put(i,false);
        }
    }

    public Map<Integer, Boolean> getBooleanMap() {
        return booleanMap;
    }

    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public String getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.huanzhe_zhusu_shijian_fenlei_lv_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((String) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(String entity, final ViewHolder holder, final int position) {
        holder.mShijianTv.setText(entity);
        if(booleanMap.get(position)){
            holder.mShijianTvLan.setVisibility(View.VISIBLE);
            holder.mIconSelectImgv.setVisibility(View.VISIBLE);
        }else{
            holder.mShijianTvLan.setVisibility(View.GONE);
            holder.mIconSelectImgv.setVisibility(View.GONE);
        }
        holder.mShijianTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mShijianTvLan.getVisibility() == View.VISIBLE) {
                    holder.mShijianTvLan.setVisibility(View.GONE);
                    holder.mIconSelectImgv.setVisibility(View.GONE);
                    booleanMap.put(position,false);
                } else {
                    booleanMap.put(position,true);
                    holder.mShijianTvLan.setVisibility(View.VISIBLE);
                    holder.mIconSelectImgv.setVisibility(View.VISIBLE);
                    Iterator<Integer> iterator = booleanMap.keySet().iterator();
                    while(iterator.hasNext()){
                        Integer next = iterator.next();
                        if(next != position){
                            booleanMap.put(next,false);
                        }
                    }
                }
                notifyDataSetChanged();
            }
        });
    }

    protected class ViewHolder {
        private TextView mShijianTv;
        private TextView mShijianTvLan;
        private ImageView mIconSelectImgv;


        public ViewHolder(View view) {
            mShijianTv = (TextView) view.findViewById(R.id.shijian_tv);
            mShijianTvLan = (TextView) view.findViewById(R.id.shijian_tv_lan);
            mIconSelectImgv = (ImageView) view.findViewById(R.id.icon_select_imgv);

        }
    }
}