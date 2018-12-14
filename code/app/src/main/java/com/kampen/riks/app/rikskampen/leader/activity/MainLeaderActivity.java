package com.kampen.riks.app.rikskampen.leader.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import com.kampen.riks.app.rikskampen.LoginSignupActivity;
import com.kampen.riks.app.rikskampen.MyApplication;
import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.LeaderBordTab.LeaderBoardFragment;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.account.ProfileFragment;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.HomeFragment;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.map.MapFragment;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.OrderHistoryFragment;
import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;

public class MainLeaderActivity extends AppCompatActivity {


    private Fragment[] mFragments=new Fragment[5];





    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()) {
                case R.id.navigation_home:

                    if(mFragments[0]==null) {
                        mFragments[0]=HomeFragment.newInstance();
                    }
                    addFragment(mFragments[0]);

                    return  true;



                case R.id.navigation_map:


                    if(mFragments[1]==null) {
                        mFragments[1]=MapFragment.newInstance();
                    }
                    addFragment(mFragments[1]);
                    return  true;

                case R.id.navigation_order_history:
                    if(mFragments[2]==null) {
                        mFragments[2]= OrderHistoryFragment.newInstance();
                    }
                    addFragment(mFragments[2]);
                    return  true;

                case R.id.navigation_profile:

                    if(mFragments[3]==null) {
                        mFragments[3]= ProfileFragment.newInstance();
                    }
                    addFragment(mFragments[3]);
                    return  true;

                case R.id.navigation_me:

                    if(mFragments[4]==null) {
                        mFragments[4]= LeaderBoardFragment.newInstance();
                    }

                    addFragment(mFragments[4]);
                    return  true;


            }
            return false;
        }
    };


    private  void  addFragment( Fragment selectedFragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_leader);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

       try {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            mFragments[0]=HomeFragment.newInstance();
            transaction.replace(R.id.frame_layout,mFragments[0]);
            transaction.commit();
        }catch (Exception ex)
        {
            ex.toString();
        }
    }


    @Override
    public void onBackPressed() {


       finish();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Fragment f = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if(f instanceof HomeFragment)
        {
            if(mFragments[0]!=null)
            {
                mFragments[0].onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
        /*else if(f instanceof MapFragment)
        {
            if(mFragments[1]!=null)
            {
                mFragments[1].onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }*/




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {

            if (mFragments[0] != null) {
                mFragments[0].onActivityResult(requestCode, resultCode, data);
            }
        }catch (Exception ex)
        {
            ex.toString();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mFragments[0]=null;
        mFragments[1]=null;
        mFragments[2]=null;
        mFragments[3]=null;
        mFragments=null;
    }
}
