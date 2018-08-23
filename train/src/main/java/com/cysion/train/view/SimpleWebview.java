package com.cysion.train.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SimpleWebview extends WebView {
    public SimpleWebview(Context context) {
        super(context);
        init(context);
    }

    public SimpleWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SimpleWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context aContext) {
        initSettings();
        setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });
    }

    //配置webview的设置
    private void initSettings() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        //支持自动加载图片
        settings.setLoadsImagesAutomatically(true);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        // 缩放按钮
        settings.setDisplayZoomControls(false);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setUseWideViewPort(true);//适应分辨率
        settings.setLoadWithOverviewMode(true);
    }

    public void loadHtmlStr(String str) {
        loadData(str, "text/html; charset=utf-8", "utf-8");
    }

}
