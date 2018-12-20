package com.kampen.riks.app.rikskampen.signup;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;


import com.kampen.riks.app.rikskampen.LoginSignupActivity;
import com.kampen.riks.app.rikskampen.MyApplication;
import com.kampen.riks.app.rikskampen.R;

import com.kampen.riks.app.rikskampen.leader.activity.MainLeaderActivity;

import com.kampen.riks.app.rikskampen.leader.activity.fragments.plans.PlanDetailActivity;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.plans.SelectPlansActivity;
import com.kampen.riks.app.rikskampen.login.LoginActivity;
import com.kampen.riks.app.rikskampen.utils.Constants;
import com.kampen.riks.app.rikskampen.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import adil.dev.lib.materialnumberpicker.dialog.GenderPickerDialog;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;


public class SignUpActivity extends AppCompatActivity implements  SignupContract.View{




    private  EditText mUserFname;
    private  EditText mUserLname;

    private  EditText mUserEmail;
    private  EditText mUserPass;
    private  EditText mUserPassC;
    private  EditText mUserDOB;
    private  EditText mUserWeight;



    private   SignupPresenter mSignupPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        initViews();

        mSignupPresenter=new SignupPresenter(SignUpActivity.this);
    }


    private void  initViews()
    {
        mUserFname=findViewById(R.id.editText_fName);
        mUserLname=findViewById(R.id.editText_lName);
        mUserEmail=findViewById(R.id.editText_email);
        mUserPass=findViewById(R.id.editText_pass);
        mUserPassC=findViewById(R.id.editText_pass_c);
        mUserDOB=findViewById(R.id.editText_Age);
        mUserWeight=findViewById(R.id.editText_Weight);
    }

    private  boolean  validateData( )
    {

        if(mUserFname.getText().toString().trim().length()==0)
        {
            mUserFname.requestFocus();
            mUserFname.setError("Enter first name");
            return false;

        }


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
            mUserPass.requestFocus();
            mUserPass.setError("Enter password");
            return false;

        }

        if(mUserPassC.getText().toString().trim().length()==0)
        {
            mUserPassC.requestFocus();
            mUserPassC.setError("Enter confirm password");
            return false;

        }

        String temp1=mUserPass.getText().toString();
        String temp2=mUserPassC.getText().toString();

        if(!temp1.equals(temp2))
        {
            mUserPass.requestFocus();
            mUserPass.setError("Password do not match");
            return false;
        }


        return  true;
    }




    public void onNextClick(View view) {

        if(validateData( )) {


            Constants.hideSoftKeyboard(view,SignUpActivity.this);

            ProgressManager.showProgress(SignUpActivity.this,"Please Wait...");

            final String fName= mUserFname.getText().toString();

            final String lName= mUserLname.getText().toString();

            final String email= mUserEmail.getText().toString();
            final String pass =mUserPass.getText().toString();

            mSignupPresenter.performSign_up(fName,lName,pass,email);




        }
    }


    public void onBackClick(View view) {

        onBackPressed();
    }




    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(),LoginSignupActivity.class);
        startActivity(intent);
        finish();
    }

    public void onSexClick(View view) {

        final EditText gender= (EditText) view;

        try {

            GenderPickerDialog dialog = new GenderPickerDialog(SignUpActivity.this);
            dialog.setOnSelectingGender(new GenderPickerDialog.OnGenderSelectListener() {
                @Override
                public void onSelectingGender(String value) {
                    gender.setText(value);
                }
            });
            dialog.show();
        }catch (Exception ex)
        {
            ex.toString();
        }

    }


    public void onAgeClick(View view) {


        Constants.hideSoftKeyboard(view,SignUpActivity.this);

        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy"; // your format
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

                mUserDOB.setText(sdf.format(myCalendar.getTime()));
            }

        };
        new DatePickerDialog(SignUpActivity.this, date, 1990, 0, 1).show();


    }




    public void onWeightClick(View view) {

        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(SignUpActivity.this)
                .minValue(1)
                .maxValue(200)
                .defaultValue(70)
                .backgroundColor(Color.WHITE)
                .separatorColor(Color.TRANSPARENT)
                .textColor(Color.BLACK)
                .textSize(20)
                .enableFocusability(false)
                .wrapSelectorWheel(true)
                .build();


        new AlertDialog.Builder(this)
                .setTitle("Weight in kg")
                .setView(numberPicker)
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mUserWeight.setText(numberPicker.getValue()+"");
                    }
                })
                .show();

    }



    @Override
    public void setSignup(String message) {

        ProgressManager.hideProgress();

        SaveSharedPreference.setLoggedIn(SignUpActivity.this,true,mUserEmail.getText().toString(),mUserPass.getText().toString());

        moveNext(SelectPlansActivity.class);
    }

    @Override
    public void setSignupFailed(String message) {

        ProgressManager.hideProgress();

        MyApplication.showSimpleSnackBar(SignUpActivity.this, message);

    }

    public void moveNext(Class value) {
        Intent intent = new Intent(getApplicationContext(), value);
        startActivity(intent);
        finish();
    }

    @Override
    public void setmPresenter(SignupContract.Presenter mPresenter) {

    }
}
