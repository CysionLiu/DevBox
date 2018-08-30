package com.cysion.train.entity;
//行业
public class TradeEntity {

    /**
     * id : 2
     * name : 田园
     * reorder : 1
     * state : 1
     * cdate : 1535080081
     */

    private String id;
    private String name;
    private String reorder;
    private String state;
    private String cdate;

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

    @Override
    public String toString() {
        return name;
    }
}
