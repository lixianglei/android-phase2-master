package com.ge.med.mobile.nursing.ui.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.ge.med.mobile.nursing.R;

/**
 * Created by Administrator on 2016/11/17.
 */
public class JiaZaiDialog extends AlertDialog {


    public JiaZaiDialog(Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donghua_jiazaizhong_dialog);
    }
}
