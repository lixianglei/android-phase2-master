package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sj.library.base.BaseActivity;
import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.ui.component.RiskTag;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class XsdrLvItemAdapter extends BaseAdapter {

    public void setObjects(List<YiZhuBean.DataBean> objects) {
        this.objects = objects;
    }

    private List<YiZhuBean.DataBean> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public XsdrLvItemAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public YiZhuBean.DataBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.xsdr_lv_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((YiZhuBean.DataBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(YiZhuBean.DataBean object, ViewHolder holder) {
        holder.xsdrNeiRongTv.setText(object.getOrdername());
        holder.xsdrZhuangtaiImgv.setImageResource(AdapterUtil.findYZImage(object));
        holder.mYizhuZhuangtaiZifu.setText(object.getOrderststus());
        final DBHuanZheLieBiao huanZheLieBiao = AdapterUtil.getPatientFromDB(object.getPatientid());
        if(huanZheLieBiao!=null){
            holder.mHuLiJiBieIconImgv.setImageResource(AdapterUtil.findCareLevelImage(huanZheLieBiao.getCarelevel()));
            final String bedno = huanZheLieBiao.getBedno();
            final String name = huanZheLieBiao.getName();


        String dateString = DateUtils.getDateString("HH:mm", object.getStarttime());
        holder.xsdrTitleTv.setText(bedno + "床 " + name + " (" + huanZheLieBiao.getMrnno() +  ") " + dateString);
        }
        holder.hzYzPgTv1.setVisibility(View.INVISIBLE);
        holder.hzYzPgTv2.setVisibility(View.INVISIBLE);
        holder.hzYzPgTv3.setVisibility(View.INVISIBLE);
        holder.hzYzPgTv4.setVisibility(View.INVISIBLE);
        if (Constant.ORDER_DURATION_LINGSHI_0.equals(object.getOrderduration())) {
            holder.hzYzPgTv3.setVisibility(View.VISIBLE);
            holder.hzYzPgTv3.setText(Constant.ORDER_DURATION_LINGSHI);
            holder.hzYzPgTv3.setTextColor(Color.parseColor("#adadad"));
        } else if (Constant.ORDER_DURATION_CHANGQI_1.equals(object.getOrderduration())) {
            holder.hzYzPgTv3.setVisibility(View.VISIBLE);
            holder.hzYzPgTv3.setText(Constant.ORDER_DURATION_CHANGQI);
            holder.hzYzPgTv3.setTextColor(Color.parseColor("#adadad"));
        } else if (Constant.ORDER_DURATION_LIJI_2.equals(object.getOrderduration())) {
            holder.hzYzPgTv3.setVisibility(View.VISIBLE);
            holder.hzYzPgTv3.setText(Constant.ORDER_DURATION_LIJI);
            holder.hzYzPgTv3.setTextColor(Color.RED);
        }else {
            holder.hzYzPgTv3.setVisibility(View.INVISIBLE);
        }
    }

    protected class ViewHolder {
        private TextView xsdrTitleTv;
        private TextView xsdrNeiRongTv;
        private ImageView xsdrZhuangtaiImgv;
        private LinearLayout hzYzPgLl;
        private TextView hzYzPgTv1;
        private TextView hzYzPgTv2;
        private TextView hzYzPgTv3;
        private TextView hzYzPgTv4;
        private ImageView mHuLiJiBieIconImgv;
        private TextView mYizhuZhuangtaiZifu;

        public ViewHolder(View view) {
            xsdrTitleTv = (TextView) view.findViewById(R.id.xsdr_title_tv);
            xsdrNeiRongTv = (TextView) view.findViewById(R.id.xsdr_nei_rong_tv);
            xsdrZhuangtaiImgv = (ImageView) view.findViewById(R.id.xsdr_zhuangtai_imgv);
            hzYzPgLl = (LinearLayout) view.findViewById(R.id.hz_yz_pg_ll);
            hzYzPgTv1 = (TextView) view.findViewById(R.id.hz_yz_pg_tv1);
            hzYzPgTv2 = (TextView) view.findViewById(R.id.hz_yz_pg_tv2);
            hzYzPgTv3 = (TextView) view.findViewById(R.id.hz_yz_pg_tv3);
            hzYzPgTv4 = (TextView) view.findViewById(R.id.hz_yz_pg_tv4);
            mHuLiJiBieIconImgv = (ImageView) view.findViewById(R.id.hu_li_ji_bie_icon_imgv);
            mYizhuZhuangtaiZifu = (TextView) view.findViewById(R.id.yi_zhu_zhuangtai_zifu);
        }
    }
}
