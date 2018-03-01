package com.zjw.dr.ui.base.mvp;

/**
 * Created by 祝锦伟 on 2018/1/22.
 */

public interface MvpView {

    /**
     * 当presenter 获取数据过程中时调用
     * **/
    void showLoading();

    /**
     * 当presenter 获取数据成功后调用
     * **/
    void onComplete();

    /**
     *
     * **/
    void showError();


}
