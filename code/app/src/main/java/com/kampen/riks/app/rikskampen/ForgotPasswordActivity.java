package com.kampen.riks.app.rikskampen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kampen.riks.app.rikskampen.leader.activity.MainLeaderActivity;
import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.kampen.riks.app.rikskampen.user.module.DB_User_Module;
import com.kampen.riks.app.rikskampen.utils.Constants;
import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ForgotPasswordActivity extends AppCompatActivity {



    private EditText mUserPass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        mUserPass=findViewById(R.id.editText_pass);

        //setUpDB();

    }


    public void onForgotPassssssClick(View view) {



        if(validateData( ))
        {
            Toast.makeText(this, "Email send", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();

    }

    public void onBackClick(View view) {

        onBackPressed();
    }


    private  boolean  validateData( )
    {
        if(mUserPass.getText().toString().trim().length()==0)
        {
            mUserPass.requestFocus();
            mUserPass.setError("Enter email");
            return false;

        }



        return  true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


        mUserPass=null;
    }

}
