package com.zjw.dr.app;

import android.app.Application;
import android.content.Context;

import com.zjw.dr.util.SPHelper;

/**
 * Created by 祝锦伟 on 2018/1/18.
 */

public class DrApp extends Application {

    public static Context mContext;

    private static AppConfig mAppConfig;

    public static final boolean IS_DEBUG=true;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        SPHelper.init(mContext);
        mAppConfig=new AppConfig(mContext);
    }


    public static AppConfig getAppConfig(){

        return mAppConfig;
    }






}