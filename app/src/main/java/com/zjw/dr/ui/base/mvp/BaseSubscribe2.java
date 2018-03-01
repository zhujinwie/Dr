package com.zjw.dr.ui.base.mvp;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by 祝锦伟 on 2018/2/3.
 */

public abstract class BaseSubscribe2<T> implements Subscriber<T> {


    private MvpView mView;

    private boolean mIsShowLoading;

    public BaseSubscribe2(MvpView mView){

        this.mView=mView;

    }

    public BaseSubscribe2(MvpView mView,boolean mIsShowLoading){

        this.mView=mView;
        this.mIsShowLoading=mIsShowLoading;
    }


    @Override
    public void onSubscribe(Subscription s) {

        Log.d("BaseSubscribe2","--> onSubscribe!");
        if(mIsShowLoading && mView != null){
            mView.showLoading();
        }
    }

    @Override
    public void onNext(T t) {
        Log.d("BaseSubscribe2","--> onNext!");

        onSuccess(t);
    }

    @Override
    public void onError(Throwable t) {

        Log.d("BaseSubscribe2","--> onError!");
        t.printStackTrace();
        onFail(t.getMessage());
        if(mView!=null){
            mView.onComplete();
            if(t.getMessage().contains("404")) return;
            mView.showError();
        }
    }

    @Override
    public void onComplete() {

        Log.d("BaseSubscribe2","--> onComplete!");
        if(mView != null){
            mView.onComplete();
            if(mIsShowLoading) mView.showLoading();
        }
        mView = null;
    }


    protected void onFail(String msg){}

    protected abstract void onSuccess(T t);

}