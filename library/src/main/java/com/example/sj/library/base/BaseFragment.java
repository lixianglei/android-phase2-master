package com.example.sj.library.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sj.library.R;
import com.example.sj.library.adapter.TitleBarConfig;
import com.example.sj.library.control.FragmentControl;

import org.xutils.common.util.LogUtil;

/**
 * Created by Administrator on 2016/7/11.
 */
public abstract  class BaseFragment extends Fragment{
    
    public String mTag="";
    public BaseActivity mActivitySelf;
    public BaseFragment mFragSelf;
    public View mRootView;
    public FragmentManager mFragmentManager;
    public LayoutInflater mLayoutInflater;
    public BaseFragment() {
        mTag = this.getClass().getSimpleName();
    }
    private TextView mTextViewTitleLeft,mTextViewTitleRight,mTextViewTitleCenter;
    private View mTitleView;
    private Bundle mBundle;
    
    
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySelf= (BaseActivity) this.getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragSelf=this;
        mActivitySelf= (BaseActivity) this.getActivity();
        mFragmentManager=this.getFragmentManager();
        mLayoutInflater=inflater;
        mRootView=mLayoutInflater.inflate(setRootView(),container,false);
        FragmentControl.addFragment(this);
        if (useTitleBar()){
            mRootView=initTitleBar(mRootView);
        }
        
        initViews();
        initDatas();
        init();
        return mRootView;
    }
    
    private   View initTitleBar(View rootView){
        LinearLayout linearLayout=new LinearLayout(mActivitySelf);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        mTitleView=mLayoutInflater.inflate(TitleBarConfig.getTitleLayout(),linearLayout,false);
        mTitleView.setVisibility(View.GONE);
        mTextViewTitleLeft= (TextView) mTitleView.findViewById(R.id.tv_title_left);
        mTextViewTitleCenter= (TextView) mTitleView.findViewById(R.id.tv_title_center);
        mTextViewTitleRight= (TextView) mTitleView.findViewById(R.id.tv_title_right);

        linearLayout.addView(mTitleView);
        linearLayout.addView(rootView);
        return linearLayout;
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
    public void onDestroy() {
        super.onDestroy();
        FragmentControl.removeFragment(this);
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
        mActivitySelf.startService(intent);
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
        mActivitySelf.sendBroadcast(intent);
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
       removeFrag(this);
    }

    public void setBundle(Bundle bundle) {
        this.mBundle = bundle;
    }
    public Bundle getBundle() {
        return mBundle;
    }

    public  void handleOnError(){

    };

    public  void handleSuccess(Object assess){

    };
}
