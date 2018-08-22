package com.cysion.train.entity;

import java.io.Serializable;

public class UserOptions implements Serializable {
    private String name;
    private int icon;
    private String msg1;
    private String extend;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int aType) {
        type = aType;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int aIcon) {
        icon = aIcon;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String aMsg1) {
        msg1 = aMsg1;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String aExtend) {
        extend = aExtend;
    }
}
