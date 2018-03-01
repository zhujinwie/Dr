package com.zjw.dr.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjw.dr.R;
import com.zjw.dr.ui.base.BaseFragment;

import java.util.List;

/**
 * Created by 祝锦伟 on 2018/1/22.
 */

public class MyTabAdapter extends FragmentPagerAdapter {

    private Context context;

    private List<BaseFragment> fragmentList;

    private String[] titles;

    private int[] images;

    public MyTabAdapter(@NonNull FragmentManager fm,Context context,@NonNull List<BaseFragment> fragments,@NonNull String[] titles,@NonNull int[] images) {
        super(fm);
        this.context=context;
        fragmentList=fragments;
        this.images=images;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    public View getTabView(int position){

        View v= LayoutInflater.from(context).inflate(R.layout.tab_main,null);

        TextView textView =v.findViewById(R.id.title_tab_main);
        ImageView imageView = v.findViewById(R.id.icon_tab_main);

        textView.setText(titles[position]);
        imageView.setImageResource(images[position]);

        //imageView.setBackgroundResource(images[position]);
        return v;
    }




    public View getTabView02(int position,int num){

        View v=LayoutInflater.from(context).inflate(R.layout.tab_with_number,null);

        TextView titleTv=v.findViewById(R.id.title_tab_with_number);

        TextView numTv=v.findViewById(R.id.number_tab_with_number);

        if(num==-1) numTv.setVisibility(View.GONE);

        titleTv.setText(titles[position]);

        numTv.setText(String.valueOf(num));

        return v;
    }




}
