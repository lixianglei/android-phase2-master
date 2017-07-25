package com.ge.med.mobile.nursing.ui.component;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sj.library.base.BaseActivity;
import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.ui.activity.ActivityUtils;
import com.ge.med.mobile.nursing.ui.adapter.AdapterUtil;

import org.xutils.common.util.LogUtil;

import java.util.List;

/**
 * Created by Qu on 2016/12/2.
 */
public class DocOrderPannel {
    private TextView mHzBiaoQianBgTv;
    private TextView mHzSjBgTv;
    private TextView mHzJibenXinxiTv;
    private LinearLayout mHzPgLl;
    private TextView mHzPgTv1;
    private TextView mHzPgTv2;
    private TextView mHzPgTv3;
    private TextView mHzPgTv4;
    private TextView mHzYsZdTv;
    private TextView mHzGmsTv;
    private ImageView mHzYzTuBiaoTv;
    private TextView mHzYzShuWeizhixing;
    private TextView mHzYzZhuangtaiIconTv;


    private RiskTag riskTag;

    private YiZhuBean.DataBean mOrder;

    public DocOrderPannel(final BaseActivity activity) {
        mHzSjBgTv = (TextView) activity.findViewById(R.id.hz_sj_bg_tv);
        mHzJibenXinxiTv = (TextView) activity.findViewById(R.id.hz_jiben_xinxi_tv);
        mHzPgLl = (LinearLayout) activity.findViewById(R.id.hz_pg_ll);
        mHzPgTv1 = (TextView) activity.findViewById(R.id.hz_pg_tv1);
        mHzPgTv2 = (TextView) activity.findViewById(R.id.hz_pg_tv2);
        mHzPgTv3 = (TextView) activity.findViewById(R.id.hz_pg_tv3);
        mHzPgTv4 = (TextView) activity.findViewById(R.id.hz_pg_tv4);
        mHzYsZdTv = (TextView) activity.findViewById(R.id.hz_ys_zd_tv);
        mHzGmsTv = (TextView) activity.findViewById(R.id.hz_gms_tv);
        mHzYzTuBiaoTv = (ImageView) activity.findViewById(R.id.hz_yz_tu_biao_tv);
        mHzYzZhuangtaiIconTv = (TextView)activity.findViewById(R.id.hz_yz_zhuangtai_icon_tv);

        mHzYzShuWeizhixing = (TextView) activity.findViewById(R.id.hz_yz_shu_weizhixing);
    }

    public DocOrderPannel(final View activity, Context context) {
        riskTag = new RiskTag((BaseActivity) context);
        mHzSjBgTv = (TextView) activity.findViewById(R.id.hz_sj_bg_tv);
        mHzJibenXinxiTv = (TextView) activity.findViewById(R.id.hz_jiben_xinxi_tv);
        mHzPgLl = (LinearLayout) activity.findViewById(R.id.hz_pg_ll);
        mHzPgTv1 = (TextView) activity.findViewById(R.id.hz_pg_tv1);
        mHzPgTv2 = (TextView) activity.findViewById(R.id.hz_pg_tv2);
        mHzPgTv3 = (TextView) activity.findViewById(R.id.hz_pg_tv3);
        mHzPgTv4 = (TextView) activity.findViewById(R.id.hz_pg_tv4);
        mHzYsZdTv = (TextView) activity.findViewById(R.id.hz_ys_zd_tv);
        mHzGmsTv = (TextView) activity.findViewById(R.id.hz_gms_tv);
        mHzYzTuBiaoTv = (ImageView) activity.findViewById(R.id.hz_yz_tu_biao_tv);
        mHzYzShuWeizhixing = (TextView) activity.findViewById(R.id.hz_yz_shu_weizhixing);
    }

    public DocOrderPannel(final BaseActivity activity, YiZhuBean.DataBean order) {
        this(activity);
        bindData(order);
    }

    public DocOrderPannel(final View activity, YiZhuBean.DataBean order, Context context) {
        this(activity, context);
        bindData(order);
    }

    public void bindData(YiZhuBean.DataBean order) {
        mOrder = order;
        mHzJibenXinxiTv.setText(mOrder.getOrdername());
        String dateString = null;
        try {
            dateString = DateUtils.getDateString("HH:mm", mOrder.getStarttime());
        } catch (Exception e) {
            LogUtil.e("mOrder.getStarttime()  is null");
        }
        if (null != mHzSjBgTv) {
            mHzSjBgTv.setText(dateString);
        }
        mHzYsZdTv.setText("医生：" + mOrder.getDoctorname() + "    途径：" + mOrder.getRoutedesc());
        String not = "";
        if(mOrder.getOrderNoteList()!=null && mOrder.getOrderNoteList().size()>0
                &&mOrder.getOrderNoteList().get(0)!=null){
            not =mOrder.getOrderNoteList().get(0) .getNotevalue();
        }
        mHzGmsTv.setText("备注：" +not);
        if (Constant.TYPE_YZ_PISHI.equals(mOrder.getOrdertype())
                && Constant.YZ_TYPE_ZHIXINGZHONG.equals(mOrder.getOrderststus())) {
            mHzYzShuWeizhixing.setText(Constant.YZ_TYPE_DAIGUANCHA);
        } else {
            mHzYzShuWeizhixing.setText(mOrder.getOrderststus());
            if (Constant.TYPE_YZ_SHUXUE.equals(mOrder.getOrdertype())&&Constant.YZ_TYPE_ZHIXINGZHONG.equals(mOrder.getOrderststus())) {
                mHzYzShuWeizhixing.setText("输血中");
            }
            if (Constant.TYPE_YZ_SHUYE.equals(mOrder.getOrdertype())&&Constant.YZ_TYPE_ZHIXINGZHONG.equals(mOrder.getOrderststus())) {
                mHzYzShuWeizhixing.setText("输液中");
            }
            if (Constant.TYPE_YZ_PISHI.equals(mOrder.getOrdertype())
                    &&Constant.YZ_TYPE_YIZHIXING.equals(mOrder.getOrderststus())
                    &&(mOrder.getOrderresult() == null || mOrder.getOrderresult().trim().isEmpty())) {
                mHzYzShuWeizhixing.setText("待观察");
            }
        }
        mHzPgTv1.setVisibility(View.INVISIBLE);
        mHzPgTv2.setVisibility(View.INVISIBLE);
        mHzPgTv3.setVisibility(View.INVISIBLE);
        mHzPgTv4.setVisibility(View.INVISIBLE);

//        if (mSelectedHZ != null && mSelectedHZ.getIlldetial() != null) {
//            RiskTag.setIllnessTag(mHzPgTv1, mSelectedHZ.getIlldetial(), null);
//        }
        if (Constant.ORDER_DURATION_LINGSHI_0.equals(mOrder.getOrderduration())) {
            mHzPgTv3.setVisibility(View.VISIBLE);
            mHzPgTv3.setText(Constant.ORDER_DURATION_LINGSHI);
            mHzPgTv3.setTextColor(Color.parseColor("#adadad"));
        } else if (Constant.ORDER_DURATION_CHANGQI_1.equals(mOrder.getOrderduration())) {
            mHzPgTv3.setVisibility(View.VISIBLE);
            mHzPgTv3.setText(Constant.ORDER_DURATION_CHANGQI);
            mHzPgTv3.setTextColor(Color.parseColor("#adadad"));
        } else if (Constant.ORDER_DURATION_LIJI_2.equals(mOrder.getOrderduration())) {
            mHzPgTv3.setVisibility(View.VISIBLE);
            mHzPgTv3.setText(Constant.ORDER_DURATION_LIJI);
            mHzPgTv3.setTextColor(Color.RED);
        } else {
            mHzPgTv3.setVisibility(View.INVISIBLE);
        }
        mHzYzTuBiaoTv.setImageResource(AdapterUtil.findYZImage(mOrder));
        if(Constant.YZ_TYPE_DAIHEDUI.equals(mOrder.getOrderststus()) && Constant.TYPE_YZ_SHUXUE.equals(mOrder.getOrdertype())) {
            mHzYzZhuangtaiIconTv.setVisibility(View.VISIBLE);
            mHzYzTuBiaoTv.setImageResource(R.drawable.yuan_xing_shape);
            mHzYzZhuangtaiIconTv.setText("2");
            try {
                List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> dbYiZhudata_orderExecuteRecords
                        = ActivityUtils.getDBYiZhudata_OrderExecuteRecords(mOrder.getDoctorOrders().get(0).getOrderExecuteRecords());
                for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean
                        : dbYiZhudata_orderExecuteRecords) {
                    if (Constant.YZ_TYPE_DAIHEDUI.equals(mOrder.getOrderststus())) {
                        if (Constant.YZ_EXECUTE_JOBTYPE_HEDUI.equals(orderExecuteRecordsBean.getJobtype())) {
                            mHzYzZhuangtaiIconTv.setText("1/2");
                        }
                    }
                }
            } catch (Exception e) {
                LogUtil.e(e.getMessage()+"");
            }
        }
    }
}
