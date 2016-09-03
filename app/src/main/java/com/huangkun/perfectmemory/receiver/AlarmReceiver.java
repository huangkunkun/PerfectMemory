package com.huangkun.perfectmemory.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.huangkun.perfectmemory.activity.WarnActivity;

/**
 * 设置定时提醒
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentNew = new Intent(context, WarnActivity.class);
        intentNew.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentNew.putExtra("content", intent.getStringExtra("content"));
        context.startActivity(intentNew);
    }
}
