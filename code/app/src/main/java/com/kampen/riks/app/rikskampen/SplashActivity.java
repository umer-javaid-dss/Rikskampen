package com.kampen.riks.app.rikskampen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kampen.riks.app.rikskampen.leader.activity.MainLeaderActivity;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.LeaderBordTab.LeaderAdapterStar;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.plans.SelectPlansActivity;
import com.kampen.riks.app.rikskampen.user.model.DB_User;
import com.kampen.riks.app.rikskampen.user.module.DB_User_Module;
import com.kampen.riks.app.rikskampen.utils.SaveSharedPreference;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class SplashActivity extends AppCompatActivity {

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);



           if(SaveSharedPreference.getLoggedStatus(getApplicationContext()))
            {

                setUpDB();

                String[] params=SaveSharedPreference.getLoggedParams(getApplicationContext());

                final RealmResults<DB_User> user = mRealm.where(DB_User.class)
                        .equalTo("email",params[0].trim())
                        .and()
                        .equalTo("pass",params[1].trim())
                        .findAll();


                if(user.size()>0) {

                    MyApplication.tempUser = user.get(0);

                    Intent intent = new Intent(getApplicationContext(),MainLeaderActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(),LoginSignupActivity.class);
                    startActivity(intent);
                    finish();
                }

                /*Intent intent = new Intent(getApplicationContext(),SelectPlansActivity.class);
                startActivity(intent);
                finish();*/


            }
            else
            {
                Intent intent = new Intent(getApplicationContext(),LoginSignupActivity.class);
                startActivity(intent);
                finish();
            }



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

}
