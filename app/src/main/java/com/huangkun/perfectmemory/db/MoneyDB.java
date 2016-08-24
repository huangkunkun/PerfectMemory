package com.huangkun.perfectmemory.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huangkun.perfectmemory.model.Money;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类用于管理Word数据库，封装对于该数据库的操作
 */
public class MoneyDB {

    public static final String DB_NAME = "perfect_memory";  //数据库名

    public static final int VERSION = 1; //数据库版本

    private static MoneyDB sMoneyDB;

    private SQLiteDatabase db;

    private MoneyDB(Context context){
        MoneyOpenHelper dbHelper = new MoneyOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    //用于获取WordDB的实例
    public synchronized static MoneyDB getInstance(Context context){
        if (sMoneyDB ==null){
            sMoneyDB = new MoneyDB(context);
        }
        return sMoneyDB;
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
                money.setId(cursor.getInt(cursor.getColumnIndex("id")));
                money.setMoneyAmount(cursor.getInt(cursor.getColumnIndex("money_amount")));
                money.setMoneyMean(cursor.getString(cursor.getColumnIndex("money_mean")));
                money.setMoneyTime(cursor.getString(cursor.getColumnIndex("money_time")));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
