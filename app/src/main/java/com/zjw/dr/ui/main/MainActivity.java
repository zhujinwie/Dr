package com.zjw.dr.ui.main;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjw.dr.R;
import com.zjw.dr.adapter.MyTabAdapter;
import com.zjw.dr.app.DrApp;

import com.zjw.dr.constant.Constants;
import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.ui.auth.LoginActivity;
import com.zjw.dr.ui.base.BaseFragment;
import com.zjw.dr.ui.base.mvp.BaseMvpActivity;
import com.zjw.dr.ui.debuts.DebutsFragment;
import com.zjw.dr.ui.following.FollowingFragment;

import com.zjw.dr.ui.more.MoreFragment;
import com.zjw.dr.ui.popular.PopularFragment;
import com.zjw.dr.ui.profile.ProfileActivity;
import com.zjw.dr.ui.recents.RecentFragment;

import com.zjw.dr.util.EvenMode;
import com.zjw.dr.util.FileUtils;
import com.zjw.dr.util.GlideHelper;
import com.zjw.dr.util.SPHelper;
import com.zjw.dr.util.TimeMode;
import com.zjw.dr.util.UserInfoHelper;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.zjw.dr.util.DownLoadHelper.FILE_NAME;


public class MainActivity extends BaseMvpActivity<UserInfoPresenter> implements UserInfoContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.viewpager_main)
    ViewPager viewPager;

    @BindView(R.id.tablayout_main)
    TabLayout tabLayout;

    @BindView(R.id.avatar_toolbar)
    ImageView avatarIv;

    @BindView(R.id.title_toolbar)
    TextView mTitleTv;

    private long mExitTime;

    public static final int REQUEST_CODE_LOGIN=100;

    private String[] titles={"关注","流行","新作","首秀","更多"};

    private int images[]={R.drawable.homepage_gray_18,
            R.drawable.popular_gray_18,
            R.drawable.recent_gray_18,
            R.drawable.debut_gray_18,
            R.drawable.more_gray_18};

    private int image02[]= {R.drawable.homepage_red_18,
            R.drawable.popular_red_18,
            R.drawable.recent_red_18,
            R.drawable.debut_red_18,
            R.drawable.more_red_18
    };


    private List<BaseFragment> fragmentList;

    private MyTabAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initToolbar();
        shouldAuthorize();
        initFragment();
        initUserInFo();

    }

    public void initToolbar(){

        avatarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(DrApp.getAppConfig().isLogin()){

                    Intent  intent=new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra(ProfileActivity.EXTRA_USER_ENTITY,UserInfoHelper.getCurrentUser(MainActivity.this));
                    startActivity(intent);
                }else{

                    startActivityForResult(new Intent(MainActivity.this,LoginActivity.class),REQUEST_CODE_LOGIN);

                }
            }
        });

    }


    private void shouldAuthorize(){

        if((boolean)SPHelper.getData(Constants.KEYS.IS_FIRST_RUN,true)){

            Zog("首次打开Dr！进入登陆界面");

            startActivityForResult(new Intent(MainActivity.this,LoginActivity.class),REQUEST_CODE_LOGIN);

            SPHelper.saveData(Constants.KEYS.IS_FIRST_RUN,false);
        }

    }

    private void initFragment(){

        fragmentList=new ArrayList<>(5);

        FollowingFragment followingFragmen=FollowingFragment.newInstance();
        PopularFragment popularFragment=PopularFragment.newInstance();
        RecentFragment recentFragment=RecentFragment.newInstance();
        DebutsFragment debutsFragment=DebutsFragment.newInstance();
        MoreFragment moreFragment=MoreFragment.newInstance();

        fragmentList.add(followingFragmen);

        fragmentList.add(popularFragment);

        fragmentList.add(recentFragment);

        fragmentList.add(debutsFragment);

        fragmentList.add(moreFragment);

        mAdapter=new MyTabAdapter(getSupportFragmentManager(),this,fragmentList,titles,images);

        viewPager.setAdapter(mAdapter) ;

        viewPager.setOffscreenPageLimit(fragmentList.size()-1);

        tabLayout.setupWithViewPager(viewPager);


        for(int i=0;i<tabLayout.getTabCount();i++){

            TabLayout.Tab tab=tabLayout.getTabAt(i);

            tab.setCustomView(mAdapter.getTabView(i));

        }


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Zog("position="+tab.getPosition());
               // toolbar.setTitle(titles[tab.getPosition()]);
                toolbar.setTitle(null);
                mTitleTv.setText(titles[tab.getPosition()]);
                viewPager.setCurrentItem(tab.getPosition());
                changeTabStatus(tab,true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                changeTabStatus(tab,false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(2);
    }

    private void initUserInFo(){

        if(DrApp.getAppConfig().isLogin()){

            mPresenter.getUserInfo();
        }
    }


    private void changeTabStatus(TabLayout.Tab tab,boolean isSelected){

        final View v=tab.getCustomView();
        int positon=tab.getPosition();
        TextView textView =v.findViewById(R.id.title_tab_main);
        ImageView imageView = v.findViewById(R.id.icon_tab_main);

        if(isSelected){

            textView.setTextColor(Color.parseColor("#FF4081"));
            imageView.setImageResource(image02[positon]);
        }else{

            textView.setTextColor(Color.parseColor("#9DA0A3"));
            imageView.setImageResource(images[positon]);
        }


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE_LOGIN){

                TagAndToast("授权登陆成功！");
                mPresenter.getUserInfo();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_time_frame, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){

            case R.id.menu_time_now:

                EventBus.getDefault().post(new TimeMode(0));
                break;

            case R.id.menu_time_week:
                EventBus.getDefault().post(new TimeMode(1));
                break;

            case R.id.menu_time_month:
                EventBus.getDefault().post(new TimeMode(2));
                break;

            case R.id.menu_time_year:

                EventBus.getDefault().post(new TimeMode(3));
                break;

            case R.id.menu_time_all:

                EventBus.getDefault().post(new Time(4));
                break;

            case R.id.menu_large:

                EventBus.getDefault().post(new EvenMode(0));

                break;

            case R.id.menu_small:

                EventBus.getDefault().post(new EvenMode(1));
                break;
        }

        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                showToast("再次点击退出应用");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }



        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void getUserInfoSuccess(final UserEntity userEntity) {

        UserInfoHelper.setUserInfo(userEntity);
        //TODO 更新 toolbar navigationIcon
        GlideHelper.setAvatar(this,userEntity.getAvatarUrl(),avatarIv);
    }
}
