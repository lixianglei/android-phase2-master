package com.ge.med.mobile.nursing.forjson.entity;

import java.util.Map;

/**
 * Created by Lisa on 2017/6/17.
 * 评估问题
 */

public class QuestionBean {
    private String questionName;
    private String questionId;
    private Map<String, String> options;

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }
}
