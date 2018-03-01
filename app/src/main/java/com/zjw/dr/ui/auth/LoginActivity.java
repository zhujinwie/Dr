package com.zjw.dr.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zjw.dr.R;
import com.zjw.dr.api.DrRetrofit;
import com.zjw.dr.app.DrApp;
import com.zjw.dr.constant.Constants;
import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.ui.base.BaseActivity;
import com.zjw.dr.util.UserInfoHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 祝锦伟 on 2018/1/18.
 */

public class LoginActivity  extends BaseActivity implements LoginContract.View {

    @BindView(R.id.auth_btn_login)
    Button mLoginBtn;

    @BindView(R.id.unauth_tv_login)
    TextView mUnAuthTv;

    @BindView(R.id.rootview_splash)
    FrameLayout mRootView;

    @BindView(R.id.progress_pb_login)
    ProgressBar mProgressBar;

    @BindView(R.id.welcome_tv_login)
    TextView mWelcomeTv;

    private static final int REQUEST_AUTH_CODE=1;

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Animation animation=AnimationUtils.loadAnimation(this,R.anim.splash);
        mRootView.setAnimation(animation);

        mPresenter=new LoginPresenter(this);
    }


    @OnClick(R.id.auth_btn_login)
    void startAuth(){

        Zog("跳转到登陆界面!");
        startActivityForResult(new Intent(LoginActivity.this, AuthActivity.class),REQUEST_AUTH_CODE);

    }

    @OnClick(R.id.unauth_tv_login)
    void unAuth(){

        Zog("取消登陆哦！");
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( resultCode==RESULT_OK && requestCode== REQUEST_AUTH_CODE){

            String code=data.getStringExtra(Constants.KEYS.CODE);
            Zog("onREsult获取code 成功！ code="+code);
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setIndeterminate(true);
            mLoginBtn.setVisibility(View.GONE);
            mWelcomeTv.setText("欢迎回来!");
            mUnAuthTv.setVisibility(View.GONE);
            mPresenter.loadToken(code);

        }
    }

    @Override
    public void loginSuccess(String token) {

        DrApp.getAppConfig().setAccessToken(token);
        DrRetrofit.resetRestRetrofit();
        mPresenter.loadUserInfo();
    }

    @Override
    public void onError() {

        Zog("登陆出错！");
        mLoginBtn.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateUserSuccess(UserEntity userEntity) {

        Zog("开始更新userInfo设置！");
        UserInfoHelper.setUserInfo(userEntity);
        setResult(RESULT_OK);
        finish();
    }
}
