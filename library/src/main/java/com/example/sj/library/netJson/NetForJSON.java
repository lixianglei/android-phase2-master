package com.example.sj.library.netJson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/10.
 */
public class NetForJSON implements Callback.CommonCallback<String> {
    
    private String mUrl;
    private boolean mIsPost;
    private Type mEntityClass;
    private NetCallBack mNetCallBack;
    private RequestParams mRequestParams;
    private  Cancelable mCancelable;
    private Map<String,Object> mCookies=new HashMap<>();
    
    public NetForJSON(String url, boolean isPost) {
        //确保不论程序传入的URL最后带不带/，我们都统一处理成不带/
        if (url.endsWith("/")) {
            this.mUrl = url.substring(0, url.length() - 1);
        } else {
            this.mUrl = url;
        }
        mRequestParams = new RequestParams(url);
        this.mIsPost = isPost;
    }
    
    public NetForJSON(String url) {
        this(url,false);
    }
    
    public NetForJSON(String url, NetCallBack netCallBack, boolean isPost) {
        //确保不论程序传入的URL最后带不带/，我们都统一处理成不带/
        if (url.endsWith("/")) {
            this.mUrl = url.substring(0, url.length() - 1);
        } else {
            this.mUrl = url;
        }
        mRequestParams = new RequestParams(url);
        mRequestParams.setCancelFast(true);
        this.mIsPost = isPost;
        mNetCallBack = netCallBack;
        //根据泛型获取Type类型，从而省略传入解析类型参数
        if (mNetCallBack!=null){
            ParameterizedType parameterizedType = (ParameterizedType) mNetCallBack
                    .getClass().getGenericSuperclass();
            mEntityClass = parameterizedType.getActualTypeArguments()[0];
        }
       
        
    }
    
    public void cancel(){
        if (mCancelable!=null){
            mCancelable.cancel();
        }
      
    }
    
    public NetForJSON(String url, NetCallBack netCallBack) {
        this(url, netCallBack, false);
    }
    
    //添加参数
    public void addParams(String key, Object value) {
        if (mIsPost) {
            mRequestParams.addBodyParameter(key, String.valueOf(value));
        } else {
            mRequestParams.addParameter(key, value);
        }
        mRequestParams.addHeader("User-Agent", "www.gs.com");
    }
    
    //移除参数
    public void removeParams(String... keys) {
        if (keys != null && keys.length != 0) {
            for (String key : keys) {
                mRequestParams.removeParameter(key);
            }
        }
    }
    
    //替换参数
    public void replaceParams(String key, Object newValue) {
        removeParams(key);
        addParams(key, newValue);
    }
    
    //添加Cookie
    public void addCookie(String key,Object value){
        mCookies.put(key,value);
    }
    //移除Cookie
    public void removeCookie(String key){
        mCookies.remove(key);
    }
    //修改Cookie
    public void replaceCookie(String key,Object newValue){
        mCookies.put(key,newValue);
    }
    //执行网络访问
    public void execute() {
        if (mCookies!=null&&mCookies.size()!=0){
            mRequestParams.setUseCookie(true);
            StringBuilder cs=new StringBuilder();
            Set<String> keys = mCookies.keySet();
            boolean isFirst=true;
            for (String key:keys){
                Object value=mCookies.get(key);
                if (!isFirst){
                    cs.append("; ");
                }
                isFirst=false;
                cs.append(key+"="+value);
              
                
            }
            mRequestParams.setHeader("Cookie",cs.toString());
        }
        mRequestParams.setCacheMaxAge(1);
        if (mIsPost) {
            doPost();
        } else {
            doGet();
        }
    }
    
    public  String executeSync(){
        String jsonStr=null;
        if (mCookies.size()!=0){
            mRequestParams.setUseCookie(true);
            StringBuilder cs=new StringBuilder();
            Set<String> keys = mCookies.keySet();
            boolean isFirst=true;
            for (String key:keys){
                Object value=mCookies.get(key);
                if (!isFirst){
                    cs.append("; ");
                }
                isFirst=false;
                cs.append(key+"="+value);
            
            
            }
            mRequestParams.setHeader("Cookie",cs.toString());
        }
        if (mIsPost) {
            try {
                jsonStr=  x.http().postSync(mRequestParams,String.class);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }else {
            try {
                jsonStr=  x.http().getSync(mRequestParams,String.class);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return jsonStr;
    }
    //执行网络访问---by get
    private void doGet() {
        mCancelable=  x.http().get(mRequestParams, this);
    }
    
    //执行网络访问---by post
    private void doPost() {

        mCancelable= x.http().post(mRequestParams, this);
    }
    
    @Override
    public void onSuccess(String result) {
        System.out.println("NetForJSON.onSuccess"+"result="+result);
        LogUtil.e("返回的JSON数据--->"+result);
        if (mNetCallBack!=null){

            mNetCallBack.onSuccess(result);
        }

    }
    
    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        ex.printStackTrace();
        if (mNetCallBack!=null){
            mNetCallBack.onError(ex);
        }
      
    }
    
    @Override
    public void onCancelled(CancelledException cex) {
        
    }
    
    @Override
    public void onFinished() {
        if (mNetCallBack!=null){
            mNetCallBack.onFinish();
        }
     
    }
}
