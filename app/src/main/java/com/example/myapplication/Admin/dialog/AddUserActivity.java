package com.example.myapplication.Admin.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.UserDbHelper;

public class AddUserActivity extends AppCompatActivity {
    EditText et_username;
    EditText et_register_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        et_username = findViewById(R.id.et_username);
        et_register_type = findViewById(R.id.et_register_type);
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_username.getText().toString();
                if (!et_register_type.getText().toString().equals("1") && !et_register_type.getText().toString().equals("0")) {
                    Toast.makeText(AddUserActivity.this, "用户权限输入格式错误", Toast.LENGTH_SHORT).show();
                } else {
                    int register_type = Integer.parseInt(et_register_type.getText().toString());
                    //初始密码：000000
                    int row = UserDbHelper.getInstance(AddUserActivity.this).register(username, "000000", register_type);
                    if(row>0){
                        Toast.makeText(AddUserActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(AddUserActivity.this, "添加失败,用户已经存在", Toast.LENGTH_SHORT).show();
                    }
                }
                finish();
            }
        });
    }
}