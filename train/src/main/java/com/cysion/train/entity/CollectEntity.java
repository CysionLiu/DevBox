package com.cysion.train.entity;

import java.io.Serializable;

public class CollectEntity implements Serializable {

    /**
     * id : 362
     * uid : 12
     * col_id : 2
     * type : 1
     * reorder : 1
     * state : 1
     * cdate : 1535287594
     * data :
     */

    private String id;
    private String uid;
    private String col_id;
    private String type;
    private String reorder;
    private String state;
    private String cdate;

    public TrainCourseBean getData() {
        return data;
    }

    public void setData(TrainCourseBean aData) {
        data = aData;
    }

    private TrainCourseBean data;

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

    public String getCol_id() {
        return col_id;
    }

    public void setCol_id(String col_id) {
        this.col_id = col_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
