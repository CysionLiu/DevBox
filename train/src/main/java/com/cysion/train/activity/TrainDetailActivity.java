package com.cysion.train.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.PageConstant;
import com.cysion.train.R;
import com.cysion.train.adapter.TrainDetailPageAdapter;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.logic.TrainLogic;
import com.cysion.train.view.MyUltranViewPager;
import com.cysion.train.view.SimpleWebview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TrainDetailActivity extends BaseActivity {

    @BindView(R.id.bar_train)
    TopBar mBarTrain;
    @BindView(R.id.vp_train_detail)
    MyUltranViewPager mVpTrainDetail;
    @BindView(R.id.tv_train_name)
    TextView mTvTrainName;

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

    private String mId;
    private String mUid;

    //type，机构or专家；id
    public static void start(Activity aActivity, String uid, TrainCourseBean aTrainCourseBean) {
        Intent myIntent = new Intent(aActivity, TrainDetailActivity.class);
        myIntent.putExtra(PageConstant.USER_ID, uid);
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
        mBarTrain.setTitle(getString(R.string.str_meeting_detail));
        mWebSimple.loadUrl("https://www.baidu.com/");
        mVpTrainDetail.setAutoScroll(100000000);
        setupData(courseBean);
    }

    private void setupData(TrainCourseBean aCourseBean) {
        if (aCourseBean == null) {
            return;
        }
        mTvTrainName.setText(aCourseBean.getName());
        List<String> images = new ArrayList<>();
        images.add(aCourseBean.getTop());
        PagerAdapter adapter = new TrainDetailPageAdapter(this, images);
        mVpTrainDetail.setAdapter(adapter);
        mTvTime.setText(aCourseBean.getStart());
        mTvAddress.setText(aCourseBean.getCity());
        if (aCourseBean.getPrice() != null) {
            mTvAddress.setText(aCourseBean.getPrice().getMin());
        }
    }

    @Override
    protected void initData() {
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
    }
}
