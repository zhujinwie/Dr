package com.zjw.dr.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 祝锦伟 on 2018/2/19.
 */

public class SimpleTabAdapter <T extends Fragment> extends PagerAdapter{

    private FragmentManager mManager;

    private List<T> mFragments;
    private List<String>    mTitle;

    public SimpleTabAdapter(FragmentManager manager, List<T> data) {
        this(manager, data, null);
    }

    public SimpleTabAdapter(FragmentManager manager, List<T> data, List<String> title) {
        mManager = manager;
        mFragments = data;
        mTitle = title;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = mFragments.get(position);
        if (!fragment.isAdded()) {
            mManager.beginTransaction().add(fragment, fragment.getClass().getName()).commitAllowingStateLoss();
            mManager.executePendingTransactions();
        }
        View view = fragment.getView();
        if (view.getParent() == null) {
            container.addView(view);
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mFragments.get(position).getView());
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle != null ? mTitle.get(position) : super.getPageTitle(position);
    }

}
