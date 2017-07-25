package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.base.Myapp;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.db.DBUser;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.mySyncTask.ErWeiMaSync;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.JiaoBanGvItemAdapter;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.view.JiaZaiDialog;
import com.ge.med.mobile.nursing.utils.YiZhuBeanList;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 核对医嘱
 */
public class HD_YZActivity extends MyBaseActivity implements View.OnClickListener {
    private GridView mJiaoBanGv;
    private JiaoBanGvItemAdapter jiaoBanGvItemAdapter;
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
    private TextView mShaiXuanIncludeShezhiTv;
    private TextView mShaiXuanIncludeTuiTv;
    private ErWeiMaSync erWeiMaSync;
    private TextView mShaiXuanIncludeShezhiXian;
    private TextView mShaiXuanIncludeTuichuXian;
    private Button mHdYzQueRenBt;
    private boolean mChuang = true;
    private String mLeiXing = "我的患者";
    private List<DBHuanZheLieBiao> dbHuanZheLieBiaoList;
    private HuanZheLieBiaoImpl huanZheLieBiao = new HuanZheLieBiaoImpl();
    private DBUser dbUser;
    private int user_id;
    private List<DBHuanZheLieBiao> mDBHuanZheLieBiaolist;
    private List<HuanZheLieBiaoBean.DataBean> dataBeanList;
    private ArrayList<CharSequence> patientidlist = new ArrayList<>();
    private Bundle mBundle;
    private boolean isOK = false;
    private YiZhuBeanList mYizhuBeanList = new YiZhuBeanList();
    public JiaZaiDialog jiaZaiDialog;
    private TitleBar mTitleBar;
    private TextView mShaiXuanIncludeUserLevelTv;


    private LinearLayout mShaiXuanIncludeAllTvLl;
    private LinearLayout mShaiXuanIncludeMyTvLl;
    private LinearLayout mShaiXuanIncludeOneTvLl;
    private LinearLayout mShaiXuanIncludeTwoTvLl;
    private LinearLayout mShaiXuanIncludeThreeTvLl;
    private LinearLayout mShaiXuanIncludeTeTvLl;
    private ImageView mHeduiSaomaImgv;
    private String mRWleiXing;
    private TextView mHeduiSaomaTv;

    // 神达扫描
    //private McBcrConnection bcrConnection;


    @Override
    public int setRootView() {
        return R.layout.activity_hd__yz;
    }

    @Override
    public void initViews() {
        mJiaoBanGv = (GridView) findViewById(R.id.jiao_ban_gv);
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
        mShaiXuanIncludeShezhiTv = (TextView) findViewById(R.id.shai_xuan_include_shezhi_tv);
        mShaiXuanIncludeTuiTv = (TextView) findViewById(R.id.shai_xuan_include_tui_tv);
        mShaiXuanIncludeShezhiXian = (TextView) findViewById(R.id.shai_xuan_include_shezhi_xian);
        mShaiXuanIncludeTuichuXian = (TextView) findViewById(R.id.shai_xuan_include_tuichu_xian);
        mHdYzQueRenBt = (Button) findViewById(R.id.hd_yz_que_ren_bt);
        mHeduiSaomaTv = (TextView) findViewById(R.id.hedui_saoma_tv);
        mShaiXuanIncludeUserLevelTv = (TextView) findViewById(R.id.shai_xuan_include_user_level_tv);


        mShaiXuanIncludeAllTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_all_tv_ll);
        mShaiXuanIncludeMyTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_my_tv_ll);
        mShaiXuanIncludeOneTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_one_tv_ll);
        mShaiXuanIncludeTwoTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_two_tv_ll);
        mShaiXuanIncludeThreeTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_three_tv_ll);
        mShaiXuanIncludeTeTvLl = (LinearLayout) findViewById(R.id.shai_xuan_include_te_tv_ll);
        mHeduiSaomaImgv = (ImageView) findViewById(R.id.hedui_saoma_imgv);
        mJiaoBanGv.setVisibility(View.GONE);
        mHeduiSaomaImgv.setVisibility(View.VISIBLE);
        mHeduiSaomaTv.setVisibility(View.VISIBLE);
        mHdYzQueRenBt.setText("手动选择患者");
    }

    @Override
    public void initDatas() {
        dbUser = new DBUser();
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        String username = sharePLogin.getUsername();

        List<DBUser> mDestList = DataSupport.where("user_id = ?", username).find(DBUser.class);
        if (mDestList != null && mDestList.size() > 0) {
            dbUser = mDestList.get(0);
        }
        user_id = dbUser.getZid();
        String ward_id = dbUser.getWard_id();
        erWeiMaSync = new ErWeiMaSync(this, mShaiXuanIncludeErweimaImgv);
        erWeiMaSync.execute(mShaiXuanIncludeUseridTv.getText().toString());
        mShaiXuanIncludeLl.setVisibility(View.GONE);
        if (dbUser.getNursing_level() != null && dbUser.getWork_year() != null) {
            mShaiXuanIncludeUserLevelTv.setText(dbUser.getWork_year() + " " + dbUser.getNursing_level());
        }
        mShaiXuanIncludeUsernameTv.setText(dbUser.getUsername());
        mShaiXuanIncludeUseridTv.setText(dbUser.getUser_id());
        mShaiXuanIncludeBaiseTv.setOnClickListener(this);
        mShaiXuanIncludeKongbaiTv.setOnClickListener(this);
        mShaiXuanIncludeErweimaImgv.setOnClickListener(this);
        mShaiXuanIncludeAllTvLl.setOnClickListener(this);
        mShaiXuanIncludeMyTvLl.setOnClickListener(this);
        mShaiXuanIncludeOneTvLl.setOnClickListener(this);
        mShaiXuanIncludeTwoTvLl.setOnClickListener(this);
        mShaiXuanIncludeThreeTvLl.setOnClickListener(this);
        mShaiXuanIncludeTeTvLl.setOnClickListener(this);
        mShaiXuanIncludeChuangTv.setVisibility(View.GONE);
        mShaiXuanIncludeShezhiTv.setVisibility(View.GONE);
        mShaiXuanIncludeTuiTv.setVisibility(View.GONE);
        mShaiXuanIncludeShezhiXian.setVisibility(View.GONE);
        mShaiXuanIncludeTuichuXian.setVisibility(View.GONE);
        mDBHuanZheLieBiaolist = DataSupport.findAll(DBHuanZheLieBiao.class);
        dataBeanList = huanZheLieBiao.getDataBeanList(mDBHuanZheLieBiaolist);

        mJiaoBanGv.setVerticalScrollBarEnabled(true);

        mBundle = getIntent().getBundleExtra("data");

        /**
         * 获取未核对医嘱 网络请求
         */
        jiaZaiDialog = new JiaZaiDialog(mActivitySelf);
        jiaZaiDialog.setCanceledOnTouchOutside(false);
        jiaZaiDialog.show();
        mRWleiXing = mBundle.getString(Constant.BUNDLE_KEY_RW);
        if (mRWleiXing != null && Constant.BUNDLE_KEY_VALUE_BAIYAO.equals(mRWleiXing)) {
            NetworkForDoctorOrder.callFindAllDoctorOrder(this, ward_id, null, null, null, Constant.YZ_TYPE_DAIBAIYAO);
            mTitleBar = new TitleBar(this, "摆药");
        } else if (mRWleiXing != null && Constant.BUNDLE_KEY_VALUE_PEIYE.equals(mRWleiXing)) {
            NetworkForDoctorOrder.callFindAllDoctorOrder(this, ward_id, null, null, null, Constant.YZ_TYPE_DAIPEIYE);
            mTitleBar = new TitleBar(this, "配液");
        } else {
            NetworkForDoctorOrder.callFindAllDoctorOrder(this, ward_id, null, null, null, Constant.YZ_TYPE_WEIHEDUI);
            mTitleBar = new TitleBar(this, "核对医嘱");
        }
        mTitleBar.setShaiXuanBehavior(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShaiXuanIncludeLl.setVisibility(View.VISIBLE);
            }
        });
        mTitleBar.setShaiXuanText(dbUser.getUsername());
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
            if (user_id == dataBean.getUserid()) {
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
        mJiaoBanGv.setAdapter(jiaoBanGvItemAdapter);
        jiaoBanGvItemAdapter.setObjects(dataBeanList);
        mHdYzQueRenBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHdYzQueRenBt.getText().equals("开始核对") || mHdYzQueRenBt.getText().equals("开始摆药")
                        || mHdYzQueRenBt.getText().equals("开始配液")) {
                    mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU_LIST, mYizhuBeanList);
                    Map<Integer, String> xuanZeDataMap = jiaoBanGvItemAdapter.getXuanZeDataMap();
                    String hd_yz = mBundle.getString("hd_yz");
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
                    if ("按患者".equals(hd_yz)) {
                        mJiaoBanGv.setVisibility(View.GONE);
                        mHeduiSaomaImgv.setVisibility(View.VISIBLE);
                        mHeduiSaomaTv.setVisibility(View.VISIBLE);
                        mHdYzQueRenBt.setText("手动选择患者");
                        mTitleBar.setShaiXuanVisible(false);
                        goToActivity(YZ_HD_RenActivity.class, mBundle, 0);
                    } else {
                        mJiaoBanGv.setVisibility(View.GONE);
                        mHeduiSaomaImgv.setVisibility(View.VISIBLE);
                        mHeduiSaomaTv.setVisibility(View.VISIBLE);
                        mHdYzQueRenBt.setText("手动选择患者");
                        mTitleBar.setShaiXuanVisible(false);
                        goToActivity(YZ_HD_LXActivity.class, mBundle, 0);
                    }
                } else {
                    if (mRWleiXing != null && Constant.BUNDLE_KEY_VALUE_BAIYAO.equals(mRWleiXing)) {
                        mHdYzQueRenBt.setText("开始摆药");
                        mTitleBar.setShaiXuanVisible(true);
                    } else if (mRWleiXing != null && Constant.BUNDLE_KEY_VALUE_PEIYE.equals(mRWleiXing)) {
                        mHdYzQueRenBt.setText("开始配液");
                        mTitleBar.setShaiXuanVisible(true);
                    } else {
                        mTitleBar.setShaiXuanVisible(true);
                        mHdYzQueRenBt.setText("开始核对");
                    }
                    mJiaoBanGv.setVisibility(View.VISIBLE);
                    mHeduiSaomaImgv.setVisibility(View.GONE);
                    mHeduiSaomaTv.setVisibility(View.GONE);

                }

            }
        });
        /*bcrConnection = new McBcrConnection(this);
        bcrConnection.setListener(this);
        this.bcrConnection.bind();*/
        Myapp.bcrConnection.setListener(this);
    }

    @Override
    public void init() {
        mTitleBar.setShaiXuanVisible(false);
    }

    //扫码扫到患者
    @Override
    public void afterPatientScanned(String patientID) {
        patientidlist.clear();
        patientidlist.add(patientID);
        mBundle.putCharSequenceArrayList(Constant.BUNDLE_KEY_LIST_HUANZHE, patientidlist);
        goToActivity(YZ_HD_RenActivity.class, mBundle, 0);
    }

    //扫码扫到医嘱
    @Override
    public void afterDoctorOrderScanned(int hisID, Boolean isSaoMa) {
        LogUtil.d("MyBaseActivity[" + this.getClass().toString() + "].afterDoctorOrderScanned calling, hisID is " + hisID);
        Log.e("医嘱跳转", "hisId" + hisID);
        YiZhuBean.DataBean dataBean = new DoctorOrderDaoImpl().getDoctorOrderByHisId(hisID);
        Log.e("医嘱跳转", "dbYiZhuDataOne is " + dataBean);
        if (dataBean == null) {
            showToastShort("该医嘱未找到！请刷新医嘱数据！");
            return;
        }
        Log.e("医嘱跳转", "dbYiZhuDataOne bbb " + dataBean.getOrderBloodProperty());
        if (mRWleiXing != null && Constant.BUNDLE_KEY_VALUE_BAIYAO.equals(mRWleiXing)) {
            if (!(Constant.YZ_TYPE_DAIBAIYAO.equals(dataBean.getOrderststus()))) {
                showToastShort("此医嘱状态不是待摆药！");
                return;
            }
        } else if (mRWleiXing != null && Constant.BUNDLE_KEY_VALUE_PEIYE.equals(mRWleiXing)) {
            if (!(Constant.YZ_TYPE_DAIPEIYE.equals(dataBean.getOrderststus()))) {
                showToastShort("此医嘱状态不是待配液！");
                return;
            }
        } else {
            if (!(Constant.YZ_TYPE_WEIHEDUI.equals(dataBean.getOrderststus()))) {
                showToastShort("此医嘱状态不是未核对！");
                return;
            }
        }

        mBundle.putInt(Constant.BUNDLE_KEY_HISID, hisID);
        patientidlist.clear();
        patientidlist.add(dataBean.getPatientid());
        mBundle.putCharSequenceArrayList(Constant.BUNDLE_KEY_LIST_HUANZHE, patientidlist);
        goToActivity(YZ_HD_RenActivity.class, mBundle, 0);
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

            case R.id.shai_xuan_include_baise_tv:
                break;
            //****************** include 筛选控件
        }
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
                if (!(user_id == dataBean.getUserid())) {
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
    public void handleOnError() {
        super.handleOnError();
        showToastShort("医嘱数据获取失败");
        if (jiaZaiDialog != null) jiaZaiDialog.cancel();
    }

    @Override
    public void handleOnError(String urlStr) {
        super.handleOnError(urlStr);
        showToastShort("医嘱数据获取失败");
        if (jiaZaiDialog != null) jiaZaiDialog.cancel();
    }

    @Override
    public void handleSuccess(Object obj) {
        super.handleSuccess(obj);
        if (jiaZaiDialog != null) jiaZaiDialog.cancel();
        if (obj == null) {
            LogUtil.i("Can not handle anything since obj is null!");
        } else {
            LogUtil.d("handleSuccess>obj is " + obj.getClass().getName());
            if (obj instanceof List && ((List) obj).size() > 0) {
                LogUtil.d("handleSuccess>obj->list.get(0) is " + ((List) obj).get(0).getClass().getName());
                if (((List) obj).get(0) instanceof YiZhuBean.DataBean) {
                    mYizhuBeanList.setmYizhuBeanList((List<YiZhuBean.DataBean>) obj);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * onStop event handler
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
