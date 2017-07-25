package com.ge.med.mobile.nursing.mySyncTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * Created by Administrator on 2016/11/8.
 */
public class ErWeiMaSync extends AsyncTask<String ,Void,Bitmap>{
    private  Context mContext;
    private ImageView mView;
    public ErWeiMaSync(Context context,ImageView mView) {
        mContext=context;
        this.mView=mView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return QRCodeEncoder.syncEncodeQRCode(params[0], BGAQRCodeUtil.dp2px(mContext, 150));
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            mView.setImageBitmap(bitmap);
        } else {
            Toast.makeText(mContext, "生成中文二维码失败", Toast.LENGTH_SHORT).show();
        }
    }
}
