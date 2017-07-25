package com.ge.med.mobile.nursing.base;

import com.example.sj.library.base.BaseApplication;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.utils.GlobalValue;
import com.mitac.lib.bcr.McBcrConnection;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * APP初始化设置
 * Created by Administrator on 2016/10/27.
 */
public class Myapp extends BaseApplication {
    public static boolean vibrate = true;
    public static boolean playSound = true;
    // 神达扫描
    public static McBcrConnection bcrConnection;


    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);

        // 获取设备型号
        String DeviceModel = android.os.Build.MANUFACTURER;
        if (DeviceModel != null) {
            GlobalValue.DEVICE_MODEL = DeviceModel;
        } else {
            GlobalValue.DEVICE_MODEL = Constant.DEVICE_MODEL_UNKNOW;
        }
        bcrConnection = new McBcrConnection(this);
        bcrConnection.bind();

        /*if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);*/
    }

    /**
     * 是否打开DEBUG
     *
     * @return
     */
    @Override
    public boolean isDebug() {
        return false;
    }

    /**
     * @return
     */
    @Override
    public String setDebugTag() {
        return null;
    }

    @Override
    public void init() {


    }

    /**
     * 设置标题栏 此APP未用到
     *
     * @return
     */
    @Override
    public int initTitle() {
        return 0;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (bcrConnection != null) {
            bcrConnection.unbind();
        }
    }
}
