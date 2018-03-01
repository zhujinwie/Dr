package com.zjw.dr.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 祝锦伟 on 2017/11/1.
 */

public class BackGroundView extends View {

    Paint mPaint;

    //30个-40个球
    private final int POINT_MAX=8;

    private static final String TAG="BackGroundView";

    int width,heigth;

    int minRadius=15;
    int maxRadius=40;
    int maxMx=80,maxMy=80,minMx=5,minMy=5;

    //100帧
    int FPS=60;

    private int maxLength;
    private List<Circle> circles;

    private static final int OPEN=1;
    private static final int CLOSE=-1;

    public BackGroundView(Context context) {
        super(context);
        init();
    }

    private void init() {

        initPaint();
        initCircles();
        startThead();
    }

    private void initPaint(){

        mPaint=new Paint();
        mPaint.setColor(Color.rgb(120,120,120));
        mPaint.setStrokeWidth(1.5f);
        mPaint.setAntiAlias(true);

    }

    private void initCircles() {

        Log.d(TAG,"initCircles!");

        circles=new ArrayList<>(POINT_MAX);

        WindowManager wm= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        width=wm.getDefaultDisplay().getWidth();
        heigth=wm.getDefaultDisplay().getHeight();
        maxLength=Math.min(width,heigth);
        for(int i=0;i<POINT_MAX;i++){

            Circle circle=new Circle(randomNum(0,width),randomNum(0,heigth),randomNum(minRadius,maxRadius),randomNum(minMx,maxMx)/40,randomNum(minMy,maxMy)/40,OPEN,OPEN);

            circles.add(circle);

        }


    }

    private void startThead() {

        Log.d(TAG,"startThread!");

/*
        new Thread(){

            @Override
            public void run() {
                super.run();

                try {
                    //100hz
                    Thread.sleep(50);
                    Log.d(TAG,"执行循环中！");
                    for(int i=0;i<POINT_MAX;i++){

                        Circle circle=circles.get(i);

                        if((circle.ox+circle.r)==width){
                            circle.xDiff=CLOSE;
                        }else if((circle.ox-circle.r)==0){
                            circle.xDiff=OPEN;
                        }
                        if((circle.oy+circle.r)==heigth){
                            circle.yDiff=CLOSE;
                        }else if((circle.oy-circle.r)==0){
                            circle.yDiff=OPEN;
                        }

                        circle.ox+=(circle.xDiff*circle.mx);
                        circle.oy+=(circle.yDiff*circle.my);

                        if((circle.ox+circle.r)>=width){

                            circle.ox=width-circle.r;

                        }else if((circle.ox-circle.r)<=0){

                            circle.ox=circle.r;
                        }

                        if((circle.oy+circle.r)>=heigth){

                            circle.oy=heigth-circle.r;
                        }else if((circle.oy-circle.r)<=0){

                            circle.oy=circle.r;
                        }
                    }

                    invalidate();

                }catch(Exception e){

                    e.printStackTrace();
                }
            }
        }.start();
*/

        final Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                try {
        //            Log.d(TAG,"执行循环中！");
                    for(int i=0;i<POINT_MAX;i++){

                        Circle circle=circles.get(i);

                        if((circle.ox+circle.r)==width){
                            circle.xDiff=CLOSE;
                        }else if((circle.ox-circle.r)==0){
                            circle.xDiff=OPEN;
                        }
                        if((circle.oy+circle.r)==heigth){
                            circle.yDiff=CLOSE;
                        }else if((circle.oy-circle.r)==0){
                            circle.yDiff=OPEN;
                        }

                        circle.ox+=(circle.xDiff*circle.mx);
                        circle.oy+=(circle.yDiff*circle.my);

                        if((circle.ox+circle.r)>=width){

                            circle.ox=width-circle.r;

                        }else if((circle.ox-circle.r)<=0){

                            circle.ox=circle.r;
                        }

                        if((circle.oy+circle.r)>=heigth){

                            circle.oy=heigth-circle.r;
                        }else if((circle.oy-circle.r)<=0){

                            circle.oy=circle.r;
                        }
                    }

                    invalidate();

                    handler.postDelayed(this,1000/FPS);
                }catch(Exception e){

                    e.printStackTrace();
                }
            }
        };

        handler.postDelayed(runnable,0);
    }


    public float randomNum(int min,int max){

        return (float) (Math.random()*(max-min)+min);
    }

    public BackGroundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG,"构造 2参!");

        init();
    }

    public BackGroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG,"构造 3参!");


        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BackGroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*for (Circle circle:circles){
            draw2Circle(canvas,circle);
        }*/

        for(int i=0;i<POINT_MAX;i++){

            draw2Circle(canvas,circles.get(i));
            for(int j=0;j<POINT_MAX;j++){

                if((i+j)<POINT_MAX){
          //          Log.d(TAG,"i="+i+" ; j="+j);
                    //X偏移
                    float A=Math.abs(circles.get(i+j).ox-circles.get(i).ox);
                    //Y偏移
                    float B=Math.abs(circles.get(i+j).oy-circles.get(i).oy);
                    //圆心距离
                    float Length= (float) Math.sqrt(A*A+B*B);

                    if((Length<=minRadius)|(Length>maxLength))continue;
                    float C=(float)(1/Length*7-0.009);

                    float alpha=C>0.02?(float)0.02:C;
            //        Log.d(TAG,"i="+i+" ;j="+j+ " ;A="+A+" ; B="+B+" ; C="+C+" ;length="+Length+" ; Alpha="+alpha);
                    if(alpha>0){

                        Line line=new Line(circles.get(i).ox,circles.get(i).oy,circles.get(i+j).ox,circles.get(i+j).oy,alpha);
                        draw2Line(canvas,line);
                    }

                }

            }

        }

    }


    public void draw2Circle(Canvas canvas,Circle cirlce){

        //Log.d(TAG,"--> draw2Circle：  CIRLCE="+cirlce);
        //绘制圆点
        mPaint.setAlpha((int)cirlce.r*2);
        canvas.drawCircle(cirlce.ox,cirlce.oy,cirlce.r,mPaint);

    }

    public void draw2Line(Canvas canvas,Line line){

        //Log.d(TAG,"-->draw2line: line="+line);
        //绘制连接线
        mPaint.setAlpha((int) (line.alpha*500));
        canvas.drawLine(line.ox,line.oy,line.tx,line.ty,mPaint);

    }

    class Circle{
        //原点X,Y坐标，半径,X方向速度,Y方向速度,扩散性diff
        float ox,oy,r,mx,my;

        int xDiff,yDiff;


        public Circle(float ox,float oy,float r,float mx,float my,int xDiff,int yDiff){

            this.mx=mx;
            this.my=my;
            this.r=r;
            this.ox=ox;
            this.oy=oy;
            this.xDiff=xDiff;
            this.yDiff=yDiff;
        }

        @Override
        public String toString() {
            return "Circle{" +
                    "ox=" + ox +
                    ", oy=" + oy +
                    ", r=" + r +
                    ", mx=" + mx +
                    ", my=" + my +
                    ", xDiff="+xDiff+
                    ", yDiff="+yDiff+
                    '}';
        }
    }

    class Line{
        //初始点X,Y坐标,末尾点X,Y坐标,线段的透明度alpha
        float ox,oy,tx,ty,alpha;

        public Line(float ox,float oy,float tx,float ty,float alpha){

            this.ox=ox;
            this.oy=oy;
            this.tx=tx;
            this.ty=ty;
            this.alpha=alpha;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "ox=" + ox +
                    ", oy=" + oy +
                    ", tx=" + tx +
                    ", ty=" + ty +
                    ", alpha=" + alpha +
                    '}';
        }
    }


}
