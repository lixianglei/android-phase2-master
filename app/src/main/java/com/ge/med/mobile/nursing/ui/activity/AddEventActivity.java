package com.ge.med.mobile.nursing.ui.activity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.forjson.entity.EventBean;
import com.ge.med.mobile.nursing.forjson.entity.EventDefineBean;
import com.ge.med.mobile.nursing.forjson.entity.EventSubmitBean;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.component.DateTimeControl;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.view.dialog.EventDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Calendar;
import java.util.List;

import okhttp3.Call;

/**
 * 添加、编辑事件界面
 */
public class AddEventActivity extends MyBaseActivity implements View.OnClickListener {
    // 时间控件
    private DateTimeControl mDateTimeView;
    // 设置事件按钮
    private TextView tv_set_event_name;
    // 保存按钮
    private TextView tv_save;
    // 事件名称
    private TextView tv_event_name;

    // 编辑、添加事件的标识
    private String action;
    // 事件id
    private int eventId;
    // 事件名称数据源
    private List<EventDefineBean.DataBean> datas;
    // 单条事件信息：编辑事件时使用
    private EventBean.DataBean eventInfo;
    // 当前患者
    private HuanZheLieBiaoBean.DataBean currentPatient;
    // 患者面板控件
    private PatientInfoPannel mPatientPanel;
    // 标题控件
    private TitleBar mTitleBar;


    @Override
    public int setRootView() {
        return R.layout.activity_add_event;
    }

    @Override
    public void initViews() {
        // 获取从TiZhengFragment界面传递而来的数据
        currentPatient = (HuanZheLieBiaoBean.DataBean) getIntent().getSerializableExtra("currentPatient");
        action = getIntent().getStringExtra("action");
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
        // 时间控件
        mDateTimeView = new DateTimeControl(this);
        mDateTimeView.setVisible(true);
        // 设置事件按钮
        tv_set_event_name = (TextView) findViewById(R.id.tv_set_event_name);
        // 设置事件按钮 单机事件
        tv_set_event_name.setOnClickListener(this);
        // 保存按钮
        tv_save = (TextView) findViewById(R.id.tv_save);
        tv_save.setOnClickListener(this);
        // 事件名称
        tv_event_name = (TextView) findViewById(R.id.tv_event_name);

        // 获取事件定义
        getEventDefine();
        // 编辑界面则需要获取当前事件的相关数据
        if (action != null && action.equals("edit")) {
            // 事件id
            eventId = getIntent().getIntExtra("eventId", -1);
            // 获取事件信息
            getEventInfo();
        }

    }

    /**
     * 获取事件定义
     */
    private void getEventDefine() {
        OkHttpUtils.get().url(URL.URL_EVENT_GET_DEFINE).addHeader("User-Agent", "www.gs.com")
                .addParams("wardId", String.valueOf(currentPatient.getWardid()))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        EventDefineBean eventDefineBean = JSON.parseObject(response, EventDefineBean.class);
                        datas = eventDefineBean.getData();
                    }
                });
    }

    /**
     * 获取当前事件的相关数据
     */
    private void getEventInfo() {
        if (currentPatient.getPatientid() != null && eventId != -1) {
            OkHttpUtils.get().url(URL.URL_EVENT_GET_RECORD).addHeader("User-Agent", "www.gs.com")
                    .addParams("patientId", currentPatient.getPatientid())
                    .addParams("id", String.valueOf(eventId))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            EventBean eventBean = JSON.parseObject(response, EventBean.class);
                            // 获得事件信息
                            eventInfo = eventBean.getData();
                            long time = eventInfo.getEventtime();
                            Calendar cal = Calendar.getInstance();
                            cal.setTimeInMillis(time);
                            int year = cal.get(Calendar.YEAR);
                            int month = cal.get(Calendar.MONTH) + 1;
                            int day = cal.get(Calendar.DAY_OF_MONTH);
                            int hour = cal.get(Calendar.HOUR_OF_DAY);
                            int minute = cal.get(Calendar.MINUTE);
                            // 设置要显示的时间
                            mDateTimeView.setDateTime(year, month, day, hour, minute);
                            // 设置事件名称
                            tv_event_name.setText(eventInfo.getDefineEventName());
                        }
                    });
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


    /**
     * 单机事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 设置事件
            case R.id.tv_set_event_name: {
                // 弹出界面进行选择
                if (datas != null && datas.size() > 0) {
                    EventDialog eventDialog = new EventDialog(this, datas);
                    // 如果是编辑，则需要设置默认选中的项
                    if (action != null && action.equals("edit")) {
                        eventDialog.setSelectIndex(eventInfo.getEventdefineid());
                    }
                    // 显示弹窗
                    eventDialog.showPopupWindow();
                    // 点击确定按钮监听
                    eventDialog.setListener(new EventDialog.ClickListener() {
                        @Override
                        public void clickOk(EventDefineBean.DataBean selectItem) {
                            // 设置当前的事件定义id
                            if (action != null && action.equals("edit")) {
                                eventInfo.setEventdefineid(selectItem.getId());
                            } else {
                                if (eventInfo == null) {
                                    EventBean eventBean = new EventBean();
                                    eventInfo = eventBean.new DataBean();
                                }
                                // 新建事件，将id置为0
                                eventInfo.setId(0);
                                eventInfo.setEventdefineid(selectItem.getId());
                            }
                            // 设置名称
                            tv_event_name.setText(selectItem.getEventname());
                        }
                    });
                }
            }
            break;
            // 保存按钮
            case R.id.tv_save: {
                if (eventInfo == null) {
                    Toast.makeText(this,"请设置事件名称",Toast.LENGTH_SHORT).show();
                    return;
                }
                String jsonString = null;
                try {
                    String userId = new SharePLogin(this).getUserid();
                    // 创建事件提交对象
                    EventSubmitBean eventSubmitBean = new EventSubmitBean(eventInfo.getId(), currentPatient.getPatientid(), eventInfo.getEventdefineid(), Long.parseLong(mDateTimeView.getLong()), userId);
                    jsonString = JSONObject.toJSONString(eventSubmitBean);
                    jsonString = "[" + jsonString + "]";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 调用提交接口
                OkHttpUtils.post().url(URL.URL_EVENT_SUBMIT_RECORD).addHeader("User-Agent", "www.gs.com")
                        .addParams("jsonString", jsonString)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                finish();
                            }
                        });
            }
            break;
        }
    }
}
