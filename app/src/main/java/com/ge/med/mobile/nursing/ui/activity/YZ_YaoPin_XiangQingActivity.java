package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.TuPianBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
//医嘱药品详情
public class YZ_YaoPin_XiangQingActivity extends MyBaseActivity {

    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private TitleBar mTitleBar;
    private DocOrderPannel mOrderPannel;
    private ImageView mYzXiangQingImgv;
    public Map<Integer,String> tuPianURL = new HashMap<>();
    private EditText mYzXiangQingShuoMingEdt;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                Glide.with(mActivitySelf)
                        .load(tuPianURL.get(dataBean))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(mYzXiangQingImgv);
            }
        }
    };

    @Override
    public int setRootView() {
        return R.layout.activity_yz_yaopin_xiang_qing;
    }

    @Override
    public void initViews() {
        mYzXiangQingImgv = (ImageView) findViewById(R.id.yz_xiang_qing_imgv);
        mYzXiangQingShuoMingEdt = (EditText) findViewById(R.id.yz_xiang_qing_shuo_ming_edt);

        mBundle = getIntent().getBundleExtra(Constant.GLOBAL_KEY_DATA);
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
        mOrderPannel = new DocOrderPannel(this, (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU));
        mTitleBar = new TitleBar(this, (HuanZheLieBiaoBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE));
        if (dataBean != null) {
            if (dataBean.getPharmList() != null) {
//                mYzXiangQingShuoMingEdt.setText(dataBean.getMedicinedetails());
            }
            if (dataBean.getImageid() != null) {
                OkHttpUtils.get().url(URL.URL_ICON)
                        .addHeader("User-Agent", "www.gs.com")
                        .addParams(Constant.GLOBAL_KEY_ID, dataBean.getImageid())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                e.printStackTrace();
                                if(tuPianURL.get(dataBean.getId())!=null){
                                    mHandler.sendEmptyMessage(1);
                                }
                            }
                            @Override
                            public void onResponse(String response, int id) {
                                TuPianBean tuPianBean = JSON.parseObject(response, TuPianBean.class);
                                tuPianURL .put(dataBean.getId(),tuPianBean.getData());
                                mHandler.sendEmptyMessage(1);
                            }
                        });
            } else {

            }
        }
    }

    @Override
    public void initDatas() {

    }
    @Override
    protected void onResume() {
        super.onResume();
        mOrderPannel.bindData(new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId()));
    }
    @Override
    public void init() {

    }
    @Override
    public boolean useTitleBar() {
        return false;
    }

}
