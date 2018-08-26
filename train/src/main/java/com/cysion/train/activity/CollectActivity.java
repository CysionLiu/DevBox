package com.cysion.train.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.R;
import com.cysion.train.adapter.TrainAdapter;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.logic.UserLogic;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CollectActivity extends BaseActivity implements OnTypeClickListener {


    @BindView(R.id.bar_expert)
    TopBar mBarExpert;
    @BindView(R.id.rv_train)
    RecyclerView mRvTrain;

    private List<TrainCourseBean> dataList;
    private TrainAdapter mTrainAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect_list;
    }

    @Override
    protected void initView() {
        ShowUtil.darkAndWhite(this, true);
        mBarExpert.setTitle("我的收藏");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvTrain.setLayoutManager(layoutManager);
        dataList = new ArrayList<>();
        mTrainAdapter = new TrainAdapter(dataList, this, this);
        mRvTrain.setAdapter(mTrainAdapter);
    }

    @Override
    protected void initData() {
        UserLogic.obj().getColList(new PureListener<List<TrainCourseBean>>() {
            @Override
            public void done(List<TrainCourseBean> result) {
                dataList.clear();
                dataList.addAll(result);
                mTrainAdapter.notifyDataSetChanged();
            }

            @Override
            public void dont(int flag, String msg) {

            }
        });
    }

    @Override
    public void onClicked(Object obj, int position, int flag) {

    }
}
