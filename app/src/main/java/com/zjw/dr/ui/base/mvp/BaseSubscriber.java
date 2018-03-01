package com.zjw.dr.ui.base.mvp;


import org.reactivestreams.Subscription;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by 祝锦伟 on 2018/1/28.
 */

public abstract class BaseSubscriber<T> {

    private MvpView mView;

    private boolean mIsShowLoading;

    public Consumer<T> consumer;

    public Consumer<? super Throwable> throwable;

    public Action action;

    public Consumer<? super Subscription>  subscriber;

    public BaseSubscriber (MvpView view){
        mView=view;
        init();
    }

    public BaseSubscriber (MvpView view,boolean isShowLoading){
        mView=view;
        mIsShowLoading=isShowLoading;
        init();
    }

    private void init(){

        consumer=new Consumer<T>() {
            @Override
            public void accept(T t) throws Exception {
                BaseSubscriber.this.onNext(t);
            }
        };

        throwable=new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                BaseSubscriber.this.onError(throwable);
            }
        };

        action=new Action() {
            @Override
            public void run() throws Exception {
                BaseSubscriber.this.onComplete();
            }
        };

        subscriber=new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) throws Exception {
                BaseSubscriber.this.onSubscribe(subscription);
            }
        };

    }


    public void onSubscribe(Subscription s) {
        if(mIsShowLoading && mView != null){
            mView.showLoading();
        }
    }

    public void onNext(T t) {
        onSuccess(t);
    }

    public void onError(Throwable t) {

        t.printStackTrace();
        onFail(t.getMessage());
        if(mView!=null){
            mView.onComplete();
            if(t.getMessage().contains("404")) return;
            mView.showError();
        }

    }

    public void onComplete() {
        if(mView != null){
            mView.onComplete();
            if(mIsShowLoading) mView.showLoading();
        }
        mView = null;
    }


    protected void onFail(String msg){}

    protected abstract void onSuccess(T t);
}
