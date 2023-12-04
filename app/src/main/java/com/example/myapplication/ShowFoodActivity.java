package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.adapter.FoodListAdapter;
import com.example.myapplication.db.FoodDbHelper;
import com.example.myapplication.db.WindowDbHelper;
import com.example.myapplication.entity.FoodInfo;
import com.example.myapplication.entity.WindowInfo;

import java.util.ArrayList;
import java.util.List;

public class ShowFoodActivity extends AppCompatActivity {
    private RecyclerView myRecycleView;//当前展示列表的控件
    private FoodListAdapter myFoodListAdapter;
    private List<String> foodList = new ArrayList<>();
    private String my_canteen_name;
    private String my_window_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //先插入一个食物，做做测试
        FoodDbHelper.getInstance(ShowFoodActivity.this).addFood("测试食物","1","1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food);
        //获取传输进来的要展示的食物信息
        Intent intent = getIntent();
        my_canteen_name = intent.getStringExtra("canteen_name");
        my_window_name = intent.getStringExtra("window_name");
        //控件初始化
        myRecycleView = findViewById(R.id.foodRecyclerView);
        myFoodListAdapter = new FoodListAdapter();
        myRecycleView.setAdapter(myFoodListAdapter);//一定要记得为控件配备适配器
        loadData();//初始化数据
    }

    //根据食堂名和窗口名来加载数据
    public void loadData() {
        foodList.clear();
        List<FoodInfo> foodInfoList = FoodDbHelper.getInstance(ShowFoodActivity.this).queryFoodListData(my_canteen_name, my_window_name);
        for (int i = 0; i < foodInfoList.size(); i++) {
            foodList.add(foodInfoList.get(i).getFood_name());
        }
        myFoodListAdapter.setDataList(foodList);
    }
}