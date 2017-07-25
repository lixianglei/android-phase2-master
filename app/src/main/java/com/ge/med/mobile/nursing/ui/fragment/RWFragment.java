package com.ge.med.mobile.nursing.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.RWentity;
import com.ge.med.mobile.nursing.ui.activity.HD_YZActivity;
import com.ge.med.mobile.nursing.ui.activity.JiaoBanActivity;
import com.ge.med.mobile.nursing.ui.activity.MainActivity;
import com.ge.med.mobile.nursing.ui.activity.SignAssignmentActivity;
import com.ge.med.mobile.nursing.ui.activity.XSDRActivity;
import com.ge.med.mobile.nursing.ui.activity.XuanJiaoActivity;
import com.ge.med.mobile.nursing.ui.adapter.RwSyLvItemAdapter;
import com.ge.med.mobile.nursing.ui.view.SelfDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RWFragment extends BaseFragment {
    private List<RWentity> rWentities = new ArrayList<>();
    private RwSyLvItemAdapter rwSyLvItemAdapter;
    private SelfDialog selfDialog;

    private ListView mRwFragLv;

    private MainActivity activity;


    public RWFragment() {
        // Required empty public constructor
    }

    @Override
    public int setRootView() {
        return R.layout.fragment_rw;
    }

    @Override
    public void initViews() {
        activity = (MainActivity) mActivitySelf;
        mRwFragLv = (ListView) mRootView.findViewById(R.id.rw_frag_lv);
    }

    @Override
    public void initDatas() {
        rWentities.add(new RWentity(R.mipmap.hd_ren, "核对医嘱(按患者)"));
        rWentities.add(new RWentity(R.mipmap.icon_signs_task, "摆药"));
        rWentities.add(new RWentity(R.mipmap.icon_signs_task, "配液"));
        rWentities.add(new RWentity(R.mipmap.icon_signs_task, "体征任务"));
        rWentities.add(new RWentity(R.mipmap.icon_blood_task_copy, "巡视多人(输液)"));
        rWentities.add(new RWentity(R.mipmap.icon_infusion_task_copy, "巡视多人(输血)"));
        rWentities.add(new RWentity(R.mipmap.icon_shift_task, "交班"));
        rWentities.add(new RWentity(R.mipmap.xuanjiao, "患者宣教"));
        rwSyLvItemAdapter = new RwSyLvItemAdapter(mActivitySelf, rWentities);
        mRwFragLv.setAdapter(rwSyLvItemAdapter);
        mRwFragLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToastShort(rWentities.get(position).getName());
                Bundle bd = new Bundle();
                switch (position) {
                    case 0:
                        activity.mBundle.putString(Constant.BUNDLE_KEY_RW, Constant.BUNDLE_KEY_VALUE_HEDUI);
                        activity.mBundle.putString("hd_yz", "按患者");
                        goToActivity(HD_YZActivity.class, activity.mBundle);
                        break;
                    case 1:
                        activity.mBundle.putString("hd_yz", "按患者");
                        activity.mBundle.putString(Constant.BUNDLE_KEY_RW, Constant.BUNDLE_KEY_VALUE_BAIYAO);
                        goToActivity(HD_YZActivity.class, activity.mBundle);
                        break;
                    case 2:
                        activity.mBundle.putString("hd_yz", "按患者");
                        activity.mBundle.putString(Constant.BUNDLE_KEY_RW, Constant.BUNDLE_KEY_VALUE_PEIYE);
                        goToActivity(HD_YZActivity.class, activity.mBundle);
                        break;
                    // 体征任务
                    case 3:
                        //goToActivity(DRTZActivity.class);
                        goToActivity(SignAssignmentActivity.class);
                        break;
                    case 4:
                        bd.putSerializable(Constant.BUNDLE_KEY_YIZHULEIXING, Constant.BUNDLE_KEY_SHUYE);
                        goToActivity(XSDRActivity.class, bd);
                        break;
                    case 5:
                        bd.putSerializable(Constant.BUNDLE_KEY_YIZHULEIXING, Constant.BUNDLE_KEY_SHUXUE);
                        goToActivity(XSDRActivity.class, bd);
                        break;
                    case 6:
                        goToActivity(JiaoBanActivity.class, bd);
                        break;
                    case 7:
                        // 宣教
                        goToActivity(XuanJiaoActivity.class, bd);
                        break;
//                    case 5:
//
//                        break;

                }
            }
        });

    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }


}
