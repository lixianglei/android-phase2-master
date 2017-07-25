package com.ge.med.mobile.nursing.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.DoctorOrderDao;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.db.DBExceptionDefine;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.forjson.callback.INetworkHandler;
import com.ge.med.mobile.nursing.ui.activity.YZ_HD_RenActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_XiangQingActivity;
import com.ge.med.mobile.nursing.ui.component.RiskTag;
import com.ge.med.mobile.nursing.ui.view.SelfDialog;
import com.ge.med.mobile.nursing.ui.view.YiZhuPopuwindow;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class YzHdRenLvItemAdapter extends BaseAdapter implements INetworkHandler{
    private int i = -1;
    private List<YiZhuBean.DataBean> mEntities;
    private Map<Integer, TextView> cbMap;
    private YZ_HD_RenActivity context;
    private LayoutInflater layoutInflater;
    private Button mYzHdRenYichangBt;
    private int zid;
    private ImageView yichangIMGV;
    private TextView xuanzhongTV;
    private TextView xuanzhongTV1;
    private int pos;
    private Bundle mBundle;
    private YiZhuBean.DataBean dbYiZhuDaTa;
    private DoctorOrderDao mDoctorOrderDaoImpl;
    private Map<Integer, Boolean> sjPosList;
    public Map<Integer, Boolean> mCBFlag = null;
    public SelfDialog selfDialog;
    private YiZhuPopuwindow yiZhuPopuwindow;
    private String mRWLeiXing;
    private Map<String, String> yiChangMap;

    private String userId;
    private boolean isPiLiang;

    public boolean isPiLiang() {
        return isPiLiang;
    }

    public void setPiLiang(boolean piLiang) {
        isPiLiang = piLiang;
    }

    public Map<Integer, Boolean> getSjPosList() {
        return sjPosList;
    }


    public void setSjPosList(Map<Integer, Boolean> sjPosList) {
        this.sjPosList = sjPosList;
    }

    public YiZhuBean.DataBean getDbYiZhuDaTa() {
        return dbYiZhuDaTa;
    }

    public void setDbYiZhuDaTa(YiZhuBean.DataBean dbYiZhuDaTa) {
        this.dbYiZhuDaTa = dbYiZhuDaTa;
    }


    public Map<Integer, Boolean> getmCBFlag() {
        return mCBFlag;
    }

    public void setmCBFlag(Map<Integer, Boolean> mCBFlag) {
        this.mCBFlag = mCBFlag;
    }

    public Map<Integer, TextView> getCbMap() {
        return cbMap;
    }

    public void setCbMap(Map<Integer, TextView> cbMap) {
        this.cbMap = cbMap;
    }

    public List<YiZhuBean.DataBean> getmEntities() {
        return mEntities;
    }

    public void setmEntities(List<YiZhuBean.DataBean> mEntities) {
        if (mEntities == null) {
            mEntities = new ArrayList<>();
        }
        this.mEntities = mEntities;
        cbMap = new HashMap<>();
        init();
        for (YiZhuBean.DataBean dataBean : mEntities) {
            initYiChang(dataBean);
        }
        i = -1;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public TextView getXuanzhongTV1() {
        return xuanzhongTV1;
    }

    public void setXuanzhongTV1(TextView xuanzhongTV1) {
        this.xuanzhongTV1 = xuanzhongTV1;
    }

    private CheckBox yichangCB;

    public CheckBox getYichangCB() {
        return yichangCB;
    }

    public void setYichangCB(CheckBox yichangCB) {
        this.yichangCB = yichangCB;
    }

    public TextView getXuanzhongTV() {
        return xuanzhongTV;
    }

    public void setXuanzhongTV(TextView xuanzhongTV) {
        this.xuanzhongTV = xuanzhongTV;
    }

    public ImageView getYichangIMGV() {
        return yichangIMGV;
    }

    public void setYichangIMGV(ImageView yichangIMGV) {
        this.yichangIMGV = yichangIMGV;
    }

    public TextView getYichangTV() {
        return yichangTV;
    }

    public void setYichangTV(TextView yichangTV) {
        this.yichangTV = yichangTV;
    }

    private TextView yichangTV;

    public int getZid() {
        return zid;
    }

    public void setZid(int zid) {
        this.zid = zid;
    }

    public YzHdRenLvItemAdapter(Context context, List<YiZhuBean.DataBean> entities, Button YzHdRenYichangBt
            , Bundle mBundle, String renWuLeiXing, String userid) {
        userId = userid;
        this.context = (YZ_HD_RenActivity) context;
        selfDialog = new SelfDialog(context);
        selfDialog.setTitle("再次核对异常医嘱");
        selfDialog.setMessage("如问题以解决，请点击“确定”继续完成核对。");
        mRWLeiXing = renWuLeiXing;
        selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });
        this.layoutInflater = LayoutInflater.from(context);
        if (entities == null) {
            entities = new ArrayList<>();
        }
        mDoctorOrderDaoImpl = new DoctorOrderDaoImpl();
        this.mEntities = entities;
        for (YiZhuBean.DataBean dataBean : mEntities) {
            initYiChang(dataBean);
        }
        mYzHdRenYichangBt = YzHdRenYichangBt;
        cbMap = new HashMap<>();
        this.mBundle = mBundle;
        init();
    }

    //初始化CheckBox状态
    void init() {
        mCBFlag = new HashMap<Integer, Boolean>();
        if (mEntities != null) {
            for (int i = 0; i < mEntities.size(); i++) {
                mCBFlag.put(i, false);
            }
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
            convertView = layoutInflater.inflate(R.layout.yz_hd_ren_lv_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((YiZhuBean.DataBean) getItem(position), (ViewHolder) convertView.getTag(), position, convertView);
        return convertView;
    }

    private void initializeViews(final YiZhuBean.DataBean entity, final ViewHolder holder, final int position, final View convertView) {

        String dateString1 = null;
        try {
            dateString1 = DateUtils.getDateString("yyyy/MM/dd", entity.getStarttime());
        } catch (Exception e) {
            LogUtil.e(e.getMessage()+"");
        }
        holder.yizhuHeduiRenLvItemCheckBox.setEnabled(true);
        holder.yizhuHeduiRenLvItemCheckBox.setClickable(true);
        holder.mHzYzZhuangtaiIconTv.setVisibility(View.GONE);
        holder.yizhuHeduiRenLvItemCheckBox.setBackgroundResource(R.drawable.login_edt_shape);
        holder.yizhuHeduiRenLvItemChakanxiangqingTv.setBackgroundResource(R.drawable.login_edt_shape);
        holder.yizhuHeduiRenLvItemChakanxiangqingTv.setEnabled(true);
        holder.yizhuHeduiRenLvItemChakanxiangqingTv.setClickable(true);
        if (sjPosList.get(position)) {
            holder.mYzHdLxSjTv.setVisibility(View.VISIBLE);
            holder.mYzHdLxSjTv.setText(dateString1);
        } else {
            holder.mYzHdLxSjTv.setVisibility(View.GONE);
        }
        holder.yizhuHeduiRenLvItemZhuangtaiImgv.setVisibility(View.VISIBLE);
        if (isPiLiang) {
            holder.yizhuHeduiRenLvItemZhuangtaiImgv.setImageResource(R.mipmap.icon_complete);
        } else {
            holder.yizhuHeduiRenLvItemZhuangtaiImgv.setImageResource(AdapterUtil.findYZImage(entity));
        }
        holder.hzYzPgTv1.setVisibility(View.INVISIBLE);
        holder.hzYzPgTv2.setVisibility(View.INVISIBLE);
        holder.hzYzPgTv3.setVisibility(View.INVISIBLE);
        holder.hzYzPgTv4.setVisibility(View.INVISIBLE);
        holder.yizhuHeduiRenLvItemNeirongTv.setText(entity.getOrdername());
        holder.mYizhuHeduiRenLvItemTujingTv.setText(entity.getOrderfrequency() + " " + entity.getRoutedesc());
        LogUtil.d("医嘱频次" + entity.getOrderfrequency());
        holder.hzYzPgTv1.setVisibility(View.INVISIBLE);
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
        String dateString = DateUtils.getDateString("HH:mm", entity.getStarttime());
        holder.yizhuHeduiRenLvItemSjTv.setText(dateString);
        holder.yizhuHeduiRenLvItemChakanxiangqingTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entity.getPharmList() == null || entity.getPharmList().size() <= 0) {
                    Toast.makeText(context, "该医嘱没有详情！", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbYiZhuDaTa = entity;
                Intent intent = new Intent(context, YZ_XiangQingActivity.class);
                mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU, entity);
                if (mBundle != null) {
                    intent.putExtra(Constant.GLOBAL_KEY_DATA, mBundle);
                }
                context.startActivityForResult(intent, 0);
            }
        });
        //长按显示药品详情
        holder.yizhuHeduiRenLvItemChakanxiangqingTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (entity != null && entity.getPharmList() != null && entity.getPharmList().size() > 0) {
                    yiZhuPopuwindow = new YiZhuPopuwindow((Activity) context, convertView, entity, null, YzHdRenLvItemAdapter.this);
                    yiZhuPopuwindow.showPopupWindow();
                } else {
                    Toast.makeText(context, "该医嘱没有药品信息!", Toast.LENGTH_SHORT).show();
                    return true;
                }
                i = position;
                if (Constant.BUNDLE_KEY_VALUE_BAIYAO.equals(mRWLeiXing) || Constant.BUNDLE_KEY_VALUE_PEIYE.equals(mRWLeiXing)) {
                    holder.mYzShuxueXuanzhongCheckBox.setVisibility(View.VISIBLE);
                    holder.mYzShuxueXuanzhongCheckBox1.setVisibility(View.VISIBLE);
                    if(yiZhuPopuwindow!=null){
                        yiZhuPopuwindow.setOptFor(true, holder.mYzShuxueXuanzhongCheckBox, holder.mYzShuxueXuanzhongCheckBox1);
                    }
                } else {
                    check(holder, position, entity);
                    holder.yizhuHeduiRenLvItemCheckBox.setChecked(true);
                }
                return true;
            }
        });
        holder.yizhuHeduiRenLvItemCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Constant.BUNDLE_KEY_VALUE_PEIYE.equals(mRWLeiXing)
                        || Constant.BUNDLE_KEY_VALUE_BAIYAO.equals(mRWLeiXing)) && !isPiLiang) {
                    holder.yizhuHeduiRenLvItemCheckBox.setChecked(false);
                    holder.yizhuHeduiRenLvItemChakanxiangqingTv.performClick();
                } else {
                    if (mDoctorOrderDaoImpl.getExceptionForCheck(entity.getId()) != null) {
                        mCBFlag.put(position, false);
                        selfDialog.setYesOnclickListener("确认", new SelfDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                try {
                                    if (Constant.YZ_TYPE_DAIBAIYAO.equals(entity.getOrderststus())
                                            || Constant.YZ_TYPE_DAIPEIYE.equals(entity.getOrderststus())) {
                                        String s = yiChangMap.get(entity.getId() + "");
                                        for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean orderExceptionsBean : entity.getDoctorOrders().get(0).getOrderExceptions()) {
                                            if (s.equals(orderExceptionsBean.getId() + "")) {
                                                orderExceptionsBean.setIsdeleted(1 + "");
                                            }
                                        }
                                        new DoctorOrderDaoImpl().saveDoctorOrder(entity, true);
                                        NetworkForDoctorOrder.submitSingleDoctorOrder(YzHdRenLvItemAdapter.this, entity);
                                    }
                                } catch (Exception e) {
                                    LogUtil.e(e.getMessage() + "");
                                }
                                holder.yizhuHeduiRenLvItemCheckBox.setChecked(true);
                                mDoctorOrderDaoImpl.saveExceptionForCheck(entity.getId(), null);
                                holder.yizhuHeduiRenLvItemCheckBox.setChecked(false);
                                holder.yizhuHeduiRenLvItemCheckBox.performClick();
                                notifyDataSetChanged();
                                selfDialog.dismiss();
                            }
                        });
                        selfDialog.show();
                    } else {
                        i = position;
                        if (holder.yizhuHeduiRenLvItemCheckBox.isChecked()) {
                            check(holder, position, entity);
                            peiYeChuLi(entity, holder, 0);
                        } else {
                            cbMap.remove(position);
                            mCBFlag.put(position, false);
                            holder.mYzShuxueXuanzhongCheckBox.setVisibility(View.GONE);
                            holder.yizhuHeduiRenLvItemCheckImgv.setVisibility(View.GONE);
                            holder.yizhuHeduiRenLvItemZhuangtaiTv.setText(entity.getOrderststus());
                            holder.yizhuHeduiRenLvItemZhuangtaiTv.setTextColor(R.color.se559bec);
                            holder.mYzShuxueXuanzhongCheckBox1.setVisibility(View.GONE);
                            peiYeChuLi(entity, holder, 1);
                        }
                        if (holder.mYzShuxueXuanzhongCheckBox.getVisibility() == View.VISIBLE) {
                            mYzHdRenYichangBt.setEnabled(true);
                        } else {
                            mYzHdRenYichangBt.setEnabled(false);
                        }
                    }
                    notifyDataSetChanged();
                }

            }
        });
        holder.yizhuHeduiRenLvItemCheckBox.setChecked(mCBFlag.get(position));

        if (mCBFlag.get(position)) {
            holder.mYzShuxueXuanzhongCheckBox1.setVisibility(View.VISIBLE);
            if (pos == position) {
                holder.mYzShuxueXuanzhongCheckBox.setVisibility(View.VISIBLE);
                holder.mYzShuxueXuanzhongCheckBox.setVisibility(View.VISIBLE);
                zid = entity.getId();
                yichangTV = holder.yizhuHeduiRenLvItemZhuangtaiTv;
                yichangIMGV = holder.yizhuHeduiRenLvItemCheckImgv;
                yichangCB = holder.yizhuHeduiRenLvItemCheckBox;
                xuanzhongTV = holder.mYzShuxueXuanzhongCheckBox;
                xuanzhongTV1 = holder.mYzShuxueXuanzhongCheckBox1;
                pos = position;
                dbYiZhuDaTa = entity;
            } else {
                holder.mYzShuxueXuanzhongCheckBox.setVisibility(View.GONE);
            }
            holder.yizhuHeduiRenLvItemCheckImgv.setVisibility(View.VISIBLE);
            holder.yizhuHeduiRenLvItemCheckImgv.setImageResource(R.mipmap.icon_select);
            if (!(Constant.BUNDLE_KEY_VALUE_BAIYAO.equals(mRWLeiXing) || Constant.BUNDLE_KEY_VALUE_PEIYE.equals(mRWLeiXing))) {
                holder.yizhuHeduiRenLvItemZhuangtaiTv.setText("已核对");
            }
            if(Constant.BUNDLE_KEY_VALUE_BAIYAO.equals(mRWLeiXing)){
                holder.yizhuHeduiRenLvItemZhuangtaiTv.setText("已摆药");
            }
            holder.yizhuHeduiRenLvItemZhuangtaiTv.setTextColor(R.color.se559bec);
            peiYeChuLi(entity, holder, 0);
        } else {
            holder.mYzShuxueXuanzhongCheckBox.setVisibility(View.GONE);
            holder.yizhuHeduiRenLvItemCheckImgv.setVisibility(View.GONE);
            holder.yizhuHeduiRenLvItemZhuangtaiTv.setText(entity.getOrderststus());
            holder.yizhuHeduiRenLvItemZhuangtaiTv.setTextColor(R.color.se559bec);
            holder.mYzShuxueXuanzhongCheckBox1.setVisibility(View.GONE);
            peiYeChuLi(entity, holder, 1);
        }
        //        if (i == position) {
//            holder.mYzShuxueXuanzhongCheckBox.setVisibility(View.VISIBLE);
//        } else {
//            holder.mYzShuxueXuanzhongCheckBox.setVisibility(View.GONE);
//        }
        if (mDoctorOrderDaoImpl.getExceptionForCheck(entity.getId()) != null) {
            cbMap.remove(position);
            mCBFlag.put(position, false);
            holder.yizhuHeduiRenLvItemCheckBox.setChecked(false);
            holder.yizhuHeduiRenLvItemZhuangtaiTv.setText(mDoctorOrderDaoImpl.getExceptionForCheck(entity.getId()));
            holder.yizhuHeduiRenLvItemZhuangtaiTv.setTextColor(Color.RED);
            holder.yizhuHeduiRenLvItemCheckImgv.setVisibility(View.VISIBLE);
            holder.yizhuHeduiRenLvItemCheckImgv.setImageResource(R.mipmap.icon_stop);
            holder.yizhuHeduiRenLvItemZhuangtaiImgv.setVisibility(View.GONE);
            holder.mYzShuxueXuanzhongCheckBox.setVisibility(View.GONE);
            holder.mYzShuxueXuanzhongCheckBox1.setVisibility(View.GONE);
        }

        if (entity.getPharmList() != null && entity.getPharmList().size() > 1 && Constant.BUNDLE_KEY_VALUE_BAIYAO.equals(mRWLeiXing)) {
            int i = entity.getPharmList().size();
            holder.mHzYzZhuangtaiIconTv.setVisibility(View.VISIBLE);
            holder.yizhuHeduiRenLvItemZhuangtaiImgv.setImageResource(R.drawable.yuan_xing_shape);
            //合并医嘱的操作
            if (entity.getDoctorOrders() != null && entity.getDoctorOrders().size() > 0 && entity.getDoctorOrders().get(0) != null
                    && entity.getDoctorOrders().get(0).getOrderExecuteRecords() != null) {
                for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean
                        : entity.getDoctorOrders().get(0).getOrderExecuteRecords()) {
                    if (Constant.YZ_EXECUTE_JOBTYPE_BAIYAO.equals(orderExecuteRecordsBean.getJobtype())) {
                        i--;
                    }
                }
            }
            holder.mHzYzZhuangtaiIconTv.setText(i + "/" + entity.getPharmList().size());
            if (isPiLiang) {
                holder.yizhuHeduiRenLvItemCheckBox.setEnabled(false);
                holder.yizhuHeduiRenLvItemCheckBox.setClickable(false);
                holder.yizhuHeduiRenLvItemCheckBox.setBackgroundResource(R.drawable.ddd);
                holder.yizhuHeduiRenLvItemChakanxiangqingTv.setBackgroundResource(R.drawable.ddd);
                holder.yizhuHeduiRenLvItemChakanxiangqingTv.setEnabled(false);
                holder.yizhuHeduiRenLvItemChakanxiangqingTv.setClickable(false);
                holder.yizhuHeduiRenLvItemZhuangtaiImgv.setImageResource(R.drawable.yuan_xing_shape_ddd);
            }
        }
    }

    private void initYiChang(YiZhuBean.DataBean entity) {
        if (Constant.YZ_TYPE_WEIHEDUI.equals(entity.getOrderststus())) {
            return;
        }
        yiChangMap = new HashMap<>();
        List<YiZhuBean.DataBean.DoctorOrdersBean> doctorOrders = entity.getDoctorOrders();
        long orderExceptionsLastTime = 0;
        String lastNote = "";
        if (doctorOrders != null && doctorOrders.size() > 0) {
            if (doctorOrders.get(0) != null) {
                List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean> orderExceptions = doctorOrders.get(0).getOrderExceptions();
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
                                    mDoctorOrderDaoImpl.saveExceptionForCheck(entity.getId(), lastNote);
                                    yiChangMap.put(entity.getId() + "", orderExceptionsBean.getId() + "");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void peiYeChuLi(YiZhuBean.DataBean entity, ViewHolder holder, int i) {
        if (Constant.BUNDLE_KEY_VALUE_PEIYE.equals(mRWLeiXing)) {
            holder.yizhuHeduiRenLvItemCheckImgv.setVisibility(View.GONE);
            boolean flag = false;
            if (entity.getDoctorOrders() != null && entity.getDoctorOrders().size() > 0 && entity.getDoctorOrders().get(0) != null
                    && entity.getDoctorOrders().get(0).getOrderExecuteRecords() != null
                    && entity.getDoctorOrders().get(0).getOrderExecuteRecords().size() > 0) {
                for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean
                        : entity.getDoctorOrders().get(0).getOrderExecuteRecords()) {
                    if (orderExecuteRecordsBean != null) {
                        if (Constant.YZ_EXECUTE_JOBTYPE_PEIYE.equals(orderExecuteRecordsBean.getJobtype())) {
                            if (orderExecuteRecordsBean.getUserid2() == null || orderExecuteRecordsBean.getUserid2().equals("0")) {
                                flag = true;
                                holder.mHzYzZhuangtaiIconTv.setVisibility(View.VISIBLE);
                                holder.yizhuHeduiRenLvItemZhuangtaiImgv.setVisibility(View.VISIBLE);
                                if (i == 0) {
                                    if (userId.equals(orderExecuteRecordsBean.getUserid())) {
                                        Toast.makeText(context, "双人确认不能是同一个人！", Toast.LENGTH_SHORT).show();
                                        holder.yizhuHeduiRenLvItemCheckBox.performClick();
                                    } else {
                                        holder.mHzYzZhuangtaiIconTv.setText("2/2");
                                        holder.yizhuHeduiRenLvItemZhuangtaiTv.setText("已双人确认");
                                        holder.yizhuHeduiRenLvItemZhuangtaiImgv.setImageResource(R.drawable.yuan_xing_shape);
                                    }
                                } else if (i == 1) {
                                    holder.mHzYzZhuangtaiIconTv.setText("1/2");
                                    holder.yizhuHeduiRenLvItemZhuangtaiTv.setText("待双人确认");
                                    if (!isPiLiang) {
                                        holder.yizhuHeduiRenLvItemZhuangtaiImgv.setImageResource(R.drawable.yuan_xing_shape);
                                    } else {
                                        holder.yizhuHeduiRenLvItemZhuangtaiImgv.setImageResource(R.drawable.yuan_xing_shape_ddd);
                                    }
                                }
                            } else {
                                holder.mHzYzZhuangtaiIconTv.setVisibility(View.VISIBLE);
                                holder.yizhuHeduiRenLvItemZhuangtaiImgv.setVisibility(View.VISIBLE);
                                holder.mHzYzZhuangtaiIconTv.setVisibility(View.VISIBLE);
                                holder.yizhuHeduiRenLvItemZhuangtaiImgv.setImageResource(R.drawable.yuan_xing_shape);
                                holder.mHzYzZhuangtaiIconTv.setText("2/2");
                                holder.yizhuHeduiRenLvItemZhuangtaiTv.setText("已双人确认");
                            }
                        }
                    }
                }
            }
            if (!flag && i == 0) {
                holder.mHzYzZhuangtaiIconTv.setVisibility(View.VISIBLE);
                holder.yizhuHeduiRenLvItemZhuangtaiImgv.setImageResource(R.drawable.yuan_xing_shape);
                holder.mHzYzZhuangtaiIconTv.setText("1/2");
                holder.yizhuHeduiRenLvItemZhuangtaiTv.setText("待双人确认");
            }
        }
    }

    private void check(ViewHolder holder, int position, YiZhuBean.DataBean entity) {
        mCBFlag.put(position, true);
        cbMap.put(position, holder.mYzShuxueXuanzhongCheckBox);
        holder.mYzShuxueXuanzhongCheckBox.setVisibility(View.VISIBLE);
        holder.mYzShuxueXuanzhongCheckBox1.setVisibility(View.VISIBLE);
        holder.yizhuHeduiRenLvItemCheckImgv.setVisibility(View.VISIBLE);
        holder.yizhuHeduiRenLvItemCheckImgv.setImageResource(R.mipmap.icon_select);
        if (!(Constant.BUNDLE_KEY_VALUE_BAIYAO.equals(mRWLeiXing) || Constant.BUNDLE_KEY_VALUE_PEIYE.equals(mRWLeiXing))) {
            holder.yizhuHeduiRenLvItemZhuangtaiTv.setText("已核对");
        }
        holder.yizhuHeduiRenLvItemZhuangtaiTv.setTextColor(R.color.se559bec);
        Iterator it = cbMap.entrySet().iterator();
        int key;
        TextView value;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            key = (int) entry.getKey();
            value = (TextView) entry.getValue();
            value.setVisibility(View.GONE);
        }
        if (i == position) {
            holder.mYzShuxueXuanzhongCheckBox.setVisibility(View.VISIBLE);
            zid = entity.getId();
            yichangTV = holder.yizhuHeduiRenLvItemZhuangtaiTv;
            yichangIMGV = holder.yizhuHeduiRenLvItemCheckImgv;
            yichangCB = holder.yizhuHeduiRenLvItemCheckBox;
            xuanzhongTV = holder.mYzShuxueXuanzhongCheckBox;
            xuanzhongTV1 = holder.mYzShuxueXuanzhongCheckBox1;
            pos = position;
            dbYiZhuDaTa = entity;
        }
    }

    @Override
    public void handleOnError() {

    }

    @Override
    public void handleOnError(String urlStr) {

    }

    @Override
    public void handleSuccess(Object obj) {

    }

    protected class ViewHolder {
        private TextView yizhuHeduiRenLvItemChakanxiangqingTv;
        private TextView yizhuHeduiRenLvItemNeirongTv;
        private View xian1;
        private LinearLayout hzYzPgLl;
        private TextView hzYzPgTv1;
        private TextView hzYzPgTv2;
        private TextView hzYzPgTv3;
        private TextView hzYzPgTv4;
        private ImageView yizhuHeduiRenLvItemZhuangtaiImgv;
        private TextView yizhuHeduiRenLvItemZhuangtaiTv;
        private TextView yizhuHeduiRenLvItemSjTv;
        private CheckBox yizhuHeduiRenLvItemCheckBox;
        private ImageView yizhuHeduiRenLvItemCheckImgv;
        private TextView mYzShuxueXuanzhongCheckBox;
        private TextView mYzShuxueXuanzhongCheckBox1;
        private TextView mYzHdLxSjTv;
        private TextView mHzYzZhuangtaiIconTv;
        private TextView mYizhuHeduiRenLvItemTujingTv;

        public ViewHolder(View view) {
            yizhuHeduiRenLvItemChakanxiangqingTv = (TextView) view.findViewById(R.id.yizhu_hedui_ren_lv_item_chakanxiangqing_tv);
            yizhuHeduiRenLvItemNeirongTv = (TextView) view.findViewById(R.id.yizhu_hedui_ren_lv_item_neirong_tv);
            xian1 = (View) view.findViewById(R.id.xian1);
            hzYzPgLl = (LinearLayout) view.findViewById(R.id.hz_yz_pg_ll);
            hzYzPgTv1 = (TextView) view.findViewById(R.id.hz_yz_pg_tv1);
            hzYzPgTv2 = (TextView) view.findViewById(R.id.hz_yz_pg_tv2);
            hzYzPgTv3 = (TextView) view.findViewById(R.id.hz_yz_pg_tv3);
            hzYzPgTv4 = (TextView) view.findViewById(R.id.hz_yz_pg_tv4);
            yizhuHeduiRenLvItemZhuangtaiImgv = (ImageView) view.findViewById(R.id.yizhu_hedui_ren_lv_item_zhuangtai_imgv);
            yizhuHeduiRenLvItemZhuangtaiTv = (TextView) view.findViewById(R.id.yizhu_hedui_ren_lv_item_zhuangtai_tv);
            yizhuHeduiRenLvItemSjTv = (TextView) view.findViewById(R.id.yizhu_hedui_ren_lv_item_sj_tv);
            yizhuHeduiRenLvItemCheckBox = (CheckBox) view.findViewById(R.id.yizhu_hedui_ren_lv_item_check_box);
            yizhuHeduiRenLvItemCheckImgv = (ImageView) view.findViewById(R.id.yizhu_hedui_ren_lv_item_check_imgv);
            mYzShuxueXuanzhongCheckBox = (TextView) view.findViewById(R.id.yz_shuxue_xuanzhong_check_box);
            mYzShuxueXuanzhongCheckBox1 = (TextView) view.findViewById(R.id.yz_hd_ren_check_box1);
            mYzHdLxSjTv = (TextView) view.findViewById(R.id.yz_hd_lx_sj_tv);
            mHzYzZhuangtaiIconTv = (TextView) view.findViewById(R.id.hz_yz_zhuangtai_icon_tv);
            mYizhuHeduiRenLvItemTujingTv = (TextView) view.findViewById(R.id.yizhu_hedui_ren_lv_item_tujing_tv);

        }
    }
}
