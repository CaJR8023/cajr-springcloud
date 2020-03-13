package com.cajr.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CAJR
 * @date 2020/3/13 4:07 下午
 */
public class QuartzCronDateUtils {

    public static String formatDateByPattern(Date date, String dateFormat){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String formatTimeStr = null;
        if (date != null){
            formatTimeStr = simpleDateFormat.format(date);
        }
        return formatTimeStr;
    }

    public static String getCron(Date  date){
        String dateFormat="ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date,dateFormat);
    }
}
