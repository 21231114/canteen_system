package com.example.myapplication.Admin.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.CanteenDbHelper;
import com.example.myapplication.db.WindowDbHelper;

public class AddWindowActivity extends AppCompatActivity {
    private EditText et_canteen_name;
    private EditText et_window_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_window);
        et_canteen_name = findViewById(R.id.et_canteen_name);
        et_window_name = findViewById(R.id.et_window_name);
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String canteen_name = et_canteen_name.getText().toString();
                String window_name = et_window_name.getText().toString();
                if (CanteenDbHelper.getInstance(AddWindowActivity.this).isHasCanteen(canteen_name) == null) {
                    Toast.makeText(AddWindowActivity.this, "食堂不存在", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    if (WindowDbHelper.getInstance(AddWindowActivity.this).isHasWindow(canteen_name, window_name) != null) {
                        Toast.makeText(AddWindowActivity.this, "食堂已经拥有这个窗口", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        int row = WindowDbHelper.getInstance(AddWindowActivity.this).addWindow(window_name, canteen_name);
                        if (row > 0) {
                            Intent intent = new Intent();
                            intent.putExtra("canteen_name", canteen_name);//需要告诉MainActivity应该更新哪一个食堂界面
                            setResult(RESULT_OK, intent);
                            Toast.makeText(AddWindowActivity.this, "成功写入数据库", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddWindowActivity.this, "写入数据库失败", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                }
            }
        });
    }
}