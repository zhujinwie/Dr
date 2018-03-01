package com.zjw.dr.ui.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zjw.dr.ui.base.BaseActivity;
import com.zjw.dr.util.TUtil;

/**
 * Created by 祝锦伟 on 2018/2/1.
 */

public abstract class BaseMvpActivity<P extends RxPresenter> extends BaseActivity {

    public P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter= TUtil.getT(this,0);

        if(this instanceof MvpView) mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null) mPresenter.detachView(false);
    }

    public void showLoading(){}

    public void showError(){}

    public void onComplete(){}


}
