package com.example.myapplication.Admin.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.FoodDbHelper;

public class ModifyFoodTypeActivity extends AppCompatActivity {
    private EditText et_food_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_food_type);
        et_food_type = findViewById(R.id.et_food_type);
        Intent intent = getIntent();
        String canteen_name = intent.getStringExtra("canteen_name");
        String window_name = intent.getStringExtra("window_name");
        String food_name = intent.getStringExtra("food_name");
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int food_type = Integer.parseInt(et_food_type.getText().toString());
                int row = FoodDbHelper.getInstance(ModifyFoodTypeActivity.this).updateFoodType(canteen_name, window_name, food_name,food_type);
                if(row>0){
                    Toast.makeText(ModifyFoodTypeActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ModifyFoodTypeActivity.this, "更新失败", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}