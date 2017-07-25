package com.ge.med.mobile.nursing.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.sj.library.util.NetReceiver;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.base.Myapp;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.TongZhiBean;
import com.ge.med.mobile.nursing.dao.entity.TongZhiSocket;
import com.ge.med.mobile.nursing.dao.entity.User;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBAssess;
import com.ge.med.mobile.nursing.db.DBAssessDefine;
import com.ge.med.mobile.nursing.db.DBAssessMeasureRecords;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.db.DBMeasureDefine;
import com.ge.med.mobile.nursing.db.DBVitalSignSheet;
import com.ge.med.mobile.nursing.db.DBVitalSignWardDefine;
import com.ge.med.mobile.nursing.db.DBXuanJiao;
import com.ge.med.mobile.nursing.db.DBXuanJiaoRecord;
import com.ge.med.mobile.nursing.forjson.NetworkForSynchronize;
import com.ge.med.mobile.nursing.forjson.callback.MainQuanBuYZ_CallBack;
import com.ge.med.mobile.nursing.forjson.callback.MainUserCallBack;
import com.ge.med.mobile.nursing.forjson.entity.NetworkForImage;
import com.ge.med.mobile.nursing.forjson.entity.WardRiskDefine;
import com.ge.med.mobile.nursing.forjson.syncDifine.CacheDefine;
import com.ge.med.mobile.nursing.mySyncTask.ErWeiMaSync;
import com.ge.med.mobile.nursing.scancode.MyPrefs;
import com.ge.med.mobile.nursing.service.SyncService;
import com.ge.med.mobile.nursing.service.TongZhiService;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.data.RiskDefine;
import com.ge.med.mobile.nursing.ui.fragment.RWFragment;
import com.ge.med.mobile.nursing.ui.fragment.SYFragment;
import com.ge.med.mobile.nursing.ui.fragment.TZFragment;
import com.ge.med.mobile.nursing.ui.fragment.YZFragment;
import com.ge.med.mobile.nursing.ui.view.JiaZaiDialog;
import com.ge.med.mobile.nursing.ui.view.SelfDialog;
import com.ge.med.mobile.nursing.utils.MessageEvent;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import okhttp3.Call;

/**
 * 主界面
 */
public class MainActivity extends MyBaseActivity implements View.OnClickListener {
    private LinearLayout mShaiXuanIncludeLl;
    private TextView mShaiXuanIncludeKongbaiTv;
    private ImageView mShaiXuanIncludeErweimaImgv;
    public TextView mShaiXuanIncludeAllTv;
    public TextView mShaiXuanIncludeMyTv;
    public TextView mShaiXuanIncludeOneTv;
    public TextView mShaiXuanIncludeTwoTv;
    public TextView mShaiXuanIncludeThreeTv;
    public TextView mShaiXuanIncludeTeTv;
    private LinearLayout mShaiXuanIncludeChuangTv;
    private TextView mShaiXuanIncludeShezhiTv;
    private TextView mShaiXuanIncludeTuiTv;
    private TextView mShaiXuanIncludeUsernameTv;
    public TextView mShaiXuanIncludeUseridTv;
    private ImageView mShaiXuanIncludeChuangImgv;
    private TextView mShaiXuanIncludeBaiseTv;
    public Bundle mBundle;
    private SelfDialog selfDialog;
    private boolean isSelfDialogShow = false;
    private FrameLayout mMain3Vp;
    private RWFragment rwFragment = new RWFragment();
    private SYFragment syFragment = new SYFragment();
    private TZFragment tzFragment = new TZFragment();
    private long mLastDT;
    private TextView mMain3LlHzBt;
    private TextView mMain3LlRwBt;
    private TextView mMain3LlTzBt;
    private ErWeiMaSync erWeiMaSync;
    private YiZhuBean mYiZhuBean;
    private TongZhiSocket tongZhiSocket;
    List<TongZhiBean.DataBean> dataTongZhiBean;

    private boolean mChuang = true;
    public String mLeiXing = "全部患者";
    public static Map<String, String> userCache = new HashMap<>();

    private User user;
    private User.DataBean data;
    private HuanZheLieBiaoImpl huanZheLieBiao = new HuanZheLieBiaoImpl();
    private List<DBHuanZheLieBiao> dbHuanZheLieBiaoList;
    private SharedPreferences.Editor editor;
    private TitleBar mTitleBar;
    private TextView mShaiXuanIncludeUserLevelTv;
    private LinearLayout mShaiXuanIncludeAllTvLl;
    private LinearLayout mShaiXuanIncludeMyTvLl;
    private LinearLayout mShaiXuanIncludeOneTvLl;
    private LinearLayout mShaiXuanIncludeTwoTvLl;
    private LinearLayout mShaiXuanIncludeThreeTvLl;
    private LinearLayout mShaiXuanIncludeTeTvLl;
    private SharePLogin sharePLogin;


    @Override
    public int setRootView() {
        return R.layout.activity_main3;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initViews() {
        isSelfDialogShow = false;
        userCache.clear();
        EventBus.getDefault().register(this);
        if (HZActivity.lastCallNetTime != null) {
            HZActivity.lastCallNetTime.clear();
            YZFragment.lastCallNetTime.clear();
        }
        if (YZFragment.mRefreshDate != null) YZFragment.mRefreshDate.clear();
        clearLocalDB();
        Intent intent = getIntent();
        mBundle = intent.getBundleExtra(Constant.GLOBAL_KEY_DATA);
        goToService(SyncService.class);
        String useraccount = mBundle.getString(Constant.GLOBAL_KEY_USER_ACCOUND);
        sharePLogin = new SharePLogin(mActivitySelf);
        sharePLogin.saveUsername(useraccount);
        Myapp.vibrate = sharePLogin.getVibrate();
        Myapp.playSound = sharePLogin.getPlaySound();
        final SharedPreferences sharedPreferences = mActivitySelf.getSharedPreferences(Constant.GLOBAL_FILENAME_USER, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //******** include 筛选控件
        mShaiXuanIncludeBaiseTv = (TextView) findViewById(R.id.shai_xuan_include_baise_tv);
            mShaiXuanIncludeLl = (LinearLayout) findViewById(R.id.shai_xuan_include_ll);
        mShaiXuanIncludeUserLevelTv = (TextView) findViewById(R.id.shai_xuan_include_user_level_tv);
        mShaiXuanIncludeKongbaiTv = (TextView) findViewById(R.id.shai_xuan_include_kongbai_tv);
        mShaiXuanIncludeErweimaImgv = (ImageView) findViewById(R.id.shai_xuan_include_erweima_imgv);
        mShaiXuanIncludeUsernameTv = (TextView) findViewById(R.id.shai_xuan_include_username_tv);
        mShaiXuanIncludeUseridTv = (TextView) findViewById(R.id.shai_xuan_include_userid_tv);


        mShaiXuanIncludeAllTv = (TextView) findViewById(R.id.shai_xuan_include_all_tv);
        mShaiXuanIncludeMyTv = (TextView) findViewById(R.id.shai_xuan_include_my_tv);
        mShaiXuanIncludeOneTv = (TextView) findViewById(R.id.shai_xuan_include_one_tv);
        mShaiXuanIncludeTwoTv = (TextView) findViewById(R.id.shai_xuan_include_two_tv);
        mShaiXuanIncludeThreeTv = (TextView) findViewById(R.id.shai_xuan_include_three_tv);
        mShaiXuanIncludeTeTv = (TextView) findViewById(R.id.shai_xuan_include_te_tv);
        mShaiXuanIncludeChuangTv = (LinearLayout) findViewById(R.id.shai_xuan_include_chuang_tv);
        mShaiXuanIncludeChuangImgv = (ImageView) findViewById(R.id.shai_xuan_include_chuang_imgv);
        mShaiXuanIncludeShezhiTv = (TextView) findViewById(R.id.shai_xuan_include_shezhi_tv);
        mShaiXuanIncludeTuiTv = (TextView) findViewById(R.id.shai_xuan_include_tui_tv);

        mShaiXuanIncludeAllTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_all_tv_ll);
        mShaiXuanIncludeMyTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_my_tv_ll);
        mShaiXuanIncludeOneTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_one_tv_ll);
        mShaiXuanIncludeTwoTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_two_tv_ll);
        mShaiXuanIncludeThreeTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_three_tv_ll);
        mShaiXuanIncludeTeTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_te_tv_ll);

        mShaiXuanIncludeLl.setVisibility(View.GONE);
        mShaiXuanIncludeLl.setOnClickListener(this);
        mShaiXuanIncludeBaiseTv.setOnClickListener(this);
        mShaiXuanIncludeKongbaiTv.setOnClickListener(this);
        mShaiXuanIncludeErweimaImgv.setOnClickListener(this);
        mShaiXuanIncludeAllTvLl.setOnClickListener(this);
        mShaiXuanIncludeMyTvLl.setOnClickListener(this);
        mShaiXuanIncludeOneTvLl.setOnClickListener(this);
        mShaiXuanIncludeTwoTvLl.setOnClickListener(this);
        mShaiXuanIncludeThreeTvLl.setOnClickListener(this);
        mShaiXuanIncludeTeTvLl.setOnClickListener(this);
        mShaiXuanIncludeChuangTv.setOnClickListener(this);
        mShaiXuanIncludeShezhiTv.setOnClickListener(this);
        mShaiXuanIncludeTuiTv.setOnClickListener(this);
        mTitleBar = new TitleBar(this);
        mTitleBar.setBackVisible(false);
        mTitleBar.setShaiXuanVisible(true);
        mTitleBar.setShaiXuanBehavior(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShaiXuanIncludeLl.setVisibility(View.VISIBLE);
            }
        });
        //**********************************************｝
        mMain3LlHzBt = (TextView) findViewById(R.id.main3_ll_hz_bt);
        mMain3LlRwBt = (TextView) findViewById(R.id.main3_ll_rw_bt);
        mMain3LlTzBt = (TextView) findViewById(R.id.main3_ll_tz_bt);
        mMain3LlHzBt.setOnClickListener(this);
        mMain3LlRwBt.setOnClickListener(this);
        mMain3LlTzBt.setOnClickListener(this);
        mMain3Vp = (FrameLayout) findViewById(R.id.main3_vp);

        /**
         * 获取全部医嘱回调
         */
        final MainQuanBuYZ_CallBack mainQuanBuYZ_callBack = new MainQuanBuYZ_CallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
                mYiZhuBean = yiZhuBean;
            }
        };

/**
 * 获取用户信息回调
 */
        MainUserCallBack mainUserCallBack = new MainUserCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtil.e("Can not got user info from server!exception is:" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                super.onResponse(response, id);
                LogUtil.d("Get user from API success:" + response);
                showToastShort("病区访问成功");
                MainActivity.this.user = user;
                MainActivity.this.data = user.getData();
                if (data == null) {
                    showToastShort("服务器获取数据异常！请重新登录");
                    goToActivity(LoginActivity.class);
                    killSelf();
                }
                syFragment.setSYNetForJSON(data.getWard_id(), data.getId());
                if (sharePLogin != null && sharePLogin.getToken() != null) {
                    tzFragment.setSYNetForJSON(data.getId() + "", sharePLogin.getToken());
                }
                mBundle.putString(Constant.GLOBAL_KEY_USER_ID, data.getId() + "");
                mBundle.putString(Constant.GLOBAL_KEY_TOKEN, sharePLogin.getToken() + "");
                goToService(TongZhiService.class, mBundle);
                String name = data.getName();
                erWeiMaSync = new ErWeiMaSync(mActivitySelf, mShaiXuanIncludeErweimaImgv);
                erWeiMaSync.execute(data.getEmp_no());
                userCache.put("userid", data.getId() + "");
                userCache.put("username", data.getName());
                userCache.put("usertype", data.getRole_type());
                editor.putString(Constant.GLOBAL_KEY_WARD_ID, data.getWard_id() + "");
                editor.putString(Constant.GLOBAL_KEY_WARD_NAME, data.getWard_name());
                editor.putString(Constant.GLOBAL_KEY_USER_ID, data.getId() + "");
                editor.putString(Constant.GLOBAL_KEY_USER_NAME, data.getName());
                editor.commit();
                if (name != null) {
                    mTitleBar.setShaiXuanText(name);
                    mShaiXuanIncludeUsernameTv.setText(name);
                }

                if (data.getNursing_level() != null && data.getWork_year() != null) {
                    sharePLogin.saveUserLevel(data.getWork_year() + " " + data.getNursing_level());
                    mShaiXuanIncludeUserLevelTv.setText(data.getWork_year() + " " + data.getNursing_level());
                } else {
                    sharePLogin.saveUserLevel("");
                }
                String ward_name = data.getWard_name();
                if (ward_name != null) {
                    mTitleBar.setTitleText(ward_name);
                }
                String emp_no = data.getEmp_no();
                if (emp_no != null) {
                    mShaiXuanIncludeUseridTv.setText(emp_no);
                }
                cacheDefine();
                new Thread(){
                    @Override
                    public void run() {
                        //获取服务器数据；
                        //NetworkForDoctorOrder.callFindDoctorOrderByADay((MyBaseActivity) mActivitySelf, data.getWard_id()); //当天数据
                        //NetworkForAssessment.callAssessHistoryByWard((MyBaseActivity) mActivitySelf, data.getWard_id());
                        //NetworkForVitalSign.callVitalSignHistoryByWard((MyBaseActivity) mActivitySelf, data.getWard_id());//当天数据
                    }
                }.start();
            }
        };
        //获取用户信息的网络访问
        OkHttpUtils.get()
                .url(URL.LOG_IN_GET_USER)
                .addHeader("User-Agent", "www.gs.com")
                .addParams("empno", useraccount)
                .build()
                .execute(mainUserCallBack);

        addFrag(R.id.main3_vp, syFragment);
        addFrag(R.id.main3_vp, rwFragment);
        addFrag(R.id.main3_vp, tzFragment);
        hideFrag(rwFragment);
        hideFrag(tzFragment);
        showFrag(syFragment);

        uiHnadler.post(new Runnable() {
            public void run() {
                registerReceiver(mReceiver, new IntentFilter(MyPrefs.HONEYWELL_ACTION_SCAN_RESULT));//霍尼韦尔
                registerReceiver(mReceiver, new IntentFilter(MyPrefs.EMH_ACTION));//易麦海
                registerReceiver(mReceiver, new IntentFilter(MyPrefs.XMG_ACTION));//小码哥
            }
        });
        // 重新定义缓存
        saveCacheDefine();
        cacheDefine();
    }

    private void clearLocalDB() {
        //new AssessDaoImpl().clearAllRecord();
        //new DoctorOrderDaoImpl().clearAll();
        DataSupport.deleteAll(DBAssess.class);
        DataSupport.deleteAll(DBAssessDefine.class);
        DataSupport.deleteAll(DBMeasureDefine.class);
        DataSupport.deleteAll(DBAssessMeasureRecords.class);
        DataSupport.deleteAll(DBXuanJiao.class);
        DataSupport.deleteAll(DBXuanJiaoRecord.class);
        DataSupport.deleteAll(DBVitalSignWardDefine.class);
        DataSupport.deleteAll(DBVitalSignSheet.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(mReceiver);
        } catch (Exception e) {
            LogUtil.e(e.getMessage() + "");
        }
        Intent intent = new Intent(mActivitySelf, SyncService.class);
        stopService(intent);
        intent = new Intent(mActivitySelf, TongZhiService.class);
        stopService(intent);
//        tzFragment.handler.removeCallbacks(tzFragment.runnable);
        EventBus.getDefault().unregister(this);
    }

    //评估定义网络访问成功
    @Override
    public void handleSuccess(Object obj) {
        if (obj == null) {
            LogUtil.e("Can not handle anything since obj is null!");
            return;
        }
        pingGuDefine(obj);
    }

    private void pingGuDefine(Object obj) {
        if (obj instanceof List) {
            final List lst = (List) obj;
            if (lst.size() == 0) {
                LogUtil.i("Do nothing since list size is zero!");
                return;
            }
            if (lst.get(0) instanceof WardRiskDefine) {
                LogUtil.d("Got ward risk define data, try to load patient.");
                if (syFragment == null) {
                    LogUtil.e("Can not reset risk define data since syFragment is null!");
                } else if (syFragment.mSyHzlbLvItemAdapter == null) {
                    LogUtil.e("Can not reset risk define data since syFragment.mSyHzlbLvItemAdapter is null!");
                } else {
                    syFragment.mSyHzlbLvItemAdapter.setRiskDefines(RiskDefine.convertFromJsonBeans(lst));
                    //syFragment.mSyHzlbLvItemAdapter.notifyDataSetChanged();
                    LogUtil.d("Reset risk define data for syFragment.mSyHzlbLvItemAdapter.");
                }
            } else if (lst.get(0) instanceof AssessDefine.DataBean) {
                DataSupport.deleteAll(DBAssessDefine.class); //清空数据库
                NetworkForImage.deleteAllFiles(NetworkForImage.fileCache);//删除目录所在文件夹 重新添加文件
                LogUtil.d("Got assess define data, try to load all images.");
                new Thread() {
                    @Override
                    public void run() {
                        NetworkForImage.IImageBean image = null;
                        for (AssessDefine.DataBean define : (List<AssessDefine.DataBean>) lst) {
                            if (define.getAssessTopicDefineList() != null) {
                                for (AssessDefine.DataBean.AssessTopicDefineListBean topic : define.getAssessTopicDefineList()) {
                                    cacheImge(topic, this);
                                    if (topic.getClassifyType() == 0) {
                                        List<AssessDefine.DataBean.AssessTopicDefineListBean> relateTopicList = topic.getRelateTopicList();
                                        if (relateTopicList != null) {
                                            for (AssessDefine.DataBean.AssessTopicDefineListBean assessTopicDefineListBean : relateTopicList) {
                                                cacheImge(assessTopicDefineListBean, this);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        new AssessDaoImpl().saveAssessDefines((List<AssessDefine.DataBean>) lst);
                    }
                }.start();
                CacheDefine.changeStatus(Constant.GLOBAL_KEY_IS_CACHE_ASSESSDEFINE, false);
            }
        }
    }

    private void cacheImge(AssessDefine.DataBean.AssessTopicDefineListBean topic, Thread thread) {
        if (topic.getAssessAnswerDefineList() != null) {
            for (AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean answer : topic.getAssessAnswerDefineList()) {
                if (answer.getAndroidImage() != null) {
                    NetworkForImage.callGetImage(MainActivity.this, answer);
                    try {
                        thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LogUtil.d("load image string for answerdefine[id" + answer.getId() + ":,imageId:" + answer.getAndroidImage());
                }
            }
        }
        if (topic.getAppPicture() != null) {
            NetworkForImage.callGetImage(MainActivity.this, topic);
            try {
                thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LogUtil.d("load image string for topicdefine[id" + topic.getId() + ":,imageId:" + topic.getImageId());
        }
    }

    @Override
    public void initDatas() {

    }

    private Drawable drawable_hz1;
    private Drawable drawable_hz2;
    private Drawable drawable_rw1;
    private Drawable drawable_rw2;
    private Drawable drawable_tz1;
    private Drawable drawable_tz2;
    private Drawable drawable_ch1;
    private Drawable drawable_ch2;

    @Override
    public void init() {
        drawable_hz1 = getResources().getDrawable(R.mipmap.icon_patient_nor);
        drawable_hz2 = getResources().getDrawable(R.mipmap.icon_patient_pre);
        drawable_rw1 = getResources().getDrawable(R.mipmap.icon_task_nor);
        drawable_rw2 = getResources().getDrawable(R.mipmap.icon_task_pre);
        drawable_tz1 = getResources().getDrawable(R.mipmap.icon_notice_nor);
        drawable_tz2 = getResources().getDrawable(R.mipmap.icon_notice_pre);

        drawable_hz1.setBounds(0, 0, drawable_hz1.getMinimumWidth(), drawable_hz1.getMinimumHeight());
        drawable_hz2.setBounds(0, 0, drawable_hz1.getMinimumWidth(), drawable_hz1.getMinimumHeight());
        drawable_rw1.setBounds(0, 0, drawable_hz1.getMinimumWidth(), drawable_hz1.getMinimumHeight());
        drawable_rw2.setBounds(0, 0, drawable_hz1.getMinimumWidth(), drawable_hz1.getMinimumHeight());
        drawable_tz1.setBounds(0, 0, drawable_hz1.getMinimumWidth(), drawable_hz1.getMinimumHeight());
        drawable_tz2.setBounds(0, 0, drawable_hz1.getMinimumWidth(), drawable_hz1.getMinimumHeight());
    }

    @Override
    public boolean useTitleBar() {
        return false;
    }


    @Override
    public void onClick(View v) {
        mShaiXuanIncludeLl.setVisibility(View.GONE);
        switch (v.getId()) {
            //点到了患者 按钮
            case R.id.main3_ll_hz_bt:
                mMain3LlHzBt.setTextColor(Color.parseColor("#559bec"));
                mMain3LlRwBt.setTextColor(Color.parseColor("#5e5e5e"));
                mMain3LlTzBt.setTextColor(Color.parseColor("#5e5e5e"));
                mTitleBar.setBackVisible(false);
                mTitleBar.setShaiXuanVisible(true);
                if (data != null) {
                    mTitleBar.setTitleText(data.getWard_name());
                }
                showFrag(syFragment);
                hideFrag(rwFragment);
                hideFrag(tzFragment);
                mMain3LlHzBt.setCompoundDrawables(null, drawable_hz2, null, null);
                mMain3LlRwBt.setCompoundDrawables(null, drawable_rw1, null, null);
                mMain3LlTzBt.setCompoundDrawables(null, drawable_tz1, null, null);

                break;
            //点到了任务 按钮
            case R.id.main3_ll_rw_bt:
                mMain3LlHzBt.setTextColor(Color.parseColor("#5e5e5e"));
                mMain3LlRwBt.setTextColor(Color.parseColor("#559bec"));
                mMain3LlTzBt.setTextColor(Color.parseColor("#5e5e5e"));
                mTitleBar.setBackVisible(false);
                mTitleBar.setShaiXuanVisible(true);
                if (data != null) {
                    mTitleBar.setTitleText(data.getWard_name());
                }
                showFrag(rwFragment);
                hideFrag(syFragment);
                hideFrag(tzFragment);
                mMain3LlHzBt.setCompoundDrawables(null, drawable_hz1, null, null);
                mMain3LlRwBt.setCompoundDrawables(null, drawable_rw2, null, null);
                mMain3LlTzBt.setCompoundDrawables(null, drawable_tz1, null, null);
                break;
            //点到了通知 按钮
            case R.id.main3_ll_tz_bt:
                mMain3LlHzBt.setTextColor(Color.parseColor("#5e5e5e"));
                mMain3LlRwBt.setTextColor(Color.parseColor("#5e5e5e"));
                mMain3LlTzBt.setTextColor(Color.parseColor("#559bec"));
                mTitleBar.setBackVisible(false);
                mTitleBar.setShaiXuanVisible(false);
                mTitleBar.setTitleText("通知");
                showFrag(tzFragment);
                hideFrag(rwFragment);
                hideFrag(syFragment);
                mMain3LlHzBt.setCompoundDrawables(null, drawable_hz1, null, null);
                mMain3LlRwBt.setCompoundDrawables(null, drawable_rw1, null, null);
                mMain3LlTzBt.setCompoundDrawables(null, drawable_tz2, null, null);
                break;
            //****************** include 筛选控件
            //include 空白监听
            case R.id.shai_xuan_include_kongbai_tv:
                mShaiXuanIncludeLl.setVisibility(View.GONE);
                break;
            //include 二维码监听
            case R.id.shai_xuan_include_erweima_imgv:
                showToastShort("二维码");
                goToActivity(ErWeiMaActivity.class);
                mShaiXuanIncludeLl.setVisibility(View.GONE);
                break;
            //include 全部患者
            case R.id.shai_xuan_include_all_tv_ll:
                showToastShort("全部患者");
                mLeiXing = "全部患者";
                jibieshuaixuan("全部患者");
                mShaiXuanIncludeLl.setVisibility(View.GONE);
                break;
            //include 我的患者
            case R.id.shai_xuan_include_my_tv_ll:
                showToastShort("我的患者");
                mLeiXing = "我的患者";
                jibieshuaixuan("我的患者");
                mShaiXuanIncludeLl.setVisibility(View.GONE);
                break;
            //include 一级护理
            case R.id.shai_xuan_include_one_tv_ll:
                showToastShort("一级护理");
                mLeiXing = "一级";
                jibieshuaixuan("一级");
                mShaiXuanIncludeLl.setVisibility(View.GONE);
                break;
            //include 二级护理
            case R.id.shai_xuan_include_two_tv_ll:
                showToastShort("二级护理");
                mLeiXing = "二级";
                jibieshuaixuan("二级");
                mShaiXuanIncludeLl.setVisibility(View.GONE);
                break;
            //include 三级护理
            case R.id.shai_xuan_include_three_tv_ll:
                showToastShort("三级护理");
                mLeiXing = "三级";
                jibieshuaixuan("三级");
                mShaiXuanIncludeLl.setVisibility(View.GONE);
                break;
            //include 特级护理
            case R.id.shai_xuan_include_te_tv_ll:
                showToastShort("特级护理");
                mLeiXing = "特级";
                jibieshuaixuan("特级");
                mShaiXuanIncludeLl.setVisibility(View.GONE);
                break;
            //include 床号
            case R.id.shai_xuan_include_chuang_tv:
                showToastShort("床号");
                dbHuanZheLieBiaoList = huanZheLieBiao.getListDBHuanZheLieBiao();
                System.out.println("MainActivity.onClick" + dbHuanZheLieBiaoList.size());
                if (mChuang) {
                    mShaiXuanIncludeChuangImgv.setImageResource(R.mipmap.zheng);
                    mShaiXuanIncludeLl.setVisibility(View.GONE);
                    chuangHaoPaiXu(mChuang, mLeiXing);
                    mChuang = false;
                    break;
                }
                mShaiXuanIncludeChuangImgv.setImageResource(R.mipmap.dao);
                mShaiXuanIncludeLl.setVisibility(View.GONE);
                chuangHaoPaiXu(mChuang, mLeiXing);
                mChuang = true;
                break;
            case R.id.shai_xuan_include_baise_tv:
                break;
            //include 设置
            case R.id.shai_xuan_include_shezhi_tv:
                showToastShort("设置");
                goToActivity(SheZhiActivity.class);
                mShaiXuanIncludeLl.setVisibility(View.GONE);
                break;
            //include 退出
            case R.id.shai_xuan_include_tui_tv:
                selfDialog = new SelfDialog(mActivitySelf);
                selfDialog.setTitle("注销登录");
                if (NetReceiver.isConnected(mActivitySelf) == 1) {
                    selfDialog.setMessage("确认注销？（确认后将退回登录界面）");
                } else {
                    selfDialog.setMessage("确认注销？（无网络连接，退出后数据将丢失）");
                }
                selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        selfDialog.dismiss();
                    }
                });
                selfDialog.setYesOnclickListener("确认", new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        //saveCacheDefine();
                        //cacheDefine();
                        goToActivity(LoginActivity.class);
                        Intent intent = new Intent(mActivitySelf, TongZhiService.class);
                        stopService(intent);
                        killSelf();
                        selfDialog.dismiss();
                    }
                });

                selfDialog.show();
                break;
            //****************** include 筛选控件
        }
    }

    private void chuangHaoPaiXu(boolean mChuang, String mLeiXing) {
        if (mChuang) {
            huanZheLieBiao.getDaoXuList(dbHuanZheLieBiaoList);
            List<HuanZheLieBiaoBean.DataBean> dataBeanList = huanZheLieBiao.getDataBeanList(dbHuanZheLieBiaoList);
            List<HuanZheLieBiaoBean.DataBean> dataBeanList1 = shuaiXuan(mLeiXing, dataBeanList);
            System.out.println("MainActivity.onClick1" + dataBeanList.size());
            syFragment.mSyHzlbLvItemAdapter.setObjects(dataBeanList1);
            syFragment.mSyHzlbLvItemAdapter.notifyDataSetChanged();
            return;
        }
        huanZheLieBiao.getZhengXuList(dbHuanZheLieBiaoList);
        List<HuanZheLieBiaoBean.DataBean> dataBeanList = huanZheLieBiao.getDataBeanList(dbHuanZheLieBiaoList);
        List<HuanZheLieBiaoBean.DataBean> dataBeanList1 = shuaiXuan(mLeiXing, dataBeanList);
        syFragment.mSyHzlbLvItemAdapter.setObjects(dataBeanList1);
        syFragment.mSyHzlbLvItemAdapter.notifyDataSetChanged();
    }

    public void jibieshuaixuan(String str) {
        dbHuanZheLieBiaoList = huanZheLieBiao.getListDBHuanZheLieBiao();
        List<HuanZheLieBiaoBean.DataBean> dataBeanteList = huanZheLieBiao.getDataBeanList(dbHuanZheLieBiaoList);
        shuaiXuan(str, dataBeanteList);

        syFragment.mSyHzlbLvItemAdapter.setObjects(dataBeanteList);
        syFragment.mSyHzlbLvItemAdapter.notifyDataSetChanged();
        mShaiXuanIncludeChuangImgv.setImageResource(R.mipmap.dao);
        mShaiXuanIncludeLl.setVisibility(View.GONE);
        mChuang = true;
    }

    private List<HuanZheLieBiaoBean.DataBean> shuaiXuan(String str, List<HuanZheLieBiaoBean.DataBean> dataBeanteList) {
        Iterator<HuanZheLieBiaoBean.DataBean> iterator = dataBeanteList.iterator();
        if ("全部患者".equals(str)) {
            return dataBeanteList;
        }
        if ("我的患者".equals(str)) {
            while (iterator.hasNext()) {
                HuanZheLieBiaoBean.DataBean dataBean = iterator.next();
                if (!(user.getData().getId() == dataBean.getUserid())) {
                    iterator.remove();
                }
            }
            return dataBeanteList;
        }
        while (iterator.hasNext()) {
            HuanZheLieBiaoBean.DataBean dataBean = iterator.next();
            if (!str.equals(dataBean.getCarelevel())) {
                iterator.remove();
            }
        }
        return dataBeanteList;
    }


    @Override
    public void onBackPressed() {
        if (mShaiXuanIncludeLl.getVisibility() == View.VISIBLE) {
            mShaiXuanIncludeLl.setVisibility(View.GONE);
            return;
        }
        long timeNow = System.currentTimeMillis();
        if (timeNow - mLastDT > 2000) {
            showToastShort("再点击一次退出");
            mLastDT = timeNow;
            return;
        }
        cacheDefine();
        super.onBackPressed();
    }

    //接受消息的地方(在Android的UI线程中)
    public void onEventMainThread(MessageEvent event) {

        try {
            if (event == null) return;
            final String msg = event.getMsg();
            if (msg == null || msg.trim().isEmpty()) return;
            switch (msg) {
                case Constant.MSG_SYNC_REQ: // 请求用户同意同步操作
                    if (isSelfDialogShow) {
                        LogUtil.i("Confirm dialog is showing no need to show it duplicated!");
                        break;
                    }
                    isSelfDialogShow = true;
                    selfDialog = new SelfDialog(mActivitySelf);
                    selfDialog.setTitle("数据同步");
                    selfDialog.setMessage("您有离线数据未提交，确认现在同步吗？");
                    selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            selfDialog.dismiss();
                            isSelfDialogShow = false;
                        }
                    });
                    selfDialog.setYesOnclickListener("确认", new SelfDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            SyncService.syncData();
                            selfDialog.dismiss();
                            // 显示等待页面：等待服务端响应或超时退出
                            if (jiaZaiDialog == null)
                                jiaZaiDialog = new JiaZaiDialog(mActivitySelf);
                            jiaZaiDialog.setCanceledOnTouchOutside(false);
                            jiaZaiDialog.setCancelable(false);
                            jiaZaiDialog.show();
                            // 检测服务端返回
                            new Thread() {
                                @Override
                                public void run() {
                                    int count = 0;
                                    while (true) {
                                        if (NetworkForSynchronize.isSyncFinished() || count > Constant.CHECK_UNSYNC_MAX_COUNT) {
                                            if (jiaZaiDialog != null) jiaZaiDialog.cancel();
                                            isSelfDialogShow = false;
                                            LogUtil.i("Cancel waiting window si9nce all data synced or it was timeout!");
                                            break;
                                        }
                                        try {
                                            this.sleep(Constant.TIME_INTERVAL_COUNT);
                                            LogUtil.i("Try to wait server response for [" + count * Constant.TIME_INTERVAL_COUNT + "] ms!");
                                            count++;
                                        } catch (InterruptedException e) {
                                            LogUtil.e("Thread[" + this.getId() + "] occured error!msg is :" + e.getMessage());
                                            if (jiaZaiDialog != null) jiaZaiDialog.cancel();
                                            isSelfDialogShow = false;
                                            break;
                                        }
                                    }
                                }
                            }.start();

                        }
                    });

                    selfDialog.show();
                    break;
                case Constant.MSG_SYNC_REQ_SUBMIT: // 同步数据已经提交服务器
                    showToastShort("同步数据已经提交服务器,请等待服务器反馈");
                    break;
                case Constant.MSG_SYNC_REQ_REFUSE_NODATA: // 没有需要同步的数据，无需同步
                    showToastShort("没有需要同步的数据，无需同步");
                    break;
                case Constant.MSG_SYNC_REQ_REFUSE_SYNCING: // 上次同步没完成，应稍后同步
                    showToastShort("上次同步没完成，应稍后同步");
                    break;
                default:
                    tongZhiSocket = JSON.parseObject(event.message, TongZhiSocket.class);
                    tzFragment.setSYNetForJSON(sharePLogin.getUserid(), sharePLogin.getToken());
                    break;
            }
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
    }

    @Override
    public void handleOnError() {
        super.handleOnError();

    }

    @Override
    public void handleOnError(String urlStr) {
        super.handleOnError(urlStr);
    }

    private void saveCacheDefine() {
        sharePLogin.saveCache(true);
        sharePLogin.saveCacheAssessDefine(true);
        sharePLogin.saveVitalDefine(true);
        sharePLogin.saveCacheUser(true);
        sharePLogin.saveRiskDefine(true);
        sharePLogin.saveNursingEventTemp(true);
        sharePLogin.saveNursingLieBiao(true);
        sharePLogin.saveXuanJiao(true);
        sharePLogin.saveHouQiCuoShi(true);
        sharePLogin.saveExceptionDefine(true);
    }

    private void cacheDefine() {
        if (sharePLogin.getCache()) {
            if (jiaZaiDialog == null) {
                jiaZaiDialog = new JiaZaiDialog(mActivitySelf);
                jiaZaiDialog.setCanceledOnTouchOutside(false);
            }
            jiaZaiDialog.show();
            //cache define
            LogUtil.d("Begin to cache definition!");
            try {
                new CacheDefine(sharePLogin, (MyBaseActivity) mActivitySelf, sharePLogin.getWardid());
                showToastLong("开始缓存定义");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            jiaZaiDialog.cancel();
        }

    }

}

