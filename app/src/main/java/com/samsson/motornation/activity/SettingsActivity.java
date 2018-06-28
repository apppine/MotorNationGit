package com.samsson.motornation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.samsson.motornation.R;

public class SettingsActivity extends AppCompatActivity {

    ImageView switch1,switch2,backBtn;
    TextView contact;
    LinearLayout changePwdLinear,facebook,blockedPeoples,vehicleDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();
        ClickListeners();

    }
    public void init() {
        switch1 = findViewById(R.id.pvt_switch1);
        switch2 = findViewById(R.id.sub_switch2);
        backBtn = findViewById(R.id.back_btn);
        vehicleDetails = findViewById(R.id.vehicle_details);
        contact = findViewById(R.id.tv_contact);
        changePwdLinear = findViewById(R.id.change_pwd);
        blockedPeoples = findViewById(R.id.blked_ppl);
        facebook = findViewById(R.id.facebook);

    }
    public void ClickListeners() {
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this,ContactActivity.class);
                startActivity(intent);
            }
        });
        changePwdLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        blockedPeoples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this,BlockedListActivity.class);
                startActivity(intent);
            }
        });
        vehicleDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this,VehicledetailActivity.class);
                startActivity(intent);
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String bodyMessage="I Like to recommended to download  Android App , It's most useful while visiting kerala...\n";
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getText(R.string.app_name)+" (Open it in Google Play Store to Download the Application)");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, bodyMessage);
                startActivity(Intent.createChooser(shareIntent, "Share via"));*/

            }
        });
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // switch1.setImageResource(R.drawable.switch1);

                if(switch1.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.switch1).getConstantState())
                {
                    //  Toast.makeText(getApplicationContext(),"eye",Toast.LENGTH_SHORT).show();
                    switch1.setImageResource(R.drawable.switch3);

                }
                else if (switch1.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.switch3).getConstantState())
                {
                    //Toast.makeText(getApplicationContext(),"eye22",Toast.LENGTH_SHORT).show();
                    switch1.setImageResource(R.drawable.switch1);

                }

            }
        });
        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  switch2.setImageResource(R.drawable.switch1);
                if(switch2.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.switch1).getConstantState())
                {
                    //  Toast.makeText(getApplicationContext(),"eye",Toast.LENGTH_SHORT).show();
                    switch2.setImageResource(R.drawable.switch3);

                }
                else if (switch2.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.switch3).getConstantState())
                {
                    //Toast.makeText(getApplicationContext(),"eye22",Toast.LENGTH_SHORT).show();
                    switch2.setImageResource(R.drawable.switch1);

                }
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
