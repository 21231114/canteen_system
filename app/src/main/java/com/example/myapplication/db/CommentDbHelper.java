package com.example.myapplication.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.entity.CommentInfo;
import com.example.myapplication.entity.FavorInfo;

import java.util.ArrayList;
import java.util.List;

public class CommentDbHelper extends SQLiteOpenHelper {
    private static CommentDbHelper sHelper;
    private static final String DB_NAME = "comment.db";//数据库名
    private static final int VERSION = 1;//版本号{

    public CommentDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table comment_table(comment_id integer primary key autoincrement, " +
                "send_user_id integer ," +
                "food_id integer," +
                "receive_user_id integer," +
                "content text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public synchronized static CommentDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new CommentDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    public int addComment(int send_user_id, int food_id, int receive_user_id, String content) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("send_user_id", send_user_id);
        values.put("food_id", food_id);
        values.put("receive_user_id", receive_user_id);
        values.put("content", content);
        String nullColumnHack = "values(null,?,?,?,?)";
        //执行
        int insert = (int) db.insert("comment_table", nullColumnHack, values);
        //插入成功，返回id,插入失败返回-1
        db.close();
        return insert;
    }

    @SuppressLint("Range")
    public List<CommentInfo> queryCommentListDataByFoodId(int food_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<CommentInfo> list = new ArrayList<>();
        String sql = "select comment_id,send_user_id,receive_user_id,food_id,content from comment_table where food_id=?";
        String[] selectionArgs = {food_id + ""};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            int comment_id = cursor.getInt(cursor.getColumnIndex("comment_id"));
            int send_user_id = cursor.getInt(cursor.getColumnIndex("send_user_id"));
            int receive_user_id = cursor.getInt(cursor.getColumnIndex("receive_user_id"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            list.add(new CommentInfo(comment_id, send_user_id, food_id, receive_user_id, content));
        }
        cursor.close();
        db.close();
        return list;
    }
}
