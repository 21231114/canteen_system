package com.example.myapplication.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.Admin.dialog.AddWindowActivity;
import com.example.myapplication.Admin.dialog.DeleteCanteenActivity;
import com.example.myapplication.Admin.dialog.ModifyCanteenActivity;
import com.example.myapplication.Admin.fragment.RecipesFragment;
import com.example.myapplication.Admin.fragment.UsersFragment;
import com.example.myapplication.Admin.dialog.AddCanteenActivity;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private RecipesFragment recipesFragment;
    private UsersFragment usersFragment;
    private BottomNavigationView myBottomNavigationView;
    private boolean isEqual = false;
    //private String now_show_canteen_name = "";

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
                fragmentTransaction.add(R.id.content, usersFragment);//要把usersFragment这个片段加载到main.xml的contentId对应的容器中
            } else {
                fragmentTransaction.show(usersFragment);//因为之前一定加载过，所以系统会自动知道加到哪个容器里
            }
        } else {
            //此时是菜品管理界面
            if (recipesFragment == null) {
                //还没有加载过这个布局
                recipesFragment = new RecipesFragment();
                //一定要在这里设置监听，否则会取null
                recipesFragment.setMyAddCanteenOnClickItemListener(new RecipesFragment.AddCanteenOnClickItemListener() {
                    @Override
                    public void AddCanteenOnClick() {
                        //需要在跳转到对话时，传入recipeFragment
                        Intent intent = new Intent(MainActivity.this, AddCanteenActivity.class);
                        //intent类型传递对象时,一定要实现一个Serializable,将对象转换为字节流
                        //踩过的坑，fragment不能这么传，因为不能序列化
//                        intent.putExtra("recipeFragmentId", );
                        startActivityForResult(intent, 3);//1是请求码，用于确定是要启动哪个活动
                        //  recipesFragment.loadData();
                    }

                    @Override
                    public void ModifyOnClick() {
                        Intent intent = new Intent(MainActivity.this, ModifyCanteenActivity.class);
                        startActivityForResult(intent, 1);//1是请求码，用于确定是要启动哪个活动
                    }

                    @Override
                    public void DeleteOnClick() {
                        Intent intent = new Intent(MainActivity.this, DeleteCanteenActivity.class);
                        startActivityForResult(intent, 4);//1是请求码，用于确定是要启动哪个活动
                    }

                    @Override
                    public void AddWindowOnClick() {
                        Intent intent = new Intent(MainActivity.this, AddWindowActivity.class);
                        startActivityForResult(intent, 2);//2是请求码，用于确定是要启动哪个活动
                    }
                });
                fragmentTransaction.add(R.id.content, recipesFragment);//要把usersFragment这个片段加载到main.xml的contentId对应的容器中
            } else {
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
    /*
    当从其其他活动返回到主活动,需要更新数据
    需要通过钩子函数去实时更新数据
    需要注意因为调用的是dialog类型的activity
    所以不会走onRestart
     */

    @Override
    protected void onResume() {
        if (recipesFragment != null) {
            recipesFragment.loadData();
            if (isEqual) {
                recipesFragment.getMyListAdapter().setCurrentIndex(0);//当前索引食堂被删除，跳回第一个
                View itemView = recipesFragment.getRecyclerViewItem(recipesFragment.getLeftRecyclerView(), 0);
                if(itemView!=null) {
                    recipesFragment.setNow_canteen_name(((TextView) (itemView.findViewById(R.id.diningName))).getText().toString());
                }
                else{
                    recipesFragment.setNow_canteen_name("");
                }
                isEqual = false;
            }
//            if (recipesFragment.getNow_canteen_name() != null) {
                // Toast.makeText(this, recipesFragment.getNow_canteen_name(), Toast.LENGTH_SHORT).show();
                recipesFragment.loadRightData(recipesFragment.getNow_canteen_name());
            //}
        }
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    //1是修改食堂名活动返回
                    //Todo:
                    if (Objects.equals(recipesFragment.getNow_canteen_name(), data.getStringExtra("before_canteen_name")))
                        recipesFragment.setNow_canteen_name(data.getStringExtra("after_canteen_name"));
                    // now_show_canteen_name = data.getStringExtra("after_canteen_name");
                    if (recipesFragment != null) {
                        //recipesFragment.loadData();
                        //recipesFragment.loadRightData(after_canteen_name);
                    }
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    //2是添加窗口活动返回
                    //now_show_canteen_name = data.getStringExtra("canteen_name");
                    if (recipesFragment != null) {
                        // recipesFragment.loadData();
                        // recipesFragment.loadRightData(recipesFragment.getNow_canteen_name());
                    }
                }
                break;
            case 3:
                if (resultCode == RESULT_OK) {
                    //3是添加食堂活动返回
                    String canteen_name = data.getStringExtra("canteen_name");
                    if (Objects.equals(recipesFragment.getNow_canteen_name(), "")) {
                        recipesFragment.setNow_canteen_name(canteen_name);
                    }
                }
                break;
            case 4:
                if (resultCode == RESULT_OK) {
                    //4是删除食堂活动返回
                    String canteen_name = data.getStringExtra("canteen_name");//获取删除了那个食堂
                    isEqual = Objects.equals(recipesFragment.getNow_canteen_name(), canteen_name);
                }
                break;
            default:
        }

    }
}