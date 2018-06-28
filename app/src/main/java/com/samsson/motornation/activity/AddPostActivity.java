package com.samsson.motornation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.samsson.motornation.R;

public class AddPostActivity extends AppCompatActivity {
    Button post_btn;
    ImageView back_btn;
    LinearLayout photoLinear,videoLinear,liveLinear,tagLinear,locationLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        init();
        ClickListener();
    }
    public void init() {
        post_btn = findViewById(R.id.post_btn);
        back_btn = findViewById(R.id.back_btn);

        photoLinear = findViewById(R.id.photo);
        videoLinear = findViewById(R.id.video);
        liveLinear = findViewById(R.id.go_live);
        tagLinear = findViewById(R.id.tag);
        locationLinear = findViewById(R.id.location);

    }
    public void ClickListener()
    {
        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Successfully Posted",Toast.LENGTH_SHORT).show();
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
