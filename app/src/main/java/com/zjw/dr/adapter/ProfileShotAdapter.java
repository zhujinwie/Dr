package com.zjw.dr.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjw.dr.R;
import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.ui.shot.ShotDetailActivity;
import com.zjw.dr.util.GlideHelper;

import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * Created by 祝锦伟 on 2018/2/22.
 */

public class ProfileShotAdapter extends BaseQuickAdapter<ShotEntity,BaseViewHolder> {

    public ProfileShotAdapter(@Nullable List<ShotEntity> data) {
        super(R.layout.item_view_no_info,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShotEntity item) {

        ImageView avatarIv=helper.getView(R.id.shot_driv);

        if(item.isAnimated()){

            helper.setVisible(R.id.gif_iv, true);

            GlideHelper.loadGif(mContext,item.getImages().getHidpi(),avatarIv,item.getWidth(),item.getHeight());
        }else{

            helper.setVisible(R.id.gif_iv,false);

            GlideHelper.loadImageWithThumb(mContext,item.getImages().getHidpi(),avatarIv);
        }

        avatarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext, ShotDetailActivity.class);
                Log.d("ProfileShotAdapter","item="+item);
                intent.putExtra(ShotDetailActivity.EXTRA_SHOT_ENTITY,item);
                intent.putExtra(ShotDetailActivity.EXTRA_IS_NEED_REQUEST,true);

                mContext.startActivity(intent);
            }
        });






    }
}
