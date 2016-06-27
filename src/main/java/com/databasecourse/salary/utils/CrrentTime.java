package com.databasecourse.salary.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/9/24.
 */
public class CrrentTime {
    public  String getDate(){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return  time;
    }

    public Long getTimeStamp(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssS");
        String c=sdf.format(new Date());
        Long date = Long.parseLong(c);
        return  date;
    }
}
