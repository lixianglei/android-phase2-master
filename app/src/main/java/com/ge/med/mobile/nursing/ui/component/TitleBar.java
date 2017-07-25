package com.ge.med.mobile.nursing.ui.component;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sj.library.base.BaseActivity;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.ui.activity.ActivityUtils;
import com.ge.med.mobile.nursing.ui.view.SelfDialog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by Qu on 2016/12/2.
 */
public class TitleBar {
    private RelativeLayout rlTitleBj;
    private ImageView myTvTitleLeft;
    private TextView myTvTitleCenter;
    private ImageView imgvTitleRight;
    private TextView myTvTitleRight;
    private SystemBarTintManager mTintManager;
    private int mPageType = Constant.PAGE_TYPE_NOPATIENT;
    private SelfDialog selfDialog;
    private boolean showConfirmDialog;
    private BaseActivity mActivity;

    public TitleBar(final MyBaseActivity activity) {
        mActivity = activity;
        rlTitleBj = (RelativeLayout) activity.findViewById(R.id.rl_title_bj);
        myTvTitleLeft = (ImageView) activity.findViewById(R.id.my_tv_title_left);
        myTvTitleCenter = (TextView) activity.findViewById(R.id.my_tv_title_center);
        imgvTitleRight = (ImageView) activity.findViewById(R.id.imgv_title_right);
        myTvTitleRight = (TextView) activity.findViewById(R.id.my_tv_title_right);
        myTvTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (showConfirmDialog) {
                        selfDialog.setYesOnclickListener("确认", new SelfDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                selfDialog.dismiss();
                                activity.killSelf();
                            }
                        });
                        selfDialog.show();
                    } else {
                        activity.killSelf();
                    }
            }
        });
        imgvTitleRight.setVisibility(View.GONE);
        myTvTitleRight.setVisibility(View.GONE);
        mTintManager = activity.tintManager;
        showConfirmDialog = false;

        setTitleBackgroundColor(Color.parseColor("#dddddd"));
        setShaiXuanText("");
    }

    public TitleBar(final MyBaseActivity activity, String titleName) {
        this(activity);
        setTitleText(titleName);
    }

    public TitleBar(final MyBaseActivity activity, HuanZheLieBiaoBean.DataBean patient) {
        this(activity);
        myTvTitleLeft.setImageResource(R.mipmap.icon_return);
        imgvTitleRight.setImageResource(R.mipmap.icon_rightbar);
        changePatient(patient);
    }

    public void changePatient(HuanZheLieBiaoBean.DataBean patient) {
        if (null != patient) {
            setTitleText(patient.getName() + " " + patient.getBedno() + "床");
            setTitleBackgroundColor(ActivityUtils.getColorByCareLevel(patient.getCarelevel()));
            myTvTitleCenter.setTextColor(Color.WHITE);
            myTvTitleLeft.setImageResource(R.mipmap.icon_return);
            imgvTitleRight.setImageResource(R.mipmap.icon_rightbar);
            myTvTitleRight.setTextColor(Color.WHITE);
        }
    }

    public void setTitleText(String str) {
        myTvTitleCenter.setText(str);
    }

    public void setBackBehavior(View.OnClickListener listener) {
        myTvTitleLeft.setOnClickListener(listener);
    }

    public void setTitleBackgroundColor(int color) {
        mTintManager.setTintColor(color);
        rlTitleBj.setBackgroundColor(color);
    }

    public void setTitleFontColor(int color) {
        myTvTitleCenter.setTextColor(color);
    }

    public void setBackIcon(int iconId) {
        myTvTitleLeft.setImageResource(iconId);
    }

    public void setBackVisible(boolean visible) {
        if (visible) myTvTitleLeft.setVisibility(View.VISIBLE);
        else myTvTitleLeft.setVisibility(View.GONE);
    }

    public void setShaiXuanText(String str) {
        myTvTitleRight.setText(str);
    }

    public void setShaiXuanVisible(boolean visible) {
        if (visible) {
            imgvTitleRight.setVisibility(View.VISIBLE);
            myTvTitleRight.setVisibility(View.VISIBLE);
        } else {
            imgvTitleRight.setVisibility(View.GONE);
            myTvTitleRight.setVisibility(View.GONE);
        }
    }

    public void setShaiXuanBehavior(View.OnClickListener listener) {
        imgvTitleRight.setVisibility(View.VISIBLE);
        imgvTitleRight.setOnClickListener(listener);
        myTvTitleRight.setOnClickListener(listener);
    }

    public boolean isShowConfirmDialog() {
        return showConfirmDialog;
    }

    public void setShowConfirmDialog(boolean showConfirmDialog) {
        this.showConfirmDialog = showConfirmDialog;
        if (showConfirmDialog) {
            if (selfDialog == null) {
                selfDialog = new SelfDialog(mActivity);
                selfDialog.setTitle("确认操作");
                selfDialog.setMessage("退出将会放弃所有操作，请确认是否继续？");
                selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        selfDialog.dismiss();
                    }
                });
            }
        }

    }
}
