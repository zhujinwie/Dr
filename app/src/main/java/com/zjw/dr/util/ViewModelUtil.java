package com.zjw.dr.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.zjw.dr.R;
import com.zjw.dr.app.DrApp;
import com.zjw.dr.constant.Constants;

/**
 * Created by 祝锦伟 on 2018/2/2.
 */

public class ViewModelUtil {

    public static final int[] VIEW_MODEL_Title_RES={
            R.string.view_model_with_info,
            R.string.view_model_no_info
    };

    public static void changeLayoutManager(RecyclerView rcv,int viewMode){

        switch (viewMode){

            case Constants.PARAMETER.VIEW_WITH_INFO:

                rcv.setLayoutManager(new LinearLayoutManager(rcv.getContext()));
                break;

            case Constants.PARAMETER.VIEW_NO_INFO:

                rcv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                break;
        }


    }


}
