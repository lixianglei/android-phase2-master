package com.ge.med.mobile.nursing.ui.component;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.base.MyBaseActivity;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.entity.NursingEventTempLateLisBean;
import com.ge.med.mobile.nursing.dao.entity.TiJiaoXuanJiao;
import com.ge.med.mobile.nursing.dao.entity.XuanJiaoBean;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.db.DBXuanJiaoRecord;
import com.ge.med.mobile.nursing.ui.activity.ChangGuiHuLiActivity;
import com.ge.med.mobile.nursing.ui.adapter.YiZhuBeiZhuAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/31.
 */
public class ChangGuiHuLiBeiZhu {
    private List<NursingEventTempLateLisBean.DataBean> nursingEventTempLateList;
    private YiZhuBean.DataBean entities;
    private YiZhuBeiZhuAdapter yiZhuBeiZhuAdapter;
    private MyBaseActivity mActivity;
    private LayoutInflater layoutInflater;
    private ListView mHuanzheZhusuShijianFenlei;
    private RelativeLayout mLvRl;
    private ArrayList<Object> arrayList = new ArrayList<>();
    private NursingEventTempLateLisBean.DataBean dataBean;
    private XuanJiaoBean.DataBean mXuanJiaodataBean;
    private HuanZheLieBiaoBean.DataBean mHuanZheLieBiaoBean;
    private String edurecordid;
    // 是否为宣教修改
    private boolean isEduUpdate;
    // 宣教修改时用到的服务端数据库的主键
    private int eduRecordPK;

    public ChangGuiHuLiBeiZhu(MyBaseActivity mActivity, LayoutInflater layoutInflater, LinearLayout linearLayout,
                              YiZhuBean.DataBean entitie, NursingEventTempLateLisBean.DataBean dataBean) {
        this.mActivity = mActivity;
        this.entities = entitie;
        this.dataBean = dataBean;
        this.layoutInflater = layoutInflater;
        View view = layoutInflater.inflate(R.layout.shijian_fenlei_include, null);
        linearLayout.removeAllViewsInLayout();
        linearLayout.addView(view);
        mHuanzheZhusuShijianFenlei = (ListView) linearLayout.findViewById(R.id.huanzhe_zhusu_shijian_fenlei);
        mLvRl = (RelativeLayout) linearLayout.findViewById(R.id.lv_rl);
        arrayList.add("历史备注");
        if (entitie.getOrderNoteList() != null && entitie.getOrderNoteList().size() > 0) {
            for (YiZhuBean.OrderNoteListBean orderNoteListBean : entitie.getOrderNoteList()) {
                if (orderNoteListBean != null) {
                    arrayList.add(orderNoteListBean);
                }
            }
        }
        arrayList.add("新增备注");
        if (dataBean != null && dataBean.getNursingEventTemplateDefineList() != null && dataBean.getNursingEventTemplateDefineList().size() > 0) {
            for (NursingEventTempLateLisBean.DataBean.NursingEventTemplateDefineListBean n : dataBean.getNursingEventTemplateDefineList()) {
                if (n != null) {
                    arrayList.add(n);
                }
            }
        }
        arrayList.add("edt");
        yiZhuBeiZhuAdapter = new YiZhuBeiZhuAdapter(mActivity, arrayList);
        mHuanzheZhusuShijianFenlei.setAdapter(yiZhuBeiZhuAdapter);
    }

    // 宣教
    public ChangGuiHuLiBeiZhu(ChangGuiHuLiActivity changGuiHuLiActivity, LayoutInflater layoutInflater
            , LinearLayout mIncludeLl, XuanJiaoBean.DataBean dataBean, HuanZheLieBiaoBean.DataBean mHuanZheLieBiaoBean, String edurecordid) {
        this.mActivity = changGuiHuLiActivity;
        this.mXuanJiaodataBean = dataBean;
        this.mHuanZheLieBiaoBean = mHuanZheLieBiaoBean;
        this.layoutInflater = layoutInflater;
        this.edurecordid = edurecordid;
        View view = layoutInflater.inflate(R.layout.shijian_fenlei_include, null);
        mIncludeLl.removeAllViewsInLayout();
        mIncludeLl.addView(view);
        mHuanzheZhusuShijianFenlei = (ListView) mIncludeLl.findViewById(R.id.huanzhe_zhusu_shijian_fenlei);
        mLvRl = (RelativeLayout) mIncludeLl.findViewById(R.id.lv_rl);
        arrayList.add("1、宣教方式");
        String edumode = dataBean.getEdumode();
        String[] split = edumode.split(",");
        for (int i = 0; i < split.length; i++) {
            arrayList.add("0001" + split[i]);
        }
        arrayList.add("2、结果");
        String eduresult = dataBean.getEduresult();
        String[] split2 = eduresult.split(",");
        for (int i = 0; i < split2.length; i++) {
            arrayList.add("0002" + split2[i]);
        }
        // 数据库查询条件
        String eduDefineId = String.valueOf(dataBean.getId());
        String patientId = mHuanZheLieBiaoBean.getPatientid();
        List<DBXuanJiaoRecord> eduRecords = DataSupport.select("edurecordpk").where("edudefineid = ? and patientid = ?", eduDefineId, patientId).find(DBXuanJiaoRecord.class);
        if (eduRecords != null && eduRecords.size() > 0) {
            isEduUpdate = true;
            eduRecordPK = eduRecords.get(0).getEdurecordpk();
        }
        yiZhuBeiZhuAdapter = new YiZhuBeiZhuAdapter(mActivity, arrayList, eduDefineId, patientId);
        mHuanzheZhusuShijianFenlei.setAdapter(yiZhuBeiZhuAdapter);
    }

    public String getNot() {
        return yiZhuBeiZhuAdapter.getEdt();
    }

    public List<TiJiaoXuanJiao> getTiJiaoJson(XuanJiaoBean.DataBean dataBean, int userid, String userName) {
        String mJson = "";
        Map<Integer, Boolean> booleanMap = yiZhuBeiZhuAdapter.getBooleanMap();
        ArrayList<Object> ls = yiZhuBeiZhuAdapter.getLs();
        Iterator<Integer> iterator = booleanMap.keySet().iterator();
        TiJiaoXuanJiao mTiJiaoXuanJiao = new TiJiaoXuanJiao();
        String str = null;
        String edumodetext = null;
        String eduresulttext = null;
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (booleanMap.get(next)) {
                if (ls.get(next) instanceof String) {
                    str = (String) ls.get(next);
                    if (str.startsWith("0001")) {
                        if (edumodetext == null) {
                            edumodetext = "";
                        }
                        edumodetext = edumodetext + str.replace("0001", "") + ",";
                    } else if (str.startsWith("0002")) {
                        eduresulttext = str.replace("0002", "");
                    }
                }
            }
        }
        if (edumodetext != null && eduresulttext != null) {
            mTiJiaoXuanJiao.setEdudefineid(dataBean.getId());
            edumodetext = edumodetext.substring(0, edumodetext.length() - 1);
            mTiJiaoXuanJiao.setEdumodetext(edumodetext);
            mTiJiaoXuanJiao.setEduresulttext(eduresulttext);
            mTiJiaoXuanJiao.setEdutime(System.currentTimeMillis());
            // 第一次创建的时候，edu_record_pk为0;修改的时候将这个值设置为已有的值
            if (isEduUpdate) {
                mTiJiaoXuanJiao.setEdurecordpk(eduRecordPK);
            } else {
                mTiJiaoXuanJiao.setEdurecordpk(0);
            }
            mTiJiaoXuanJiao.setUserName(userName);
            mTiJiaoXuanJiao.setStatus(1 + "");
            mTiJiaoXuanJiao.setEduuserid(userid);
            // 唯一的标识符，每个病人是一样的
            mTiJiaoXuanJiao.setEdurecordid(edurecordid);
            if (mHuanZheLieBiaoBean != null) {
                mTiJiaoXuanJiao.setPatientid(mHuanZheLieBiaoBean.getPatientid());
            }
            List<TiJiaoXuanJiao> tiJiaoXuanJiaos = new ArrayList<>();
            tiJiaoXuanJiaos.add(mTiJiaoXuanJiao);
            return tiJiaoXuanJiaos;
        } else {
            return null;
        }

    }
}
