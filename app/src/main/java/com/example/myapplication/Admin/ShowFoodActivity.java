package com.example.myapplication.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Admin.adapter.FoodListAdapter;
import com.example.myapplication.Admin.dialog.ModifyFoodCntActivity;
import com.example.myapplication.Admin.dialog.ModifyFoodPriceActivity;
import com.example.myapplication.Admin.dialog.ModifyFoodTypeActivity;
import com.example.myapplication.R;
import com.example.myapplication.db.FoodDbHelper;
import com.example.myapplication.Admin.dialog.AddFoodActivity;
import com.example.myapplication.Admin.dialog.ModifyFoodActivity;
import com.example.myapplication.entity.FoodInfo;

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
        myFoodListAdapter.setMyFoodListOnClickItemListener(new FoodListAdapter.FoodListOnClickItemListener() {
            @Override
            public void onItemDeleteFoodClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                String food_name = ((TextView) itemView.findViewById(R.id.food_name)).getText().toString();
                int row = FoodDbHelper.getInstance(ShowFoodActivity.this).deleteFood(my_canteen_name, my_window_name, food_name);
                if (row > 0) {
                    Toast.makeText(ShowFoodActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ShowFoodActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                }
                loadData();//重新加载食物列表
            }

            @Override
            public void onItemMOdifyFoodTypeClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                String food_name = ((TextView) itemView.findViewById(R.id.food_name)).getText().toString();
                Intent intent = new Intent(ShowFoodActivity.this, ModifyFoodTypeActivity.class);
                intent.putExtra("canteen_name", my_canteen_name);
                intent.putExtra("window_name", my_window_name);
                intent.putExtra("food_name", food_name);
                startActivity(intent);
            }

            @Override
            public void onItemMOdifyFoodPriceClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                String food_name = ((TextView) itemView.findViewById(R.id.food_name)).getText().toString();
                Intent intent = new Intent(ShowFoodActivity.this, ModifyFoodPriceActivity.class);
                intent.putExtra("canteen_name", my_canteen_name);
                intent.putExtra("window_name", my_window_name);
                intent.putExtra("food_name", food_name);
                startActivity(intent);
            }

            @Override
            public void onItemMOdifyFoodCntClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                String food_name = ((TextView) itemView.findViewById(R.id.food_name)).getText().toString();
                Intent intent = new Intent(ShowFoodActivity.this, ModifyFoodCntActivity.class);
                intent.putExtra("canteen_name", my_canteen_name);
                intent.putExtra("window_name", my_window_name);
                intent.putExtra("food_name", food_name);
                startActivity(intent);
            }
        });
//设置分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        myRecycleView.addItemDecoration(dividerItemDecoration);
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

    public View getRecyclerViewItem(RecyclerView recyclerView, int position) {
        if (recyclerView == null || recyclerView.getLayoutManager() == null || recyclerView.getAdapter() == null || recyclerView.getAdapter().getItemCount() <= 0) {
            return null;
        }
        if (position > recyclerView.getAdapter().getItemCount()) {
            return null;
        }
        RecyclerView.ViewHolder viewHolder = recyclerView.getAdapter().createViewHolder(recyclerView, recyclerView.getAdapter().getItemViewType(position));
        recyclerView.getAdapter().onBindViewHolder(viewHolder, position);
        viewHolder.itemView.measure(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        return viewHolder.itemView;
    }
}