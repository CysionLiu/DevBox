package com.cysion.train.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.baselib.utils.ViewUtil;
import com.cysion.train.Constant;
import com.cysion.train.PageConstant;
import com.cysion.train.R;
import com.cysion.train.adapter.TrainAdapter;
import com.cysion.train.entity.ExpertBean;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.logic.TrainLogic;
import com.cysion.train.view.SimpleWebview;

import java.util.List;

import butterknife.BindView;

/**
 * 机构/专家详情页面
 */
public class TrainOrgActivity extends BaseActivity {

    //专家详情介绍
    public static final String EXPERT_URL = "https://trade.5dev.cn/cultivate/main/?l=details1&id=";
    //机构详情介绍--details后面没有1
    public static final String ORG_URL = "https://trade.5dev.cn/cultivate/main/?l=details&id=";
    @BindView(R.id.bar_expert)
    TopBar mBarExpert;
    @BindView(R.id.rv_recent_train)
    RecyclerView mRvRecentTrain;
    @BindView(R.id.iv_expert_logo)
    ImageView mIvExpertLogo;
    @BindView(R.id.tv_expert_name)
    TextView mTvExpertName;
    @BindView(R.id.tv_info)
    TextView mTvInfo;
    @BindView(R.id.tv_meeting_oper)
    TextView mTvMeetingOper;
    @BindView(R.id.tv_meeting_count)
    TextView mTvMeetingCount;
    @BindView(R.id.web_simple)
    SimpleWebview mWebSimple;
    @BindView(R.id.tv_meeting_list_name)
    TextView mTvMeetingListName;
    @BindView(R.id.ll_meeting_box)
    LinearLayout mLlMeetingBox;
    private String mId;
    private String mType;

    //type，机构or专家；id
    public static void start(Activity aActivity, String type, String id) {
        Intent myIntent = new Intent(aActivity, TrainOrgActivity.class);
        myIntent.putExtra(PageConstant.EXPERT_TYPE, type);
        myIntent.putExtra(PageConstant.EXPERT_OR_ORG_ID, id);
        aActivity.startActivity(myIntent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_expert_detail;
    }

    @Override
    protected void initView() {
        ShowUtil.darkAndWhite(this, true);
        Intent intent = getIntent();
        if (intent != null) {
            mId = intent.getStringExtra(PageConstant.EXPERT_OR_ORG_ID);
            mType = intent.getStringExtra(PageConstant.EXPERT_TYPE);
        }
        mBarExpert.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onIconClicked(View aView, TopBar.Pos aPosition) {
                if (aPosition == TopBar.Pos.LEFT) {
                    TrainOrgActivity.this.finish();
                }
            }
        });
    }

    @Override
    protected void initData() {
        if (PageConstant.IS_EXPERT.equals(mType)) {
            mBarExpert.setTitle("专家介绍");
            mWebSimple.loadUrl(EXPERT_URL + mId);
            TrainLogic.obj().getExpertDetail(mId, mBeanPureListener);
        } else {
            mBarExpert.setTitle("培训机构");
            mWebSimple.loadUrl(ORG_URL + mId);
            TrainLogic.obj().getOrgDetail(mId, mBeanPureListener);
        }
    }

    private PureListener<ExpertBean> mBeanPureListener = new PureListener<ExpertBean>() {
        @Override
        public void done(ExpertBean result) {
            mTvExpertName.setText(result.getName());
            mTvMeetingCount.setText(result.getTotal() + "");
            if (PageConstant.IS_EXPERT.equals(mType)) {
                if (setDataForExpert(result)) return;
            } else {
                Glide.with(TrainOrgActivity.this)
                        .load(result.getLogo()).into(mIvExpertLogo);
                mTvInfo.setText(result.getWork());
                mTvMeetingOper.setText("举办会议");
                List<TrainCourseBean> open = result.getOpen();
                if (open != null && open.size() > 0) {
                    for (TrainCourseBean bean : open) {
                        bean.setLocalType(Constant.ORG_LIST);
                    }
                    mRvRecentTrain.setLayoutManager(new LinearLayoutManager(TrainOrgActivity.this));
                    TrainAdapter adapter = new TrainAdapter(open, TrainOrgActivity.this, new OnTypeClickListener() {
                        @Override
                        public void onClicked(Object obj, int position, int flag) {
                            TrainCourseBean bean = (TrainCourseBean) obj;
                            TrainDetailActivity.start(TrainOrgActivity.this, "", bean);
                        }
                    });
                    mRvRecentTrain.setAdapter(adapter);
                } else {
                    mLlMeetingBox.setVisibility(View.GONE);
                }
// TODO: 2018\8\23 0023 过往会议。。。
            }

        }

        @Override
        public void dont(int flag, String msg) {
            Toast.makeText(TrainOrgActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };

    private boolean setDataForExpert(ExpertBean result) {
        Glide.with(TrainOrgActivity.this)
                .load(result.getTop()).into(mIvExpertLogo);
        mTvInfo.setText(result.getInfo());
        mTvMeetingOper.setText("参与会议");
        List<TrainCourseBean> list = result.getList();
        if (list == null || list.size() == 0) {
            mLlMeetingBox.setVisibility(View.GONE);
            return true;
        }
        for (TrainCourseBean bean : list) {
            bean.setLocalType(Constant.ORG_LIST);
        }
        mRvRecentTrain.setLayoutManager(new LinearLayoutManager(TrainOrgActivity.this));
        TrainAdapter adapter = new TrainAdapter(list, TrainOrgActivity.this, new OnTypeClickListener() {
            @Override
            public void onClicked(Object obj, int position, int flag) {
                TrainCourseBean bean = (TrainCourseBean) obj;
                TrainDetailActivity.start(TrainOrgActivity.this, "", bean);
            }
        });
        mRvRecentTrain.setAdapter(adapter);
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewUtil.obj().gcViews(getWindow().getDecorView());
    }
}
