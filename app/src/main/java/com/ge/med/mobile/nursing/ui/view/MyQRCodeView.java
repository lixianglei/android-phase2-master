package com.ge.med.mobile.nursing.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import cn.bingoogolapple.qrcode.core.QRCodeView;

/**
 * Created by Administrator on 2016/11/7.
 */
public class MyQRCodeView extends QRCodeView {


    public MyQRCodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public String processData(byte[] data, int width, int height) {
        return null;
    }
}
