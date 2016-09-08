package com.huangkun.perfectmemory.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 主要提供用于返回标准时间格式的方法
 */
public class FormatTime {

    /**
     * 传入获取的年月日时分，给出类似 2016 - 1 - 1 00 : 00这种格式的数据
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @return 2016 - 1 - 1 00 : 00这种格式的字符串
     */
    public static String formatTime(int year, int month, int day, int hour, int minute) {

        String yearStr = year + "";
        String monthStr = month + "";
        String dayStr = day + "";
        String hourStr = hour + "";
        String minuteStr = minute + "";
        if (hour < 10) {
            hourStr = 0 + hourStr;
        }
        if (minute < 10) {
            minuteStr = 0 + minuteStr;
        }
        String formatTime = yearStr + " - " + monthStr + " - " + dayStr + "   " + hourStr + " : " + minuteStr;

        return formatTime;
    }

    /**
     * 给出获取的年月日时分，给出以毫秒为单位的时间，主要用作设置alarm时的参数
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @return 以毫秒为单位的时间
     */
    public static long getTime(int year, int month, int day, int hour, int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        long time = calendar.getTimeInMillis();

        return time;
    }

    //获取当前标准时间
    public static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
        Date date = new Date();
        String time = dateFormat.format(date);

        return time;
    }
}
