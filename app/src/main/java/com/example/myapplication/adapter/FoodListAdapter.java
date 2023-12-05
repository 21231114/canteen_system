package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.entity.FoodInfo;

import java.util.ArrayList;
import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.MyHolder> {
    private List<FoodInfo> dataList = new ArrayList<>();//存储食物信息

    public void setDataList(List<FoodInfo> dataList) {
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
        String foodName = dataList.get(position).getFood_name();
        String food_type = Integer.toString(dataList.get(position).getFood_type());
        holder.food_name.setText(foodName);
        holder.food_type.setText(food_type);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView food_name;
        TextView food_type;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //初始化控件
            food_name = itemView.findViewById(R.id.food_name);
            food_type = itemView.findViewById(R.id.food_type);
            // menu = itemView.findViewById(R.id.menu);
        }
    }
}
