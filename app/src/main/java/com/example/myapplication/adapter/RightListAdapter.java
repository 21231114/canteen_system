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

public class RightListAdapter extends RecyclerView.Adapter<RightListAdapter.MyHolder> {
    public RightListAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    private List<String> dataList = new ArrayList<>();//存储窗口信息

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView window_name;
        Button menu;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //初始化控件
            window_name = itemView.findViewById(R.id.windowName);
            menu = itemView.findViewById(R.id.menu);
        }
    }

    @NonNull
    @Override
    public RightListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate将布局文件转换为View对象
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_right_list_item, null);
        return new RightListAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RightListAdapter.MyHolder holder, int position) {
        String windowName = dataList.get(position);
        holder.window_name.setText(windowName);//这步就把数据绑定到MyHolder了

//        //也可绑定点击事件,此处是分类的点击事件
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (myLeftListOnClickItemListener != null) {
//                    myLeftListOnClickItemListener.onItemClick(position);//回调
//                }
//            }
//        });
//        if(currentIndex == position){
//            holder.itemView.setBackgroundResource(R.drawable.type_selcetor_bg);
//            //如果当前item被选中了，设置
//        }
//        else {
//            holder.itemView.setBackgroundResource(R.drawable.type_selector_normal_bg);
//        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
