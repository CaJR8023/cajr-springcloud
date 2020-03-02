package com.cajr.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/2/26 5:53 下午
 */
public class TimeUtil {
    public static Date getCertainTimestamp(int year, int month, int day,int hour,int minute,int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,second);
        return new Date(calendar.getTime().getTime());
    }

    /**
     *  返回时效时间Timestamp形式表示，方便其他推荐算法
     * @param beforeDays
     * @return
     */
    public static Timestamp getInRecTimestamp(int beforeDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, beforeDays);
        return new Timestamp(calendar.getTime().getTime());
    }

    public static String getSpecificDayFormatForString(int beforeDays){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, beforeDays);
        Date date = calendar.getTime();
        return "'" + dateFormat.format(date) + "'";
    }

    public static Date  getSpecificDayFormatForDate(int beforeDays){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, beforeDays);
        return calendar.getTime();
    }
}
