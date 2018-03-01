package com.zjw.dr.ui.debuts;

import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.ui.base.mvp.MvpView;
import com.zjw.dr.ui.base.mvp.RxPresenter;

import java.util.List;
import java.util.Map;

/**
 * Created by 祝锦伟 on 2018/1/30.
 */

public interface DebutsConstract {

    interface View extends MvpView{

        void getDebutShotListSuccess(List<ShotEntity> shots);
    }

    abstract class Presenter extends RxPresenter<View>{

        abstract void getDebutShotList(Map<String,String> param);

    }

}
