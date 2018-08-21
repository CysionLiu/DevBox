package com.cysion.train.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.ui.TopBar;
import com.cysion.train.PageConstant;
import com.cysion.train.R;

import butterknife.BindView;

public class TrainOrgActivity extends BaseActivity {


    @BindView(R.id.bar_expert)
    TopBar mBarExpert;
    @BindView(R.id.tv_detail)
    TextView mTvDetail;
    @BindView(R.id.rv_recent_train)
    RecyclerView mRvRecentTrain;
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
        mBarExpert.setTitle("专家");
    }

    @Override
    protected void initData() {
        mTvDetail.setText(mType + mId);
    }

}
