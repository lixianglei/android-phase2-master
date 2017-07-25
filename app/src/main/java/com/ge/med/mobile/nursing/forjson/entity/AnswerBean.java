package com.ge.med.mobile.nursing.forjson.entity;

/**
 * Created by Lisa on 2017/6/18.
 * 评估问题的答案
 */

public class AnswerBean {
    private String questionId;
    private String[] optionIds;
    private String value;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String[] getOptionIds() {
        return optionIds;
    }

    public void setOptionIds(String[] optionIds) {
        this.optionIds = optionIds;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOptionIdString() {
        String result = "";
        for (int i = 0; i < optionIds.length; i++) {
            result += optionIds[i];
            if (i != optionIds.length - 1) {
                result += ",";
            }
        }
        return result;
    }
}
