package com.ge.med.mobile.nursing.forjson.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.AssessDefine;
import com.ge.med.mobile.nursing.dao.entity.TuPianBean;
import com.ge.med.mobile.nursing.forjson.callback.BaseNetCallback;
import com.ge.med.mobile.nursing.forjson.callback.INetworkHandler;
import com.ge.med.mobile.nursing.utils.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.common.util.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by Qu on 2016/12/23.
 */
public class NetworkForImage {
    public static File fileCache = new File(Environment.getExternalStorageDirectory(), "cache");

    public interface IImageBean{
        Integer getImageId();
        void setImageString(String str);
    }
    public static void callGetImage(final INetworkHandler handler, final IImageBean image) {
        if (image == null || image.getImageId() == null) {
            LogUtil.e("Can not get image from server since image parameter is null!");
            return;
        }
        final String urlStr = URL.URL_ICON;
        LogUtil.d("call network API:" + urlStr + ", imageId is " + image.getImageId());
        OkHttpUtils.get().url(urlStr).addHeader("User-Agent", "www.gs.com")
                .addParams(Constant.GLOBAL_KEY_ID, image.getImageId().toString()).build().execute(new BaseNetCallback() {
            @Override
            protected void preHandleError() {
                handler.handleOnError(urlStr);
            }

            @Override
            protected void preHandleSuccess(Object obj) {
                if (obj instanceof String) {
                    if (image instanceof AssessDefine.DataBean.AssessTopicDefineListBean) {
                        LogUtil.d("load image[" + image.getImageId() + "] from server for topic[" + ((AssessDefine.DataBean.AssessTopicDefineListBean)image).getId() + "], url is " + obj);
                    }else if (image instanceof AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean){
                        LogUtil.d("load image[" + image.getImageId() + "] from server for answer[" + ((AssessDefine.DataBean.AssessTopicDefineListBean.AssessAnswerDefineListBean)image).getId() + "], url is " + obj);
                    }else{
                        LogUtil.d("load image[" + image.getImageId() + "] from server, url is " + obj);
                    }
                    try {
                        String str = getImageLocalPath((String)obj, fileCache);
                        image.setImageString(str);
                        LogUtil.d("set local image path:" + str);
                    } catch (Exception e) {
                        e.printStackTrace();
                        LogUtil.e("Can not save image!exception is " + e.getMessage());
                    }
                }
            }

            @Override
            protected BaseJSONBean parseResonseString(String response) {
                return JSON.parseObject(response, TuPianBean.class);
            }

        });
    }

    /*
     * 从网络上获取图片，如果图片在本地存在的话就直接拿，如果不存在再去服务器上下载图片
     * 这里的path是图片的地址
     */
    public static String getImageLocalPath(final String path, final File cache) {
        final String name;
        if (!(path == null || path.trim().isEmpty() || cache == null)){
            StringBuilder sb = new StringBuilder();
            sb.append(MD5Util.md5(path));
            if (!(path.lastIndexOf(".") < 0)){
                sb.append(path.substring(path.lastIndexOf(".")));
            }
            name = sb.toString();
            new Thread(){
                @Override
                public void run() {
                    if (!cache.exists()){
                        cache.mkdir();
                    }
                    File file = new File(cache, name);
                    // 如果图片存在本地缓存目录，则不去服务器下载
                    if (!file.exists()) {
                        LogUtil.d("local file[" + name + "] not existed in local cache.path:" + path);
                        // 从网络上获取图片
                        HttpURLConnection conn = null;
                        InputStream is = null;
                        FileOutputStream fos = null;
                        try {
                            //file.createNewFile();
                            java.net.URL url = new java.net.URL(path);
                            conn = (HttpURLConnection) url.openConnection();
                            conn.setConnectTimeout(5000);
                            conn.setRequestMethod("GET");
                            conn.setDoInput(true);
                            if (conn.getResponseCode() == 200) {
                                LogUtil.d("requested path[" + path + "] return code is 200!");
                                is = conn.getInputStream();
                                fos = new FileOutputStream(file);
                                byte[] buffer = new byte[1024];
                                int len = 0;
                                while ((len = is.read(buffer)) != -1) {
                                    fos.write(buffer, 0, len);
                                }
                                LogUtil.d("wrote path[" + path + "] to local[" + name + "] finished!");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            LogUtil.e("Can not save image!exception is " + e.getMessage());

                        }finally {
                            try {
                                if (is != null) is.close();
                            } catch (IOException e1) {
                                LogUtil.e("Can not close inputstream!exception is " + e1.getMessage());
                            }
                            try {
                                if (fos != null) fos.close();
                            } catch (IOException e1) {
                                LogUtil.e("Can not close fileoutputstream!exception is " + e1.getMessage());
                            }
                        }
                    }else LogUtil.d("file[" + name + "] was already existed in local cache.");
                }
            }.start();
        }else name = null;
        return name;
    }
    public static Bitmap getLoacalBitmap(String url) {
        Bitmap retval = null;
        File file = new File(fileCache, url);
        if (file.exists()) {
            LogUtil.d("File[" + url + "] existed in local cache path!");
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                retval = BitmapFactory.decodeStream(fis);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null) fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else LogUtil.e("File[" + url + "] not existed!");
        return retval;
    }

    //删除文件夹和文件夹里面的文件
    public  static void deleteAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null)
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹
                    deleteAllFiles(f);
                    try {
                        f.delete();
                    } catch (Exception e) {
                    }
                } else {
                    if (f.exists()) { // 判断是否存在
                        deleteAllFiles(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                        }
                    }
                }
            }
    }
}
