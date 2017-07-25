package com.ge.med.mobile.nursing.ui.adapter;

/**
 * Created by Administrator on 2016/11/14.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.ge.med.mobile.nursing.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShuXueShenqingYichangLvItemAdapter extends BaseAdapter {

    private List<String> objects = new ArrayList<String>();
    private Map<Integer, Boolean> mCBFlag = null;
    private String yiChangCuoShi = null;

    private Context context;
    private LayoutInflater layoutInflater;

    public String getYiChangCuoShi() {
        return yiChangCuoShi;
    }

    public void setYiChangCuoShi(String yiChangCuoShi) {
        this.yiChangCuoShi = yiChangCuoShi;
    }

    public ShuXueShenqingYichangLvItemAdapter(Context context,  List<String> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=objects;
        init();
    }

    void init() {
        mCBFlag = new HashMap<Integer, Boolean>();
        for (int i = 0; i < objects.size(); i++) {
            mCBFlag.put(i, false);
        }
    }

    public List<String> getObjects() {
        return objects;
    }

    public void setObjects(List<String> objects) {
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public String getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.shu_xue_shenqing_yichang_lv_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((String) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }


    private void initializeViews(String object, final ViewHolder holder, final int position) {
        holder.mShuXueShenqingLvCb.setText(object);
        holder.mShuXueShenqingLvCb.setChecked(mCBFlag.get(position));
        System.out.println("yiChangCeShi------------->" + yiChangCuoShi);
        holder.mShuXueShenqingLvCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Map.Entry<Integer, Boolean> entry : mCBFlag.entrySet()) {
                    if (position == entry.getKey()) {
                        if (holder.mShuXueShenqingLvCb.isChecked()) {
                            mCBFlag.put(entry.getKey(), true);
                            yiChangCuoShi = holder.mShuXueShenqingLvCb.getText().toString();
                        } else {
                            mCBFlag.put(entry.getKey(), false);
                            yiChangCuoShi = null;
                        }
                    } else {
                        mCBFlag.put(entry.getKey(), false);
                    }
                }
                notifyDataSetChanged();
            }
        });
    }

    protected class ViewHolder {
        private CheckBox mShuXueShenqingLvCb;


        public ViewHolder(View view) {
            mShuXueShenqingLvCb = (CheckBox) view.findViewById(R.id.shu_xue_shenqing_lv_cb);
        }
    }
}
