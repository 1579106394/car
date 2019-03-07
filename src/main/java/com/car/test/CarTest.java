package com.car.test;

import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CarTest {

    @Test
    public void testSubstring() {
        String s = "20190312001";
        s = s.substring(8);
        System.out.println(s);
    }

    @Test
    public void testHour() throws ParseException {
        String date1 = "2018-01-02 09:00:01";
        String date2 = "2018-01-02 10:00:00";
        double h = 1000 * 60 * 60;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf.parse(date1);
        Date d2 = sdf.parse(date2);
        long time1 = d1.getTime();
        long time2 = d2.getTime();
        double tempH = Math.ceil((time2 - time1) / h);
        int huor = (int) tempH;
        System.out.println(huor);
    }

}
