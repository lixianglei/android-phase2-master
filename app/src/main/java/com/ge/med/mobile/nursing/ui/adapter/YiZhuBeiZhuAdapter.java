package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.NursingEventTempLateLisBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.db.DBXuanJiaoRecord;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/31.
 */
public class YiZhuBeiZhuAdapter extends BaseAdapter {
    ArrayList<Object> ls;
    Context mContext;
    LinearLayout linearLayout = null;
    LayoutInflater inflater;
    TextView tex;
    final int VIEW_TYPE = 4;
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;
    final int TYPE_3 = 2;
    final int TYPE_4 = 3;
    private YiZhuBean.OrderNoteListBean orderNoteListBean;
    private String dateString;
    private Map<Integer, Boolean> booleanMap;
    private String edt;
    private NursingEventTempLateLisBean.DataBean.NursingEventTemplateDefineListBean nursingEventTemplateDefineListBean;

    public YiZhuBeiZhuAdapter(Context context, ArrayList<Object> list) {
        ls = list;
        mContext = context;
        initMap();
    }

    /**
     * 添加eduDefineId，然后在数据库查找宣教记录，有记录的话要将结果显示在界面中(变色，打钩)
     */
    public YiZhuBeiZhuAdapter(Context context, ArrayList<Object> list, String eduDefineId, String patientId) {
        ls = list;
        mContext = context;
        // 在数据库中查找数据
        List<DBXuanJiaoRecord> eduRecords = DataSupport.select("edumodetext", "eduresulttext").where("edudefineid = ? and patientid = ?", eduDefineId, patientId).find(DBXuanJiaoRecord.class);
        if (eduRecords != null && eduRecords.size() > 0) {
            // 宣教方式
            String mode = eduRecords.get(0).getEdumodetext();
            // 结果
            String result = eduRecords.get(0).getEduresulttext();
            if (mode != null && result != null) {
                booleanMap = new HashMap<>();
                for (int i = 0; i < ls.size(); i++) {
                    if (ls.get(i) instanceof String) {
                        String str = (String) ls.get(i);
                        if (str != null) {
                            if (str.startsWith("0001")) {
                                str = str.replace("0001", "");
                            } else if (str.startsWith("0002")) {
                                str = str.replace("0002", "");
                            }
                            if (str.equals(mode) || str.equals(result)) {
                                booleanMap.put(i, true);
                            } else {
                                booleanMap.put(i, false);
                            }
                        }
                    }
                }
            }
        } else {
            initMap();
        }
    }

    public ArrayList<Object> getLs() {
        return ls;
    }

    public Map<Integer, Boolean> getBooleanMap() {
        return booleanMap;
    }

    private void initMap() {
        booleanMap = new HashMap<>();
        for (int i = 0; i < ls.size(); i++) {
            booleanMap.put(i, false);
        }
    }

    public String getEdt() {
        return edt;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public Object getItem(int position) {
        return ls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 每个convert view都会调用此方法，获得当前所需要的view样式
    @Override
    public int getItemViewType(int position) {
        Object o = ls.get(position);
        if (o instanceof String && o.equals("edt"))
            return TYPE_4;
        else if (o instanceof YiZhuBean.OrderNoteListBean)
            return TYPE_2;
        else if (o instanceof NursingEventTempLateLisBean.DataBean.NursingEventTemplateDefineListBean)
            return TYPE_3;
        else if (o instanceof String && ((String) o).startsWith("000"))
            return TYPE_3;
        else
            return TYPE_1;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        ViewHolder3 holder3 = null;
        ViewHolder4 holder4 = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            inflater = LayoutInflater.from(mContext);
            // 按当前所需的样式，确定new的布局
            switch (type) {
                case TYPE_1:
                    convertView = inflater.inflate(R.layout.huli_jilu_beizhu_lv_item_tv1, parent, false);
                    holder1 = new ViewHolder1(convertView);
                    convertView.setTag(holder1);
                    break;
                case TYPE_2:
                    convertView = inflater.inflate(R.layout.huli_jilu_beizhu_lv_item_tv2,
                            parent, false);
                    holder2 = new ViewHolder2(convertView);
                    convertView.setTag(holder2);
                    break;
                case TYPE_3:
                    convertView = inflater.inflate(R.layout.huanzhe_zhusu_shijian_fenlei_lv_item,
                            parent, false);
                    holder3 = new ViewHolder3(convertView);
                    convertView.setTag(holder3);
                    break;
                case TYPE_4:
                    convertView = inflater.inflate(R.layout.huli_jilu_beizhu_lv_item_tv3,
                            parent, false);
                    holder4 = new ViewHolder4(convertView);
                    convertView.setTag(holder4);
                    break;
                default:
                    break;
            }

        } else {
            switch (type) {
                case TYPE_1:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
                case TYPE_2:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
                case TYPE_3:
                    holder3 = (ViewHolder3) convertView.getTag();
                    break;
                case TYPE_4:
                    holder4 = (ViewHolder4) convertView.getTag();
                    break;
            }
        }
        // 设置资源
        switch (type) {
            case TYPE_1:
                holder1.mHuliJiluBeizhuLvItemTv1.setText(ls.get(position) + "");
                break;
            case TYPE_2:
                if (ls.get(position) instanceof YiZhuBean.OrderNoteListBean) {
                    orderNoteListBean = (YiZhuBean.OrderNoteListBean) ls.get(position);
                    if (orderNoteListBean.getNoterecordtime() != null) {
                        dateString = DateUtils.getDateString("yyyy年MM月dd日HH点mm分", orderNoteListBean.getNoterecordtime());
                    }
                    holder2.mSjTv.setText(dateString);
                    holder2.mBeizhuTv.setText(orderNoteListBean.getNotevalue());

                    holder2.mZhuangtaiTv.setText(orderNoteListBean.getNoteStatus());
                }
                break;
            case TYPE_3:
                if (ls.get(position) instanceof String) {
                    String str = (String) ls.get(position);
                    if (str.startsWith("0001")) {
                        str = str.replace("0001", "");
                    } else if (str.startsWith("0002")) {
                        str = str.replace("0002", "");
                    }
                    holder3.mShijianTv.setText(str);
                } else {
                    nursingEventTemplateDefineListBean = (NursingEventTempLateLisBean.DataBean.NursingEventTemplateDefineListBean) ls.get(position);
                    holder3.mShijianTv.setText("");
                    if (nursingEventTemplateDefineListBean.getTemplate() != null) {
                        holder3.mShijianTv.setText(nursingEventTemplateDefineListBean.getTemplate().getTemplatename());
                    }
                }
                // 控制对勾的显示、隐藏以及文本框的颜色
                if (booleanMap.get(position)) {
                    holder3.mShijianTvLan.setVisibility(View.VISIBLE);
                    holder3.mIconSelectImgv.setVisibility(View.VISIBLE);
                } else {
                    holder3.mShijianTvLan.setVisibility(View.GONE);
                    holder3.mIconSelectImgv.setVisibility(View.GONE);
                }
                final ViewHolder3 finalHolder = holder3;
                holder3.mShijianTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt = "";
                        if (finalHolder.mShijianTvLan.getVisibility() == View.VISIBLE) {
                            finalHolder.mShijianTvLan.setVisibility(View.GONE);
                            finalHolder.mIconSelectImgv.setVisibility(View.GONE);
                            booleanMap.put(position, false);
                        } else {
                            booleanMap.put(position, true);
                            if (ls.get(position) instanceof String) {

                            } else {
                                if (nursingEventTemplateDefineListBean.getTemplate() != null) {
                                    edt = nursingEventTemplateDefineListBean.getTemplate().getTemplatecontent();
                                    edt = edt.replace("<span style=\"color:red\">XX</span>", "XX");
                                }
                            }
                            finalHolder.mShijianTvLan.setVisibility(View.VISIBLE);
                            finalHolder.mIconSelectImgv.setVisibility(View.VISIBLE);
                            Iterator<Integer> iterator = booleanMap.keySet().iterator();
                            while (iterator.hasNext()) {
                                Integer next = iterator.next();

                                if (next != position) {
                                    if (ls.get(next) instanceof String) {
                                        String str = (String) ls.get(next);
                                        if (!str.startsWith("0001")) {
                                            booleanMap.put(next, false);
                                        }
                                    } else {
                                        booleanMap.put(next, false);
                                    }
                                }
                            }
                        }
                        notifyDataSetChanged();
                    }
                });
                break;
            case TYPE_4:
                holder4.huliJiluBeizhuLvItemTv3Edt.setText(edt);
                holder4.huliJiluBeizhuLvItemTv3Edt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        edt = s.toString();
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        edt = s.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        edt = s.toString();
                    }
                });
                break;
        }
        return convertView;
    }

    public class ViewHolder1 {
        private TextView mHuliJiluBeizhuLvItemTv1;

        public ViewHolder1(View view) {
            mHuliJiluBeizhuLvItemTv1 = (TextView) view.findViewById(R.id.huli_jilu_beizhu_lv_item_tv1);
        }
    }

    public class ViewHolder2 {
        private RelativeLayout mHuliJiluBeizhuLvItemTv2Rl;
        private TextView mSjTv;
        private TextView mBeizhuTv;
        private TextView mZhuangtaiTv;

        public ViewHolder2(View view) {
            mHuliJiluBeizhuLvItemTv2Rl = (RelativeLayout) view.findViewById(R.id.huli_jilu_beizhu_lv_item_tv2_rl);
            mSjTv = (TextView) view.findViewById(R.id.sj_tv);
            mBeizhuTv = (TextView) view.findViewById(R.id.beizhu_tv);
            mZhuangtaiTv = (TextView) view.findViewById(R.id.zhuangtai_tv);
        }
    }

    public class ViewHolder3 {
        private TextView mShijianTv;
        private TextView mShijianTvLan;
        private ImageView mIconSelectImgv;


        public ViewHolder3(View view) {
            mShijianTv = (TextView) view.findViewById(R.id.shijian_tv);
            mShijianTvLan = (TextView) view.findViewById(R.id.shijian_tv_lan);
            mIconSelectImgv = (ImageView) view.findViewById(R.id.icon_select_imgv);

        }
    }

    public class ViewHolder4 {
        private EditText huliJiluBeizhuLvItemTv3Edt;


        public ViewHolder4(View view) {
            huliJiluBeizhuLvItemTv3Edt = (EditText) view.findViewById(R.id.huli_jilu_beizhu_lv_item_tv3_edt);


        }
    }
}
