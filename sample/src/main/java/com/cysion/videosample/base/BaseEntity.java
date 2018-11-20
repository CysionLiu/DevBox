package com.cysion.videosample.base;

import java.io.Serializable;

public class BaseEntity<T> implements Serializable {
    private int status;
    private int code;
    private String msg;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int aStatus) {
        status = aStatus;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int aCode) {
        code = aCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String aMsg) {
        msg = aMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T aData) {
        data = aData;
    }
}
