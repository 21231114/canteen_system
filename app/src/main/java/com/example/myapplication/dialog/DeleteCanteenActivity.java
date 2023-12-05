package com.example.myapplication.dialog;

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

public class DeleteCanteenActivity extends AppCompatActivity {
    private EditText et_canteen_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_canteen);
        et_canteen_name = findViewById(R.id.et_canteen_name);
        //获取传递的数据
        //recipesFragment = (RecipesFragment) getIntent().getSerializableExtra("recipeFragment");
        //点击事件
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String canteen_name = et_canteen_name.getText().toString();
                if (CanteenDbHelper.getInstance(DeleteCanteenActivity.this).isHasCanteen(canteen_name) == null) {
                    Toast.makeText(DeleteCanteenActivity.this, "该食堂不存在", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("canteen_name", canteen_name);//
                    setResult(RESULT_CANCELED, intent);
                } else {
                    CanteenDbHelper.getInstance(DeleteCanteenActivity.this).deleteCanteen(canteen_name);
                    WindowDbHelper.getInstance(DeleteCanteenActivity.this).deleteCanteen(canteen_name);
                    FoodDbHelper.getInstance(DeleteCanteenActivity.this).deleteCanteen(canteen_name);
                    Intent intent = new Intent();
                    intent.putExtra("canteen_name", canteen_name);//
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });
    }
}