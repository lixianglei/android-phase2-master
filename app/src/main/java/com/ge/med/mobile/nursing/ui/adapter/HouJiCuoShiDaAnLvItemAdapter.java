package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.AssessMeasureRecords;
import com.ge.med.mobile.nursing.dao.entity.MeasureDefineBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxl on 2017/4/15.
 */

public class HouJiCuoShiDaAnLvItemAdapter  extends BaseAdapter {

    private List<MeasureDefineBean.DataBean.MeasureTopicDefineListBean.MeasureAnswerDefineListBean> objects = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private Map<Integer , Boolean> booleanMap;
    private boolean flag;
    private String userId;
    private String time;
    private  List<AssessMeasureRecords.MeasureTopicRecordListBean.MeasureAnswerRecordListBean> measureAnswerRecordList;

    public void setMeasureAnswerRecordList(List<AssessMeasureRecords.MeasureTopicRecordListBean.MeasureAnswerRecordListBean> measureAnswerRecordList) {
        if(measureAnswerRecordList == null){
            measureAnswerRecordList = new ArrayList<>();
        }
        this.measureAnswerRecordList = measureAnswerRecordList;

    }

    public HouJiCuoShiDaAnLvItemAdapter(Context context
            , List<MeasureDefineBean.DataBean.MeasureTopicDefineListBean.MeasureAnswerDefineListBean> measureAnswerDefineListBeen, boolean flag, String userId, String time) {
        this.flag = flag;
        this.userId = userId;
        this.time = time;
        objects = measureAnswerDefineListBeen;
        if ( objects == null) {
           objects = new ArrayList<>();
        }
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        initMap();
    }

    public Map<Integer, Boolean> getBooleanMap() {
        return booleanMap;
    }

    public void setBooleanMap(Map<Integer, Boolean> booleanMap) {
        this.booleanMap = booleanMap;
    }

    private void initMap() {
        booleanMap = new HashMap<>();

        for(int i = 0 ;i<objects.size() ;i++){
            booleanMap.put(i,false);
        }
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public MeasureDefineBean.DataBean.MeasureTopicDefineListBean.MeasureAnswerDefineListBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.hou_ji_cuo_shi_da_an_lv_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MeasureDefineBean.DataBean.MeasureTopicDefineListBean.MeasureAnswerDefineListBean)getItem(position)
                , (ViewHolder) convertView.getTag(),position);
        return convertView;
    }

    private void initializeViews(MeasureDefineBean.DataBean.MeasureTopicDefineListBean.MeasureAnswerDefineListBean object, final ViewHolder holder, final int position) {
        //TODO implement
        holder.mTv2.setVisibility(View.GONE);
        holder.mTv1.setText(object.getValue());
        holder.mTv1.setEnabled(true);
        if(booleanMap.get(position)){

            holder.mRl.setBackgroundResource(R.drawable.login_edt_shape_lan);
            holder.mImgv1.setImageResource(R.mipmap.icon_select);
        }else{
            holder.mRl.setBackgroundResource(R.drawable.login_edt_shape);
            holder.mImgv1.setImageResource(R.mipmap.icon_complete);
        }
        holder.mTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(booleanMap.get(position)){
                    booleanMap.put(position,false);
                    holder.mRl.setBackgroundResource(R.drawable.login_edt_shape);
                    holder.mImgv1.setImageResource(R.mipmap.icon_complete);
                }else{
                    booleanMap.put(position,true);
                    holder.mRl.setBackgroundResource(R.drawable.login_edt_shape_lan);
                    holder.mImgv1.setImageResource(R.mipmap.icon_select);
                }
            }
        });
        if(flag){
            holder.mImgv1.setImageResource(R.mipmap.icon_complete);
            holder.mRl.setBackgroundResource(R.drawable.ddd);
            holder.mTv2.setVisibility(View.VISIBLE);
            String dateString = null;
            try {
                 dateString = DateUtils.getDateString("yyyy/MM/dd HH:mm", Long.parseLong(time));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            holder.mTv2.setText(dateString + " "+userId);
        }
        if(measureAnswerRecordList!=null && measureAnswerRecordList.size()>0){
            for(AssessMeasureRecords.MeasureTopicRecordListBean.MeasureAnswerRecordListBean measureAnswerRecordListBean:measureAnswerRecordList){
                if(measureAnswerRecordListBean.getMeasureAnswerDefineId() == object.getId()){
                    holder.mTv1.setEnabled(false);
                    holder.mRl.setBackgroundResource(R.drawable.login_edt_shape_lan);
                    holder.mImgv1.setImageResource(R.mipmap.icon_select);
                }
            }
        }
    }

    protected class ViewHolder {
        private TextView mTv1;
        private ImageView mImgv1;
        private RelativeLayout mRl;
        private TextView mTv2;

        public ViewHolder(View view) {
            mTv1 = (TextView) view.findViewById(R.id.tv_1);
            mImgv1 = (ImageView) view.findViewById(R.id.imgv_1);
            mRl = (RelativeLayout) view.findViewById(R.id.rl);
            mTv2 = (TextView) view.findViewById(R.id.tv_2);

        }
    }
}
