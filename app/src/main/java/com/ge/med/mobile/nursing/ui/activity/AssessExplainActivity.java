package com.ge.med.mobile.nursing.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.ui.adapter.AssessExplainAdapter;

import java.util.List;

/**
 * 压疮评估说明界面
 */
public class AssessExplainActivity extends Activity {

    // 列表
    private ListView lv_explain;
    // 适配器
    private AssessExplainAdapter assessExplainAdapter;
    // 数据源
    List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assess_explain);
        datas = (List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean>) getIntent().getSerializableExtra("explain");

        // 初始化相关控件
        initView();
    }

    /**
     * 初始化相关控件
     */
    private void initView() {
        lv_explain = (ListView) findViewById(R.id.lv_explain);
        if (datas != null) {
            assessExplainAdapter = new AssessExplainAdapter(this, datas);
            lv_explain.setAdapter(assessExplainAdapter);
        }
    }
}
