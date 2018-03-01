package com.zjw.dr.ui.profile.tougao;

import android.app.ActionBar;

import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.ui.base.mvp.MvpView;
import com.zjw.dr.ui.base.mvp.RxPresenter;

import java.util.List;
import java.util.Map;

/**
 * Created by 祝锦伟 on 2018/2/19.
 */

public interface ProfileProjectContract {

    interface View extends MvpView{

        void getProjectsSuccess(List<ShotEntity> shots);

    }

    abstract class Presenter extends RxPresenter<View>{

        abstract void getProjects(String userId,Map<String,String> params);

    }


}
