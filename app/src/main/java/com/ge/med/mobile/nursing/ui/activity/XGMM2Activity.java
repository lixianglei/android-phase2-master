package com.ge.med.mobile.nursing.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
//修改密码
public class XGMM2Activity extends MyBaseActivity {
    private RelativeLayout mRlTitleBj;
    private ImageView mMyTvTitleLeft;
    private TextView mMyTvTitleCenter;
    private ImageView mImgvTitleRight;
    private TextView mMyTvTitleRight;
    private EditText mXgmm2YmmEdt1;
    private EditText mXgmm2YmmEdt2;
    private Button mXgmm2YmmBt;



    @Override
    public int setRootView() {
        return R.layout.activity_xgmm2;
    }

    @Override
    public void initViews() {
        tintManager.setTintColor(Color.parseColor("#dddddd"));
        mRlTitleBj = (RelativeLayout) findViewById(R.id.rl_title_bj);
        mMyTvTitleLeft = (ImageView) findViewById(R.id.my_tv_title_left);
        mMyTvTitleCenter = (TextView) findViewById(R.id.my_tv_title_center);
        mImgvTitleRight = (ImageView) findViewById(R.id.imgv_title_right);
        mMyTvTitleRight = (TextView) findViewById(R.id.my_tv_title_right);
        mXgmm2YmmEdt1 = (EditText) findViewById(R.id.xgmm2_ymm_edt1);
        mXgmm2YmmEdt2 = (EditText) findViewById(R.id.xgmm2_ymm_edt2);
        mXgmm2YmmBt = (Button) findViewById(R.id.xgmm2_ymm_bt);
        mMyTvTitleCenter.setText("密码修改");
        mImgvTitleRight.setVisibility(View.GONE);
        mMyTvTitleRight.setVisibility(View.GONE);
        mMyTvTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killSelf();
            }
        });
        mXgmm2YmmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd1=mXgmm2YmmEdt1.getText().toString();
                String pwd2=mXgmm2YmmEdt2.getText().toString();
                if(pwd1==null||pwd2==null){
                    showToastShort("密码不能为空");
                    return;
                }
                if("".equals(pwd1)||pwd1.length()==0||"".equals(pwd2)||pwd2.length()==0){
                    showToastShort("密码不能为空");
                    return;
                }
                if(!pwd1.equals(pwd2)){
                    showToastShort("两次输入密码不一致");
                    return;
                }
                showToastShort("修改密码");
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
