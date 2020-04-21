package com.cajr.util;

import org.apache.commons.lang.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
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
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

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

    public static void main(String[] args) throws ParseException {
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
            return new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(date);
        }
        if ((ds - yd) == (24 * 60 * 60 * 1000)) {
            return new SimpleDateFormat("昨天").format(date);
        }
        if (e < ds) {
            return new SimpleDateFormat("MM月dd日 HH:mm").format(date);
        }
        if (n - e > 60 * 60 * 1000) {
            return new SimpleDateFormat("今天").format(date);
        }
        if (n - e > 60 * 1000) {
            return (long) Math.floor((n - e) * 1d / 60000) + "分钟前";
        }
        if (n - e >= 0) {
            return "刚刚";
        }
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(date);
    }

    //时间转换
    public static String format(Timestamp timestamp) {
        Date paramDate = new Date(timestamp.getTime());
        long delta = new Date().getTime() - paramDate.getTime();
        if (delta < ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(paramDate);
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

}
