package com.example.myapplication.Admin.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.FoodDbHelper;

public class AddFoodActivity extends AppCompatActivity {
    private EditText et_food_name;
    private EditText et_food_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        //init
        et_food_name = findViewById(R.id.et_food_name);
        et_food_type = findViewById(R.id.et_food_type);
        Intent intent = getIntent();
        String canteen_name = intent.getStringExtra("canteen_name");
        String window_name = intent.getStringExtra("window_name");
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //食物名不是初始化时获取，是在提交时才知道!!!
                String food_name = et_food_name.getText().toString();
                String food_Type = et_food_type.getText().toString();
                if (!food_Type.equals("1") && !food_Type.equals("2") && !food_Type.equals("0")) {
                    Toast.makeText(AddFoodActivity.this, "食物类别码不存在，添加失败", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    int food_type = Integer.parseInt(food_Type);
                    FoodDbHelper myFoodDbHelper = FoodDbHelper.getInstance(AddFoodActivity.this);
                    //加入前需要判断该食物名是否在窗口里冲突
                    if (myFoodDbHelper.isHasFood(canteen_name, window_name, food_name)) {
                        Toast.makeText(AddFoodActivity.this, "食物名重复，添加失败", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        int row = myFoodDbHelper.addFood(food_name, canteen_name, window_name,food_type);
                        if (row > 0) {
                            Toast.makeText(AddFoodActivity.this, "添加食物成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddFoodActivity.this, "添加食物失败", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                }
            }
        });
    }
}