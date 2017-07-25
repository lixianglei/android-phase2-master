package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.RWentity;

import java.util.List;

public class RwSyLvItemAdapter extends BaseAdapter {

    private List<RWentity> objects;

    private Context context;
    private LayoutInflater layoutInflater;

    public RwSyLvItemAdapter(Context context,List<RWentity> rWentities) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        objects=rWentities;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public RWentity getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.rw_sy_lv_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((RWentity)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(RWentity object, ViewHolder holder) {
       holder.rwSyLvItemIv.setImageResource(object.getImageView());
        holder.rwSyLvItemTv.setText(object.getName());
    }

    protected class ViewHolder {
        private ImageView rwSyLvItemIv;
        private TextView rwSyLvItemTv;

        public ViewHolder(View view) {
            rwSyLvItemIv = (ImageView) view.findViewById(R.id.rw_sy_lv_item_iv);
            rwSyLvItemTv = (TextView) view.findViewById(R.id.rw_sy_lv_item_tv);
        }
    }
}
