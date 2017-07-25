package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;

import java.util.List;

/**
 * Created by lisa on 2017/7/19.
 * 选择患者适配器
 */

public class ChoosePatientAdapter extends BaseAdapter {

    private Context context;
    private List<String> datas;
    // 标记每个item选中的状态
    private boolean[] checkedMarks;

    public ChoosePatientAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
        checkedMarks = new boolean[datas.size()];
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_choosepatient, null);
            viewHolder = new ViewHolder();
            // 获取控件
            viewHolder.frame_container = (FrameLayout) convertView.findViewById(R.id.frame_container);
            viewHolder.img_grade = (ImageView) convertView.findViewById(R.id.img_grade);
            viewHolder.img_checked = (ImageView) convertView.findViewById(R.id.img_checked);
            viewHolder.tv_sickbednum = (TextView) convertView.findViewById(R.id.tv_sickbednum);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_info = (TextView) convertView.findViewById(R.id.tv_info);
            // setTag
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 给控件赋值
        if (checkedMarks[position]) {
            // 选中
            viewHolder.img_checked.setVisibility(View.VISIBLE);
            viewHolder.frame_container.setBackgroundColor(ContextCompat.getColor(context, R.color.touse559bec));
        } else {
            // 未选中
            viewHolder.img_checked.setVisibility(View.INVISIBLE);
            viewHolder.frame_container.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        }

        return convertView;
    }

    class ViewHolder {
        // 主容器
        FrameLayout frame_container;
        // 患者等级
        ImageView img_grade;
        // 选中时显示的对勾
        ImageView img_checked;
        // 床号
        TextView tv_sickbednum;
        // 患者姓名
        TextView tv_name;
        // 提示信息
        TextView tv_info;
    }

    /**
     * 记录标记
     */
    public void setMarks(int position) {
        // 重置所有标记
        for (int i = 0; i < checkedMarks.length; i++) {
            checkedMarks[i] = false;
        }
        // 将选中的标记为true
        checkedMarks[position] = true;
        // 刷新列表
        notifyDataSetChanged();
    }
}
