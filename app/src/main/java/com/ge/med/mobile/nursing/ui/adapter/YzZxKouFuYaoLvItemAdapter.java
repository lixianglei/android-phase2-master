package com.ge.med.mobile.nursing.ui.adapter;

/**
 * Created by Administrator on 2016/11/16.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class YzZxKouFuYaoLvItemAdapter extends BaseAdapter {

    private List<YiZhuBean.DataBean> mEntities;
    private int i = -1;
    private int p = -1;
    private Context context;
    private LayoutInflater layoutInflater;
    public Map<Integer, Boolean> mCBFlag = null;
    private Map<Integer, TextView> cbMap;
    private Button mYzHdLxYichangBt;
    private YiZhuBean.DataBean dataBean;
    private Map<Integer, Boolean> posList;
    private String type;

    public Map<Integer, Boolean> getPosList() {
        return posList;
    }

    public void setPosList(Map<Integer, Boolean> posList) {
        this.posList = posList;
    }

    public YzZxKouFuYaoLvItemAdapter(Context context, List<YiZhuBean.DataBean> entities, Button mYzHdLxYichangBt, String type) {
        this.type = type;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mYzHdLxYichangBt = mYzHdLxYichangBt;
        if (entities == null) {
            this.mEntities = new ArrayList<>();
        } else {
            this.mEntities = entities;
        }
        cbMap = new HashMap<>();
        init();
    }

    public YiZhuBean.DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(YiZhuBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    public Map<Integer, Boolean> getmCBFlag() {
        return mCBFlag;
    }

    public void setmCBFlag(Map<Integer, Boolean> mCBFlag) {
        this.mCBFlag = mCBFlag;
    }

    public List<YiZhuBean.DataBean> getmEntities() {
        return mEntities;
    }

    public void setmEntities(List<YiZhuBean.DataBean> mEntities) {
        if (mEntities == null) {
            this.mEntities = new ArrayList<>();
        } else {
            this.mEntities = mEntities;
        }
        cbMap = new HashMap<>();
        i = -1;
        init();
    }

    void init() {
        mCBFlag = new HashMap<Integer, Boolean>();
        for (int i = 0; i < mEntities.size(); i++) {
            mCBFlag.put(i, false);
        }
    }

    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public YiZhuBean.DataBean getItem(int position) {
        return mEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.yz_zx_kou_fu_yao_lv_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((YiZhuBean.DataBean) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(final YiZhuBean.DataBean entity, final ViewHolder holder, final int position) {
        String dateString = DateUtils.getDateString("HH:mm", entity.getStarttime());
        String dateStringpos = DateUtils.getDateString("yyyy/MM/dd", entity.getStarttime());
        if (posList!=null&&posList.get(position)) {
            holder.mYzHdLxSjTv.setVisibility(View.VISIBLE);
            holder.mYzHdLxSjTv.setText(dateStringpos);
        } else {
            holder.mYzHdLxSjTv.setVisibility(View.GONE);
        }
        holder. mHzYzPgTv1.setVisibility(View.INVISIBLE);
        holder.hzYzFragLvSjTv.setText(dateString);
        holder.hzYzFragLvBtTv.setText(entity.getOrdername());
        holder.hzYzZhuangtaiTv.setText(entity.getOrderststus());

        holder.hzYzFragLvBeizhu.setVisibility(View.INVISIBLE);
        holder.hzYzFragLvShuangren1.setVisibility(View.INVISIBLE);
        List<YiZhuBean.DataBean.DoctorOrdersBean> doctorOrders = entity.getDoctorOrders();
        long orderExceptionsLastTime = 0;
        String lastNote = "";
        if (showExceptions(entity)) {
            if (doctorOrders != null && doctorOrders.size() > 0) {
                for (YiZhuBean.DataBean.DoctorOrdersBean doctorOrdersBean : doctorOrders) {
                    List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> orderExecuteRecords = doctorOrdersBean.getOrderExecuteRecords();
                    List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean> orderExceptions = doctorOrdersBean.getOrderExceptions();
                    if (orderExceptions != null && orderExceptions.size() > 0) {
                        for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean orderExceptionsBean : orderExceptions) {
                            if (orderExceptionsBean != null && orderExceptionsBean.getCreationtime() != null) {
                                if ((orderExceptionsLastTime - orderExceptionsBean.getCreationtime()) < 0) {
                                    orderExceptionsLastTime = orderExceptionsBean.getCreationtime();
                                }
                                if (orderExceptionsLastTime == orderExceptionsBean.getCreationtime()
                                        && (!("1".equals(orderExceptionsBean.getIsdeleted()+""))) ) {
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
                        String dateString1 = DateUtils.getDateString("yyyy/MM/dd HH:mm", orderExceptionsLastTime);
                        holder.hzYzFragLvShuangren1.setText(dateString1);
                    }
                }
            }
        }

        holder.hzYzZhuangtaiImgv.setImageResource(AdapterUtil.findYZImage(entity));
        holder.mYzShuxueXuanzhongCheckBox.setChecked(mCBFlag.get(position));

        AdapterUtil.setZhuangtaiImgv(entity.getOrderststus(), holder.hzYzZhuangtaiImgv);
        holder.mYzShuxueXuanzhongCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (ActivityUtils.isExeTime(entity.getStarttime())&&!( Constant.HULI_TYPR_YIZHU_BEIZHU.equals(type))) {
//                    Toast.makeText(context, "不在执行时间范围内！(前后2小时)", Toast.LENGTH_SHORT).show();
//                    holder.mYzShuxueXuanzhongCheckBox.setChecked(false);
//                    return;
//                }
                i = position;
                Iterator it = cbMap.entrySet().iterator();
                int key;
                TextView value;
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    key = (int) entry.getKey();
                    value = (TextView) entry.getValue();
                    value.setVisibility(View.GONE);
                }
                mCBFlag.put(position, holder.mYzShuxueXuanzhongCheckBox.isChecked());
                if (holder.mYzShuxueXuanzhongCheckBox.isChecked()) {
                    cbMap.put(position, holder.mYzShuxueXuanzhongCheckTv);
                    if(Constant.HULI_TYPR_YIZHU_BEIZHU.equals(type)){
                        for (Map.Entry<Integer, Boolean> entry : mCBFlag.entrySet()) {
                            int pos = entry.getKey();
                           if(pos != position){
                               mCBFlag.put(pos,false);
                           }
                        }
                    }
                    holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_select);
                    if (i == position) {
                        p = position;
                        dataBean = entity;
                        mYzHdLxYichangBt.setEnabled(true);
                        holder.mYzShuxueXuanzhongCheckTv.setVisibility(View.VISIBLE);
                    }
                } else {
                    cbMap.remove(position);
                    mYzHdLxYichangBt.setEnabled(false);
                    holder.hzYzZhuangtaiImgv.setImageResource(AdapterUtil.findYZImage(entity));
                    holder.mYzShuxueXuanzhongCheckTv.setVisibility(View.GONE);
                }
                notifyDataSetChanged();
            }
        });
        if (mCBFlag.get(position)) {
            holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_select);
            if (p == position) {
                holder.mYzShuxueXuanzhongCheckTv.setVisibility(View.VISIBLE);
            } else {
                holder.mYzShuxueXuanzhongCheckTv.setVisibility(View.GONE);
            }
        } else {
            holder.hzYzZhuangtaiImgv.setImageResource(AdapterUtil.findYZImage(entity));
            holder.mYzShuxueXuanzhongCheckTv.setVisibility(View.GONE);
        }

        if (Constant.ORDER_DURATION_LINGSHI_0.equals(entity.getOrderduration())) {
            holder.hzYzPgTv3.setVisibility(View.VISIBLE);
            holder.hzYzPgTv3.setText(Constant.ORDER_DURATION_LINGSHI);
            holder.hzYzPgTv3.setTextColor(Color.parseColor("#adadad"));
        } else if (Constant.ORDER_DURATION_CHANGQI_1.equals(entity.getOrderduration())) {
            holder.hzYzPgTv3.setVisibility(View.VISIBLE);
            holder.hzYzPgTv3.setText(Constant.ORDER_DURATION_CHANGQI);
            holder.hzYzPgTv3.setTextColor(Color.parseColor("#adadad"));
        } else if (Constant.ORDER_DURATION_LIJI_2.equals(entity.getOrderduration())) {
            holder.hzYzPgTv3.setVisibility(View.VISIBLE);
            holder.hzYzPgTv3.setText(Constant.ORDER_DURATION_LIJI);
            holder.hzYzPgTv3.setTextColor(Color.RED);
        } else {
            holder.hzYzPgTv3.setVisibility(View.INVISIBLE);
        }
        holder.mYzShuxueXuanzhongCheckBox.setEnabled(true);
        if (Constant.YZ_TYPE_YIZHIXING.equals(entity.getOrderststus())) {
            holder.mYzShuxueXuanzhongCheckBox.setEnabled(false);
            holder.hzYzFragLvBgTv.setBackgroundResource(R.drawable.ddd);
            holder.hzYzZhuangtaiTv.setTextColor(Color.parseColor("#9b9b9b"));
            String dateString2 = null;
            try {
                dateString2 = DateUtils.getDateString("yyyy/MM/dd HH:mm ", entity.getLastupdatetime());
            } catch (Exception e) {
                LogUtil.e("object.getLastUpdateTime() is null");
            }
            holder.hzYzZhuangtaiTv.setText(dateString2 + DataConverter.getUserName(entity.getExecuteby()));
            holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_complete);
        } else if (Constant.YZ_TYPE_YIQUXIAO.equals(entity.getOrderststus())) {
            holder.hzYzFragLvBgTv.setBackgroundResource(R.drawable.login_edt_shape);
            holder.mYzShuxueXuanzhongCheckBox.setEnabled(false);
            holder.hzYzFragLvBtTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            holder.hzYzZhuangtaiTv.setTextColor(Color.parseColor("#dddddd"));
            holder.hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_cancel);
        } else {
            holder.hzYzFragLvBgTv.setBackgroundResource(R.drawable.login_edt_shape);
            holder.hzYzFragLvBtTv.getPaint().setFlags(0);
            holder.hzYzZhuangtaiTv.setTextColor(Color.parseColor("#559bec"));
            holder.mYzShuxueXuanzhongCheckBox.setEnabled(true);
        }
    }

    protected class ViewHolder {
        private RelativeLayout hzYzFragLvItemRl;
        private TextView hzYzFragLvBgTv;
        private TextView hzYzFragLvSjTv;
        private TextView hzYzFragLvBtTv;
        private TextView hzYzFragLvBeizhu;
        private TextView hzYzFragLvShuangren1;
        private TextView hzYzFragLvXianTv;
        private LinearLayout hzYzPgLl;
        private ImageView hzYzZhuangtaiImgv;
        private TextView hzYzZhuangtaiTv;
        private TextView hzYzPgTv3;
        private CheckBox mYzShuxueXuanzhongCheckBox;
        private TextView mYzShuxueXuanzhongCheckTv;
        private TextView mHzYzPgTv1;
        private TextView mYzHdLxSjTv;


        public ViewHolder(View view) {
            hzYzFragLvItemRl = (RelativeLayout) view.findViewById(R.id.hz_yz_frag_lv_item_rl);
            hzYzFragLvBgTv = (TextView) view.findViewById(R.id.hz_yz_frag_lv_bg_tv);
            hzYzFragLvSjTv = (TextView) view.findViewById(R.id.hz_yz_frag_lv_sj_tv);
            hzYzFragLvBtTv = (TextView) view.findViewById(R.id.hz_yz_frag_lv_bt_tv);
            hzYzFragLvBeizhu = (TextView) view.findViewById(R.id.hz_yz_frag_lv_beizhu);
            hzYzFragLvShuangren1 = (TextView) view.findViewById(R.id.hz_yz_frag_lv_shuangren1);
            hzYzFragLvXianTv = (TextView) view.findViewById(R.id.hz_yz_frag_lv_xian_tv);
            hzYzPgLl = (LinearLayout) view.findViewById(R.id.hz_yz_pg_ll);
            hzYzZhuangtaiImgv = (ImageView) view.findViewById(R.id.hz_yz_zhuangtai_imgv);
            hzYzZhuangtaiTv = (TextView) view.findViewById(R.id.hz_yz_zhuangtai_tv);
            hzYzPgTv3 = (TextView) view.findViewById(R.id.hz_yz_pg_tv3);
            mHzYzPgTv1 = (TextView) view.findViewById(R.id.hz_yz_pg_tv1);
            mYzHdLxSjTv = (TextView) view.findViewById(R.id.yz_hd_lx_sj_tv);

            mYzShuxueXuanzhongCheckBox = (CheckBox) view.findViewById(R.id.yz_shuxue_xuanzhong_check_box);
            mYzShuxueXuanzhongCheckTv = (TextView) view.findViewById(R.id.yz_shuxue_xuanzhong_check_tv);


        }
    }

    private boolean showExceptions(YiZhuBean.DataBean object) {
        if (object == null) {
            return false;
        }
        if (object.getOrderststus() == null) {
            return false;
        }
        switch (object.getOrderststus()) {
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
