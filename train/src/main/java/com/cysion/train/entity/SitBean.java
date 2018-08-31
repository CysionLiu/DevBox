package com.cysion.train.entity;

import java.io.Serializable;

//会议座位类型
public class SitBean implements Serializable {

    /**
     * id : 22
     * uid : 0
     * meet_id : 21
     * sitname : 普通坐席
     * common :
     * name : null
     * price : 2200
     * now_price : 1800
     * low_price : null
     * num : 100
     * end : 2018.10.20
     * reorder : 1
     * state : 1
     * cdate : 1535615758
     * zhe : 8.18
     */

    private String id;
    private String uid;
    private String meet_id;
    private String sitname;
    private String common;
    private String name;
    private String price;
    private String now_price;
    private String low_price;
    private String num;
    private String end;
    private String reorder;
    private String state;
    private String cdate;
    private double zhe;
    private int nativecount=0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMeet_id() {
        return meet_id;
    }

    public void setMeet_id(String meet_id) {
        this.meet_id = meet_id;
    }

    public String getSitname() {
        return sitname;
    }

    public void setSitname(String sitname) {
        this.sitname = sitname;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }


    public int getNativecount() {
        return nativecount;
    }

    public void setNativecount(int aNativecount) {
        nativecount = aNativecount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNow_price() {
        return now_price;
    }

    public void setNow_price(String now_price) {
        this.now_price = now_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public String getLow_price() {
        return low_price;
    }

    public void setLow_price(String aLow_price) {
        low_price = aLow_price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getReorder() {
        return reorder;
    }

    public void setReorder(String reorder) {
        this.reorder = reorder;
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

    public double getZhe() {
        return zhe;
    }

    public void setZhe(double zhe) {
        this.zhe = zhe;
    }
}
