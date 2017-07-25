package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
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
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.db.DBExceptionDefine;
import com.ge.med.mobile.nursing.ui.component.RiskTag;
import com.ge.med.mobile.nursing.utils.DataConverter;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class HzYzFragLvItemAdapter2 extends BaseAdapter {
    private List<YiZhuBean.DataBean.DoctorOrdersBean> dbYiZhuDaTaList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private YiZhuBean.DataBean dataBean;
    private int ciShuInt;
    private RiskTag riskTag;

    public HzYzFragLvItemAdapter2(Context context, List<YiZhuBean.DataBean.DoctorOrdersBean> dbYiZhuDaTaList, YiZhuBean.DataBean dataBean, int i) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.dbYiZhuDaTaList = dbYiZhuDaTaList;
        this.dataBean = dataBean;
        riskTag = new RiskTag((BaseActivity) context);
        ciShuInt = i;
    }

    public List<YiZhuBean.DataBean.DoctorOrdersBean> getDbYiZhuDaTaList() {
        return dbYiZhuDaTaList;
    }

    public void setDbYiZhuDaTaList(List<YiZhuBean.DataBean.DoctorOrdersBean> dbYiZhuDaTaList) {
        this.dbYiZhuDaTaList = dbYiZhuDaTaList;
    }

    @Override
    public int getCount() {
        return dbYiZhuDaTaList.size();
    }

    @Override
    public YiZhuBean.DataBean.DoctorOrdersBean getItem(int position) {
        return dbYiZhuDaTaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.hz_yz_frag_lv_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((YiZhuBean.DataBean.DoctorOrdersBean) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(YiZhuBean.DataBean.DoctorOrdersBean object, ViewHolder holder, int pos) {
        holder.hzYzFragLvBeizhu.setVisibility(View.INVISIBLE);
        holder.hzYzFragLvShuangren1.setVisibility(View.INVISIBLE);
        holder.mHzYzZhuangtaiIconTv.setVisibility(View.VISIBLE);
        holder.mYzHdLxSjTv.setVisibility(View.GONE);
        holder.mHzYzZhuangtaiIconTv.setText((pos + 1) + "/" + ciShuInt);

        long orderExceptionsLastTime = 0;
        String lastNote = "";
        if (showExceptions(object)) {
            if (object != null) {
                List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean> orderExceptions = object.getOrderExceptions();
                if (orderExceptions != null && orderExceptions.size() > 0) {
                    for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean orderExceptionsBean : orderExceptions) {
                        if (orderExceptionsBean != null && orderExceptionsBean.getCreationtime() != null) {
                            if ((orderExceptionsLastTime - orderExceptionsBean.getCreationtime()) < 0) {
                                orderExceptionsLastTime = orderExceptionsBean.getCreationtime();
                            }
                            if (orderExceptionsLastTime == orderExceptionsBean.getCreationtime()
                                    && (!("1".equals(orderExceptionsBean.getIsdeleted()+"")))) {
                                Integer exceptiondefineid = orderExceptionsBean.getExceptiondefineid();
                                DBExceptionDefine first = DataSupport.where("zid = ?", exceptiondefineid + "").findFirst(DBExceptionDefine.class);
                                if (first != null) {
                                    lastNote = first.getExceptionname();
                                }
                            }
                        }
                    }
                    holder.hzYzFragLvBeizhu.setVisibility(View.VISIBLE);
                    holder.hzYzFragLvBeizhu.setText("未能执行:" + lastNote);
                    holder.hzYzFragLvShuangren1.setVisibility(View.VISIBLE);
                    String dateString = DateUtils.getDateString("yyyy/MM/dd HH:mm", orderExceptionsLastTime);
                    holder.hzYzFragLvShuangren1.setText(dateString);
                }

            }
        }
        String dateString = null;
        try {
            dateString = DateUtils.getDateString("HH:mm", object.getStarttime());
        } catch (Exception e) {
            LogUtil.e(" object.getStarttime()  is null");
        }
        holder.hzYzFragLvSjTv.setText(dateString);

        AdapterUtil.setZhuangtaiImgv(object.getStatus(), holder.hzYzZhuangtaiImgv);
        holder.hzYzZhuangtaiImgv.setImageResource(R.drawable.yuan_xing_shape);
        holder.hzYzZhuangtaiTv.setText(object.getStatus());
        holder.hzYzFragLvBtTv.setText(dataBean.getOrdername());
        holder.hzYzPgTv1.setVisibility(View.INVISIBLE);
        holder.hzYzPgTv2.setVisibility(View.INVISIBLE);
        holder.hzYzPgTv3.setVisibility(View.INVISIBLE);
        holder.hzYzPgTv4.setVisibility(View.INVISIBLE);

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
        if (Constant.YZ_TYPE_YIZHIXING.equals(object.getStatus())) {
            holder.hzYzFragLvBgTv.setBackgroundResource(R.drawable.ddd);
            holder.hzYzZhuangtaiTv.setTextColor(Color.parseColor("#dddddd"));
            String dateString1 = DateUtils.getDateString("yyyy/MM/dd HH:mm ", object.getStarttime());
            holder.hzYzZhuangtaiTv.setText(dateString1 + DataConverter.getUserName(object.getLastupdatedby()));
            holder.mHzYzZhuangtaiIconTv.setVisibility(View.GONE);
            holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_complete);
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa" + pos);
        } else if (Constant.YZ_TYPE_YIQUXIAO.equals(object.getStatus())) {
            holder.hzYzFragLvBgTv.setBackgroundResource(R.drawable.login_edt_shape);
            holder.hzYzFragLvBtTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            holder.hzYzZhuangtaiTv.setTextColor(Color.parseColor("#dddddd"));
            holder.mHzYzZhuangtaiIconTv.setVisibility(View.GONE);
        } else {
            holder.hzYzFragLvBgTv.setBackgroundResource(R.drawable.login_edt_shape);
            holder.hzYzFragLvBtTv.getPaint().setFlags(0);
            holder.hzYzZhuangtaiTv.setTextColor(Color.parseColor("#559bec"));
            holder.mHzYzZhuangtaiIconTv.setVisibility(View.VISIBLE);

        }
    }

    protected class ViewHolder {
        private TextView hzYzFragLvBgTv;
        private TextView hzYzFragLvSjTv;
        private TextView hzYzFragLvBtTv;
        private TextView hzYzFragLvBeizhu;
        private TextView hzYzFragLvShuangren1;
        private TextView hzYzFragLvXianTv;
        private LinearLayout hzYzPgLl;
        private TextView hzYzPgTv1;
        private TextView hzYzPgTv2;
        private TextView hzYzPgTv3;
        private TextView hzYzPgTv4;
        private ImageView hzYzZhuangtaiImgv;
        private TextView hzYzZhuangtaiTv;
        private LinearLayout mHzYzFragLvItemRl;
        private TextView mHzYzZhuangtaiIconTv;
        private TextView mYzHdLxSjTv;


        public ViewHolder(View view) {
            hzYzFragLvBgTv = (TextView) view.findViewById(R.id.hz_yz_frag_lv_bg_tv);
            hzYzFragLvSjTv = (TextView) view.findViewById(R.id.hz_yz_frag_lv_sj_tv);
            hzYzFragLvBtTv = (TextView) view.findViewById(R.id.hz_yz_frag_lv_bt_tv);
            hzYzFragLvBeizhu = (TextView) view.findViewById(R.id.hz_yz_frag_lv_beizhu);
            hzYzFragLvShuangren1 = (TextView) view.findViewById(R.id.hz_yz_frag_lv_shuangren1);
            hzYzFragLvXianTv = (TextView) view.findViewById(R.id.hz_yz_frag_lv_xian_tv);
            hzYzPgLl = (LinearLayout) view.findViewById(R.id.hz_yz_pg_ll);
            hzYzPgTv1 = (TextView) view.findViewById(R.id.hz_yz_pg_tv1);
            hzYzPgTv2 = (TextView) view.findViewById(R.id.hz_yz_pg_tv2);
            hzYzPgTv3 = (TextView) view.findViewById(R.id.hz_yz_pg_tv3);
            hzYzPgTv4 = (TextView) view.findViewById(R.id.hz_yz_pg_tv4);
            hzYzZhuangtaiImgv = (ImageView) view.findViewById(R.id.hz_yz_zhuangtai_imgv);
            hzYzZhuangtaiTv = (TextView) view.findViewById(R.id.hz_yz_zhuangtai_tv);
            mHzYzZhuangtaiIconTv = (TextView) view.findViewById(R.id.hz_yz_zhuangtai_icon_tv);
            mYzHdLxSjTv = (TextView) view.findViewById(R.id.yz_hd_lx_sj_tv);

            mHzYzFragLvItemRl = (LinearLayout) view.findViewById(R.id.hz_yz_frag_lv_item_rl);

        }
    }

    private boolean showExceptions(YiZhuBean.DataBean.DoctorOrdersBean object) {
        if (object == null) {
            return false;
        }

        switch (object.getStatus()) {
            //未核对
            case Constant.YZ_TYPE_WEIHEDUI:
                return true;
            //未执行
            case Constant.YZ_TYPE_WEIZHIXING:
                return true;
            //执行中
            case Constant.YZ_TYPE_ZHIXINGZHONG:
                return true;
            //已执行
            case Constant.YZ_TYPE_YIZHIXING:
                return false;
            //已暂停
            case Constant.YZ_TYPE_YIZANTING:
                return true;
            //已取消
            case Constant.YZ_TYPE_YIQUXIAO:
                return true;
            //已停止
            case Constant.YZ_TYPE_YITINGZHI:
                return true;
        }
        return false;
    }
}

