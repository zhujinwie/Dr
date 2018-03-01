package com.zjw.dr.api;

import android.text.TextUtils;
import android.util.Log;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zjw.dr.constant.Constants;
import com.zjw.dr.util.SPHelper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 祝锦伟 on 2017/11/5.
 */

public class DrRetrofit {

    private static final int TIME_OUT=15;

    private static Retrofit restRetrofit,oauthRetrofit;

    private static SPHelper mSP= SPHelper.getInstance();

    public static  synchronized  DRApi getRestApi(){

        if(restRetrofit==null){

            OkHttpClient.Builder builder=new OkHttpClient.Builder();

            String access= (String) mSP.getData(Constants.KEYS.ACCESS_TOKEN,"");
            //final String accessToken="Bearer "+Constants.PARAMETER.ACCESS_TOKE
            Log.d("Retrofit","access ="+access);

            access=TextUtils.isEmpty(access)?Constants.PARAMETER.TOKEN:access;

            final String accessToken="Bearer "+ access;
            builder.addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request=chain.request();

                    request=request.newBuilder()
                            .header("Authorization",accessToken)
                            .build();

                    return chain.proceed(request);
                }
            });

            builder.retryOnConnectionFailure(false)
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(TIME_OUT,TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT,TimeUnit.SECONDS);


            restRetrofit=new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .baseUrl(Constants.URL.BASE_URL)
                            .client(builder.build())
                            .build();

        }
        return restRetrofit.create(DRApi.class);

    }


    public static synchronized DRApi getOauthApi(){

        if(oauthRetrofit==null){

            OkHttpClient.Builder builder=new OkHttpClient.Builder();

            builder.retryOnConnectionFailure(false)
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(TIME_OUT,TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT,TimeUnit.SECONDS);

            oauthRetrofit=new Retrofit.Builder()
                            .baseUrl(Constants.URL.OAUTH_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(builder.build())
                            .build();
        }

        return oauthRetrofit.create(DRApi.class);

    }


    public static synchronized void resetRestRetrofit(){

        if(restRetrofit!=null) restRetrofit=null;

    }




}
