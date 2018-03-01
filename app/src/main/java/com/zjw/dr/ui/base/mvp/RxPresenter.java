package com.zjw.dr.ui.base.mvp;

import com.zjw.dr.ui.base.BasePresenter;
import com.zjw.dr.ui.base.mvp.MvpView;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 祝锦伟 on 2018/1/22.
 */

public abstract class RxPresenter<V> extends BasePresenter<V> {

    protected <T> void subscribe(Flowable<T> observable,BaseSubscriber<T> subscriber) {
        //observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        // .subscribe(subscriber.consumer,
        // subscriber.throwable,
        // subscriber.action,
        // subscriber.subscriber);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber.consumer,subscriber.throwable);
    }


    protected <T> void subscribe2(Flowable<T> flowable,BaseSubscribe2<T> subscribe){

        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscribe);

    }

}
