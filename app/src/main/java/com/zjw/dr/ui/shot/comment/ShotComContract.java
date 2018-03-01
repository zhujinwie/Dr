package com.zjw.dr.ui.shot.comment;

import com.zjw.dr.entity.ShotsCommentEntity;
import com.zjw.dr.ui.base.mvp.MvpView;
import com.zjw.dr.ui.base.mvp.RxPresenter;

import java.util.List;
import java.util.Map;

/**
 * Created by 祝锦伟 on 2018/2/4.
 */

public interface ShotComContract {

    interface View extends MvpView{

        void getCommentSuccess(List<ShotsCommentEntity> commentList);

        void likeCommentSuccess();

        void unLikeCommentSuccess();

        void createComOk();

    }

    abstract class Presenter extends RxPresenter<View>{

        abstract void getCommentList(String shotId, Map<String,String> params);

        abstract void likeComment();

        abstract void unlikeComment();

        abstract void createComment();

    }






}
