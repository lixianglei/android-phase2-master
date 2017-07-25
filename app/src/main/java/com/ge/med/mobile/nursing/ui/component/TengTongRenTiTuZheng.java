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
public class TengTongRenTiTuZheng implements View.OnClickListener {
    private MyBaseActivity mActivity;
    private ImageView mTongZhengLianImgv1;
    private ImageView mTongZhengYoujianImgv2;
    private ImageView mTongZhengHoubuImgv3;
    private ImageView mTongZhengZuojianImgv4;
    private ImageView mTongZhengYoushangbiImgv5;
    private ImageView mTongZhengXiongbuImgv6;
    private ImageView mTongZhengZuoshangbiImgv7;
    private ImageView mTongZhengFubuImgv8;
    private ImageView mTongZhengYouqianbiImgv9;
    private ImageView mTongZhengYinbuImgv10;
    private ImageView mTongZhengZuoqianbiImgv11;
    private ImageView mTongZhengYoushouwanImgv12;
    private ImageView mTongZhengZuoshouwanImgv13;
    private ImageView mTongZhengYoushouzhangImgv14;
    private ImageView mTongZhengZuoshouzhangImgv15;
    private ImageView mTongZhengYoushouzhiImgv16;
    private ImageView mTongZhengZuoshouzhiImgv17;
    private ImageView mTongZhengYoudatuiImgv18;
    private ImageView mTongZhengZuodatuiImgv19;
    private ImageView mTongZhengYouxiImgv20;
    private ImageView mTongZhengZuoxiImgv21;
    private ImageView mTongZhengYouxiaotuiImgv22;
    private ImageView mTongZhengZuoxiaotuiImgv23;
    private ImageView mTongZhengYouzuhuaiImgv24;
    private ImageView mTongZhengZuozuhuaiImgv25;
    private ImageView mTongZhengYouzubeiImgv26;
    private ImageView mTongZhengZuozubeiImgv27;
    private ImageView mTongZhengYoujiaoImgv28;
    private ImageView mTongZhengZuojiaoImgv29;
    private ImageView mTongDianZhengLianImgv1;
    private ImageView mTongDianZhengYoujianImgv2;
    private ImageView mTongDianZhengHoubuImgv3;
    private ImageView mTongDianZhengZuojianImgv4;
    private ImageView mTongDianZhengYoushangbiImgv5;
    private ImageView mTongDianZhengXiongbuImgv6;
    private ImageView mTongDianZhengZuoshangbiImgv7;
    private ImageView mTongDianZhengFubuImgv8;
    private ImageView mTongDianZhengYouqianbiImgv9;
    private ImageView mTongDianZhengYinbuImgv10;
    private ImageView mTongDianZhengZuoqianbiImgv11;
    private ImageView mTongDianZhengYoushouwanImgv12;
    private ImageView mTongDianZhengZuoshouwanImgv13;
    private ImageView mTongDianZhengYoushouzhangImgv14;
    private ImageView mTongDianZhengZuoshouzhangImgv15;
    private ImageView mTongDianZhengYoushouzhiImgv16;
    private ImageView mTongDianZhengZuoshouzhiImgv17;
    private ImageView mTongDianZhengYoudatuiImgv18;
    private ImageView mTongDianZhengZuodatuiImgv19;
    private ImageView mTongDianZhengYouxiImgv20;
    private ImageView mTongdianZhengZuoxiImgv21;
    private ImageView mTongDianZhengYouxiaotuiImgv22;
    private ImageView mTongDianZhengZuoxiaotuiImgv23;
    private ImageView mTongDianZhengYouzuhuaiImgv24;
    private ImageView mTongDianZhengZuozuhuaiImgv25;
    private ImageView mTongDianZhengYouzubeiImgv26;
    private ImageView mTongDianZhengZuozubeiImgv27;
    private ImageView mTongDianZhengYoujiaoImgv28;
    private ImageView mTongDianZhengZuojiaoImgv29;
    private List<ImageView> mImageViewList;
    private List<Integer> mDisplayImageViewList;

    public TengTongRenTiTuZheng(MyBaseActivity activity, List<Integer> imageViewList) {
        mActivity = activity;
        mDisplayImageViewList = imageViewList;
        initView(activity);
        setViewOnClick();
        addList();
        initViewVisible();
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

    private void addList() {
        mImageViewList = new ArrayList<>();
        mImageViewList.add(mTongDianZhengLianImgv1);
        mImageViewList.add(mTongDianZhengYoujianImgv2);
        mImageViewList.add(mTongDianZhengHoubuImgv3);
        mImageViewList.add(mTongDianZhengZuojianImgv4);
        mImageViewList.add(mTongDianZhengYoushangbiImgv5);
        mImageViewList.add(mTongDianZhengXiongbuImgv6);
        mImageViewList.add(mTongDianZhengZuoshangbiImgv7);
        mImageViewList.add(mTongDianZhengFubuImgv8);
        mImageViewList.add(mTongDianZhengYouqianbiImgv9);
        mImageViewList.add(mTongDianZhengYinbuImgv10);
        mImageViewList.add(mTongDianZhengZuoqianbiImgv11);
        mImageViewList.add(mTongDianZhengYoushouwanImgv12);
        mImageViewList.add(mTongDianZhengZuoshouwanImgv13);
        mImageViewList.add(mTongDianZhengYoushouzhangImgv14);
        mImageViewList.add(mTongDianZhengZuoshouzhangImgv15);
        mImageViewList.add(mTongDianZhengYoushouzhiImgv16);
        mImageViewList.add(mTongDianZhengZuoshouzhiImgv17);
        mImageViewList.add(mTongDianZhengYoudatuiImgv18);
        mImageViewList.add(mTongDianZhengZuodatuiImgv19);
        mImageViewList.add(mTongDianZhengYouxiImgv20);
        mImageViewList.add(mTongdianZhengZuoxiImgv21);
        mImageViewList.add(mTongDianZhengYouxiaotuiImgv22);
        mImageViewList.add(mTongDianZhengZuoxiaotuiImgv23);
        mImageViewList.add(mTongDianZhengYouzuhuaiImgv24);
        mImageViewList.add(mTongDianZhengZuozuhuaiImgv25);
        mImageViewList.add(mTongDianZhengYouzubeiImgv26);
        mImageViewList.add(mTongDianZhengZuozubeiImgv27);
        mImageViewList.add(mTongDianZhengYoujiaoImgv28);
        mImageViewList.add(mTongDianZhengZuojiaoImgv29);
    }

    private void initView(MyBaseActivity activity) {
        mTongZhengLianImgv1 = (ImageView) activity.findViewById(R.id.tong_zheng_lian_imgv1);
        mTongZhengYoujianImgv2 = (ImageView) activity.findViewById(R.id.tong_zheng_youjian_imgv2);
        mTongZhengHoubuImgv3 = (ImageView) activity.findViewById(R.id.tong_zheng_houbu_imgv3);
        mTongZhengZuojianImgv4 = (ImageView) activity.findViewById(R.id.tong_zheng_zuojian_imgv4);
        mTongZhengYoushangbiImgv5 = (ImageView) activity.findViewById(R.id.tong_zheng_youshangbi_imgv5);
        mTongZhengXiongbuImgv6 = (ImageView) activity.findViewById(R.id.tong_zheng_xiongbu_imgv6);
        mTongZhengZuoshangbiImgv7 = (ImageView) activity.findViewById(R.id.tong_zheng_zuoshangbi_imgv7);
        mTongZhengFubuImgv8 = (ImageView) activity.findViewById(R.id.tong_zheng_fubu_imgv8);
        mTongZhengYouqianbiImgv9 = (ImageView) activity.findViewById(R.id.tong_zheng_youqianbi_imgv9);
        mTongZhengYinbuImgv10 = (ImageView) activity.findViewById(R.id.tong_zheng_yinbu_imgv10);
        mTongZhengZuoqianbiImgv11 = (ImageView) activity.findViewById(R.id.tong_zheng_zuoqianbi_imgv11);
        mTongZhengYoushouwanImgv12 = (ImageView) activity.findViewById(R.id.tong_zheng_youshouwan_imgv12);
        mTongZhengZuoshouwanImgv13 = (ImageView) activity.findViewById(R.id.tong_zheng_zuoshouwan_imgv13);
        mTongZhengYoushouzhangImgv14 = (ImageView) activity.findViewById(R.id.tong_zheng_youshouzhang_imgv14);
        mTongZhengZuoshouzhangImgv15 = (ImageView) activity.findViewById(R.id.tong_zheng_zuoshouzhang_imgv15);
        mTongZhengYoushouzhiImgv16 = (ImageView) activity.findViewById(R.id.tong_zheng_youshouzhi_imgv16);
        mTongZhengZuoshouzhiImgv17 = (ImageView) activity.findViewById(R.id.tong_zheng_zuoshouzhi_imgv17);
        mTongZhengYoudatuiImgv18 = (ImageView) activity.findViewById(R.id.tong_zheng_youdatui_imgv18);
        mTongZhengZuodatuiImgv19 = (ImageView) activity.findViewById(R.id.tong_zheng_zuodatui_imgv19);
        mTongZhengYouxiImgv20 = (ImageView) activity.findViewById(R.id.tong_zheng_youxi_imgv20);
        mTongZhengZuoxiImgv21 = (ImageView) activity.findViewById(R.id.tong_zheng_zuoxi_imgv21);
        mTongZhengYouxiaotuiImgv22 = (ImageView) activity.findViewById(R.id.tong_zheng_youxiaotui_imgv22);
        mTongZhengZuoxiaotuiImgv23 = (ImageView) activity.findViewById(R.id.tong_zheng_zuoxiaotui_imgv23);
        mTongZhengYouzuhuaiImgv24 = (ImageView) activity.findViewById(R.id.tong_zheng_youzuhuai_imgv24);
        mTongZhengZuozuhuaiImgv25 = (ImageView) activity.findViewById(R.id.tong_zheng_zuozuhuai_imgv25);
        mTongZhengYouzubeiImgv26 = (ImageView) activity.findViewById(R.id.tong_zheng_youzubei_imgv26);
        mTongZhengZuozubeiImgv27 = (ImageView) activity.findViewById(R.id.tong_zheng_zuozubei_imgv27);
        mTongZhengYoujiaoImgv28 = (ImageView) activity.findViewById(R.id.tong_zheng_youjiao_imgv28);
        mTongZhengZuojiaoImgv29 = (ImageView) activity.findViewById(R.id.tong_zheng_zuojiao_imgv29);
        mTongDianZhengLianImgv1 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_lian_imgv1);
        mTongDianZhengYoujianImgv2 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_youjian_imgv2);
        mTongDianZhengHoubuImgv3 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_houbu_imgv3);
        mTongDianZhengZuojianImgv4 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_zuojian_imgv4);
        mTongDianZhengYoushangbiImgv5 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_youshangbi_imgv5);
        mTongDianZhengXiongbuImgv6 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_xiongbu_imgv6);
        mTongDianZhengZuoshangbiImgv7 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_zuoshangbi_imgv7);
        mTongDianZhengFubuImgv8 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_fubu_imgv8);
        mTongDianZhengYouqianbiImgv9 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_youqianbi_imgv9);
        mTongDianZhengYinbuImgv10 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_yinbu_imgv10);
        mTongDianZhengZuoqianbiImgv11 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_zuoqianbi_imgv11);
        mTongDianZhengYoushouwanImgv12 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_youshouwan_imgv12);
        mTongDianZhengZuoshouwanImgv13 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_zuoshouwan_imgv13);
        mTongDianZhengYoushouzhangImgv14 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_youshouzhang_imgv14);
        mTongDianZhengZuoshouzhangImgv15 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_zuoshouzhang_imgv15);
        mTongDianZhengYoushouzhiImgv16 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_youshouzhi_imgv16);
        mTongDianZhengZuoshouzhiImgv17 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_zuoshouzhi_imgv17);
        mTongDianZhengYoudatuiImgv18 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_youdatui_imgv18);
        mTongDianZhengZuodatuiImgv19 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_zuodatui_imgv19);
        mTongDianZhengYouxiImgv20 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_youxi_imgv20);
        mTongdianZhengZuoxiImgv21 = (ImageView) activity.findViewById(R.id.tongdian__zheng_zuoxi_imgv21);
        mTongDianZhengYouxiaotuiImgv22 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_youxiaotui_imgv22);
        mTongDianZhengZuoxiaotuiImgv23 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_zuoxiaotui_imgv23);
        mTongDianZhengYouzuhuaiImgv24 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_youzuhuai_imgv24);
        mTongDianZhengZuozuhuaiImgv25 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_zuozuhuai_imgv25);
        mTongDianZhengYouzubeiImgv26 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_youzubei_imgv26);
        mTongDianZhengZuozubeiImgv27 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_zuozubei_imgv27);
        mTongDianZhengYoujiaoImgv28 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_youjiao_imgv28);
        mTongDianZhengZuojiaoImgv29 = (ImageView) activity.findViewById(R.id.tong_dian_zheng_zuojiao_imgv29);

        mTongDianZhengLianImgv1.setVisibility(View.GONE);
        mTongDianZhengYoujianImgv2.setVisibility(View.GONE);
        mTongDianZhengHoubuImgv3.setVisibility(View.GONE);
        mTongDianZhengZuojianImgv4.setVisibility(View.GONE);
        mTongDianZhengYoushangbiImgv5.setVisibility(View.GONE);
        mTongDianZhengXiongbuImgv6.setVisibility(View.GONE);
        mTongDianZhengZuoshangbiImgv7.setVisibility(View.GONE);
        mTongDianZhengFubuImgv8.setVisibility(View.GONE);
        mTongDianZhengYouqianbiImgv9.setVisibility(View.GONE);
        mTongDianZhengYinbuImgv10.setVisibility(View.GONE);
        mTongDianZhengZuoqianbiImgv11.setVisibility(View.GONE);
        mTongDianZhengYoushouwanImgv12.setVisibility(View.GONE);
        mTongDianZhengZuoshouwanImgv13.setVisibility(View.GONE);
        mTongDianZhengYoushouzhangImgv14.setVisibility(View.GONE);
        mTongDianZhengZuoshouzhangImgv15.setVisibility(View.GONE);
        mTongDianZhengYoushouzhiImgv16.setVisibility(View.GONE);
        mTongDianZhengZuoshouzhiImgv17.setVisibility(View.GONE);
        mTongDianZhengYoudatuiImgv18.setVisibility(View.GONE);
        mTongDianZhengZuodatuiImgv19.setVisibility(View.GONE);
        mTongDianZhengYouxiImgv20.setVisibility(View.GONE);
        mTongdianZhengZuoxiImgv21.setVisibility(View.GONE);
        mTongDianZhengYouxiaotuiImgv22.setVisibility(View.GONE);
        mTongDianZhengZuoxiaotuiImgv23.setVisibility(View.GONE);
        mTongDianZhengYouzuhuaiImgv24.setVisibility(View.GONE);
        mTongDianZhengZuozuhuaiImgv25.setVisibility(View.GONE);
        mTongDianZhengYouzubeiImgv26.setVisibility(View.GONE);
        mTongDianZhengZuozubeiImgv27.setVisibility(View.GONE);
        mTongDianZhengYoujiaoImgv28.setVisibility(View.GONE);
        mTongDianZhengZuojiaoImgv29.setVisibility(View.GONE);
    }

    private void setViewOnClick() {
        mTongZhengLianImgv1.setOnClickListener(this);
        mTongZhengYoujianImgv2.setOnClickListener(this);
        mTongZhengHoubuImgv3.setOnClickListener(this);
        mTongZhengZuojianImgv4.setOnClickListener(this);
        mTongZhengYoushangbiImgv5.setOnClickListener(this);
        mTongZhengXiongbuImgv6.setOnClickListener(this);
        mTongZhengZuoshangbiImgv7.setOnClickListener(this);
        mTongZhengFubuImgv8.setOnClickListener(this);
        mTongZhengYouqianbiImgv9.setOnClickListener(this);
        mTongZhengYinbuImgv10.setOnClickListener(this);
        mTongZhengZuoqianbiImgv11.setOnClickListener(this);
        mTongZhengYoushouwanImgv12.setOnClickListener(this);
        mTongZhengZuoshouwanImgv13.setOnClickListener(this);
        mTongZhengYoushouzhangImgv14.setOnClickListener(this);
        mTongZhengZuoshouzhangImgv15.setOnClickListener(this);
        mTongZhengYoushouzhiImgv16.setOnClickListener(this);
        mTongZhengZuoshouzhiImgv17.setOnClickListener(this);
        mTongZhengYoudatuiImgv18.setOnClickListener(this);
        mTongZhengZuodatuiImgv19.setOnClickListener(this);
        mTongZhengYouxiImgv20.setOnClickListener(this);
        mTongZhengZuoxiImgv21.setOnClickListener(this);
        mTongZhengYouxiaotuiImgv22.setOnClickListener(this);
        mTongZhengZuoxiaotuiImgv23.setOnClickListener(this);
        mTongZhengYouzuhuaiImgv24.setOnClickListener(this);
        mTongZhengZuozuhuaiImgv25.setOnClickListener(this);
        mTongZhengYouzubeiImgv26.setOnClickListener(this);
        mTongZhengZuozubeiImgv27.setOnClickListener(this);
        mTongZhengYoujiaoImgv28.setOnClickListener(this);
        mTongZhengZuojiaoImgv29.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tong_zheng_lian_imgv1:
                setGoneOrVisible(mTongDianZhengLianImgv1);
                break;
            case R.id.tong_zheng_youjian_imgv2:
                setGoneOrVisible(mTongDianZhengYoujianImgv2);
                break;
            case R.id.tong_zheng_houbu_imgv3:
                setGoneOrVisible(mTongDianZhengHoubuImgv3);
                break;
            case R.id.tong_zheng_zuojian_imgv4:
                setGoneOrVisible(mTongDianZhengZuojianImgv4);
                break;
            case R.id.tong_zheng_youshangbi_imgv5:
                setGoneOrVisible(mTongDianZhengYoushangbiImgv5);
                break;
            case R.id.tong_zheng_xiongbu_imgv6:
                setGoneOrVisible(mTongDianZhengXiongbuImgv6);
                break;
            case R.id.tong_zheng_zuoshangbi_imgv7:
                setGoneOrVisible(mTongDianZhengZuoshangbiImgv7);
                break;
            case R.id.tong_zheng_fubu_imgv8:
                setGoneOrVisible(mTongDianZhengFubuImgv8);
                break;
            case R.id.tong_zheng_youqianbi_imgv9:
                setGoneOrVisible(mTongDianZhengYouqianbiImgv9);
                break;
            case R.id.tong_zheng_yinbu_imgv10:
                setGoneOrVisible(mTongDianZhengYinbuImgv10);
                break;
            case R.id.tong_zheng_zuoqianbi_imgv11:
                setGoneOrVisible(mTongDianZhengZuoqianbiImgv11);
                break;
            case R.id.tong_zheng_youshouwan_imgv12:
                setGoneOrVisible(mTongDianZhengYoushouwanImgv12);
                break;
            case R.id.tong_zheng_zuoshouwan_imgv13:
                setGoneOrVisible(mTongDianZhengZuoshouwanImgv13);
                break;
            case R.id.tong_zheng_youshouzhang_imgv14:
                setGoneOrVisible(mTongDianZhengYoushouzhangImgv14);
                break;
            case R.id.tong_zheng_zuoshouzhang_imgv15:
                setGoneOrVisible(mTongDianZhengZuoshouzhangImgv15);
                break;
            case R.id.tong_zheng_youshouzhi_imgv16:
                setGoneOrVisible(mTongDianZhengYoushouzhiImgv16);
                break;
            case R.id.tong_zheng_zuoshouzhi_imgv17:
                setGoneOrVisible(mTongDianZhengZuoshouzhiImgv17);
                break;
            case R.id.tong_zheng_youdatui_imgv18:
                setGoneOrVisible(mTongDianZhengYoudatuiImgv18);
                break;
            case R.id.tong_zheng_zuodatui_imgv19:
                setGoneOrVisible(mTongDianZhengZuodatuiImgv19);
                break;
            case R.id.tong_zheng_youxi_imgv20:
                setGoneOrVisible(mTongDianZhengYouxiImgv20);
                break;
            case R.id.tong_zheng_zuoxi_imgv21:
                setGoneOrVisible(mTongdianZhengZuoxiImgv21);
                break;
            case R.id.tong_zheng_youxiaotui_imgv22:
                setGoneOrVisible(mTongDianZhengYouxiaotuiImgv22);
                break;
            case R.id.tong_zheng_zuoxiaotui_imgv23:
                setGoneOrVisible(mTongDianZhengZuoxiaotuiImgv23);
                break;
            case R.id.tong_zheng_youzuhuai_imgv24:
                setGoneOrVisible(mTongDianZhengYouzuhuaiImgv24);
                break;
            case R.id.tong_zheng_zuozuhuai_imgv25:
                setGoneOrVisible(mTongDianZhengZuozuhuaiImgv25);
                break;
            case R.id.tong_zheng_youzubei_imgv26:
                setGoneOrVisible(mTongDianZhengYouzubeiImgv26);
                break;
            case R.id.tong_zheng_zuozubei_imgv27:
                setGoneOrVisible(mTongDianZhengZuozubeiImgv27);
                break;
            case R.id.tong_zheng_youjiao_imgv28:
                setGoneOrVisible(mTongDianZhengYoujiaoImgv28);
                break;
            case R.id.tong_zheng_zuojiao_imgv29:
                setGoneOrVisible(mTongDianZhengZuojiaoImgv29);
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
