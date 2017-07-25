package com.ge.med.mobile.nursing.ui.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TZ_SSYFragment extends BaseFragment implements View.OnClickListener {
    private TextView mTiZhengZhi;
    private TextView mTiZhengDanWei;
    private LinearLayout mDiyiPaiBtLl1;
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
    private RadioGroup mDiyiPaiBtLl5;
    private RadioButton mBt51;
    private RadioButton mBt52;
    private RadioButton mBt53;
    private RadioButton mBt54;

    @Override
    public int setRootView() {
        return R.layout.fragment_tz__ssy;
    }


    @Override
    public void initViews() {
        mTiZhengZhi = (TextView) mRootView.findViewById(R.id.ti_zheng_zhi);
        mTiZhengDanWei = (TextView) mRootView.findViewById(R.id.ti_zheng_dan_wei);
        mDiyiPaiBtLl1 = (LinearLayout) mRootView.findViewById(R.id.diyi_pai_bt_ll_1);
        mBt11 = (Button) mRootView.findViewById(R.id.bt_1_1);
        mBt12 = (Button) mRootView.findViewById(R.id.bt_1_2);
        mBt13 = (Button) mRootView.findViewById(R.id.bt_1_3);
        mBt14 = (Button) mRootView.findViewById(R.id.bt_1_4);
        mDiyiPaiBtLl2 = (LinearLayout) mRootView.findViewById(R.id.diyi_pai_bt_ll_2);
        mBt21 = (Button) mRootView.findViewById(R.id.bt_2_1);
        mBt22 = (Button) mRootView.findViewById(R.id.bt_2_2);
        mBt23 = (Button) mRootView.findViewById(R.id.bt_2_3);
        mBt24 = (Button) mRootView.findViewById(R.id.bt_2_4);
        mDiyiPaiBtLl3 = (LinearLayout) mRootView.findViewById(R.id.diyi_pai_bt_ll_3);
        mBt31 = (Button) mRootView.findViewById(R.id.bt_3_1);
        mBt32 = (Button) mRootView.findViewById(R.id.bt_3_2);
        mBt33 = (Button) mRootView.findViewById(R.id.bt_3_3);
        mBt34 = (Button) mRootView.findViewById(R.id.bt_3_4);
        mDiyiPaiBtLl4 = (LinearLayout) mRootView.findViewById(R.id.diyi_pai_bt_ll_4);
        mBt41 = (Button) mRootView.findViewById(R.id.bt_4_1);
        mBt42 = (Button) mRootView.findViewById(R.id.bt_4_2);
        mBt43 = (Button) mRootView.findViewById(R.id.bt_4_3);
        mBt44 = (ImageButton) mRootView.findViewById(R.id.bt_4_4);
        mDiyiPaiBtLl5 = (RadioGroup) mRootView.findViewById(R.id.diyi_pai_bt_ll_5);
        mBt51 = (RadioButton) mRootView.findViewById(R.id.bt_5_1);
        mBt52 = (RadioButton) mRootView.findViewById(R.id.bt_5_2);
        mBt53 = (RadioButton) mRootView.findViewById(R.id.bt_5_3);
        mBt54 = (RadioButton) mRootView.findViewById(R.id.bt_5_4);



        mTiZhengDanWei.setText("mmHg");
        mDiyiPaiBtLl1.setVisibility(View.INVISIBLE);
        mBt11.setOnClickListener(this);
        mBt12.setOnClickListener(this);
        mBt13.setOnClickListener(this);
        mBt14.setOnClickListener(this);
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
        mBt51.setOnClickListener(this);
        mBt52.setOnClickListener(this);
        mBt53.setOnClickListener(this);
        mBt54.setVisibility(View.GONE);

        mBt51.setText("仰卧");
        mBt52.setText("坐姿");
        mBt53.setText("站立");
        mDiyiPaiBtLl5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    //体征结果值
                    case R.id.bt_5_1:
                        mBt51.setTextColor(Color.WHITE);
                        mBt52.setTextColor(Color.BLACK);
                        mBt53.setTextColor(Color.BLACK);
                        mBt54.setTextColor(Color.BLACK);
                        break;
                    //体征结果值
                    case R.id.bt_5_2:
                        mBt51.setTextColor(Color.BLACK);
                        mBt52.setTextColor(Color.WHITE);
                        mBt53.setTextColor(Color.BLACK);
                        mBt54.setTextColor(Color.BLACK);
                        break;
                    //体征结果值
                    case R.id.bt_5_3:
                        mBt51.setTextColor(Color.BLACK);
                        mBt52.setTextColor(Color.BLACK);
                        mBt53.setTextColor(Color.WHITE);
                        mBt54.setTextColor(Color.BLACK);
                        break;
                    //体征结果值
                    case R.id.bt_5_4:
                        mBt51.setTextColor(Color.BLACK);
                        mBt52.setTextColor(Color.BLACK);
                        mBt53.setTextColor(Color.BLACK);
                        mBt54.setTextColor(Color.WHITE);
                        break;
                }
            }
        });
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void init() {

    }

    @Override
    public boolean useTitleBar() {
        return false;
    }

    StringBuilder stringBuilder;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //体征结果值
            case R.id.bt_1_1:

                stringBuilder = new StringBuilder(mTiZhengZhi.getText());
                stringBuilder.append(mBt11.getText());
                mTiZhengZhi.setText(stringBuilder);

                break;
            //体征结果值
            case R.id.bt_1_2:
                stringBuilder = new StringBuilder(mTiZhengZhi.getText());

                stringBuilder.append(mBt12.getText());
                mTiZhengZhi.setText(stringBuilder);
                break;
            //体征结果值
            case R.id.bt_1_3:
                stringBuilder = new StringBuilder(mTiZhengZhi.getText());

                stringBuilder.append(mBt13.getText());
                mTiZhengZhi.setText(stringBuilder);
                break;
            //体征结果值
            case R.id.bt_1_4:

                stringBuilder = new StringBuilder(mTiZhengZhi.getText());
                stringBuilder.append(mBt14.getText());
                mTiZhengZhi.setText(stringBuilder);
                break;
            //体征结果值
            case R.id.bt_2_1:

                stringBuilder = new StringBuilder(mTiZhengZhi.getText());
                stringBuilder.append(mBt21.getText());
                mTiZhengZhi.setText(stringBuilder);
                break;
            //体征结果值
            case R.id.bt_2_2:
                stringBuilder = new StringBuilder(mTiZhengZhi.getText());
                stringBuilder.append(mBt22.getText());
                mTiZhengZhi.setText(stringBuilder);
                break;
            //体征结果值
            case R.id.bt_2_3:

                stringBuilder = new StringBuilder(mTiZhengZhi.getText());
                stringBuilder.append(mBt23.getText());
                mTiZhengZhi.setText(stringBuilder);
                break;
            //体征结果值
            case R.id.bt_2_4:
                stringBuilder = new StringBuilder(mTiZhengZhi.getText());


                stringBuilder.append(mBt24.getText());
                mTiZhengZhi.setText(stringBuilder);
                break;
            //体征结果值
            case R.id.bt_3_1:
                stringBuilder = new StringBuilder(mTiZhengZhi.getText());

                stringBuilder.append(mBt31.getText());
                mTiZhengZhi.setText(stringBuilder);
                break;
            //体征结果值
            case R.id.bt_3_2:

                stringBuilder = new StringBuilder(mTiZhengZhi.getText());
                stringBuilder.append(mBt32.getText());
                mTiZhengZhi.setText(stringBuilder);
                break;
            //体征结果值
            case R.id.bt_3_3:
                stringBuilder = new StringBuilder(mTiZhengZhi.getText());

                stringBuilder.append(mBt33.getText());
                mTiZhengZhi.setText(stringBuilder);
                break;
            //体征结果值
            case R.id.bt_3_4:
                stringBuilder = new StringBuilder(mTiZhengZhi.getText());

                stringBuilder.append(mBt34.getText());
                mTiZhengZhi.setText(stringBuilder);
                break;
            //体征结果值
            case R.id.bt_4_1:

                stringBuilder = new StringBuilder(mTiZhengZhi.getText());
                stringBuilder.append(mBt41.getText());
                mTiZhengZhi.setText(stringBuilder);
                break;
            //体征结果值
            case R.id.bt_4_2:
                stringBuilder = new StringBuilder(mTiZhengZhi.getText());

                stringBuilder.append(mBt42.getText());
                mTiZhengZhi.setText(stringBuilder);
                break;
            //体征结果值
            case R.id.bt_4_3:

                stringBuilder = new StringBuilder(mTiZhengZhi.getText());
                stringBuilder.append(mBt43.getText());
                mTiZhengZhi.setText(stringBuilder);
                break;
            //体征结果值
            case R.id.bt_4_4:
                stringBuilder = new StringBuilder(mTiZhengZhi.getText());
                if (stringBuilder != null && stringBuilder.length() != 0) {
                    String s = stringBuilder.substring(0, stringBuilder.length() - 1);
                    mTiZhengZhi.setText(s);
                    break;
                }
                if (stringBuilder.length() == 1) {
                    mTiZhengZhi.setText("");
                }

                break;
            //体征结果值
            case R.id.bt_5_1:

                break;
            //体征结果值
            case R.id.bt_5_2:

                break;
            //体征结果值
            case R.id.bt_5_3:

                break;
            //体征结果值
            case R.id.bt_5_4:

                break;

        }
    }
}
