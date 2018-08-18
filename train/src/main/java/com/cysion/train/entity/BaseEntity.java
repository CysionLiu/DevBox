package com.cysion.train.entity;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    /**
     * status : 1
     * code : 1
     * msg : success
     */

    private int status;
    private int code;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
