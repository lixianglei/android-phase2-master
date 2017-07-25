package com.ge.med.mobile.nursing.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.db.DBExceptionDefine;
import com.ge.med.mobile.nursing.ui.activity.ActivityUtils;
import com.ge.med.mobile.nursing.ui.component.RiskTag;
import com.ge.med.mobile.nursing.ui.fragment.YZFragment;
import com.ge.med.mobile.nursing.ui.view.YiZhuPopuwindow;
import com.ge.med.mobile.nursing.utils.DataConverter;

import org.litepal.crud.DataSupport;
import org.w3c.dom.Attr;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HzYzFragLvItemAdapter extends BaseAdapter {
    private List<YiZhuBean.DataBean> dbYiZhuDaTaList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private String date = "";
    private Map<Integer, Boolean> posList;
    private YZFragment yzFragment;

    public Map<Integer, Boolean> getPosList() {
        return posList;
    }

    public void setPosList(Map<Integer, Boolean> posList) {
        this.posList = posList;
    }

    public HzYzFragLvItemAdapter(Context context, List<YiZhuBean.DataBean> dbYiZhuDaTaList, YZFragment yzFragment) {
        this.context = context;
        this.yzFragment = yzFragment;
        this.layoutInflater = LayoutInflater.from(context);
        setDbYiZhuDaTaList(dbYiZhuDaTaList);
    }

    public List<YiZhuBean.DataBean> getDbYiZhuDaTaList() {
        return dbYiZhuDaTaList;
    }

    public void setDbYiZhuDaTaList(List<YiZhuBean.DataBean> dbYiZhuDaTaList) {
        this.dbYiZhuDaTaList = dbYiZhuDaTaList;

        if (this.dbYiZhuDaTaList == null) this.dbYiZhuDaTaList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        if (dbYiZhuDaTaList == null) dbYiZhuDaTaList = new ArrayList<>();
        return dbYiZhuDaTaList.size();
    }

    @Override
    public YiZhuBean.DataBean getItem(int position) {
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
        initializeViews((YiZhuBean.DataBean) getItem(position), (ViewHolder) convertView.getTag(), position, convertView);
        return convertView;
    }

    private void initializeViews(final YiZhuBean.DataBean object, final ViewHolder holder, final int pos, final View convertView) {
        holder.mHzYzZhuangtaiIconTv.setVisibility(View.GONE);
        holder.hzYzFragLvBtTv.setTextColor(Color.parseColor("#FF767676"));
        String dateString1 = DateUtils.getDateString("yyyy/MM/dd", object.getStarttime());
        if (posList.get(pos)) {
            holder.mYzHdLxSjTv.setVisibility(View.VISIBLE);
            holder.mYzHdLxSjTv.setText(dateString1);
        } else {
            holder.mYzHdLxSjTv.setVisibility(View.GONE);
        }


        holder.hzYzFragLvBeizhu.setVisibility(View.INVISIBLE);
        holder.hzYzFragLvShuangren1.setVisibility(View.INVISIBLE);
        List<YiZhuBean.DataBean.DoctorOrdersBean> doctorOrders = object.getDoctorOrders();
        long orderExceptionsLastTime = 0;
        String lastNote = "";
        if (showExceptions(object)) {
            if (doctorOrders != null && doctorOrders.size() > 0) {
                for (YiZhuBean.DataBean.DoctorOrdersBean doctorOrdersBean : doctorOrders) {
                    List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean> orderExceptions = doctorOrdersBean.getOrderExceptions();
                    if (orderExceptions != null && orderExceptions.size() > 0) {
                        for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean orderExceptionsBean : orderExceptions) {
                            if (orderExceptionsBean != null && orderExceptionsBean.getCreationtime() != null) {
                                if ((orderExceptionsLastTime - orderExceptionsBean.getCreationtime().longValue()) < 0) {
                                    orderExceptionsLastTime = orderExceptionsBean.getCreationtime();
                                }
                                if (orderExceptionsLastTime == orderExceptionsBean.getCreationtime().longValue()
                                        && (!("1".equals(orderExceptionsBean.getIsdeleted() + "")))) {
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
        }
        String dateString = DateUtils.getDateString("HH:mm", object.getStarttime());
        holder.hzYzFragLvSjTv.setText(dateString);
        if (object.getOrdertype() != null) {
            switch (object.getOrdertype()) {
                case Constant.TYPE_YZ_JINGMAIZHUSHE:
                case Constant.TYPE_YZ_JIROUZHUSHE:
                case Constant.TYPE_YZ_PIXIAZHUSHE:
                    holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_injection);
                    AdapterUtil.setZhuangtaiImgv(object.getOrderststus(), holder.hzYzZhuangtaiImgv);
                    break;
                case Constant.TYPE_YZ_SHUYE:
                    holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_iinfusion);
                    AdapterUtil.setZhuangtaiImgv(object.getOrderststus(), holder.hzYzZhuangtaiImgv);
                    break;
                case Constant.TYPE_YZ_KOUFU:
                    holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_medicine);
                    AdapterUtil.setZhuangtaiImgv(object.getOrderststus(), holder.hzYzZhuangtaiImgv);
                    break;
                case Constant.TYPE_YZ_JIANYAN_UN_BLOOD:
                case Constant.TYPE_YZ_JIANYAN_BLOOD:
                    holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_bio);
                    AdapterUtil.setZhuangtaiImgv(object.getOrderststus(), holder.hzYzZhuangtaiImgv);
                    break;
                case Constant.TYPE_YZ_SHUXUE:
                case Constant.TYPE_YZ_CAIXUE:
                    holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_blood_transfusion);
                    AdapterUtil.setZhuangtaiImgv(object.getOrderststus(), holder.hzYzZhuangtaiImgv);
                    break;
                case Constant.TYPE_YZ_ZHILIAO:
                case Constant.TYPE_YZ_HULI:
                    holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.fuzhuzhiliao);
                    AdapterUtil.setZhuangtaiImgv(object.getOrderststus(), holder.hzYzZhuangtaiImgv);
                    break;
                case Constant.TYPE_YZ_SHANSHI:
                    holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.shanshi);
                    AdapterUtil.setZhuangtaiImgv(object.getOrderststus(), holder.hzYzZhuangtaiImgv);
                    break;
                case Constant.TYPE_YZ_RUHU:
                    holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.ruhu);
                    AdapterUtil.setZhuangtaiImgv(object.getOrderststus(), holder.hzYzZhuangtaiImgv);
                    break;
                case Constant.TYPE_YZ_PISHI:
                    holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_injection);
//                    if( null == object.getOrderresult() || "".equals(object.getOrderresult())){
//
//                    }
//                    AdapterUtil.setZhuangtaiImgv(object.getOrderststus(), holder.hzYzZhuangtaiImgv);
                    break;
                default:
                    break;
            }
        }
        holder.hzYzFragLvBgTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yzFragment.yzTiaoZhuan(pos);
            }
        });
        holder.hzYzFragLvBgTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (object != null && object.getPharmList() != null && object.getPharmList().size() > 0) {
                    YiZhuPopuwindow yiZhuPopuwindow = new YiZhuPopuwindow((Activity) context, convertView, object, holder.mYzShuxueXuanzhongCheckBox, null);
                    yiZhuPopuwindow.showPopupWindow();
                } else {
                    Toast.makeText(context, "该医嘱没有药品信息!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        holder.hzYzZhuangtaiTv.setText(object.getOrderststus());
        holder.hzYzFragLvBtTv.setText(object.getOrdername());
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
        } else {
            holder.hzYzPgTv3.setVisibility(View.INVISIBLE);
        }
        if (Constant.YZ_TYPE_YIZHIXING.equals(object.getOrderststus())) {
            if (null == object.getOrderresult() || "".equals(object.getOrderresult())
                    && Constant.TYPE_YZ_PISHI.equals(object.getOrdertype())) {
                holder.hzYzZhuangtaiTv.setText(Constant.YZ_TYPE_DAIGUANCHA);
            } else {
                holder.hzYzFragLvBgTv.setBackgroundResource(R.drawable.ddd);
                holder.hzYzZhuangtaiTv.setTextColor(Color.parseColor("#9b9b9b"));
                if (Constant.TYPE_YZ_PISHI.equals(object.getOrdertype())) {//皮试结果显示
                    if (Constant.SKIN_RESULT_YIN.equals(object.getOrderresult())) {
                        holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.yinxing);
                        holder.hzYzFragLvBtTv.setText(object.getOrdername() + Constant.SKIN_RESULT_NAME_YIN);
                    } else {
                        holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.yangxing);
                        holder.hzYzFragLvBtTv.setTextColor(Color.RED);
                        holder.hzYzFragLvBtTv.setText(object.getOrdername() + Constant.SKIN_RESULT_NAME_YANG);
                    }
                }
                String dateString2 = null;
                try {
                    dateString2 = DateUtils.getDateString("yyyy/MM/dd HH:mm ", object.getLastupdatetime());
                } catch (Exception e) {
                    LogUtil.e("object.getLastUpdateTime() is null");
                }
                holder.hzYzZhuangtaiTv.setText(dateString2 + DataConverter.getUserName(object.getExecuteby()));
            }

        } else if (Constant.YZ_TYPE_YIQUXIAO.equals(object.getOrderststus())) {
            holder.hzYzFragLvBgTv.setBackgroundResource(R.drawable.login_edt_shape);
            holder.hzYzFragLvBtTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            holder.hzYzZhuangtaiTv.setTextColor(Color.parseColor("#dddddd"));
        } else if (Constant.YZ_TYPE_DAIHEDUI.equals(object.getOrderststus())) {
            holder.mHzYzZhuangtaiIconTv.setVisibility(View.VISIBLE);
            holder.hzYzZhuangtaiImgv.setImageResource(R.drawable.yuan_xing_shape);
            holder.mHzYzZhuangtaiIconTv.setText("2");
            if (object.getDoctorOrders() != null && object.getDoctorOrders().size() > 0 && object.getDoctorOrders().get(0) != null
                    && object.getDoctorOrders().get(0).getOrderExecuteRecords() != null) {
                List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> dbYiZhudata_orderExecuteRecords
                        = ActivityUtils.getDBYiZhudata_OrderExecuteRecords(object.getDoctorOrders().get(0).getOrderExecuteRecords());
                for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean
                        : dbYiZhudata_orderExecuteRecords) {
                    if (Constant.YZ_TYPE_DAIHEDUI.equals(object.getOrderststus())) {
                        if (Constant.YZ_EXECUTE_JOBTYPE_HEDUI.equals(orderExecuteRecordsBean.getJobtype())) {
                            holder.mHzYzZhuangtaiIconTv.setText("1/2");
                        }
                    }
                }
            }

        } else {
            holder.hzYzFragLvBgTv.setBackgroundResource(R.drawable.login_edt_shape);
            holder.hzYzFragLvBtTv.getPaint().setFlags(0);
            holder.hzYzZhuangtaiTv.setTextColor(Color.parseColor("#559bec"));
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
        private TextView mYzHdLxSjTv;
        private TextView mYzShuxueXuanzhongCheckBox;
        private TextView mHzYzZhuangtaiIconTv;


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
            mYzHdLxSjTv = (TextView) view.findViewById(R.id.yz_hd_lx_sj_tv);
            mYzShuxueXuanzhongCheckBox = (TextView) view.findViewById(R.id.yz_shuxue_xuanzhong_check_box);
            mHzYzZhuangtaiIconTv = (TextView) view.findViewById(R.id.hz_yz_zhuangtai_icon_tv);

            mHzYzFragLvItemRl = (LinearLayout) view.findViewById(R.id.hz_yz_frag_lv_item_rl);

        }
    }

    private boolean showExceptions(YiZhuBean.DataBean object) {
        boolean flag = false;
        if (object == null) {
            return flag;
        }
        if (object.getOrderststus() == null) {
            return flag;
        }
        switch (object.getOrderststus()) {
            //未核对
            case Constant.YZ_TYPE_WEIHEDUI:
                flag = true;
                break;
            //未执行
            case Constant.YZ_TYPE_WEIZHIXING:
                flag = true;
                break;
            //执行中
            case Constant.YZ_TYPE_ZHIXINGZHONG:
                flag = true;
                break;
            //已执行
            case Constant.YZ_TYPE_YIZHIXING:
                flag = false;
                break;
            //已暂停
            case Constant.YZ_TYPE_YIZANTING:
                flag = true;
                break;
            //已取消
            case Constant.YZ_TYPE_YIQUXIAO:
                flag = true;
                break;
            //已停止
            case Constant.YZ_TYPE_YITINGZHI:
                flag = true;
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }
}

