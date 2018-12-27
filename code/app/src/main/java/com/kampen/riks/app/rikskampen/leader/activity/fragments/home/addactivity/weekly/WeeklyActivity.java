package com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.weekly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.home.addactivity.weekly.adapter.WeeklyActivityAdapter;
import com.kampen.riks.app.rikskampen.utils.Constants;

public class WeeklyActivity extends AppCompatActivity {


    WeeklyActivityAdapter mAdapter;

    RecyclerView    mWeeklyActivityRV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);


        mWeeklyActivityRV=findViewById(R.id.weeklyActivityRV);

        mAdapter=new WeeklyActivityAdapter();


        mWeeklyActivityRV.setLayoutManager(Constants.getVerticalLayoutManager(WeeklyActivity.this));


        mWeeklyActivityRV.setAdapter(mAdapter);


    }

    public void onBackClick(View view) {


        finish();
    }
}
