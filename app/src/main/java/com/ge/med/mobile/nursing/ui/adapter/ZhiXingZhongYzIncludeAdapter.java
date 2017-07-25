package com.ge.med.mobile.nursing.ui.adapter;

/**
 * Created by Administrator on 2016/12/10.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.XinjianJiaoBanBean;
import com.ge.med.mobile.nursing.forjson.entity.VitalDefineEntity;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignRecord;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignSheet;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignWardDefine;
import com.ge.med.mobile.nursing.ui.activity.ChangGuiHuLiActivity;
import com.ge.med.mobile.nursing.ui.activity.VitalSignSheetActivity;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.JiaoBanEdt;
import com.ge.med.mobile.nursing.utils.Utility;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZhiXingZhongYzIncludeAdapter extends BaseAdapter {

    private final JiaobanChuruliangListItemAdapter jiaobanChuruliangListItemAdapter;
    private List<XinjianJiaoBanBean.DataBean.PatientBean.ExecutingOrderListBean> mEntities;
    private HuanZheLieBiaoBean.DataBean dataBean;

    private List<VitalSignWardDefine> mVitalDefines = null;
    private VitalSignSheet vitalSignSheet;
    public Map<Integer, Boolean> mCBFlag = null;
    private Context context;
    private JiaoBanEdt jiaoBanEdt;
    private LayoutInflater layoutInflater;
    private String edt = "";
    private XinjianJiaoBanBean.DataBean eventList;
    private boolean tiZhengXuanZhong = false;
    private ArrayList<Integer> integers = new ArrayList<>();

    public ZhiXingZhongYzIncludeAdapter(Context context, List<XinjianJiaoBanBean.DataBean.PatientBean.ExecutingOrderListBean> entities
            , HuanZheLieBiaoBean.DataBean dataBean, Map<String, String> mEntitiesMap, XinjianJiaoBanBean.DataBean eventList, JiaoBanEdt jiaoBanEdt) {
        this.eventList = eventList;
        if (eventList != null && eventList.getWardVitalDefine() != null && eventList.getWardVitalDefine().size() > 0) {
            for (XinjianJiaoBanBean.DataBean.WardVitalDefineBean wardVitalDefineBean : eventList.getWardVitalDefine()) {
                integers.add(wardVitalDefineBean.getId());
            }
        }
        this.context = context;
        this.jiaoBanEdt = jiaoBanEdt;
        this.layoutInflater = LayoutInflater.from(context);
        if (entities == null || entities.size() == 0) {
            mEntities = new ArrayList<>();
            mEntities.add(new XinjianJiaoBanBean.DataBean.PatientBean.ExecutingOrderListBean());
        } else {
            this.mEntities = entities;
        }
        if (dataBean == null) {
            dataBean = new HuanZheLieBiaoBean.DataBean();
        }
        this.dataBean = dataBean;
        tiZhengXuanZhong = false;
        vitalSignSheet = null;
        jiaobanChuruliangListItemAdapter = new JiaobanChuruliangListItemAdapter(context, mEntitiesMap);
        init();
    }

    public Map<Integer, Boolean> getmCBFlag() {
        return mCBFlag;
    }

    public void setmCBFlag(Map<Integer, Boolean> mCBFlag) {
        this.mCBFlag = mCBFlag;
    }

    public boolean isTiZhengXuanZhong() {
        return tiZhengXuanZhong;
    }

    public void setTiZhengXuanZhong(boolean tiZhengXuanZhong) {
        this.tiZhengXuanZhong = tiZhengXuanZhong;
    }

    public String getEdt() {
        return edt;
    }

    public void setEdt(String edt) {
        this.edt = edt;
    }

    void init() {
        mCBFlag = new HashMap<Integer, Boolean>();
        for (int i = 0; i < mEntities.size(); i++) {
            mCBFlag.put(i, false);
        }
    }

    public void setVitalSignDefines(List<VitalSignWardDefine> defines) {
        mVitalDefines = defines;
    }

    public List<VitalSignWardDefine> getmVitalDefines() {
        return mVitalDefines;
    }

    public HuanZheLieBiaoBean.DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(HuanZheLieBiaoBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    public VitalSignSheet getVitalSignSheet() {
        return vitalSignSheet;
    }

    public void setVitalSignSheet(VitalSignSheet vitalSignSheet) {
        this.vitalSignSheet = vitalSignSheet;
    }

    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public XinjianJiaoBanBean.DataBean.PatientBean.ExecutingOrderListBean getItem(int position) {
        return mEntities.get(position);
    }

    public List<XinjianJiaoBanBean.DataBean.PatientBean.ExecutingOrderListBean> getmEntities() {
        return mEntities;
    }

    public void setmEntities(List<XinjianJiaoBanBean.DataBean.PatientBean.ExecutingOrderListBean> mEntities) {
        if (mEntities == null || mEntities.size() == 0) {
            mEntities = new ArrayList<>();
            mEntities.add(new XinjianJiaoBanBean.DataBean.PatientBean.ExecutingOrderListBean());
        }
        this.mEntities = mEntities;
        tiZhengXuanZhong = false;
        vitalSignSheet = null;
        init();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.zhi_xing_zhong_yz_include, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(XinjianJiaoBanBean.DataBean.PatientBean.ExecutingOrderListBean entity, final ViewHolder holder, final int position) {
        holder.mHzPgTv1.setVisibility(View.INVISIBLE);
        holder.mHzPgTv2.setVisibility(View.INVISIBLE);
        holder.mHzPgTv3.setVisibility(View.INVISIBLE);
        holder.mHzPgTv4.setVisibility(View.INVISIBLE);
        if (Constant.ORDER_DURATION_LINGSHI_0.equals(entity.getOrderduration())) {
            holder.mHzPgTv3.setVisibility(View.VISIBLE);
            holder.mHzPgTv3.setText(Constant.ORDER_DURATION_LINGSHI);
            holder.mHzPgTv3.setTextColor(Color.parseColor("#adadad"));
        } else if (Constant.ORDER_DURATION_CHANGQI_1.equals(entity.getOrderduration())) {
            holder.mHzPgTv3.setVisibility(View.VISIBLE);
            holder.mHzPgTv3.setText(Constant.ORDER_DURATION_CHANGQI);
            holder.mHzPgTv3.setTextColor(Color.parseColor("#adadad"));
        } else if (Constant.ORDER_DURATION_LIJI_2.equals(entity.getOrderduration())) {
            holder.mHzPgTv3.setVisibility(View.VISIBLE);
            holder.mHzPgTv3.setText(Constant.ORDER_DURATION_LIJI);
            holder.mHzPgTv3.setTextColor(Color.RED);
        } else {
            holder.mHzPgTv3.setVisibility(View.INVISIBLE);
        }
        holder.mJiaobanChuruliangLv.setAdapter(jiaobanChuruliangListItemAdapter);
        Utility.setListViewHeightBasedOnChildren(holder.mJiaobanChuruliangLv);
        if (position == 0) {
            tizheng(holder, dataBean);
            holder.drtzSjImgv.setVisibility(View.VISIBLE);
            holder.drtzTizhengImgv.setVisibility(View.VISIBLE);
            holder.mTizhengXinxiRl.setVisibility(View.VISIBLE);

            holder.mJiaoBanChuRuLiangRl.setVisibility(View.VISIBLE);
            holder.mJiaobanChuruliangSjTv.setVisibility(View.VISIBLE);

            holder.drtzYizhuImgv.setVisibility(View.VISIBLE);
            holder.mJiaobanJiluEdt.setVisibility(View.VISIBLE);
            holder.mJiaobanTeshushijianBt.setVisibility(View.VISIBLE);

            holder.mJiaobanJiluEdt.setText(edt);
            holder.mDuorenTizhengCheckbox.setChecked(tiZhengXuanZhong);

            holder.onclickTv.getLayoutParams().height = Utility.getTotalHeightofListView(holder.mJiaobanChuruliangLv);

            holder.onclickTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle mBundle = new Bundle();
                    Intent intent = new Intent(context, VitalSignSheetActivity.class);
                    mBundle.putIntegerArrayList(Constant.BUNDLE_KEY_VITAL_GROUP, integers);
                    mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SINGLE_PATIENT, true);
                    mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, dataBean);
                    mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SINGLE_PATIENT, false);
                    intent.putExtra("data", mBundle);
                    context.startActivity(intent);
                }
            });

            //特殊事件 处理
            holder.mJiaobanTeshushijianBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChangGuiHuLiActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, dataBean);
                    bundle.putSerializable(Constant.BUNDLE_KEY_JIAOBAN_SHIJIAN_EDT, jiaoBanEdt);
                    bundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.HULI_TYPR_JIAOBAN);
                    bundle.putSerializable(Constant.BUNDLE_KEY_JIAOBAN_SHIJIAN, eventList);
                    if (bundle != null) {
                        intent.putExtra("data", bundle);
                    }
                    ((Activity) context).startActivityForResult(intent, 1);
                }
            });

            holder.drtzTizhengImgv.setText("最后一次体征");
            if (vitalSignSheet == null) {
                holder.mTizhengXinxiRl.setVisibility(View.GONE);
                holder.drtzTizhengImgv.setText("最后一次体征：暂无");
                LogUtil.e("Can not show vital sign sheet since the data object is null!");
            }
            if (tiZhengXuanZhong) {
                holder.mDuorenTizhengCheckImgv.setVisibility(View.VISIBLE);
            } else {
                holder.mDuorenTizhengCheckImgv.setVisibility(View.GONE);
            }
            holder.mDuorenTizhengCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.mDuorenTizhengCheckbox.isChecked()) {
                        holder.mDuorenTizhengCheckImgv.setVisibility(View.VISIBLE);
                        tiZhengXuanZhong = true;
                    } else {
                        holder.mDuorenTizhengCheckImgv.setVisibility(View.GONE);
                        tiZhengXuanZhong = false;
                    }
                }
            });
            holder.mJiaobanJiluEdt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    edt = s.toString();
                    Log.d("Editable------->", edt);
                }
            });
        } else {
            holder.drtzSjImgv.setVisibility(View.GONE);
            holder.drtzTizhengImgv.setVisibility(View.GONE);
            holder.mTizhengXinxiRl.setVisibility(View.GONE);
            holder.drtzYizhuImgv.setVisibility(View.GONE);
            holder.mJiaobanJiluEdt.setVisibility(View.GONE);
            holder.mJiaoBanChuRuLiangRl.setVisibility(View.GONE);
            holder.mJiaobanChuruliangSjTv.setVisibility(View.GONE);
            holder.mJiaobanTeshushijianBt.setVisibility(View.GONE);
        }
        if (mEntities != null && mEntities.size() > 0 && mEntities.get(0).getOrdername() != null) {
            holder.mZhiXingZhongYizhuRl.setVisibility(View.VISIBLE);
            holder.drtzYizhuImgv.setText("执行中医嘱");
        } else {
            holder.mZhiXingZhongYizhuRl.setVisibility(View.GONE);
            holder.drtzYizhuImgv.setText("执行中医嘱：暂无");
        }
//        holder.docOrderPannel.bindData(entity);
        holder.zhiXingZhongYzIncluedHuliJibieImgv.setImageResource(AdapterUtil.findCareLevelImage(dataBean.getCarelevel()));
        holder.mHzJibenXinxiTv.setText(entity.getOrdername());
        holder.mHzGmsTv.setText("备注：" + entity.getNote());
        holder.mHzYzTuBiaoTv.setImageResource(getImageR(entity.getOrdertype()));
        holder.mHzYzShuWeizhixing.setText(entity.getOrderststus());
        holder.mHzYsZdTv.setText("医生：" + dataBean.getDoctorname() + "    途径：" + entity.getRoutedesc());
        holder.mJiaobanChuruliangJibieImgv.setImageResource(AdapterUtil.findCareLevelImage(dataBean.getCarelevel()));
        try {
            holder.zhiXingZhongYzIncluedSjTv.setText(DateUtils.getDateString("yyyy-MM-dd HH:mm", entity.getStarttime()));
        } catch (Exception e) {
            LogUtil.e(" entity.getStarttime()  is  null");
        }
        holder.zhiXingZhongCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.zhiXingZhongCheckBox.isChecked()) {
                    mCBFlag.put(position, true);
                    holder.zhiXingZhongCheckImgv.setVisibility(View.VISIBLE);
                } else {
                    mCBFlag.put(position, false);
                    holder.zhiXingZhongCheckImgv.setVisibility(View.GONE);
                }
            }
        });

        holder.zhiXingZhongCheckBox.setChecked(mCBFlag.get(position));
        if (mCBFlag.get(position)) {
            holder.zhiXingZhongCheckImgv.setVisibility(View.VISIBLE);
        } else {
            holder.zhiXingZhongCheckImgv.setVisibility(View.GONE);
        }
    }

    private void tizheng(ViewHolder holder, HuanZheLieBiaoBean.DataBean dataBean) {
        if (vitalSignSheet == null) {
            LogUtil.e("Can not show vital sign sheet since the data object is null!");
            return;
        }
        long time = 0;
        try {
            time = Long.parseLong(vitalSignSheet.getTime());
        } catch (NumberFormatException e) {
            LogUtil.e("Can not parse time to long!error is :" + e.getMessage());
        }
        holder.duorenTizhengcheckImgv.setVisibility(View.GONE);

        holder.duorenTizhengChXmIdTv.setText(DateUtils.getDateString("yyyy-MM-dd HH:mm", time));
        holder.duorenTizhengSjTv.setVisibility(View.GONE);
// else{
//        holder.duorenTizhengChXmIdTv.setText(vitalSignSheet.getBedNo() + "床 " + vitalSignSheet.getPatientName() + " (" + vitalSignSheet.getPatientId() + ")");
//        holder.duorenTizhengSjTv.setVisibility(View.VISIBLE);
//        holder.duorenTizhengSjTv.setText(DateUtils.getDateString(" HH:mm", time));
//    }
        holder.duorenTizhengImgv.setImageResource(AdapterUtil.findCareLevelImage(dataBean.getCarelevel()));
        if (mVitalDefines != null && mVitalDefines.size() > 0) {
            VitalDefineEntity define = null;
            for (int i = 0; i < mVitalDefines.size(); i++) {
                if (i >= 8) break;
                define = mVitalDefines.get(i).getVitalDefine();
                if (define != null) {
                    holder.vitalNameList.get(i).setText(define.getSignname());
                    holder.vitalValueList.get(i).setText(
                            getVitalValueStringBySignType(vitalSignSheet, define.getId()) + " " + define.getUnitdesc());
                }
            }
        }
    }
private  int getImageR(String str){
    int retval = R.mipmap.icon_injection;
    switch (str) {
        case Constant.TYPE_YZ_JINGMAIZHUSHE:
        case Constant.TYPE_YZ_PIXIAZHUSHE:
        case Constant.TYPE_YZ_JIROUZHUSHE:
        case Constant.TYPE_YZ_PISHI:
            retval = R.mipmap.icon_injection;
            break;
        case Constant.TYPE_YZ_SHUYE:
            retval = R.mipmap.icon_iinfusion;
            break;
        case Constant.TYPE_YZ_KOUFU:
            retval = R.mipmap.icon_medicine;
            break;
        case Constant.TYPE_YZ_JIANYAN_BLOOD:
        case Constant.TYPE_YZ_JIANYAN_UN_BLOOD:
            retval = R.mipmap.icon_bio;
            break;
        case Constant.TYPE_YZ_SHUXUE:
        case Constant.TYPE_YZ_CAIXUE:
            retval = R.mipmap.icon_blood_transfusion;
            break;
        case Constant.TYPE_YZ_ZHILIAO:
        case Constant.TYPE_YZ_HULI:
            retval = R.mipmap.fuzhuzhiliao;
            break;
        case Constant.TYPE_YZ_SHANSHI:
            retval = R.mipmap.shanshi;
            break;
        case Constant.TYPE_YZ_RUHU:
            retval = R.mipmap.ruhu;
            break;
    }
    return retval;
}
    private String getVitalValueStringBySignType(VitalSignSheet object, int signType) {
        String retval = "";
        if (object == null || object.getSignRecordList() == null) {
            LogUtil.e("Can not get vital value since sheet is null.");
            return retval;
        }
        VitalSignRecord vital = null;
        for (VitalSignRecord record : object.getSignRecordList()) {
            if (record.getSigntype() == signType) {
                vital = record;
                break;
            }
        }
        if (vital != null) {
            retval = vital.getSignvalue();
        }
        return retval;
    }

    protected class ViewHolder {
        private TextView drtzSjImgv;
        private TextView drtzTizhengImgv;
        private TextView drtzYizhuImgv;
        private ImageView zhiXingZhongYzIncluedHuliJibieImgv;
        private TextView zhiXingZhongYzIncluedSjTv;

        private CheckBox zhiXingZhongCheckBox;
        private ImageView zhiXingZhongCheckImgv;
        private EditText mJiaobanJiluEdt;
        private RelativeLayout mTizhengXinxiRl;
        private CheckBox mDuorenTizhengCheckbox;
        private ImageView mDuorenTizhengCheckImgv;
        private RelativeLayout mZhiXingZhongYizhuRl;
        private DocOrderPannel docOrderPannel;

        private ImageView duorenTizhengImgv;
        private ImageView duorenTizhengcheckImgv;
        private TextView duorenTizhengChXmIdTv;
        private TextView duorenTizhengSjTv;
        private List<TextView> vitalNameList = new ArrayList<>();
        private List<TextView> vitalValueList = new ArrayList<>();

        private RelativeLayout mJiaoBanChuRuLiangRl;
        private ImageView mJiaobanChuruliangJibieImgv;
        private ListView mJiaobanChuruliangLv;
        private TextView mJiaobanChuruliangSjTv;
        private RelativeLayout mChuruliangBackgroundLl;
        private TextView onclickTv;
        private ImageView mHzYzTuBiaoTv;
        private TextView mHzYzShuWeizhixing;


        private Button mJiaobanTeshushijianBt;
        private TextView mHzPgTv1;
        private TextView mHzPgTv2;
        private TextView mHzPgTv3;
        private TextView mHzPgTv4;
        private TextView mHzYsZdTv;
        private TextView mHzGmsTv;
        private TextView mHzJibenXinxiTv;
        private LinearLayout mHzPgLl;


        public ViewHolder(View view) {
            docOrderPannel = new DocOrderPannel(view, context);
            drtzSjImgv = (TextView) view.findViewById(R.id.drtz_sj_imgv);
            drtzTizhengImgv = (TextView) view.findViewById(R.id.drtz_tizheng_imgv);
            drtzYizhuImgv = (TextView) view.findViewById(R.id.drtz_yizhu_imgv);
            zhiXingZhongYzIncluedHuliJibieImgv = (ImageView) view.findViewById(R.id.zhi_xing_zhong_yz_inclued_huli_jibie_imgv);
            zhiXingZhongYzIncluedSjTv = (TextView) view.findViewById(R.id.zhi_xing_zhong_yz_inclued_sj_tv);

            zhiXingZhongCheckBox = (CheckBox) view.findViewById(R.id.zhi_xing_zhong_check_box);
            zhiXingZhongCheckImgv = (ImageView) view.findViewById(R.id.zhi_xing_zhong_check_imgv);
            mJiaobanJiluEdt = (EditText) view.findViewById(R.id.jiaoban_jilu_edt);
            mTizhengXinxiRl = (RelativeLayout) view.findViewById(R.id.tizheng_xinxi_rl);
            mDuorenTizhengCheckbox = (CheckBox) view.findViewById(R.id.duoren_tizheng_checkbox);
            mDuorenTizhengCheckImgv = (ImageView) view.findViewById(R.id.duoren_tizheng_check_imgv);
            mZhiXingZhongYizhuRl = (RelativeLayout) view.findViewById(R.id.zhi_xing_zhong_yizhu_rl);
            onclickTv = (TextView) view.findViewById(R.id.onclick_tv);

            mHzYzTuBiaoTv = (ImageView) view.findViewById(R.id.hz_yz_tu_biao_tv);
            mHzYzShuWeizhixing = (TextView) view.findViewById(R.id.hz_yz_shu_weizhixing);


            duorenTizhengImgv = (ImageView) view.findViewById(R.id.duoren_tizheng_imgv);
            duorenTizhengcheckImgv = (ImageView) view.findViewById(R.id.duoren_tizheng_check_imgv);
            duorenTizhengChXmIdTv = (TextView) view.findViewById(R.id.duoren_tizheng_title_name);
            duorenTizhengSjTv = (TextView) view.findViewById(R.id.duoren_tizheng_title_time);
            for (int i = 1; i <= 8; i++) {
                vitalNameList.add((TextView) view.findViewWithTag("vital_name_tv_" + i));
                vitalValueList.add((TextView) view.findViewWithTag("vital_value_tv_" + i));
            }
            mJiaoBanChuRuLiangRl = (RelativeLayout) view.findViewById(R.id.jiaoban_churuliang_rl);
            mJiaobanChuruliangJibieImgv = (ImageView) view.findViewById(R.id.jiaoban_churuliang_jibie_imgv);
            mJiaobanChuruliangLv = (ListView) view.findViewById(R.id.jiaoban_churuliang_lv);
            mJiaobanChuruliangSjTv = (TextView) view.findViewById(R.id.jiaoban_churuliang_sj_tv);
            mChuruliangBackgroundLl = (RelativeLayout) view.findViewById(R.id.churuliang_background_ll);
            mJiaobanTeshushijianBt = (Button) view.findViewById(R.id.jiaoban_teshushijian_bt);
            mHzPgTv1 = (TextView) view.findViewById(R.id.hz_pg_tv1);
            mHzPgTv2 = (TextView) view.findViewById(R.id.hz_pg_tv2);
            mHzPgTv3 = (TextView) view.findViewById(R.id.hz_pg_tv3);
            mHzPgTv4 = (TextView) view.findViewById(R.id.hz_pg_tv4);
            mHzYsZdTv = (TextView) view.findViewById(R.id.hz_ys_zd_tv);
            mHzGmsTv = (TextView) view.findViewById(R.id.hz_gms_tv);

            mHzJibenXinxiTv = (TextView) view.findViewById(R.id.hz_jiben_xinxi_tv);
            mHzPgLl = (LinearLayout) view.findViewById(R.id.hz_pg_ll);

        }
    }
}
