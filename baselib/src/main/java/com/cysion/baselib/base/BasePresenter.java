package com.cysion.baselib.base;

import com.cysion.baselib.listener.IBaseView;

public class BasePresenter<V extends IBaseView> {
    protected V IView;

    public void attachView(V view) {
        IView = view;
    }

    public void detachView() {

    }

    public boolean isViewAttached() {
        return IView != null;
    }

    protected V getIView() {
        return IView;
    }

    protected void error(int errorCode, String msg) {
        if (isViewAttached()) {
            IView.error(errorCode, msg);
        }
    }
}
