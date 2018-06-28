package com.samsson.motornation.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.samsson.motornation.R;
import com.samsson.motornation.delegates.CustomDelegates;
import com.samsson.motornation.utils.Constantvariables;
import com.samsson.motornation.utils.MultiServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, CustomDelegates {
    /*FB Integration*/
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    TextView details_txt;
    ImageView login_button;

    TextView tv_Signup;
    Button btn_Signin;
    ImageView btn_Fb, btn_Gp;

    /*GooglePlus Integration*/
    private SignInButton gplusloginButton;
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;
    ImageView gplusBtn;
    LoginButton fbloginButton;


    //a constant for detecting the login intent result
    private static final int RC_SIGN_IN = 234;

    //Tag for the logs optional
    private static final String TAG = "moto";

    //creating a GoogleSignInClient object
    GoogleSignInClient mGoogleSignInClient;

    //And also a Firebase Auth object
    FirebaseAuth mAuth;
    SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        mAuth = FirebaseAuth.getInstance();

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int ui = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(ui);
        }
        callbackManager = CallbackManager.Factory.create();


        tv_Signup = findViewById(R.id.tv_signup);
        btn_Signin = findViewById(R.id.btn_signin);
        btn_Fb = findViewById(R.id.btn_fb);
        btn_Gp = findViewById(R.id.btn_g);
        fbloginButton = (LoginButton) findViewById(R.id.fblogin_button);
        fbloginButton.setReadPermissions("email");

        getLoginDetails(fbloginButton);

        btn_Fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbloginButton.performClick();
            }
        });

        tv_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        btn_Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        gplusBtn = (ImageView) findViewById(R.id.btn_g);
        //Initializing signinbutton
        // signInButton = (SignInButton) findViewById(R.id.sign_in_button);
/*

        //Initializing google signin option
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this */
        /* FragmentActivity *//*
, this */
        /* OnConnectionFailedListener *//*
)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

*/


        signInButton = (SignInButton) findViewById(R.id.sign_in_button);


        //Then we need a GoogleSignInOptions object
        //And we need to build it as below
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Then we will get the GoogleSignInClient object from GoogleSignIn class
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //Now we will attach a click listener to the sign_in_button
        //and inside onClick() method0 we are calling the signIn() method that will open
        //google sign in intent
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }


    protected void getLoginDetails(LoginButton login_button) {
        // Callback registration<br />
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult login_result) {

               /* Intent intent = new Intent(SignUp.this,HomeScreen.class);
                startActivity(intent);*/

                String accessToken = login_result.getAccessToken().getToken();
                Log.i("accessToken", accessToken);

                GraphRequest request = GraphRequest.newMeRequest(login_result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        // Get facebook data from login
                        getFacebookData(object);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Par·metros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // code for cancellation<br />
            }

            @Override
            public void onError(FacebookException exception) {
                //  code to handle error<br />
            }
        });
    }

    public void getFacebookData(JSONObject object) {
        try {
            //   Bundle bundle = new Bundle();
            String fbemail = "", fbfname = "", fblname = "", fbid = "";
            URL profile_pic = null;
            String id = object.getString("id");
            if (object.has("email")) {
                fbemail = object.getString("email");
            }
            if (object.has("first_name")) {
                fbfname = object.getString("first_name");
            }
            if (object.has("last_name")) {
                fblname = object.getString("last_name");
            }
            if (object.has("idFacebook")) {
                fbid = object.getString("idFacebook");
            }
            try {
                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            Constantvariables.setIdentityProvider(getApplicationContext(), "fb");
            HashMap<String, String> values = new HashMap<String, String>();
            values.put("firstname", fbfname);
            values.put("lastname", fblname);
            values.put("email", fbemail);
            values.put("telephone", "");
            values.put("device_id", "");
            values.put("image", profile_pic + "");
            values.put("social_token", fbid);
            values.put("identity_provider", "fb");

            MultiServerRequest serverRequest = new MultiServerRequest(this, getApplicationContext());
            serverRequest.postData(values, "");
        } catch (JSONException e) {
            Log.d("TAG", "Error parsing JSON");
        }
    }

    @Override
    public void multiResponseFromApi(String data, String Apiname) {
        if (Constantvariables.getIdentityProvider(getApplicationContext()).equals("fb")) {
            LoginManager.getInstance().logOut();
        } else if (Constantvariables.getIdentityProvider(getApplicationContext()).equals("google")) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                        }
                    });
        }
    }

    @Override
    public void onErrorResponse() {

    }

    //G+ Login

    @Override
    protected void onStart() {
        super.onStart();

        //if the user is already signed in
        //we will close this activity
        //and take the user to profile activity
        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if the requestCode is the Google Sign In code that we defined at starting
        if (requestCode == RC_SIGN_IN) {
            //Getting the GoogleSignIn Task
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                //authenticating with firebase
                firebaseAuthWithGoogle(account);
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                //Calling a new function to handle signin
                handleSignInResult(result);
            } catch (ApiException e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                callbackManager.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        //getting the auth credential
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        //Now using firebase we are signing in the user here
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(LoginActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }


    //this method is called on click
    private void signIn() {
        //getting the google signin intent
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        //starting the activity for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            GoogleSignInAccount acct = result.getSignInAccount();
            String firstname = "", middlename = "", lastname = "";
            String fullname = acct.getDisplayName();
            String name[] = fullname.split(" ");

            if (name.length == 2) {
                firstname = name[0];
                lastname = name[1];
            } else if (name.length == 3) {
                firstname = name[0];
                middlename = name[1];
                lastname = name[2];
            }

            Constantvariables.setIdentityProvider(getApplicationContext(), "google");
            HashMap<String, String> values = new HashMap<String, String>();
            values.put("firstname", firstname);
            values.put("lastname", lastname);
            values.put("email", acct.getEmail());
            values.put("telephone", "");
            values.put("image", acct.getPhotoUrl() + "");
            values.put("social_token", acct.getId());
            values.put("identity_provider", "g+");

            Log.d("g+data", firstname + "," + lastname);
            MultiServerRequest serverRequest = new MultiServerRequest(this, getApplicationContext());
            serverRequest.postData(values, "");


        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }
}
