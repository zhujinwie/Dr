package com.zjw.dr.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by 祝锦伟 on 2018/1/18.
 */

public class DigitHelper {

    public static String to_k_system(int num){

        String str="";
        if(num<1000){
            str=String.valueOf(num);
        }
        else{
            DecimalFormat format=new DecimalFormat("#.0");
            str=format.format(num/1000d)+"k";
        }

        return str;

    }


    public static String to_h_system(String var){

        var=var.replace("T"," ").replace("Z"," ");
        String pattern2="yyyy-MM-dd hh:mm:ss ";
        long now=System.currentTimeMillis();
        DateFormat format=new SimpleDateFormat(pattern2);
        String str="";
        try {

            long date=format.parse(var).getTime();
            long between=(now-date)/1000;
            int day=(int)between / (24*3600);
            int hour=(int)between % (24*3600)/3600;
            int minute=(int)between % 3600/60;

            if(day>0){
                str=var.substring(0,9);
            }
            if(day==0 && hour>0){

                str=hour+"小时前";
            }
            if(day==0 && hour==0 && minute>0){

                str=minute+"分钟前";
            }else{

                str="刚刚";
            }

        }catch (ParseException e){

            e.printStackTrace();
        }

        return str;

    }

}
