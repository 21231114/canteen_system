package com.example.myapplication.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.Admin.adapter.FoodListAdapter;
import com.example.myapplication.Admin.fragment.UsersFragment;
import com.example.myapplication.R;
import com.example.myapplication.User.Adapter.CanteenListAdapter;
import com.example.myapplication.User.dialog.ModifyPasswordActivity;
import com.example.myapplication.User.dialog.ModifyUsernameActivity;
import com.example.myapplication.User.fragment.FavorFragment;
import com.example.myapplication.User.fragment.HistoryFragment;
import com.example.myapplication.User.fragment.HomeFragment;
import com.example.myapplication.User.fragment.UserCenterFragment;
import com.example.myapplication.db.CommentDbHelper;
import com.example.myapplication.db.RecommendDbHelper;
import com.example.myapplication.db.UserDbHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

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
        now_user_id = intent.getIntExtra("user_id", 0);
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
                historyFragment.loadData();
                fragmentTransaction.show(historyFragment);
            }
        } else {
            if (userCenterFragment == null) {
                //还没有加载过这个布局
                userCenterFragment = new UserCenterFragment(now_user_id);
                userCenterFragment.setAddUserCenterOnClickItemListener(new UserCenterFragment.AddUserCenterOnClickItemListener() {
                    @Override
                    public void AddToolBar() {
                        finish();//退出登录
                    }

                    @Override
                    public void ModifyPassword() {
                        Intent intent = new Intent(MainActivity2.this, ModifyPasswordActivity.class);
                        intent.putExtra("now_user_id", now_user_id);
                        startActivity(intent);
                    }

                    @Override
                    public void ModifyUsername() {
                        Intent intent = new Intent(MainActivity2.this, ModifyUsernameActivity.class);
                        intent.putExtra("now_user_id", now_user_id);
                        startActivity(intent);
                    }

                    @Override
                    public void DeleteUser() {
                        int row = UserDbHelper.getInstance(MainActivity2.this).deleteUserById(now_user_id);
                        if (row > 0) {
                            Toast.makeText(MainActivity2.this, "成功注销", Toast.LENGTH_SHORT).show();
                            CommentDbHelper.getInstance(MainActivity2.this).deleteCommentByReceive_user_id(now_user_id);
                            CommentDbHelper.getInstance(MainActivity2.this).deleteCommentBySend_user_id(now_user_id);
                            RecommendDbHelper.getInstance(MainActivity2.this).deleteRecommendByUser_id(now_user_id);
                            finish();
                        } else {
                            Toast.makeText(MainActivity2.this, "注销失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

    @Override
    protected void onResume() {
        super.onResume();
        if (historyFragment != null) {
            historyFragment.loadData();
        }
        if (homeFragment != null) {
            homeFragment.loadData();
        }
        if (favorFragment != null) {
            favorFragment.loadData();
        }
        if (userCenterFragment != null) {
            userCenterFragment.loadData();//用户名可能更新
        }
    }
}