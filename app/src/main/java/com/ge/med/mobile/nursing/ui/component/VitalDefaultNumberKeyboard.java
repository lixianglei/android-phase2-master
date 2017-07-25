package com.ge.med.mobile.nursing.ui.component;

import android.view.View;
import android.widget.TextView;

import com.example.sj.library.base.BaseActivity;
import com.ge.med.mobile.nursing.R;

import org.xutils.common.util.LogUtil;

/**
 * Created by Alex Qu on 2017/4/7.
 */

public class VitalDefaultNumberKeyboard extends VitalNumberKeyboard {

    private TextView singleTextView;
    private TextView singleUnitView;

    public VitalDefaultNumberKeyboard(BaseActivity activity) {
        super(activity);
        singleTextView = (TextView) activity.findViewById(R.id.vital_single_tv);
        if (mSingleValueDisplayLayout != null)
            mSingleValueDisplayLayout.setVisibility(View.VISIBLE);
        singleUnitView = (TextView) activity.findViewById(R.id.vital_single_unit_tv);
    }

    @Override
    public void initKeyboard() {
        if (activity == null) {
            LogUtil.e("Activity can not be null!");
            return;
        }
        super.initKeyboard();
        if (singleUnitView != null && vitalDefine != null && vitalDefine.getUnitdesc() != null) {// 设置单位显示
            singleUnitView.setText(vitalDefine.getUnitdesc());
        }
    }

    @Override
    protected String getVitalValue() {
        return singleTextView.getText().toString();
    }

    @Override
    protected void setVitalDisplayTextView(String vitalValue) {
        singleTextView.setText(vitalValue);
    }

    protected void displayVitalData() {
        LogUtil.d("VitalDefaultNumberKeyboard.displayVitalData calling... vitalRecord is [type:"
                + (vitalRecord == null ? "null" : vitalRecord.getSigntype()) + ",value:"
                + (vitalRecord == null ? "null" : vitalRecord.getSignvalue()) + "]");
        super.displayVitalData();
        if (null != vitalRecord) {
            singleTextView.setText(vitalRecord.getSignvalue());
        }
    }

    @Override
    protected String switchButtonClick(String vitalValue, TextView view) {
        String retval = vitalValue;
        if (view != null) retval = retval + view.getText().toString();
        return retval;
    }
}
