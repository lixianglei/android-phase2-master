package com.ge.med.mobile.nursing.ui.adapter;

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
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.ui.activity.YZ_YaoPin_XiangQingActivity;
import com.ge.med.mobile.nursing.ui.component.RiskTag;
import com.ge.med.mobile.nursing.ui.view.SelfDialog;
import com.ge.med.mobile.nursing.utils.DataConverter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class YizhuHeduiLeixingLvItemAdapter extends BaseAdapter {
    private int i = -1;
    private List<YiZhuBean.DataBean> mEntities;
    private Map<Integer, TextView> cbMap;
    private Button mYzHdLxYichangBt;
    public Map<Integer, Boolean> mCBFlag = null;
    private int zid;
    private CheckBox yichangCB;
    private int pos;
    private TextView yichangTV;
    private ImageView yichangIMGV;
    private TextView xuanzhongTV;
    private Bundle mBundle;
    private YiZhuBean.DataBean dbYiZhuDaTa;
    private String date = "";
    private DoctorOrderDao mDoctorOrderDaoImpl;
    private Map<Integer, Boolean> posList;
    private int p;
    public   SelfDialog selfDialog;

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public Map<Integer, Boolean> getPosList() {
        return posList;
    }

    public void setPosList(Map<Integer, Boolean> posList) {
        this.posList = posList;
    }

    public YiZhuBean.DataBean getDbYiZhuDaTa() {
        return dbYiZhuDaTa;
    }

    public void setDbYiZhuDaTa(YiZhuBean.DataBean dbYiZhuDaTa) {
        this.dbYiZhuDaTa = dbYiZhuDaTa;
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

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public CheckBox getYichangCB() {
        return yichangCB;
    }

    public void setYichangCB(CheckBox yichangCB) {
        this.yichangCB = yichangCB;
    }

    public Map<Integer, TextView> getCbMap() {
        return cbMap;
    }

    public void setCbMap(Map<Integer, TextView> cbMap) {
        this.cbMap = cbMap;
    }

    public int getZid() {
        return zid;
    }

    public void setZid(int zid) {
        this.zid = zid;
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
        this.mEntities = mEntities;
        i = -1;
        init();
        cbMap = new HashMap<>();
        notifyDataSetChanged();
    }

    private Context context;
    private LayoutInflater layoutInflater;

    public YizhuHeduiLeixingLvItemAdapter(Context context, List<YiZhuBean.DataBean> entities, Button mYzHdLxYichangBt, Bundle mBundle) {
        this.context = context;
        selfDialog= new SelfDialog(context);
        selfDialog.setTitle("再次核对异常医嘱");
        selfDialog.setMessage("如问题以解决，请点击“确定”继续完成核对。");

        selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });
        this.layoutInflater = LayoutInflater.from(context);
        this.mEntities = entities;
        this.mYzHdLxYichangBt = mYzHdLxYichangBt;
        cbMap = new HashMap<>();
        mDoctorOrderDaoImpl = new DoctorOrderDaoImpl();
        init();
        this.mBundle = mBundle;
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
            convertView = layoutInflater.inflate(R.layout.yizhu_hedui_leixing_lv_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((YiZhuBean.DataBean) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }
    private void initializeViews(final YiZhuBean.DataBean entity, final ViewHolder holder, final int position) {
        String dateString1 = DateUtils.getDateString("yyyy/MM/dd", entity.getStarttime());
        if (posList.get(position)) {
            holder.mYzHdLxSjTv.setVisibility(View.VISIBLE);
            holder.mYzHdLxSjTv.setText(dateString1);
        } else {
            holder.mYzHdLxSjTv.setVisibility(View.GONE);
        }

        holder.yzShuxueXuanzhongCheckImgv.setImageResource(AdapterUtil.findYZImage(entity));
        final DBHuanZheLieBiao huanZheLieBiao = AdapterUtil.getPatientFromDB(entity.getPatientid());
        final HuanZheLieBiaoBean.DataBean dataBean = DataConverter.convert(huanZheLieBiao);
        String substring = "";
        if (dataBean.getName() != null && dataBean.getName().length() > 4) {
            substring = dataBean.getName().substring(0, 4);
        }else if(dataBean.getName() != null && dataBean.getName().length() <= 4){
            substring = dataBean.getName();
        }
        holder.yizhuHeduiLeixingLvItemBiaotiTv.setText(dataBean.getBedno() + "床 " + substring + " (" + entity.getPatientid() + ")");
        holder.yizhuHeduiLeixxingJiaobiaoImgv.setImageResource(AdapterUtil.findCareLevelImage(dataBean.getCarelevel()));

        String dateString = DateUtils.getDateString("HH:mm", entity.getStarttime());
        holder.yizhuHeduiLeixingLvItemSjTv.setText(dateString);
        holder.yizhuHeduiLeixingLvItemNeirongTv.setText(entity.getOrdername());
        holder.hzYzPgTv1.setVisibility(View.INVISIBLE);
        holder.hzYzPgTv2.setVisibility(View.INVISIBLE);
        holder.hzYzPgTv3.setVisibility(View.INVISIBLE);
        holder.hzYzPgTv4.setVisibility(View.INVISIBLE);
        if (dataBean.getIlldetial() != null) {
            holder.hzYzPgTv1.setVisibility(View.VISIBLE);
            RiskTag.setIllnessTag(holder.hzYzPgTv1, dataBean.getIlldetial(), null);
        } else {
            holder.hzYzPgTv1.setVisibility(View.INVISIBLE);
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
        }else {
            holder.hzYzPgTv3.setVisibility(View.INVISIBLE);
        }
        holder.changAnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entity.getPharmList()==null || entity.getPharmList().size()<=0){
                    Toast.makeText(context, "该医嘱没有详情！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(context, YZ_YaoPin_XiangQingActivity.class);
                mBundle.putInt("hisid", entity.getId());
                mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, dataBean);
                mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU, entity);
                if (mBundle != null) {
                    intent.putExtra("data", mBundle);
                }
                context.startActivity(intent);
            }
        });
        holder.xuanZeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDoctorOrderDaoImpl.getExceptionForCheck(entity.getId()) != null) {
                    mCBFlag.put(position, false);

                    selfDialog.setYesOnclickListener("确认", new SelfDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {

                            holder.xuanZeTv.setChecked(true);
//                            entity.setYiChang(false);
//                            entity.setYiChangXinXi(null);
                            mDoctorOrderDaoImpl.saveExceptionForCheck(entity.getId(), null);
                            holder.xuanZeTv.setChecked(false);
                            holder.xuanZeTv.performClick();
                            notifyDataSetChanged();
                            selfDialog.dismiss();
                        }
                    });
                    selfDialog.show();
                } else {
                    Iterator it = cbMap.entrySet().iterator();
                    int key;
                    TextView value;
                    while (it.hasNext()) {
                        Map.Entry entry = (Map.Entry) it.next();
                        key = (int) entry.getKey();
                        value = (TextView) entry.getValue();
                        value.setBackgroundResource(R.color.touse559bec);
                    }
                    i = position;
                    if (holder.xuanZeTv.isChecked()) {
                        mCBFlag.put(position, true);
                        cbMap.put(position, holder.yzShuxueXuanzhongCheckBox);
                        holder.yzShuxueXuanzhongCheckBox.setVisibility(View.VISIBLE);
                        holder.yizhuHeduiLeixingLvItemZhuangtaiTv.setText("已核对");
                        holder.yizhuHeduiLeixingLvItemZhuangtaiTv.setTextColor(R.color.se559bec);
                        holder.mYzShuxueXuanzhongCheckImgv1.setImageResource(R.mipmap.icon_select);
                        holder.mYzShuxueXuanzhongCheckImgv1.setVisibility(View.VISIBLE);
                        if (i == position) {
                            p = position;
                            zid = entity.getId();
                            holder.yzShuxueXuanzhongCheckBox.setBackgroundResource(R.drawable.yz_hd_miao_bian_shape);
                            mYzHdLxYichangBt.setEnabled(true);
                            yichangCB = holder.xuanZeTv;
                            pos = position;
                            yichangTV = holder.yizhuHeduiLeixingLvItemZhuangtaiTv;
                            yichangIMGV = holder.mYzShuxueXuanzhongCheckImgv1;
                            xuanzhongTV = holder.yzShuxueXuanzhongCheckBox;
                            dbYiZhuDaTa = mEntities.get(position);
                        }
                    } else {
                        cbMap.remove(position);
                        mCBFlag.put(position, false);
                        mYzHdLxYichangBt.setEnabled(false);
                        holder.yizhuHeduiLeixingLvItemZhuangtaiTv.setText("未核对");
                        holder.yizhuHeduiLeixingLvItemZhuangtaiTv.setTextColor(R.color.se559bec);
                        holder.yzShuxueXuanzhongCheckBox.setVisibility(View.GONE);
                        holder.mYzShuxueXuanzhongCheckImgv1.setVisibility(View.GONE);

                    }
                }

            }
        });
        holder.xuanZeTv.setChecked(mCBFlag.get(position));
        if (mCBFlag.get(position)) {
            holder.yzShuxueXuanzhongCheckBox.setVisibility(View.VISIBLE);
            if (p == position) {
                cbMap.put(position, holder.yzShuxueXuanzhongCheckBox);
                p = position;
                zid = entity.getId();
                holder.yzShuxueXuanzhongCheckBox.setBackgroundResource(R.drawable.yz_hd_miao_bian_shape);
                mYzHdLxYichangBt.setEnabled(true);
                yichangCB = holder.xuanZeTv;
                pos = position;
                yichangTV = holder.yizhuHeduiLeixingLvItemZhuangtaiTv;
                yichangIMGV = holder.mYzShuxueXuanzhongCheckImgv1;
                xuanzhongTV = holder.yzShuxueXuanzhongCheckBox;
                dbYiZhuDaTa = mEntities.get(position);
            } else {
                holder.yzShuxueXuanzhongCheckBox.setBackgroundResource(R.color.touse559bec);

            }
            holder.mYzShuxueXuanzhongCheckImgv1.setVisibility(View.VISIBLE);
            holder.mYzShuxueXuanzhongCheckImgv1.setImageResource(R.mipmap.icon_select);
            holder.yizhuHeduiLeixingLvItemZhuangtaiTv.setText("已核对");
            holder.yizhuHeduiLeixingLvItemZhuangtaiTv.setTextColor(R.color.se559bec);
        } else {
            holder.yzShuxueXuanzhongCheckBox.setVisibility(View.GONE);
            holder.mYzShuxueXuanzhongCheckImgv1.setVisibility(View.GONE);
            holder.yizhuHeduiLeixingLvItemZhuangtaiTv.setText("未核对");
            holder.yizhuHeduiLeixingLvItemZhuangtaiTv.setTextColor(R.color.se559bec);
            holder.yzShuxueXuanzhongCheckBox.setVisibility(View.GONE);
        }


//        if (i == position) {
//            holder.mYzShuxueXuanzhongCheckBox.setVisibility(View.VISIBLE);
//        } else {
//            holder.mYzShuxueXuanzhongCheckBox.setVisibility(View.GONE);
//
//        }

        if (mDoctorOrderDaoImpl.getExceptionForCheck(entity.getId()) != null) {
            cbMap.remove(position);
            mCBFlag.put(position, false);
            holder.xuanZeTv.setChecked(false);
            holder.yizhuHeduiLeixingLvItemZhuangtaiTv.setText(mDoctorOrderDaoImpl.getExceptionForCheck(entity.getId()));
            holder.yizhuHeduiLeixingLvItemZhuangtaiTv.setTextColor(Color.RED);
            holder.mYzShuxueXuanzhongCheckImgv1.setVisibility(View.VISIBLE);
            holder.mYzShuxueXuanzhongCheckImgv1.setImageResource(R.mipmap.icon_stop);
            holder.yzShuxueXuanzhongCheckBox.setVisibility(View.GONE);
        }
    }

    protected class ViewHolder {
        private ImageView yizhuHeduiLeixxingJiaobiaoImgv;
        private View xian;
        private TextView changAnTv;
        private CheckBox xuanZeTv;
        private TextView yizhuHeduiLeixingLvItemBiaotiTv;
        private TextView yizhuHeduiLeixingLvItemSjTv;
        private TextView yizhuHeduiLeixingLvItemNeirongTv;
        private View xian1;
        private LinearLayout hzYzPgLl;
        private TextView hzYzPgTv1;
        private TextView hzYzPgTv2;
        private TextView hzYzPgTv3;
        private TextView hzYzPgTv4;
        private ImageView yizhuHeduiLeixingLvItemZhuangtaiImgv;
        private TextView yizhuHeduiLeixingLvItemZhuangtaiTv;
        private TextView yzShuxueXuanzhongCheckBox;
        private ImageView yzShuxueXuanzhongCheckImgv;
        private ImageView mYzShuxueXuanzhongCheckImgv1;
        private TextView mYzHdLxSjTv;


        public ViewHolder(View view) {
            yizhuHeduiLeixxingJiaobiaoImgv = (ImageView) view.findViewById(R.id.yizhu_hedui_leixxing_jiaobiao_imgv);
            xian = (View) view.findViewById(R.id.xian);
            changAnTv = (TextView) view.findViewById(R.id.chang_an_tv);
            xuanZeTv = (CheckBox) view.findViewById(R.id.xuan_ze_tv);
            yizhuHeduiLeixingLvItemBiaotiTv = (TextView) view.findViewById(R.id.yizhu_hedui_leixing_lv_item_biaoti_tv);
            yizhuHeduiLeixingLvItemSjTv = (TextView) view.findViewById(R.id.yizhu_hedui_leixing_lv_item_sj_tv);
            yizhuHeduiLeixingLvItemNeirongTv = (TextView) view.findViewById(R.id.yizhu_hedui_leixing_lv_item_neirong_tv);
            xian1 = (View) view.findViewById(R.id.xian1);
            hzYzPgLl = (LinearLayout) view.findViewById(R.id.hz_yz_pg_ll);
            hzYzPgTv1 = (TextView) view.findViewById(R.id.hz_yz_pg_tv1);
            hzYzPgTv2 = (TextView) view.findViewById(R.id.hz_yz_pg_tv2);
            hzYzPgTv3 = (TextView) view.findViewById(R.id.hz_yz_pg_tv3);
            hzYzPgTv4 = (TextView) view.findViewById(R.id.hz_yz_pg_tv4);
            yizhuHeduiLeixingLvItemZhuangtaiImgv = (ImageView) view.findViewById(R.id.yizhu_hedui_leixing_lv_item_zhuangtai_imgv);
            yizhuHeduiLeixingLvItemZhuangtaiTv = (TextView) view.findViewById(R.id.yizhu_hedui_leixing_lv_item_zhuangtai_tv);
            yzShuxueXuanzhongCheckBox = (TextView) view.findViewById(R.id.yz_shuxue_xuanzhong_check_box);
            yzShuxueXuanzhongCheckImgv = (ImageView) view.findViewById(R.id.yz_shuxue_xuanzhong_check_imgv);
            mYzShuxueXuanzhongCheckImgv1 = (ImageView) view.findViewById(R.id.yz_shuxue_xuanzhong_check_imgv1);
            mYzHdLxSjTv = (TextView) view.findViewById(R.id.yz_hd_lx_sj_tv);

        }
    }

}
