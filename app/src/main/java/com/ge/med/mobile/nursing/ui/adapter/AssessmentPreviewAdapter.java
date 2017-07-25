package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.forjson.entity.ResultBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lisa on 2017/6/14.
 * 评估预览列表
 */

public class AssessmentPreviewAdapter extends BaseAdapter {
    private Context context;
    List<ResultBean> datas = new ArrayList<>();

    public AssessmentPreviewAdapter(Context context, List<ResultBean> resultList) {
        this.context = context;
        this.datas = resultList;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_assessment_preview, null);
            viewHolder = new ViewHolder();
            // 获取控件
            viewHolder.tv_question = (TextView) convertView.findViewById(R.id.tv_question);
            viewHolder.tv_value = (TextView) convertView.findViewById(R.id.tv_value);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 给控件赋值
        viewHolder.tv_question.setText(datas.get(position).getQuestion());
        viewHolder.tv_value.setText(datas.get(position).getValue());

        return convertView;
    }

    class ViewHolder {
        // 问题
        TextView tv_question;
        // 值
        TextView tv_value;
    }
}
