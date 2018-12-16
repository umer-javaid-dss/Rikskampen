package com.kampen.riks.app.rikskampen.leader.activity.fragments.plans;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.MainLeaderActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SelectPlansActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_plans);
    }


    public void onPlanOneClick(View view) {


        Intent intent = new Intent(getApplicationContext(), PlanDetailActivity.class);
        intent.putExtra("value",1);
        startActivity(intent);
        finish();

    }

    public void onPlanTwoClick(View view) {

        Intent intent = new Intent(getApplicationContext(), PlanDetailActivity.class);
        intent.putExtra("value",2);
        startActivity(intent);
        finish();

    }

    public void onPlanFourClick(View view) {

        Intent intent = new Intent(getApplicationContext(), PlanDetailActivity.class);
        intent.putExtra("value",4);
        startActivity(intent);
        finish();

    }

    public void onPlanThreeClick(View view) {

        Intent intent = new Intent(getApplicationContext(), PlanDetailActivity.class);
        intent.putExtra("value",3);
        startActivity(intent);
        finish();

    }

    public void onPlanFiveClick(View view) {

        Intent intent = new Intent(getApplicationContext(), PlanDetailActivity.class);
        intent.putExtra("value",5);
        startActivity(intent);
        finish();

    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
