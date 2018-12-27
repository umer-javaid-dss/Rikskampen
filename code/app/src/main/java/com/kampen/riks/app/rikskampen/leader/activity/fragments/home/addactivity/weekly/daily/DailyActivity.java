package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.weekly.daily;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.weekly.model.WeeklyActivityModel;

public class DailyActivity extends AppCompatActivity {


    WeeklyActivityModel  weeklyActivityModel;

    ImageView  yourPick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_activity);


        yourPick=findViewById(R.id.yourPick);


        weeklyActivityModel= (WeeklyActivityModel)getIntent().getSerializableExtra("sampleObject");

        yourPick.setImageResource(Integer.parseInt(weeklyActivityModel.getActivityPic()));

    }


    public void onBackClick(View view) {

        finish();
    }
}
