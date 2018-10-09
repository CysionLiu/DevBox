package com.cysion.baselib.listener;

public interface Action<T> {
    void done(T t);
    Action FOO = new Action() {
        @Override
        public void done(Object aO) {
            //占位
        }
    };
}

