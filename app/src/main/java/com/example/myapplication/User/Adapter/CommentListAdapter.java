package com.example.myapplication.User.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.db.UserDbHelper;
import com.example.myapplication.entity.CommentInfo;

import java.util.ArrayList;
import java.util.List;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.MyHolder> {
    private List<CommentInfo> dataList = new ArrayList<>();//存储食堂

    public void setDataList(List<CommentInfo> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void setCommentListOnClickItemListener(CommentListOnClickItemListener commentListOnClickItemListener) {
        this.commentListOnClickItemListener = commentListOnClickItemListener;
    }

    private CommentListOnClickItemListener commentListOnClickItemListener;

    public interface CommentListOnClickItemListener {
        void onItemReplyClick(int position);


    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_item, null);
        return new CommentListAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        CommentInfo commentInfo = dataList.get(position);
        int send_user_id = commentInfo.getSend_user_id();
        int receive_user_id = commentInfo.getReceive_user_id();
        String content = commentInfo.getContent();
        String send_user_name = UserDbHelper.getInstance(holder.itemView.getContext()).findUserById(send_user_id).getUsername();
        if (receive_user_id != 0) {
            //当前评论是一条回复
            String receive_user_name = UserDbHelper.getInstance(holder.itemView.getContext()).findUserById(receive_user_id).getUsername();
            holder.comment_info.setText(send_user_name + "回复了" + receive_user_name);
        } else {
            holder.comment_info.setText(send_user_name + "评论了");
        }
        holder.content.setText(content);
        holder.itemView.setTag(commentInfo);
        holder.itemView.findViewById(R.id.reply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentListOnClickItemListener.onItemReplyClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView comment_info;
        TextView content;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //初始化控件
            comment_info = itemView.findViewById(R.id.comment_info);
            content = itemView.findViewById(R.id.content);
        }
    }
}
