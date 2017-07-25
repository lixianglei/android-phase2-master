package com.ge.med.mobile.nursing.ui.component;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sj.library.base.BaseActivity;
import com.ge.med.mobile.nursing.R;

import org.xutils.common.util.LogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Qu on 2016/12/16.
 */
public class DateTimeControl implements View.OnClickListener {
    private RelativeLayout timeControls;
    private TextView mShiJianNianYueRiTv;
    private ImageButton mImageButton;
    private TextView mShiJianShiTv;
    private ImageButton mShiJianShangBtimgv;
    private ImageButton mShiJianXiaBtimgv;
    private TextView mTextView;
    private TextView mShiJianFenTv;
    private ImageButton mShiJianFenShangBtimgv;
    private ImageButton mShiJianFenXiaBtimgv;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private boolean canSelectFutureDate;
    private BaseActivity mActivity;

    public DateTimeControl(final BaseActivity activity) {
        mActivity = activity;
        canSelectFutureDate = false;
        timeControls = (RelativeLayout) activity.findViewById(R.id.time_controls);
        mShiJianNianYueRiTv = (TextView) activity.findViewById(R.id.shi_jian_nian_yue_ri_tv);
        mImageButton = (ImageButton) activity.findViewById(R.id.imageButton);
        mShiJianShiTv = (TextView) activity.findViewById(R.id.shi_jian_shi_tv);
        mShiJianShangBtimgv = (ImageButton) activity.findViewById(R.id.shi_jian_shang_btimgv);
        mShiJianXiaBtimgv = (ImageButton) activity.findViewById(R.id.shi_jian_xia_btimgv);
        mTextView = (TextView) activity.findViewById(R.id.textView);
        mShiJianFenTv = (TextView) activity.findViewById(R.id.shi_jian_fen_tv);
        mShiJianFenShangBtimgv = (ImageButton) activity.findViewById(R.id.shi_jian_fen_shang_btimgv);
        mShiJianFenXiaBtimgv = (ImageButton) activity.findViewById(R.id.shi_jian_fen_xia_btimgv);
        setToCurrentDateTime();

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        setDateTime(year, monthOfYear + 1, dayOfMonth, mHour, mMinute);
                    }
                }, mYear, mMonth - 1, mDay);

                datePickerDialog.show();
            }
        });

        mShiJianShangBtimgv.setOnClickListener(this);
        mShiJianXiaBtimgv.setOnClickListener(this);

        mShiJianFenShangBtimgv.setOnClickListener(this);
        mShiJianFenXiaBtimgv.setOnClickListener(this);
    }

    private void setToCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        setDateTime(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1
                , calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }

    public void setDateTime(int year, int month, int day, int hour, int minute) {
        mYear = year;
        mMonth = month;
        mDay = day;
        mHour = hour;
        mMinute = minute;
        LogUtil.d("setDateTime.mYear:" + mYear + ",mMonth:" + mMonth + ",mDay:" + mDay + ",mHour:" + mHour + ",mMinute:" + mMinute);
        if (!canSelectFutureDate) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day, hour, minute, 0);
            long currentTime = System.currentTimeMillis(), wantTime = calendar.getTimeInMillis();
            if (currentTime <= wantTime) {
                LogUtil.i("Can not set to furture! current:" + currentTime + ", want to set to:" + wantTime);
                mActivity.showToastShort("不能设置成未来的时间！");
                setToCurrentDateTime();
                return;
            }
        }
        mShiJianNianYueRiTv.setText(mYear + "-" + mMonth + "-" + mDay);
        mShiJianShiTv.setText(mHour + "");
        mShiJianFenTv.setText(mMinute + "");
    }


    public void setVisible(boolean isShow) {
        if (isShow) timeControls.setVisibility(View.VISIBLE);
        else timeControls.setVisibility(View.GONE);
    }

    public String getDateTime() {
        return mShiJianNianYueRiTv.getText().toString() + " "
                + mShiJianShiTv.getText() + ":" + mShiJianFenTv.getText() + ":00";
//        Long retval = null;
//        try {
//            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
//            String dateStr = ;
//            LogUtil.d("Vital Sign Sheet time string is >"+dateStr);
//            Date date = format.parse(dateStr);
//            retval = date.getTime();
//        } catch (ParseException e) {
//            LogUtil.e("Can not parse date time to long!");
//        }
//        return retval;
    }

    //
    public String getLong() {
        String retval = mShiJianNianYueRiTv.getText().toString() + " "
                + mShiJianShiTv.getText() + ":" + mShiJianFenTv.getText() + ":00";
        LogUtil.d("Vital Sign Sheet time string is >" + retval);
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            retval = format.parse(retval).getTime() + "";
            LogUtil.d("Vital Sign Sheet time long is >" + retval);
        } catch (ParseException e) {
            LogUtil.e("Can not parse date time to long!error is " + e.getMessage());
            retval = "";
        } catch (Exception e) {
            LogUtil.e("Format datetime to long failed!err is " + e.getMessage());
            retval = "";
        }
        return retval;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //时 上
            case R.id.shi_jian_shang_btimgv:
                if (++mHour >= 24) mHour = 0;
                setDateTime(mYear, mMonth, mDay, mHour, mMinute);
                break;
            //时 下
            case R.id.shi_jian_xia_btimgv:
                if (--mHour < 0) mHour = 23;
                setDateTime(mYear, mMonth, mDay, mHour, mMinute);
                break;
            //分 上
            case R.id.shi_jian_fen_shang_btimgv:
                if (++mMinute >= 60) mMinute = 0;
                setDateTime(mYear, mMonth, mDay, mHour, mMinute);
                break;
            //分 下
            case R.id.shi_jian_fen_xia_btimgv:
                if (--mMinute < 0) mMinute = 59;
                setDateTime(mYear, mMonth, mDay, mHour, mMinute);
                break;
        }
    }

    public boolean isCanSelectFutureDate() {
        return canSelectFutureDate;
    }

    public void setCanSelectFutureDate(boolean canSelectFutureDate) {
        this.canSelectFutureDate = canSelectFutureDate;
    }
}
