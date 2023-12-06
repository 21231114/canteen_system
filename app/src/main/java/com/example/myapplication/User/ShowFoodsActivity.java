package com.example.myapplication.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Admin.ShowFoodActivity;
import com.example.myapplication.Admin.adapter.FoodListAdapter;
import com.example.myapplication.R;
import com.example.myapplication.User.Adapter.FoodsListAdapter;
import com.example.myapplication.db.FavorDbHelper;
import com.example.myapplication.db.FoodDbHelper;
import com.example.myapplication.entity.FoodInfo;

import java.util.ArrayList;
import java.util.List;

public class ShowFoodsActivity extends AppCompatActivity {
    private RecyclerView myRecycleView;
    private FoodsListAdapter myFoodsListAdapter;
    private List<FoodInfo> foodList = new ArrayList<>();
    private String my_canteen_name = "";
    private String my_window_name = "";
    private int now_user_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_foods);
        //data
        Intent intent = getIntent();
        my_canteen_name = intent.getStringExtra("canteen_name");
        my_window_name = intent.getStringExtra("window_name");
        now_user_id = intent.getIntExtra("user_id", 0);
        //控件
        myRecycleView = findViewById(R.id.foodsRecyclerView);
        //标题
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(my_canteen_name + " " + my_window_name);//标题设置
        //视图于适配器绑定
        myFoodsListAdapter = new FoodsListAdapter();
        myRecycleView.setAdapter(myFoodsListAdapter);//一定要记得为控件配备适配器
        loadData();//初始化数据

        //设置recycleView每个itemView的按钮操作
        myFoodsListAdapter.setMyFoodsListOnClickItemListener(new FoodsListAdapter.FoodsListOnClickItemListener() {
            @Override
            public void onItemEnterCommentClick(int position) {

            }

            @Override
            public void onItemAddOrderClick(int position) {

            }

            @Override
            public void onItemAddFavorClick(int position) {
                TextView tv_food_name = getRecyclerViewItem(myRecycleView, position).findViewById(R.id.food_name);
                String now_food_name = tv_food_name.getText().toString();
                int food_id = getItemFoodId(my_canteen_name, my_window_name, now_food_name);
                if (FavorDbHelper.getInstance(ShowFoodsActivity.this).isHasFavor(now_user_id, food_id) != null) {
                    Toast.makeText(ShowFoodsActivity.this, "添加失败，已经收藏该菜品", Toast.LENGTH_SHORT).show();
                } else {
                    FavorDbHelper.getInstance(ShowFoodsActivity.this).addFavor(now_user_id, food_id);
                    Toast.makeText(ShowFoodsActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //设置分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        myRecycleView.addItemDecoration(dividerItemDecoration);
    }

    public void loadData() {
        foodList.clear();
        foodList = FoodDbHelper.getInstance(ShowFoodsActivity.this).queryFoodListData(my_canteen_name, my_window_name);
        myFoodsListAdapter.setDataList(foodList);
    }

    public int getItemFoodId(String canteen_name, String window_name, String food_name) {
        FoodInfo foodInfo = FoodDbHelper.getInstance(ShowFoodsActivity.this).queryFood(canteen_name, window_name, food_name);
        return foodInfo.getFood_id();
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