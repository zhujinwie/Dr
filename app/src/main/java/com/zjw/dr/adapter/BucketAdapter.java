package com.zjw.dr.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjw.dr.R;
import com.zjw.dr.entity.UserBucketEntity;
import com.zjw.dr.util.DigitHelper;

import java.util.List;

/**
 * Created by 祝锦伟 on 2018/2/20.
 */

public class BucketAdapter extends BaseQuickAdapter<UserBucketEntity,BaseViewHolder> {

    public BucketAdapter(@Nullable List<UserBucketEntity> data) {
        super(R.layout.item_bucket,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserBucketEntity item) {

        helper.setText(R.id.shotcount_item_tv, DigitHelper.to_k_system(item.getShotsCount()));

        helper.setText(R.id.title_bucket_tv,item.getName());

        helper.setText(R.id.create_time_bucket_tv,DigitHelper.to_h_system(item.getCreatedAt()));

    }
}
