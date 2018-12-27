package com.kampen.riks.app.rikskampen.leader.activity.fragments.account.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ShareActionProvider;


import com.kampen.riks.app.rikskampen.LoginSignupActivity;
import com.kampen.riks.app.rikskampen.MyApplication;
import com.kampen.riks.app.rikskampen.R;


import com.kampen.riks.app.rikskampen.leader.activity.fragments.account.editprofile.EditProfileActivity;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.chat.ChatActivity;
import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.kampen.riks.app.rikskampen.user.module.DB_User_Module;
import com.kampen.riks.app.rikskampen.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;


import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ProfileFragment extends Fragment implements  ProfileContract.View{




    private  View  mProfileLayout;



    private ImageView profileImage;


    private  View  mLogoutButton,chatLayout;



    private   ProfilePresenter  mProfilePresenter;



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


        mProfilePresenter=new ProfilePresenter(ProfileFragment.this);



    }




    @Override
    public void onResume() {
        super.onResume();

        mProfilePresenter.getUserProfilePhoto(getActivity());

    }




    private  void manageClicks()
    {
        mProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);

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

                                ProgressManager.showProgress(getContext(),"Please wait...");
                               mProfilePresenter.performLogout(SaveSharedPreference.getUserToken(getContext()));

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


    @Override
    public void setProfile(DB_User user) {



             String  profilePath=user.getProfile_image();

             if(profilePath!=null && profilePath.length()>0) {
                 Bitmap bmp = BitmapFactory.decodeFile(profilePath);
                 profileImage.setImageBitmap(bmp);
             }



    }

    @Override
    public void setLogoutSuccess(String message) {


        ProgressManager.hideProgress();

        Intent intent = new Intent(getActivity(),LoginSignupActivity.class);
        startActivity(intent);

        SaveSharedPreference.setLoggedIn(getActivity(), false);
        getActivity().finish();


    }

    @Override
    public void setLogoutFailed(String message) {

        ProgressManager.hideProgress();

    }

    @Override
    public void setPresenter(ProfileContract.Presenter mPresenter) {


    }
}
