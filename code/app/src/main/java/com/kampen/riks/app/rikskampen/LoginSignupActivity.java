package com.kampen.riks.app.rikskampen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kampen.riks.app.rikskampen.login.LoginActivity;
import com.kampen.riks.app.rikskampen.signup.SignUpActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
    }




    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void onLoginClick(View view) {


        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();

    }

    public void onJoinClick(View view) {


        Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(intent);
        finish();

    }
}
