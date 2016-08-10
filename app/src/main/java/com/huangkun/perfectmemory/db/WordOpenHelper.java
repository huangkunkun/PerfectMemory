package com.huangkun.perfectmemory.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class WordOpenHelper extends SQLiteOpenHelper {

    /**
     * Word表建表语句
     */
    public static final String CREATE_WORD = "create table Word(" +
            "id integer primary key autoincrement" +
            "word_name text," +
            "word_mean text" +
            "word_time text)";


    public WordOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WORD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
