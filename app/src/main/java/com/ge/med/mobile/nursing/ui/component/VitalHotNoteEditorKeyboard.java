package com.ge.med.mobile.nursing.ui.component;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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

public class VitalHotNoteEditorKeyboard extends VitalGridNoteKeyboard{

    private EditText mSingleEditableValueDisplayEditText;
    private TextView mSingleEditableValueDisplayUnitTextView;

    public VitalHotNoteEditorKeyboard(BaseActivity activity) {
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
        if (mSingleEditableValueDisplayUnitTextView != null && vitalDefine != null && vitalDefine.getUnitdesc() != null){// 设置单位显示
            mSingleEditableValueDisplayUnitTextView.setText(vitalDefine.getUnitdesc());
        }
    }

    /**
     * 查找控件并初始化
     */
    private void loadControls(){
        if (mSingleEditableValueDisplayLayout != null) mSingleEditableValueDisplayLayout.setVisibility(View.VISIBLE);
        mSingleEditableValueDisplayEditText = (EditText) activity.findViewById(R.id.vital_single_editable_tv);
        mSingleEditableValueDisplayUnitTextView = (TextView) activity.findViewById(R.id.vital_single_editable_unit_tv);
        if (keyboardGridView != null)
            keyboardGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.d("VitalHotNoteEditorKeyboard.keyboardGridView.onItemClick calling...");
                if (keyboardItemAdapter.getObjects().get(position)[1] == null || keyboardItemAdapter.getObjects().get(position)[1].isEmpty()){
                    LogUtil.d("Click empty button!");
                    return;
                }
                if (Constant.VITAL_KEYBOARD_BUTTON_BACK.equals(keyboardItemAdapter.getObjects().get(position))){
                    if (mSingleEditableValueDisplayEditText.getText() != null && mSingleEditableValueDisplayEditText.getText().length() > 0){
                        mSingleEditableValueDisplayEditText.setText(mSingleEditableValueDisplayEditText.getText().subSequence(0, mSingleEditableValueDisplayEditText.getText().length()-1));
                    }
                }else{
                    if ((mSingleEditableValueDisplayEditText.getText() + keyboardItemAdapter.getObjects().get(position)[1]).length() > 10){
                        activity.showToastShort("最多输入10个字符");
                    }
                    mSingleEditableValueDisplayEditText.setText(mSingleEditableValueDisplayEditText.getText() + keyboardItemAdapter.getObjects().get(position)[1]);
                    vitalRecord.setSignvalue(mSingleEditableValueDisplayEditText.getText().toString());
                    LogUtil.e("set vitalRecord[" + vitalRecord + "].value=" + vitalRecord.getSignvalue());
                }
            }
        });
    }

    @Override
    protected void displayVitalData() {
        LogUtil.d("VitalHotNoteEditorKeyboard.displayVitalData calling... ");
        if (vitalRecord != null){
            mSingleEditableValueDisplayEditText.setText(vitalRecord.getSignvalue());
        }
    }
}
