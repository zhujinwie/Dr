package com.zjw.dr.ui.auth;

import android.util.Log;

import com.zjw.dr.api.DrRetrofit;
import com.zjw.dr.constant.Constants;
import com.zjw.dr.entity.AccessToken;
import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.util.SPHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 祝锦伟 on 2017/11/6.
 */

public class LoginPresenter implements LoginContract.Presenter{


    private LoginContract.View mView;

    private  String TAG=getClass().getSimpleName();

    public LoginPresenter(LoginContract.View view){
        mView=view;
    }

    @Override
    public void loadToken(String code) {

        Log.d(TAG,"-->loadToken  + code="+code);

        DrRetrofit.getOauthApi()
                    .getAccessToken(Constants.PARAMETER.CLIENT_ID,
                            Constants.PARAMETER.CLIENT_SECRET,
                            code,
                            Constants.URL.REDIRECT_URL)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Consumer<AccessToken>() {
                        @Override
                        public void accept(AccessToken accessToken) throws Exception {
                            String access = accessToken.getAccess_token();
                            if (access == null & access.length() == 0) {

                                Log.d(TAG, "login failed!  accessToken=" + access);

                                mView.onError();

                                return;
                            }

                            mView.loginSuccess(access);
                            Log.d(TAG, "loginSuccess OJBK! access=" + access + " ;token_type=" + accessToken.getToken_type() + " ;scope=" + accessToken.getScope());

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                            Log.d("LoginPreseter","throwable="+throwable);

                            if(throwable.getMessage().contains("404")) return;
                            mView.onError();
                        }
                    });

    }

    @Override
    public void loadUserInfo() {

        DrRetrofit.getRestApi().getAuthenticatedUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<UserEntity>() {
                    @Override
                    public void accept(UserEntity userEntity) throws Exception {
                        mView.updateUserSuccess(userEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError();
                    }
                });

    }
}
