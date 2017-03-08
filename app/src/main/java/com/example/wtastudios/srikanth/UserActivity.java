package com.example.wtastudios.srikanth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.UserInfo;

/**
 * Created by WTA Studios on 3/4/2017.
 */

public class UserActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "UserActivity";
    private static final int RC_SIGN_IN = 100;
    GoogleApiClient mGoogleApiClient;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        preferences = getSharedPreferences("LOGIN", MODE_PRIVATE);
        editor = preferences.edit();

        Button signup = (Button) findViewById(R.id.signup);
        Button login = (Button) findViewById(R.id.login);
        ImageView gmail = (ImageView) findViewById(R.id.gmail);
        gmail.setOnClickListener(this);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


       /* login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(in);
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(UserActivity.this, SignUpActivity.class);
                startActivity(in);
                finish();
            }
        });*/
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.gmail:
                signIn();
                break;
            case R.id.login:
                Intent login = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
                break;
            case R.id.signup:
                Intent signup = new Intent(UserActivity.this, SignUpActivity.class);
                startActivity(signup);
                finish();
                break;

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }


    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            RegisterUser(acct.getDisplayName(), acct.getEmail(), "Google");

        } else {
            // Signed out, show unauthenticated UI.
            Log.e(TAG, "Error while Signin");
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        signIn();
    }

    private void RegisterUser(String strName, String strEmail, String strPassword) {
        editor.putString(SignUpActivity.PREF_NAME, strName);
        editor.putString(SignUpActivity.PREF_EMAIL, strEmail);
        editor.putString(SignUpActivity.PREF_PASSWORD, strPassword);
        editor.commit();

        Intent in = new Intent(UserActivity.this, UserInfoActivity.class);
        startActivity(in);
        finish();
    }

}
