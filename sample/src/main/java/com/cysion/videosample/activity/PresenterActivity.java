package com.cysion.videosample.activity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.videosample.Presenter;
import com.cysion.videosample.R;
import com.cysion.videosample.entity.ExpertEntity;
import com.cysion.videosample.view.iview.PresentView;

import java.util.List;

public class PresenterActivity extends BaseActivity implements PresentView {

    private TextView mTvdata1;
    private TextView mTvdata2;
    private Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_presenter;
    }

    @Override
    protected void initView() {
        mTvdata1 = (TextView) findViewById(R.id.tv_data1);
        mTvdata2 = (TextView) findViewById(R.id.tv_data2);
        mPresenter = new Presenter();
        mPresenter.attachView(this);

    }


    public void getData1(View view) {
        mPresenter.getExpertList();
    }

    public void getData2(View view) {
        mPresenter.getExpert("6");
    }


    @Override
    public void setExpertList(List<ExpertEntity> datalist) {
        for (ExpertEntity expertEntity : datalist) {
            mTvdata1.append(expertEntity.getName()+"-");
        }
    }

    @Override
    public void setExpert(ExpertEntity aEntity) {
        mTvdata2.setText(aEntity.getName() + "--" + aEntity.getTop());
    }

    @Override
    public void error(int errorCode, String msg) {
        Toast.makeText(PresenterActivity.this, errorCode + ": " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
