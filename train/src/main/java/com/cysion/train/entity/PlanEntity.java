package com.cysion.train.entity;

import java.io.Serializable;

public class PlanEntity implements Serializable {

    /**
     * id : 1
     * name : 方案一
     * reorder : 1
     * state : 1
     * cdate : 1535681094
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
