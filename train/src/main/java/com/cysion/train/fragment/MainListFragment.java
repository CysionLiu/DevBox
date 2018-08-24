package com.cysion.train.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.cysion.baselib.Box;
import com.cysion.baselib.base.BaseFragment;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.baselib.cache.ACache;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.baselib.listener.PureListener;
import com.cysion.train.Constant;
import com.cysion.train.R;
import com.cysion.train.activity.TrainDetailActivity;
import com.cysion.train.adapter.TrainAdapter;
import com.cysion.train.entity.HomeAllDataBean;
import com.cysion.train.entity.HomeDataBean;
import com.cysion.train.entity.StyleBean;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.logic.TrainLogic;
import com.cysion.train.view.MySmartRefreshLayout;
import com.cysion.train.view.MyToast;
import com.cysion.train.view.MyTopBar;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainListFragment extends BaseFragment implements OnTypeClickListener {
    @BindView(R.id.rv_train_list)
    RecyclerView mRvTrainList;
    @BindView(R.id.rl_empty)
    RelativeLayout mRlEmpty;
    @BindView(R.id.smr_refresj)
    MySmartRefreshLayout mSmrRefresj;
    @BindView(R.id.topbar_list_meeting)
    MyTopBar mTopbarListMeeting;
    private LinearLayoutManager mLayoutManager;
    private TrainAdapter mTrainAdapter;
    private String mSearchArea;
    private String mSearchStyle;
    private String mSearchTime;
    private int mSearchType;
    private List<StyleBean> mStyleBeans = new ArrayList<>();

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
        mSmrRefresj.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
                new MyToast.Builder()
                        .gravity(Gravity.CENTER)
                        .text("这是自定义toast")
                        .buildToShow();
            }
        });
    }

    @Override
    protected void initData() {
        try {
            String body = ACache.get(Box.ctx()).getAsString(HomeFragment.HOME_DATA_CACHE);
            if (!TextUtils.isEmpty(body)) {
                HomeAllDataBean homeAllDataBean = new Gson().fromJson(body, HomeAllDataBean.class);
                HomeDataBean data = homeAllDataBean.getData();
                mStyleBeans.clear();
                mStyleBeans.addAll(data.getStyle());
            }
        } catch (Exception aE) {
        }
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getData();
    }

    private void getData() {
        TrainLogic.obj().getTrainList(new PureListener<List<TrainCourseBean>>() {
            @Override
            public void done(List<TrainCourseBean> result) {
                mSmrRefresj.finishRefresh();
                for (TrainCourseBean bean : result) {
                    bean.setLocalType(Constant.MAIN_LIST);
                }
                mTrainAdapter.setEntities(result);
                mTrainAdapter.notifyDataSetChanged();
                changeLayout();
            }

            @Override
            public void dont(int flag, String msg) {
                mSmrRefresj.finishRefresh(0, false);
                ToastUtils.showShort(msg);
                changeLayout();
            }
        }, mSearchArea, mSearchStyle, mSearchTime, mSearchType);
    }

    //列表内元素点击事件，ITEM_CLICK默认为整个item
    @Override
    public void onClicked(Object obj, int position, int flag) {
        switch (flag) {
            case BaseViewHolder.ITEM_CLICK:
                TrainCourseBean bean = (TrainCourseBean) obj;
                TrainDetailActivity.start(mActivity, "", bean);
        }
    }

    //别的页面跳入本页面，携带筛选条件
    public void fromOuter(String area, String style, String period, int type) {
        mSearchArea = area;
        mSearchStyle = style;
        mSearchTime = period;
        mSearchType = type;
        getData();
        mSearchType = 0;
        if (TextUtils.isEmpty(style)) {
            mTopbarListMeeting.setTitle(Box.str(R.string.str_style));
            return;
        }
        for (StyleBean styleBean : mStyleBeans) {
            if (style.equals(styleBean.getId())) {
                mTopbarListMeeting.setTitle(styleBean.getName());
                break;
            }
        }
    }

    //数据列视图和空视图切换
    private void changeLayout() {
        boolean hasData = mTrainAdapter.getItemCount() != 0;
        if (hasData) {
            mRlEmpty.setVisibility(View.GONE);
            mRvTrainList.setVisibility(View.VISIBLE);
        } else {
            mRlEmpty.setVisibility(View.VISIBLE);
            mRvTrainList.setVisibility(View.GONE);
        }
    }
}
