package com.zjw.dr.util;

import java.lang.reflect.ParameterizedType;

/**
 * Created by 祝锦伟 on 2018/1/29.
 */

public class TUtil {

    public static <T> T getT(Object o, int i) {
        try {
            ParameterizedType parameter = (ParameterizedType) (o.getClass().getGenericSuperclass());
            return ((Class<T>) parameter.getActualTypeArguments()[i]).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}

