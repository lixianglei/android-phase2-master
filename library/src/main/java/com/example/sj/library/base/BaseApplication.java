package com.example.sj.library.base;

import android.content.Context;

import com.example.sj.library.adapter.TitleBarConfig;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.litepal.LitePalApplication;
import org.xutils.common.util.LogUtil;
import org.xutils.x;

/**
 * Created by Administrator on 2016/7/11.
 */
public abstract class BaseApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
        x.Ext.init(this);
        x.Ext.setDebug(isDebug()); // 是否输出debug日志, 开启debug会影响性能.
        LogUtil.customTagPrefix=setDebugTag();
        TitleBarConfig.setTitleLayout(initTitle());
        init();
        
    }
    public  abstract  boolean isDebug();
    public  abstract  String setDebugTag();
    public  abstract  void init();
    public  abstract int initTitle();
    
    
    
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.FIFO);
        //config.writeDebugLogs(); // Remove for release app
        
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
}
