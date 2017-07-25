package com.ge.med.mobile.nursing.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.mySyncTask.ErWeiMaSync;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.JiaoBanGvItemAdapter;
import com.ge.med.mobile.nursing.ui.view.JiaZaiDialog;
import com.mitac.lib.bcr.utils.BARCODE;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JiaoBanActivity extends MyBaseActivity implements View.OnClickListener {
    private TextView mMyTvTitleCenter;
    private ImageView mImgvTitleRight;
    private TextView mMyTvTitleRight;
    private GridView mJiaoBanGv;
    private JiaoBanGvItemAdapter jiaoBanGvItemAdapter;
    private SharePLogin sharePLogin;
    private Button mTiJiaoBt;
    private Bundle mBundle;
    private ArrayList<CharSequence> patientidlist = new ArrayList<>();
    private String mLeiXing = "我的患者";
    private boolean mChuang = true;
    private String user_id = "";
    private List<DBHuanZheLieBiao> dbHuanZheLieBiaoList;
    private HuanZheLieBiaoImpl huanZheLieBiao = new HuanZheLieBiaoImpl();
    private JiaZaiDialog jiaZaiDialog;

    private LinearLayout mShaiXuanIncludeLl;
    private TextView mShaiXuanIncludeKongbaiTv;
    private ImageView mShaiXuanIncludeErweimaImgv;
    private TextView mShaiXuanIncludeUsernameTv;
    private TextView mShaiXuanIncludeUseridTv;
    private TextView mShaiXuanIncludeAllTv;
    private TextView mShaiXuanIncludeMyTv;
    private TextView mShaiXuanIncludeOneTv;
    private TextView mShaiXuanIncludeTwoTv;
    private TextView mShaiXuanIncludeThreeTv;
    private TextView mShaiXuanIncludeTeTv;
    private LinearLayout mShaiXuanIncludeChuangTv;
    private ImageView mShaiXuanIncludeChuangImgv;
    private TextView mShaiXuanIncludeBaiseTv;
    private TextView mShaiXuanIncludeShezhiXian;
    private TextView mShaiXuanIncludeShezhiTv;
    private TextView mShaiXuanIncludeTuichuXian;
    private TextView mShaiXuanIncludeTuiTv;

    private ErWeiMaSync erWeiMaSync;
    private String username;
    private String displayUserName;
    private DBHuanZheLieBiao mDBHuanZheLieBiao;
    private List<DBHuanZheLieBiao> mDBHuanZheLieBiaolist;
    private List<HuanZheLieBiaoBean.DataBean> dataBeanList;

    private LinearLayout mShaiXuanIncludeAllTvLl;
    private LinearLayout mShaiXuanIncludeMyTvLl;
    private LinearLayout mShaiXuanIncludeOneTvLl;
    private LinearLayout mShaiXuanIncludeTwoTvLl;
    private LinearLayout mShaiXuanIncludeThreeTvLl;
    private LinearLayout mShaiXuanIncludeTeTvLl;
    private TextView mShaiXuanIncludeUserLevelTv;

    @Override
    public int setRootView() {
        return R.layout.activity_jiao_ban;
    }

    @Override
    public void initViews() {
        mShaiXuanIncludeLl = (LinearLayout) findViewById(R.id.shai_xuan_include_ll);
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
        mShaiXuanIncludeBaiseTv = (TextView) findViewById(R.id.shai_xuan_include_baise_tv);
        mShaiXuanIncludeShezhiXian = (TextView) findViewById(R.id.shai_xuan_include_shezhi_xian);
        mShaiXuanIncludeShezhiTv = (TextView) findViewById(R.id.shai_xuan_include_shezhi_tv);
        mShaiXuanIncludeTuichuXian = (TextView) findViewById(R.id.shai_xuan_include_tuichu_xian);
        mShaiXuanIncludeTuiTv = (TextView) findViewById(R.id.shai_xuan_include_tui_tv);

        mShaiXuanIncludeAllTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_all_tv_ll);
        mShaiXuanIncludeMyTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_my_tv_ll);
        mShaiXuanIncludeOneTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_one_tv_ll);
        mShaiXuanIncludeTwoTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_two_tv_ll);
        mShaiXuanIncludeThreeTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_three_tv_ll);
        mShaiXuanIncludeTeTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_te_tv_ll);

        mShaiXuanIncludeTuiTv.setVisibility(View.GONE);
        mShaiXuanIncludeTuichuXian.setVisibility(View.GONE);
        mShaiXuanIncludeShezhiTv.setVisibility(View.GONE);
        mShaiXuanIncludeShezhiXian.setVisibility(View.GONE);
        mShaiXuanIncludeChuangTv.setVisibility(View.GONE);
        tintManager.setTintColor(Color.parseColor("#dddddd"));
        sharePLogin = new SharePLogin(mActivitySelf);
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        username = sharePLogin.getUsername();
        displayUserName = sharePLogin.getDisplayUserName();

        user_id = sharePLogin.getUserid();
        if (user_id == null) {
            user_id = "";
        }
        mBundle = getIntent().getBundleExtra("data");
        mDBHuanZheLieBiao = new DBHuanZheLieBiao();
        mDBHuanZheLieBiaolist = DataSupport.findAll(DBHuanZheLieBiao.class);
        dataBeanList = huanZheLieBiao.getDataBeanList(mDBHuanZheLieBiaolist);
        dataBeanList = huanZheLieBiao.getZhengXuListBean(dataBeanList);
        String wardid = sharePLogin.getWardid();
        RelativeLayout mRlTitleBj = (RelativeLayout) findViewById(R.id.rl_title_bj);
        ImageView mMyTvTitleLeft = (ImageView) findViewById(R.id.my_tv_title_left);
        mMyTvTitleCenter = (TextView) findViewById(R.id.my_tv_title_center);
        mImgvTitleRight = (ImageView) findViewById(R.id.imgv_title_right);
        mMyTvTitleRight = (TextView) findViewById(R.id.my_tv_title_right);
        mShaiXuanIncludeUserLevelTv = (TextView) findViewById(R.id.shai_xuan_include_user_level_tv);
        mMyTvTitleRight.setText(displayUserName);
        if (sharePLogin.getUserLevel()!=null) {
            mShaiXuanIncludeUserLevelTv.setText(sharePLogin.getUserLevel());
        }
        mShaiXuanIncludeUsernameTv.setText(displayUserName);
        mShaiXuanIncludeUseridTv.setText(username);
        erWeiMaSync = new ErWeiMaSync(mActivitySelf, mShaiXuanIncludeErweimaImgv);
        erWeiMaSync.execute(username);
        mTiJiaoBt = (Button) findViewById(R.id.jiao_ban_que_ren_bt);
        mJiaoBanGv = (GridView) findViewById(R.id.jiao_ban_gv);

        if (dataBeanList == null || dataBeanList.size() <= 0) {
            showToastShort("病区当前没有患者！");
            LogUtil.e("Can not got patient from server!");
            return;
        }
        LogUtil.d("Got [" + dataBeanList.size() + "] patient(s) from server!");
        mShaiXuanIncludeAllTv.setText("" + dataBeanList.size());
        List<HuanZheLieBiaoBean.DataBean> data0 = new ArrayList<>();
        List<HuanZheLieBiaoBean.DataBean> data1 = new ArrayList<>();
        List<HuanZheLieBiaoBean.DataBean> data2 = new ArrayList<>();
        List<HuanZheLieBiaoBean.DataBean> data3 = new ArrayList<>();
        List<HuanZheLieBiaoBean.DataBean> data4 = new ArrayList<>();
        for (HuanZheLieBiaoBean.DataBean dataBean : dataBeanList) {
            if (user_id.equals(dataBean.getUserid() + "")) {
                data0.add(dataBean);
            }
            if (Constant.CARE_LEVEL_YIZHI.equals(dataBean.getCarelevel())) {
                data1.add(dataBean);
            }
            if (Constant.CARE_LEVEL_ERZHI.equals(dataBean.getCarelevel())) {
                data2.add(dataBean);
            }
            if (Constant.CARE_LEVEL_SANZHI.equals(dataBean.getCarelevel())) {
                data3.add(dataBean);
            }
            if (Constant.CARE_LEVEL_TEZHI.equals(dataBean.getCarelevel())) {
                data4.add(dataBean);
            }
        }
        mShaiXuanIncludeMyTv.setText(data0.size() + "");
        mShaiXuanIncludeOneTv.setText("" + data1.size());
        mShaiXuanIncludeTwoTv.setText("" + data2.size());
        mShaiXuanIncludeThreeTv.setText("" + data3.size());
        mShaiXuanIncludeTeTv.setText("" + data4.size());

        jiaoBanGvItemAdapter = new JiaoBanGvItemAdapter(this);
        jiaoBanGvItemAdapter.setObjects(dataBeanList);
        mJiaoBanGv.setAdapter(jiaoBanGvItemAdapter);

//        //獲取全部患者列表
//        OkHttpUtils
//                .post()
//                .url(URL.URL_QUAN_BU_SY)
//                .addHeader("User-Agent", "www.gs.com")
//                .addParams("wardId", wardid + "")
//                .build()
//                .execute(new SY_HuanZheLieBiao_CallBack() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        showToastShort("网络访问异常!");
//                        jiaZaiDialog.cancel();
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        super.onResponse(response, id);
//                        jibieshuaixuan("我的患者");
//                        jiaZaiDialog.cancel();

//                        jiaoBanGvItemAdapter.setObjects(data);
//                        jiaoBanGvItemAdapter.notifyDataSetChanged();
//
//                    }
//                });
//        jiaZaiDialog = new JiaZaiDialog(mActivitySelf);
//        jiaZaiDialog.setCanceledOnTouchOutside(false);
//        jiaZaiDialog.show();

        mJiaoBanGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToActivity(JiaoBanSJActivity.class,mBundle);
            }
        });
        mMyTvTitleCenter.setText("交班");
        mMyTvTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killSelf();
            }
        });
        mImgvTitleRight.setOnClickListener(this);
        mMyTvTitleRight.setOnClickListener(this);

    }

    @Override
    public void initDatas() {
        mTiJiaoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<Integer, String> xuanZeDataMap = jiaoBanGvItemAdapter.getXuanZeDataMap();
                patientidlist.clear();
                Iterator it = xuanZeDataMap.keySet().iterator();
                while (it.hasNext()) {
                    String patientid = xuanZeDataMap.get(it.next());
                    if (patientid != null) {
                        patientidlist.add(patientid);
                    }
                }
                if (patientidlist.size() == 0) {
                    showToastShort("请选择患者！");
                    return;
                }
                mBundle.putCharSequenceArrayList(Constant.BUNDLE_KEY_LIST_HUANZHE, patientidlist);
                goToActivity(JiaoBanSJActivity.class, mBundle);
            }
        });
    }

    @Override
    public void init() {


        mShaiXuanIncludeLl.setOnClickListener(this);
        mShaiXuanIncludeAllTvLl.setOnClickListener(this);
        mShaiXuanIncludeMyTvLl.setOnClickListener(this);
        mShaiXuanIncludeOneTvLl.setOnClickListener(this);
        mShaiXuanIncludeTwoTvLl.setOnClickListener(this);
        mShaiXuanIncludeThreeTvLl.setOnClickListener(this);
        mShaiXuanIncludeTeTvLl.setOnClickListener(this);
        mShaiXuanIncludeChuangTv.setOnClickListener(this);
        mShaiXuanIncludeBaiseTv.setOnClickListener(this);
        mShaiXuanIncludeKongbaiTv.setOnClickListener(this);
    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                break;
            //include 我的患者
            case R.id.shai_xuan_include_my_tv_ll:
                showToastShort("我的患者");
                mLeiXing = "我的患者";
                jibieshuaixuan("我的患者");
                break;
            //include 一级护理
            case R.id.shai_xuan_include_one_tv_ll:
                showToastShort("一级护理");
                mLeiXing = "一级";
                jibieshuaixuan("一级");
                break;
            //include 二级护理
            case R.id.shai_xuan_include_two_tv_ll:
                showToastShort("二级护理");
                mLeiXing = "二级";
                jibieshuaixuan("二级");
                break;
            //include 三级护理
            case R.id.shai_xuan_include_three_tv_ll:
                showToastShort("三级护理");
                mLeiXing = "三级";
                jibieshuaixuan("三级");
                break;
            //include 特级护理
            case R.id.shai_xuan_include_te_tv_ll:
                showToastShort("特级护理");
                mLeiXing = "特级";
                jibieshuaixuan("特级");
                break;

            case R.id.shai_xuan_include_baise_tv:
                break;
            //include 床号
            case R.id.shai_xuan_include_chuang_tv:
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
            case R.id.imgv_title_right:
                showToastShort("筛选");
                mShaiXuanIncludeLl.setVisibility(View.VISIBLE);
                break;
            case R.id.my_tv_title_right:
                showToastShort("筛选");
                mShaiXuanIncludeLl.setVisibility(View.VISIBLE);
                break;
            //****************** include 筛选控件
        }
    }

    private void chuangHaoPaiXu(boolean mChuang, String mLeiXing) {
        if (mChuang) {
            huanZheLieBiao.getDaoXuList(dbHuanZheLieBiaoList);
            dataBeanList = huanZheLieBiao.getDataBeanList(dbHuanZheLieBiaoList);
            List<HuanZheLieBiaoBean.DataBean> dataBeanList1 = shuaiXuan(mLeiXing, dataBeanList);
            System.out.println("MainActivity.onClick1" + dataBeanList.size());
            jiaoBanGvItemAdapter.setObjects(dataBeanList1);
            jiaoBanGvItemAdapter.notifyDataSetChanged();
            return;
        }
        huanZheLieBiao.getZhengXuList(dbHuanZheLieBiaoList);
        dataBeanList = huanZheLieBiao.getDataBeanList(dbHuanZheLieBiaoList);
        List<HuanZheLieBiaoBean.DataBean> dataBeanList1 = shuaiXuan(mLeiXing, dataBeanList);
        jiaoBanGvItemAdapter.setObjects(dataBeanList1);
        jiaoBanGvItemAdapter.notifyDataSetChanged();
    }

    private void jibieshuaixuan(String str) {
        dbHuanZheLieBiaoList = huanZheLieBiao.getListDBHuanZheLieBiao();
        List<HuanZheLieBiaoBean.DataBean> dataBeanteList = huanZheLieBiao.getDataBeanList(dbHuanZheLieBiaoList);
        shuaiXuan(str, dataBeanteList);
        System.out.println(dataBeanteList.size() + "((((((((((((((((((((((((");
        jiaoBanGvItemAdapter.setObjects(dataBeanteList);
        jiaoBanGvItemAdapter.notifyDataSetChanged();
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
                System.out.println("wodehuanzhe" + user_id + "===" + dataBean.getUserid());
                if (!(user_id.equals(dataBean.getUserid() + ""))) {
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


}
