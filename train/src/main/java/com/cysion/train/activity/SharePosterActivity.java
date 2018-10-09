package com.cysion.train.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.image.GlideRoundTransform;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.PageConstant;
import com.cysion.train.R;
import com.cysion.train.logic.MultiLogic;
import com.cysion.train.utils.Alert;
import com.cysion.train.utils.DownLoadUtil;
import com.cysion.train.view.MyToast;

import butterknife.BindView;

public class SharePosterActivity extends BaseActivity {


    @BindView(R.id.bar_poster)
    TopBar mBarPoster;
    @BindView(R.id.iv_poster)
    ImageView mIvPoster;
    @BindView(R.id.tv_download)
    TextView mTvDownload;

    private String mid;
    private String mImgUrl;

    public static void start(Activity aActivity, String id) {
        Intent myIntent = new Intent(aActivity, SharePosterActivity.class);
        myIntent.putExtra(PageConstant.TRAIN_ID, id);
        aActivity.startActivity(myIntent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share_poster;
    }

    @Override
    protected void initView() {
        ShowUtil.whiteStatusBar(this, true);
        mBarPoster.setTitle("分享海报");
        mBarPoster.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onIconClicked(View aView, TopBar.Pos aPosition) {
                if (aPosition == TopBar.Pos.LEFT) {
                    finish();
                }
            }
        });
        mTvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownLoadUtil.obj().downloadPoster(mImgUrl);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        if (getIntent() != null) {
            mid = getIntent().getStringExtra(PageConstant.TRAIN_ID);
        }
        Alert.obj().loading(self);
        MultiLogic.obj().getPoster(mid, new PureListener<String>() {
            @Override
            public void done(String result) {
                mImgUrl = result;
                Glide.with(SharePosterActivity.this).load(mImgUrl)
                        .placeholder(R.drawable.poster_place)
                        .transform(new GlideRoundTransform(SharePosterActivity.this))
                        .into(mIvPoster);
                Alert.obj().loaded();
            }

            @Override
            public void dont(int flag, String msg) {
                MyToast.quickShow(msg);
                Alert.obj().loaded();
            }
        });
    }
}
