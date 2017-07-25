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
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;

import java.util.List;

public class PingGuZjmLvItemAdapter extends BaseAdapter {

    private List<AssessDefine.DataBean> objects;

    private Context context;
    private LayoutInflater layoutInflater;

    public PingGuZjmLvItemAdapter(Context context,List<AssessDefine.DataBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public AssessDefine.DataBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.ping_gu_zjm_lv_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((AssessDefine.DataBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(AssessDefine.DataBean object, ViewHolder holder) {
        holder.jiLvBiaoTiTv.setText(object.getName());
        holder.jiLvShouZiTv.setText(object.getName().substring(0,1));
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
