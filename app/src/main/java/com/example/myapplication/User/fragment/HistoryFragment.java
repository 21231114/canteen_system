package com.example.myapplication.User.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.User.Adapter.HistoryListAdapter;
import com.example.myapplication.User.dialog.ModifyHistoryFoodActivity;
import com.example.myapplication.db.FavorDbHelper;
import com.example.myapplication.db.FoodDbHelper;
import com.example.myapplication.db.HistoryDbHelper;
import com.example.myapplication.entity.HistoryInfo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class HistoryFragment extends Fragment {
    private int now_user_id;
    private RecyclerView myRecycleView;
    private HistoryListAdapter historyListAdapter;
    private View rootView;
    private List<HistoryInfo> dataList = new ArrayList<>();
    private Button find_today;
    private Button find_all;
    private boolean isToday = false;

    public HistoryFragment(int now_user_id) {
        this.now_user_id = now_user_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_history, container, false);

        //初始化控件
        myRecycleView = rootView.findViewById(R.id.historyRecyclerView);
        find_today = rootView.findViewById(R.id.find_today);
        find_all = rootView.findViewById(R.id.find_all);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        historyListAdapter = new HistoryListAdapter();
        myRecycleView.setAdapter(historyListAdapter);
        loadData();

        find_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isToday = true;
                loadData();
            }
        });
        find_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isToday = false;
                loadData();
            }
        });
        historyListAdapter.setHistoryListOnClickItemListener(new HistoryListAdapter.HistoryListOnClickItemListener() {
            @Override
            public void onItemDeleteHistoryClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                HistoryInfo historyInfo = (HistoryInfo) itemView.getTag();
                int row = HistoryDbHelper.getInstance(getActivity()).deleteHistory(historyInfo.getHistory_id());
                if (row > 0) {
                    Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "删除失败", Toast.LENGTH_SHORT).show();
                }
                loadData();
            }

            @Override
            public void onItemModifyHistoryFoodIdClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                HistoryInfo historyInfo = (HistoryInfo) itemView.getTag();
                Intent intent = new Intent(getActivity(), ModifyHistoryFoodActivity.class);
                intent.putExtra("history", historyInfo);
                startActivity(intent);//修改历史菜品
            }
        });
        //设置分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        myRecycleView.addItemDecoration(dividerItemDecoration);
    }

    public void loadData() {
        dataList.clear();
        if (!isToday)
            dataList = HistoryDbHelper.getInstance(getActivity()).queryHistoryListData(now_user_id);
        else {
            String now_time = getTime().substring(0, 10);
            dataList = HistoryDbHelper.getInstance(getActivity()).queryHistoryListDataByToday(now_time,now_user_id);
        }
        for (int i = 0; i < dataList.size(); i++) {
            //如果菜品被删除，历史记录也随之删除
            HistoryInfo historyInfo = dataList.get(i);
            int food_id = historyInfo.getFood_id();
            int history_id = historyInfo.getHistory_id();
            if (FoodDbHelper.getInstance(getActivity()).isHasFoodByFoodId(food_id) == null) {
                //当前收藏的食物已经被删除了,因为是id所以保证后添加的食物不会有重复的
                HistoryDbHelper.getInstance(getActivity()).deleteHistory(history_id);
                dataList.remove(i);
                i--;
            }
        }
        historyListAdapter.setDataList(dataList);
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

}