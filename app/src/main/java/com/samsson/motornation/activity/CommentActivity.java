package com.samsson.motornation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.samsson.motornation.R;
import com.samsson.motornation.adapter.Commentlist_adapter;
import com.samsson.motornation.adapter.Linearlist_adapter;
import com.samsson.motornation.fragments.SearchFragment;
import com.samsson.motornation.model.Home;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {
    RecyclerView rvComment;
    public ArrayList<Home> Homelist;
    Commentlist_adapter commentlist_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        init();
        ClickListener();
    }
    public void init() {

        rvComment = findViewById(R.id.recycler_comment);
        rvComment.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        LoadData2();
        commentlist_adapter=new Commentlist_adapter(getApplicationContext(),Homelist);
        rvComment.setAdapter(commentlist_adapter);

    }
    public void ClickListener()
    {

        rvComment.addOnItemTouchListener(new SearchFragment.RecyclerTouchListener(getApplicationContext(), rvComment, new SearchFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), PreviewActivity.class);
                startActivity(intent);
            }
            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }
    public void LoadData2()
    {
        Homelist=new ArrayList<>();
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());

        rvComment.setAdapter(commentlist_adapter);
    }
}
