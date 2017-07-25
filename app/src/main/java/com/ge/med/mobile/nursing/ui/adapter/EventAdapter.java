package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.forjson.entity.EventsBean;
import com.ge.med.mobile.nursing.utils.DateUtil;

import java.util.List;

/**
 * Created by lisa on 2017/6/30.
 * 事件列表适配器
 */

public class EventAdapter extends BaseAdapter {

    private Context context;
    private List<EventsBean.DataBean> datas;

    public EventAdapter(Context context, List<EventsBean.DataBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_event, null);
            viewHolder = new ViewHolder();
            // 获取控件
            viewHolder.tv_event_name = (TextView) convertView.findViewById(R.id.tv_event_name);
            viewHolder.tv_event_time = (TextView) convertView.findViewById(R.id.tv_event_time);
            // setTag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 给控件赋值
        viewHolder.tv_event_name.setText(datas.get(position).getDefineEventName());
        viewHolder.tv_event_time.setText(DateUtil.longToString(datas.get(position).getEventtime()));

        return convertView;
    }

    class ViewHolder {
        // 事件名称
        TextView tv_event_name;
        // 事件时间
        TextView tv_event_time;
    }
}
