package com.ge.med.mobile.nursing.ui.adapter;

/**
 * Created by Administrator on 2016/11/18.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class RWyizhuShuaiXuanItemAdapter extends BaseAdapter {
    private Map<String ,Integer> stringIntegerMap;
    private List<String> mEntities;
    private Context context;
    private LayoutInflater layoutInflater;

    public List<String> getmEntities() {
        return mEntities;
    }
    public RWyizhuShuaiXuanItemAdapter(Context context, Map<String ,Integer> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        initEntity(entities);
    }

    public Map<String, Integer> getStringIntegerMap() {
        return stringIntegerMap;
    }

    public void setStringIntegerMap(Map<String, Integer> stringIntegerMap) {
        initEntity(stringIntegerMap);
    }

    private void initEntity(Map<String, Integer> entities){
        stringIntegerMap=entities;
        mEntities = new ArrayList<>();
        if(entities!=null){
            Iterator<Map.Entry<String, Integer>> iterator = entities.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, Integer> next = iterator.next();
                if("全部摆药".equals(next.getKey())){
                    mEntities.add(0, next.getKey());
                }else{
                    mEntities.add( next.getKey());
                }
            }
        }
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
            convertView = layoutInflater.inflate(R.layout.huan_zhe_xuan_ze_lv_item, parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((String)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(String entity, ViewHolder holder,int position) {
        if(entity.length() == 4){
            holder.mHuanZheXuanZeLvTv.setText(entity+"  \t"+stringIntegerMap.get(entity));
        }else{
            holder.mHuanZheXuanZeLvTv.setText(entity+"          \t"+stringIntegerMap.get(entity));
        }
    }

    protected class ViewHolder {
        private TextView mHuanZheXuanZeLvTv;



        public ViewHolder(View view) {
            mHuanZheXuanZeLvTv = (TextView) view.findViewById(R.id.huan_zhe_xuan_ze_lv_tv);
        }
    }
}
