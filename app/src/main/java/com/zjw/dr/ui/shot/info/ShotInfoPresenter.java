package com.zjw.dr.ui.shot.info;

import com.zjw.dr.api.DrRetrofit;
import com.zjw.dr.entity.CheckLikeEntity;
import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.ui.base.mvp.BaseSubscriber;

/**
 * Created by 祝锦伟 on 2018/2/5.
 */

public class ShotInfoPresenter extends ShotInfoContract.Presenter {


    @Override
    void getShot(String shotId) {
        subscribe(DrRetrofit.getRestApi().getSingleShot(shotId), new BaseSubscriber<ShotEntity>(mView) {
            @Override
            protected void onSuccess(ShotEntity shotEntity) {
                mView.getShotSuccess(shotEntity);
            }
        });

    }

    @Override
    void likeShot(String shotId) {

        subscribe(DrRetrofit.getRestApi().likeShots(shotId), new BaseSubscriber<CheckLikeEntity>(mView) {
            @Override
            protected void onSuccess(CheckLikeEntity checkLikeEntity) {
                mView.likeShotSuccess();
            }
        });
    }

    @Override
    void collectShot(String shotId,String bucketId) {

        subscribe(DrRetrofit.getRestApi().addShotsToBuckets(bucketId, shotId), new BaseSubscriber<String>(mView) {
            @Override
            protected void onSuccess(String s) {
                mView.collectShotSuccess();
            }
        });


    }

    @Override
    void downLoadShot(String url) {




    }

    @Override
    void shareShot(String url) {

    }

    @Override
    void followUser(String userId) {

        subscribe(DrRetrofit.getRestApi().followUser(userId), new BaseSubscriber<String>(mView) {
            @Override
            protected void onSuccess(String s) {
                mView.followUserSuccess();
            }
        });
    }

    @Override
    void checkLike(String shotId) {
        subscribe(DrRetrofit.getRestApi().checkShotsLike(shotId), new BaseSubscriber<CheckLikeEntity>(mView) {
            @Override
            protected void onSuccess(CheckLikeEntity checkLikeEntity) {
                mView.checkLikeShotSuccess(true);
            }

            @Override
            protected void onFail(String msg) {
                super.onFail(msg);
                mView.checkLikeShotSuccess(false);
            }
        });
    }

    @Override
    void checkFollow(String userId) {

        subscribe(DrRetrofit.getRestApi().checkFollowing(userId), new BaseSubscriber<String>(mView) {
            @Override
            protected void onSuccess(String s) {
                mView.checkFollowSuccess(true);
            }

            @Override
            protected void onFail(String msg) {
                super.onFail(msg);

                mView.checkFollowSuccess(false);
            }
        });
    }

    @Override
    void unFollowUser(String userId) {

        subscribe(DrRetrofit.getRestApi().unFollowUser(userId), new BaseSubscriber<String>(mView) {
            @Override
            protected void onSuccess(String s) {
                mView.unFollowUserSuccess();
            }
        });
    }

    @Override
    void unLikeShot(String shotId) {

        subscribe(DrRetrofit.getRestApi().unlikeShots(shotId), new BaseSubscriber<CheckLikeEntity>(mView) {
            @Override
            protected void onSuccess(CheckLikeEntity checkLikeEntity) {
             mView.unLikeShotSuccess();
            }
        });

    }
}
