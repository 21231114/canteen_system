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
import com.example.myapplication.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyHolder> {
    public void setDataList(List<UserInfo> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void setUserListOnClickItemListener(UserListOnClickItemListener userListOnClickItemListener) {
        this.userListOnClickItemListener = userListOnClickItemListener;
    }

    private List<UserInfo> dataList = new ArrayList<>();
    private UserListOnClickItemListener userListOnClickItemListener;

    public interface UserListOnClickItemListener {
        void onItemDeleteUserClick(int position);

        void onItemModifyRegisterTypeClick(int position);

        void onItemInitPasswordClick(int position);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, null);
        return new UserListAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        String userName = dataList.get(position).getUsername();
        holder.user_name.setText(userName);
        int register_type = dataList.get(position).getRegister_type();
        holder.register_type.setText(Integer.toString(register_type));

        holder.itemView.findViewById(R.id.delete_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userListOnClickItemListener != null)
                    userListOnClickItemListener.onItemDeleteUserClick(position);
            }
        });
        holder.itemView.findViewById(R.id.modify_register_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userListOnClickItemListener != null)
                    userListOnClickItemListener.onItemModifyRegisterTypeClick(position);
            }
        });
        holder.itemView.findViewById(R.id.init_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userListOnClickItemListener != null)
                    userListOnClickItemListener.onItemInitPasswordClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView user_name;
        TextView register_type;
        Button delete_user;
        Button modify_register_type;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //初始化控件
            user_name = itemView.findViewById(R.id.user_name);
            register_type = itemView.findViewById(R.id.register_type);
            delete_user = itemView.findViewById(R.id.delete_user);
            modify_register_type = itemView.findViewById(R.id.modify_register_type);
        }
    }
}
