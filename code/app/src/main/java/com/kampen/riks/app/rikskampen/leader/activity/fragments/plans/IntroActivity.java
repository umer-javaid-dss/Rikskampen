package com.kampen.riks.app.rikskampen.leader.activity.fragments.plans;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.MainLeaderActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);





    }



    public void  onNextClick(View view)
    {

        Intent intent = new Intent(getApplicationContext(), SelectPlansActivity.class);
        startActivity(intent);
        finish();

    }


   /* @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }*/

}
