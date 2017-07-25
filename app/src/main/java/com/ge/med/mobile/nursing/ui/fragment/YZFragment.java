package com.ge.med.mobile.nursing.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sj.library.base.BaseFragment;
import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.config.Config;
import com.ge.med.mobile.nursing.dao.DoctorOrderDao;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.forjson.callback.INetworkHandler;
import com.ge.med.mobile.nursing.ui.activity.ActivityUtils;
import com.ge.med.mobile.nursing.ui.activity.HZActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ShuXue_QuXueActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_XiangQingActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZXActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_JianYanActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_ShuYeActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_ZhuShe2Activity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZX_ZhuSheActivity;
import com.ge.med.mobile.nursing.ui.adapter.HzYzFragLvItemAdapter;
import com.ge.med.mobile.nursing.ui.view.JiaZaiDialog;
import com.ge.med.mobile.nursing.ui.view.SelfDialogDan;
import com.ge.med.mobile.nursing.ui.view.YiZhuPopuwindow;
import com.ge.med.mobile.nursing.utils.MessageEvent;
import com.ge.med.mobile.nursing.utils.ShiJianUtil;
import com.ge.med.mobile.nursing.utils.refresh.RefreshFooter;
import com.ge.med.mobile.nursing.utils.refresh.RefreshHeader;
import com.ge.med.mobile.nursing.utils.refresh.SpringView;

import org.xutils.common.util.LogUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * 医嘱
 */
public class YZFragment extends BaseFragment implements INetworkHandler {
    public static final String TYPE_YZ_HULI = Constant.TYPE_YZ_HULI;
    private ListView mHzYzLv;
    private SpringView mRefreshLayout;
    //    private BGARefreshLayout refreshLayout;
    private HzYzFragLvItemAdapter hzYzFragLvItemAdapter;
    private Bundle mBundle;
    private JiaZaiDialog jiaZaiDialog;
    private DoctorOrderDao mOrderDao;
    private List<YiZhuBean.DataBean> mYizhuBeanList;
    private ArrayList<Integer> yzShuList;
    public static Map<String, RefreshDate> mRefreshDate;
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;
    private String mOrderType = Constant.YZ_TYPE_ALL;
    public static Map<String, Long> lastCallNetTime = new HashMap<String, Long>();
    private RefreshDate refreshDate;
    private HZActivity hzActivity;
    private boolean mIsSaoMa = true;
    private int saoMaHisId;
    private YiZhuPopuwindow yiZhuPopuwindow;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            LogUtil.d("定位 msg.what：" + msg.what);
            if (msg.what == 1) {
                if (mHzYzLv != null){
                    mHzYzLv.setSelection(msg.arg1);
                    LogUtil.d("定位 msg.arg1：" + msg.arg1);
                    mIsSaoMa = false;
                }
            }
        }
    };

    public ArrayList<Integer> getYzShuList() {
        return yzShuList;
    }

    class RefreshDate {
        private Long upDate;
        private Long downDate;

        public Long getUpDate() {
            return upDate;
        }

        public void setUpDate(Long upDate) {
            this.upDate = upDate;
        }

        public Long getDownDate() {
            return downDate;
        }

        public void setDownDate(Long downDate) {
            this.downDate = downDate;
        }
    }

    @Override
    public int setRootView() {
        return R.layout.fragment_yz;
    }

    @Override
    public void initViews() {
        mHzYzLv = (ListView) mRootView.findViewById(R.id.hz_yz_lv);
        mRefreshLayout = (SpringView) mRootView.findViewById(R.id.rl_modulename_refresh);

        hzActivity = (HZActivity) mActivitySelf;
        mBundle = hzActivity.getmBundle();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initRefresh();
        // 隐藏评估标签
        hzActivity.labels_layout.setVisibility(View.GONE);
    }

    //初始化下拉刷新 ，上拉加载更多。
    private void initRefresh() {
        mRefreshLayout.setType(SpringView.Type.FOLLOW);
        mRefreshLayout.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新回调
                NetworkForDoctorOrder.callFindDoctorOrderMore(
                        new INetworkHandler() {
                            @Override
                            public void handleOnError() {
                                mRefreshLayout.onFinishFreshAndLoad();
                            }

                            @Override
                            public void handleOnError(String urlStr) {
                                mRefreshLayout.onFinishFreshAndLoad();
                            }

                            @Override
                            public void handleSuccess(Object obj) {
                                mRefreshLayout.onFinishFreshAndLoad();
                                refreshDate = mRefreshDate.get(mSelectedHZ.getPatientid());
                                LogUtil.d("before refreshDate getUpDate :" + ShiJianUtil.longToString(refreshDate.getUpDate(), "yyyy-MM-dd HH:mm:ss"));
                                long upDate = refreshDate.getUpDate();
                                upDate = upDate + (1000 * 60 * 60 * 12);
                                refreshDate.setUpDate(upDate);
                                mRefreshDate.put(mSelectedHZ.getPatientid(), refreshDate);
                                LogUtil.d("after refreshDate getUpDate :" + ShiJianUtil.longToString(refreshDate.getUpDate(), "yyyy-MM-dd HH:mm:ss"));
                            }
                        }, mSelectedHZ.getPatientid(), refreshDate.getUpDate(), 1,
                        Constant.YZ_TYPE_ALL.equals(mOrderType) ? null : mOrderType, Constant.YZ_TYPE_CHECKED);
            }
            @Override
            public void onLoadmore() {
                //上拉加载更多回调
                NetworkForDoctorOrder.callFindDoctorOrderMore(
                        new INetworkHandler() {
                            @Override
                            public void handleOnError() {
                                mRefreshLayout.onFinishFreshAndLoad();
                            }

                            @Override
                            public void handleOnError(String urlStr) {
                                mRefreshLayout.onFinishFreshAndLoad();
                            }

                            @Override
                            public void handleSuccess(Object obj) {
                                mRefreshLayout.onFinishFreshAndLoad();
                                refreshDate = mRefreshDate.get(mSelectedHZ.getPatientid());
                                LogUtil.d("before refreshDate getDownDate :" + ShiJianUtil.longToString(refreshDate.getDownDate(), "yyyy-MM-dd HH:mm:ss"));
                                long downDate = refreshDate.getDownDate();
                                downDate = downDate - (1000 * 60 * 60 * 12);
                                refreshDate.setDownDate(downDate);
                                mRefreshDate.put(mSelectedHZ.getPatientid(), refreshDate);
                                LogUtil.d("after refreshDate getDownDate :" + ShiJianUtil.longToString(refreshDate.getDownDate(), "yyyy-MM-dd HH:mm:ss"));
                            }
                        }, mSelectedHZ.getPatientid(), refreshDate.getDownDate(), 0,
                        Constant.YZ_TYPE_ALL.equals(mOrderType) ? null : mOrderType, Constant.YZ_TYPE_CHECKED);
            }
        });
        //下拉刷新
        mRefreshLayout.setHeader(new RefreshHeader(mActivitySelf, Config.pullAnimSrcs, Config.refreshAnimSrcs));
        //上拉加载
        mRefreshLayout.setFooter(new RefreshFooter(mActivitySelf, Config.loadingAnimSrcs));
    }


    @Override
    public void onResume() {
        super.onResume();
        if(mBundle == null){
            mBundle = hzActivity.getmBundle();
        }
        saoMaHisId = mBundle.getInt(Constant.BUNDLE_KEY_IS_SAOMA);
        mSelectedHZ = (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
        if (mSelectedHZ == null) {
            showToastShort("未知患者！");
            LogUtil.e("Can not get current patient from bundle!");
        } else {
            if (mRefreshDate == null) mRefreshDate = new HashMap<>();
            if (mRefreshDate.get(mSelectedHZ.getPatientid()) == null) {
                RefreshDate rd = new RefreshDate();
                long nowTime = System.currentTimeMillis();
                long UpDateLong = nowTime;
                long DownDateLong = nowTime;
                try {
                    String s = ShiJianUtil.longToString(nowTime, "yyyy-MM-dd");
                    String mUpDateStr = s + " 23:59:59";
                    String mDownDateStr = s + " 00:00:00";
                    UpDateLong = ShiJianUtil.stringToLong(mUpDateStr, "yyyy-MM-dd HH:mm;ss");
                    DownDateLong = ShiJianUtil.stringToLong(mDownDateStr, "yyyy-MM-dd HH:mm:ss");
                } catch (ParseException e) {
                    LogUtil.e("时间格式转换出错！");
                }
                rd.setUpDate(UpDateLong);
                rd.setDownDate(DownDateLong);
                mRefreshDate.put(mSelectedHZ.getPatientid(), rd);
                LogUtil.d("init refreshDate getDownDate :" + ShiJianUtil.longToString(rd.getDownDate(), "yyyy-MM-dd HH:mm:ss"));
            }
            refreshDate = mRefreshDate.get(mSelectedHZ.getPatientid());
            hzYzFragLvItemAdapter = new HzYzFragLvItemAdapter(hzActivity, mYizhuBeanList, this);
            hzYzFragLvItemAdapter.setPosList(ActivityUtils.suanPos(mYizhuBeanList));
            mHzYzLv.setAdapter(hzYzFragLvItemAdapter);

            String patientid = mSelectedHZ.getPatientid();
            long interval = System.currentTimeMillis() - (lastCallNetTime.get(patientid) == null ? 0 : lastCallNetTime.get(patientid).longValue());
            if (lastCallNetTime == null || interval > HZActivity.CALL_NET_INTERVAL) {
                LogUtil.d("interval is " + interval + ", setting is " + HZActivity.CALL_NET_INTERVAL);
                jiaZaiDialog.setCanceledOnTouchOutside(false);
                jiaZaiDialog.show();
                NetworkForDoctorOrder.callFindDoctorOrderByADay(this, null, patientid, Constant.YZ_TYPE_CHECKED);
                lastCallNetTime.put(patientid, System.currentTimeMillis());
            } else {
                List<YiZhuBean.DataBean> doctorOrdersExcludeStatus
                        = mOrderDao.findDoctorOrdersExcludeStatus(mSelectedHZ.getPatientid(), null, Constant.YZ_TYPE_WEIHEDUI);
                if (mYizhuBeanList == null) {
                    mYizhuBeanList = new ArrayList<>();
                }
                mYizhuBeanList.clear();
                for (YiZhuBean.DataBean dataBean : doctorOrdersExcludeStatus) {
                    if (!(Constant.YZ_TYPE_DAIBAIYAO.equals(dataBean.getOrderststus())
                            || Constant.YZ_TYPE_DAIPEIYE.equals(dataBean.getOrderststus()))) {
                        mYizhuBeanList.add(dataBean);
                    }
                }
                LogUtil.e("扫码 跳转0" + saoMaHisId);
                if (saoMaHisId != 0 && mIsSaoMa) {
                    LogUtil.e("扫码 跳转1" + saoMaHisId);
                    hzActivity.afterDoctorOrderScanned(saoMaHisId);
                }
                refreshData(mOrderType, mIsSaoMa, true, saoMaHisId);
                LogUtil.d("Successfully load [" + (mYizhuBeanList == null ? "null" : mYizhuBeanList.size())
                        + "] patient[" + patientid + "] data from local DB!");
            }
        }
    }

    private void locateFirstDisplayItem(boolean isWeiZhi, boolean isSaoMa, int hisId) {
        int i = 0;
        String nowDateString = DateUtils.getDateString("HH", System.currentTimeMillis());
        int nowDateInt = Integer.parseInt(nowDateString) + 1;
        if (mYizhuBeanList != null && mYizhuBeanList.size() > 0) {
            long starttime = 0l;
            String dateString = "";
            int dateInt = 0;
            for (YiZhuBean.DataBean dataBean : mYizhuBeanList) {
                starttime = dataBean.getStarttime();
                dateString = DateUtils.getDateString("HH", starttime);
                dateInt = Integer.parseInt(dateString);
                if (isSaoMa) {
                    if (hisId == dataBean.getId()) {
                        LogUtil.d("扫码 定位 i：" + mYizhuBeanList.indexOf(dataBean));
                        i = mYizhuBeanList.indexOf(dataBean);
                        break;
                    }
                } else {
                    if (dateInt < nowDateInt) {
                        i = mYizhuBeanList.indexOf(dataBean);
                        System.out.println("YZFragment.onResponse--->" + i);
                        break;
                    }
                }

            }
        }
        if (isSaoMa) {
            Message msg = mHandler.obtainMessage(1);
            msg.arg1 = i;
            LogUtil.d("定位 i：" + i);
            mHandler.sendMessage(msg);
        } else {
            if (isWeiZhi) {
                Message msg = mHandler.obtainMessage(1);
                msg.arg1 = i;
                LogUtil.d("定位 i：" + i);
                mHandler.sendMessage(msg);
            }
        }
    }

    @Override
    public void initDatas() {
        jiaZaiDialog = new JiaZaiDialog(mActivitySelf);
//        initRefreshLayout(mRefreshLayout);
        mOrderDao = new DoctorOrderDaoImpl();

    }
    @Override
    public void init() {

        mHzYzLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public void yzTiaoZhuan(int position) {
        if (hzYzFragLvItemAdapter == null) {
            hzYzFragLvItemAdapter = new HzYzFragLvItemAdapter(hzActivity, mYizhuBeanList,  this);
            hzYzFragLvItemAdapter.setPosList(ActivityUtils.suanPos(mYizhuBeanList));
        }
        YiZhuBean.DataBean dataBean = hzYzFragLvItemAdapter.getDbYiZhuDaTaList().get(position);

        if (Constant.YZ_TYPE_YIZHIXING.equals(dataBean.getOrderststus())) {
            if (!(Constant.TYPE_YZ_PISHI.equals(dataBean.getOrdertype())
                    && (null == dataBean.getOrderresult() || "".equals(dataBean.getOrderresult())))) {
                return;
            }
        }
        if (Constant.YZ_TYPE_YIQUXIAO.equals(dataBean.getOrderststus())) {
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
        mBundle.putInt("pos", position);
        mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU, dataBean);
        mBundle.putIntegerArrayList("yzshu", yzShuList);
        switch (dataBean.getOrdertype()) {
            //注射
            //皮试
            case Constant.TYPE_YZ_PISHI://皮试
            case Constant.TYPE_YZ_JIROUZHUSHE://肌肉注射
            case Constant.TYPE_YZ_PIXIAZHUSHE://皮下注射
            case Constant.TYPE_YZ_JINGMAIZHUSHE://静脉注射
            case Constant.TYPE_YZ_RUHU://入壶
                if(Constant.TYPE_YZ_PISHI.equals(dataBean.getOrdertype())
                        && Constant.YZ_TYPE_YIZHIXING.equals(dataBean.getOrderststus())){
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
                                if (hzActivity.getUserid() != null) {
                                    if (hzActivity.getUserid().equals(orderExecuteRecordsBean.getUserid())) {
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

    @Override
    public boolean useTitleBar() {
        return false;
    }

    public void calculateCount() {
        LogUtil.d("calculateCount mYizhuBeanList.size() is " + (mYizhuBeanList == null ? "null" : mYizhuBeanList.size()));
        yzShuList = new ArrayList<>();
        for (int i = 0; i < Constant.YZ_TYPE_NUMBER; i++) {
            yzShuList.add(Integer.valueOf(0));
        }
        int totalCount = 0;
        if (mYizhuBeanList != null && !mYizhuBeanList.isEmpty()) {
            for (YiZhuBean.DataBean dataBean : mYizhuBeanList) {
                if (Constant.YZ_TYPE_WEIZHIXING.equals(dataBean.getOrderststus())) {
                    totalCount++;
                    if (Constant.TYPE_YZ_ZHILIAO.equals(dataBean.getOrdertype())) {
                        yzShuList.set(1, yzShuList.get(1) + 1);
                    }
                    if (Constant.TYPE_YZ_JINGMAIZHUSHE.equals(dataBean.getOrdertype())) {
                        yzShuList.set(2, yzShuList.get(2) + 1);
                    }
                    if (Constant.TYPE_YZ_SHUYE.equals(dataBean.getOrdertype())) {
                        yzShuList.set(3, yzShuList.get(3) + 1);
                    }
                    if (Constant.TYPE_YZ_KOUFU.equals(dataBean.getOrdertype())) {
                        yzShuList.set(4, yzShuList.get(4) + 1);
                    }
                    if (Constant.TYPE_YZ_JIANYAN_UN_BLOOD.equals(dataBean.getOrdertype())) {
                        yzShuList.set(5, yzShuList.get(5) + 1);
                    }
                    if (Constant.TYPE_YZ_SHUXUE.equals(dataBean.getOrdertype())) {
                        yzShuList.set(6, yzShuList.get(6) + 1);
                    }
                    if (Constant.TYPE_YZ_PISHI.equals(dataBean.getOrdertype())) {
                        yzShuList.set(7, yzShuList.get(7) + 1);
                    }
                    if (Constant.TYPE_YZ_PIXIAZHUSHE.equals(dataBean.getOrdertype())) {
                        yzShuList.set(8, yzShuList.get(8) + 1);
                    }
                    if (Constant.TYPE_YZ_JIROUZHUSHE.equals(dataBean.getOrdertype())) {
                        yzShuList.set(9, yzShuList.get(9) + 1);
                    }
                    if (Constant.TYPE_YZ_SHANSHI.equals(dataBean.getOrdertype())) {
                        yzShuList.set(10, yzShuList.get(10) + 1);
                    }
                    if (Constant.TYPE_YZ_HULI.equals(dataBean.getOrdertype())) {
                        yzShuList.set(11, yzShuList.get(11) + 1);
                    }
                    if (Constant.TYPE_YZ_JIANYAN_BLOOD.equals(dataBean.getOrdertype())) {
                        yzShuList.set(12, yzShuList.get(12) + 1);
                    }
                    if (Constant.TYPE_YZ_RUHU.equals(dataBean.getOrdertype())) {
                        yzShuList.set(13, yzShuList.get(13) + 1);
                    }
                    if (Constant.ORDER_DURATION_CHANGQI_1.equals(dataBean.getOrderduration())) {
                        yzShuList.set(14, yzShuList.get(14) + 1);
                    }
                    if (Constant.ORDER_DURATION_LINGSHI_0.equals(dataBean.getOrderduration())) {
                        yzShuList.set(15, yzShuList.get(15) + 1);
                    }
                    if (Constant.ORDER_DURATION_LIJI_2.equals(dataBean.getOrderduration())) {
                        yzShuList.set(16, yzShuList.get(16) + 1);
                    }
                    if (Constant.TYPE_YZ_CAIXUE.equals(dataBean.getOrdertype())) {
                        yzShuList.set(17, yzShuList.get(17) + 1);
                    }
                }
            }
        }
        yzShuList.set(0, Integer.valueOf(totalCount));
        ((HZActivity) mActivitySelf).mHzYzShuTv.setText(yzShuList.get(0) + "");
        ((HZActivity) mActivitySelf).setmHzYzShuTv();
        int i = 0;
        for (Integer shu : yzShuList) {
            LogUtil.d("yzShuList.get(" + i++ + ") is " + shu);
        }
        setActivityCount();
    }

    private void setActivityCount() {
        try {
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangQuanbuYizhuShu.setText(yzShuList.get(0) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuFuzhuZhiliaoShu.setText(yzShuList.get(1) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuSheShu.setText(yzShuList.get(2) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuShuYeShu.setText(yzShuList.get(3) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuSkouFuShu.setText(yzShuList.get(4) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuJianYanShu.setText(yzShuList.get(5) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuShuXueShu.setText(yzShuList.get(6) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuPishiShu.setText(yzShuList.get(7) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangPixiaZhuSheShu.setText(yzShuList.get(8) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangJirouZhuSheShu.setText(yzShuList.get(9) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuShanshiZhiliaoShu.setText(yzShuList.get(10) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuHuliZhiliaoShu.setText(yzShuList.get(11) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuJianYanXueShu.setText(yzShuList.get(12) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuRuhuShu.setText(yzShuList.get(13) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuChangqiShu.setText(yzShuList.get(14) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuLinshiShu.setText(yzShuList.get(15) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuLijiShu.setText(yzShuList.get(16) + "");
            ((HZActivity) mActivitySelf).mYiZhuXuanXiangZhuCaixueShu.setText(yzShuList.get(17) + "");
        } catch (Exception e) {
            LogUtil.e("YZFragment setActivityCount()");
        }
    }

    public void refreshData(String yiZhuType, boolean isSaoMa, boolean isWeiZhi, int hisId) {
        LogUtil.d("YZFragment.refreshData(" + yiZhuType + ") calling...");
        mOrderType = yiZhuType;
        if (mOrderType != null) {
            calculateCount();
            if (Constant.YZ_TYPE_ALL.equals(mOrderType)) {
                if (hzYzFragLvItemAdapter != null && mYizhuBeanList != null) {
                    hzYzFragLvItemAdapter.setDbYiZhuDaTaList(mOrderDao.getFPaiXuDBYiZhuDatabean(mYizhuBeanList));
                    hzYzFragLvItemAdapter.setPosList(ActivityUtils.suanPos(mYizhuBeanList));
                    hzYzFragLvItemAdapter.notifyDataSetChanged();
                }
            } else {
                if (hzYzFragLvItemAdapter != null) {
                    List<YiZhuBean.DataBean> dataBeen = getOrdersByType(mOrderType);
                    LogUtil.d("refresh type[" + mOrderType + "] doctor orders[" + (dataBeen == null ? "null" : dataBeen.size()) + "].");
                    if (dataBeen == null) dataBeen = new ArrayList<>();
                    hzYzFragLvItemAdapter.setDbYiZhuDaTaList(mOrderDao.getFPaiXuDBYiZhuDatabean(dataBeen));
                    hzYzFragLvItemAdapter.setPosList(ActivityUtils.suanPos(mYizhuBeanList));
                    hzYzFragLvItemAdapter.notifyDataSetChanged();
                    if (dataBeen.isEmpty()) {
                        showToastShort("暂时没有医嘱数据！");
                    }
                }
            }

            locateFirstDisplayItem(isWeiZhi, isSaoMa, hisId);
        }
    }

    public List<YiZhuBean.DataBean> getOrdersByType(String type) {
        List<YiZhuBean.DataBean> dataBeen = new ArrayList<>();
        if (mYizhuBeanList != null && type != null) {
            for (YiZhuBean.DataBean dataB : mYizhuBeanList) {
                if (Constant.ORDER_DURATION_CHANGQI.equals(type) && Constant.ORDER_DURATION_CHANGQI_1.equals(dataB.getOrderduration())) {
                    dataBeen.add(dataB);
                } else if (Constant.ORDER_DURATION_LINGSHI.equals(type) && Constant.ORDER_DURATION_LINGSHI_0.equals(dataB.getOrderduration())) {
                    dataBeen.add(dataB);
                } else if (Constant.ORDER_DURATION_LIJI.equals(type) && Constant.ORDER_DURATION_LIJI_2.equals(dataB.getOrderduration())) {
                    dataBeen.add(dataB);
                } else {
                    if (type.equals(dataB.getOrdertype())) {
                        dataBeen.add(dataB);
                    }
                }
            }
        }
        return dataBeen;
    }

    private void clearWaitingWindow() {
        if (jiaZaiDialog != null) jiaZaiDialog.cancel();
//        if (refreshLayout != null) {
//            refreshLayout.endLoadingMore();
//            refreshLayout.endRefreshing();
//        }
    }

    @Override
    public void handleOnError() {
        clearWaitingWindow();
        showToastShort("无法加载医嘱！");
    }

    @Override
    public void handleOnError(String urlStr) {
        clearWaitingWindow();
        showToastShort("无法加载医嘱！");
    }

    @Override
    public void handleSuccess(Object obj) {
        clearWaitingWindow();
        if (obj == null) {
            LogUtil.i("Can not handle anything since obj is null!");
        } else if (obj instanceof List && ((List) obj).size() > 0) {
            if (((List) obj).get(0) instanceof YiZhuBean.DataBean) {
                mYizhuBeanList = (List<YiZhuBean.DataBean>) obj;
                LogUtil.e("扫码 跳转0" + saoMaHisId);
                if (saoMaHisId != 0 && mIsSaoMa) {
                    LogUtil.e("扫码 跳转1" + saoMaHisId);
                    hzActivity.afterDoctorOrderScanned(saoMaHisId);
                }
                refreshData(mOrderType, mIsSaoMa, true, saoMaHisId);
                LogUtil.d("Successfully refresh patient[" + mSelectedHZ.getPatientid() + "] data!");
            }
        }
    }

    //接受消息的地方(在Android的UI线程中)
    public void onEventMainThread(MessageEvent event) {
        if ("数据存储成功".equals(event.message)) {
            jiaZaiDialog.cancel();
//            if (refreshLayout != null) {
//                refreshLayout.endRefreshing();
//                refreshLayout.endLoadingMore();
//            }
            List<YiZhuBean.DataBean> doctorOrdersExcludeStatus
                    = mOrderDao.findDoctorOrdersExcludeStatus(mSelectedHZ.getPatientid(), null, Constant.YZ_TYPE_WEIHEDUI);
            if(mYizhuBeanList == null){
                mYizhuBeanList = new ArrayList<>();
            }
            mYizhuBeanList.clear();
            for (YiZhuBean.DataBean dataBean : doctorOrdersExcludeStatus) {
                if (!(Constant.YZ_TYPE_DAIBAIYAO.equals(dataBean.getOrderststus())
                        || Constant.YZ_TYPE_DAIPEIYE.equals(dataBean.getOrderststus()))) {
                    mYizhuBeanList.add(dataBean);
                }
            }
            refreshData(mOrderType, mIsSaoMa, true, saoMaHisId);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }
}
