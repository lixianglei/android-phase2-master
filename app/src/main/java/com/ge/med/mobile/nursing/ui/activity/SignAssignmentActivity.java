package com.ge.med.mobile.nursing.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.ui.adapter.ChoosePatientAdapter;
import com.ge.med.mobile.nursing.ui.adapter.SignAssignmentMenuAdapter;
import com.ge.med.mobile.nursing.ui.component.TitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 体征任务界面
 */
public class SignAssignmentActivity extends MyBaseActivity implements View.OnClickListener {

    // 标题控件
    private TitleBar mTitleBar;
    // 右侧菜单栏
    private LinearLayout ll_menu;
    // 菜单列表
    private ListView lv_menu;
    // 菜单列表适配器
    private SignAssignmentMenuAdapter signAssignmentMenuAdapter;
    // 菜单列表数据源
    private List<String> datas;
    // 选择患者
    private GridView gv_patient;
    // 选择患者适配器
    private ChoosePatientAdapter choosePatientAdapter;

    @Override
    public int setRootView() {
        return R.layout.activity_sign_assignment;
    }

    @Override
    public void initViews() {
        // 右侧菜单栏
        ll_menu = (LinearLayout) findViewById(R.id.ll_menu);
        ll_menu.setOnClickListener(this);
        // 菜单列表
        lv_menu = (ListView) findViewById(R.id.lv_menu);
        // 选择患者
        gv_patient = (GridView) findViewById(R.id.gv_patient);
        // 标题控件
        mTitleBar = new TitleBar(this, "体征任务");
        // 显示菜单按钮
        mTitleBar.setShaiXuanVisible(true);
        mTitleBar.setShaiXuanBehavior(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_menu.setVisibility(View.VISIBLE);
            }
        });
        // 菜单列表数据源
        datas = new ArrayList<>();
        datas.add("2点体温");
        datas.add("6点体温");
        datas.add("10点体温");
        datas.add("14点体温");
        datas.add("18点体温");
        datas.add("22点体温");
        datas.add("测血压");
        datas.add("疼痛");
        datas.add("测血糖");
        datas.add("记尿量");
        datas.add("记出入量");

        // 菜单列表适配器
        signAssignmentMenuAdapter = new SignAssignmentMenuAdapter(this, datas);
        lv_menu.setAdapter(signAssignmentMenuAdapter);
        // 选择患者适配器
        choosePatientAdapter = new ChoosePatientAdapter(this, datas);
        gv_patient.setAdapter(choosePatientAdapter);

        // 菜单列表单机事件
        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ll_menu.setVisibility(View.GONE);
                gv_patient.setVisibility(View.VISIBLE);
            }
        });

        // 选择患者控件单机事件
        gv_patient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 设置选中标记，改变控件显示状态
                choosePatientAdapter.setMarks(position);
                /*// 跳转到体征界面
                Bundle mBundle = new Bundle();
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SINGLE_PATIENT, true);
                // 添加患者信息
                goToActivity(VitalSignSheetActivity.class, mBundle);*/
            }
        });

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

    /**
     * 单机监听
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 菜单页面
            case R.id.ll_menu: {
                ll_menu.setVisibility(View.GONE);
            }
            break;
        }
    }
}
