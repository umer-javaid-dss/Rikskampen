package com.kampen.riks.app.rikskampen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {


    private EditText mUserEmail;
    private EditText mUserPass;

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserEmail=findViewById(R.id.editText_email);
        mUserPass=findViewById(R.id.editText_pass);

        setUpDB();
        //addTestUser();
    }


    private void  setUpDB()
    {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(getPackageName() + ".realm")
                .schemaVersion(2)
                .modules(new DB_User_Module())
                .build();

        mRealm = Realm.getInstance(config);


    }

    public void onLoginClick(View view) {



        if(validateData( )) {

            String tempUserEmail=mUserEmail.getText().toString();
            String tempUserPass =mUserPass.getText().toString();


            final RealmResults<DB_User> user = mRealm.where(DB_User.class)
                    .equalTo("email",tempUserEmail.trim())
                    .and()
                    .equalTo("pass",tempUserPass.trim())
                    .findAll();


            if(user.size()>0) {

                MyApplication.tempUser=user.get(0);

                SaveSharedPreference.setLoggedIn(getApplicationContext(), true,MyApplication.tempUser.getEmail(),MyApplication.tempUser.getPass());

                Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
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

    public void addTestUser() {

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                DB_User db_user = realm.createObject(DB_User.class);
                db_user.setId("12345");
                db_user.setAge(29);
                db_user.setEmail("test@gmail.com");
                db_user.setPass("12345678");
                db_user.setF_name("Umer");
                db_user.setL_name("Javaid");
                db_user.setProfile_image("");
                db_user.setRole("c");
                db_user.setUser_gender(1);
                db_user.setHeight_in_cm(5);
                db_user.setHeight_in_inches(7);
                db_user.setHeight_unit("ft");
                db_user.setWeight(65);
                db_user.setWeight_unit("kg");


            }
        });
    }





    public void onBackClick(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void onForgotPasswordClick(View view) {

        Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
        startActivity(intent);
        finish();

    }
}
