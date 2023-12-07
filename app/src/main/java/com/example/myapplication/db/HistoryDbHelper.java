package com.example.myapplication.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.entity.FavorInfo;
import com.example.myapplication.entity.HistoryInfo;

import java.util.ArrayList;
import java.util.List;

public class HistoryDbHelper extends SQLiteOpenHelper {
    private static HistoryDbHelper sHelper;
    private static final String DB_NAME = "history.db";//数据库名
    private static final int VERSION = 1;//版本号{

    public HistoryDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public synchronized static HistoryDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new HistoryDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table history_table(history_id integer primary key autoincrement, " +
                "user_id integer ," +
                "food_id integer," +
                "food_time text" +//点单时间
                ")");
        ContentValues values = new ContentValues();
        values.put("user_id", 1);
        values.put("food_id", 1);
        values.put("food_time", "2023-12-06 08:00:00");
        String nullColumnHack = "values(null,?,?,?)";
        db.insert("history_table", nullColumnHack, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int addHistory(int user_id, int food_id, String food_time) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("user_id", user_id);
        values.put("food_id", food_id);
        values.put("food_time", food_time);
        String nullColumnHack = "values(null,?,?,?)";
        //执行
        int insert = (int) db.insert("history_table", nullColumnHack, values);
        //插入成功，返回id,插入失败返回-1
        db.close();
        return insert;
    }

    //    @SuppressLint("Range")
//    public List<HistoryInfo> queryHistoryListDataToday(String time) {
//        //获取SQLiteDatabase实例
//        SQLiteDatabase db = getReadableDatabase();
//        List<FavorInfo> list = new ArrayList<>();
//        String sql = "select favor_id,user_id,food_id,type from favor_table where user_id=?";
//        String[] selectionArgs = {user_id + ""};//查询条件
//        Cursor cursor = db.rawQuery(sql, selectionArgs);
//        while (cursor.moveToNext()) {
//            int favor_id = cursor.getInt(cursor.getColumnIndex("favor_id"));
//            int food_id = cursor.getInt(cursor.getColumnIndex("food_id"));
//            int type = cursor.getInt(cursor.getColumnIndex("type"));
//            list.add(new FavorInfo(favor_id, user_id, food_id, type));
//        }
//        cursor.close();
//        db.close();
//        return list;
//    }
    @SuppressLint("Range")
    public List<HistoryInfo> queryHistoryListData(int user_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<HistoryInfo> list = new ArrayList<>();
        String sql = "select history_id,user_id,food_id,food_time from history_table where user_id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{user_id + ""});
        while (cursor.moveToNext()) {
            int history_id = cursor.getInt(cursor.getColumnIndex("history_id"));
            int food_id = cursor.getInt(cursor.getColumnIndex("food_id"));
            String food_time = cursor.getString(cursor.getColumnIndex("food_time"));
            list.add(new HistoryInfo(history_id, user_id, food_id, food_time));
        }
        cursor.close();
        db.close();
        return list;
    }

    @SuppressLint("Range")
    public List<HistoryInfo> queryHistoryListDataByToday(String now_time, int user_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<HistoryInfo> list = new ArrayList<>();
        String sql = "select history_id,user_id,food_id,food_time from history_table where  substr(food_time,1,10)=? and  user_id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{now_time, user_id + ""});
        while (cursor.moveToNext()) {
            int history_id = cursor.getInt(cursor.getColumnIndex("history_id"));
            int food_id = cursor.getInt(cursor.getColumnIndex("food_id"));
            String food_time = cursor.getString(cursor.getColumnIndex("food_time"));
            list.add(new HistoryInfo(history_id, user_id, food_id, food_time));
        }
        cursor.close();
        db.close();
        return list;
    }

    public int deleteHistory(int history_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("history_table", "history_id=?", new String[]{history_id + ""});
        // 关闭数据库连接
        db.close();
        return delete;
    }

    public int updateFood(int food_id, int history_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("food_id", food_id);
        // 执行SQL
        int update = db.update("history_table", values, "history_id=?", new String[]{history_id + ""});
        // 关闭数据库连接
        db.close();
        return update;
    }
}
