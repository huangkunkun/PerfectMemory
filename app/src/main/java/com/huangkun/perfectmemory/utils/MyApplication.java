package com.huangkun.perfectmemory.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by hi on 2016/8/10.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
