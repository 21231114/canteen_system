package com.example.myapplication.User.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Admin.adapter.LeftListAdapter;
import com.example.myapplication.R;
import com.example.myapplication.User.Adapter.CanteenListAdapter;
import com.example.myapplication.db.CanteenDbHelper;
import com.example.myapplication.entity.CanteenInfo;
import com.example.myapplication.entity.FoodInfo;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private View rootView;
    private RecyclerView myRecycleView;//当前展示列表的控件
    private CanteenListAdapter canteenListAdapter;
    private List<String> dataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //初始化控件
        myRecycleView = rootView.findViewById(R.id.canteenRecyclerView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        canteenListAdapter = new CanteenListAdapter(dataList);//适配器需要数据接口
        myRecycleView.setAdapter(canteenListAdapter);//一定一定一定记得将视图与适配器绑定
        loadData();
    }

    public void loadData() {
        dataList.clear();
        List<CanteenInfo> canteenInfoList = CanteenDbHelper.getInstance(getActivity()).queryCanteenListData();
        for (int i = 0; i < canteenInfoList.size(); i++) {
            dataList.add(canteenInfoList.get(i).getCanteen_name());
        }
        canteenListAdapter.setDataList(dataList);
    }
}