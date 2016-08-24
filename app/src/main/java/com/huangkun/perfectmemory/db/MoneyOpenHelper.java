package com.huangkun.perfectmemory.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MoneyOpenHelper extends SQLiteOpenHelper {

    /**
     * Word表建表语句
     */
    public static final String CREATE_MONEY = "create table Money(" +
            "id integer primary key autoincrement" +
            "money_amount integer," +
            "money_mean text" +
            "money_time text)";

    public MoneyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MONEY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
