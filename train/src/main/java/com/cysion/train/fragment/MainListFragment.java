package com.cysion.train.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.cysion.baselib.base.BaseFragment;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.baselib.listener.PureListener;
import com.cysion.train.R;
import com.cysion.train.adapter.TrainAdapter;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.logic.TrainLogic;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainListFragment extends BaseFragment implements OnTypeClickListener {
    @BindView(R.id.rv_train_list)
    RecyclerView mRvTrainList;
    @BindView(R.id.rl_empty)
    RelativeLayout mRlEmpty;
    private LinearLayoutManager mLayoutManager;
    private TrainAdapter mTrainAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initViews() {
        mLayoutManager = new LinearLayoutManager(mActivity);
        mTrainAdapter = new TrainAdapter(new ArrayList<TrainCourseBean>(), mActivity, this);
        mRvTrainList.setLayoutManager(mLayoutManager);
        mRvTrainList.setAdapter(mTrainAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        TrainLogic.obj().getTrainList(new PureListener<List<TrainCourseBean>>() {
            @Override
            public void done(List<TrainCourseBean> result) {
                mTrainAdapter.setEntities(result);
                mTrainAdapter.notifyDataSetChanged();
                changeLayout(true);

            }

            @Override
            public void dont(int flag, String msg) {
                ToastUtils.showShort(msg);
                changeLayout(false);
            }
        }, "", "", "", 0);

    }

    @Override
    public void onClicked(Object obj, int position, int flag) {
        switch (flag) {
            case BaseViewHolder.ITEM_CLICK:
                TrainCourseBean bean = (TrainCourseBean) obj;
                ToastUtils.showShort(bean.getName());
        }

    }

    private void changeLayout(boolean hasData) {
        if (hasData) {
            mRlEmpty.setVisibility(View.GONE);
            mRvTrainList.setVisibility(View.VISIBLE);
        } else {
            mRlEmpty.setVisibility(View.VISIBLE);
            mRvTrainList.setVisibility(View.GONE);
        }
    }
}
