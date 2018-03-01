package com.zjw.dr.ui.profile.guanzhu;

import com.zjw.dr.api.DrRetrofit;
import com.zjw.dr.constant.Constants;
import com.zjw.dr.entity.FollowerEntity;
import com.zjw.dr.ui.base.mvp.BaseSubscriber;

import java.util.List;
import java.util.Map;

/**
 * Created by 祝锦伟 on 2018/2/19.
 */

public class ProfileFollowPresenter extends ProfileFollowContract.Presenter {
    @Override
    void getFollowers(String userId, Map<String, String> params) {
        subscribe(DrRetrofit.getRestApi().getFollowers(userId, params), new BaseSubscriber<List<FollowerEntity>>(mView) {
            @Override
            protected void onSuccess(List<FollowerEntity> followerEntities) {
                mView.getFollowersSuccess(followerEntities);
            }
        });
    }
}
