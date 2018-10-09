package com.cysion.videosample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cysion.videosample.R;
import com.cysion.videosample.VideoConstant;
import com.cysion.videosample.adapter.MyAdapter;
import com.cysion.videosample.base.OnTypeClickListener;
import com.cysion.videosample.entity.VideoEntity;
import com.orhanobut.logger.Logger;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.ArrayList;
import java.util.List;

public class ListVideoActivity extends AppCompatActivity implements OnTypeClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;
    private List<VideoEntity> mVideoEntities;
    private MyAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_video);
        mRecyclerView = findViewById(R.id.rl_list_video);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        //--
        mRecyclerView2 = findViewById(R.id.rl_list_video2);
        mGridLayoutManager = new GridLayoutManager(this,1);
        mRecyclerView2.setLayoutManager(mGridLayoutManager);
        loadData();
        mAdapter = new MyAdapter(mVideoEntities, this, this);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView2.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView2.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(VideoConstant.PLAYING_TAG)
                            && (position < mLinearLayoutManager.findFirstVisibleItemPosition() || position > lastVisibleItem)) {
                        if (GSYVideoManager.isFullState(ListVideoActivity.this)) {
                            return;
                        }
                        //如果滑出去了上面和下面就是否，和今日头条一样
                        GSYVideoManager.releaseAllVideos();
                        mAdapter.notifyDataSetChanged();
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mRecyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(VideoConstant.PLAYING_TAG)
                            && (position < mLinearLayoutManager.findFirstVisibleItemPosition() || position > lastVisibleItem)) {
                        if (GSYVideoManager.isFullState(ListVideoActivity.this)) {
                            return;
                        }
                        //如果滑出去了上面和下面就是否，和今日头条一样
                        GSYVideoManager.releaseAllVideos();
                        mAdapter.notifyDataSetChanged();
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    private void loadData() {
        mVideoEntities = new ArrayList<>();
        VideoEntity entity = new VideoEntity();
        entity.setName("测试A");
        entity.setUrl("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4");
        mVideoEntities.add(entity);
        VideoEntity entity2 = new VideoEntity();
        entity2.setName("测试B");
        entity2.setUrl("http://o6rla8z20.bkt.clouddn.com/Wildlife_s.mov");
        mVideoEntities.add(entity2);
        VideoEntity entity3 = new VideoEntity();
        entity3.setName("测试C");
        entity3.setUrl("http://o6rla8z20.bkt.clouddn.com/advideo.mp4");
        mVideoEntities.add(entity3);
        VideoEntity entity4 = new VideoEntity();
        entity4.setName("测试D");
        entity4.setUrl("http://o6rla8z20.bkt.clouddn.com/video/testDior0921.mov");
        mVideoEntities.add(entity4);
        VideoEntity entity5 = new VideoEntity();
        entity5.setName("测试E");
        entity5.setUrl("http://o6rla8z20.bkt.clouddn.com/video/testDior0921A.avi");
        mVideoEntities.add(entity5);
    }

    @Override
    public void onClicked(Object obj, int position, int flag) {
        Logger.d("click:" + flag);
    }

    @Override
    public void onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }
}
