package com.cysion.train.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.IntentUtils;
import com.bumptech.glide.Glide;
import com.cysion.baselib.base.BaseFragment;
import com.cysion.baselib.base.BusEvent;
import com.cysion.baselib.image.GlideCircleTransform;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.baselib.listener.PureListener;
import com.cysion.train.Constant;
import com.cysion.train.PageConstant;
import com.cysion.train.R;
import com.cysion.train.activity.CollectActivity;
import com.cysion.train.activity.PersonActivity;
import com.cysion.train.adapter.UserOptionAdapter;
import com.cysion.train.entity.UserEntity;
import com.cysion.train.entity.UserOptions;
import com.cysion.train.helper.LoginHelper;
import com.cysion.train.logic.UserCache;
import com.cysion.train.logic.UserLogic;
import com.cysion.train.view.MyToast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class UserFragment extends BaseFragment {
    @BindView(R.id.rv_user_options)
    RecyclerView mRvUserOptions;
    List<UserOptions> mUserOptions;
    @BindView(R.id.iv_user_avatar)
    ImageView mIvUserAvatar;
    @BindView(R.id.tv_logo_name)
    TextView mTvLogoName;
    @BindView(R.id.tv_to_logout)
    TextView mTvToLogout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initViews() {
        mRvUserOptions.setLayoutManager(new LinearLayoutManager(mActivity));
        mUserOptions = getOptions();

        UserOptionAdapter userOptionAdapter = new UserOptionAdapter(mUserOptions, mActivity, new OnTypeClickListener() {
            @Override
            public void onClicked(Object obj, int position, int flag) {
                UserOptions options = (UserOptions) obj;
                if (options.getType() == Constant.MY_COLLECT) {
                    if (LoginHelper.obj().toLoginPage(mActivity)) {
                        return;
                    }
                    Intent myIntent = new Intent(mActivity, CollectActivity.class);
                    startActivity(myIntent);
                } else if (options.getType() == Constant.MY_HOTLINE) {
                    Intent myIntent = IntentUtils.getDialIntent(options.getMsg1());
                    startActivity(myIntent);
                } else if (options.getType() == Constant.MY_PROFILE) {
                    if (LoginHelper.obj().toLoginPage(mActivity)) {
                        return;
                    }
                    Intent myIntent = new Intent(mActivity, PersonActivity.class);
                    startActivity(myIntent);
                } else {
                    MyToast.quickShow("功能未完成");
                }
            }
        });
        mRvUserOptions.setAdapter(userOptionAdapter);
        mTvLogoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginHelper.obj().toLoginPage(mActivity)) {
                    return;
                }
            }
        });
        mIvUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginHelper.obj().toLoginPage(mActivity)) {
                    return;
                }
            }
        });
        mTvToLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserCache.obj().clearCache();
                refreshPage();
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void refreshPage() {
        if (UserCache.obj().isLogin()) {
            mTvToLogout.setVisibility(View.VISIBLE);
            UserEntity userEntity = UserCache.obj().getUserEntity();
            if (userEntity != null) {
                if (!TextUtils.isEmpty(userEntity.getPic())) {
                    Glide.with(this).load(userEntity.getPic())
                            .placeholder(R.drawable.user_avatar_default)
                            .transform(new GlideCircleTransform(mActivity))
                            .into(mIvUserAvatar);
                }
                mTvLogoName.setText(userEntity.getName());
            }
        } else {
            mTvToLogout.setVisibility(View.GONE);
            mTvLogoName.setText("未登录");
        }

    }

    private List<UserOptions> getOptions() {
        List<UserOptions> tmp = new ArrayList<>();

        UserOptions userOptions0 = new UserOptions();
        userOptions0.setName(getString(R.string.str_my_profile));
        userOptions0.setType(Constant.MY_PROFILE);
        tmp.add(userOptions0);

        UserOptions userOptions1 = new UserOptions();
        userOptions1.setName(getString(R.string.str_my_sign));
        userOptions1.setType(Constant.MY_SIGN);
        tmp.add(userOptions1);

        UserOptions userOptions2 = new UserOptions();
        userOptions2.setName(getString(R.string.str_my_collect));
        userOptions2.setType(Constant.MY_COLLECT);
        tmp.add(userOptions2);

        UserOptions userOptions3 = new UserOptions();
        userOptions3.setName(getString(R.string.str_hotline));
        userOptions3.setType(Constant.MY_HOTLINE);
        userOptions3.setMsg1(Constant.HOTLINE_NUMBER);
        tmp.add(userOptions3);
        return tmp;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void fromEventBus(BusEvent event) {
        if (event.getTag() == PageConstant.LOGIN_SUCCESS) {
            refreshPage();
        }
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        refreshPage();
    }

    @Override
    protected void visibleAgain() {
        super.visibleAgain();
        //每次重现，更新用户信息
        PureListener<String> t = new PureListener<String>() {
            @Override
            public void done(String result) {

            }

            @Override
            public void dont(int flag, String msg) {
                MyToast.quickShow(msg);
            }
        };
        UserLogic.obj().getUserInfo(t);
        UserLogic.obj().getClientInfo(t);
        refreshPage();
    }
}
