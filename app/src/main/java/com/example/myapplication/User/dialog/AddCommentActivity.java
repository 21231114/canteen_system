package com.example.myapplication.User.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.db.CommentDbHelper;

public class AddCommentActivity extends AppCompatActivity {
    EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        et_content = findViewById(R.id.et_content);

        findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                int food_id = intent.getIntExtra("food_id", 0);
                int send_user_id = intent.getIntExtra("send_user_id", 0);
                String content = et_content.getText().toString();
                if (content.equals("")) {
                    Toast.makeText(AddCommentActivity.this, "评论不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    int row = CommentDbHelper.getInstance(AddCommentActivity.this).addComment(send_user_id, food_id, 0, content);
                    if(row>0){
                        Toast.makeText(AddCommentActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(AddCommentActivity.this, "评论失败", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            }
        });
    }
}