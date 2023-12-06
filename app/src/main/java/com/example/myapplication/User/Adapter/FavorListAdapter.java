package com.example.myapplication.User.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.db.FoodDbHelper;
import com.example.myapplication.entity.FavorInfo;
import com.example.myapplication.entity.FoodInfo;

import java.util.ArrayList;
import java.util.List;

public class FavorListAdapter extends RecyclerView.Adapter<FavorListAdapter.MyHolder> {
    public void setDataList(List<FavorInfo> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void setFavorListOnClickItemListener(FavorListOnClickItemListener favorListOnClickItemListener) {
        this.favorListOnClickItemListener = favorListOnClickItemListener;
    }

    private FavorListOnClickItemListener favorListOnClickItemListener;
    private List<FavorInfo> dataList = new ArrayList<>();

    public interface FavorListOnClickItemListener {
        void onItemRemoveClick(int position);

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favor_list_item, null);
        return new FavorListAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        int food_id = dataList.get(position).getFood_id();
        FoodInfo foodInfo = FoodDbHelper.getInstance(holder.itemView.getContext()).isHasFoodByFoodId(food_id);
        holder.food_name.setText(foodInfo.getFood_name());
        holder.canteen_name.setText(foodInfo.getCanteen_name());
        holder.window_name.setText(foodInfo.getWindow_name());
       // Toast.makeText(holder.itemView.getContext(), foodInfo.getFood_name(), Toast.LENGTH_SHORT).show();
        holder.itemView.findViewById(R.id.remove_favor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favorListOnClickItemListener.onItemRemoveClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView food_name;
        Button remove_favor;
        TextView canteen_name;
        TextView window_name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //初始化控件
            food_name = itemView.findViewById(R.id.food_name);
            remove_favor = itemView.findViewById(R.id.remove_favor);
            canteen_name = itemView.findViewById(R.id.canteen_name);
            window_name = itemView.findViewById(R.id.window_name);
        }
    }
}
