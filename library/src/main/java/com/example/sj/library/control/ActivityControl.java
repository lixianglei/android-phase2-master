package com.example.sj.library.control;

import com.example.sj.library.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public class ActivityControl {
    private static List<BaseActivity> mBaseActivities = new ArrayList<>();
    
    public static void addActivity(BaseActivity baseActivity) {
        mBaseActivities.add(baseActivity);
    }
    
    public static void removeActivity(BaseActivity baseActivity) {
        mBaseActivities.remove(baseActivity);
    }

    public   static <T extends BaseActivity>  T    getActivity (Class activityClass) {
        for (BaseActivity baseActivity : mBaseActivities) {
            if (baseActivity.getClass() == activityClass) {
                return (T)baseActivity;
            }
        }
        throw new RuntimeException("NO SUCH ACTIVITY FOUND");
    }
    public static void killActivity(Class activityClass) {
        for (int i=mBaseActivities.size()-1;i>=0;i--){
            if (activityClass== mBaseActivities.get(i).getClass()){
                mBaseActivities.get(i).killSelf();
            }
            
        }
    }
    
    public static void killAll() {
        for (int i=mBaseActivities.size()-1;i>=0;i--){
            mBaseActivities.get(i).killSelf();
        }
    }
}
