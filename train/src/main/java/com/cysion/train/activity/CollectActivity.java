package com.cysion.train.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.baselib.base.BusEvent;
import com.cysion.baselib.listener.Action;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.Constant;
import com.cysion.train.PageConstant;
import com.cysion.train.R;
import com.cysion.train.adapter.TrainAdapter;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.holder.train.CollectTrainHolder;
import com.cysion.train.logic.MultiLogic;
import com.cysion.train.logic.UserLogic;
import com.cysion.train.utils.Alert;
import com.cysion.train.utils.ShareUtil;
import com.cysion.train.view.MySmartMoreLayout;
import com.cysion.train.view.MyToast;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CollectActivity extends BaseActivity implements OnTypeClickListener {


    @BindView(R.id.bar_expert)
    TopBar mBarExpert;
    @BindView(R.id.rv_train)
    RecyclerView mRvTrain;
    @BindView(R.id.smr_loadmore)
    MySmartMoreLayout mSmrLoadmore;
    @BindView(R.id.iv_empty_view)
    ImageView mIvEmptyView;
    private int pageNum = 1;

    private List<TrainCourseBean> dataList;
    private TrainAdapter mTrainAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collect_list;
    }

    @Override
    protected void initView() {
        ShowUtil.whiteStatusBar(this, true);
        mBarExpert.setTitle("我的收藏");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvTrain.setLayoutManager(layoutManager);
        dataList = new ArrayList<>();
        mTrainAdapter = new TrainAdapter(dataList, this, this);
        mRvTrain.setAdapter(mTrainAdapter);
        mBarExpert.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onIconClicked(View aView, TopBar.Pos aPosition) {
                if (aPosition == TopBar.Pos.LEFT) {
                    CollectActivity.this.finish();
                }
            }
        });
        mSmrLoadmore.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMore();
            }
        });

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
        }, pageNum);
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
                changeLayout();
            }

            @Override
            public void dont(int flag, String msg) {
                Alert.obj().loaded();
                changeLayout();
            }
        }, pageNum);
    }

    public void changeLayout() {
        if (dataList.size() > 0) {
            mSmrLoadmore.setVisibility(View.VISIBLE);
            mIvEmptyView.setVisibility(View.GONE);
        } else {
            mSmrLoadmore.setVisibility(View.GONE);
            mIvEmptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClicked(Object obj, int position, int flag) {
        TrainCourseBean bean = (TrainCourseBean) obj;
        if (BaseViewHolder.ITEM_CLICK == flag) {
            TrainDetailActivity.start(this, bean);
        } else if (CollectTrainHolder.DEL == flag) {
            delCol(bean.getId(), position);
        } else if (CollectTrainHolder.SHARE == flag) {
            toShare(bean.getId());
        }
    }


    private void delCol(String aId, final int pos) {
        UserLogic.obj().decol(aId, new PureListener<Integer>() {
            @Override
            public void done(Integer result) {
                dataList.remove(pos);
                if (mTrainAdapter != null) {
                    mTrainAdapter.notifyDataSetChanged();
                }
                //取消成功
                if (result == Constant.NOT_COLLECTED_STATE) {
                    initData();
                }
            }

            @Override
            public void dont(int flag, String msg) {
                MyToast.quickShow(msg);
            }
        });
    }

    private void toShare(final String aId) {
        createPoster(false, aId);//加速海报生成
        ShareUtil.obj().popShareWindow(this, "", new Action<String>() {
            @Override
            public void done(String result) {
                if (ShareUtil.SHARE_ERWEIMA.equals(result)) {
                    SharePosterActivity.start(CollectActivity.this, aId);
                } else if (ShareUtil.SHARE_WEIXIN.equals(result)) {
                    Alert.obj().loading(self);
                    createPoster(true, aId);
                }
            }
        });
    }

    private void createPoster(final boolean toShare, String mId) {
        MultiLogic.obj().getPoster(mId, new PureListener<String>() {
            @Override
            public void done(String result) {
                Alert.obj().loaded();
                if (toShare) {
                    ShareUtil.obj().shareToWein(self, result);
                }
            }

            @Override
            public void dont(int flag, String msg) {
                Alert.obj().loaded();
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void fromEventBus(BusEvent event) {
        if (event.getTag() == PageConstant.DEL_COLLECT) {
            getDataList();
        }
    }
}
