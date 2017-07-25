package com.ge.med.mobile.nursing.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.dao.entity.RWentity;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.db.DBAssessDefine;
import com.ge.med.mobile.nursing.forjson.entity.NetworkForImage;
import com.ge.med.mobile.nursing.forjson.syncDifine.CacheDefine;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.SyShezhiSyLvItemAdapter;
import com.ge.med.mobile.nursing.ui.view.JiaZaiDialog;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class SheZhiActivity extends MyBaseActivity {
    private RelativeLayout mRlTitleBj;
    private ImageView mMyTvTitleLeft;
    private TextView mMyTvTitleCenter;
    private ImageView mImgvTitleRight;
    private TextView mMyTvTitleRight;
    private ListView mSzActivityLv;
    private SyShezhiSyLvItemAdapter syShezhiSyLvItemAdapter;
    private List<RWentity> list = new ArrayList<>();
    private SharePLogin sharePLogin;

    @Override
    public int setRootView() {
        return R.layout.activity_she_zhi;
    }

    @Override
    public void initViews() {
        sharePLogin = new SharePLogin(this);
        tintManager.setTintColor(Color.parseColor("#dddddd"));
        mRlTitleBj = (RelativeLayout) findViewById(R.id.rl_title_bj);
        mMyTvTitleLeft = (ImageView) findViewById(R.id.my_tv_title_left);
        mMyTvTitleCenter = (TextView) findViewById(R.id.my_tv_title_center);
        mImgvTitleRight = (ImageView) findViewById(R.id.imgv_title_right);
        mMyTvTitleRight = (TextView) findViewById(R.id.my_tv_title_right);
        mSzActivityLv = (ListView) findViewById(R.id.sz_activity_lv);
        mMyTvTitleLeft.setImageResource(R.mipmap.fan_hui);
        mMyTvTitleCenter.setText("设置");
        mMyTvTitleRight.setVisibility(View.GONE);
        mImgvTitleRight.setVisibility(View.GONE);
        mMyTvTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killSelf();
            }
        });

    }

    @Override
    public void initDatas() {
        list.add(new RWentity(R.mipmap.icon_password, "更改密码"));
        list.add(new RWentity(R.mipmap.icon_remind, "设置提醒方式"));
        list.add(new RWentity(R.mipmap.icon_user_agreement, "用户协议"));
        list.add(new RWentity(R.mipmap.icon_copyright, "版权信息"));
        list.add(new RWentity(R.mipmap.icon_copyright, "定义缓存"));
        syShezhiSyLvItemAdapter = new SyShezhiSyLvItemAdapter(this, list);
        mSzActivityLv.setAdapter(syShezhiSyLvItemAdapter);
        mSzActivityLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        goToActivity(XGMMActivity.class);
                        break;
                    case 1:
                        goToActivity(TXFSActivity.class);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    // 定义缓存
                    case 4:
                        saveCacheDefine();
                        cacheDefine();
                        killSelf();
                        break;
                }
            }
        });
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
            if (lst.get(0) instanceof AssessDefine.DataBean) {
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
                    NetworkForImage.callGetImage(SheZhiActivity.this, answer);
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
            NetworkForImage.callGetImage(SheZhiActivity.this, topic);
            try {
                thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LogUtil.d("load image string for topicdefine[id" + topic.getId() + ":,imageId:" + topic.getImageId());
        }
    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }


}
