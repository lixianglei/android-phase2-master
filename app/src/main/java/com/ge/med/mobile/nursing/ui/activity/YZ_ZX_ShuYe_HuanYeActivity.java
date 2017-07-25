package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.HuanZheLieBiaoInterface;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.ui.adapter.AdapterUtil;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;

public class YZ_ZX_ShuYe_HuanYeActivity extends MyBaseActivity {

    private Button mYzZxZhuSheXiangQingBt;
    private LinearLayout mYzZxZhuSheBtLl;
    private Button mYzZxZhuSheBeizhuBt;
    private Button mYzZxZhuSheYichangBt;
    private Button mYzZxZhuSheNextBt;
    private TextView mXinyetiXinxiTv;
    private ImageView mXinyetiXinxiImgv;

    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private DBHuanZheLieBiao huanZheLieBiao;
    private HuanZheLieBiaoInterface huanZheLieBiaoInterface;

    private TitleBar mTitleBar;
    private DocOrderPannel mDocOrderPannel;
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;


    @Override
    public int setRootView() {
        return R.layout.activity_yz__zx__shu_ye__huan_ye;
    }

    @Override
    public void initViews() {
        mYzZxZhuSheXiangQingBt = (Button) findViewById(R.id.yz_zx_zhu_she_xiang_qing_bt);
        mYzZxZhuSheBtLl = (LinearLayout) findViewById(R.id.yz_zx_zhu_she_bt_ll);
        mYzZxZhuSheBeizhuBt = (Button) findViewById(R.id.yz_zx_zhu_she_beizhu_bt);
        mYzZxZhuSheYichangBt = (Button) findViewById(R.id.yz_zx_zhu_she_yichang_bt);
        mYzZxZhuSheNextBt = (Button) findViewById(R.id.yz_zx_zhu_she_next_bt);
        mXinyetiXinxiTv = (TextView) findViewById(R.id.xinyeti_xinxi_tv);
        mXinyetiXinxiImgv = (ImageView) findViewById(R.id.xinyeti_xinxi_imgv);

        mBundle = getIntent().getBundleExtra("data");
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
        huanZheLieBiao = AdapterUtil.getPatientFromDB(dataBean.getPatientid());
        huanZheLieBiaoInterface = new HuanZheLieBiaoImpl();
        mSelectedHZ = huanZheLieBiaoInterface.getDataBean(huanZheLieBiao);
        mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, mSelectedHZ);
        mTitleBar = new TitleBar(this, mSelectedHZ);
        mDocOrderPannel = new DocOrderPannel(this, dataBean);

        mXinyetiXinxiTv.setText(dataBean.getOrdername());

        mXinyetiXinxiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mXinyetiXinxiImgv.getVisibility() == View.GONE) {
                    mXinyetiXinxiImgv.setVisibility(View.VISIBLE);
                } else {
                    mXinyetiXinxiImgv.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void initDatas() {
        //备注跳转
        mYzZxZhuSheBeizhuBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(YZ_ZX_ZhuShe_BeiZhuActivity.class, mBundle);
            }
        });

        //异常按钮
        mYzZxZhuSheYichangBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(Constant.ZHU_SHE_2_KEY, Constant.ZHU_SHE_2);
                goToActivity(YZ_ZX_ZhuSheActivity.class, mBundle);
            }
        });
        //查看详情
        mYzZxZhuSheXiangQingBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(YZ_YaoPin_XiangQingActivity.class, mBundle);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDocOrderPannel.bindData(new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId()));
    }

    @Override
    public void init() {
        //下一步按钮监听
        mYzZxZhuSheNextBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mXinyetiXinxiImgv.getVisibility() == View.GONE) {
                    showToastShort("请确认换液液体！");
                } else {
                    mBundle.putBoolean(Constant.BUNDLE_KEY_IS_SHIRULIANG, false);
                    mBundle.putBoolean(Constant.BUNDLE_KEY_IS_HUANYE, false);
                    mBundle.putBoolean(Constant.BUNDLE_KEY_IS_HUANYE, false);
                    goToActivity(YZ_ZX_ShuYe_DiSuActivity.class, mBundle);
                }
            }
        });
        if(dataBean.getPharmList()==null || dataBean.getPharmList().size()<=0){
            mYzZxZhuSheXiangQingBt.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

}
