package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.db.DBUserList;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.fragment.YZ_SX_SMFragment;
import com.mitac.lib.bcr.utils.BARCODE;

import org.litepal.crud.DataSupport;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class SaoMiaoActivitya extends MyBaseActivity implements QRCodeView.Delegate {
    private String username;

    //**********************************************************************************************
    private QRCodeView mQRCodeView;

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
        mQRCodeView.startSpot();
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    //震动器
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(100);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        //Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        // vibrate();//震动
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

        vibrate();
        if (username.equals(result)) {
            showToastShort("不能是自己的二维码！");
        } else {
            DBUserList first = DataSupport.where("empno = ?", result).findFirst(DBUserList.class);
            if(first!=null){
                showToastShort(first.getName()+"确认成功！");
                Intent intent = getIntent();
                intent.setClass(mActivitySelf, YZ_SX_SMFragment.class);
                setResult(0, intent);
                killSelf();
            }else{
                showToastShort("未找到该用户！");
            }

        }
        mQRCodeView.startSpot();
    }
    //**********************************************************************************************
    @Override
    public int setRootView() {
        return R.layout.activity_sao_miao_activitya;
    }

    @Override
    public void initViews() {
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        username = sharePLogin.getUsername();
        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);


    }

    /**
     * 处理打开相机出错
     */
    @Override
    public void onScanQRCodeOpenCameraError() {
        System.out.println("SaoMiaoActivitya.onScanQRCodeOpenCameraError");
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }


}
