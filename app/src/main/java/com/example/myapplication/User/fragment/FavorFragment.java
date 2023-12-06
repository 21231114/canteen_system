package com.example.myapplication.User.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.User.Adapter.CanteenListAdapter;
import com.example.myapplication.User.Adapter.FavorListAdapter;
import com.example.myapplication.db.CanteenDbHelper;
import com.example.myapplication.db.FavorDbHelper;
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
    }

    public void loadData() {
        dataList.clear();
        dataList = FavorDbHelper.getInstance(getActivity()).queryFavorListData(now_user_id);
        //Toast.makeText(getActivity(), Integer.toString(dataList.size()), Toast.LENGTH_SHORT).show();
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