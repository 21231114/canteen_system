package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.fragment.RecipesFragment;
import com.example.myapplication.fragment.UsersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private RecipesFragment recipesFragment;
    private UsersFragment usersFragment;
    private BottomNavigationView myBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        myBottomNavigationView = findViewById(R.id.bottomNavigationView);
        //导航栏被选择时的事件
        myBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.users) {
                    //当前管理员选中了用户管理
                    selectedFragment(0);

                } else {
                    //选中了菜品管理
                    selectedFragment(1);
                }
                return true;//表示选定的菜单已经处理成功
            }
        });
        //设置默认选中用户管理栏
        selectedFragment(0);
    }

    //选中哪个页面，显示这个页面的操作
    private void selectedFragment(int position) {
        //需要使用fragment的布局加载器
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        if (position == 0) {
            //此时是用户管理界面
            if (usersFragment == null) {
                //还没有加载过这个布局
                usersFragment = new UsersFragment();
                fragmentTransaction.add(R.id.content,usersFragment);//要把usersFragment这个片段加载到main.xml的contentId对应的容器中
            }
            else{
                fragmentTransaction.show(usersFragment);//因为之前一定加载过，所以系统会自动知道加到哪个容器里
            }
        }
        else{
            //此时是菜品管理界面
            if (recipesFragment == null) {
                //还没有加载过这个布局
                recipesFragment = new RecipesFragment();
                fragmentTransaction.add(R.id.content,recipesFragment);//要把usersFragment这个片段加载到main.xml的contentId对应的容器中
            }
            else{
                fragmentTransaction.show(recipesFragment);//因为之前一定加载过，所以系统会自动知道加到哪个容器里
            }
        }

        //提交是一个异步的过程，将要处理的事务加入队列
        fragmentTransaction.commit();
    }

    //隐藏非空布局
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (recipesFragment != null) {
            fragmentTransaction.hide(recipesFragment);
        }
        if (usersFragment != null) {
            fragmentTransaction.hide(usersFragment);
        }
    }
}