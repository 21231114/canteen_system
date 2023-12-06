package com.example.myapplication.User.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.User.Adapter.CanteenListAdapter;
import com.example.myapplication.User.Adapter.FavorListAdapter;
import com.example.myapplication.User.ShowWindowsActivity;
import com.example.myapplication.db.CanteenDbHelper;
import com.example.myapplication.db.FavorDbHelper;
import com.example.myapplication.db.FoodDbHelper;
import com.example.myapplication.db.WindowDbHelper;
import com.example.myapplication.entity.CanteenInfo;
import com.example.myapplication.entity.FavorInfo;

import java.util.ArrayList;
import java.util.List;


public class FavorFragment extends Fragment {
    private int now_user_id;
    private View rootView;
    private RecyclerView myRecycleView;//当前展示列表的控件
    private FavorListAdapter favorListAdapter;
    private List<FavorInfo> dataList = new ArrayList<>();

    public FavorFragment(int now_user_id) {
        this.now_user_id = now_user_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_favor, container, false);

        //初始化控件
        myRecycleView = rootView.findViewById(R.id.favorRecyclerView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        favorListAdapter = new FavorListAdapter();//适配器需要数据接口
        myRecycleView.setAdapter(favorListAdapter);//一定一定一定记得将视图与适配器绑定
        loadData();
        favorListAdapter.setFavorListOnClickItemListener(new FavorListAdapter.FavorListOnClickItemListener() {
            @Override
            public void onItemRemoveClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                int food_id = 0;
                int type = 0;
                if (itemView != null) {
                    food_id = ((FavorInfo) itemView.getTag()).getFood_id();
                    type = ((FavorInfo) itemView.getTag()).getType();
//                    Toast.makeText(getActivity(), Integer.toString(food_id), Toast.LENGTH_SHORT).show();
                }
                int row = FavorDbHelper.getInstance(getActivity()).deleteFavor(now_user_id, food_id, type);
                if (row > 0) {
                    Toast.makeText(getActivity(), "移除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "移除失败", Toast.LENGTH_SHORT).show();
                }
                loadData();
            }
        });
    }

    public void loadData() {
        dataList.clear();
        dataList = FavorDbHelper.getInstance(getActivity()).queryFavorListData(now_user_id);
        for (int i = 0; i < dataList.size(); i++) {
            int food_id = dataList.get(i).getFood_id();
            int type = dataList.get(i).getType();
            if (type == 1) {
                //当前收藏的是菜
                if (FoodDbHelper.getInstance(getActivity()).isHasFoodByFoodId(food_id) == null) {
                    //当前收藏的食物已经被删除了,因为是id所以保证后添加的食物不会有重复的
                    FavorDbHelper.getInstance(getActivity()).deleteFavorByFoodId(food_id, type);
                    dataList.remove(i);
                    i--;
                }
            } else if (type == 2) {
                //当前收藏的是窗口
                if (WindowDbHelper.getInstance(getActivity()).isHasWindowById(food_id) == null) {
                    //当前收藏的食物已经被删除了,因为是id所以保证后添加的食物不会有重复的
                    FavorDbHelper.getInstance(getActivity()).deleteFavorByFoodId(food_id, type);
                    dataList.remove(i);
                    i--;
                }
            } else if (type == 3) {
                if (CanteenDbHelper.getInstance(getActivity()).isHasCanteenById(food_id) == null) {
                    FavorDbHelper.getInstance(getActivity()).deleteFavorByFoodId(food_id, type);
                    dataList.remove(i);
                    i--;
                }
                //当前收藏的是食堂
            }
        }
        favorListAdapter.setDataList(dataList);
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