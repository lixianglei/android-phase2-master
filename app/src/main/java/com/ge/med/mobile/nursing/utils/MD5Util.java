package com.ge.med.mobile.nursing.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static MessageDigest bmd5 = null;
	
	static{
		try {
			bmd5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
	}
	
    /**
     * 求一个字符串的md5值
     * @param target 字符串
     * @return md5 value
     */
    public static String md5(String target) {
    	if(target == null || target.isEmpty()){
    		return "";
    	}
    	 bmd5.update(target.getBytes());  
         int i;
         StringBuffer buf = new StringBuffer();  
         byte[] b = bmd5.digest();// 加密  
         for (int offset = 0; offset < b.length; offset++) {  
             i = b[offset];  
             if (i < 0) {
            	 i += 256;
             }
             if (i < 16){
            	 buf.append("0");
             }
             buf.append(Integer.toHexString(i));  
         }  
         return buf.toString();
    }
    
    public static void main(String[] args) {
    	System.out.println(md5("1"));
	}
}
