package com.example.myapplication.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.example.myapplication.Admin.ShowFoodActivity;
import com.example.myapplication.Admin.adapter.FoodListAdapter;
import com.example.myapplication.R;
import com.example.myapplication.User.Adapter.CanteenListAdapter;
import com.example.myapplication.User.Adapter.WindowListAdapter;
import com.example.myapplication.db.CanteenDbHelper;
import com.example.myapplication.db.FoodDbHelper;
import com.example.myapplication.db.WindowDbHelper;
import com.example.myapplication.entity.CanteenInfo;
import com.example.myapplication.entity.WindowInfo;

import java.util.ArrayList;
import java.util.List;

public class ShowWindowsActivity extends AppCompatActivity {
    private RecyclerView myRecycleView;//当前展示列表的控件
    private WindowListAdapter windowListAdapter;
    private List<String> dataList = new ArrayList<>();
    private String my_canteen_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_windows);
        Intent intent = getIntent();
        my_canteen_name = intent.getStringExtra("canteen_name");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(my_canteen_name);//标题设置为食堂名

        myRecycleView = findViewById(R.id.windowRecyclerView);
        windowListAdapter = new WindowListAdapter(dataList);
        myRecycleView.setAdapter(windowListAdapter);//一定要记得为控件配备适配器
        loadData();//初始化数据
    }

    public void loadData() {
        dataList.clear();
        List<WindowInfo> windowInfoList = WindowDbHelper.getInstance(ShowWindowsActivity.this).queryWindowListData(my_canteen_name);
        for (int i = 0; i < windowInfoList.size(); i++) {
            dataList.add(windowInfoList.get(i).getWindow_name());
        }
        windowListAdapter.setDataList(dataList);
    }
}