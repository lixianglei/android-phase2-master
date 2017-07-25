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
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.dao.entity.XuanJiaoBean;
import com.ge.med.mobile.nursing.db.DBXuanJiaoRecord;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.List;

public class XuanJiaoZjmLvItemAdapter extends BaseAdapter {

    private List<XuanJiaoBean.DataBean> objects;

    private Context context;
    private LayoutInflater layoutInflater;
    private boolean flag;
    private String patientid;
    private DBXuanJiaoRecord mDBXuanJiaoRecord;


    public XuanJiaoZjmLvItemAdapter(Context context, List<XuanJiaoBean.DataBean> objects, boolean flag, String patientid) {
        this.context = context;
        this.patientid = patientid;
        this.flag = flag;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public XuanJiaoBean.DataBean getItem(int position) {
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
        initializeViews((XuanJiaoBean.DataBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(XuanJiaoBean.DataBean object, ViewHolder holder) {
        holder.jiLvShouZiTv.setText(object.getTitle().substring(0, 1));
        // 一级菜单
        if (flag) {
            holder.jiLvBiaoTiTv.setText(object.getTitle());
            holder.jiLvBiaoTiTv.setVisibility(View.VISIBLE);
            holder.mJiLvBiaoTiLl.setVisibility(View.GONE);
            holder.mJiLvBiaoTiTv2.setVisibility(View.GONE);
            holder.mJiLvBiaoTiTv3.setVisibility(View.GONE);
            holder.mJiLvShouZiTvFlag.setVisibility(View.GONE);
        } else {
            // 二级菜单
            if (patientid != null) {
                mDBXuanJiaoRecord = DataSupport.where("patientid = ? and edudefineid = ?", patientid, object.getId() + "")
                        .findFirst(DBXuanJiaoRecord.class);
                LogUtil.d("宣教測試: patientid = " + patientid + "edudefineid = " + object.getId());
                if (mDBXuanJiaoRecord != null) {
                    holder.mJiLvShouZiTvFlag.setVisibility(View.VISIBLE);
                    holder.mJiLvBiaoTiTv3.setVisibility(View.VISIBLE);
                    holder.jiLvShouZiTv.setVisibility(View.GONE);
                    String dateString = DateUtils.getDateString("yyyy/MM/dd HH:mm", mDBXuanJiaoRecord.getEdutime());
                    if ("掌握".equals(mDBXuanJiaoRecord.getEduresulttext().trim())) {
                        holder.mJiLvShouZiTvFlag.setBackgroundResource(R.mipmap.landiduigou);
                    } else {
                        holder.mJiLvShouZiTvFlag.setBackgroundResource(R.mipmap.icon_stop);
                    }
                    holder.mJiLvBiaoTiTv3.setText(mDBXuanJiaoRecord.getEduresulttext() + " " + dateString + " " + mDBXuanJiaoRecord.getUserName());
                } else {
                    holder.mJiLvBiaoTiTv3.setVisibility(View.GONE);
                    holder.mJiLvShouZiTvFlag.setVisibility(View.GONE);
                    holder.jiLvShouZiTv.setVisibility(View.VISIBLE);
                }
            }
            holder.mJiLvBiaoTiTv2.setText(object.getTitle());
            holder.jiLvBiaoTiTv.setVisibility(View.GONE);
            holder.mJiLvBiaoTiLl.setVisibility(View.VISIBLE);
            holder.mJiLvBiaoTiTv2.setVisibility(View.VISIBLE);

            if (holder.mJiLvBiaoTiTv3.getVisibility() == View.VISIBLE) {
                holder.mJiLvBiaoTiTv2.setLines(1);
            } else {
                holder.mJiLvBiaoTiTv2.setLines(2);
            }
        }
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
            mJiLvShouZiTvFlag = (TextView) view.findViewById(R.id.ji_lv_shou_zi_tv_flag);

        }
    }
}
