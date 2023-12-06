package com.example.myapplication.User.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.db.FoodDbHelper;
import com.example.myapplication.entity.FoodInfo;
import com.example.myapplication.entity.HistoryInfo;

import java.util.ArrayList;
import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.MyHolder> {
    private List<HistoryInfo> dataList = new ArrayList<>();//存储食堂
    private HistoryListOnClickItemListener historyListOnClickItemListener;

    public void setDataList(List<HistoryInfo> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void setHistoryListOnClickItemListener(HistoryListOnClickItemListener historyListOnClickItemListener) {
        this.historyListOnClickItemListener = historyListOnClickItemListener;
    }

    public interface HistoryListOnClickItemListener {
        void onItemDeleteHistoryClick(int position);//接收点某个具体项菜单的操作函数

        void onItemModifyHistoryFoodIdClick(int position);

    }

    @NonNull
    @Override
    public HistoryListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_list_item, null);
        return new HistoryListAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListAdapter.MyHolder holder, @SuppressLint("RecyclerView") int position) {
        HistoryInfo historyInfo = dataList.get(position);
        holder.itemView.setTag(historyInfo);//让Item记录historyInfo
        int food_id = historyInfo.getFood_id();
        //和收藏同样的道理，在加载数据时会清空已经被删除的菜品,所以绝对不会为null
        FoodInfo foodInfo = FoodDbHelper.getInstance(holder.itemView.getContext()).isHasFoodByFoodId(food_id);
        holder.canteen_name.setText(foodInfo.getCanteen_name());
        holder.window_name.setText(foodInfo.getWindow_name());
        holder.food_name.setText(foodInfo.getFood_name());
        holder.food_time.setText(historyInfo.getFood_time());
        holder.itemView.findViewById(R.id.delete_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historyListOnClickItemListener.onItemDeleteHistoryClick(position);
            }
        });
        holder.itemView.findViewById(R.id.modify_history_food_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historyListOnClickItemListener.onItemModifyHistoryFoodIdClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView food_name;
        TextView canteen_name;
        TextView window_name;
        TextView food_time;


        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //初始化控件
            food_name = itemView.findViewById(R.id.food_name);
            food_time = itemView.findViewById(R.id.food_time);
            canteen_name = itemView.findViewById(R.id.canteen_name);
            window_name = itemView.findViewById(R.id.window_name);
        }
    }
}
