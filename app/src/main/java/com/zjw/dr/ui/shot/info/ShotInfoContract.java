package com.zjw.dr.ui.shot.info;

import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.ui.base.mvp.MvpView;
import com.zjw.dr.ui.base.mvp.RxPresenter;

/**
 * Created by 祝锦伟 on 2018/2/5.
 */

public interface ShotInfoContract {

    interface View extends MvpView{

        void getShotSuccess(ShotEntity shotEntity);

        void checkLikeShotSuccess(boolean isLike);

        void checkFollowSuccess(boolean isFollow);

        void likeShotSuccess();

        void collectShotSuccess();

        void downLoadShotSuccess();

        void shareShotSuccess();

        void followUserSuccess();

        void unFollowUserSuccess();

        void unLikeShotSuccess();
    }


    abstract class Presenter extends RxPresenter<View>{

        abstract void getShot(String shotId);

        abstract void likeShot(String shotId);

        abstract void collectShot(String shotId,String bucketId);

        abstract void downLoadShot(String url);

        abstract void shareShot(String url);

        abstract void followUser(String userId);

        abstract void checkLike(String shotId);

        abstract void checkFollow(String userId);

        abstract void unFollowUser(String userId);

        abstract void unLikeShot(String shotId);

    }




}
