package com.ge.med.mobile.nursing.forjson.callback;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.dao.entity.UserListBean;
import com.ge.med.mobile.nursing.db.DBUserList;
import com.ge.med.mobile.nursing.forjson.syncDifine.CacheDefine;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;

import java.util.List;

import okhttp3.Call;

/**
 * Created by xxl on 2016/12/14.
 */
public class UserListCallBack extends StringCallback {
    @Override
    public void onError(Call call, Exception e, int id) {
        CacheDefine.showDialog();
        e.printStackTrace();
    }

    @Override
    public void onResponse(String response, int id) {
        UserListBean userListBean = JSON.parseObject(response, UserListBean.class);
        if (userListBean != null) {
            final List<UserListBean.DataBean> datas = userListBean.getData();
            if (datas != null && datas.size() > 0) {
                synchronized (String.class) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            DBUserList dbUserList ;
                            List<DBUserList> dbUserLists ;
                            for (UserListBean.DataBean data : datas) {
                                dbUserLists = DataSupport.where("zid = ?", data.getId() + "").find(DBUserList.class);
                                dbUserList = new DBUserList();
                                dbUserList.setEmpno(data.getEmpno());
                                dbUserList.setName(data.getName());
                                dbUserList.setZid(data.getId());
                                if(dbUserLists!=null&&dbUserLists.size()>0){
                                    dbUserList.updateAll("zid=?", data.getId() + "");
                                }else{
                                    dbUserList.save();
                                }
                            }
                            CacheDefine.changeStatus(Constant.GLOBAL_KEY_IS_CACHE_USER,false);
                        }
                    }.start();
                }
            }
        }
    }
}
