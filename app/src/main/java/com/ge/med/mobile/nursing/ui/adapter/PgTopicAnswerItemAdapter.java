package com.ge.med.mobile.nursing.ui.adapter;

/**
 * Created by Administrator on 2016/11/16.
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
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.forjson.entity.AssessRecordBean;

import java.util.ArrayList;

public class PgTopicAnswerItemAdapter extends BaseAdapter {

    private AssessDefine.DataBean.AssessTopicDefineListBean mTopicDefine;
    private AssessRecordBean.AssessTopicRecordListBean mTopicRecord;
    private Context context;
    private LayoutInflater layoutInflater;

    public PgTopicAnswerItemAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public void changeTopic(AssessDefine.DataBean.AssessTopicDefineListBean topicDefine
            , AssessRecordBean.AssessTopicRecordListBean topicRecord){
        this.mTopicDefine = topicDefine;
        if (mTopicDefine.getAssessAnswerDefineList() == null)
            topicDefine.setAssessAnswerDefineList(new ArrayList<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean>());
        this.mTopicRecord = topicRecord;
        if (mTopicRecord == null) mTopicRecord = new AssessRecordBean.AssessTopicRecordListBean();
        if (mTopicRecord.getAssessAnswerRecordList() == null)
            mTopicRecord.setAssessAnswerRecordList(new ArrayList<AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean>());
    }


    @Override
    public int getCount() {
        if (mTopicDefine == null) mTopicDefine = new AssessDefine.DataBean.AssessTopicDefineListBean();
        if (mTopicDefine.getAssessAnswerDefineList() == null){
            mTopicDefine.setAssessAnswerDefineList(new ArrayList<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean>());
        }
        return mTopicDefine.getAssessAnswerDefineList().size();
    }

    @Override
    public AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean getItem(int position) {
        return mTopicDefine.getAssessAnswerDefineList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listview_item_textview, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean entity, final ViewHolder holder, final int position) {
        holder.tvpg_item_answer.setText(entity.getValue());
        setAnswerSelected(holder, isAnswerSelected(entity.getId()));
    }
    private boolean isAnswerSelected(int defineId){
        boolean retval = false;
        if (mTopicRecord.getAssessAnswerRecordList() != null && mTopicRecord.getAssessAnswerRecordList().size() > 0){
            for(AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean answerRecord : mTopicRecord.getAssessAnswerRecordList()){
                if (answerRecord.getAnswerDefineId() == defineId){
                    retval = true;
                    break;
                }
            }
        }
        return retval;
    }
    private boolean addRemoveAnswer(int defineId){
        boolean retval = true;
        if (mTopicRecord.getAssessAnswerRecordList() == null)
            mTopicRecord.setAssessAnswerRecordList(new ArrayList<AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean>());

        AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean answerRecord = null;
        if (mTopicRecord.getAssessAnswerRecordList().size() > 0){
            for(int i = mTopicRecord.getAssessAnswerRecordList().size()-1; i>= 0; i--){
                answerRecord = mTopicRecord.getAssessAnswerRecordList().get(i);
                if (answerRecord.getAnswerDefineId() == defineId){
                    retval = false;
                    mTopicRecord.getAssessAnswerRecordList().remove(answerRecord);
                }
            }
        }
        if (Constant.TOPIC_TYPE_SELECT_SINGLE.equals(mTopicDefine.getTopicType())){
            mTopicRecord.getAssessAnswerRecordList().clear();
            notifyDataSetChanged();
        }
        if (retval){
            answerRecord = new AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean();
            answerRecord.setAnswerDefineId(defineId);
            answerRecord.setToprecordid(mTopicRecord.getId());
            mTopicRecord.getAssessAnswerRecordList().add(answerRecord);
        }
        return retval;
    }
    public void clickItem(View view, int position){
        addRemoveAnswer(mTopicDefine.getAssessAnswerDefineList().get(position).getId());
        notifyDataSetChanged();
    }
    private void setAnswerSelected(ViewHolder holder, boolean selected){
        if (selected){
            holder.selected_icon.setVisibility(View.VISIBLE);
            holder.tvpg_item_answer.setBackgroundResource(R.drawable.yz_hd_miao_bian_shape3);
           // holder.tvpg_item_answer.setTextColor(Color.WHITE);
        }else{
            holder.selected_icon.setVisibility(View.INVISIBLE);
            holder.tvpg_item_answer.setBackgroundResource(R.drawable.yz_hd_miao_bian_shape1);
           // holder.tvpg_item_answer.setTextColor(Color.rgb(85,155,236));
        }
    }
    protected class ViewHolder {
        private TextView tvpg_item_answer;
        private ImageView selected_icon;

        public ViewHolder(View view) {
            tvpg_item_answer = (TextView) view.findViewById(R.id.pg_item_answer);
            selected_icon = (ImageView) view.findViewById(R.id.pg_selected_icon);
        }
    }
}
