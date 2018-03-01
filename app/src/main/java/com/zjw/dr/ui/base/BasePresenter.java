package com.zjw.dr.ui.base;

import android.support.annotation.Nullable;

import com.zjw.dr.ui.base.mvp.MvpView;

import java.lang.ref.WeakReference;

/**
 * Created by 祝锦伟 on 2018/1/22.
 */

public class BasePresenter<V> implements Presenter<V>{

    private WeakReference<V> mViewRef;

    public V mView;

    @Override
    public void attachView(V view) {
        mViewRef=new WeakReference<>(view);
        mView=mViewRef.get();
    }

    @Override
    public void detachView(boolean retainInstance) {

        if(mViewRef!=null){

            mViewRef.clear();
            mViewRef=null;
        }

    }

    public boolean isViewAttach(){

        return mViewRef !=null && mViewRef.get() != null;
    }

    @Nullable
    public V getView(){

        return mViewRef == null ? null : mViewRef.get();
    }





}
