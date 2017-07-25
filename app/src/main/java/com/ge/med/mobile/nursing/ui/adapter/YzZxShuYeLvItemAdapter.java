package com.ge.med.mobile.nursing.ui.adapter;

/**
 * Created by Administrator on 2016/11/16.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.db.DBExceptionDefine;
import com.ge.med.mobile.nursing.db.DBUserList;
import com.ge.med.mobile.nursing.ui.component.RiskTag;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.List;

public class YzZxShuYeLvItemAdapter extends BaseAdapter {

    private List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> mEntities;

    private Context context;
    private LayoutInflater layoutInflater;
    private YiZhuBean.DataBean dataBean;

    public void setmEntities(List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> mEntities) {
        this.mEntities = mEntities;
    }

    public YzZxShuYeLvItemAdapter(Context context, List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> entities, YiZhuBean.DataBean dataBean) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities = entities;
        this.dataBean = dataBean;
    }
    public void setDataBean(YiZhuBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }
    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.yz_zx_shu_ye_lv_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean entity, ViewHolder holder, int position) {
        String dateString = null;
        try {
            dateString = DateUtils.getDateString("HH:mm", entity.getCreationtime());
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
        holder.hzYzFragLvSjTv.setText(dateString);
        holder.hzYzPgTv3.setVisibility(View.INVISIBLE);
        holder.hzYzPgTv1.setVisibility(View.INVISIBLE);

        if (Constant.ORDER_DURATION_LINGSHI_0.equals(dataBean.getOrderduration())) {
            holder.hzYzPgTv3.setVisibility(View.VISIBLE);
            holder.hzYzPgTv3.setText(Constant.ORDER_DURATION_LINGSHI);
            holder.hzYzPgTv3.setTextColor(Color.parseColor("#adadad"));
        } else if (Constant.ORDER_DURATION_CHANGQI_1.equals(dataBean.getOrderduration())) {
            holder.hzYzPgTv3.setVisibility(View.VISIBLE);
            holder.hzYzPgTv3.setText(Constant.ORDER_DURATION_CHANGQI);
            holder.hzYzPgTv3.setTextColor(Color.parseColor("#adadad"));
        } else if (Constant.ORDER_DURATION_LIJI_2.equals(dataBean.getOrderduration())) {
            holder.hzYzPgTv3.setVisibility(View.VISIBLE);
            holder.hzYzPgTv3.setText(Constant.ORDER_DURATION_LIJI);
            holder.hzYzPgTv3.setTextColor(Color.RED);
        } else {
            holder.hzYzPgTv3.setVisibility(View.INVISIBLE);
        }
        DBUserList first1 = null;
        if (entity.getUserid() != null) {
            first1 = DataSupport.where("zid = ?", entity.getUserid()).findFirst(DBUserList.class);
        }
        if (position == (mEntities.size() - 1) && Constant.TYPE_YZ_SHUYE.equals(dataBean.getOrdertype())) {
            holder.hzYzFragLvBtTv.setText("开始输液");
            if (first1 != null) {
                holder.mHzYzZhuangtaiTv.setText(first1.getName() + ": 已执行");
            }
        } else {
            DBUserList first2 = null;
            if (entity.getUserid2() != null) {
                first2 = DataSupport.where("zid = ?", entity.getUserid2()).findFirst(DBUserList.class);
            }
            if (first1 != null) {
                holder.mHzYzZhuangtaiTv.setText(first1.getName() + ": 已执行");
            }
            if (Constant.YZ_EXECUTE_JOBTYPE_HUANYE.equals(entity.getJobtype()) ) {
                if(Constant.TYPE_YZ_SHUXUE.equals(dataBean.getOrdertype())&& entity.getJobStatus() != null){
                    holder.hzYzFragLvBtTv.setText("第" + entity.getJobStatus() + "袋开始");
                }else{
                    holder.hzYzFragLvBtTv.setText("换液");
                }
                if (first1 != null && first2 != null) {
                    holder.mHzYzZhuangtaiTv.setText(first1.getName() + "/" + first2.getName() + ": 已执行");
                }
            }
            YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean orderExceptions = null;
            try {
                if (entity.getOrderExceptionList() != null && entity.getOrderExceptionList().size() > 0) {
                    orderExceptions = entity.getOrderExceptionList().get(entity.getOrderExceptionList().size() - 1);
                }
            } catch (Exception e) {
                LogUtil.e("orderExceptions is null!");
            }
            String entityExceptions = "";
            if (orderExceptions != null&& (!"1".equals(orderExceptions.getIsdeleted()))) {
                try {
                    Integer exceptiondefineid = orderExceptions.getExceptiondefineid();
                    DBExceptionDefine first = DataSupport.where("zid = ?", exceptiondefineid + "").findFirst(DBExceptionDefine.class);
                    entityExceptions = first.getExceptionname();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (entityExceptions != null && !entityExceptions.isEmpty()) {
                holder.hzYzFragLvBtTv.setText(entityExceptions);
                holder.hzYzPgTv3.setBackgroundResource(R.drawable.sy_hljb_pg_shape_2);
                holder.mXunshiYichangBgTv.setVisibility(View.VISIBLE);
                holder.hzYzZhuangtaiTv.setTextColor(Color.WHITE);
                holder.hzYzPgTv3.setTextColor(Color.WHITE);
            } else {
                if ("4".equals(entity.getJobtype())) {
                    holder.hzYzFragLvBtTv.setText("正常");
                }
                holder.hzYzPgTv3.setBackgroundResource(R.drawable.sy_hljb_pg_shape_1);
                holder.mXunshiYichangBgTv.setVisibility(View.GONE);
                holder.hzYzZhuangtaiTv.setTextColor(Color.parseColor("#559bec"));
                holder.hzYzPgTv3.setTextColor(Color.parseColor("#adadad"));
            }
        }
        holder.yzZxShuYeLvItemDisuZhiTv.setText(entity.getDropspeed());
        holder.yzZxShuYeLvItemShiruliangZhiTv.setText(entity.getActualvolume());
        if (entity.getActualvolume() == null) {
            holder.yzZxShuYeLvItemShiruliangZhiTv.setText(0 + "");
        }

    }

    protected class ViewHolder {
        private RelativeLayout hzYzFragLvItemRl;
        private TextView hzYzFragLvBgTv;
        private TextView hzYzFragLvSjTv;
        private TextView hzYzFragLvBtTv;
        private TextView yzZxShuYeLvItemDisuTv;
        private TextView yzZxShuYeLvItemShiruliangTv;
        private TextView yzZxShuYeLvItemDisuZhiTv;
        private TextView yzZxShuYeLvItemShiruliangZhiTv;
        private TextView yzZxShuYeLvItemDisuDanweiTv;
        private TextView yzZxShuYeLvItemShiruliangDanweiTv;
        private TextView hzYzFragLvXianTv;
        private LinearLayout hzYzPgLl;
        private TextView hzYzZhuangtaiTv;
        private TextView hzYzPgTv3;
        private TextView hzYzPgTv1;
        private TextView mXunshiYichangBgTv;
        private TextView mHzYzZhuangtaiTv;


        public ViewHolder(View view) {
            hzYzFragLvItemRl = (RelativeLayout) view.findViewById(R.id.hz_yz_frag_lv_item_rl);
            hzYzFragLvBgTv = (TextView) view.findViewById(R.id.hz_yz_frag_lv_bg_tv);
            hzYzFragLvSjTv = (TextView) view.findViewById(R.id.hz_yz_frag_lv_sj_tv);
            hzYzFragLvBtTv = (TextView) view.findViewById(R.id.hz_yz_frag_lv_bt_tv);
            yzZxShuYeLvItemDisuTv = (TextView) view.findViewById(R.id.yz_zx_shu_ye_lv_item_disu_tv);
            yzZxShuYeLvItemShiruliangTv = (TextView) view.findViewById(R.id.yz_zx_shu_ye_lv_item_shiruliang_tv);
            yzZxShuYeLvItemDisuZhiTv = (TextView) view.findViewById(R.id.yz_zx_shu_ye_lv_item_disu_zhi_tv);
            yzZxShuYeLvItemShiruliangZhiTv = (TextView) view.findViewById(R.id.yz_zx_shu_ye_lv_item_shiruliang_zhi_tv);
            yzZxShuYeLvItemDisuDanweiTv = (TextView) view.findViewById(R.id.yz_zx_shu_ye_lv_item_disu_danwei_tv);
            yzZxShuYeLvItemShiruliangDanweiTv = (TextView) view.findViewById(R.id.yz_zx_shu_ye_lv_item_shiruliang_danwei_tv);
            hzYzFragLvXianTv = (TextView) view.findViewById(R.id.hz_yz_frag_lv_xian_tv);
            hzYzPgLl = (LinearLayout) view.findViewById(R.id.hz_yz_pg_ll);
            hzYzZhuangtaiTv = (TextView) view.findViewById(R.id.hz_yz_zhuangtai_tv);
            hzYzPgTv3 = (TextView) view.findViewById(R.id.hz_yz_pg_tv3);
            hzYzPgTv1 = (TextView) view.findViewById(R.id.hz_yz_pg_tv1);
            mXunshiYichangBgTv = (TextView) view.findViewById(R.id.xunshi_yichang_bg_tv);
            mHzYzZhuangtaiTv = (TextView) view.findViewById(R.id.hz_yz_zhuangtai_tv);

        }
    }
}
