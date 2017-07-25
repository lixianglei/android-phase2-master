package com.ge.med.mobile.nursing.ui.component;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sj.library.base.BaseActivity;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.ui.data.RiskDefine;
import com.ge.med.mobile.nursing.ui.view.SearchGroupView;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Qu on 2016/12/17.
 */
public class RiskTag {
    public static final int MAX_TAG_COUNT = 10;
    private LinearLayout mAssessTagLayout;
    private TextView[] mAssessTags;
    private static int[] mShapes;
    private HuanZheLieBiaoBean.DataBean mPatient;
    private List<RiskDefine> mWardRiskDefineList;
    private int firstPos = 0;

    public RiskTag(final BaseActivity activity, List<RiskDefine> defines, HuanZheLieBiaoBean.DataBean patient, SearchGroupView view, int deleteWidth, TextView clickView) {
        this(activity);
        setData(defines, patient, view, deleteWidth, clickView);
    }

    public RiskTag(final BaseActivity activity) {
        mAssessTagLayout = (LinearLayout) activity.findViewById(R.id.hz_pg_ll);
        if (null != mAssessTagLayout) {
            initAssessTags();
        }
        initShapes(activity);
    }

    private static void initShapes(BaseActivity activity) {
        if (activity != null && mShapes == null) {
            mShapes = new int[5];
            mShapes[0] =R.drawable.sy_hljb_pg_shape_1;
            mShapes[1] = R.drawable.sy_hljb_pg_shape_2;
            mShapes[2] = R.drawable.sy_hljb_pg_shape_3;
            mShapes[3] =R.drawable.sy_hljb_pg_shape_4;
            mShapes[4] = R.drawable.sy_hljb_pg_shape_5;
        }
    }

    public void setView(View view) {
        if (null == view) {
            LogUtil.e("Can not find set view since given view is null!");
            return;
        }
        mAssessTagLayout = (LinearLayout) view.findViewById(R.id.hz_pg_ll);
        if (null == mAssessTagLayout) {
            LogUtil.e("Can not find assess tag controls in given View:" + view.getClass().getName());
            return;
        }
        initAssessTags();
        bindData();
    }

    public void setData(List<RiskDefine> defines, HuanZheLieBiaoBean.DataBean patient, SearchGroupView view, int deleteWidth, TextView clickView) {
        setPatient(patient);
        setRiskDefine(defines);
        bindData();
        setOnClick(view, patient, deleteWidth, clickView);
    }

    private void setOnClick(final SearchGroupView view, final HuanZheLieBiaoBean.DataBean patient, final int deleteWidth, final TextView clickView) {
        mAssessTagLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLongAssessresut(view, patient, deleteWidth, clickView);
            }
        });
    }

    private void initAssessTags() {
        if (mAssessTagLayout != null) mAssessTagLayout.setVisibility(View.VISIBLE);
        mAssessTags = new TextView[MAX_TAG_COUNT];
        for (int i = 0; i < MAX_TAG_COUNT; i++) {
            mAssessTags[i] = (TextView) mAssessTagLayout.findViewWithTag("patient_assess_tag" + (i + 1));
            LogUtil.d("init assess tag[" + i + "] as " + mAssessTags[i]);
            // mAssessTags[i].setVisibility(View.GONE);
        }
    }

    private void setPatient(HuanZheLieBiaoBean.DataBean patient) {
        this.mPatient = patient;
    }

    private void setRiskDefine(List<RiskDefine> defines) {
        this.mWardRiskDefineList = defines;
    }

    public static void setIllnessTag(TextView view, String illState, BaseActivity activity) {
        initShapes(activity);
        if (Constant.ILLNESS_STATE_NAME_HEAVY.equals(illState) ||
                Constant.ILLNESS_STATE_NAME_FATAL.equals(illState)) {
            view.setText(illState.substring(1, 2));
            view.setVisibility(View.VISIBLE);
            setIllTagColor(view, illState);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }

    //    public void setIllnessTag(TextView view, String illState){
//        view.setText(illState);
//        view.setVisibility(View.VISIBLE);
//        setIllTagColor(view, illState);
//    }
//    public String getIllnessName(Integer illState){
//        if (illState == null) return null;
//        String retval = null;
//        switch (illState.intValue()){
//            case Constant.ILLNESS_STATE_GENERAL:
//                retval = Constant.ILLNESS_STATE_NAME_GENERAL;
//                break;
//            case Constant.ILLNESS_STATE_HEAVY:
//                retval = Constant.ILLNESS_STATE_NAME_HEAVY;
//                break;
//            case Constant.ILLNESS_STATE_FATAL:
//                retval = Constant.ILLNESS_STATE_NAME_FATAL;
//                break;
//        }
//        return retval;
//    }
    private static void setIllTagColor(TextView view, String illState) {
        if (illState == null) return;
        switch (illState) {
            case Constant.ILLNESS_STATE_NAME_HEAVY:
                view.setVisibility(View.VISIBLE);
                view.setBackgroundResource(mShapes[2]);
                view.setTextColor(Color.parseColor("#f6af39"));
                break;
            case Constant.ILLNESS_STATE_NAME_FATAL:
                view.setVisibility(View.VISIBLE);
                view.setBackgroundResource(mShapes[3]);
                view.setTextColor(Color.parseColor("#881927"));
                break;
            case Constant.ILLNESS_STATE_NAME_GENERAL:
            default:
                view.setVisibility(View.GONE);
//                view.setBackground(mShapes[0]);
//                view.setTextColor(Color.parseColor("#adadad"));
                break;
        }
    }

    private void initByDefines() {
        if (mWardRiskDefineList == null || mWardRiskDefineList.size() <= 0) {
            LogUtil.i("Can not set Risk Tags since definition of ward is empty!");
            return;
        }
        firstPos = 0;
        if (mPatient != null && mPatient.getIlldetial() != null
                && (Constant.ILLNESS_STATE_NAME_HEAVY.equals(mPatient.getIlldetial())
                || Constant.ILLNESS_STATE_NAME_FATAL.equals(mPatient.getIlldetial()))) {
            firstPos = 1;
        }
        try {
            setIllnessTag(mAssessTags[0], mPatient.getIlldetial(), null);
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
        for (int iloop = firstPos; iloop < MAX_TAG_COUNT; iloop++) {
            try {
                mAssessTags[iloop].setVisibility(View.GONE);
            } catch (Exception e) {
                LogUtil.d(e.getMessage());
            }
            LogUtil.d("set assess tag[" + iloop + "] as " + mAssessTags[iloop].getVisibility());
        }
    }

    private void setPatientTags() {
        if (mPatient == null || mPatient.getLabelList() == null || mPatient.getLabelList().size() <= 0) {
            LogUtil.d("No risk need to be set since patient is null or assess result is null.");
            return;
        }
        List<HuanZheLieBiaoBean.DataBean.LabelList> assres = mPatient.getLabelList();
        if (this.mWardRiskDefineList != null && this.mWardRiskDefineList.size() > 1) {
            Collections.sort(this.mWardRiskDefineList, new Comparator<RiskDefine>() {
                @Override
                public int compare(RiskDefine lhs, RiskDefine rhs) {
                    if (lhs != null && rhs != null && lhs.getRankNo() != null && rhs.getRankNo() != null) {
                        if (lhs.getRankNo().intValue() > rhs.getRankNo().intValue()) return 1;
                        else if (lhs.getRankNo().intValue() < rhs.getRankNo().intValue()) return -1;
                    }
                    return 0;
                }
            });
        }
        int ipos = firstPos;
        String result = null;
        String score = null;
        for (RiskDefine define : mWardRiskDefineList) {
            for (HuanZheLieBiaoBean.DataBean.LabelList results : assres) {
                if (results != null) {
                    result = results.getRiskName();
                    score = results.getScore();
                    if (score != null && score.trim().length() > 0) {
                        score = " " + score;
                    }
                    if (result.equals(define.getRiskName())) {
                        mAssessTags[ipos].setTextColor(Color.parseColor("#adadad"));
                        mAssessTags[ipos].setBackgroundResource(mShapes[0]);
                        mAssessTags[ipos].setVisibility(View.VISIBLE);
                        LogUtil.d("set patient[]'s result[" + result + "] assess tag["
                                + define.getRiskName() + "] at postion [" + ipos + "] as " + mAssessTags[ipos].getVisibility());
                        if (define.getShortName() == null || define.getShortName().trim().isEmpty()) {
                            mAssessTags[ipos].setText(define.getRiskName().substring(0, 1) + score);
                        } else mAssessTags[ipos].setText(define.getShortName() + score);
                        ipos++;
                        break;
                    }
                }
            }
            if (ipos >= MAX_TAG_COUNT - firstPos) break;
        }
    }

    private void bindData() {
        initByDefines();
        setPatientTags();
    }

    public void showLongAssessresut(final SearchGroupView view, HuanZheLieBiaoBean.DataBean patient, int deleteWidth, TextView clickView) {
        if (view != null && clickView != null) {
            if (deleteWidth == 0) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, 0);
                view.setLayoutParams(layoutParams);
            }
            view.setVisibility(view.VISIBLE);
            view.initViews(getRiskLongName(patient), null, deleteWidth);
            clickView.setVisibility(android.view.View.VISIBLE);
            clickView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.setVisibility(android.view.View.GONE);
                    v.setVisibility(android.view.View.GONE);
                }
            });
        }
    }

    public String[] getRiskLongName(HuanZheLieBiaoBean.DataBean patient) {
        List<HuanZheLieBiaoBean.DataBean.LabelList> assresList = patient.getLabelList();
        String[] assres = new String[0];
        if (assresList != null && assresList.size() > 0) {
            assres = new String[assresList.size()];
            String str = "";
            for (int i = 0; i < assresList.size(); i++) {
                if (assresList.get(i).getScore() != null && assresList.get(i).getScore().trim().length() > 0) {
                    str = " " + assresList.get(i).getScore().trim();
                }
                assres[i] = assresList.get(i).getRiskName() + str;
            }
        }
        if (patient.getIlldetial() == null) return assres;
        if (Constant.ILLNESS_STATE_NAME_HEAVY.equals(patient.getIlldetial())
                || Constant.ILLNESS_STATE_NAME_FATAL.equals(patient.getIlldetial())) {
            assres = getAssres(assres, patient.getIlldetial());
        }
        return assres;
    }

    //在数组中增加一个元素 如果有 风险标签则加入到数组的第0号位置
    private String[] getAssres(String[] str, String illdetial) {
        for (String elementA : str) {
            System.out.print(elementA + " ");
        }
        //增加ruby
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }
        list.add(0, illdetial); //list.add("ruby")
        System.out.println();
        String[] newStr = list.toArray(new String[1]); //返回一个包含所有对象的指定类型的数组
        return newStr;
    }
}
