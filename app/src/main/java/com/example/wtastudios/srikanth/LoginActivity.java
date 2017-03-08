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
public class LoginActivity extends AppCompatActivity {

    SharedPreferences preferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        preferences = getSharedPreferences("LOGIN", MODE_PRIVATE);

        final EditText email, password;

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(email.getText().toString().length() > 0 && password.getText().toString().length() > 0){
                 validate(email.getText().toString(), password.getText().toString());
             }else{
                 Toast.makeText(LoginActivity.this, "Email & Password Required! " , Toast.LENGTH_SHORT).show();
             }
            }
        });
    }

    private void validate(String s, String s1) {
        String strEmail = preferences.getString(SignUpActivity.PREF_EMAIL, null);
        String strPassword = preferences.getString(SignUpActivity.PREF_PASSWORD, null);

        if(s.equals(strEmail) && s1.equals(strPassword)){

            Intent in = new Intent(LoginActivity.this, UserInfoActivity.class);
            startActivity(in);
            finish();

        }else{
            Toast.makeText(getApplicationContext(), "INVALIDE EMAIL & PASSWORD ", Toast.LENGTH_LONG).show();
        }

    }
}
