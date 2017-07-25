package com.ge.med.mobile.nursing.ui.component;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.sj.library.base.BaseActivity;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.forjson.entity.VitalNoteListEntity;
import com.ge.med.mobile.nursing.ui.adapter.VitalKeyboardItemAdapter;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;

/**
 * Created by Alex Qu on 2017/4/7.
 */

public class VitalHotNoteSelectorKeyboard extends VitalGridNoteKeyboard{

    private TextView mSingleValueDisplayTextView;
    private TextView mSingleValueDisplayUnitTextView;

    public VitalHotNoteSelectorKeyboard(BaseActivity activity) {
        super(activity);
        loadControls();
    }

    @Override
    public void initKeyboard() {
        if (activity == null){
            LogUtil.e("Activity can not be null!");
            return;
        }
        bindVitalDefine();
        if (mSingleValueDisplayUnitTextView != null && vitalDefine != null && vitalDefine.getUnitdesc() != null){// 设置单位显示
            mSingleValueDisplayUnitTextView.setText(vitalDefine.getUnitdesc());
        }
    }

    /**
     * 查找控件并初始化
     */
    private void loadControls(){
        if (mSingleValueDisplayLayout != null) mSingleValueDisplayLayout.setVisibility(View.VISIBLE);
        mSingleValueDisplayTextView = (TextView) activity.findViewById(R.id.vital_single_tv);
        mSingleValueDisplayUnitTextView = (TextView) activity.findViewById(R.id.vital_single_unit_tv);

        if (keyboardGridView != null)
            keyboardGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    LogUtil.d("VitalHotNoteSelectorKeyboard.keyboardGridView.onItemClick calling...");
                    if (keyboardItemAdapter.getObjects().get(position)[1] == null || keyboardItemAdapter.getObjects().get(position)[1].isEmpty()){
                        LogUtil.d("Click empty button!");
                        return;
                    }
                    if (Constant.VITAL_KEYBOARD_BUTTON_BACK.equals(keyboardItemAdapter.getObjects().get(position))){
                        if (mSingleValueDisplayTextView.getText() != null && mSingleValueDisplayTextView.getText().length() > 0){
                            mSingleValueDisplayTextView.setText(mSingleValueDisplayTextView.getText().subSequence(0, mSingleValueDisplayTextView.getText().length()-1));
                        }
                    }else{
                        mSingleValueDisplayTextView.setText(keyboardItemAdapter.getObjects().get(position)[1]);
                        vitalRecord.setSignvalue(mSingleValueDisplayTextView.getText().toString());
                        LogUtil.e("set vitalRecord[" + vitalRecord + "].value=" + vitalRecord.getSignvalue());
                    }
                }
            });
    }

    @Override
    protected void displayVitalData() {
        LogUtil.d("VitalHotNoteSelectorKeyboard.displayVitalData calling... ");
        if (vitalRecord != null){
            mSingleValueDisplayTextView.setText(vitalRecord.getSignvalue());
        }
    }
}
