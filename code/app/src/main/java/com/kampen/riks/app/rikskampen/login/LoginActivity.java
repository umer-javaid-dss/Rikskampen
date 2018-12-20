package com.kampen.riks.app.rikskampen.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.kampen.riks.app.rikskampen.ForgotPasswordActivity;
import com.kampen.riks.app.rikskampen.LoginSignupActivity;
import com.kampen.riks.app.rikskampen.MyApplication;
import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.MainLeaderActivity;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.plans.SelectPlansActivity;
import com.kampen.riks.app.rikskampen.utils.Constants;
import com.kampen.riks.app.rikskampen.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {


    private EditText mUserEmail;
    private EditText mUserPass;
    private LoginPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mUserEmail = findViewById(R.id.editText_email);
        mUserPass = findViewById(R.id.editText_pass);

        mPresenter = new LoginPresenter(LoginActivity.this);

    }

    public void onLoginClick(View view) {


        if (validateData()) {


            ProgressManager.showProgress(LoginActivity.this, "Please wait...");

            String tempUserEmail = mUserEmail.getText().toString().trim();
            String tempUserPass = mUserPass.getText().toString().trim();
            try {
                mPresenter.performLogin(tempUserEmail, tempUserPass);
            } catch (Exception ex) {
                ex.toString();
            }

        }

    }

    @Override
    public void onBackPressed() {

        moveNext(LoginSignupActivity.class);

    }

    private boolean validateData() {
        if (mUserEmail.getText().toString().trim().length() == 0) {
            mUserEmail.requestFocus();
            mUserEmail.setError("Enter email");
            return false;

        }

        if (!Constants.isValidEmailId(mUserEmail.getText().toString())) {
            mUserEmail.requestFocus();
            mUserEmail.setError("Enter valid email");
            return false;
        }

        if (mUserPass.getText().toString().trim().length() == 0) {
            mUserEmail.requestFocus();
            mUserEmail.setError("Enter password");
            return false;

        }

        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mUserEmail = null;
        mUserPass = null;
    }


    public void onBackClick(View view) {
        moveNext(LoginActivity.class);
    }

    public void onForgotPasswordClick(View view) {

        moveNext(ForgotPasswordActivity.class);

    }


    @Override
    public void setLogin(String message) {


        ProgressManager.hideProgress();

        SaveSharedPreference.setLoggedIn(LoginActivity.this,true,mUserEmail.getText().toString(),mUserPass.getText().toString());

        moveNext(SelectPlansActivity.class);


    }

    @Override
    public void setLoginFailed(String message) {


        ProgressManager.hideProgress();

        MyApplication.showSimpleSnackBar(LoginActivity.this, message);

    }


    public void moveNext(Class value) {
        Intent intent = new Intent(getApplicationContext(), value);
        startActivity(intent);
        finish();
    }

    @Override
    public void setmPresenter(LoginContract.Presenter mPresenter) {


    }
}
