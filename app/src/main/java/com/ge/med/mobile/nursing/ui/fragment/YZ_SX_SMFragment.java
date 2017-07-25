package com.ge.med.mobile.nursing.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sj.library.base.BaseFragment;
import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZXActivity;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class YZ_SX_SMFragment extends BaseFragment implements View.OnClickListener {
    private TextView mShuxueXuedaiHeduiDaishuTv;
    private CheckBox mShuxueXuedaiHeduiCb1;
    private TextView mShuxueXuedaiHeduiChukushijianTv;
    private TextView mShuxueXuedaiHeduiXuedaixuexingTv;
    private TextView mShuxueXuedaiHeduiXuedairhTv;
    private TextView mShuxueXuedaiHeduiXuedaiNameTv;
    private TextView mShuxueXuedaiHeduiXueliangTv;
    private ImageView mYizhuXiangqingLvImgv1;
    private CheckBox mShuxueXuedaiHeduiCb2;
    private ImageView mYizhuXiangqingLvImgv2;
    public CheckBox mShuxueXuedaiHeduiCb3;
    private ImageView mYizhuXiangqingLvImgv3;
    public CheckBox mShuxueXuedaiHeduiCb4;
    private ImageView mYizhuXiangqingLvImgv4;
    public CheckBox mShuxueXuedaiHeduiCb5;
    private ImageView mYizhuXiangqingLvImgv5;

    private YZ_ZXActivity yz_zxActivity;
    private YiZhuBean.DataBean dataBean;
    private List<YiZhuBean.DataBean.OrderBloodPropertyBean> orderBloodProperty;
    private YiZhuBean.DataBean.OrderBloodPropertyBean orderBloodPropertyBean;
    private Bundle mBundle;
    private List<CheckBox> cbs;
    private int XueDaiNO;

    @Override
    public int setRootView() {
        return R.layout.fragment_yz__sx__sm;
    }

    @Override
    public void initViews() {

        mShuxueXuedaiHeduiDaishuTv = (TextView) mRootView.findViewById(R.id.shuxue_xuedai_hedui_daishu_tv);
        mShuxueXuedaiHeduiCb1 = (CheckBox) mRootView.findViewById(R.id.shuxue_xuedai_hedui_cb1);
        mShuxueXuedaiHeduiChukushijianTv = (TextView) mRootView.findViewById(R.id.shuxue_xuedai_hedui_chukushijian_tv);
        mShuxueXuedaiHeduiXuedaixuexingTv = (TextView) mRootView.findViewById(R.id.shuxue_xuedai_hedui_xuedaixuexing_tv);
        mShuxueXuedaiHeduiXuedairhTv = (TextView) mRootView.findViewById(R.id.shuxue_xuedai_hedui_xuedairh_tv);
        mShuxueXuedaiHeduiXuedaiNameTv = (TextView) mRootView.findViewById(R.id.shuxue_xuedai_hedui_xuedai_name_tv);
        mShuxueXuedaiHeduiXueliangTv = (TextView) mRootView.findViewById(R.id.shuxue_xuedai_hedui_xueliang_tv);
        mYizhuXiangqingLvImgv1 = (ImageView) mRootView.findViewById(R.id.yizhu_xiangqing_lv_imgv1);
        mShuxueXuedaiHeduiCb2 = (CheckBox) mRootView.findViewById(R.id.shuxue_xuedai_hedui_cb2);
        mYizhuXiangqingLvImgv2 = (ImageView) mRootView.findViewById(R.id.yizhu_xiangqing_lv_imgv2);
        mShuxueXuedaiHeduiCb3 = (CheckBox) mRootView.findViewById(R.id.shuxue_xuedai_hedui_cb3);
        mYizhuXiangqingLvImgv3 = (ImageView) mRootView.findViewById(R.id.yizhu_xiangqing_lv_imgv3);
        mShuxueXuedaiHeduiCb4 = (CheckBox) mRootView.findViewById(R.id.shuxue_xuedai_hedui_cb4);
        mYizhuXiangqingLvImgv4 = (ImageView) mRootView.findViewById(R.id.yizhu_xiangqing_lv_imgv4);
        mShuxueXuedaiHeduiCb5 = (CheckBox) mRootView.findViewById(R.id.shuxue_xuedai_hedui_cb5);
        mYizhuXiangqingLvImgv5 = (ImageView) mRootView.findViewById(R.id.yizhu_xiangqing_lv_imgv5);

        yz_zxActivity = (YZ_ZXActivity) mActivitySelf;
        mBundle = yz_zxActivity.getmBundle();
        dataBean = yz_zxActivity.getDataBean();
        XueDaiNO = mBundle.getInt(Constant.BUNDLE_KEY_STATUS_EXCE);
        orderBloodProperty = dataBean.getOrderBloodProperty();
        if (orderBloodProperty != null && !orderBloodProperty.isEmpty()) {
            orderBloodPropertyBean = orderBloodProperty.get(XueDaiNO);
        } else {
            orderBloodPropertyBean = new YiZhuBean.DataBean.OrderBloodPropertyBean();
        }
    }

    @Override
    public void initDatas() {
        mShuxueXuedaiHeduiCb1.setOnClickListener(this);
        mShuxueXuedaiHeduiCb2.setOnClickListener(this);
        mShuxueXuedaiHeduiCb3.setOnClickListener(this);
        mShuxueXuedaiHeduiCb4.setOnClickListener(this);
        mShuxueXuedaiHeduiCb5.setOnClickListener(this);
        mShuxueXuedaiHeduiCb5.setClickable(false);
        cbs = new ArrayList<>();
        cbs.add(mShuxueXuedaiHeduiCb1);
        cbs.add(mShuxueXuedaiHeduiCb2);
        cbs.add(mShuxueXuedaiHeduiCb3);
        cbs.add(mShuxueXuedaiHeduiCb4);
        cbs.add(mShuxueXuedaiHeduiCb5);

        mShuxueXuedaiHeduiDaishuTv.setText("第" + (XueDaiNO + 1) + "袋/共" + orderBloodProperty.size() + "袋");
        String dateString = null;
        try {
            dateString = DateUtils.getDateString("yyyy-MM-dd HH:mm", orderBloodPropertyBean.getLeavestoragetime());
        } catch (Exception e) {
            LogUtil.e(e.getMessage());
        }
        mShuxueXuedaiHeduiChukushijianTv.setText("出库时间：" + dateString);
        mShuxueXuedaiHeduiXuedaixuexingTv.setText("血袋血型：" + orderBloodPropertyBean.getBloodbagbloodtype());
        mShuxueXuedaiHeduiXuedairhTv.setText("血袋RH：" + orderBloodPropertyBean.getBloodbagbloodrh());
        mShuxueXuedaiHeduiXuedaiNameTv.setText("血袋类型名称：" + orderBloodPropertyBean.getBloodbagtypename());
        mShuxueXuedaiHeduiXueliangTv.setText("血量：" + orderBloodPropertyBean.getBloodvolume());
        mShuxueXuedaiHeduiCb3.setText("血袋号：" + orderBloodPropertyBean.getBloodbagno());
        mShuxueXuedaiHeduiCb4.setText("产品号：" + orderBloodPropertyBean.getProductno());
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
        CheckBox cb = (CheckBox) v;
        switch (v.getId()) {
            case R.id.shuxue_xuedai_hedui_cb1:
                if (cb.isChecked()) {
                    mYizhuXiangqingLvImgv1.setImageResource(R.mipmap.icon_select);
                } else {
                    mYizhuXiangqingLvImgv1.setImageResource(R.mipmap.icon_complete);
                }
                setCbEnable();
                break;
            case R.id.shuxue_xuedai_hedui_cb2:
                if (cb.isChecked()) {
                    mYizhuXiangqingLvImgv2.setImageResource(R.mipmap.icon_select);
                    mShuxueXuedaiHeduiCb2.setText("患者自述血型确认一致");
                } else {
                    mYizhuXiangqingLvImgv2.setImageResource(R.mipmap.icon_complete);
                    mShuxueXuedaiHeduiCb2.setText("患者自述血型");
                }
                setCbEnable();
                break;
            case R.id.shuxue_xuedai_hedui_cb3:
                if (cb.isChecked()) {
                    mYizhuXiangqingLvImgv3.setImageResource(R.mipmap.icon_select);
                } else {
                    mYizhuXiangqingLvImgv3.setImageResource(R.mipmap.icon_complete);
                }
                setCbEnable();
                break;
            case R.id.shuxue_xuedai_hedui_cb4:
                if (cb.isChecked()) {
                    mYizhuXiangqingLvImgv4.setImageResource(R.mipmap.icon_select);
                } else {
                    mYizhuXiangqingLvImgv4.setImageResource(R.mipmap.icon_complete);
                }
                setCbEnable();
                break;
            case R.id.shuxue_xuedai_hedui_cb5:

                if (cb.isChecked()) {
                    if (cbs != null && cbs.size() > 0) {
                        for (CheckBox checkBox : cbs) {
                            if (cb != checkBox && !checkBox.isChecked()) {
                                showToastShort("请先确认基本信息及血袋号和产品号是否匹配！");
                                mShuxueXuedaiHeduiCb5.setChecked(false);
                                return;
                            }
                        }
                    }
                    mShuxueXuedaiHeduiCb1.setEnabled(false);
                    mShuxueXuedaiHeduiCb2.setEnabled(false);
                    mShuxueXuedaiHeduiCb3.setEnabled(false);
                    mShuxueXuedaiHeduiCb4.setEnabled(false);

                    mYizhuXiangqingLvImgv5.setImageResource(R.mipmap.icon_select);
                } else {
                    mYizhuXiangqingLvImgv5.setImageResource(R.mipmap.icon_complete);
                    mShuxueXuedaiHeduiCb1.setEnabled(true);
                    mShuxueXuedaiHeduiCb2.setEnabled(true);
                    mShuxueXuedaiHeduiCb3.setEnabled(true);
                    mShuxueXuedaiHeduiCb4.setEnabled(true);
                }
                setCbEnable();
                break;
        }

    }

    private void setCbEnable() {
        boolean flag = true;
        if (cbs != null && cbs.size() > 0) {
            for (CheckBox cb : cbs) {
                if (!cb.isChecked()) {
                    flag = false;
                    break;
                }
            }

        }
        yz_zxActivity.setBtEnadle(flag);
    }
}