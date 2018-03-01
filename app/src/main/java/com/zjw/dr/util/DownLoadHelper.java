package com.zjw.dr.util;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.ui.base.BaseActivity;

import java.io.File;
import java.util.concurrent.ExecutionException;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 祝锦伟 on 2018/2/21.
 */

public class DownLoadHelper {

    public static final String FILE_NAME="dr";

    public static void downLoadImage(final BaseActivity activity, final ShotEntity shotEntity){

        downLoadImage(activity,shotEntity.getImages().getHidpi(),shotEntity.getTitle(),shotEntity.isAnimated());
    }


    public static void downLoadImage(final BaseActivity activity,final String url,final String title,final boolean isAnimated){

       Observable.create(new ObservableOnSubscribe<File>() {
           @Override
           public void subscribe(ObservableEmitter<File> e) throws Exception {

               try{

                   e.onNext(GlideHelper.downLoadImage(activity,url));

               }catch (InterruptedException | ExecutionException exception){

                   exception.printStackTrace();
                   e.onError(exception);
               }
           }
       }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<File>() {
                   @Override
                   public void accept(File file) throws Exception {
                       String suffix = isAnimated ? ".gif" : ".png";
                       File saveFile = FileUtils.saveToExternalCustomDir(FileUtils.getBytesFromFile(file), FILE_NAME,
                               title + suffix);
                       if (saveFile != null) {

                           activity.showToast("保存成功！");
                           activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(saveFile)));
                       } else {
                           activity.showToast("下载失败，请重试");
                       }
                   }
               }, new Consumer<Throwable>() {
                   @Override
                   public void accept(Throwable throwable) throws Exception {

                       activity.showToast("下载失败，请重试！");
                   }
               });
    }


}
