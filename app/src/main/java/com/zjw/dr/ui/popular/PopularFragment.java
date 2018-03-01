package com.zjw.dr.ui.popular;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.View;

import com.zjw.dr.R;
import com.zjw.dr.adapter.ShotAdapter;
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

public class PopularFragment extends BaseMvpListFragment<PopularPresenter> implements PopularContract.View{

    @BindView(R.id.rcv_popu)
    RecyclerView mRcv;

    @BindView(R.id.srl_popu)
    SwipeRefreshLayout mSrl;

    private String timeFrame;



    public static PopularFragment newInstance(){

        Bundle bundle=new Bundle();

        PopularFragment fragment=new PopularFragment();

        fragment.setArguments(bundle);

        return fragment;
    }




    @Override
    public void getPopularShotsSuccess(List<ShotEntity> shots) {
        setData(shots);
    }

    @Override
    protected void init(View rootView, Bundle savedInstanceState) {

        EventBus.getDefault().register(this);
        ShotAdapter adapter=new ShotAdapter(mActivity,new ArrayList<ShotEntity>());

        timeFrame=Constants.PARAMETER.SHOT_LIST_TIMEFRAME[0];
        ViewModelUtil.changeLayoutManager(mRcv, Constants.PARAMETER.VIEW_WITH_INFO);
        setUpList(mSrl,mRcv,adapter);

    }

    @Override
    protected void refreshData(boolean isRefresh) {
        super.refreshData(isRefresh);

        Zog("请求数据中！");

        Map<String,String> param=new HashMap<>();
        param.put(Constants.KEYS.PAGE_SIZE,getPageSize()+"");
        param.put(Constants.KEYS.PAGE,mPage+"");
        param.put(Constants.KEYS.SHOT_LIST_SORT,Constants.PARAMETER.SHOT_LIST_SORT[0]);
        param.put(Constants.KEYS.SHOT_LIST_TYPE,Constants.PARAMETER.SHOT_LIST_TYPE[0]);
        param.put(Constants.KEYS.SHOT_LIST_TIMEFRAME,timeFrame);

        mPresenter.getPopularShots(param);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_popular;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeViewMode(EvenMode mode){

        ViewModelUtil.changeLayoutManager(mRcv,mode.evenMode);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeTimeFrame(TimeMode mode){

        timeFrame=Constants.PARAMETER.SHOT_LIST_TIMEFRAME[mode.mode];

        refreshData(true);

    }



    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
}
