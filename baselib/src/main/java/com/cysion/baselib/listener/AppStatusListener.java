package com.cysion.baselib.listener;

/**
 * Created by cysion on 2018\8\2 0002.
 * app在前后台的接口，isFront=true，说明app切换到前台
 */

public interface AppStatusListener {
    void isFront(boolean isFront);
}
