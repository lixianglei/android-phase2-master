package com.ge.med.mobile.nursing.ui.fragment;


import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sj.library.base.BaseFragment;
import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.ui.activity.Shu_Xue_SQ_YCActivity;
import com.ge.med.mobile.nursing.ui.activity.YZ_ZXActivity;

import org.xutils.common.util.LogUtil;

public class YZ_ShuXueFragment extends BaseFragment implements View.OnClickListener {
    private TextView mYzShuXueNameTv;
    private TextView mShuXueInculdeTv01;
    private TextView mShuXueInculdeTv02;
    private TextView mShuXueInculdeTv03;
    private TextView mShuXueInculdeTv04;
    private TextView mShuXueInculdeTv05;
    private TextView mShuXueInculdeTv06;
    private TextView mShuXueInculdeTv07;

    private CheckBox mYzShuxueXuanzhongCheckBox;
    private ImageView mYzShuxueXuanzhongCheckImgv;
    private YZ_ZXActivity yz_zxActivity;
    private YiZhuBean.DataBean mDataBean;
    public CheckBox mCaixueShuangrenCb;
    @Override
    public int setRootView() {
        return R.layout.fragment_yz__shu_xue;
    }

    @Override
    public void initViews() {
        yz_zxActivity = (YZ_ZXActivity) getActivity();

        mYzShuXueNameTv = (TextView) mRootView.findViewById(R.id.yz_shu_xue_name_tv);
        mShuXueInculdeTv01 = (TextView) mRootView.findViewById(R.id.shu_xue_inculde_tv01);
        mShuXueInculdeTv02 = (TextView) mRootView.findViewById(R.id.shu_xue_inculde_tv02);
        mShuXueInculdeTv03 = (TextView) mRootView.findViewById(R.id.shu_xue_inculde_tv03);
        mShuXueInculdeTv04 = (TextView) mRootView.findViewById(R.id.shu_xue_inculde_tv04);
        mShuXueInculdeTv05 = (TextView) mRootView.findViewById(R.id.shu_xue_inculde_tv05);
        mShuXueInculdeTv06 = (TextView) mRootView.findViewById(R.id.shu_xue_inculde_tv06);
        mShuXueInculdeTv07 = (TextView) mRootView.findViewById(R.id.shu_xue_inculde_tv07);

        mCaixueShuangrenCb = (CheckBox) mRootView.findViewById(R.id.caixue_shuangren_cb);

        mYzShuxueXuanzhongCheckBox = (CheckBox) mRootView.findViewById(R.id.yz_shuxue_xuanzhong_check_box);
        mYzShuxueXuanzhongCheckImgv = (ImageView) mRootView.findViewById(R.id.yz_shuxue_xuanzhong_check_imgv);

        mYzShuxueXuanzhongCheckBox.setOnClickListener(this);

        mCaixueShuangrenCb.setOnClickListener(this);
        mCaixueShuangrenCb.setClickable(false);

    }

    @Override
    public void initDatas() {
        mDataBean = yz_zxActivity.getDataBean();
        if (mDataBean != null && mDataBean.getOrderBloodProperty() != null && mDataBean.getOrderBloodProperty().size() > 0
                && mDataBean.getOrderBloodProperty().get(0) != null) {
            YiZhuBean.DataBean.OrderBloodPropertyBean orderBloodPropertyBean = mDataBean.getOrderBloodProperty().get(0);
            try {
                mShuXueInculdeTv01.setText("预输血期： "+ DateUtils.getDateString("yyyy-MM-dd", Long.parseLong(orderBloodPropertyBean.getScheduledbloodtime())));
            } catch (NumberFormatException e) {
                LogUtil.e(e.getMessage());
            }
            mShuXueInculdeTv02.setText("血型： "+orderBloodPropertyBean.getBloodtype());
            mShuXueInculdeTv03.setText("预定输血品种： "+orderBloodPropertyBean.getScheduledbloodvariety());
            mShuXueInculdeTv04.setText("预输血量： "+orderBloodPropertyBean.getScheduledbloodvolume());
            mShuXueInculdeTv05.setText("是否需要交叉配血： "+orderBloodPropertyBean.getCrossbloodvalue());
            mShuXueInculdeTv06.setText("试管类别： "+orderBloodPropertyBean.getBloodtubetype());
            mShuXueInculdeTv07.setText("试管编号： "+orderBloodPropertyBean.getBloodtubenumber());
        }
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
            //check 选中监听
            case R.id.yz_shuxue_xuanzhong_check_box:
                if (mYzShuxueXuanzhongCheckBox.isChecked()) {
                    mYzShuxueXuanzhongCheckImgv.setVisibility(View.VISIBLE);
                } else {
                    mYzShuxueXuanzhongCheckImgv.setVisibility(View.GONE);
                }
                setWanChengEnable();
                break;
            //check 双人
            case R.id.caixue_shuangren_cb:
                setWanChengEnable();
                break;
        }
    }
    private void setWanChengEnable(){
        if(mYzShuxueXuanzhongCheckBox.isChecked() && mCaixueShuangrenCb.isChecked()){
            yz_zxActivity. setBtEnadle(true);
        }else{
            yz_zxActivity. setBtEnadle(false);
        }
    }
}
