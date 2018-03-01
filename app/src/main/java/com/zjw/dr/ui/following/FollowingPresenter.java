package com.zjw.dr.ui.following;

import com.zjw.dr.api.DRApi;
import com.zjw.dr.api.DrRetrofit;
import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.entity.UserLikeEntity;
import com.zjw.dr.ui.base.mvp.BaseSubscriber;
import com.zjw.dr.ui.base.mvp.MvpView;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by 祝锦伟 on 2018/1/29.
 */

public class FollowingPresenter extends FollowingContract.Presenter {
    @Override
    void getFollowingShotList(String userId,Map<String,String> params) {

        subscribe(DrRetrofit.getRestApi().getUserLikeShots(userId, params),
                new BaseSubscriber<List<UserLikeEntity>>(mView) {
                    @Override
                    protected void onSuccess(List<UserLikeEntity> shotEntities) {
                          mView.getFollowingShotListSuccess(shotEntities);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        if(t.getMessage().contains("404"))return;
                    }


                }
        );
    }
}
