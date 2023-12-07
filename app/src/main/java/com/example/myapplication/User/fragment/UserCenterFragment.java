package com.example.myapplication.User.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.db.UserDbHelper;
import com.example.myapplication.entity.UserInfo;

public class UserCenterFragment extends Fragment {
    private int now_user_id;
    private View rootView;
    private TextView tv_username;

    public UserCenterFragment(int now_user_id) {
        this.now_user_id = now_user_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user_center, container, false);
        //初始化控件
        tv_username = rootView.findViewById(R.id.tv_username);
        return rootView;
    }

    public void setAddUserCenterOnClickItemListener(AddUserCenterOnClickItemListener addUserCenterOnClickItemListener) {
        this.addUserCenterOnClickItemListener = addUserCenterOnClickItemListener;
    }

    private AddUserCenterOnClickItemListener addUserCenterOnClickItemListener;

    public interface AddUserCenterOnClickItemListener {
        void AddToolBar();//点击了退出登录按钮

        void ModifyPassword();

        void ModifyUsername();

        void DeleteUser();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rootView.findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserCenterOnClickItemListener.AddToolBar();
            }
        });
        UserInfo userInfo = UserDbHelper.getInstance(getActivity()).findUserById(now_user_id);
        tv_username.setText(userInfo.getUsername());
        rootView.findViewById(R.id.modify_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserCenterOnClickItemListener.ModifyPassword();
            }
        });
        rootView.findViewById(R.id.modify_username).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserCenterOnClickItemListener.ModifyUsername();
            }
        });
        rootView.findViewById(R.id.delete_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserCenterOnClickItemListener.DeleteUser();
            }
        });
    }
}