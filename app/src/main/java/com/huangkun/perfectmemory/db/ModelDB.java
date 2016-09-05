package com.huangkun.perfectmemory.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huangkun.perfectmemory.model.Money;
import com.huangkun.perfectmemory.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类用于管理Word数据库，封装对于该数据库的操作
 */
public class ModelDB {

    public static final String DB_NAME = "perfect_memory";  //数据库名

    public static final int VERSION = 1; //数据库版本

    private static ModelDB sModelDB;

    private SQLiteDatabase db;

    private ModelDB(Context context){
        ModelOpenHelper dbHelper = new ModelOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    //用于获取WordDB的实例
    public synchronized static ModelDB getInstance(Context context){
        if (sModelDB ==null){
            sModelDB = new ModelDB(context);
        }
        return sModelDB;
    }

    //将Word实例存储到数据库
    public void saveMoney(Money money){
        if (money != null){
            ContentValues values = new ContentValues();
            values.put("money_amount", money.getMoneyAmount());
            values.put("money_mean", money.getMoneyMean());
            values.put("money_time", money.getMoneyTime());
            db.insert("Money", null, values);
        }
    }

    //从数据库中读取Word信息
    public List<Money> loadMoney(){
        List<Money> list = new ArrayList<>();
        Cursor cursor = db.query("Money", null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Money money = new Money();
               // money.setId(cursor.getInt(cursor.getColumnIndex("id")));
                money.setMoneyAmount(cursor.getInt(cursor.getColumnIndex("money_amount")));
                money.setMoneyMean(cursor.getString(cursor.getColumnIndex("money_mean")));
                money.setMoneyTime(cursor.getString(cursor.getColumnIndex("money_time")));
                list.add(money);
            }while (cursor.moveToNext());
        }
        return list;
    }

    public void saveNote(Note note){
        if (note != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("content", note.getContent());
            contentValues.put("time", note.getTime());
            db.insert("Note",null, contentValues);
        }
    }

    public List<Note> loadNote(){
        List<Note> list = new ArrayList<>();
        Cursor cursor = db.query("Note", null, null, null, null, null,null);
        if (cursor.moveToFirst()){
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex("id")));
                note.setTime(cursor.getString(cursor.getColumnIndex("time")));
                note.setContent(cursor.getString(cursor.getColumnIndex("content")));
                list.add(note);
            }while (cursor.moveToNext());
        }
        return list;
    }
}
