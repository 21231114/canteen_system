package com.example.myapplication.Admin.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Admin.adapter.UserListAdapter;
import com.example.myapplication.Admin.dialog.AddUserActivity;
import com.example.myapplication.Admin.dialog.ModifyRegisterTypeActivity;
import com.example.myapplication.R;
import com.example.myapplication.db.CommentDbHelper;
import com.example.myapplication.db.UserDbHelper;
import com.example.myapplication.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;


public class UsersFragment extends Fragment {
    private RecyclerView myRecycleView;
    private UserListAdapter userListAdapter;
    private View rootView; //rootView就是当前user的界面
    private List<UserInfo> dataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_users, container, false);

        //初始化控件
        myRecycleView = rootView.findViewById(R.id.usersRecyclerView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userListAdapter = new UserListAdapter();
        myRecycleView.setAdapter(userListAdapter);
        loadData();
        rootView.findViewById(R.id.add_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddUserActivity.class);
                startActivity(intent);
            }
        });
        userListAdapter.setUserListOnClickItemListener(new UserListAdapter.UserListOnClickItemListener() {
            @Override
            public void onItemDeleteUserClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                String username = ((TextView) itemView.findViewById(R.id.user_name)).getText().toString();
                UserInfo userInfo = UserDbHelper.getInstance(getActivity()).login(username);
                int user_id = userInfo.getUser_id();
                int row = UserDbHelper.getInstance(getActivity()).deleteUser(username);
                if (row > 0) {
                    Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                    CommentDbHelper.getInstance(getActivity()).deleteCommentBySend_user_id(user_id);
                    CommentDbHelper.getInstance(getActivity()).deleteCommentByReceive_user_id(user_id);
                    loadData();
                } else {
                    Toast.makeText(getActivity(), "删除失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemModifyRegisterTypeClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                String username = ((TextView) itemView.findViewById(R.id.user_name)).getText().toString();
                Intent intent = new Intent(getActivity(), ModifyRegisterTypeActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }

            @Override
            public void onItemInitPasswordClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                String username = ((TextView) itemView.findViewById(R.id.user_name)).getText().toString();
                int row = UserDbHelper.getInstance(getActivity()).initPassword(username);
                if (row > 0) {
                    Toast.makeText(getActivity(), "初始化密码成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "初始化密码失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //设置分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        myRecycleView.addItemDecoration(dividerItemDecoration);
    }

    public void loadData() {
        dataList.clear();
        dataList = UserDbHelper.getInstance(getActivity()).queryUserListData();
        userListAdapter.setDataList(dataList);
    }

    @Override
    public void onResume() {
        loadData();
        super.onResume();
    }

    public View getRecyclerViewItem(RecyclerView recyclerView, int position) {
        if (recyclerView == null || recyclerView.getLayoutManager() == null || recyclerView.getAdapter() == null || recyclerView.getAdapter().getItemCount() <= 0) {
            return null;
        }
        if (position > recyclerView.getAdapter().getItemCount()) {
            return null;
        }
        RecyclerView.ViewHolder viewHolder = recyclerView.getAdapter().createViewHolder(recyclerView, recyclerView.getAdapter().getItemViewType(position));
        recyclerView.getAdapter().onBindViewHolder(viewHolder, position);
        viewHolder.itemView.measure(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        return viewHolder.itemView;
    }
}