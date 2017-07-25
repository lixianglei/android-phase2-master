package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.db.DBDischargePatient;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignSheet;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JiaoBanGvItemAdapter extends BaseAdapter {
    private List<HuanZheLieBiaoBean.DataBean> objects = new ArrayList<>();
    private Map<Integer, String> XuanZeDataMap = new HashMap<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private List<YiZhuBean.DataBean> mEntities;
    private Map<Integer, TextView> cbMap;
    private YiZhuBean.DataBean dbYiZhuDaTa;
    private Bundle mBundle;
    private int pos;
    private TextView xuanzhongTV;
    private List<YiZhuBean.DataBean> executingOrderList = new ArrayList<>();
    private TextView xuanzhongTV1;
    private DBDischargePatient dischargePatient;
    private VitalSignSheet vitalSignSheet;
    private int i = -1;



    public VitalSignSheet getVitalSignSheet() {
        return vitalSignSheet;
    }

    public void setVitalSignSheet(VitalSignSheet vitalSignSheet) {
        this.vitalSignSheet = vitalSignSheet;
    }

    public DBDischargePatient getDischargePatient() {
        return dischargePatient;
    }

    public void setDischargePatient(DBDischargePatient dischargePatient) {
        this.dischargePatient = dischargePatient;
    }

    public List<YiZhuBean.DataBean> getExecutingOrderList() {
        return executingOrderList;
    }

    public void setExecutingOrderList(List<YiZhuBean.DataBean> executingOrderList) {
        this.executingOrderList = executingOrderList;
    }


    public TextView getXuanzhongTV() {
        return xuanzhongTV;
    }

    public void setXuanzhongTV(TextView xuanzhongTV) {
        this.xuanzhongTV = xuanzhongTV;
    }

    public TextView getXuanzhongTV1() {
        return xuanzhongTV1;
    }

    public void setXuanzhongTV1(TextView xuanzhongTV1) {
        this.xuanzhongTV1 = xuanzhongTV1;
    }



    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }


    public YiZhuBean.DataBean getDbYiZhuDaTa() {
        return dbYiZhuDaTa;
    }

    public void setDbYiZhuDaTa(YiZhuBean.DataBean dbYiZhuDaTa) {
        this.dbYiZhuDaTa = dbYiZhuDaTa;
    }

    public List<YiZhuBean.DataBean> getmEntities() {
        return mEntities;
    }

    public void setmEntities(List<YiZhuBean.DataBean> mEntities) {
        this.mEntities = mEntities;
        cbMap = new HashMap<>();
        mCBFlag = new HashMap<Integer, Boolean>();
        XuanZeDataMap = new HashMap<>();
        init();
        i = -1;
    }

    public Map<Integer, TextView> getCbMap() {
        return cbMap;
    }

    public void setCbMap(Map<Integer, TextView> cbMap) {
        this.cbMap = cbMap;
    }

    public Map<Integer, Boolean> mCBFlag = null;

    public Map<Integer, String> getXuanZeDataMap() {
        return XuanZeDataMap;
    }

    public JiaoBanGvItemAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        objects.add(new HuanZheLieBiaoBean.DataBean());
        mCBFlag = new HashMap<Integer, Boolean>();
        XuanZeDataMap = new HashMap<>();
        init();

    }
//初始化CheckBox状态

    void init() {
        for (int i = 0; i < objects.size(); i++) {
            mCBFlag.put(i, true);
            XuanZeDataMap.put(i, objects.get(i).getPatientid());
        }
    }

    public List<HuanZheLieBiaoBean.DataBean> getObjects() {
        return objects;
    }

    public void setObjects(List<HuanZheLieBiaoBean.DataBean> objects) {
        if(objects == null){
            objects = new ArrayList<>();
        }
        objects.add(0, new HuanZheLieBiaoBean.DataBean());
        mCBFlag = new HashMap<Integer, Boolean>();
        this.objects = objects;
        cbMap = new HashMap<>();
        XuanZeDataMap = new HashMap<>();
        init();
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
            convertView = layoutInflater.inflate(R.layout.jiao_ban_gv_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(final HuanZheLieBiaoBean.DataBean object, final ViewHolder holder, final int position) {
        if (position == 0) {
            holder.mJiaoBan01.setVisibility(View.VISIBLE);
            holder.jiaoBanCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.jiaoBanCheckBox.isChecked()) {
                        for (int i = 0; i < mCBFlag.size(); i++) {
                            mCBFlag.put(i, true);
                            LogUtil.e(" mCBFlag.size()   =="+ mCBFlag.size());
                            XuanZeDataMap.put(i, objects.get(i).getPatientid());
                            holder.jiaoBanCheckImgv.setVisibility(View.VISIBLE);
                        }
                    } else {
                        for (int i = 0; i < mCBFlag.size(); i++) {
                            mCBFlag.put(i, false);
                            XuanZeDataMap.remove(i);
                            holder.jiaoBanCheckImgv.setVisibility(View.GONE);
                        }
                    }
                    if(mCBFlag.get(position)){
                        holder.mJiaoBan01.setText("反选");
                    }else{
                        holder.mJiaoBan01.setText("全选");
                    }
                    notifyDataSetChanged();
                }
            });
            holder.jiaoBanCheckBox.setChecked(mCBFlag.get(position));
            if(mCBFlag.get(position)){
                holder.jiaoBanCheckImgv.setVisibility(View.VISIBLE);
            }else{
                holder.jiaoBanCheckImgv.setVisibility(View.GONE);
            }

            return;
        }
        holder.mJiaoBan01.setVisibility(View.GONE);
        String carelevel = object.getCarelevel();
        if (carelevel != null) {
            switch (carelevel) {
                case "一级":
                    holder.jiaoBanJiBieImgv.setImageResource(R.mipmap.icon_one);
                    break;
                case "二级":
                    holder.jiaoBanJiBieImgv.setImageResource(R.mipmap.icon_two);
                    break;
                case "三级":
                    holder.jiaoBanJiBieImgv.setImageResource(R.mipmap.icon_three);
                    break;
                case "特级":
                    holder.jiaoBanJiBieImgv.setImageResource(R.mipmap.icon_premium);
                    break;
            }
        }
        holder.jiaoBanChuangHaoTv.setText(object.getBedno() + "");
        holder.jiaoBanXmTv.setText(object.getName());
        holder.jiaoBanIdTv.setText(object.getMrnno() + "");
        holder.jiaoBanCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.jiaoBanCheckBox.isChecked()) {
                    mCBFlag.put(position, true);
                    holder.jiaoBanCheckImgv.setVisibility(View.VISIBLE);
                    XuanZeDataMap.put(position, object.getPatientid());
                } else {
                    mCBFlag.put(position, false);
                    holder.jiaoBanCheckImgv.setVisibility(View.GONE);
                    XuanZeDataMap.remove(position);
                }
                System.out.println(XuanZeDataMap.size() + "++++++++++++++++++++++");
            }
        });
        holder.jiaoBanCheckBox.setChecked(mCBFlag.get(position));
        if(mCBFlag.get(position)){
            holder.jiaoBanCheckImgv.setVisibility(View.VISIBLE);
        }else{
            holder.jiaoBanCheckImgv.setVisibility(View.GONE);
        }

    }

    protected class ViewHolder {
        private ImageView jiaoBanJiBieImgv;
        private TextView jiaoBanChuangHaoTv;
        private TextView jiaoBanXmTv;
        private TextView jiaoBanIdTv;
        private CheckBox jiaoBanCheckBox;
        private ImageView jiaoBanCheckImgv;
        private TextView mJiaoBan01;


        public ViewHolder(View view) {
            jiaoBanJiBieImgv = (ImageView) view.findViewById(R.id.jiao_ban_ji_bie_imgv);
            jiaoBanChuangHaoTv = (TextView) view.findViewById(R.id.jiao_ban_chuang_hao_tv);
            jiaoBanXmTv = (TextView) view.findViewById(R.id.jiao_ban_xm_tv);
            jiaoBanIdTv = (TextView) view.findViewById(R.id.jiao_ban_id_tv);
            jiaoBanCheckBox = (CheckBox) view.findViewById(R.id.jiao_ban_check_box);
            jiaoBanCheckImgv = (ImageView) view.findViewById(R.id.jiao_ban_check_imgv);
            mJiaoBan01 = (TextView) view.findViewById(R.id.jiao_ban_01);
        }
    }
}
