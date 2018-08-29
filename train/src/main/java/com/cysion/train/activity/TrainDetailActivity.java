package com.cysion.train.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.IntentUtils;
import com.cysion.baselib.Box;
import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.base.BusEvent;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.Constant;
import com.cysion.train.PageConstant;
import com.cysion.train.R;
import com.cysion.train.adapter.TrainAdapter;
import com.cysion.train.adapter.TrainDetailPageAdapter;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.helper.LoginHelper;
import com.cysion.train.logic.TrainLogic;
import com.cysion.train.logic.UserLogic;
import com.cysion.train.view.MyToast;
import com.cysion.train.view.MyUltranViewPager;
import com.cysion.train.view.SimpleWebview;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TrainDetailActivity extends BaseActivity implements View.OnClickListener {

    public static final String TRAIN_INFO = "https://trade.5dev.cn/cultivate/main/?l=index&id=";

    @BindView(R.id.bar_train)
    TopBar mBarTrain;
    @BindView(R.id.vp_train_detail)
    MyUltranViewPager mVpTrainDetail;
    @BindView(R.id.tv_train_name)
    TextView mTvTrainName;
    @BindView(R.id.tv_train_style)
    TextView mTvTrainStyle;
    @BindView(R.id.tv_train_tags)
    TextView mTvTrainTags;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.web_simple)
    SimpleWebview mWebSimple;
    @BindView(R.id.iv_to_share)
    ImageView mIvToShare;
    @BindView(R.id.tv_collect)
    TextView mTvCollect;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_enroll)
    TextView mTvEnroll;
    @BindView(R.id.ll_bar_bottom)
    LinearLayout mLlBarBottom;
    @BindView(R.id.tv_enroll_end)
    TextView mTvEnrollEnd;
    @BindView(R.id.iv_time)
    ImageView mIvTime;
    @BindView(R.id.tv_time_fix)
    TextView mTvTimeFix;
    @BindView(R.id.iv_address)
    ImageView mIvAddress;
    @BindView(R.id.tv_address_fix)
    TextView mTvAddressFix;
    @BindView(R.id.iv_price)
    ImageView mIvPrice;
    @BindView(R.id.tv_price_fix)
    TextView mTvPriceFix;
    @BindView(R.id.rv_recommand)
    RecyclerView mRvRecommand;
    @BindView(R.id.rl_address_box)
    RelativeLayout mRlAddressBox;

    private String mId;
    private TrainCourseBean mCurCourseBean;

    //type，机构or专家；id，打开本页面
    public static void start(Activity aActivity, TrainCourseBean aTrainCourseBean) {
        Intent myIntent = new Intent(aActivity, TrainDetailActivity.class);
        myIntent.putExtra(PageConstant.TRAIN_ENTITY, aTrainCourseBean);
        aActivity.startActivity(myIntent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_train_detail;
    }

    @Override
    protected void initView() {
        ShowUtil.darkAndWhite(this, true);
        Intent intent = getIntent();
        TrainCourseBean courseBean = null;
        if (intent != null) {
            courseBean = (TrainCourseBean) intent.getSerializableExtra(PageConstant.TRAIN_ENTITY);
            mId = courseBean.getId();
        }
        mBarTrain.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onIconClicked(View aView, TopBar.Pos aPosition) {
                if (aPosition == TopBar.Pos.LEFT) {
                    TrainDetailActivity.this.finish();
                }
            }
        });
        mBarTrain.setTitle(getString(R.string.str_meeting_detail));
        mWebSimple.loadUrl(TRAIN_INFO + mId);
        mVpTrainDetail.setAutoScroll(100000000);
        setupData(courseBean);
        mTvCollect.setOnClickListener(this);
        mIvToShare.setOnClickListener(this);
        mTvPhone.setOnClickListener(this);
        mTvAddress.setOnClickListener(this);
        mTvEnroll.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        //获取用户详细数据
        TrainLogic.obj().getTrainDetail(mId, new PureListener<TrainCourseBean>() {
            @Override
            public void done(TrainCourseBean result) {
                setupData(result);
            }

            @Override
            public void dont(int flag, String msg) {
                Toast.makeText(TrainDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        //获取推荐会议
        TrainLogic.obj().getTrainRecommand(mId, new PureListener<List<TrainCourseBean>>() {
            @Override
            public void done(List<TrainCourseBean> result) {
                for (TrainCourseBean courseBean : result) {
                    courseBean.setLocalType(Constant.RECOMMAND_LIST);
                }
                GridLayoutManager gridLayoutManager = new GridLayoutManager(TrainDetailActivity.this, 2);
                mRvRecommand.setLayoutManager(gridLayoutManager);
                mRvRecommand.setAdapter(new TrainAdapter(result, TrainDetailActivity.this, new OnTypeClickListener() {
                    @Override
                    public void onClicked(Object obj, int position, int flag) {
                        TrainCourseBean bean = (TrainCourseBean) obj;
                        TrainDetailActivity.start(TrainDetailActivity.this, bean);
                    }
                }));
            }

            @Override
            public void dont(int flag, String msg) {
            }
        });
    }

    //更新数据
    private void setupData(TrainCourseBean courseBean) {
        if (courseBean == null) {
            return;
        }
        mCurCourseBean = courseBean;
        String style = mCurCourseBean.getStyle();
        if (TextUtils.isEmpty(style)) {
            style = "";
        }
        mTvTrainStyle.setText(style);
        int width = (int) mTvTrainStyle.getPaint().measureText(style);
        int c = (int) (width / (4 * Box.density()));
        String div = "  ";//padding
        for (int i = 0; i < c; i++) {
            div = div + " ";
        }
        mTvTrainName.setText(div + mCurCourseBean.getName());
        resetColState();
        //标签
        String tagStr = "";
        List<TrainCourseBean.TagsBean> tags = mCurCourseBean.getTags();
        if (tags != null) {
            for (TrainCourseBean.TagsBean tag : tags) {
                tagStr += " · " + tag.getName();
            }
        }
        if (tagStr.length() > 3) {
            tagStr = tagStr.substring(3);
        }
        mTvTrainTags.setText(tagStr);
        List<String> images = new ArrayList<>();
        images.add(mCurCourseBean.getPic());
        PagerAdapter adapter = new TrainDetailPageAdapter(this, images);
        mVpTrainDetail.setAdapter(adapter);
        mTvTime.setText(mCurCourseBean.getStart() + " - " + mCurCourseBean.getEnd());
        String map = mCurCourseBean.getMap();
        if (TextUtils.isEmpty(map)) {
            mRlAddressBox.setVisibility(View.GONE);
        } else {
            mRlAddressBox.setVisibility(View.VISIBLE);
            String loc = map.split(",")[0];
            mTvAddress.setText(loc);
        }
        if (mCurCourseBean.getPrice() != null) {
            mTvPrice.setText(mCurCourseBean.getPrice().getMin());
        }
    }

    private void resetColState() {
        mTvCollect.setSelected(false);
        if (mCurCourseBean.getStates() == Constant.COLLECTED_STATE) {
            mTvCollect.setSelected(true);
        } else {
            EventBus.getDefault().post(new BusEvent().tag(PageConstant.DEL_COLLECT));
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.tv_collect:
                //是否登录
                if (LoginHelper.obj().toLoginPage(this)) {
                    return;
                }
                if (mCurCourseBean.getStates() != Constant.COLLECTED_STATE) {
                    UserLogic.obj().col(mCurCourseBean.getId(), colListener);
                } else {
                    UserLogic.obj().decol(mCurCourseBean.getId(), colListener);
                }
                break;
            case R.id.iv_to_share:
                MyToast.quickShow("功能未完成");
                break;
            case R.id.tv_phone:
                Intent myIntent = IntentUtils.getDialIntent(Constant.HOTLINE_NUMBER);
                startActivity(myIntent);
                break;
            case R.id.tv_enroll:
                //是否登录
                if (LoginHelper.obj().toLoginPage(this)) {
                    return;
                }
                MyToast.quickShow("功能未完成");
                break;
            case R.id.tv_address:
                String map = mCurCourseBean.getMap();
                Logger.d(map);
                String[] split = map.split(",");
                if (split.length >= 3) {
                    MapActivity.start(this, split[0], split[1], split[2]);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == Constant.LOGIN_REQ) {
            initData();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        resetColState();
    }

    //收藏和取消收藏 回调
    private PureListener<Integer> colListener = new PureListener<Integer>() {

        @Override
        public void done(Integer result) {
            mCurCourseBean.setStates(result);
            resetColState();
        }

        @Override
        public void dont(int flag, String msg) {
            Toast.makeText(TrainDetailActivity.this, msg, Toast.LENGTH_SHORT).show();

        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void fromEventBus(BusEvent event) {
        if (event.getTag() == PageConstant.LOGIN_SUCCESS) {
            initData();
        }
    }
}
