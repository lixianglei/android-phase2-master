package com.ge.med.mobile.nursing.ui.component;

import android.view.View;
import android.widget.AdapterView;
import com.example.sj.library.base.BaseActivity;
import com.ge.med.mobile.nursing.forjson.entity.VitalNoteListEntity;
import com.ge.med.mobile.nursing.ui.adapter.VitalKeyboardItemAdapter;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;

/**
 * Created by Alex Qu on 2017/4/7.
 */

public abstract class VitalGridNoteKeyboard extends VitalKeyboard{

    protected VitalKeyboardItemAdapter keyboardItemAdapter;


    public VitalGridNoteKeyboard(BaseActivity activity) {
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
    }

    /**
     * 将体征定义里的配置显示在控件上
     */
    protected void bindVitalDefine(){
        LogUtil.d("VitalGridNoteKeyboard.bindVitalDefine calling...");
        if (vitalDefine != null){
            if (vitalDefine.getVitalNoteList() != null && keyboardItemAdapter != null) {
                keyboardItemAdapter.setObjects(new ArrayList<String[]>());
                String[] object = null;
                for (VitalNoteListEntity vNote : vitalDefine.getVitalNoteList()) { // 设置键盘按钮
                    if (vNote != null) {
                        object = new String[2];
                        object[1] = vNote.getNote();
                        if (vNote.getAndroidshowname() == null || vNote.getAndroidshowname().trim().isEmpty())
                            object[0] = vNote.getNote();
                        else object[0] = vNote.getAndroidshowname();
                        keyboardItemAdapter.getObjects().add(object);
                    }
                }
            }
        }
    };
    /**
     * 查找控件并初始化
     */
    private void loadControls(){
        if (keyboardGridView != null) keyboardGridView.setVisibility(View.VISIBLE);
        keyboardItemAdapter = new VitalKeyboardItemAdapter(activity);
        keyboardGridView.setAdapter(keyboardItemAdapter);
    }
}
