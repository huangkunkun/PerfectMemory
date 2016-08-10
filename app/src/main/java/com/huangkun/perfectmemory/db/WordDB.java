package com.huangkun.perfectmemory.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.huangkun.perfectmemory.model.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类用于管理Word数据库，封装对于该数据库的操作
 */
public class WordDB {

    public static final String DB_NAME = "perfect_memory";  //数据库名

    public static final int VERSION = 1; //数据库版本

    private static WordDB wordDB;

    private SQLiteDatabase db;

    private WordDB(Context context){
        WordOpenHelper dbHelper = new WordOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    //用于获取WordDB的实例
    public synchronized static WordDB getInstance(Context context){
        if (wordDB ==null){
            wordDB = new WordDB(context);
        }
        return wordDB;
    }

    //将Word实例存储到数据库
    public void saveWord(Word word){
        if (word != null){
            ContentValues values = new ContentValues();
            values.put("word_name", word.getWordName());
            values.put("word_mean", word.getWordMean());
            values.put("word_time", word.getWordTime());
            db.insert("Word", null, values);
        }
    }

    //从数据库中读取Word信息
    public List<Word> loadWord(){
        List<Word> list = new ArrayList<>();
        Cursor cursor = db.query("Word", null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Word word = new Word();
                word.setId(cursor.getInt(cursor.getColumnIndex("id")));
                word.setWordName(cursor.getString(cursor.getColumnIndex("word_name")));
                word.setWordMean(cursor.getString(cursor.getColumnIndex("word_mean")));
                word.setWordTime(cursor.getString(cursor.getColumnIndex("word_time")));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
