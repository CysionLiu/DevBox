package com.cysion.train.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.ui.TopBar;
import com.cysion.train.PageConstant;
import com.cysion.train.R;
import com.cysion.train.entity.ExpertBean;
import com.cysion.train.logic.TrainLogic;
import com.cysion.train.utils.URLImageParser;

import butterknife.BindView;

public class TrainOrgActivity extends BaseActivity {


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
    @BindView(R.id.tv_expert_desc)
    TextView mTvExpertDesc;
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
            TrainLogic.obj().getExpertDetail(mId, mBeanPureListener);
        } else {
            mBarExpert.setTitle("培训机构");
            TrainLogic.obj().getOrgDetail(mId, mBeanPureListener);
        }
    }

    private PureListener<ExpertBean> mBeanPureListener = new PureListener<ExpertBean>() {
        @Override
        public void done(ExpertBean result) {
            if (PageConstant.IS_EXPERT.equals(mType)) {
                Glide.with(TrainOrgActivity.this)
                        .load(result.getTop()).into(mIvExpertLogo);
                mTvInfo.setText(result.getInfo());
                mTvMeetingOper.setText("参与会议");
            } else {
                Glide.with(TrainOrgActivity.this)
                        .load(result.getLogo()).into(mIvExpertLogo);
                mTvInfo.setText(result.getWork());
                mTvMeetingOper.setText("举办会议");
            }
            mTvExpertName.setText(result.getName());
            mTvMeetingCount.setText(result.getTotal() + "");
            mTvExpertDesc.setText(Html.fromHtml(result.getDesc(), new URLImageParser(mTvExpertDesc), null));
        }

        @Override
        public void dont(int flag, String msg) {
            Toast.makeText(TrainOrgActivity.this, msg, Toast.LENGTH_SHORT).show();

        }
    };
}
