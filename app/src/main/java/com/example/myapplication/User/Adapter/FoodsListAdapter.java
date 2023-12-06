package com.example.myapplication.User.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Admin.adapter.FoodListAdapter;
import com.example.myapplication.R;
import com.example.myapplication.entity.FoodInfo;

import java.util.ArrayList;
import java.util.List;

public class FoodsListAdapter extends RecyclerView.Adapter<FoodsListAdapter.MyHolder> {
    private List<FoodInfo> dataList = new ArrayList<>();//存储食物信息
    private FoodsListAdapter.FoodsListOnClickItemListener myFoodsListOnClickItemListener;

    public void setDataList(List<FoodInfo> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public interface FoodsListOnClickItemListener {
        void onItemEnterCommentClick(int position);

        void onItemAddOrderClick(int position);

        void onItemAddFavorClick(int position);
    }

    public void setMyFoodsListOnClickItemListener(FoodsListAdapter.FoodsListOnClickItemListener myFoodsListOnClickItemListener) {
        this.myFoodsListOnClickItemListener = myFoodsListOnClickItemListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foods_list_item, null);
        return new FoodsListAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        String foodName = dataList.get(position).getFood_name();
        int food_type = dataList.get(position).getFood_type();
        String food_price = dataList.get(position).getFood_price();
        String food_cnt = dataList.get(position).getFood_cnt();
        holder.food_name.setText(foodName);
        if (food_type == 0)
            holder.food_type.setText("饮品");
        else if (food_type == 1) {
            holder.food_type.setText("早饭");
        } else {
            holder.food_type.setText("正餐");
        }
        food_price = food_price + "元";
        holder.food_price.setText(food_price);
        food_cnt = "余" + food_cnt + "份";
        holder.food_cnt.setText(food_cnt);

        holder.itemView.findViewById(R.id.enter_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFoodsListOnClickItemListener.onItemEnterCommentClick(position);
            }
        });
        holder.itemView.findViewById(R.id.add_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFoodsListOnClickItemListener.onItemAddOrderClick(position);
            }
        });
        holder.itemView.findViewById(R.id.add_favor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这个按钮是删除菜品
                myFoodsListOnClickItemListener.onItemAddFavorClick(position);
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
        Button enter_comment;
        Button add_order;
        Button add_favor;
        TextView food_price;
        TextView food_cnt;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //初始化控件
            food_name = itemView.findViewById(R.id.food_name);
            food_type = itemView.findViewById(R.id.food_type);
            food_price = itemView.findViewById(R.id.food_price);
            food_cnt = itemView.findViewById(R.id.food_cnt);
            enter_comment = itemView.findViewById(R.id.enter_comment);
            add_order = itemView.findViewById(R.id.add_order);
            add_favor = itemView.findViewById(R.id.add_favor);
        }
    }
}
