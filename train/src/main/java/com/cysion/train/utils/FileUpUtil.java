package com.cysion.train.utils;

import android.os.Handler;
import android.os.Message;

import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.net.Caller;
import com.cysion.train.view.MyToast;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

public class FileUpUtil {

    private static volatile FileUpUtil instance;

    private FileUpUtil() {

    }

    public static synchronized FileUpUtil obj() {
        if (instance == null) {
            instance = new FileUpUtil();
        }
        return instance;
    }

    PureListener<String> mPureListener;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String mAvatarUrl = (String) msg.obj;
                mPureListener.done(mAvatarUrl);
                mHandler.removeCallbacksAndMessages(null);
            } else if (msg.what == 2) {
                MyToast.quickShow("上传图片失败");
                mPureListener.dont(0, "失败");
                mHandler.removeCallbacksAndMessages(null);
            }
        }
    };

    //上传图片文件，附带参数
    public void postFile(final String url, final Map<String, String> map, File file, PureListener<String> aPureListener) {
        mPureListener = aPureListener;
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (file != null) {
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("file", filename, body);
        }
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            Set<Map.Entry<String, String>> entries = map.entrySet();
            for (Map.Entry entry : entries) {
                String key = valueOf(entry.getKey());
                String value = valueOf(entry.getValue());
                requestBody.addFormDataPart(key, value);
            }
        }
        Request request = new Request.Builder().url(url).post(requestBody.build()).build();
        // readTimeout("请求超时时间" , 时间单位);
        Caller.obj().getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.sendEmptyMessage(2);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() != null) {
                    String string = response.body().string();
                    Map<String, String> result = MyJsonUtil.obj().gson().fromJson(string, Map.class);
                    String url = result.get("url");
                    mHandler.sendMessage(mHandler.obtainMessage(1, url));
                } else {
                    mHandler.sendEmptyMessage(2);
                }
            }
        });
    }
}
