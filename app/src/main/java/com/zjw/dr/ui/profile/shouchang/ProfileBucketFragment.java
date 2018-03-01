package com.zjw.dr.ui.profile.shouchang;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zjw.dr.R;
import com.zjw.dr.adapter.BucketAdapter;
import com.zjw.dr.constant.Constants;
import com.zjw.dr.entity.UserBucketEntity;
import com.zjw.dr.ui.base.mvp.BaseMvpListFragment;
import com.zjw.dr.ui.profile.guanzhu.ProfileFollowContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by 祝锦伟 on 2018/2/19.
 */

public class ProfileBucketFragment extends BaseMvpListFragment<ProfileBucketPresenter> implements ProfileBucketContract.View {

    public static final String EXTRA_USER_ID="extra_user_id";

    private String mUserId;

    @BindView(R.id.srl_bucket)
    SwipeRefreshLayout mSrl;

    @BindView(R.id.rcv_bucket)
    RecyclerView mRcv;

    private BucketAdapter mAdapter;

    public static ProfileBucketFragment newInstance(String userId){

        ProfileBucketFragment fragment=new ProfileBucketFragment();

        Bundle args=new Bundle();

        args.putString(EXTRA_USER_ID,userId);

        fragment.setArguments(args);

        return fragment;
    }




    @Override
    protected void init(View rootView, Bundle savedInstanceState) {

        mUserId=getArguments().getString(EXTRA_USER_ID);

        mAdapter=new BucketAdapter(new ArrayList<UserBucketEntity>());

        mRcv.setItemAnimator(new DefaultItemAnimator());

        mRcv.setLayoutManager(new GridLayoutManager(mContext,2));
        setUpList(mSrl,mRcv,mAdapter);

    }


    @Override
    protected void refreshData(boolean isRefresh) {
        super.refreshData(isRefresh);

        Map<String,String> params=new HashMap<>();

        params.put(Constants.KEYS.PAGE,mPage+"");

        params.put(Constants.KEYS.PAGE_SIZE,getPageSize()+"");

        mPresenter.getBuckets(mUserId,params);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_bucket;
    }

    @Override
    public void getBucketsSuccess(List<UserBucketEntity> buckets) {

        Zog("获取到收藏数据了！ bucket.size="+buckets.size());
        setData(buckets);
    }
}
