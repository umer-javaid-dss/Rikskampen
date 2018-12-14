package com.kampen.riks.app.rikskampen.leader.activity.fragments.account;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riks.app.rikskampen.MyApplication;
import com.kampen.riks.app.rikskampen.R;

import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.kampen.riks.app.rikskampen.user.module.DB_User_Module;
import com.kampen.riks.app.rikskampen.utils.Constants;


import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


import adil.dev.lib.materialnumberpicker.dialog.GenderPickerDialog;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import in.mayanknagwanshi.imagepicker.imageCompression.ImageCompressionListener;
import in.mayanknagwanshi.imagepicker.imagePicker.ImagePicker;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class EditProfileActivity extends AppCompatActivity {



    private  TextView mUserDOB;
    private  TextView mUserHeight;

    private  TextView mUserWeight;
    private TextView mUserGender;

    private Realm mRealm;



    private ImagePicker imagePicker;

    private ImageView  mProfileImage;


    private  DB_User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        init();


        imagePicker = new ImagePicker();

        setUpDB();


        setUser();
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



    private  void setUser()
    {
        mUser=MyApplication.tempUser;

        if(mUser!=null)
        {
            if(mUser!=null )
            {

                byte []  profileData=mUser.getProfilePicData();

                if(profileData!=null)
                {
                    Bitmap bmp = BitmapFactory.decodeByteArray(profileData, 0, profileData.length);
                    mProfileImage.setImageBitmap(bmp);
                }

                final String    dob  =mUser.getDob();
                final int       height_ft= mUser.getHeight_in_feet();

                final int    weight=mUser.getWeight();
                final int    gender=mUser.getUser_gender();

                mUserDOB.setText(dob);
                mUserHeight.setText(height_ft+" cm");
                mUserWeight.setText(weight+" kg");
                mUserGender.setText(""+(gender==1?"Male":"Female"));


            }

        }
    }




    private  void updateProfilePic(final Bitmap profilePic)
    {


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        profilePic.compress(Bitmap.CompressFormat.PNG, 70, stream);
        final byte[] byteArray = stream.toByteArray();


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                DB_User db_user = mUser;

                db_user.setProfilePicData(byteArray);

                realm.insertOrUpdate(db_user);

                MyApplication.tempUser=db_user;

            }
        });
    }




    private  void updateGender(final int gender)
    {




        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                DB_User db_user = mUser;

                db_user.setUser_gender(gender);

                realm.insertOrUpdate(db_user);

                MyApplication.tempUser=db_user;

            }
        });
    }


    private  void updateDOB(final String DOB)
    {


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                DB_User db_user = mUser;

                db_user.setDob(DOB);
                realm.insertOrUpdate(db_user);
                MyApplication.tempUser=db_user;
            }
        });
    }


    private  void updateHeight(final int heightInCM)
    {


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                DB_User db_user = mUser;

                db_user.setHeight_in_cm(heightInCM);
                realm.insertOrUpdate(db_user);
                MyApplication.tempUser=db_user;
            }
        });
    }


    private  void updateWeight(final int weightInKg)
    {


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                DB_User db_user = mUser;

                db_user.setWeight(weightInKg);
                realm.insertOrUpdate(db_user);
                MyApplication.tempUser=db_user;
            }
        });
    }




    @Override
    public void onBackPressed() {

        finish();
    }


    public void onEditProfileClick(View view) {


        isReadStoragePermissionGranted();


    }

    private  void init()
    {

        mProfileImage=findViewById(R.id.profileImage);



        mUserGender=findViewById(R.id.genderValue);
        mUserDOB=findViewById(R.id.dobValue);
        mUserHeight=findViewById(R.id.heightValue);

        mUserWeight=findViewById(R.id.weightValue);

    }

    private void manageClicks()
    {
        mUserDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onAgeClick(v);
            }
        });

       /* mUserHeightInFeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onHeightInFeetClick(v);
            }
        });*/

       /* mUserHeightInInches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onHeightInIncheClick(v);
            }
        });*/

        mUserWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onWeightClick(v);
            }
        });


       /* mSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateData( ))
                {

                    if(editProfileLocal())
                    {
                        Toast.makeText(EditProfileActivity.this, "Data saved", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });*/

    }


    private  boolean  validateData( )
    {



        if(mUserDOB.getText().toString().trim().length()==0)
        {
            mUserDOB.requestFocus();
            mUserDOB.setError("Select date of birth");
            return false;

        }

        if(mUserHeight.getText().toString().trim().length()==0)
        {
            mUserHeight.requestFocus();
            mUserHeight.setError("Enter height in feet");
            return false;

        }

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






    public void onAgeClick(View view) {


        Constants.hideSoftKeyboard(view,this);

        final EditText DOB= (EditText) view;


        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(this)
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
                .show();


    }

    public void onHeightInFeetClick(View view) {

        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(this)
                .minValue(1)
                .maxValue(10)
                .defaultValue(5)
                .backgroundColor(Color.WHITE)
                .separatorColor(Color.TRANSPARENT)
                .textColor(Color.BLACK)
                .textSize(20)
                .enableFocusability(false)
                .wrapSelectorWheel(true)
                .build();


        new AlertDialog.Builder(this)
                .setTitle("Height in Feet")
                .setView(numberPicker)
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mUserHeight.setText(numberPicker.getValue()+"");
                    }
                })
                .show();

    }

    public void onHeightInIncheClick(View view) {


        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(this)
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

                       // mUserHeightInInches.setText(numberPicker.getValue()+"");
                    }
                })
                .show();

    }

    public void onWeightClick(View view) {

        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(this)
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

                        mUserWeight.setText(numberPicker.getValue()+" kg");

                        updateWeight(numberPicker.getValue());
                    }
                })
                .show();

    }



    public  void  startPickingImage()
    {
        try {
            imagePicker.withActivity(EditProfileActivity.this) //calling from activity
                    //.withFragment(W) //calling from fragment
                    .chooseFromGallery(false) //default is true
                    //.chooseFromCamera(false) //default is true
                    .withCompression(true) //default is true
                    .start();
        }catch (Exception ex)
        {
            ex.toString();
        }
    }




    public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                isWriteStoragePermissionGranted();

                return true;
            } else {


                ActivityCompat.requestPermissions(EditProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation


            startPickingImage();

            return true;
        }
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                startPickingImage();

                return true;
            } else {


                //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation

            startPickingImage();

            return true;
        }
    }




    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:

                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){


                }else{


                }
                break;

            case 3:


                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){

                    try {
                        imagePicker.withActivity(EditProfileActivity.this) //calling from activity

                                .chooseFromGallery(false) //default is true

                                .withCompression(true) //default is true
                                .start();
                    }catch (Exception ex)
                    {
                        ex.toString();
                    }

                }else{

                }
                break;
        }
    }





    public void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (requestCode == ImagePicker.SELECT_IMAGE && resultCode == Activity.RESULT_OK) {

            imagePicker.addOnCompressListener(new ImageCompressionListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onCompressed(String filePath) {


                    Bitmap selectedImage = BitmapFactory.decodeFile(filePath);

                    mProfileImage.setImageBitmap(selectedImage);


                    updateProfilePic(selectedImage);



                }
            });
        }

        String filePath = imagePicker.getImageFilePath(data);
        if (filePath != null) {

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void onGenderClick(View view) {



        onSexClick();

    }

    public void onSexClick() {



        try {

            GenderPickerDialog dialog = new GenderPickerDialog(this);
            dialog.setOnSelectingGender(new GenderPickerDialog.OnGenderSelectListener() {
                @Override
                public void onSelectingGender(String value) {
                    mUserGender.setText(value);

                    int genderInt=1;

                    if(value.toLowerCase().equals("male"))
                    {
                        genderInt=1;
                    }
                    else
                    {
                        genderInt=2;
                    }

                    updateGender(genderInt);
                }
            });
            dialog.show();
        }catch (Exception ex)
        {
            ex.toString();
        }

    }

    public void onDOBClick(View view) {




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


                updateDOB(mUserDOB.getText().toString());
            }

        };
        new DatePickerDialog(EditProfileActivity.this, date, 1990, 0, 1).show();


    }


    public void onHeightClick(View view) {


        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts_height, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);


        userInput.setInputType(InputType.TYPE_CLASS_NUMBER);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                mUserHeight.setText(userInput.getText()+" cm");


                                try {
                                    updateHeight(Integer.parseInt(userInput.getText().toString()));
                                }catch (NumberFormatException ex)
                                {

                                }

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });


        AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.show();

    }

    public void onBackClick(View view) {

        finish();

    }
}
