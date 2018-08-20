package com.cysion.train.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 首页数据的data
 */
public class HomeDataBean implements Serializable {
    private ConfigBean config;


    private List<HomeTopBean> home;
    private List<StyleBean> style;

    private List<ExpertBean> expert;

    private List<TrainOrgBean> train;

    @SerializedName("new")
    private List<TrainCourseBean> news;
    private List<TrainCourseBean> old;

    public ConfigBean getConfig() {
        return config;
    }

    public void setConfig(ConfigBean aConfig) {
        config = aConfig;
    }

    public List<StyleBean> getStyle() {
        return style;
    }

    public void setStyle(List<StyleBean> aStyle) {
        style = aStyle;
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

    public List<HomeTopBean> getHome() {
        return home;
    }

    public void setHome(List<HomeTopBean> aHome) {
        home = aHome;
    }

    public List<ExpertBean> getExpert() {
        return expert;
    }

    public void setExpert(List<ExpertBean> aExpert) {
        expert = aExpert;
    }

    public List<TrainOrgBean> getTrain() {
        return train;
    }

    public void setTrain(List<TrainOrgBean> aTrain) {
        train = aTrain;
    }
}
