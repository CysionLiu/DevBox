package com.cysion.train.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.PageConstant;
import com.cysion.train.R;

import butterknife.BindView;

public class TrainDetailActivity extends BaseActivity {

    @BindView(R.id.bar_train)
    TopBar mBarTrain;
    @BindView(R.id.tv_detail)
    TextView mTvDetail;
    @BindView(R.id.rv_infos)
    RecyclerView mRvInfos;

    private String mId;
    private String mUid;

    //type，机构or专家；id
    public static void start(Activity aActivity, String uid, String id) {
        Intent myIntent = new Intent(aActivity, TrainDetailActivity.class);
        myIntent.putExtra(PageConstant.USER_ID, uid);
        myIntent.putExtra(PageConstant.TRAIN_ID, id);
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
        if (intent != null) {
            mId = intent.getStringExtra(PageConstant.TRAIN_ID);
            mUid = intent.getStringExtra(PageConstant.USER_ID);
        }

        mBarTrain.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onIconClicked(View aView, TopBar.Pos aPosition) {
                if (aPosition == TopBar.Pos.LEFT) {
                    TrainDetailActivity.this.finish();
                }
            }
        });
        mBarTrain.setTitle("会议");
    }

    @Override
    protected void initData() {
        mTvDetail.setText(mUid + "id" + mId);
    }
}
