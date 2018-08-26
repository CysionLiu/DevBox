package com.cysion.train.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cysion.baselib.base.BaseFragment;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.train.Constant;
import com.cysion.train.R;
import com.cysion.train.activity.CollectActivity;
import com.cysion.train.adapter.UserOptionAdapter;
import com.cysion.train.entity.UserOptions;
import com.cysion.train.helper.UserCache;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class UserFragment extends BaseFragment {
    @BindView(R.id.rv_user_options)
    RecyclerView mRvUserOptions;
    List<UserOptions> mUserOptions;
    public static final int ZILIAO = 1000;
    public static final int BAOMING = 1001;
    public static final int SHOUCANG = 1002;
    public static final int KEFU = 1003;
    @BindView(R.id.iv_user_avatar)
    ImageView mIvUserAvatar;
    @BindView(R.id.tv_logo_name)
    TextView mTvLogoName;
    @BindView(R.id.tv_to_logout)
    TextView mTvToLogout;
    Unbinder unbinder;

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
                    Intent myIntent = new Intent(mActivity, CollectActivity.class);
                    startActivity(myIntent);
                }
            }
        });
        mRvUserOptions.setAdapter(userOptionAdapter);
        mTvLogoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mTvToLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserCache.obj().clearCache();
                refreshPage();
            }
        });
        refreshPage();

    }

    private void refreshPage() {
        if (UserCache.obj().isLogin()) {
            mTvToLogout.setVisibility(View.VISIBLE);
            mTvLogoName.setText("已登录");
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

    @Override
    protected void initData() {

    }

    @Override
    protected void visibleAgain() {
        super.visibleAgain();
        refreshPage();
    }
}
