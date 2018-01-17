package com.hengyi.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hengyi.mvp.component.ApplicationComponent;
import com.hengyi.mvp.ui.base.BaseActivity;
import com.hengyi.mvp.util.ImageLoaderUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.iv_ad)
    ImageView ivAd;
    @BindView(R.id.gifImageView)
    GifImageView gifImageView;
    @BindView(R.id.tv_skip)
    TextView tvSkip;


    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public int getContentLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {}

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        final GifDrawable gifDrawable = (GifDrawable) gifImageView.getDrawable();
        gifDrawable.setLoopCount(1);
        gifImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                gifDrawable.start();
            }
        }, 100);

        ImageLoaderUtil.LoadImage(this, "http://api.dujin.org/bing/1920.php", ivAd);

        mCompositeDisposable.add(countDown(3).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                //初始化
                tvSkip.setText("跳过 4");
            }
        }).subscribeWith(new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                tvSkip.setText("跳过 " + (integer + 1));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                toMain();
            }
        }));
    }

    @Override
    public void initData() {

    }

    @Override
    public void onRetry() {

    }

    @Override
    protected void onDestroy() {
        mCompositeDisposable.dispose();
        super.onDestroy();
    }


    @OnClick(R.id.fl_ad)
    public void onViewClicked() {
        toMain();
    }


    Observable<Integer> countDown(int time){
        if(time<0) {
            time = 0;
        }
        final int countTime = time;
        return Observable.interval(0,1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong) throws Exception {
                        return countTime - aLong.intValue();
                    }
                })
                .take(countTime+1);
                //注意take操作符->只发送前count+1个事件
    }

    private void toMain() {
        mCompositeDisposable.dispose();

        Intent intent = new Intent();
        intent.setClass(WelcomeActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    /*
    * Rxjava的操作符
    *
    * Function<Long, Integer>
    * map() 将参数中的Long对象转换成一个Integer对象
    *
    * subscribeOn() 指定的是上游发送事件的线程, observeOn() 指定的是下游接收事件的线程.
    *
    * doOnSubscribe用于在call之前执行一些初始化操作
    * */
}
