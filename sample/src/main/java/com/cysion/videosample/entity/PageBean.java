package com.cysion.videosample.entity;

import java.io.Serializable;

public class PageBean implements Serializable {
    private Class<?> targetPage;
    private String name;

    public PageBean(Class<?> aTargetPage, String aName) {
        targetPage = aTargetPage;
        name = aName;
    }

    public Class getTargetPage() {
        return targetPage;
    }

    public void setTargetPage(Class aTargetPage) {
        targetPage = aTargetPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }
}
