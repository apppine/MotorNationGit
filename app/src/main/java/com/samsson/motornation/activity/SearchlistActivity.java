package com.samsson.motornation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.samsson.motornation.R;
import com.samsson.motornation.adapter.Searchlist_adapter;
import com.samsson.motornation.adapter.Suggestedlist_adapter;
import com.samsson.motornation.model.Home;
import java.util.ArrayList;

public class SearchlistActivity extends AppCompatActivity {
RecyclerView home_recycler;
    public ArrayList<Home> Homelist;
    Searchlist_adapter homelist_adapter;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlist);
        back_btn = findViewById(R.id.back_btn);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        home_recycler=findViewById(R.id.searchlist_recycler);
        home_recycler.setLayoutManager(layoutManager);
        LoadData();
         homelist_adapter=new Searchlist_adapter(getApplicationContext(),Homelist);
        home_recycler.setAdapter(homelist_adapter);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void LoadData()
    {
        Homelist=new ArrayList<>();
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        Homelist.add(new Home());
        home_recycler.setAdapter(homelist_adapter);
    }
}
