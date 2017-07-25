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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VitalKeyboardItemAdapter extends BaseAdapter {
    private List<String[]> objects;
    private Context context;
    private LayoutInflater layoutInflater;

    public VitalKeyboardItemAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        objects = new ArrayList<>();
    }

    public List<String[]> getObjects() {
        return objects;
    }

    public void setObjects(List<String[]> objects) {
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public String[] getItem(int position) {

        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.vital_keyboard_item_include, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(final String[] object, final ViewHolder holder, final int position) {
        if (Constant.VITAL_KEYBOARD_BUTTON_BACK.equals(object)){
            holder.mTextView.setVisibility(View.GONE);
            holder.mImageView.setVisibility(View.VISIBLE);
        }else {
            holder.mImageView.setVisibility(View.GONE);
            holder.mTextView.setVisibility(View.VISIBLE);
            holder.mTextView.setText(object[0]);
        }
    }

    protected class ViewHolder {
        private TextView mTextView;
        private ImageView mImageView;

        public ViewHolder(View view) {
            mTextView = (TextView) view.findViewById(R.id.vital_item_bt);
            mImageView = (ImageView) view.findViewById(R.id.vital_item_ib);
        }
    }
}
