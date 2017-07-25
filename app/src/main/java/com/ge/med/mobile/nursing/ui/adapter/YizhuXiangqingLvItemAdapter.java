package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/20.
 */

public class YizhuXiangqingLvItemAdapter extends BaseAdapter {
    private List<YiZhuBean.OrderPharm> mEntities;
    private YiZhuBean.DataBean mEntitie;
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Integer> pharmIds;
    private Map<Integer ,Boolean> map;


    public YizhuXiangqingLvItemAdapter(Context context, YiZhuBean.DataBean entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        if (entities != null) {
            mEntitie = entities;
            this.mEntities = entities.getPharmList();
        }
        initMap();
    }

    private void initMap() {
        map = new HashMap<>();
        if(mEntities!=null ){
            for(int i = 0;i<mEntities.size();i++){
                map.put(i,false);
            }

        }
    }

    public List<Integer> getPharmIds() {
        return pharmIds;
    }

    public void setPharmIds(List<Integer> pharmIds) {
        this.pharmIds = pharmIds;
    }

    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public YiZhuBean.OrderPharm getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.yizhu_xiangqing_lv_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((YiZhuBean.OrderPharm) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(final YiZhuBean.OrderPharm entity, final ViewHolder holder, final int position) {
        holder.yizhuXiangqingLvTv1.setText(entity.getPharmName() + " " + entity.getDose() + " " + entity.getUnit());
        holder.yizhuXiangqingLvTv2.setText(entity.getFrequency() + " " + mEntitie.getRoutedesc());
        holder.mYizhuXiangqingLvBgtv2.setVisibility(View.GONE);
        if (!Constant.YZ_TYPE_DAIBAIYAO.equals(mEntitie.getOrderststus())) {
            holder.mYizhuXiangqingLvImgv.setVisibility(View.GONE);
        }
        if (mEntitie != null && mEntitie.getDoctorOrders() != null && Constant.YZ_TYPE_DAIBAIYAO.equals(mEntitie.getOrderststus())
                && mEntitie.getDoctorOrders().get(0) != null && mEntitie.getDoctorOrders().get(0).getOrderExecuteRecords() != null
                && mEntitie.getDoctorOrders().get(0).getOrderExecuteRecords().size() > 0) {
            for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean recordsBean : mEntitie.getDoctorOrders().get(0).getOrderExecuteRecords()) {
                if (Constant.YZ_EXECUTE_JOBTYPE_BAIYAO.equals(recordsBean.getJobtype())) {
                    if (recordsBean.getPharmId() != null) {
                        if (entity.getPharmId() == recordsBean.getPharmId()) {
                            holder.mYizhuXiangqingLvBgtv2.setVisibility(View.VISIBLE);
                            holder.mYizhuXiangqingLvImgv.setImageResource(R.mipmap.icon_select);
                            map.put(position,true);
                            holder.mYizhuXiangqingLvBgtv1.setEnabled(false);
                            holder.mYizhuXiangqingLvBgtv2.setEnabled(false);
                            holder.mYizhuXiangqingLvBgtv1.setClickable(false);
                            holder.mYizhuXiangqingLvBgtv2.setClickable(false);
                        }
                    }
                }
            }
        }
        holder.mYizhuXiangqingLvBgtv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constant.YZ_TYPE_DAIBAIYAO.equals(mEntitie.getOrderststus())) {
                    holder.mYizhuXiangqingLvBgtv2.setVisibility(View.VISIBLE);
                    holder.mYizhuXiangqingLvImgv.setImageResource(R.mipmap.icon_select);
                    map.put(position,true);
                    if (pharmIds == null) {
                        pharmIds = new ArrayList<Integer>();
                    }
                    pharmIds.add(entity.getPharmId());
                }
            }
        });
        holder.mYizhuXiangqingLvBgtv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constant.YZ_TYPE_DAIBAIYAO.equals(mEntitie.getOrderststus())) {
                    holder.mYizhuXiangqingLvBgtv2.setVisibility(View.GONE);
                    holder.mYizhuXiangqingLvImgv.setImageResource(R.mipmap.icon_complete);
                    map.put(position,false);
                    if (pharmIds == null) {
                        pharmIds = new ArrayList<Integer>();
                    }
                    if (pharmIds.size() > 0) {
                        for (int i = 0; i < pharmIds.size(); i++) {
                            if (pharmIds.get(i) == entity.getPharmId()) {
                                pharmIds.remove(i);
                                break;
                            }
                        }
                    }

                }
            }
        });
        if(map.get(position)){
            holder.mYizhuXiangqingLvBgtv2.setVisibility(View.VISIBLE);
            holder.mYizhuXiangqingLvImgv.setImageResource(R.mipmap.icon_select);
        }else{
            holder.mYizhuXiangqingLvBgtv2.setVisibility(View.GONE);
            holder.mYizhuXiangqingLvImgv.setImageResource(R.mipmap.icon_complete);
        }

    }

    protected class ViewHolder {
        private TextView yizhuXiangqingLvTv1;
        private TextView yizhuXiangqingLvTv2;
        private TextView mYizhuXiangqingLvBgtv1;
        private TextView mYizhuXiangqingLvBgtv2;
        private ImageView mYizhuXiangqingLvImgv;


        public ViewHolder(View view) {
            yizhuXiangqingLvTv1 = (TextView) view.findViewById(R.id.yizhu_xiangqing_lv_tv1);
            yizhuXiangqingLvTv2 = (TextView) view.findViewById(R.id.yizhu_xiangqing_lv_tv2);
            mYizhuXiangqingLvBgtv1 = (TextView) view.findViewById(R.id.yizhu_xiangqing_lv_bgtv1);
            mYizhuXiangqingLvBgtv2 = (TextView) view.findViewById(R.id.yizhu_xiangqing_lv_bgtv2);
            mYizhuXiangqingLvImgv = (ImageView) view.findViewById(R.id.yizhu_xiangqing_lv_imgv);
        }
    }
}
