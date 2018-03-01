package com.zjw.dr.ui.popular;

import com.zjw.dr.api.DrRetrofit;
import com.zjw.dr.constant.Constants;
import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.ui.base.mvp.BaseSubscribe2;
import com.zjw.dr.ui.base.mvp.BaseSubscriber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 祝锦伟 on 2018/1/30.
 */

public class PopularPresenter extends PopularContract.Presenter {
    @Override
    void getPopularShots(Map<String,String> param) {

        subscribe(DrRetrofit.getRestApi().getShotList(param), new BaseSubscriber<List<ShotEntity>>(mView) {
            @Override
            protected void onSuccess(List<ShotEntity> shotEntities) {
                mView.getPopularShotsSuccess(shotEntities);
            }
        });

   /*    subscribe2(DrRetrofit.getRestApi().getShotList(param),
               new BaseSubscribe2<List<ShotEntity>>(mView) {
                   @Override
                   protected void onSuccess(List<ShotEntity> shotEntities) {
                       mView.getPopularShotsSuccess(shotEntities);
                   }
               }

       );*/

    }
}
