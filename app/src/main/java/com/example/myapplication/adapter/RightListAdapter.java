package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
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

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
        notifyDataSetChanged();
    }

    private List<String> dataList = new ArrayList<>();//存储窗口信息
    private RightListOnClickItemListener myRightListOnClickItemListener;
    private  int currentIndex = 0;//哪一个项被选中了，需要由recipesFragment告诉adapter
    public interface RightListOnClickItemListener {
        void onItemMenuClick(int position);//接收点某个具体项菜单的操作函数
        void onItemModifyWindowClick(int position);//接收点某个具体项修改窗口的操作函数
        void onItemDeleteWindowClick(int position);//接收点某个具体项删除窗口的操作函数
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void setMyRightListOnClickItemListener(RightListAdapter.RightListOnClickItemListener myRightListOnClickItemListener) {
        this.myRightListOnClickItemListener = myRightListOnClickItemListener;
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView window_name;
        Button menu;
        Button modify_window;
        Button delete_window;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //初始化控件
            window_name = itemView.findViewById(R.id.windowName);
            menu = itemView.findViewById(R.id.menu);
            modify_window =itemView.findViewById(R.id.modify_window);
            delete_window =itemView.findViewById(R.id.delete_window);
        }
    }

    @NonNull
    @Override
    public RightListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate将布局文件转换为View对象
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_right_list_item, null);
        return new RightListAdapter.MyHolder(view);
    }

    //这里的position其实是一个遍历的过程
    @Override
    public void onBindViewHolder(@NonNull RightListAdapter.MyHolder holder, @SuppressLint("RecyclerView") int position) {
        String windowName = dataList.get(position);
        holder.window_name.setText(windowName);//这步就把数据绑定到MyHolder了

       //也可绑定点击事件,此处是查看窗口对应菜单的点击事件
        holder.itemView.findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myRightListOnClickItemListener != null) {
                    myRightListOnClickItemListener.onItemMenuClick(position);//回调
                }
            }
        });
        holder.itemView.findViewById(R.id.modify_window).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myRightListOnClickItemListener != null) {
                    myRightListOnClickItemListener.onItemModifyWindowClick(position);//回调
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
