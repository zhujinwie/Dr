package com.zjw.dr.ui.profile.guanzhu;

import com.zjw.dr.entity.FollowerEntity;
import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.ui.base.mvp.MvpView;
import com.zjw.dr.ui.base.mvp.RxPresenter;

import java.util.List;
import java.util.Map;

/**
 * Created by 祝锦伟 on 2018/2/19.
 */

public interface ProfileFollowContract {

    interface View extends MvpView{

        void getFollowersSuccess(List<FollowerEntity> followers);

    }

    abstract class Presenter extends RxPresenter<View>{

        abstract void getFollowers(String userId,Map<String,String> params);

    }
}
