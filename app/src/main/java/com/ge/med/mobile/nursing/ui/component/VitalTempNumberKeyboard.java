package com.ge.med.mobile.nursing.ui.component;

import android.graphics.Color;
import android.widget.Button;
import com.example.sj.library.base.BaseActivity;

/**
 * 特殊体征“温度”的扩展子类
 * 主要负责将温度快捷录入按键赋值
 * Created by Alex Qu on 2017/4/7.
 */

public class VitalTempNumberKeyboard extends VitalDefaultNumberKeyboard{

    public VitalTempNumberKeyboard(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected void bindVitalDefine() {
        super.bindVitalDefine();
        int startTemp = 36;
        for (Button bt : fastButtons){
            bt.setText(startTemp++ + ".");
            bt.setEnabled(true);
            bt.setTextColor(Color.BLACK);
        }
    }
}
