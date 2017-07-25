package com.ge.med.mobile.nursing.ui.adapter;

/**
 * Created by Administrator on 2016/11/11.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.MeasureDefineBean;
import com.ge.med.mobile.nursing.dao.entity.XuanJiaoBean;
import com.ge.med.mobile.nursing.db.DBXuanJiaoRecord;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.List;

public class HouJiCuoShiZjmLvItemAdapter extends BaseAdapter {

    private List<MeasureDefineBean.DataBean.MeasureTopicDefineListBean> objects;

    private Context context;
    private LayoutInflater layoutInflater;


    public HouJiCuoShiZjmLvItemAdapter(Context context, List<MeasureDefineBean.DataBean.MeasureTopicDefineListBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public MeasureDefineBean.DataBean.MeasureTopicDefineListBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.ping_gu_zjm_lv_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MeasureDefineBean.DataBean.MeasureTopicDefineListBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(MeasureDefineBean.DataBean.MeasureTopicDefineListBean object, ViewHolder holder) {
        holder.jiLvShouZiTv.setText(object.getMeasureTopicValue().substring(0, 1));
            holder.jiLvBiaoTiTv.setText(object.getMeasureTopicValue());
            holder.jiLvBiaoTiTv.setVisibility(View.VISIBLE);
            holder.mJiLvBiaoTiLl.setVisibility(View.GONE);
            holder.mJiLvBiaoTiTv2.setVisibility(View.GONE);
            holder.mJiLvBiaoTiTv3.setVisibility(View.GONE);
            holder.mJiLvShouZiTvFlag.setVisibility(View.GONE);
    }

    protected class ViewHolder {
        private TextView jiLvShouZiTv;
        private TextView jiLvBiaoTiTv;
        private LinearLayout mJiLvBiaoTiLl;
        private TextView mJiLvBiaoTiTv2;
        private TextView mJiLvBiaoTiTv3;
        private TextView mJiLvShouZiTvFlag;



        public ViewHolder(View view) {
            jiLvShouZiTv = (TextView) view.findViewById(R.id.ji_lv_shou_zi_tv);
            jiLvBiaoTiTv = (TextView) view.findViewById(R.id.ji_lv_biao_ti_tv);
            mJiLvBiaoTiLl = (LinearLayout) view.findViewById(R.id.ji_lv_biao_ti_ll);
            mJiLvBiaoTiTv2 = (TextView) view.findViewById(R.id.ji_lv_biao_ti_tv2);
            mJiLvBiaoTiTv3 = (TextView) view.findViewById(R.id.ji_lv_biao_ti_tv3);
            mJiLvShouZiTvFlag = (TextView)view. findViewById(R.id.ji_lv_shou_zi_tv_flag);

        }
    }
}
