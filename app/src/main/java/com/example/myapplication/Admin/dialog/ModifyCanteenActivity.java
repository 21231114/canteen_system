package com.example.myapplication.Admin.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.CanteenDbHelper;
import com.example.myapplication.db.FoodDbHelper;
import com.example.myapplication.db.WindowDbHelper;

public class ModifyCanteenActivity extends AppCompatActivity {
    private EditText et_before_canteen_name;
    private EditText et_after_canteen_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_canteen);
        et_before_canteen_name = findViewById(R.id.et_before_canteen_name);
        et_after_canteen_name = findViewById(R.id.et_after_canteen_name);
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String before_canteen_name = et_before_canteen_name.getText().toString();//更改前的食堂名
                String after_canteen_name = et_after_canteen_name.getText().toString();//更改后的食堂名
                if (CanteenDbHelper.getInstance(ModifyCanteenActivity.this).isHasCanteen(after_canteen_name) != null) {
                    //已经拥有食堂，不满足unique
                    Toast.makeText(ModifyCanteenActivity.this, "修改后的食堂名已经存在", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    int row = CanteenDbHelper.getInstance(ModifyCanteenActivity.this).updateCanteen(before_canteen_name, after_canteen_name);
                    if (row == 0) {
                        Toast.makeText(ModifyCanteenActivity.this, "修改食堂名失败", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        WindowDbHelper.getInstance(ModifyCanteenActivity.this).updateWindowCanteenName(before_canteen_name, after_canteen_name);
                        FoodDbHelper.getInstance(ModifyCanteenActivity.this).updateFoodCanteenName(before_canteen_name, after_canteen_name);
                        Intent intent = new Intent();
                        intent.putExtra("after_canteen_name", after_canteen_name);
                        intent.putExtra("before_canteen_name", before_canteen_name);
                        setResult(RESULT_OK, intent);
                        Toast.makeText(ModifyCanteenActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}