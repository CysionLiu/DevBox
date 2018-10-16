package com.cysion.videosample.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.cysion.baselib.listener.TypeAction;
import com.cysion.videosample.entity.TransferTextBean;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class AudioSocketClient {

    private static volatile AudioSocketClient instance;
    // appid
    private static final String APPID = "5bbb1fd3";
    // appid对应的secret_key
    private static final String SECRET_KEY = "264bf5298b368f551147fd42493a3682";
    // 请求地址
    private static final String HOST = "rtasr.xfyun.cn/v1/ws";
    private static final String BASE_URL = "ws://" + HOST;

    //socket的状态
    public static final int IDLE = 606;
    public static final int CONNECTED = 607;
    public static final int CLOSED = 608;

    private WebSocket mWebSocket;
    private boolean isRealTime = false;//是否开启
    private String mConfirmedText = "";
    private TypeAction<String> mTypeAction;
    private int mSocketState = IDLE;
    private boolean hasAudio;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                sendVoid();
            } else {
                hasAudio = false;
                sendVoid();
            }
        }
    };

    private AudioSocketClient() {
    }

    public static synchronized AudioSocketClient obj() {
        if (instance == null) {
            instance = new AudioSocketClient();
        }
        return instance;
    }

    public void connect(TypeAction<String> aTypeAction, boolean needRealTime) {
        isRealTime = needRealTime;
        if (!isRealTime) {
            return;
        }
        mTypeAction = aTypeAction;
        mSocketState = IDLE;
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder().url(BASE_URL + getHandShakeParams(APPID, SECRET_KEY)).build();
        EchoWebSocketListener socketListener = new EchoWebSocketListener();
        mOkHttpClient.newWebSocket(request, socketListener);
        mOkHttpClient.dispatcher().executorService().shutdown();

    }

    public void sendBytes(ByteString aByteString) {
        if (isRealTime && mWebSocket != null) {
            hasAudio = true;
            mWebSocket.send(aByteString);
        }
    }

    //保持websocket长链接
    public void keepLive() {
        mHandler.sendEmptyMessageDelayed(1, 3000);
    }

    //3s发送一次空数据
    private void sendVoid() {
        if (!hasAudio && mWebSocket != null && isRealTime) {
            Log.d("flag--", "sendVoid(AudioSocketClient.java:102)---->>发送空数据");
            mHandler.sendEmptyMessageDelayed(0, 3000);
            mWebSocket.send(ByteString.of(new byte[8]));
        }
    }


    public void cancel() {
        if (mWebSocket != null) {
            mWebSocket.cancel();
            isRealTime = false;
        }
        mSocketState = IDLE;
        mConfirmedText = "";
        mHandler.removeCallbacksAndMessages(null);
    }

    // 生成握手参数
    private String getHandShakeParams(String appId, String secretKey) {
        String ts = System.currentTimeMillis() / 1000 + "";
        String signa = "";
        try {
            signa = EncryptUtil.HmacSHA1Encrypt(EncryptUtil.MD5(appId + ts), secretKey);
            return "?appid=" + appId + "&ts=" + ts + "&signa=" + URLEncoder.encode(signa, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // 把转写结果解析为句子
    private String getContent(String message) {
        StringBuffer resultBuilder = new StringBuffer();
        message.replaceAll("\\\\", "");
        String transferingText = "";
        try {
            JSONObject messageObj = new JSONObject(message);
            TransferTextBean bean = new Gson().fromJson(messageObj.optString("data"), TransferTextBean.class);
            if (bean != null && bean.getCn() != null) {
                if (bean.getCn().getSt().getType().equals("0")) {
                    List<TransferTextBean.CnBean.StBean.RtBean> rt = bean.getCn().getSt().getRt();
                    TransferTextBean.CnBean.StBean.RtBean rtBean1 = rt.get(0);
                    List<TransferTextBean.CnBean.StBean.RtBean.WsBean> ws = rtBean1.getWs();
                    for (TransferTextBean.CnBean.StBean.RtBean.WsBean w : ws) {
                        resultBuilder.append(w.getCw().get(0).getW());
                    }
                    transferingText = resultBuilder.toString();
                    mConfirmedText = mConfirmedText + transferingText;
                    return mConfirmedText;

                } else {
                    List<TransferTextBean.CnBean.StBean.RtBean> rt = bean.getCn().getSt().getRt();
                    TransferTextBean.CnBean.StBean.RtBean rtBean1 = rt.get(0);
                    List<TransferTextBean.CnBean.StBean.RtBean.WsBean> ws = rtBean1.getWs();
                    for (TransferTextBean.CnBean.StBean.RtBean.WsBean w : ws) {
                        resultBuilder.append(w.getCw().get(0).getW());
                    }
                    transferingText = resultBuilder.toString();
                    return mConfirmedText + transferingText;
                }
            }
        } catch (Exception e) {
            return mConfirmedText;
        }
        return mConfirmedText;
    }

    final class EchoWebSocketListener extends WebSocketListener {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            Log.d("flag--", "onOpen(AudioSocketClient.java:121)---->>" + "连接ws成功");
            mWebSocket = webSocket;
            mSocketState = CONNECTED;
            if (mTypeAction != null) {
                mTypeAction.done("", mSocketState);
            }
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            Log.d("flag--", "onMessage(AudioSocketClient.java:127)---->>" + "接收消息");
            if (mTypeAction != null) {
                mTypeAction.done(getContent(text), mSocketState);
            }
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
            mSocketState = CLOSED;
            if (mTypeAction != null) {
                mTypeAction.done("连接关闭", mSocketState);
            }
            Log.d("flag--", "onClosed(AudioSocketClient.java:156)---->>" + "连接关闭");
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
            mSocketState = CLOSED;
            if (mTypeAction != null) {
                mTypeAction.done("连接关闭", CLOSED);
            }
            Log.d("flag--", "onFailure(AudioSocketClient.java:163)---->>" + "连接失败");
        }
    }
}
