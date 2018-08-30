package com.cysion.baselib.listener;

/**
 * Created by cysion on 2018\7\5 0005.
 */

public interface PureListener<R> {
    void done(R result);

    void dont(int flag, String msg);

    //纯占位
    PureListener DEFAULT = new PureListener() {

        @Override
        public void done(Object result) {

        }

        @Override
        public void dont(int flag, String msg) {

        }
    };
}