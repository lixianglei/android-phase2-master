package com.ge.med.mobile.nursing.ui.view.dialog;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by lisa on 2017/6/21.
 * 病人类型
 * 压疮，跌倒评估时选择病人的类型，如果是孕妇，儿童，残疾人则直接跳转到措施界面，否则跳转到评估界面
 */

public class PatientTypeDialog extends BasePopupWindow implements View.OnClickListener {
    private ClickListener listener;
    private RadioButton radio_other;
    private RadioButton radio_pregnant;
    private RadioButton radio_children;
    private RadioButton radio_disabled;

    public PatientTypeDialog(Activity context) {
        super(context);
        RelativeLayout rl_cancel = (RelativeLayout) findViewById(R.id.rl_cancel);
        RelativeLayout rl_ok = (RelativeLayout) findViewById(R.id.rl_ok);
        // 其他
        radio_other = (RadioButton) findViewById(R.id.radio_other);
        // 孕妇
        radio_pregnant = (RadioButton) findViewById(R.id.radio_pregnant);
        // 儿童
        radio_children = (RadioButton) findViewById(R.id.radio_children);
        // 残疾人
        radio_disabled = (RadioButton) findViewById(R.id.radio_disabled);
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
        return createPopupById(R.layout.dialog_patient_type);
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
                this.dismiss();
                if (listener != null) {
                    // mark==0则是非孕妇、儿童、残疾人
                    int mark = 0;
                    if (!radio_other.isChecked()) {
                        mark = 1;
                    }
                    listener.clickOk(mark);
                }
            }
            break;
        }
    }

    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    public interface ClickListener {
        void clickOk(int mark);
    }
}
