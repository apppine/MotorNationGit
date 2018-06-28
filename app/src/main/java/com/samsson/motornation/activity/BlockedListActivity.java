package com.samsson.motornation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.samsson.motornation.R;
import com.samsson.motornation.adapter.Blocklist_adapter;
import com.samsson.motornation.model.Home;

import java.util.ArrayList;

public class BlockedListActivity extends AppCompatActivity {

    ImageView backBtn;
    RecyclerView block_recycler;
    Blocklist_adapter blocklist_adapter;
    public ArrayList<Home> Blocklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocked_list);

        backBtn = findViewById(R.id.back_btn);
        block_recycler= findViewById(R.id.recycler_block);
        block_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        LoadData();
        blocklist_adapter=new Blocklist_adapter(getApplicationContext(),Blocklist);
        block_recycler.setAdapter(blocklist_adapter);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
    public void LoadData()
    {
        Blocklist=new ArrayList<>();
        Blocklist.add(new Home());
        Blocklist.add(new Home());
        Blocklist.add(new Home());
        block_recycler.setAdapter(blocklist_adapter);
    }
}
