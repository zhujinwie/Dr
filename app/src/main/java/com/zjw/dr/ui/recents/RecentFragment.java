package com.zjw.dr.ui.recents;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zjw.dr.R;
import com.zjw.dr.adapter.ShotAdapter;
import com.zjw.dr.app.DrApp;
import com.zjw.dr.constant.Constants;
import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.ui.base.mvp.BaseMvpListFragment;
import com.zjw.dr.util.EvenMode;
import com.zjw.dr.util.TimeMode;
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
 * Created by 祝锦伟 on 2018/1/30.
 */

public class RecentFragment extends BaseMvpListFragment<RecentPresenter> implements RecentContract.View{

    private String timeFrame;

    @BindView(R.id.srl_recent)
    SwipeRefreshLayout mSrl;

    @BindView(R.id.rcv_recent)
    RecyclerView mRcv;

    Map<String,String> param=new HashMap<>();
    public static RecentFragment newInstance(){

        Bundle bundle=new Bundle();

        RecentFragment fragment=new RecentFragment();

        fragment.setArguments(bundle);

        return fragment;

    }

    @Override
    protected void init(View rootView, Bundle savedInstanceState) {

        EventBus.getDefault().register(this);
        timeFrame=Constants.PARAMETER.SHOT_LIST_TIMEFRAME[0];
        ShotAdapter mAdapter=new ShotAdapter(mActivity,new ArrayList<ShotEntity>());
        ViewModelUtil.changeLayoutManager(mRcv, DrApp.getAppConfig().getViewMode());
        setUpList(mSrl,mRcv,mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recent;
    }

    @Override
    public void getRecentSuccess(List<ShotEntity> shotEntities) {
        setData(shotEntities);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeViewMode(EvenMode mode){
        ViewModelUtil.changeLayoutManager(mRcv,mode.evenMode);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeTimeMode(TimeMode mode){

        timeFrame=Constants.PARAMETER.SHOT_LIST_TIMEFRAME[mode.mode];

        refreshData(true);
    }


    @Override
    protected void refreshData(boolean isRefresh) {
        super.refreshData(isRefresh);
        param.put(Constants.KEYS.PAGE,mPage+"");
        param.put(Constants.KEYS.PAGE_SIZE,getPageSize()+"");
        param.put(Constants.KEYS.SHOT_LIST_TIMEFRAME,timeFrame);
        param.put(Constants.KEYS.SHOT_LIST_SORT,Constants.PARAMETER.SHOT_LIST_SORT[2]);
        param.put(Constants.KEYS.SHOT_LIST_TYPE,Constants.PARAMETER.SHOT_LIST_TYPE[0]);
        mPresenter.getRecentShotList(param);
    }
}
