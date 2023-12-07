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
import com.example.myapplication.db.HistoryDbHelper;
import com.example.myapplication.db.RecommendDbHelper;
import com.example.myapplication.entity.FoodInfo;
import com.example.myapplication.entity.HistoryInfo;
import com.example.myapplication.entity.Recommend;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;

public class ShowFoodsActivity extends AppCompatActivity {
    private RecyclerView myRecycleView;
    private FoodsListAdapter myFoodsListAdapter;
    private List<FoodInfo> foodList = new ArrayList<>();
    private String my_canteen_name = "";
    private String my_window_name = "";
    private int now_user_id = 0;
    private String now_food_name = "";
    private String recommend_type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_foods);
        //data
        Intent intent = getIntent();
        //可能为null
        my_canteen_name = intent.getStringExtra("canteen_name");
        my_window_name = intent.getStringExtra("window_name");
        now_food_name = intent.getStringExtra("food_name");
        recommend_type = intent.getStringExtra("type");
        //
        now_user_id = intent.getIntExtra("user_id", 0);
        //控件
        myRecycleView = findViewById(R.id.foodsRecyclerView);
        //标题
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (my_window_name != null)
            toolbar.setTitle(my_canteen_name + " " + my_window_name);//标题设置
        else if (recommend_type != null) {
            if (recommend_type.equals("0"))
                toolbar.setTitle("个性化推荐");
            else toolbar.setTitle("热榜");
        }
        //视图于适配器绑定
        myFoodsListAdapter = new FoodsListAdapter();
        myRecycleView.setAdapter(myFoodsListAdapter);//一定要记得为控件配备适配器
        loadData();//初始化数据

        //设置recycleView每个itemView的按钮操作
        myFoodsListAdapter.setMyFoodsListOnClickItemListener(new FoodsListAdapter.FoodsListOnClickItemListener() {
            @Override
            public void onItemEnterCommentClick(int position) {
                Intent intent = new Intent(ShowFoodsActivity.this, ShowFoodCommentActivity.class);
                int food_id = ((FoodInfo) getRecyclerViewItem(myRecycleView, position).getTag()).getFood_id();
                String food_time = getTime();
                RecommendDbHelper.getInstance(ShowFoodsActivity.this).addRecommend(now_user_id, food_id, food_time);//查看评论时加，视为浏览

                FoodInfo foodInfo = FoodDbHelper.getInstance(ShowFoodsActivity.this).isHasFoodByFoodId(food_id);
                intent.putExtra("food_info", foodInfo);
                intent.putExtra("now_user_id", now_user_id);
                startActivity(intent);
            }

            @Override
            public void onItemAddOrderClick(int position) {
                FoodInfo foodInfo = ((FoodInfo) getRecyclerViewItem(myRecycleView, position).getTag());
                int food_id = foodInfo.getFood_id();
                int food_cnt = Integer.parseInt(foodInfo.getFood_cnt());
                String food_time = getTime();
                if (food_cnt == 0) {
                    Toast.makeText(ShowFoodsActivity.this, "已售罄,购买失败", Toast.LENGTH_SHORT).show();
                } else {
                    int row = HistoryDbHelper.getInstance(ShowFoodsActivity.this).addHistory(now_user_id, food_id, food_time);
                    if (row > 0) {
                        Toast.makeText(ShowFoodsActivity.this, "购买成功", Toast.LENGTH_SHORT).show();
                        RecommendDbHelper.getInstance(ShowFoodsActivity.this).addRecommend(now_user_id, food_id, food_time);//点单量时加
                        String now_food_cnt = Integer.toString(food_cnt - 1);
                        FoodDbHelper.getInstance(ShowFoodsActivity.this).updateFoodCnt(foodInfo.getCanteen_name(), foodInfo.getWindow_name(), foodInfo.getFood_name(), now_food_cnt);
                    } else {
                        Toast.makeText(ShowFoodsActivity.this, "购买失败", Toast.LENGTH_SHORT).show();
                    }
                }
                loadData();//余量可能会改变
            }

            @Override
            public void onItemAddFavorClick(int position) {
                int food_id = ((FoodInfo) getRecyclerViewItem(myRecycleView, position).getTag()).getFood_id();
                if (FavorDbHelper.getInstance(ShowFoodsActivity.this).isHasFavor(now_user_id, food_id, 1) != null) {
                    Toast.makeText(ShowFoodsActivity.this, "添加失败，已经收藏该菜品", Toast.LENGTH_SHORT).show();
                } else {
                    FavorDbHelper.getInstance(ShowFoodsActivity.this).addFavor(now_user_id, food_id, 1);
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
        if (my_window_name != null) {
            foodList = FoodDbHelper.getInstance(ShowFoodsActivity.this).queryFoodListData(my_canteen_name, my_window_name);
            myFoodsListAdapter.setShow(false);
        } else if (now_food_name != null) {
            foodList = FoodDbHelper.getInstance(ShowFoodsActivity.this).queryFoodListDataByFoodName(now_food_name);
            myFoodsListAdapter.setShow(true);
        } else {
            //此时是推荐功能
            List<Recommend> recommendList = new ArrayList<>();
            int now_time = timeIs(getTime());
            myFoodsListAdapter.setShow(true);
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            if (Objects.equals(recommend_type, "0")) {
                //个性化
                recommendList = RecommendDbHelper.getInstance(ShowFoodsActivity.this).queryRecommendListDataByUserId(now_user_id);
                for (int i = 0; i < recommendList.size(); i++) {
                    Recommend recommend = recommendList.get(i);
                    int food_id = recommend.getFood_id();
                    FoodInfo foodInfo = FoodDbHelper.getInstance(ShowFoodsActivity.this).isHasFoodByFoodId(food_id);
                    int food_type = foodInfo.getFood_type();
                    if (now_time == 1) {
                        //当前是早饭
                        if (food_type == 0 || food_type == 1) {
                            if (treeMap.containsKey(food_id)) {
                                int cnt = treeMap.get(food_id);
                                treeMap.remove(food_id);
                                treeMap.put(food_id, cnt + 1);
                            } else {
                                treeMap.put(food_id, 1);
                            }
                        }
                    } else {
                        //当前是正餐
                        if (food_type == 0 || food_type == 2) {
                            if (treeMap.containsKey(food_id)) {
                                int cnt = treeMap.get(food_id);
                                treeMap.remove(food_id);
                                treeMap.put(food_id, cnt + 1);
                            } else {
                                treeMap.put(food_id, 1);
                            }
                        }
                    }
                }
                TreeSet<Sort> treeSet = new TreeSet<>();
                while (!treeMap.isEmpty()) {
                    int food_id = treeMap.firstKey();
                    int food_cnt = treeMap.get(food_id);
                    treeMap.remove(food_id);
                    treeSet.add(new Sort(food_cnt, food_id));
                }
                while (!treeSet.isEmpty()) {
                    Sort sort = treeSet.first();
                    treeSet.remove(sort);
                    FoodInfo foodInfo = FoodDbHelper.getInstance(ShowFoodsActivity.this).isHasFoodByFoodId(sort.getFood_id());
                    foodList.add(foodInfo);
                }
            } else {
                recommendList = RecommendDbHelper.getInstance(ShowFoodsActivity.this).queryRecommendListData();
            }
        }
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

    public String getTime() {
        DateFormat dfgmt = new java.text.SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        dfgmt.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String nowTime = dfgmt.format(new Date());
        return nowTime;
    }

    public int timeIs(String time) {
        int hour = Integer.parseInt(time.substring(11, 13));//通过小时看是什么饭点
        if (hour >= 8 && hour <= 11) {
            return 1;//提供早餐
        } else {
            return 0;//提供正餐（午餐和晚餐）
        }
    }
}