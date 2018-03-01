package com.zjw.dr.ui.main;

import android.media.MediaRouter;

import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.ui.base.mvp.MvpView;
import com.zjw.dr.ui.base.mvp.RxPresenter;

import io.reactivex.Flowable;

/**
 * Created by 祝锦伟 on 2018/2/1.
 */

public interface UserInfoContract {

    interface View extends MvpView{
        void getUserInfoSuccess(UserEntity userEntity);
    }

    abstract class Presenter extends RxPresenter<View>{

       abstract void getUserInfo();

    }



}
