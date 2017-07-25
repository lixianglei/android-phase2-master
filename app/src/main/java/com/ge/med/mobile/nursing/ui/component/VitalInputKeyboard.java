package com.ge.med.mobile.nursing.ui.component;


import android.view.View;
import android.widget.RelativeLayout;

import com.example.sj.library.base.BaseActivity;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.forjson.entity.VitalDefineEntity;
import com.ge.med.mobile.nursing.forjson.entity.VitalNoteListEntity;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignRecord;

import org.xutils.common.util.LogUtil;

import java.util.Collections;
import java.util.Comparator;

/**
 * 体征键盘适配包装类.
 * 主要负责根据体征定义创建匹配的体征键盘处理子类，并提供与activity的读写接口
 */
public class VitalInputKeyboard {

    private RelativeLayout mOverallLayout;
    private BaseActivity activity;
    private VitalDefineEntity vitalDefine;
    private VitalSignRecord vitalRecord;
    private VitalKeyboard currentKeyboard;

    public VitalInputKeyboard(final BaseActivity activity) {
        this.activity = activity;
        mOverallLayout = (RelativeLayout) activity.findViewById(R.id.vital_keyboard);
        mOverallLayout.setVisibility(View.GONE);
    }

    public void setVisible(boolean isShow) {
        if (isShow) mOverallLayout.setVisibility(View.VISIBLE);
        else mOverallLayout.setVisibility(View.GONE);
    }

    public VitalSignRecord getVitalRecord() {
        return vitalRecord;
    }

    public VitalDefineEntity getVitalDefine() {
        return vitalDefine;
    }

    /**
     * 根据体征定义的vcode和inputtype获得当前操作的键盘子实例
     */
    protected VitalKeyboard getKeyBoard() {
        VitalKeyboard keyboard = null;
        if (vitalDefine != null) {
            // 根据VCODE获得特殊的键盘
            if (vitalDefine.getVcode() != null && (Constant.VITAL_SIGN_TYPE_TEMPERATURE.equalsIgnoreCase(vitalDefine.getVcode().trim()) || Constant.VITAL_SIGN_TYPE_PAIN.equalsIgnoreCase(vitalDefine.getVcode().trim()))) {
                if (Constant.VITAL_SIGN_TYPE_TEMPERATURE.equalsIgnoreCase(vitalDefine.getVcode().trim())) {
                    keyboard = new VitalTempNumberKeyboard(activity);
                } else {
                    keyboard = new VitalPainKeyboard(activity);
                }
            } else if (vitalDefine.getInputtype() != null) {// 根据Inputtype获得通用的键盘
                switch (vitalDefine.getInputtype()) {
                    case Constant.VITAL_INPUT_TYPE_HOT:
                        keyboard = new VitalHotNoteSelectorKeyboard(activity);
                        break;
                    case Constant.VITAL_INPUT_TYPE_EDITABLE_HOT:
                        keyboard = new VitalHotNoteEditorKeyboard(activity);
                        break;
                    case Constant.VITAL_INPUT_TYPE_NUMBER_RANGE:
                        keyboard = new VitalRangeNumberKeyboard(activity);
                        break;
                    case Constant.VITAL_INPUT_TYPE_NOTE:
                        keyboard = new VitalEditorKeyboard(activity);
                        break;
                    case Constant.VITAL_INPUT_TYPE_NUMBER:
                    default:
                        keyboard = new VitalDefaultNumberKeyboard(activity);
                        break;
                }
            } else {
                LogUtil.e("VitalInputKeyboard.getKeyboard> Cannot initialize keyboard since vcode["
                        + (vitalDefine.getVcode() == null ? "null" : vitalDefine.getVcode()) + "] or inputtype["
                        + (vitalDefine.getInputtype() == null ? "null" : vitalDefine.getInputtype()) + "] not correct!");
            }

        }
        return keyboard;
    }

    public void setVitalThing(VitalDefineEntity vitalDefine, VitalSignRecord vitalRecord) {
        if (vitalDefine == null) {
            LogUtil.e("Can not load vital keyboard since vital define is null!");
            return;
        }
        this.vitalDefine = vitalDefine;

        if (this.vitalDefine.getVitalNoteList() != null) { // 对热词按照PK升序进行排序
            Collections.sort(vitalDefine.getVitalNoteList(), new Comparator<VitalNoteListEntity>() {
                @Override
                public int compare(VitalNoteListEntity lhs, VitalNoteListEntity rhs) {
                    if (lhs.getId().intValue() > rhs.getId().intValue()) return 1;
                    else if (lhs.getId().intValue() < rhs.getId().intValue()) return -1;
                    return 0;
                }
            });
        }
        // 根据体征定义的inputtype设置当前操作的键盘子实例
        currentKeyboard = getKeyBoard();
        if (currentKeyboard == null) {
            return;
        }
        this.vitalRecord = vitalRecord;
        currentKeyboard.setVitalSign(vitalDefine, vitalRecord);
    }

}