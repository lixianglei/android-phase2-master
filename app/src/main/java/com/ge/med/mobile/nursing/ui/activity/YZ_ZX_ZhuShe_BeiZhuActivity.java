package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.dao.impl.DoctorOrderDaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.AdapterUtil;
import com.ge.med.mobile.nursing.ui.component.DocOrderPannel;
import com.ge.med.mobile.nursing.ui.component.TitleBar;
import com.ge.med.mobile.nursing.utils.DataConverter;
import com.mitac.lib.bcr.utils.BARCODE;

import java.util.ArrayList;
import java.util.List;

public class YZ_ZX_ZhuShe_BeiZhuActivity extends MyBaseActivity {

    private DocOrderPannel mDocOrderPannel;
    private EditText mBeizhuNeirongEdt;
    private Button mYzZxZhusheFenciBaocunBt;

    private Bundle mBundle;
    private YiZhuBean.DataBean dataBean;
    private DBHuanZheLieBiao huanZheLieBiao;
    private TitleBar mTitleBar;
    private String displayUserName;
    private String userid;

    @Override
    public int setRootView() {
        return R.layout.activity_yz__zx__zhu_she__bei_zhu;
    }

    @Override
    public void initViews() {
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        userid = sharePLogin.getUserid();
        displayUserName = sharePLogin.getDisplayUserName();
        mBundle = getIntent().getBundleExtra("data");
        dataBean = (YiZhuBean.DataBean) mBundle.getSerializable(Constant.BUNDLE_KEY_YIZHU);
        huanZheLieBiao = AdapterUtil.getPatientFromDB(dataBean.getPatientid());
        mBeizhuNeirongEdt = (EditText) findViewById(R.id.beizhu_neirong_edt);
        mYzZxZhusheFenciBaocunBt = (Button) findViewById(R.id.yz_zx_zhushe_fenci_baocun_bt);
        mTitleBar = new TitleBar(this, DataConverter.convert(huanZheLieBiao));
        mDocOrderPannel = new DocOrderPannel(this, dataBean);
        dataBean = new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId());
    }

    @Override
    public void initDatas() {
        mYzZxZhusheFenciBaocunBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dataBean!=null){
                    if( dataBean.getOrderNoteList() == null){
                        dataBean.setOrderNoteList(new ArrayList<YiZhuBean.OrderNoteListBean>());
                    }
                    YiZhuBean.OrderNoteListBean orderNoteListBean = new YiZhuBean.OrderNoteListBean();
                    orderNoteListBean.setHisorderid(dataBean.getId());
                    orderNoteListBean.setNoterecordtime(System.currentTimeMillis());
                    orderNoteListBean.setNoteStatus(dataBean.getOrderststus());
                    orderNoteListBean.setNotevalue(mBeizhuNeirongEdt.getText().toString());
                    dataBean.getOrderNoteList().add(0,orderNoteListBean);
                }
                new DoctorOrderDaoImpl().saveDoctorOrder(dataBean,true);
                NetworkForDoctorOrder.submitSingleDoctorOrder(YZ_ZX_ZhuShe_BeiZhuActivity.this, dataBean);

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        mDocOrderPannel.bindData(new DoctorOrderDaoImpl().getDoctorOrderByHisId(dataBean.getId()));
    }
    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    @Override
    public void handleOnError() {
        super.handleOnError();
        killSelf();

    }

    @Override
    public void handleOnError(String urlStr) {
        super.handleOnError(urlStr);
        killSelf();

    }

    @Override
    public void handleSuccess(Object obj) {
        super.handleSuccess(obj);
        killSelf();

    }

}
