package com.ge.med.mobile.nursing.ui.component;

import android.view.View;
import android.widget.ImageView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxl on 2017/3/7.
 */
public class TengTongRenTiTuBei implements View.OnClickListener {

    private ImageView mTongBeiTouImgv1;
    private ImageView mTongBeiJingImgv2;
    private ImageView mTongBeiBeiImgv3;
    private ImageView mTongBeiZuoshouzhouImgv4;
    private ImageView mTongBeiYaoImgv5;
    private ImageView mTongBeiYoushouzhouImgv6;
    private ImageView mTongBeiZuoshoubeiImgv7;
    private ImageView mTongBeiTunImgv8;
    private ImageView mTongBeiYoushoubeiImgv9;
    private ImageView mTongBeiRentibeiImgv10;
    private ImageView mTongBeiZuozugenImgv11;
    private ImageView mTongBeiYouzugenImgv12;
    private ImageView mTongBeiZuozudiImgv13;
    private ImageView mTongBeiYouzudiImgv14;
    private ImageView mTongBeiZuojiaozhiImgv15;
    private ImageView mTongBeiYoujiaozhiImgv16;
    private ImageView mTongDianBeiTouImgv1;
    private ImageView mTongDianBeiJingImgv2;
    private ImageView mTongDianBeiBeiImgv3;
    private ImageView mTongDianBeiZuoshouzhouImgv4;
    private ImageView mTongDianBeiYaoImgv5;
    private ImageView mTongDianBeiYoushouzhouImgv6;
    private ImageView mTongDianBeiZuoshoubeiImgv7;
    private ImageView mTongDianBeiTunImgv8;
    private ImageView mTongDianBeiYoushoubeiImgv9;
    private ImageView mTongDianBeiRentibeiImgv10;
    private ImageView mTongDianBeiZuozugenImgv11;
    private ImageView mTongDianBeiYouzugenImgv12;
    private ImageView mTongDianBeiZuozudiImgv13;
    private ImageView mTongDianBeiYouzudiImgv14;
    private ImageView mTongDianBeiZuojiaozhiImgv15;
    private ImageView mTongDianBeiYoujiaozhiImgv16;
    private MyBaseActivity mActivity;
    private List<ImageView> mImageViewList;
    private List<Integer> mDisplayImageViewList;

    public List<Integer> getmImageViewList() {
        List<Integer> imageViews = new ArrayList<>();
        if (mImageViewList != null && mImageViewList.size() > 0) {
            for (ImageView imageView : mImageViewList) {
                if (imageView != null) {
                    if (imageView.getVisibility() == View.VISIBLE) {
                        imageViews.add(imageView.getId());
                    }
                }
            }
        }
        return imageViews;
    }
    private void initViewVisible() {
        if (mDisplayImageViewList != null && mDisplayImageViewList.size() > 0 && mImageViewList != null && mImageViewList.size() > 0) {
            for (ImageView imageView:mImageViewList) {
                if(imageView!=null){
                    for (Integer integer : mDisplayImageViewList) {
                        if(integer!=null){
                            if(imageView.getId() == integer){
                                imageView.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
            }

        }
    }
    public TengTongRenTiTuBei(MyBaseActivity activity,List<Integer> imageViewList) {
        mActivity = activity;
        mDisplayImageViewList = imageViewList;
        initView(activity);
        setViewOnClick();
        addList();
        initViewVisible();
    }

    private void addList() {
        mImageViewList = new ArrayList<>();
        mImageViewList.add(mTongDianBeiTouImgv1);
        mImageViewList.add(mTongDianBeiJingImgv2);
        mImageViewList.add(mTongDianBeiBeiImgv3);
        mImageViewList.add(mTongDianBeiZuoshouzhouImgv4);
        mImageViewList.add(mTongDianBeiYaoImgv5);
        mImageViewList.add(mTongDianBeiYoushouzhouImgv6);
        mImageViewList.add(mTongDianBeiZuoshoubeiImgv7);
        mImageViewList.add(mTongDianBeiTunImgv8);
        mImageViewList.add(mTongDianBeiYoushoubeiImgv9);
        mImageViewList.add(mTongDianBeiRentibeiImgv10);
        mImageViewList.add(mTongDianBeiZuozugenImgv11);
        mImageViewList.add(mTongDianBeiYouzugenImgv12);
        mImageViewList.add(mTongDianBeiZuozudiImgv13);
        mImageViewList.add(mTongDianBeiYouzudiImgv14);
        mImageViewList.add(mTongDianBeiZuojiaozhiImgv15);
        mImageViewList.add(mTongDianBeiYoujiaozhiImgv16);
    }

    private void setViewOnClick() {
        mTongBeiTouImgv1.setOnClickListener(this);
        mTongBeiJingImgv2.setOnClickListener(this);
        mTongBeiBeiImgv3.setOnClickListener(this);
        mTongBeiZuoshouzhouImgv4.setOnClickListener(this);
        mTongBeiYaoImgv5.setOnClickListener(this);
        mTongBeiYoushouzhouImgv6.setOnClickListener(this);
        mTongBeiZuoshoubeiImgv7.setOnClickListener(this);
        mTongBeiTunImgv8.setOnClickListener(this);
        mTongBeiYoushoubeiImgv9.setOnClickListener(this);
        mTongBeiRentibeiImgv10.setOnClickListener(this);
        mTongBeiZuozugenImgv11.setOnClickListener(this);
        mTongBeiYouzugenImgv12.setOnClickListener(this);
        mTongBeiZuozudiImgv13.setOnClickListener(this);
        mTongBeiYouzudiImgv14.setOnClickListener(this);
        mTongBeiZuojiaozhiImgv15.setOnClickListener(this);
        mTongBeiYoujiaozhiImgv16.setOnClickListener(this);
    }

    private void initView(MyBaseActivity activity) {
        mTongBeiTouImgv1 = (ImageView) activity.findViewById(R.id.tong_bei_tou_imgv1);
        mTongBeiJingImgv2 = (ImageView) activity.findViewById(R.id.tong_bei_jing_imgv2);
        mTongBeiBeiImgv3 = (ImageView) activity.findViewById(R.id.tong_bei_bei_imgv3);
        mTongBeiZuoshouzhouImgv4 = (ImageView) activity.findViewById(R.id.tong_bei_zuoshouzhou_imgv4);
        mTongBeiYaoImgv5 = (ImageView) activity.findViewById(R.id.tong_bei_yao_imgv5);
        mTongBeiYoushouzhouImgv6 = (ImageView) activity.findViewById(R.id.tong_bei_youshouzhou_imgv6);
        mTongBeiZuoshoubeiImgv7 = (ImageView) activity.findViewById(R.id.tong_bei_zuoshoubei_imgv7);
        mTongBeiTunImgv8 = (ImageView) activity.findViewById(R.id.tong_bei_tun_imgv8);
        mTongBeiYoushoubeiImgv9 = (ImageView) activity.findViewById(R.id.tong_bei_youshoubei_imgv9);
        mTongBeiRentibeiImgv10 = (ImageView) activity.findViewById(R.id.tong_bei_rentibei_imgv10);
        mTongBeiZuozugenImgv11 = (ImageView) activity.findViewById(R.id.tong_bei_zuozugen_imgv11);
        mTongBeiYouzugenImgv12 = (ImageView) activity.findViewById(R.id.tong_bei_youzugen_imgv12);
        mTongBeiZuozudiImgv13 = (ImageView) activity.findViewById(R.id.tong_bei_zuozudi_imgv13);
        mTongBeiYouzudiImgv14 = (ImageView) activity.findViewById(R.id.tong_bei_youzudi_imgv14);
        mTongBeiZuojiaozhiImgv15 = (ImageView) activity.findViewById(R.id.tong_bei_zuojiaozhi_imgv15);
        mTongBeiYoujiaozhiImgv16 = (ImageView) activity.findViewById(R.id.tong_bei_youjiaozhi_imgv16);
        mTongDianBeiTouImgv1 = (ImageView) activity.findViewById(R.id.tong_dian_bei_tou_imgv1);
        mTongDianBeiJingImgv2 = (ImageView) activity.findViewById(R.id.tong_dian_bei_jing_imgv2);
        mTongDianBeiBeiImgv3 = (ImageView) activity.findViewById(R.id.tong_dian_bei_bei_imgv3);
        mTongDianBeiZuoshouzhouImgv4 = (ImageView) activity.findViewById(R.id.tong_dian_bei_zuoshouzhou_imgv4);
        mTongDianBeiYaoImgv5 = (ImageView) activity.findViewById(R.id.tong_dian_bei_yao_imgv5);
        mTongDianBeiYoushouzhouImgv6 = (ImageView) activity.findViewById(R.id.tong_dian_bei_youshouzhou_imgv6);
        mTongDianBeiZuoshoubeiImgv7 = (ImageView) activity.findViewById(R.id.tong_dian_bei_zuoshoubei_imgv7);
        mTongDianBeiTunImgv8 = (ImageView) activity.findViewById(R.id.tong_dian_bei_tun_imgv8);
        mTongDianBeiYoushoubeiImgv9 = (ImageView) activity.findViewById(R.id.tong_dian_bei_youshoubei_imgv9);
        mTongDianBeiRentibeiImgv10 = (ImageView) activity.findViewById(R.id.tong_dian_bei_rentibei_imgv10);
        mTongDianBeiZuozugenImgv11 = (ImageView) activity.findViewById(R.id.tong_dian_bei_zuozugen_imgv11);
        mTongDianBeiYouzugenImgv12 = (ImageView) activity.findViewById(R.id.tong_dian_bei_youzugen_imgv12);
        mTongDianBeiZuozudiImgv13 = (ImageView) activity.findViewById(R.id.tong_dian_bei_zuozudi_imgv13);
        mTongDianBeiYouzudiImgv14 = (ImageView) activity.findViewById(R.id.tong_dian_bei_youzudi_imgv14);
        mTongDianBeiZuojiaozhiImgv15 = (ImageView) activity.findViewById(R.id.tong_dian_bei_zuojiaozhi_imgv15);
        mTongDianBeiYoujiaozhiImgv16 = (ImageView) activity.findViewById(R.id.tong_dian_bei_youjiaozhi_imgv16);
        mTongDianBeiTouImgv1.setVisibility(View.GONE);
        mTongDianBeiJingImgv2.setVisibility(View.GONE);
        mTongDianBeiBeiImgv3.setVisibility(View.GONE);
        mTongDianBeiZuoshouzhouImgv4.setVisibility(View.GONE);
        mTongDianBeiYaoImgv5.setVisibility(View.GONE);
        mTongDianBeiYoushouzhouImgv6.setVisibility(View.GONE);
        mTongDianBeiZuoshoubeiImgv7.setVisibility(View.GONE);
        mTongDianBeiTunImgv8.setVisibility(View.GONE);
        mTongDianBeiYoushoubeiImgv9.setVisibility(View.GONE);
        mTongDianBeiRentibeiImgv10.setVisibility(View.GONE);
        mTongDianBeiZuozugenImgv11.setVisibility(View.GONE);
        mTongDianBeiYouzugenImgv12.setVisibility(View.GONE);
        mTongDianBeiZuozudiImgv13.setVisibility(View.GONE);
        mTongDianBeiYouzudiImgv14.setVisibility(View.GONE);
        mTongDianBeiZuojiaozhiImgv15.setVisibility(View.GONE);
        mTongDianBeiYoujiaozhiImgv16.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tong_bei_tou_imgv1:
                setGoneOrVisible(mTongDianBeiTouImgv1);
                break;
            case R.id.tong_bei_jing_imgv2:
                setGoneOrVisible(mTongDianBeiJingImgv2);
                break;
            case R.id.tong_bei_bei_imgv3:
                setGoneOrVisible(mTongDianBeiBeiImgv3);
                break;
            case R.id.tong_bei_zuoshouzhou_imgv4:
                setGoneOrVisible(mTongDianBeiZuoshouzhouImgv4);
                break;
            case R.id.tong_bei_yao_imgv5:
                setGoneOrVisible(mTongDianBeiYaoImgv5);
                break;
            case R.id.tong_bei_youshouzhou_imgv6:
                setGoneOrVisible(mTongDianBeiYoushouzhouImgv6);
                break;
            case R.id.tong_bei_zuoshoubei_imgv7:
                setGoneOrVisible(mTongDianBeiZuoshoubeiImgv7);
                break;
            case R.id.tong_bei_tun_imgv8:
                setGoneOrVisible(mTongDianBeiTunImgv8);
                break;
            case R.id.tong_bei_youshoubei_imgv9:
                setGoneOrVisible(mTongDianBeiYoushoubeiImgv9);
                break;
//            case R.id.tong_bei_rentibei_imgv10:
//                setGoneOrVisible(mTongDianBeiRentibeiImgv10);
//                break;
            case R.id.tong_bei_zuozugen_imgv11:
                setGoneOrVisible(mTongDianBeiZuozugenImgv11);
                break;
            case R.id.tong_bei_youzugen_imgv12:
                setGoneOrVisible(mTongDianBeiYouzugenImgv12);
                break;
            case R.id.tong_bei_zuozudi_imgv13:
                setGoneOrVisible(mTongDianBeiZuozudiImgv13);
                break;
            case R.id.tong_bei_youzudi_imgv14:
                setGoneOrVisible(mTongDianBeiYouzudiImgv14);
                break;
            case R.id.tong_bei_zuojiaozhi_imgv15:
                setGoneOrVisible(mTongDianBeiZuojiaozhiImgv15);
                break;
            case R.id.tong_bei_youjiaozhi_imgv16:
                setGoneOrVisible(mTongDianBeiYoujiaozhiImgv16);
                break;
        }
    }

    public void setGoneOrVisible(ImageView goneOrVisible) {
        if (goneOrVisible == null) {
            return;
        }
        if (goneOrVisible.getVisibility() == View.VISIBLE) {
            goneOrVisible.setVisibility(View.GONE);
        } else {
            goneOrVisible.setVisibility(View.VISIBLE);
        }
    }
}
