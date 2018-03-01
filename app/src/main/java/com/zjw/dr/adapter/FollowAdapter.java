package com.zjw.dr.adapter;

import android.content.Intent;
import android.content.MutableContextWrapper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjw.dr.R;
import com.zjw.dr.entity.FollowerEntity;
import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.ui.profile.ProfileActivity;
import com.zjw.dr.util.GlideHelper;

import java.util.List;

/**
 * Created by 祝锦伟 on 2018/2/20.
 */


public class FollowAdapter extends BaseQuickAdapter<FollowerEntity,BaseViewHolder>{


    public FollowAdapter(@Nullable List<FollowerEntity> datas){
        super(R.layout.item_profile_follow,datas);
    }


    @Override
    protected void convert(BaseViewHolder helper, FollowerEntity item) {

        final UserEntity user=item.getFollower();

        ImageView avatarIv=helper.getView(R.id.iv_item_follower_avatar);
        GlideHelper.setAvatar(mContext,user.getAvatarUrl(),avatarIv);

        helper.setText(R.id.tv_item_follower_user_name, user.getUserName());
        helper.setText(R.id.tv_item_follower_shots, user.getShotsCount() + " 作品");

        TextView locationTv=helper.getView(R.id.tv_item_follower_location);

        String location=user.getLocation();
        if(TextUtils.isEmpty(location)){

            locationTv.setVisibility(View.GONE);

        }else{

            locationTv.setVisibility(View.VISIBLE);

            locationTv.setText(location);
        }


        helper.getView(R.id.item_profile_followers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext, ProfileActivity.class);

                intent.putExtra(ProfileActivity.EXTRA_USER_ENTITY,user);
                mContext.startActivity(intent);
            }
        });

    }
}
