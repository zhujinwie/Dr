package com.zjw.dr.ui.recents;

import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.ui.base.mvp.MvpView;
import com.zjw.dr.ui.base.mvp.RxPresenter;

import java.util.List;
import java.util.Map;

/**
 * Created by 祝锦伟 on 2018/2/2.
 */

public interface RecentContract {

    interface View extends MvpView{

        void getRecentSuccess(List<ShotEntity> shotEntities);
    }

    abstract class Presenter extends RxPresenter<View>{

        abstract void getRecentShotList(Map<String,String> param);
    }

}
