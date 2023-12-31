package com.example.myapplication.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.entity.CanteenInfo;
import com.example.myapplication.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class CanteenDbHelper extends SQLiteOpenHelper {
    private static CanteenDbHelper sHelper;
    private static final String DB_NAME = "canteen.db";//数据库名
    private static final int VERSION = 1;//版本号

    //实现其中构造方法
    public CanteenDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static CanteenDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new CanteenDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table canteen_table(canteen_id integer primary key autoincrement, " +
                "canteen_name text unique" +       //食堂名,不能重复
                ")");
        //初始化数据
        ContentValues values = new ContentValues();
        values.put("canteen_name", "合一楼");
        String nullColumnHack = "values(null,?)";
        db.insert("canteen_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        nullColumnHack = "values(null,?)";
        db.insert("canteen_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学二食堂");
        nullColumnHack = "values(null,?)";
        db.insert("canteen_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学三食堂");
        nullColumnHack = "values(null,?)";
        db.insert("canteen_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学四食堂");
        nullColumnHack = "values(null,?)";
        db.insert("canteen_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学五食堂");
        nullColumnHack = "values(null,?)";
        db.insert("canteen_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学六食堂");
        nullColumnHack = "values(null,?)";
        db.insert("canteen_table", nullColumnHack, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //实现注册
    public int addCanteen(String canteen_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("canteen_name", canteen_name);
        String nullColumnHack = "values(null,?)";
        //执行
        int insert = (int) db.insert("canteen_table", nullColumnHack, values);
        //插入成功，返回id,插入失败返回-1
        db.close();
        return insert;
    }

    public int updateCanteen(String before_canteen_name, String canteen_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("canteen_name", canteen_name);
        // 执行SQL
        int update = db.update("canteen_table", values, " canteen_name=?", new String[]{before_canteen_name});
        // 关闭数据库连接
        db.close();
        return update;
    }

    //判断食堂是否已经存在
    @SuppressLint("Range")
    public CanteenInfo isHasCanteen(String canteen_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        CanteenInfo canteenInfo = null;
        String sql = "select canteen_id,canteen_name  from canteen_table where canteen_name=?";
        String[] selectionArgs = {canteen_name};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext()) {
            int canteen_id = cursor.getInt(cursor.getColumnIndex("canteen_id"));
            canteenInfo = new CanteenInfo(canteen_id, canteen_name);
        }
        cursor.close();
        db.close();
        return canteenInfo;
    }
    @SuppressLint("Range")
    public CanteenInfo isHasCanteenById(int canteen_id) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        CanteenInfo canteenInfo = null;
        String sql = "select canteen_id,canteen_name  from canteen_table where canteen_id=?";
        String[] selectionArgs = {canteen_id+""};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext()) {
            //int canteen_id = cursor.getInt(cursor.getColumnIndex("canteen_id"));
            String canteen_name = cursor.getString(cursor.getColumnIndex("canteen_name"));
            canteenInfo = new CanteenInfo(canteen_id, canteen_name);
        }
        cursor.close();
        db.close();
        return canteenInfo;
    }
    //将所有数据转换为一个链表传回去
    @SuppressLint("Range")
    public List<CanteenInfo> queryCanteenListData() {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<CanteenInfo> list = new ArrayList<>();
        String sql = "select canteen_id,canteen_name  from canteen_table";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int canteen_id = cursor.getInt(cursor.getColumnIndex("canteen_id"));
            String canteen_name = cursor.getString(cursor.getColumnIndex("canteen_name"));

            list.add(new CanteenInfo(canteen_id, canteen_name));
        }
        cursor.close();
        db.close();
        return list;
    }
    public int deleteCanteen(String canteen_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("canteen_table", " canteen_name=?", new String[]{canteen_name});
        // 关闭数据库连接
        db.close();
        return delete;
    }
}

