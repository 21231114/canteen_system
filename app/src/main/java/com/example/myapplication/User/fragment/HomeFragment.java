package com.example.myapplication.User.fragment;

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

import com.example.myapplication.Admin.ShowFoodActivity;
import com.example.myapplication.Admin.adapter.LeftListAdapter;
import com.example.myapplication.R;
import com.example.myapplication.User.Adapter.CanteenListAdapter;
import com.example.myapplication.User.ShowWindowsActivity;
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
    private int now_user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //初始化控件
        myRecycleView = rootView.findViewById(R.id.canteenRecyclerView);
        return rootView;
    }

    public HomeFragment(int now_user_id) {
        this.now_user_id = now_user_id;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        canteenListAdapter = new CanteenListAdapter(dataList);//适配器需要数据接口
        myRecycleView.setAdapter(canteenListAdapter);//一定一定一定记得将视图与适配器绑定
        loadData();

        canteenListAdapter.setCanteenListOnClickItemListener(new CanteenListAdapter.CanteenListOnClickItemListener() {
            @Override
            public void onItemEnterCanteenClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                String now_canteen_name = "";//获取当前要查看的是哪个食堂
                if (itemView != null) {
                    now_canteen_name = ((TextView) (itemView.findViewById(R.id.canteen_name))).getText().toString();
                }
                Intent intent = new Intent(getActivity(), ShowWindowsActivity.class);
                intent.putExtra("canteen_name", now_canteen_name);
                intent.putExtra("user_id", now_user_id);
                //传递要查看的窗口信息
                startActivity(intent);
            }
        });
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        myRecycleView.addItemDecoration(dividerItemDecoration);
    }

    public void loadData() {
        dataList.clear();
        List<CanteenInfo> canteenInfoList = CanteenDbHelper.getInstance(getActivity()).queryCanteenListData();
        for (int i = 0; i < canteenInfoList.size(); i++) {
            dataList.add(canteenInfoList.get(i).getCanteen_name());
        }
        canteenListAdapter.setDataList(dataList);
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