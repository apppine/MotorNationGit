package com.samsson.motornation.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsson.motornation.R;
import com.samsson.motornation.delegates.CustomDelegates;
import com.samsson.motornation.utils.Constantvariables;
import com.samsson.motornation.utils.MultiServerRequest;
import org.json.JSONObject;
import java.util.HashMap;

public class SignInActivity extends AppCompatActivity implements CustomDelegates{
    Button btn_Signin;
    TextView tv_Signup,tv_forgot;
    EditText et_Uname,et_Pswd;
    public ImageView eye;
    CheckBox im_Checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btn_Signin = findViewById(R.id.btn_signin);
        et_Uname= findViewById(R.id.et_uname);
        et_Pswd = findViewById(R.id.et_pswd);
        tv_Signup = findViewById(R.id.tv_signup);
        tv_forgot = findViewById(R.id.forgot);
        eye = findViewById(R.id.eye);
        im_Checkbox = findViewById(R.id.im_checkbox);
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eye.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.eye).getConstantState())
                {
                    //  Toast.makeText(getApplicationContext(),"eye",Toast.LENGTH_SHORT).show();
                    eye.setImageResource(R.drawable.eye2);
                    et_Pswd.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                else if (eye.getDrawable().getConstantState() == getResources().getDrawable( R.drawable.eye2).getConstantState())
                {
                    //Toast.makeText(getApplicationContext(),"eye22",Toast.LENGTH_SHORT).show();
                    eye.setImageResource(R.drawable.eye);

                    et_Pswd.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_NORMAL);
                }
            }
        });

        btn_Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_Uname.getText().toString().trim().equals("")) {
                    if (!et_Pswd.getText().toString().trim().equals("")) {
                        signin(et_Uname.getText().toString(),et_Pswd.getText().toString());
                    }
                }
            }
        });

        tv_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        tv_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SignInActivity.this);
                // Include dialog.xml file
                dialog.setContentView(R.layout.forgot);
                // Set dialog title
                dialog.setTitle("MotorNation");
                dialog.show();
                EditText logtext = (EditText) dialog.findViewById(R.id.logtext);
                Button yesButton = (Button) dialog.findViewById(R.id.yes);
                Button cancelButton = (Button) dialog.findViewById(R.id.no);

                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(dialog.getContext(), SignInActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        //testing for github respository upload
    }

    public void signin(String name,String password)
    {
        Intent intent = new Intent(SignInActivity.this,MainActivity.class);
        startActivity(intent);

        HashMap<String, String> values = new HashMap<String, String>();
        values.put("username",name);
        values.put("password", password);

        MultiServerRequest serverRequest = new MultiServerRequest(this,getApplicationContext());
        serverRequest.postData(values, Constantvariables.SIGNIN_CONTROLLER);
    }

    @Override
    public void multiResponseFromApi(String data, String Apiname) {
        if (Apiname.equals(Constantvariables.SIGNIN_CONTROLLER))
        {
            try {
                Boolean status;
                JSONObject jsonObject = new JSONObject(data);
                status = jsonObject.getBoolean("status");
                if (status) {
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
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
