package com.cysion.train.entity;

import java.io.Serializable;
//我的报名列表
public class EnrollEntity implements Serializable {

    /**
     * id : 332
     * uid : 12
     * share_id : 12
     * goods : 1536376269381008689
     * pay_id : 0
     * name : 测试人员
     * phone : 18511094185
     * start : 1536652547
     * numer : null
     * amount : 6
     * product_name : 【无动力亲子乐园的投资运营之道】研学营第三季
     * product_id : 4
     * discount_id : 0
     * agent_id : 1
     * type_id : 1
     * price : 58800
     * now_price : 48260
     * low_price : null
     * ordertype : 1
     * payment : 3
     * remark : null
     * state : 1
     * cdate : 1536376269
     * tong : 1
     * bill : 2
     * bill_name : 测试
     * bill_num :
     * order_type : 1
     */

    private String id;
    private String uid;
    private String share_id;
    private String goods;
    private String pay_id;
    private String name;
    private String phone;
    private String start;
    private String numer;
    private String amount;
    private String product_name;
    private String product_id;
    private String discount_id;
    private String agent_id;
    private String type_id;
    private String price;
    private String now_price;
    private String low_price;
    private String ordertype;
    private String payment;
    private String remark;
    private String state;
    private String cdate;
    private String tong;
    private String bill;
    private String bill_name;
    private String bill_num;
    private String order_type;
    private TrainCourseBean meeting;

    public String getNumer() {
        return numer;
    }

    public void setNumer(String aNumer) {
        numer = aNumer;
    }

    public String getLow_price() {
        return low_price;
    }

    public void setLow_price(String aLow_price) {
        low_price = aLow_price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String aRemark) {
        remark = aRemark;
    }

    public TrainCourseBean getMeeting() {
        return meeting;
    }

    public void setMeeting(TrainCourseBean aMeeting) {
        meeting = aMeeting;
    }

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

    public String getShare_id() {
        return share_id;
    }

    public void setShare_id(String share_id) {
        this.share_id = share_id;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(String discount_id) {
        this.discount_id = discount_id;
    }

    public String getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(String agent_id) {
        this.agent_id = agent_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
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


    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
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

    public String getTong() {
        return tong;
    }

    public void setTong(String tong) {
        this.tong = tong;
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

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }
}
