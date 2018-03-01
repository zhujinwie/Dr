package com.zjw.dr.ui.shot.comment;

import com.zjw.dr.api.DrRetrofit;
import com.zjw.dr.entity.ShotsCommentEntity;
import com.zjw.dr.ui.base.mvp.BaseSubscriber;

import java.util.List;
import java.util.Map;

/**
 * Created by 祝锦伟 on 2018/2/4.
 */

public class ShotComPresenter extends ShotComContract.Presenter {
    @Override
    void getCommentList(String shotId,Map<String,String> params) {
        subscribe(DrRetrofit.getRestApi().getShotComments(shotId, params),

                new BaseSubscriber<List<ShotsCommentEntity>>(mView) {
                    @Override
                    protected void onSuccess(List<ShotsCommentEntity> shotsCommentEntities) {
                        mView.getCommentSuccess(shotsCommentEntities);
                    }
                });
    }

    @Override
    void likeComment() {

    }

    @Override
    void unlikeComment() {

    }

    @Override
    void createComment() {

    }
}
