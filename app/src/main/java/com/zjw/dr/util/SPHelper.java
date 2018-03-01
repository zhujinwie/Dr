package com.zjw.dr.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Set;

/**
 * Created by 祝锦伟 on 2018/1/18.
 */

public class SPHelper {


    private static final String FILE_NAME = "dribbbble_data";

    private static SharedPreferences mSP;

    private static SPHelper instance;

    private static SharedPreferences.Editor editor;

    private static Gson gson;

    private SPHelper(Context context) {

        mSP = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (instance == null) {

            instance = new SPHelper(context);
        }

        gson = new Gson();

        editor = mSP.edit();
    }

    public static SPHelper getInstance() {

        if (instance == null) {
            throw new RuntimeException("init first!!!");
        }

        return instance;
    }


    public static void saveData(String key, Object data) {

        String type = data.getClass().getSimpleName();

        //SharedPreferences.Editor editor=mSP.edit();

        if ("Integer".equals(type)) {

            editor.putInt(key, (Integer) data);
        } else if ("Boolean".equals(type)) {

            editor.putBoolean(key, (Boolean) data);
        } else if ("Float".equals(type)) {

            editor.putFloat(key, (Float) data);
        } else if ("Long".equals(type)) {

            editor.putLong(key, (Long) data);
        } else if ("String".equals(type)) {

            editor.putString(key, (String) data);
        } else if ("Set".equals(type)) {

            editor.putStringSet(key, (Set<String>) data);
        }

        editor.commit();

    }


    //以gson字符串形式保存对象
    public static void saveObject(String keyName, Object object) {

        //SharedPreferences.Editor editor=mSP.edit();

        String str = gson.toJson(object);

        editor.putString(keyName, str);

        editor.commit();

    }

    public static Object getData(String key, Object defValue) {

        String type = defValue.getClass().getSimpleName();

        if ("Integer".equals(type)) {

            return mSP.getInt(key, (Integer) defValue);

        } else if ("Float".equals(type)) {

            return mSP.getFloat(key, (Float) defValue);

        } else if ("Boolean".equals(type)) {

            return mSP.getBoolean(key, (Boolean) defValue);

        } else if ("String".equals(type)) {

            return mSP.getString(key, (String) defValue);

        } else if ("Long".equals(type)) {

            return mSP.getLong(key, (Long) defValue);
        }

        return null;

    }

    //TODO 数据的其他处理方法
    public static <T> T getObject(String keyName, Class<?> t) {

        String str = mSP.getString(keyName, null);

        try {

            if (str.length() != 0 & str != null) {

                return (T) gson.fromJson(str, t);
            }

        } catch (Exception e) {

        }

        return null;
    }

    public static void clearAll(){

        editor.clear();
    }



}
