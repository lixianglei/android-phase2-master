package com.ge.med.mobile.nursing.ui.view.dialog;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Lisa on 2017/6/6.
 * 更新APP对话框
 */

public class AppUpdateDialog extends BasePopupWindow implements View.OnClickListener {
    private RelativeLayout rl_cancel, rl_ok;
    private LinearLayout ll_downing;
    private LinearLayout ll_buttons;
    private TextView tv_hint;
    private ClickListener listener;

    public AppUpdateDialog(Activity context) {
        super(context);
        rl_cancel = (RelativeLayout) findViewById(R.id.rl_cancel);
        rl_ok = (RelativeLayout) findViewById(R.id.rl_ok);
        ll_downing = (LinearLayout) findViewById(R.id.ll_downing);
        ll_buttons = (LinearLayout) findViewById(R.id.ll_buttons);
        tv_hint = (TextView) findViewById(R.id.tv_hint);
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
        return createPopupById(R.layout.dialog_app_update);
    }

    @Override
    public View initAnimaView() {
        return null;
    }

    /**
     * 点击事件
     *
     * @param v
     */
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
                listener.clickOk();
            }
            break;
        }
    }

    /**
     * 改变为下载状态
     */
    public void changeStatusToDownloading() {
        tv_hint.setVisibility(View.GONE);
        ll_buttons.setVisibility(View.GONE);
        ll_downing.setVisibility(View.VISIBLE);
    }

    /**
     * 设置监听
     */
    public void setListener(ClickListener listener) {
        this.listener = listener;
    }


    public interface ClickListener {
        void clickOk();
    }
}
