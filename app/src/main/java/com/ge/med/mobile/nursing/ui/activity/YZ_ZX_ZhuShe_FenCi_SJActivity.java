package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.sj.library.control.ActivityControl;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.TouchTime;
import com.ge.med.mobile.nursing.dao.HuanZheLieBiaoInterface;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.ui.adapter.AdapterUtil;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.utils.DataConverter;
import com.mitac.lib.bcr.utils.BARCODE;

public class YZ_ZX_ZhuShe_FenCi_SJActivity extends MyBaseActivity implements View.OnClickListener {

    private TextView mTiZhengZhi;
    private TextView mTiZhengDanWei;
    private LinearLayout mDiyiPaiBtLl1;
    private Button mBt11;
    private Button mBt12;
    private Button mBt13;
    private Button mBt14;
    private LinearLayout mDiyiPaiBtLl2;
    private Button mBt21;
    private Button mBt22;
    private Button mBt23;
    private Button mBt24;
    private LinearLayout mDiyiPaiBtLl3;
    private Button mBt31;
    private Button mBt32;
    private Button mBt33;
    private Button mBt34;
    private LinearLayout mDiyiPaiBtLl4;
    private Button mBt41;
    private Button mBt42;
    private Button mBt43;
    private ImageButton mBt44;
    private RadioGroup mDiyiPaiBtLl5;
    private RadioButton mBt51;
    private RadioButton mBt52;
    private RadioButton mBt53;
    private RadioButton mBt54;
    private Button mYzZxZhusheFenciBaocunBt;

    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private DBHuanZheLieBiao huanZheLieBiao;
    private TitleBar mTitleBar;
    private DocOrderPannel mDocOrderPannel;
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;
    private HuanZheLieBiaoInterface huanZheLieBiaoInterface;
    @Override
    public int setRootView() {
        return R.layout.activity_yz__zx__zhu_she__fen_ci_sj;
    }
    @Override
    protected void onResume() {
        super.onResume();
        mDocOrderPannel.bindData(new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId()));
    }
    @Override
    public void initViews() {
        mTiZhengZhi = (TextView) findViewById(R.id.ti_zheng_zhi);
        mTiZhengDanWei = (TextView) findViewById(R.id.ti_zheng_dan_wei);
        mDiyiPaiBtLl1 = (LinearLayout) findViewById(R.id.diyi_pai_bt_ll_1);
        mBt11 = (Button) findViewById(R.id.bt_1_1);
        mBt12 = (Button) findViewById(R.id.bt_1_2);
        mBt13 = (Button) findViewById(R.id.bt_1_3);
        mBt14 = (Button) findViewById(R.id.bt_1_4);
        mDiyiPaiBtLl2 = (LinearLayout) findViewById(R.id.diyi_pai_bt_ll_2);
        mBt21 = (Button) findViewById(R.id.bt_2_1);
        mBt22 = (Button) findViewById(R.id.bt_2_2);
        mBt23 = (Button) findViewById(R.id.bt_2_3);
        mBt24 = (Button) findViewById(R.id.bt_2_4);
        mDiyiPaiBtLl3 = (LinearLayout) findViewById(R.id.diyi_pai_bt_ll_3);
        mBt31 = (Button) findViewById(R.id.bt_3_1);
        mBt32 = (Button) findViewById(R.id.bt_3_2);
        mBt33 = (Button) findViewById(R.id.bt_3_3);
        mBt34 = (Button) findViewById(R.id.bt_3_4);
        mDiyiPaiBtLl4 = (LinearLayout) findViewById(R.id.diyi_pai_bt_ll_4);
        mBt41 = (Button) findViewById(R.id.bt_4_1);
        mBt42 = (Button) findViewById(R.id.bt_4_2);
        mBt43 = (Button) findViewById(R.id.bt_4_3);
        mBt44 = (ImageButton) findViewById(R.id.bt_4_4);
        mDiyiPaiBtLl5 = (RadioGroup) findViewById(R.id.diyi_pai_bt_ll_5);
        mBt51 = (RadioButton) findViewById(R.id.bt_5_1);
        mBt52 = (RadioButton) findViewById(R.id.bt_5_2);
        mBt53 = (RadioButton) findViewById(R.id.bt_5_3);
        mBt54 = (RadioButton) findViewById(R.id.bt_5_4);
        mYzZxZhusheFenciBaocunBt = (Button) findViewById(R.id.yz_zx_zhushe_fenci_baocun_bt);

        mBundle = getIntent().getBundleExtra("data");
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
        huanZheLieBiao = AdapterUtil.getPatientFromDB(dataBean.getPatientid());
        huanZheLieBiaoInterface = new HuanZheLieBiaoImpl();
        mSelectedHZ = huanZheLieBiaoInterface.getDataBean(huanZheLieBiao);
        mBundle.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE, mSelectedHZ);


        mTitleBar = new TitleBar(this, mSelectedHZ);
        mDocOrderPannel = new DocOrderPannel(this, dataBean);
        mTiZhengZhi.setText(TouchTime.fenCi_F+"");
        mTiZhengDanWei.setText("分钟");
        mDiyiPaiBtLl1.setVisibility(View.INVISIBLE);
        mBt11.setOnClickListener(this);
        mBt12.setOnClickListener(this);
        mBt13.setOnClickListener(this);
        mBt14.setOnClickListener(this);
        mBt21.setOnClickListener(this);
        mBt22.setOnClickListener(this);
        mBt23.setOnClickListener(this);
        mBt24.setOnClickListener(this);
        mBt31.setOnClickListener(this);
        mBt32.setOnClickListener(this);
        mBt33.setOnClickListener(this);
        mBt34.setOnClickListener(this);
        mBt41.setOnClickListener(this);
        mBt42.setOnClickListener(this);
        mBt43.setOnClickListener(this);
        mBt44.setOnClickListener(this);
        mYzZxZhusheFenciBaocunBt.setOnClickListener(this);
        mDiyiPaiBtLl5.setVisibility(View.GONE);

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

    StringBuilder stringBuilder;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //体征结果值
            case R.id.bt_1_1:
                jianPanChuLi(mBt11);
                break;
            //体征结果值
            case R.id.bt_1_2:
                jianPanChuLi(mBt12);
                break;
            //体征结果值
            case R.id.bt_1_3:
                jianPanChuLi(mBt13);
                break;
            //体征结果值
            case R.id.bt_1_4:
                jianPanChuLi(mBt14);
                break;
            //体征结果值
            case R.id.bt_2_1:
                jianPanChuLi(mBt21);
                break;
            //体征结果值
            case R.id.bt_2_2:
                jianPanChuLi(mBt22);
                break;
            //体征结果值
            case R.id.bt_2_3:
                jianPanChuLi(mBt23);
                break;
            //体征结果值
            case R.id.bt_2_4:
//                jianPanChuLi(mBt24);
                break;
            //体征结果值
            case R.id.bt_3_1:
                jianPanChuLi(mBt31);
                break;
            //体征结果值
            case R.id.bt_3_2:
                jianPanChuLi(mBt32);
                break;
            //体征结果值
            case R.id.bt_3_3:
                jianPanChuLi(mBt33);
                break;
            //体征结果值
            case R.id.bt_3_4:
                jianPanChuLi(mBt34);
                break;
            //体征结果值
            case R.id.bt_4_1:
                jianPanChuLi(mBt41);
                break;
            //体征结果值
            case R.id.bt_4_2:
                jianPanChuLi(mBt42);
                break;
            //体征结果值
            case R.id.bt_4_3:
                jianPanChuLi(mBt43);
                break;
            //体征结果值
            case R.id.bt_4_4:
                stringBuilder = new StringBuilder(mTiZhengZhi.getText());
                if (stringBuilder != null && stringBuilder.length() != 0) {
                    String s = stringBuilder.substring(0, stringBuilder.length() - 1);
                    mTiZhengZhi.setText(s);
                    break;
                }
                if (stringBuilder.length() == 1) {
                    mTiZhengZhi.setText("");
                }

                break;
            case R.id.yz_zx_zhushe_fenci_baocun_bt:
                String s = mTiZhengZhi.getText().toString();
                if(null==s||"".equals(s)||"0".equals(s)){
                    showToastShort("请输入时间！");
                    break;
                }
                TouchTime.fenCi_F=Integer.parseInt(s);
                goToActivity(YZ_ZX_ZhuShe2Activity.class,mBundle);
                ActivityControl.killActivity(YZ_ZX_ZhuShe_FenCiActivity.class);
                ActivityControl.killActivity(YZ_ZX_ZhuSheActivity.class);
                ActivityControl.killActivity(YZ_XiangQingActivity.class);
                killSelf();
                break;
        }
    }
    private void jianPanChuLi(Button mBt11) {
        stringBuilder = new StringBuilder(mTiZhengZhi.getText());
        stringBuilder.append(mBt11.getText());
        mTiZhengZhi.setText(DataConverter.getFormalFloat(stringBuilder.toString()));
    }

}
