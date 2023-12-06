package com.example.myapplication.User.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.User.Adapter.HistoryListAdapter;
import com.example.myapplication.db.HistoryDbHelper;
import com.example.myapplication.entity.HistoryInfo;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private int now_user_id;
    private RecyclerView myRecycleView;
    private HistoryListAdapter historyListAdapter;
    private View rootView;
    private List<HistoryInfo> dataList = new ArrayList<>();

    public HistoryFragment(int now_user_id) {
        this.now_user_id = now_user_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_history, container, false);

        //初始化控件
        myRecycleView = rootView.findViewById(R.id.historyRecyclerView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        historyListAdapter = new HistoryListAdapter();
        myRecycleView.setAdapter(historyListAdapter);
        loadData();

        //设置分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        myRecycleView.addItemDecoration(dividerItemDecoration);
    }

    public void loadData() {
        dataList.clear();
        dataList = HistoryDbHelper.getInstance(getActivity()).queryHistoryListData();
        historyListAdapter.setDataList(dataList);
    }
}