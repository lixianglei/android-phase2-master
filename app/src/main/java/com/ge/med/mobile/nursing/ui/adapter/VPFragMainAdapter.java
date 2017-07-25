package com.ge.med.mobile.nursing.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sj.library.base.BaseFragment;

/**
 * Created by Alex Qu on 2016/12/7
 */
public class VPFragMainAdapter extends FragmentPagerAdapter {
    BaseFragment[] baseFragments;
    private Bundle mBundle;
    public VPFragMainAdapter(FragmentManager fm, BaseFragment[] baseFragments) {
        super(fm);
        this.baseFragments=baseFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return baseFragments[position];
    }

    @Override
    public int getCount() {
        return baseFragments.length;
    }

}
