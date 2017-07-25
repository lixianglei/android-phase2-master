package com.ge.med.mobile.nursing.ui.component;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.example.sj.library.base.BaseActivity;
import com.ge.med.mobile.nursing.R;
import org.xutils.common.util.LogUtil;

/**
 * Created by Alex Qu on 2017/4/7.
 */

public class VitalEditorKeyboard extends VitalKeyboard{

    private EditText mEditValueDisplayEditText;
    public VitalEditorKeyboard(BaseActivity activity) {
        super(activity);
        loadControls();
    }

    @Override
    public void initKeyboard() {
        LogUtil.d("VitalEditorKeyboard.initKeyboard calling...do nothing");
    }

    /**
     * 查找控件并初始化
     */
    private void loadControls(){
        if (mEditorValueDisplayLayout != null) mEditorValueDisplayLayout.setVisibility(View.VISIBLE);
        mEditValueDisplayEditText = (EditText) activity.findViewById(R.id.vital_editor_et);
        if (mEditValueDisplayEditText != null){
            mEditValueDisplayEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s != null && vitalRecord != null) vitalRecord.setSignvalue(s.toString());
                }
            });
        }
    }

    @Override
    protected void displayVitalData() {
        LogUtil.d("VitalEditorKeyboard.displayVitalData calling... ");
        if (vitalRecord != null){
            if (mEditValueDisplayEditText != null) mEditValueDisplayEditText.setText(vitalRecord.getSignvalue());
        }
    }
}
