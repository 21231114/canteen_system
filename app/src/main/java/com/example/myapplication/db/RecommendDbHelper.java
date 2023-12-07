package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RecommendDbHelper extends SQLiteOpenHelper {
    private static  RecommendDbHelper sHelper;
    private static final String DB_NAME = "recommend.db";//数据库名
    private static final int VERSION = 1;//版本号{

    public RecommendDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table recommend_table(recommend_id integer primary key autoincrement, " +
                "user_id integer ," +
                "food_id integer," +
                "food_time text" +//浏览(即查看评论)或者点单的时间
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public synchronized static RecommendDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new RecommendDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    public int addRecommend(int user_id, int food_id, String food_time) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("user_id", user_id);
        values.put("food_id", food_id);
        values.put("food_time", food_time);
        String nullColumnHack = "values(null,?,?,?)";
        //执行
        int insert = (int) db.insert("recommend_table", nullColumnHack, values);
        //插入成功，返回id,插入失败返回-1
        db.close();
        return insert;
    }
}
