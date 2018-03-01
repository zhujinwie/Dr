package com.zjw.dr.ui.shot;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.zjw.dr.R;
import com.zjw.dr.adapter.MyTabAdapter;
import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.entity.ShotsCommentEntity;
import com.zjw.dr.ui.base.BaseActivity;
import com.zjw.dr.ui.base.BaseFragment;
import com.zjw.dr.ui.base.mvp.BaseMvpActivity;
import com.zjw.dr.ui.shot.comment.ShotComFragment;
import com.zjw.dr.ui.shot.info.ShotInfoFragment;
import com.zjw.dr.util.GlideHelper;
import com.zjw.dr.util.StatusBarCompat;
import com.zjw.dr.widget.DrImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 祝锦伟 on 2018/1/31.
 */

public class ShotDetailActivity extends BaseActivity {

    @BindView(R.id.shot_detail)
    DrImageView mShotIv;

    @BindView(R.id.tab_detail)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager_detail)
    ViewPager mViewPager;

    @BindView(R.id.toolbar_detail)
    Toolbar mToolbar;

    public static final String EXTRA_SHOT_ENTITY="extra_shot_entity";

    public static final String EXTRA_IS_NEED_REQUEST="extra_is_need_request";

    private ShotEntity mShotEntity;

    private boolean mIsNeedRequest;

    List<BaseFragment> fragments;

    MyTabAdapter mAdapter;

    private String[] mTitles={"详情","评论"};

    private int[] mImages={};

    @BindView(R.id.title_shot_detail)
    TextView mTitleTv;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shot_detail);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        mShotEntity= (ShotEntity) intent.getSerializableExtra(EXTRA_SHOT_ENTITY);
        mIsNeedRequest= intent.getBooleanExtra(EXTRA_IS_NEED_REQUEST,false);

        Zog("mIsNeedRequest="+mIsNeedRequest);
        initToolBar();
        initShotView();
        initPager();

    }

    public void initToolBar(){

        StatusBarCompat.translucentStatusBar(this,false);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTitleTv.setText(mShotEntity.getTitle());
    }

    public void initShotView(){

        if(mShotEntity.isAnimated()){
            GlideHelper.loadGif(ShotDetailActivity.this,mShotEntity.getImages().getHidpi(),mShotIv,mShotEntity.getWidth(),mShotEntity.getHeight());
        }else{

            GlideHelper.loadImageWithThumb(ShotDetailActivity.this,mShotEntity.getImages().getHidpi(),mShotIv);
        }
    }

    public void initPager(){

        fragments=new ArrayList<>(2);
        if(mIsNeedRequest) fragments.add(ShotInfoFragment.newInstance(mShotEntity.getId()));
        else fragments.add(ShotInfoFragment.newInstance(mShotEntity));

        fragments.add(ShotComFragment.newInstance(mShotEntity.getId()));

        mAdapter=new MyTabAdapter(getSupportFragmentManager(),this,fragments,mTitles,mImages);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);

        TabLayout.Tab tab1=mTabLayout.getTabAt(0);

        TabLayout.Tab tab2=mTabLayout.getTabAt(1);

        tab1.setCustomView(mAdapter.getTabView02(0,-1));
        tab2.setCustomView(mAdapter.getTabView02(1,mShotEntity.getCommentsCount()));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

    }




}


