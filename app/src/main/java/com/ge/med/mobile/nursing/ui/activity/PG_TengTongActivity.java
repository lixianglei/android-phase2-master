package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.TengTongRenTiTuBei;
import com.ge.med.mobile.nursing.ui.component.TengTongRenTiTuZheng;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.mitac.lib.bcr.utils.BARCODE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PG_TengTongActivity extends MyBaseActivity {
    private TitleBar mTitleBar;
    private PatientInfoPannel mPatientPanel;
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;
    private Intent intent;
    private Bundle mBundle;
    private LinearLayout mPgIncludeLl;
    private ImageButton mPingguBackBt;
    private ImageButton mPingguNextBt;
    private Button mPingguBaocunBt;
    private TengTongRenTiTuZheng mTengTongRenTiTuZheng;
    private TengTongRenTiTuBei mTengTongRenTiTuBei;
    private LayoutInflater layoutInflater;
    private List<Integer> imageViewBeiIDs;
    private List<Integer> imageViewZhengIDs;
    private TextView mHzPgDtTopicName0;
    private TextView mHzPgDtTopicName1;
    private AssessDefine.DataBean mAssessDefine;
    private Map<Integer ,String> mTengTongBuWeiName;

    @Override
    public int setRootView() {
        return R.layout.activity_pg__teng_tong;
    }

    @Override
    public void initViews() {
        initMap();
        mPgIncludeLl = (LinearLayout) findViewById(R.id.pg_include_ll);
        mPingguBackBt = (ImageButton) findViewById(R.id.pinggu_back_bt);
        mPingguNextBt = (ImageButton) findViewById(R.id.pinggu_next_bt);
        mPingguBaocunBt = (Button) findViewById(R.id.pinggu_baocun_bt);
        mHzPgDtTopicName0 = (TextView) findViewById(R.id.hz_pg_dt_topic_name_0);
        mHzPgDtTopicName1 = (TextView) findViewById(R.id.hz_pg_dt_topic_name_1);

        intent = getIntent();
        mBundle = getBundle(intent);
        mSelectedHZ = getPatient();
        mAssessDefine = getAssessDefine();
        mTitleBar = new TitleBar(this, mSelectedHZ);
        mTitleBar.setShowConfirmDialog(true);
        mPatientPanel = new PatientInfoPannel(this, new AssessDaoImpl().findAllRiskDefine(), mSelectedHZ);
        mPatientPanel.showHideTipLayout(false);
        layoutInflater = LayoutInflater.from(mActivitySelf);

        mHzPgDtTopicName0.setText(mAssessDefine.getName());
    }
//初始化 部位名称对应图片id Map
    private void initMap() {
        mTengTongBuWeiName = new HashMap<>();
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_lian_imgv1,"脸");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youjian_imgv2,"右肩");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_houbu_imgv3,"喉部");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuojian_imgv4,"左肩");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youshangbi_imgv5,"右上臂");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_xiongbu_imgv6,"胸部");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuoshangbi_imgv7,"左上臂");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_fubu_imgv8,"腹部");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youqianbi_imgv9,"右前臂");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_yinbu_imgv10,"阴部");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuoqianbi_imgv11,"左前臂");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youshouwan_imgv12,"右手腕");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuoshouwan_imgv13,"左手腕");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youshouzhang_imgv14,"右手掌");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuoshouzhang_imgv15,"左手掌");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youshouzhi_imgv16,"右手指");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuoshouzhi_imgv17,"左手指");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youdatui_imgv18,"右大腿");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuodatui_imgv19,"左大腿");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youxi_imgv20,"右膝");
        mTengTongBuWeiName.put(R.id.tongdian__zheng_zuoxi_imgv21,"左膝");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youxiaotui_imgv22,"右小腿");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuoxiaotui_imgv23,"左小腿");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youzuhuai_imgv24,"右足踝");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuozuhuai_imgv25,"左足踝");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youzubei_imgv26,"右足背");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuozubei_imgv27,"左足背");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youjiao_imgv28,"右脚");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuojiao_imgv29,"左脚");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_tou_imgv1,"头");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_jing_imgv2,"颈");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_bei_imgv3,"背");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_zuoshouzhou_imgv4,"左手肘");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_yao_imgv5,"腰");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_youshouzhou_imgv6,"右手肘");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_zuoshoubei_imgv7,"左手背");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_tun_imgv8,"臀");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_youshoubei_imgv9,"右手背");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_rentibei_imgv10,"人体背");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_zuozugen_imgv11,"左足跟");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_youzugen_imgv12,"右足跟");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_zuozudi_imgv13,"左足底");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_youzudi_imgv14,"右足底");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_zuojiaozhi_imgv15,"左脚趾");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_youjiaozhi_imgv16,"右脚趾");
    }

    private Bundle getBundle(Intent intent) {
        Bundle bundle = intent.getBundleExtra(Constant.GLOBAL_KEY_DATA);
        if (bundle == null) bundle = new Bundle();
        return bundle;
    }

    private HuanZheLieBiaoBean.DataBean getPatient() {
        HuanZheLieBiaoBean.DataBean retval = null;
        if (mBundle != null) {
            retval = (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
        }
        return retval;
    }
    //  For assessment of definition  （获取评估定义）
    private AssessDefine.DataBean getAssessDefine() {
        AssessDefine.DataBean retval = null;
        if (mBundle != null) {
            retval = (AssessDefine.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_ASSESSDEFINE);
        }
        return retval;
    }
    private void setRenTiTuZheng() {
        View view = layoutInflater.inflate(R.layout.ren_ti_pin_tu_zheng_include, null);
        mPgIncludeLl.removeAllViewsInLayout();
        mPgIncludeLl.addView(view);
        mTengTongRenTiTuZheng = new TengTongRenTiTuZheng(this,imageViewZhengIDs);
       ImageView mCheckBeimianImgv = (ImageView) this.findViewById(R.id.check_beimian_imgv);
        mHzPgDtTopicName1.setVisibility(View.VISIBLE);
        mHzPgDtTopicName1.setText("/选择疼痛部位(正)");
        mCheckBeimianImgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewZhengIDs = mTengTongRenTiTuZheng.getmImageViewList();
                setRenTiTuBei();
            }
        });
//        设置Width和Hight：
//        LinearLayout.LayoutParams.FILL_PARENT,mPgIncludeLl.getLayoutParams().width;
//        mPgIncludeLl.addView(v,
//                new LinearLayout.LayoutParams(mPgIncludeLl.getLayoutParams().width,
//                        mPgIncludeLl.getLayoutParams().height));
    }
    private void setRenTiTuBei() {
        View view = layoutInflater.inflate(R.layout.ren_ti_pin_tu_bei_include, null);
        mPgIncludeLl.removeAllViewsInLayout();
        mPgIncludeLl.addView(view);
        mTengTongRenTiTuBei = new TengTongRenTiTuBei(this,imageViewBeiIDs);
        ImageView mCheckBeimianImgv = (ImageView) this.findViewById(R.id.check_zhengmian_imgv);
        mHzPgDtTopicName1.setVisibility(View.VISIBLE);
        mHzPgDtTopicName1.setText("/选择疼痛部位(背)");
        mCheckBeimianImgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewBeiIDs = mTengTongRenTiTuBei.getmImageViewList();
                setRenTiTuZheng();
            }
        });
//        设置Width和Hight：
//        LinearLayout.LayoutParams.FILL_PARENT,mPgIncludeLl.getLayoutParams().width;
//        mPgIncludeLl.addView(v,
//                new LinearLayout.LayoutParams(mPgIncludeLl.getLayoutParams().width,
//                        mPgIncludeLl.getLayoutParams().height));
    }
    @Override
    public void initDatas() {
        setRenTiTuZheng();

    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }


}
