package com.ge.med.mobile.nursing.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.dao.entity.NursingLieBiaoBean;
import com.ge.med.mobile.nursing.db.DBNursingLieBiao;
import com.ge.med.mobile.nursing.ui.activity.HZActivity;
import com.ge.med.mobile.nursing.ui.activity.JiaoBanSJActivity;
import com.ge.med.mobile.nursing.ui.activity.PG_DTQuestionActivity;
import com.ge.med.mobile.nursing.ui.adapter.JiLuLvItemAdapter;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class JLFragment extends BaseFragment {
    private ListView mHzJiLuLv;
    private JiLuLvItemAdapter jiLuLvItemAdapter;
    private HZActivity activity;
    private String wardId;
    private Bundle mBundle;
    private DBNursingLieBiao mDBNursingLieBiao;
    private ArrayList<String> jiLuLieBiao;
    private NursingLieBiaoBean nursingLieBiaoBean;


    @Override
    public int setRootView() {
        return R.layout.fragment_jl;
    }

    @Override
    public void initViews() {
        activity = (HZActivity) getActivity();
        mBundle = activity.getmBundle();
        wardId = activity.getWardId();
        mDBNursingLieBiao = DataSupport.findFirst(DBNursingLieBiao.class);
        if (mDBNursingLieBiao != null && mDBNursingLieBiao.getJsonString() != null) {
            try {
                nursingLieBiaoBean = JSON.parseObject(mDBNursingLieBiao.getJsonString(), NursingLieBiaoBean.class);
            } catch (Exception e) {
                LogUtil.e(e.getMessage());
            }
            jiLuLieBiao = new ArrayList<String>();
            if (nursingLieBiaoBean != null && nursingLieBiaoBean.getData() != null) {
                if (nursingLieBiaoBean.getData().getAssessDefineList() != null
                        && nursingLieBiaoBean.getData().getAssessDefineList().size() > 0) {
                    for (AssessDefine.DataBean assessDefineListBean : nursingLieBiaoBean.getData().getAssessDefineList()) {
                        jiLuLieBiao.add(assessDefineListBean.getName());
                    }

                }
                if (nursingLieBiaoBean.getData().getNursingRecordDefineList() != null
                        && nursingLieBiaoBean.getData().getAssessDefineList().size() > 0) {
                    for (NursingLieBiaoBean.DataBean.NursingRecordDefineListBean nursingRecordDefineListBean
                            : nursingLieBiaoBean.getData().getNursingRecordDefineList()) {
                        jiLuLieBiao.add(nursingRecordDefineListBean.getName());
                    }
                }
            }

        }
        mHzJiLuLv = (ListView) mRootView.findViewById(R.id.hz_ji_lu_lv);
        jiLuLvItemAdapter = new JiLuLvItemAdapter(mActivitySelf, jiLuLieBiao);
        mHzJiLuLv.setAdapter(jiLuLvItemAdapter);
        mHzJiLuLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if ("护理记录".equals(jiLuLieBiao.get(position))) {
                    mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.BUNDLE_ACTIVITY_JILU_CHANGGUI_HULI);
                    goToActivity(JiaoBanSJActivity.class, mBundle);
                } else {
                    if (nursingLieBiaoBean != null && nursingLieBiaoBean.getData() != null) {
                        if (nursingLieBiaoBean.getData().getAssessDefineList() != null
                                && nursingLieBiaoBean.getData().getAssessDefineList().size() > 0) {
                            for (AssessDefine.DataBean assessDefineListBean : nursingLieBiaoBean.getData().getAssessDefineList()) {
                                if (jiLuLieBiao.get(position).equals(assessDefineListBean.getName())) {
                                    mBundle.putSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE, assessDefineListBean);
                                    goToActivity(PG_DTQuestionActivity.class, mBundle);
                                }
                            }

                        }
                    }
                }
            }
        });
        // 隐藏评估标签
        activity.labels_layout.setVisibility(View.GONE);
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
}
