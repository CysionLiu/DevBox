package com.cysion.train.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.cysion.baselib.base.BaseFragment;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.baselib.listener.PureListener;
import com.cysion.train.Constant;
import com.cysion.train.R;
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
import com.cysion.train.view.MyUltranViewPager;
import com.orhanobut.logger.Logger;

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
        initViewPager();
        initStyleList();
        initTrainOpt();
        initTrainRecent();
        initOrgs();
        initExperts();

    }

    private void initExperts() {
        mRvTrainExperts.setLayoutManager(new LinearLayoutManager(mActivity, HORIZONTAL, false));
        mExpertAdapter = new ExpertAdapter(mExperts, mActivity, new OnTypeClickListener() {
            @Override
            public void onClicked(Object obj, int position, int flag) {

            }
        });
        mRvTrainExperts.setAdapter(mExpertAdapter);
        mRvTrainExperts.setNestedScrollingEnabled(false);
    }

    private void initOrgs() {
        mRvTrainOrgs.setLayoutManager(new LinearLayoutManager(mActivity, HORIZONTAL, false));
        mExpertOrgAdapter = new ExpertAdapter(mExpertOrgs, mActivity, new OnTypeClickListener() {
            @Override
            public void onClicked(Object obj, int position, int flag) {

            }
        });
        mRvTrainOrgs.setAdapter(mExpertOrgAdapter);
        mRvTrainOrgs.setNestedScrollingEnabled(false);
    }

    private void initTrainRecent() {
        mRvTrainRecent.setLayoutManager(new LinearLayoutManager(mActivity));
        mTrainAdapterRecent = new TrainAdapter(mRecentTrains, mActivity, new OnTypeClickListener() {
            @Override
            public void onClicked(Object obj, int position, int flag) {

            }
        });
        mRvTrainRecent.setAdapter(mTrainAdapterRecent);
        mRvTrainRecent.setNestedScrollingEnabled(false);
    }

    private void initTrainOpt() {
        mRvTrainOpt.setLayoutManager(new LinearLayoutManager(mActivity));
        mTrainAdapterOpt = new TrainAdapter(mOptTrains, mActivity, new OnTypeClickListener() {
            @Override
            public void onClicked(Object obj, int position, int flag) {

            }
        });
        mRvTrainOpt.setAdapter(mTrainAdapterOpt);
        mRvTrainOpt.setNestedScrollingEnabled(false);

    }

    private void initStyleList() {
        mRvTopStyles.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 4);
        mRvTopStyles.setLayoutManager(gridLayoutManager);
        mStyleAdapter = new StyleAdapter(mStyleBeans, mActivity, new OnTypeClickListener() {
            @Override
            public void onClicked(Object obj, int position, int flag) {
                if (BaseViewHolder.ITEM_CLICK == flag) {
                    StyleBean bean = (StyleBean) obj;
                    ToastUtils.showShort(bean.getName());
                }
            }
        });
        mRvTopStyles.setAdapter(mStyleAdapter);
    }

    private void initViewPager() {
        //UltraPagerAdapter 绑定子view到UltraViewPager
        PagerAdapter adapter = new HomeTopPageAdapter(mActivity, mHomeTopBeans);
        mVpHomeTop.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        getAllData();
    }

    public void getAllData() {
        HomeLogic.obj().getAllData(new PureListener<HomeDataBean>() {
            @Override
            public void done(HomeDataBean result) {
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

            }
        });
    }
}
