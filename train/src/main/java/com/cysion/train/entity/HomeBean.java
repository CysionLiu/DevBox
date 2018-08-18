package com.cysion.train.entity;


import java.io.Serializable;

//首页轮播-单个对象
public class HomeBean implements Serializable {

    /**
     * id : 11
     * title : 最后的晚餐
     * top : http://trade.5dev.cn/upload/data/upload/1/2018/08/09/785435a7f043c9d3f1d15b82b0b4eb4d.png
     * link :
     * state : 1
     * cdate : 1533792347
     */

    private String id;
    private String title;
    private String top;
    private String link;
    private String state;
    private String cdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
