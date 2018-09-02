package com.cysion.train.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.R;
import com.cysion.train.adapter.TrainAdapter;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.logic.UserLogic;
import com.cysion.train.utils.Alert;
import com.cysion.train.view.MySmartMoreLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyEnrollActivity extends BaseActivity implements OnTypeClickListener {


    @BindView(R.id.bar_expert)
    TopBar mBarExpert;
    @BindView(R.id.rv_train)
    RecyclerView mRvTrain;
    @BindView(R.id.smr_loadmore)
    MySmartMoreLayout mSmrLoadmore;
    @BindView(R.id.tv_look)
    TextView mTvLook;
    private int pageNum = 1;

    private List<TrainCourseBean> dataList;
    private TrainAdapter mTrainAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enroll_list;
    }

    @Override
    protected void initView() {
        ShowUtil.darkAndWhite(this, true);
        initTopBar();
        initRvList();
        initEvent();

    }

    private void initEvent() {
        mTvLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MyEnrollActivity.this, PayDescActivity.class);
                startActivity(myIntent);
            }
        });
        mSmrLoadmore.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }

    private void initRvList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvTrain.setLayoutManager(layoutManager);
        dataList = new ArrayList<>();
        mTrainAdapter = new TrainAdapter(dataList, this, this);
        mRvTrain.setAdapter(mTrainAdapter);
    }

    private void initTopBar() {
        mBarExpert.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onIconClicked(View aView, TopBar.Pos aPosition) {
                finish();
            }
        });
        mBarExpert.setTitle("我的报名");
    }
    @Override
    protected void initData() {
        Alert.obj().loading(this);
        getDataList();
    }

    private void getDataList() {
        pageNum = 1;
        UserLogic.obj().getColList(new PureListener<List<TrainCourseBean>>() {
            @Override
            public void done(List<TrainCourseBean> result) {
                Alert.obj().loaded();
                dataList.clear();
                dataList.addAll(result);
                mTrainAdapter.notifyDataSetChanged();
                pageNum++;
            }

            @Override
            public void dont(int flag, String msg) {
                Alert.obj().loaded();
            }
        },pageNum);
    }
    private void loadMore() {
        UserLogic.obj().getColList(new PureListener<List<TrainCourseBean>>() {
            @Override
            public void done(List<TrainCourseBean> result) {
                mSmrLoadmore.finishLoadMore();
                mTrainAdapter.addEntities(result);
                mTrainAdapter.notifyDataSetChanged();
                pageNum++;
            }

            @Override
            public void dont(int flag, String msg) {
                mSmrLoadmore.finishLoadMore();
            }
        },pageNum);
    }
    @Override
    public void onClicked(Object obj, int position, int flag) {

    }
}
