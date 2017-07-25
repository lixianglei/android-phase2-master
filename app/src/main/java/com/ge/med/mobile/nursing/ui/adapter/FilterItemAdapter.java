package com.ge.med.mobile.nursing.ui.adapter;

/**
 * Created by Administrator on 2016/11/18.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.ui.component.DataFilterMenu;

import org.xutils.common.util.LogUtil;

import java.util.List;


public class FilterItemAdapter extends BaseAdapter {

    private  List<DataFilterMenu.FilterItem> mEntities;

    private Context context;
    private LayoutInflater layoutInflater;

    public FilterItemAdapter(Context context, List<DataFilterMenu.FilterItem> entities) {
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
    public DataFilterMenu.FilterItem getItem(int position) {
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
            convertView = layoutInflater.inflate(R.layout.filter_lv_item, parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((DataFilterMenu.FilterItem)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(DataFilterMenu.FilterItem entity, ViewHolder holder,int position) {
        LogUtil.d("initializeViews(entity["
                + (entity==null?"null":entity.getName()+"-"+entity.getDesc()+":"+entity.getResultString())
                + "], position[" + position + "].");
        if (entity == null) {
            holder.mItemNameTextView.setText("empty");
            holder.mItemDescTextView.setVisibility(View.GONE);
            holder.mSortImageView.setVisibility(View.GONE);
        }else{
            holder.mItemNameTextView.setText(entity.getName().trim());
            if (entity.getName() == null || entity.getName().trim().isEmpty()){
                holder.mItemDescTextView.setText("");
            }else {
                holder.mItemNameTextView.setText(entity.getName().trim());
            }

            if (entity.getDesc() == null || entity.getDesc().trim().isEmpty()){
                LogUtil.d("Description is null or empty!");
                holder.mItemDescTextView.setVisibility(View.GONE);
            }else{
                LogUtil.d("Description is not empty!");
                holder.mItemDescTextView.setVisibility(View.VISIBLE);
                holder.mItemDescTextView.setText(entity.getDesc().trim());
            }
            if (entity.getFilterResultType() == DataFilterMenu.FILTER_TYPE_BED_NO){
                holder.mSortImageView.setVisibility(View.VISIBLE);
                if (entity.getResultString() == null || Constant.SORT_BY_ASC.equals(entity.getResultString())){
                    holder.mSortImageView.setImageResource(R.mipmap.dao);
                    entity.setResultString(Constant.SORT_BY_DESC);
                }else{
                    holder.mSortImageView.setImageResource(R.mipmap.zheng);
                    entity.setResultString(Constant.SORT_BY_ASC);
                }

            }else{
                holder.mSortImageView.setVisibility(View.GONE);
            }
        }
    }

    public void setData(List<DataFilterMenu.FilterItem> data){
        LogUtil.d("FilterItemAdapter.setData(" + (data==null?"null":data.size()) + ") calling...");
        if (data != null){
            mEntities = data;
        }
    }

    protected class ViewHolder {
        private TextView mItemNameTextView;
        private TextView mItemDescTextView;
        private ImageView mSortImageView;



        public ViewHolder(View view) {
            mItemNameTextView = (TextView) view.findViewById(R.id.filter_item_name_tv);
            mItemDescTextView = (TextView) view.findViewById(R.id.filter_item_desc_tv);
            mSortImageView = (ImageView) view.findViewById(R.id.filter_sort_image_view);
        }
    }
}
