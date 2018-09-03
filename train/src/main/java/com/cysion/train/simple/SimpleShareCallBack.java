package com.cysion.train.simple;

import com.cysion.train.view.MyToast;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class SimpleShareCallBack implements UMShareListener {
    @Override
    public void onStart(SHARE_MEDIA aSHARE_media) {

    }

    @Override
    public void onResult(SHARE_MEDIA aSHARE_media) {
        MyToast.quickShow("分享成功");
    }

    @Override
    public void onError(SHARE_MEDIA aSHARE_media, Throwable aThrowable) {
        MyToast.quickShow("分享失败");
    }

    @Override
    public void onCancel(SHARE_MEDIA aSHARE_media) {
        MyToast.quickShow("取消分享");

    }
}
