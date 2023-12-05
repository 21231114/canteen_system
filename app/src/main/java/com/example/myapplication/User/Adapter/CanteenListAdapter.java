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

public class CanteenListAdapter extends RecyclerView.Adapter<CanteenListAdapter.MyHolder> {
    private List<String> dataList = new ArrayList<>();//存储食堂

    public CanteenListAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    private CanteenListOnClickItemListener canteenListOnClickItemListener;

    public void setCanteenListOnClickItemListener(CanteenListOnClickItemListener canteenListOnClickItemListener) {
        this.canteenListOnClickItemListener = canteenListOnClickItemListener;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public interface CanteenListOnClickItemListener {
        void onItemEnterCanteenClick(int position);//接收点某个具体项菜单的操作函数

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.canteen_list, null);
        return new CanteenListAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        String canteenName = dataList.get(position);
        holder.canteen_name.setText(canteenName);
        holder.itemView.findViewById(R.id.enter_canteen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canteenListOnClickItemListener.onItemEnterCanteenClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView canteen_name;
        Button enter_canteen;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //初始化控件
            canteen_name = itemView.findViewById(R.id.canteen_name);
            enter_canteen = itemView.findViewById(R.id.enter_canteen);
        }
    }
}
