package com.example.myapplication.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.WindowDbHelper;

public class ModifyWindowActivity extends AppCompatActivity {
    private EditText et_after_window_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_window);
        Intent intent = getIntent();
        String canteen_name = intent.getStringExtra("canteen_name");
        String before_window_name = intent.getStringExtra("window_name");
        et_after_window_name = findViewById(R.id.et_after_window_name);
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String after_window_name = et_after_window_name.getText().toString();
                if (WindowDbHelper.getInstance(ModifyWindowActivity.this).isHasWindow(canteen_name, after_window_name) != null) {
                    Toast.makeText(ModifyWindowActivity.this, "此食堂该窗口名称已经存在", Toast.LENGTH_SHORT).show();
                } else {
                    int row = WindowDbHelper.getInstance(ModifyWindowActivity.this).updateWindowName(canteen_name, before_window_name, after_window_name);
                    if(row>0){
                        Toast.makeText(ModifyWindowActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ModifyWindowActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                    }
                }
                finish();
            }
        });
    }
}