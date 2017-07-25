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
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;

import java.util.List;


public class HuanZheXuanZeLvItemAdapter extends BaseAdapter {

    private  List<HuanZheLieBiaoBean.DataBean> mEntities;

    private Context context;
    private LayoutInflater layoutInflater;

    public HuanZheXuanZeLvItemAdapter(Context context, List<HuanZheLieBiaoBean.DataBean> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities=entities;

    }

    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public HuanZheLieBiaoBean.DataBean getItem(int position) {
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
        initializeViews((HuanZheLieBiaoBean.DataBean)getItem(position), (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(HuanZheLieBiaoBean.DataBean entity, ViewHolder holder,int position) {
                holder.mHuanZheXuanZeLvTv.setText(entity.getBedno()+"床    "+entity.getName());
    }

    protected class ViewHolder {
        private TextView mHuanZheXuanZeLvTv;



        public ViewHolder(View view) {
            mHuanZheXuanZeLvTv = (TextView) view.findViewById(R.id.huan_zhe_xuan_ze_lv_tv);
        }
    }
}
