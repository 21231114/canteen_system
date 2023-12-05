package com.example.myapplication.Admin.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.FoodDbHelper;

public class ModifyFoodActivity extends AppCompatActivity {
    private EditText et_before_food_name;
    private EditText et_after_food_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_food);
        et_before_food_name = findViewById(R.id.et_before_food_name);
        et_after_food_name = findViewById(R.id.et_after_food_name);
        Intent intent = getIntent();
        String canteen_name = intent.getStringExtra("canteen_name");
        String window_name = intent.getStringExtra("window_name");
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //食物名不是初始化时获取，是在提交时才知道!!!
                String before_food_name = et_before_food_name.getText().toString();
                String after_food_name = et_after_food_name.getText().toString();
                FoodDbHelper myFoodDbHelper = FoodDbHelper.getInstance(ModifyFoodActivity.this);
                //需要判断该食物名是否在窗口里冲突，与待修改的食物名是否存在
                if (!myFoodDbHelper.isHasFood(canteen_name, window_name, before_food_name)) {
                    Toast.makeText(ModifyFoodActivity.this, "修改前食物名不存在，修改失败", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    if (myFoodDbHelper.isHasFood(canteen_name, window_name, after_food_name)) {
                        Toast.makeText(ModifyFoodActivity.this, "修改后食物名重复，修改失败", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        int row = myFoodDbHelper.updateFoodName(canteen_name, window_name, before_food_name, after_food_name);
                        if (row > 0) {
                            Toast.makeText(ModifyFoodActivity.this, "修改食物成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ModifyFoodActivity.this, "修改食物失败", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                }
            }
        });
    }
}