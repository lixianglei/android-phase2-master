package com.ge.med.mobile.nursing.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.ge.med.mobile.nursing.db.DBUser;
import com.ge.med.mobile.nursing.forjson.callback.XGMM_CallBack;
import com.ge.med.mobile.nursing.service.TongZhiService;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.utils.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

import okhttp3.Call;
//修改密码
public class XGMMActivity extends MyBaseActivity {
    private RelativeLayout mRlTitleBj;
    private ImageView mMyTvTitleLeft;
    private TextView mMyTvTitleCenter;
    private ImageView mImgvTitleRight;
    private TextView mMyTvTitleRight;
    private EditText mXgmmYmmEdt;

    private EditText mXgmm2YmmEdt1;
    private EditText mXgmm2YmmEdt2;
    private Button mXgmm2YmmBt;
    private SharePLogin sharePLogin;
    private XGMM_CallBack xgmm_callBack;
    @Override
    public int setRootView() {
        return R.layout.activity_xgmm;
    }

    @Override
    public void initViews() {
        sharePLogin = new SharePLogin(mActivitySelf);
        tintManager.setTintColor(Color.parseColor("#dddddd"));
        mRlTitleBj = (RelativeLayout) findViewById(R.id.rl_title_bj);
        mMyTvTitleLeft = (ImageView) findViewById(R.id.my_tv_title_left);
        mMyTvTitleCenter = (TextView) findViewById(R.id.my_tv_title_center);
        mImgvTitleRight = (ImageView) findViewById(R.id.imgv_title_right);
        mMyTvTitleRight = (TextView) findViewById(R.id.my_tv_title_right);
        mXgmmYmmEdt = (EditText) findViewById(R.id.xgmm_ymm_edt);
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
        SharedPreferences sharedPreferences = mActivitySelf.getSharedPreferences("user", Context.MODE_PRIVATE);
        final String username = sharedPreferences.getString("username", "");
        mXgmm2YmmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pwd=mXgmmYmmEdt.getText().toString();
                if(pwd==null){
                    showToastShort("密码不能为空");
                    return;
                }
                if("".equals(pwd)||pwd.length()==0){
                    showToastShort("密码不能为空");
                    return;
                }
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
                String MD5pwd = MD5Util.md5(pwd);
                String MD5pwd1 = MD5Util.md5(pwd1);
                List<DBUser> dbUserList = DataSupport.where("user_id = ?", username).find(DBUser.class);
                //修改密码的网络访问
                OkHttpUtils.post()
                        .url(URL.XIU_GAI_MI_MA)
                        .addHeader("User-Agent", "www.gs.com")
                        .addParams("password", MD5pwd)
                        .addParams("newPwd", MD5pwd1)
                        .addParams("id", dbUserList.get(0).getZid()+"")
                        .build()
                        .execute(xgmm_callBack);
            }
        });
        //修改密码的网络访问回调
         xgmm_callBack=new XGMM_CallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                showToastShort("修改失败");
            }
            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
                if(loginBean.getData()!=null && loginBean.getData().equals("true")){
                    showToastShort("修改成功,请重新登录!");
                    goToActivity(LoginActivity.class);
                    ActivityControl.killActivity(SheZhiActivity.class);
                    Intent intent = new Intent(mActivitySelf, TongZhiService.class);
                    stopService(intent);
                    ActivityControl.killActivity(MainActivity.class);
                    killSelf();
                    showToastShort("22222222");
                }else{
                    showToastShort("修改失败,原密码输入错误!");
                }
            }
        };


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
