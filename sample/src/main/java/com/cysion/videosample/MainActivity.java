package com.cysion.videosample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.base.BaseAdapter;
import com.cysion.baselib.base.BaseViewHolder;
import com.cysion.baselib.listener.OnTypeClickListener;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.videosample.activity.AudioRecordActivity;
import com.cysion.videosample.activity.ListVideoActivity;
import com.cysion.videosample.activity.SimplePlayActivity;
import com.cysion.videosample.activity.WebSocketActivity;
import com.cysion.videosample.entity.PageBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements OnTypeClickListener {


    @BindView(R.id.rv_mainlist)
    RecyclerView mRvMainlist;
    private List<PageBean> mPageBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ShowUtil.whiteStatusBar(this);
        initPageList();
        mRvMainlist.setLayoutManager(new LinearLayoutManager(self));
        mRvMainlist.setAdapter(new PageAdapter(mPageBeanList, self, this));
    }

    @Override
    public void onClicked(Object obj, int position, int flag) {
        PageBean bean = (PageBean) obj;
        Intent myIntent = new Intent(MainActivity.this, bean.getTargetPage());
        startActivity(myIntent);
    }

    private void initPageList() {
        mPageBeanList = new ArrayList<>();
        mPageBeanList.add(new PageBean(SimplePlayActivity.class, "简单视频播放"));
        mPageBeanList.add(new PageBean(ListVideoActivity.class, "列表视频播放"));
        mPageBeanList.add(new PageBean(WebSocketActivity.class, "WebSocket测试练习"));
        mPageBeanList.add(new PageBean(AudioRecordActivity.class, "测试音频录制"));

    }

    class PageAdapter extends BaseAdapter<PageBean> {

        public PageAdapter(List<PageBean> aEntities, Context aContext, OnTypeClickListener aOnTypeClickListener) {
            super(aEntities, aContext, aOnTypeClickListener);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PageHolder(LayoutInflater.from(self)
                    .inflate(R.layout.item_activities, parent, false));
        }
    }

    class PageHolder extends BaseViewHolder<PageBean> {

        @BindView(R.id.tv_name)
        TextView mTvName;

        public PageHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void fillData(PageBean obj, int position) {
            mTvName.setText(obj.getName());
        }
    }
}
