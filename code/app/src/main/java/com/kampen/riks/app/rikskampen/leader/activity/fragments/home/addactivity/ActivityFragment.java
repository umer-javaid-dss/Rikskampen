package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.kampen.riks.app.rikskampen.MyApplication;
import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.adapter.DailyPicAdapter;
import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.kampen.riks.app.rikskampen.utils.Constants;

import adil.dev.lib.materialnumberpicker.dialog.GenderPickerDialog;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import in.mayanknagwanshi.imagepicker.imageCompression.ImageCompressionListener;
import in.mayanknagwanshi.imagepicker.imagePicker.ImagePicker;
import io.realm.Realm;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 */
public class ActivityFragment extends Fragment {

    private  EditText mUserFname;
    private  EditText mUserLname;
    private  EditText mUserEmail;
    private  EditText mUserPass;
    private  EditText mUserDOB;
    private  EditText mUserHeightInFeet;
    private  EditText mUserHeightInInches;
    private  EditText mUserWeight;
    private  EditText mUserGender;

    private Realm mRealm;

    private  View     mSaveData;

    private ImagePicker imagePicker;


    private RecyclerView  dailyPickRecyclerView;
    private DailyPicAdapter dailyPicAdapter;


    public ActivityFragment() {

    }

    public static ActivityFragment newInstance() {
        ActivityFragment fragment = new ActivityFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_account, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //init(view);

        dailyPickRecyclerView = (RecyclerView) view.findViewById(R.id.dailyPicRV);

        dailyPicAdapter=new DailyPicAdapter(this);

        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        mLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);

        dailyPickRecyclerView.setLayoutManager(mLayoutManager1);

        dailyPickRecyclerView.setAdapter(dailyPicAdapter);

        imagePicker = new ImagePicker();



    }


    private  void init(View view)
    {
       /* mUserFname=view.findViewById(R.id.editText_fName);
        mUserLname=view.findViewById(R.id.editText_lName);
        mUserEmail=view.findViewById(R.id.editText_email);
        mUserPass=view.findViewById(R.id.editText_pass);
        mUserDOB=view.findViewById(R.id.editText_Age);
        mUserHeightInFeet=view.findViewById(R.id.editText_Height_F);
        mUserHeightInInches=view.findViewById(R.id.editText_Height_I);
        mUserWeight=view.findViewById(R.id.editText_Weight);
        mUserGender=view.findViewById(R.id.editText_Sex);
        mSaveData=view.findViewById(R.id.button_signup);

        manageClicks();

        setProfileLocal();*/
    }

    private void manageClicks()
    {
        mUserDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onAgeClick(v);
            }
        });
        mUserHeightInFeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onHeightInFeetClick(v);
            }
        });

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
                        Toast.makeText(getActivity(), "Data saved", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

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
           mUserHeightInFeet.setText(height_ft+"");
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
        final int    height_ft= Integer.parseInt(mUserHeightInFeet.getText().toString());
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
                db_user.setHeight_in_cm(height_ft);
                db_user.setHeight_in_inches(height_in);
                db_user.setHeight_unit("ft");
                db_user.setWeight(weight);
                db_user.setWeight_unit("kg");


            }
        });

        return true;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

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

        if(mUserHeightInFeet.getText().toString().trim().length()==0)
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



    public void onSexClick(View view) {

        final EditText gender= (EditText) view;

        try {

            GenderPickerDialog dialog = new GenderPickerDialog(getActivity());
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


        Constants.hideSoftKeyboard(view,getActivity());

        final EditText DOB= (EditText) view;


        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(getActivity())
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


        new AlertDialog.Builder(getActivity())
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

        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(getActivity())
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


        new AlertDialog.Builder(getActivity())
                .setTitle("Height in Feet")
                .setView(numberPicker)
                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mUserHeightInFeet.setText(numberPicker.getValue()+"");
                    }
                })
                .show();

    }

    public void onHeightInIncheClick(View view) {


        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(getActivity())
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


        new AlertDialog.Builder(getActivity())
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

        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(getActivity())
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


        new AlertDialog.Builder(getActivity())
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



//image picker


    public void onAddPicture( ) {

        isReadStoragePermissionGranted();



    }


    public  void  startPickingImage()
    {
        try {
            imagePicker.withActivity(getActivity()) //calling from activity
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
            if (checkSelfPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                isWriteStoragePermissionGranted();

                return true;
            } else {


                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
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
            if (checkSelfPermission(getActivity(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
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
                        imagePicker.withActivity(getActivity()) //calling from activity

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

                    //Toast.makeText(getActivity(), "Image Captured", Toast.LENGTH_SHORT).show();

                    dailyPicAdapter.addYourDailyPick(filePath);

                }
            });
        }

        String filePath = imagePicker.getImageFilePath(data);
        if (filePath != null) {

        }
        super.onActivityResult(requestCode, resultCode, data);
    }








}
