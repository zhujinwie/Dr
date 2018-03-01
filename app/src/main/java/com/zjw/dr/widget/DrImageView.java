package com.zjw.dr.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.zjw.dr.R;


/**
 * Created by 祝锦伟 on 2018/1/10.
 */

public class DrImageView extends android.support.v7.widget.AppCompatImageView {

    private float mRatio;

    public DrImageView(Context context) {
        super(context);
    }

    public DrImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public DrImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public void init(Context context,AttributeSet attrs){

        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.DrImageView);

        mRatio=ta.getFloat(R.styleable.DrImageView_ratio_w_h,1.33f);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSize=MeasureSpec.getSize(widthMeasureSpec);

        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);

        int heightMode=MeasureSpec.getMode(heightMeasureSpec);

        //宽度确定，高度未定
        if(widthMode==MeasureSpec.EXACTLY && heightMode!=MeasureSpec.EXACTLY && mRatio!=0){

            heightSize=(int)(widthSize/mRatio+0.5f);

            heightMeasureSpec=MeasureSpec.makeMeasureSpec(heightSize,MeasureSpec.EXACTLY);

        }//宽度未定， 高度已定
        else if(widthMode!=MeasureSpec.EXACTLY && heightMode==MeasureSpec.EXACTLY && mRatio!=0){

            widthSize=(int)(heightSize*mRatio+0.5f);

            widthMeasureSpec=MeasureSpec.makeMeasureSpec(widthSize,MeasureSpec.EXACTLY);

        }

        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);

    }
}
