package com.zjw.dr.ui.recents;

import com.zjw.dr.api.DrRetrofit;
import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.ui.base.mvp.BaseSubscriber;

import java.util.List;
import java.util.Map;

/**
 * Created by 祝锦伟 on 2018/1/30.
 */

public class RecentPresenter extends RecentContract.Presenter{
    @Override
    void getRecentShotList(Map<String, String> param) {
        subscribe(DrRetrofit.getRestApi().getShotList(param), new BaseSubscriber<List<ShotEntity>>(mView) {
            @Override
            protected void onSuccess(List<ShotEntity> shotEntities) {
                mView.getRecentSuccess(shotEntities);
            }
        });
    }
}
