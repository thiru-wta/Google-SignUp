package com.example.wtastudios.srikanth;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by WTA Studios on 3/4/2017.
 */

public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        TextView userName = (TextView)findViewById(R.id.userName);
        TextView userEmail = (TextView)findViewById(R.id.userEmail);

        SharedPreferences preferences = getSharedPreferences("LOGIN", MODE_PRIVATE);
        String strName = preferences.getString(SignUpActivity.PREF_NAME, null);
        String strEmail = preferences.getString(SignUpActivity.PREF_EMAIL, null);

        if(strName != null){
            userName.setText(strName);
        }

        if(strEmail != null){
            userEmail.setText(strEmail);
        }
    }
}
