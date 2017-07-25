package com.ge.med.mobile.nursing.forjson.callback;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.dao.entity.HuanZheLieBiaoBean;
import com.ge.med.mobile.nursing.dao.impl.HuanZheLieBiaoImpl;
import com.ge.med.mobile.nursing.db.DBHuanZheLieBiao;
import com.zhy.http.okhttp.callback.StringCallback;

import org.litepal.crud.DataSupport;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public abstract class SY_HuanZheLieBiao_CallBack extends StringCallback {
    public List<HuanZheLieBiaoBean.DataBean> data;

    @Override
    public void onResponse(String response, int id) {
        System.out.println("-------------------->患者列表="+response);
        HuanZheLieBiaoBean huanZheLieBiaoBeen = JSON.parseObject(response, HuanZheLieBiaoBean.class);
        data = huanZheLieBiaoBeen.getData();
        if(data!=null&&data.size()>0){
            data = paiXu(data);
            cunShuJu(data);
        }else{
            return;
        }
    }

    private List<HuanZheLieBiaoBean.DataBean> paiXu(List<HuanZheLieBiaoBean.DataBean> data) {
        Comparator<HuanZheLieBiaoBean.DataBean> comparator = new Comparator<HuanZheLieBiaoBean.DataBean>() {
            public int compare(HuanZheLieBiaoBean.DataBean s1, HuanZheLieBiaoBean.DataBean s2) {
                return s1.getBedno() - s2.getBedno();
            }
        };
        Collections.sort(data, comparator);
        return data;
    }
    private void cunShuJu(List<HuanZheLieBiaoBean.DataBean> datas) {
        new HuanZheLieBiaoImpl().savePatients(datas);
    }
}
