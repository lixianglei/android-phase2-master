package com.ge.med.mobile.nursing.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.XuanJiaoBean;
import com.ge.med.mobile.nursing.forjson.entity.NetworkForImage;
import com.ge.med.mobile.nursing.ui.activity.ChangGuiHuLiActivity;
import com.ge.med.mobile.nursing.ui.activity.PG_ZJMActivity;

public class XuanJiaoXiangQingFragment extends BaseFragment {
    private TextView mBiaotiTv;
    private TextView mXuanjiaoXiangqingTv;
    private ImageView mXuanjiaoXiangqingImgv;
    private Button mXuanjiaoXiangqingBt;
    private Bundle mBundle;


    @Override
    public int setRootView() {
        return R.layout.fragment_xuan_jiao_xiang_qing;
    }

    @Override
    public void initViews() {
        mBiaotiTv = (TextView) mRootView.findViewById(R.id.biaoti_tv);
        mXuanjiaoXiangqingTv = (TextView) mRootView.findViewById(R.id.xuanjiao_xiangqing_tv);
        mXuanjiaoXiangqingImgv = (ImageView) mRootView.findViewById(R.id.xuanjiao_xiangqing_imgv);
        mXuanjiaoXiangqingBt = (Button) mRootView.findViewById(R.id.xuanjiao_xiangqing_bt);
        PG_ZJMActivity activity = (PG_ZJMActivity) this.getActivity();
        mBundle = activity.getmBundle();
    }

    @Override
    public void initDatas() {
        XuanJiaoBean.DataBean dataBean = (XuanJiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_XUANJIAO_DEFINE);
        if (dataBean != null) {
            String title = dataBean.getTitle();
            if (title.length() > 10) {
                title = title.substring(0, 9) + "...";
            }
            mBiaotiTv.setText(title);
            String detailpagetext = dataBean.getDetailpagetext();
            if(detailpagetext!=null){
                detailpagetext =  detailpagetext.replace("\\n","\n");
            }
            mXuanjiaoXiangqingTv.setText(detailpagetext);
            if(dataBean.getDetailpageimgpath()!=null){
                mXuanjiaoXiangqingImgv.setImageBitmap(NetworkForImage.getLoacalBitmap(dataBean.getDetailpageimgpath()));
            }
        }
    }

    @Override
    public void init() {
        mXuanjiaoXiangqingBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBundle.putString(Constant.BUNDLE_KEY_ACTIVITY_TYPE,Constant.BUNDLE_KEY_ACTIVITY_TYPE_XIANGQING);
                // 下一步---结果
                goToActivity(ChangGuiHuLiActivity.class,mBundle);
            }
        });
    }

    @Override
    public boolean useTitleBar() {
        return false;
    }
}
