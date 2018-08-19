package com.cysion.train.fragment;

import android.view.View;
import android.widget.Button;

import com.cysion.baselib.base.BaseFragment;
import com.cysion.baselib.listener.PureListener;
import com.cysion.train.R;
import com.cysion.train.entity.HomeDataBean;
import com.cysion.train.logic.HomeLogic;
import com.orhanobut.logger.Logger;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.btnGetAll)
    Button mBtnGetAll;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        mBtnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAll(v);
            }
        });

    }

    @Override
    protected void initData() {

    }

    public void getAll(View aView) {
        HomeLogic.obj().getAllData(new PureListener<HomeDataBean>() {
            @Override
            public void done(HomeDataBean result) {
                Logger.d(result);
                Logger.d(result.getConfig().getExplain());
                Logger.d(result.getRevisedHome().get(0).getTitle());
                Logger.d(result.getRevisedExpert().get(0).getName());
                Logger.d(result.getRevisedTrain().get(1).getName());
                Logger.d(result.getStyle().get(3).getName());
                Logger.d(result.getNews().get(0).getName());
            }

            @Override
            public void dont(int flag, String msg) {
                Logger.d(msg);

            }
        });
    }
}
