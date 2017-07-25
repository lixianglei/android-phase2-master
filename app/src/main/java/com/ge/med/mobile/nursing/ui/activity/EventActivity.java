package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.forjson.entity.EventsBean;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.EventAdapter;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.view.dialog.ConfirmDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 事件列表
 */
public class EventActivity extends MyBaseActivity implements View.OnClickListener {

    // 事件列表
    private ListView lv_event;
    // 事件列表适配器
    private EventAdapter eventAdapter;
    // 添加事件按钮
    private RelativeLayout rl_add_event;

    // 事件列表数据源
    private List<EventsBean.DataBean> datas;
    // 用户id
    private String userId;
    // 当前患者
    private HuanZheLieBiaoBean.DataBean currentPatient;
    // 患者面板控件
    private PatientInfoPannel mPatientPanel;
    // 标题控件
    private TitleBar mTitleBar;

    @Override
    public int setRootView() {
        return R.layout.activity_event;
    }

    @Override
    public void initViews() {
        // 获取从TiZhengFragment界面传递而来的数据
        currentPatient = (HuanZheLieBiaoBean.DataBean) getIntent().getSerializableExtra("currentPatient");

        // 初始化
        initialization();
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
     * 初始化操作
     */
    private void initialization() {
        // 标题控件
        mTitleBar = new TitleBar(this, currentPatient);
        mTitleBar.setShowConfirmDialog(false);
        // 患者面板
        mPatientPanel = new PatientInfoPannel(this, new AssessDaoImpl().findAllRiskDefine());
        mPatientPanel.setSinglePatient(true);
        mPatientPanel.setPatient(currentPatient);
        mPatientPanel.changePatient(currentPatient);
        mPatientPanel.setTipBehavior(null);
        mPatientPanel.showHideTipLayout(false);
        // 事件列表数据源
        datas = new ArrayList<>();
        // 事件列表
        lv_event = (ListView) findViewById(R.id.lv_event);
        // 事件列表适配器
        eventAdapter = new EventAdapter(this, datas);
        // 列表设置适配器
        lv_event.setAdapter(eventAdapter);
        // 添加事件按钮
        rl_add_event = (RelativeLayout) findViewById(R.id.rl_add_event);
        // 添加事件按钮设置监听
        rl_add_event.setOnClickListener(this);
        // 长按删除
        lv_event.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                // 根据defineReadOnly来判断是否可以删除，值为“1”则不可以删除
                String defineReadOnly = datas.get(position).getDefineReadOnly();
                if (defineReadOnly != null && defineReadOnly.equals("1")) {
                    Toast.makeText(EventActivity.this, "此数据不可删除", Toast.LENGTH_SHORT).show();
                } else {
                    // 弹出提示框是否确认删除
                    ConfirmDialog confirmDialog = new ConfirmDialog(EventActivity.this, "确认要删除该条记录吗?");
                    confirmDialog.showPopupWindow();
                    // 确定按钮点击监听
                    confirmDialog.setListener(new ConfirmDialog.ClickListener() {
                        @Override
                        public void clickOk() {
                            // 调用接口删除数据
                            OkHttpUtils.post().url(URL.URL_EVENT_DELETE).addHeader("User-Agent", "www.gs.com")
                                    .addParams("id", String.valueOf(datas.get(position).getId()))
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            e.printStackTrace();
                                            Toast.makeText(EventActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            // 删除成功后从列表中移除
                                            datas.remove(position);
                                            eventAdapter.notifyDataSetChanged();
                                        }
                                    });
                        }
                    });
                }
                return true;
            }
        });
        // 单机进入编辑页面进行修改
        lv_event.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 根据defineReadOnly来判断是否可以修改，值为“1”则不可以修改
                String defineReadOnly = datas.get(position).getDefineReadOnly();
                if (defineReadOnly != null && defineReadOnly.equals("1")) {
                    Toast.makeText(EventActivity.this, "此数据不可修改", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                // 添加标识，用于AddEventActivity界面判断是添加还是编辑
                intent.putExtra("action", "edit");
                intent.putExtra("currentPatient", currentPatient);
                intent.putExtra("eventId", datas.get(position).getId());
                intent.setClass(EventActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

        // 获得用户id
        SharePLogin sharePLogin = new SharePLogin(this);
        userId = sharePLogin.getUserid();
        // 获取数据源
        getEventList(userId);

    }

    /**
     * 从服务器获取事件列表
     */
    private void getEventList(String userId) {
        OkHttpUtils.get().url(URL.URL_EVENT_GET_LIST).addHeader("User-Agent", "www.gs.com")
                .addParams("patientId", currentPatient.getPatientid())
                .addParams("userId", userId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        EventsBean eventsBean = JSON.parseObject(response, EventsBean.class);
                        datas.clear();
                        datas.addAll(eventsBean.getData());
                        eventAdapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 单机事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 添加事件按钮
            case R.id.rl_add_event: {
                // 跳转到添加事件界面
                Intent intent = new Intent();
                // 添加标识，用于AddEventActivity界面判断是添加还是编辑
                intent.putExtra("action", "add");
                intent.putExtra("currentPatient", currentPatient);
                intent.setClass(this, AddEventActivity.class);
                startActivity(intent);
            }
            break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 获取数据源
        if (userId != null) {
            getEventList(userId);
        }
    }
}
