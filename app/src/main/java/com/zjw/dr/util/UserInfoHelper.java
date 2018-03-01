package com.zjw.dr.util;

import android.content.Context;

import com.zjw.dr.constant.Constants;
import com.zjw.dr.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 祝锦伟 on 2018/1/22.
 */

public class UserInfoHelper {

    public static final String KEY_CURRENT_USER="user";

    private static Map<String,UserEntity> userEntityMap=new HashMap<>();

    public static UserEntity getCurrentUser(Context context){

        UserEntity currentUser=userEntityMap.get(KEY_CURRENT_USER);

        if(currentUser!=null) return currentUser;

        UserEntity user=SPHelper.getObject(Constants.KEYS.CURRENT_USER,UserEntity.class);

        if(user ==null) return null;

        userEntityMap.put(KEY_CURRENT_USER,user);

        return user;

    }

    public static void clearUserInfo(){

        userEntityMap.clear();
        SPHelper.saveObject(Constants.KEYS.CURRENT_USER,null);

    }

    public static void setUserInfo(UserEntity userInfo){

        userEntityMap.put(KEY_CURRENT_USER,userInfo);
        SPHelper.saveObject(Constants.KEYS.CURRENT_USER,userInfo);

    }





}
