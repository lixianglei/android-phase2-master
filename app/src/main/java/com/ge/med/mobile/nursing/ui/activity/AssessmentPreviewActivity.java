package com.ge.med.mobile.nursing.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.db.DBAssess;
import com.ge.med.mobile.nursing.db.DBAssessDefine;
import com.ge.med.mobile.nursing.forjson.entity.AnswerBean;
import com.ge.med.mobile.nursing.forjson.entity.AssessRecordBean;
import com.ge.med.mobile.nursing.forjson.entity.QuestionBean;
import com.ge.med.mobile.nursing.forjson.entity.ResultBean;
import com.ge.med.mobile.nursing.ui.adapter.AssessmentPreviewAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评估预览界面
 */
public class AssessmentPreviewActivity extends Activity {

    // 问题集合
    List<QuestionBean> questionList = new ArrayList<>();
    // 答案集合
    List<AnswerBean> answerList = new ArrayList<>();
    // 结果集合
    List<ResultBean> resultList = new ArrayList<>();

    // 评估记录列表
    private ListView lv_previewList;
    // 适配器
    private AssessmentPreviewAdapter assessmentPreviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_preview);
        // 获取从评估列表界面传递而来的值:AssessDefineId
        String assessDefineId = getIntent().getStringExtra("AssessDefineId");
        // 获取从评估列表界面传递而来的值:CreationTime
        String creationTime = getIntent().getStringExtra("CreationTime");

        // 初始化控件
        initView();
        // 获取数据源
        if (assessDefineId != null) {
            calResult(assessDefineId, creationTime);
            assessmentPreviewAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        // 评估记录列表
        lv_previewList = (ListView) findViewById(R.id.lv_previewList);
        assessmentPreviewAdapter = new AssessmentPreviewAdapter(this, resultList);
        lv_previewList.setAdapter(assessmentPreviewAdapter);
    }

    /**
     * 找出问题和答案
     */
    private void calResult(String assessDefineId, String creationTime) {
        String quseStr = null;
        String answStr = null;

        // 获取问题jsonString
        List<DBAssessDefine> assessDefineList = DataSupport.select("jsonstring").where("assessdefineid = ?", assessDefineId).find(DBAssessDefine.class);
        if (assessDefineList.size() > 0) {
            DBAssessDefine entity = assessDefineList.get(0);
            // 获取数据库中存储的jsonString
            quseStr = entity.getJsonString();
        }

        // 获取答案jsonString
        List<DBAssess> assessList = DataSupport.select("jsonstring").where("assessdefineid = ? and creationtime = ?", assessDefineId, creationTime).find(DBAssess.class);
        if (assessList.size() > 0) {
            DBAssess entity = assessList.get(0);
            answStr = entity.getJsonString();
        }

        if (quseStr != null && answStr != null) {
            // 从jonString中取出评估问题
            AssessDefine.DataBean data = JSON.parseObject(quseStr, AssessDefine.DataBean.class);
            String assessType = data.getAssessCode();
            if (!assessType.equals("PAIN_ASSESS") && !assessType.equals("NURSING_ASSESS")
                    && !assessType.equals("ANINGZHONGHE")) { // 非(疼痛评估,入院评估,安宁综合评估)
                List<AssessDefine.DataBean.AssessTopicDefineListBean> topicList = data.getAssessTopicDefineList();
                if (topicList.size() > 0) {
                    for (int i = 0; i < topicList.size(); i++) {
                        // 问题
                        String questionName = topicList.get(i).getName();
                        // 问题对象
                        QuestionBean question = new QuestionBean();
                        // 问题名称
                        question.setQuestionName(questionName);
                        // questionId
                        int questionId = topicList.get(i).getId();
                        question.setQuestionId(String.valueOf(questionId));
                        if (topicList.get(i).getAssessAnswerDefineList().size() == 0) {
                            // 添加到集合中
                            questionList.add(question);
                        } else {
                            Map<String, String> options = new HashMap<>();
                            for (int j = 0; j < topicList.get(i).getAssessAnswerDefineList().size(); j++) {
                                int optionId = topicList.get(i).getAssessAnswerDefineList().get(j).getId();
                                String value = topicList.get(i).getAssessAnswerDefineList().get(j).getValue();
                                options.put(String.valueOf(optionId), value);
                            }
                            question.setOptions(options);
                            // 添加到集合中
                            questionList.add(question);
                        }
                    }
                }
            } else { // 疼痛评估,入院评估,安宁综合评估
                List<AssessDefine.DataBean.AssessTopicDefineListBean> topicList = data.getAssessTopicDefineList();
                if (topicList.size() > 0) {
                    for (int i = 0; i < topicList.size(); i++) {
                        if (topicList.get(i).getRelateTopicList() != null) { // 有子题的情况
                            for (int j = 0; j < topicList.get(i).getRelateTopicList().size(); j++) {
                                // 问题对象
                                QuestionBean question = new QuestionBean();
                                // 问题名称
                                String questionName = topicList.get(i).getRelateTopicList().get(j).getName();
                                // 选项组id
                                int questionId = topicList.get(i).getRelateTopicList().get(j).getId();
                                question.setQuestionName(questionName);
                                question.setQuestionId(String.valueOf(questionId));
                                List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean> list = topicList.get(i).getRelateTopicList().get(j)
                                        .getAssessAnswerDefineList();
                                if (list != null && list.size() > 0) {
                                    Map<String, String> options = new HashMap<>();
                                    for (int k = 0; k < list.size(); k++) {
                                        // 选项id
                                        int optionId = list.get(k).getId();
                                        // 选项值
                                        String value = list.get(k).getValue();
                                        options.put(String.valueOf(optionId), value);
                                    }
                                    question.setOptions(options);
                                    // 添加到集合中
                                    questionList.add(question);
                                }
                            }

                        } else { // 没有子题的情况
                            // 问题
                            String questionName = topicList.get(i).getName();
                            // 问题对象
                            QuestionBean question = new QuestionBean();
                            // 问题名称
                            question.setQuestionName(questionName);
                            // questionId
                            int questionId = topicList.get(i).getId();
                            question.setQuestionId(String.valueOf(questionId));
                            if (topicList.get(i).getAssessAnswerDefineList().size() == 0) {
                                // 添加到集合中
                                questionList.add(question);
                            } else {
                                Map<String, String> options = new HashMap<>();
                                for (int j = 0; j < topicList.get(i).getAssessAnswerDefineList().size(); j++) {
                                    int optionId = topicList.get(i).getAssessAnswerDefineList().get(j).getId();
                                    String value = topicList.get(i).getAssessAnswerDefineList().get(j).getValue();
                                    options.put(String.valueOf(optionId), value);
                                }
                                question.setOptions(options);
                                // 添加到集合中
                                questionList.add(question);
                            }
                        }
                    }
                }
            }


            // 测试输出
            for (int i = 0; i < questionList.size(); i++) {
                if (questionList.get(i).getOptions() == null) {
                    Log.d("weiyi", "name:" + questionList.get(i).getQuestionName() + "   questionId:" + questionList.get(i).getQuestionId());
                } else {
                    Log.d("weiyi", "name:" + questionList.get(i).getQuestionName() + "   questionId:" + questionList.get(i).getQuestionId());
                    Map<String, String> map = questionList.get(i).getOptions();
                    for (Map.Entry<String, String> entity : map.entrySet()) {
                        String optionId = entity.getKey();
                        String value = entity.getValue();
                        Log.d("weiyi", "optionId:" + optionId + "     value:" + value);
                    }
                }
            }

            Log.d("weiyi", "-------------------------------------------------------------------------");

            // 获取答案
            AssessRecordBean data2 = JSON.parseObject(answStr, AssessRecordBean.class);
            List<AssessRecordBean.AssessTopicRecordListBean> recordList = data2.getAssessTopicRecordList();
            if (recordList.size() > 0) {
                for (int i = 0; i < recordList.size(); i++) {
                    // questionId
                    int questionId = recordList.get(i).getTopicDefineId();
                    AnswerBean answer = new AnswerBean();
                    answer.setQuestionId(String.valueOf(questionId));
                    if (!recordList.get(i).getValue().equals("")) { // value有值则直接取value的值
                        // 值
                        String value = recordList.get(i).getValue();
                        answer.setValue(value);
                        // 添加到集合中
                        answerList.add(answer);
                    } else { // 值为空的情况
                        List<AssessRecordBean.AssessTopicRecordListBean.AssessAnswerRecordListBean> list = recordList.get(i).getAssessAnswerRecordList();
                        if (list.size() > 0 && list.get(0).getAnswerDefineId() != null) {
                            String[] optionIds = new String[list.size()];
                            for (int j = 0; j < list.size(); j++) {
                                optionIds[j] = String.valueOf(list.get(j).getAnswerDefineId());
                            }
                            answer.setOptionIds(optionIds);
                            // 添加到集合中
                            answerList.add(answer);
                        } else {
                            // 值
                            String value = recordList.get(i).getValue();
                            answer.setValue(value);
                            // 添加到集合中
                            answerList.add(answer);
                        }
                    }
                }
            }

            // 测试输出
            if (answerList.size() > 0) {
                for (int i = 0; i < answerList.size(); i++) {
                    AnswerBean answer = answerList.get(i);
                    if (answer.getValue() == null) {
                        Log.d("weiyi", "questionId:" + answer.getQuestionId() + "      optionId:" + answer.getOptionIdString());
                    } else {
                        Log.d("weiyi", "questionId:" + answer.getQuestionId() + "      value:" + answer.getValue());
                    }
                }
            }

            Log.d("weiyi", "-------------------------------------------------------------------------");

            // 最后结果
            if (questionList.size() > 0) {
                for (int i = 0; i < questionList.size(); i++) {
                    QuestionBean ques = questionList.get(i);
                    String quesId = ques.getQuestionId();
                    ResultBean result = new ResultBean();
                    result.setQuestion(ques.getQuestionName());
                    // 在答案集合中查找quesId
                    AnswerBean answ = getAnswer(quesId);
                    if (answ == null) {
                        result.setValue("");
                    } else {
                        if (answ.getOptionIds() == null) { // 没有选项id
                            result.setValue(answ.getValue());
                        } else { // 有选项id
                            String[] optionIds = answ.getOptionIds();
                            if (optionIds.length > 0) {
                                String value = "";
                                for (int j = 0; j < optionIds.length; j++) {
                                    // 根据optionId找到optionName
                                    value += getOptionName(optionIds[j], ques);
                                    if (j != optionIds.length - 1) {
                                        value += ",";
                                    }
                                }
                                result.setValue(value);
                            }
                        }
                    }
                    resultList.add(result);
                }
            }

            // 测试输出
            for (int i = 0; i < resultList.size(); i++) {
                ResultBean resu = resultList.get(i);
                Log.d("weiyi", resu.getQuestion() + ":" + resu.getValue());
            }
        }
    }

    /**
     * 根据questionId查找答案
     */
    private AnswerBean getAnswer(String quesId) {
        if (answerList.size() > 0) {
            for (int i = 0; i < answerList.size(); i++) {
                AnswerBean answ = answerList.get(i);
                if (answ.getQuestionId().equals(quesId)) {
                    return answ;
                }
            }
            return null;
        }
        return null;
    }

    /**
     * 根据optionId找到optionName
     */
    private String getOptionName(String optionId, QuestionBean ques) {
        Map<String, String> options = ques.getOptions();
        for (Map.Entry<String, String> entity : options.entrySet()) {
            String opId = entity.getKey();
            if (opId.equals(optionId)) {
                return entity.getValue();
            }
        }
        return "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (questionList != null) {
            questionList = null;
        }

        if (answerList != null) {
            answerList = null;
        }

        if (resultList != null) {
            resultList = null;
        }
    }
}
