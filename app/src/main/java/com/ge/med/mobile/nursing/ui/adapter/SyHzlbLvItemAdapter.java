package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.ui.component.RiskTag;
import com.ge.med.mobile.nursing.ui.data.RiskDefine;
import com.ge.med.mobile.nursing.ui.view.SearchGroupView;

import java.util.List;

public class SyHzlbLvItemAdapter extends BaseAdapter {

    private List<HuanZheLieBiaoBean.DataBean> objects;

    public List<HuanZheLieBiaoBean.DataBean> getObjects() {
        return objects;
    }

    private Context context;
    private LayoutInflater layoutInflater;
    private List<RiskDefine> riskDefines;
    private TextView mClickView;

    public void loadRiskDefines() {
        this.riskDefines = new AssessDaoImpl().findAllRiskDefine();
    }

    public void setRiskDefines(List<RiskDefine> riskDefines) {
        this.riskDefines = riskDefines;
    }

    public void setObjects(List<HuanZheLieBiaoBean.DataBean> objects) {
        this.objects = objects;
    }


    public SyHzlbLvItemAdapter(Context context, List<HuanZheLieBiaoBean.DataBean> objects,TextView mClickView) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
        if (riskDefines == null || riskDefines.size() <= 0) loadRiskDefines();
        this.mClickView = mClickView;

    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public HuanZheLieBiaoBean.DataBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.sy_hzlb_lv_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(final HuanZheLieBiaoBean.DataBean object, final ViewHolder holder, int pos) {
        holder.mHuanzheXinxiLl.setVisibility(View.GONE);
        holder.mRiskTag.setData(riskDefines, object,holder.mHuanzheXinxiLl,240,mClickView);
        holder.syHljbChLlTv.setText(object.getBedno() + "");
        holder.syHljbXmTv.setText(object.getName() + "");
        holder.syHljbIdTv.setText(object.getMrnno() + "");
        holder.syHljbXbnlTv.setText(object.getSex() + "," + object.getAge() + "岁");
        if (object.getVoOrderStatus() != null) {
            if (object.getVoOrderStatus().getNoExeOrderCount() == 0) {
                holder.syHljbYzTv.setVisibility(View.GONE);
                holder.syHljbYzIv.setVisibility(View.VISIBLE);
            } else {
                holder.syHljbYzTv.setVisibility(View.VISIBLE);
                holder.syHljbYzTv.setText(object.getVoOrderStatus().getNoExeOrderCount() + "");
                holder.syHljbYzIv.setVisibility(View.GONE);
            }
        }

        String carelevel = object.getCarelevel();
        if (carelevel != null) {
            switch (carelevel) {
                case "一级":
                    holder.mSyHljbHljbRl.setBackgroundResource(R.drawable.sy_hzlb_shape_1);
                    break;
                case "二级":
                    holder.mSyHljbHljbRl.setBackgroundResource(R.drawable.sy_hzlb_shape_2);
                    break;
                case "三级":
                    holder.mSyHljbHljbRl.setBackgroundResource(R.drawable.sy_hzlb_shape_3);
                    break;
                case "特级":
                    holder.mSyHljbHljbRl.setBackgroundResource(R.drawable.sy_hzlb_shape_0);
                    break;
            }
        }

        if ("1".equals(object.getNomoneystatus())) {
            holder.mQianfeiImgv.setVisibility(View.VISIBLE);
        } else {
            holder.mQianfeiImgv.setVisibility(View.GONE);
        }

    }

    protected class ViewHolder {
        private TextView syHljbChLlTv;
        private TextView syHljbXmTv;
        private TextView syHljbIdTv;
        private TextView syHljbXbnlTv;
        private TextView syHljbYzTv;
        private ImageView syHljbYzIv;
        private TextView mSyHljbHljbRl;
        private ImageView mQianfeiImgv;
        private RiskTag mRiskTag;
        private SearchGroupView mHuanzheXinxiLl;
        private LinearLayout mHzPgLl;


        public ViewHolder(View view) {
            mRiskTag = new RiskTag((MyBaseActivity) context);
            mRiskTag.setView(view);
            syHljbChLlTv = (TextView) view.findViewById(R.id.sy_hljb_ch_ll_tv);
            syHljbXmTv = (TextView) view.findViewById(R.id.sy_hljb_xm_tv);
            syHljbIdTv = (TextView) view.findViewById(R.id.sy_hljb_id_tv);
            syHljbXbnlTv = (TextView) view.findViewById(R.id.sy_hljb_xbnl_tv);
            syHljbYzTv = (TextView) view.findViewById(R.id.sy_hljb_yz_tv);
            syHljbYzIv = (ImageView) view.findViewById(R.id.sy_hljb_yz_iv);
            mSyHljbHljbRl = (TextView) view.findViewById(R.id.sy_hljb_hljb_rl);
            mQianfeiImgv = (ImageView) view.findViewById(R.id.qianfei_imgv);
            mHuanzheXinxiLl = (SearchGroupView) view.findViewById(R.id.huanzhe_xinxi_ll);
            mHzPgLl = (LinearLayout) view.findViewById(R.id.hz_pg_ll);


        }
    }
}
