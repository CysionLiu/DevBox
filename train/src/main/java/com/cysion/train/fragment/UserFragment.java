package com.cysion.train.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    String[] optionNames = {"我的报名", "我的收藏", "客服热线", "我的资料"};
    int[] types = {100, 101, 102, 103};

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

    }

    private List<UserOptions> getOptions() {
        List<UserOptions> tmp = new ArrayList<>();

        UserOptions userOptions1 = new UserOptions();
        userOptions1.setName("我的报名");
        userOptions1.setType(101);
        tmp.add(userOptions1);

        UserOptions userOptions2 = new UserOptions();
        userOptions2.setName("我的收藏");
        userOptions2.setType(102);
        tmp.add(userOptions2);

        UserOptions userOptions3 = new UserOptions();
        userOptions3.setName("客服热线");
        userOptions3.setType(103);
        userOptions3.setMsg1("1234567890");
        tmp.add(userOptions3);

        UserOptions userOptions4 = new UserOptions();
        userOptions4.setName("我的资料");
        userOptions4.setType(104);
        tmp.add(userOptions4);
        return tmp;
    }

    @Override
    protected void initData() {

    }
}
