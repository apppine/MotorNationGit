package com.samsson.motornation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.samsson.motornation.R;
import com.samsson.motornation.delegates.CustomDelegates;
import com.samsson.motornation.utils.Constantvariables;
import com.samsson.motornation.utils.MultiServerRequest;

import org.json.JSONObject;

import java.util.HashMap;

public class ChangePasswordActivity extends AppCompatActivity implements CustomDelegates {
    ImageView back_btn;
    EditText currentPswd,newPswd,confirmPswd;
    Button saveChanges;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();
        ClickListener();
    }
    public void init() {

        back_btn = findViewById(R.id.back_btn);
        currentPswd = findViewById(R.id.et_currentpswd);
        newPswd = findViewById(R.id.et_newpswd);
        confirmPswd = findViewById(R.id.et_confirmPswd);
        saveChanges = findViewById(R.id.btn_save);



    }
    public void ClickListener()
    {

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveChanges(currentPswd.getText().toString(),newPswd.getText().toString(),confirmPswd.getText().toString());
                Toast.makeText(ChangePasswordActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void saveChanges(String currentPswd,String newPswd,String confirmPswd)
    {
        Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
        startActivity(intent);

        HashMap<String, String> values = new HashMap<String, String>();
        values.put("fst_name",currentPswd);
        values.put("password", newPswd);
        values.put("lst_name", confirmPswd);


        MultiServerRequest serverRequest = new MultiServerRequest(this, getApplicationContext());
        serverRequest.postData(values, Constantvariables.SIGNUP_CONTROLLER);
    }
    @Override
    public void multiResponseFromApi(String data, String Apiname) {
        if (Apiname.equals(Constantvariables.SIGNUP_CONTROLLER))
        {
            try {
                Boolean status;
                JSONObject jsonObject = new JSONObject(data);
                status = jsonObject.getBoolean("status");
                if (status) {
                    Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onErrorResponse() {
    }
}
