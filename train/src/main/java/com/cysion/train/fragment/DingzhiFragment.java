package com.cysion.train.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.RegexUtils;
import com.cysion.baselib.Box;
import com.cysion.baselib.base.BaseFragment;
import com.cysion.baselib.listener.PureListener;
import com.cysion.train.R;
import com.cysion.train.activity.LoginActivity;
import com.cysion.train.entity.AreaBean;
import com.cysion.train.entity.PlanEntity;
import com.cysion.train.logic.MultiLogic;
import com.cysion.train.logic.UserCache;
import com.cysion.train.simple.SimpleEditListener;
import com.cysion.train.utils.AreaUtil;
import com.cysion.train.view.MyToast;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class DingzhiFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.rv_plans)
    RecyclerView mRvPlans;
    @BindView(R.id.iv_right_arrow2)
    ImageView mIvRightArrow2;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.iv_right_arrow1)
    ImageView mIvRightArrow1;
    @BindView(R.id.iv_dingzhi_right_now)
    ImageView mIvDingzhiRightNow;
    @BindView(R.id.tv_plans)
    TextView mTvPlans;
    @BindView(R.id.et_company)
    EditText mEtCompany;
    @BindView(R.id.et_contact)
    EditText mEtContact;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    @BindView(R.id.sc_box)
    NestedScrollView mScBox;
    @BindView(R.id.cv_dingzhi)
    CardView mCvDingzhi;
    Unbinder unbinder;
    private List<PlanEntity> mPlanEntities;
    private String mSelectPlanId = "";
    private String mSearchAreaId = "";
    private OptionsPickerView mAreaPvOptions;
    private OptionsPickerView mPlanOptions;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dingzhi;
    }

    @Override
    protected void initViews() {
        mRvPlans.setNestedScrollingEnabled(false);
        initList();
        initEvent();
        changeBtnStatue();
    }

    //设置方案列表
    private void initList() {
        if (mPlanEntities == null) {
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRvPlans.setLayoutManager(layoutManager);
    }

    private void initEvent() {
        mTvAddress.setOnClickListener(this);
        mTvPlans.setOnClickListener(this);
        mIvRightArrow1.setOnClickListener(this);
        mIvRightArrow2.setOnClickListener(this);
        mEtPhone.addTextChangedListener(mSimpleEditListener);
        mEtContact.addTextChangedListener(mSimpleEditListener);
        mBtnSubmit.setOnClickListener(this);
        mIvDingzhiRightNow.setOnClickListener(this);
        mScBox.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] loc = new int[2];
                mCvDingzhi.getLocationOnScreen(loc);
                if (loc[1] + Box.density() * 50 > Box.h()) {
                    mIvDingzhiRightNow.setVisibility(View.VISIBLE);
                } else {
                    mIvDingzhiRightNow.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void initData() {
        getPlan();
    }

    //获取方案列表
    private void getPlan() {
        MultiLogic.obj().getPlans(new PureListener<List<PlanEntity>>() {
            @Override
            public void done(List<PlanEntity> result) {
                mPlanEntities = result;
                initList();
            }

            @Override
            public void dont(int flag, String msg) {

            }
        });
    }

    @Override
    protected void visibleAgain() {
        super.visibleAgain();
        if (mPlanEntities == null || mPlanEntities.size() == 0) {
            getPlan();
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.tv_address:
            case R.id.iv_right_arrow1:
                chooseArea();
                break;
            case R.id.tv_plans:
            case R.id.iv_right_arrow2:
                choosePlans();
                break;
            case R.id.btn_submit:
                toSubmit();
                break;
            case R.id.iv_dingzhi_right_now:
                mScBox.fullScroll(View.FOCUS_DOWN);
                break;
            default:
                break;
        }
    }

    //提交方案
    private void toSubmit() {
        if (!UserCache.obj().isLogin()) {
            Intent myIntent = new Intent(mActivity, LoginActivity.class);
            startActivity(myIntent);
            return;
        }
        String contact = mEtContact.getText().toString().trim();
        if (TextUtils.isEmpty(contact)) {
            MyToast.quickShow("联系人为空");
            return;
        }
        String phone = mEtPhone.getText().toString().trim();
        if (!RegexUtils.isMobileExact(phone)) {
            MyToast.quickShow(Box.str(R.string.str_error_phone));
            return;
        }
        String comname = mEtCompany.getText().toString().trim();
        String remark = mEtRemark.getText().toString().trim();
        MultiLogic.obj().publishPlan(mSelectPlanId, contact, phone, comname,
                mSearchAreaId, remark, new PureListener<Integer>() {
                    @Override
                    public void done(Integer result) {
                        MyToast.quickShow("提交成功");
                        reset();
                    }

                    @Override
                    public void dont(int flag, String msg) {
                        MyToast.quickShow(msg);
                    }
                });

    }

    //选择方案
    private void choosePlans() {
        if (mPlanOptions == null) {
            mPlanOptions = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    PlanEntity bean = mPlanEntities.get(options1);
                    mSelectPlanId = bean.getId();
                    mTvPlans.setText(bean.getName());
                }
            }).setContentTextSize(18).setCancelColor(Color.GRAY).setSubmitColor(Box.color(R.color.main_tag)).build();
            mPlanOptions.setPicker(mPlanEntities);
        }
        mPlanOptions.show();
    }

    //选择地区
    private void chooseArea() {
        if (mAreaPvOptions == null) {
            //返回的分别是三个级别的选中位置
            mAreaPvOptions = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    mSearchAreaId = "";
                    //返回的分别是三个级别的选中位置
                    AreaBean p = AreaUtil.obj().getProvince().get(options1);
                    AreaBean city = AreaUtil.obj().getCities().get(options1).get(option2);
                    AreaBean county = AreaUtil.obj().getCounties().get(options1).get(option2).get(options3);
                    mSearchAreaId = p.getId() + "," + city.getId() + "," + county.getId();
                    String area = "";
                    if (options1 > 0) {
                        area = p.getName();
                    }
                    if (option2 > 0) {
                        area = area + "-" + city.getName();
                    }
                    if (options3 > 0) {
                        area = area + "-" + county.getName();
                    }
                    mTvAddress.setText(area);
                }
            }).setContentTextSize(18).setCancelColor(Color.GRAY).setSubmitColor(Box.color(R.color.main_tag)).build();
            mAreaPvOptions.setPicker(AreaUtil.obj().getProvince(), AreaUtil.obj().getCities()
                    , AreaUtil.obj().getCounties());
        }
        mAreaPvOptions.show();
    }

    //重置
    private void reset() {
        mEtRemark.setText("");
        mEtCompany.setText("");
        mEtPhone.setText("");
        mEtContact.setText("");
        mTvAddress.setText("请选择");
        mTvPlans.setText("请选择");
        changeBtnStatue();
    }

    private void changeBtnStatue() {
        String c = mEtContact.getText().toString().trim();
        String p = mEtPhone.getText().toString().trim();
        if (!TextUtils.isEmpty(c) && !TextUtils.isEmpty(p)) {
            mBtnSubmit.setEnabled(true);
        } else {
            mBtnSubmit.setEnabled(false);
        }
    }

    private SimpleEditListener mSimpleEditListener = new SimpleEditListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            super.onTextChanged(s, start, before, count);
            changeBtnStatue();
        }
    };

}
