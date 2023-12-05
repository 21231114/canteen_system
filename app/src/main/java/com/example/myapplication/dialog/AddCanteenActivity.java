package com.example.myapplication.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.CanteenDbHelper;

public class AddCanteenActivity extends AppCompatActivity {
    private EditText et_canteen_name;
//    private RecipesFragment recipesFragment;//从启动它的活动接受数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_canteen);
        et_canteen_name = findViewById(R.id.et_canteen_name);
        //获取传递的数据
        //recipesFragment = (RecipesFragment) getIntent().getSerializableExtra("recipeFragment");
        //点击事件
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String canteen_name = et_canteen_name.getText().toString();
                int row = CanteenDbHelper.getInstance(AddCanteenActivity.this).addCanteen(canteen_name);
                if (row > 0) {
                    Intent intent = new Intent();
                    intent.putExtra("canteen_name", canteen_name);//
                    setResult(RESULT_OK, intent);
                    Toast.makeText(AddCanteenActivity.this, "成功写入数据库", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddCanteenActivity.this, "写入数据库失败", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}