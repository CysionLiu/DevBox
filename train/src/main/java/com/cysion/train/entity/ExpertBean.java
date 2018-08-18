package com.cysion.train.entity;

import java.io.Serializable;

public class ExpertBean implements Serializable {

    /**
     * id : 5
     * name : 葛凌波
     * work : 北京读道创意旅游文化发展股份有限公司
     * head : CEO
     * top : https://trade.5dev.cn/upload/data/upload/1/2018/07/31/09dd8c2662b96ce14928333f055c5580.png
     * cate_id : 1
     * desc : <p>英国布鲁内尔大学品牌学硕士，读道文旅+橙猫网络合伙人<br></p>
     * reorder : 1
     * state : 1
     * cdate : 1533030751
     * info : 北京读道创意旅游文化发展股份有限公司,CEO
     * total : 0
     */

    private String id;
    private String name;
    private String work;
    private String head;
    private String top;
    private String cate_id;
    private String desc;
    private String reorder;
    private String state;
    private String cdate;
    private String info;
    private int total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
