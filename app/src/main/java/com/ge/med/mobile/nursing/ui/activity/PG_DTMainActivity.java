package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sj.library.control.ActivityControl;
import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.forjson.NetworkForAssessment;
import com.ge.med.mobile.nursing.forjson.entity.AssessRecordBean;
import com.ge.med.mobile.nursing.service.SyncService;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.utils.DataConverter;

import org.xutils.common.util.LogUtil;

public class PG_DTMainActivity extends MyBaseActivity {
    TextView tvhz_pg_dt_name;
    TextView tvhz_pg_dt_note;
    TextView tvhz_biao_qian_bg_tv;
    TextView tvhz_pg_dt_result;
    Button bthz_pg_dt_start;
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;
    private AssessDefine.DataBean mAssessDefine;
    private AssessRecordBean mAssess;
    private TitleBar mTitleBar;
    private PatientInfoPannel mPatientPanel;
    private Bundle mBundle;
    private Intent intent;
    private RelativeLayout mRl2;
    private TextView mRl2Tv1;
    private TextView mRl2Tv21;
    private TextView mRl2Tv22;
    private TextView mRl2Tv31;
    private TextView mRl2Tv32;
    private LinearLayout mLl1;
    private TextView mBiaotiTv;


    @Override
    public int setRootView() {
        return R.layout.activity_pg__dtmain;
    }

    @Override
    public void initViews() {
        intent = getIntent();
        mBundle = intent.getBundleExtra(Constant.GLOBAL_KEY_DATA);
        if (null == mBundle) {
            mSelectedHZ = null;
            mAssessDefine = null;
            mAssess = null;
        } else {
            mSelectedHZ = (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
            mAssessDefine = (AssessDefine.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE);
            mAssess = (AssessRecordBean) mBundle.getSerializable(Constant.BUNDLE_KEY_ASSESS);
        }
        mTitleBar = new TitleBar(this, mSelectedHZ);
        mPatientPanel = new PatientInfoPannel(this, new AssessDaoImpl().findAllRiskDefine(), mSelectedHZ);
        mPatientPanel.showHideTipLayout(false);
        tvhz_pg_dt_name = (TextView) findViewById(R.id.hz_pg_dt_name);
        tvhz_pg_dt_note = (TextView) findViewById(R.id.hz_pg_dt_note);
        tvhz_biao_qian_bg_tv = (TextView) findViewById(R.id.hz_pg_topic_no);
        tvhz_pg_dt_result = (TextView) findViewById(R.id.hz_pg_dt_result);
        bthz_pg_dt_start = (Button) findViewById(R.id.hz_pg_dt_start);
        mRl2 = (RelativeLayout) findViewById(R.id.rl_2);
        mRl2Tv1 = (TextView) findViewById(R.id.rl_2_tv1);
        mRl2Tv21 = (TextView) findViewById(R.id.rl_2_tv2_1);
        mRl2Tv22 = (TextView) findViewById(R.id.rl_2_tv2_2);
        mRl2Tv31 = (TextView) findViewById(R.id.rl_2_tv3_1);
        mRl2Tv32 = (TextView) findViewById(R.id.rl_2_tv3_2);
        mBiaotiTv = (TextView) findViewById(R.id.biaoti_tv);
        mLl1 = (LinearLayout) findViewById(R.id.ll_1);
        tvhz_pg_dt_name.setText(null == mAssessDefine ? "数据错误" : mAssessDefine.getName());
        tvhz_pg_dt_note.setText(null == mAssessDefine ? "数据错误" : mAssessDefine.getNote());
        tvhz_biao_qian_bg_tv.setText(null == mAssessDefine ? "没有题目" : "共" + mAssessDefine.getTopicTotalNo() + "题");
        if (mAssess != null) {
            mLl1.setVisibility(View.GONE);
            mBiaotiTv.setVisibility(View.VISIBLE);
            mRl2.setVisibility(View.VISIBLE);
            mBiaotiTv.setText(mAssessDefine.getName());
            try {
                mRl2Tv1.setText("评估时间: " + DateUtils.getDateString("yyyy年MM月dd日HH时mm分", mAssess.getCreationTime()));
            } catch (Exception e) {
                LogUtil.e(e.getMessage() + "");
            }
            // 入院评估不显示分数
            if (!mAssessDefine.getName().equals("入院评估")) {
                mRl2Tv22.setText(mAssess.getScore() + "");
            }
            mRl2Tv32.setText(mAssess.getResultDescription() + "");
            bthz_pg_dt_start.setText("完成评估");
            if (mAssess.getAssessMeasureRecord() != null && mAssess.getAssessMeasureRecord().getAssessMeasureDefineId() != null) {
                bthz_pg_dt_start.setText("预防措施");
            }
            bthz_pg_dt_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("预防措施".equals(bthz_pg_dt_start.getText())) {
                        mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_HOUJI_CUOSHI);
                        mBundle.putString(Constant.BUNDLE_KEY_FRAGMENT_TYPE, Constant.BUNDLE_KEY_ACTIVITY_TYPE_HOUJI_CUOSHI);
                        bthz_pg_dt_start.setEnabled(false);
                        if (mAssess == null) {
                            showToastShort("没有可完成的评估！");
                            return;
                        }
                        mAssess.setStatus(Constant.ASSESS_STATUS_FINISHED);
                        NetworkForAssessment.submitSingleAssessment(PG_DTMainActivity.this, mAssess);
                    } else {
                        finishAssess();
                    }
                }
            });
        } else {
            tvhz_pg_dt_result.setVisibility(View.GONE);
            bthz_pg_dt_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToActivity(PG_DTQuestionActivity.class, mBundle);
                }
            });
        }
    }

    private void finishAssess() {
        if (mAssess == null) {
            showToastShort("没有可完成的评估！");
            return;
        }
        mAssess.setStatus(Constant.ASSESS_STATUS_FINISHED);
        if (SyncService.isConnected()) {
            NetworkForAssessment.submitSingleAssessment(this, mAssess);
            LogUtil.d("Try to submit finish assessment request to server!");
        } else {
            showToastLong("离线状态下无法完成评估！");
            LogUtil.e("Can not finish assessment since server is not reachable!");
            backToPatientPage();
        }
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

    @Override
    public void handleOnError() {
        showToastLong("无法完成评估！");
        bthz_pg_dt_start.setEnabled(true);
        backToPatientPage();
    }

    @Override
    public void handleOnError(String urlStr) {
        super.handleOnError(urlStr);
        bthz_pg_dt_start.setEnabled(true);
    }

    @Override
    public void handleSuccess(Object obj) {
        bthz_pg_dt_start.setEnabled(true);
        LogUtil.d("PG_DTMainActivity.handleSuccess calling... obj is " + obj);
        if (obj == null) {
            LogUtil.e("Server response object is null can not continue handle anything!");
            return;
        }
        if (obj instanceof AssessRecordBean) {
            mAssess = (AssessRecordBean) obj;
            LogUtil.d("PG_DTMainActivity.handleSuccess returned assessId:"
                    + mAssess.getId() + "/" + mAssess.getDbId() + ", result:" + mAssess.getResult());
            NetworkForAssessment.callSinglePatient(this, mAssess.getPatientId());

        } else if (obj instanceof HuanZheLieBiaoBean.DataBean) {
            showToastShort("完成评估成功！");
            updatePatientRiskTag((HuanZheLieBiaoBean.DataBean) obj, mBundle);
            backToPatientPage();
        }
    }

    private void backToPatientPage() {
        ActivityControl.killActivity(HZActivity.class);
        ActivityControl.killActivity(PG_DTMainActivity.class);
        ActivityControl.killActivity(PG_ZJMActivity.class);
        ActivityControl.killActivity(PG_DTQuestionActivity.class);
        gotoPatientPage(mBundle, 1);
        if ("预防措施".equals(bthz_pg_dt_start.getText())) {
            goToActivity(PG_ZJMActivity.class, mBundle);
        }
    }

    private void updatePatientRiskTag(HuanZheLieBiaoBean.DataBean patient, Bundle bundle) {
        if (bundle == null || patient == null) {
            LogUtil.e("Can not update patient risk tag since bundle or assess bean is null!");
            return;
        }
        mSelectedHZ = patient;
        bundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, patient);
    }

    private String addAssessResult(String oldResult, String newResult) {
        if (newResult == null || newResult.isEmpty()) return oldResult;
        if (oldResult == null || oldResult.isEmpty()) return newResult;
        if (oldResult.contains(newResult)) return oldResult;
        return oldResult + "," + newResult;
    }

    private HuanZheLieBiaoBean.DataBean getCurrentPatient(String patientId) {
        HuanZheLieBiaoBean.DataBean retval = mSelectedHZ;
        if (null == retval && patientId != null) {
            DBHuanZheLieBiao patient = new HuanZheLieBiaoImpl().getDBHuanZheLieBiao(patientId);
            retval = DataConverter.convert(patient);
        }
        return retval;
    }

    private void updatePatientDBRiskTag(HuanZheLieBiaoBean.DataBean patient) {
        if (patient == null) {
            LogUtil.e("Can not update local Patient since patient bean is null!");
            return;
        }
        new HuanZheLieBiaoImpl().updatePatientAssessResult(patient);
    }


}
