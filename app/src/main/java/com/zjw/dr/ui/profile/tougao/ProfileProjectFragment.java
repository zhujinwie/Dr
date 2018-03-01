package com.zjw.dr.ui.profile.tougao;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zjw.dr.R;
import com.zjw.dr.adapter.ProfileShotAdapter;
import com.zjw.dr.adapter.ShotAdapter;
import com.zjw.dr.constant.Constants;
import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.ui.base.mvp.BaseMvpListFragment;
import com.zjw.dr.util.EvenMode;
import com.zjw.dr.util.ViewModelUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * Created by 祝锦伟 on 2018/2/19.
 */

public class ProfileProjectFragment extends BaseMvpListFragment<ProfileProjectPresenter> implements ProfileProjectContract.View{

    public static final String EXTRA_USER_ID="extra_user_id";

    private String mUserId;

    @BindView(R.id.srl_project)
    SwipeRefreshLayout mSrl;

    @BindView(R.id.rcv_project)
    RecyclerView mRcv;

    ProfileShotAdapter mAdapter;

    public static ProfileProjectFragment newInstance(String userId){

        ProfileProjectFragment fragment=new ProfileProjectFragment();

        Bundle args=new Bundle();

        args.putString(EXTRA_USER_ID,userId);

        fragment.setArguments(args);

        return fragment;

    }

    @Override
    protected void init(View rootView, Bundle savedInstanceState) {

        mUserId=getArguments().getString(EXTRA_USER_ID);

        mRcv.setLayoutManager(new GridLayoutManager(mActivity,2));

        mAdapter=new ProfileShotAdapter(new ArrayList<ShotEntity>());

        setUpList(mSrl,mRcv,mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void refreshData(boolean isRefresh) {
        super.refreshData(isRefresh);
        Map<String,String> params=new HashMap<>();

        params.put(Constants.KEYS.PAGE,mPage+"");

        params.put(Constants.KEYS.PAGE_SIZE,getPageSize()+"");

        mPresenter.getProjects(mUserId,params);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_project;
    }

    @Override
    public void getProjectsSuccess(List<ShotEntity> shots) {

        Zog("获取用户shot成成功！ shots.size="+shots.size());

        setData(shots);
    }
}
