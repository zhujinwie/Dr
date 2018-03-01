package com.zjw.dr.ui.profile.tougao;

import com.zjw.dr.api.DrRetrofit;
import com.zjw.dr.app.DrApp;
import com.zjw.dr.constant.Constants;
import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.ui.base.mvp.BaseSubscriber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 祝锦伟 on 2018/2/19.
 */

public class ProfileProjectPresenter extends ProfileProjectContract.Presenter {
    @Override
    void getProjects(String userId,Map<String,String> params) {
        subscribe(DrRetrofit.getRestApi().getUserShots(userId, params), new BaseSubscriber<List<ShotEntity>>(mView) {
            @Override
            protected void onSuccess(List<ShotEntity> shotEntities) {
                mView.getProjectsSuccess(shotEntities);
            }
        });
    }
}
