package com.ge.med.mobile.nursing.ui.component;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.sj.library.base.BaseActivity;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.R;
import com.ge.med.mobile.nursing.forjson.entity.VitalNoteListEntity;
import com.ge.med.mobile.nursing.ui.adapter.MoreNotesItemAdapter;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Qu on 2017/4/7.
 * 体征键盘
 */

public abstract class VitalNumberKeyboard extends VitalKeyboard implements View.OnClickListener {

    private Button mFastButton1;
    private Button mFastButton2;
    private Button mFastButton3;
    private Button mFastButton4;

    private Button mNumberButton1;
    private Button mNumberButton2;
    private Button mNumberButton3;
    private Button mDotButton;
    private Button mNumberButton4;
    private Button mNumberButton5;
    private Button mNumberButton6;
    private Button mNumberButton0;
    private Button mNumberButton7;
    private Button mNumberButton8;
    private Button mNumberButton9;
    private ImageButton mBackButton;
    protected RadioGroup mNoteButtonGroup;
    protected RadioButton mNoteButton1;
    protected RadioButton mNoteButton2;
    protected RadioButton mNoteButton3;
    protected CheckBox[] handleButtons;
    protected LinearLayout mHandleButtonGroup;
    protected CheckBox mHandleButton1;
    protected CheckBox mHandleButton2;
    protected RadioButton[] noteButtons;
    protected Button[] fastButtons;
    protected TextView mMoreNotesTextView;
    protected MoreNotesItemAdapter mMoreNotesAdapter;

    public VitalNumberKeyboard(BaseActivity activity) {
        super(activity);
        loadControls();
    }

    @Override
    public void initKeyboard() {
        if (activity == null) {
            LogUtil.e("Activity can not be null!");
            return;
        }
        resetRadioButton();
        bindVitalDefine();
    }

    protected void resetRadioButton() {
        mNoteButtonGroup.clearCheck();

        for (RadioButton bt : noteButtons) {
            bt.setText("");
            bt.setEnabled(false);
            bt.setTextColor(Color.BLACK);
        }
        for (CheckBox bt : handleButtons) {
            bt.setChecked(false);
            bt.setText("");
            bt.setEnabled(false);
            bt.setTextColor(Color.BLACK);
            bt.setBackgroundColor(ContextCompat.getColor(activity, R.color.see8e8e8));
        }
        for (Button bt : fastButtons) {
            bt.setText("");
        }
    }

    /**
     * 将体征定义里的配置显示在控件上
     */
    protected void bindVitalDefine() {
        if (vitalDefine != null && vitalDefine.getVitalNoteList() != null) {
            int iNoteIndex = 0, iHandleIndex = 0;
            List<String> lstMoreNotes = new ArrayList<String>();
            String displayName = null;
            for (VitalNoteListEntity vNote : vitalDefine.getVitalNoteList()) {
                if (vNote != null) {
                    if (vNote.getIshandlemeasure() != null && vNote.getIshandlemeasure().intValue() == Constant.VITAL_NOTE_TYPE_HANDLE) {
                        if (iHandleIndex >= handleButtons.length) continue;
                        if (vNote.getAndroidshowname() == null || vNote.getAndroidshowname().isEmpty())
                            handleButtons[iHandleIndex].setText(vNote.getNote());
                        else handleButtons[iHandleIndex].setText(vNote.getAndroidshowname());
                        handleButtons[iHandleIndex].setEnabled(true);
                        handleButtons[iHandleIndex].setTextColor(Color.BLACK);
                        iHandleIndex++;
                    } else { //如果不是Handle类的note
                        // 获取合适的note button显示名称
                        if (vNote.getAndroidshowname() == null || vNote.getAndroidshowname().isEmpty())
                            displayName = vNote.getNote();
                        else displayName = vNote.getAndroidshowname();
                        // note数量超过外部note button的数量则忽略处理
                        if (iNoteIndex >= noteButtons.length) {
                            lstMoreNotes.add(displayName);
                            continue;
                        }
                        noteButtons[iNoteIndex].setText(displayName);
                        noteButtons[iNoteIndex].setEnabled(true); // enable当前位置的note button
                        if (vitalDefine.getDefaultvitalnoteid() != null && vNote.getId() != null
                                && vNote.getId().intValue() == vitalDefine.getDefaultvitalnoteid().intValue()) { //设置默认值
                            // 设置note button状态
                            noteButtons[iNoteIndex].setTextColor(Color.WHITE);
                            noteButtons[iNoteIndex].setChecked(true);
                            noteButtons[iNoteIndex].setSelected(true);
                            // 设置默认值，在绑定VitalRecord方法中使用
                            defaultVitalNote = vNote.getNote();
                        } else {
                            noteButtons[iNoteIndex].setTextColor(Color.BLACK);
                            noteButtons[iNoteIndex].setChecked(false);
                            noteButtons[iNoteIndex].setSelected(false);
                        }
                        iNoteIndex++;
                    }
                }
            }
            if (lstMoreNotes.size() > 0) { //如果note外部button显示不下则激活 more note按钮
                mMoreNotesAdapter.setData(lstMoreNotes);
                mMoreNotesAdapter.notifyDataSetChanged();
                mMoreNotesTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    ;

    /**
     * 查找控件并初始化
     */
    private void loadControls() {
        if (mNumberKeyboardLayout != null) mNumberKeyboardLayout.setVisibility(View.VISIBLE);
        mFastButton1 = (Button) activity.findViewById(R.id.bt_1_1);
        mFastButton2 = (Button) activity.findViewById(R.id.bt_1_2);
        mFastButton3 = (Button) activity.findViewById(R.id.bt_1_3);
        mFastButton4 = (Button) activity.findViewById(R.id.bt_1_4);
        mNumberButton1 = (Button) activity.findViewById(R.id.bt_2_1);
        mNumberButton2 = (Button) activity.findViewById(R.id.bt_2_2);
        mNumberButton3 = (Button) activity.findViewById(R.id.bt_2_3);
        mDotButton = (Button) activity.findViewById(R.id.bt_2_4);
        mNumberButton4 = (Button) activity.findViewById(R.id.bt_3_1);
        mNumberButton5 = (Button) activity.findViewById(R.id.bt_3_2);
        mNumberButton6 = (Button) activity.findViewById(R.id.bt_3_3);
        mNumberButton0 = (Button) activity.findViewById(R.id.bt_3_4);
        mNumberButton7 = (Button) activity.findViewById(R.id.bt_4_1);
        mNumberButton8 = (Button) activity.findViewById(R.id.bt_4_2);
        mNumberButton9 = (Button) activity.findViewById(R.id.bt_4_3);
        mBackButton = (ImageButton) activity.findViewById(R.id.bt_4_4);
        mNoteButtonGroup = (RadioGroup) activity.findViewById(R.id.diyi_pai_bt_ll_5);
        mNoteButton1 = (RadioButton) activity.findViewById(R.id.bt_5_1);
        mNoteButton2 = (RadioButton) activity.findViewById(R.id.bt_5_2);
        mNoteButton3 = (RadioButton) activity.findViewById(R.id.bt_5_3);
        mHandleButtonGroup = (LinearLayout) activity.findViewById(R.id.vital_keyboard_handle_note_group);
        mHandleButton1 = (CheckBox) activity.findViewById(R.id.handle_note_1);
        mHandleButton2 = (CheckBox) activity.findViewById(R.id.handle_note_2);
        handleButtons = new CheckBox[]{mHandleButton1, mHandleButton2};
        noteButtons = new RadioButton[]{mNoteButton1, mNoteButton2, mNoteButton3};
        fastButtons = new Button[]{mFastButton1, mFastButton2, mFastButton3, mFastButton4};
        for (RadioButton bt : noteButtons) {
            bt.setText("");
            bt.setEnabled(false);
            bt.setTextColor(Color.BLACK);
        }
        for (CheckBox bt : handleButtons) {
            bt.setText("");
            bt.setEnabled(false);
            bt.setTextColor(Color.BLACK);
        }
        for (Button bt : fastButtons) {
            bt.setText("");
        }
        mFastButton1.setOnClickListener(this);
        mFastButton2.setOnClickListener(this);
        mFastButton3.setOnClickListener(this);
        mFastButton4.setOnClickListener(this);
        mNumberButton1.setOnClickListener(this);
        mNumberButton2.setOnClickListener(this);
        mNumberButton3.setOnClickListener(this);
        mDotButton.setOnClickListener(this);
        mNumberButton4.setOnClickListener(this);
        mNumberButton5.setOnClickListener(this);
        mNumberButton6.setOnClickListener(this);
        mNumberButton0.setOnClickListener(this);
        mNumberButton7.setOnClickListener(this);
        mNumberButton8.setOnClickListener(this);
        mNumberButton9.setOnClickListener(this);
        mBackButton.setOnClickListener(this);
        mNoteButton1.setOnClickListener(this);
        mNoteButton2.setOnClickListener(this);
        mNoteButton3.setOnClickListener(this);
        mHandleButton1.setOnClickListener(this);
        mHandleButton2.setOnClickListener(this);

        mNoteButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mNoteButton1.setTextColor(Color.BLACK);
                mNoteButton2.setTextColor(Color.BLACK);
                mNoteButton3.setTextColor(Color.BLACK);
                switch (checkedId) {
                    //体征备注1
                    case R.id.bt_5_1:
                        mNoteButton1.setTextColor(Color.WHITE);
                        break;
                    //体征备注2
                    case R.id.bt_5_2:
                        mNoteButton2.setTextColor(Color.WHITE);
                        break;
                    //体征备注3
                    case R.id.bt_5_3:
                        mNoteButton3.setTextColor(Color.WHITE);
                        break;
                }
            }
        });

        mMoreNotesTextView = (TextView) activity.findViewById(R.id.other_note_tv);
        if (mMoreNotesTextView != null) {
            mMoreNotesTextView.setVisibility(View.GONE);
            mMoreNotesTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMoreNotesListView != null) {
                        mMoreNotesListView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
        if (mMoreNotesListView != null) {
            mMoreNotesAdapter = new MoreNotesItemAdapter(activity, new ArrayList<String>());
            mMoreNotesListView.setAdapter(mMoreNotesAdapter);
            mMoreNotesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mMoreNotesListView != null) {
                        mMoreNotesListView.setVisibility(View.GONE);
                        if (mMoreNotesAdapter != null) {
                            setNoteButton(mMoreNotesAdapter.getItem(position));
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        String vitalValue = getVitalValue();
        String vitalNote = getVitalNoteValue();
        String vitalHandle = getVitalHandleMeasure();
        LogUtil.d("VitalNumberKeyboard.onClick calling...vital value is [" + vitalValue
                + "], vital note is [" + vitalNote + "], vital handle measure is [" + vitalHandle + "]");

        switch (v.getId()) {
            // 切换键盘
            case R.id.bt_1_4:
                vitalValue = switchButtonClick(vitalValue, (TextView) v);
                break;
            // 退格键
            case R.id.bt_4_4:
                if (vitalValue != null && vitalValue.length() != 0) {
                    vitalValue = vitalValue.substring(0, vitalValue.length() - 1);
                    break;
                }
                if (vitalValue.length() == 1) {
                    vitalValue = "";
                }
                break;
            // 备注按钮
            case R.id.bt_5_1:
            case R.id.bt_5_2:
            case R.id.bt_5_3:
                setNoteButton(((TextView) v).getText().toString());
                vitalNote = vitalRecord == null ? "" : (vitalRecord.getNote() == null ? "" : vitalRecord.getNote());
                break;
            // 措施按钮
            case R.id.handle_note_1:
                if (mHandleButton1.isChecked()) {
                    // 判断另个控件是否选中，如果另一个选中了，则不能选中
                    if (mHandleButton2.isChecked()) {
                        mHandleButton1.setChecked(false);
                        mHandleButton1.setTextColor(Color.BLACK);
                        mHandleButton1.setBackgroundColor(ContextCompat.getColor(activity, R.color.see8e8e8));
                    } else {
                        mHandleButton1.setTextColor(Color.WHITE);
                        mHandleButton1.setBackgroundColor(ContextCompat.getColor(activity, R.color.se559bec));
                    }

                } else {
                    mHandleButton1.setTextColor(Color.BLACK);
                    mHandleButton1.setBackgroundColor(ContextCompat.getColor(activity, R.color.see8e8e8));
                }
                vitalHandle = ((TextView) v).getText().toString();
                break;
            case R.id.handle_note_2:
                if (mHandleButton2.isChecked()) {
                    if (mHandleButton1.isChecked()) {
                        mHandleButton2.setChecked(false);
                        mHandleButton2.setTextColor(Color.BLACK);
                        mHandleButton2.setBackgroundColor(ContextCompat.getColor(activity, R.color.see8e8e8));
                    } else {
                        mHandleButton2.setTextColor(Color.WHITE);
                        mHandleButton2.setBackgroundColor(ContextCompat.getColor(activity, R.color.se559bec));
                    }
                } else {
                    mHandleButton2.setTextColor(Color.BLACK);
                    mHandleButton2.setBackgroundColor(ContextCompat.getColor(activity, R.color.see8e8e8));
                }
                vitalHandle = ((TextView) v).getText().toString();
                break;
            default:
                vitalValue = vitalValue + ((TextView) v).getText().toString();
                break;
        }
        vitalValue = checkVitalValue(vitalValue);
        setVitalDisplayTextView(vitalValue);
        LogUtil.d("VitalNumberKeyboard.onClick>Set set Vital Display TextView to vitalValue["
                + (vitalValue == null ? "null" : vitalValue) + "], vital note is [" + vitalNote + "], vital handle measure is [" + vitalHandle + "]");
        addOrUpdateVitalData(vitalValue, vitalNote, vitalHandle);
    }

    protected abstract String switchButtonClick(String vitalValue, TextView view);

    protected abstract String getVitalValue();

    protected abstract void setVitalDisplayTextView(String vitalValue);

    protected String getVitalNoteValue() {
        if (vitalRecord != null) return vitalRecord.getNote();
        return "";
    }

    protected String getVitalHandleMeasure() {
        if (vitalRecord != null) return vitalRecord.getHandlemeasure();
        return "";
    }

    @Override
    protected void displayVitalData() {
        LogUtil.d("VitalNumberKeyboard.displayVitalData calling... vitalRecord is [type:"
                + (vitalRecord == null ? "null" : vitalRecord.getSigntype()) + ",handle measure:"
                + (vitalRecord == null ? "null" : vitalRecord.getHandlemeasure())
                + ",note:" + (vitalRecord == null ? "null" : vitalRecord.getNote()) + "]");
        if (vitalRecord != null) {
            // 设置以前选择的note
            setNoteButton(vitalRecord.getNote());
            if (vitalRecord.getHandlemeasure() != null) {
                for (CheckBox bt : handleButtons) { // 设置以前选择的handleMeasure
                    if (vitalRecord.getHandlemeasure().equals(bt.getText())) {
                        bt.setTextColor(Color.WHITE);
                        bt.setChecked(true);
                        bt.setBackgroundColor(ContextCompat.getColor(activity, R.color.se559bec));
                    } else {
                        bt.setTextColor(Color.BLACK);
                        bt.setChecked(false);
                        bt.setBackgroundColor(ContextCompat.getColor(activity, R.color.see8e8e8));
                    }
                }
            }
        }
    }

    private void setRealNote(String note) {
        if (note == null || vitalDefine == null) {
            LogUtil.e("Note[" + (note == null ? "null" : note) + "] or vitalDefine[" + (vitalDefine == null ? "null" : vitalDefine.getSignname()) + "] is null!");
        } else {
            if (vitalDefine.getVitalNoteList() == null || vitalDefine.getVitalNoteList().size() <= 0) {
                LogUtil.e("VitalDefine[" + vitalDefine.getSignname() + "] does not have any note define !");
            } else {
                for (VitalNoteListEntity noteDefine : vitalDefine.getVitalNoteList()) {
                    if (noteDefine != null) {
                        if (note.equals(noteDefine.getAndroidshowname()) || note.equals(noteDefine.getNote())) {
                            if (vitalRecord != null) vitalRecord.setNote(noteDefine.getNote());
                            LogUtil.d("Set vitalrecord[" + vitalDefine.getSignname() + "] to its real note[" + noteDefine.getNote() + "]!");
                            break;
                        }
                    }
                }
            }
        }
    }

    private void setNoteButton(String note) {
        setRealNote(note);
        boolean bNoteFound = true;
        if (note != null && !note.trim().isEmpty()) {
            bNoteFound = false;
            for (RadioButton bt : noteButtons) {
                if (note.equals(bt.getText())) {
                    bt.setTextColor(Color.WHITE);
                    bt.setChecked(true);
                    bt.setSelected(true);
                    bNoteFound = true;
                } else {
                    bt.setTextColor(Color.BLACK);
                    bt.setChecked(false);
                    bt.setSelected(false);
                }
            }
            // 如果外部notebutton没有合适的，则设置在morenote上
            if (mMoreNotesTextView != null && mMoreNotesAdapter != null) {
                if (!bNoteFound) {
                    mMoreNotesTextView.setText(note);
                    mMoreNotesTextView.setPadding(0, 0, 20, 0);
                    mMoreNotesTextView.setBackgroundResource(R.color.se559bec);
                    mMoreNotesAdapter.setSelectedItem(note);
                    mMoreNotesAdapter.notifyDataSetChanged();
                } else {
                    mMoreNotesTextView.setText("");
                    mMoreNotesTextView.setPadding(0, 0, 90, 0);
                    mMoreNotesTextView.setBackgroundResource(R.color.see8e8e8);
                    mMoreNotesAdapter.setSelectedItem("");
                    mMoreNotesAdapter.notifyDataSetChanged();
                }
            }
        } else if (defaultVitalNote != null && !defaultVitalNote.trim().isEmpty()) { // 如果没有设置过note则设置成default
            if (vitalRecord != null) {
                vitalRecord.setNote(defaultVitalNote);
            }
        }
    }
}
