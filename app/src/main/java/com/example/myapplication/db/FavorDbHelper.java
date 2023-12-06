package com.example.myapplication.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.entity.FavorInfo;
import com.example.myapplication.entity.UserInfo;
import com.example.myapplication.entity.WindowInfo;

import java.util.ArrayList;
import java.util.List;

public class FavorDbHelper extends SQLiteOpenHelper {
    private static FavorDbHelper sHelper;
    private static final String DB_NAME = "favor.db";//数据库名
    private static final int VERSION = 1;//版本号

    public FavorDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public synchronized static FavorDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new FavorDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table favor_table(favor_id integer primary key autoincrement, " +
                "user_id integer ," +
                "food_id integer" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public int addFavor(int user_id, int food_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("user_id", user_id);
        values.put("food_id", food_id);
        String nullColumnHack = "values(null,?,?)";
        //执行
        int insert = (int) db.insert("favor_table", nullColumnHack, values);
        //插入成功，返回id,插入失败返回-1
        db.close();
        return insert;
    }

    //用户是否已经收藏食物
    @SuppressLint("Range")
    public FavorInfo isHasFavor(int user_id, int food_id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select user_id,food_id,favor_id  from favor_table where user_id=? and food_id=?";
        String[] selectionArgs = {user_id + "", food_id + ""};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        FavorInfo favorInfo = null;
        if (cursor.moveToNext()) {
            int favor_id = cursor.getInt(cursor.getColumnIndex("favor_id"));
            favorInfo = new FavorInfo(favor_id, user_id, food_id);
        }
        cursor.close();
        db.close();
        return favorInfo;
    }

    //查询用户的收藏
    @SuppressLint("Range")
    public List<FavorInfo> queryFavorListData(int user_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<FavorInfo> list = new ArrayList<>();
        String sql = "select favor_id,user_id,food_id  from favor_table where user_id=?";
        String[] selectionArgs = {user_id + ""};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            int favor_id = cursor.getInt(cursor.getColumnIndex("favor_id"));
            int food_id = cursor.getInt(cursor.getColumnIndex("food_id"));
            list.add(new FavorInfo(favor_id, user_id, food_id));
        }
        cursor.close();
        db.close();
        return list;
    }
    public int deleteFavor(int user_id,int food_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("favor_table", " user_id=? and food_id=?", new String[]{user_id + "",food_id+""});
        // 关闭数据库连接
        db.close();
        return delete;
    }
    public int deleteFavorByUserId(int user_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("favor_table", " user_id=?", new String[]{user_id + ""});
        // 关闭数据库连接
        db.close();
        return delete;
    }

    public int deleteFavorByFoodId(int food_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("favor_table", " food_id=?", new String[]{food_id + ""});
        // 关闭数据库连接
        db.close();
        return delete;
    }
}
