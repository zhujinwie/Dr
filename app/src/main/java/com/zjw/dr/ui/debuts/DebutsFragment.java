package com.zjw.dr.ui.debuts;

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

public class DebutsFragment extends BaseMvpListFragment<DebutsPresenter> implements DebutsConstract.View{

    private String timeFrame;

    @BindView(R.id.srl_debut)
    SwipeRefreshLayout mSrl;

    @BindView(R.id.rcv_debut)
    RecyclerView mRcv;

    private Map<String,String> params=new HashMap<>();

    @Override
    protected void init(View rootView, Bundle savedInstanceState) {

        timeFrame= Constants.PARAMETER.SHOT_LIST_TIMEFRAME[0];
        EventBus.getDefault().register(this);

        ViewModelUtil.changeLayoutManager(mRcv, DrApp.getAppConfig().getViewMode());
        setUpList(mSrl,mRcv,new ShotAdapter(mActivity,new ArrayList<ShotEntity>()));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_debuts;
    }

    public static DebutsFragment newInstance(){

        DebutsFragment fragment=new DebutsFragment();

        Bundle bundle=new Bundle();

        fragment.setArguments(bundle);

        return fragment;

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
    public void changeTimeFrame(TimeMode mode){
        timeFrame=Constants.PARAMETER.SHOT_LIST_TIMEFRAME[mode.mode];

        refreshData(true);

    }

    @Override
    protected void refreshData(boolean isRefresh) {
        super.refreshData(isRefresh);

        Zog("请求网络数据！");
        params.put(Constants.KEYS.PAGE_SIZE,getPageSize()+"");

        params.put(Constants.KEYS.PAGE,mPage+"");

        params.put(Constants.KEYS.SHOT_LIST_TYPE,Constants.PARAMETER.SHOT_LIST_TYPE[3]);

        params.put(Constants.KEYS.SHOT_LIST_SORT,Constants.PARAMETER.SHOT_LIST_SORT[0]);

        params.put(Constants.KEYS.SHOT_LIST_TIMEFRAME,timeFrame);

        mPresenter.getDebutShotList(params);
    }


    @Override
    public void getDebutShotListSuccess(List<ShotEntity> shots) {

        Zog("debuts 数据获取成功！ shots.size="+shots.size());
        setData(shots);
    }
}
