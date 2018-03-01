package com.zjw.dr.ui.shot.info;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zjw.dr.R;
import com.zjw.dr.app.DrApp;
import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.ui.auth.LoginActivity;
import com.zjw.dr.ui.base.BaseActivity;
import com.zjw.dr.ui.base.mvp.BaseMvpFragment;
import com.zjw.dr.ui.profile.ProfileActivity;
import com.zjw.dr.util.DigitHelper;
import com.zjw.dr.util.DownLoadHelper;
import com.zjw.dr.util.GlideHelper;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zjw.dr.ui.main.MainActivity.REQUEST_CODE_LOGIN;

/**
 * Created by 祝锦伟 on 2018/2/4.
 */

public class ShotInfoFragment extends BaseMvpFragment<ShotInfoPresenter> implements ShotInfoContract.View{

    public static final String ARGS_SHOT_ID="args_shot_id";

    public static final String ARGS_SHOT_ENTITY="args_shot_entity";

    @BindView(R.id.title_info)
    TextView titleTv;

    @BindView(R.id.viewcount_info)
    TextView viewCountTv;

    @BindView(R.id.commentcount_info)
    TextView comCountTv;

    @BindView(R.id.createAt_info)
    TextView createAtTv;

    @BindView(R.id.desc_info)
    TextView descTv;

    @BindView(R.id.like_info)
    TextView likeTv;

    @BindView(R.id.bucket_info)
    TextView bucketTv;

    @BindView(R.id.download_info)
    TextView downLoadTv;

    @BindView(R.id.share_info)
    TextView shareTv;

    @BindView(R.id.avatar_info)
    ImageView avatarIv;

    @BindView(R.id.name_info)
    TextView nameTv;

    @BindView(R.id.followcount_info)
    TextView follCountTv;

    @BindView(R.id.follow_info)
    TextView follTv;

    @BindView(R.id.tags_ll_info)
    LinearLayout tagsLl;

    ShotEntity mShotEntity;

    int mShotId;

    boolean mIsFollow,mIsLike;

    public static ShotInfoFragment newInstance(int shotId){

        Bundle args=new Bundle();

        args.putInt(ARGS_SHOT_ID,shotId);

        ShotInfoFragment shotInfoFragment=new ShotInfoFragment();

        shotInfoFragment.setArguments(args);

        return shotInfoFragment;

    }

    public static ShotInfoFragment newInstance(ShotEntity shotEntity){

        Bundle args=new Bundle();

        args.putSerializable(ARGS_SHOT_ENTITY,shotEntity);

        ShotInfoFragment shotInfoFragment=new ShotInfoFragment();

        shotInfoFragment.setArguments(args);

        return shotInfoFragment;
    }



    @Override
    protected void init(View rootView, Bundle savedInstanceState) {

        Bundle args=getArguments();

        mShotId =args.getInt(ARGS_SHOT_ID,-1);

        if(mShotId == -1){
            mShotEntity= (ShotEntity) args.getSerializable(ARGS_SHOT_ENTITY);

            if(mShotEntity != null){
                initInfoView(mShotEntity);
                mShotId=mShotEntity.getId();
            }

        }else{

            Zog("mPresenter="+mPresenter);

            if(mPresenter == null){

                mPresenter=new ShotInfoPresenter();
                mPresenter.attachView(ShotInfoFragment.this);
            }

            mPresenter.getShot(mShotId+"");
        }

        if(DrApp.getAppConfig().isLogin()){
            mPresenter.checkLike(mShotId+"");
        }

    }


    public void initInfoView(ShotEntity item){

        Zog("init开始了 item="+item);

        if (item == null) return;

        titleTv.setText(item.getTitle());
        viewCountTv.setText(DigitHelper.to_k_system(item.getViewsCount()));
        comCountTv.setText(DigitHelper.to_k_system(item.getCommentsCount()));
        createAtTv.setText(DigitHelper.to_h_system(item.getCreatedAt()));

        if(TextUtils.isEmpty(item.getDescription()))descTv.setText("");
        else descTv.setText(Html.fromHtml(item.getDescription()));

        likeTv.setText(DigitHelper.to_k_system(item.getLikesCount()));
        bucketTv.setText(DigitHelper.to_k_system(item.getBucketsCount()));

        final UserEntity user=item.getUserEntity();

        if(user == null) return;
        nameTv.setText(user.getUserName());
        follCountTv.setText(DigitHelper.to_k_system(user.getFollowerCount()));

        GlideHelper.setAvatar(mActivity,user.getAvatarUrl(),avatarIv);

        //Tags 标签

        //设置监听
        avatarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mActivity, ProfileActivity.class);

                intent.putExtra(ProfileActivity.EXTRA_IS_NEED_REQUEST,false);

                intent.putExtra(ProfileActivity.EXTRA_USER_ENTITY,user);

                mActivity.startActivity(intent);

            }
        });

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shot_info;
    }

    @Override
    public void getShotSuccess(ShotEntity shotEntity) {
        mShotEntity=shotEntity;
        mShotId=shotEntity.getId();
        initInfoView(shotEntity);
    }

    @OnClick(R.id.like_info)
    void changeLikeState(){
        if(mIsLike){

            mPresenter.unLikeShot(mShotId+"");
        }else{

            mPresenter.likeShot(mShotId+"");
        }
    }

    @OnClick(R.id.bucket_info)
    void changeBucketState(){}

    @OnClick(R.id.download_info)
    void downLoad(){
        DownLoadHelper.downLoadImage((BaseActivity) mActivity,mShotEntity);
    }

    @OnClick(R.id.share_info)
    void shareShot(){}

    @OnClick(R.id.follow_info)
    void changeFollowState(){

        if(mIsFollow){

            mPresenter.unFollowUser(mShotEntity.getUserEntity().getId()+"");
        }else{

            mPresenter.followUser(mShotEntity.getUserEntity().getId()+"");
        }

    }

    public void shouldLogin(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setMessage("登录后才可以执行该操作");
        dialog.setPositiveButton("去登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivityForResult(new Intent(mContext, LoginActivity.class), REQUEST_CODE_LOGIN);
            }
        });
        dialog.setNegativeButton("算了", null);
        dialog.show();

    }


    @Override
    public void checkLikeShotSuccess(boolean isLike) {

        if(isLike){

            likeTv.setEnabled(true);

            mIsLike=true;
        }else{

            likeTv.setEnabled(false);


            mIsLike=false;
        }

    }

    @Override
    public void checkFollowSuccess(boolean isFollow) {

        if(isFollow){

            mIsFollow=true;
        }else{

            mIsFollow=false;
        }

    }

    @Override
    public void likeShotSuccess() {

        mIsLike=true;
        likeTv.setText("不喜欢");
        likeTv.setCompoundDrawables(null,mContext.getResources().getDrawable(R.drawable.iv_like_pink_24dp),null,null);
    }

    @Override
    public void collectShotSuccess() {

        bucketTv.setText("取消收藏");
    }

    @Override
    public void downLoadShotSuccess() {
    }

    @Override
    public void shareShotSuccess() {

    }

    @Override
    public void followUserSuccess() {

        mIsFollow=true;
        follTv.setText("已关注");
    }

    @Override
    public void unFollowUserSuccess() {

        mIsFollow=false;
        follTv.setText("取消关注");
    }

    @Override
    public void unLikeShotSuccess() {

        mIsLike=false;
        likeTv.setCompoundDrawables(null, mContext.getResources().getDrawable(R.drawable.unliked_18),null,null);
        likeTv.setText("喜欢");
    }
}
