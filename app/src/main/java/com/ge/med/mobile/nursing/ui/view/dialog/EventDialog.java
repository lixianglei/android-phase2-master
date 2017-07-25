package com.ge.med.mobile.nursing.ui.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.forjson.entity.EventDefineBean;

import java.util.List;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by lisa on 2017/7/4.
 * 事件名称选择
 */

public class EventDialog extends BasePopupWindow implements View.OnClickListener {
    private Context context;
    private ClickListener listener;
    private List<EventDefineBean.DataBean> datas;
    private RadioButton[] radios;

    public EventDialog(Activity context, List<EventDefineBean.DataBean> datas) {
        super(context);
        this.context = context;
        RelativeLayout rl_cancel = (RelativeLayout) findViewById(R.id.rl_cancel);
        RelativeLayout rl_ok = (RelativeLayout) findViewById(R.id.rl_ok);
        // 单选按钮组
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        this.datas = datas;
        radios = new RadioButton[datas.size()];
        for (int i = 0; i < datas.size(); i++) {
            RadioButton radio = new RadioButton(context);
            radio.setText(datas.get(i).getEventname());
            radios[i] = radio;
            radioGroup.addView(radios[i]);
        }

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
        return createPopupById(R.layout.dialog_event_name);
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
                    // 找出选中的项
                    EventDefineBean.DataBean selectItem = null;
                    for (int i = 0; i < radios.length; i++) {
                        if (radios[i].isChecked()) {
                            selectItem = datas.get(i);
                        }
                    }
                    if (selectItem == null) {
                        Toast.makeText(context, "请选择事件名称", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    listener.clickOk(selectItem);
                }
            }
            break;
        }
    }

    /**
     * 设置被选中的项
     */
    public void setSelectIndex(int eventId) {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getId() == eventId) {
                radios[i].setChecked(true);
                break;
            }
        }
    }

    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    public interface ClickListener {
        void clickOk(EventDefineBean.DataBean selectItem);
    }
}
