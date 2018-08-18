package com.cysion.videosample.entity;

import java.util.List;
import java.util.Map;

public class ExpertMapEntity extends BaseEntity {
    private Map<String,ExpertEntity> data;
    private List<ExpertEntity> revisedData;

    public List<ExpertEntity> getRevisedData() {
        return revisedData;
    }

    public void setRevisedData(List<ExpertEntity> aRevisedData) {
        revisedData = aRevisedData;
    }

    public Map<String, ExpertEntity> getData() {
        return data;
    }

    public void setData(Map<String, ExpertEntity> aData) {
        data = aData;
    }
}
