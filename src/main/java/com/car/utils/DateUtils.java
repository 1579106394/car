package com.car.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtils {

    public static String newDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String time = sdf.format(new Date());
        return time;
    }

    public static String newDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String time = sdf.format(new Date());
        return time;
    }

    public static Integer getHour(String date1, String date2) throws ParseException {
        double h = 1000 * 60 * 60;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf.parse(date1);
        Date d2 = sdf.parse(date2);
        return (int)Math.ceil((d2.getTime() - d1.getTime()) / h);
    }

}
