package com.zjw.dr.ui.base.mvp;

import android.os.Bundle;
import android.view.View;

import com.zjw.dr.ui.base.BaseFragment;
import com.zjw.dr.util.TUtil;

/**
 * Created by 祝锦伟 on 2018/2/5.
 */

public class BaseMvpFragment<P extends RxPresenter> extends BaseFragment {

    public P mPresenter;

    @Override
    protected void init(View rootView, Bundle savedInstanceState) {

        mPresenter= TUtil.getT(this,0);
        if(this instanceof MvpView) mPresenter.attachView(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null) mPresenter.detachView(false);

    }

    public void showLoading(){}

    public void showError(){}

    public void onComplete(){}
}
