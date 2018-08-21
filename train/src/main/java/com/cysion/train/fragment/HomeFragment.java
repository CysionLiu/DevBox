package com.cysion.train.fragment;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cysion.baselib.base.BaseFragment;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.baselib.listener.PureListener;
import com.cysion.train.Constant;
import com.cysion.train.PageConstant;
import com.cysion.train.R;
import com.cysion.train.activity.MainActivity;
import com.cysion.train.activity.TrainDetailActivity;
import com.cysion.train.activity.TrainOrgActivity;
import com.cysion.train.adapter.ExpertAdapter;
import com.cysion.train.adapter.HomeTopPageAdapter;
import com.cysion.train.adapter.StyleAdapter;
import com.cysion.train.adapter.TrainAdapter;
import com.cysion.train.entity.ExpertBean;
import com.cysion.train.entity.HomeDataBean;
import com.cysion.train.entity.HomeTopBean;
import com.cysion.train.entity.StyleBean;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.logic.HomeLogic;
import com.cysion.train.view.MySmartRefreshLayout;
import com.cysion.train.view.MyUltranViewPager;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.rv_top_styles)
    RecyclerView mRvTopStyles;
    @BindView(R.id.rv_train_opt)
    RecyclerView mRvTrainOpt;
    @BindView(R.id.tv_more_opt)
    TextView mTvMoreOpt;
    @BindView(R.id.rv_train_orgs)
    RecyclerView mRvTrainOrgs;
    @BindView(R.id.rv_train_experts)
    RecyclerView mRvTrainExperts;
    @BindView(R.id.rv_train_recent)
    RecyclerView mRvTrainRecent;
    @BindView(R.id.tv_more_recent)
    TextView mTvMoreRecent;
    @BindView(R.id.vp_home_top)
    MyUltranViewPager mVpHomeTop;
    @BindView(R.id.smr_refresj)
    MySmartRefreshLayout mSmrRefresj;

    private List<HomeTopBean> mHomeTopBeans = new ArrayList<>();
    private List<StyleBean> mStyleBeans = new ArrayList<>();
    private List<TrainCourseBean> mOptTrains = new ArrayList<>();
    private List<TrainCourseBean> mRecentTrains = new ArrayList<>();
    private List<ExpertBean> mExpertOrgs = new ArrayList<>();
    private List<ExpertBean> mExperts = new ArrayList<>();
    private StyleAdapter mStyleAdapter;
    private TrainAdapter mTrainAdapterOpt;
    private TrainAdapter mTrainAdapterRecent;
    private ExpertAdapter mExpertOrgAdapter;
    private ExpertAdapter mExpertAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        mSmrRefresj.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getAllData();
            }
        });
        initClick();
        initViewPager();
        initStyleList();
        initTrainOpt();
        initOrgs();
        initExperts();
        initTrainRecent();
    }

    private void initClick() {
        mTvMoreOpt.setOnClickListener(mOnClickMoreListener);
        mTvMoreRecent.setOnClickListener(mOnClickMoreListener);
    }

    //初始化顶部轮播
    private void initViewPager() {
        //UltraPagerAdapter 绑定子view到UltraViewPager
        PagerAdapter adapter = new HomeTopPageAdapter(mActivity, mHomeTopBeans);
        mVpHomeTop.setAdapter(adapter);
    }

    //初始化培训风格
    private void initStyleList() {
        mRvTopStyles.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 4);
        mRvTopStyles.setLayoutManager(gridLayoutManager);
        mStyleAdapter = new StyleAdapter(mStyleBeans, mActivity, new OnTypeClickListener() {
            @Override
            public void onClicked(Object obj, int position, int flag) {
                if (BaseViewHolder.ITEM_CLICK == flag) {
                    StyleBean bean = (StyleBean) obj;
                    ((MainActivity) mActivity).switchToList(bean.getId());
                }
            }
        });
        mRvTopStyles.setAdapter(mStyleAdapter);
    }

    //初始化优选的培训
    private void initTrainOpt() {
        mRvTrainOpt.setLayoutManager(new LinearLayoutManager(mActivity));
        mTrainAdapterOpt = new TrainAdapter(mOptTrains, mActivity, new OnTypeClickListener() {
            @Override
            public void onClicked(Object obj, int position, int flag) {
                TrainCourseBean bean = (TrainCourseBean) obj;
                TrainDetailActivity.start(mActivity, "", bean.getId());
            }
        });
        mRvTrainOpt.setAdapter(mTrainAdapterOpt);
        mRvTrainOpt.setNestedScrollingEnabled(false);
    }

    //初始化培训机构
    private void initOrgs() {
        mRvTrainOrgs.setLayoutManager(new LinearLayoutManager(mActivity, HORIZONTAL, false));
        mExpertOrgAdapter = new ExpertAdapter(mExpertOrgs, mActivity, new OnTypeClickListener() {
            @Override
            public void onClicked(Object obj, int position, int flag) {
                ExpertBean expertBean = (ExpertBean) obj;
                TrainOrgActivity.start(mActivity,  PageConstant.IS_ORG, expertBean.getId());
            }
        });
        mRvTrainOrgs.setAdapter(mExpertOrgAdapter);
        mRvTrainOrgs.setNestedScrollingEnabled(false);
    }

    //初始化专家
    private void initExperts() {
        mRvTrainExperts.setLayoutManager(new LinearLayoutManager(mActivity, HORIZONTAL, false));
        mExpertAdapter = new ExpertAdapter(mExperts, mActivity, new OnTypeClickListener() {
            @Override
            public void onClicked(Object obj, int position, int flag) {
                ExpertBean expertBean = (ExpertBean) obj;
                TrainOrgActivity.start(mActivity, PageConstant.IS_EXPERT, expertBean.getId());
            }
        });
        mRvTrainExperts.setAdapter(mExpertAdapter);
        mRvTrainExperts.setNestedScrollingEnabled(false);
    }

    //初始化近期的培训
    private void initTrainRecent() {
        mRvTrainRecent.setLayoutManager(new LinearLayoutManager(mActivity));
        mTrainAdapterRecent = new TrainAdapter(mRecentTrains, mActivity, new OnTypeClickListener() {
            @Override
            public void onClicked(Object obj, int position, int flag) {
                TrainCourseBean bean = (TrainCourseBean) obj;
                TrainDetailActivity.start(mActivity, "", bean.getId());
            }
        });
        mRvTrainRecent.setAdapter(mTrainAdapterRecent);
        mRvTrainRecent.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {
        getAllData();
    }

    public void getAllData() {
        HomeLogic.obj().getAllData(new PureListener<HomeDataBean>() {
            @Override
            public void done(HomeDataBean result) {
                mSmrRefresj.finishRefresh();
                Logger.d(result);
                //轮播图
                List<HomeTopBean> revisedHome = result.getHome();
                mHomeTopBeans.clear();
                mHomeTopBeans.addAll(revisedHome);
                mVpHomeTop.refresh();
                //样式列表
                List<StyleBean> styleBeans = result.getStyle();
                mStyleBeans.clear();
                mStyleBeans.addAll(styleBeans);
                mStyleAdapter.notifyDataSetChanged();
                //优选培训
                List<TrainCourseBean> news = result.getNews();
                for (TrainCourseBean bean : news) {
                    bean.setLocalType(Constant.HOME_LIST);
                }
                mOptTrains.clear();
                mOptTrains.addAll(news);
                mTrainAdapterOpt.notifyDataSetChanged();
                //近期培训
                List<TrainCourseBean> old = result.getOld();
                for (TrainCourseBean bean : old) {
                    bean.setLocalType(Constant.HOME_LIST);
                }
                mRecentTrains.clear();
                mRecentTrains.addAll(old);
                mTrainAdapterRecent.notifyDataSetChanged();
                //机构简介
                List<ExpertBean> trainOrg = result.getTrain();
                mExpertOrgs.clear();
                mExpertOrgs.addAll(trainOrg);
                mExpertOrgAdapter.notifyDataSetChanged();
                //专家简介
                List<ExpertBean> expert = result.getExpert();
                mExperts.clear();
                mExperts.addAll(expert);
                mExpertAdapter.notifyDataSetChanged();
            }

            @Override
            public void dont(int flag, String msg) {
                Logger.d(msg);
                mSmrRefresj.finishRefresh(0, false);
            }
        });
    }

    private View.OnClickListener mOnClickMoreListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity) mActivity).switchToList("");
        }
    };
}
