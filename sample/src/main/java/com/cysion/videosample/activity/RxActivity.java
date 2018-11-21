package com.cysion.videosample.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cysion.baselib.base.BaseActivity;
import com.cysion.videosample.R;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends BaseActivity {

    private TextView mTvData;
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rxjava;
    }

    @Override
    protected void initView() {
        mTvData = findViewById(R.id.tv_data);
    }

    public void operCreate(View view) {
        mTvData.setText("");
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                Thread.sleep(100);
                emitter.onNext("2");
                Thread.sleep(100);
                emitter.onNext("3");
                emitter.onNext("4");
                Thread.sleep(100);
                emitter.onNext("5");
                Log.e("flag--", "subscribe(RxActivity.java:53)---->>" + Thread.currentThread().getName());
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    Disposable de;

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("flag--", "onSubscribe(RxActivity.java:63)---->>" + Thread.currentThread().getName());
                        de = d;

                    }

                    @Override
                    public void onNext(String aS) {
                        Log.e("flag--", "onNext(RxActivity.java:71)---->>" + Thread.currentThread().getName());
                        mTvData.append(aS);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void operMap(View view) {
        mTvData.setText("");
        getIntObervable()
                .map(new Function<Integer, String>() {
                    int i = 0;

                    @Override
                    public String apply(Integer aInteger) {
                        String t = i + ". the number is " + aInteger;
                        i++;
                        return t;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String aS) {
                        Log.e("flag--", "accept(RxActivity.java:106)---->>" + Thread.currentThread().getName());
                        Log.e("flag--", "accept(RxActivity.java:107)---->>" + aS);

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("flag--", "onSubscribe(RxActivity.java:114)---->>" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String aS) {
                        Log.e("flag--", "onNext(RxActivity.java:119)---->>" + aS);
                        mTvData.append(aS + "\n");

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e("flag--", "onComplete(RxActivity.java:130)---->>");

                    }
                });


    }

    public void operFlatmap(View view) {
        mTvData.setText("");
        mCompositeDisposable.add(getStrObervable()
                .flatMap(new Function<String, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(String aS) throws Exception {
                        return Observable.just(aS.length());
                    }
                })
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer aInteger) throws Exception {
                mTvData.append(aInteger+"-");

            }
        }));

    }

    public void operzip(View view) {
        mTvData.setText("");

    }

    public void operConcat(View view) {
        mTvData.setText("");

    }

    public void operDistinct(View view) {
        mTvData.setText("");

    }

    public void operReduce(View view) {
        mTvData.setText("");

    }

    public void operDebounce(View view) {

        mTvData.setText("");

    }

    public void operBuffer(View view) {
        mTvData.setText("");

    }
    //-------------------------------------

    Observable<String> getStrObervable() {
        return Observable.just("java", "android", "python", "kotlin", "javascript");
    }

    Observable<Integer> getIntObervable() {
        return Observable.fromIterable(Arrays.asList(12, 32, 4, 54, 635, 98));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }
}
