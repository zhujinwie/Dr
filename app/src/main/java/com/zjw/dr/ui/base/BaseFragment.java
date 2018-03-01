package com.zjw.dr.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjw.dr.app.DrApp;
import com.zjw.dr.ui.auth.LoginContract;

import butterknife.ButterKnife;

/**
 * Created by 祝锦伟 on 2018/1/22.
 */

public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;

    protected Context mContext;

    protected ViewGroup mRootView;

    private String TAG=getClass().getSimpleName();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity=getActivity();
        mContext=getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView==null){

            mRootView= (ViewGroup) inflater.inflate(getLayoutId(),container,false);

        }

        mRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        //注意，mRootView 创建于fragment中，此处不可用mActivity
        ButterKnife.bind(this,mRootView);

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init(mRootView,savedInstanceState);
    }

    protected abstract void init(View rootView,Bundle savedInstanceState);

    protected abstract int getLayoutId();

    public void Zog(String var){

        if(DrApp.IS_DEBUG)
        Log.d(TAG,"--> "+var);
    }

}

