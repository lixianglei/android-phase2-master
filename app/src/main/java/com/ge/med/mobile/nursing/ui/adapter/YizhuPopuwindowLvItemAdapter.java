package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;

import java.util.ArrayList;
import java.util.List;


public class YizhuPopuwindowLvItemAdapter extends BaseAdapter {

    private List<YiZhuBean.OrderPharm> mEntities;

    private Context context;
    private YiZhuBean.DataBean entities;
    private LayoutInflater layoutInflater;

    public YizhuPopuwindowLvItemAdapter(Context context, YiZhuBean.DataBean entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        if(entities == null){
            entities = new YiZhuBean.DataBean();
        }
        this.entities = entities;
        if ( entities.getPharmList() != null) {
            this.mEntities = entities.getPharmList();
        }else{
            this.mEntities = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public YiZhuBean.OrderPharm getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.yizhu_popuwindow_lv_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((YiZhuBean.OrderPharm) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(YiZhuBean.OrderPharm entity, ViewHolder holder, int position) {
        holder.mYizhuXiangqingTv1.setText(entity.getPharmName() + " " + entity.getDose() + " " + entity.getUnit());
        holder.mYizhuXiangqingTv2.setText(entity.getFrequency() + " " +entities.getRoutedesc());
    }

    protected class ViewHolder {
        private TextView mYizhuXiangqingTv1;
        private TextView mYizhuXiangqingTv2;


        public ViewHolder(View view) {
            mYizhuXiangqingTv1 = (TextView) view.findViewById(R.id.yizhu_xiangqing_tv1);
            mYizhuXiangqingTv2 = (TextView) view.findViewById(R.id.yizhu_xiangqing_tv2);

        }
    }
}
