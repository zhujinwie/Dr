package com.zjw.dr.ui.profile.guanzhu;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zjw.dr.R;
import com.zjw.dr.adapter.FollowAdapter;
import com.zjw.dr.constant.Constants;
import com.zjw.dr.entity.FollowerEntity;
import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.ui.base.mvp.BaseMvpListFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by 祝锦伟 on 2018/2/19.
 */

public class ProfileFollowFragment extends BaseMvpListFragment<ProfileFollowPresenter> implements ProfileFollowContract.View  {

    public static final String EXTRA_USER_ID="extra_user_id";

    private String userId;

    @BindView(R.id.srl_profile_follow)
    SwipeRefreshLayout mSrl;

    @BindView(R.id.rcv_profile_follow)
    RecyclerView mRcv;

    FollowAdapter mAdapter;

    public static ProfileFollowFragment newInstance(String userId){

        ProfileFollowFragment fragment=new ProfileFollowFragment();

        Bundle args=new Bundle();

        args.putString(EXTRA_USER_ID,userId);
        fragment.setArguments(args);

        return fragment;
    }



    @Override
    protected void init(View rootView, Bundle savedInstanceState) {

        userId=getArguments().getString(EXTRA_USER_ID);

        mRcv.setLayoutManager(new LinearLayoutManager(mContext));
        mRcv.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.HORIZONTAL));

        mAdapter=new FollowAdapter(new ArrayList<FollowerEntity>());
        setUpList(mSrl,mRcv,mAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_follow;
    }


    @Override
    protected void refreshData(boolean isRefresh) {
        super.refreshData(isRefresh);

        Map<String,String> params=new HashMap<>();
        params.put(Constants.KEYS.PAGE,mPage+"");

        params.put(Constants.KEYS.PAGE_SIZE,getPageSize()+"");

        mPresenter.getFollowers(userId,params);

    }

    @Override
    public void getFollowersSuccess(List<FollowerEntity> followers) {

        Zog("获取到关注人数据了！ followers.size="+followers.size());

        setData(followers);
    }
}
