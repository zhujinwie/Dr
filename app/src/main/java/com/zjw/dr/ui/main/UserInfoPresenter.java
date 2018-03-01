package com.zjw.dr.ui.main;

import com.zjw.dr.api.DrRetrofit;
import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.ui.base.mvp.BaseSubscriber;
import com.zjw.dr.ui.base.mvp.MvpView;


/**
 * Created by 祝锦伟 on 2018/2/1.
 */

public class UserInfoPresenter extends UserInfoContract.Presenter {


    @Override
    void getUserInfo() {
        subscribe(DrRetrofit.getRestApi().getAuthenticatedUser(),
                new BaseSubscriber<UserEntity>(mView) {
                    @Override
                    protected void onSuccess(UserEntity userEntity) {
                        mView.getUserInfoSuccess(userEntity);
                    }
                });
    }
}
