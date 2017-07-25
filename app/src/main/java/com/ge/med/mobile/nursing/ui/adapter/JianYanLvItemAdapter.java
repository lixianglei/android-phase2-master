package com.ge.med.mobile.nursing.ui.adapter;

/**
 * Created by Administrator on 2016/11/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JianYanLvItemAdapter extends BaseAdapter {

    private List<YiZhuBean.DataBean.OrderCheckPropertyBean> mEntities;

    public Map<Integer, Boolean> mCBFlag = null;
    private Context context;
    private LayoutInflater layoutInflater;

    public JianYanLvItemAdapter(Context context, List<YiZhuBean.DataBean.OrderCheckPropertyBean> entities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        if (entities != null) {
            this.mEntities = entities;
        } else {
            this.mEntities = new ArrayList<>();
        }
        init();
    }


    public List<YiZhuBean.DataBean.OrderCheckPropertyBean> getmEntities() {
        return mEntities;
    }

    public void setmEntities(List<YiZhuBean.DataBean.OrderCheckPropertyBean> mEntities) {
        if (mEntities != null) {
            this.mEntities = mEntities;
        } else {
            this.mEntities = new ArrayList<>();
        }
        init();
    }
    void init() {
        mCBFlag = new HashMap<Integer, Boolean>();
        for (int i = 0; i < mEntities.size(); i++) {
            mCBFlag.put(i, false);
        }
    }
    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public YiZhuBean.DataBean.OrderCheckPropertyBean getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.jian_yan_lv_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((YiZhuBean.DataBean.OrderCheckPropertyBean) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(YiZhuBean.DataBean.OrderCheckPropertyBean entity, final ViewHolder holder, final int position) {
        String dateString = DateUtils.getDateString("yyyy-MM-dd HH:mm",entity.getTesttime());
        holder.shuXueInculdeTv01.setText("开立时间: "+dateString);
        holder.shuXueInculdeTv02.setText("试管编号: "+entity.getTesttubenumber());
        holder.shuXueInculdeTv03.setText("试管类别: "+entity.getTesttubetype());
        holder.shuXueInculdeTv04.setText("标本量: "+entity.getSamplevolume());
        holder.shuXueInculdeTv05.setText("特使要求: "+entity.getSpecialrequirement());
        holder.yzShuxueXuanzhongCheckBox.setChecked(mCBFlag.get(position));
        if (holder.yzShuxueXuanzhongCheckBox.isChecked()) {
            holder.yzShuxueXuanzhongCheckImgv.setImageResource(R.mipmap.icon_select);
        } else {
            holder.yzShuxueXuanzhongCheckImgv.setImageResource(sheZhiIcon(position));
        }
        holder.yzShuxueXuanzhongCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCBFlag.put(position, holder.yzShuxueXuanzhongCheckBox.isChecked());

                if (holder.yzShuxueXuanzhongCheckBox.isChecked()) {
                    holder.yzShuxueXuanzhongCheckImgv.setImageResource(R.mipmap.icon_select);
                } else {
                    holder.yzShuxueXuanzhongCheckImgv.setImageResource(  sheZhiIcon(position));
                }
            }
        });

    }

    private int sheZhiIcon(int position) {
        int i=R.mipmap._1st;
        if (position==0) {
            i= R.mipmap._1st;
        }else if(position==2){
            i= R.mipmap._3rd;
        }else if(position==3){
            i= R.mipmap._4th;
        }else if(position==4){
            i= R.mipmap._5th;
        }else if(position==1){
            i= R.mipmap._2nd;
        }
        return  i;
    }


    protected class ViewHolder {
        private TextView shuXueInculdeTv01;
        private TextView shuXueInculdeTv02;
        private TextView shuXueInculdeTv03;
        private TextView shuXueInculdeTv04;
        private TextView shuXueInculdeTv05;
        private CheckBox yzShuxueXuanzhongCheckBox;
        private ImageView yzShuxueXuanzhongCheckImgv;

        public ViewHolder(View view) {
            shuXueInculdeTv01 = (TextView) view.findViewById(R.id.shu_xue_inculde_tv01);
            shuXueInculdeTv02 = (TextView) view.findViewById(R.id.shu_xue_inculde_tv02);
            shuXueInculdeTv03 = (TextView) view.findViewById(R.id.shu_xue_inculde_tv03);
            shuXueInculdeTv04 = (TextView) view.findViewById(R.id.shu_xue_inculde_tv04);
            shuXueInculdeTv05 = (TextView) view.findViewById(R.id.shu_xue_inculde_tv05);
            yzShuxueXuanzhongCheckBox = (CheckBox) view.findViewById(R.id.yz_shuxue_xuanzhong_check_box);
            yzShuxueXuanzhongCheckImgv = (ImageView) view.findViewById(R.id.yz_shuxue_xuanzhong_check_imgv);
        }
    }
}
