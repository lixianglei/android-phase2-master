package com.example.sj.library.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.library.R;
import com.example.sj.library.adapter.TitleBarConfig;
import com.example.sj.library.control.ActivityControl;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.xutils.common.util.LogUtil;

/**
 * Created by Administrator on 2016/7/11.
 */
public abstract class BaseActivity extends FragmentActivity {
    public SystemBarTintManager tintManager;
    public BaseActivity mActivitySelf;
    public FragmentManager mFragmentManager;
    public LayoutInflater mLayoutInflater;
    private TextView mTextViewTitleLeft,mTextViewTitleRight,mTextViewTitleCenter;
    private View mTitleView;

    /**
     * 设置页面根布局
     * @return 页面根布局索引---R.layout.xx
     */
    public abstract int setRootView();
    /**
     * 初始化页面控件
     */
    public  abstract void initViews();
    /**
     * 初始化页面数据
     */
    public  abstract void initDatas();
    
    public abstract void init();
    
    public abstract boolean useTitleBar();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        mActivitySelf=this;
        ActivityControl.addActivity(this);
        mFragmentManager=this.getSupportFragmentManager();
        mLayoutInflater=LayoutInflater.from(this);

        setContentView(setRootView());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        // create our manager instance after the content view is set
        tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        initViews();
        initDatas();
        init();

    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    @Override
    public void setContentView(int layoutResID) {
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (useTitleBar()){
            //用标题栏
            LinearLayout linearLayout=new LinearLayout(this);
            linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            mTitleView=mLayoutInflater.inflate(TitleBarConfig.getTitleLayout(),linearLayout,false);
            mTitleView.setVisibility(View.GONE);
            mTextViewTitleLeft= (TextView) mTitleView.findViewById(R.id.tv_title_left);
            mTextViewTitleCenter= (TextView) mTitleView.findViewById(R.id.tv_title_center);
            mTextViewTitleRight= (TextView) mTitleView.findViewById(R.id.tv_title_right);
            View rootView=mLayoutInflater.inflate(layoutResID,linearLayout,false);
            linearLayout.addView(mTitleView);
            linearLayout.addView(rootView);
            setContentView(linearLayout);
        }else {
            super.setContentView(layoutResID);
        }
    }
    
    public void setTitleLeft(String text, View.OnClickListener onClickListener){
        if (mTextViewTitleLeft==null){
            return;
            
        }
        mTitleView.setVisibility(View.VISIBLE);
        mTextViewTitleLeft.setVisibility(View.VISIBLE);
        mTextViewTitleLeft.setText(text);
        if (onClickListener!=null){
            mTextViewTitleLeft.setOnClickListener(onClickListener);
        }
    }
    public void setTitleRight(String text, View.OnClickListener onClickListener){
        if (mTextViewTitleRight==null){
            return;
        
        }
        mTitleView.setVisibility(View.VISIBLE);
        mTextViewTitleRight.setVisibility(View.VISIBLE);
        mTextViewTitleRight.setText(text);
        if (onClickListener!=null){
            mTextViewTitleRight.setOnClickListener(onClickListener);
        }
    }
    public void setTitleCenter(String text){
        if (mTextViewTitleCenter==null){
            return;
        
        }
        mTitleView.setVisibility(View.VISIBLE);
        mTextViewTitleCenter.setVisibility(View.VISIBLE);
        mTextViewTitleCenter.setText(text);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityControl.removeActivity(this);
    }
    
    /**
     * 快捷弹出一个短吐司
     * @param text  吐司内容
     */
    public void showToastShort(String text){
        Toast.makeText(mActivitySelf, text, Toast.LENGTH_SHORT).show();
        
    }
    
    /**
     * 快捷弹出一个长吐司
     * @param text  吐司内容
     */
    public void showToastLong(String text){
        Toast.makeText(mActivitySelf, text, Toast.LENGTH_LONG).show();
        
    }
    //-------------------快捷打各种log-------------------
    public  void logE(String text){
        LogUtil.e(text);
    }
    public  void logD(String text){
        LogUtil.d(text);
    }
    public  void logI(String text){
        LogUtil.i(text);
    }
    public  void logW(String text){
        LogUtil.w(text);
    }
    public  void logV(String text){
        LogUtil.v(text);
    }
    //-------------------快捷打各种log-------------------
    
    /**
     * 快捷跳转到另一个Activity，可传递数据
     * @param activityClass  目标
     * @param bundle  数据
     */
    public void goToActivity(Class activityClass,Bundle bundle,int requestCode){
    
        Intent intent=new Intent(mActivitySelf,activityClass);
        if (bundle!=null){
            intent.putExtra("data",bundle);
        }
        startActivityForResult(intent,requestCode);
    }
    /**
     * 快捷跳转到另一个Activity
     * @param activityClass  目标
     */
    public void goToActivity(Class activityClass){
    
        goToActivity(activityClass,null,0);
    }
    public void goToActivity(Class activityClass,Bundle bundle){

        goToActivity(activityClass,bundle,0);
    }
    
    public Bundle getData(){
        return this.getIntent().getBundleExtra("data");
    }
    
    /**
     * 快捷跳转到另一个Service，可传递数据
     * @param serviceClass  目标
     * @param bundle  数据
     */
    public void goToService(Class serviceClass,Bundle bundle){
        
        Intent intent=new Intent(mActivitySelf,serviceClass);
        if (bundle!=null){
            intent.putExtra("data",bundle);
        }
        startService(intent);
    }
    /**
     * 快捷跳转到另一个Service
     * @param serviceClass  目标
     */
    public void goToService(Class serviceClass){
        
        goToService(serviceClass,null);
    }
    
    
    
    /**
     * 快捷跳转到另一个BrodCastReceiver，可传递数据
     * @param receiverClass  目标
     * @param bundle  数据
     */
    public void goToReceiver(Class receiverClass,Bundle bundle){
        
        Intent intent=new Intent(mActivitySelf,receiverClass);
        if (bundle!=null){
            intent.putExtra("data",bundle);
        }
        sendBroadcast(intent);
    }
    /**
     * 快捷跳转到另一个BrodCastReceiver
     * @param receiverClass  目标
     */
    public void goToReceiver(Class receiverClass){
        
        goToReceiver(receiverClass,null);
    }
    
    //-----------------对于Fragment的快捷操作
    
    /**
     * 
     * @param containerID 往哪儿放
     * @param frag  放谁
     */
    public void addFrag(int containerID, BaseFragment frag){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(containerID,frag,frag.mTag);
        fragmentTransaction.commit();
        
    }
    public void removeFrag( BaseFragment frag){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.remove(frag);
        fragmentTransaction.commit();
        
    }
    public void replaceFrag(int containerID, BaseFragment frag){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(containerID,frag,frag.mTag);
        fragmentTransaction.commit();
        
    }
    
    public void hideFrag( BaseFragment frag){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.hide(frag);
        fragmentTransaction.commit();
        
    }
    public void showFrag( BaseFragment frag){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.show(frag);
        fragmentTransaction.commit();
        
    }
    //-----------------对于Fragment的快捷操作
    /**
     * 自杀
     */
    public void killSelf(){
        finish();
    }


}
