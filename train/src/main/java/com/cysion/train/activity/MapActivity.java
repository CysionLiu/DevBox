package com.cysion.train.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.cysion.baselib.base.BaseActivity;
import com.cysion.baselib.ui.TopBar;
import com.cysion.baselib.utils.ShowUtil;
import com.cysion.train.R;

import butterknife.BindView;

public class MapActivity extends BaseActivity {

    public static final String LATI = "lati";
    public static final String LONG_TI = "long";
    public static final String TITLE = "title";

    @BindView(R.id.bar_expert)
    TopBar mBarExpert;
    @BindView(R.id.mapview)
    MapView mMapview;

    private double mLanti;
    private double mLon;
    private String mAreaTitle;
    private AMap aMap;

    public static void start(Activity aActivity, String title, String longStr, String latiStr) {
        double lati = 0;
        double lon = 0;
        try {
            lati = Double.valueOf(latiStr);
            lon = Double.valueOf(longStr);
        } catch (Exception aE) {

        }
        Intent myIntent = new Intent(aActivity, MapActivity.class);
        myIntent.putExtra(LATI, lati);
        myIntent.putExtra(LONG_TI, lon);
        myIntent.putExtra(TITLE, title);
        aActivity.startActivity(myIntent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    protected void initView() {
        ShowUtil.darkAndWhite(this, true);
        mBarExpert.setTitle("地图");
        mBarExpert.setOnTopBarClickListener(new TopBar.OnTopBarClickListener() {
            @Override
            public void onIconClicked(View aView, TopBar.Pos aPosition) {
                if (aPosition == TopBar.Pos.LEFT) {
                    MapActivity.this.finish();
                }
            }
        });
        if (mMapview != null) {
            aMap = mMapview.getMap();
        } else {
            Toast.makeText(MapActivity.this, "应用未获得相关权限", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            //默认北京
            mLanti = intent.getDoubleExtra(LATI, 39.906901);
            mLon = intent.getDoubleExtra(LONG_TI, 116.397972);
            mAreaTitle = intent.getStringExtra(TITLE);
        }
        if (aMap != null) {
            LatLng latLng = new LatLng(mLanti, mLon);
            CameraUpdate camupdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(
                    latLng, 12, 30, 0));
            aMap.moveCamera(camupdate);
            aMap.clear();
            aMap.addMarker(new MarkerOptions().position(latLng).title(mAreaTitle).snippet("DefaultMarker"));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapview.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapview.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapview.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapview.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        if (mMapview != null) {
            mMapview.onDestroy();
        }
    }
}
