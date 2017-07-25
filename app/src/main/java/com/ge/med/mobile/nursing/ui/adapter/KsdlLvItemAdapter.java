package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.db.DBUser;

import org.xutils.common.util.LogUtil;

import java.util.List;

public class KsdlLvItemAdapter extends BaseAdapter {

    private List<DBUser> objects;

    private Context context;
    private LayoutInflater layoutInflater;

    public KsdlLvItemAdapter(Context context, List<DBUser> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=objects;

    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public DBUser getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.ksdl_lv_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((DBUser)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(DBUser object, ViewHolder holder) {
        try {
            holder.ksdlLvIdTv.setText(object.getUser_id());
            holder.ksdlLvXmTv.setText(object.getUsername());
        } catch (Exception e) {
            LogUtil.e("ksdlLvIdTv or object is null");
        }
    }

    protected class ViewHolder {
        private TextView ksdlLvXmTv;
        private TextView ksdlLvIdTv;

        public ViewHolder(View view) {
            ksdlLvXmTv = (TextView) view.findViewById(R.id.ksdl_lv_xm_tv);
            ksdlLvIdTv = (TextView) view.findViewById(R.id.ksdl_lv_id_tv);
        }
    }
}
