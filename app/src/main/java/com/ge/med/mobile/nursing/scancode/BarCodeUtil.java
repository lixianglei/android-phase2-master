package com.ge.med.mobile.nursing.scancode;

/**
 * Created by gs on 2016/12/22.
 */
public class BarCodeUtil {
    /**
     * 判断扫码code是患者条码
     *
     * @param barCode
     * @return
     */
    public static boolean isPatientCode(String barCode){
        if(barCode != null && !barCode.isEmpty()){
            return barCode.startsWith("1");
        }
        return false;
    }

    /**
     * 判断扫码code是医嘱条码
     *
     * @param barCode
     * @return
     */
    public static boolean isOrderCode(String barCode){
        if(barCode != null && !barCode.isEmpty()){
            return barCode.startsWith("2");
        }
        return false;
    }

    /**
     * 判断扫码code是产品号
     *
     * @param barCode
     * @return
     */
    public static boolean isBloodProductNo(String barCode){
        if(barCode != null && !barCode.isEmpty()){
            return barCode.startsWith("3");
        }
        return false;
    }

    /**
     *判断扫码code是检验试管编号
     *
     * @param barCode
     * @return
     */
    public static boolean isCheckProperty(String barCode){
        if(barCode != null && !barCode.isEmpty()){
            return barCode.startsWith("5");
        }
        return false;
    }
    /**
     * 判断扫码code是血袋号
     *
     * @param barCode
     * @return
     */
    public static boolean isBloodPackageNo(String barCode){
        if(barCode != null && !barCode.isEmpty()){
            return barCode.startsWith("4");
        }
        return false;
    }
    /**
     * 判断扫码code是用户
     *
     * @param barCode
     * @return
     */
    public static boolean isUser(String barCode){
        if(barCode != null && !barCode.isEmpty()){
            return barCode.startsWith("a") || barCode.startsWith("A");
        }
        return false;
    }
}
