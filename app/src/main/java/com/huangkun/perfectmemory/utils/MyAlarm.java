package com.huangkun.perfectmemory.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * 提供定时的类
 */
public class MyAlarm {

    /**
     * context 是上下文
     * cls 是需要启用的广播接收器
     * time 是用户设置的定时提醒时间
     * content 是用户设置的提醒内容
     */
    public void setAlarm(Context context, Class<?> cls, long time, String content) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("content", content);
        final int id = (int)System.currentTimeMillis();//使用该id可以保证每次传入的pendingIntent不同，从而alarm不会覆盖之前的设置
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id,
                intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }

}
