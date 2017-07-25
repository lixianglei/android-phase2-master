package com.ge.med.mobile.nursing.ui.view;

import android.animation.Animator;
import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;

import com.ge.med.mobile.nursing.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/3/16.
 */
public class FuWenPopuwindow extends BasePopupWindow {
    private Button mQuerenAnniuBt;
    private EditText mFuwenEdt;
    private String FuWenInterval;//复温间隔 分钟

    public String getFuWenInterval() {
        return FuWenInterval;
    }

    public FuWenPopuwindow(Activity context) {
        super(context);
        mQuerenAnniuBt = (Button) findViewById(R.id.queren_anniu_bt);
        mFuwenEdt = (EditText) findViewById(R.id.fuwen_edt);
        mQuerenAnniuBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FuWenInterval = mFuwenEdt.getText().toString();
                FuWenPopuwindow.this.dismiss();
            }
        });
    }

    @Override
    protected Animator initShowAnimator() {
        return super.initShowAnimator();
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.fuwen_sj_window);
    }

    @Override
    public View initAnimaView() {
        return null;
    }
}
