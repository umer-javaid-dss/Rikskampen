package com.kampen.riks.app.rikskampen.leader.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.LeaderBordTab.LeaderBoardFragment;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.account.profile.ProfileFragment;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.HomeFragment;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.map.MapFragment;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.order_history.OrderHistoryFragment;
import com.kampen.riks.app.rikskampen.user.module.DB_User_Module;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainLeaderActivity extends AppCompatActivity {


    private Fragment[] mFragments=new Fragment[5];



    private   View mChatHeadView;

    WindowManager mWindowManager;


    private final static int CLICK_ACTION_THRESHOLD = 25;





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


        if(Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1234);
            }else
            {
                manageChatHead();
            }
        }
        else {

            manageChatHead();
        }



       // exportDatabase();


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

            Fragment f = getSupportFragmentManager().findFragmentById(R.id.frame_layout);

            if(f instanceof HomeFragment){
                mFragments[0].onActivityResult(requestCode, resultCode, data);
            }
            else if(f instanceof MapFragment)
            {
             mFragments[1].onActivityResult(requestCode, resultCode, data);

            }
        }catch (Exception ex)
        {
            ex.toString();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        if (mChatHeadView != null) mWindowManager.removeView(mChatHeadView);

        mFragments[0]=null;
        mFragments[1]=null;
        mFragments[2]=null;
        mFragments[3]=null;
        mFragments=null;
    }


    @Override
    protected void onStop() {
        super.onStop();


        if (mChatHeadView != null)
        {
            mChatHeadView.setVisibility(View.GONE);
        }


    }


    @Override
    protected void onResume() {
        super.onResume();

        if (mChatHeadView != null)
        {
            mChatHeadView.setVisibility(View.VISIBLE);
        }

    }

    public  void   manageChatHead()
    {




        mChatHeadView = LayoutInflater.from(this).inflate(R.layout.layout_live_video, null);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);


        params.gravity = Gravity.TOP | Gravity.LEFT;        //Initially view will be added to top-left corner
        params.x = 0;
        params.y = 100;

        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        try {
            mWindowManager.addView(mChatHeadView, params);
        }catch (Exception ex){

            ex.toString();
        }


        final ImageView chatHeadImage = (ImageView) mChatHeadView.findViewById(R.id.liveVideo);
        chatHeadImage.setOnTouchListener(new View.OnTouchListener() {
            private int lastAction;
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;

                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();

                        lastAction = event.getAction();
                        return true;
                    case MotionEvent.ACTION_UP:
                        //As we implemented on touch listener with ACTION_MOVE,
                        //we have to check if the previous action was ACTION_DOWN
                        //to identify if the user clicked the view or not.
                        if (isAClick(initialTouchX, event.getRawX(), initialTouchY,event.getRawY()))  {
                            //Open the chat conversation click.

                            Toast.makeText(MainLeaderActivity.this, "Live View Clicked", Toast.LENGTH_SHORT).show();

                        }
                        lastAction = event.getAction();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);

                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(mChatHeadView, params);
                        lastAction = event.getAction();
                        return true;
                }
                return false;
            }
        });


    }



    public void exportDatabase() {


        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(getPackageName() + ".realm")
                .schemaVersion(2)
                .modules(new DB_User_Module())
                .build();
        // init realm
        Realm realm = Realm.getInstance(config);

        File exportRealmFile = null;
        // get or create an "export.realm" file
        exportRealmFile = new File(getExternalCacheDir(), "export.realm");

        // if "export.realm" already exists, delete
        exportRealmFile.delete();

        // copy current realm to "export.realm"
        realm.writeCopyTo(exportRealmFile);

        realm.close();

        // init email intent and add export.realm as attachment
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, "kh.m.umerjavaid@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Ream data base");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        Uri u = Uri.fromFile(exportRealmFile);
        intent.putExtra(Intent.EXTRA_STREAM, u);

        // start email intent
        startActivity(Intent.createChooser(intent, "YOUR CHOOSER TITLE"));
    }


    private boolean isAClick(float startX, float endX, float startY, float endY) {
        float differenceX = Math.abs(startX - endX);
        float differenceY = Math.abs(startY - endY);
        return !(differenceX > CLICK_ACTION_THRESHOLD/* =5 */ || differenceY > CLICK_ACTION_THRESHOLD);
    }

}
