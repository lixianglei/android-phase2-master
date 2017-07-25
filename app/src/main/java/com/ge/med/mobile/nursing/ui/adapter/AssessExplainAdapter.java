package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;

import java.util.List;

/**
 * Created by lisa on 2017/6/20.
 * 压疮评估说明适配器
 */

public class AssessExplainAdapter extends BaseAdapter {

    private Context context;
    private List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean> datas;

    public AssessExplainAdapter(Context context, List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean> datas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_assessment_explain, null);
            viewHolder = new ViewHolder();
            // 获取控件
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 给控件赋值
        viewHolder.tv_title.setText(datas.get(position).getValue());
        viewHolder.tv_content.setText(datas.get(position).getInstruction());

        return convertView;
    }

    class ViewHolder {
        // 标题
        TextView tv_title;
        // 具体解释
        TextView tv_content;
    }
}
