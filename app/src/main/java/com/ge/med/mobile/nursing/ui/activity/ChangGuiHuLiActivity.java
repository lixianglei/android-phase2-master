package com.ge.med.mobile.nursing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.sj.library.control.ActivityControl;
import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.NursingEventTempLateLisBean;
import com.ge.med.mobile.nursing.dao.entity.SubmitNursingEventBean;
import com.ge.med.mobile.nursing.dao.entity.TiJiaoXuanJiao;
import com.ge.med.mobile.nursing.dao.entity.XinjianJiaoBanBean;
import com.ge.med.mobile.nursing.dao.entity.XuanJiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.AssessDaoImpl;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.db.DBXuanJiaoRecord;
import com.ge.med.mobile.nursing.forjson.NetworkForXuanJiao;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.component.ChangGuiHuLiBeiZhu;
import com.ge.med.mobile.nursing.ui.component.ChangGuiHuLiBingQingZhiLiang;
import com.ge.med.mobile.nursing.ui.component.ChangGuiHuLiJiLuTengTongJiBie;
import com.ge.med.mobile.nursing.ui.component.ChangGuiHuLiYiZhu;
import com.ge.med.mobile.nursing.ui.component.JiaoBanEdt;
import com.ge.med.mobile.nursing.ui.component.PatientInfoPannel;
import com.ge.med.mobile.nursing.ui.component.ShiJianFenLei;
import com.ge.med.mobile.nursing.ui.component.TengTongChuLi;
import com.ge.med.mobile.nursing.ui.component.TengTongRenTiTuBei;
import com.ge.med.mobile.nursing.ui.component.TengTongRenTiTuZheng;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;

public class ChangGuiHuLiActivity extends MyBaseActivity {
    private TextView mDrtzSjImgv;
    private LinearLayout mIncludeLl;
    private Button mTiZhengDuoRenBackBt;
    private Button mTiZhengDuoRenBaocunBt;
    private Bundle mBundle;
    private HuanZheLieBiaoBean.DataBean dataBean;
    private PatientInfoPannel mPatientPanel;
    private TitleBar mTitleBar;
    private LayoutInflater layoutInflater;
    //    private List<String> jmType = new ArrayList<>();
    private final String SHIJIAN_FENLEI = "0";
    private final String TENGTONG_TU = "1";
    private final String TENGTONG_JIBIE = "2";
    private final String BINGQING_ZHILIAO = "3";
    private String mTime;
    private String mActivityType;
    private List<Integer> imageViewBeiIDs;
    private List<Integer> imageViewZhengIDs;
    private TengTongRenTiTuZheng mTengTongRenTiTuZheng;
    private TengTongRenTiTuBei mTengTongRenTiTuBei;
    private YiZhuBean.DataBean yiZhuDataBean = null;
    private int currentIndex;
    private ArrayList<String> tengTongBuWei;
    private Map<String, String> buWeiMingCheng;
    private Map<String, String> buWeiJiBie;
    private ChangGuiHuLiJiLuTengTongJiBie changGuiHuLiJiLuTengTongJiBie;
    private String userId;
    private String userName;
    private LinearLayout mTiZhengDuoRenBtLl;
    private String edt;
    private XuanJiaoBean.DataBean mXuanJiaoBean;
    // 提交宣教时需要使用的edurecordid，每个病人一样
    private String edurecordid;


    @Override
    public int setRootView() {
        return R.layout.activity_jiao_ban_sj;
    }

    @Override
    public void initViews() {
        SharePLogin sharePLogin = new SharePLogin(this);
        userId = sharePLogin.getUserid();
        userName = sharePLogin.getDisplayUserName();
        mDrtzSjImgv = (TextView) findViewById(R.id.drtz_sj_imgv);
        mIncludeLl = (LinearLayout) findViewById(R.id.include_ll);
        mTiZhengDuoRenBackBt = (Button) findViewById(R.id.ti_zheng_duo_ren_back_bt);
        mTiZhengDuoRenBaocunBt = (Button) findViewById(R.id.ti_zheng_duo_ren_baocun_bt);
        mTiZhengDuoRenBtLl = (LinearLayout) findViewById(R.id.ti_zheng_duo_ren_bt_ll);

        mBundle = getIntent().getBundleExtra(Constant.GLOBAL_KEY_DATA);
        dataBean = (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE);
        mTime = mBundle.getString(Constant.BUNDLE_KEY_SJ);
        mActivityType = mBundle.getString(Constant.BUNDLE_KEY_ACTIVITY_TYPE);
        // 获取edurecordid
        edurecordid = mBundle.getString("edurecordid");
        mPatientPanel = new PatientInfoPannel(this, new AssessDaoImpl().findAllRiskDefine(), dataBean);
        mPatientPanel.showHideTipLayout(false);
        mTitleBar = new TitleBar(this, dataBean);
        layoutInflater = LayoutInflater.from(this);
//        jmType.add(SHIJIAN_FENLEI);
        mTiZhengDuoRenBackBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Constant.BUNDLE_KEY_TENGTONG_BUWEI.equals(mActivityType)) {
                    killSelf();
                } else {
                    if (changGuiHuLiJiLuTengTongJiBie != null && buWeiJiBie != null && currentIndex < tengTongBuWei.size()) {
                        buWeiJiBie.put(tengTongBuWei.get(currentIndex), changGuiHuLiJiLuTengTongJiBie.getJiBieText());
                    }
                    currentIndex--;
                    if (currentIndex >= 0 && currentIndex < tengTongBuWei.size()) {
                        mDrtzSjImgv.setText("患者主诉/选择疼痛级别(" + buWeiMingCheng.get(tengTongBuWei.get(currentIndex)) + ")");
                        changGuiHuLiJiLuTengTongJiBie.setJiBieText(buWeiJiBie.get(tengTongBuWei.get(currentIndex)));
                    } else {
                        showToastShort("第一个！");
                        currentIndex++;
                    }
                }
            }
        });
    }

    @Override
    public void initDatas() {
        if (mActivityType != null) {
            switch (mActivityType) {
//                时间界面跳转过来的 事件选择界面处理
                case Constant.BUNDLE_KEY_SJ:
                    shiJianJieMianChuLi();
                    break;
                //疼痛图 处理
                case Constant.HULI_TYPR_TENGTONG:
                    tengTongTuChuLi();
                    break;
                //医嘱备注处理
                case Constant.HULI_TYPR_YIZHU_BEIZHU:
                    yizhuBeiZhu();
                    break;
                //医嘱备注处理
                case Constant.HULI_TYPR_YIZHU_BEIZHU_2:
                    buLuBeiZhu();
                    break;
                //疼痛级别处理
                case Constant.BUNDLE_KEY_TENGTONG_BUWEI:
                    tengTongJiBie();
                    break;
                //交班事件处理
                case Constant.HULI_TYPR_JIAOBAN:
                    jiaoBanShiJianChuLi();
                    break;
                //宣教结果处理
                case Constant.BUNDLE_KEY_ACTIVITY_TYPE_XIANGQING:
                    xuanJiaoChuLi();
                    break;
                default:
                    qiTaJieMian();
                    break;
            }
        }

    }

    private void jiaoBanShiJianChuLi() {
        mDrtzSjImgv.setText("特殊事件(模板)");
        mTiZhengDuoRenBaocunBt.setText("确认");
        XinjianJiaoBanBean.DataBean dataBean = (XinjianJiaoBanBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_JIAOBAN_SHIJIAN);
        final JiaoBanEdt jiaoBanEdt = (JiaoBanEdt) mBundle.getSerializable(Constant.BUNDLE_KEY_JIAOBAN_SHIJIAN_EDT);
        ShiJianFenLei shiJianFenLei = null;
        if (dataBean != null && dataBean.getEventList() != null) {
            shiJianFenLei = new ShiJianFenLei(this, layoutInflater, mIncludeLl, dataBean.getEventList());
        }
        final ShiJianFenLei finalShiJianFenLei = shiJianFenLei;
        mTiZhengDuoRenBaocunBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalShiJianFenLei != null) {
                    edt = finalShiJianFenLei.getEdt();
                    int templateId = finalShiJianFenLei.getTemplateId();
                    edt = edt.replace("<span style=\"color:red\">XX</span>", "XX");
                    jiaoBanEdt.setEdt(edt);
                    mBundle.putSerializable(Constant.BUNDLE_KEY_JIAOBAN_SHIJIAN_EDT, jiaoBanEdt);
                    mBundle.putInt(Constant.BUNDLE_KEY_JIAOBAN_SHIJIAN_TEMPLATEID, templateId);
                    Intent intent = ChangGuiHuLiActivity.this.getIntent();
                    setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                }
                killSelf();
            }
        });
    }

    //疼痛级别处理
    private void tengTongJiBie() {
        currentIndex = 0;
        tengTongBuWei = mBundle.getStringArrayList(Constant.BUNDLE_KEY_TENGTONG_BUWEI);
        final SubmitNursingEventBean submitNursingEventBean
                = (SubmitNursingEventBean) mBundle.getSerializable(Constant.BUNDLE_ACTIVITY_JILU_CHANGGUI_HULI_SHIJIAN_SUB);
        initMap(tengTongBuWei);
        final TengTongChuLi tengTongChuLi = new TengTongChuLi();
        buWeiMingCheng = tengTongChuLi.getBuWeiMingCheng(tengTongBuWei);
        mDrtzSjImgv.setText("患者主诉/选择疼痛级别(" + buWeiMingCheng.get(tengTongBuWei.get(0)) + ")");
        changGuiHuLiJiLuTengTongJiBie = new ChangGuiHuLiJiLuTengTongJiBie(this, layoutInflater, mIncludeLl);
        mTiZhengDuoRenBaocunBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changGuiHuLiJiLuTengTongJiBie != null && buWeiJiBie != null && currentIndex < tengTongBuWei.size()) {
                    buWeiJiBie.put(tengTongBuWei.get(currentIndex), changGuiHuLiJiLuTengTongJiBie.getJiBieText());
                }
                currentIndex++;
                if (currentIndex < tengTongBuWei.size()) {
                    mDrtzSjImgv.setText("患者主诉/选择疼痛级别(" + buWeiMingCheng.get(tengTongBuWei.get(currentIndex)) + ")");
                    changGuiHuLiJiLuTengTongJiBie.setJiBieText(buWeiJiBie.get(tengTongBuWei.get(currentIndex)));
                } else {
                    List<SubmitNursingEventBean.VitalSignRecordListBean> vitalSignRecordList
                            = new ArrayList<SubmitNursingEventBean.VitalSignRecordListBean>();
                    SubmitNursingEventBean.VitalSignRecordListBean vitalSignRecordListBean;
                    Set<String> keySet = buWeiJiBie.keySet();
                    for (String s : keySet) {
                        vitalSignRecordListBean = new SubmitNursingEventBean.VitalSignRecordListBean();
                        vitalSignRecordListBean.setSignvalue(buWeiJiBie.get(s));
                        vitalSignRecordListBean.setSigntype(tengTongChuLi.getVitalSignWardDefineId());
                        vitalSignRecordListBean.setNote(s);
                        vitalSignRecordList.add(vitalSignRecordListBean);
                    }
                    submitNursingEventBean.setVitalSignRecordList(vitalSignRecordList);
                    mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.HULI_TYPR_QITA);
                    mBundle.putSerializable(Constant.BUNDLE_ACTIVITY_JILU_CHANGGUI_HULI_SHIJIAN_SUB, submitNursingEventBean);
                    goToActivity(ChangGuiHuLiActivity.class, mBundle);
                    currentIndex--;
                }
            }
        });
    }

    private void initMap(ArrayList<String> tengTongBuWei) {
        buWeiJiBie = new HashMap<>();
        if (tengTongBuWei != null && tengTongBuWei.size() > 0) {
            for (String s : tengTongBuWei) {
                buWeiJiBie.put(s, "");
            }
        }

    }

    private void qiTaJieMian() {
        mDrtzSjImgv.setText("患者主诉/病情与治疗");
        final SubmitNursingEventBean submitNursingEventBean
                = (SubmitNursingEventBean) mBundle.getSerializable(Constant.BUNDLE_ACTIVITY_JILU_CHANGGUI_HULI_SHIJIAN_SUB);
        NursingEventTempLateLisBean.DataBean dataBean =
                (NursingEventTempLateLisBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_ACTIVITY_JILU_CHANGGUI_HULI_SHIJIAN);
        final ChangGuiHuLiBingQingZhiLiang changGuiHuLiBingQingZhiLiang = new ChangGuiHuLiBingQingZhiLiang(this, layoutInflater, mIncludeLl, dataBean);
        mTiZhengDuoRenBaocunBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changGuiHuLiBingQingZhiLiang.getNot() == null || changGuiHuLiBingQingZhiLiang.getNot().trim().length() <= 0) {
                    showToastShort("请选择模板或输入内容!");
                    return;
                }
                submitNursingEventBean.setComment(changGuiHuLiBingQingZhiLiang.getNot());
                submitNursingEvent(submitNursingEventBean);
            }
        });
    }

    private void xuanJiaoChuLi() {
        mDrtzSjImgv.setText("宣教方式及结果");
        mTiZhengDuoRenBaocunBt.setText("提交");
        mXuanJiaoBean = (XuanJiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_XUANJIAO_DEFINE);
        final ChangGuiHuLiBeiZhu changGuiHuLiBeiZhu = new ChangGuiHuLiBeiZhu(this, layoutInflater, mIncludeLl, mXuanJiaoBean, dataBean, edurecordid);
        mTiZhengDuoRenBaocunBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 宣教提交
                List<TiJiaoXuanJiao> tiJiaoXuanJiaos = changGuiHuLiBeiZhu.getTiJiaoJson(mXuanJiaoBean, Integer.parseInt(userId), userName);
                DBXuanJiaoRecord dbXuanJiaoRecord;
                if (tiJiaoXuanJiaos != null) {
                    for (TiJiaoXuanJiao tiJiaoXuanJiao : tiJiaoXuanJiaos) {
                        dbXuanJiaoRecord = DBXuanJiaoRecord.convertXuanJiaoRecord(tiJiaoXuanJiao);
                        /*first = DataSupport.where("patientid = ? and edudefineid = ?", dataBean.getPatientid(), tiJiaoXuanJiao.getEdudefineid() + "").findFirst(DBXuanJiaoRecord.class);
                        if (first == null) {
                            dbXuanJiaoRecord.save();
                        } else {
                            dbXuanJiaoRecord.updateAll("patientid = ? and edudefineid = ?", dataBean.getPatientid(), tiJiaoXuanJiao.getEdudefineid() + "");
                        }*/
                        // 先删除后保存
                        DataSupport.deleteAll(DBXuanJiaoRecord.class, "patientid = ? and edudefineid = ?", dataBean.getPatientid(), tiJiaoXuanJiao.getEdudefineid() + "");
                        dbXuanJiaoRecord.save();
                    }
                }
                if (tiJiaoXuanJiaos == null || tiJiaoXuanJiaos.size() <= 0) {
                    showToastShort("请选择宣教方式和结果!");
                } else {
                    NetworkForXuanJiao.submitXuanJiaoJilu(tiJiaoXuanJiaos);
                    //ActivityControl.killActivity(PG_ZJMActivity.class);
                    //ActivityControl.killActivity(ChangGuiHuLiActivity.class);
                    killSelf();
                }
            }
        });
    }

    private void buLuBeiZhu() {
        mDrtzSjImgv.setText("历史备注/新增备注");
        NursingEventTempLateLisBean.DataBean dataBean =
                (NursingEventTempLateLisBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_ACTIVITY_JILU_CHANGGUI_HULI_SHIJIAN);
        final SubmitNursingEventBean submitNursingEventBean
                = (SubmitNursingEventBean) mBundle.getSerializable(Constant.BUNDLE_ACTIVITY_JILU_CHANGGUI_HULI_SHIJIAN_SUB);
        yiZhuDataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
        final ChangGuiHuLiBeiZhu changGuiHuLiBeiZhu = new ChangGuiHuLiBeiZhu(this, layoutInflater, mIncludeLl, yiZhuDataBean, dataBean);
        mTiZhengDuoRenBaocunBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changGuiHuLiBeiZhu.getNot() == null || changGuiHuLiBeiZhu.getNot().trim().length() <= 0) {
                    showToastShort("请选择模板或输入内容!");
                    return;
                }
                submitNursingEventBean.setComment(changGuiHuLiBeiZhu.getNot());
                if (yiZhuDataBean != null) {
                    submitNursingEventBean.setOrderid(yiZhuDataBean.getId());
                }
                submitNursingEvent(submitNursingEventBean);
            }
        });
    }

    private void submitNursingEvent(SubmitNursingEventBean submitNursingEventBean) {
        String jsonStr = JSON.toJSONString(submitNursingEventBean, SerializerFeature.WriteNullListAsEmpty);
        LogUtil.d("jsonStr :" + jsonStr);
        OkHttpUtils.post().url(URL.URL_HULI_JILU_SUBMIT).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_SUBMIT_JSON, jsonStr)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("submitNursingEvent :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.d("submitNursingEvent response:" + response);
                        ActivityControl.killActivity(JiaoBanSJActivity.class);
                        ActivityControl.killActivity(ChangGuiHuLiActivity.class);
                    }
                });
    }

    private void yizhuBeiZhu() {
        mDrtzSjImgv.setText("选择补充备注的医嘱");
        List<YiZhuBean.DataBean> doctorOrdersExcludeStatus
                = new DoctorOrderDaoImpl().findDoctorOrdersInJsonBean(dataBean.getPatientid(), null, Constant.YZ_TYPE_ZHIXINGZHONG);
        new DoctorOrderDaoImpl().getZPaiXuDBYiZhuDatabean(doctorOrdersExcludeStatus);
        final ChangGuiHuLiYiZhu changGuiHuLiYiZhu = new ChangGuiHuLiYiZhu(this, layoutInflater, mIncludeLl, doctorOrdersExcludeStatus, mTiZhengDuoRenBaocunBt);
        mTiZhengDuoRenBaocunBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changGuiHuLiYiZhu.getDataBean() != null) {
                    mBundle.putSerializable(Constant.BUNDLE_KEY_YIZHU, changGuiHuLiYiZhu.getDataBean());
                    mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.HULI_TYPR_YIZHU_BEIZHU_2);
                    goToActivity(ChangGuiHuLiActivity.class, mBundle);
                }
            }
        });


    }

    //疼痛图 界面处理
    private void tengTongTuChuLi() {
        setRenTiTuZheng();
        mTiZhengDuoRenBaocunBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TengTongChuLi tengTongChuLi = new TengTongChuLi();
                ArrayList<String> tengTongBuWei = tengTongChuLi.getTengTongBuWei(mTengTongRenTiTuBei, mTengTongRenTiTuZheng);
                if (tengTongBuWei == null || tengTongBuWei.size() <= 0) {
                    showToastShort("未选择疼痛部位！");
                    return;
                }
                mBundle.putStringArrayList(Constant.BUNDLE_KEY_TENGTONG_BUWEI, tengTongBuWei);
                mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.BUNDLE_KEY_TENGTONG_BUWEI);
                goToActivity(ChangGuiHuLiActivity.class, mBundle);
            }
        });
    }

    private void setRenTiTuBei() {
        View view = layoutInflater.inflate(R.layout.ren_ti_pin_tu_bei_include, null);
        mIncludeLl.removeAllViewsInLayout();
        mIncludeLl.addView(view);
        mTengTongRenTiTuBei = new TengTongRenTiTuBei(this, imageViewBeiIDs);
        ImageView mCheckBeimianImgv = (ImageView) this.findViewById(R.id.check_zhengmian_imgv);
        mDrtzSjImgv.setText("患者主诉/选择疼痛部位(背)");
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

    private void setRenTiTuZheng() {
        View view = layoutInflater.inflate(R.layout.ren_ti_pin_tu_zheng_include, null);
        mIncludeLl.removeAllViewsInLayout();
        mIncludeLl.addView(view);
        mTengTongRenTiTuZheng = new TengTongRenTiTuZheng(this, imageViewZhengIDs);
        ImageView mCheckBeimianImgv = (ImageView) this.findViewById(R.id.check_beimian_imgv);
        mDrtzSjImgv.setText("患者主诉/选择疼痛部位(正)");
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

    //时间跳转过来界面处理
    private void shiJianJieMianChuLi() {
        mDrtzSjImgv.setText("患者主诉/事件分类");
        final ShiJianFenLei shiJianFenLei = new ShiJianFenLei(this, layoutInflater, mIncludeLl);
        mTiZhengDuoRenBaocunBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shiJianFenLei != null && shiJianFenLei.getDataBean() != null) {
                    SubmitNursingEventBean submitNursingEventBean = new SubmitNursingEventBean();
//                    2017-3-31 9:22:35
                    String dateString = null;
                    try {
                        dateString = DateUtils.getDateString("yyyy-MM-dd HH:mm:ss", Long.parseLong(mTime));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    submitNursingEventBean.setRecordtime(dateString);
                    submitNursingEventBean.setUserid(userId);
                    submitNursingEventBean.setSavestatus(1);
                    submitNursingEventBean.setPatientid(dataBean.getPatientid());
                    submitNursingEventBean.setNursingeventdefineid(shiJianFenLei.getDataBean().getId());
                    mBundle.putSerializable(Constant.BUNDLE_ACTIVITY_JILU_CHANGGUI_HULI_SHIJIAN, shiJianFenLei.getDataBean());
                    mBundle.putSerializable(Constant.BUNDLE_ACTIVITY_JILU_CHANGGUI_HULI_SHIJIAN_SUB, submitNursingEventBean);
                    if (Constant.HULI_TYPR_TENGTONG.equals(shiJianFenLei.getDataBean().getEventcode())) {
                        mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.HULI_TYPR_TENGTONG);
                        goToActivity(ChangGuiHuLiActivity.class, mBundle);
                    } else if (Constant.HULI_TYPR_YIZHU_BEIZHU.equals(shiJianFenLei.getDataBean().getEventcode())) {
                        mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.HULI_TYPR_YIZHU_BEIZHU);
                        goToActivity(ChangGuiHuLiActivity.class, mBundle);
                    } else {
                        mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE, Constant.HULI_TYPR_QITA);
                        goToActivity(ChangGuiHuLiActivity.class, mBundle);
                    }
                }
            }
        });
    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

}
