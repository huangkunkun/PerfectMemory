package com.huangkun.perfectmemory.utils;

/**
 * Created by hi on 2016/8/13.
 */
public class FormatTime {
    public static String formatTime(int year, int month, int day, int hour, int minute){

        String yearStr = year + "";
        String monthStr = month + "";
        String dayStr = day + "";
        String hourStr = hour + "";
        String minuteStr = minute + "";
        String formatTime = yearStr + " - " + monthStr + " - " + dayStr + "  " + hourStr + " : " + minuteStr;

        return formatTime;
    }
}
