package com.zjw.dr.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjw.dr.R;
import com.zjw.dr.entity.ShotsCommentEntity;
import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.ui.profile.ProfileActivity;
import com.zjw.dr.util.DigitHelper;
import com.zjw.dr.util.GlideHelper;

import java.util.List;

/**
 * Created by 祝锦伟 on 2018/2/5.
 */

public class CommentAdapter extends BaseQuickAdapter<ShotsCommentEntity,BaseViewHolder>{

    Activity mActivity;

    public CommentAdapter(int layoutResId,@Nullable List<ShotsCommentEntity> datas,Activity activity){
        super(layoutResId,datas);
        this.mActivity=activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, ShotsCommentEntity item) {

        final UserEntity userEntity=item.getUser();

        holder.setText(R.id.name_item_comment_tv,userEntity.getUserName());
        holder.setText(R.id.createAt_item_comment, DigitHelper.to_h_system(item.getCreatedAt()));

        if(TextUtils.isEmpty(item.getBody())) holder.setText(R.id.body_item_comment,"");
        else holder.setText(R.id.body_item_comment, Html.fromHtml(item.getBody()));
        holder.setText(R.id.like_item_comment,DigitHelper.to_k_system(item.getLikesCount()));

        ImageView avatarIv=holder.getView(R.id.avatar_item_comment);

        GlideHelper.setAvatar(mActivity,userEntity.getAvatarUrl(),avatarIv);

        avatarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mActivity, ProfileActivity.class);
                intent.putExtra(ProfileActivity.EXTRA_USER_ENTITY,userEntity);
                intent.putExtra(ProfileActivity.EXTRA_IS_NEED_REQUEST,true);
                mActivity.startActivity(intent);
            }
        });






    }
}