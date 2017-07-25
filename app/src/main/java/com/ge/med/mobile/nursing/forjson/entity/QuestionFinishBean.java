package com.ge.med.mobile.nursing.forjson.entity;

/**
 * Created by lisa on 2017/6/20.
 * 评估问题完成情况
 */

public class QuestionFinishBean {
    // 已经完成的数量
    private int finishNum;
    // 总共的数量
    private int totalNum;

    public int getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(int finishNum) {
        this.finishNum = finishNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
