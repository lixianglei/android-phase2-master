package com.ge.med.mobile.nursing.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by xxl on 2017/2/24.
 */

public class JiaobanChuruliangListItemAdapter extends BaseAdapter {

    private List<String> mEntities;
    private Map<String, String> mEntitiesMap;

    private Context context;
    private LayoutInflater layoutInflater;

    public JiaobanChuruliangListItemAdapter(Context context, Map<String, String> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        if (entities == null) {
            entities = new HashMap<>();
        }
        mEntitiesMap = entities;
        Set<String> strings = mEntitiesMap.keySet();
        this.mEntities = new ArrayList<>();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if(next!=null){
                this.mEntities.add(next);
            }
        }
    }

    @Override
    public int getCount() {
        return mEntities.size() / 2 + (mEntities.size() % 2);
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
            convertView = layoutInflater.inflate(R.layout.jiaoban_churuliang_list_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }

        initializeViews((String) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(String entity, ViewHolder holder, int position) {
        holder.position1 = position * 2;
        holder.position2 = position * 2 + 1;
        if (holder.position1 < mEntities.size()) {
            holder.vitalNameTv1.setText(mEntities.get(holder.position1));
            holder.vitalValueTv1.setText(mEntitiesMap.get(mEntities.get(holder.position1)));
        }
        if (holder.position2 < mEntities.size()) {
            holder.vitalNameTv2.setText(mEntities.get(holder.position2));
            holder.vitalValueTv2.setText(mEntitiesMap.get(mEntities.get(holder.position2)));
        }
    }

    protected class ViewHolder {
        private TextView vitalNameTv1;
        private TextView vitalValueTv1;
        private TextView vitalNameTv2;
        private TextView vitalValueTv2;
        private int position1;
        private int position2;

        public ViewHolder(View view) {
            vitalNameTv1 = (TextView) view.findViewById(R.id.vital_name_tv_1);
            vitalValueTv1 = (TextView) view.findViewById(R.id.vital_value_tv_1);
            vitalNameTv2 = (TextView) view.findViewById(R.id.vital_name_tv_2);
            vitalValueTv2 = (TextView) view.findViewById(R.id.vital_value_tv_2);

        }
    }
}
