package com.zjw.dr.ui.profile.shouchang;

import com.zjw.dr.entity.UserBucketEntity;
import com.zjw.dr.ui.base.mvp.MvpView;
import com.zjw.dr.ui.base.mvp.RxPresenter;

import java.util.List;
import java.util.Map;

/**
 * Created by 祝锦伟 on 2018/2/19.
 */

public interface ProfileBucketContract {

    interface View extends MvpView{

        void getBucketsSuccess(List<UserBucketEntity> buckets);

    }

    abstract class Presenter extends RxPresenter<View>{

        abstract void getBuckets(String userId, Map<String,String> params);
    }
}
