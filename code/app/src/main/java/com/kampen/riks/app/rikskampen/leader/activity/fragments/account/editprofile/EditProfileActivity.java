package com.kampen.riks.app.rikskampen.leader.activity.fragments.account.editprofile;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
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

import com.kampen.riks.app.rikskampen.api.remote_api.models.RemoteUser;
import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.kampen.riks.app.rikskampen.utils.Constants;
import com.kampen.riks.app.rikskampen.utils.Custom_Progress_Module.ProgressManager;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


import adil.dev.lib.materialnumberpicker.dialog.GenderPickerDialog;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import in.mayanknagwanshi.imagepicker.imageCompression.ImageCompressionListener;
import in.mayanknagwanshi.imagepicker.imagePicker.ImagePicker;

public class EditProfileActivity extends AppCompatActivity implements   EditProfileContract.View {



    private  TextView mUserDOB;
    private  TextView mUserHeight;

    private  TextView mUserWeight;
    private TextView mUserGender;

    private  TextView mfNameValue;

    private  TextView mlNameValue;

    private  TextView mPassValue;


    private ImagePicker imagePicker;

    private ImageView  mProfileImage;


    private  DB_User mUser;

    private  RemoteUser mRUser;


    private   EditProfilePresenter  mEditProfilePresenter;


    private Context   mContext;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        init();


        imagePicker = new ImagePicker();



        mEditProfilePresenter=new EditProfilePresenter(EditProfileActivity.this);

        setUser();
    }





    private  void setUser()
    {


        mRUser=new RemoteUser();

       mUser=mEditProfilePresenter.provideUserLocal(mContext);



       /* if(mUser==null) {

            RealmConfiguration config = new RealmConfiguration.Builder()
                    .name(getPackageName() + ".realm")
                    .schemaVersion(2)
                    .modules(new DB_User_Module())
                    .build();

            Realm mRealm = Realm.getInstance(config);


            String[] params = SaveSharedPreference.getLoggedParams(mContext);
            RealmResults<DB_User> userList = mRealm.where(DB_User.class)
                    .equalTo("_email", params[0])
                    .equalTo("_pass", params[1])
                    .findAll();


            DB_User user = null;

            if (userList != null && userList.size() > 0) {

                mUser = userList.get(0);
            }


        }*/


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


                mfNameValue.setText(mUser.getF_name());
                mlNameValue.setText(mUser.getL_name());

                mUserDOB.setText(dob);
                mUserHeight.setText(height_ft+" cm");
                mUserWeight.setText(weight+" kg");
                mUserGender.setText(""+(gender==1?"Male":"Female"));
                mPassValue.setText(mUser.getPass());


            }

        }
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

        mContext=EditProfileActivity.this;

        mProfileImage=findViewById(R.id.profileImage);



        mUserGender=findViewById(R.id.genderValue);
        mUserDOB=findViewById(R.id.dobValue);
        mUserHeight=findViewById(R.id.heightValue);

        mUserWeight=findViewById(R.id.weightValue);

        mfNameValue=findViewById(R.id.fNameValue);

        mlNameValue=findViewById(R.id.lNameValue);

        mPassValue=findViewById(R.id.passValue);


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
                        mUser.setWeight(numberPicker.getValue());
                        //upDateUserOnServer(mUser);
                        //ProgressManager.showProgress(EditProfileActivity.this,"Updating user");

                        //mEditProfilePresenter.performEditProfile(mUser.getF_name(),mUser.getL_name(),mUser.getPass(),mUser.getEmail());
                    }
                })
                .show();

    }



    public  void  startPickingImage()
    {
        try {
            imagePicker.withActivity(EditProfileActivity.this) //calling from activity
                    //.withFragment(W) //calling from fragment
                    .chooseFromGallery(true) //default is true
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


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
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

                                .chooseFromGallery(true) //default is true

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

                    int genderInt=1;

                    if(value.toLowerCase().equals("male"))
                    {
                        genderInt=1;
                    }
                    else
                    {
                        genderInt=2;
                    }

                    mUser.setUser_gender(genderInt);
                    //upDateUserOnServer(mUser);
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

                mUser.setDob(sdf.format(myCalendar.getTime()));

                //upDateUserOnServer(mUser);
            }

        };
        new DatePickerDialog(EditProfileActivity.this, date, 1990, 0, 1).show();


    }


    public void onHeightClick(View view) {


        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts_height, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);


        userInput.setInputType(InputType.TYPE_CLASS_NUMBER);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(R.string.edit_profile_dialog_pos_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {


                                mUserHeight.setText(userInput.getText()+" cm");
                                mUser.setHeight_in_cm(Integer.parseInt(userInput.getText().toString()));
                                //upDateUserOnServer(mUser);


                            }
                        })
                .setNegativeButton(R.string.edit_profile_dialog_nag_button,
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

    public void onFNameClick(View view) {



        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts_height, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        final TextView titleInput = (TextView) promptsView.findViewById(R.id.textView1);



        userInput.setInputType(InputType.TYPE_CLASS_TEXT);


        titleInput.setText("Enter your first name");


        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(R.string.edit_profile_dialog_pos_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                mfNameValue.setText(userInput.getText());
                                mUser.setF_name(userInput.getText().toString());


                                  mRUser=Constants.DB_To_R_USER(mUser);
                                  mRUser.setFirstname(userInput.getText().toString());
                                  updateUserToServer(mRUser);



                            }
                        })
                .setNegativeButton(R.string.edit_profile_dialog_nag_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });


        AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.show();


    }







    public   void updateUserToServer(RemoteUser user)
    {
        ProgressManager.showProgress(EditProfileActivity.this,"Updating user");

        mEditProfilePresenter.performEditProfile(user.getFirstname(),user.getLastname(),user.getPassword(),user.getEmail());
    }


    public void onLNameClick(View view) {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts_height, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        final TextView titleInput = (TextView) promptsView.findViewById(R.id.textView1);



        userInput.setInputType(InputType.TYPE_CLASS_TEXT);


        titleInput.setText("Enter your last name");


        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(R.string.edit_profile_dialog_pos_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                mlNameValue.setText(userInput.getText());


                                 //mUser.setL_name(userInput.getText().toString());

                                mRUser=Constants.DB_To_R_USER(mUser);
                                mRUser.setLastname(userInput.getText().toString());
                                updateUserToServer(mRUser);


                            }
                        })
                .setNegativeButton(R.string.edit_profile_dialog_nag_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });


        AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.show();


    }






    public void onPassClick(View view) {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompts_height, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        final TextView titleInput = (TextView) promptsView.findViewById(R.id.textView1);



        userInput.setInputType(InputType.TYPE_CLASS_TEXT);


        titleInput.setText("Change your password");


        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(R.string.edit_profile_dialog_pos_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                mPassValue.setText(userInput.getText());


                                mUser.setPass(userInput.getText().toString());
                                //upDateUserOnServer(mUser);

                            }
                        })
                .setNegativeButton(R.string.edit_profile_dialog_nag_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });


        AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.show();


    }

    @Override
    public void setEditProfile(String message) {

        mEditProfilePresenter.updateUserLocalWithRemoteUser(mRUser,mUser);

        ProgressManager.hideProgress();
        MyApplication.showSimpleSnackBar(mContext, message);

    }

    @Override
    public void setEditProfileFailed(String message) {

        ProgressManager.hideProgress();
        MyApplication.showSimpleSnackBar(mContext, message);

    }

    @Override
    public void setPresenter(EditProfileContract.Presenter mPresenter) {



    }
}
