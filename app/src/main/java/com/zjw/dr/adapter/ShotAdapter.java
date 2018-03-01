package com.zjw.dr.adapter;

import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjw.dr.R;
import com.zjw.dr.constant.Constants;
import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.ui.profile.ProfileActivity;
import com.zjw.dr.ui.shot.ShotDetailActivity;
import com.zjw.dr.util.DigitHelper;
import com.zjw.dr.util.GlideHelper;
import com.zjw.dr.widget.DrImageView;

import java.util.List;

/**
 * Created by 祝锦伟 on 2018/1/30.
 */

public class ShotAdapter extends BaseMultiItemQuickAdapter<ShotEntity,BaseViewHolder> {


    Activity mActivity;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ShotAdapter(Activity activity,List<ShotEntity> data) {
        super(data);
        mActivity=activity;

        addItemType(Constants.PARAMETER.VIEW_WITH_INFO, R.layout.item_view_with_info);
        addItemType(Constants.PARAMETER.VIEW_NO_INFO,R.layout.item_view_no_info);

    }

    @Override
    protected void convert(BaseViewHolder holder, final ShotEntity item) {

        final UserEntity userEntity=item.getUserEntity();

            if(holder.getItemViewType()==Constants.PARAMETER.VIEW_WITH_INFO){

                //详细视图
                holder.setText(R.id.viewcount_item_with_info, DigitHelper.to_k_system(item.getViewsCount()));
                holder.setText(R.id.like_item_with_info,DigitHelper.to_k_system(item.getLikesCount()));
                holder.setText(R.id.comment_item_with_info,DigitHelper.to_k_system(item.getCommentsCount()));

                holder.setText(R.id.name_item_with_info,userEntity.getUserName());
                ImageView avatarIv=holder.getView(R.id.avatar_item_with_info);
                loadImageToAvatar(userEntity.getAvatarUrl(),avatarIv,userEntity);
            }


        DrImageView shotIv=holder.getView(R.id.shot_driv);
        if(item.isAnimated()){
            holder.setVisible(R.id.gif_iv,true);
            GlideHelper.loadGif(mActivity,item.getImages().getHidpi(),shotIv,item.getWidth(),item.getHeight());
        }else{
            holder.setVisible(R.id.gif_iv,false);
            GlideHelper.loadImageWithThumb(mActivity,item.getImages().getNormal(),shotIv);
        }

        shotIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mActivity, ShotDetailActivity.class);
                intent.putExtra(ShotDetailActivity.EXTRA_SHOT_ENTITY,item);
                intent.putExtra(ShotDetailActivity.EXTRA_IS_NEED_REQUEST,false);
                mActivity.startActivity(intent);
            }
        });


    }


    public void loadImageToAvatar(String url, ImageView iv, final UserEntity userEntity){

        GlideHelper.setAvatar(mActivity,url,iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mActivity, ProfileActivity.class);
                intent.putExtra(ProfileActivity.EXTRA_IS_NEED_REQUEST,false);
                intent.putExtra(ProfileActivity.EXTRA_USER_ENTITY,userEntity);

                mActivity.startActivity(intent);

            }
        });

    }




}
