package com.samsson.motornation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.samsson.motornation.R;
import com.samsson.motornation.fragments.GridFragment;
import com.samsson.motornation.fragments.ListFragment;

public class FriendsProfileActivity extends AppCompatActivity {
    Button postBtn;
    ImageView backBtn,moreBtn;
    ImageView grid,linear,imgFollowing;
    TextView follow;
    LinearLayout edit_profileLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_profile);
        init();
        ClickListeners();
        
        Fragment fragment = new GridFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.image_frame, fragment).commit();
    }
    public void init() {
        postBtn = findViewById(R.id.post_btn);
        backBtn = findViewById(R.id.back_btn);
        moreBtn = findViewById(R.id.more);
        imgFollowing = findViewById(R.id.im_follow);

        edit_profileLinear = findViewById(R.id.edit_profile);
        follow = findViewById(R.id.follow);

        grid = findViewById(R.id.grid);
        linear = findViewById(R.id.list);

    }
    public void ClickListeners() {
            moreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FriendsProfileActivity.this,SettingsActivity.class);
                    startActivity(intent);
                }
            });
            edit_profileLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(follow.getText().equals("Follow"))
                    {
                        follow.setText("Following");
                        imgFollowing.setImageResource(R.drawable.following);

                    }else
                    {
                        follow.setText("Follow");
                        imgFollowing.setImageResource(R.drawable.edit_profile);
                    }
                   /* Intent intent = new Intent(FriendsProfileActivity.this,EditProfileActivity.class);
                    startActivity(intent);*/
                }
            });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid.setImageResource(R.drawable.grid);
                linear.setImageResource(R.drawable.list);

                Fragment fragment = new GridFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.image_frame, fragment).commit();

            }
        });
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid.setImageResource(R.drawable.grid2);
                linear.setImageResource(R.drawable.list2);

                Fragment fragment = new ListFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.image_frame, fragment).commit();
            }
        });
    }
}
