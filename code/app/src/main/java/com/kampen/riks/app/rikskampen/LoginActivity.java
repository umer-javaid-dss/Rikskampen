package com.kampen.riks.app.rikskampen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.kampen.riks.app.rikskampen.leader.activity.MainLeaderActivity;
import com.kampen.riks.app.rikskampen.utils.Constants;

public class LoginActivity extends AppCompatActivity {


    private EditText mUserEmail;
    private EditText mUserPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserEmail=findViewById(R.id.editText_email);
        mUserPass=findViewById(R.id.editText_pass);

    }


    public void onLoginClick(View view) {


        if(validateData( )) {

            Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), LoginSignupActivity.class);
        startActivity(intent);
        finish();

    }

    private  boolean  validateData( )
    {
        if(mUserEmail.getText().toString().trim().length()==0)
        {
            mUserEmail.requestFocus();
            mUserEmail.setError("Enter email");
            return false;

        }

        if(!Constants.isValidEmailId(mUserEmail.getText().toString()))
        {
            mUserEmail.requestFocus();
            mUserEmail.setError("Enter valid email");
            return false;
        }

        if(mUserPass.getText().toString().trim().length()==0)
        {
            mUserEmail.requestFocus();
            mUserEmail.setError("Enter password");
            return false;

        }

        return  true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mUserEmail=null;
        mUserPass=null;
    }
}
