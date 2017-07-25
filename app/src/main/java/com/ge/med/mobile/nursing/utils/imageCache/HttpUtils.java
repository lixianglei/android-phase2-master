package com.ge.med.mobile.nursing.utils.imageCache;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/2/14.
 */
public class HttpUtils {
    public static InputStream getStreamFromURL(String imageURL) {
        InputStream in=null;
        try {
            URL url=new URL(imageURL);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            in=connection.getInputStream();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return in;

    }
}
