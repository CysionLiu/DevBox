package com.cysion.train.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cysion.baselib.Box;
import com.cysion.baselib.base.BaseFragment;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.train.R;
import com.cysion.train.adapter.UserOptionAdapter;
import com.cysion.train.entity.UserOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
                Toast.makeText(Box.ctx(), "onClicked--" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mRvUserOptions.setAdapter(userOptionAdapter);
        mTvLogoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private List<UserOptions> getOptions() {
        List<UserOptions> tmp = new ArrayList<>();

        UserOptions userOptions0 = new UserOptions();
        userOptions0.setName(getString(R.string.str_my_profile));
        userOptions0.setType(100);
        tmp.add(userOptions0);

        UserOptions userOptions1 = new UserOptions();
        userOptions1.setName(getString(R.string.str_my_sign));
        userOptions1.setType(101);
        tmp.add(userOptions1);

        UserOptions userOptions2 = new UserOptions();
        userOptions2.setName(getString(R.string.str_my_collect));
        userOptions2.setType(102);
        tmp.add(userOptions2);

        UserOptions userOptions3 = new UserOptions();
        userOptions3.setName(getString(R.string.str_hotline));
        userOptions3.setType(103);
        userOptions3.setMsg1("1234567890");
        tmp.add(userOptions3);

        return tmp;
    }

    @Override
    protected void initData() {

    }
}
