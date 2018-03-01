package com.zjw.dr.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.zjw.dr.R;
import com.zjw.dr.constant.Constants;
import com.zjw.dr.ui.base.BaseActivity;
import com.zjw.dr.util.SPHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zjw.dr.constant.Constants.URL.AUTHORIZE_URL;

/**
 * Created by 祝锦伟 on 2017/11/1.
 */

public class AuthActivity extends BaseActivity{

    @BindView(R.id.progressbar_auth)
    ProgressBar mProgressBar;

    @BindView(R.id.webview_auth)
    WebView mWebView;

    private SPHelper mSP= SPHelper.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        Zog("--> onCreate!");
        setUpWebView();

    }

    private void setUpWebView() {

        Zog("--> setUpWebView!");

        mWebView.clearCache(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.setWebViewClient(new OauthClient());
        mWebView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {


                Zog("onProgressChanged!");

                if(newProgress==100){

                    mProgressBar.setVisibility(View.GONE);
                }else{

                    if(View.VISIBLE!=mProgressBar.getVisibility()){

                        mProgressBar.setVisibility(View.VISIBLE);
                    }

                    mProgressBar.setProgress(newProgress);

                }

                super.onProgressChanged(view, newProgress);
            }


        });

        mWebView.loadUrl(String.format(AUTHORIZE_URL,mSP.getData(Constants.KEYS.CLIENT_ID,Constants.PARAMETER.CLIENT_ID),Constants.PARAMETER.SCOPES,Constants.URL.REDIRECT_URL,Constants.PARAMETER.STATE));

    }

  /*  @Override
    public void loginSuccess() {

        Log.d(TAG,"loginSuccess!");

        mSP.saveData(Constants.KEYS.IS_AUTH_SUCCESS,true);
        DrRetrofit.resetRestRetrofit();
        Intent intent=new Intent(AuthActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }*/


/*    @Override
    public void loginFailed() {

        Zog("-->loginFailed!");
        mSP.saveData(Constants.KEYS.IS_AUTH_SUCCESS,false);
        //Toast.makeText(AuthActivity.this,"输入有误，请重新输入！",Toast.LENGTH_SHORT).show();
        finish();

    }*/

    class OauthClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Zog("shouldOverrideUrl: url="+url);

            if(url.contains("code")){

                String code=url.replace(Constants.URL.REDIRECT_URL+"?code=","")
                        .replace("&state=zjw","");
                Zog("code="+code);
                Intent intent=new Intent();
                intent.putExtra(Constants.KEYS.CODE,code);
                setResult(RESULT_OK,intent);
                finish();
            }else{

                view.loadUrl(url);
            }
            return true;
        }

    }
}
