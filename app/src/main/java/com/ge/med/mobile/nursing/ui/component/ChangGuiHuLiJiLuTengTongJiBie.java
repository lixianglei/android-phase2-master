package com.ge.med.mobile.nursing.ui.component;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;

/**
 * Created by Administrator on 2017/4/1.
 */
public class ChangGuiHuLiJiLuTengTongJiBie {
    private final VitalPainKeyboard vitalPainKeyboard;
    private MyBaseActivity mActivity;
    private LayoutInflater layoutInflater;

    public ChangGuiHuLiJiLuTengTongJiBie(MyBaseActivity mActivity, LayoutInflater layoutInflater, LinearLayout linearLayout) {
        this.mActivity = mActivity;
        this.layoutInflater = layoutInflater;
        View view = layoutInflater.inflate(R.layout.tengtong_keyboard_include, null);
        linearLayout.removeAllViewsInLayout();
        linearLayout.addView(view);
        vitalPainKeyboard = new VitalPainKeyboard(mActivity);
        vitalPainKeyboard.setJiBieText("");
    }
    public void setJiBieText(String text){
        vitalPainKeyboard.setJiBieText(text);
    }
    public String  getJiBieText(){
      return   vitalPainKeyboard.getJiBieText();
    }
}
