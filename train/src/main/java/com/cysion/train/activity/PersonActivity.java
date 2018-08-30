package com.cysion.train.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.image.GlideCircleTransform;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.R;
import com.cysion.train.entity.ClientEntity;
import com.cysion.train.entity.UserEntity;
import com.cysion.train.logic.UserCache;
import com.cysion.train.view.MyToast;

import butterknife.BindView;

public class PersonActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.bar_train)
    TopBar mBarTrain;
    @BindView(R.id.iv_user_avatar)
    ImageView mIvUserAvatar;
    @BindView(R.id.et_nickname)
    EditText mEtNickname;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.et_contactor)
    EditText mEtContactor;
    @BindView(R.id.et_contact_phone)
    EditText mEtContactPhone;
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
    @BindView(R.id.rl_head_box)
    RelativeLayout mRlHeadBox;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void initView() {
        ShowUtil.darkAndWhite(this, true);
        mBarTrain.setTitle("个人资料");
        mBarTrain.imageRight(false);
        mBarTrain.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onIconClicked(View aView, TopBar.Pos aPosition) {
                if (aPosition == TopBar.Pos.LEFT) {
                    PersonActivity.this.finish();
                } else if (aPosition == TopBar.Pos.RIGHT) {
                }
            }
        });
        showClientInfo();
        showUserInfo();
        mRlHeadBox.setOnClickListener(this);
        mTvNotCompany.setOnClickListener(this);
        mTvCompany.setOnClickListener(this);
    }

    private void showUserInfo() {
        UserEntity userEntity = UserCache.obj().getUserEntity();
        if (userEntity != null) {
            if (!TextUtils.isEmpty(userEntity.getPic())) {
                Glide.with(this).load(userEntity.getPic())
                        .placeholder(R.drawable.user_avatar_default)
                        .transform(new GlideCircleTransform(this))
                        .into(mIvUserAvatar);
            }
            mEtNickname.setText(userEntity.getName());
            mTvPhone.setText(userEntity.getMobile());
        }
    }

    private void showClientInfo() {
        ClientEntity clientEntity = UserCache.obj().getClientEntity();
        if (clientEntity != null) {
            mEtContactor.setText(clientEntity.getName());
            mEtContactPhone.setText(clientEntity.getPhone());
            if ("1".equals(clientEntity.getBill())) {
                isCompany();
            } else {
                notCompany();
            }
            mEtTaitouFapiao.setText(clientEntity.getBill_name());
            mEtSuihao.setText(clientEntity.getBill_num());
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.tv_company:
                if (!mTvCompany.isSelected()) {
                    isCompany();
                }
                break;
            case R.id.tv_not_company:
                if (!mTvNotCompany.isSelected()) {
                    notCompany();
                }
                break;
            case R.id.rl_head_box:
                MyToast.quickShow("head");
                break;
            default:

                break;
        }
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
}
