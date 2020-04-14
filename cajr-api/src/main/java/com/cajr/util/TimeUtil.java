package com.cajr.util;

import org.apache.commons.lang.time.DateUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        beforeDays = 0-beforeDays;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, beforeDays);
        return new Timestamp(calendar.getTime().getTime());
    }

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(getMondayOfThisWeek());
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        System.out.println(calendar.getTime().toString());
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

    /**
     * 获取本周一 时间0点
     */
    public static Timestamp getMondayOfThisWeek(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 友好显示时间
     */
    public static String friendlyDate(Timestamp timestamp) {

        Date date = new Date(timestamp.getTime());
        Date now = new Date();
        long ys = DateUtils.truncate(now, Calendar.YEAR).getTime();
        long ds = DateUtils.truncate(now, Calendar.DAY_OF_MONTH).getTime();
        long yd = DateUtils.truncate(date, Calendar.DAY_OF_MONTH).getTime();

        long n = now.getTime();

        long e = date.getTime();
        if (e < ys) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        }
        if ((ds - yd) == (24 * 60 * 60 * 1000)) {
            return new SimpleDateFormat("昨天  HH:mm").format(date);
        }
        if (e < ds) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        }
        if (n - e > 60 * 60 * 1000) {
            return new SimpleDateFormat("今天  HH:mm").format(date);
        }
        if (n - e > 60 * 1000) {
            return (long) Math.floor((n - e) * 1d / 60000) + "分钟前";
        }
        if (n - e >= 0) {
            return "刚刚";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }
}
