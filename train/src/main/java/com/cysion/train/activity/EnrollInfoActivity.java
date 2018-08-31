package com.cysion.train.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.image.GlideRoundTransform;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.PageConstant;
import com.cysion.train.R;
import com.cysion.train.adapter.SitAdapter;
import com.cysion.train.entity.SitBean;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.logic.TrainLogic;
import com.cysion.train.logic.UserCache;

import java.util.List;

import butterknife.BindView;

public class EnrollInfoActivity extends BaseActivity implements OnTypeClickListener {


    @BindView(R.id.tbar_enroll)
    TopBar mTbarEnroll;
    @BindView(R.id.iv_train_top)
    ImageView mIvTrainTop;
    @BindView(R.id.tv_style_tag)
    TextView mTvStyleTag;
    @BindView(R.id.tv_train_name)
    TextView mTvTrainName;
    @BindView(R.id.tv_train_tags)
    TextView mTvTrainTags;
    @BindView(R.id.tv_train_time_address)
    TextView mTvTrainTimeAddress;
    @BindView(R.id.tv_price_ext)
    TextView mTvPriceExt;
    @BindView(R.id.tv_train_price)
    TextView mTvTrainPrice;
    @BindView(R.id.rv_sits)
    RecyclerView mRvSits;
    @BindView(R.id.tv_gates_count)
    TextView mTvGatesCount;
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @BindView(R.id.tv_cheap_num)
    TextView mTvCheapNum;
    @BindView(R.id.rl_cheap_defer)
    RelativeLayout mRlCheapDefer;
    @BindView(R.id.tv_exact_price)
    TextView mTvExactPrice;
    @BindView(R.id.et_contactor)
    EditText mEtContactor;
    @BindView(R.id.et_contact_phone)
    EditText mEtContactPhone;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.tv_user_service_tip)
    TextView mTvUserServiceTip;
    private String mId;
    private TrainCourseBean mCurCourseBean;
    private int mSelectCount = 0;
    private int mNowTotalPrice = 0;
    private int mOldTotalPrice = 0;


    //type，机构or专家；id，打开本页面
    public static void start(Activity aActivity, TrainCourseBean aTrainCourseBean) {
        Intent myIntent = new Intent(aActivity, EnrollInfoActivity.class);
        myIntent.putExtra(PageConstant.TRAIN_ENTITY, aTrainCourseBean);
        aActivity.startActivity(myIntent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enroll_train;
    }

    @Override
    protected void initView() {
        ShowUtil.darkAndWhite(this, true);
        getPageData();
        initTopBar();
        initSitList();
        setupData();

    }

    private void initSitList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvSits.setLayoutManager(linearLayoutManager);
        mRvSits.setAdapter(new SitAdapter(mCurCourseBean.getSit(), this, this));
        mRvSits.setNestedScrollingEnabled(false);
    }

    private void initTopBar() {
        mTbarEnroll.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onIconClicked(View aView, TopBar.Pos aPosition) {
                if (aPosition == TopBar.Pos.LEFT) {
                    EnrollInfoActivity.this.finish();
                }
            }
        });
        mTbarEnroll.setTitle(getString(R.string.str_enroll_order));
    }

    private void getPageData() {
        Intent intent = getIntent();
        if (intent != null) {
            mCurCourseBean = (TrainCourseBean) intent.getSerializableExtra(PageConstant.TRAIN_ENTITY);
            mId = mCurCourseBean.getId();
        }
    }

    @Override
    protected void initData() {
        super.initData();
        //获取用户详细数据
        TrainLogic.obj().getEnrollInfo(mId, new PureListener<TrainCourseBean>() {
            @Override
            public void done(TrainCourseBean result) {
                mCurCourseBean = result;
                setupData();
                initSitList();
            }

            @Override
            public void dont(int flag, String msg) {
                Toast.makeText(EnrollInfoActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //更新数据
    private void setupData() {
        if (mCurCourseBean == null) {
            return;
        }
        String style = mCurCourseBean.getStyle();
        if (TextUtils.isEmpty(style)) {
            style = "";
        }
        mTvStyleTag.setText(style);
        mTvTrainName.setText(mCurCourseBean.getName());
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
        Glide.with(this).load(mCurCourseBean.getTop())
                .placeholder(R.mipmap.place_list).transform(new GlideRoundTransform(this)).into(
                mIvTrainTop);
        //时间地点
        String area = mCurCourseBean.getCity();
        String startTime = mCurCourseBean.getStart();
        String area_time = startTime + " · " + area;
        mTvTrainTimeAddress.setText(area_time);
        //价格
        TrainCourseBean.PriceBean price = mCurCourseBean.getPrice();
        String priceStr = "";
        if (price != null) {
            priceStr = "¥" + price.getMin();
            mTvPriceExt.setText(price.getPrice_ext());
        }
        mTvTrainPrice.setText(priceStr);
        mEtContactor.setText(UserCache.obj().getClientEntity().getName());
        mEtContactPhone.setText(UserCache.obj().getClientEntity().getPhone());
    }

    @Override
    public void onClicked(Object obj, int position, int flag) {
        SitBean bean = (SitBean) obj;
        List<SitBean> sit = mCurCourseBean.getSit();
        mSelectCount = 0;
        mNowTotalPrice = 0;
        mOldTotalPrice = 0;
        int mDeferPrice = 0;
        for (SitBean sitBean : sit) {
            int nativecount = sitBean.getNativecount();
            if (nativecount > 0) {
                mOldTotalPrice += Integer.valueOf(sitBean.getPrice()) * nativecount;
                mNowTotalPrice += Integer.valueOf(sitBean.getNow_price()) * nativecount;
                mSelectCount += nativecount;
            }
        }
        if (mSelectCount > 0) {
            mRlCheapDefer.setVisibility(View.VISIBLE);
        } else {
            mRlCheapDefer.setVisibility(View.GONE);
        }
        mTvGatesCount.setText(mSelectCount + "张");
        //原总价
        mTvTotalPrice.setText("¥" + mOldTotalPrice);
        //现在总价
        mTvExactPrice.setText("¥" + mNowTotalPrice);
        //优惠
        mTvCheapNum.setText("立省 " + (mOldTotalPrice - mNowTotalPrice));

    }
}
