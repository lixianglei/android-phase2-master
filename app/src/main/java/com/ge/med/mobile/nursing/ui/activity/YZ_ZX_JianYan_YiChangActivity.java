package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.sj.library.control.ActivityControl;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.HuanZheLieBiaoInterface;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBExceptionDefine;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.AdapterUtil;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.utils.MyExceptionDingYi;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
//医嘱 检验 异常
public class YZ_ZX_JianYan_YiChangActivity extends MyBaseActivity implements View.OnClickListener {

    private LinearLayout mYzZxZhuSheLl1;
    private CheckBox mYzZxZhuSheCb11;
    private CheckBox mYzZxZhuSheCb12;
    private CheckBox mYzZxZhuSheCb13;
    private LinearLayout mYzZxZhuSheLl2;
    private CheckBox mYzZxZhuSheCb21;
    private CheckBox mYzZxZhuSheCb22;
    private CheckBox mYzZxZhuSheCb23;
    private LinearLayout mYzZxZhuSheLl3;
    private CheckBox mYzZxZhuSheCb31;
    private CheckBox mYzZxZhuSheCb32;
    private CheckBox mYzZxZhuSheCb33;
    private LinearLayout mYzZxZhuSheLl4;
    private CheckBox mYzZxZhuSheCb41;
    private CheckBox mYzZxZhuSheCb42;
    private CheckBox mYzZxZhuSheCb43;
    private LinearLayout mYzZxZhuSheLl5;
    private LinearLayout mYzZxZhuSheLl6;
    private CheckBox mYzZxZhuSheCb51;
    private CheckBox mYzZxZhuSheCb52;
    private CheckBox mYzZxZhuSheCb53;
    private CheckBox mYzZxZhuSheCb61;
    private CheckBox mYzZxZhuSheCb62;
    private CheckBox mYzZxZhuSheCb63;
    private Button mYzZxJianYanYiChangZantingBt;
    private Button mYzZxJianYanYiChangZhixingBt;
    private List<String> list = new ArrayList<>();

    private TitleBar mTitleBar;
    private DocOrderPannel mDocOrderPannel;
    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private DBHuanZheLieBiao huanZheLieBiao;
    private HuanZheLieBiaoInterface huanZheLieBiaoInterface;

    private String userid;

    private List<CheckBox> checkBoxes = new ArrayList<>();
    private Integer mExceptiondefineid;
    private HuanZheLieBiaoBean.DataBean huanZheLieBiaoInterfaceDataBean;

    @Override
    public int setRootView() {
        return R.layout.activity_yz__zx__jian_yan__yi_chang;
    }

    @Override
    public void initViews() {
        mYzZxZhuSheLl1 = (LinearLayout) findViewById(R.id.yz_zx_zhu_she_ll1);
        mYzZxZhuSheCb11 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb1_1);
        mYzZxZhuSheCb12 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb1_2);
        mYzZxZhuSheCb13 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb1_3);
        mYzZxZhuSheLl2 = (LinearLayout) findViewById(R.id.yz_zx_zhu_she_ll2);
        mYzZxZhuSheCb21 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb2_1);
        mYzZxZhuSheCb22 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb2_2);
        mYzZxZhuSheCb23 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb2_3);
        mYzZxZhuSheLl3 = (LinearLayout) findViewById(R.id.yz_zx_zhu_she_ll3);
        mYzZxZhuSheCb31 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb3_1);
        mYzZxZhuSheCb32 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb3_2);
        mYzZxZhuSheCb33 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb3_3);
        mYzZxZhuSheLl4 = (LinearLayout) findViewById(R.id.yz_zx_zhu_she_ll4);
        mYzZxZhuSheCb41 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb4_1);
        mYzZxZhuSheCb42 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb4_2);
        mYzZxZhuSheCb43 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb4_3);
        mYzZxZhuSheLl5 = (LinearLayout) findViewById(R.id.yz_zx_zhu_she_ll5);
        mYzZxZhuSheCb51 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb5_1);
        mYzZxZhuSheCb52 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb5_2);
        mYzZxZhuSheCb53 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb5_3);
        mYzZxZhuSheLl6 = (LinearLayout) findViewById(R.id.yz_zx_zhu_she_ll6);
        mYzZxZhuSheCb61 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb6_1);
        mYzZxZhuSheCb62 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb6_2);
        mYzZxZhuSheCb63 = (CheckBox) findViewById(R.id.yz_zx_zhu_she_cb6_3);
        mYzZxJianYanYiChangZantingBt = (Button) findViewById(R.id.yz_zx_jian_yan_yi_chang_zanting_bt);
        mYzZxJianYanYiChangZhixingBt = (Button) findViewById(R.id.yz_zx_jian_yan_yi_chang_zhixing_bt);

        mBundle = getIntent().getBundleExtra("data");
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
        huanZheLieBiao = AdapterUtil.getPatientFromDB(dataBean.getPatientid());
        huanZheLieBiaoInterface = new HuanZheLieBiaoImpl();
        huanZheLieBiaoInterfaceDataBean = huanZheLieBiaoInterface.getDataBean(huanZheLieBiao);
        mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, huanZheLieBiaoInterfaceDataBean);

        mTitleBar = new TitleBar(this, huanZheLieBiaoInterfaceDataBean);
        mDocOrderPannel = new DocOrderPannel(this, dataBean);


        mYzZxZhuSheCb11.setOnClickListener(this);
        mYzZxZhuSheCb12.setOnClickListener(this);
        mYzZxZhuSheCb13.setOnClickListener(this);
        mYzZxZhuSheCb21.setOnClickListener(this);
        mYzZxZhuSheCb22.setOnClickListener(this);
        mYzZxZhuSheCb23.setOnClickListener(this);
        mYzZxZhuSheCb31.setOnClickListener(this);
        mYzZxZhuSheCb32.setOnClickListener(this);
        mYzZxZhuSheCb33.setOnClickListener(this);
        mYzZxZhuSheCb41.setOnClickListener(this);
        mYzZxZhuSheCb42.setOnClickListener(this);
        mYzZxZhuSheCb43.setOnClickListener(this);
        mYzZxZhuSheCb51.setOnClickListener(this);
        mYzZxZhuSheCb52.setOnClickListener(this);
        mYzZxZhuSheCb53.setOnClickListener(this);
        mYzZxZhuSheCb61.setOnClickListener(this);
        mYzZxZhuSheCb62.setOnClickListener(this);
        mYzZxZhuSheCb63.setOnClickListener(this);

        checkBoxes.add(mYzZxZhuSheCb11);
        checkBoxes.add(mYzZxZhuSheCb12);
        checkBoxes.add(mYzZxZhuSheCb13);
        checkBoxes.add(mYzZxZhuSheCb21);
        checkBoxes.add(mYzZxZhuSheCb22);
        checkBoxes.add(mYzZxZhuSheCb23);
        checkBoxes.add(mYzZxZhuSheCb31);
        checkBoxes.add(mYzZxZhuSheCb32);
        checkBoxes.add(mYzZxZhuSheCb33);
        checkBoxes.add(mYzZxZhuSheCb41);
        checkBoxes.add(mYzZxZhuSheCb42);
        checkBoxes.add(mYzZxZhuSheCb43);
        checkBoxes.add(mYzZxZhuSheCb51);
        checkBoxes.add(mYzZxZhuSheCb52);
        checkBoxes.add(mYzZxZhuSheCb53);
        checkBoxes.add(mYzZxZhuSheCb61);
        checkBoxes.add(mYzZxZhuSheCb62);
        checkBoxes.add(mYzZxZhuSheCb63);
        MyExceptionDingYi.setZhuShe_NOExce(checkBoxes,dataBean.getOrdertype());
        mYzZxJianYanYiChangZantingBt.setOnClickListener(this);
        mYzZxJianYanYiChangZhixingBt.setOnClickListener(this);
        if(mYzZxZhuSheCb21.getVisibility()==View.GONE){
            mYzZxZhuSheLl2.setVisibility(View.GONE);
        }
        if(mYzZxZhuSheCb31.getVisibility()==View.GONE){
            mYzZxZhuSheLl3.setVisibility(View.GONE);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        mDocOrderPannel.bindData(new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId()));
    }
    @Override
    public void initDatas() {
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        userid = sharePLogin.getUserid();
    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //  cb1_1
            case R.id.yz_zx_zhu_she_cb1_1:
                btKongZhi(mYzZxZhuSheCb11);
                break;
            //  cb1_2
            case R.id.yz_zx_zhu_she_cb1_2:
                btKongZhi(mYzZxZhuSheCb12);
                break;
            //  cb1_3
            case R.id.yz_zx_zhu_she_cb1_3:
                btKongZhi(mYzZxZhuSheCb13);
                break;
            //  cb2_1
            case R.id.yz_zx_zhu_she_cb2_1:
                btKongZhi(mYzZxZhuSheCb21);
                break;
            //  cb2_2
            case R.id.yz_zx_zhu_she_cb2_2:
                btKongZhi(mYzZxZhuSheCb22);
                break;
            //  cb2_3
            case R.id.yz_zx_zhu_she_cb2_3:
                btKongZhi(mYzZxZhuSheCb23);
                break;
            //  cb3_1
            case R.id.yz_zx_zhu_she_cb3_1:
                btKongZhi(mYzZxZhuSheCb31);
                break;
            //  cb3_2
            case R.id.yz_zx_zhu_she_cb3_2:
                btKongZhi(mYzZxZhuSheCb32);
                break;
            //  cb3_3
            case R.id.yz_zx_zhu_she_cb3_3:
                btKongZhi(mYzZxZhuSheCb33);
                break;
            //  cb4_1
            case R.id.yz_zx_zhu_she_cb4_1:
                btKongZhi(mYzZxZhuSheCb41);
                break;
            //  cb4_2
            case R.id.yz_zx_zhu_she_cb4_2:
                btKongZhi(mYzZxZhuSheCb42);
                break;
            //  cb4_3
            case R.id.yz_zx_zhu_she_cb4_3:
                btKongZhi(mYzZxZhuSheCb43);
                break;
            //  cb5_1
            case R.id.yz_zx_zhu_she_cb5_1:
                btKongZhi(mYzZxZhuSheCb51);
                break;
            //  cb5_2
            case R.id.yz_zx_zhu_she_cb5_2:
                btKongZhi(mYzZxZhuSheCb52);
                break;
            //  cb5_3
            case R.id.yz_zx_zhu_she_cb5_3:
                btKongZhi(mYzZxZhuSheCb53);
                break;
            //  cb6_1
            case R.id.yz_zx_zhu_she_cb6_1:
                btKongZhi(mYzZxZhuSheCb61);
                break;
            //  cb6_2
            case R.id.yz_zx_zhu_she_cb6_2:
                btKongZhi(mYzZxZhuSheCb62);

                break;
            //  cb6_3
            case R.id.yz_zx_zhu_she_cb6_3:
                btKongZhi(mYzZxZhuSheCb63);

                break;
            //  暂停
            case R.id.yz_zx_jian_yan_yi_chang_zanting_bt:
                cunshuju();

                break;
            //  执行
            case R.id.yz_zx_jian_yan_yi_chang_zhixing_bt:
                killSelf();
                break;
        }
    }

    private void cunshuju() {
        long currentTimeMillis = System.currentTimeMillis();
        dataBean.setLastupdatedby(userid);
        dataBean.setLastupdatetime(currentTimeMillis);
        List<YiZhuBean.DataBean.DoctorOrdersBean> doctorOrders = dataBean.getDoctorOrders();
        String yiChang = list.get(0);
        LogUtil.e("yiChang ---->"+yiChang);
        if (yiChang != null) {
            DBExceptionDefine first = DataSupport.where("exceptionname = ?", yiChang).findFirst(DBExceptionDefine.class);
            if (first != null) {
                mExceptiondefineid = first.getZid();
            }
        }
        if (doctorOrders != null && doctorOrders.size() > 0) {
            for (YiZhuBean.DataBean.DoctorOrdersBean doctorOrdersBean : doctorOrders) {
                List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean> orderExceptions = doctorOrdersBean.getOrderExceptions();
                if(orderExceptions == null ){
                    orderExceptions = new ArrayList<>();
                }

                YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean exceptionsBean = new YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean();
                exceptionsBean.setLastupdatedby(userid);
                exceptionsBean.setLastupdatetime(currentTimeMillis);
                exceptionsBean.setExceptionDefine(null);
                exceptionsBean.setExceptiondefineid(mExceptiondefineid);
                exceptionsBean.setExerecordid(null);
                exceptionsBean.setId(null);
                if (list.get(0) != null) {
                    DBExceptionDefine first = DataSupport.where("exceptionname = ? ", list.get(0)).findFirst(DBExceptionDefine.class);
                    exceptionsBean.setExceptiondefineid(first.getZid());
                }
//                exceptionsBean.setNote(list.get(0));
                exceptionsBean.setUserid(Integer.parseInt(userid));
                exceptionsBean.setOrderid(doctorOrdersBean.getId());
                orderExceptions.add(exceptionsBean);
                doctorOrdersBean.setHisid(dataBean.getId());
                doctorOrdersBean.setOrderExceptions(orderExceptions);

            }
            dataBean.setDoctorOrders(doctorOrders);

        } else {
            List<YiZhuBean.DataBean.DoctorOrdersBean> doctorOrders1 = new ArrayList<YiZhuBean.DataBean.DoctorOrdersBean>();
            YiZhuBean.DataBean.DoctorOrdersBean doctorOrdersBean = new YiZhuBean.DataBean.DoctorOrdersBean();
            List<YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean> orderExceptions = new ArrayList<YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean>();
            YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean exceptionsBean = new YiZhuBean.DataBean.DoctorOrdersBean.OrderExceptionsBean();
            exceptionsBean.setLastupdatedby(userid);
            exceptionsBean.setLastupdatetime(currentTimeMillis);
            exceptionsBean.setExerecordid(null);
            exceptionsBean.setId(null);
            exceptionsBean.setExceptionDefine(null);
            if (list.get(0) != null) {
                DBExceptionDefine first = DataSupport.where("exceptionname = ? ", list.get(0)).findFirst(DBExceptionDefine.class);
                exceptionsBean.setExceptiondefineid(first.getZid());
            }
            //                exceptionsBean.setNote(list.get(0));
            exceptionsBean.setUserid(Integer.parseInt(userid));
            exceptionsBean.setOrderid(doctorOrdersBean.getId());
            orderExceptions.add(exceptionsBean);
            doctorOrdersBean.setOrderExceptions(orderExceptions);
            doctorOrdersBean.setHisid(dataBean.getId());
            doctorOrders1.add(doctorOrdersBean);
            dataBean.setDoctorOrders(doctorOrders1);
        }
        dataBean.setOrderststus(Constant.YZ_TYPE_YIZANTING);
        new DoctorOrderDaoImpl().saveDoctorOrder(dataBean,true);
        NetworkForDoctorOrder.submitSingleDoctorOrder(YZ_ZX_JianYan_YiChangActivity.this, dataBean);
    }


    private void btKongZhi(CheckBox checkBox) {
        for (CheckBox checkBox1 : checkBoxes) {
            if (checkBox1.getId() == checkBox.getId()) {

            } else {
                checkBox1.setChecked(false);
            }
            if (checkBox1.isChecked()) {
                if("".equals(checkBox1.getText().toString())||checkBox1.getText().toString().isEmpty()){
                    checkBox1.setChecked(false);
                    list.remove(checkBox1.getText().toString());
                }else{
                    list.add(checkBox1.getText().toString());
                }
            } else {
                list.remove(checkBox1.getText().toString());
            }
        }
        btKongZhi();
    }

    private void btKongZhi() {
        if (list.size() > 0) {
            mYzZxJianYanYiChangZantingBt.setVisibility(View.VISIBLE);
        } else {
            mYzZxJianYanYiChangZantingBt.setVisibility(View.GONE);
        }
    }

    @Override
    public void handleSuccess(Object obj) {
        super.handleSuccess(obj);
        ActivityControl.killActivity(YZ_ZX_JianYanActivity.class);
        killSelf();
    }

    @Override
    public void handleOnError() {
        super.handleOnError();
        ActivityControl.killActivity(YZ_ZX_JianYanActivity.class);
        killSelf();
    }

    @Override
    public void handleOnError(String urlStr) {
        super.handleOnError(urlStr);
        ActivityControl.killActivity(YZ_ZX_JianYanActivity.class);
        killSelf();
    }

}
