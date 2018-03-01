package com.zjw.dr.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zjw.dr.R;
import com.zjw.dr.adapter.SimpleTabAdapter;
import com.zjw.dr.app.DrApp;
import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.ui.base.BaseActivity;
import com.zjw.dr.ui.base.BaseFragment;
import com.zjw.dr.ui.profile.guanzhu.ProfileFollowFragment;
import com.zjw.dr.ui.profile.jianjie.ProfileDetailFragment;
import com.zjw.dr.ui.profile.shouchang.ProfileBucketFragment;
import com.zjw.dr.ui.profile.tougao.ProfileProjectFragment;
import com.zjw.dr.util.DigitHelper;
import com.zjw.dr.util.GlideHelper;
import com.zjw.dr.util.StatusBarCompat;
import com.zjw.dr.util.UserInfoHelper;

import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 祝锦伟 on 2018/1/31.
 */

public class ProfileActivity extends BaseActivity {

    public static final String EXTRA_IS_NEED_REQUEST="extra_is_need_request";
    public static final String EXTRA_USER_ENTITY="extra_user_entity";

    private UserEntity mUser;
    private boolean mIsNeedRequest;

    @BindView(R.id.toolbar_profile)
    Toolbar mToolbar;

    @BindView(R.id.tablayout_profile)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager_profile)
    ViewPager mViewPager;

    @BindView(R.id.avatar_account_iv)
    ImageView mAvatar;

    @BindView(R.id.follow_account_btn)
    Button mFollowBtn;

    @BindView(R.id.name_account_tv)
    TextView mNameTv;

    @BindView(R.id.follower_account_tv)
    TextView mFollowerAccountTv;

    @BindView(R.id.following_account_tv)
    TextView mFollowingAccountTv;

    @BindView(R.id.bio_account_tv)
    TextView mBioAccountTv;

    private static final String[] titles={"作品","粉丝","收藏"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        Intent intent=getIntent();

        mUser= (UserEntity) intent.getSerializableExtra(EXTRA_USER_ENTITY);

        mIsNeedRequest=intent.getBooleanExtra(EXTRA_IS_NEED_REQUEST,true);
        StatusBarCompat.translucentStatusBar(this);


        initToolBar();

        initProfileInfo();

        initViewPagerAndFragments();

    }

    private void initToolBar(){

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void initProfileInfo(){

        GlideHelper.setAvatar(this,mUser.getAvatarUrl(),mAvatar);

        UserEntity mySelf=UserInfoHelper.getCurrentUser(this);

        if(mySelf != null && mySelf.getId() != mUser.getId()) mFollowBtn.setVisibility(View.VISIBLE);

        else mFollowBtn.setVisibility(View.GONE);

        mNameTv.setText(mUser.getUserName());

        mFollowerAccountTv.setText(DigitHelper.to_k_system(mUser.getFollowerCount()));

        mFollowingAccountTv.setText(DigitHelper.to_k_system(mUser.getFollowingsCount()));

        if(TextUtils.isEmpty(mUser.getBio())) mBioAccountTv.setText("");

        else  mBioAccountTv.setText(Html.fromHtml(mUser.getBio()));

    }

    private void initViewPagerAndFragments(){

        List<Fragment>  fragments=new ArrayList<>(3);

        String userId=mUser.getId()+"";
        ProfileProjectFragment projectFragment= ProfileProjectFragment.newInstance(userId);

        ProfileBucketFragment bucketFragment= ProfileBucketFragment.newInstance(userId);

        ProfileFollowFragment followFragment= ProfileFollowFragment.newInstance(userId);

//        ProfileDetailFragment detailFragment= ProfileDetailFragment.newInstance(mUser);

        fragments.add(projectFragment);

        fragments.add(followFragment);

        fragments.add(bucketFragment);

  //      fragments.add(detailFragment);

        SimpleTabAdapter<Fragment> adapter=new SimpleTabAdapter<Fragment>(getSupportFragmentManager(),fragments, Arrays.asList(titles));

        mViewPager.setAdapter(adapter);

        for(String title:titles){

            mTabLayout.addTab(mTabLayout.newTab().setText(title));

        }

        mTabLayout.setupWithViewPager(mViewPager);

    }


}

