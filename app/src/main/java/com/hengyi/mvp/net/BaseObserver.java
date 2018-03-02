package com.hengyi.mvp.net;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by honghengqiang on 2018/2/21.
 */

public abstract class BaseObserver<T> implements Observer<T> {

    public abstract void onSucess(T t);

    public abstract void onFail(Throwable e);


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSucess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFail(e);
    }

    @Override
    public void onComplete() {

    }
}
