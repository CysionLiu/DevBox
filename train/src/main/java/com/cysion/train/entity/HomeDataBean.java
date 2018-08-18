package com.cysion.train.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 首页数据的data
 */
public class HomeDataBean implements Serializable {
    private ConfigBean config;

    private Map<String, HomeBean> home;
    private List<HomeBean> revisedHome;

    private List<StyleBean> style;

    private Map<String, ExpertBean> expert;
    private List<ExpertBean> revisedExpert;

    private Map<String, TrainOrgBean> train;
    private List<TrainOrgBean> revisedTrain;

    @SerializedName("new")
    private List<TrainCourseBean> news;
    private List<TrainCourseBean> old;

    public ConfigBean getConfig() {
        return config;
    }

    public void setConfig(ConfigBean aConfig) {
        config = aConfig;
    }

    public Map<String, HomeBean> getHome() {
        return home;
    }

    public void setHome(Map<String, HomeBean> aHome) {
        home = aHome;
    }

    public List<HomeBean> getRevisedHome() {
        return revisedHome;
    }

    public void setRevisedHome(List<HomeBean> aRevisedHome) {
        revisedHome = aRevisedHome;
    }

    public List<StyleBean> getStyle() {
        return style;
    }

    public void setStyle(List<StyleBean> aStyle) {
        style = aStyle;
    }

    public Map<String, ExpertBean> getExpert() {
        return expert;
    }

    public void setExpert(Map<String, ExpertBean> aExpert) {
        expert = aExpert;
    }

    public List<ExpertBean> getRevisedExpert() {
        return revisedExpert;
    }

    public void setRevisedExpert(List<ExpertBean> aRevisedExpert) {
        revisedExpert = aRevisedExpert;
    }

    public Map<String, TrainOrgBean> getTrain() {
        return train;
    }

    public void setTrain(Map<String, TrainOrgBean> aTrain) {
        train = aTrain;
    }

    public List<TrainOrgBean> getRevisedTrain() {
        return revisedTrain;
    }

    public void setRevisedTrain(List<TrainOrgBean> aRevisedTrain) {
        revisedTrain = aRevisedTrain;
    }

    public List<TrainCourseBean> getNews() {
        return news;
    }

    public void setNews(List<TrainCourseBean> aNews) {
        news = aNews;
    }

    public List<TrainCourseBean> getOld() {
        return old;
    }

    public void setOld(List<TrainCourseBean> aOld) {
        old = aOld;
    }
}
