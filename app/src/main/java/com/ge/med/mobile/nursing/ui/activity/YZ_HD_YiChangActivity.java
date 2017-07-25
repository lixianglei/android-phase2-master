package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.utils.MyExceptionDingYi;

import java.util.ArrayList;
import java.util.List;

//R.layout.activity_yz__hd__yi_chang
public class YZ_HD_YiChangActivity extends MyBaseActivity implements View.OnClickListener {
    private RelativeLayout mYiZhuZhiXingFrameLayout;
    private Button mYzZxZhuSheXiangQingBt;
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
    private CheckBox mYzZxZhuSheCb51;
    private CheckBox mYzZxZhuSheCb52;
    private CheckBox mYzZxZhuSheCb53;
    private LinearLayout mYzZxZhuSheLl6;
    private CheckBox mYzZxZhuSheCb61;
    private CheckBox mYzZxZhuSheCb62;
    private CheckBox mYzZxZhuSheCb63;
    private LinearLayout mYzZxZhuSheBtLl;
    private Button mYzZxZhuSheBeizhuBt;
    private Button mYzZxZhuSheZhixingBt;
    private List<CheckBox> cbs = new ArrayList<>();


    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private List<String> list = new ArrayList<>();
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private TitleBar mTitleBar;
    private DocOrderPannel mOrderPanel;


    @Override
    public int setRootView() {
        return R.layout.activity_yz__hd__yi_chang;
    }

    @Override
    public void initViews() {
        mBundle = getIntent().getBundleExtra("data");
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);

        mYiZhuZhiXingFrameLayout = (RelativeLayout) findViewById(R.id.yi_zhu_zhi_xing_frame_layout);
        mYzZxZhuSheXiangQingBt = (Button) findViewById(R.id.yz_zx_zhu_she_xiang_qing_bt);
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
        mYzZxZhuSheBtLl = (LinearLayout) findViewById(R.id.yz_zx_zhu_she_bt_ll);
        mYzZxZhuSheBeizhuBt = (Button) findViewById(R.id.yz_zx_zhu_she_beizhu_bt);
        mYzZxZhuSheZhixingBt = (Button) findViewById(R.id.yz_zx_zhu_she_zhixing_bt);

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
        mYzZxZhuSheBeizhuBt.setOnClickListener(this);
        mYzZxZhuSheZhixingBt.setOnClickListener(this);
        mYzZxZhuSheXiangQingBt.setOnClickListener(this);
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
        //根据医嘱类型配置异常
//        MyExceptionDingYi.setZhuShe_NOExce(checkBoxes,Constant.TYPE_YZ_ZHUSHE);
        MyExceptionDingYi.setZhuShe_NOExce(checkBoxes,dataBean.getOrdertype());
        if(mYzZxZhuSheCb21.getVisibility()==View.GONE){
            mYzZxZhuSheLl2.setVisibility(View.GONE);
        }
        if(mYzZxZhuSheCb31.getVisibility()==View.GONE){
            mYzZxZhuSheLl3.setVisibility(View.GONE);
        }
        mTitleBar = new TitleBar(this, (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE));
        mOrderPanel = new DocOrderPannel(this, dataBean);
    }


    @Override
    public void initDatas() {
        mYzZxZhuSheBeizhuBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(YZ_ZX_ZhuShe_BeiZhuActivity.class, mBundle,0);
            }
        });
 }
    @Override
    protected void onResume() {
        super.onResume();
        mOrderPanel.bindData(new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId()));
    }
    @Override
    public void init() {
        cbs.add(mYzZxZhuSheCb11);
        cbs.add(mYzZxZhuSheCb12);
        cbs.add(mYzZxZhuSheCb13);
        cbs.add(mYzZxZhuSheCb21);
        cbs.add(mYzZxZhuSheCb22);
        cbs.add(mYzZxZhuSheCb23);
        cbs.add(mYzZxZhuSheCb31);
        cbs.add(mYzZxZhuSheCb32);
        cbs.add(mYzZxZhuSheCb33);
        cbs.add(mYzZxZhuSheCb41);
        cbs.add(mYzZxZhuSheCb42);
        cbs.add(mYzZxZhuSheCb43);
        cbs.add(mYzZxZhuSheCb51);
        cbs.add(mYzZxZhuSheCb52);
        cbs.add(mYzZxZhuSheCb53);
        cbs.add(mYzZxZhuSheCb61);
        cbs.add(mYzZxZhuSheCb62);
        cbs.add(mYzZxZhuSheCb63);

        mYzZxZhuSheXiangQingBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(YZ_YaoPin_XiangQingActivity.class, mBundle,0);
            }
        });
        mYzZxZhuSheZhixingBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<String>();
                for (CheckBox cb : cbs) {
                    if (cb.isChecked()) {
                        list.add(cb.getText().toString());
                    }
                }
                Intent intent = new Intent();
                intent.setClass(mActivitySelf, YZ_HD_RenActivity.class);
                mBundle.putStringArrayList("yichang", list);
                intent.putExtra("data", mBundle);
                setResult(0, intent);
                killSelf();
            }
        });
        if(dataBean.getPharmList()==null ||dataBean.getPharmList().size()<=0 ){
            mYzZxZhuSheXiangQingBt.setVisibility(View.GONE);
        }
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
        }
    }
    private void btKongZhi(CheckBox checkBox) {
            for (CheckBox checkBox1 : checkBoxes) {
                if (checkBox1.getId() == checkBox.getId()) {

                }else{
                    checkBox1.setChecked(false);
                }
                if (checkBox1.isChecked()) {
                    if("".equals(checkBox1.getText().toString())||checkBox1.getText().toString().isEmpty()){
                        checkBox1.setChecked(false);
                        list.remove(checkBox1.getText().toString());
                    }else{
                        list.add(checkBox1.getText().toString());
                    }
                }else{
                    list.remove(checkBox1.getText().toString());
                }
            }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}
