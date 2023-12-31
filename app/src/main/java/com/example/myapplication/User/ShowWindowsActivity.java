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
import com.example.myapplication.User.Adapter.CanteenListAdapter;
import com.example.myapplication.User.Adapter.WindowListAdapter;
import com.example.myapplication.db.CanteenDbHelper;
import com.example.myapplication.db.FavorDbHelper;
import com.example.myapplication.db.FoodDbHelper;
import com.example.myapplication.db.WindowDbHelper;
import com.example.myapplication.entity.CanteenInfo;
import com.example.myapplication.entity.WindowInfo;

import java.util.ArrayList;
import java.util.List;

public class ShowWindowsActivity extends AppCompatActivity {
    private RecyclerView myRecycleView;//当前展示列表的控件
    private WindowListAdapter windowListAdapter;
    private List<WindowInfo> dataList = new ArrayList<>();
    private String my_canteen_name = "";
    private String now_window_name = "";
    private int now_user_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_windows);
        Intent intent = getIntent();
        my_canteen_name = intent.getStringExtra("canteen_name");
        now_user_id = intent.getIntExtra("user_id", 0);
        now_window_name = intent.getStringExtra("window_name");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (my_canteen_name != null)
            toolbar.setTitle(my_canteen_name);//标题设置为食堂名
        myRecycleView = findViewById(R.id.windowRecyclerView);
        windowListAdapter = new WindowListAdapter(dataList);
        myRecycleView.setAdapter(windowListAdapter);//一定要记得为控件配备适配器
        loadData();//初始化数据

        windowListAdapter.setWindowListOnClickItemListener(new WindowListAdapter.WindowListOnClickItemListener() {
            @Override
            public void onItemEnterWindowClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                WindowInfo windowInfo = (WindowInfo) itemView.getTag();
                String now_window_name = windowInfo.getWindow_name();
                String now_canteen_name = windowInfo.getCanteen_name();
                Intent intent = new Intent(ShowWindowsActivity.this, ShowFoodsActivity.class);
                intent.putExtra("canteen_name", now_canteen_name);
                intent.putExtra("window_name", now_window_name);
                intent.putExtra("user_id", now_user_id);
                //传递要查看的窗口信息
                startActivity(intent);
            }

            @Override
            public void onItemAddFavorClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                WindowInfo windowInfo = (WindowInfo) itemView.getTag();
                int window_id = windowInfo.getWindow_id();
                if (FavorDbHelper.getInstance(ShowWindowsActivity.this).isHasFavor(now_user_id, window_id, 2) != null) {
                    Toast.makeText(ShowWindowsActivity.this, "添加失败，已经收藏该窗口", Toast.LENGTH_SHORT).show();
                } else {
                    FavorDbHelper.getInstance(ShowWindowsActivity.this).addFavor(now_user_id, window_id, 2);
                    Toast.makeText(ShowWindowsActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //设置分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        myRecycleView.addItemDecoration(dividerItemDecoration);
    }

    public void loadData() {
        dataList.clear();
        if (now_window_name == null) {
            windowListAdapter.setShow(false);
            dataList = WindowDbHelper.getInstance(ShowWindowsActivity.this).queryWindowListData(my_canteen_name);
        } else {
            windowListAdapter.setShow(true);
            dataList = WindowDbHelper.getInstance(ShowWindowsActivity.this).queryWindowListDataByWindow_name(now_window_name);
        }
        windowListAdapter.setDataList(dataList);
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