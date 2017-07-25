package com.ge.med.mobile.nursing.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.dao.entity.AssessMeasureRecords;
import com.ge.med.mobile.nursing.dao.entity.AssessMeasureRecordsList;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.MeasureDefineBean;
import com.ge.med.mobile.nursing.dao.entity.XuanJiaoBean;
import com.ge.med.mobile.nursing.db.DBAssessMeasureRecords;
import com.ge.med.mobile.nursing.db.DBMeasureDefine;
import com.ge.med.mobile.nursing.db.DBUserList;
import com.ge.med.mobile.nursing.db.DBXuanJiao;
import com.ge.med.mobile.nursing.db.DBXuanJiaoRecord;
import com.ge.med.mobile.nursing.forjson.NetworkForAssessMeasureRecords;
import com.ge.med.mobile.nursing.forjson.NetworkForXuanJiao;
import com.ge.med.mobile.nursing.forjson.entity.AssessRecordBean;
import com.ge.med.mobile.nursing.ui.activity.ChangGuiHuLiActivity;
import com.ge.med.mobile.nursing.ui.activity.PG_ZJMActivity;
import com.ge.med.mobile.nursing.ui.adapter.HouJiCuoShiDaAnLvItemAdapter;
import com.ge.med.mobile.nursing.ui.adapter.HouJiCuoShiZjmLvItemAdapter;
import com.ge.med.mobile.nursing.ui.adapter.XuanJiaoZjmLvItemAdapter;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * 宣教一级、二级菜单，预防措施
 */
public class XuanJiaoFragment extends BaseFragment {
    private ListView mPingGuZjmLv;
    private TextView mBiaotiTv;
    private XuanJiaoZjmLvItemAdapter xuanJiaoZjmLvItemAdapter;
    private Bundle mBundle;
    private List<XuanJiaoBean.DataBean> mList = new ArrayList<>();
    private String wardName;
    private HuanZheLieBiaoBean.DataBean mHuanZheLieBiaoBean;
    private PG_ZJMActivity activity;
    private Button mYzZxZhuSheXiangQingBt;
    private LinearLayout mBtLl;
    private Button mYzHdRenFanhuiBt;
    private Button mYzShuxueWanchengBt;
    // 每个患者宣教记录的edurecordid
    private String edurecordid;


    @Override
    public int setRootView() {
        return R.layout.fragment_pg__zym;
    }

    @Override
    public void initViews() {
        mBiaotiTv = (TextView) mRootView.findViewById(R.id.biaoti_tv);
        mYzZxZhuSheXiangQingBt = (Button) mRootView.findViewById(R.id.yz_zx_zhu_she_xiang_qing_bt);
        mPingGuZjmLv = (ListView) mRootView.findViewById(R.id.ping_gu_zjm_lv);

        mBtLl = (LinearLayout) mRootView.findViewById(R.id.bt_ll);
        mYzHdRenFanhuiBt = (Button) mRootView.findViewById(R.id.yz_hd_ren_fanhui_bt);
        mYzShuxueWanchengBt = (Button) mRootView.findViewById(R.id.yz_shuxue_wancheng_bt);

        mBiaotiTv.setVisibility(View.VISIBLE);
        activity = (PG_ZJMActivity) this.getActivity();
        mBundle = activity.getmBundle();
        wardName = activity.getWardName();
        mHuanZheLieBiaoBean = activity.getmSelectedHZ();
        String fragmentType = mBundle.getString(Constant.BUNDLE_KEY_FRAGMENT_TYPE);
        MeasureDefineBean.DataBean.MeasureTopicDefineListBean measureTopicDefineListBean
                = (MeasureDefineBean.DataBean.MeasureTopicDefineListBean) mBundle.getSerializable(Constant.BUNDLE_KEY_TOPIC_DEFINE);
        AssessMeasureRecordsList assessMeasureRecords = (AssessMeasureRecordsList) mBundle.getSerializable(Constant.BUNDLE_KEY_CUOSHI_TOPIC_DEFINE);
        // 获得edurecordid
        edurecordid = mBundle.getString("edurecordid");

        if (Constant.BUNDLE_KEY_ACTIVITY_TYPE_XUANJIAO_DEFINE.equals(fragmentType)) {
            // 宣教子条目
            childChuLi();
        } else if (Constant.BUNDLE_KEY_ACTIVITY_TYPE_HOUJI_CUOSHI.equals(fragmentType)) {
            if (measureTopicDefineListBean == null) {
                // 预防措施
                houJiCuoShiChuLi();
            } else {
                if (assessMeasureRecords == null) {
                    // 预防措施子项
                    houJiCuoShiXuanXiang(measureTopicDefineListBean);
                } else {
                    caoZuoJiLi(measureTopicDefineListBean, assessMeasureRecords);
                }
            }
        } else {
            // 宣教
            pingGuChuLi();
        }
    }

    //查看操作记录处理
    private void caoZuoJiLi(final MeasureDefineBean.DataBean.MeasureTopicDefineListBean measureTopicDefineListBean, AssessMeasureRecordsList assessMeasureRecordsBean) {
        mBtLl.setVisibility(View.VISIBLE);
        mYzShuxueWanchengBt.setVisibility(View.GONE);
        final AssessRecordBean mAssess = (AssessRecordBean) mBundle.getSerializable(Constant.BUNDLE_KEY_ASSESS);
        AssessDefine.DataBean mAssessDefine = (AssessDefine.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE);
        mBiaotiTv.setText(mAssessDefine.getName() + "/" + measureTopicDefineListBean.getMeasureTopicValue());
        List<MeasureDefineBean.DataBean.MeasureTopicDefineListBean.MeasureAnswerDefineListBean> measureAnswerDefineList
                = measureTopicDefineListBean.getMeasureAnswerDefineList();
        List<MeasureDefineBean.DataBean.MeasureTopicDefineListBean.MeasureAnswerDefineListBean> measureAnswerDefineList1 = new ArrayList<>();
        String userId = null;
        String time = null;
        List<AssessMeasureRecords.MeasureTopicRecordListBean.MeasureAnswerRecordListBean> measureAnswerRecordList = new ArrayList<>();
        if (assessMeasureRecordsBean != null) {
            List<AssessMeasureRecords> assessMeasureRecordsList = assessMeasureRecordsBean.getAssessMeasureRecordsList();
            if (assessMeasureRecordsList != null) {
                for (AssessMeasureRecords assessMeasureRecords : assessMeasureRecordsList) {
                    if (assessMeasureRecords != null && assessMeasureRecords.getMeasureTopicRecordList() != null
                            && assessMeasureRecords.getMeasureTopicRecordList().size() > 0) {
                        for (AssessMeasureRecords.MeasureTopicRecordListBean measureTopicRecordListBean : assessMeasureRecords.getMeasureTopicRecordList()) {
                            if (measureTopicRecordListBean != null && measureTopicDefineListBean != null) {
                                if (measureTopicDefineListBean.getId() == measureTopicRecordListBean.getMeasureTopicDefineId()) {
                                    measureAnswerRecordList = measureTopicRecordListBean.getMeasureAnswerRecordList();
                                    userId = assessMeasureRecords.getUserId();
                                    time = assessMeasureRecords.getTime() + "";
                                }
                            }
                        }
                    }
                }
            }
        }
        if (measureAnswerRecordList == null || measureAnswerRecordList.size() <= 0) {
            showToastShort("该措施没有操作记录!");
            activity.killSelf();
        } else {
            for (MeasureDefineBean.DataBean.MeasureTopicDefineListBean.MeasureAnswerDefineListBean measureAnswerDefineListBean : measureAnswerDefineList) {
                for (AssessMeasureRecords.MeasureTopicRecordListBean.MeasureAnswerRecordListBean measureAnswerRecordListBean : measureAnswerRecordList) {
                    if (measureAnswerRecordListBean.getMeasureAnswerDefineId() == measureAnswerDefineListBean.getId()) {
                        measureAnswerDefineList1.add(measureAnswerDefineListBean);
                    }
                }
            }
        }
        String name = "";
        if (userId != null) {
            DBUserList first = DataSupport.where("zid = ?", userId).findFirst(DBUserList.class);
            name = first.getName() + "";
        }

        HouJiCuoShiDaAnLvItemAdapter houJiCuoShiDaAnLvItemAdapter = new HouJiCuoShiDaAnLvItemAdapter(mActivitySelf, measureAnswerDefineList1, true, name, time);
        mYzZxZhuSheXiangQingBt.setVisibility(View.GONE);
        mPingGuZjmLv.setAdapter(houJiCuoShiDaAnLvItemAdapter);
        mYzHdRenFanhuiBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.killSelf();
            }
        });
    }

    //后继措施选项处理
    private void houJiCuoShiXuanXiang(final MeasureDefineBean.DataBean.MeasureTopicDefineListBean measureTopicDefineListBean) {
        mBtLl.setVisibility(View.VISIBLE);
        final AssessRecordBean mAssess = (AssessRecordBean) mBundle.getSerializable(Constant.BUNDLE_KEY_ASSESS);
        AssessDefine.DataBean mAssessDefine = (AssessDefine.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE);
        mBiaotiTv.setText(mAssessDefine.getName() + "/" + measureTopicDefineListBean.getMeasureTopicValue());
        final List<MeasureDefineBean.DataBean.MeasureTopicDefineListBean.MeasureAnswerDefineListBean> measureAnswerDefineList
                = measureTopicDefineListBean.getMeasureAnswerDefineList();
        final HouJiCuoShiDaAnLvItemAdapter houJiCuoShiDaAnLvItemAdapter = new HouJiCuoShiDaAnLvItemAdapter(mActivitySelf, measureAnswerDefineList, false, null, null);
        mYzZxZhuSheXiangQingBt.setVisibility(View.VISIBLE);
        mPingGuZjmLv.setAdapter(houJiCuoShiDaAnLvItemAdapter);
        // 返回
        mYzHdRenFanhuiBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.killSelf();
            }
        });
        // 保存
        mYzShuxueWanchengBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<Integer, Boolean> booleanMap = houJiCuoShiDaAnLvItemAdapter.getBooleanMap();
                String measureDefineId = null;
                int id = 0;
                if (measureTopicDefineListBean != null) {
                    measureDefineId = measureTopicDefineListBean.getMeasureDefineId();
                    id = measureTopicDefineListBean.getId();
                }
                List<DBAssessMeasureRecords> dbAssessMeasureRecordses = DataSupport.where("patientId = ? and assessMeasureDefineId = ? and assessRecordId = ?"
                        , mHuanZheLieBiaoBean.getPatientid(), measureDefineId, mAssess.getId() + "")
                        .find(DBAssessMeasureRecords.class);
                AssessMeasureRecords assessMeasureRecords;
                if (dbAssessMeasureRecordses == null || dbAssessMeasureRecordses.size() <= 0) {
                    assessMeasureRecords = new AssessMeasureRecords();
                } else {
                    assessMeasureRecords = DBAssessMeasureRecords.convertBean(dbAssessMeasureRecordses.get(0));
                }
                if (mAssess != null) {
                    assessMeasureRecords.setAssessRecordId(mAssess.getId());
                    if (mAssess.getAssessMeasureRecord() != null) {
                        assessMeasureRecords.setId(mAssess.getAssessMeasureRecord().getId());
                    }
                }
                if (mHuanZheLieBiaoBean != null) {
                    assessMeasureRecords.setPatientId(mHuanZheLieBiaoBean.getPatientid());
                }
                assessMeasureRecords.setUserId(activity.getUserid());
                assessMeasureRecords.setTime(System.currentTimeMillis());
                if (assessMeasureRecords.getMeasureTopicRecordList() == null) {
                    assessMeasureRecords.setMeasureTopicRecordList(new ArrayList<AssessMeasureRecords.MeasureTopicRecordListBean>());
                }
                AssessMeasureRecords.MeasureTopicRecordListBean measureTopicRecordListBean = null;
                for (AssessMeasureRecords.MeasureTopicRecordListBean measureTopicRecordListBean1
                        : assessMeasureRecords.getMeasureTopicRecordList()) {
                    if (measureTopicRecordListBean1.getMeasureTopicDefineId() == id) {
                        measureTopicRecordListBean = measureTopicRecordListBean1;
                    }
                }
                if (measureTopicRecordListBean == null) {
                    measureTopicRecordListBean = new AssessMeasureRecords.MeasureTopicRecordListBean();
                    assessMeasureRecords.getMeasureTopicRecordList().add(measureTopicRecordListBean);
                }
                measureTopicRecordListBean.setMeasureTopicDefineId(id);
                measureTopicRecordListBean.setMeasureAnswerRecordList(new ArrayList<AssessMeasureRecords.MeasureTopicRecordListBean.MeasureAnswerRecordListBean>());
                AssessMeasureRecords.MeasureTopicRecordListBean.MeasureAnswerRecordListBean measureAnswerRecordListBean;
                MeasureDefineBean.DataBean.MeasureTopicDefineListBean.MeasureAnswerDefineListBean measureAnswerDefineListBean;
                if (booleanMap != null) {
                    Iterator<Integer> iterator = booleanMap.keySet().iterator();
                    Integer next;
                    while (iterator.hasNext()) {
                        next = iterator.next();
                        if (booleanMap.get(next)) {
                            measureAnswerDefineListBean = measureAnswerDefineList.get(next);
                            measureAnswerRecordListBean = new AssessMeasureRecords.MeasureTopicRecordListBean.MeasureAnswerRecordListBean();
                            measureAnswerRecordListBean.setMeasureAnswerDefineId(measureAnswerDefineListBean.getId());
                            measureTopicRecordListBean.getMeasureAnswerRecordList().add(measureAnswerRecordListBean);
                        }
                    }
                }
                DBAssessMeasureRecords dbAssessMeasureRecords = DBAssessMeasureRecords.convertBean(assessMeasureRecords);
                dbAssessMeasureRecords.setIsModified(true + "");
                boolean save = dbAssessMeasureRecords.save();
                List<AssessMeasureRecords> assessMeasureRecordsList = new ArrayList();
                assessMeasureRecordsList.add(assessMeasureRecords);
                NetworkForAssessMeasureRecords.submitAssessMeasureRecord(assessMeasureRecordsList, false + "");
                activity.killSelf();
            }
        });
        String measureDefineId = null;
        if (measureTopicDefineListBean != null) {
            measureDefineId = measureTopicDefineListBean.getMeasureDefineId();
        }
        List<DBAssessMeasureRecords> dbAssessMeasureRecordses = null;
        dbAssessMeasureRecordses = DataSupport.where("patientId = ? and assessMeasureDefineId = ? and assessRecordId = ?"
                , mHuanZheLieBiaoBean.getPatientid(), measureDefineId, mAssess.getId() + "")
                .find(DBAssessMeasureRecords.class);
        if (dbAssessMeasureRecordses != null) {
            if (dbAssessMeasureRecordses != null) {
                List<AssessMeasureRecords> assessMeasureRecordsList = new ArrayList<AssessMeasureRecords>();
                for (DBAssessMeasureRecords dbAssessMeasureRecordsBean : dbAssessMeasureRecordses) {
                    assessMeasureRecordsList.add(DBAssessMeasureRecords.convertBean(dbAssessMeasureRecordsBean));
                }
                List<AssessMeasureRecords.MeasureTopicRecordListBean.MeasureAnswerRecordListBean> measureAnswerRecordList = new ArrayList<>();
                if (assessMeasureRecordsList != null) {
                    for (AssessMeasureRecords assessMeasureRecords : assessMeasureRecordsList) {
                        if (assessMeasureRecords != null && assessMeasureRecords.getMeasureTopicRecordList() != null
                                && assessMeasureRecords.getMeasureTopicRecordList().size() > 0) {
                            for (AssessMeasureRecords.MeasureTopicRecordListBean measureTopicRecordListBean : assessMeasureRecords.getMeasureTopicRecordList()) {
                                if (measureTopicRecordListBean != null && measureTopicDefineListBean != null) {
                                    if (measureTopicDefineListBean.getId() == measureTopicRecordListBean.getMeasureTopicDefineId()) {
                                        measureAnswerRecordList.addAll(measureTopicRecordListBean.getMeasureAnswerRecordList());
                                    }
                                }
                            }
                        }
                    }
                }
                houJiCuoShiDaAnLvItemAdapter.setMeasureAnswerRecordList(measureAnswerRecordList);
            }
        }
        // 查看操作记录
        mYzZxZhuSheXiangQingBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String measureDefineId = null;
                if (measureTopicDefineListBean != null) {
                    measureDefineId = measureTopicDefineListBean.getMeasureDefineId();
                }
                List<DBAssessMeasureRecords> dbAssessMeasureRecordses = DataSupport.where("patientId = ? and assessMeasureDefineId = ? and assessRecordId = ?"
                        , mHuanZheLieBiaoBean.getPatientid(), measureDefineId, mAssess.getId() + "")
                        .find(DBAssessMeasureRecords.class);
                if (dbAssessMeasureRecordses != null) {
                    List<AssessMeasureRecords> assessMeasureRecordsList = new ArrayList<AssessMeasureRecords>();
                    for (DBAssessMeasureRecords dbAssessMeasureRecordsBean : dbAssessMeasureRecordses) {
                        assessMeasureRecordsList.add(DBAssessMeasureRecords.convertBean(dbAssessMeasureRecordsBean));
                    }
                    AssessMeasureRecordsList assessMeasureRecords = new AssessMeasureRecordsList();
                    assessMeasureRecords.setAssessMeasureRecordsList(assessMeasureRecordsList);
                    mBundle.putSerializable(Constant.BUNDLE_KEY_CUOSHI_TOPIC_DEFINE, assessMeasureRecords);
                    goToActivity(PG_ZJMActivity.class, mBundle);

                } else {
                    showToastShort("该措施没有操作记录!");
                }
            }
        });
    }

    //后继措施主页面处理
    private void houJiCuoShiChuLi() {
        AssessDefine.DataBean mAssessDefine = (AssessDefine.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE);
        AssessRecordBean mAssess = (AssessRecordBean) mBundle.getSerializable(Constant.BUNDLE_KEY_ASSESS);
        String assessDefineCode = mAssessDefine.getAssessCode();
        if (mHuanZheLieBiaoBean != null && mAssess != null) {
            NetworkForAssessMeasureRecords.callAssessMeasureRecord(mHuanZheLieBiaoBean.getPatientid(), mAssess.getId() + "");
        }
        if (mAssessDefine != null) {
            mBiaotiTv.setText(mAssessDefine.getName());
        }
        LogUtil.d("后期措施 : assessDefineCode:" + assessDefineCode);
        DBMeasureDefine first = DataSupport.where("assessDefineCode = ?", assessDefineCode).findFirst(DBMeasureDefine.class);
        if (first == null) {
            showToastShort("未找到该评估措施定义,请更新定义数据!");
            activity.killSelf();
        } else {
            MeasureDefineBean.DataBean dataBean = DBMeasureDefine.convertBean(first);
            final List<MeasureDefineBean.DataBean.MeasureTopicDefineListBean> dataBeanList = dataBean.getMeasureTopicDefineList();
            HouJiCuoShiZjmLvItemAdapter houJiCuoShiZjmLvItemAdapter
                    = new HouJiCuoShiZjmLvItemAdapter(mActivitySelf, dataBeanList);
            mPingGuZjmLv.setAdapter(houJiCuoShiZjmLvItemAdapter);
            mPingGuZjmLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mBundle.putSerializable(Constant.BUNDLE_KEY_TOPIC_DEFINE, dataBeanList.get(position));
                    goToActivity(PG_ZJMActivity.class, mBundle);
                }
            });
        }
    }

    //宣教子条目
    private void childChuLi() {
        mBundle.putString(Constant.XUANJIAO_CHILD_KEY, Constant.XUANJIAO_CHILD_VALUE);
        XuanJiaoBean.DataBean dataBean = (XuanJiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_XUANJIAO_DEFINE);
        if (dataBean != null) {
            if (wardName.length() > 6) {
                wardName = wardName.substring(0, 5) + "...";
            }
            mBiaotiTv.setText(wardName + "/" + dataBean.getTitle());
            mList = dataBean.getChild();
            xuanJiaoZjmLvItemAdapter = new XuanJiaoZjmLvItemAdapter(mActivitySelf, mList, false, mHuanZheLieBiaoBean.getPatientid() + "");
            mPingGuZjmLv.setAdapter(xuanJiaoZjmLvItemAdapter);
            mPingGuZjmLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mBundle == null) mBundle = new Bundle();
                    if (mList != null && mList.size() > position) {
                        if (mList.get(position).getChild() == null || mList.get(position).getChild().size() <= 0) {
                            mBundle.putSerializable(Constant.BUNDLE_KEY_XUANJIAO_DEFINE, mList.get(position));
                            mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_XIANGQING);
                            // 从数据库查询edurecordid
                            List<DBXuanJiaoRecord> eduRecords = DataSupport.select("edurecordid").where("patientid = ?", mHuanZheLieBiaoBean.getPatientid()).find(DBXuanJiaoRecord.class);
                            if (eduRecords != null && eduRecords.size() > 0) {
                                String edurecordid = eduRecords.get(0).getEdurecordid();
                                mBundle.putString("edurecordid", edurecordid);
                            } else {
                                mBundle.putString("edurecordid", edurecordid);
                            }
                            // 宣教二级菜单---->宣教方式及结果
                            goToActivity(ChangGuiHuLiActivity.class, mBundle);
                        } else {
                            mBundle.putSerializable(Constant.BUNDLE_KEY_XUANJIAO_DEFINE, mList.get(position));
                            mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_XUANJIAO);
                            mBundle.putString(Constant.BUNDLE_KEY_FRAGMENT_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_XUANJIAO_DEFINE);
                            goToActivity(PG_ZJMActivity.class, mBundle);
                        }
                    }
                }
            });
        }
    }

    //宣教主界面处理
    private void pingGuChuLi() {
        final String patientId = mHuanZheLieBiaoBean.getPatientid();
        NetworkForXuanJiao.callXuanJiaoRecord(patientId);
        mList.clear();
        DBXuanJiao mDBXuanJiao = DataSupport.findFirst(DBXuanJiao.class);
        if (mDBXuanJiao != null) {
            XuanJiaoBean xuanJiaoBean = JSON.parseObject(mDBXuanJiao.getJsonString(), XuanJiaoBean.class);
            if (xuanJiaoBean != null) {
                mList = xuanJiaoBean.getData();
            }
        }

        if (wardName.length() > 6) {
            wardName = wardName.substring(0, 5) + "...";
        }
        mBiaotiTv.setText(wardName + "/患者宣教");
        xuanJiaoZjmLvItemAdapter = new XuanJiaoZjmLvItemAdapter(mActivitySelf, mList, true, mHuanZheLieBiaoBean.getPatientid() + "");
        mPingGuZjmLv.setAdapter(xuanJiaoZjmLvItemAdapter);
        mPingGuZjmLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mBundle == null) mBundle = new Bundle();
                if (mList != null && mList.size() > position) {
                    // 一级菜单进入详情
                    if (mList.get(position).getChild() == null || mList.get(position).getChild().size() <= 0) {
                        mBundle.putSerializable(Constant.BUNDLE_KEY_XUANJIAO_DEFINE, mList.get(position));
                        mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_XIANGQING);
                        //goToActivity(PG_ZJMActivity.class, mBundle);
                        goToActivity(ChangGuiHuLiActivity.class, mBundle);
                    } else {
                        // 一级菜单进入二级
                        mBundle.putSerializable(Constant.BUNDLE_KEY_XUANJIAO_DEFINE, mList.get(position));
                        mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_XUANJIAO);
                        mBundle.putString(Constant.BUNDLE_KEY_FRAGMENT_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_XUANJIAO_DEFINE);
                        goToActivity(PG_ZJMActivity.class, mBundle);
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("educationFragment", "重新显示");
        if (xuanJiaoZjmLvItemAdapter != null) {
            xuanJiaoZjmLvItemAdapter.notifyDataSetChanged();
        }
        // 重新获取宣教数据
        String patientId = mHuanZheLieBiaoBean.getPatientid();
        NetworkForXuanJiao.callXuanJiaoRecord(patientId);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void init() {

    }

    public void setBundle(Bundle bundle) {
        mBundle = bundle;
    }

    @Override
    public boolean useTitleBar() {
        return false;
    }


}
