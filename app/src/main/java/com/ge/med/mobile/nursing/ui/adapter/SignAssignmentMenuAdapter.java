package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;

import java.util.List;

/**
 * Created by lisa on 2017/7/18.
 * 体征任务界面中的右侧菜单列表适配器
 */

public class SignAssignmentMenuAdapter extends BaseAdapter {
    private Context context;
    private List<String> datas;

    public SignAssignmentMenuAdapter(Context context, List<String> datas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_signassignment_menu, null);
            viewHolder = new ViewHolder();
            // 获取控件
            viewHolder.tv_menu_item = (TextView) convertView.findViewById(R.id.tv_menu_item);
            // setTag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 给控件赋值
        viewHolder.tv_menu_item.setText(datas.get(position));

        return convertView;
    }

    class ViewHolder {
        // 菜单条目
        TextView tv_menu_item;
    }
}
