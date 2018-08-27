package com.cysion.train.fragment;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
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
import com.cysion.train.entity.AreaBean;
import com.cysion.train.entity.HomeAllDataBean;
import com.cysion.train.entity.HomeDataBean;
import com.cysion.train.entity.StyleBean;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.logic.TrainLogic;
import com.cysion.train.utils.AreaUtil;
import com.cysion.train.view.MySmartRefreshLayout;
import com.cysion.train.view.MyToast;
import com.cysion.train.view.MyTopBar;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 主页--会议列表页
 */

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
    private OptionsPickerView mAreaPvOptions;
    private OptionsPickerView mStylePcOptions;
    private OptionsPickerView mPeriodPcOptions;
    private List<String> mPeriod = new ArrayList<>();

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
            }
        });
        mTopbarListMeeting.setOnTopBarClickListener(new MyTopBar.OnTopBarClickListener() {
            @Override
            public void onIconClicked(View aView, MyTopBar.Pos aPosition) {
                switch (aPosition) {
                    //城市
                    case LEFT:
                        onAreaClicked();
                        break;
                    //类型
                    case MIDDLE:
                        onStyleClicked();
                        break;
                    default:
                        onPeriodClicked();
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        //读取Style列表的缓存数据
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

    //远程获取数据
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
                new MyToast.Builder().text(msg).buildToShow();
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
                TrainDetailActivity.start(mActivity, bean);
                break;
            default:
                break;
        }
    }

    //当地区按钮点击
    private void onAreaClicked() {
        if (mAreaPvOptions == null) {
            //返回的分别是三个级别的选中位置
            mAreaPvOptions = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    AreaBean p = AreaUtil.obj().getProvince().get(options1);
                    if (p != null) {
                        mTopbarListMeeting.setLeftText(p.getName());
                        mSearchArea = p.getId();
                    }
                    AreaBean city = AreaUtil.obj().getCities().get(options1).get(option2);
                    if (city != null && option2 > 0) {
                        mTopbarListMeeting.setLeftText(city.getName());
                        mSearchArea = city.getId();
                    }
                    AreaBean county = AreaUtil.obj().getCounties().get(options1).get(option2).get(options3);
                    if (county != null && options3 > 0) {
                        mTopbarListMeeting.setLeftText(county.getName());
                        mSearchArea = county.getId();
                    }
                    getData();
                }
            }).setCancelColor(Color.GRAY).build();
            mAreaPvOptions.setPicker(AreaUtil.obj().getProvince(), AreaUtil.obj().getCities()
                    , AreaUtil.obj().getCounties());
        }
        mAreaPvOptions.show();
    }

    //当类型按钮被点击
    private void onStyleClicked() {
        if (mStylePcOptions == null) {
            mStylePcOptions = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    StyleBean bean = mStyleBeans.get(options1);
                    mSearchStyle = bean.getId();
                    getData();
                    if (options1 > 0) {
                        mTopbarListMeeting.setTitle(bean.getName());
                    } else {
                        mTopbarListMeeting.setTitle(Box.str(R.string.str_style));
                    }
                }
            }).setCancelColor(Color.GRAY).build();
            if (mStyleBeans != null) {
                StyleBean bean = new StyleBean();
                bean.setId("");
                bean.setName("不限");
                mStyleBeans.add(0, bean);
                mStylePcOptions.setPicker(mStyleBeans);
            }
        }
        mStylePcOptions.show();
    }

    //时段按钮被点击
    private void onPeriodClicked() {
        if (mPeriodPcOptions == null) {
            mPeriodPcOptions = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {

                }
            }).setCancelColor(Color.GRAY).build();
            mPeriod.add("不限");
            mPeriodPcOptions.setPicker(mPeriod);
        }
        mPeriodPcOptions.show();
    }

    //别的页面跳入本页面，携带筛选条件
    public void fromOuter(String style, String period, int type) {
        mSearchArea = "";
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
        mTopbarListMeeting.setLeftText(Box.str(R.string.str_area));
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
