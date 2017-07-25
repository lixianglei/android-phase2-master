package com.ge.med.mobile.nursing.forjson.entity;

import java.util.List;

/**
 * Created by Qu on 2016/12/9.
 */
public class ResponseSubmitAssessSignle implements BaseJSONBean {

    /**
     * status : 1
     * data : {"assessId":117,"assessResult":"跌倒"}
     * msg : 成功
     */
    private Integer status;
    private List<DataEntity> data;
    private String msg;

    @Override
    public Integer getStatus() {
        return status;
    }
@Override
    public List<DataEntity> getData() {
        return data;
    }
@Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getCallName() {
        return "Submit Assess";
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public class DataEntity {
        /**
         * assessId : 117
         * assessResult : 跌倒
         */
        private int assessId;
        private String assessResult;

        public void setAssessId(int assessId) {
            this.assessId = assessId;
        }

        public void setAssessResult(String assessResult) {
            this.assessResult = assessResult;
        }

        public int getAssessId() {
            return assessId;
        }

        public String getAssessResult() {
            return assessResult;
        }
    }
}
