package com.cysion.baselib.listener;

/**
 * Created by cysion on 2018\7\5 0005.
 */

public interface PureListener<R> {
    void done(R result);

    void dont(int flag, String msg);
}
