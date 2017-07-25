package com.example.sj.library.netJson;

/**
 * Created by Administrator on 2016/7/10.
 */
public abstract class NetCallBack<T> {
    
   public abstract void onSuccess(T entity);
    public abstract void onError(Throwable ex);
    public abstract void onFinish();
}
