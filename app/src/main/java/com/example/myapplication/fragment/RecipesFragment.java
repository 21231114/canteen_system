package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.LeftListAdapter;
import com.example.myapplication.adapter.RightListAdapter;
import com.example.myapplication.db.CanteenDbHelper;
import com.example.myapplication.db.WindowDbHelper;
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
        LinearLayoutManager layoutManager = (LinearLayoutManager) leftRecyclerView.getLayoutManager();
        View itemView = layoutManager.getChildAt(0);
        String FirstDiningName = "";
        if (itemView != null) {
            FirstDiningName = ((TextView) (itemView.findViewById(R.id.diningName))).getText().toString();
            FirstDiningName="1";
        }
        loadRightData(FirstDiningName);

        //recycleView的点击事件
        myListAdapter.setMyLeftListOnClickItemListener(new LeftListAdapter.LeftListOnClickItemListener() {
            @Override
            public void onItemClick(int position) {
                myListAdapter.setCurrentIndex(position);//当发生点击事件时，需要更改页面
                LinearLayoutManager layoutManager = (LinearLayoutManager) leftRecyclerView.getLayoutManager();
                View itemView = layoutManager.getChildAt(position);
                String DiningName = "";
                if (itemView != null) {
                    DiningName = ((TextView) (itemView.findViewById(R.id.diningName))).getText().toString();
                }
                loadRightData(DiningName);
            }
        });

        //食堂添加按钮点击
        rootView.findViewById(R.id.add_canteen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myAddCanteenOnClickItemListener != null) {
                    myAddCanteenOnClickItemListener.AddCanteenOnClick();
//                    loadData();
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
        rightDataList.clear();
        if(name==null){
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


}