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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.samsson.motornation.R;
import com.samsson.motornation.delegates.CustomDelegates;
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

public class EditProfileActivity extends AppCompatActivity implements CustomDelegates {
    private  ImageView back_btn,profileImage,addImgBtn;
    private  TextView profileName;
    private EditText firstName,lastName,mobile,userName,vehicleRegnum,vehicleModel;
    Button saveCgangesBtn;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        init();
        ClickListener();
    }
    public void init() {

        back_btn = findViewById(R.id.back_btn);
        profileImage = findViewById(R.id.im_image_profile);
        addImgBtn = findViewById(R.id.im_add_imgbtn);
        profileName = findViewById(R.id.tv_profile_name);
        firstName = findViewById(R.id.et_fst_name);
        lastName = findViewById(R.id.et_lst_name);
        mobile = findViewById(R.id.et_mobile);
        userName = findViewById(R.id.et_user_name);
        vehicleRegnum = findViewById(R.id.et_regnum_vehcl);
        vehicleModel = findViewById(R.id.et_model_vehcl);
        saveCgangesBtn = findViewById(R.id.btn_save);

    }
    public void ClickListener()
    {

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        addImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });
        saveCgangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges(firstName.getText().toString(),lastName.getText().toString(),userName.getText().toString(),mobile.getText().toString(),vehicleRegnum.getText().toString(),vehicleModel.getText().toString());
                Toast.makeText(EditProfileActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void saveChanges(String fst_name,String lst_name,String user_name,String mobile,String regnum,String model)
    {
        Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
        startActivity(intent);

        HashMap<String, String> values = new HashMap<String, String>();
        values.put("fst_name",fst_name);
        values.put("password", lst_name);
        values.put("lst_name", user_name);
        values.put("mobile",mobile);
        values.put("regnum", regnum);
        values.put("model", model);

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
                    Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(EditProfileActivity.this);

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

        profileImage.setImageBitmap(thumbnail);
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

        profileImage.setImageBitmap(bm);
    }

}
