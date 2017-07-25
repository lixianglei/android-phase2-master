package com.ge.med.mobile.nursing.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sj.library.control.ActivityControl;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.mitac.lib.bcr.utils.BARCODE;

public class IPSheZhiActivity extends MyBaseActivity {
    private RelativeLayout mRlTitleBj;
    private ImageView mMyTvTitleLeft;
    private TextView mMyTvTitleCenter;
    private ImageView mImgvTitleRight;
    private TextView mMyTvTitleRight;
    private EditText mIpTv;
    private EditText mDuankouhaoTv;
    private Button mQuerenBt;

    private String ip_string;
    private String port;
    private SharePLogin sharePLogin;

    @Override
    public int setRootView() {
        return R.layout.activity_ipshe_zhi;
    }

    @Override
    public void initViews() {
        mRlTitleBj = (RelativeLayout) findViewById(R.id.rl_title_bj);
        mMyTvTitleLeft = (ImageView) findViewById(R.id.my_tv_title_left);
        mMyTvTitleCenter = (TextView) findViewById(R.id.my_tv_title_center);
        mImgvTitleRight = (ImageView) findViewById(R.id.imgv_title_right);
        mMyTvTitleRight = (TextView) findViewById(R.id.my_tv_title_right);
        mIpTv = (EditText) findViewById(R.id.ip_tv);
        mDuankouhaoTv = (EditText) findViewById(R.id.duankouhao_tv);
        mQuerenBt = (Button) findViewById(R.id.queren_bt);


        sharePLogin =new SharePLogin(mActivitySelf);
        ip_string = sharePLogin.getIP();
        port = sharePLogin.getPort();
        mIpTv.setText(ip_string);
        mDuankouhaoTv.setText(port);
        tintManager.setTintColor(Color.parseColor("#dddddd"));
        mMyTvTitleCenter.setText("配置");
        mImgvTitleRight.setVisibility(View.GONE);
        mMyTvTitleRight.setVisibility(View.GONE);
        mMyTvTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killSelf();
            }
        });
        mQuerenBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip_string = mIpTv.getText().toString();
                port = mDuankouhaoTv.getText().toString();
                boolean matches = ip_string.matches("((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))");
                if(!matches){
                    showToastShort("ip地址格式不正确！请重新输入");
                    return;
                }
                URL.resetURL(ip_string,port);
                sharePLogin.saveIP(ip_string);
                sharePLogin.savePort(port);
                if(sharePLogin.getFirst()){
                    sharePLogin.saveFirst(false);
                }
                ActivityControl.killActivity(LoginActivity.class);
                goToActivity(LoginActivity.class);
                killSelf();
            }
        });
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
