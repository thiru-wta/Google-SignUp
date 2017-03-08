package com.example.wtastudios.srikanth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by WTA Studios on 3/4/2017.
 */
public class SignUpActivity extends AppCompatActivity{
    EditText name, email, password;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public static final String PREF_NAME = "NAME";
    public static final String PREF_EMAIL = "EMAIL";
    public static final String PREF_PASSWORD = "PASSWORD";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        preferences = getSharedPreferences("LOGIN", MODE_PRIVATE);
        editor = preferences.edit();

        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        Button signup = (Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strName = name.getText().toString();
                String strEmail = email.getText().toString();
                String strPassword = password.getText().toString();

                if(strName.length() > 0 && strEmail.length() > 0 && strPassword.length() > 0 ){
                    RegisterUser(strName, strEmail, strPassword);
                }else{
                    Toast.makeText(SignUpActivity.this, "All fields required! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void RegisterUser(String strName, String strEmail, String strPassword) {
        editor.putString(PREF_NAME, strName);
        editor.putString(PREF_EMAIL, strEmail);
        editor.putString(PREF_PASSWORD, strPassword);
        editor.commit();

        Intent in = new Intent(SignUpActivity.this, UserInfoActivity.class);
        startActivity(in);
        finish();
    }
}
