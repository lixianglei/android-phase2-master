package com.ge.med.mobile.nursing.ui.adapter;

/**
 * Created by Administrator on 2016/11/11.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;

import java.util.ArrayList;
import java.util.List;

public class JiLuLvItemAdapter extends BaseAdapter {

    private List<String> objects = new ArrayList<String>();

    private Context context;
    private LayoutInflater layoutInflater;

    public JiLuLvItemAdapter(Context context,List<String> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        if(objects == null){
            objects = new ArrayList<>();
        }
       this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public String getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.ji_lu_lv_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((String)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(String object, ViewHolder holder) {
        if(object == null){
            return;
        }
        holder.jiLvBiaoTiTv.setText(object);

        holder.jiLvShouZiTv.setText(object.substring(0,1));
    }

    protected class ViewHolder {
        private TextView jiLvShouZiTv;
        private TextView jiLvBiaoTiTv;

        public ViewHolder(View view) {
            jiLvShouZiTv = (TextView) view.findViewById(R.id.ji_lv_shou_zi_tv);
            jiLvBiaoTiTv = (TextView) view.findViewById(R.id.ji_lv_biao_ti_tv);
        }
    }
}
