package com.ge.med.mobile.nursing.forjson.callback;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.forjson.entity.BaseJSONBean;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.common.util.LogUtil;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Qu on 2016/12/20.
 */
public abstract class BaseNetCallback extends StringCallback {
    @Override
    public void onError(Call call, Exception e, int id) {
        LogUtil.e("There was an error for call [" + call.request().headers().toString() + "], the id is [" + id
                + "], the exception is [" + e.getMessage() + "]");
        preHandleError();
    }

    abstract protected void preHandleError();

    abstract protected void preHandleSuccess(final Object obj);

    abstract protected BaseJSONBean parseResonseString(String response);

    @Override
    public void onResponse(String response, int id) {
        LogUtil.d("Received response from server, msg is [" + (null == response ? "null" : response) + "]");
        boolean isOk = false;
        if (null != response) {
            BaseJSONBean jsonBean = parseResonseString(response);
            isOk = handleJsonObject(jsonBean);
        } else {
            LogUtil.e("Can not get data from server since response message is null!");
        }
        if (!isOk) {
            preHandleError();
        }
    }

    protected boolean handleJsonObject(BaseJSONBean jsonBean) {
        boolean isOk = false;
        if (jsonBean == null || !Constant.NETWORK_MSG_OK.equals(jsonBean.getMsg())) {
            LogUtil.e("Server response null or failed while calling "
                    + (jsonBean == null ? "" : jsonBean.getCallName()) + " from Server!");
        } else if (jsonBean.getData() == null) {
            LogUtil.i("Found return null data after calling " + jsonBean.getCallName() + " from Server!");
        } else if (jsonBean.getData() instanceof List && ((List) jsonBean.getData()).size() <= 0) {
            LogUtil.i("Found empty " + jsonBean.getCallName() + " return list from Server!");
        } else {
            String countStr = "";
            if (jsonBean.getData() instanceof List)
                countStr = ((List) jsonBean.getData()).size() + "";
            LogUtil.d("Successfully got " + jsonBean.getCallName() + " return " + countStr + " data(s) from Server!");
            isOk = true;
            preHandleSuccess(jsonBean.getData());
        }
        return isOk;
    }
}
