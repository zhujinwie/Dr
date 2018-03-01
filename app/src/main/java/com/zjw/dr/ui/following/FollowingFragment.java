package com.zjw.dr.ui.following;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zjw.dr.R;
import com.zjw.dr.adapter.ShotAdapter;
import com.zjw.dr.app.DrApp;
import com.zjw.dr.constant.Constants;
import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.entity.UserLikeEntity;
import com.zjw.dr.ui.auth.LoginActivity;
import com.zjw.dr.ui.base.mvp.BaseMvpListFragment;
import com.zjw.dr.util.EvenMode;
import com.zjw.dr.util.UserInfoHelper;
import com.zjw.dr.util.ViewModelUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 祝锦伟 on 2018/1/29.
 */

public class FollowingFragment extends BaseMvpListFragment<FollowingPresenter> implements FollowingContract.View {


    @BindView(R.id.srl_follow)
    SwipeRefreshLayout mSrL;

    @BindView(R.id.rcv_follow)
    RecyclerView mRcV;

    @BindView(R.id.layout_follow_error)
    RelativeLayout mUnLoginLayout;

    public static FollowingFragment newInstance(){

        FollowingFragment fragment=new FollowingFragment();
        Bundle bundle=new Bundle();
        fragment.setArguments(bundle);
        return fragment;

    }


    @Override
    public void getFollowingShotListSuccess(List<UserLikeEntity> shots) {
        Zog("获取到关注数据了！ shots.size="+shots.size());

        List<ShotEntity> datas=new ArrayList<>(getPageSize());

        for(UserLikeEntity likeEntity:shots){
            datas.add(likeEntity.getShotEntity());
        }
        setData (datas);
    }

    @Override
    protected void init(View rootView, Bundle savedInstanceState) {

        EventBus.getDefault().register(this);
        ShotAdapter shotAdapter=new ShotAdapter(mActivity,new ArrayList<ShotEntity>());
        ViewModelUtil.changeLayoutManager(mRcV, DrApp.getAppConfig().getViewMode());

        setUpList(mSrL,mRcV,shotAdapter);


    }

    @OnClick(R.id.btn_follow_login)
    void toLogin(){

        Intent intent=new Intent(mActivity, LoginActivity.class);
        mActivity.startActivity(intent);
    }


    @Override
    protected void refreshData(boolean isRefresh) {
        super.refreshData(isRefresh);

        if(DrApp.getAppConfig().isLogin()){

            mUnLoginLayout.setVisibility(View.GONE);

            Map<String,String> params=new HashMap<>();

            params.put(Constants.KEYS.PAGE_SIZE,getPageSize()+"");
            params.put(Constants.KEYS.PAGE,mPage+"");

            mPresenter.getFollowingShotList(UserInfoHelper.getCurrentUser(mContext).getId()+"",params);
        }else{
            mUnLoginLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_follow;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeViewMode(EvenMode mode){
        ViewModelUtil.changeLayoutManager(mRcV,mode.evenMode);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
