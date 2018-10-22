package com.cysion.videosample.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.cysion.videosample.R;
import com.cysion.videosample.view.MyStandardPlayer;
import com.cysion.videosample.view.TimeAxiel;
import com.orhanobut.logger.Logger;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class SimplePlayActivity extends GSYBaseActivityDetail<StandardGSYVideoPlayer> {

    MyStandardPlayer videoPlayer;

    OrientationUtils orientationUtils;
    StandardGSYVideoPlayer detailPlayer;
    private TimeAxiel mTimeAxiel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_play);

        detailPlayer = (StandardGSYVideoPlayer) findViewById(R.id.sp_video);
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.GONE);

        initVideoBuilderMode();
        mTimeAxiel = (TimeAxiel) findViewById(R.id.timeaxel);

//        init();
    }

    private void init() {
        videoPlayer = findViewById(R.id.sp_video);

        String source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
//        String source1 = "http://o6rla8z20.bkt.clouddn.com/video/testDior0921.mov";
        videoPlayer.setUp(source1, true, "测试视频");

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_launcher);
        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d("fullscreen play");
//                orientationUtils.resolveByClick();
                videoPlayer.startWindowFullscreen(SimplePlayActivity.this, false, true);
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        videoPlayer.startPlayLogic();
    }

//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        videoPlayer.onVideoPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        videoPlayer.onVideoResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        GSYVideoManager.releaseAllVideos();
//        if (orientationUtils != null)
//            orientationUtils.releaseListener();
//    }

    @Override
    public StandardGSYVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //内置封面可参考SampleCoverVideo
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher);
        String source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";

//        loadCover(imageView, url);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(source1)
                .setCacheWithPlay(true)
                .setVideoTitle(" ")
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1);
    }

    @Override
    public void clickForFullScreen() {

    }

    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }

    int t = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mHandler.sendEmptyMessageDelayed(0, 1000);
        }
    };

    public void startAnim(View view) {
        mTimeAxiel.onStart();
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    public void pauseAnim(View view) {
        mTimeAxiel.onPaused();
        mHandler.removeCallbacksAndMessages(null);
    }

    public void resumeAnim(View view) {
        mTimeAxiel.onResumed();
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    public void stopAnim(View view) {
        mTimeAxiel.onStopped();
        mHandler.removeCallbacksAndMessages(null);
    }

//    @Override
//    public void onBackPressed() {
//        //先返回正常状态
//        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//            videoPlayer.getFullscreenButton().performClick();
//            return;
//        }
//        //释放所有
//        videoPlayer.setVideoAllCallBack(null);
//        super.onBackPressed();
//    }
}
