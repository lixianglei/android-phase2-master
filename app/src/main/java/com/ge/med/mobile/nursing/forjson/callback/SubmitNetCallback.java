package com.ge.med.mobile.nursing.forjson.callback;

import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.forjson.entity.BaseJSONBean;

import org.xutils.common.util.LogUtil;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Qu on 2016/12/20.
 */
public abstract class SubmitNetCallback extends BaseNetCallback {
    @Override
    public void onError(Call call, Exception e, int id) {
        LogUtil.e("There was an error for call [" + call.toString() + "], the id is [" + id
                + "], the exception is [" + e.getMessage() + "]");
        preHandleError();
    }

    @Override
    protected boolean handleJsonObject(BaseJSONBean jsonBean) {
        boolean isOk = true;
        if (jsonBean == null || !Constant.NETWORK_MSG_OK.equals(jsonBean.getMsg())) {
            LogUtil.e("Server response null or failed while calling "
                    + (jsonBean == null?"":jsonBean.getCallName()) + " from Server!");
            isOk = false;
        }else if (jsonBean.getData() == null){
            LogUtil.i("Found return null data after calling " + jsonBean.getCallName() + " from Server!");
            preHandleSuccess(null);
        } else if (jsonBean.getData() instanceof List && ((List)jsonBean.getData()).size() <= 0){
            LogUtil.i("Found empty " + jsonBean.getCallName() + " return list from Server!");
            preHandleSuccess(jsonBean.getData());
        } else {
            String countStr = "";
            if (jsonBean.getData() instanceof List) countStr = ((List)jsonBean.getData()).size() + "";
            LogUtil.d("Successfully got " + jsonBean.getCallName() + " return " + countStr + " data(s) from Server!");
            preHandleSuccess(jsonBean.getData());
        }
        return isOk;
    }
}
