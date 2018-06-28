package com.samsson.motornation.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.samsson.motornation.R;
import com.samsson.motornation.delegates.CustomDelegates;
import com.samsson.motornation.utils.CheckNetworkConnection;
import com.samsson.motornation.utils.Constantvariables;
import com.samsson.motornation.utils.MultiServerRequest;
import com.samsson.motornation.utils.Utility;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity implements CustomDelegates{
    Button btn_Signup;
    ImageView im_Profile;
    CheckBox im_Checkbox;
    EditText et_UserName,et_Password,et_Email,et_ConfirmPassword;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn_Signup = findViewById(R.id.btn_signup);
        im_Profile = findViewById(R.id.im_profile);
        im_Checkbox = findViewById(R.id.im_checkbox);
        et_UserName = findViewById(R.id.et_uname);
        et_Email = findViewById(R.id.et_email);
        et_Password= findViewById(R.id.et_pswd);
        et_ConfirmPassword= findViewById(R.id.et_confirmPswd);
        final String email = et_Email.getText().toString().trim();

        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        im_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean inet = new CheckNetworkConnection().isNetworkAvailable(getApplicationContext());
                if (!inet) {
                    Toast.makeText(SignUpActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                } else {
                    if (!et_UserName.getText().toString().trim().equals("")) {
                        if (!email.trim().equals("")) {
                            if (!email.matches(emailPattern)) {
                                if (!et_Password.getText().toString().trim().equals("")) {
                                    if (!et_ConfirmPassword.getText().toString().trim().equals("")) {
                                        if (et_ConfirmPassword.getText().toString().equals(et_Password.getText().toString().trim())) {
                                            signup(email,et_UserName.getText().toString(),et_Password.getText().toString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
    public void signup(String name,String email,String password)
    {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);

        HashMap<String, String> values = new HashMap<String, String>();
        values.put("username",name);
        values.put("password", password);
        values.put("email", email);

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
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
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

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(SignUpActivity.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        im_Profile.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        im_Profile.setImageBitmap(bm);
    }
}
