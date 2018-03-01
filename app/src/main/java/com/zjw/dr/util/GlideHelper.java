package com.zjw.dr.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.manager.TargetTracker;
import com.bumptech.glide.request.target.Target;
import com.zjw.dr.R;
import com.zjw.dr.app.GlideApp;
import com.zjw.dr.constant.Constants;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Created by 祝锦伟 on 2018/1/18.
 */

public class GlideHelper {

    //Glide工具类
    public static void loadGif(Context context, String url, ImageView imageView, int width, int heigth) {

        if ((boolean) SPHelper.getData(Constants.KEYS.IS_SHOW_GIF, true)) {
            GlideApp.with(context)
                    .asGif()
                    .override(width, heigth)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.shape_grey)
                    .error(new ColorDrawable(Color.WHITE))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imageView);
        }else{
            GlideApp.with(context)
                    .asBitmap()
                    .override(width,heigth)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.shape_grey)
                    .error(new ColorDrawable(Color.WHITE))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(imageView);
        }

    }

    public static void loadImageWithThumb(Context context,String url,ImageView imageView){

        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .thumbnail(0.2f)
                .placeholder(R.drawable.shape_grey)
                .error(new ColorDrawable(Color.WHITE))
                .into(imageView);

    }

    public static void setAvatar(Context context,String url,ImageView imageView){

        GlideApp.with(context)
                .load(url)
                .transform(new GlideCircleTransform(context))
                .placeholder(R.drawable.shape_corner_blue)
                .into(imageView);

    }


    public static File downLoadImage(Context context,String url) throws InterruptedException,ExecutionException{

        return GlideApp.with(context)
                .load(url)
                .downloadOnly(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
                .get();
    }


    public static void loadImageWithOutThumb(Context context,String url,ImageView imageView){

        imageView.setTag(null);

        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.shape_grey)
                .centerCrop()
                .error(new ColorDrawable(Color.WHITE))
                .into(imageView);

    }


}
