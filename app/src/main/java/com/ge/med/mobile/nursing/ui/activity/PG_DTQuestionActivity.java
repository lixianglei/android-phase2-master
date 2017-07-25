package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sj.library.control.ActivityControl;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.db.DBAssessDefine;
import com.ge.med.mobile.nursing.forjson.NetworkForAssessment;
import com.ge.med.mobile.nursing.forjson.entity.AssessRecordBean;
import com.ge.med.mobile.nursing.service.SyncService;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.AssessTopicAnswerItemAdapter;
import com.ge.med.mobile.nursing.ui.component.DateTimeControl;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.TengTongChuLi;
import com.ge.med.mobile.nursing.ui.component.TengTongRenTiTuBei;
import com.ge.med.mobile.nursing.ui.component.TengTongRenTiTuZheng;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.component.VitalPainKeyboard;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 评估答题界面
 */
public class PG_DTQuestionActivity extends MyBaseActivity implements View.OnClickListener {

    private ImageButton btpinggu_back_bt;
    private ImageButton btpinggu_next_bt;
    private Button btpinggu_baocun_bt;
    private TextView tvhz_pg_dt_topic_name;
    private TextView mHzPgDtTopicName1;
    private TextView mHzPgDtTopicName0;

    private TextView tv_footer_pg;
    private EditText ethz_pg_dt_topic_input;
    private ListView mPgItemAnswer;
    private AssessTopicAnswerItemAdapter topicAnswerItemAdapter;
    private AssessDefine.DataBean mAssessDefine;
    private AssessRecordBean mAssess;
    private int currentTopicIndex = 0;
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;
    private TitleBar mTitleBar;
    private PatientInfoPannel mPatientPanel;
    private Bundle mBundle;
    private Intent intent;
    private List<AssessDefine.DataBean.AssessTopicDefineListBean> mTopicDefineList;
    private List<AssessDefine.DataBean.AssessTopicDefineListBean> mTengTongTiList;
    private List<Integer> mCurrentDefineList;
    private DateTimeControl dateTimeControl;
    private LinearLayout mAnswerOptionsLl;
    private LayoutInflater layoutInflater;
    private TengTongRenTiTuZheng mTengTongRenTiTuZheng;
    private TengTongRenTiTuBei mTengTongRenTiTuBei;
    private List<Integer> imageViewBeiIDs;
    private List<Integer> imageViewZhengIDs;
    private LinearLayout mAnswerOptionsRentiLl;
    private Map<String, AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean> mTengTongBuWeiCode;
    private AssessDefine.DataBean.AssessTopicDefineListBean mTengTongJiBieTopicDefineBean;
    private int mTengTongCode = -1;
    private List<String> mTengTongBuWei;
    private String mBuWeiName;
    private VitalPainKeyboard vitalPainKeyboard;
    private Map<Integer, String> mTengTongBuWeiName;
    private TengTongChuLi mTengTongChuLi;

    // 压疮评估用到的解释说明
    private ImageView img_explain;
    // 问题总数
    private int questionNum;

    @Override
    public int setRootView() {
        return R.layout.activity_pg__dtquestion;
    }

    private Bundle getBundle(Intent intent) {
        Bundle bundle = intent.getBundleExtra(Constant.GLOBAL_KEY_DATA);
        if (bundle == null) bundle = new Bundle();
        return bundle;
    }

    //   Access to patient information （获取患者信息）
    private HuanZheLieBiaoBean.DataBean getPatient() {
        HuanZheLieBiaoBean.DataBean retval = null;
        if (mBundle != null) {
            retval = (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
        }
        return retval;
    }

    //  For assessment of definition  （获取评估定义）
    private AssessDefine.DataBean getAssessDefine() {
        AssessDefine.DataBean retval = null;
        if (mBundle != null) {
            retval = (AssessDefine.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE);
        }
        return retval;
    }

    //  For assessment record  （获取评估记录）
    private AssessRecordBean getAssess() {
        AssessRecordBean retval = null;
        if (mBundle != null) {
            retval = (AssessRecordBean) mBundle.getSerializable(Constant.BUNDLE_KEY_ASSESS);
        }
        return retval;
    }

    @Override
    public void initViews() {
        intent = getIntent();
        mTengTongTiList = new ArrayList<>();
        mBundle = getBundle(intent);
        mSelectedHZ = getPatient();
        mAssessDefine = getAssessDefine();
        mTopicDefineList = getmTopicDefineList();
        mAssess = getAssess();
        mCurrentDefineList = initShowTopicDefine();
        dateTimeControl = new DateTimeControl(mActivitySelf);
        dateTimeControl.setVisible(false);
        mTitleBar = new TitleBar(this, mSelectedHZ);
        mTitleBar.setShowConfirmDialog(true);
        mPatientPanel = new PatientInfoPannel(this, new AssessDaoImpl().findAllRiskDefine(), mSelectedHZ);
        mPatientPanel.showHideTipLayout(false);
        tv_footer_pg = (TextView) findViewById(R.id.tv_footer_pg);
        mAnswerOptionsLl = (LinearLayout) findViewById(R.id.answer_options_ll);
        btpinggu_back_bt = (ImageButton) findViewById(R.id.pinggu_back_bt);
        btpinggu_next_bt = (ImageButton) findViewById(R.id.pinggu_next_bt);
        btpinggu_baocun_bt = (Button) findViewById(R.id.pinggu_baocun_bt);
        tvhz_pg_dt_topic_name = (TextView) findViewById(R.id.hz_pg_dt_topic_name);
        mHzPgDtTopicName1 = (TextView) findViewById(R.id.hz_pg_dt_topic_name_1);
        mHzPgDtTopicName0 = (TextView) findViewById(R.id.hz_pg_dt_topic_name_0);
        ethz_pg_dt_topic_input = (EditText) findViewById(R.id.hz_pg_dt_topic_input);
        mAnswerOptionsRentiLl = (LinearLayout) findViewById(R.id.answer_options_renti_ll);
        mPgItemAnswer = (ListView) findViewById(R.id.pg_lv_topic);
        img_explain = (ImageView) findViewById(R.id.img_explain);
        layoutInflater = LayoutInflater.from(mActivitySelf);
        loadAssessData();
        btpinggu_back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (checkInput()) {
                saveTime();
                setBuWeiName();
                saveTengTongJiBie();
                currentTopicIndex--;
                gotoTopic();
//                }
            }
        });
        btpinggu_next_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTengtong();
                if (checkInput()) {
                    gotoNextTopic();
                }
            }
        });
        btpinggu_baocun_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTengtong();
                if (checkInput()) {
                    if (currentTopicIndex >= mCurrentDefineList.size() - 1 && !Constant.TOPIC_CODE_HUMAN_BOTY
                            .equals(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getTopicCode())) {
                        //疼痛check
                        boolean result = checkTengTong();
                        if (!result) {
                            showToastShort("请选择疼痛级别！");
                            return;
                        }
                        btpinggu_baocun_bt.setEnabled(false);
                        //saveAssess(true);
                        submitAssess();
                    } else {
                        saveAssess(false);
                        gotoNextTopic();
                    }
                }
            }
        });
        // 获得题目总数
        String assessDefineId = String.valueOf(mAssess.getAssessDefineId());
        List<DBAssessDefine> assessDefineList = DataSupport.select("topictotalno").where("assessdefineid = ?", assessDefineId).find(DBAssessDefine.class);
        if (assessDefineList != null && assessDefineList.size() > 0) {
            questionNum = assessDefineList.get(0).getTopictotalno();
        }
        setProgress();
    }

    private boolean checkTengTong() {
        if (Constant.ASSESS_CODE_PAIN_ASSESS.equals(mAssessDefine.getAssessCode())) {
            if (mAssess.getAssessTopicRecordList() != null) {//清空疼痛数据，避免重复提交
                mAssess.getAssessTopicRecordList().clear();
            }
            convertTengTongAssess();//转换为评估数据
            List<AssessRecordBean.AssessTopicRecordListBean> assessTopicRecordList = mAssess.getAssessTopicRecordList();
            if (assessTopicRecordList != null) {
                for (AssessRecordBean.AssessTopicRecordListBean topicRecordBean : assessTopicRecordList) {
                    List<AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean> assessAnswerRecordList = topicRecordBean.getAssessAnswerRecordList();
                    if (assessAnswerRecordList != null) {
                        for (AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean assessAnswerRecordBean : assessAnswerRecordList) {
                            Integer answerDefineId = assessAnswerRecordBean.getAnswerDefineId();
                            if (answerDefineId == null ||
                                    (answerDefineId != null && (answerDefineId.intValue() == -1 || answerDefineId.intValue() == 0))) {
                                if (mAssess.getAssessTopicRecordList() != null) {//清空疼痛数据，避免重复提交
                                    mAssess.getAssessTopicRecordList().clear();
                                }
                                return false;
                            }
                        }
                    }
                }
            }

            if (mAssess.getAssessTopicRecordList() != null) {//清空疼痛数据，避免重复提交
                mAssess.getAssessTopicRecordList().clear();
            }

        }
        return true;
    }

    private void isTengtong() {
        saveTime();
        addTengTongTopic();
        setBuWeiName();
        saveTengTongJiBie();
    }

    private void saveTengTongJiBie() {
        try {
            if (mTopicDefineList.size() > 0 && Constant.TOPIC_CODE_PAINT_LEVEL.equals(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getTopicCode())) {
                if (mAssess != null) {
                    if (mAssess.getAssessRecorMapTengTong() == null) {
                        mAssess.setAssessRecorMapTengTong(new HashMap<Integer, Map<Integer, AssessRecordBean.AssessTopicRecordListBean>>());
                    }
                    if (mAssess.getAssessRecorMapTengTong().get(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getId()) == null) {
                        mAssess.getAssessRecorMapTengTong()
                                .put(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getId()
                                        , new HashMap<Integer, AssessRecordBean.AssessTopicRecordListBean>());
                    }
                    AssessRecordBean.AssessTopicRecordListBean assessTopicRecordListBean = new AssessRecordBean.AssessTopicRecordListBean();
                    assessTopicRecordListBean.setTopicDefineId(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getId());
                    assessTopicRecordListBean.setParentAnswerDefineId(mTengTongBuWeiCode.get(mTengTongBuWei.get(currentTopicIndex - mTengTongCode - 1)).getId());
                    List<AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean> answerRecordList = new ArrayList<>();
                    AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean assessAnswerRecordListBean = new AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean();
                    assessAnswerRecordListBean.setAnswerDefineId(vitalPainKeyboard
                            .getTengTongJiBieId());
                    answerRecordList.add(assessAnswerRecordListBean);
                    assessTopicRecordListBean.setAssessAnswerRecordList(answerRecordList);
                    mAssess.getAssessRecorMapTengTong()
                            .get(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getId())
                            .put(mTengTongBuWeiCode.get(mTengTongBuWei.get(currentTopicIndex - mTengTongCode - 1)).getId(), assessTopicRecordListBean);
                }
            }
        } catch (Exception e) {
            LogUtil.e(e.getMessage() + "");
        }
    }

    private void setBuWeiName() {
        if (mTopicDefineList.size() > 0 && Constant.TOPIC_CODE_HUMAN_BOTY
                .equals(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getTopicCode())) {
            if (mTengTongCode >= 0 && mTengTongJiBieTopicDefineBean != null) {
                mTengTongJiBieTopicDefineBean.setInitType(0);
            }
        }
    }

    private void addTengTongTopic() {
        if (mTopicDefineList.size() > 0 && Constant.TOPIC_CODE_HUMAN_BOTY
                .equals(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getTopicCode())) {
            if (mTengTongRenTiTuBei != null) {
                imageViewBeiIDs = mTengTongRenTiTuBei.getmImageViewList();
            }
            if (mTengTongChuLi == null) {
                mTengTongChuLi = new TengTongChuLi();
            }
            if (mTengTongRenTiTuZheng != null) {
                imageViewZhengIDs = mTengTongRenTiTuZheng.getmImageViewList();
            }
            mTengTongBuWei = mTengTongChuLi.getTengTongBuWei(mTengTongRenTiTuBei, mTengTongRenTiTuZheng);
            addTengTongTopic(mTengTongBuWei);
            addTopicMap(mTengTongBuWei);
        }
    }

    private void addTopicMap(List<String> mTengTongBuWei) {
        if (mTengTongBuWei != null && mAssess != null) {
            if (mAssess.getAssessRecorMap() == null) {
                mAssess.setAssessRecorMap(new HashMap<Integer, List<AssessRecordBean.AssessTopicRecordListBean>>());
            }
            List<AssessRecordBean.AssessTopicRecordListBean> topicRecordListBeen;
            if (mAssess.getAssessRecorMap().get(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getId()) == null) {
                topicRecordListBeen = new ArrayList<>();
                AssessRecordBean.AssessTopicRecordListBean topicRecordBeen = new AssessRecordBean.AssessTopicRecordListBean();
                topicRecordBeen.setTopicDefineId(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getId());
                topicRecordBeen.setAnswerRecordListMap(new HashMap<Integer, AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean>());
                topicRecordListBeen.add(topicRecordBeen);
                mAssess.getAssessRecorMap().put(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getId(), topicRecordListBeen);
            }
            AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean answerRecord;
            for (String s : mTengTongBuWei) {
                if (mTengTongBuWeiCode.get(s) != null) {
                    answerRecord = new AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean();
                    answerRecord.setAnswerDefineId(mTengTongBuWeiCode.get(s).getId());
                    mAssess.getAssessRecorMap().get(mTopicDefineList
                            .get(mCurrentDefineList.get(currentTopicIndex)).getId()).get(0)
                            .getAnswerRecordListMap().put(mTengTongBuWeiCode.get(s).getId(), answerRecord);
                }
            }
        }
    }

    private void addTengTongTopic(List<String> mTengTongBuWei) {
        if (mTengTongCode >= 0 && mTengTongJiBieTopicDefineBean != null) {
            mTopicDefineList.removeAll(mTengTongTiList);
            mTopicDefineList.remove(mTengTongJiBieTopicDefineBean);
            mTengTongTiList.clear();
            mCurrentDefineList = initShowTopicDefine();
            mTengTongJiBieTopicDefineBean.setInitType(0);
            for (int i = 1; i <= mTengTongBuWei.size(); i++) {
                mTengTongTiList.add(mTengTongJiBieTopicDefineBean);
            }
            mTopicDefineList.addAll(mCurrentDefineList.get(currentTopicIndex) + 1, mTengTongTiList);
        }
    }

    private void saveTime() {
        if (mCurrentDefineList.size() > 0 && mTopicDefineList.size() > 0) {
            if (Constant.TOPIC_TYPE_TIME.equals(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getTopicType())) {
                String dateTime = dateTimeControl.getDateTime();
                dateTime.replace("/", "-");
                LogUtil.d("question dateTime:" + dateTime);
                AssessRecordBean.AssessTopicRecordListBean topicRecord = getTopicRecord(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)));
                if (topicRecord == null) {
                    topicRecord = new AssessRecordBean.AssessTopicRecordListBean();
                    topicRecord.setIsdeleted("0");
                    topicRecord.setAssessid(mAssess.getId());
                    topicRecord.setTopicDefineId(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getId());
                }
                if (topicRecord.getAssessAnswerRecordList() == null)
                    topicRecord.setAssessAnswerRecordList(new ArrayList<AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean>());
                if (topicRecord.getAnswerRecordListMap() == null) {
                    topicRecord.setAnswerRecordListMap(new HashMap<Integer, AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean>());
                }
                AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean assessAnswerRecordListBean
                        = new AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean();
                assessAnswerRecordListBean.setTimeValue(dateTime);
                topicRecord.getAssessAnswerRecordList().add(assessAnswerRecordListBean);
                topicRecord.getAnswerRecordListMap().put(null, assessAnswerRecordListBean);
                List<AssessRecordBean.AssessTopicRecordListBean> topicRecords = new ArrayList<>();
                topicRecords.add(topicRecord);

                mAssess.getAssessRecorMap().put(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getId(), topicRecords);
            }
        }
    }

    private void setRenTiTuZheng() {
        View view = layoutInflater.inflate(R.layout.ren_ti_pin_tu_zheng_include, null);
        mAnswerOptionsRentiLl.removeAllViewsInLayout();
        mAnswerOptionsRentiLl.addView(view);
        mTengTongRenTiTuZheng = new TengTongRenTiTuZheng(this, imageViewZhengIDs);
        ImageView mCheckBeimianImgv = (ImageView) this.findViewById(R.id.check_beimian_imgv);
        mHzPgDtTopicName1.setVisibility(View.VISIBLE);
        mHzPgDtTopicName1.setText("/选择疼痛部位(正)");
        mCheckBeimianImgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewZhengIDs = mTengTongRenTiTuZheng.getmImageViewList();
                setRenTiTuBei();
            }
        });

    }

    private void setTengTongKeyBoard() {
        View view = layoutInflater.inflate(R.layout.tengtong_keyboard_include, null);
        mAnswerOptionsRentiLl.removeAllViewsInLayout();
        mAnswerOptionsRentiLl.addView(view);
        vitalPainKeyboard = new VitalPainKeyboard(this, mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getAssessAnswerDefineList());
        if (mAssess != null && mAssess.getAssessRecorMapTengTong() != null && mCurrentDefineList != null
                && mCurrentDefineList.get(currentTopicIndex) != null && mTopicDefineList != null
                && mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)) != null
                && mAssess.getAssessRecorMapTengTong().get(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getId()) != null
                && mTengTongBuWeiCode != null && mTengTongBuWei != null && (currentTopicIndex - mTengTongCode - 1) < mTengTongBuWei.size()
                && mTengTongBuWei.get(currentTopicIndex - mTengTongCode - 1) != null) {
            AssessRecordBean.AssessTopicRecordListBean assessTopicRecordListBean = mAssess.getAssessRecorMapTengTong().get(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getId())
                    .get(mTengTongBuWeiCode.get(mTengTongBuWei.get(currentTopicIndex - mTengTongCode - 1)).getId());
            if (vitalPainKeyboard != null && assessTopicRecordListBean != null && assessTopicRecordListBean.getAssessAnswerRecordList().get(0) != null) {
                vitalPainKeyboard.setTengTongJiBie(assessTopicRecordListBean.getAssessAnswerRecordList().get(0).getAnswerDefineId());
            }
        }
    }

    private void setRenTiTuBei() {
        View view = layoutInflater.inflate(R.layout.ren_ti_pin_tu_bei_include, null);
        mAnswerOptionsRentiLl.removeAllViewsInLayout();
        mAnswerOptionsRentiLl.addView(view);
        mTengTongRenTiTuBei = new TengTongRenTiTuBei(this, imageViewBeiIDs);
        ImageView mCheckBeimianImgv = (ImageView) this.findViewById(R.id.check_zhengmian_imgv);
        mHzPgDtTopicName1.setVisibility(View.VISIBLE);
        mHzPgDtTopicName1.setText("/选择疼痛部位(背)");
        mCheckBeimianImgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewBeiIDs = mTengTongRenTiTuBei.getmImageViewList();
                setRenTiTuZheng();
            }
        });
    }

    // Check the answer （检测是否答题）
    private boolean checkInput() {
        boolean retval = false;
        if (!isTopicExistes(currentTopicIndex)) return retval;
        if (mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getRequested() == 0) {
            if (Constant.TOPIC_CODE_PAINT_LEVEL.equals(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getTopicCode())) {
                if (mAssess.getAssessRecorMapTengTong()
                        .get(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getId())
                        .get(mTengTongBuWeiCode.get(mTengTongBuWei.get(currentTopicIndex - mTengTongCode - 1)).getId())
                        .getAssessAnswerRecordList() != null) {
                    retval = true;
                }
            }
            AssessRecordBean.AssessTopicRecordListBean topic = getTopicRecord(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)));
            LogUtil.d("checkInput" + mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)));
            if (topic != null) {
                if (Constant.TOPIC_TYPE_INPUT.equals(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getTopicType())) {
                    LogUtil.d("checkInput" + mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getTopicType());
                    if (topic.getValue() != null && !(topic.getValue().trim().isEmpty())) {
                        retval = true;
                    }
                } else { // 单选或多选
//                    if (topic.getAssessAnswerRecordList() != null && topic.getAssessAnswerRecordList().size() > 0) {
                    if (topic.getAnswerRecordListMap() != null && topic.getAnswerRecordListMap().size() > 0) {
                        LogUtil.d("checkInput" + mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getTopicType());
                        retval = true;
                    }
                }
            }
            if (!retval) showToastShort("此题必答,请先答题！");
        } else {
            retval = true;
        }
        return retval;
    }

    private void submitAssess() {
        //mAssess.setCreationTime(null);
        if (SyncService.isConnected()) {
            saveAssess(true);
            if (convertAssess()) {
                if (mAssessDefine != null && mAssessDefine.getAssessCode() != null && mAssessDefine.getAssessCode().endsWith("RECORD")) {
                    mAssess.setStatus(Constant.ASSESS_STATUS_FINISHED);
                }
                NetworkForAssessment.submitSingleAssessment(this, mAssess);
            } else {
                showToastShort("还有必答题未答完！");
                btpinggu_baocun_bt.setEnabled(true);
            }
            /*LogUtil.d("Server is ready, try to submit assess!");
            convertAssess();
            NetworkForAssessment.submitSingleAssessment(this, mAssess);*/
        } else {
            btpinggu_baocun_bt.setEnabled(true);
            if (convertAssess()) {
                saveAssess(true);
                LogUtil.i("Can not reach server, save assess local only!");
                showToastLong("离线下无法查看评估结果！");
                ActivityControl.killActivity(HZActivity.class);
                ActivityControl.killActivity(PG_DTMainActivity.class);
                ActivityControl.killActivity(PG_ZJMActivity.class);
                gotoPatientPage(mBundle, 1);
            } else {
                showToastShort("还有必答题未答完！");
                btpinggu_baocun_bt.setEnabled(true);
            }
        }
    }

    private void convertmAssess() {
        if (mAssess == null) {
            return;
        }
        mAssess.setAssessRecorMap(new HashMap<Integer, List<AssessRecordBean.AssessTopicRecordListBean>>());
        mAssess.setAssessRecorMapTengTong(new HashMap<Integer, Map<Integer, AssessRecordBean.AssessTopicRecordListBean>>());
        List<AssessRecordBean.AssessTopicRecordListBean> topicRecordListBeens;
        List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean> answerDefineList;
        String answerDefineCode;
        Iterator<Integer> iterator;
        Integer next;
        Map<Integer, AssessRecordBean.AssessTopicRecordListBean> topicRecordListBeanMap = new HashMap<>();
        if (mAssess.getAssessTopicRecordList() != null && mAssess.getAssessTopicRecordList().size() > 0) {
            for (AssessRecordBean.AssessTopicRecordListBean assessTopicRecordListBean : mAssess.getAssessTopicRecordList()) {
                if (assessTopicRecordListBean.getParentAnswerDefineId() != null) {
                    topicRecordListBeanMap.put(assessTopicRecordListBean.getParentAnswerDefineId(), assessTopicRecordListBean);
                    if (mTopicDefineList != null) {
                        for (AssessDefine.DataBean.AssessTopicDefineListBean assessTopicDefineListBean : mTopicDefineList) {
                            if (Constant.TOPIC_CODE_HUMAN_BOTY.equals(assessTopicDefineListBean.getTopicCode())) {
                                answerDefineList = assessTopicDefineListBean.getAssessAnswerDefineList();
                                if (answerDefineList != null && answerDefineList.size() > 0) {
                                    for (AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean assessAnswerDefineListBean
                                            : answerDefineList) {
                                        if (assessAnswerDefineListBean.getId() == assessTopicRecordListBean.getParentAnswerDefineId()) {
                                            answerDefineCode = assessAnswerDefineListBean.getAnswerDefineCode();
                                            if (answerDefineCode != null) {
                                                if (answerDefineCode.startsWith("BZ")) {
                                                    if (imageViewZhengIDs == null) {
                                                        imageViewZhengIDs = new ArrayList<>();
                                                    }
                                                    if (mTengTongBuWeiName != null) {
                                                        iterator = mTengTongBuWeiName.keySet().iterator();
                                                        while (iterator.hasNext()) {
                                                            next = iterator.next();
                                                            if (answerDefineCode.equals(mTengTongBuWeiName.get(next))) {
                                                                imageViewZhengIDs.add(next);
                                                            }
                                                        }
                                                    }
                                                } else if (answerDefineCode.startsWith("BF")) {
                                                    if (imageViewZhengIDs == null) {
                                                        imageViewZhengIDs = new ArrayList<>();
                                                    }
                                                    if (mTengTongBuWeiName != null) {
                                                        iterator = mTengTongBuWeiName.keySet().iterator();
                                                        while (iterator.hasNext()) {
                                                            next = iterator.next();
                                                            if (answerDefineCode.equals(mTengTongBuWeiName.get(next))) {
                                                                imageViewBeiIDs.add(next);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (assessTopicRecordListBean.getAssessAnswerRecordList() != null && assessTopicRecordListBean.getAssessAnswerRecordList().size() > 0) {
                    if (assessTopicRecordListBean.getAnswerRecordListMap() == null) {
                        assessTopicRecordListBean.setAnswerRecordListMap(new HashMap<Integer, AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean>());
                    } else {
                        assessTopicRecordListBean.getAnswerRecordListMap().clear();
                    }
                    for (AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean assessAnswerRecordListBean : assessTopicRecordListBean.getAssessAnswerRecordList()) {
                        assessTopicRecordListBean.getAnswerRecordListMap().put(assessAnswerRecordListBean.getAnswerDefineId(), assessAnswerRecordListBean);
                    }
                    assessTopicRecordListBean.getAssessAnswerRecordList().clear();
                }
                topicRecordListBeens = new ArrayList<>();
                topicRecordListBeens.add(assessTopicRecordListBean);
                mAssess.getAssessRecorMap().put(assessTopicRecordListBean.getTopicDefineId(), topicRecordListBeens);
                mAssess.getAssessRecorMapTengTong().put(assessTopicRecordListBean.getTopicDefineId(), topicRecordListBeanMap);
                LogUtil.d(mAssess + "");
            }
            mAssess.getAssessTopicRecordList().clear();
        }
    }

    //map 转换assess ；
    private boolean convertAssess() {
        if (mAssess != null && mAssess.getAssessRecorMap() != null && mAssess.getAssessRecorMap().size() > 0) {
            if (mCurrentDefineList != null && mCurrentDefineList.size() > 0
                    && mTopicDefineList != null && mTopicDefineList.size() > 0) {
                for (Integer integer : mCurrentDefineList) {
                    if (mTopicDefineList.get(integer) != null && mTopicDefineList.get(integer).getRequested() == 0) {
                        if (Constant.TOPIC_CODE_PAINT_LEVEL.equals(mTopicDefineList.get(integer).getTopicCode())) {
                            if (!(mAssess != null && mAssess.getAssessRecorMapTengTong() != null && mCurrentDefineList != null
                                    && mCurrentDefineList.get(currentTopicIndex) != null && mTopicDefineList != null
                                    && mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)) != null
                                    && mAssess.getAssessRecorMapTengTong().get(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getId()) != null
                                    && mTengTongBuWeiCode != null && mTengTongBuWei != null
                                    && (currentTopicIndex - mTengTongCode - 1) < mTengTongBuWei.size()
                                    && mTengTongBuWei.get(currentTopicIndex - mTengTongCode - 1) != null)) {
                                return false;
                            } else {
                                AssessRecordBean.AssessTopicRecordListBean assessTopicRecordListBean = mAssess.getAssessRecorMapTengTong()
                                        .get(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)).getId())
                                        .get(mTengTongBuWeiCode.get(mTengTongBuWei.get(currentTopicIndex - mTengTongCode - 1)).getId());
                                if (!(vitalPainKeyboard != null && assessTopicRecordListBean != null && assessTopicRecordListBean.getAssessAnswerRecordList().get(0) != null)) {
                                    return false;
                                }
                            }
                        } else {
                            if (!(mAssess.getAssessRecorMap().get(mTopicDefineList.get(integer).getId()) != null
                                    && mAssess.getAssessRecorMap().get(mTopicDefineList.get(integer).getId()).size() > 0
                                    && mAssess.getAssessRecorMap().get(mTopicDefineList.get(integer).getId()).get(0) != null)) {
                                currentTopicIndex = mCurrentDefineList.indexOf(integer);
                                bindTopic(currentTopicIndex);
                                return false;
                            }

                            if (!(mAssess.getAssessRecorMap().get(mTopicDefineList.get(integer).getId()).get(0).getAnswerRecordListMap() != null
                                    && mAssess.getAssessRecorMap().get(mTopicDefineList.get(integer).getId()).get(0).getAnswerRecordListMap().size() > 0
                                    || mAssess.getAssessRecorMap().get(mTopicDefineList.get(integer).getId()).get(0).getValue() != null)) {
                                currentTopicIndex = mCurrentDefineList.indexOf(integer);
                                bindTopic(currentTopicIndex);
                                return false;
                            }
                        }
                    }
                }
            }
            mAssess.setAssessTopicRecordList(new ArrayList<AssessRecordBean.AssessTopicRecordListBean>());
            convertTengTongAssess();
            convertPuTongAssess();
            LogUtil.d(mAssess + "");
        }
        return true;
    }

    private void convertTengTongAssess() {
        if (mAssess.getAssessRecorMapTengTong() == null) {
            return;
        }
        Iterator<Map.Entry<Integer, Map<Integer, AssessRecordBean.AssessTopicRecordListBean>>> iter = mAssess.getAssessRecorMapTengTong().entrySet().iterator();
        AssessRecordBean.AssessTopicRecordListBean record = null;
        Map.Entry entrys = null;
        Map<Integer, AssessRecordBean.AssessTopicRecordListBean> value;
        Iterator<Map.Entry<Integer, AssessRecordBean.AssessTopicRecordListBean>> iterator;
        Map.Entry entry = null;
        while (iter.hasNext()) {
            entrys = (Map.Entry) iter.next();
            if (entrys.getValue() != null) {
                value = (Map<Integer, AssessRecordBean.AssessTopicRecordListBean>) entrys.getValue();
                iterator = value.entrySet().iterator();
                while (iterator.hasNext()) {
                    entry = (Map.Entry) iterator.next();
                    if (entry.getValue() != null) {
                        record = (AssessRecordBean.AssessTopicRecordListBean) entry.getValue();
                        mAssess.getAssessTopicRecordList().add(record);
                    }
                }
            }
        }
    }

    private void convertPuTongAssess() {
        Iterator iter = mAssess.getAssessRecorMap().entrySet().iterator();
        List<AssessRecordBean.AssessTopicRecordListBean> record = null;
        AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean assessAnswerRecordListBean = null;
        Map.Entry entry = null;
        while (iter.hasNext()) {
            entry = (Map.Entry) iter.next();
            if (entry.getValue() != null) {
                record = (List<AssessRecordBean.AssessTopicRecordListBean>) entry.getValue();
                if (record != null && record.size() > 0) {
                    for (AssessRecordBean.AssessTopicRecordListBean assessTopicRecordListBean : record) {
                        if (assessTopicRecordListBean.getAnswerRecordListMap() != null
                                && assessTopicRecordListBean.getAnswerRecordListMap().size() > 0) {
                            Iterator iterAnswer = assessTopicRecordListBean.getAnswerRecordListMap().entrySet().iterator();
                            Map.Entry entryAnswer = null;
                            while (iterAnswer.hasNext()) {
                                entryAnswer = (Map.Entry) iterAnswer.next();
                                assessAnswerRecordListBean = (AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean) entryAnswer.getValue();
                                if (assessAnswerRecordListBean != null) {
                                    if (assessTopicRecordListBean.getAssessAnswerRecordList() == null) {
                                        assessTopicRecordListBean.setAssessAnswerRecordList(new ArrayList<AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean>());
                                    }
                                    assessTopicRecordListBean.getAssessAnswerRecordList().add(assessAnswerRecordListBean);
                                }
                            }
                        }
                        mAssess.getAssessTopicRecordList().add(assessTopicRecordListBean);
                    }
                }
            }
        }
    }

    @Override
    public void handleOnError() {
        btpinggu_baocun_bt.setEnabled(true);
        showToastShort("网络或服务器异常，评估提交失败！");
    }

    @Override
    public void handleSuccess(Object obj) {
        btpinggu_baocun_bt.setEnabled(true);
        if (mAssessDefine != null && mAssessDefine.getAssessCode() != null && mAssessDefine.getAssessCode().endsWith("RECORD")) {
            killSelf();
            return;
        }
        LogUtil.d("PG_DTQuestionActivity.handleSuccess calling... obj is " + obj);
        if (obj == null) {
            LogUtil.e("Server response object is null can not continue handle anything!");
            return;
        }
        if (obj instanceof AssessRecordBean) {
            if (mAssess != null && mAssess.getDbId() != null && mAssess.getId() == null) {
                new AssessDaoImpl().delete(mAssess);
            }
            mAssess = (AssessRecordBean) obj;
            LogUtil.d("PG_DTQuestionActivity.handleSuccess returned assessId:"
                    + mAssess.getId() + "/" + mAssess.getDbId() + ", result:" + mAssess.getResult());
            showToastShort("评估提交成功！");
            mBundle.putSerializable(Constant.BUNDLE_KEY_ASSESS, mAssess);
            goToActivity(PG_DTMainActivity.class, mBundle);
        }
    }

    private void gotoNextTopic() {
        currentTopicIndex++;
        gotoTopic();
    }

    //  Turn to the specified topic （转到指定题目）
    private void gotoTopic() {
        if (!isTopicExistes(0)) return;
        boolean needBind = true;
        mCurrentDefineList = initShowTopicDefine();
        if (currentTopicIndex > mCurrentDefineList.size() - 1) {
            currentTopicIndex = mCurrentDefineList.size() - 1;
            needBind = false;
        }
        if (currentTopicIndex < 0) {
            currentTopicIndex = 0;
            needBind = false;
        }
        /*if (currentTopicIndex == mCurrentDefineList.size() - 1) {
            btpinggu_baocun_bt.setText("提交");
        } else {
            btpinggu_baocun_bt.setText("保存");
        }*/

        if (needBind) {
            setProgress();
            bindTopic(currentTopicIndex);
        }
    }

    //  Set the answer progress （设置答题进度）
    private void setProgress() {
        //tv_footer_pg.setText("已完成" + haveAnswerNum() + "道题 / 共" + mCurrentDefineList.size() + "道题");
        //tv_footer_pg.setText("已完成" + haveAnswerNum() + "道题 / 共" + questionNum + "道题");
        tv_footer_pg.setText("共" + questionNum + "道题");
    }

    //  保存数据库
    private void saveAssess(boolean isModified) {
        new AssessDaoImpl().saveAssess(mAssess, isModified);
    }

    //    初始化
    private void loadAssessData() {
        if (mAssessDefine == null) {
            return;
        }
        if (Constant.ASSESS_CODE_PAIN_ASSESS.equals(mAssessDefine.getAssessCode())) {
            mTengTongChuLi = new TengTongChuLi();
            mTengTongBuWeiName = mTengTongChuLi.getmTengTongBuWeiName();
        }
        convertmAssess();
        if (mAssess == null) {
            SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
            mAssess = new AssessRecordBean();
            mAssess.setAssessDefineId(mAssessDefine.getId());
            mAssess.setPatientId(mSelectedHZ.getPatientid());
            mAssess.setDescription(mAssessDefine.getName());
            mAssess.setUserId(Integer.parseInt(sharePLogin.getUserid()));
            mAssess.setCreatedBy(sharePLogin.getUserid());
            mAssess.setLastupdatedby(sharePLogin.getUserid());
            mAssess.setLastUpdateTime(System.currentTimeMillis());
            mAssess.setCreationTime(System.currentTimeMillis());
            mAssess.setUserName(sharePLogin.getDisplayUserName());
            mAssess.setIsdeleted("0");
            mAssess.setStatus(Constant.ASSESS_STATUS_SAVE);
            mAssess.setScore(0);
            List<AssessRecordBean.AssessTopicRecordListBean> topicList = new ArrayList<>();
            mAssess.setAssessRecorMap(new HashMap<Integer, List<AssessRecordBean.AssessTopicRecordListBean>>());
            mAssess.setAssessTopicRecordList(topicList);
        }

        topicAnswerItemAdapter = new AssessTopicAnswerItemAdapter(mActivitySelf, mAssessDefine);
        bindTopic(0);
        mPgItemAnswer.setAdapter(topicAnswerItemAdapter);
        mPgItemAnswer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean b = topicAnswerItemAdapter.clickItem(view, position);
                updateRelateTopic(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)));
                updateRelateAnswer(mTopicDefineList.get(mCurrentDefineList.get(currentTopicIndex)));
            }
        });
    }


    private void updateRelateAnswer(AssessDefine.DataBean.AssessTopicDefineListBean assessTopicDefineListBean) {
        //判断传值是否有数据
        if (assessTopicDefineListBean != null && assessTopicDefineListBean.getAssessAnswerDefineList() != null && assessTopicDefineListBean.getAssessAnswerDefineList().size() > 0) {
            //判断是否有答题记录
            if (mAssess != null && mAssess.getAssessRecorMap() != null && mAssess.getAssessRecorMap().get(assessTopicDefineListBean.getId()) != null
                    && mAssess.getAssessRecorMap().get(assessTopicDefineListBean.getId()).get(0) != null
                    && mAssess.getAssessRecorMap().get(assessTopicDefineListBean.getId()).get(0).getAnswerRecordListMap() != null
                    && mAssess.getAssessRecorMap().get(assessTopicDefineListBean.getId()).get(0).getAnswerRecordListMap().size() > 0) {
                //遍历题目答案定义
                for (AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean assessAnswerDefineListBean : assessTopicDefineListBean.getAssessAnswerDefineList()) {
                    //判断是否和其他题的答案有关联
                    if (assessAnswerDefineListBean.getRelateTo() == 2) {
                        if (assessAnswerDefineListBean.getAssessAnswerRelateAnswerList() != null
                                && assessAnswerDefineListBean.getAssessAnswerRelateAnswerList().size() > 0) {
                            //遍历和此选项有关联的题 只能获取ID
                            for (AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean.AssessAnswerRelateAnswerListBean answerRelateAnswer
                                    : assessAnswerDefineListBean.getAssessAnswerRelateAnswerList()) {
                                //判断此选项有无选中
                                if (mAssess.getAssessRecorMap().get(assessTopicDefineListBean.getId())
                                        .get(0).getAnswerRecordListMap().get(assessAnswerDefineListBean.getId()) != null) {
//                                    遍历所有题目定义找到和此选项有关联的题
                                    for (AssessDefine.DataBean.AssessTopicDefineListBean assessTopicDefineListBean1 : mTopicDefineList) {
                                        if (assessTopicDefineListBean1.getId() == answerRelateAnswer.getRelateTopicDefineId()) {
                                            if (assessTopicDefineListBean1.getAssessAnswerDefineList() != null
                                                    && assessTopicDefineListBean1.getAssessAnswerDefineList().size() > 0) {
                                                for (AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean anserDifine
                                                        : assessTopicDefineListBean1.getAssessAnswerDefineList()) {
                                                    if (answerRelateAnswer.getRelateAnswerDefineId() == anserDifine.getId()) {
//                                                        如果控制其不显示
                                                        if (answerRelateAnswer.getDisplayType() == 0) {
                                                            assessTopicDefineListBean1.setInitType(0);
                                                        } else {
                                                            assessTopicDefineListBean1.setInitType(1);
                                                        }
                                                    }
                                                }
                                                mAssess.getAssessRecorMap().remove(assessTopicDefineListBean1.getId());
                                            }
                                        }
                                    }
                                } else {
//                                    遍历所有题目定义找到和此选项有关联的题
                                    for (AssessDefine.DataBean.AssessTopicDefineListBean assessTopicDefineListBean1 : mTopicDefineList) {
                                        if (assessTopicDefineListBean1.getId() == answerRelateAnswer.getRelateTopicDefineId()) {
                                            if (assessTopicDefineListBean1.getAssessAnswerDefineList() != null
                                                    && assessTopicDefineListBean1.getAssessAnswerDefineList().size() > 0) {
                                                for (AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean anserDifine
                                                        : assessTopicDefineListBean1.getAssessAnswerDefineList()) {
                                                    if (answerRelateAnswer.getRelateAnswerDefineId() == anserDifine.getId()) {
//                                                        如果控制其不显示
                                                        if (answerRelateAnswer.getDisplayType() == 0) {
                                                            assessTopicDefineListBean1.setInitType(0);
                                                        } else {
                                                            assessTopicDefineListBean1.setInitType(1);
                                                        }
                                                    }
                                                }
                                                mAssess.getAssessRecorMap().remove(assessTopicDefineListBean1.getId());
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    private void updateRelateTopic(AssessDefine.DataBean.AssessTopicDefineListBean assessTopicDefineListBean) {
        //判断传值是否有数据
        if (assessTopicDefineListBean != null && assessTopicDefineListBean.getAssessAnswerDefineList() != null && assessTopicDefineListBean.getAssessAnswerDefineList().size() > 0) {
            //判断是否有答题记录
            if (mAssess != null && mAssess.getAssessRecorMap() != null && mAssess.getAssessRecorMap().get(assessTopicDefineListBean.getId()) != null
                    && mAssess.getAssessRecorMap().get(assessTopicDefineListBean.getId()).get(0) != null
                    && mAssess.getAssessRecorMap().get(assessTopicDefineListBean.getId()).get(0).getAnswerRecordListMap() != null
                    && mAssess.getAssessRecorMap().get(assessTopicDefineListBean.getId()).get(0).getAnswerRecordListMap().size() > 0) {
                //遍历题目答案定义
                for (AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean assessAnswerDefineListBean : assessTopicDefineListBean.getAssessAnswerDefineList()) {
                    //判断是否和其他题有关联

                    if (assessAnswerDefineListBean.getRelateTo() == 1) {
                        if (assessAnswerDefineListBean.getAssessAnswerTopicRelationList() != null
                                && assessAnswerDefineListBean.getAssessAnswerTopicRelationList().size() > 0) {
                            //遍历和此选项有关联的题 只能获取ID
                            for (AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean.AssessAnswerTopicRelationListBean assessAnswerTopicRelationListBean
                                    : assessAnswerDefineListBean.getAssessAnswerTopicRelationList()) {
                                //判断此选项有无选中

                                if (mAssess.getAssessRecorMap().get(assessTopicDefineListBean.getId())
                                        .get(0).getAnswerRecordListMap().get(assessAnswerDefineListBean.getId()) != null) {
//                                    遍历所有题目定义找到和此选项有关联的题
                                    for (AssessDefine.DataBean.AssessTopicDefineListBean assessTopicDefineListBean1 : mTopicDefineList) {
                                        if (assessTopicDefineListBean1.getId() == assessAnswerTopicRelationListBean.getRelateTopicDefineId()) {
//                                            如果控制其不显示
                                            if (assessAnswerTopicRelationListBean.getAttribute() == 0) {
                                                assessTopicDefineListBean1.setInitType(1);
                                                if (assessTopicDefineListBean1.getOptionList() != null && assessTopicDefineListBean1.getOptionList().size() > 0) {
                                                    //遍历所有和此需要隐藏的题关联的选项 ，看看是否有选中 有则不隐藏；
                                                    // // TODO: 2017/3/9 只是知道有无关联 而不知道Attribute 的值 ？
                                                    for (AssessDefine.OptionList optionList : assessTopicDefineListBean1.getOptionList()) {
                                                        if (mAssess.getAssessRecorMap().get(optionList.getTopicDefineId()) != null) {
                                                            if (mAssess.getAssessRecorMap().get(optionList.getTopicDefineId()).get(0) != null
                                                                    && mAssess.getAssessRecorMap().get(optionList.getTopicDefineId()).get(0).getAnswerRecordListMap() != null
                                                                    && mAssess.getAssessRecorMap().get(optionList.getTopicDefineId()).get(0).getAnswerRecordListMap().size() > 0) {
                                                                assessTopicDefineListBean1.setInitType(0);
                                                                break;
                                                            }
                                                        }

                                                    }
                                                }
                                            } else {
                                                assessTopicDefineListBean1.setInitType(0);
                                            }
                                            mAssess.getAssessRecorMap().remove(assessTopicDefineListBean1.getId());
                                        }
                                    }
                                } else {
//                                    遍历所有题目定义找到和此选项有关联的题
                                    for (AssessDefine.DataBean.AssessTopicDefineListBean assessTopicDefineListBean1 : mTopicDefineList) {
                                        if (assessTopicDefineListBean1.getId() == assessAnswerTopicRelationListBean.getRelateTopicDefineId()) {
//                                            如果控制其不显示
                                            if (assessAnswerTopicRelationListBean.getAttribute() == 0) {
                                                assessTopicDefineListBean1.setInitType(0);
                                            } else {
                                                assessTopicDefineListBean1.setInitType(1);
                                                if (assessTopicDefineListBean1.getOptionList() != null && assessTopicDefineListBean1.getOptionList().size() > 0) {
                                                    //遍历所有和此需要隐藏的题关联的选项 ，看看是否有选中 有则不隐藏；
                                                    // // TODO: 2017/3/9 只是知道有无关联 而不知道Attribute 的值 ？
                                                    for (AssessDefine.OptionList optionList : assessTopicDefineListBean1.getOptionList()) {
                                                        if (mAssess.getAssessRecorMap().get(optionList.getTopicDefineId()) != null) {
                                                            if (mAssess.getAssessRecorMap().get(optionList.getTopicDefineId()).get(0) != null
                                                                    && mAssess.getAssessRecorMap().get(optionList.getTopicDefineId()).get(0).getAnswerRecordListMap() != null
                                                                    && mAssess.getAssessRecorMap().get(optionList.getTopicDefineId()).get(0).getAnswerRecordListMap().size() > 0) {
                                                                assessTopicDefineListBean1.setInitType(0);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            mAssess.getAssessRecorMap().remove(assessTopicDefineListBean1.getId());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private int haveAnswerNum() {
        int retval = 0;
        if (mAssess == null || mAssess.getAssessRecorMap() == null) {
            return retval;
        }
        AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean define = null;
        Iterator iter = mAssess.getAssessRecorMap().entrySet().iterator();
        List<AssessRecordBean.AssessTopicRecordListBean> record = null;
        AssessRecordBean.AssessTopicRecordListBean assessTopicRecordListBean = null;
        Map.Entry entry = null;
        Map.Entry entry1 = null;
        Map.Entry entry2 = null;
        while (iter.hasNext()) {
            entry = (Map.Entry) iter.next();
            if (entry.getValue() != null) {
                record = (List<AssessRecordBean.AssessTopicRecordListBean>) entry.getValue();
                if (record != null && record.get(0) != null && record.get(0).getAnswerRecordListMap() != null && record.get(0).getAnswerRecordListMap().size() > 0) {
                    //  修正跌倒评估中已完成题目数的计算错误问题
                    for (Map.Entry<Integer, AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean> entity : record.get(0).getAnswerRecordListMap().entrySet()) {
                        if (entity.getValue().getAnswerDefineId() != null) {
                            retval++;
                        }
                    }
                }
            }
        }

        // 疼痛题目的计算
        /*try {
            Iterator iterator = mAssess.getAssessRecorMapTengTong().entrySet().iterator();
            Iterator iterator1;
            Map<Integer, AssessRecordBean.AssessTopicRecordListBean> value = null;
            while (iterator.hasNext()) {
                entry1 = (Map.Entry) iterator.next();
                if (entry1.getValue() != null) {
                    value = (Map<Integer, AssessRecordBean.AssessTopicRecordListBean>) entry1.getValue();
                    if (value != null && value.size() > 0) {
                        iterator1 = value.entrySet().iterator();
                        while (iterator1.hasNext()) {
                            entry2 = (Map.Entry) iterator1.next();
                            if (entry2.getValue() != null) {
                                assessTopicRecordListBean
                                        = (AssessRecordBean.AssessTopicRecordListBean) entry2.getValue();
                                if (assessTopicRecordListBean.getAssessAnswerRecordList() != null
                                        && assessTopicRecordListBean.getAssessAnswerRecordList().size() > 0) {
                                    retval++;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.e(e.getMessage() + "");
        }*/
        return retval;
    }

    //   获取答题记录
    private AssessRecordBean.AssessTopicRecordListBean getTopicRecord(AssessDefine.DataBean.AssessTopicDefineListBean topicDefine) {
        AssessRecordBean.AssessTopicRecordListBean retval = null;
        if (null != topicDefine) {
            if (mAssess.getAssessRecorMap() == null) {
                mAssess.setAssessRecorMap(new HashMap<Integer, List<AssessRecordBean.AssessTopicRecordListBean>>());
            }
            if (mAssess.getAssessRecorMap().get(topicDefine.getId()) != null) {
                retval = mAssess.getAssessRecorMap().get(topicDefine.getId()).get(0);
            }
        }
        return retval;
    }

    /**
     * Whether the title    （判断题目是否存在）
     *
     * @param position 题目位置
     * @return
     */
    private boolean isTopicExistes(int position) {
        boolean retval = true;
        if (mAssessDefine == null || mCurrentDefineList == null
                || mCurrentDefineList.size() <= position
                || position < 0 || mCurrentDefineList.get(position) == null) retval = false;
        return retval;
    }

    /**
     * Questions and answers are binding  (题目和答案进行绑定)
     *
     * @param position 题目位置
     */
    private void bindTopic(int position) {
        if (!isTopicExistes(position)) return;
        mHzPgDtTopicName0.setText(mAssessDefine.getName());
        if (mAssessDefine.getName().equals("压疮风险评估")) {
            img_explain.setVisibility(View.VISIBLE);
            img_explain.setOnClickListener(this);
        }
        tvhz_pg_dt_topic_name.setText((currentTopicIndex + 1) + ". " + mTopicDefineList.get(mCurrentDefineList.get(position)).getName());
        mHzPgDtTopicName1.setVisibility(View.GONE);
        if (mTopicDefineList.get(mCurrentDefineList.get(position)).getClassifyType() == 2) {
            mHzPgDtTopicName1.setVisibility(View.VISIBLE);
            mHzPgDtTopicName1.setText("/" + mTopicDefineList.get(mCurrentDefineList.get(position)).getFatherTitle());
        }
        if (Constant.TOPIC_CODE_PAINT_LEVEL.equals(mTopicDefineList.get(mCurrentDefineList.get(position)).getTopicCode())) {
            try {
                mBuWeiName = mTengTongBuWeiCode.get(mTengTongBuWei.get(currentTopicIndex - mTengTongCode - 1)).getValue();
                mHzPgDtTopicName1.setText("/" + mTopicDefineList.get(mCurrentDefineList.get(position)).getFatherTitle() + "(" + mBuWeiName + ")");
                setTengTongKeyBoard();
            } catch (Exception e) {
                LogUtil.e(e.getMessage() + "");
            }
            return;
        }
        if (Constant.TOPIC_CODE_HUMAN_BOTY.equals(mTopicDefineList.get(mCurrentDefineList.get(position)).getTopicCode())) {
            mTengTongCode = position;
            mAnswerOptionsLl.setVisibility(View.GONE);
            mAnswerOptionsRentiLl.setVisibility(View.VISIBLE);
            List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean> answerDefineList
                    = mTopicDefineList.get(mCurrentDefineList.get(position)).getAssessAnswerDefineList();
            initTengTongAnswerMap(answerDefineList);
            setRenTiTuZheng();
            return;
        } else {
            mAnswerOptionsLl.setVisibility(View.VISIBLE);
            mAnswerOptionsRentiLl.setVisibility(View.GONE);
        }
        if (Constant.TOPIC_TYPE_TIME.equals(mTopicDefineList.get(mCurrentDefineList.get(position)).getTopicType())) {
            bindTimeTopic(position);
            return;
        } else {
            dateTimeControl.setVisible(false);
        }
        bindGlobalTopic(position);
    }

    private void initTengTongAnswerMap(List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean> answerDefineList) {
        if (answerDefineList != null && answerDefineList.size() > 0) {
            mTengTongBuWeiCode = new HashMap<>();
            for (AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean assessAnswerDefineListBean : answerDefineList) {
                mTengTongBuWeiCode.put(assessAnswerDefineListBean.getAnswerDefineCode(), assessAnswerDefineListBean);
            }
        }
    }

    private void bindTimeTopic(int position) {
        dateTimeControl.setVisible(true);
        try {
            AssessRecordBean.AssessTopicRecordListBean topicRecord = getTopicRecord(mTopicDefineList.get(mCurrentDefineList.get(position)));
            if (topicRecord != null && topicRecord.getAssessAnswerRecordList() != null
                    && topicRecord.getAssessAnswerRecordList().get(0) != null && topicRecord.getAssessAnswerRecordList().get(0).getTimeValue() != null) {
                long dateTime = Long.parseLong(topicRecord.getAssessAnswerRecordList().get(0).getTimeValue());
                Date date = new Date(dateTime);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                dateTimeControl.setDateTime(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1
                        , calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void bindGlobalTopic(int position) {
        AssessRecordBean.AssessTopicRecordListBean topicRecord = getTopicRecord(mTopicDefineList.get(mCurrentDefineList.get(position)));
        if (topicRecord == null) {
            topicRecord = new AssessRecordBean.AssessTopicRecordListBean();
            topicRecord.setIsdeleted("0");
            topicRecord.setAssessid(mAssess.getId());
            topicRecord.setTopicDefineId(mTopicDefineList.get(mCurrentDefineList.get(position)).getId());
        }
        mPgItemAnswer.setVisibility(View.VISIBLE);
        if (topicRecord.getAssessAnswerRecordList() == null)
            topicRecord.setAssessAnswerRecordList(new ArrayList<AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean>());
        List<AssessRecordBean.AssessTopicRecordListBean> topicRecords = new ArrayList<>();
        topicRecords.add(topicRecord);
        mAssess.getAssessRecorMap().put(mTopicDefineList.get(mCurrentDefineList.get(position)).getId(), topicRecords);
        topicAnswerItemAdapter.changeTopic(mTopicDefineList.get(mCurrentDefineList.get(position)), topicRecord);
        topicAnswerItemAdapter.notifyDataSetChanged();
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

    public List<AssessDefine.DataBean.AssessTopicDefineListBean> getmTopicDefineList() {
        if (mAssessDefine == null) return null;
        List<AssessDefine.DataBean.AssessTopicDefineListBean> topicDefineList = mAssessDefine.getAssessTopicDefineList();
        List<AssessDefine.DataBean.AssessTopicDefineListBean> newList = new ArrayList<>();
        // 去掉父标题
        if (topicDefineList != null && topicDefineList.size() > 0) {
            for (int i = 0; i < topicDefineList.size(); i++) {
                if (topicDefineList.get(i).getInitType() != 1) {
                    newList.add(topicDefineList.get(i));
                }
            }
        }
        if (Constant.ASSESS_CODE_PAIN_ASSESS.equals(mAssessDefine.getAssessCode())) {
            for (AssessDefine.DataBean.AssessTopicDefineListBean assessTopicDefineListBean : newList) {
                if (Constant.TOPIC_CODE_PAINT_LEVEL.equals(assessTopicDefineListBean.getTopicCode())) {
                    mTengTongJiBieTopicDefineBean = assessTopicDefineListBean;
                }
            }
        }
        newList = addTopicDefineList(newList);
        //return addTopicDefineList(topicDefineList);
        return newList;
    }

    //题目初始化显示或隐藏。
    private List<Integer> initShowTopicDefine() {
        List<Integer> topicDefineList = new ArrayList<>();
        if (mTopicDefineList != null) {
            for (int i = 0; i < mTopicDefineList.size(); i++) {
                if (mAssess != null && mAssess.getAssessRecorMap() != null
                        && mAssess.getAssessRecorMap().get(mTopicDefineList.get(i).getId()) != null
                        && mAssess.getAssessRecorMap().get(mTopicDefineList.get(i).getId()).get(0) != null
                        && mAssess.getAssessRecorMap().get(mTopicDefineList.get(i).getId()).get(0).getAnswerRecordListMap() != null) {
                    mTopicDefineList.get(i).setInitType(0);
                }
                if (mTopicDefineList.get(i).getInitType() == 0) {
                    topicDefineList.add(i);
                }
            }
        }
        return topicDefineList;
    }

    //分组题加如集合
    public List<AssessDefine.DataBean.AssessTopicDefineListBean> addTopicDefineList(List<AssessDefine.DataBean.AssessTopicDefineListBean> topicDefineList) {
        List<AssessDefine.DataBean.AssessTopicDefineListBean> relateTopicList;
        boolean isStop = true;
        for (int i = 0; i < topicDefineList.size(); i++) {
            if (topicDefineList.get(i).getClassifyType() == 0) {
                relateTopicList = topicDefineList.get(i).getRelateTopicList();
                List<AssessDefine.DataBean.AssessTopicDefineListBean> tempList = new ArrayList<>();
                // 去掉不该显示的项
                for (int j = 0; j < relateTopicList.size(); j++) {
                    if (relateTopicList.get(j).getInitType() != 1) {
                        tempList.add(relateTopicList.get(j));
                    }
                }
                relateTopicList.clear();
                relateTopicList.addAll(tempList);

                for (AssessDefine.DataBean.AssessTopicDefineListBean listBean : relateTopicList) {
                    listBean.setFatherTitle(topicDefineList.get(i).getName());
                    if (Constant.TOPIC_CODE_PAINT_LEVEL.equals(listBean.getTopicCode())) {
                        mTengTongJiBieTopicDefineBean = listBean;
                    }
                }
                topicDefineList.remove(i);
                topicDefineList.addAll(i, relateTopicList);
                isStop = false;
                break;
            }
        }
        if (!isStop) {
            addTopicDefineList(topicDefineList);
        }
        return topicDefineList;
    }

    /**
     * 单机事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 压疮评估用到的说明
            case R.id.img_explain: {
                if (mAssessDefine.getAssessTopicDefineList() != null && mAssessDefine.getAssessTopicDefineList().size() > 0) {
                    // 跳转到说明界面
                    Intent intent = new Intent();
                    intent.setClass(PG_DTQuestionActivity.this, AssessExplainActivity.class);
                    intent.putExtra("explain", (Serializable) mAssessDefine.getAssessTopicDefineList().get(currentTopicIndex).getAssessAnswerDefineList());
                    startActivity(intent);
                }
            }
            break;
        }
    }
}