package com.zjw.dr.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.zjw.dr.constant.Constants;
import com.zjw.dr.util.SPHelper;

import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.functions.Consumer;

/**
 * Created by 祝锦伟 on 2018/1/21.
 */

public class AppConfig {

    private String accessToken;

    private int mViewMode;

    private boolean isShowGif;

    private boolean isUserWifi;

    private int mPageSize;

    public AppConfig(Context context){

        accessToken= (String) SPHelper.getData(Constants.KEYS.ACCESS_TOKEN,"");

        mViewMode= (int) SPHelper.getData(Constants.KEYS.VIEW_MODE,Constants.PARAMETER.VIEW_WITH_INFO);

        isShowGif= (boolean) SPHelper.getData(Constants.KEYS.IS_SHOW_GIF,true);

        mPageSize=(int) SPHelper.getData(Constants.KEYS.PAGE_SIZE,10);
    }

    public boolean isLogin(){

        return !TextUtils.isEmpty(accessToken);
    }

    public void setAccessToken(String token){

        accessToken=token;
        SPHelper.saveData(Constants.KEYS.ACCESS_TOKEN,token);

    }

    public void setPageSize(int size){

        mPageSize=size;
        SPHelper.saveData(Constants.KEYS.PAGE_SIZE,size);
    }

    public int getPageSize(){

        return mPageSize;
    }

    public String getAccessToken(){

        return accessToken;
    }

    public boolean getIsShowGif(){
        return isShowGif;
    }

    public void setIsShowGif(boolean isShowGif){
        this.isShowGif=isShowGif;

        SPHelper.saveData(Constants.KEYS.IS_SHOW_GIF,isShowGif);
    }

    public int getViewMode(){
        return mViewMode;
    }

    public void setViewMode(int mode){

        mViewMode=mode;
        SPHelper.saveData(Constants.KEYS.VIEW_MODE,mode);
    }

    public void setUserWifi(boolean isUserWifi){

        this.isUserWifi=isUserWifi;

    }


}
