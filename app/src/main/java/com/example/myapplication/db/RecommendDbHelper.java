package com.example.myapplication.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.entity.HistoryInfo;
import com.example.myapplication.entity.Recommend;

import java.util.ArrayList;
import java.util.List;

public class RecommendDbHelper extends SQLiteOpenHelper {
    private static RecommendDbHelper sHelper;
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

    @SuppressLint("Range")
    public List<Recommend> queryRecommendListDataByUserId(int user_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<Recommend> list = new ArrayList<>();
        String sql = "select recommend_id,user_id,food_id,food_time from recommend_table where user_id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{user_id + ""});
        while (cursor.moveToNext()) {
            int recommend_id = cursor.getInt(cursor.getColumnIndex("recommend_id"));
            int food_id = cursor.getInt(cursor.getColumnIndex("food_id"));
            String food_time = cursor.getString(cursor.getColumnIndex("food_time"));
            list.add(new Recommend( recommend_id, user_id, food_id, food_time));
        }
        cursor.close();
        db.close();
        return list;
    }

    @SuppressLint("Range")
    public List<Recommend> queryRecommendListData() {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<Recommend> list = new ArrayList<>();
        String sql = "select recommend_id,user_id,food_id,food_time from recommend_table";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int  recommend_id = cursor.getInt(cursor.getColumnIndex("recommend_id"));
            int food_id = cursor.getInt(cursor.getColumnIndex("food_id"));
            int user_id = cursor.getInt(cursor.getColumnIndex("user_id"));
            String food_time = cursor.getString(cursor.getColumnIndex("food_time"));
            list.add(new Recommend( recommend_id, user_id, food_id, food_time));
        }
        cursor.close();
        db.close();
        return list;
    }
}
