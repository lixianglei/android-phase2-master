package com.ge.med.mobile.nursing.forjson.callback;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.dao.entity.User;
import com.ge.med.mobile.nursing.db.DBUser;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;
import org.xutils.common.util.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public abstract class MainUserCallBack extends StringCallback{
    public User user;
    public User.DataBean data;
    @Override
    public void onResponse(String response, int id) {
        user = JSON.parseObject(response, User.class);
        data = user.getData();
        if(data==null){
            return;
        }
        List<DBUser> dbUserList = DataSupport.where("user_id = ?", data.getEmp_no()).find(DBUser.class);
        if (dbUserList != null && dbUserList.size() > 0) {
            DBUser dbUser = new DBUser();
            dbUser.setUser_id(data.getEmp_no());
            dbUser.setUsername(data.getName());
            dbUser.setWard_id(data.getWard_id());
            dbUser.setWardname(data.getWard_name());
            dbUser.setNursing_level(data.getNursing_level());
            dbUser.setWork_year(data.getWork_year());
            dbUser.setZid(data.getId());
            try {
                dbUser.updateAll("user_id = ?", data.getEmp_no());
            } catch (Exception e) {
                LogUtil.e(""+e.getMessage());
            }
        } else {
            DBUser dbUser = new DBUser();
            dbUser.setUser_id(data.getEmp_no());
            dbUser.setUsername(data.getName());
            dbUser.setWard_id(data.getWard_id());
            dbUser.setZid(data.getId());
            dbUser.setWardname(data.getWard_name());
            dbUser.setNursing_level(data.getNursing_level());
            dbUser.setWork_year(data.getWork_year());
            dbUser.save();
        }
    }
}
