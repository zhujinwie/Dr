package com.zjw.dr.ui.more;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zjw.dr.R;
import com.zjw.dr.app.DrApp;
import com.zjw.dr.ui.auth.LoginActivity;
import com.zjw.dr.ui.base.BaseFragment;
import com.zjw.dr.ui.main.MainActivity;
import com.zjw.dr.util.DensityHelper;
import com.zjw.dr.util.EvenMode;
import com.zjw.dr.util.FileCacheUtils;
import com.zjw.dr.util.UserInfoHelper;

import org.greenrobot.eventbus.EventBus;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by 祝锦伟 on 2018/1/30.
 */

public class MoreFragment extends BaseFragment{


    @BindView(R.id.wifiswitch_sc)
    SwitchCompat mSwith;

    @BindView(R.id.cache_tv)
    TextView mCacheTv;

    @BindView(R.id.login_tv)
    TextView mLoginTv;

    @BindView(R.id.block_view)
    View mBlockView;

    @BindView(R.id.low_size_btn)
    Button mLowBtn;

    @BindView(R.id.low_middle_size_btn)
    Button mLowMidBtn;

    @BindView(R.id.high_middle_size_btn)
    Button mHighMidBtn;

    @BindView(R.id.high_size_btn)
    Button mHighBtn;

    //屏幕宽度，图片刷新率，btn长度,block长度，block与btn X轴的偏移差，block当前的X轴偏移； 单位 px
    int width,perPage,btnWidth,blockWidth,var,curX;

    String animMode;

    List<Button> buttonList;

    @Override
    protected void init(View rootView, Bundle savedInstanceState) {

        mSwith.setChecked(DrApp.getAppConfig().getIsShowGif());
        mCacheTv.setText(FileCacheUtils.getTotalCacheSize(mContext));

        buttonList=new ArrayList<>(4);
        buttonList.add(mLowBtn);
        buttonList.add(mLowMidBtn);
        buttonList.add(mHighMidBtn);
        buttonList.add(mHighBtn);

        width=DensityHelper.getDevicePx(mActivity)[0];
        perPage=DrApp.getAppConfig().getPageSize();
        btnWidth=(width-DensityHelper.dip2px(mContext,30))/4;
        blockWidth=DensityHelper.dip2px(mContext,60);
        animMode="translationX";
        var=(btnWidth-blockWidth)/2;
        initBlock();

    }

    public void initBlock(){

        curX= (int) mBlockView.getTranslationX();

        int scale=0;
        switch (perPage){

            case 10:

                scale=0;

                break;

            case 20:

                scale=1;
                break;

            case 30:

                scale=2;
                break;

            case 50:

                scale=3;
                break;
        }
        setPageSizeAndBlockAnim(scale);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    public static MoreFragment newInstance(){

        MoreFragment fragment=new MoreFragment();

        Bundle bundle=new Bundle();

        fragment.setArguments(bundle);

        return fragment;

    }

    @OnCheckedChanged(R.id.wifiswitch_sc)
    void setShowGif(SwitchCompat button,boolean isChecked){
        DrApp.getAppConfig().setIsShowGif(isChecked);
    }

    @OnClick(R.id.clear_rl)
    void clearCache(){


        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        builder.setTitle("确认清除缓存么？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                FileCacheUtils.clearAllCache(mContext);
                Toast.makeText(mContext,"已清除缓存",Toast.LENGTH_SHORT).show();
                mCacheTv.setText(FileCacheUtils.getTotalCacheSize(mContext));
            }
        });
        builder.setNegativeButton("取消",null);
        builder.show();

    }

    @OnClick(R.id.layout_rl)
    void changeLayoutSetting(){

        CharSequence[] items={"大图与简略信息","仅小图"};
        AlertDialog.Builder buidler=new AlertDialog.Builder(mContext,R.style.DrDialog);

        buidler.setTitle("请选择布局样式");
        buidler.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EventBus.getDefault().post(new EvenMode(which));
            }
        });

        buidler.show();

    }


    @OnClick(R.id.login_tv)
    void changeLoginState(){

        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);

        builder.setMessage("确定退出当前账号么？");
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                clearInfo();
            }
        });

        builder.setPositiveButton("取消",null);
        builder.show();


    }

    public void clearInfo(){

        UserInfoHelper.clearUserInfo();

        android.webkit.CookieManager manager = android.webkit.CookieManager.getInstance();

        manager.removeAllCookie();

        DrApp.getAppConfig().setAccessToken("");

        mActivity.startActivity(new Intent(mActivity,MainActivity.class));

        mActivity.finish();
    }

    @OnClick(R.id.low_size_btn)
    void setLowSize(){

        setPageSizeAndBlockAnim(0);
    }

    @OnClick(R.id.low_middle_size_btn)
    void setLowMidSize(){
        setPageSizeAndBlockAnim(1);
    }

    @OnClick(R.id.high_middle_size_btn)
    void setHighMidSize(){
        setPageSizeAndBlockAnim(2);
    }

    @OnClick(R.id.high_size_btn)
    void setHighSize(){
        setPageSizeAndBlockAnim(3);
    }


    /**
     * @param position block 将去的button序列
     * **/
    public void setPageSizeAndBlockAnim(final int position){

        switch(position){

            case 0:

                DrApp.getAppConfig().setPageSize(10);
                break;

            case 1:

                DrApp.getAppConfig().setPageSize(20);
                break;

            case 2:

                DrApp.getAppConfig().setPageSize(30);
                break;

            case 3:

                DrApp.getAppConfig().setPageSize(50);
                break;
        }

        curX=(int) mBlockView.getTranslationX();

        ObjectAnimator anim=ObjectAnimator.ofFloat(mBlockView,animMode,curX,position*btnWidth+var);

        anim.setDuration(1500);
        anim.start();

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                for(Button btn: buttonList){
                    btn.setTextColor(Color.GRAY);
                    btn.setClickable(true);

                }

                buttonList.get(position).setClickable(false);
                buttonList.get(position).setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

                for(Button btn:buttonList){
                    btn.setClickable(false);
                }

            }
        });

    }




}
