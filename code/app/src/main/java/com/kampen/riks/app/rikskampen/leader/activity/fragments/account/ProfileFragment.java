package com.kampen.riks.app.rikskampen.leader.activity.fragments.account;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.kampen.riks.app.rikskampen.LoginSignupActivity;
import com.kampen.riks.app.rikskampen.MyApplication;
import com.kampen.riks.app.rikskampen.R;


import com.kampen.riks.app.rikskampen.leader.activity.fragments.chat.ChatActivity;
import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;


import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;
import io.realm.Realm;

public class ProfileFragment extends Fragment {




    private  View  mProfileLayout;

    private Realm mRealm;

    private ImageView profileImage;


    private  View  mLogoutButton,chatLayout;




    public ProfileFragment() {

    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mLogoutButton=view.findViewById(R.id.logoutButton);
        mProfileLayout=view.findViewById(R.id.profileLayout);
        chatLayout=view.findViewById(R.id.chatLayout);

        profileImage=view.findViewById(R.id.profileImage);


        manageClicks();


        setUser();
    }




    private  void setUser()
    {
       DB_User mUser=MyApplication.tempUser;

        if(mUser!=null)
        {
            if(mUser!=null )
            {

                byte []  profileData=mUser.getProfilePicData();

                if(profileData!=null)
                {
                    Bitmap bmp = BitmapFactory.decodeByteArray(profileData, 0, profileData.length);
                    profileImage.setImageBitmap(bmp);
                }




            }

        }
    }



    private  void manageClicks()
    {
        mProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
               // finish();


            }
        });


        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new AlertDialog.Builder(getActivity())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(),LoginSignupActivity.class);
                                startActivity(intent);
                                MyApplication.tempUser=null;
                                SaveSharedPreference.setLoggedIn(getActivity(), false);
                                getActivity().finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });



        chatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),ChatActivity.class);
                startActivity(intent);

            }
        });




    }








}
