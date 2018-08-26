package com.cysion.train.helper;

import com.cysion.train.Constant;
import com.cysion.train.entity.TrainCourseBean;

import java.util.ArrayList;
import java.util.List;

public class TrainHelper {
    private static volatile TrainHelper instance;

    private TrainHelper() {

    }

    public static synchronized TrainHelper obj() {
        if (instance == null) {
            instance = new TrainHelper();
        }
        return instance;
    }

    public List<TrainCourseBean> getDemoTrains() {
        List<TrainCourseBean> courseBeans = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TrainCourseBean e1 = new TrainCourseBean();
            e1.setLocalType(Constant.RECOMMAND_LIST);
            e1.setName("这是占位这是占位");
            e1.setStart("9/11");
            e1.setTop("http://trade.5dev.cn/upload/data/upload/1/2018/08/08/4a47a0db6e60853dedfcfdf08a5ca249.png");
            e1.setCity("北京市");
            courseBeans.add(e1);
        }
        return courseBeans;
    }
}
