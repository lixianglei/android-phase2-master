package com.ge.med.mobile.nursing.ui.component;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.impl.VitalSignDaoImpl;
import com.ge.med.mobile.nursing.forjson.entity.VitalDefineEntity;
import com.ge.med.mobile.nursing.forjson.entity.VitalNoteListEntity;
import com.ge.med.mobile.nursing.forjson.entity.VitalSignWardDefine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/31.
 */
public class TengTongChuLi {
    private int vitalSignWardDefineId;

    public int getVitalSignWardDefineId() {
        return vitalSignWardDefineId;
    }

    public TengTongChuLi() {
        initMap();
    }
    private Map<Integer, String> mTengTongBuWeiName;


    public Map<Integer, String> getmTengTongBuWeiName() {
        return mTengTongBuWeiName;
    }

    //初始化 部位名称对应图片id Map
    private void initMap() {
        mTengTongBuWeiName = new HashMap<>();
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_lian_imgv1, "BZ3");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youjian_imgv2, "BZ7");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_houbu_imgv3, "BZ2");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuojian_imgv4, "BZ19");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youshangbi_imgv5, "BZ10");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_xiongbu_imgv6, "BZ4");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuoshangbi_imgv7, "BZ22");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_fubu_imgv8, "BZ1");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youqianbi_imgv9, "BZ9");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_yinbu_imgv10, "BZ5");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuoqianbi_imgv11, "BZ21");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youshouwan_imgv12, "BZ11");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuoshouwan_imgv13, "BZ23");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youshouzhang_imgv14, "BZ12");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuoshouzhang_imgv15, "BZ24");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youshouzhi_imgv16, "BZ13");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuoshouzhi_imgv17, "BZ25");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youdatui_imgv18, "BZ6");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuodatui_imgv19, "BZ18");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youxi_imgv20, "BZ14");
        mTengTongBuWeiName.put(R.id.tongdian__zheng_zuoxi_imgv21, "BZ26");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youxiaotui_imgv22, "BZ15");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuoxiaotui_imgv23, "BZ27");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youzuhuai_imgv24, "BZ17");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuozuhuai_imgv25, "BZ29");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youzubei_imgv26, "BZ16");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuozubei_imgv27, "BZ28");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_youjiao_imgv28, "BZ8");
        mTengTongBuWeiName.put(R.id.tong_dian_zheng_zuojiao_imgv29, "BZ20");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_tou_imgv1, "BF33");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_jing_imgv2, "BF31");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_bei_imgv3, "BF30");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_zuoshouzhou_imgv4, "BF43");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_yao_imgv5, "BF35");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_youshouzhou_imgv6, "BF38");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_zuoshoubei_imgv7, "BF42");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_tun_imgv8, "BF34");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_youshoubei_imgv9, "BF37");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_rentibei_imgv10, "BF32");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_zuozugen_imgv11, "BF45");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_youzugen_imgv12, "BF40");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_zuozudi_imgv13, "BF44");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_youzudi_imgv14, "BF39");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_zuojiaozhi_imgv15, "BF41");
        mTengTongBuWeiName.put(R.id.tong_dian_bei_youjiaozhi_imgv16, "BF36");
    }

    public ArrayList<String> getTengTongBuWei(TengTongRenTiTuBei mTengTongRenTiTuBei, TengTongRenTiTuZheng mTengTongRenTiTuZheng) {
        List<Integer> imageViewBeiIDs = null, imageViewZhengIDs = null;
        ArrayList<String> mTengTongBuWei;
        if (mTengTongRenTiTuBei != null) {
            imageViewBeiIDs = mTengTongRenTiTuBei.getmImageViewList();
        }
        if (mTengTongRenTiTuZheng != null) {
            imageViewZhengIDs = mTengTongRenTiTuZheng.getmImageViewList();
        }
        mTengTongBuWei = new ArrayList<>();
        if (imageViewBeiIDs != null && mTengTongBuWeiName != null) {
            for (Integer integer : imageViewBeiIDs) {
                mTengTongBuWei.add(mTengTongBuWeiName.get(integer));
            }
        }
        if (imageViewZhengIDs != null && mTengTongBuWeiName != null) {
            for (Integer integer : imageViewZhengIDs) {
                mTengTongBuWei.add(mTengTongBuWeiName.get(integer));
            }
        }
        return mTengTongBuWei;
    }

    public  Map<String,String> getBuWeiMingCheng(List<String> list) {
        Map<String,String> mTengTongBuWeiName = new HashMap<>();
        List<VitalSignWardDefine> allWardDefineFromDB = new VitalSignDaoImpl().findAllWardDefineFromDB();
        VitalDefineEntity vitalDefine = null;
        List<VitalNoteListEntity> vitalNoteList = null;
        if (allWardDefineFromDB != null && allWardDefineFromDB.size() > 0) {
            for (VitalSignWardDefine vitalSignWardDefine : allWardDefineFromDB) {
                if (vitalSignWardDefine != null) {
                    vitalDefine = vitalSignWardDefine.getVitalDefine();
                    if (Constant.VITAL_SIGN_TYPE_PAIN.equals(vitalDefine.getVcode())) {
                        vitalNoteList = vitalDefine.getVitalNoteList();
                         vitalSignWardDefineId = vitalSignWardDefine.getVitaldefineid();
                        break;
                    }
                }
            }
        }
        if (vitalNoteList != null && vitalNoteList.size() > 0) {
            for (VitalNoteListEntity vitalNoteListEntity : vitalNoteList) {
                if (vitalNoteListEntity != null) {
                    for (String s : list) {
                        if (s != null && s.equals(vitalNoteListEntity.getNcode())) {
                            mTengTongBuWeiName.put(s,vitalNoteListEntity.getNote());
                        }
                    }
                }
            }
        }
        return mTengTongBuWeiName;
    }
}
