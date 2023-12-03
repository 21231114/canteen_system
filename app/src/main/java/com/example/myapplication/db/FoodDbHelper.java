//package com.example.myapplication.db;
//
//import android.annotation.SuppressLint;
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//import com.example.myapplication.entity.FoodInfo;
//import com.example.myapplication.entity.UserInfo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FoodDbHelper extends SQLiteOpenHelper {
//    private static FoodDbHelper sHelper;
//    private static final String DB_NAME = "Food.db";//数据库名
//    private static final int VERSION = 1;//版本号
//
//    //实现其中构造方法
//    public FoodDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    //创建单例，供使用调用该类里面的的增删改查的方法
//    public synchronized static FoodDbHelper getInstance(Context context) {
//        if (null == sHelper) {
//            sHelper = new FoodDbHelper(context, DB_NAME, null, VERSION);
//        }
//        return sHelper;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//        db.execSQL("create table food_table(food_id integer primary key autoincrement, " +
//                "food_name text unique," +//食物名,不能重复
//
//                ")");
//
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//
//    }
//
//    //实现登录
//    @SuppressLint("Range")//减少警告
//    public UserInfo login(String username) {
//        //获取SQLiteDatabase实例
//        SQLiteDatabase db = getReadableDatabase();
//        UserInfo userInfo = null;
//        String sql = "select Food_id,Food_name,password,register_type  from user_table where username=?";
//        String[] selectionArgs = {username};//查询条件
//        Cursor cursor = db.rawQuery(sql, selectionArgs);
//        if (cursor.moveToNext()) {
//            int user_id = cursor.getInt(cursor.getColumnIndex("user_id"));
//            String name = cursor.getString(cursor.getColumnIndex("username"));
//            String password = cursor.getString(cursor.getColumnIndex("password"));
//            int register_type = cursor.getInt(cursor.getColumnIndex("register_type"));
//            userInfo = new UserInfo(user_id, name, password, register_type);
//        }
//        cursor.close();
//        db.close();
//        return userInfo;
//    }
//
//    //实现注册
//    public int addFood(String Food_name) {
//        //获取SQLiteDatabase实例
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        //填充占位符
//        values.put("Food_name", Food_name);
//        String nullColumnHack = "values(null,?)";
//        //执行
//        int insert = (int) db.insert("Food_table", nullColumnHack, values);
//        //插入成功，返回id,插入失败返回-1
//        db.close();
//        return insert;
//    }
//
//    //TODO 在这里根据自己的业务需求，编写增删改查的方法，如下所示
//    public int updateFood(int Food_id, String Food_name) {
//        //获取SQLiteDatabase实例
//        SQLiteDatabase db = getWritableDatabase();
//        // 填充占位符
//        ContentValues values = new ContentValues();
//        values.put("Food_name", Food_name);
//        // 执行SQL
//        int update = db.update("Food_table", values, " Food_id=?", new String[]{Food_id + ""});
//        // 关闭数据库连接
//        db.close();
//        return update;
//    }
//
//    //将所有数据转换为一个链表传回去
//    @SuppressLint("Range")
//    public List<FoodInfo> queryFoodListData() {
//        //获取SQLiteDatabase实例
//        SQLiteDatabase db = getReadableDatabase();
//        List<FoodInfo> list = new ArrayList<>();
//        String sql = "select Food_id,Food_name  from Food_table";
//        Cursor cursor = db.rawQuery(sql, null);
//        while (cursor.moveToNext()) {
//            int Food_id = cursor.getInt(cursor.getColumnIndex("Food_id"));
//            String Food_name = cursor.getString(cursor.getColumnIndex("Food_name"));
//
//            list.add(new FoodInfo(Food_id, Food_name));
//        }
//        cursor.close();
//        db.close();
//        return list;
//    }
//}
