package com.cysion.train.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.R;

import butterknife.BindView;

public class EnrollSucessActivity extends BaseActivity {

    @BindView(R.id.bar_expert)
    TopBar mBarExpert;
    @BindView(R.id.tv_look)
    TextView mTvLook;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enroll_sucess;
    }

    @Override
    protected void initView() {
        ShowUtil.whiteStatusBar(this,true);
        mBarExpert.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onIconClicked(View aView, TopBar.Pos aPosition) {
                if (aPosition == TopBar.Pos.LEFT) {
                    finish();
                }
            }
        });
        mBarExpert.setTitle("提交结果");
        mTvLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent myIntent = new Intent(EnrollSucessActivity.this, PayDescActivity.class);
                startActivity(myIntent);
            }
        });

    }
}
