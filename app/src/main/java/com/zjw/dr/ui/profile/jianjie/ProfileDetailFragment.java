package com.zjw.dr.ui.profile.jianjie;

import android.os.Bundle;
import android.view.View;

import com.zjw.dr.R;
import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.ui.base.BaseFragment;

/**
 * Created by 祝锦伟 on 2018/2/20.
 */

public class ProfileDetailFragment extends BaseFragment {

    private UserEntity mUserEntity;

    public static final String EXTRA_USER_ENTITY="extra_user_entity";

    public static ProfileDetailFragment newInstance(UserEntity userEntity){

        Bundle args=new Bundle();

        args.putSerializable(EXTRA_USER_ENTITY,userEntity);

        ProfileDetailFragment fragment=new ProfileDetailFragment();

        fragment.setArguments(args);

        return fragment;
    }




    @Override
    protected void init(View rootView, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile_detail;
    }
}
