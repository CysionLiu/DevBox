package com.cysion.videosample.view.iview;

import com.cysion.baselib.listener.IBaseView;
import com.cysion.videosample.entity.ExpertEntity;

import java.util.List;

public interface PresentView extends IBaseView {
    void setExpertList(List<ExpertEntity> datalist);

    void setExpert(ExpertEntity aEntity);
}
