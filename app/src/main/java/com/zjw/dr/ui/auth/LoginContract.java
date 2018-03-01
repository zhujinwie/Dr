package com.zjw.dr.ui.auth;

import com.zjw.dr.entity.UserEntity;

/**
 * Created by 祝锦伟 on 2017/11/6.
 */

public interface LoginContract {

    interface View{

        void loginSuccess(String token);

        void onError();

        void updateUserSuccess(UserEntity userEntity);

    }

    interface Presenter{

        void loadToken(String code);

        void loadUserInfo();
    }

}
