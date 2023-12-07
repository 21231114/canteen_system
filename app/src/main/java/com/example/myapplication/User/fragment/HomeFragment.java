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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Admin.ShowFoodActivity;
import com.example.myapplication.Admin.adapter.LeftListAdapter;
import com.example.myapplication.R;
import com.example.myapplication.User.Adapter.CanteenListAdapter;
import com.example.myapplication.User.ShowFoodsActivity;
import com.example.myapplication.User.ShowWindowsActivity;
import com.example.myapplication.db.CanteenDbHelper;
import com.example.myapplication.db.FavorDbHelper;
import com.example.myapplication.db.FoodDbHelper;
import com.example.myapplication.db.WindowDbHelper;
import com.example.myapplication.entity.CanteenInfo;
import com.example.myapplication.entity.FoodInfo;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private View rootView;
    private RecyclerView myRecycleView;//当前展示列表的控件
    private CanteenListAdapter canteenListAdapter;
    private List<String> dataList = new ArrayList<>();
    EditText et_search_content;
    TextView search;
    private int now_user_id;
    private String now_canteen_name = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //初始化控件
        myRecycleView = rootView.findViewById(R.id.canteenRecyclerView);
        search = rootView.findViewById(R.id.search);
        et_search_content = rootView.findViewById(R.id.search_content);
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
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search_content = et_search_content.getText().toString();

                if (CanteenDbHelper.getInstance(getActivity()).isHasCanteen(search_content) != null) {
                    //当前输入的位食堂名
                    now_canteen_name = search_content;
                    loadData();
                    now_canteen_name = null;
                } else if (WindowDbHelper.getInstance(getActivity()).queryWindowListDataByWindow_name(search_content).size() != 0) {
                    //当前输入的是窗口名
                    Intent intent = new Intent(getActivity(), ShowWindowsActivity.class);
                    intent.putExtra("user_id", now_user_id);
                    intent.putExtra("window_name", search_content);
                    startActivity(intent);
                } else if (FoodDbHelper.getInstance(getActivity()).queryFoodListDataByFoodName(search_content).size() != 0) {
                    //当前输入的是食物名
                    Intent intent = new Intent(getActivity(), ShowFoodsActivity.class);
                    intent.putExtra("user_id", now_user_id);
                    intent.putExtra("food_name", search_content);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "搜索不到您想要查找的内容", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

            @Override
            public void onItemAddFavorClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                String now_canteen_name = "";//获取当前要查看的是哪个食堂
                if (itemView != null) {
                    now_canteen_name = ((TextView) (itemView.findViewById(R.id.canteen_name))).getText().toString();
                }
                CanteenInfo canteenInfo = CanteenDbHelper.getInstance(getActivity()).isHasCanteen(now_canteen_name);
                int canteen_id = canteenInfo.getCanteen_id();
                if (FavorDbHelper.getInstance(getActivity()).isHasFavor(now_user_id, canteen_id, 3) != null) {
                    Toast.makeText(getActivity(), "添加失败，已经收藏该食堂", Toast.LENGTH_SHORT).show();
                } else {
                    FavorDbHelper.getInstance(getActivity()).addFavor(now_user_id, canteen_id, 3);
                    Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rootView.findViewById(R.id.person_recommend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShowFoodsActivity.class);
                intent.putExtra("type", "0");//当前位个性化推荐
                intent.putExtra("user_id",now_user_id);
                startActivity(intent);
            }
        });
        rootView.findViewById(R.id.recommend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShowFoodsActivity.class);
                intent.putExtra("type", "1");//当前是总的推荐
                intent.putExtra("user_id",now_user_id);
                startActivity(intent);
            }
        });
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        myRecycleView.addItemDecoration(dividerItemDecoration);
    }

    public void loadData() {
        dataList.clear();
        if (now_canteen_name == null) {
            List<CanteenInfo> canteenInfoList = CanteenDbHelper.getInstance(getActivity()).queryCanteenListData();
            for (int i = 0; i < canteenInfoList.size(); i++) {
                dataList.add(canteenInfoList.get(i).getCanteen_name());
            }
        } else {
            dataList.add(now_canteen_name);
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