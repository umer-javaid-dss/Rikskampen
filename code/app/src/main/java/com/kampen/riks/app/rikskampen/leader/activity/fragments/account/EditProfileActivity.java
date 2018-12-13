package com.kampen.riks.app.rikskampen.leader.activity.fragments.account;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
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
import android.widget.Toast;

import com.kampen.riks.app.rikskampen.MyApplication;
import com.kampen.riks.app.rikskampen.R;

import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.kampen.riks.app.rikskampen.utils.Constants;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


import adil.dev.lib.materialnumberpicker.dialog.GenderPickerDialog;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import in.mayanknagwanshi.imagepicker.imageCompression.ImageCompressionListener;
import in.mayanknagwanshi.imagepicker.imagePicker.ImagePicker;
import io.realm.Realm;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

public class EditProfileActivity extends AppCompatActivity {


    private EditText mUserFname;
    private  EditText mUserLname;
    private  EditText mUserEmail;
    private  EditText mUserPass;
    private  TextView mUserDOB;
    private  TextView mUserHeight;
    private  EditText mUserHeightInInches;
    private  TextView mUserWeight;
    private TextView mUserGender;

    private Realm mRealm;

    private View mSaveData;


    private ImagePicker imagePicker;

    private ImageView  mProfileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        init();


        imagePicker = new ImagePicker();
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

        mUserHeightInInches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onHeightInIncheClick(v);
            }
        });

        mUserWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onWeightClick(v);
            }
        });


        mSaveData.setOnClickListener(new View.OnClickListener() {
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
        });

    }


    private  boolean  validateData( )
    {

        if(mUserFname.getText().toString().trim().length()==0)
        {
            mUserFname.requestFocus();
            mUserFname.setError("Enter first name");
            return false;

        }

        if(mUserLname.getText().toString().trim().length()==0)
        {
            mUserLname.requestFocus();
            mUserLname.setError("Enter last name");
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

        if(mUserHeightInInches.getText().toString().trim().length()==0)
        {
            mUserHeightInInches.requestFocus();
            mUserHeightInInches.setError("Enter height in feet");
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

    private  boolean setProfileLocal()
    {


        DB_User user=MyApplication.tempUser;

        if(user!=null )
        {
            final String fName=user.getF_name();
            final String lName= user.getL_name();
            final String email= user.getEmail();
            final String pass =user.getPass();
            final int    age  =user.getAge();
            final int    height_ft= user.getHeight_in_feet();
            final int    height_in= user.getHeight_in_inches();
            final int    weight=user.getWeight();
            final int    gender=user.getUser_gender();

            mUserFname.setText(fName);
            mUserLname.setText(lName);
            mUserEmail.setText(email);
            mUserPass.setText(pass);
            mUserDOB.setText(age+"");
            mUserHeight.setText(height_ft+"");
            mUserHeightInInches.setText(height_in+"");
            mUserWeight.setText(weight+"");
            mUserGender.setText(""+(gender==1?"Male":"Female"));
        }


        return true;
    }

    private  boolean editProfileLocal()
    {



        final String fName= mUserFname.getText().toString();
        final String lName= mUserLname.getText().toString();
        final String email= mUserEmail.getText().toString();
        final String pass =mUserPass.getText().toString();
        final int    age  =Integer.parseInt(mUserDOB.getText().toString());
        final int    height_ft= Integer.parseInt(mUserHeight.getText().toString());
        final int    height_in= Integer.parseInt(mUserHeightInInches.getText().toString());
        final int    weight=Integer.parseInt(mUserWeight.getText().toString());



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
                db_user.setEmail(email);
                db_user.setPass(pass);
                db_user.setF_name(fName);
                db_user.setL_name(lName);
                db_user.setProfile_image("");
                db_user.setRole("c");
                db_user.setUser_gender(gender);
                db_user.setHeight_in_feet(height_ft);
                db_user.setHeight_in_inches(height_in);
                db_user.setHeight_unit("ft");
                db_user.setWeight(weight);
                db_user.setWeight_unit("kg");


            }
        });

        return true;
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

                        mUserHeightInInches.setText(numberPicker.getValue()+"");
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
