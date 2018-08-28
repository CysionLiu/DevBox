package com.cysion.train.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.cysion.baselib.Box;
import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.R;
import com.cysion.train.adapter.CustomHomePageAdapter;
import com.cysion.train.fragment.MainListFragment;
import com.cysion.train.helper.HomeHelper;
import com.cysion.train.simple.SimpleTabSelectListener;
import com.cysion.train.view.CustomHomeViewPager;
import com.cysion.train.view.MyToast;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_top_title)
    TextView mTvTopTitle;
    @BindView(R.id.vp_home_vp)
    CustomHomeViewPager mVpHomeVp;
    @BindView(R.id.tablayout_main)
    TabLayout mTablayoutMain;

    private List<Fragment> mFragments;
    private List<String> mTitles;
    private CustomHomePageAdapter mPageAdapter;
    private long currentBackPressedTime = 0;
    private static final int BACK_PRESSED_INTERVAL = 2000;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ShowUtil.darkAndWhite(this, true);
        mVpHomeVp.setOffscreenPageLimit(4);
    }

    @Override
    protected void initData() {
        mFragments = HomeHelper.obj().getFragments();
        mTitles = HomeHelper.obj().getTitles();
        mPageAdapter = new CustomHomePageAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mVpHomeVp.setAdapter(mPageAdapter);
        mTablayoutMain.setupWithViewPager(mVpHomeVp);
        initTabs();

        mTablayoutMain.addOnTabSelectedListener(new SimpleTabSelectListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                Logger.d(tab.getPosition());
                mTvTopTitle.setText(HomeHelper.obj().getTopTitles().get(tab.getPosition()));
                if (tab.getPosition() == 3) {
                    ShowUtil.yellow(MainActivity.this, Box.color(R.color.yellow_background));
                    mTvTopTitle.setVisibility(View.GONE);
                } else {
                    ShowUtil.darkAndWhite(MainActivity.this, true);
                    mTvTopTitle.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    //初始化tab，设置图标和自定义布局，注意顺序和某些语句。
    private void initTabs() {
        int[] icons = HomeHelper.obj().getIcons();
        for (int i = 0; i < mTablayoutMain.getTabCount(); i++) {
            mTablayoutMain.getTabAt(i).setIcon(icons[i]);
            mTablayoutMain.getTabAt(i).setCustomView(R.layout.tabitem_main);
        }
    }

    public void switchToList(String style, int type) {
        mTablayoutMain.getTabAt(1).select();
        ((MainListFragment) mFragments.get(1)).fromOuter(style, "", type);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
            currentBackPressedTime = System.currentTimeMillis();
            MyToast.builder().gravity(Gravity.BOTTOM)
                    .text("再按一次即可退出应用")
                    .yOffset(100)
                    .textSize(12)
                    .buildToShow();
        } else {
            finish();
        }
    }
}
