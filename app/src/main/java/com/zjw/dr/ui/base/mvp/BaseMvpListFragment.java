package com.zjw.dr.ui.base.mvp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjw.dr.R;
import com.zjw.dr.app.DrApp;
import com.zjw.dr.ui.base.BaseFragment;
import com.zjw.dr.widget.MyLoadMoreView;

import java.util.List;
import com.zjw.dr.util.TUtil;


/**
 * Created by 祝锦伟 on 2018/1/22.
 */

public abstract class BaseMvpListFragment<P extends RxPresenter> extends BaseFragment {

    public P mPresenter;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private BaseQuickAdapter mAdapter;

    private boolean mIsRefreshing,mIsLoadMoreFailed;

    public int mPage;

    private View mErrorView,mEmptyView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter=TUtil.getT(this,0);
        if(this instanceof MvpView) {

            Zog("presenter attachView!");
            mPresenter.attachView(this);
        }

    }




    @Override
    public void onDestroy() {

        super.onDestroy();
        if(mPresenter != null){
            mPresenter.detachView(false);
        }
    }

    public void setUpList(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView,BaseQuickAdapter adapter){

        LayoutInflater inflater=LayoutInflater.from(mContext);

        mAdapter=adapter;

        ViewGroup container= (ViewGroup) recyclerView.getParent();

        //initView
        mEmptyView=inflater.inflate(R.layout.layout_empty_view,container,false);
        TextView mEmptyTv=mEmptyView.findViewById(R.id.msg_tv_empty);
        mEmptyTv.setText(getEmptyMessage());
        //TODO 增加Drawable Top设置

        mErrorView=inflater.inflate(R.layout.layout_error_view,container,false);
        TextView mErrorTv=mErrorView.findViewById(R.id.msg_tv_error);
        mErrorTv.setText(getErrorMessage());
        //TODO 增加DrawableTop 设置

        TextView mRetryTv=mErrorView.findViewById(R.id.retry_tv_error);
        mRetryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Zog("点击重试！");
                mSwipeRefreshLayout.setRefreshing(true);
                //请求数据
                refreshData(true);
            }
        });

        //刷新设置
        mSwipeRefreshLayout=refreshLayout;
        mSwipeRefreshLayout.setColorSchemeResources(R.color.purple_500, R.color.blue_500, R.color
                .orange_500, R.color.pink_500);;
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Zog("下拉刷新！");
                refreshData(true);
            }
        });

        //加载更多
        mAdapter.setLoadMoreView(new MyLoadMoreView());
        mAdapter.setPreLoadNumber(18);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Zog("上划请求更多！");
                refreshData(false);
            }
        },recyclerView);

        //首次进入自动刷新
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                Zog("首次刷新！");
                mSwipeRefreshLayout.setRefreshing(true);
                refreshData(true);

            }
        },200);

        mAdapter.openLoadAnimation(1);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


    }


    protected void refreshData(boolean isRefresh){
        Zog("请求数据中!");
        mIsRefreshing=isRefresh;

        if(mIsRefreshing){

            //请求首页数据
            mPage=1;
        }else{

            if(mIsLoadMoreFailed) mIsLoadMoreFailed=false;
            else mPage++;

        }

    }

    //子类可以自定义 空内容 和 错误内容 的消息提示
    protected String getEmptyMessage(){

        return "没有更多内容";
    }

    protected  String getErrorMessage(){

        return "出错了";
    }


    protected int getPageSize(){

        return DrApp.getAppConfig().getPageSize();

    }

    protected <T> void setData(List<T> datas){

        mSwipeRefreshLayout.setRefreshing(false);
        Zog("setData!");
        mAdapter.isUseEmpty(true);
        if(mIsRefreshing){
            //首次刷新
            mAdapter.setNewData(datas);
            //首次刷新判断是否结束
            if(datas == null || datas.size()==0){
                //无内容
                mAdapter.setEmptyView(mEmptyView);
                mAdapter.notifyDataSetChanged();
            }else if(datas.size() < getPageSize()){
                //数据到底了
                mAdapter.loadMoreEnd();
            }else {
                //加载完毕
                mAdapter.loadMoreComplete();
            }
        }else{
            //加载更多
            if(datas == null || datas.size() == 0){
                mAdapter.loadMoreEnd();
            }else{
                mAdapter.addData(datas);
                mAdapter.loadMoreComplete();
            }
        }
    }

    public void showLoading(){
        Zog("显示加载中！");

    }

    public void showError(){
        Zog("显示出错界面！");
        if(mIsRefreshing){
            mAdapter.setEmptyView(mErrorView);
            mAdapter.notifyDataSetChanged();
        }else{
            mAdapter.loadMoreFail();
        }
    }

    public void onComplete(){

        Zog("加载完成！");
        mSwipeRefreshLayout.setRefreshing(false);
    }



}
