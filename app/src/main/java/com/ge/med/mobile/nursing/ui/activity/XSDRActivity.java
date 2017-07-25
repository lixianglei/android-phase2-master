package com.ge.med.mobile.nursing.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sj.library.util.DateUtils;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.shareP.SharePLogin;
import com.ge.med.mobile.nursing.ui.adapter.XsdrLvItemAdapter;
import com.ge.med.mobile.nursing.ui.component.TitleBar;

import org.xutils.common.util.LogUtil;

import java.util.List;

public class XSDRActivity extends MyBaseActivity {
    private TextView mXsdrSjTv;
    private ListView mXsdrLv;
    private XsdrLvItemAdapter xsdrLvItemAdapter;
    private List<YiZhuBean.DataBean> mListYiZhu;
    private String yizhuLeixing = Constant.BUNDLE_KEY_SHUYE;
    private  String yztype = Constant.TYPE_YZ_SHUXUE;
    private TitleBar mTitleBar;
    private HuanZheLieBiaoBean.DataBean mSelectedHZ;
    @Override
    public int setRootView() {
        return R.layout.activity_xsdr;
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        yztype = Constant.TYPE_YZ_SHUXUE;
        if (Constant.BUNDLE_KEY_SHUYE.equals(yizhuLeixing)){
            yztype = Constant.TYPE_YZ_SHUYE;
        }
        NetworkForDoctorOrder.callFindAllDoctorOrder(this, sharePLogin.getWardid(), null, null, yztype, Constant.YZ_TYPE_ZHIXINGZHONG);
    }

    @Override
    public void handleSuccess(Object obj) {
        if (obj == null){
            LogUtil.i("Can not handle anything since return obj is null!");
            return;
        }
        if (obj instanceof  List && ((List)obj).size() > 0) {
            List lst = ((List) obj);
            if (lst.get(0) instanceof YiZhuBean.DataBean) {
                showToastShort("在线获取巡视医嘱数据成功");
                mListYiZhu = (List<YiZhuBean.DataBean>) lst;
                LogUtil.d("Found [" + (mListYiZhu==null?"null":mListYiZhu.size()) + "] doctor order for query check!");
                xsdrLvItemAdapter.setObjects(mListYiZhu);
                xsdrLvItemAdapter.notifyDataSetChanged();
            }else LogUtil.e("Can not handle anything since return collection is not recognized!");
        } else LogUtil.e("Can not handle anything since return object is not a collection!");
    }

    @Override
    public void initViews() {
        Bundle data = getIntent().getBundleExtra(Constant.GLOBAL_KEY_DATA);
        yizhuLeixing = data.getString(Constant.BUNDLE_KEY_YIZHULEIXING);
        mTitleBar = new TitleBar(this, "巡视");
        SharePLogin sharePLogin = new SharePLogin(mActivitySelf);
        mXsdrSjTv = (TextView) findViewById(R.id.xsdr_sj_tv);
        mXsdrLv = (ListView) findViewById(R.id.xsdr_lv);
        mXsdrSjTv.setText(DateUtils.getDateString("yyyy/MM/dd", System.currentTimeMillis()));
        xsdrLvItemAdapter = new XsdrLvItemAdapter(this);
        mXsdrLv.setAdapter(xsdrLvItemAdapter);
        mXsdrLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mListYiZhu.get(position)!=null) {
                    afterDoctorOrderScanned(mListYiZhu.get(position).getId());
                }
//                Bundle bd = new Bundle();
//                bd.putSerializable(Constant.BUNDLE_KEY_YIZHU, mListYiZhu.get(position));
//                if(mListYiZhu.get(position)!=null){
//                    String patientid =mListYiZhu.get(position).getPatientId();
//                    DBHuanZheLieBiao dbHuanZheLieBiao = new HuanZheLieBiaoImpl().getDBHuanZheLieBiao(patientid);
//                    mSelectedHZ = DataConverter.convert(dbHuanZheLieBiao);
//                }
//                bd.putSerializable(Constant.BUNDLE_KEY_SELECTED_HUANZHE,mSelectedHZ);
//                if (Constant.BUNDLE_KEY_SHUXUE.equals(yizhuLeixing)) {
//                    goToActivity(YZ_ZXActivity.class, bd,0);
//                }else if(Constant.BUNDLE_KEY_SHUYE.equals(yizhuLeixing)){
//                    goToActivity(YZ_ZX_ShuYeActivity.class, bd,0);
//                }
            }
        });
    }

    @Override
    public void initDatas() {


    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

}