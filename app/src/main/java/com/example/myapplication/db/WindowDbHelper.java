package com.example.myapplication.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.entity.WindowInfo;


import java.util.ArrayList;
import java.util.List;

public class WindowDbHelper extends SQLiteOpenHelper {
    private static WindowDbHelper sHelper;
    private static final String DB_NAME = "window.db";//数据库名
    private static final int VERSION = 1;//版本号

    //实现其中构造方法
    public WindowDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static WindowDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new WindowDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table window_table(window_id integer primary key autoincrement, " +
                "window_name text ," +       //
                "canteen_name text" + //所属的食堂
                ")");
        //init
        //合一楼
        ContentValues values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "航味");
        String nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼米之乡");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "合一楼");
        values.put("window_name", "江南风情");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "合一楼");
        values.put("window_name", "快餐");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "合一楼");
        values.put("window_name", "小盘菜");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "合一楼");
        values.put("window_name", "早点窗口");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "合一楼");
        values.put("window_name", "面食窗口");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "合一楼");
        values.put("window_name", "蒸饭窗口");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼粉窗口");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "合一楼");
        values.put("window_name", "饮吧窗口");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        /*
        学一食堂
        */

        values.put("canteen_name", "学一食堂");
        values.put("window_name", "东北菜窗口");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "学一食堂");
        values.put("window_name", "常驻窗口");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "学一食堂");
        values.put("window_name", "拌饭窗口");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "学一食堂");
        values.put("window_name", "饮吧窗口");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "学一食堂");
        values.put("window_name", "特色窗口");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "学二食堂");
        values.put("window_name", "唯二窗口之一");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

        values.put("canteen_name", "学二食堂");
        values.put("window_name", "唯二窗口之二");
        nullColumnHack = "values(null,?,?)";
        db.insert("window_table", nullColumnHack, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //实现注册
    public int addWindow(String window_name, String canteen_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("window_name", window_name);
        values.put("canteen_name", canteen_name);
        String nullColumnHack = "values(null,?,?)";
        //执行
        int insert = (int) db.insert("window_table", nullColumnHack, values);
        //插入成功，返回id,插入失败返回-1
        db.close();
        return insert;
    }

    //检查是否已经存在该食堂和窗口的对应关系
    @SuppressLint("Range")
    public WindowInfo isHasWindow(String canteen_name, String window_name) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select window_id,window_name,canteen_name  from window_table where canteen_name=? and window_name=?";
        String[] selectionArgs = {canteen_name, window_name};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        WindowInfo windowInfo = null;
        if (cursor.moveToNext()) {
            int window_id = cursor.getInt(cursor.getColumnIndex("window_id"));
            String window_Name = cursor.getString(cursor.getColumnIndex("window_name"));
            String canteen_Name = cursor.getString(cursor.getColumnIndex("canteen_name"));
            windowInfo = new WindowInfo(window_Name, window_id, canteen_Name);
        }
        cursor.close();
        db.close();
        return windowInfo;
    }
    @SuppressLint("Range")
    public WindowInfo isHasWindowById(int window_id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select window_id,window_name,canteen_name  from window_table where window_id=?";
        String[] selectionArgs = {window_id+""};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        WindowInfo windowInfo = null;
        if (cursor.moveToNext()) {
            String window_Name = cursor.getString(cursor.getColumnIndex("window_name"));
            String canteen_Name = cursor.getString(cursor.getColumnIndex("canteen_name"));
            windowInfo = new WindowInfo(window_Name, window_id, canteen_Name);
        }
        cursor.close();
        db.close();
        return windowInfo;
    }

    public int updateWindowCanteenName(String before_canteen_name, String canteen_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("canteen_name", canteen_name);
        // 执行SQL
        int update = db.update("window_table", values, " canteen_name=?", new String[]{before_canteen_name});
        // 关闭数据库连接
        db.close();
        return update;
    }

    public int updateWindowName(String canteen_name, String before_window_name, String after_window_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("window_name", after_window_name);
        // 执行SQL
        int update = db.update("window_table", values, " canteen_name=? and window_name=?", new String[]{canteen_name, before_window_name});
        // 关闭数据库连接
        db.close();
        return update;
    }

    //根据食堂名查询对应的窗口列表
    @SuppressLint("Range")
    public List<WindowInfo> queryWindowListData(String diningName) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<WindowInfo> list = new ArrayList<>();
        String sql = "select window_id,window_name,canteen_name  from window_table where canteen_name=?";
        String[] selectionArgs = {diningName};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            int window_id = cursor.getInt(cursor.getColumnIndex("window_id"));
            String window_name = cursor.getString(cursor.getColumnIndex("window_name"));
            String canteen_name = cursor.getString(cursor.getColumnIndex("canteen_name"));
            list.add(new WindowInfo(window_name, window_id, canteen_name));
        }
        cursor.close();
        db.close();
        return list;
    }
    @SuppressLint("Range")
    public List<WindowInfo> queryWindowListDataByWindow_name(String window_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<WindowInfo> list = new ArrayList<>();
        String sql = "select window_id,window_name,canteen_name  from window_table where window_name=?";
        String[] selectionArgs = {window_name};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            int window_id = cursor.getInt(cursor.getColumnIndex("window_id"));
            String canteen_name = cursor.getString(cursor.getColumnIndex("canteen_name"));
            list.add(new WindowInfo(window_name, window_id, canteen_name));
        }
        cursor.close();
        db.close();
        return list;
    }
    //删除操作
    //1.删除窗口
    public int deleteWindow(String canteen_name, String window_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("window_table", " canteen_name=? and window_name=?", new String[]{canteen_name, window_name});
        // 关闭数据库连接
        db.close();
        return delete;
    }

    //2.删除食堂
    public int deleteCanteen(String canteen_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("window_table", " canteen_name=?", new String[]{canteen_name});
        // 关闭数据库连接
        db.close();
        return delete;
    }
}

