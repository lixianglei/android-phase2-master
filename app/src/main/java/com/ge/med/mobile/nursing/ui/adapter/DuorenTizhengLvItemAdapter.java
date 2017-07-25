package com.ge.med.mobile.nursing.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.forjson.entity.VitalDefineEntity;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignRecord;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignSheet;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignWardDefine;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class DuorenTizhengLvItemAdapter extends BaseAdapter {

    private List<VitalSignSheet> objects = new ArrayList<VitalSignSheet>();
    private List<VitalSignWardDefine> mVitalDefines = null;

    public void setDan(boolean dan) {
        isDan = dan;
    }

    private boolean isDan;

    private Context context;
    private LayoutInflater layoutInflater;

    public DuorenTizhengLvItemAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setVitalSigns(List<VitalSignSheet> vitals) {
        objects = VitalSignSheet.sortByTimeDesc(vitals);
    }

    public void setVitalSignDefines(List<VitalSignWardDefine> defines) {
        mVitalDefines = defines;
    }

    public List<VitalSignWardDefine> getmVitalDefines() {
        return mVitalDefines;
    }

    @Override
    public int getCount() {
        LogUtil.d("DuorenTizhengLvItemAdapter.getCount() return " + objects.size());
        return objects.size();
    }

    @Override
    public VitalSignSheet getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.duoren_tizheng_lv_item1, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((VitalSignSheet) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private String getVitalValueStringBySignType(VitalSignSheet object, int signType) {
        String retval = "";
        if (object == null || object.getSignRecordList() == null) {
            LogUtil.e("Can not get vital value since sheet is null.");
            return retval;
        }
        LogUtil.d("getVitalValueStringBySignType.sheetId:" + object.getId()
                + ", vitalRecord count is " + (object.getSignRecordList() == null ? "0" : object.getSignRecordList().size()));

        VitalSignRecord vital = null;
        for (VitalSignRecord record : object.getSignRecordList()) {
            LogUtil.d("getVitalValueStringBySignType.define.Id[" + signType
                    + "] vs record.type[" + record.getType() + "],signType[" + record.getSigntype()
                    + "], sheetid[" + record.getSheetid() + "],standardVitalSignType[" + record.getStandardVitalSignType() + "]");
            if (record.getSigntype().intValue() == signType) {
                vital = record;
                break;
            }
        }
        if (vital != null) {
            retval = vital.getSignvalue();
        }
        return retval;
    }

    private void initializeViews(VitalSignSheet object, ViewHolder holder) {
        holder.mDuorenTizhengCheckbox.setClickable(false);
        //holder.mDuorenTizhengCheckbox.setEnabled(false);
        if (object == null) {
            LogUtil.e("Can not show vital sign sheet since the data object is null!");
            return;
        }
        long time = 0;
        try {
            time = Long.parseLong(object.getTime());
        } catch (NumberFormatException e) {
            LogUtil.e("Can not parse time[" + (object.getTime() == null ? "null" : object.getTime()) + "] to long!error is :" + e.getMessage());
        }
        holder.duorenTizhengcheckImgv.setVisibility(View.GONE);
        if (isDan) {
            holder.duorenTizhengChXmIdTv.setText("护士：" + object.getCreatedby());
            holder.duorenTizhengSjTv.setText(DateUtils.getDateString("yyyy-MM-dd HH:mm", time));
            //holder.duorenTizhengSjTv.setVisibility(View.GONE);
        } else {
            String pName = object.getPatientName();
            if (pName != null && pName.length() > 4) {
                pName = pName.substring(0, 4);
            }
            holder.duorenTizhengChXmIdTv.setText(object.getBedNo() + "床 " + pName + " (" + object.getMrnNo() + ")");
            holder.duorenTizhengSjTv.setVisibility(View.VISIBLE);
            holder.duorenTizhengSjTv.setText(DateUtils.getDateString(" HH:mm", time));
        }
        holder.duorenTizhengImgv.setImageResource(AdapterUtil.findCareLevelImage(object.getCareLevel()));
        if (mVitalDefines != null && mVitalDefines.size() > 0) {
            VitalDefineEntity define = null;
            for (int i = 0; i < mVitalDefines.size(); i++) {
                if (i >= 8) break;
                define = mVitalDefines.get(i).getVitalDefine();
                if (define != null) {
                    LogUtil.d("initializeViews.mVitalDefines.get(" + i + ").id["
                            + define.getId() + "],signName[" + define.getSignname() + "],unit[" + define.getUnitdesc() + "]");
                    holder.vitalNameList.get(i).setText(define.getSignname());
                    holder.vitalValueList.get(i).setText(
                            getVitalValueStringBySignType(object, define.getId()) + " " + define.getUnitdesc());
                } else {
                    LogUtil.e("Can not initialize vital item view since mVitalDefines is null or empty!");
                }
            }
        } else
            LogUtil.e("Can not initialize vital item view since mVitalDefines is null or empty!");

    }

    protected class ViewHolder {
        private ImageView duorenTizhengImgv;
        private ImageView duorenTizhengcheckImgv;
        private TextView duorenTizhengChXmIdTv;
        private TextView duorenTizhengSjTv;
        private List<TextView> vitalNameList = new ArrayList<>();
        private List<TextView> vitalValueList = new ArrayList<>();
        private CheckBox mDuorenTizhengCheckbox;


        public ViewHolder(View view) {
            mDuorenTizhengCheckbox = (CheckBox) view.findViewById(R.id.duoren_tizheng_checkbox);
            duorenTizhengImgv = (ImageView) view.findViewById(R.id.duoren_tizheng_imgv);
            duorenTizhengcheckImgv = (ImageView) view.findViewById(R.id.duoren_tizheng_check_imgv);
            duorenTizhengChXmIdTv = (TextView) view.findViewById(R.id.duoren_tizheng_title_name);
            duorenTizhengSjTv = (TextView) view.findViewById(R.id.duoren_tizheng_title_time);
            for (int i = 1; i <= 8; i++) {
                vitalNameList.add((TextView) view.findViewWithTag("vital_name_tv_" + i));
                vitalValueList.add((TextView) view.findViewWithTag("vital_value_tv_" + i));
            }
        }
    }
}
