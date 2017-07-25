package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.sj.library.control.ActivityControl;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.DoctorOrderDao;
import com.ge.med.mobile.nursing.dao.HuanZheLieBiaoInterface;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.YizhuHeduiLeixingLvItemAdapter;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.view.SelfDialog;
import com.ge.med.mobile.nursing.utils.DataConverter;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YZ_HD_LXActivity extends MyBaseActivity implements View.OnClickListener {
    private YizhuHeduiLeixingLvItemAdapter yizhuHeduiLeixingLvItemAdapter;
    private ArrayList<CharSequence> list = new ArrayList<>();
    private ListView mYzHdLxSjLv;
    private ScrollView mYiZhuXuanXiangIncludeLl;
    private TextView mShaiXuanIncludeKongbaiTv;
    private TextView mYiZhuXuanXiangQuanbuYizhuShu;
    private LinearLayout mYiZhuXuanXiangZhuSheLl;
    private TextView mYiZhuXuanXiangZhuSheShu;
    private LinearLayout mYiZhuXuanXiangZhuShuYeLl;
    private TextView mYiZhuXuanXiangZhuShuYeShu;
    private LinearLayout mYiZhuXuanXiangZhuKouFuLl;
    private TextView mYiZhuXuanXiangZhuSkouFuShu;
    private LinearLayout mYiZhuXuanXiangZhuJianYanLl;
    private TextView mYiZhuXuanXiangZhuJianYanShu;
    private LinearLayout mYiZhuXuanXiangZhuShuXueLl;
    private TextView mYiZhuXuanXiangZhuShuXueShu;
    private LinearLayout mYiZhuXuanXiangZhuFuzhuZhiliaoLl;
    private TextView mYiZhuXuanXiangZhuFuzhuZhiliaoShu;
    private LinearLayout mYiZhuXuanXiangZhuPishiLl;
    private TextView mYiZhuXuanXiangZhuPishiShu;

    private TextView mZhanweizhi;
    private Button mYzHdLxYichangBt;
    private Button mYzHdLxTijiaoBt;
    private Bundle mBundle;
    private TextView mYiZhuXuanXiangQuanbuYizhuTv;
    private List<DBHuanZheLieBiao> huanZheLieBiaoList = new ArrayList<>();
    private List<HuanZheLieBiaoBean.DataBean> dataBeanList;
    private List<YiZhuBean.DataBean> mDbYiZhuData;
    private TitleBar mTitleBar;
    private HuanZheLieBiaoInterface huanZheLieBiao;
    private String userid;
    private boolean isToast;
    private YiZhuBean.DataBean yizhuDataBean;
    private DoctorOrderDao mDoctorOrderDaoImpl;
    private LinearLayout mYiZhuXuanXiangZhuJianYanXueLl;
    private TextView mYiZhuXuanXiangZhuRuhuShu;
    private LinearLayout mYiZhuXuanXiangZhuRuhuLl;
    private TextView mYiZhuXuanXiangZhuHuliZhiliaoShu;
    private LinearLayout mYiZhuXuanXiangZhuHuliZhiliaoLl;
    private TextView mYiZhuXuanXiangZhuJianYanXueShu;
    private TextView mYiZhuXuanXiangZhuShanshiZhiliaoShu;
    private LinearLayout mYiZhuXuanXiangZhuShanshiZhiliaoLl;
    private TextView mYiZhuXuanXiangJirouZhuSheShu;
    private LinearLayout mYiZhuXuanXiangJirouZhuSheLl;
    private TextView mYiZhuXuanXiangPixiaZhuSheShu;
    private LinearLayout mYiZhuXuanXiangPixiaZhuSheLl;

    @Override
    public int setRootView() {
        return R.layout.activity_yz__hd__lx;
    }

    @Override
    public void initViews() {
        mYzHdLxSjLv = (ListView) findViewById(R.id.yz_hd_lx_sj_lv);
        mYiZhuXuanXiangQuanbuYizhuTv = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_quanbu_yizhu_tv);
        mYiZhuXuanXiangIncludeLl = (ScrollView) findViewById(R.id.yi_zhu_xuan_xiang_include_ll);
        mShaiXuanIncludeKongbaiTv = (TextView) findViewById(R.id.shai_xuan_include_kongbai_tv);
        mYiZhuXuanXiangQuanbuYizhuShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_quanbu_yizhu_shu);
        mYiZhuXuanXiangZhuSheLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_she_ll);
        mYiZhuXuanXiangZhuSheShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_she_shu);
        mYiZhuXuanXiangZhuShuYeLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_shu_ye_ll);
        mYiZhuXuanXiangZhuShuYeShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_shu_ye_shu);
        mYiZhuXuanXiangZhuKouFuLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_kou_fu_ll);
        mYiZhuXuanXiangZhuSkouFuShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_skou_fu_shu);
        mYiZhuXuanXiangZhuJianYanLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_jian_yan_ll);
        mYiZhuXuanXiangZhuJianYanShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_jian_yan_shu);
        mYiZhuXuanXiangZhuShuXueLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_shu_xue_ll);
        mYiZhuXuanXiangZhuShuXueShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_shu_xue_shu);
        mYiZhuXuanXiangZhuFuzhuZhiliaoLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_fuzhu_zhiliao_ll);
        mYiZhuXuanXiangZhuFuzhuZhiliaoShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_fuzhu_zhiliao_shu);
        mYiZhuXuanXiangZhuPishiLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_pishi_ll);
        mYiZhuXuanXiangZhuPishiShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_pishi_shu);
        mYiZhuXuanXiangZhuJianYanXueLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_jian_yan_xue_ll);
        mYiZhuXuanXiangZhuJianYanXueShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_jian_yan_xue_shu);
        mYiZhuXuanXiangPixiaZhuSheLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_pixia_zhu_she_ll);
        mYiZhuXuanXiangPixiaZhuSheShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_pixia_zhu_she_shu);
        mYiZhuXuanXiangJirouZhuSheLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_jirou_zhu_she_ll);
        mYiZhuXuanXiangJirouZhuSheShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_jirou_zhu_she_shu);
        mYiZhuXuanXiangZhuShanshiZhiliaoLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_shanshi_zhiliao_ll);
        mYiZhuXuanXiangZhuShanshiZhiliaoShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_shanshi_zhiliao_shu);
        mYiZhuXuanXiangZhuHuliZhiliaoLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_huli_zhiliao_ll);
        mYiZhuXuanXiangZhuHuliZhiliaoShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_huli_zhiliao_shu);
        mYiZhuXuanXiangZhuRuhuLl = (LinearLayout) findViewById(R.id.yi_zhu_xuan_xiang_zhu_ruhu_ll);
        mYiZhuXuanXiangZhuRuhuShu = (TextView) findViewById(R.id.yi_zhu_xuan_xiang_zhu_ruhu_shu);

        mZhanweizhi = (TextView) findViewById(R.id.zhanweizhi);
        mYzHdLxYichangBt = (Button) findViewById(R.id.yz_hd_lx_yichang_bt);
        mYzHdLxTijiaoBt = (Button) findViewById(R.id.yz_hd_lx_tijiao_bt);
        mDoctorOrderDaoImpl = new DoctorOrderDaoImpl();

    }


    //数目
    private int shuMu(String string) {
        List<YiZhuBean.DataBean> dbYiZhuData1 = new ArrayList<>();
        for (YiZhuBean.DataBean dbYiZhuDaTa : mDbYiZhuData) {
            if (string.equals(dbYiZhuDaTa.getOrdertype())) {
                dbYiZhuData1.add(dbYiZhuDaTa);
            }
        }
        return dbYiZhuData1.size();
    }

    //筛选医嘱
    private void yiZhuLv(String string) {
        if (string.equals("全部医嘱")) {
            yizhuHeduiLeixingLvItemAdapter.setmEntities(mDbYiZhuData);
            yizhuHeduiLeixingLvItemAdapter.setPosList(ActivityUtils.suanPos(mDbYiZhuData));
            yizhuHeduiLeixingLvItemAdapter.notifyDataSetChanged();
            return;
        }
        List<YiZhuBean.DataBean> dbYiZhuData1 = new ArrayList<>();
        for (YiZhuBean.DataBean dbYiZhuDaTa : mDbYiZhuData) {
            if (string.equals(dbYiZhuDaTa.getOrdertype())) {
                dbYiZhuData1.add(dbYiZhuDaTa);
            }
        }
        yizhuHeduiLeixingLvItemAdapter.setmEntities(dbYiZhuData1);
        yizhuHeduiLeixingLvItemAdapter.setPosList(ActivityUtils.suanPos(dbYiZhuData1));

        yizhuHeduiLeixingLvItemAdapter.notifyDataSetChanged();

    }

    @Override
    public void afterDoctorOrderScanned(int hisID, Boolean isSaoMa) {
        final List<YiZhuBean.DataBean> dataBeen = yizhuHeduiLeixingLvItemAdapter.getmEntities();

        for (int i = 0; i < dataBeen.size(); i++) {
            if (dataBeen.get(i) != null) {
                if (hisID == dataBeen.get(i).getId()) {
                    if (mDoctorOrderDaoImpl.getExceptionForCheck(dataBeen.get(i).getId()) != null) {
                        final int finalI = i;
                        yizhuHeduiLeixingLvItemAdapter.selfDialog.setYesOnclickListener("确认", new SelfDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                saoMa(dataBeen.get(finalI), finalI);
                                mDoctorOrderDaoImpl.saveExceptionForCheck(dataBeen.get(finalI).getId(), null);
                                yizhuHeduiLeixingLvItemAdapter.selfDialog.dismiss();
                            }
                        });
                        yizhuHeduiLeixingLvItemAdapter.selfDialog.show();
                    }
                    saoMa(dataBeen.get(i), i);
                }
            }
        }
        if (!isToast) {
            showMessage("条码不匹配！");
            isToast = false;
        }
    }

    private void saoMa(YiZhuBean.DataBean dataBean, int i) {
        yizhuHeduiLeixingLvItemAdapter.mCBFlag.put(i, true);
        yizhuHeduiLeixingLvItemAdapter.setP(i);
        yizhuHeduiLeixingLvItemAdapter.notifyDataSetChanged();
        mYzHdLxSjLv.setSelection(i);
        isToast = true;
        mYzHdLxYichangBt.setEnabled(true);
        yizhuDataBean = dataBean;
    }

    @Override
    public void initDatas() {
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        userid = sharePLogin.getUserid();
        huanZheLieBiao = new HuanZheLieBiaoImpl();
        mBundle = getIntent().getBundleExtra("data");

        list = mBundle.getCharSequenceArrayList(Constant.BUNDLE_KEY_LIST_HUANZHE);
        for (CharSequence cs : list) {
            System.out.println("patientIdList is :" + list);
            huanZheLieBiaoList.add(huanZheLieBiao.getDBHuanZheLieBiao(cs.toString()));
        }
        dataBeanList = huanZheLieBiao.getDataBeanList(huanZheLieBiaoList);
        //
//        YiZhuBeanList mYizhuBeanList = (YiZhuBeanList) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU_LIST);
//        if (mYizhuBeanList != null) {
//            mDbYiZhuData = mYizhuBeanList.getmYizhuBeanList();
//            if (mDbYiZhuData == null) {
//                mDbYiZhuData = new ArrayList<>();
//            }
//        } else {
        DoctorOrderDaoImpl doctorOrderDao = new DoctorOrderDaoImpl();
        if (mDbYiZhuData == null) {
            mDbYiZhuData = new ArrayList<>();
        }
        for (HuanZheLieBiaoBean.DataBean dataBean : dataBeanList) {
            if (null != dataBean.getPatientid()) {
                mDbYiZhuData.addAll(doctorOrderDao.findDoctorOrdersInJsonBean(dataBean.getPatientid(),
                        null, Constant.YZ_TYPE_WEIHEDUI));
            }
        }

//        }
        mTitleBar = new TitleBar(this, "核对医嘱");
        mTitleBar.setShaiXuanBehavior(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYiZhuXuanXiangIncludeLl.setVisibility(View.VISIBLE);
            }
        });
        mYiZhuXuanXiangQuanbuYizhuTv.setOnClickListener(this);
        mYiZhuXuanXiangQuanbuYizhuShu.setText(mDbYiZhuData.size() + "");
        mYiZhuXuanXiangZhuSheLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuSheShu.setText(shuMu(Constant.TYPE_YZ_JINGMAIZHUSHE) + "");
        mYiZhuXuanXiangZhuShuYeLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuShuYeShu.setText(shuMu(Constant.TYPE_YZ_SHUYE) + "");
        mYiZhuXuanXiangZhuKouFuLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuSkouFuShu.setText(shuMu(Constant.TYPE_YZ_KOUFU) + "");
        mYiZhuXuanXiangZhuJianYanLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuJianYanShu.setText(shuMu(Constant.TYPE_YZ_JIANYAN_UN_BLOOD) + "");
        mYiZhuXuanXiangZhuShuXueLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuShuXueShu.setText(shuMu(Constant.TYPE_YZ_SHUXUE) + "");
        mYiZhuXuanXiangZhuFuzhuZhiliaoLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuFuzhuZhiliaoShu.setText(shuMu(Constant.TYPE_YZ_ZHILIAO) + "");
        mYiZhuXuanXiangZhuPishiLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuPishiShu.setText(shuMu(Constant.TYPE_YZ_PISHI) + "");
        mYiZhuXuanXiangPixiaZhuSheLl.setOnClickListener(this);
        mYiZhuXuanXiangPixiaZhuSheShu.setText(shuMu(Constant.TYPE_YZ_PIXIAZHUSHE) + "");
        mYiZhuXuanXiangJirouZhuSheLl.setOnClickListener(this);
        mYiZhuXuanXiangJirouZhuSheShu.setText(shuMu(Constant.TYPE_YZ_JIROUZHUSHE) + "");
        mYiZhuXuanXiangZhuShanshiZhiliaoLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuShanshiZhiliaoShu.setText(shuMu(Constant.TYPE_YZ_SHANSHI) + "");
        mYiZhuXuanXiangZhuHuliZhiliaoLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuHuliZhiliaoShu.setText(shuMu(Constant.TYPE_YZ_HULI) + "");
        mYiZhuXuanXiangZhuRuhuLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuRuhuShu.setText(shuMu(Constant.TYPE_YZ_RUHU) + "");
        mShaiXuanIncludeKongbaiTv.setOnClickListener(this);
        mYiZhuXuanXiangZhuJianYanXueLl.setOnClickListener(this);
        mYiZhuXuanXiangZhuJianYanXueShu.setText(shuMu(Constant.TYPE_YZ_JIANYAN_BLOOD) + "");
        mZhanweizhi.setOnClickListener(this);
        mDbYiZhuData = new DoctorOrderDaoImpl().getFPaiXuDBYiZhuDatabean(mDbYiZhuData);
        yizhuHeduiLeixingLvItemAdapter = new YizhuHeduiLeixingLvItemAdapter(this, mDbYiZhuData, mYzHdLxYichangBt, mBundle);
        yizhuHeduiLeixingLvItemAdapter.setPosList(ActivityUtils.suanPos(mDbYiZhuData));
        mYzHdLxSjLv.setAdapter(yizhuHeduiLeixingLvItemAdapter);
    }

    @Override
    public void init() {
//异常按钮
        mYzHdLxYichangBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putInt("hisid", yizhuHeduiLeixingLvItemAdapter.getZid());
                YiZhuBean.DataBean dbYiZhuDaTa = null;
                if (yizhuHeduiLeixingLvItemAdapter != null) {
                    dbYiZhuDaTa = yizhuHeduiLeixingLvItemAdapter.getDbYiZhuDaTa();
                }
                if (dbYiZhuDaTa == null) {
                    dbYiZhuDaTa = yizhuDataBean;
                }
                if (dbYiZhuDaTa == null) {
                    showToastShort("医嘱数据异常！");
                    return;
                }
                DBHuanZheLieBiao dbHuanZheLieBiao = huanZheLieBiao.getDBHuanZheLieBiao(dbYiZhuDaTa.getPatientid());
                mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU, dbYiZhuDaTa);
                mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, DataConverter.convert(dbHuanZheLieBiao));
                Intent intent = new Intent(mActivitySelf, YZ_HD_YiChangActivity.class);
                if (mBundle != null) {
                    intent.putExtra("data", mBundle);
                }
                startActivityForResult(intent, 0);
            }
        });

        //提交按钮
        mYzHdLxTijiaoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<YiZhuBean.DataBean> dbYiZhuDaTas = yizhuHeduiLeixingLvItemAdapter.getmEntities();
                Map<Integer, TextView> cbMap = yizhuHeduiLeixingLvItemAdapter.getCbMap();
                int pos = 0;
                for (Map.Entry<Integer, TextView> entry : cbMap.entrySet()) {
                    pos = entry.getKey();
                    dbYiZhuDaTas.get(pos).setOrderststus(Constant.YZ_TYPE_WEIZHIXING);
                    dbYiZhuDaTas.get(pos).setLastupdatedby(userid);
                    dbYiZhuDaTas.get(pos).setCheckby(userid);
                    dbYiZhuDaTas.get(pos).setChecktime(System.currentTimeMillis());
                    dbYiZhuDaTas.get(pos).setLastupdatetime(System.currentTimeMillis());
                }
                mDbYiZhuData = new ArrayList<>();
                for (int i = dbYiZhuDaTas.size() - 1; i >= 0; i--) {
                    if (Constant.YZ_TYPE_WEIZHIXING.equals(dbYiZhuDaTas.get(i).getOrderststus())) {
                        mDbYiZhuData.add(dbYiZhuDaTas.get(i));
                        dbYiZhuDaTas.remove(i);
                    }
                }
                LogUtil.e("mDbYiZhuData . size" + mDbYiZhuData.size());
                new DoctorOrderDaoImpl().saveDoctorOrders(mDbYiZhuData,true);
                NetworkForDoctorOrder.sumbmitAllDoctorOrder(YZ_HD_LXActivity.this, mDbYiZhuData);
                dbYiZhuDaTas = new DoctorOrderDaoImpl().getFPaiXuDBYiZhuDatabean(dbYiZhuDaTas);
                yizhuHeduiLeixingLvItemAdapter.setmEntities(dbYiZhuDaTas);
                yizhuHeduiLeixingLvItemAdapter.setPosList(ActivityUtils.suanPos(dbYiZhuDaTas));
                yizhuHeduiLeixingLvItemAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //全部医嘱
            case R.id.yi_zhu_xuan_xiang_quanbu_yizhu_tv:
                showToastShort("全部医嘱");
                yiZhuLv("全部医嘱");
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //注射
            case R.id.yi_zhu_xuan_xiang_zhu_she_ll:
                showToastShort("静脉注射");
                yiZhuLv(Constant.TYPE_YZ_JINGMAIZHUSHE);
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //注射
            case R.id.yi_zhu_xuan_xiang_pixia_zhu_she_ll:
                showToastShort("皮下注射");
                yiZhuLv(Constant.TYPE_YZ_PIXIAZHUSHE);
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //注射
            case R.id.yi_zhu_xuan_xiang_jirou_zhu_she_ll:
                showToastShort("肌肉注射");
                yiZhuLv(Constant.TYPE_YZ_JIROUZHUSHE);
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //输液
            case R.id.yi_zhu_xuan_xiang_zhu_shu_ye_ll:
                showToastShort("输液");
                yiZhuLv(Constant.TYPE_YZ_SHUYE);
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //口服
            case R.id.yi_zhu_xuan_xiang_zhu_kou_fu_ll:
                showToastShort("口服");
                yiZhuLv(Constant.TYPE_YZ_KOUFU);
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //检验
            case R.id.yi_zhu_xuan_xiang_zhu_jian_yan_ll:
                showToastShort("检验(非血)");
                yiZhuLv(Constant.TYPE_YZ_JIANYAN_UN_BLOOD);
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //检验
            case R.id.yi_zhu_xuan_xiang_zhu_jian_yan_xue_ll:
                showToastShort("检验(血)");
                yiZhuLv(Constant.TYPE_YZ_JIANYAN_BLOOD);
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //输血
            case R.id.yi_zhu_xuan_xiang_zhu_shu_xue_ll:
                showToastShort("输血");
                yiZhuLv(Constant.TYPE_YZ_SHUXUE);
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //辅助治疗
            case R.id.yi_zhu_xuan_xiang_zhu_fuzhu_zhiliao_ll:
                showToastShort("治疗");
                yiZhuLv(Constant.TYPE_YZ_ZHILIAO);
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //辅助治疗
            case R.id.yi_zhu_xuan_xiang_zhu_huli_zhiliao_ll:
                showToastShort("护理");
                yiZhuLv(Constant.TYPE_YZ_HULI);
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //辅助治疗
            case R.id.yi_zhu_xuan_xiang_zhu_shanshi_zhiliao_ll:
                showToastShort("膳食");
                yiZhuLv(Constant.TYPE_YZ_SHANSHI);
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;

            //皮试
            case R.id.yi_zhu_xuan_xiang_zhu_pishi_ll:
                showToastShort("皮试");
                yiZhuLv(Constant.TYPE_YZ_PISHI);
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //皮试
            case R.id.yi_zhu_xuan_xiang_zhu_ruhu_ll:
                showToastShort("入壶");
                yiZhuLv(Constant.TYPE_YZ_RUHU);
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;

            //空
            case R.id.shai_xuan_include_kongbai_tv:
                mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
                break;
            //空
            case R.id.zhanweizhi:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (0 == requestCode) {
            if (0 == resultCode) {
                if (data != null) {
                    Bundle bundle2 = data.getBundleExtra("data");
                    final ArrayList<String> yichangs = bundle2.getStringArrayList("yichang");
                    if (yichangs != null && !yichangs.isEmpty()) {
                        final CheckBox yichangCB = yizhuHeduiLeixingLvItemAdapter.getYichangCB();
                        int pos = yizhuHeduiLeixingLvItemAdapter.getPos();
                        Map<Integer, TextView> cbMap = yizhuHeduiLeixingLvItemAdapter.getCbMap();
                        cbMap.remove(pos);
                        YiZhuBean.DataBean dbYiZhuDaTa = mDbYiZhuData.get(pos);
                        dbYiZhuDaTa.setYiChang(true);
                        yichangCB.setChecked(false);
                        final TextView yichangTV = yizhuHeduiLeixingLvItemAdapter.getYichangTV();
                        yichangTV.setText(yichangs.get(yichangs.size() - 1));
                        dbYiZhuDaTa.setYiChangXinXi(yichangs.get(yichangs.size() - 1));
                        yichangTV.setTextColor(Color.RED);
                        final ImageView yichangIMGV = yizhuHeduiLeixingLvItemAdapter.getYichangIMGV();
                        yichangIMGV.setVisibility(View.VISIBLE);
                        yichangIMGV.setImageResource(R.mipmap.icon_stop);
                        final TextView xuanzhongTV = yizhuHeduiLeixingLvItemAdapter.getXuanzhongTV();
                        xuanzhongTV.setVisibility(View.GONE);
                        mYzHdLxYichangBt.setEnabled(false);
                        yizhuHeduiLeixingLvItemAdapter.notifyDataSetChanged();

                        new DoctorOrderDaoImpl().saveExceptionForCheck(dbYiZhuDaTa.getId(), yichangs.get(yichangs.size() - 1));
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {

        if (mYiZhuXuanXiangIncludeLl.getVisibility() == View.VISIBLE) {
            mYiZhuXuanXiangIncludeLl.setVisibility(View.GONE);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void handleOnError() {
        super.handleOnError();
    }

    @Override
    public void handleOnError(String urlStr) {
        super.handleOnError(urlStr);
    }

    @Override
    public void handleSuccess(Object entity) {
        super.handleSuccess(entity);
        ActivityControl.killActivity(HD_YZActivity.class);
        killSelf();
    }

}
