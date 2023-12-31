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
import com.example.myapplication.entity.WindowInfo;

import java.util.ArrayList;
import java.util.List;

public class WindowListAdapter extends RecyclerView.Adapter<WindowListAdapter.MyHolder> {
    private List<WindowInfo> dataList = new ArrayList<>();//存储食堂
    private boolean isShow = false;

    public void setShow(boolean show) {
        isShow = show;
    }

    public WindowListAdapter(List<WindowInfo> dataList) {
        this.dataList = dataList;
    }

    private WindowListAdapter.WindowListOnClickItemListener windowListOnClickItemListener;

    public void setWindowListOnClickItemListener(WindowListAdapter.WindowListOnClickItemListener windowListOnClickItemListener) {
        this.windowListOnClickItemListener = windowListOnClickItemListener;
    }

    public void setDataList(List<WindowInfo> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public interface WindowListOnClickItemListener {
        void onItemEnterWindowClick(int position);//接收点某个具体项菜单的操作函数

        void onItemAddFavorClick(int position);//接收点某个具体项菜单的操作函数
    }

    @NonNull
    @Override
    public WindowListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.window_list_item, null);
        return new WindowListAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WindowListAdapter.MyHolder holder, @SuppressLint("RecyclerView") int position) {
        WindowInfo windowInfo = dataList.get(position);
        holder.itemView.setTag(windowInfo);
        if (!isShow) {
            holder.window_name.setText(windowInfo.getWindow_name());
        } else {
            holder.window_name.setText(windowInfo.getCanteen_name() + " " + windowInfo.getWindow_name());
        }
        holder.itemView.findViewById(R.id.enter_window).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (windowListOnClickItemListener != null) {
                    windowListOnClickItemListener.onItemEnterWindowClick(position);
                }
            }
        });
        holder.itemView.findViewById(R.id.add_favor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (windowListOnClickItemListener != null) {
                    windowListOnClickItemListener.onItemAddFavorClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView window_name;
        Button enter_window;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //初始化控件
            window_name = itemView.findViewById(R.id.window_name);
            enter_window = itemView.findViewById(R.id.enter_window);
        }
    }
}
