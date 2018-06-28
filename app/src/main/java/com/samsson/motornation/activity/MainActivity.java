package com.samsson.motornation.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.samsson.motornation.R;
import com.samsson.motornation.fragments.ChallengeFragment;
import com.samsson.motornation.fragments.HomeFragment;
import com.samsson.motornation.fragments.NotificationFragment;
import com.samsson.motornation.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    LinearLayout challengeLinear,homeLinear,searchLinear,bellLinear;
    ImageView challenge,home,search,bell,editProfile,addPost;
    View challengeview,homeview,searchview,bellview;
    public static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity=this;

                    Fragment fragment = new HomeFragment();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.Contentframe, fragment).commit();

        init();
        ClickListeners();
    }

    public void init()
    {
        editProfile = findViewById(R.id.title_bar_left_menu);
        addPost = findViewById(R.id.filter);

        homeLinear=findViewById(R.id.home);
        challengeLinear=findViewById(R.id.challenge);
        searchLinear=findViewById(R.id.search);
        bellLinear=findViewById(R.id.bell);

        home=findViewById(R.id.home_icon);
        challenge=findViewById(R.id.challenge_icon);
        search=findViewById(R.id.search_icon);
        bell=findViewById(R.id.bell_icon);

        homeview=findViewById(R.id.view1);
        challengeview=findViewById(R.id.view2);
        searchview=findViewById(R.id.view3);
        bellview=findViewById(R.id.view4);
    }

    public void ClickListeners()
    {
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddPostActivity.class);
                startActivity(intent);
            }
        });
        homeLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* homeLinear.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                challengeLinear.setBackgroundColor(getResources().getColor(R.color.black));
                searchLinear.setBackgroundColor(getResources().getColor(R.color.black));
                bellLinear.setBackgroundColor(getResources().getColor(R.color.black));*/

                home.setImageResource(R.drawable.home_active);
                challenge.setImageResource(R.drawable.challenge);
                search.setImageResource(R.drawable.search);
                bell.setImageResource(R.drawable.bell);

                homeview.setBackgroundColor(getResources().getColor(R.color.yellow_light));
                challengeview.setBackgroundColor(getResources().getColor(R.color.black));
                searchview.setBackgroundColor(getResources().getColor(R.color.black));
                bellview.setBackgroundColor(getResources().getColor(R.color.black));

                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.Contentframe, fragment).commit();
                //MainActivity.main.progress_layout.setVisibility(View.GONE);
            }
        });
        searchLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*homeLinear.setBackgroundColor(getResources().getColor(R.color.black));
                challengeLinear.setBackgroundColor(getResources().getColor(R.color.black));
                searchLinear.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                bellLinear.setBackgroundColor(getResources().getColor(R.color.black));*/

                home.setImageResource(R.drawable.home);
                challenge.setImageResource(R.drawable.challenge);
                search.setImageResource(R.drawable.search_active);
                bell.setImageResource(R.drawable.bell);

                homeview.setBackgroundColor(getResources().getColor(R.color.black));
                challengeview.setBackgroundColor(getResources().getColor(R.color.yellow_light));
                searchview.setBackgroundColor(getResources().getColor(R.color.black));
                bellview.setBackgroundColor(getResources().getColor(R.color.black));

                Fragment fragment = new SearchFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.Contentframe, fragment).commit();
                //MainActivity.main.progress_layout.setVisibility(View.GONE);
            }
        });
        bellLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* homeLinear.setBackgroundColor(getResources().getColor(R.color.black));
                challengeLinear.setBackgroundColor(getResources().getColor(R.color.black));
                searchLinear.setBackgroundColor(getResources().getColor(R.color.black));
                bellLinear.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
               */
                home.setImageResource(R.drawable.home);
                challenge.setImageResource(R.drawable.challenge);
                search.setImageResource(R.drawable.search);
                bell.setImageResource(R.drawable.bell_active);

                homeview.setBackgroundColor(getResources().getColor(R.color.black));
                challengeview.setBackgroundColor(getResources().getColor(R.color.black));
                searchview.setBackgroundColor(getResources().getColor(R.color.yellow_light));
                bellview.setBackgroundColor(getResources().getColor(R.color.black));

                Fragment fragment = new NotificationFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.Contentframe, fragment).commit();
                //MainActivity.main.progress_layout.setVisibility(View.GONE);
            }
        });
        challengeLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* homeLinear.setBackgroundColor(getResources().getColor(R.color.black));
                challengeLinear.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                searchLinear.setBackgroundColor(getResources().getColor(R.color.black));
                bellLinear.setBackgroundColor(getResources().getColor(R.color.black));*/

                home.setImageResource(R.drawable.home);
                challenge.setImageResource(R.drawable.challenge_active);
                search.setImageResource(R.drawable.search);
                bell.setImageResource(R.drawable.bell);

                homeview.setBackgroundColor(getResources().getColor(R.color.black));
                challengeview.setBackgroundColor(getResources().getColor(R.color.black));
                searchview.setBackgroundColor(getResources().getColor(R.color.black));
                bellview.setBackgroundColor(getResources().getColor(R.color.yellow_light));

                Fragment fragment = new ChallengeFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.Contentframe, fragment).commit();
                //MainActivity.main.progress_layout.setVisibility(View.GONE);
            }
        });
    }
}
