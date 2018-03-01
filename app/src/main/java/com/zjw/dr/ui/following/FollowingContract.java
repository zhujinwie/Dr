package com.zjw.dr.ui.following;

import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.entity.UserLikeEntity;
import com.zjw.dr.ui.base.mvp.MvpView;
import com.zjw.dr.ui.base.mvp.RxPresenter;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;


/**
 * Created by 祝锦伟 on 2018/1/29.
 */

public interface FollowingContract {

    interface View extends MvpView{

        void getFollowingShotListSuccess(List<UserLikeEntity> shots);

    }

    abstract class Presenter extends RxPresenter<View>{

       abstract void getFollowingShotList(String userId, Map<String,String> params);
    }


}
