package com.example.myapplication.Admin.adapter;

import android.annotation.SuppressLint;
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
    private FoodListAdapter.FoodListOnClickItemListener myFoodListOnClickItemListener;

    public interface FoodListOnClickItemListener {
        void onItemDeleteFoodClick(int position);//接收点某个具体项菜单的操作函数

        void onItemMOdifyFoodTypeClick(int position);//接收点某个具体项菜单的操作函数
    }

    public void setMyFoodListOnClickItemListener(FoodListOnClickItemListener myFoodListOnClickItemListener) {
        this.myFoodListOnClickItemListener = myFoodListOnClickItemListener;
    }

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
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        String foodName = dataList.get(position).getFood_name();
        int food_type = dataList.get(position).getFood_type();
        holder.food_name.setText(foodName);
        if (food_type == 0)
            holder.food_type.setText("饮品");
        else if (food_type == 1) {
            holder.food_type.setText("早饭");
        } else {
            holder.food_type.setText("正餐");
        }
        holder.itemView.findViewById(R.id.delete_food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这个按钮是删除菜品
                myFoodListOnClickItemListener.onItemDeleteFoodClick(position);
            }
        });
        holder.itemView.findViewById(R.id.modify_food_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这个按钮是删除菜品
                myFoodListOnClickItemListener.onItemMOdifyFoodTypeClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView food_name;
        TextView food_type;
        Button delete_food;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //初始化控件
            food_name = itemView.findViewById(R.id.food_name);
            food_type = itemView.findViewById(R.id.food_type);
            delete_food = itemView.findViewById(R.id.delete_food);
        }
    }
}
