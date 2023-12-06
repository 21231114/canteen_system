package com.example.myapplication.Admin.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.FoodDbHelper;

public class ModifyFoodCntActivity extends AppCompatActivity {
    private EditText et_food_cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_food_cnt);
        et_food_cnt = findViewById(R.id.et_food_cnt);
        Intent intent = getIntent();
        String canteen_name = intent.getStringExtra("canteen_name");
        String window_name = intent.getStringExtra("window_name");
        String food_name = intent.getStringExtra("food_name");
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String food_cnt = et_food_cnt.getText().toString();
                try {
                    Integer.parseInt(food_cnt);
                    int row = FoodDbHelper.getInstance(ModifyFoodCntActivity.this).updateFoodCnt(canteen_name, window_name, food_name, food_cnt);
                    if (row > 0) {
                        Toast.makeText(ModifyFoodCntActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ModifyFoodCntActivity.this, "更新失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(ModifyFoodCntActivity.this, "输入的不是数字", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}