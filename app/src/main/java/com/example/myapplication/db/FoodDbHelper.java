package com.example.myapplication.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.entity.FoodInfo;


import java.util.ArrayList;
import java.util.List;

public class FoodDbHelper extends SQLiteOpenHelper {
    private static FoodDbHelper sHelper;
    private static final String DB_NAME = "Food.db";//数据库名
    private static final int VERSION = 1;//版本号

    //实现其中构造方法
    public FoodDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static FoodDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new FoodDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table food_table(food_id integer primary key autoincrement, " +
                "food_name text," +
                "canteen_name text," +
                "window_name text," +
                "food_type integer" +//食物的类型，0--饮品,1--早饭 ,2正餐
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //实现注册
    public int addFood(String food_name, String canteen_name, String window_name, int food_type) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("food_name", food_name);
        values.put("canteen_name", canteen_name);
        values.put(" window_name", window_name);
        values.put(" food_type", food_type);
        String nullColumnHack = "values(null,?,?,?,?)";
        //执行
        int insert = (int) db.insert("food_table", nullColumnHack, values);
        //插入成功，返回id,插入失败返回-1
        db.close();
        return insert;
    }

    //TODO 在这里根据自己的业务需求，编写增删改查的方法，如下所示
    public int updateFoodName(String canteen_name, String window_name, String before_food_name, String after_food_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("food_name", after_food_name);
        // 执行SQL
        int update = db.update("food_table", values, "food_name=? and canteen_name=? and window_name=?", new String[]{before_food_name, canteen_name, window_name});
        // 关闭数据库连接
        db.close();
        return update;
    }

    //更新食物对应的窗口的名字
    public int updateFoodWindowName(String canteen_name, String before_window_name, String after_window_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("window_name", after_window_name);
        // 执行SQL
        int update = db.update("food_table", values, " canteen_name=? and window_name=?", new String[]{canteen_name, before_window_name});
        // 关闭数据库连接
        db.close();
        return update;
    }

    public int updateFoodType(String canteen_name, String window_name, String food_name,int food_type) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("food_type", food_type);
        // 执行SQL
        int update = db.update("food_table", values, " canteen_name=? and window_name=? and food_name=?", new String[]{canteen_name, window_name, food_name});
        // 关闭数据库连接
        db.close();
        return update;
    }

    //同一食堂，窗口不能含有相同的食物
    public boolean isHasFood(String canteen_name, String window_name, String food_name) {
        SQLiteDatabase db = getReadableDatabase();
        FoodInfo foodInfo = null;
        String sql = "select food_id,food_name,canteen_name  from food_table where canteen_name=? and window_name=? and food_name=?";
        String[] selectionArgs = {canteen_name, window_name, food_name};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        boolean ans = false;
        if (cursor.moveToNext()) {
            ans = true;
        }
        cursor.close();
        db.close();
        return ans;
    }

    //更新食物对应的食堂的名字
    public int updateFoodCanteenName(String before_canteen_name, String after_canteen_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("canteen_name", after_canteen_name);
        // 执行SQL
        int update = db.update("food_table", values, "canteen_name=?", new String[]{before_canteen_name});
        // 关闭数据库连接
        db.close();
        return update;
    }

    //更新食物类型
//    public int updateFoodCanteenName(String before_canteen_name, String after_canteen_name) {
//        //获取SQLiteDatabase实例
//        SQLiteDatabase db = getWritableDatabase();
//        // 填充占位符
//        ContentValues values = new ContentValues();
//        values.put("canteen_name", after_canteen_name);
//        // 执行SQL
//        int update = db.update("food_table", values, "canteen_name=?", new String[]{before_canteen_name});
//        // 关闭数据库连接
//        db.close();
//        return update;
//    }
    //需要根据食堂名称和窗口名称查询食物列表
    @SuppressLint("Range")
    public List<FoodInfo> queryFoodListData(String canteen_name, String window_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<FoodInfo> list = new ArrayList<>();
        String sql = "select food_id,food_name,canteen_name,window_name,food_type from food_table where canteen_name=? and window_name=?";
        String[] selectionArgs = {canteen_name, window_name};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            int food_id = cursor.getInt(cursor.getColumnIndex("food_id"));
            int food_type = cursor.getInt(cursor.getColumnIndex("food_type"));
            String food_name = cursor.getString(cursor.getColumnIndex("food_name"));
            list.add(new FoodInfo(food_id, food_name, food_type, canteen_name, window_name));
        }
        cursor.close();
        db.close();
        return list;
    }
    //各种删除操作

    //1.删除食物时
    public int deleteFood(String canteen_name, String window_name, String food_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("food_table", " canteen_name=? and window_name=? and food_name=?", new String[]{canteen_name, window_name, food_name});
        // 关闭数据库连接
        db.close();
        return delete;
    }

    //2.删除窗口是，删除，对应的食物
    public int deleteWindow(String canteen_name, String window_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("food_table", " canteen_name=? and window_name=?", new String[]{canteen_name, window_name});
        // 关闭数据库连接
        db.close();
        return delete;
    }

    //3.删除食堂时删除对应的食物
    public int deleteCanteen(String canteen_name) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("food_table", " canteen_name=?", new String[]{canteen_name});
        // 关闭数据库连接
        db.close();
        return delete;
    }
}
