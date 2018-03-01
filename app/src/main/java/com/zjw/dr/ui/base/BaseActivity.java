package com.zjw.dr.ui.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zjw.dr.app.DrApp;

/**
 * Created by 祝锦伟 on 2018/1/18.
 */

public class BaseActivity extends AppCompatActivity {

    private boolean isDebug=DrApp.IS_DEBUG;

    private boolean isSetStatusBar=false;

    private boolean isSetStatusBar02=false;

    public  String TAG=getClass().getSimpleName();

    public void TagAndToast(String var){

        if(isDebug){
            Log.d(TAG,"--> "+var);
            Toast.makeText(this,var,Toast.LENGTH_SHORT).show();
        }

    }

    public void Zog(String var){

        if(isDebug){

            Log.d(TAG,"-->"+var);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(isSetStatusBar02){
            if (Build.VERSION.SDK_INT >= 21) {
                View decorView = getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                getWindow().setNavigationBarColor(Color.TRANSPARENT);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }

        }

    }

    public void showToast(String str){

        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        /**
         * 实现沉浸式体验
         * **/
        if(isSetStatusBar&&hasFocus) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                Zog("沉浸式开启");

                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                );

            }
        }


    }
}
