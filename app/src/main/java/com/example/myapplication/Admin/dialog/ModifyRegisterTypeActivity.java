package com.example.myapplication.Admin.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.UserDbHelper;

public class ModifyRegisterTypeActivity extends AppCompatActivity {
    EditText et_after_register_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_register_type);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        et_after_register_type = findViewById(R.id.et_after_register_type);
        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!et_after_register_type.getText().toString().equals("1") && !et_after_register_type.getText().toString().equals("0")){
                    Toast.makeText(ModifyRegisterTypeActivity.this, "输入格式错误", Toast.LENGTH_SHORT).show();
                }
                else{
                    int register_type = Integer.parseInt(et_after_register_type.getText().toString());
                    UserDbHelper.getInstance(ModifyRegisterTypeActivity.this).updateRegisterType(username, register_type);
                    Toast.makeText(ModifyRegisterTypeActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}