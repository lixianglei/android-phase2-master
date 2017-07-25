package com.ge.med.mobile.nursing.ui.component;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.example.sj.library.base.BaseActivity;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;

import org.xutils.common.util.LogUtil;

/**
 * Created by Alex Qu on 2017/4/7.
 */

public class VitalRangeNumberKeyboard extends VitalNumberKeyboard{


    private TextView mRangeValueLeftDisplayTextView;
    private TextView mRangeValueRightDisplayTextView;
    private TextView mRangeValueDisplayUnitTextView;
    private TextView mRangeLeftFocusLineTextView;
    private TextView mRangeRightFocusLineTextView;
    private boolean rangeLeft = true;

    public VitalRangeNumberKeyboard(BaseActivity activity) {
        super(activity);
        if (mRangeValueDisplayLayout != null) mRangeValueDisplayLayout.setVisibility(View.VISIBLE);
        mRangeValueLeftDisplayTextView= (TextView) activity.findViewById(R.id.vital_range_tv1);
        mRangeValueRightDisplayTextView= (TextView) activity.findViewById(R.id.vital_range_tv2);
        mRangeValueDisplayUnitTextView = (TextView) activity.findViewById(R.id.vital_range_unit_tv);
        mRangeLeftFocusLineTextView = (TextView) activity.findViewById(R.id.vital_range_focus_line_left);
        mRangeRightFocusLineTextView = (TextView) activity.findViewById(R.id.vital_range_focus_line_right);
    }

    @Override
    protected void displayVitalData() {
        super.displayVitalData();
        LogUtil.d("VitalRangeNumberKeyboard.displayVitalData calling...");
        if (null != vitalRecord){
            if (vitalRecord.getSignvalue() == null || vitalRecord.getSignvalue().trim().isEmpty()) {
                mRangeValueLeftDisplayTextView.setText("");
                mRangeValueRightDisplayTextView.setText("");
            }else {
                LogUtil.d("Range vital value is " + vitalRecord.getSignvalue());
                String[] vals = vitalRecord.getSignvalue().split("-");
                if (vals != null && vals.length > 0) {
                    mRangeValueLeftDisplayTextView.setText(vals[0]);
                    if (vals.length > 1) {
                        mRangeValueRightDisplayTextView.setText(vals[1]);
                    }
                }else LogUtil.e("Split range value failed!");
            }
        }
    }

    @Override
    protected String getVitalValue() {
        String retval = null;
        if (rangeLeft) {
            if (mRangeValueLeftDisplayTextView != null) retval = mRangeValueLeftDisplayTextView.getText().toString();
        }else{
            if (mRangeValueRightDisplayTextView != null) retval = mRangeValueRightDisplayTextView.getText().toString();
        }
        return retval;
    }

    @Override
    protected String switchButtonClick(String vitalValue, TextView view) {
        if (rangeLeft) {
            mRangeLeftFocusLineTextView.setVisibility(View.INVISIBLE);
            mRangeRightFocusLineTextView.setVisibility(View.VISIBLE);
        }else{
            mRangeLeftFocusLineTextView.setVisibility(View.VISIBLE);
            mRangeRightFocusLineTextView.setVisibility(View.INVISIBLE);
        }
        rangeLeft = !rangeLeft;
        return getVitalValue();
    }

    @Override
    protected void setVitalDisplayTextView(String vitalValue) {
        if (rangeLeft) mRangeValueLeftDisplayTextView.setText(vitalValue);
        else mRangeValueRightDisplayTextView.setText(vitalValue);
    }

    @Override
    protected void addOrUpdateVitalData(String vitalValue, String vitalNote, String vitalHandle) {
        String otherPart = "";
        if (!rangeLeft) {
            if (mRangeValueLeftDisplayTextView != null) otherPart = mRangeValueLeftDisplayTextView.getText().toString();
            vitalValue = otherPart + Constant.VITAL_VALUE_SPLIT_CHAR + vitalValue;
        }else{
            if (mRangeValueRightDisplayTextView != null) otherPart = mRangeValueRightDisplayTextView.getText().toString();
            vitalValue = vitalValue + Constant.VITAL_VALUE_SPLIT_CHAR + otherPart;
        }
        super.addOrUpdateVitalData(vitalValue, vitalNote, vitalHandle);
    }

    @Override
    protected void bindVitalDefine() {
        super.bindVitalDefine();
        fastButtons[3].setText("切换");
        fastButtons[3].setEnabled(true);
        fastButtons[3].setTextColor(Color.BLACK);
        if (mRangeValueDisplayUnitTextView != null && vitalDefine != null && vitalDefine.getUnitdesc() != null){// 设置单位显示
            mRangeValueDisplayUnitTextView.setText(vitalDefine.getUnitdesc());
        }
    }
}
