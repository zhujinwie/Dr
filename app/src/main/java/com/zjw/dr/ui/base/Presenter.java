package com.zjw.dr.ui.base;

/**
 * Created by 祝锦伟 on 2018/1/22.
 */

public interface Presenter<V> {


    void attachView(V view);


    void detachView(boolean retainInstance);

}
