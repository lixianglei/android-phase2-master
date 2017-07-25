package com.ge.med.mobile.nursing.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.VitalSignDaoImpl;
import com.ge.med.mobile.nursing.db.DBVitalSignSheet;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignSheet;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.activity.EventActivity;
import com.ge.med.mobile.nursing.ui.activity.HZActivity;
import com.ge.med.mobile.nursing.ui.activity.VitalSignSheetActivity;
import com.ge.med.mobile.nursing.ui.adapter.DuorenTizhengLvItemAdapter;
import com.ge.med.mobile.nursing.ui.view.dialog.ConfirmDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 体征
 */
public class TiZhengFragment extends BaseFragment {
    private TextView mXinJianTzTv;
    private ListView mHzLstzLv;
    private DuorenTizhengLvItemAdapter duorenTizhengLvItemAdapter;
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;
    private List<VitalSignSheet> hisVitalSheetList = new ArrayList<>();
    private Bundle mBundle;
    // 事件 按钮
    private RelativeLayout rl_event;


    @Override
    public int setRootView() {
        return R.layout.fragment_ti_zheng;
    }

    @Override
    public void onResume() {
        LogUtil.d("TiZhengFragment.onResume calling...");
        super.onResume();
        mBundle = getBundle();
        if (mBundle != null)
            mSelectedHZ = (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
        else mBundle = new Bundle();
        if (duorenTizhengLvItemAdapter != null) {
            duorenTizhengLvItemAdapter.setDan(true);
            if (duorenTizhengLvItemAdapter.getmVitalDefines() == null) {
                duorenTizhengLvItemAdapter.setVitalSignDefines(new VitalSignDaoImpl().findAllWardDefineFromDB());
            }
            duorenTizhengLvItemAdapter.setVitalSigns(hisVitalSheetList);
            duorenTizhengLvItemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void initViews() {
        LogUtil.d("TiZhengFragment.initViews calling...");
        mXinJianTzTv = (TextView) mRootView.findViewById(R.id.xin_jian_tz_tv);
        mHzLstzLv = (ListView) mRootView.findViewById(R.id.hz_lstz_lv);
        duorenTizhengLvItemAdapter = new DuorenTizhengLvItemAdapter(mActivitySelf);
        mHzLstzLv.setAdapter(duorenTizhengLvItemAdapter);
        // 事件 按钮
        rl_event = (RelativeLayout) mRootView.findViewById(R.id.rl_event);
        // 单机列表进入编辑界面
        mHzLstzLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 跳转到收集体征界面
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SINGLE_PATIENT, true);
                mBundle.remove(Constant.BUNDLE_KEY_VITAL_GROUP);
                // 传递编辑标记
                mBundle.putString("action", "edit");
                // 传递体征记录
                mBundle.putSerializable("vitalSignRecord", (Serializable) hisVitalSheetList.get(position).getSignRecordList());
                goToActivity(VitalSignSheetActivity.class, mBundle);
            }
        });
        // 长按列表删除
        mHzLstzLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                // 删除提示框
                ConfirmDialog confirmDialog = new ConfirmDialog(mActivitySelf, "确认要删除该条记录吗?");
                confirmDialog.showPopupWindow();
                confirmDialog.setListener(new ConfirmDialog.ClickListener() {
                    @Override
                    public void clickOk() {
                        final String sheetId = String.valueOf(hisVitalSheetList.get(position).getSignRecordList().get(0).getSheetid());
                        // 调用接口
                        // 获得当前护士的id
                        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
                        String nurseId = sharePLogin.getUserid();
                        if (sheetId != null && nurseId != null) {
                            RequestBody body = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), "");
                            OkHttpUtils.put().addHeader("User-Agent", "www.gs.com")
                                    .url(URL.URL_VITAL_DELETE_RECORD + sheetId + "?empNo=" + nurseId)
                                    .requestBody(body)
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            e.printStackTrace();
                                            Toast.makeText(mActivitySelf, "删除失败", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            JSONObject object = JSON.parseObject(response);
                                            if (object.getString("status").equals("1")) {
                                                // 删除数据库的数据
                                                DataSupport.deleteAll(DBVitalSignSheet.class, "sheetid = ?", sheetId);
                                                // 从列表中移除
                                                hisVitalSheetList.remove(position);
                                                duorenTizhengLvItemAdapter.notifyDataSetChanged();
                                            }
                                        }
                                    });
                        }
                    }
                });
                return true;
            }
        });

        // 收集体征按钮单机事件
        mXinJianTzTv.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                showToastShort("收集体征");
                mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SINGLE_PATIENT, true);
                mBundle.remove(Constant.BUNDLE_KEY_VITAL_GROUP);
                mBundle.remove("action");
                mBundle.remove("vitalSignRecord");
                goToActivity(VitalSignSheetActivity.class, mBundle);
            }
        });
        // 点击事件按钮，跳转到事件列表界面
        rl_event.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                String patientId = mSelectedHZ.getPatientid();
                String wardId = String.valueOf(mSelectedHZ.getWardid());
                Intent intent = new Intent();
                intent.putExtra("patientId", patientId);
                intent.putExtra("wardId", wardId);
                intent.putExtra("currentPatient", mSelectedHZ);
                intent.setClass(mActivitySelf, EventActivity.class);
                startActivity(intent);
            }
        });
        // 隐藏评估标签
        ((HZActivity) mActivitySelf).labels_layout.setVisibility(View.GONE);
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

    public void setHisVitalSheetList(List<VitalSignSheet> hisVitalSheetList) {
        LogUtil.d("TiZhengFragment.setHisVitalSheetList calling...");
        this.hisVitalSheetList = hisVitalSheetList;
        if (this.hisVitalSheetList != null) {
            LogUtil.d("hisVitalSheetList size is " + this.hisVitalSheetList.size());
            for (VitalSignSheet sheet : this.hisVitalSheetList) {
                if (sheet != null && mSelectedHZ != null)
                    sheet.setCareLevel(mSelectedHZ.getCarelevel());
            }
        }
        if (null != duorenTizhengLvItemAdapter) {
            LogUtil.d("duorenTizhengLvItemAdapter is not null, adapter refreshed!");
            duorenTizhengLvItemAdapter.setVitalSigns(hisVitalSheetList);
            duorenTizhengLvItemAdapter.notifyDataSetChanged();
        }
    }


}
