package com.ge.med.mobile.nursing.ui.activity;

import android.graphics.Color;
import android.widget.RelativeLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by Administrator on 2016/11/22.
 */
public class BiaoTiLianColor {
    public static void setColor(String carelevel, SystemBarTintManager tintManager, RelativeLayout mRlTitleBj) {
        switch (carelevel){
            case "特级":
                tintManager.setTintColor(Color.parseColor("#7a000f"));
                mRlTitleBj.setBackgroundColor(Color.parseColor("#7a000f"));
            break;
            case "一级":
                tintManager.setTintColor(Color.parseColor("#cb0019"));
                mRlTitleBj.setBackgroundColor(Color.parseColor("#cb0019"));
                break;
            case "二级":
                tintManager.setTintColor(Color.parseColor("#f5a623"));
                mRlTitleBj.setBackgroundColor(Color.parseColor("#f5a623"));
                break;
            case "三级":
                tintManager.setTintColor(Color.parseColor("#90c556"));
                mRlTitleBj.setBackgroundColor(Color.parseColor("#90c556"));
                break;

        }


    }
}
