package com.example.myapplication.User.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.UserDbHelper;

public class ModifyPasswordActivity extends AppCompatActivity {
    EditText et_after_password;
    private int now_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        et_after_password = findViewById(R.id.et_after_password);
        Intent intent = getIntent();
        now_user_id = intent.getIntExtra("now_user_id", 0);
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = et_after_password.getText().toString();
                if (password.equals("")) {
                    Toast.makeText(ModifyPasswordActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(ModifyPasswordActivity.this, now_user_id + ":" + password, Toast.LENGTH_SHORT).show();
                int row = UserDbHelper.getInstance(ModifyPasswordActivity.this).updatePasswordById(now_user_id, password);
                if (row > 0) {
                    Toast.makeText(ModifyPasswordActivity.this, "更改成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ModifyPasswordActivity.this, "更改失败", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}