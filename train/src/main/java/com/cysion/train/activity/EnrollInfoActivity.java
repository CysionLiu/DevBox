package com.cysion.train.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.bumptech.glide.Glide;
import com.cysion.baselib.Box;
import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.image.GlideRoundTransform;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.baselib.listener.PureListener;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.PageConstant;
import com.cysion.train.R;
import com.cysion.train.adapter.SitAdapter;
import com.cysion.train.entity.ClientEntity;
import com.cysion.train.entity.SitBean;
import com.cysion.train.entity.TrainCourseBean;
import com.cysion.train.logic.ConfigLogic;
import com.cysion.train.logic.EnrollLogic;
import com.cysion.train.logic.TrainLogic;
import com.cysion.train.logic.UserCache;
import com.cysion.train.logic.UserLogic;
import com.cysion.train.simple.SimpleEditListener;
import com.cysion.train.utils.Alert;
import com.cysion.train.view.MyToast;

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
    @BindView(R.id.tv_user_service_img)
    ImageView mTvUserServiceImg;
    @BindView(R.id.tv_fix_taitou)
    TextView mTvFixTaitou;
    @BindView(R.id.tv_company)
    TextView mTvCompany;
    @BindView(R.id.tv_not_company)
    TextView mTvNotCompany;
    @BindView(R.id.et_taitou_fapiao)
    EditText mEtTaitouFapiao;
    @BindView(R.id.et_suihao)
    EditText mEtSuihao;
    @BindView(R.id.rl_suihao_box)
    RelativeLayout mRlSuihaoBox;
    @BindView(R.id.tv_total_price_bottom)
    TextView mTvTotalPriceBottom;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.tv_refresh_contact)
    TextView mTvRefreshContact;
    @BindView(R.id.tv_refresh_fapiao)
    TextView mTvRefreshFapiao;
    private String mId;
    private TrainCourseBean mCurCourseBean;
    private int mSelectCount = 0;
    private int mNowTotalPrice = 0;
    private int mOldTotalPrice = 0;
    private String mSelectedSit = "";
    private String mSitNums = "";


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
        initEvent();
    }


    private void getPageData() {
        Intent intent = getIntent();
        if (intent != null) {
            mCurCourseBean = (TrainCourseBean) intent.getSerializableExtra(PageConstant.TRAIN_ENTITY);
            mId = mCurCourseBean.getId();
        }
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

    private void initSitList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvSits.setLayoutManager(linearLayoutManager);
        mRvSits.setAdapter(new SitAdapter(mCurCourseBean.getSit(), this, this));
        mRvSits.setNestedScrollingEnabled(false);
    }

    private void initEvent() {
        mTvSubmit.setOnClickListener(mOnClickListener);
        mTvRefreshContact.setOnClickListener(mOnClickListener);
        mTvRefreshFapiao.setOnClickListener(mOnClickListener);
        mTvUserServiceTip.setOnClickListener(mOnClickListener);
        mTvUserServiceImg.setOnClickListener(mOnClickListener);
        mTvNotCompany.setOnClickListener(mOnClickListener);
        mTvCompany.setOnClickListener(mOnClickListener);
        mEtSuihao.addTextChangedListener(mTextWatcher);
        mEtTaitouFapiao.addTextChangedListener(mTextWatcher);
        mEtContactPhone.addTextChangedListener(mContactTextWatcher);
        mEtContactor.addTextChangedListener(mContactTextWatcher);
        mEtSuihao.addTextChangedListener(mTextWatcher);

    }

    //更新数据
    private void setupData() {
        mTvUserServiceImg.setSelected(true);
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
        ClientEntity clientEntity = UserCache.obj().getClientEntity();
        mEtContactor.setText(clientEntity.getName());
        mEtContactPhone.setText(clientEntity.getPhone());
        if ("1".equals(clientEntity.getBill())) {
            isCompany();
        } else {
            notCompany();
        }
        mEtTaitouFapiao.setText(clientEntity.getBill_name());
        mEtSuihao.setText(clientEntity.getBill_num());
        mTvRefreshContact.setVisibility(View.GONE);
        mTvRefreshFapiao.setVisibility(View.GONE);
        changeSubmitState();
    }

    private void notCompany() {
        mTvCompany.setSelected(false);
        mTvNotCompany.setSelected(true);
        mRlSuihaoBox.setVisibility(View.GONE);
    }

    private void isCompany() {
        mTvCompany.setSelected(true);
        mTvNotCompany.setSelected(false);
        mRlSuihaoBox.setVisibility(View.VISIBLE);
    }

    //当列表item发生点击时
    @Override
    public void onClicked(Object obj, int position, int flag) {
        List<SitBean> sit = mCurCourseBean.getSit();
        mSelectCount = 0;
        mNowTotalPrice = 0;
        mOldTotalPrice = 0;
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
        mTvTotalPriceBottom.setText("合计: ¥" + mNowTotalPrice);
        changeSubmitState();
    }

    //改变底部提交按钮的状态
    private void changeSubmitState() {

        boolean canSubmit = true;
        if (mSelectCount == 0) {
            canSubmit = false;
        }
        if (!mTvUserServiceImg.isSelected()) {
            canSubmit = false;
        }
        if (TextUtils.isEmpty(mEtContactor.getText().toString().trim())) {
            canSubmit = false;
        }
        if (TextUtils.isEmpty(mEtContactPhone.getText().toString().trim())) {
            canSubmit = false;
        }
//        if (!mTvCompany.isSelected() && !mTvNotCompany.isSelected()) {
//            canSubmit = false;
//        }
        //选了企业，但是没有抬头和税号
//        if (mTvCompany.isSelected() && TextUtils.isEmpty(mEtSuihao.getText().toString().trim())
//                || TextUtils.isEmpty(mEtTaitouFapiao.getText().toString().trim())) {
//            canSubmit = false;
//        } else {
//            if (TextUtils.isEmpty(mEtTaitouFapiao.getText().toString().trim())) {
//                canSubmit = false;
//            }
//        }
        if (canSubmit) {
            mTvSubmit.setEnabled(true);
        } else {
            mTvSubmit.setEnabled(false);
        }

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int viewId = v.getId();
            switch (viewId) {
                case R.id.tv_company:
                    if (!mTvCompany.isSelected()) {
                        isCompany();
                    }
                    changeSubmitState();
                    break;
                case R.id.tv_not_company:
                    if (!mTvNotCompany.isSelected()) {
                        notCompany();
                    }
                    changeSubmitState();
                    break;
                case R.id.tv_refresh_contact:
                    refreshContact();
                    break;
                case R.id.tv_refresh_fapiao:
                    refreshBill();
                    break;
                case R.id.tv_submit:
                    submit();
                    break;
                case R.id.tv_user_service_tip:
                    Alert.obj().showUserService(EnrollInfoActivity.this, ConfigLogic.obj().sConfigBean.getServe());
                    break;
                case R.id.tv_user_service_img:
                    mTvUserServiceImg.setSelected(!mTvUserServiceImg.isSelected());
                    changeSubmitState();
                    break;
                default:
                    break;
            }
        }
    };

    private void submit() {
        if (!RegexUtils.isMobileExact(mEtContactPhone.getText().toString().trim())) {
            MyToast.quickShow(Box.str(R.string.str_error_phone));
            return;
        }
        List<SitBean> sit = mCurCourseBean.getSit();
        for (SitBean sitBean : sit) {
            if (sitBean.getNativecount() > 0) {
                if (TextUtils.isEmpty(mSelectedSit)) {
                    mSelectedSit = sitBean.getId();
                    mSitNums = sitBean.getNativecount() + "";
                } else {
                    mSelectedSit = mSelectedSit + "," + sitBean.getId();
                    mSitNums = mSitNums + "," + sitBean.getNativecount();
                }
            }
        }
        EnrollLogic.obj().enroll(mId, mEtContactor.getText().toString().trim(),
                mEtContactPhone.getText().toString().trim(),
                mTvCompany.isSelected() ? "1" : "2", mEtTaitouFapiao.getText().toString().trim(),
                mEtSuihao.getText().toString().trim(), mSelectedSit, mSitNums,
                mEtRemark.getText().toString().trim(), new PureListener<String>() {
                    @Override
                    public void done(String result) {
                        if (UserCache.obj().getClientEntity().getPhone() == null) {
                            autoRefresh();
                        }
                        finish();
                        Intent myIntent = new Intent(EnrollInfoActivity.this, EnrollSucessActivity.class);
                        startActivity(myIntent);
                    }

                    @Override
                    public void dont(int flag, String msg) {
                        MyToast.quickShow(msg);
                    }
                });

    }

    private void autoRefresh() {
        UserLogic.obj().updateClientInfo(mEtContactor.getText().toString().trim(),
                mEtContactPhone.getText().toString().trim(), mTvCompany.isSelected() ? "1" : "2",
                mEtTaitouFapiao.getText().toString().trim(),
                mEtSuihao.getText().toString().trim(), "", PureListener.DEFAULT);
    }

    private void refreshBill() {
        UserLogic.obj().updateClientInfo("",
                "", mTvCompany.isSelected() ? "1" : "2", mEtTaitouFapiao.getText().toString().trim(),
                mEtSuihao.getText().toString().trim(), "", new PureListener<String>() {
                    @Override
                    public void done(String result) {
                        MyToast.quickShow("更新成功");
                    }

                    @Override
                    public void dont(int flag, String msg) {
                        MyToast.quickShow("更新失败");
                    }
                });
    }

    private void refreshContact() {
        UserLogic.obj().updateClientInfo(mEtContactor.getText().toString().trim(),
                mEtContactPhone.getText().toString().trim(),
                "", "", "", "", new PureListener<String>() {
                    @Override
                    public void done(String result) {
                        MyToast.quickShow("更新成功");
                    }

                    @Override
                    public void dont(int flag, String msg) {
                        MyToast.quickShow("更新失败");
                    }
                });
    }

    private TextWatcher mTextWatcher = new SimpleEditListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            super.onTextChanged(s, start, before, count);
            mTvRefreshFapiao.setVisibility(View.VISIBLE);
            changeSubmitState();
        }
    };
    private TextWatcher mContactTextWatcher = new SimpleEditListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            super.onTextChanged(s, start, before, count);
            changeSubmitState();
            mTvRefreshContact.setVisibility(View.VISIBLE);
        }
    };
}
