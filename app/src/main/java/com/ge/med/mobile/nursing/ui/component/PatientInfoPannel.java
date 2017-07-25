package com.ge.med.mobile.nursing.ui.component;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sj.library.base.BaseActivity;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.ui.component.callback.NotifyPatientChange;
import com.ge.med.mobile.nursing.ui.data.RiskDefine;
import com.ge.med.mobile.nursing.ui.view.SearchGroupView;
import com.ge.med.mobile.nursing.ui.view.SelfDialog;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qu on 2016/12/2.
 */
public class PatientInfoPannel {
    private TextView mPatientInfoTextView;
    private LinearLayout mPatientSelectLayout;
    private LinearLayout mPatientInfoLayout;
    private RelativeLayout mOrderStatisticsLayout;
    private TextView mDoctorDiagnosisTextView;
    private TextView mAllergyTextView;
    private TextView mOrderCount;
    private TextView mOrderStatusTextView;
    private ImageView mOrderFilterButton;
    private ImageView mPrevPatientButton;
    private ImageView mNextPatientButton;
    private List<HuanZheLieBiaoBean.DataBean> mPatientList;
    private List<RiskDefine> mRiskDefines;
    private int currentPatient = -1;
    private boolean isSinglePatient = false;
    private NotifyPatientChange callBack;
    private SelfDialog selfDialog;
    private boolean showConfirmDialog;
    private RiskTag mRiskTag;
    private BaseActivity mBaseActivity;
    private SearchGroupView mHuanzheXinxiLl;
    private int deleteWidth;


    public PatientInfoPannel(final BaseActivity activity, List<RiskDefine> defines, List<HuanZheLieBiaoBean.DataBean> patients) {
        this(activity, defines);
        setPatients(patients);
        bindData();
    }

    public PatientInfoPannel(final BaseActivity activity, List<RiskDefine> defines, HuanZheLieBiaoBean.DataBean patient) {
        this(activity, defines);
        currentPatient = 0;
        deleteWidth = 146;
        setPatient(patient);
        setSinglePatient(true);
        bindData();
    }

    public PatientInfoPannel(final BaseActivity activity, List<RiskDefine> defines) {
        mBaseActivity = activity;
        mRiskTag = new RiskTag(activity);
        this.mRiskDefines = defines;
        showConfirmDialog = false;
        selfDialog = new SelfDialog(activity);
        selfDialog.setTitle("确认操作");
        selfDialog.setMessage("切换新的患者将会放弃当前所有操作，是否继续？");
        selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });

        mPatientInfoTextView = (TextView) activity.findViewById(R.id.hz_jiben_xinxi_tv);
        mPatientSelectLayout = (LinearLayout) activity.findViewById(R.id.patientSelectlayoutId);
        mPatientInfoLayout = (LinearLayout) activity.findViewById(R.id.patientInfolayoutId);
        mOrderStatisticsLayout = (RelativeLayout) activity.findViewById(R.id.patientStatisticsId);
        mDoctorDiagnosisTextView = (TextView) activity.findViewById(R.id.hz_ys_zd_tv);
        mAllergyTextView = (TextView) activity.findViewById(R.id.hz_gms_tv);
        mOrderCount = (TextView) activity.findViewById(R.id.hz_yz_shu_tv);
        mOrderStatusTextView = (TextView) activity.findViewById(R.id.hz_yz_shu_weizhixing);
        mOrderFilterButton = (ImageView) activity.findViewById(R.id.hz_yz_xiao_jian_tou);
        mPrevPatientButton = (ImageView) activity.findViewById(R.id.drtz_back_imgv);
        mNextPatientButton = (ImageView) activity.findViewById(R.id.drtz_next_imgv);
        mHuanzheXinxiLl = (SearchGroupView) activity.findViewById(R.id.huanzhe_xinxi_ll);

        mPrevPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPatient == 0) {
                    mBaseActivity.showToastShort("没有上一个了！");
                    return;
                }
                if (showConfirmDialog) {
                    selfDialog.setYesOnclickListener("确认", new SelfDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            selfDialog.dismiss();
                            prevPatient();
                            if (callBack != null) callBack.onPatientChange();
                        }
                    });
                    selfDialog.show();
                } else {
                    prevPatient();
                    if (callBack != null) callBack.onPatientChange();
                }
            }
        });
        mNextPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPatient == mPatientList.size() - 1) {
                    mBaseActivity.showToastShort("没有下一个了！");
                    return;
                }
                if (showConfirmDialog) {
                    selfDialog.setYesOnclickListener("确认", new SelfDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            selfDialog.dismiss();
                            nextPatient();
                            if (callBack != null) callBack.onPatientChange();
                        }
                    });
                    selfDialog.show();
                } else {
                    nextPatient();
                    if (callBack != null) callBack.onPatientChange();
                }
            }
        });
        showHideControls();
    }

    public void showPrevMyDialog() {
        selfDialog.setYesOnclickListener("确认", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                selfDialog.dismiss();
                prevPatient();
                if (callBack != null) callBack.onPatientChange();
            }
        });
        selfDialog.show();
    }

    public void showNextMyDialog() {
        selfDialog.setYesOnclickListener("确认", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                selfDialog.dismiss();
                nextPatient();
                if (callBack != null) callBack.onPatientChange();
            }
        });
        selfDialog.show();
    }

    public Integer changePatient(String patientId) {
        Integer retval = null;
        if (patientId == null || patientId.trim().isEmpty()) {
            LogUtil.e("Can not change patient since patientId is null or empty!");
            return retval;
        }
        LogUtil.d("Trying to change patient in patient pannel for patientId " + patientId);
        patientId = patientId.trim();
        if (mPatientList == null) {
            LogUtil.e("Can not change patient since patient list in pannel is null!");
            return retval;
        }
        HuanZheLieBiaoBean.DataBean dataBean = null;
        for (int i = 0; i < mPatientList.size(); i++) {
            dataBean = mPatientList.get(i);
            if (dataBean != null && dataBean.getPatientid() != null) {
                if (patientId.equals(dataBean.getPatientid().trim())) {
                    currentPatient = i;
                    doPatientChange();
                    retval = Integer.valueOf(i);
                    LogUtil.d("Found patient[id:" + patientId + ",name:" + dataBean.getName() + "] at position[" + i + "].");
                    break;
                }
            } else LogUtil.e("Patient or its id is null in patient list position[" + i + "]!");
        }
        if (retval == null) {
            LogUtil.i("No data found for patient[id:" + patientId + ",name:" + dataBean.getName() + "] in patient pannel");
        }
        return retval;
    }

    public Integer changePatient(HuanZheLieBiaoBean.DataBean patient) {
        Integer retval = null;
        if (patient == null || patient.getPatientid() == null) {
            LogUtil.e("Can not change patient since patient or its id is null!");
            return retval;
        }
        return changePatient(patient.getPatientid());
    }


    public void setPatient(HuanZheLieBiaoBean.DataBean patient) {
        mPatientList = new ArrayList<>();
        mPatientList.add(patient);
    }

    public boolean prevPatient() {
        if (mPatientList == null || mPatientList.size() <= 0) {
            return false;
        }

        currentPatient--;
        if (currentPatient < 0) {
            currentPatient = 0;
            return false;
        } else {
            doPatientChange();
        }
        return true;
    }

    public boolean nextPatient() {
        if (mPatientList == null || mPatientList.size() <= 0) {
            return false;
        }
        currentPatient++;
        if (currentPatient >= mPatientList.size()) {
            currentPatient = mPatientList.size() - 1;
            return false;
        }
        doPatientChange();
        return true;
    }

    public void needConfirmWhileChangePatient(boolean need) {
        showConfirmDialog = need;
    }

    public void needConfirmWhileChangePatient(boolean need, String content) {
        needConfirmWhileChangePatient(need);
        selfDialog.setMessage(content);
    }

    public void setOnPatientChangeCallback(NotifyPatientChange changeCallback) {
        this.callBack = changeCallback;
    }

    private void showHideControls() {
        if (isSinglePatient) {
            mPatientSelectLayout.setVisibility(View.GONE);
            mPatientInfoLayout.setVisibility(View.VISIBLE);
            mOrderStatisticsLayout.setVisibility(View.VISIBLE);
            mPrevPatientButton.setVisibility(View.GONE);
            mNextPatientButton.setVisibility(View.GONE);
        } else {
            if (currentPatient == -1) {
                mPatientSelectLayout.setVisibility(View.VISIBLE);
                mPatientInfoLayout.setVisibility(View.GONE);
            } else {
                mPatientSelectLayout.setVisibility(View.GONE);
                mPatientInfoLayout.setVisibility(View.VISIBLE);
            }
            mOrderStatisticsLayout.setVisibility(View.GONE);
            mPrevPatientButton.setVisibility(View.VISIBLE);
            mNextPatientButton.setVisibility(View.VISIBLE);
        }

    }

    private void doPatientChange() {
        bindData();
        showHideControls();
    }

    public void setSinglePatient(boolean isSingle) {
        this.isSinglePatient = isSingle;
        showHideControls();
    }

    public void setPatients(List<HuanZheLieBiaoBean.DataBean> patients) {
        mPatientList = patients;
    }

    public void showHideTipLayout(boolean show) {
        if (mOrderStatisticsLayout != null) {
            if (show) {
                mOrderStatisticsLayout.setVisibility(View.VISIBLE);
            } else {
                mOrderStatisticsLayout.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void setTipNumber(int tipNubmer) {
        if (null != mOrderCount) mOrderCount.setText(tipNubmer + "");
    }

    public void setTipName(String name) {
        if (null != mOrderStatusTextView) mOrderStatusTextView.setText(name);
    }

    public void setTipBehavior(View.OnClickListener listener) {
        if (null != mOrderFilterButton) {
            if (null != listener) {
                mOrderFilterButton.setVisibility(View.VISIBLE);
                mOrderFilterButton.setOnClickListener(listener);
            } else mOrderFilterButton.setVisibility(View.GONE);
        }
    }

    public HuanZheLieBiaoBean.DataBean getCurrentPatient() {
        if (mPatientList == null || mPatientList.size() <= 0
                || currentPatient < 0 || currentPatient >= mPatientList.size()) return null;
        return mPatientList.get(currentPatient);
    }

    public List<HuanZheLieBiaoBean.DataBean> getPatients() {
        return mPatientList;
    }

    private void bindData() {
        if (null == mPatientList || mPatientList.size() <= 0 || currentPatient < 0 || currentPatient >= mPatientList.size()) {
            LogUtil.e("Can not bind data since patient(s) information is not correct!");
            return;
        }
        HuanZheLieBiaoBean.DataBean mPatient = mPatientList.get(currentPatient);
        if (mPatient == null) {
            LogUtil.e("Can not bind data since patient in current postion[" + currentPatient + "] is null!");
            return;
        }
        mPatientInfoTextView.setText(mPatient.getSex() + "," + mPatient.getAge() + "岁 " + mPatient.getMrnno());


        //风险评估设置
        mRiskTag.setData(mRiskDefines, mPatient, mHuanzheXinxiLl, deleteWidth, getClickView());
        //医生主要诊断
        mDoctorDiagnosisTextView.setText("医生：" + mPatient.getDoctorname() + "    主要诊断：" + mPatient.getDiagnosis());
        //过敏史
        mAllergyTextView.setText("过敏史：" + mPatient.getAllergyhistory());
    }

    //添加一个textView
    public TextView getClickView() {
        ViewGroup viewById = (ViewGroup) mBaseActivity.getWindow().getDecorView().findViewById(android.R.id.content);
        LayoutInflater layoutInflater = mBaseActivity.getLayoutInflater();
        View inflate = layoutInflater.inflate(R.layout.macth_textview, (ViewGroup) viewById);
        TextView viewById1 = (TextView) mBaseActivity.findViewById(R.id.click_view);
        return viewById1;
    }
}
