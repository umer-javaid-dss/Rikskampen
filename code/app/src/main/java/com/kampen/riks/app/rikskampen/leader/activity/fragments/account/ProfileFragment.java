package com.kampen.riks.app.rikskampen.leader.activity.fragments.account;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.kampen.riks.app.rikskampen.LoginSignupActivity;
import com.kampen.riks.app.rikskampen.MyApplication;
import com.kampen.riks.app.rikskampen.R;


import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;


import io.realm.Realm;

public class ProfileFragment extends Fragment {




    private  View  mProfileLayout;

    private Realm mRealm;


    private  View  mLogoutButton;


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

        manageClicks();
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
    }






}
