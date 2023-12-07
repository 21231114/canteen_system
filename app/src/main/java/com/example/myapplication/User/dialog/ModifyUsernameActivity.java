package com.example.myapplication.User.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.UserDbHelper;

public class ModifyUsernameActivity extends AppCompatActivity {
    EditText et_username;
    private int now_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_username);
        et_username = findViewById(R.id.et_username);
        Intent intent = getIntent();
        now_user_id = intent.getIntExtra("now_user_id", 0);
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_username.getText().toString();
                if (username.equals("")) {
                    Toast.makeText(ModifyUsernameActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    int row = UserDbHelper.getInstance(ModifyUsernameActivity.this).updateUsernameById(now_user_id, username);
                    if(row>0){
                        Toast.makeText(ModifyUsernameActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ModifyUsernameActivity.this, "修改失败,该用户名已存在", Toast.LENGTH_SHORT).show();
                    }
                }
                finish();
            }
        });
    }
}