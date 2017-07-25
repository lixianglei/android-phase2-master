package com.ge.med.mobile.nursing.ui.view.dialog;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Lisa on 2017/6/16.
 * 确认提示框
 */

public class ConfirmDialog extends BasePopupWindow implements View.OnClickListener {

    private ClickListener listener;

    public ConfirmDialog(Activity context, String hint) {
        super(context);
        RelativeLayout rl_cancel = (RelativeLayout) findViewById(R.id.rl_cancel);
        RelativeLayout rl_ok = (RelativeLayout) findViewById(R.id.rl_ok);
        TextView tv_hint = (TextView) findViewById(R.id.tv_hint);
        tv_hint.setText(hint);

        // 点击事件
        setViewClickListener(this, rl_ok, rl_cancel);
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
        return createPopupById(R.layout.dialog_confirm);
    }

    @Override
    public View initAnimaView() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 取消
            case R.id.rl_cancel: {
                this.dismiss();
            }
            break;
            // 确定
            case R.id.rl_ok: {
                dismiss();
                if (listener != null) {
                    listener.clickOk();
                }
            }
            break;
        }
    }

    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    public interface ClickListener {
        void clickOk();
    }
}
