package com.ge.med.mobile.nursing.ui.component;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.sj.library.base.BaseActivity;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;

import org.xutils.common.util.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */
public class VitalPainKeyboard extends VitalKeyboard implements View.OnClickListener {
    private TextView mVitalSingleTv;
    private Button mBt11;
    private Button mBt12;
    private Button mBt13;
    private Button mBt14;
    private LinearLayout mDiyiPaiBtLl2;
    private Button mBt21;
    private Button mBt22;
    private Button mBt23;
    private Button mBt24;
    private LinearLayout mDiyiPaiBtLl3;
    private Button mBt31;
    private Button mBt32;
    private Button mBt33;
    private Button mBt34;
    private LinearLayout mDiyiPaiBtLl4;
    private Button mBt41;
    private Button mBt42;
    private Button mBt43;
    private ImageButton mBt44;
    private LinearLayout mNumberKeyboardLl;
    private Button mBtFuyao;



    List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean> list;

    public VitalPainKeyboard(BaseActivity activity) {
        super(activity);
        initView();
        mPainKeyboardLayout.setVisibility(View.VISIBLE);
        mNumberKeyboardLl.setVisibility(View.VISIBLE);
        mBtFuyao.setVisibility(View.GONE);
    }

    public VitalPainKeyboard(BaseActivity activity, List<AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean> list) {
        super(activity);
        this.list = list;
        initView();
        mPainKeyboardLayout.setVisibility(View.VISIBLE);
        mNumberKeyboardLl.setVisibility(View.VISIBLE);
        mBtFuyao.setVisibility(View.GONE);
    }
    public int getTengTongJiBieId() {
        String edtText = mVitalSingleTv.getText().toString();
        if(edtText.length() == 1){
            edtText="T00"+edtText;
        }else if(edtText.length() == 2){
            edtText="T0"+edtText;
        }
        int mTengTongJiBieId = -1;
        if (list != null) {
            for (AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean assessAnswerDefineListBean : list) {
                if (edtText.equals(assessAnswerDefineListBean.getAnswerDefineCode())) {
                    mTengTongJiBieId = assessAnswerDefineListBean.getId();
                }
            }
        }
        return mTengTongJiBieId;
    }

    public void setTengTongJiBie(int id) {
        if(list!=null){
            for (AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean assessAnswerDefineListBean : list) {
                if(assessAnswerDefineListBean !=null){
                    if (id == assessAnswerDefineListBean.getId()) {
                        if(assessAnswerDefineListBean.getAnswerDefineCode()!=null){
                            try {
                                mVitalSingleTv.setText(Integer.parseInt(assessAnswerDefineListBean.getAnswerDefineCode().replace("T",""))+"");
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        if (activity == null) {
            LogUtil.e("Activity can not be null!");
            return;
        }
        mVitalSingleTv = (TextView) activity.findViewById(R.id.vital_pain_tv);
        mNumberKeyboardLl = (LinearLayout) activity.findViewById(R.id.pain_number_keyboard_ll);
        mDiyiPaiBtLl2 = (LinearLayout) activity.findViewById(R.id.row_bt_ll_2);
         mBt21 = (Button)activity.findViewById(R.id.bt_pain_2_1);
         mBt22 = (Button)activity.findViewById(R.id.bt_pain_2_2);
         mBt23 = (Button)activity.findViewById(R.id.bt_pain_2_3);
         mBt24 = (Button)activity.findViewById(R.id.bt_pain_2_4);
        mDiyiPaiBtLl3 = (LinearLayout) activity.findViewById(R.id.row_bt_ll_3);
         mBt31 = (Button)activity.findViewById(R.id.bt_pain_3_1);
         mBt32 = (Button)activity.findViewById(R.id.bt_pain_3_2);
         mBt33 = (Button)activity.findViewById(R.id.bt_pain_3_3);
         mBt34 = (Button)activity.findViewById(R.id.bt_pain_3_4);
        mDiyiPaiBtLl4 = (LinearLayout) activity.findViewById(R.id.row_bt_ll_4);
        mBt41 = (Button) activity.findViewById(R.id.bt_pain_4_1);
        mBt42 = (Button)activity. findViewById(R.id.bt_pain_4_2);
        mBt43 = (Button) activity.findViewById(R.id.bt_pain_4_3);
        mBt44 = (ImageButton) activity.findViewById(R.id.bt_pain_4_4);
        mBtFuyao = (Button) activity.findViewById(R.id.bt_fuyao);


        mBt21.setOnClickListener(this);
        mBt22.setOnClickListener(this);
        mBt23.setOnClickListener(this);
        mBt24.setOnClickListener(this);
        mBt31.setOnClickListener(this);
        mBt32.setOnClickListener(this);
        mBt33.setOnClickListener(this);
        mBt34.setOnClickListener(this);
        mBt41.setOnClickListener(this);
        mBt42.setOnClickListener(this);
        mBt43.setOnClickListener(this);
        mBt44.setOnClickListener(this);

        mBt24.setText("0");
        mBt34.setText("10");
    }
    public void setJiBieText(String text){
        if(text == null){
            mVitalSingleTv.setText("");
        }else{
            mVitalSingleTv.setText(text);
        }
    }
    public String  getJiBieText(){
       return mVitalSingleTv.getText().toString();
    }
    @Override
    public void onClick(View v) {
        String vitalValue = mVitalSingleTv.getText().toString();
        String vitalNote = "";
        String vitalHandle = "";
        switch (v.getId()) {
            //体征结果值
            case R.id.bt_pain_2_1:
                vitalValue = mBt21.getText().toString();
                break;
            //体征结果值
            case R.id.bt_pain_2_2:
                vitalValue = mBt22.getText().toString();
                break;
            //体征结果值
            case R.id.bt_pain_2_3:
                vitalValue = mBt23.getText().toString();
                break;
            //体征结果值
            case R.id.bt_pain_2_4:
                vitalValue = mBt24.getText().toString();
                break;
            //体征结果值
            case R.id.bt_pain_3_1:
                vitalValue = mBt31.getText().toString();
                break;
            //体征结果值
            case R.id.bt_pain_3_2:
                vitalValue = mBt32.getText().toString();
                break;
            //体征结果值
            case R.id.bt_pain_3_3:
                vitalValue = mBt33.getText().toString();
                break;
            //体征结果值
            case R.id.bt_pain_3_4:
                vitalValue = mBt34.getText().toString();
                break;
            //体征结果值
            case R.id.bt_pain_4_1:
                vitalValue = mBt41.getText().toString();
                break;
            //体征结果值
            case R.id.bt_pain_4_2:
                vitalValue = mBt42.getText().toString();
                break;
            //体征结果值
            case R.id.bt_pain_4_3:
                vitalValue = mBt43.getText().toString();
                break;
            //体征结果值
            case R.id.bt_pain_4_4:
                vitalValue = mVitalSingleTv.getText().toString();
                if (vitalValue != null && vitalValue.length() != 0) {
                    vitalValue = vitalValue.substring(0, vitalValue.length() - 1);
                    break;
                }
                if (vitalValue.length() == 1) {
                    vitalValue = "";
                }
                break;
        }
        mVitalSingleTv.setText(vitalValue);
        if (vitalRecord != null){
            vitalRecord.setSignvalue(vitalValue);
            vitalRecord.setNote(vitalNote);
            vitalRecord.setHandlemeasure(vitalHandle);
        }
    }

    @Override
    public void initKeyboard() {
        if (mPainKeyboardLayout != null) mPainKeyboardLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void displayVitalData() {
        if (vitalRecord != null){
            mVitalSingleTv.setText(vitalRecord.getSignvalue());
            //vitalRecord.setHandlemeasure(vitalHandle);
        }
    }
}
