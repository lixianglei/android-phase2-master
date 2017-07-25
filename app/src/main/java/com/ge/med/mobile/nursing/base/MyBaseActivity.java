package com.ge.med.mobile.nursing.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.sj.library.base.BaseActivity;
import com.example.sj.library.control.ActivityControl;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.dao.DoctorOrderDao;
import com.ge.med.mobile.nursing.dao.HuanZheLieBiaoInterface;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.forjson.callback.INetworkHandler;
import com.ge.med.mobile.nursing.scancode.BarCodeUtil;
import com.ge.med.mobile.nursing.scancode.MyPrefs;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.activity.ActivityUtils;
import com.ge.med.mobile.nursing.ui.activity.HZActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ShuXue_QuXueActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_XiangQingActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZXActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_JianYanActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_ShuYeActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_ZhuShe2Activity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_ZhuSheActivity;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.view.JiaZaiDialog;
import com.ge.med.mobile.nursing.ui.view.SelfDialogDan;
import com.ge.med.mobile.nursing.utils.DataConverter;
import com.mitac.lib.bcr.MiBcrListener;
import com.mitac.lib.bcr.utils.BARCODE;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
public abstract class MyBaseActivity extends BaseActivity implements INetworkHandler, MiBcrListener {
    protected static MyBaseActivity topActivity;
    protected UIHanlder uiHnadler = new UIHanlder();
    public static boolean IsLogin = true;
    public JiaZaiDialog jiaZaiDialog;
    private HuanZheLieBiaoInterface huanZheLieBiaoInterface = new HuanZheLieBiaoImpl();
    private DoctorOrderDao yiZhuInterface = new DoctorOrderDaoImpl();
    private SharePLogin sharePLogin;

    protected void gotoPatientPage(int pageNo) {
        gotoPatientPage(null, pageNo);
    }

    protected void gotoPatientPage(Bundle bundle, int pageNo) {
        if (bundle == null) bundle = new Bundle();
        bundle.putInt(Constant.BUNDLE_KEY_PATIENT_TAB_NUM, pageNo);
        goToActivity(HZActivity.class, bundle);
        killSelf();
    }

    protected HuanZheLieBiaoBean.DataBean updatePatientPannel(Bundle bundle, PatientInfoPannel patientPannel) {
        bundle = mActivitySelf.getIntent().getBundleExtra(Constant.GLOBAL_KEY_DATA);
        HuanZheLieBiaoBean.DataBean patient = (HuanZheLieBiaoBean.DataBean) bundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
        if (patient != null && patientPannel != null) {
            patientPannel.setPatient(patient);
            patientPannel.changePatient(patient);
            LogUtil.d("Update patient in patient pannel! patientid is "
                    + patient.getPatientid() + ", and assess result is :");
        } else {
            LogUtil.e("Can not update patient in patient pannel since no patient found from bundle! ");
        }
        return patient;
    }

    //    public void goToActivity(Class activityClass,Bundle bundle, boolean clearAll){
//        if (clearAll) {
//            Intent intent = new Intent(mActivitySelf, activityClass);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            if (bundle != null) {
//                intent.putExtra(Constant.GLOBAL_KEY_DATA, bundle);
//            }
//            startActivityForResult(intent, 0);
//        }else goToActivity(activityClass, bundle);
//    }
    public void handleOnError() {
        LogUtil.e("Error found while call network.");
    }

    public void handleOnError(String urlStr) {
        LogUtil.e("Error found while call network[" + urlStr + "].");
    }

    public void handleSuccess(Object obj) {
        LogUtil.d("Successfully called network.");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        jiaZaiDialog = new JiaZaiDialog(mActivitySelf);
        jiaZaiDialog.setCanceledOnTouchOutside(false);
        sharePLogin = new SharePLogin(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        topActivity = this;

    }


    /**
     * onStop event handler
     */
    @Override
    protected void onStop() {
        super.onStop();
    }


//    /**
//     * s时间监听
//     */
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        TouchTime.NOW_TIME = System.currentTimeMillis();
////        goToService(MyService.class);
//        return super.dispatchTouchEvent(ev);
//    }

    //new add start
    protected BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ((MyPrefs.HONEYWELL_ACTION_SCAN_RESULT.equals(intent.getAction()))) {
                String barcode = intent.getStringExtra(MyPrefs.HONEYWELL_ACTION_SCAN_RESULT_KEY);
                if (barcode != null) {
                    startNextActivity(barcode.trim());
                }
            }

            if ((MyPrefs.EMH_ACTION.equals(intent.getAction()))) {
                String barcode2 = intent.getStringExtra(MyPrefs.EMH_ACTION_SCAN_RESULT_KEY);//适配EMH手持设备
                if (barcode2 != null) {
                    startNextActivity(barcode2.trim());
                }
            }

            if ((MyPrefs.XMG_ACTION.equals(intent.getAction()))) {
                String barcode3 = intent.getStringExtra(MyPrefs.XMG_ACTION_SCAN_RESULT_KEY);//适配小码哥手持设备
                if (barcode3 != null) {
                    startNextActivity(barcode3.trim());
                }
            }
        }
    };

    protected void startNextActivity(String barCode) {
//        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
//        String className = info.topActivity.getClassName();              //完整类名
//        MyBaseActivity activity = this;
//        try {
//            activity = (MyBaseActivity) Class.forName(className).newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        Toast.makeText(this, "扫到的条码是：" + barCode, Toast.LENGTH_SHORT).show();
        if (BarCodeUtil.isPatientCode(barCode)) {
            DBHuanZheLieBiao dbHuanZheLieBiao = DataSupport.where("wandaicode = ?", barCode).findFirst(DBHuanZheLieBiao.class);
            if (dbHuanZheLieBiao != null) {
                topActivity.afterPatientScanned(dbHuanZheLieBiao.getPatientid());
            } else {
                showMessage("此患者不存在！" + barCode);
            }
        } else if (BarCodeUtil.isOrderCode(barCode)) {
            YiZhuBean.DataBean doctorOrderByHisId = new DoctorOrderDaoImpl().getDoctorOrderByHisId(barCode);
            if (doctorOrderByHisId != null) {
                topActivity.afterDoctorOrderScanned(doctorOrderByHisId.getId(), true);
            } else {
                showMessage("此医嘱不存在！" + barCode);
            }
        } else if (BarCodeUtil.isBloodProductNo(barCode)) {
            topActivity.afterProductScanned(barCode);
        } else if (BarCodeUtil.isBloodPackageNo(barCode)) {
            topActivity.afterBloodPackageScanned(barCode);
        } else if (BarCodeUtil.isUser(barCode)) {
            topActivity.afterUserScanned(barCode);
        } else if (BarCodeUtil.isCheckProperty(barCode)) {
            topActivity.afterCheckPropertyScanned(barCode);
        } else {
            showMessage("错误的条码！" + barCode);
        }

    } // the end of startNextActivity()

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //根据Patient ID跳转到患者界面
    public void afterPatientScanned(String patientID) {
        LogUtil.d("MyBaseActivity[" + this.getClass().toString() + "].afterPatientScanned calling, patientId is " + patientID);
        Bundle mBundle = new Bundle();
        mBundle.putString("patientid", patientID);
        mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE,
                DataConverter.convert(huanZheLieBiaoInterface.getDBHuanZheLieBiao(patientID)));
        goToActivity(HZActivity.class, mBundle);
    }

    public void afterDoctorOrderScanned(int hisID, Boolean isSaoMa) {
        if (isSaoMa) {
            Bundle mBundle = new Bundle();
            mBundle.putInt(Constant.BUNDLE_KEY_IS_SAOMA, hisID);
            YiZhuBean.DataBean dataBean = yiZhuInterface.getDoctorOrderByHisId(hisID);
            if (dataBean == null) {
                showToastShort("该医嘱未找到！请刷新医嘱数据！");
                return;
            }
            if (Constant.YZ_TYPE_WEIHEDUI.equals(dataBean.getOrderststus())) {
                showToastShort("医嘱未核对！请刷新医嘱数据！");
                return;
            }
            if (Constant.YZ_TYPE_DAIBAIYAO.equals(dataBean.getOrderststus())) {
                showToastShort("医嘱待摆药！请刷新医嘱数据！");
                return;
            }
            if (Constant.YZ_TYPE_DAIPEIYE.equals(dataBean.getOrderststus())) {
                showToastShort("医嘱待配液！请刷新医嘱数据！");
                return;
            }
            mBundle.putString("patientid", dataBean.getPatientid());
            mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE,
                    DataConverter.convert(huanZheLieBiaoInterface.getDBHuanZheLieBiao(dataBean.getPatientid())));
            ActivityControl.killActivity(HZActivity.class);
            goToActivity(HZActivity.class, mBundle);
        } else {
            afterDoctorOrderScanned(hisID);
        }
    }

    //根据his ID跳转到医嘱界面
    public void afterDoctorOrderScanned(int hisID) {
        LogUtil.d("MyBaseActivity[" + this.getClass().toString() + "].afterDoctorOrderScanned calling, hisID is " + hisID);
        Log.e("医嘱跳转", "hisId" + hisID);
        Bundle mBundle = new Bundle();
        YiZhuBean.DataBean dataBean = yiZhuInterface.getDoctorOrderByHisId(hisID);
        Log.e("医嘱跳转", "dbYiZhuDataOne is " + dataBean);
        if (dataBean == null) {
            showToastShort("该医嘱未找到！请刷新医嘱数据！");
            return;
        }
        Log.e("医嘱跳转", "dbYiZhuDataOne bbb " + dataBean.getOrderBloodProperty());
        if (Constant.YZ_TYPE_YIZHIXING.equals(dataBean.getOrderststus())) {
            if (!(Constant.TYPE_YZ_PISHI.equals(dataBean.getOrdertype())
                    && (null == dataBean.getOrderresult() || "".equals(dataBean.getOrderresult())))) {
                showToastShort("医嘱已执行！请刷新医嘱数据！");
                return;
            }
        }
        if (Constant.YZ_TYPE_YIQUXIAO.equals(dataBean.getOrderststus())) {
            showToastShort("医嘱已取消！请刷新医嘱数据！");
            return;
        }
        if (Constant.YZ_TYPE_YITINGZHI.equals(dataBean.getOrderststus())) {
            showToastShort("医嘱已停止！请刷新医嘱数据！");
            return;
        }


        if (dataBean.getOrderststus() != null
                && !(Constant.YZ_TYPE_ZHIXINGZHONG.equals(dataBean.getOrderststus()))
                && ActivityUtils.isExeTime(dataBean.getStarttime())) {
            showToastShort("不在执行时间范围内！(前后2小时)");
            return;
        }
        if (dataBean.getOrderststus() != null
                && Constant.YZ_TYPE_WEIHEDUI.equals(dataBean.getOrderststus())) {
            showToastShort("此医嘱未核对!");
            return;
        }
        mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU, dataBean);
        mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE,
                DataConverter.convert(huanZheLieBiaoInterface.getDBHuanZheLieBiao(dataBean.getPatientid())));
//        mBundle.putStringArrayList("yzshu", yzShuList);
        switch (dataBean.getOrdertype()) {
            //注射
            //皮试
            case Constant.TYPE_YZ_PISHI://皮试
            case Constant.TYPE_YZ_JIROUZHUSHE://肌肉注射
            case Constant.TYPE_YZ_PIXIAZHUSHE://皮下注射
            case Constant.TYPE_YZ_JINGMAIZHUSHE://静脉注射
            case Constant.TYPE_YZ_RUHU://入壶
                if (Constant.TYPE_YZ_PISHI.equals(dataBean.getOrdertype())
                        && Constant.YZ_TYPE_YIZHIXING.equals(dataBean.getOrderststus())) {
                    goToActivity(YZ_ZX_ZhuSheActivity.class, mBundle);
                    break;
                }
                List<YiZhuBean.DataBean.DoctorOrdersBean> doctorOrders = dataBean.getDoctorOrders();
                if (doctorOrders != null && doctorOrders.size() > 1) {
                    mBundle.putBoolean(Constant.BUNDLE_KEY_ISFENCI, Constant.BUNDLE_KEY_FENCI_YES);
                    goToActivity(YZ_ZX_ZhuShe2Activity.class, mBundle);
                } else {
                    mBundle.putBoolean(Constant.BUNDLE_KEY_ISFENCI, Constant.BUNDLE_KEY_FENCI_NO);
                    if (dataBean != null && dataBean.getPharmList() != null && dataBean.getPharmList().size() > 0) {
                        goToActivity(YZ_XiangQingActivity.class, mBundle);
                    } else {
                        goToActivity(YZ_ZX_ZhuSheActivity.class, mBundle);
                    }
                }
                break;
            //口服
            case Constant.TYPE_YZ_KOUFU:
                if (dataBean != null && dataBean.getPharmList() != null && dataBean.getPharmList().size() > 0) {
                    goToActivity(YZ_XiangQingActivity.class, mBundle);
                } else {
                    goToActivity(YZ_ZX_ZhuSheActivity.class, mBundle);
                }
                break;
            //辅助治疗
            case Constant.TYPE_YZ_SHANSHI:
            case Constant.TYPE_YZ_ZHILIAO:
            case Constant.TYPE_YZ_HULI:
                goToActivity(YZ_ZX_ZhuSheActivity.class, mBundle);
                break;
            //检验
            case Constant.TYPE_YZ_JIANYAN_BLOOD:
            case Constant.TYPE_YZ_JIANYAN_UN_BLOOD:
                goToActivity(YZ_ZX_JianYanActivity.class, mBundle);
                break;
            //输液
            case Constant.TYPE_YZ_SHUYE:
                List<YiZhuBean.DataBean.DoctorOrdersBean> doctorOrders2 = dataBean.getDoctorOrders();
                if (doctorOrders2 != null && !doctorOrders2.isEmpty()) {
                    YiZhuBean.DataBean.DoctorOrdersBean doctorOrdersBean = doctorOrders2.get(0);
                    if (doctorOrdersBean != null) {
                        List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> orderExecuteRecords = doctorOrdersBean.getOrderExecuteRecords();
                        if (orderExecuteRecords != null && orderExecuteRecords.size() > 0) {
                            for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean
                                    : orderExecuteRecords) {
                                if (Constant.YZ_EXECUTE_JOBTYPE_XUNSHI.equals(orderExecuteRecordsBean.getJobtype())
                                        || Constant.YZ_EXECUTE_JOBTYPE_HUANYE.equals(orderExecuteRecordsBean.getJobtype())) {
                                    goToActivity(YZ_ZX_ShuYeActivity.class, mBundle);
                                    return;
                                }
                            }
                        }
                    }
                }
//                if(Constant.YZ_TYPE_ZHIXINGZHONG.equals(dataBean.getOrderststus())){
//                    goToActivity(YZ_ZX_ShuYeActivity.class, mBundle);
//                    return;
//                }
                goToActivity(YZ_XiangQingActivity.class, mBundle);
                break;
            case Constant.TYPE_YZ_CAIXUE:
                goToActivity(YZ_ZXActivity.class, mBundle);
                break;
            //输血
            case Constant.TYPE_YZ_SHUXUE:
                int xuedaiNO = 0;
                if (Constant.YZ_TYPE_FUWENZHONG.equals(dataBean.getOrderststus())) {
                    final SelfDialogDan selfDialogDan = new SelfDialogDan(mActivitySelf);
                    selfDialogDan.setMessage("请稍候，血液制品正在复温中，请等待系统提示！");
                    selfDialogDan.setTitle("血液制品复温中");
                    selfDialogDan.setYesOnclickListener("确认", new SelfDialogDan.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            selfDialogDan.dismiss();
                        }
                    });
                    selfDialogDan.show();
                    break;
                }
                if (dataBean.getDoctorOrders() != null && dataBean.getDoctorOrders().size() > 0
                        && dataBean.getDoctorOrders().get(0) != null && dataBean.getDoctorOrders().get(0).getOrderExecuteRecords() != null
                        && dataBean.getDoctorOrders().get(0).getOrderExecuteRecords().size() > 0) {
                    List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean> dbYiZhudata_orderExecuteRecords
                            = ActivityUtils.getDBYiZhudata_OrderExecuteRecords(dataBean.getDoctorOrders().get(0).getOrderExecuteRecords());
                    for (YiZhuBean.DataBean.DoctorOrdersBean.OrderExecuteRecordsBean orderExecuteRecordsBean
                            : dbYiZhudata_orderExecuteRecords) {
                        if (Constant.YZ_TYPE_DAIHEDUI.equals(dataBean.getOrderststus())) {
                            if (Constant.YZ_EXECUTE_JOBTYPE_HEDUI.equals(orderExecuteRecordsBean.getJobtype())) {
                                if (sharePLogin != null && sharePLogin.getUserid() != null) {
                                    if (sharePLogin.getUserid().equals(orderExecuteRecordsBean.getUserid())) {
                                        showToastShort("您已核对过,请第二位用户登录进行核对！");
                                        return;
                                    }
                                }
                            }
                        }
                        if (Constant.YZ_EXECUTE_JOBTYPE_XUNSHI.equals(orderExecuteRecordsBean.getJobtype())
                                || Constant.YZ_EXECUTE_JOBTYPE_HUANYE.equals(orderExecuteRecordsBean.getJobtype())) {
                            goToActivity(YZ_ZX_ShuYeActivity.class, mBundle);
                            return;
                        }
                        if (Constant.YZ_EXECUTE_JOBTYPE_HUANYE.equals(orderExecuteRecordsBean.getJobtype())) {
                            if (orderExecuteRecordsBean.getJobStatus() != null) {
                                try {
                                    xuedaiNO = Integer.parseInt(orderExecuteRecordsBean.getJobStatus());
                                } catch (NumberFormatException e) {
                                    LogUtil.e(e.getMessage());
                                }
                            }
                        }
                    }
                }
                if (dataBean != null && Constant.YZ_TYPE_DAIQUXUE.equals(dataBean.getOrderststus())
                        || Constant.YZ_TYPE_DAIHEDUI.equals(dataBean.getOrderststus())) {
                    goToActivity(YZ_ShuXue_QuXueActivity.class, mBundle);
                    return;
                }
                mBundle.putInt(Constant.BUNDLE_KEY_STATUS_EXCE, xuedaiNO);
                goToActivity(YZ_ZXActivity.class, mBundle);
                break;
            default:
                break;


        }
    }

    public void afterProductScanned(String ProductScanned) {
        LogUtil.d("MyBaseActivity.afterProductScanned calling, ProductScanned is " + ProductScanned);

    }

    public void afterBloodPackageScanned(String BloodPackageScanned) {
        LogUtil.d("MyBaseActivity.afterBloodPackageScanned calling, BloodPackageScanned is " + BloodPackageScanned);

    }

    public void afterCheckPropertyScanned(String CheckPropertyScanned) {
        LogUtil.d("MyBaseActivity.afterBloodPackageScanned calling, CheckPropertyScanned is " + CheckPropertyScanned);

    }

    public void afterUserScanned(String UserScanned) {
        LogUtil.d("MyBaseActivity.afterUserScanned calling, UserScanned is " + UserScanned);

    }

    public class UIHanlder extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
    //new add end


    /**
     * 神达扫码
     */
    @Override
    public void onScanned(BARCODE.TYPE type, String s, int i) {
        if (s != null) {
            startNextActivity(s);
        } else {
            showToastShort("扫描失败");
        }
    }

    @Override
    public void onStatusChanged(int i) {

    }
}
