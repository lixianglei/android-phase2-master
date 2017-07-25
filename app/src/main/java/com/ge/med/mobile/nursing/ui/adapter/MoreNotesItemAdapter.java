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

import org.xutils.common.util.LogUtil;

import java.util.List;


public class MoreNotesItemAdapter extends BaseAdapter {

    private  List<String> mEntities;
    private String selectedItem;

    private Context context;
    private LayoutInflater layoutInflater;

    public MoreNotesItemAdapter(Context context, List<String> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities=entities;

    }

    @Override
    public int getCount() {
        if (mEntities==null) return 0;
        return mEntities.size();
    }

    @Override
    public String getItem(int position) {
        if (mEntities == null || mEntities.size() <= position) return null;
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.more_notes_listview_item, parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((String)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(String entity, ViewHolder holder,int position) {
        LogUtil.d("initializeViews(entity[" + (entity==null?"null":entity)+ "].");
        if (entity != null && holder.mItem != null) {
            holder.mItem.setText(entity);
            if (selectedItem != null && entity.trim().equals(selectedItem.trim())){
                holder.mItem.setBackgroundResource(R.drawable.item_selected_bg);
            }else{
                holder.mItem.setBackgroundResource(R.drawable.ddd);

            }





        }
    }

    public void setData(List<String> data){
        LogUtil.d("FilterItemAdapter.setData(" + (data==null?"null":data.size()) + ") calling...");
        if (data != null){
            mEntities = data;
        }
    }

    protected class ViewHolder {
        private TextView mItem;



        public ViewHolder(View view) {
            mItem = (TextView) view.findViewById(R.id.more_notes_item_id);
        }
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }
}
