package com.cysion.baselib.base;

import java.util.HashMap;
import java.util.Map;

//EventBus的消息
public class BusEvent {
    private int tag;
    private int arg;
    private String str;
    private Object obj;
    private Map<String, Object> extend = new HashMap<>();

    public BusEvent tag(int aTag) {
        tag = aTag;
        return this;
    }

    public BusEvent arg(int aArg) {
        arg = aArg;
        return this;
    }

    public BusEvent str(String aStr) {
        str = aStr;
        return this;
    }

    public BusEvent obj(Object aO) {
        obj = aO;
        return this;
    }

    public int getTag() {
        return tag;
    }

    public int getArg() {
        return arg;
    }

    public String getStr() {
        return str;
    }

    public Object getObj() {
        return obj;
    }

    public BusEvent put(String key, Object object) {
        extend.put(key, object);
        return this;
    }

    public Object get(String key) {
        return extend.get(key);
    }
}
