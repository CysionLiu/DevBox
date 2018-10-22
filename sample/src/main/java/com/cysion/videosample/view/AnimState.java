package com.cysion.videosample.view;

public interface AnimState {
    void onStart();

    void onPaused();

    void onResumed();

    void onStopped();

    void release();
}
