package com.example.myapplication.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.User.Adapter.CommentListAdapter;
import com.example.myapplication.User.dialog.AddCommentActivity;
import com.example.myapplication.db.CommentDbHelper;
import com.example.myapplication.entity.CommentInfo;
import com.example.myapplication.entity.FoodInfo;

import java.util.ArrayList;
import java.util.List;

public class ShowFoodCommentActivity extends AppCompatActivity {
    private RecyclerView myRecycleView;
    private CommentListAdapter commentListAdapter;
    private List<CommentInfo> commentList = new ArrayList<>();
    private FoodInfo foodInfo;
    Button add_comment;
    private int now_user_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food_comment);
        Intent intent = getIntent();

        foodInfo = (FoodInfo) intent.getSerializableExtra("food_info");
        now_user_id = intent.getIntExtra("now_user_id", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(foodInfo.getCanteen_name() + " " + foodInfo.getWindow_name() + " " + foodInfo.getFood_name());//标题设置

        add_comment = findViewById(R.id.add_comment);
        myRecycleView = findViewById(R.id.commentRecyclerView);
        commentListAdapter = new CommentListAdapter();
        myRecycleView.setAdapter(commentListAdapter);
        loadData();

        findViewById(R.id.add_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowFoodCommentActivity.this, AddCommentActivity.class);
                intent.putExtra("food_id", foodInfo.getFood_id());
                intent.putExtra("send_user_id", now_user_id);
                startActivity(intent);
            }
        });
        commentListAdapter.setCommentListOnClickItemListener(new CommentListAdapter.CommentListOnClickItemListener() {
            @Override
            public void onItemReplyClick(int position) {
                View itemView = getRecyclerViewItem(myRecycleView, position);
                CommentInfo commentInfo = (CommentInfo) itemView.getTag();
                Intent intent = new Intent(ShowFoodCommentActivity.this, AddCommentActivity.class);
                intent.putExtra("food_id", foodInfo.getFood_id());
                intent.putExtra("send_user_id", now_user_id);
                intent.putExtra("receive_user_id", commentInfo.getSend_user_id());
                startActivity(intent);
            }
        });
        //设置分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        myRecycleView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {
        commentList.clear();
        commentList = CommentDbHelper.getInstance(ShowFoodCommentActivity.this).queryCommentListDataByFoodId(foodInfo.getFood_id());
        commentListAdapter.setDataList(commentList);
    }

    public View getRecyclerViewItem(RecyclerView recyclerView, int position) {
        if (recyclerView == null || recyclerView.getLayoutManager() == null || recyclerView.getAdapter() == null || recyclerView.getAdapter().getItemCount() <= 0) {
            return null;
        }
        if (position > recyclerView.getAdapter().getItemCount()) {
            return null;
        }
        RecyclerView.ViewHolder viewHolder = recyclerView.getAdapter().createViewHolder(recyclerView, recyclerView.getAdapter().getItemViewType(position));
        recyclerView.getAdapter().onBindViewHolder(viewHolder, position);
        viewHolder.itemView.measure(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        return viewHolder.itemView;
    }
}