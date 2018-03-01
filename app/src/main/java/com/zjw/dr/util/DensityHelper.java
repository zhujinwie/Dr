package com.zjw.dr.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by 祝锦伟 on 2018/2/25.
 */

public class DensityHelper {


    /**
     * dip 转 px
     * **/
    public static int dip2px(Context context,float dpValue){

        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

    /**
     *
     * px 转 dp
     * **/
    public static int px2dip(Context context,float pxValue){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }

    /**
     * 获取 屏幕 宽 和 高
     * **/

    public static int[] getDevicePx(Activity activity){

        DisplayMetrics metrics=new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width=metrics.widthPixels;
        int heigth=metrics.heightPixels;
        return new int[]{width,heigth};

    }

}
