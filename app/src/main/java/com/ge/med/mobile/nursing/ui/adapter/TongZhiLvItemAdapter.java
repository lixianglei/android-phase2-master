package com.ge.med.mobile.nursing.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.TongZhiBean;
import com.ge.med.mobile.nursing.ui.fragment.TZFragment;
import com.ge.med.mobile.nursing.ui.view.TongZhiPopuwindow;

import org.bouncycastle.asn1.x509.Holder;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TongZhiLvItemAdapter extends BaseAdapter {

    private final TZFragment tzFragment;
    private TongZhiBean.DataBean objects;
    private Context context;
    private LayoutInflater layoutInflater;
    private List<TongZhiBean.DataBean> items = new ArrayList<>();


    public TongZhiLvItemAdapter(Context context, TZFragment tzFragment) {
        this.tzFragment = tzFragment;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);

    }

    public List<TongZhiBean.DataBean> getObjects() {
        return items;
    }

    public void setObjects(List<TongZhiBean.DataBean> objects) {
        this.items = objects;
        if (items == null) {
            items = new ArrayList<>();
        }
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public TongZhiBean.DataBean getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.tong_zhi_lv_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((TongZhiBean.DataBean) getItem(position), (ViewHolder) convertView.getTag(), convertView, position);
        return convertView;
    }

    private void initializeViews(final TongZhiBean.DataBean object, final ViewHolder holder, final View convertView, final int position) {

        TongZhiBean.DataBean.PatientBean patient = object.getPatient();
        if (patient != null) {
            holder.tzChXmTv.setText(patient.getBedno() + "床 " + patient.getName());
        }
        holder.tzNeiRongTv.setText(object.getContent());
        try {
            holder.mTzSjTv.setText(DateUtils.getDateString("HH:mm", object.getShowTime()));
        } catch (Exception e) {
            LogUtil.e(e.getMessage() + "");
        }
        if (Constant.NOTIFY_TYPE_ORDER == object.getNotifyType()) {
            yiZhuChuLi(object, holder, object);
        } else if (Constant.NOTIFY_TYPE_VITAL == object.getNotifyType()) {
            tiZhengChuLi(object, holder);
        } else if (Constant.NOTIFY_TYPE_ASSESS == object.getNotifyType()) {
            pingGuChuLi(object, holder);
        } else if (Constant.NOTIFY_TYPE_PATIENT_LABEL == object.getNotifyType()) {
            biaoQianChuLi(object, holder);
        } else if (Constant.NOTIFY_TYPE_PDA_SYNC == object.getNotifyType()) {
            cacheChuLi();
        } else if (Constant.NOTIFY_TYPE_JIAOBAN == object.getNotifyType()) {
            jiaobanChuLi(object, holder);
        }
        holder.mTingzhiItemRl.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String sj = holder.tzSjLxTv.getText().toString() + holder.tzShuZiTv.getText() + holder.tzSjDwTv.getText();
                TongZhiPopuwindow tongZhiPopuwindow = new TongZhiPopuwindow((Activity) context
                        , convertView, holder.mYzShuxueXuanzhongCheckBox, object, sj);
                tongZhiPopuwindow.showPopupWindow();
                return true;
            }
        });
        holder.mTingzhiItemRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tzFragment.lvClick(position);
            }
        });
    }

    //交班处理
    private void jiaobanChuLi(TongZhiBean.DataBean object, ViewHolder holder) {
        showGoToImgv(holder);
        holder.tzImgv.setImageResource(R.mipmap.tongzhi_bingqing);
        holder.tzNeiRongLxTv.setTextColor(Color.RED);
        holder.tzNeiRongLxTv.setText("病情变化");
    }

    // 缓存(数据同步)类通知处理
    private void cacheChuLi() {

    }

    // 标签类通知处理
    private void biaoQianChuLi(TongZhiBean.DataBean object, ViewHolder holder) {
        showGoToImgv(holder);
        holder.tzImgv.setImageResource(R.mipmap.tongzhi_bingqing);
        holder.tzNeiRongLxTv.setTextColor(Color.RED);
        holder.tzNeiRongLxTv.setText("病情变化");
    }

    // 评估类通知处理
    private void pingGuChuLi(TongZhiBean.DataBean object, ViewHolder holder) {
        showGoToImgv(holder);
        holder.tzImgv.setImageResource(R.mipmap.tongzhi_pinggu_blue);
        holder.tzNeiRongLxTv.setTextColor(Color.parseColor("#559bec"));
        if (Constant.NOTIFY_SUB_TYPE_ASSESS_COMMON == object.getNotifySubType()) {
            holder.tzNeiRongLxTv.setText("评估");
        } else {
            holder.tzNeiRongLxTv.setText("评估措施");
        }
    }

    // 体征类通知处理
    private void tiZhengChuLi(TongZhiBean.DataBean object, ViewHolder holder) {
        showGoToImgv(holder);
        holder.tzImgv.setImageResource(R.mipmap.tongzhi_tizheng);
        holder.tzNeiRongLxTv.setTextColor(Color.RED);
        holder.tzNeiRongLxTv.setText("体征变化");
    }

    // 医嘱类通知处理
    private void yiZhuChuLi(TongZhiBean.DataBean object, ViewHolder holder, TongZhiBean.DataBean dataBean) {
        gongGoToImgv(holder);
        if (object != null) {
            suanSJ(object.getShowTime(), holder, dataBean);
        }
    }

    // 医嘱类通知处理 算时间
    private void suanSJ(Long date, ViewHolder holder, TongZhiBean.DataBean object) {
        String str0 = null;
        String str1 = null;
        String str2 = null;
        long nowTime = System.currentTimeMillis();
        String dateString = DateUtils.getDateString("yyyy-MM-dd HH:mm:ss", date);
        String nowTime1 = DateUtils.getDateString("yyyy-MM-dd HH:mm:ss", nowTime);
        long l = nowTime - date;
        if (l >= 0) {
            long l1 = Math.abs(l) / 1000 / 60;
            str0 = "已超时";
            str1 = l1 + "";
            str2 = "分钟";
            if (l1 > 60) {
                str1 = l1 / 60 + "";
                str2 = "小时";
            }
            if (l1 > (60 * 24)) {
                str1 = "1+";
                str2 = "天";
            }

            if (object.getOrderNotify() != null) {
                holder.tzImgv.setImageResource(getImgv(object.getOrderNotify().getOrderType()
                        , object.getEventType(), holder.mTongzhiTypeBgTv, holder.tzNeiRongLxTv));
            }
        } else {
            long l1 = Math.abs(l) / 1000 / 60;
            str0 = "剩余";
            str1 = l1 + "";
            str2 = "分钟";
            if (l1 > 60) {
                str1 = l1 / 60 + "";
                str2 = "小时";
            }
            if (l1 > (60 * 24)) {
                str1 = "1+";
                str2 = "天";
            }
            if (object.getOrderNotify() != null) {
                holder.tzImgv.setImageResource(getImgv(object.getOrderNotify().getOrderType()
                        , object.getEventType(), holder.mTongzhiTypeBgTv, holder.tzNeiRongLxTv));
            }
        }
        holder.tzSjLxTv.setText(str0);
        holder.tzShuZiTv.setText(str1);
        holder.tzSjDwTv.setText(str2);
    }

    private void showGoToImgv(ViewHolder holder) {
        holder.mTongzhiGotoImgv.setVisibility(View.VISIBLE);
        holder.tzSjLxTv.setVisibility(View.GONE);
        holder.tzShuZiTv.setVisibility(View.GONE);
        holder.tzSjDwTv.setVisibility(View.GONE);
    }

    private void gongGoToImgv(ViewHolder holder) {
        holder.mTongzhiGotoImgv.setVisibility(View.GONE);
        holder.tzSjLxTv.setVisibility(View.VISIBLE);
        holder.tzShuZiTv.setVisibility(View.VISIBLE);
        holder.tzSjDwTv.setVisibility(View.VISIBLE);
    }

    private int getImgv(String orderType, String eventType, TextView textViewbg, TextView textViewtype) {
        int tzimgvid = R.mipmap.icon_not_performed_red;
        textViewtype.setText(eventType);
        if (Constant.EVENT_TYPE_ORDER_NO_EXECUTE_TIMEOUT.equals(eventType)
                || Constant.EVENT_TYPE_ORDER_INFUSION_WATCH_TIMEOUT.equals(eventType)
                || Constant.EVENT_TYPE_ORDER_BLOOD_WATCH_TIMEOUT.equals(eventType)
                ) {
            textViewbg.setBackgroundResource(R.drawable.tz_shape_0);
            textViewtype.setTextColor(Color.parseColor("#cb0019"));
            switch (orderType) {
                case Constant.TYPE_YZ_PIXIAZHUSHE:
                case Constant.TYPE_YZ_JINGMAIZHUSHE:
                case Constant.TYPE_YZ_JIROUZHUSHE:
                    tzimgvid = R.mipmap.icon_injection_red;
                    break;
                case Constant.TYPE_YZ_SHUYE:
                    tzimgvid = R.mipmap.icon_iinfusion_red;
                    break;
                case Constant.TYPE_YZ_SHUXUE:
                case Constant.TYPE_YZ_CAIXUE:
                    tzimgvid = R.mipmap.icon_not_performed_red;
                    break;
                case Constant.TYPE_YZ_KOUFU:
                    tzimgvid = R.mipmap.icon_medicine_red;
                    break;
                case Constant.TYPE_YZ_JIANYAN_BLOOD:
                case Constant.TYPE_YZ_JIANYAN_UN_BLOOD:
                    tzimgvid = R.mipmap.icon_bio_red;
                    break;
                case Constant.TYPE_YZ_HULI:
                case Constant.TYPE_YZ_ZHILIAO:
                case Constant.TYPE_YZ_SHANSHI:
                    tzimgvid = R.mipmap.fuzhuzhiliao_red;
                    break;
                case Constant.TYPE_YZ_PISHI:
                    tzimgvid = R.mipmap.icon_injection_red;
                    break;
            }
        }
        if (Constant.EVENT_TYPE_ORDER_PLAN_EXECUTE_REMIND.equals(eventType)
                || Constant.EVENT_TYPE_ORDER_INFUSION_WATCH_REMIND.equals(eventType)
                || Constant.EVENT_TYPE_ORDER_BLOOD_WATCH_REMIND.equals(eventType)
                ) {
            textViewbg.setBackgroundResource(R.drawable.tz_shape_1);
            textViewtype.setTextColor(Color.parseColor("#559bec"));
            switch (orderType) {
                case Constant.TYPE_YZ_JINGMAIZHUSHE:
                case Constant.TYPE_YZ_PIXIAZHUSHE:
                case Constant.TYPE_YZ_JIROUZHUSHE:
                    tzimgvid = R.mipmap.icon_injection;
                    break;
                case Constant.TYPE_YZ_SHUYE:
                    tzimgvid = R.mipmap.icon_iinfusion;
                    break;
                case Constant.TYPE_YZ_SHUXUE:
                case Constant.TYPE_YZ_CAIXUE:
                    tzimgvid = R.mipmap.icon_blood_transfusion;
                    break;
                case Constant.TYPE_YZ_KOUFU:
                    tzimgvid = R.mipmap.icon_medicine;
                    break;
                case Constant.TYPE_YZ_JIANYAN_BLOOD:
                case Constant.TYPE_YZ_JIANYAN_UN_BLOOD:
                    tzimgvid = R.mipmap.icon_bio;
                    break;
                case Constant.TYPE_YZ_HULI:
                case Constant.TYPE_YZ_ZHILIAO:
                    tzimgvid = R.mipmap.fuzhuzhiliao;
                    break;
                case Constant.TYPE_YZ_PISHI:
                    tzimgvid = R.mipmap.icon_injection;
                    break;
                case Constant.TYPE_YZ_RUHU:
                    tzimgvid = R.mipmap.ruhu;
                    break;
                case Constant.TYPE_YZ_SHANSHI:
                    tzimgvid = R.mipmap.shanshi;
                    break;

            }
        }
        if (Constant.EVENT_TYPE_ORDER_NEAR_EXECUTE.equals(eventType)
                || Constant.EVENT_TYPE_ORDER_NEAR_WATCH.equals(eventType)) {
            textViewbg.setBackgroundResource(R.drawable.tz_shape_2);
            textViewtype.setTextColor(Color.parseColor("#FFF5A725"));
            switch (orderType) {
                case Constant.TYPE_YZ_JINGMAIZHUSHE:
                case Constant.TYPE_YZ_PIXIAZHUSHE:
                case Constant.TYPE_YZ_JIROUZHUSHE:
                    tzimgvid = R.mipmap.icon_injection_orange;
                    break;
                case Constant.TYPE_YZ_SHUYE:
                    tzimgvid = R.mipmap.icon_iinfusion_orange;
                    break;
                case Constant.TYPE_YZ_SHUXUE:
                case Constant.TYPE_YZ_CAIXUE:
                    tzimgvid = R.mipmap.icon_not_performed_orange;
                    break;
                case Constant.TYPE_YZ_KOUFU:
                    tzimgvid = R.mipmap.icon_medicine_orange;
                    break;
                case Constant.TYPE_YZ_JIANYAN_BLOOD:
                case Constant.TYPE_YZ_JIANYAN_UN_BLOOD:
                    tzimgvid = R.mipmap.icon_bio_orange;
                    break;
                case Constant.TYPE_YZ_HULI:
                case Constant.TYPE_YZ_ZHILIAO:
                case Constant.TYPE_YZ_SHANSHI:
                    tzimgvid = R.mipmap.fuzhuzhiliao_orang;
                    break;
                case Constant.TYPE_YZ_PISHI:
                    tzimgvid = R.mipmap.icon_injection_orange;
                    break;
            }
        }
        return tzimgvid;
    }

    protected class ViewHolder {
        private ImageView tzImgv;
        private TextView tzChXmTv;
        private TextView tzXianTv;
        private TextView tzNeiRongTv;
        private TextView tzNeiRongLxTv;
        private TextView tzSjLxTv;
        private TextView tzShuZiTv;
        private TextView tzSjDwTv;
        private TextView mTongzhiTypeBgTv;
        private TextView mYzShuxueXuanzhongCheckBox;
        private RelativeLayout mTingzhiItemRl;
        private ImageView mTongzhiGotoImgv;
        private TextView mTzSjTv;


        public ViewHolder(View view) {
            tzImgv = (ImageView) view.findViewById(R.id.tz_imgv);
            tzChXmTv = (TextView) view.findViewById(R.id.tz_ch_xm_tv);
            tzXianTv = (TextView) view.findViewById(R.id.tz_xian_tv);
            tzNeiRongTv = (TextView) view.findViewById(R.id.tz_nei_rong_tv);
            tzNeiRongLxTv = (TextView) view.findViewById(R.id.tz_nei_rong_lx_tv);
            tzSjLxTv = (TextView) view.findViewById(R.id.tz_sj_lx_tv);
            tzShuZiTv = (TextView) view.findViewById(R.id.tz_shu_zi_tv);
            tzSjDwTv = (TextView) view.findViewById(R.id.tz_sj_dw_tv);
            mTongzhiTypeBgTv = (TextView) view.findViewById(R.id.tongzhi_type_bg_tv);
            mYzShuxueXuanzhongCheckBox = (TextView) view.findViewById(R.id.yz_shuxue_xuanzhong_check_box);
            mTingzhiItemRl = (RelativeLayout) view.findViewById(R.id.tingzhi_item_rl);
            mTongzhiGotoImgv = (ImageView) view.findViewById(R.id.tongzhi_goto_imgv);
            mTzSjTv = (TextView) view.findViewById(R.id.tz_sj_tv);


        }
    }

}

