package com.ge.med.mobile.nursing.ui.fragment;


import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sj.library.base.BaseFragment;
import com.ge.med.mobile.nursing.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShiJianFragment extends BaseFragment implements View.OnClickListener {
    private TextView mShiJianNianYueRiTv;
    private ImageButton mImageButton;
    private TextView mShiJianShiTv;
    private ImageButton mShiJianShangBtimgv;
    private ImageButton mShiJianXiaBtimgv;
    private TextView mTextView;
    private TextView mShiJianFenTv;
    private ImageButton mShiJianFenShangBtimgv;
    private ImageButton mShiJianFenXiaBtimgv;
    private int year, monthOfYear, dayOfMonth;



    @Override
    public int setRootView() {
        return R.layout.fragment_shi_jian ;
    }

    @Override
    public void initViews() {

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        mShiJianNianYueRiTv = (TextView) mRootView.findViewById(R.id.shi_jian_nian_yue_ri_tv);
        mImageButton = (ImageButton) mRootView.findViewById(R.id.imageButton);
        mShiJianShiTv = (TextView) mRootView.findViewById(R.id.shi_jian_shi_tv);
        mShiJianShangBtimgv = (ImageButton) mRootView.findViewById(R.id.shi_jian_shang_btimgv);
        mShiJianXiaBtimgv = (ImageButton) mRootView.findViewById(R.id.shi_jian_xia_btimgv);
        mTextView = (TextView) mRootView.findViewById(R.id.textView);
        mShiJianFenTv = (TextView) mRootView.findViewById(R.id.shi_jian_fen_tv);
        mShiJianFenShangBtimgv = (ImageButton) mRootView.findViewById(R.id.shi_jian_fen_shang_btimgv);
        mShiJianFenXiaBtimgv = (ImageButton) mRootView.findViewById(R.id.shi_jian_fen_xia_btimgv);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(mActivitySelf, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth)
                    {
                        mShiJianNianYueRiTv.setText( year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                    }
                }, year, monthOfYear, dayOfMonth);

                datePickerDialog.show();
            }
        });

        mShiJianShangBtimgv.setOnClickListener(this);
        mShiJianXiaBtimgv.setOnClickListener(this);

        mShiJianFenShangBtimgv.setOnClickListener(this);
        mShiJianFenXiaBtimgv.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //时 上
            case R.id.shi_jian_shang_btimgv:
                int shishang=Integer.parseInt(mShiJianShiTv.getText().toString());
                if(shishang==24){
                    mShiJianShiTv.setText(1+"");
                }else{
                    mShiJianShiTv.setText(shishang+1+"");
                }

            break;
            //时 下
            case R.id.shi_jian_xia_btimgv:
                int shixia=Integer.parseInt(mShiJianShiTv.getText().toString());
                if(shixia==1){
                    mShiJianShiTv.setText(24+"");
                }else{
                    mShiJianShiTv.setText(shixia-1+"");
                }
                break;

            //分 上
            case R.id.shi_jian_fen_shang_btimgv:
                int fenshang=Integer.parseInt(mShiJianFenTv.getText().toString());
                if(fenshang==60){
                    mShiJianFenTv.setText(1+"");
                }else{
                    mShiJianFenTv.setText(fenshang+1+"");
                }
                break;

            //分 下
            case R.id.shi_jian_fen_xia_btimgv:
                int fenxia=Integer.parseInt(mShiJianFenTv.getText().toString());
                if(fenxia==0){
                    mShiJianFenTv.setText(60+"");
                }else{
                    mShiJianFenTv.setText(fenxia-1+"");
                }
                break;

        }
    }
}
