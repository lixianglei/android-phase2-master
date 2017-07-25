package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.HuanZheLieBiaoInterface;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.dao.impl.VitalSignDaoImpl;
import com.ge.med.mobile.nursing.forjson.NetworkForVitalSign;
import com.ge.med.mobile.nursing.forjson.entity.VitalDefineEntity;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignRecord;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignSheet;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignWardDefine;
import com.ge.med.mobile.nursing.service.SyncService;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.component.DataFilterMenu;
import com.ge.med.mobile.nursing.ui.component.DateTimeControl;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.component.VitalInputKeyboard;
import com.ge.med.mobile.nursing.ui.component.callback.NotifyFilterSelected;
import com.ge.med.mobile.nursing.ui.component.callback.NotifyPatientChange;
import com.ge.med.mobile.nursing.ui.view.SelfDialog;
import com.ge.med.mobile.nursing.utils.DataConverter;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 体征收集界面
 */
public class VitalSignSheetActivity extends MyBaseActivity {
    private TextView tvVitalTitle;
    private ImageButton btPrevVital;
    private ImageButton btNextVital;
    private Button btSave;
    private VitalInputKeyboard vitalKeyboard;
    private TitleBar mTitleBar;
    private PatientInfoPannel mPatientPanel;
    private DateTimeControl mDateTimeControl;
    private Bundle mBundle;
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;
    // 全局体征定义
    private List<VitalSignWardDefine> mVitalDefines;
    private VitalSignSheet mVitalSheet;
    private int currentPosition = -1;
    private SharePLogin sharePLogin;
    private Boolean isSinglePatient;
    private boolean isSaved = true;
    DataFilterMenu mFilterMenu;
    private SelfDialog selfDialog;
    private MyBaseActivity myBaseActivity;
    // 编辑标记
    private String action;
    // 体征记录：编辑时用到
    private List<VitalSignRecord> vitalSignRecords;
    // 搜索框
    private EditText et_search;
    // 筛选的体征项目
    private List<VitalSignWardDefine> filterVitalSignDefines;
    // 全局体征定义备份
    private List<VitalSignWardDefine> mVitalDefinesDuplicate;

    @Override
    public int setRootView() {
        return R.layout.activity_ti_zheng__duo_ren;
    }

    @Override
    public void initViews() {
        isSaved = true;
        sharePLogin = new SharePLogin(this);
        vitalKeyboard = new VitalInputKeyboard(this);
        mDateTimeControl = new DateTimeControl(this);
        tvVitalTitle = (TextView) findViewById(R.id.drtz_sj_imgv);
        btPrevVital = (ImageButton) findViewById(R.id.ti_zheng_duo_ren_back_bt);
        btNextVital = (ImageButton) findViewById(R.id.ti_zheng_duo_ren_next_bt);
        btSave = (Button) findViewById(R.id.ti_zheng_duo_ren_baocun_bt);
        // 搜索框
        et_search = (EditText) findViewById(R.id.et_search);
        mBundle = mActivitySelf.getIntent().getBundleExtra(Constant.GLOBAL_KEY_DATA);
        // 获得从TiZhengFragment传递而来的参数：编辑标记
        action = mBundle.getString("action");
        if (action != null && action.equals("edit")) {
            vitalSignRecords = (List<VitalSignRecord>) mBundle.get("vitalSignRecord");
        }
        // 传入体征定义ID集合
        List<Integer> vitalGroup = mBundle.getIntegerArrayList(Constant.BUNDLE_KEY_VITAL_GROUP);
        // 从数据库中获取缓存的 病区体征定义集合
        List<VitalSignWardDefine> vitalDefines = new VitalSignDaoImpl().findAllWardDefineFromDB();
        // 重新排序
        Collections.sort(vitalDefines, new Comparator<VitalSignWardDefine>() {
            @Override
            public int compare(VitalSignWardDefine lhs, VitalSignWardDefine rhs) {
                return lhs.getSortvitalno() - rhs.getSortvitalno();
            }
        });

        Integer pos = Integer.valueOf(0);
        mVitalDefines = new ArrayList<VitalSignWardDefine>();
        mVitalDefinesDuplicate = new ArrayList<>();
        boolean bfound = false;
        for (VitalSignWardDefine def : vitalDefines) {
            bfound = true;
            if (vitalGroup != null) {// 根据传入的体征定义ID集合过滤需要录入的体征集合
                bfound = false;
                for (Integer did : vitalGroup) {
                    if (did != null && def != null && def.getVitalDefine() != null && def.getVitalDefine().getId() != null) {
                        if (did.intValue() == def.getVitalDefine().getId().intValue()) {
                            bfound = true;
                        }
                    }
                }
            }
            if (bfound && def.getVitalDefine() != null) {
                mVitalDefines.add(def);
                // 按照接口顺序设置体征排序编号
                def.getVitalDefine().setPosition(pos);
                pos = Integer.valueOf(pos.intValue() + 1);
                LogUtil.d("VitalSignSheetActivity.initViews> Add Vital Sign[id:" + def.getVitalDefine().getId()
                        + ",name:" + def.getVitalDefine().getSignname() + "] at position " + def.getVitalDefine().getPosition());
            }
        }
        mSelectedHZ = (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
        if (mSelectedHZ == null)
            LogUtil.e("Application maybe not stable since cannot get patient from bundle!");
        isSinglePatient = (Boolean) mBundle.getBoolean(Constant.BUNDLE_KEY_IS_SINGLE_PATIENT);
        mTitleBar = new TitleBar(this, mSelectedHZ);
        mTitleBar.setShowConfirmDialog(true);
        mFilterMenu = new DataFilterMenu(this);
        //mFilterMenu.needConfirmWhileItem(true, "切换到其它体征将放弃本体征数据！请确认是否继续？");
        mVitalDefinesDuplicate.addAll(mVitalDefines);
        mFilterMenu.setVitalDefines(mVitalDefines);
        mFilterMenu.setAfterFilterSelectedCallback(new NotifyFilterSelected() {

            @Override
            public void afterFilterSelected(DataFilterMenu.FilterItem selectedFilter) {
                LogUtil.d("afterFilterSelected was calling!");
                if (selectedFilter == null) {
                    LogUtil.e("Can not do anything since selected filter is null!");
                    return;
                }
                Integer type = null;
                try {
                    type = Integer.parseInt(selectedFilter.getResultString());
                } catch (NumberFormatException e) {
                    LogUtil.e("Can not do anything since selected filter's result is not a number!");
                    return;
                }
                VitalDefineEntity define = getVitalDefineByType(type);
                if (define == null) {
                    LogUtil.e("Can not do anything since there is not vital defind found for type[" + type + "].");
                    return;
                }
                addOrUpdateOrRemoveVitalRecordIntoSheetBean();
                gotoVitalSign(define.getPosition());
            }

            @Override
            public boolean beforeFilterSelected(DataFilterMenu.FilterItem selectedFilter) {
                LogUtil.d("beforeFilterSelected is calling and do nothing for this event!");
                return true;
            }
        });
        mTitleBar.setShaiXuanBehavior(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFilterMenu.setShowHide(true);
            }
        });
        mPatientPanel = new PatientInfoPannel(this, new AssessDaoImpl().findAllRiskDefine());
        mPatientPanel.setSinglePatient(isSinglePatient);
        if (!isSinglePatient) {
            mPatientPanel.setPatients(getPatients(sharePLogin.getUserid()));
            mPatientPanel.needConfirmWhileChangePatient(true);
            mPatientPanel.changePatient(mSelectedHZ);
        } else {
            mPatientPanel.setPatient(mSelectedHZ);
            mPatientPanel.changePatient(mSelectedHZ);
            mPatientPanel.setTipBehavior(null);
        }
        mPatientPanel.showHideTipLayout(false);
        mPatientPanel.setOnPatientChangeCallback(new NotifyPatientChange() {

            @Override
            public void onPatientChange() {
                mSelectedHZ = mPatientPanel.getCurrentPatient();
                mTitleBar.changePatient(mSelectedHZ);
                if (!isSaved) {
                    String msg = checkAllVitalValue();
                    if (Constant.NETWORK_MSG_OK.equals(msg)) {
                        saveVitalSign();
                    } else {
                        showToastShort(msg);
                    }
                }
                resetVitalSheet();
            }
        });

        /**
         * 上一步按钮点击事件处理
         */
        btPrevVital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition > -1) addOrUpdateOrRemoveVitalRecordIntoSheetBean();
                currentPosition--;
                if (currentPosition < -1) {
                    currentPosition = -1;
                    showToastShort("已经到达第一页！");
                    return;
                }
                if (currentPosition == -1) {
                    showDateTime(true);
                    tvVitalTitle.setText("本次收集时间");
                } else {
                    changeVitalInputFragment();
                }
                isSaved = false;
            }
        });

        /**
         * 下一步按钮点击事件处理
         */
        btNextVital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition > -1) addOrUpdateOrRemoveVitalRecordIntoSheetBean();
                moveNext();
                isSaved = false;
            }
        });

        /**
         * 保存/提交按钮点击事件处理
         */
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSaved = false;
                if ("保存".equals(btSave.getText())) {
                    moveNext();
                } else {
                    saveVitalSign();
                }
            }
        });
        creatNewSheetBean();
        showDateTime(true);

        // 监听搜索框的变化
        et_search.addTextChangedListener(new EditChangedListener());

        //筛选的自定义体征项目
        filterVitalSignDefines = new ArrayList<>();
    } // the end of initViews()


    private void getVitalSignDefineFromDB() {
    }

    /**
     * 获得用户的我的患者列表
     *
     * @param userId 用户编号
     * @return 患者列表
     */
    private List<HuanZheLieBiaoBean.DataBean> getPatients(String userId) {
        HuanZheLieBiaoInterface patientDao = new HuanZheLieBiaoImpl();
        return patientDao.getZhengXuListBean(DataConverter.convertHZs(patientDao.getListDBHuanZheLieBiao()));
    }

    /**
     * 为本次体征采集创建初始化记录数据
     */
    private void creatNewSheetBean() {
        mVitalSheet = new VitalSignSheet();
        try {
            mVitalSheet.setUserid(Integer.parseInt(sharePLogin.getUserid()));
        } catch (NumberFormatException e) {
            LogUtil.e("Can not get User Id:" + sharePLogin.getUserid());
        }
        mVitalSheet.setCreatedby(sharePLogin.getDisplayUserName());
        mVitalSheet.setMrnNo(mSelectedHZ.getMrnno());
        mVitalSheet.setLastupdatedby(sharePLogin.getDisplayUserName());
        if (mSelectedHZ != null) {
            mVitalSheet.setPatientid(mSelectedHZ.getPatientid());
            mVitalSheet.setBedNo(mSelectedHZ.getBedno() + "");
            mVitalSheet.setCareLevel(mSelectedHZ.getCarelevel());
            mVitalSheet.setPatientName(mSelectedHZ.getName());
        }
        mVitalSheet.setIsdeleted("0");
        mVitalSheet.setTime(System.currentTimeMillis() + "");
        mVitalSheet.setStatus("1");
    }

    /**
     * 为新的患者创建新的体征采集数据并初始化页面
     */
    private void resetVitalSheet() {
        creatNewSheetBean();
        currentPosition = 0;
        showDateTime(false);
        changeVitalInputFragment();
    }

    /**
     * 修正体征数据的录入使其格式规范化，如去掉前置零及去掉小数点末尾的零
     *
     * @param value  体正值
     * @param define 体征定义
     * @return 规范的体正值
     */
    public static String getFormalValue(String value, VitalDefineEntity define) {
        String retval = null;
        if (value != null && define != null) {
            if (Constant.VITAL_IS_NUMBER_YES.equals(define.getIsnumber())) {
                if (Constant.VITAL_INPUT_TYPE_NUMBER_RANGE.equals(define.getInputtype())) {
                    String[] values = value.split("-");
                    if (values == null || values.length <= 0 || values.length > 2) {
                        LogUtil.e("vital value[" + value + "]'s format is not correct!");
                    } else {
                        retval = DataConverter.getFormalFloat(values[0]);
                        if (retval != null && values.length == 2) {
                            retval = retval + "-" + DataConverter.getFormalFloat(values[1]);
                        }
                    }
                } else {
                    retval = DataConverter.getFormalFloat(value);
                }
            } else retval = value;
        }
        return retval;
    }

    /**
     * 校验本次体征录入中所有的体征值录入的正确性
     *
     * @return 返回“成功”如果所有体征值都正确，否则返回需要提示用户的错误信息字符串
     */
    public String checkAllVitalValue() {
        if (mVitalSheet == null || mVitalSheet.getSignRecordList() == null || mVitalSheet.getSignRecordList().size() <= 0) {
            LogUtil.i("Vital sign Sheet is null or vital records is null or empty!");
            return "没有体征数据！";
        }
        if (mVitalDefines == null || mVitalDefines.size() <= 0) {
            LogUtil.e("Can not check vital value since define is null or empty!");
            return "体征定义错误！";
        }
        String retval = Constant.NETWORK_MSG_OK;
        VitalDefineEntity define = null;
        boolean bHighPresent = false, bLowPresent = false; // 收缩压和舒张压是否录入
        VitalDefineEntity BPdefine = null;
        for (VitalSignRecord record : mVitalSheet.getSignRecordList()) {
            define = getVitalDefineByType(record.getSigntype());
            if (define == null) {
                LogUtil.e("The vital record type does not match define!");
                retval = "无法识别的体征类型！";
            } else {
                retval = checkValue(record.getSignvalue(), define);
                if (!Constant.NETWORK_MSG_OK.equals(retval)) {
                    break;
                }
                record.setSignvalue(getFormalValue(record.getSignvalue(), define));
                if ("1".equals(define.getIsnotemandatory())) {
                    if (record.getNote() == null || record.getNote().trim().isEmpty()) {
                        retval = "请设置'" + define.getSignname() + "'的备注！";
                        break;
                    }
                }
                if (Constant.VITAL_SIGN_TYPE_HIGH_BLOOD_PRESSURE.equalsIgnoreCase(define.getVcode())) {
                    //发现高压体征
                    bHighPresent = true;
                    BPdefine = define;
                }
                if (Constant.VITAL_SIGN_TYPE_LOW_BLOOD_PRESSURE.equalsIgnoreCase(define.getVcode())) {
                    //发现低压体征
                    bLowPresent = true;
                    if (BPdefine == null) BPdefine = define;
                }
            }
        }
        if (Constant.NETWORK_MSG_OK.equals(retval) && (!(bHighPresent && bLowPresent) && (bHighPresent || bLowPresent))) {
            retval = "收缩压和舒张压应该同时录入！";
            if (BPdefine != null) define = BPdefine; // 定位到血压体征
        }
        if (!Constant.NETWORK_MSG_OK.equals(retval) && define != null) {
            gotoVitalSign(define.getPosition());
        }
        return retval;
    }

    /**
     * 切换到新的体征录入页面
     *
     * @param position 体征的位置编号
     */
    private void gotoVitalSign(Integer position) {
        LogUtil.d("gotoVitalSign>position:" + position);
        currentPosition = position;
        if (currentPosition < 0 || currentPosition >= mVitalDefines.size()) {
            currentPosition = 0;
        }
        showDateTime(false);
        changeVitalInputFragment();
    }

    /**
     * 判断体征值录入的数字范围是否合理
     *
     * @param value 体征值
     * @param start 合理范围下线
     * @param end   合理范围上线
     * @return 返回true表明合理
     */
    private boolean isNumberInRange(Float value, Integer start, Integer end) {
        boolean retval = true;
        if (value != null) {
            if (end != null && end.intValue() > 0 && value.floatValue() > end) {
                retval = false;
            }
            if (start != null && value.floatValue() < start) {
                retval = false;
            }
        }
        return retval;
    }

    /**
     * 判断体征值是否为合理的数值
     *
     * @param vitalValue  体征值
     * @param vitalDefine 体征定义
     * @return 返回“成功”表明合理
     */
    private String checkNumberValue(String vitalValue, VitalDefineEntity vitalDefine) {
        String retval = Constant.NETWORK_MSG_FAILURE;
        try {
            Float value = Float.parseFloat(vitalValue);
            if (isNumberInRange(value, vitalDefine.getRangestart(), vitalDefine.getRangeend())) {
                retval = Constant.NETWORK_MSG_OK;
            } else {
                retval = "数值应该在" + vitalDefine.getRangestart() + "到" + vitalDefine.getRangeend() + "之间！";
            }
        } catch (Exception e) {
            LogUtil.d("Input value is not a valid number!" + vitalValue);
            retval = "请输入合理的数值！";
        }
        return retval;
    }

    /**
     * 根据体征定义判断体征值的合理性
     *
     * @param vitalValue
     * @param vitalDefine
     * @return
     */
    private String checkValue(String vitalValue, VitalDefineEntity vitalDefine) {
        String retval = Constant.NETWORK_MSG_OK;
        if (vitalDefine == null || vitalDefine.getInputtype() == null) {
            LogUtil.e("Can not check number format since vital define is null");
            retval = "体征定义错误！";
        } else if (vitalValue != null && !vitalValue.trim().isEmpty()) {
            if (Constant.VITAL_INPUT_TYPE_NUMBER.equals(vitalDefine.getInputtype())) { //数字键盘+热词录入
                retval = checkNumberValue(vitalValue, vitalDefine);
            } else if (Constant.VITAL_INPUT_TYPE_NUMBER_RANGE.equals(vitalDefine.getInputtype())) {//数字键盘+热刺录入+范围
                String[] values = vitalValue.split("-");
                if (values == null || values.length <= 0 || values.length > 2) {
                    retval = "请输入合理的数值！";
                } else {
                    retval = checkNumberValue(values[0], vitalDefine);
                    if (Constant.NETWORK_MSG_OK.equals(retval) && values.length == 2) {
                        retval = checkNumberValue(values[1], vitalDefine);
                    }
                }
            }
        }
        return retval;
    }

    /**
     * 将体征页面的数据添加到内存数据中
     */
    private void addOrUpdateOrRemoveVitalRecordIntoSheetBean() {
        LogUtil.d("addOrUpdateVitalRecordIntoSheetBean calling...");
        if (mVitalSheet == null) creatNewSheetBean();
        if (mVitalSheet.getSignRecordList() == null)
            mVitalSheet.setSignRecordList(new ArrayList<VitalSignRecord>());
        if (vitalKeyboard.getVitalRecord() != null && vitalKeyboard.getVitalRecord().getSignvalue() != null
                && !vitalKeyboard.getVitalRecord().getSignvalue().trim().isEmpty()) {
            LogUtil.d("Vital Sign Value is not empty try to add into or update from record list!");
            boolean bFind = false;
            for (VitalSignRecord record : mVitalSheet.getSignRecordList()) {
                if (record.getSigntype().intValue() == vitalKeyboard.getVitalRecord().getSigntype().intValue()) {
                    LogUtil.d("Vital Sign[type:" + vitalKeyboard.getVitalRecord().getSigntype()
                            + ",value:" + vitalKeyboard.getVitalRecord().getSignvalue() + ",note:"
                            + vitalKeyboard.getVitalRecord().getNote() + "] was existed! Update old one from record list!");
                    record.setSignvalue(vitalKeyboard.getVitalRecord().getSignvalue());
                    record.setNote(vitalKeyboard.getVitalRecord().getNote());
                    record.setHandlemeasure(vitalKeyboard.getVitalRecord().getHandlemeasure());
                    bFind = true;
                    break;
                }
            }
            if (!bFind) {
                LogUtil.d("Vital Sign[type:" + vitalKeyboard.getVitalRecord().getSigntype()
                        + ",value:" + vitalKeyboard.getVitalRecord().getSignvalue() + ",note:"
                        + vitalKeyboard.getVitalRecord().getNote() + "] is a new one! Add into record list!");
                mVitalSheet.getSignRecordList().add(vitalKeyboard.getVitalRecord());
            }
        } else {
            LogUtil.d("Vital Sign Value is empty try to remove from record list!");
            VitalSignRecord record = null;
            for (int i = mVitalSheet.getSignRecordList().size() - 1; i >= 0; i--) {
                record = mVitalSheet.getSignRecordList().get(i);
                if (record.getSigntype().intValue() == vitalKeyboard.getVitalRecord().getSigntype().intValue()) {
                    LogUtil.d("Vital Sign[type:" + vitalKeyboard.getVitalRecord().getSigntype()
                            + ",value:" + vitalKeyboard.getVitalRecord().getSignvalue() + ",note:"
                            + vitalKeyboard.getVitalRecord().getNote() + "] was existed! Remove it from record list!");
                    mVitalSheet.getSignRecordList().remove(i);
                    break;
                }
            }
        }
    }

    /**
     * 判断体征是否全部提交
     *
     * @return
     */
    private boolean isAllCommit() {
        List<VitalSignRecord> signRecordList = mVitalSheet.getSignRecordList();
        if (signRecordList != null) {
            if (mVitalDefines != null && signRecordList.size() == mVitalDefines.size()) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    //提交数据
    private void summitData() {
        if (SyncService.isConnected()) {
            NetworkForVitalSign.submitSingleVitalSignSheet(this, mVitalSheet);
            LogUtil.d("Server is ready try to submit vital sign sheet!");
        } else {
            new VitalSignDaoImpl().saveVitalSign(mVitalSheet, true);
            LogUtil.e("Server is not reachabel store data in local DB!");
            showToastShort("当前处于离线状态，数据将先保存在手持设备上！");
            doAfterSavePatient();
        }
    }

    /**
     * 保存本次体征录入数据（本地和服务器）
     */
    private void saveVitalSign() {
        if (currentPosition >= 0) {
            addOrUpdateOrRemoveVitalRecordIntoSheetBean();
            String msg = checkAllVitalValue();
            if (Constant.NETWORK_MSG_OK.equals(msg)) {
                LogUtil.d("Check vital value is ok!Trying to save into local DB!");
                isSaved = true;
                myBaseActivity = this;
                if (!isAllCommit()) {//体征全部提交
                    selfDialog = new SelfDialog(mActivitySelf);
                    selfDialog.setTitle("确认操作");
                    selfDialog.setMessage("还有未录完的体征，是否继续？");
                    selfDialog.show();
                    selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            selfDialog.dismiss();
                        }
                    });
                    selfDialog.setYesOnclickListener("确认", new SelfDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            summitData();
                            selfDialog.dismiss();
                        }
                    });
                } else {
                    summitData();
                }
            } else {
                LogUtil.d("Failed to check all vital values");
                showToastShort(msg);
            }
        }
    }

    /**
     * 保存提交后跳转页面
     */
    private void doAfterSavePatient() {
        showToastLong("患者体征采集完毕");
        if (!isSinglePatient) killSelf();
        else gotoPatientPage(mBundle, 2);
    }

    /**
     * 接收服务器错误返回
     *
     * @param urlStr 提交服务器时的服务器地址
     */
    @Override
    public void handleOnError(String urlStr) {
        if (URL.URL_VITAL_SAVE.equals(urlStr)) {
            new VitalSignDaoImpl().saveVitalSign(mVitalSheet, true);
            this.showToastLong("无法提交数据，先保存在手持端！");
            doAfterSavePatient();
        }
    }

    /**
     * 接收服务器正确返回
     *
     * @param obj 服务器返回数据
     */
    @Override
    public void handleSuccess(Object obj) {
        if (obj != null) {
            if (obj instanceof List) {
                if (((List) obj).size() > 0) {
                    if (((List) obj).get(0) instanceof Integer) {
                        Integer sheetId = (Integer) ((List) obj).get(0);
                        mVitalSheet.setId(sheetId);
                        LogUtil.d("Got server return sheetId: " + sheetId + ", then try to update local DB.");
                        new VitalSignDaoImpl().saveVitalSign(mVitalSheet, false);
                        isSaved = true;
                        doAfterSavePatient();
                    } else {
                        LogUtil.e("Got unknown return data format: " + obj.getClass().getName());
                    }
                }
            }
            if (obj instanceof VitalSignSheet) {
                LogUtil.d("Got Vital Sign Sheet from server and do noting!");
            }
        }
    }

    /**
     * 点击保存按钮后切换到下一体征
     */
    private void moveNext() {
        currentPosition++;
        if (currentPosition >= mVitalDefines.size()) {
            currentPosition = mVitalDefines.size() - 1;
            showToastShort("已经到达最后一页！");
        }
        mVitalSheet.setTime(mDateTimeControl.getLong());
        showDateTime(false);
        changeVitalInputFragment();
    }

    /**
     * 切换日期和体征页面显示
     *
     * @param isShow 为true时显示日期时间页面，否则显示体征页面
     */
    private void showDateTime(boolean isShow) {
        mDateTimeControl.setVisible(isShow);
        vitalKeyboard.setVisible(!isShow);
        if (isShow) {
            btPrevVital.setVisibility(View.GONE);
            btNextVital.setVisibility(View.GONE);
            btSave.setText("保存");
        } else {
            btPrevVital.setVisibility(View.VISIBLE);
            btNextVital.setVisibility(View.VISIBLE);
            btSave.setText("提交");
        }
    }

    /**
     * 切换到新的体征页面时根据体征定义和历史数据更新体征录入页面
     */
    private void changeVitalInputFragment() {
        if (currentPosition < 0 || mVitalDefines == null || mVitalDefines.size() <= 0) {
            LogUtil.e("Can not update Vital Input Fragment since vital define[" +
                    (mVitalDefines == null ? "null" : mVitalDefines.size()) + "] or current position[" + currentPosition + "] not correct!");
            return;
        }
        tvVitalTitle.setText(mVitalDefines.get(currentPosition).getVitalDefine().getSignname());
        vitalKeyboard.setVitalThing(mVitalDefines.get(currentPosition).getVitalDefine(), getVitalRecordByDefineType(mVitalDefines.get(currentPosition).getVitalDefine()));
    }

    /**
     * 根据体征记录中的SignType获得对应的体征定义
     *
     * @param defineType 体征记录中的signType
     * @return 体征定义
     */
    private VitalDefineEntity getVitalDefineByType(Integer defineType) {
        VitalDefineEntity retval = null;
        if (mVitalDefines != null && mVitalDefines.size() > 0) {
            for (VitalSignWardDefine define : mVitalDefines) {
                if (define != null && define.getVitalDefine() != null && define.getVitalDefine().getId().intValue() == defineType.intValue()) {
                    retval = define.getVitalDefine();
                    break;
                }
            }
        }
        return retval;
    }

    /**
     * 根据体征定义查找体征记录
     *
     * @param define 体征定义
     * @return 查找到返回vitalSignRecord，否则创建新记录后返回
     */
    private VitalSignRecord getVitalRecordByDefineType(VitalDefineEntity define) {
        VitalSignRecord retval = null;
        if (mVitalSheet != null) { // try to find existed vital record by defineId
            if (mVitalSheet.getSignRecordList() == null) {
                mVitalSheet.setSignRecordList(new ArrayList<VitalSignRecord>());
            }
        }
        if (mVitalSheet.getSignRecordList().size() > 0) {
            for (VitalSignRecord record : mVitalSheet.getSignRecordList()) {
                if (record != null && record.getSigntype().intValue() == define.getId().intValue()) {
                    retval = record;
                    break;
                }
            }
        }

        if (retval == null) { // 如果以前没有则创建一个新的记录
            retval = new VitalSignRecord();
            retval.setVitalDefine(define);
            retval.setSheetid(mVitalSheet.getId());
            retval.setSigntype(define.getId());
            retval.setUserid(Integer.parseInt(sharePLogin.getUserid()));
            retval.setExecuteName(sharePLogin.getDisplayUserName());
            retval.setCreatedby(sharePLogin.getUserid());
            retval.setLastupdatedby(retval.getCreatedby());
            // 编辑时需要显示原有的值
            if (action != null && action.equals("edit")) {
                if (vitalSignRecords != null && vitalSignRecords.size() > currentPosition) {
                    for (int i = 0; i < vitalSignRecords.size(); i++) {
                        if (vitalSignRecords.get(i).getSigntype().equals(define.getId())) {
                            // 值
                            retval.setSignvalue(vitalSignRecords.get(i).getSignvalue());
                            // 记录id
                            retval.setId(vitalSignRecords.get(i).getId());
                        }
                    }
                    // sheetId
                    retval.setSheetid(vitalSignRecords.get(0).getSheetid());
                    mVitalSheet.setId(vitalSignRecords.get(0).getSheetid());
                }
            }
        }
        return retval;
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
    protected void onDestroy() {
        super.onDestroy();
        if (vitalKeyboard != null) {
            vitalKeyboard = null;
        }
    }

    /**
     * 搜索框监听
     */
    class EditChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String temp = String.valueOf(s);
            temp = temp.trim();
            // 根据用户输入的关键字，显示自定义的体征项目
            filterVitalSignDefines.clear();
            if (temp.length() == 0) {
                // 搜索框清空时，重新显示全局的内容
                mVitalDefines.clear();
                mVitalDefines.addAll(mVitalDefinesDuplicate);
                mFilterMenu.setVitalDefines(mVitalDefines);
            } else {
                if (mVitalDefines != null && mVitalDefines.size() > 0) {
                    // 设置VitalDefine的position属性
                    int j = 0;
                    for (int i = 0; i < mVitalDefines.size(); i++) {
                        if (mVitalDefines.get(i).getVitalDefine().getSignname().contains(temp)) {
                            mVitalDefines.get(i).getVitalDefine().setPosition(j);
                            filterVitalSignDefines.add(mVitalDefines.get(i));
                            j++;
                        }
                    }
                    // 设置过滤菜单的显示内容
                    mFilterMenu.setVitalDefines(filterVitalSignDefines);
                    mVitalDefines.clear();
                    mVitalDefines.addAll(filterVitalSignDefines);
                }
            }
        }
    }
}