package com.ge.med.mobile.nursing.ui.component;

import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.sj.library.base.BaseActivity;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.forjson.entity.VitalDefineEntity;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignRecord;

import org.xutils.common.util.LogUtil;

/**
 * Created by Alex Qu on 2017/4/7.
 */

public abstract class VitalKeyboard {

    protected LinearLayout mRangeValueDisplayLayout;
    protected LinearLayout mSingleValueDisplayLayout;
    protected LinearLayout mSingleEditableValueDisplayLayout;
    protected LinearLayout mEditorValueDisplayLayout;
    protected LinearLayout mNumberKeyboardLayout;
    protected LinearLayout mPainKeyboardLayout;
    protected GridView keyboardGridView;
    protected ListView mMoreNotesListView;
    protected BaseActivity activity;
    protected VitalDefineEntity vitalDefine;
    protected VitalSignRecord vitalRecord;
    protected String defaultVitalNote;

    public VitalKeyboard(final BaseActivity activity){
        this.activity = activity;
        mSingleValueDisplayLayout = (LinearLayout) activity.findViewById(R.id.vital_single_display_ll);
        if (mSingleValueDisplayLayout != null) mSingleValueDisplayLayout.setVisibility(View.GONE);
        mRangeValueDisplayLayout = (LinearLayout) activity.findViewById(R.id.vital_range_display_ll);
        if (mRangeValueDisplayLayout != null) mRangeValueDisplayLayout.setVisibility(View.GONE);
        mSingleEditableValueDisplayLayout = (LinearLayout) activity.findViewById(R.id.vital_single_editable_display_ll);
        if (mSingleEditableValueDisplayLayout != null) mSingleEditableValueDisplayLayout.setVisibility(View.GONE);
        mEditorValueDisplayLayout = (LinearLayout) activity.findViewById(R.id.vital_editor_display_ll);
        if (mEditorValueDisplayLayout != null) mEditorValueDisplayLayout.setVisibility(View.GONE);
        mNumberKeyboardLayout = (LinearLayout) activity.findViewById(R.id.number_keyboard_ll);
        if (mNumberKeyboardLayout != null) mNumberKeyboardLayout.setVisibility(View.GONE);
        keyboardGridView = (GridView) activity.findViewById(R.id.vital_keyboard_gv);
        if (keyboardGridView != null) keyboardGridView.setVisibility(View.GONE);
        mPainKeyboardLayout = (LinearLayout) activity.findViewById(R.id.pain_keybaord_layout);
        if (mPainKeyboardLayout != null) mPainKeyboardLayout.setVisibility(View.GONE);
        mMoreNotesListView = (ListView)activity.findViewById(R.id.more_notes_lv);
        if (mMoreNotesListView != null) mMoreNotesListView.setVisibility(View.GONE);
    }

    public void setVitalSign(VitalDefineEntity define,VitalSignRecord record ){
        this.vitalRecord = record;
        this.vitalDefine = define;
        initKeyboard();
        displayVitalData();
    }

    /**
     * 初始化键盘控件
     */
    public abstract void initKeyboard();

    /**
     * 初步检查体征数值的录入合理性
     */
    protected String checkVitalValue(String vitalValue){
        String retval = "";
        if (vitalDefine == null){
            LogUtil.e("Can not check vital value range since vital define is null!");
            activity.showToastShort("体征定义错误！无法设置体征值！");
            return retval;
        }
        if (null == vitalValue || vitalValue.isEmpty()) return vitalValue;
        if (vitalValue.length() > 255){
            LogUtil.i("Can not input vital value more than 255 character:" + vitalValue);
            retval = vitalValue.substring(0, 255);
        } else retval = vitalValue;
        return retval;
    }
    /**
     * 将控件的数据更新到数据bean中
     */
    protected void addOrUpdateVitalData(String vitalValue, String vitalNote, String vitalHandle){
        if (vitalRecord == null){
            LogUtil.e("Can not add or update vital sign data to data bean since vital record is null!");
            return;
        }
        vitalRecord.setSignvalue(vitalValue);
        vitalRecord.setNote(vitalNote);
        vitalRecord.setHandlemeasure(vitalHandle);
    }
    /**
     * 将历史体征数据显示在控件上
     */
    protected abstract void displayVitalData();
}
