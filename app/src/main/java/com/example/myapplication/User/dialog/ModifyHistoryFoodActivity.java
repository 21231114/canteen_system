package com.example.myapplication.User.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.FoodDbHelper;
import com.example.myapplication.db.HistoryDbHelper;
import com.example.myapplication.entity.FoodInfo;
import com.example.myapplication.entity.HistoryInfo;

public class ModifyHistoryFoodActivity extends AppCompatActivity {
    EditText et_canteen_name;
    EditText et_window_name;
    EditText et_food_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_history_food);
        et_canteen_name = findViewById(R.id.et_canteen_name);
        et_window_name = findViewById(R.id.et_window_name);
        et_food_name = findViewById(R.id.et_food_name);
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String canteen_name = et_canteen_name.getText().toString();
                String window_name = et_window_name.getText().toString();
                String food_name = et_food_name.getText().toString();
                FoodInfo foodInfo = FoodDbHelper.getInstance(ModifyHistoryFoodActivity.this).queryFood(canteen_name, window_name, food_name);
                if (foodInfo == null) {
                    Toast.makeText(ModifyHistoryFoodActivity.this, "输入的菜品不存在", Toast.LENGTH_SHORT).show();
                } else {
                    int after_food_id = foodInfo.getFood_id();
                    Intent intent = getIntent();
                    HistoryInfo historyInfo = (HistoryInfo) intent.getSerializableExtra("history");
                    HistoryDbHelper.getInstance(ModifyHistoryFoodActivity.this).updateFood(after_food_id, historyInfo.getHistory_id());
                    Toast.makeText(ModifyHistoryFoodActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}