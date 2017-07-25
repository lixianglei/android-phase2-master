package com.ge.med.mobile.nursing.ui.adapter;

import android.widget.ImageView;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.YiZhuBean;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Qu on 2016/11/30.
 */
public class AdapterUtil {
    /**
     * find patient care level related image by its level string
     * @param careLevel level string
     * @return image resource identifier
     */
    public static int findCareLevelImage(String careLevel){
        int retval = R.mipmap.icon_three;
        if (careLevel == null) return retval;
        switch (careLevel){
            case Constant.CARE_LEVEL_TEZHI:
                retval = R.mipmap.icon_premium;
                break;
            case Constant.CARE_LEVEL_YIZHI:
                retval = R.mipmap.icon_one;
                break;
            case Constant.CARE_LEVEL_ERZHI:
                retval = R.mipmap.icon_two;
                break;
            case Constant.CARE_LEVEL_SANZHI:
                retval = R.mipmap.icon_three;
                break;
        }
        return retval;
    }
    /**
     * find patient information from local database
     * @param patientid the identifier of patient
     * @return patient bean
     */
    public static DBHuanZheLieBiao getPatientFromDB( String patientid){
        if (null == patientid) return null;
        try {
            List<DBHuanZheLieBiao> dbHuanZheLieBiaos = DataSupport.where("patientid = ?", patientid).find(DBHuanZheLieBiao.class);
            return dbHuanZheLieBiaos.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  find doctor order's image by order type
     * @param orderType the type of doctor order
     * @return the image identifier
     */
    public static int findYZImage(YiZhuBean.DataBean orderType){
        int retval = R.mipmap.icon_injection;
        if(null == orderType){
            return retval;
        }
        switch (orderType.getOrdertype()) {
            case Constant.TYPE_YZ_JINGMAIZHUSHE:
            case Constant.TYPE_YZ_PIXIAZHUSHE:
            case Constant.TYPE_YZ_JIROUZHUSHE:
            case Constant.TYPE_YZ_PISHI:
                retval = R.mipmap.icon_injection;
                break;
            case Constant.TYPE_YZ_SHUYE:
                retval = R.mipmap.icon_iinfusion;
                if(Constant.YZ_TYPE_ZHIXINGZHONG.equals(orderType.getOrderststus())){
                    retval = R.mipmap.zhixingzhong;
                }

                break;
            case Constant.TYPE_YZ_KOUFU:
                retval = R.mipmap.icon_medicine;
                break;
            case Constant.TYPE_YZ_JIANYAN_BLOOD:
            case Constant.TYPE_YZ_JIANYAN_UN_BLOOD:
                retval = R.mipmap.icon_bio;
                break;
            case Constant.TYPE_YZ_SHUXUE:
            case Constant.TYPE_YZ_CAIXUE:
                retval = R.mipmap.icon_blood_transfusion;
                if(Constant.YZ_TYPE_ZHIXINGZHONG.equals(orderType.getOrderststus())){
                    retval = R.mipmap.zhixingzhong;
                }
                break;
            case Constant.TYPE_YZ_ZHILIAO:
            case Constant.TYPE_YZ_HULI:
                retval = R.mipmap.fuzhuzhiliao;
                break;
            case Constant.TYPE_YZ_SHANSHI:
                retval = R.mipmap.shanshi;
                break;
            case Constant.TYPE_YZ_RUHU:
                retval = R.mipmap.ruhu;
                break;
        }
        return retval;
    }

    /**
     * 设置状态图标
     * @param ststus
     * @param hzYzZhuangtaiImgv
     */
    public static   void setZhuangtaiImgv(String ststus, ImageView hzYzZhuangtaiImgv){
        switch (ststus) {
            //未核对
            case Constant.YZ_TYPE_WEIHEDUI:
                break;
            //未执行
            case Constant.YZ_TYPE_WEIZHIXING:
                break;
            //执行中
            case Constant.YZ_TYPE_ZHIXINGZHONG:
                hzYzZhuangtaiImgv.setImageResource(R.mipmap.zhixingzhong);
                break;
            //已执行
            case Constant.YZ_TYPE_YIZHIXING:
                hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_complete);
                break;
            //已暂停
            case Constant.YZ_TYPE_YIZANTING:
                hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_stop);
                break;
            //已取消
            case Constant.YZ_TYPE_YIQUXIAO:
                hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_cancel);
                break;
            //已停止
            case Constant.YZ_TYPE_YITINGZHI:
                hzYzZhuangtaiImgv.setImageResource(R.mipmap.icon_stop);
                break;
            case Constant.YZ_TYPE_FUWENZHONG:
                hzYzZhuangtaiImgv.setImageResource(R.mipmap.fuwen);
                break;
            default:
                break;
        }
    }
}
