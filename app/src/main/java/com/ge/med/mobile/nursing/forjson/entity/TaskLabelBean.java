package com.ge.med.mobile.nursing.forjson.entity;

import java.util.List;

/**
 * Created by lisa on 2017/7/21.
 * 评估/体征标签
 */

public class TaskLabelBean {
    private int status;
    private List<DataBean> data;

    public class DataBean {
        // 标签名称
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
}
