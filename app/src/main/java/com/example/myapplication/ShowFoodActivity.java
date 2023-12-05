package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.adapter.FoodListAdapter;
import com.example.myapplication.db.FoodDbHelper;
import com.example.myapplication.db.WindowDbHelper;
import com.example.myapplication.dialog.AddFoodActivity;
import com.example.myapplication.dialog.ModifyFoodActivity;
import com.example.myapplication.entity.FoodInfo;
import com.example.myapplication.entity.WindowInfo;

import java.util.ArrayList;
import java.util.List;

public class ShowFoodActivity extends AppCompatActivity {
    private RecyclerView myRecycleView;//当前展示列表的控件
    private FoodListAdapter myFoodListAdapter;
    private List<FoodInfo> foodList = new ArrayList<>();
    private String my_canteen_name;
    private String my_window_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //先插入一个食物，做做测试
        //FoodDbHelper.getInstance(ShowFoodActivity.this).addFood("测试食物","1","1");
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
        findViewById(R.id.add_food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowFoodActivity.this, AddFoodActivity.class);
                intent.putExtra("canteen_name", my_canteen_name);
                intent.putExtra("window_name", my_window_name);
                startActivity(intent);
            }
        });
        findViewById(R.id.modify_food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowFoodActivity.this, ModifyFoodActivity.class);
                intent.putExtra("canteen_name", my_canteen_name);
                intent.putExtra("window_name", my_window_name);
                startActivity(intent);
            }
        });
    }

    public void loadData() {
        foodList.clear();
        foodList = FoodDbHelper.getInstance(ShowFoodActivity.this).queryFoodListData(my_canteen_name, my_window_name);

        //Toast.makeText(this, Integer.toString(foodList.size()), Toast.LENGTH_SHORT).show();
        myFoodListAdapter.setDataList(foodList);
    }

    @Override
    protected void onResume() {
        //从其他活动返回需要重新加载数据，因为可能数据库发生了变化
        loadData();
        super.onResume();
    }
}