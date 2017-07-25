package com.ge.med.mobile.nursing.forjson.syncDifine;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.shareP.SharePLogin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */
public class syncType {
    public List<String> difineTypes(SharePLogin sharePLogin){
        List<String> difineTypeList = new ArrayList<>();
        if(sharePLogin.getCacheAssessDefine()){
            difineTypeList.add(Constant.GLOBAL_KEY_IS_CACHE_ASSESSDEFINE);
        }
//        if(sharePLogin.getTopicDefine()){
//            difineTypeList.add(Constant.GLOBAL_KEY_IS_CACHE_TOPICDEFINE);
//        }
//        if(sharePLogin.getAnswerdefine()){
//            difineTypeList.add(Constant.GLOBAL_KEY_IS_CACHE_ANSWERDEFINE);
//        }
        if(sharePLogin.getVitalDefine()){
            difineTypeList.add(Constant.GLOBAL_KEY_IS_CACHE_VITALDEFINE);
        }
        if(sharePLogin.getExceptionDefine()){
            difineTypeList.add(Constant.GLOBAL_KEY_IS_CACHE_EXCEPTIONDEFINE);
        }
        if(sharePLogin.getRiskDefine()){
            difineTypeList.add(Constant.GLOBAL_KEY_IS_CACHE_RISKDEFINE);
        }
        if(sharePLogin.getNursingEventTemp()){
            difineTypeList.add(Constant.GLOBAL_KEY_IS_CACHE_NUESINGEVENTTEMP);
        }
        if(sharePLogin.getCacheUser()){
            difineTypeList.add(Constant.GLOBAL_KEY_IS_CACHE_USER);
        }
        if(sharePLogin.getXuanJiao()){
            difineTypeList.add(Constant.GLOBAL_KEY_IS_CACHE_XUANJIAO);
        }
        if(sharePLogin.getHouQiCuoShi()){
            difineTypeList.add(Constant.GLOBAL_KEY_IS_CACHE_HOUQI_CUOSHI);
        }
        return difineTypeList;
    }


}
