package com.example.sj.library.control;

import com.example.sj.library.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public class FragmentControl {
    private static List<BaseFragment> mBaseFragments = new ArrayList<>();
    
    public static void addFragment(BaseFragment baseFragment) {
        mBaseFragments.add(baseFragment);
    }
    
    public static void removeFragment(BaseFragment baseFragment) {
        mBaseFragments.remove(baseFragment);
    }
    
    public static BaseFragment getFragment(Class activityClass) {
        for (BaseFragment baseFragment : mBaseFragments) {
            if (baseFragment.getClass() == activityClass) {
                return baseFragment;
            }
        }
        throw new RuntimeException("NO SUCH FRAGMENT FOUND");
    }
    public static void killFragment(Class activityClass) {
        for (int i = mBaseFragments.size()-1; i>=0; i--){
            if (activityClass== mBaseFragments.get(i).getClass()){
                mBaseFragments.get(i).killSelf();
            }
            
        }
    }
    
    public static void killAll() {
        for (int i = mBaseFragments.size()-1; i>=0; i--){
            mBaseFragments.get(i).killSelf();
        }
    }
}
