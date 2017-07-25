package com.ge.med.mobile.nursing.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.ge.med.mobile.nursing.Constant;
import com.ge.med.mobile.nursing.config.URL;
import com.ge.med.mobile.nursing.dao.entity.TongZhiSocket;
import com.ge.med.mobile.nursing.forjson.NetworkForDoctorOrder;
import com.ge.med.mobile.nursing.forjson.callback.INetworkHandler;
import com.ge.med.mobile.nursing.utils.MessageEvent;

import org.xutils.common.util.LogUtil;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;


public class TongZhiService extends Service {
    private final MockWebServer mockWebServer = new MockWebServer();
    private final ExecutorService writeExecutor = Executors.newSingleThreadExecutor();
    private String messageString = null;

    public TongZhiService() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) return 0;
        Bundle data = intent.getBundleExtra("data");
        String userId = data.getString(Constant.GLOBAL_KEY_USER_ID);
        String token = data.getString(Constant.GLOBAL_KEY_TOKEN);
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(3000, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(3000, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(3000, TimeUnit.SECONDS)//设置连接超时时间
                .build();
        LogUtil.d("tongzhi response token2 :"+token);
        Request request = new Request.Builder().url(URL.URL_SOCKET).addHeader("userId", userId).addHeader("token", token).build();
        WebSocketCall webSocketCall = WebSocketCall.create(mOkHttpClient, request);
        webSocketCall.enqueue(new WebSocketListener() {
            WebSocket mWebSocket = null;

            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                //保存引用，用于后续操作
                this.mWebSocket = webSocket;
                //打印一些内容
                LogUtil.d("通知消息 保存引用" + response);
                //注意下面都是write线程回写给客户端
                //建立连接成功后，发生command 1给服务器端
                writeExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mWebSocket.sendMessage(RequestBody.create(WebSocket.TEXT, "command 1"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onFailure(IOException e, Response response) {
                //发生错误时会回调到这
                LogUtil.e("通知消息 发生错误时会回调到这");
            }

            @Override
            public void onMessage(ResponseBody message) throws IOException {
                //打印一些内容
                messageString = message.string();
                LogUtil.i("通知消息= " + messageString);
                TongZhiSocket tongZhiSocket = null;
                try {
                    tongZhiSocket = JSON.parseObject(messageString, TongZhiSocket.class);
                    if(tongZhiSocket.getType().equals("rewarming")){
                        NetworkForDoctorOrder.callGetDoctorOrderByHisId(new INetworkHandler() {
                            @Override
                            public void handleOnError() {

                            }

                            @Override
                            public void handleOnError(String urlStr) {

                            }

                            @Override
                            public void handleSuccess(Object obj) {

                            }
                        },tongZhiSocket.getHisOrderId());
                    }
                    if (tongZhiSocket.getType().equals("notify-data")) {
                        EventBus.getDefault().post(new MessageEvent(messageString));
                        LogUtil.i("通知消息= tongZhiSocket getType" + tongZhiSocket.getType());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //注意下面都是write线程回写给客户端
                if ("replay command 1".equals(messageString)) {
                    //收到服务器返回的replay command 1后继续向服务器端发送command 2
                    //replay it
                    writeExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mWebSocket.sendMessage(RequestBody.create(WebSocket.TEXT, "command 2"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            @Override
            public void onPong(Buffer payload) {
                //打印一些内容
                LogUtil.d("通知消息");
            }
            @Override
            public void onClose(int code, String reason) {
                //打印一些内容
                LogUtil.d("通知消息 onClose");
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
