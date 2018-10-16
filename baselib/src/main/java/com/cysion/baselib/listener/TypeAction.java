package com.cysion.baselib.listener;

public interface TypeAction<T> {
    void done(T t, int type);

    TypeAction DEFAULT = new TypeAction() {
        @Override
        public void done(Object aO, int type) {
            //foo...
        }
    };
}
