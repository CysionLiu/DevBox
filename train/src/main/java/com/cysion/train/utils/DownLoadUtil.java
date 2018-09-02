package com.cysion.train.utils;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import com.cysion.baselib.Box;
import com.cysion.train.view.MyToast;

import static android.content.Context.DOWNLOAD_SERVICE;

public class DownLoadUtil {
    private static volatile DownLoadUtil instance;

    private DownLoadUtil() {

    }

    public static synchronized DownLoadUtil obj() {
        if (instance == null) {
            instance = new DownLoadUtil();
        }
        return instance;
    }

    public void downloadPoster(String url) {
        if (TextUtils.isEmpty(url)) {
            MyToast.quickShow("未获得资源地址");
            return;
        }
        // 指定下载地址
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        // 允许媒体扫描，根据下载的文件类型被加入相册、音乐等媒体库
        request.allowScanningByMediaScanner();
        // 设置通知的显示类型，下载进行时和完成后显示通知
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        // 允许在计费流量下下载
        request.setAllowedOverMetered(true);
        // 允许该记录在下载管理界面可见
        request.setVisibleInDownloadsUi(true);
        // 允许漫游时下载
        request.setAllowedOverRoaming(true);
        String fileName = "cm_poster_" + System.currentTimeMillis() + url.substring(url.lastIndexOf("."));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        final DownloadManager downloadManager = (DownloadManager) Box.ctx().getSystemService(DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
        MyToast.quickShow("下载完成，请去相册查看");
    }
}
