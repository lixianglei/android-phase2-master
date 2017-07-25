package com.ge.med.mobile.nursing.forjson.callback;

/**
 * Created by Alex Qu on 2016/12/4.
 */
public interface INetworkHandler {
    public void handleOnError();
    public void handleOnError(String urlStr);
    public void handleSuccess(Object obj);
}
