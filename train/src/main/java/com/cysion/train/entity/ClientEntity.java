package com.cysion.train.entity;
//客户信息，主要是行业，发票，联系人
public class ClientEntity {


    /**
     * id : 4
     * uid : 12
     * trade_id : 1
     * name : 刘三
     * phone : 18511094185
     * bill : 1
     * bill_name : 北京风华
     * bill_num : 43234234
     * reorder : 1
     * state : 1
     * cdate : 1535611300
     */

    private String id;
    private String uid;
    private String trade_id;
    private String name;
    private String phone;
    private String bill;
    private String bill_name;
    private String bill_num;
    private String reorder;
    private String state;
    private String cdate;

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

    public String getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(String trade_id) {
        this.trade_id = trade_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getBill_name() {
        return bill_name;
    }

    public void setBill_name(String bill_name) {
        this.bill_name = bill_name;
    }

    public String getBill_num() {
        return bill_num;
    }

    public void setBill_num(String bill_num) {
        this.bill_num = bill_num;
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
}
