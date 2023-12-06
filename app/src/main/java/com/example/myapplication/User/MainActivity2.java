package com.example.myapplication.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.Admin.adapter.FoodListAdapter;
import com.example.myapplication.Admin.fragment.UsersFragment;
import com.example.myapplication.R;
import com.example.myapplication.User.Adapter.CanteenListAdapter;
import com.example.myapplication.User.fragment.FavorFragment;
import com.example.myapplication.User.fragment.HistoryFragment;
import com.example.myapplication.User.fragment.HomeFragment;
import com.example.myapplication.User.fragment.UserCenterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {
    private HomeFragment homeFragment;
    private HistoryFragment historyFragment;
    private FavorFragment favorFragment;
    private UserCenterFragment userCenterFragment;
    private BottomNavigationView bottomNavigationView;
    private int now_user_id;//知道当前用户是谁


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        Intent intent = getIntent();
        now_user_id = intent.getIntExtra("user_id",0);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    selectedFragment(0);
                } else if (item.getItemId() == R.id.history) {
                    selectedFragment(2);
                } else if (item.getItemId() == R.id.favor) {
                    selectedFragment(1);
                } else if (item.getItemId() == R.id.user_center) {
                    selectedFragment(3);
                }
                return true;
            }
        });
        //默认首页选中
        selectedFragment(0);
    }

    private void selectedFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        if (position == 0) {
            //此时是主页
            if (homeFragment == null) {
                //还没有加载过这个布局
                homeFragment = new HomeFragment(now_user_id);
                fragmentTransaction.add(R.id.content, homeFragment);//要把usersFragment这个片段加载到main.xml的contentId对应的容器中
            } else {
                homeFragment.loadData();//重新加载数据
                fragmentTransaction.show(homeFragment);//因为之前一定加载过，所以系统会自动知道加到哪个容器里
            }
        } else if (position == 1) {
            //当前是收藏界面
            if (favorFragment == null) {
                //还没有加载过这个布局
                favorFragment = new FavorFragment(now_user_id);
                fragmentTransaction.add(R.id.content, favorFragment);
            } else {
                favorFragment.loadData();
                fragmentTransaction.show(favorFragment);
            }
        } else if (position == 2) {
            //当前是历史记录界面
            if (historyFragment == null) {
                //还没有加载过这个布局
                historyFragment = new HistoryFragment(now_user_id);
                fragmentTransaction.add(R.id.content, historyFragment);
            } else {
                fragmentTransaction.show(historyFragment);
            }
        } else {
            if (userCenterFragment == null) {
                //还没有加载过这个布局
                userCenterFragment = new UserCenterFragment(now_user_id);
                fragmentTransaction.add(R.id.content, userCenterFragment);
            } else {
                fragmentTransaction.show(userCenterFragment);
            }
        }
        //提交是一个异步的过程，将要处理的事务加入队列
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (historyFragment != null) {
            fragmentTransaction.hide(historyFragment);
        }
        if (favorFragment != null) {
            fragmentTransaction.hide(favorFragment);
        }
        if (userCenterFragment != null) {
            fragmentTransaction.hide(userCenterFragment);
        }
    }
}