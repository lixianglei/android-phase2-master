package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.HuanZheLieBiaoInterface;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.AdapterUtil;
import com.ge.med.mobile.nursing.ui.adapter.JianYanLvItemAdapter;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.ui.view.SelfDialog;

import org.xutils.common.util.LogUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class YZ_ZX_JianYanActivity extends MyBaseActivity {
    private ListView mHzYzLv;
    private Button mYzShuxueYichangBt;
    private Button mYzShuxueWanchengBt;
    private JianYanLvItemAdapter jianYanLvItemAdapter;
    private TitleBar mTitleBar;
    private DocOrderPannel mDocOrderPannel;
    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private DBHuanZheLieBiao huanZheLieBiao;
    private HuanZheLieBiaoInterface huanZheLieBiaoInterface;
    private SelfDialog selfDialog;
    private HuanZheLieBiaoBean.DataBean huanZheLieBiaoInterfaceDataBean;
    private String userid;
    private List<YiZhuBean.DataBean.OrderCheckPropertyBean> orderCheckProperty;
    private boolean isToast;

    @Override
    public int setRootView() {
        return R.layout.activity_yz__zx__jian_yan;
    }

    @Override
    public void initViews() {
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        userid = sharePLogin.getUserid();
        mHzYzLv = (ListView) findViewById(R.id.hz_yz_lv);
        mYzShuxueYichangBt = (Button) findViewById(R.id.yz_shuxue_yichang_bt);
        mYzShuxueWanchengBt = (Button) findViewById(R.id.yz_shuxue_wancheng_bt);
        mBundle = getIntent().getBundleExtra("data");
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
        huanZheLieBiao = AdapterUtil.getPatientFromDB(dataBean.getPatientid());
        huanZheLieBiaoInterface = new HuanZheLieBiaoImpl();
        huanZheLieBiaoInterfaceDataBean = huanZheLieBiaoInterface.getDataBean(huanZheLieBiao);
        mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, huanZheLieBiaoInterfaceDataBean);

        mTitleBar = new TitleBar(this, huanZheLieBiaoInterfaceDataBean);
        mDocOrderPannel = new DocOrderPannel(this, dataBean);
//异常按钮监听
        mYzShuxueYichangBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(YZ_ZX_JianYan_YiChangActivity.class, mBundle);
            }
        });
        orderCheckProperty = dataBean.getOrderCheckProperty();
        orderCheckProperty = paiXu(orderCheckProperty);
        jianYanLvItemAdapter = new JianYanLvItemAdapter(mActivitySelf, orderCheckProperty);
        mHzYzLv.setAdapter(jianYanLvItemAdapter);

    }
    @Override
    protected void onResume() {
        super.onResume();
        mDocOrderPannel.bindData(new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId()));
    }
    //排序 正
    public List<YiZhuBean.DataBean.OrderCheckPropertyBean> paiXu(List<YiZhuBean.DataBean.OrderCheckPropertyBean> paiDBYiZhuData) {
        if (paiDBYiZhuData == null) {
            return null;
        }
        Comparator<YiZhuBean.DataBean.OrderCheckPropertyBean> comparator = new Comparator<YiZhuBean.DataBean.OrderCheckPropertyBean>() {
            public int compare(YiZhuBean.DataBean.OrderCheckPropertyBean s1, YiZhuBean.DataBean.OrderCheckPropertyBean s2) {
                try {
                    if (s1.getSort() > s2.getSort()) {
                        return 1;
                    } else if (s2.getSort() == s1.getSort()) {
                        return 0;
                    }
                    return -1;
                } catch (Exception e) {
                    return 0;
                }
            }
        };
        Collections.sort(paiDBYiZhuData, comparator);
        return paiDBYiZhuData;
    }

    @Override
    public void afterCheckPropertyScanned(String CheckPropertyScanned) {
        for (int i = 0; i < orderCheckProperty.size(); i++) {
            if (orderCheckProperty.get(i) != null) {
                if (CheckPropertyScanned.equals(orderCheckProperty.get(i).getTesttubenumber())) {
                    jianYanLvItemAdapter.mCBFlag.put(i,true);
                    jianYanLvItemAdapter.notifyDataSetChanged();
                    isToast = true;
                }
            }
        }
        if (!isToast) {
            showMessage("条码不匹配！" + CheckPropertyScanned);
            isToast = false;
        }
    }

    @Override
    public void initDatas() {
        mYzShuxueWanchengBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selfDialog = new SelfDialog(mActivitySelf);
                selfDialog.setTitle("确认提交");

                selfDialog.setMessage("是否确认完成了所有的样本采集工作？");

                selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        selfDialog.dismiss();
                    }
                });
                selfDialog.setYesOnclickListener("确认", new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        tiJiao();
                        selfDialog.dismiss();
                    }
                });
                selfDialog.show();
            }
        });
    }

    public void tiJiao() {
        long l = System.currentTimeMillis();
        dataBean.setOrderststus(Constant.YZ_TYPE_YIZHIXING);
        try {
            dataBean.getDoctorOrders().get(0).setStatus(Constant.YZ_TYPE_YIZHIXING);
        } catch (Exception e) {
            LogUtil.e("dataBean.getDoctorOrders() is null!");
        }
        dataBean.setLastupdatedby(userid);
        dataBean.setExecuteby(userid);
        dataBean.setExetime(l);
        dataBean.setLastupdatetime(l);
        jiaZaiDialog.show();
        new DoctorOrderDaoImpl().saveDoctorOrder(dataBean,true);
        NetworkForDoctorOrder.submitSingleDoctorOrder(this, dataBean);
    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    @Override
    public void handleSuccess(Object obj) {
        super.handleSuccess(obj);
        jiaZaiDialog.cancel();
        killSelf();
    }

    @Override
    public void handleOnError() {
        super.handleOnError();
        jiaZaiDialog.cancel();
        killSelf();
    }

    @Override
    public void handleOnError(String urlStr) {
        super.handleOnError(urlStr);
        jiaZaiDialog.cancel();
        killSelf();

    }

}
