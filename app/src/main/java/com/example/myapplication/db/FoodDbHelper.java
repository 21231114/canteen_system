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
        //合一楼
        //航味窗口
        ContentValues values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "航味");
        values.put("food_name", "卤煮");
        values.put("food_type", 2);
        String nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "航味");
        values.put("food_name", "火烧");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "航味");
        values.put("food_name", "麻辣香锅");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "航味");
        values.put("food_name", "炒饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "航味");
        values.put("food_name", "奶茶");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "航味");
        values.put("food_name", "炒面");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "航味");
        values.put("food_name", "酸辣汤");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "航味");
        values.put("food_name", "烤肉拌饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "航味");
        values.put("food_name", "鸡蛋灌饼");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "航味");
        values.put("food_name", "煎饼果子");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "航味");
        values.put("food_name", "酸奶");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "航味");
        values.put("food_name", "烤鸭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "航味");
        values.put("food_name", "豆浆");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼米之乡");
        values.put("food_name", "鱼香肉丝");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼米之乡");
        values.put("food_name", "酸菜鱼");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼米之乡");
        values.put("food_name", "水煮肉片");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼米之乡");
        values.put("food_name", "酸梅汤");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼米之乡");
        values.put("food_name", "麻婆豆腐");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼米之乡");
        values.put("food_name", "油条");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼米之乡");
        values.put("food_name", "豆浆");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼米之乡");
        values.put("food_name", "糖醋里脊");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼米之乡");
        values.put("food_name", "蛋炒饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼米之乡");
        values.put("food_name", "皮蛋瘦肉粥");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "江南风情");
        values.put("food_name", "小笼包");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "江南风情");
        values.put("food_name", "红烧排骨");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "江南风情");
        values.put("food_name", "松鼠鱼");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "江南风情");
        values.put("food_name", "蛋黄酥");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "江南风情");
        values.put("food_name", "糖醋里脊");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "江南风情");
        values.put("food_name", "南瓜饼");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "江南风情");
        values.put("food_name", "绿豆汤");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "江南风情");
        values.put("food_name", "炒年糕");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "江南风情");
        values.put("food_name", "八宝饭");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "江南风情");
        values.put("food_name", "龙井虾仁");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);


        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "快餐");
        values.put("food_name", "汉堡");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "快餐");
        values.put("food_name", "炸鸡");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "快餐");
        values.put("food_name", "可乐");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "快餐");
        values.put("food_name", "薯条");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "快餐");
        values.put("food_name", "三明治");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "快餐");
        values.put("food_name", "冰淇淋");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "快餐");
        values.put("food_name", "披萨");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "快餐");
        values.put("food_name", "沙拉");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "快餐");
        values.put("food_name", "奶茶");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "快餐");
        values.put("food_name", "寿司");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "小盘菜");
        values.put("food_name", "麻辣牛肉");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "小盘菜");
        values.put("food_name", "凉拌黄瓜");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "小盘菜");
        values.put("food_name", "酱香鸡");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "小盘菜");
        values.put("food_name", "花生米");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "小盘菜");
        values.put("food_name", "酸辣土豆丝");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "小盘菜");
        values.put("food_name", "拍黄瓜");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "小盘菜");
        values.put("food_name", "糖醋排骨");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "小盘菜");
        values.put("food_name", "炸酱面");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "小盘菜");
        values.put("food_name", "酸梅汤");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "小盘菜");
        values.put("food_name", "烤鱼");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);


        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "早点窗口");
        values.put("food_name", "豆浆");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "早点窗口");
        values.put("food_name", "油条");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "早点窗口");
        values.put("food_name", "包子");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "早点窗口");
        values.put("food_name", "鸡蛋灌饼");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "早点窗口");
        values.put("food_name", "煎饼果子");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "早点窗口");
        values.put("food_name", "馒头");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "早点窗口");
        values.put("food_name", "牛奶");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "早点窗口");
        values.put("food_name", "面包");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "早点窗口");
        values.put("food_name", "酸奶");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "早点窗口");
        values.put("food_name", "粥");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "面食窗口");
        values.put("food_name", "拉面");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "面食窗口");
        values.put("food_name", "饺子");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "面食窗口");
        values.put("food_name", "炸酱面");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "面食窗口");
        values.put("food_name", "馄饨");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "面食窗口");
        values.put("food_name", "刀削面");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "面食窗口");
        values.put("food_name", "水饺");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "面食窗口");
        values.put("food_name", "烙饼");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "面食窗口");
        values.put("food_name", "煎饼果子");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "面食窗口");
        values.put("food_name", "肉夹馍");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "面食窗口");
        values.put("food_name", "牛肉面");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "蒸饭窗口");
        values.put("food_name", "糯米鸡");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "蒸饭窗口");
        values.put("food_name", "肉松饭团");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "蒸饭窗口");
        values.put("food_name", "蛋炒饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "蒸饭窗口");
        values.put("food_name", "皮蛋瘦肉粥");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "蒸饭窗口");
        values.put("food_name", "酱油饭");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "蒸饭窗口");
        values.put("food_name", "烧麦");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "蒸饭窗口");
        values.put("food_name", "糯米团");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "蒸饭窗口");
        values.put("food_name", "煲仔饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "蒸饭窗口");
        values.put("food_name", "八宝饭");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "蒸饭窗口");
        values.put("food_name", "鸡肉饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼粉窗口");
        values.put("food_name", "鱼粉");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼粉窗口");
        values.put("food_name", "鱼丸");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼粉窗口");
        values.put("food_name", "鱼皮");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼粉窗口");
        values.put("food_name", "鱼饼");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼粉窗口");
        values.put("food_name", "鱼肉汤");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼粉窗口");
        values.put("food_name", "鱼肉饺子");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼粉窗口");
        values.put("food_name", "鱼肉炒饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼粉窗口");
        values.put("food_name", "鱼肉包子");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼粉窗口");
        values.put("food_name", "鱼肉面条");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "鱼粉窗口");
        values.put("food_name", "鱼肉沙拉");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "咖啡");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "奶茶");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "果汁");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "可乐");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "啤酒");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "红酒");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "奶昔");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "柠檬水");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "矿泉水");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "合一楼");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "茶");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        /*
        学一食堂
        */

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "东北菜窗口");
        values.put("food_name", "锅包肉");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "东北菜窗口");
        values.put("food_name", "酸菜白肉");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "东北菜窗口");
        values.put("food_name", "炖鱼块");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "东北菜窗口");
        values.put("food_name", "猪肉炖粉条");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "东北菜窗口");
        values.put("food_name", "酸菜饺子");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "东北菜窗口");
        values.put("food_name", "地三鲜");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "东北菜窗口");
        values.put("food_name", "糖醋排骨");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "东北菜窗口");
        values.put("food_name", "炒米粉");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "东北菜窗口");
        values.put("food_name", "拔丝地瓜");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "东北菜窗口");
        values.put("food_name", "炒肝儿");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "常驻窗口");
        values.put("food_name", "鱼香肉丝");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "常驻窗口");
        values.put("food_name", "酸菜鱼");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "常驻窗口");
        values.put("food_name", "水煮肉片");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "常驻窗口");
        values.put("food_name", "酸梅汤");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "常驻窗口");
        values.put("food_name", "麻婆豆腐");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "常驻窗口");
        values.put("food_name", "油条");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "常驻窗口");
        values.put("food_name", "豆浆");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "常驻窗口");
        values.put("food_name", "糖醋里脊");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "常驻窗口");
        values.put("food_name", "蛋炒饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "常驻窗口");
        values.put("food_name", "皮蛋瘦肉粥");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "拌饭窗口");
        values.put("food_name", "韩式拌饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "拌饭窗口");
        values.put("food_name", "麻辣拌饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "拌饭窗口");
        values.put("food_name", "肉末拌饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "拌饭窗口");
        values.put("food_name", "蔬菜拌饭");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "拌饭窗口");
        values.put("food_name", "酸辣拌饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "拌饭窗口");
        values.put("food_name", "鸡肉拌饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "拌饭窗口");
        values.put("food_name", "牛肉拌饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "拌饭窗口");
        values.put("food_name", "海鲜拌饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "拌饭窗口");
        values.put("food_name", "咖喱拌饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "拌饭窗口");
        values.put("food_name", "蛋炒拌饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "咖啡");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "奶茶");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "果汁");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "可乐");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "啤酒");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "红酒");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "奶昔");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "柠檬水");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "矿泉水");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "饮吧窗口");
        values.put("food_name", "茶");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "特色窗口");
        values.put("food_name", "麻辣香锅");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "特色窗口");
        values.put("food_name", "烤肉拌饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "特色窗口");
        values.put("food_name", "酸辣粉");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "特色窗口");
        values.put("food_name", "卤肉饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "特色窗口");
        values.put("food_name", "炒冰淇淋");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "特色窗口");
        values.put("food_name", "麻辣烫");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "特色窗口");
        values.put("food_name", "石锅拌饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "特色窗口");
        values.put("food_name", "烤鱼");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "特色窗口");
        values.put("food_name", "炒年糕");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学一食堂");
        values.put("window_name", "特色窗口");
        values.put("food_name", "芝士蛋糕");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);
        values = new ContentValues();
        values.put("canteen_name", "学二食堂");
        values.put("window_name", "唯二窗口之一");
        values.put("food_name", "麻辣香锅");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学二食堂");
        values.put("window_name", "唯二窗口之一");
        values.put("food_name", "烤肉拌饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学二食堂");
        values.put("window_name", "唯二窗口之一");
        values.put("food_name", "酸辣粉");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学二食堂");
        values.put("window_name", "唯二窗口之一");
        values.put("food_name", "卤肉饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学二食堂");
        values.put("window_name", "唯二窗口之一");
        values.put("food_name", "炒冰淇淋");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学二食堂");
        values.put("window_name", "唯二窗口之一");
        values.put("food_name", "麻辣烫");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学二食堂");
        values.put("window_name", "唯二窗口之一");
        values.put("food_name", "石锅拌饭");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学二食堂");
        values.put("window_name", "唯二窗口之一");
        values.put("food_name", "烤鱼");
        values.put("food_type", 2);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学二食堂");
        values.put("window_name", "唯二窗口之一");
        values.put("food_name", "炒年糕");
        values.put("food_type", 1);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        values = new ContentValues();
        values.put("canteen_name", "学二食堂");
        values.put("window_name", "唯二窗口之一");
        values.put("food_name", "芝士蛋糕");
        values.put("food_type", 0);
        nullColumnHack = "values(null,?,?,?,?)";
        db.insert("food_table", nullColumnHack, values);

        String sql = "ALTER TABLE food_table"
                + " ADD COLUMN food_price text  DEFAULT '10'";
        db.execSQL(sql);

        sql = "ALTER TABLE food_table"
                + " ADD COLUMN food_cnt text  DEFAULT '100'";
        db.execSQL(sql);
        //values.put("food_cnt", "1");
        db.execSQL("update food_table set food_cnt=1 where canteen_name=? and window_name=? and food_name=?",new String[]{"合一楼", "航味", "卤煮"});
        db.execSQL("update food_table set food_cnt=2 where canteen_name=? and window_name=? and food_name=?",new String[]{"合一楼", "航味", "火烧"});
       // db.update("food_table", values, " canteen_name=? and window_name=? and food_name=?", new String[]{"合一楼", "航味", "卤煮"});
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

    //一系列更新操作
    public int updateFoodType(String canteen_name, String window_name, String food_name, int food_type) {
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

    public int updateFoodPrice(String canteen_name, String window_name, String food_name, String food_price) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("food_price", food_price);
        // 执行SQL
        int update = db.update("food_table", values, " canteen_name=? and window_name=? and food_name=?", new String[]{canteen_name, window_name, food_name});
        // 关闭数据库连接
        db.close();
        return update;
    }

    public int updateFoodCnt(String canteen_name, String window_name, String food_name, String food_cnt) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("food_cnt", food_cnt);
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

    @SuppressLint("Range")
    public FoodInfo isHasFoodByFoodId(int food_id) {
        SQLiteDatabase db = getReadableDatabase();
        FoodInfo foodInfo = null;
        String sql = "select food_id,food_name,food_type,canteen_name,window_name,food_price,food_cnt  from food_table where food_id=?";
        String[] selectionArgs = {food_id + ""};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext()) {
            int food_type = cursor.getInt(cursor.getColumnIndex("food_type"));
            String food_name = cursor.getString(cursor.getColumnIndex("food_name"));
            String canteen_name = cursor.getString(cursor.getColumnIndex("canteen_name"));
            String window_name = cursor.getString(cursor.getColumnIndex("window_name"));
            String food_price = cursor.getString(cursor.getColumnIndex("food_price"));
            String food_cnt = cursor.getString(cursor.getColumnIndex("food_cnt"));
            foodInfo = new FoodInfo(food_id, food_name ,food_type,food_price,food_cnt, canteen_name, window_name);
        }
        cursor.close();
        db.close();
        return foodInfo;
    }

    @SuppressLint("Range")
    public FoodInfo queryFood(String canteen_name, String window_name, String food_name) {
        SQLiteDatabase db = getReadableDatabase();
        FoodInfo foodInfo = new FoodInfo(0, "", 0, "", "");
        String sql = "select food_id,food_name,canteen_name,food_type,window_name,food_price,food_cnt  from food_table where canteen_name=? and window_name=? and food_name=?";
        String[] selectionArgs = {canteen_name, window_name, food_name};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext()) {
            int food_id = cursor.getInt(cursor.getColumnIndex("food_id"));
            int food_type = cursor.getInt(cursor.getColumnIndex("food_type"));
            String food_cnt = cursor.getString(cursor.getColumnIndex("food_cnt"));
            String food_price = cursor.getString(cursor.getColumnIndex("food_price"));
            foodInfo = new FoodInfo(food_id, food_name, food_type, food_price, food_cnt, canteen_name, window_name);
        }
        cursor.close();
        db.close();
        return foodInfo;
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
        String sql = "select food_id,food_name,canteen_name,window_name,food_type,food_price,food_cnt from food_table where canteen_name=? and window_name=?";
        String[] selectionArgs = {canteen_name, window_name};//查询条件
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            int food_id = cursor.getInt(cursor.getColumnIndex("food_id"));
            int food_type = cursor.getInt(cursor.getColumnIndex("food_type"));
            String food_name = cursor.getString(cursor.getColumnIndex("food_name"));
            String food_price = cursor.getString(cursor.getColumnIndex("food_price"));
            String food_cnt = cursor.getString(cursor.getColumnIndex("food_cnt"));
            list.add(new FoodInfo(food_id, food_name, food_type, food_price, food_cnt, canteen_name, window_name));
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
