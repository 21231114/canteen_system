package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.MyHolder> {
    private List<String> dataList = new ArrayList<>();//存储食物信息

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list_item, null);
        return new FoodListAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String foodName = dataList.get(position);
        holder.food_name.setText(foodName);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView food_name;
        //Button menu;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //初始化控件
            food_name = itemView.findViewById(R.id.food_name);
            // menu = itemView.findViewById(R.id.menu);
        }
    }
}
