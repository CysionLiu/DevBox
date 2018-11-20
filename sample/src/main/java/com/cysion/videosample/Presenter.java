package com.cysion.videosample;

import com.cysion.baselib.base.BasePresenter;
import com.cysion.baselib.net.Caller;
import com.cysion.videosample.api.API;
import com.cysion.videosample.base.BaseEntity;
import com.cysion.videosample.entity.ExpertEntity;
import com.cysion.videosample.view.iview.PresentView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter extends BasePresenter<PresentView> {

    public void getExpert(String id) {
        Caller.obj().load(API.class)
                .getExpertDetail(id)
                .enqueue(new Callback<BaseEntity<ExpertEntity>>() {
                    @Override
                    public void onResponse(retrofit2.Call<BaseEntity<ExpertEntity>> call, Response<BaseEntity<ExpertEntity>> response) {
                        if (hasTargetData(response)) {
                            IView.setExpert(response.body().getData());
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<BaseEntity<ExpertEntity>> call, Throwable t) {
                        error(400, t.getMessage());
                    }
                });

    }

    public void getExpertList() {
        Caller.obj().load(API.class)
                .getExpertList()
                .enqueue(new Callback<BaseEntity<List<ExpertEntity>>>() {
                    @Override
                    public void onResponse(Call<BaseEntity<List<ExpertEntity>>> call, Response<BaseEntity<List<ExpertEntity>>> response) {
                        if (hasTargetData(response)) {
                            IView.setExpertList(response.body().getData());
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseEntity<List<ExpertEntity>>> call, Throwable t) {
                        error(400, t.getMessage());
                    }
                });

    }

    private <T> boolean hasTargetData(Response<BaseEntity<T>> aResponse) {
        if (!isViewAttached()) {
            return false;
        }
        if (aResponse.body() == null) {
            error(500, "未获取数据");
            return false;
        }
        if (aResponse.body().getStatus() != 1||aResponse.body().getData()==null) {
            error(aResponse.body().getStatus(), aResponse.body().getMsg());
            return false;
        }
        return true;
    }
}
