package com.zjw.dr.ui.profile.shouchang;

import com.zjw.dr.api.DrRetrofit;
import com.zjw.dr.entity.UserBucketEntity;
import com.zjw.dr.ui.base.mvp.BaseSubscriber;
import com.zjw.dr.ui.profile.guanzhu.ProfileFollowContract;

import java.util.List;
import java.util.Map;

/**
 * Created by 祝锦伟 on 2018/2/19.
 */

public class ProfileBucketPresenter extends ProfileBucketContract.Presenter {


    @Override
    void getBuckets(String userId, Map<String, String> params) {
        subscribe(DrRetrofit.getRestApi().getUserBuckets(userId, params), new BaseSubscriber<List<UserBucketEntity>>(mView) {
            @Override
            protected void onSuccess(List<UserBucketEntity> userBucketEntities) {
                mView.getBucketsSuccess(userBucketEntities);
            }
        });
    }
}
