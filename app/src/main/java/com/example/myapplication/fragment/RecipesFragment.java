package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ShowFoodActivity;
import com.example.myapplication.adapter.LeftListAdapter;
import com.example.myapplication.adapter.RightListAdapter;
import com.example.myapplication.db.CanteenDbHelper;
import com.example.myapplication.db.WindowDbHelper;
import com.example.myapplication.dialog.AddCanteenActivity;
import com.example.myapplication.entity.CanteenInfo;
import com.example.myapplication.entity.WindowInfo;

import java.util.ArrayList;
import java.util.List;

public class RecipesFragment extends Fragment {
    private View rootView; //rootView就是当前recipe的界面
    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;
    private LeftListAdapter myListAdapter;//初始化控件时，也需要该适配器
    private RightListAdapter myRightListAdapter;//初始化控件时，也需要该适配器
    private List<String> leftDataList = new ArrayList<>();//食堂名数据
    private List<String> rightDataList = new ArrayList<>();//窗口名数据
    private String now_canteen_name, now_window_name;

    public String getNow_canteen_name() {
        return now_canteen_name;
    }

    public void setNow_canteen_name(String now_canteen_name) {
        this.now_canteen_name = now_canteen_name;
    }

    public String getNow_window_name() {
        return now_window_name;
    }

    public void setNow_window_name(String now_window_name) {
        this.now_window_name = now_window_name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //该方法将一个布局文件转化为view（为了生成fragment）,第一个参数是要转换的布局，第二个参数是视图的父级容器，第三个参数是否将生成的布局添加到布局容器
        rootView = inflater.inflate(R.layout.fragment_recipes, container, false);


        //初始化控件
        leftRecyclerView = rootView.findViewById(R.id.leftRecyclerView);
        rightRecyclerView = rootView.findViewById(R.id.rightRecyclerView);
        return rootView;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // 在这里编写要执行的任务
            LinearLayoutManager layoutManager = (LinearLayoutManager) leftRecyclerView.getLayoutManager();
            View itemView = layoutManager.getChildAt(0);
            String FirstDiningName = "";
            if (itemView != null) {
                FirstDiningName = ((TextView) (itemView.findViewById(R.id.diningName))).getText().toString();
            } else {
//                Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
            }
            loadRightData(FirstDiningName);
            now_canteen_name = FirstDiningName;
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        leftDataList.add("学一食堂");
//        leftDataList.add("学二食堂");
//        leftDataList.add("学三食堂");
//        leftDataList.add("学四食堂");
//        leftDataList.add("学五食堂");
//        leftDataList.add("学六食堂");
//        leftDataList.add("教工餐厅");
//        leftDataList.add("清真餐厅");
        //从数据库中设置数据
        myListAdapter = new LeftListAdapter(leftDataList);//适配器需要数据接口
        loadData();
        leftRecyclerView.setAdapter(myListAdapter);//为当前左面列表设置适配器
//        WindowDataService.setLeftRecyclerView(leftRecyclerView);
        //右面同理
        myRightListAdapter = new RightListAdapter(rightDataList);
        rightRecyclerView.setAdapter(myRightListAdapter);
        //默认加载的数据是第一条
        /*
        刚刚setAdapter就getChild,UI还没有搞完
         */
        Handler handler = new Handler();
        handler.postDelayed(runnable, 1000);
//        LinearLayoutManager layoutManager = (LinearLayoutManager) leftRecyclerView.getLayoutManager();
//        View itemView = layoutManager.getChildAt(0);
//        String FirstDiningName = "";
//        if (itemView != null) {
//            FirstDiningName = ((TextView) (itemView.findViewById(R.id.diningName))).getText().toString();
//        }
//        else{
//            Toast.makeText(getActivity(), "null", Toast.LENGTH_SHORT).show();
//        }
//        loadRightData(FirstDiningName);
//        now_canteen_name = FirstDiningName;
        //recycleView的点击事件
        myListAdapter.setMyLeftListOnClickItemListener(new LeftListAdapter.LeftListOnClickItemListener() {
            @Override
            public void onItemClick(int position) {
                myListAdapter.setCurrentIndex(position);//当发生点击事件时，需要更改页面
//                LinearLayoutManager layoutManager = (LinearLayoutManager) leftRecyclerView.getLayoutManager();
//                View itemView = layoutManager.getChildAt(position);
                View itemView = getRecyclerViewItem(leftRecyclerView, position);
                String DiningName = "";
                if (itemView != null) {
                    DiningName = ((TextView) (itemView.findViewById(R.id.diningName))).getText().toString();
                }
                loadRightData(DiningName);
                now_canteen_name = DiningName;
            }
        });
        //TODO :右面的显示菜单点击事件
        myRightListAdapter.setMyRightListOnClickItemListener(new RightListAdapter.RightListOnClickItemListener() {
            @Override
            public void onItemMenuClick(int position) {
                View itemView = getRecyclerViewItem(leftRecyclerView, position);
                String windowName = "";//获取当前要查看的是哪个窗口
                if (itemView != null) {
                    windowName = ((TextView) (itemView.findViewById(R.id.windowName))).getText().toString();
                }
                Intent intent = new Intent(getActivity(), ShowFoodActivity.class);
                intent.putExtra("window_name", windowName);
                intent.putExtra("canteen_name", now_canteen_name);
                //传递要查看的窗口信息
                startActivity(intent);

            }

            @Override
            public void onItemModifyWindowClick(int position) {

            }

            @Override
            public void onItemDeleteWindowClick(int position) {

            }
        });

        //食堂添加按钮点击
        rootView.findViewById(R.id.add_canteen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myAddCanteenOnClickItemListener != null) {
                    myAddCanteenOnClickItemListener.AddCanteenOnClick();

                }
            }
        });
        //食堂更改按钮的点击
        rootView.findViewById(R.id.modify_canteen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myAddCanteenOnClickItemListener != null) {
                    myAddCanteenOnClickItemListener.ModifyOnClick();
                }
            }
        });

        rootView.findViewById(R.id.add_window).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myAddCanteenOnClickItemListener != null) {
                    myAddCanteenOnClickItemListener.AddWindowOnClick();
                }
            }
        });
    }

    //右侧数据是根据所选中的食堂定的
    public void loadRightData(String name) {

        now_canteen_name = name;//每当调用这个方法就说明，now_食堂名会发生改变
        rightDataList.clear();
        if (name == null) {
            //如果是空字符串
            myRightListAdapter.setDataList(rightDataList);
            return;
        }
        List<WindowInfo> windowInfoList = WindowDbHelper.getInstance(getActivity()).queryWindowListData(name);

        for (int i = 0; i < windowInfoList.size(); i++) {
            rightDataList.add(windowInfoList.get(i).getWindow_name());
        }
        myRightListAdapter.setDataList(rightDataList);
    }

    //这个是加载左面的数据
    public void loadData() {
        leftDataList.clear();
        List<CanteenInfo> canteenInfoList = CanteenDbHelper.getInstance(getActivity()).queryCanteenListData();
        for (int i = 0; i < canteenInfoList.size(); i++) {
            leftDataList.add(canteenInfoList.get(i).getCanteen_name());
        }
        myListAdapter.setDataList(leftDataList);
    }

    //点击添加有关食堂按钮的逻辑，这里都用Add表示了，因为先用的这个
    public interface AddCanteenOnClickItemListener {
        void AddCanteenOnClick();//点击了添加按钮

        void ModifyOnClick();//点击了更改按钮

        void DeleteOnClick();//点击了删除按钮

        void AddWindowOnClick();//点击了添加窗口的按钮
    }

    public void setMyAddCanteenOnClickItemListener(AddCanteenOnClickItemListener myAddCanteenOnClickItemListener) {
        this.myAddCanteenOnClickItemListener = myAddCanteenOnClickItemListener;
    }

    private AddCanteenOnClickItemListener myAddCanteenOnClickItemListener;

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