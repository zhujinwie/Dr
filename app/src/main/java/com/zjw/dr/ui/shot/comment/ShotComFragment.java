package com.zjw.dr.ui.shot.comment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zjw.dr.R;
import com.zjw.dr.adapter.CommentAdapter;
import com.zjw.dr.entity.ShotsCommentEntity;
import com.zjw.dr.ui.base.mvp.BaseMvpListFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by 祝锦伟 on 2018/2/4.
 */

public class ShotComFragment extends BaseMvpListFragment<ShotComPresenter> implements ShotComContract.View{

    public static final String ARGS_SHOT_ID="args_shot_id";

    @BindView(R.id.srl_comment)
    SwipeRefreshLayout mSrl;

    @BindView(R.id.rcv_comment)
    RecyclerView mRcv;

    Map<String,String> params;

    private String shotId;

    private CommentAdapter mAapter;
    public static ShotComFragment newInstance(int shotId){

        Bundle bundle=new Bundle();

        ShotComFragment shotComFragment=new ShotComFragment();

        bundle.putInt(ARGS_SHOT_ID,shotId);

        shotComFragment.setArguments(bundle);

        return shotComFragment;
    }


    @Override
    protected void init(View rootView, Bundle savedInstanceState){

        params=new HashMap<>();

        shotId=getArguments().getInt(ARGS_SHOT_ID)+"";
        mAapter=new CommentAdapter(R.layout.item_comment,new ArrayList<ShotsCommentEntity>(),mActivity);
        mRcv.setLayoutManager(new LinearLayoutManager(mContext));

        mRcv.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        setUpList(mSrl,mRcv,mAapter);

    }

    @Override
    protected String getEmptyMessage() {
        return "还没有人评论哦！";
    }

    @Override
    protected String getErrorMessage() {
        return super.getErrorMessage();
    }


    @Override
    protected void refreshData(boolean isRefresh) {
        super.refreshData(isRefresh);

        params.put("page",mPage+"");
        params.put("per_page",getPageSize()+"");
        mPresenter.getCommentList(shotId,params);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comment;
    }

    @Override
    public void getCommentSuccess(List<ShotsCommentEntity> commentList) {
        setData(commentList);
    }

    @Override
    public void likeCommentSuccess() {

    }

    @Override
    public void unLikeCommentSuccess() {

    }

    @Override
    public void createComOk() {

    }
}
