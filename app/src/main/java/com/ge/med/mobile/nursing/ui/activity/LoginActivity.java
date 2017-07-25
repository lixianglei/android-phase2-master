package com.ge.med.mobile.nursing.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.library.control.ActivityControl;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.db.DBAssess;
import com.ge.med.mobile.nursing.db.DBAssessMeasureRecords;
import com.ge.med.mobile.nursing.db.DBHisOrder;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.db.DBUser;
import com.ge.med.mobile.nursing.db.DBVitalSignSheet;
import com.ge.med.mobile.nursing.db.DBXuanJiaoRecord;
import com.ge.med.mobile.nursing.forjson.callback.AppUpdateCallback;
import com.ge.med.mobile.nursing.forjson.callback.LoginCallBack;
import com.ge.med.mobile.nursing.service.MyService;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.KsdlLvItemAdapter;
import com.ge.med.mobile.nursing.ui.view.JiaZaiDialog;
import com.ge.med.mobile.nursing.ui.view.dialog.AppUpdateDialog;
import com.ge.med.mobile.nursing.utils.DownloadUtil;
import com.ge.med.mobile.nursing.utils.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.util.List;

import okhttp3.Call;

import static com.zhy.http.okhttp.OkHttpUtils.post;

public class LoginActivity extends MyBaseActivity {
    // 获取权限
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    // 改变更新提示对话框的状态
    private static final int DIALOG_CHANGE_STATUS = 1;
    // 对话框消失
    private static final int DIALOG_DISMISS = 2;

    public static final Boolean b = true;
    private EditText mLoginUsernameEdt;
    private EditText mLoginUserpwdEdt;
    private Button mLonginBt;
    private ImageView mIpShezhi;
    private ImageView mLoginUsernameBt;
    private SharePLogin sharePLogin;
    private Bundle mBundle;
    private String username;
    private ListView mKsdlLv;
    private KsdlLvItemAdapter ksdlLvItemAdapter;
    private List<DBUser> dbUserLis;
    public JiaZaiDialog jiaZaiDialog;
    private TextView tv_version;

    // 下载路径
    private String downloadPath;
    // 更新对话框
    private AppUpdateDialog dialog;

    @Override
    public boolean useTitleBar() {
        return false;
    }

    @Override
    public int setRootView() {
        return R.layout.activity_login;
    }


    @Override
    public void initViews() {
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        int permission = ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission2 = ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED || permission2 != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    LoginActivity.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        } else {
            createDirectory();
        }
        dbUserLis = DataSupport.findAll(DBUser.class);
        tintManager.setTintColor(Color.parseColor("#FFE1E1E1"));
        mKsdlLv = (ListView) findViewById(R.id.ksdl_lv);
        mLoginUsernameEdt = (EditText) findViewById(R.id.login_username_edt);
        mLoginUserpwdEdt = (EditText) findViewById(R.id.login_userpwd_edt);
        mLonginBt = (Button) findViewById(R.id.longin_bt);
        mLoginUsernameBt = (ImageView) findViewById(R.id.login_username_bt);
        mIpShezhi = (ImageView) findViewById(R.id.ip_shezhi);
        tv_version = (TextView) findViewById(R.id.tv_version);
        dialog = new AppUpdateDialog(LoginActivity.this);

        Intent intent = getIntent();
        sharePLogin = new SharePLogin(mActivitySelf);
        if (sharePLogin.getFirst()) {
            goToActivity(IPSheZhiActivity.class);
            killSelf();
        }
        String ip = sharePLogin.getIP();
        String port = sharePLogin.getPort();
        URL.resetURL(ip, port);
        //showToastShort(URL.URL);
        //showToastShort("URL.URL_IP=" + URL.URL_IP + "   URL.URL_PORT=" + URL.URL_PORT);
        mIpShezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(IPSheZhiActivity.class);
            }
        });


        // 当前的版本号
        final String version = getVersion();
        // 更改界面上的显示
        tv_version.setText("v" + version);

        // 获取最新的版本号
        OkHttpUtils
                .post()
                .addHeader("User-Agent", "www.gs.com")
                .url(URL.URL + "/appversion/getappmaxversion")
                .build()
                .execute(new AppUpdateCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        super.onResponse(response, id);
                        // 服务器中的最新版本
                        String serverVersion = appUpdateBean.getData().getVersion();
                        // 获取文件路径
                        final String fileNme = appUpdateBean.getData().getFilePath();
                        if (serverVersion != null && version != null) {
                            // 版本号不同则提示是否进行下载
                            if (!serverVersion.equals(version)) {
                                dialog.showPopupWindow();
                                dialog.setListener(new AppUpdateDialog.ClickListener() {
                                    @Override
                                    public void clickOk() {
                                        // 点击确定则开始下载
                                        DownloadUtil.get().download(URL.URL + "/appversion/getApp?fileName=" + fileNme, Constant.PATH_APK, new DownloadUtil.OnDownloadListener() {
                                            @Override
                                            public void onDownloadSuccess() {
                                                handler.sendEmptyMessage(DIALOG_DISMISS);
                                                // 开始安装
                                                downloadPath = Constant.PATH_APK + "ydhl.apk";
                                                installAPK(new File(downloadPath));
                                                Looper.prepare();
                                                Toast.makeText(LoginActivity.this, "下载完成", Toast.LENGTH_LONG).show();
                                                Looper.loop();

                                            }
                                            @Override
                                            public void onDownloading() {
                                                handler.sendEmptyMessage(DIALOG_CHANGE_STATUS);

                                            }

                                            @Override
                                            public void onDownloadFailed() {
                                                handler.sendEmptyMessage(DIALOG_DISMISS);
                                                Looper.prepare();
                                                Toast.makeText(LoginActivity.this, "下载失败", Toast.LENGTH_LONG).show();
                                                Looper.loop();
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    }
                });


//*********获取传过来的用户名
        Bundle data = intent.getBundleExtra("data");
        if (data != null) {
            String username = data.getString("username", "");
            mLoginUsernameEdt.setText(username);
//            mLoginUserpwdEdt.setFocusable(true);
//            mLoginUserpwdEdt.setFocusableInTouchMode(true);
            mLoginUserpwdEdt.requestFocus();
            mLoginUserpwdEdt.requestFocusFromTouch();
        }
        //快速登录按钮监听
        mLoginUsernameBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mKsdlLv.getVisibility() == View.GONE) {
                    mKsdlLv.setVisibility(View.VISIBLE);
                    mLoginUsernameBt.setImageResource(R.mipmap.ksdl1);
                    if (dbUserLis != null && dbUserLis.size() > 0) {
                        mLonginBt.setVisibility(View.INVISIBLE);
                    }
                } else {
                    mKsdlLv.setVisibility(View.GONE);
                    mLonginBt.setVisibility(View.VISIBLE);
                    mLoginUsernameBt.setImageResource(R.mipmap.ksdl);
                }
            }
        });
        //帐号输入框监听
        mLoginUsernameEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mKsdlLv.setVisibility(View.GONE);
                mLoginUsernameBt.setImageResource(R.mipmap.ksdl);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //登录按钮监听
        mLonginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLoginButton();
            }
        });


//快速登录
        ksdlLvItemAdapter = new KsdlLvItemAdapter(this, dbUserLis);
        mKsdlLv.setAdapter(ksdlLvItemAdapter);
        mKsdlLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mLoginUsernameEdt.setText(dbUserLis.get(position).getUser_id());
                mKsdlLv.setVisibility(View.GONE);
                mLonginBt.setVisibility(View.VISIBLE);
                mLoginUsernameBt.setImageResource(R.mipmap.ksdl);
                mLoginUsernameEdt.setSelection(mLoginUsernameEdt.length());
            }
        });
        //TODO   for test only! need to remove
//        mLoginUsernameEdt.setText("an089");
//        mLoginUserpwdEdt.setText("1");
//        clickLoginButton();

    }

    private void clickLoginButton() {
        //MyService这个类主要用来控制用户一段时间未操作就自动返回登录页面
        goToService(MyService.class);
        username = mLoginUsernameEdt.getText().toString().toLowerCase();
//                username = mLoginUsernameEdt.getText().toString();
        String userpwd = mLoginUserpwdEdt.getText().toString();
        if (username == null || userpwd == null) {
            return;
        }
        if ("".equals(username) || "".equals(userpwd)) {
            showToastShort("帐号或密码不能为空");
            return;
        }
        if (username.length() > 19) {
            showToastShort("账号长度不能超过19个字符");
            return;
        }
        mBundle = new Bundle();
        mBundle.putString("username", username);
        String MD5pwd = MD5Util.md5(userpwd);
        post()
                .addHeader("User-Agent", "www.gs.com")
                .url(URL.LOG_IN)
                .addParams("empno", username)
                .addParams("password", MD5pwd)
                .build()
                .execute(new LoginCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showToastShort("无法连接到服务器！");
                        logE("Login failed, error message is :" + e.getMessage());
                        mLonginBt.setEnabled(true);
                        jiaZaiDialog.cancel();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        super.onResponse(response, id);

                        if (loginBean.getData() == null) {
                            showToastShort("服务器数据异常！");
                        } else {
                            if (loginBean.getData().getRemindInfo().equals("1")) {
                                sharePLogin.saveToken(loginBean.getData().getToken());
                                showToastShort("登录成功");
                                DataSupport.deleteAll(DBHuanZheLieBiao.class);
                                DataSupport.deleteAll(DBXuanJiaoRecord.class);
                                DataSupport.deleteAll(DBAssessMeasureRecords.class);
                                DataSupport.deleteAll(DBHisOrder.class);
                                DataSupport.deleteAll(DBVitalSignSheet.class);
                                DataSupport.deleteAll(DBAssess.class);
                                goToActivity(MainActivity.class, mBundle, 0);
                                killSelf();
                            }
                            if (loginBean.getData().getRemindInfo().equals("2")) {
                                showToastShort("当前终端已登录其他账号！");
                            }
                            if (loginBean.getData().getRemindInfo().equals("3")) {
                                showToastShort("用户名或密码错误 ！");
                            }
                            if (loginBean.getData().getRemindInfo().equals("4")) {
                                showToastShort("用户处于禁用状态,请联系管理员!");
                            }
                            if (loginBean.getData().getRemindInfo().equals("9")) {
                                showToastShort("没有权限登录系统!");
                            }
                        }

                        mLonginBt.setEnabled(true);
                        jiaZaiDialog.cancel();
                    }
                });
        jiaZaiDialog = new JiaZaiDialog(mActivitySelf);
        jiaZaiDialog.setCanceledOnTouchOutside(false);
//        jiaZaiDialog.setCancelable(false);
        jiaZaiDialog.show();
        mLonginBt.setEnabled(false);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MyBaseActivity.IsLogin = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyBaseActivity.IsLogin = true;
    }

    @Override
    public void initDatas() {
    }

    @Override
    public void init() {
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    //Back 键监听
    @Override
    public void onBackPressed() {
        ActivityControl.killAll();
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "get version error";
        }
    }

    //下载到本地后执行安装
    protected void installAPK(File file) {
        if (!file.exists()) return;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("file://" + file.toString());
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        //在服务中开启activity必须设置flag,后面解释
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    /**
     * 创建文件夹
     */
    private void createDirectory() {
        File file = new File(Constant.PATH_APK);
        if (!file.exists()) {
            boolean result = file.mkdirs();
        }
        File apk = new File(Constant.PATH_APK + "ydhl.apk");
        if (apk.exists()) {
            apk.delete();
        }
    }

    /**
     * 权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                createDirectory();
            } else {
                // Permission Denied
                showToastLong("权限被拒绝");
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * handler
     */
    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DIALOG_CHANGE_STATUS: {
                    // 开始下载，显示进度条
                    dialog.changeStatusToDownloading();
                }
                break;
                case DIALOG_DISMISS: {
                    // 对话框消失
                    dialog.dismiss();
                }
                break;
            }
        }
    };

}
