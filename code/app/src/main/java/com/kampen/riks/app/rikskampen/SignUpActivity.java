package com.kampen.riks.app.rikskampen;

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
import android.widget.Toast;

import com.kampen.riks.app.rikskampen.leader.activity.MainLeaderActivity;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.account.EditProfileActivity;
import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.kampen.riks.app.rikskampen.user.module.DB_User_Module;
import com.kampen.riks.app.rikskampen.utils.Constants;
import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import adil.dev.lib.materialnumberpicker.dialog.GenderPickerDialog;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class SignUpActivity extends AppCompatActivity {




    private  EditText mUserFname;

    private  EditText mUserEmail;
    private  EditText mUserPass;
    private  EditText mUserPassC;
    private  EditText mUserDOB;
    private  EditText mUserHeight;

    /*private  EditText mUserHeightInFeet;
    private  EditText mUserHeightInInches;*/
    private  EditText mUserWeight;
    private  EditText mUserGender;

    private Realm mRealm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mUserFname=findViewById(R.id.editText_fullName);
        //mUserLname=findViewById(R.id.editText_lName);
        mUserEmail=findViewById(R.id.editText_email);
        mUserPass=findViewById(R.id.editText_pass);
        mUserPassC=findViewById(R.id.editText_pass_c);
        mUserDOB=findViewById(R.id.editText_Age);
        mUserHeight=findViewById(R.id.editTextHeight);

        //mUserHeightInInches=findViewById(R.id.editText_Height_I);
        mUserWeight=findViewById(R.id.editText_Weight);
        mUserGender=findViewById(R.id.editText_Sex);


        setUpDB();
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

        if(mUserDOB.getText().toString().trim().length()==0)
        {
            mUserDOB.requestFocus();
            mUserDOB.setError("Select date of birth");
            return false;

        }

       /* if(mUserHeightInFeet.getText().toString().trim().length()==0)
        {
            mUserHeightInFeet.requestFocus();
            mUserHeightInFeet.setError("Enter height in feet");
            return false;

        }

        if(mUserHeightInInches.getText().toString().trim().length()==0)
        {
            mUserHeightInInches.requestFocus();
            mUserHeightInInches.setError("Enter height in feet");
            return false;

        }*/

        if(mUserWeight.getText().toString().trim().length()==0)
        {
            mUserWeight.requestFocus();
            mUserWeight.setError("Enter weight in lbs");
            return false;

        }

        if(mUserGender.getText().toString().trim().length()==0)
        {
            mUserGender.requestFocus();
            mUserGender.setError("Select gender");
            return false;

        }



        return  true;
    }




    public void onNextClick(View view) {

        if(validateData( )) {


            if(createProfileLocal()) {

                SaveSharedPreference.setLoggedIn(getApplicationContext(), true,MyApplication.tempUser.getEmail(),MyApplication.tempUser.getPass());

                Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }


    public void onBackClick(View view) {

        onBackPressed();
    }


    private  boolean createProfileLocal()
    {



       final String fName= mUserFname.getText().toString();
       //final String lName= mUserLname.getText().toString();
       final String email= mUserEmail.getText().toString();
       final String pass =mUserPass.getText().toString();
       final int    age  =0;

        final String dob=mUserDOB.getText().toString();

       final int    height_ft= Integer.parseInt(mUserHeight.getText().toString());
      // final int    height_in= Integer.parseInt(mUserHeightInInches.getText().toString());
       final int    weight=Integer.parseInt(mUserWeight.getText().toString());

        final RealmResults<DB_User> user = mRealm.where(DB_User.class)
                .equalTo("email",email.trim())
                .and()
                .equalTo("pass",pass.trim())
                .findAll();

        if(user!=null && user.size()>0) {

            Toast.makeText(this, "user already exits!", Toast.LENGTH_SHORT).show();
            return false;
        }


       int temGender=1;

       if(mUserGender.getText().toString().toLowerCase().equals("male"))
       {
           temGender=1;
       }
       else
       {
           temGender=2;
       }
       final int    gender=temGender;

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                DB_User db_user = realm.createObject(DB_User.class);
                db_user.setId("12345");
                db_user.setAge(age);
                db_user.setDob(dob);
                db_user.setEmail(email);
                db_user.setPass(pass);
                db_user.setF_name(fName);
                db_user.setL_name("");
                db_user.setProfile_image("");
                db_user.setRole("c");
                db_user.setUser_gender(gender);
                db_user.setHeight_in_feet(height_ft);
                //db_user.setHeight_in_inches(height_in);
                db_user.setHeight_unit("ft");
                db_user.setWeight(weight);
                db_user.setWeight_unit("kg");


                MyApplication.tempUser=db_user;


            }
        });

        return true;
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

       /* final EditText DOB= (EditText) view;


        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(SignUpActivity.this)
                .minValue(1)
                .maxValue(120)
                .defaultValue(30)
                .backgroundColor(Color.WHITE)
                .separatorColor(Color.TRANSPARENT)
                .textColor(Color.BLACK)
                .textSize(20)
                .enableFocusability(false)
                .wrapSelectorWheel(true)
                .build();


        new AlertDialog.Builder(this)
                .setTitle("Your Age")
                .setView(numberPicker)
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DOB.setText(numberPicker.getValue()+"");
                    }
                })
                .show();*/



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



    /*public void onHeightInIncheClick(View view) {


        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(SignUpActivity.this)
                .minValue(1)
                .maxValue(12)
                .defaultValue(1)
                .backgroundColor(Color.WHITE)
                .separatorColor(Color.TRANSPARENT)
                .textColor(Color.BLACK)
                .textSize(20)
                .enableFocusability(false)
                .wrapSelectorWheel(true)
                .build();


        new AlertDialog.Builder(this)
                .setTitle("Height in Inches")
                .setView(numberPicker)
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mUserHeightInInches.setText(numberPicker.getValue()+"");
                    }
                })
                .show();

    }*/

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


}
