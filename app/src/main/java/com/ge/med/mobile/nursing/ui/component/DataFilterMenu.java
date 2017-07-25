package com.ge.med.mobile.nursing.ui.component;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sj.library.base.BaseActivity;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.User;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignWardDefine;
import com.ge.med.mobile.nursing.mySyncTask.ErWeiMaSync;
import com.ge.med.mobile.nursing.ui.adapter.FilterItemAdapter;
import com.ge.med.mobile.nursing.ui.component.callback.NotifyFilterSelected;
import com.ge.med.mobile.nursing.ui.view.SelfDialog;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qu on 2016/12/2.
 */
public class DataFilterMenu{
    //患者床号 名称
    public static final int FILTER_TYPE_PATIENT = 0;
    //医嘱类型 类型名称；未执行数量
    public static final int FILTER_TYPE_DOCTOR_ORDER_TYPE = 1;
    // 全部患者；我的患者
    public static final int FILTER_TYPE_PATIENT_GROUP = 2;
    // 护理级别
    public static final int FILTER_TYPE_CARE_LEVEL = 3;
    // 床号 排序icon
    public static final int FILTER_TYPE_BED_NO = 4;
    // 床号 体征
    public static final int FILTER_TYPE_VITAL_SIGN = 5;

    private LinearLayout mNurseLayout;
    private LinearLayout mFilterLayout;
    private TextView mLeftBlankTextView;
    private ImageView mQRCodeImage;
    private TextView mSettingTextView;
    private TextView mQuitTextView;
    private TextView mNurseNameTextView;
    public TextView mNurseIdTextView;
    private TextView mItemBlankTextView;
    private TextView mSettingLine;
    private TextView mQuitLine;
    private ListView mFilterListView;
    private FilterItemAdapter itemAdapter;
    private NotifyFilterSelected callBack;
    private SelfDialog selfDialog;
    private boolean showConfirmDialog;
    private BaseActivity mBaseActivity;
    private List<FilterItem> mFilterItemeList;
    private int currentSelectedItem = -1;

    public DataFilterMenu(final BaseActivity activity) {
        initViews(activity);
        showHideControlDefault();
        bindData();
        updateAdapter();
    }
    public void setShowHide(boolean show){
        LogUtil.d("setShowHide(" + show + ") calling...");
        if (show) mFilterLayout.setVisibility(View.VISIBLE);
        else mFilterLayout.setVisibility(View.GONE);
    }
    private void initViews(BaseActivity activity){
        mBaseActivity = activity;
        mFilterLayout = (LinearLayout) activity.findViewById(R.id.filter_include_ll);
        mLeftBlankTextView = (TextView) activity.findViewById(R.id.filter_left_blank_tv);
        mItemBlankTextView = (TextView) activity.findViewById(R.id.filter_item_fill_blank_tv);
        mNurseLayout = (LinearLayout) activity.findViewById(R.id.nurse_qrcode_region);
        mQRCodeImage = (ImageView) activity.findViewById(R.id.qr_code_image);
        mFilterListView = (ListView) activity.findViewById(R.id.filter_item_list_view);
        mNurseNameTextView = (TextView) activity.findViewById(R.id.filter_item_nurse_name);
        mNurseIdTextView = (TextView) activity.findViewById(R.id.filter_item_nurse_id);
        mSettingTextView = (TextView) activity.findViewById(R.id.filter_setting_text_view);
        mQuitTextView = (TextView) activity.findViewById(R.id.filter_quit_text_view);
        mSettingLine = (TextView) activity.findViewById(R.id.filter_item_setting_line);
        mQuitLine = (TextView) activity.findViewById(R.id.filter_item_quit_line);
        selfDialog = new SelfDialog(activity);
        selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });
    }
    public void setVitalDefines(List<VitalSignWardDefine> vitals){
        if (vitals == null){
            LogUtil.e("Can not update filter item since vital defines is null!");
            return;
        }
        LogUtil.d("Got [" + (vitals==null?"null":vitals.size()) + "] vital sign define!");

        if (mFilterItemeList == null) mFilterItemeList = new ArrayList<>();
        mFilterItemeList.clear();
        FilterItem item = null;
        for(VitalSignWardDefine wd : vitals){
            if (wd == null || wd.getVitalDefine() == null){
                LogUtil.e("Can not set viatl define as filter since it is null!");
                continue;
            }
            item = new FilterItem();
            item.filterResultType = DataFilterMenu.FILTER_TYPE_VITAL_SIGN;
            item.name = wd.getVitalDefine().getSignname();
            item.resultString = wd.getVitalDefine().getId()+"";
            mFilterItemeList.add(item);
        }
        LogUtil.d("Got [" + (mFilterItemeList==null?"null":mFilterItemeList.size()) + "] filter items from viatl sign define!");
        updateAdapter();
    }
    private FilterItem getSelectedFilterItem(){
        if (currentSelectedItem <= -1){
            LogUtil.i("Filter Item not been selected! select pointer is " + currentSelectedItem);
            return null;
        }
        if (mFilterItemeList == null || mFilterItemeList.size() <= currentSelectedItem){
            LogUtil.e("Can not get selcted filter item at position[" + currentSelectedItem
                    + "], since item[" + (mFilterItemeList==null?"null":mFilterItemeList.size()) + "] not enough!");
            return null;
        }
        return mFilterItemeList.get(currentSelectedItem);
    }
    private void updateAdapter(){
        if (itemAdapter == null){
            LogUtil.d("Create a new filter adapter since it was null!");
            itemAdapter = new FilterItemAdapter(mBaseActivity, mFilterItemeList);
            mFilterListView.setAdapter(itemAdapter);
            //list 列表条目点击监听
            LogUtil.d("Set list view listener!");
            mFilterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    LogUtil.d("mFilterListView.onItemClick calling...");
                    currentSelectedItem = position;
                    setShowHide(false);
                    if (callBack != null) {
                        LogUtil.d("Callback[" + callBack.getClass().getName() + "] is calling....");

                        if (showConfirmDialog) {
                            selfDialog.show();
                            selfDialog.setYesOnclickListener("确认", new SelfDialog.onYesOnclickListener() {
                                @Override
                                public void onYesClick() {
                                selfDialog.dismiss();
                                callBack.afterFilterSelected(getSelectedFilterItem());
                                }
                            });
                        }else callBack.afterFilterSelected(getSelectedFilterItem());
                    }
                }
            });
            if (mLeftBlankTextView != null) {
                LogUtil.d("Set mLeftBlankTextView listener!");
                mLeftBlankTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtil.d("mLeftBlankTextView.onClick calling...");
                        setShowHide(false);
                    }
                });
            }else LogUtil.e("mLeftBlankTextView is null!");
        } else{
            LogUtil.d("Reset itemAdapter's data!");
            itemAdapter.setData(mFilterItemeList);
        }
        itemAdapter.notifyDataSetChanged();
    }
    public void setPatients(List<HuanZheLieBiaoBean.DataBean> patients){
        if (patients == null){
            LogUtil.e("Can not update filter item since patients is null!");
            return;
        }
        if (mFilterItemeList == null) mFilterItemeList = new ArrayList<>();
        mFilterItemeList.clear();
        FilterItem item = null;
        for(HuanZheLieBiaoBean.DataBean pt : patients){
            item = new FilterItem();
            item.filterResultType = DataFilterMenu.FILTER_TYPE_PATIENT;
            item.name = pt.getBedno() + "床";
            item.desc = pt.getName();
            item.resultString = pt.getPatientid();
            mFilterItemeList.add(item);
        }
        updateAdapter();
    }
    public void setNurse(User.DataBean nurse){
        if (nurse == null || nurse.getEmp_no() == null){
            LogUtil.e("Can not set nurse for filter since nurse or its emp_no is null!");
            mNurseLayout.setVisibility(View.GONE);
            mSettingTextView.setVisibility(View.GONE);
            mQuitTextView.setVisibility(View.GONE);
            mQuitLine.setVisibility(View.GONE);
            mSettingLine.setVisibility(View.GONE);
        }else {
            mSettingTextView.setVisibility(View.VISIBLE);
            mQuitTextView.setVisibility(View.VISIBLE);
            mNurseLayout.setVisibility(View.VISIBLE);
            new ErWeiMaSync(mBaseActivity, mQRCodeImage).execute(nurse.getEmp_no());
            mNurseNameTextView.setText(nurse.getName());
            mNurseIdTextView.setText(nurse.getEmp_no());
            mQuitLine.setVisibility(View.VISIBLE);
            mSettingLine.setVisibility(View.VISIBLE);
        }
    }
    public void needConfirmWhileChangeItem(boolean need) {
        showConfirmDialog = need;
    }

    public void needConfirmWhileItem(boolean need, String content) {
        needConfirmWhileChangeItem(need);
        selfDialog.setMessage(content);
    }

    public void setAfterFilterSelectedCallback(NotifyFilterSelected changeCallback) {
        this.callBack = changeCallback;
    }

    private void showHideControlDefault() {
        mFilterLayout.setVisibility(View.GONE);
        mNurseLayout.setVisibility(View.GONE);
        mSettingTextView.setVisibility(View.GONE);
        mQuitTextView.setVisibility(View.GONE);
        mQuitLine.setVisibility(View.GONE);
        mSettingLine.setVisibility(View.GONE);
    }

    private void bindData() {

    }


    public class FilterItem{
        private int filterResultType;
        private String name;
        private String desc;
        private String resultString;

        public int getFilterResultType() {
            return filterResultType;
        }

        public void setFilterResultType(int filterResultType) {
            this.filterResultType = filterResultType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getResultString() {
            return resultString;
        }

        public void setResultString(String resultString) {
            this.resultString = resultString;
        }
    }
}
