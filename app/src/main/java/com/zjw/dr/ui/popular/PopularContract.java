package com.zjw.dr.ui.popular;

import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.ui.base.mvp.MvpView;
import com.zjw.dr.ui.base.mvp.RxPresenter;

import java.util.List;
import java.util.Map;

/**
 * Created by 祝锦伟 on 2018/1/30.
 */

public interface PopularContract {

    interface View extends MvpView{

        void getPopularShotsSuccess(List<ShotEntity> shots);

    }

    abstract class Presenter extends RxPresenter<View>{

        abstract void getPopularShots(Map<String,String> param);
    }

}
